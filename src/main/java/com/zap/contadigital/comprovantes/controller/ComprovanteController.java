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
    File file = new File("/home/comprovantes", "transferencia.pdf");
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
        String comprovante = service.gerarComprovanteOutroFavorecido();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/same",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] same() throws Exception {
        String comprovante = service.gerarComprovanteMesmoFavorecido();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/p2p",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] p2p() throws Exception {
        String comprovante = service.gerarComprovanteP2P();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/pagamento",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] pagamento() throws Exception {
        String comprovante = service.gerarComprovantePagamento();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/recarga/bilhete",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] recargaBilhete() throws Exception {
        String comprovante = service.gerarComprovanteRecargaBilheteUnico();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
    @GetMapping(
            value = "/recarga/celular",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] recargaCelular() throws Exception {
        String comprovante = service.gerarComprovanteRecargaCelular();
        InputStream in= new FileInputStream(new File(comprovante));;
        return IOUtils.toByteArray(in);
    }
}
