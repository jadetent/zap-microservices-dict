package com.zap.contadigital.comprovantes.controller;

import com.zap.contadigital.comprovantes.exception.TransacaoNaoLocalizadaException;
import com.zap.contadigital.comprovantes.service.ComprovanteService;
import com.zap.contadigital.comprovantes.vo.*;
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

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/comprovantes")
public class ComprovanteController {
    @Autowired
    private ComprovanteService service;

    @ApiOperation(value = "Comprovante de pagamento de Recarga de Celular", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Comprovante não localizado pelo id da transação", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    @GetMapping(path = "/recarga-celular/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteRecargaCelular(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteRecargaCelularVo comprovante = new ComprovanteRecargaCelularVo();
        comprovante.setIdTransacao(idTransacao);
        byte[] bytes = service.gerarComprovanteRecargaCelular(comprovante);
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

    @ApiOperation(value = "Geração do pdf com o template e conteúdo para QRCode de estabelecimento", httpMethod = "POST", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "HttpStatus 400 = Falhas do Produto. \n" +
                    "Código da falha: 422.001 = Erro na geração do documento.\n" +
                    "Código da falha: 422.002 = Template não localizado para este estabelecimento.\n"
            ),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.")
    })
    @GetMapping(path = "/qrcode-estabelecimento/{cnpj}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> qrCodeEstabelecimento(@PathVariable("cnpj") String cnpj, @PathParam("conteudo") String conteudo) throws Exception {
        byte[] bytes = service.gerarTotenEstabelecimento(cnpj, conteudo);
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

    @ApiOperation(value = "Comprovante de Pagamentos", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    @GetMapping(path = "/pagamento/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovantePagamento(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovantePagamentoVo comprovante = new ComprovantePagamentoVo();
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
    @GetMapping(path = "/transferencia-p2p/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteTransferenciaP2p(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaP2PVo comprovante = new ComprovanteTransferenciaP2PVo();
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
    @GetMapping(path = "/transferencia-mesma-titularidade/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteTransferenciaMesmaTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaMesmaTitularidadeVo comprovante = new ComprovanteTransferenciaMesmaTitularidadeVo();
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
    @GetMapping(path = "/transferencia-outra-titularidade/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> comprovanteTransferenciaOutraTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaOutraTitularidadeVo comprovante = new ComprovanteTransferenciaOutraTitularidadeVo();
        comprovante.setProtocolo(idTransacao);
        try {
            byte[] bytes = service.gerarComprovanteTransferenciaOutraTitularidade(comprovante);
            return ResponseEntity.ok().body(bytes);
        } catch (TransacaoNaoLocalizadaException tnle) {
            return ResponseEntity.notFound().build();
        }

    }
}
