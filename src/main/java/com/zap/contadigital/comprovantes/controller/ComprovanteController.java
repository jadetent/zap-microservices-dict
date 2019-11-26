package com.zap.contadigital.comprovantes.controller;
import com.zap.contadigital.comprovantes.service.ComprovanteService;
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
        Map<String, String> parametros = new HashMap<String,String>();
        parametros.put("protocolo","F191C348-4D3D-BD5B-6B09-9DC3C9128D24");
        parametros.put("cliente","LUCIA ALVES PEREIRA SILVA");
        parametros.put("cpfCnpj","123.456.789-10");
        parametros.put("operadora","TIM - SP");
        parametros.put("telefone","(11) 99801-1234");
        parametros.put("documento","12345678");
        parametros.put("data","22/11/19");
        parametros.put("valor","R$ 20,00");

        String comprovante=service.comprovante("COMPROVANTE_P2P", parametros);
        InputStream in= new FileInputStream(new File(comprovante));
        byte[] bytes=IOUtils.toByteArray(in);
        in.close();
        return bytes;
    }


}
