package com.zap.contadigital.comprovantes.controller.impl;

import com.zap.contadigital.comprovantes.controller.IComprovanteController;
import com.zap.contadigital.comprovantes.dto.*;
import com.zap.contadigital.comprovantes.dto.request.ComprovanteRequest;
import com.zap.contadigital.comprovantes.dto.response.ComprovanteResponse;
import com.zap.contadigital.comprovantes.exception.TransacaoNaoLocalizadaException;
import com.zap.contadigital.comprovantes.service.IComprovanteService;
import com.zap.contadigital.comprovantes.service.impl.ComprovanteService;
import com.zap.contadigital.comprovantes.service.impl.QRCodeService;
import com.zap.contadigital.comprovantes.service.IRecargaCelularService;
import com.zap.contadigital.vo.response.CustomErrorResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/comprovantes")
public class ComprovanteController implements IComprovanteController {

    @Autowired
    private QRCodeService service;

    @Autowired
    private IRecargaCelularService IRecargaCelularService;

    @Autowired
    private ComprovanteService comprovanteService;

    @Override
    @GetMapping(path = "/{idTransacao}/recarga-celular", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteRecargaCelular(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteRecargaCelularDTO comprovante = new ComprovanteRecargaCelularDTO();
        comprovante.setIdTransacao(idTransacao);
        byte[] bytes = IRecargaCelularService.gerarComprovanteRecargaCelular(comprovante);
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{idTransacao}/pagamento", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovantePagamento(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovantePagamentoDTO comprovante = new ComprovantePagamentoDTO();
        comprovante.setIdTransacao(idTransacao);
        comprovante.setProtocolo("12348564");
        try {
            byte[] bytes = service.gerarComprovantePagamento(comprovante);
            return ResponseEntity.ok().body(bytes);
        } catch (TransacaoNaoLocalizadaException tnle) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping(path = "/{idTransacao}/transferencia-p2p", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteTransferenciaP2p(@PathVariable("idTransacao") String idTransacao) throws Exception {

        ComprovanteTransferenciaP2PDTO comprovante = new ComprovanteTransferenciaP2PDTO();
        comprovante.setProtocolo(idTransacao);
        try {
            byte[] bytes = service.gerarComprovanteTransferenciaP2p(comprovante);
            return ResponseEntity.ok().body(bytes);
        } catch (TransacaoNaoLocalizadaException tnle) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping(path = "/{idTransacao}/transferencia-mesma-titularidade", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteTransferenciaMesmaTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception {

        ComprovanteTransferenciaMesmaTitularidadeDTO comprovante = new ComprovanteTransferenciaMesmaTitularidadeDTO();
        comprovante.setProtocolo(idTransacao);
        try {
            byte[] bytes = service.gerarComprovanteTransferenciaMesmaTitularidade(comprovante);
            return ResponseEntity.ok().body(bytes);
        } catch (TransacaoNaoLocalizadaException tnle) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping(path = "/{idTransacao}/transferencia-outra-titularidade", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteTransferenciaOutraTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception {

        ComprovanteTransferenciaOutraTitularidadeDTO comprovante = new ComprovanteTransferenciaOutraTitularidadeDTO();
        comprovante.setProtocolo(idTransacao);
        try {
            byte[] bytes = service.gerarComprovanteTransferenciaOutraTitularidade(comprovante);
            return ResponseEntity.ok().body(bytes);
        } catch (TransacaoNaoLocalizadaException tnle) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComprovanteResponse> listarPagamentosRealizados(
            @RequestBody @Valid ComprovanteRequest comprovanteRequest) throws Exception {
        return new ResponseEntity(comprovanteService.listarPagamentosRealizados(comprovanteRequest), HttpStatus.OK);
    }
}
