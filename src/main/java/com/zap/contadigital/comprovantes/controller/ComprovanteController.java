package com.zap.contadigital.comprovantes.controller;

import com.zap.contadigital.comprovantes.dto.*;
import com.zap.contadigital.comprovantes.exception.TransacaoNaoLocalizadaException;
import com.zap.contadigital.comprovantes.service.ComprovanteService;
import com.zap.contadigital.comprovantes.service.RecargaCelularService;
import com.zap.contadigital.vo.response.CustomErrorResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/comprovantes")
public class ComprovanteController {

    @Autowired
    private ComprovanteService service;

    @Autowired
    private RecargaCelularService recargaCelularService;


    @ApiOperation(value = "Comprovante de pagamento de Recarga de Celular", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Comprovante não localizado pelo id da transação", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    @GetMapping(path = "/{idTransacao}/recarga-celular", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteRecargaCelular(@PathVariable("idTransacao") String idTransacao) throws Exception {

        ComprovanteRecargaCelularDTO comprovante = new ComprovanteRecargaCelularDTO();
        comprovante.setIdTransacao(idTransacao);
        byte[] bytes = recargaCelularService.gerarComprovanteRecargaCelular(comprovante);
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

    @ApiOperation(value = "Comprovante de Pagamentos", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
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

    @ApiOperation(value = "Comprovante de Transferências P2P", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
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

    @ApiOperation(value = "Comprovante de Transferências de Mesma Titularidade", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
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

    @ApiOperation(value = "Comprovante de Transferências de Outra Titularidade", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
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
}
