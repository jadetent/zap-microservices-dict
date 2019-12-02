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
            value = "/recarga-celular/{idTransacao}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )

    public @ResponseBody byte[] comprovanteRecargaCelular(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteRecargaCelularVo comprovante = new ComprovanteRecargaCelularVo();
        comprovante.setIdTransacao(idTransacao);
        byte[] bytes=service.gerarComprovanteRecargaCelular(comprovante);
        return bytes;
    }

    @GetMapping(
            value = "/pagamento/{idTransacao}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )

    public @ResponseBody byte[] comprovantePagamento(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovantePagamentoVo comprovante = new ComprovantePagamentoVo();
        comprovante.setIdTransacao(idTransacao);
        comprovante.setProtocolo("12348564");
        byte[] bytes=service.gerarComprovantePagamento(comprovante);
        return bytes;
    }

    @GetMapping(
            value = "/transferencia-p2p/{idTransacao}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )

    public @ResponseBody byte[] comprovanteTransferenciaP2p(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaP2PVo comprovante = new ComprovanteTransferenciaP2PVo();
        comprovante.setProtocolo(idTransacao);
        byte[] bytes=service.gerarComprovanteTransferenciaP2p(comprovante);
        return bytes;
    }

    @GetMapping(
            value = "/transferencia-mesma-titularidade/{idTransacao}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )

    public @ResponseBody byte[] comprovanteTransferenciaMesmaTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaMesmaTitularidadeVo comprovante = new ComprovanteTransferenciaMesmaTitularidadeVo();
        comprovante.setProtocolo(idTransacao);
        byte[] bytes=service.gerarComprovanteTransferenciaMesmaTitularidade(comprovante);
        return bytes;
    }

    @GetMapping(
            value = "/transferencia-outra-titularidade/{idTransacao}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )

    public @ResponseBody byte[] comprovanteTransferenciaOutraTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaOutraTitularidadeVo comprovante = new ComprovanteTransferenciaOutraTitularidadeVo();
        comprovante.setProtocolo(idTransacao);
        byte[] bytes=service.gerarComprovanteTransferenciaOutraTitularidade(comprovante);
        return bytes;
    }
}
