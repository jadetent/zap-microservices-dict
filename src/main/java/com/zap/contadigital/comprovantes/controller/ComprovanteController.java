package com.zap.contadigital.comprovantes.controller;
import com.zap.contadigital.comprovantes.service.ComprovanteService;
import com.zap.contadigital.comprovantes.vo.*;
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
    public @ResponseBody byte[] comprovanteRecargaCelular() throws Exception {
        ComprovanteRecargaCelularVo comprovante = new ComprovanteRecargaCelularVo();
        comprovante.setIdTransacao("A7EE72D0-ECFD-7AB3-791A-FDB7CC01AE9C");

        byte[] bytes=service.gerarComprovanteRecargaCelular(comprovante);
        return bytes;
    }

    @GetMapping(
            value = "/pagamento",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] comprovantePagamento() throws Exception {
        ComprovantePagamentoVo comprovante = new ComprovantePagamentoVo();
        comprovante.setIdTransacao("A7EE72D0-ECFD-7AB3-791A-FDB7CC01AE9C");
        comprovante.setProtocolo("12348564");
        byte[] bytes=service.gerarComprovantePagamento(comprovante);
        return bytes;
    }
    @GetMapping(
            value = "/transferencia-p2p",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] comprovanteTransferenciaP2p() throws Exception {
        ComprovanteTransferenciaP2PVo comprovante = new ComprovanteTransferenciaP2PVo();
        comprovante.setProtocolo("A7EE72D0-ECFD-7AB3-791A-FDB7CC01AE9C");
        byte[] bytes=service.gerarComprovanteTransferenciaP2p(comprovante);
        return bytes;
    }
    @GetMapping(
            value = "/transferencia-mesma-titularidade",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] comprovanteTransferenciaMesmaTitularidade() throws Exception {
        ComprovanteTransferenciaMesmaTitularidadeVo comprovante = new ComprovanteTransferenciaMesmaTitularidadeVo();
        comprovante.setProtocolo("A7EE72D0-ECFD-7AB3-791A-FDB7CC01AE9C");
        byte[] bytes=service.gerarComprovanteTransferenciaMesmaTitularidade(comprovante);
        return bytes;
    }

    @GetMapping(
            value = "/transferencia-outra-titularidade",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] comprovanteTransferenciaOutraTitularidade() throws Exception {
        ComprovanteTransferenciaOutraTitularidadeVo comprovante = new ComprovanteTransferenciaOutraTitularidadeVo();
        comprovante.setProtocolo("A7EE72D0-ECFD-7AB3-791A-FDB7CC01AE9C");
        byte[] bytes=service.gerarComprovanteTransferenciaOutraTitularidade(comprovante);
        return bytes;
    }
}
