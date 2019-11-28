package com.zap.contadigital.comprovantes.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.zap.contadigital.comprovantes.util.TemplateBuilder;
import com.zap.contadigital.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.Map;

@Service
public class ComprovanteService {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    private final String GRUPO="ZAP-COMPROVANTES";

    public String comprovante(String nomeChave, Map<String, String> parametros)  throws Exception {
        String template = configuracaoRepository.findOne("COMPROVANTE_P2P",GRUPO).getValor();
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
    public byte[] comprovanteByteArray(String nomeChave, Map<String, String> parametros)  throws Exception {
        String template = configuracaoRepository.findOne("COMPROVANTE_P2P",GRUPO).getValor();
        TemplateBuilder templateBuilder = new TemplateBuilder();

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            templateBuilder.setParametro(entry.getKey(), entry.getValue());
        }
        String conteudo=templateBuilder.getConteudo(template);
        String pdf = "/home/comprovantes/comprovante.pdf";
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
    private void createPdfFile(String html, String dest) throws IOException {
        HtmlConverter.convertToPdf(html, new FileOutputStream(dest));
    }
}
