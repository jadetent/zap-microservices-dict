package com.zap.contadigital.comprovantes.controller;
import com.zap.contadigital.comprovantes.service.ComprovanteService;
import com.zap.contadigital.comprovantes.vo.ComprovanteRecargaCelularVo;
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
    @Autowired
    private ComprovanteService service;

    @GetMapping(
            value = "/recarga-celular",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] recargaCelular() throws Exception {
        ComprovanteRecargaCelularVo comprovante = new ComprovanteRecargaCelularVo();
        comprovante.setIdTransacao("A7EE72D0-ECFD-7AB3-791A-FDB7CC01AE9C");
        byte[] bytes=service.gerarComprovanteRecargaCelular(comprovante);
        return bytes;
    }


}
