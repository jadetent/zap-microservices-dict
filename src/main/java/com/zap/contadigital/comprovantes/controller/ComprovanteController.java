package com.zap.contadigital.comprovantes.controller;

import com.zap.contadigital.comprovantes.service.ComprovanteService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comprovantes")
public class ComprovanteController {
    @Autowired
    private ComprovanteService service;
    @GetMapping("/text")
    public @ResponseBody String getText() {
        return "Hello world";
    }
    @GetMapping(value = "/pdf")
    public @ResponseBody byte[] getPdf() throws IOException {
        InputStream in = new FileInputStream(new File("/dev/comprovante.pdf"));
        //.getResourceAsStream("/dev/comprovante.pdf");
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/pdf-media",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] getPdfWithMediaType() throws IOException {
        InputStream in = new FileInputStream(new File("/dev/comprovante.pdf"));
        return IOUtils.toByteArray(in);
    }

    @GetMapping(
            value = "/estatico-pdf",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] transferencia() throws Exception {
        service.getLogo();
        File file = new File("/home/comprovantes", "transferencia.pdf");
        InputStream in= new FileInputStream(file);;
        return IOUtils.toByteArray(in);
        /*
        File file = new File("home", "comprovante.pdf");
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            // adicionando um parágrafo ao documento
            document.add(new Paragraph("Gerando um PDF usando iText em Java"));
            // adicionando um parágrafo com fonte diferente ao arquivo
            document.add(new Paragraph("Adicionando outro paragrafo", FontFactory.getFont(FontFactory.COURIER, 12)));
            document.close();
            in = new FileInputStream(file);
        } catch(DocumentException de) {
            System.err.println(de.getMessage());
        } catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            document.close();
        }

        */

    }

}
