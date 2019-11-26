package com.zap.contadigital.comprovantes.controller;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zap.contadigital.comprovantes.service.ComprovanteService;
import com.zap.contadigital.comprovantes.service.ComprovanteServiceIText;
import com.zap.contadigital.comprovantes.util.TemplateBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/comprovantes")
public class ComprovanteController {
    File file = new File("/home/comprovantes", "transferencia.pdf");
    @Autowired
    private ComprovanteServiceIText serviceText;
    @Autowired
    private ComprovanteService service;
    @GetMapping("/text")
    public @ResponseBody String getText() {
        return "Hello world";
    }
    @GetMapping(value = "/pdf")
    public @ResponseBody byte[] getPdf() throws IOException {
        InputStream in = new FileInputStream(file);
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/pdf-media",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] getPdfWithMediaType() throws IOException {
        InputStream in = new FileInputStream(file);
        return IOUtils.toByteArray(in);
    }

    @GetMapping(
            value = "/other",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] other() throws Exception {
        String comprovante = serviceText.gerarComprovanteOutroFavorecido();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/same",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] same() throws Exception {
        String comprovante = serviceText.gerarComprovanteMesmoFavorecido();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/p2p",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] p2p() throws Exception {
        String comprovante = serviceText.gerarComprovanteP2P();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/pagamento",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] pagamento() throws Exception {
        String comprovante = serviceText.gerarComprovantePagamento();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/recarga/bilhete",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] recargaBilhete() throws Exception {
        String comprovante = serviceText.gerarComprovanteRecargaBilheteUnico();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/recarga/celular",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] recargaCelular() throws Exception {
        String comprovante = serviceText.gerarComprovanteRecargaCelular();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/html",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] html() throws Exception {
        TemplateBuilder templateBuilder = new TemplateBuilder();
        templateBuilder.setParametro("titulo", "CONTA ZAP");
        templateBuilder.setParametro("setor", "RECIBO");
        templateBuilder.setParametro("assunto", "Comprovante");
        templateBuilder.setParametro("mensagem", "Recibo de Pagamentos");
        String conteudo=templateBuilder.getConteudo();
        String html = "/home/comprovantes/comprovante.html";
        String pdf = "/home/comprovantes/comprovante.pdf";
        Writer fileWriter = new FileWriter(html);
        fileWriter.write(conteudo);
        fileWriter.close();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(pdf));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(html));
        document.close();
        InputStream in= new FileInputStream(new File(pdf));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/p2p-template",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] p2pTemplate() throws Exception {
        Map<String, String> parametros = new HashMap<String,String>();
        parametros.put("protocolo","12331197098090910990");
        parametros.put("cliente","LUCIA ALVES PEREIRA");
        parametros.put("cpfCnpj","123.456.789-10");
        parametros.put("operadora","TIM - SP");
        String comprovante=service.comprovanteTexto("COMPROVANTE_P2P", parametros);
        InputStream in= new FileInputStream(new File(comprovante));
        byte[] bytes=IOUtils.toByteArray(in);
        in.close();
        return bytes;
    }


}
