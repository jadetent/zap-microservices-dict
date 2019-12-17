package com.zap.contadigital.comprovantes.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.zap.contadigital.comprovantes.enums.EstabelecimentoTemplate;
import com.zap.contadigital.comprovantes.exception.GeracaoDocumentoException;
import com.zap.contadigital.comprovantes.exception.TemplateNaoLocalizadoException;
import com.zap.contadigital.comprovantes.exception.TransacaoNaoLocalizadaException;
import com.zap.contadigital.comprovantes.util.TemplateBuilder;
import com.zap.contadigital.comprovantes.vo.*;
import com.zap.contadigital.model.Configuracao;
import com.zap.contadigital.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

@Service
public class ComprovanteService {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;
    @Autowired
    private QRCodeService qrCodeService;
    private final String GRUPO = "ZAP-COMPROVANTES";

    public byte[] gerarTotenEstabelecimento(String cnpj, String conteudo) throws Exception {
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            byte[] qrCode = qrCodeService.createQRCode(conteudo);
            parametros.put("imagem", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(qrCode));
            List<String> templates = listarTemplates(cnpj);
            List<String> comprovantes = new ArrayList<String>();
            for (String e : templates) {
                String comprovante = gerarComprovante(e, parametros);
                comprovantes.add(comprovante);
            }
            return createByteArray(comprovantes);
        } catch (IOException e) {
            throw new GeracaoDocumentoException();
        }
    }

    private List<String> listarTemplates(String cnpj) throws Exception {
        final List<String> lista = new ArrayList<String>();
        List<Configuracao> configuracoes = listarConfiguracaoTemplates(cnpj);
        configuracoes.forEach(c -> {
            lista.add(c.getChave());
        });
        if(lista.isEmpty())
            throw  new TemplateNaoLocalizadoException();
        return lista;
    }

    private List<QRCodeEstabelecimento> gerarEstabelecimentoQrCodes(String cnpj, String conteudo) throws Exception {
        final List<QRCodeEstabelecimento> lista = new ArrayList<QRCodeEstabelecimento>();
        List<Configuracao> configuracoes = listarConfiguracaoTemplates(cnpj);
        configuracoes.forEach(c -> {
            lista.add(new QRCodeEstabelecimento(c.getChave(), conteudo));
        });
        if(lista.isEmpty())
            throw  new TemplateNaoLocalizadoException();
        return lista;
    }

    private List<Configuracao> listarConfiguracaoTemplates(String cnpj) {
        EstabelecimentoTemplate template = null;
        for (EstabelecimentoTemplate estabelecimento : EstabelecimentoTemplate.values()) {
            Configuracao config = configuracaoRepository.findOne(estabelecimento.name(), GRUPO);
            if (config.getValor().contains(cnpj)) {
                template = estabelecimento;
                break;
            }
        }
        List<Configuracao> templates = configuracaoRepository.findByChaveLike(template.getGrupoTemplate());
        return templates;
    }

    public byte[] gerarQrCodeContaZap(String conteudo) throws Exception {
        return gerarQrCode("CONTAZAP_QRCODE_ESTATICO", conteudo);
    }

    public byte[] gerarQrCode(String template, String conteudo) throws Exception {
        Map<String, Object> parametros = new HashMap<String, Object>();
        byte[] qrCode = qrCodeService.createQRCode(conteudo);
        //File file = qrCodeService.createQRCodeFile(conteudo);
        //parametros.put("qrcode", file.getAbsolutePath());
        //parametros.put("base64", "data:image/jpeg;base64," + imagem);
        parametros.put("imagem", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(qrCode));
        return comprovanteByteArray(template, parametros);
    }

    public byte[] gerarComprovanteRecargaCelular(ComprovanteRecargaCelularVo comprovante) throws Exception {
        if (comprovante.getIdTransacao().equals("999"))
            throw new TransacaoNaoLocalizadaException(comprovante.getIdTransacao());

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("transacao", comprovante.getIdTransacao());
        parametros.put("protocolo", comprovante.getProtocolo());
        parametros.put("cliente", "LUCIA ALVES PEREIRA SILVA");
        parametros.put("terminal", "228005");
        parametros.put("agente", "238804");
        parametros.put("autenticacao", "07656");
        parametros.put("nsu", "610761");
        parametros.put("produto", "TIM - SP");
        parametros.put("cpfCnpj", "123.456.789-10");
        parametros.put("telefone", "(11) 99801-1234");
        parametros.put("documento", "12345678");
        parametros.put("data", "22/11/19");
        parametros.put("valor", "R$ 35,00");
        return comprovanteByteArray("COMPROVANTE_RECARGA_CELULAR", parametros);
    }

    public byte[] gerarComprovantePagamento(ComprovantePagamentoVo comprovante) throws Exception {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("protocolo", comprovante.getProtocolo());
        parametros.put("transacao", comprovante.getIdTransacao());
        parametros.put("cliente", "LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj", "123.456.789-10");
        parametros.put("terminal", "228005");
        parametros.put("agente", "228005");
        parametros.put("autenticacao", "03207");
        parametros.put("autorizacao", "065474");
        parametros.put("banco", "BANCO BRADESCO S.A.");
        parametros.put("codigoBarras", "23793.38128  60014.678225 56000.063307  5 80810000002000");
        parametros.put("beneficiario", alinhamento("BENEFICIARIO AMBIENTE HOMOLOGA"));
        parametros.put("cpfCnpjBeneficiario", alinhamento("21.568.259/0001-00"));
        parametros.put("pagador", alinhamento("PAGADOR AMBIENTE HOMOLOGACAO"));
        parametros.put("cpfCnpjPagador", alinhamento("96.906.497/0001-00"));
        parametros.put("dataVencimento", alinhamento("22/11/2019"));
        parametros.put("dataPagamento", alinhamento("21/11/2019"));
        parametros.put("valorTitulo", alinhamento("20,00"));
        parametros.put("valorCobrado", alinhamento("20,00"));
        return comprovanteByteArray("COMPROVANTE_PAGAMENTO", parametros);
    }

    public byte[] gerarComprovanteTransferenciaP2p(ComprovanteTransferenciaP2PVo comprovante) throws Exception {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("protocolo", comprovante.getProtocolo());
        parametros.put("transacao", comprovante.getProtocolo());
        parametros.put("cliente", "LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj", "123.456.789-10");
        parametros.put("telefone", alinhamento("(11) - 98564 - 1574"));
        parametros.put("data", alinhamento("22/11/2019"));
        parametros.put("valor", alinhamento("20,00"));
        return comprovanteByteArray("COMPROVANTE_P2P", parametros);
    }

    public byte[] gerarComprovanteTransferenciaMesmaTitularidade(ComprovanteTransferenciaMesmaTitularidadeVo comprovante) throws Exception {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("protocolo", comprovante.getProtocolo());
        parametros.put("transacao", comprovante.getProtocolo());
        parametros.put("cliente", "LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj", "123.456.789-10");
        parametros.put("favorecido", "LUCIA ALVES PEREIRA SILVA");
        parametros.put("banco", "BRANCO DO BRASIL");
        parametros.put("agencia", "0765-4");
        parametros.put("conta", "4165-9");
        parametros.put("cpfCnpjFavorecido", "123.456.789-10");
        parametros.put("valor", "R$ 100,00");
        parametros.put("data", "22/11/2019");
        return comprovanteByteArray("COMPROVANTE_MESMA_TITULARIDADE", parametros);
    }

    public byte[] gerarComprovanteTransferenciaOutraTitularidade(ComprovanteTransferenciaOutraTitularidadeVo comprovante) throws Exception {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("protocolo", comprovante.getProtocolo());
        parametros.put("transacao", comprovante.getProtocolo());
        parametros.put("cliente", "LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj", "123.456.789-10");
        parametros.put("favorecido", "MARIA LUZIA NOGUEIRA");
        parametros.put("banco", "BRANCO DO BRASIL");
        parametros.put("agencia", "0546-4");
        parametros.put("conta", "3351-9");
        parametros.put("cpfCnpjFavorecido", "123.456.789-11");
        parametros.put("valor", "R$ 100,00");
        parametros.put("data", "22/11/2019");
        return comprovanteByteArray("COMPROVANTE_OUTRA_TITULARIDADE", parametros);
    }

    private String alinhamento(String texto) {
        return String.format("%40s", texto.substring(0, Math.min(40, texto.length())));
    }


    private byte[] comprovanteByteArray(String nomeChave, Map<String, Object> parametros) throws Exception {
        String conteudo = gerarComprovante(nomeChave, parametros);
        System.getenv("ITEXT7_LICENSEKEY" + "/itextkey-html2pdf_typography.xml");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            createByteArray(conteudo.toString(), outputStream);
        } catch (IOException e) {
            throw new GeracaoDocumentoException();
        }
        return outputStream.toByteArray();
    }

    private String gerarComprovante(String nomeChave, Map<String, Object> parametros) throws Exception {
        String template = configuracaoRepository.findOne(nomeChave, GRUPO).getValor();
        TemplateBuilder templateBuilder = new TemplateBuilder();
        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            templateBuilder.setParametro(entry.getKey(), entry.getValue());
        }
        String conteudo = templateBuilder.getConteudo(template);
        return conteudo;
    }

    private void createByteArray(String html, OutputStream outputStream) throws IOException {
        HtmlConverter.convertToPdf(html, outputStream);
    }

    private byte[] createByteArray(List<String> comprovantes) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        ConverterProperties properties = new ConverterProperties();
        properties.setBaseUri(tempDir);
        File tempFile = new File(tempDir, "qrcode" + new Date().getTime() + ".pdf");
        PdfWriter writer = new PdfWriter(tempFile);
        PdfDocument pdf = new PdfDocument(writer);
        PdfMerger merger = new PdfMerger(pdf);
        for (String html : comprovantes) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfDocument temp = new PdfDocument(new PdfWriter(baos));
            PageSize pageSize = PageSize.A4;
            temp.setDefaultPageSize(pageSize);
            HtmlConverter.convertToPdf(html, temp, properties);
            temp = new PdfDocument(new PdfReader(new ByteArrayInputStream(baos.toByteArray())));
            merger.merge(temp, 1, temp.getNumberOfPages());
            temp.close();
        }
        pdf.close();
        byte[] bytes = Files.readAllBytes(tempFile.toPath());
        tempFile.delete();
        return bytes;
    }

    private String comprovanteFile(String nomeChave, Map<String, String> parametros) throws Exception {
        String template = configuracaoRepository.findOne(nomeChave, GRUPO).getValor();
        TemplateBuilder templateBuilder = new TemplateBuilder();

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            templateBuilder.setParametro(entry.getKey(), entry.getValue());
        }

        String conteudo = templateBuilder.getConteudo(template);
        String pdf = "/home/comprovantes/comprovante.pdf";
        System.getenv("ITEXT7_LICENSEKEY" + "/itextkey-html2pdf_typography.xml");
        try {
            createPdfFile(conteudo.toString(), pdf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdf;
    }

    private void createPdfFile(String html, String dest) throws IOException {
        HtmlConverter.convertToPdf(html, new FileOutputStream(dest));
    }

}
