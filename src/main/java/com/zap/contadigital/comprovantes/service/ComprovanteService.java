package com.zap.contadigital.comprovantes.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zap.contadigital.comprovantes.util.TemplateBuilder;
import com.zap.contadigital.repository.ConfiguracaoRepository;
import org.apache.commons.io.IOUtils;
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
        String html = "/home/comprovantes/comprovante.html";
        String pdf = "/home/comprovantes/comprovante.pdf";
        Writer fileWriter = new FileWriter(html);
        fileWriter.write(conteudo);
        fileWriter.close();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(pdf));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(html));
        document.close();
        return pdf;
    }
    public String comprovanteTexto(String nomeChave, Map<String, String> parametros)  throws Exception {
        String template = configuracaoRepository.findOne("COMPROVANTE_P2P",GRUPO).getValor();
        TemplateBuilder templateBuilder = new TemplateBuilder();

        for (Map.Entry<String, String> entry : parametros.entrySet()) {
            templateBuilder.setParametro(entry.getKey(), entry.getValue());
        }
        String conteudo=templateBuilder.getConteudo(template);
        String pdf = "/home/comprovantes/comprovante.pdf";
         /*
        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, new FileOutputStream(pdf));
        document.open();
        //document.addAuthor("author");
        //document.addSubject("subject");
        //document.addCreationDate();
        //document.addTitle("title");
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(conteudo));
        document.close();
         */
        OutputStream file = new FileOutputStream(new File(pdf));
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, file);
        document.open();
        InputStream is = new ByteArrayInputStream(conteudo.getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
        document.close();
        file.close();
        return pdf;
    }
}
