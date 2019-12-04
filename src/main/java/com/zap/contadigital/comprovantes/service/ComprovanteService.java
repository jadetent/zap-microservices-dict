package com.zap.contadigital.comprovantes.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.xmp.impl.Base64;
import com.zap.contadigital.comprovantes.exception.TransacaoNaoLocalizadaException;
import com.zap.contadigital.comprovantes.util.TemplateBuilder;
import com.zap.contadigital.comprovantes.vo.*;
import com.zap.contadigital.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ComprovanteService {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;
    @Autowired
    private QRCodeService qrCodeService;
    @Value("${imagem}")
    private String imagem;
    private final String GRUPO="ZAP-COMPROVANTES";
    public byte[] gerarQrCodeEstabelecimento(String conteudo) throws Exception {
        Map<String, Object> parametros = new HashMap<String,Object>();
        byte[] qrCode=qrCodeService.createQRCode(conteudo);
        File file = qrCodeService.createQRCodeFile(conteudo);
        parametros.put("qrcode", file.getAbsolutePath());
        parametros.put("base64", "data:image/jpeg;base64," + imagem);
        return comprovanteByteArray("ESTABELECIMENTO_QRCODE", parametros);
    }
    public byte[] gerarComprovanteRecargaCelular(ComprovanteRecargaCelularVo comprovante) throws Exception {
        if(comprovante.getIdTransacao().equals("999"))
            throw new TransacaoNaoLocalizadaException(comprovante.getIdTransacao());

        Map<String, Object> parametros = new HashMap<String,Object>();
        parametros.put("transacao",comprovante.getIdTransacao());
        parametros.put("protocolo",comprovante.getProtocolo());
        parametros.put("cliente","LUCIA ALVES PEREIRA SILVA");
        parametros.put("terminal","228005");
        parametros.put("agente","238804");
        parametros.put("autenticacao","07656");
        parametros.put("nsu","610761");
        parametros.put("produto","TIM - SP");
        parametros.put("cpfCnpj","123.456.789-10");
        parametros.put("telefone","(11) 99801-1234");
        parametros.put("documento","12345678");
        parametros.put("data","22/11/19");
        parametros.put("valor","R$ 35,00");
        return comprovanteByteArray("COMPROVANTE_RECARGA_CELULAR", parametros);
    }
    public byte[] gerarComprovantePagamento(ComprovantePagamentoVo comprovante) throws Exception {
        Map<String, Object> parametros = new HashMap<String,Object>();
        parametros.put("protocolo",comprovante.getProtocolo());
        parametros.put("transacao",comprovante.getIdTransacao());
        parametros.put("cliente","LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj","123.456.789-10");
        parametros.put("terminal","228005");
        parametros.put("agente","228005");
        parametros.put("autenticacao","03207");
        parametros.put("autorizacao","065474");
        parametros.put("banco","BANCO BRADESCO S.A.");
        parametros.put("codigoBarras","23793.38128  60014.678225 56000.063307  5 80810000002000");
        parametros.put("beneficiario",alinhamento("BENEFICIARIO AMBIENTE HOMOLOGA"));
        parametros.put("cpfCnpjBeneficiario",alinhamento("21.568.259/0001-00"));
        parametros.put("pagador",alinhamento("PAGADOR AMBIENTE HOMOLOGACAO"));
        parametros.put("cpfCnpjPagador",alinhamento("96.906.497/0001-00"));
        parametros.put("dataVencimento",alinhamento("22/11/2019"));
        parametros.put("dataPagamento",alinhamento("21/11/2019"));
        parametros.put("valorTitulo",alinhamento("20,00"));
        parametros.put("valorCobrado",alinhamento("20,00"));
        return comprovanteByteArray("COMPROVANTE_PAGAMENTO", parametros);
    }

    public byte[] gerarComprovanteTransferenciaP2p(ComprovanteTransferenciaP2PVo comprovante) throws Exception {
        Map<String, Object> parametros = new HashMap<String,Object>();
        parametros.put("protocolo",comprovante.getProtocolo());
        parametros.put("transacao",comprovante.getProtocolo());
        parametros.put("cliente","LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj","123.456.789-10");
        parametros.put("telefone",alinhamento("(11) - 98564 - 1574"));
        parametros.put("data",alinhamento("22/11/2019"));
        parametros.put("valor",alinhamento("20,00"));
        return comprovanteByteArray("COMPROVANTE_P2P", parametros);
    }

    public byte[] gerarComprovanteTransferenciaMesmaTitularidade(ComprovanteTransferenciaMesmaTitularidadeVo comprovante) throws Exception {
        Map<String, Object> parametros = new HashMap<String,Object>();
        parametros.put("protocolo",comprovante.getProtocolo());
        parametros.put("transacao",comprovante.getProtocolo());
        parametros.put("cliente","LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj","123.456.789-10");
        parametros.put("favorecido","LUCIA ALVES PEREIRA SILVA");
        parametros.put("banco","BRANCO DO BRASIL");
        parametros.put("agencia","0765-4");
        parametros.put("conta","4165-9");
        parametros.put("cpfCnpjFavorecido","123.456.789-10");
        parametros.put("valor","R$ 100,00");
        parametros.put("data","22/11/2019");
        return comprovanteByteArray("COMPROVANTE_MESMA_TITULARIDADE", parametros);
    }

    public byte[] gerarComprovanteTransferenciaOutraTitularidade(ComprovanteTransferenciaOutraTitularidadeVo comprovante) throws Exception {
        Map<String, Object> parametros = new HashMap<String,Object>();
        parametros.put("protocolo",comprovante.getProtocolo());
        parametros.put("transacao",comprovante.getProtocolo());
        parametros.put("cliente","LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj","123.456.789-10");
        parametros.put("favorecido","MARIA LUZIA NOGUEIRA");
        parametros.put("banco","BRANCO DO BRASIL");
        parametros.put("agencia","0546-4");
        parametros.put("conta","3351-9");
        parametros.put("cpfCnpjFavorecido","123.456.789-11");
        parametros.put("valor","R$ 100,00");
        parametros.put("data","22/11/2019");
        return comprovanteByteArray("COMPROVANTE_OUTRA_TITULARIDADE", parametros);
    }

    private String alinhamento(String texto){
        return String.format("%40s",texto.substring(0,Math.min(40,texto.length())));
    }

    private byte[] comprovanteByteArray(String nomeChave, Map<String, Object> parametros)  throws Exception {
        String template = configuracaoRepository.findOne(nomeChave,GRUPO).getValor();
        TemplateBuilder templateBuilder = new TemplateBuilder();

        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            templateBuilder.setParametro(entry.getKey(), entry.getValue());
        }
        String conteudo=templateBuilder.getConteudo(template);
        System.getenv("ITEXT7_LICENSEKEY" + "/itextkey-html2pdf_typography.xml");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            createByteArray(conteudo.toString(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    private void createByteArray(String html, OutputStream outputStream) throws IOException {
        HtmlConverter.convertToPdf(html, outputStream);
    }

    private String comprovanteFile(String nomeChave, Map<String, String> parametros)  throws Exception {
        String template = configuracaoRepository.findOne(nomeChave,GRUPO).getValor();
        TemplateBuilder templateBuilder = new TemplateBuilder();

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            templateBuilder.setParametro(entry.getKey(), entry.getValue());
        }

        String conteudo=templateBuilder.getConteudo(template);
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
