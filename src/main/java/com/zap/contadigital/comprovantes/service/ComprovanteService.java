package com.zap.contadigital.comprovantes.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.zap.contadigital.comprovantes.util.TemplateBuilder;
import com.zap.contadigital.comprovantes.vo.ComprovanteRecargaCelularVo;
import com.zap.contadigital.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ComprovanteService {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    private final String GRUPO="ZAP-COMPROVANTES";
    public byte[] gerarComprovanteRecargaCelular(ComprovanteRecargaCelularVo comprovante) throws Exception {
        Map<String, String> parametros = new HashMap<String,String>();
        parametros.put("transacao","A7EE72D0-ECFD-7AB3-791A-FDB7CC01AE9C");
        parametros.put("protocolo","0003206729");
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
    private byte[] comprovanteByteArray(String nomeChave, Map<String, String> parametros)  throws Exception {
        String template = configuracaoRepository.findOne(nomeChave,GRUPO).getValor();
        TemplateBuilder templateBuilder = new TemplateBuilder();

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
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
