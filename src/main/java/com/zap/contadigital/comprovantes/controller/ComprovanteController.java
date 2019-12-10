package com.zap.contadigital.comprovantes.controller;
import com.zap.contadigital.comprovantes.exception.TransacaoNaoLocalizadaException;
import com.zap.contadigital.comprovantes.service.ComprovanteService;
import com.zap.contadigital.comprovantes.service.QRCodeService;
import com.zap.contadigital.comprovantes.vo.*;
import com.zap.contadigital.vo.response.CustomErrorResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comprovantes")
public class ComprovanteController {
    @Autowired
    private ComprovanteService service;

    @ApiOperation(value = "Comprovante de pagamento de Recarga de Celular", httpMethod = "GET",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Comprovante não localizado pelo id da transação", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    @GetMapping(path = "/recarga-celular/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity< byte[]> comprovanteRecargaCelular(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteRecargaCelularVo comprovante = new ComprovanteRecargaCelularVo();
        comprovante.setIdTransacao(idTransacao);
        byte[] bytes = service.gerarComprovanteRecargaCelular(comprovante);
        return new ResponseEntity<>(bytes, HttpStatus.OK);
        // return ResponseEntity.ok().body(bytes);
    }

    @GetMapping(path = "/qrcode-templates/{cnpj}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Void> qrCodeTempletes(@PathVariable("cnpj") String cnpj) throws Exception {
        String qrCodeText = "bit.ly/ZapGanhei5";
        List<byte[]> result=service.gerarEstabelecimentoQrCodes(cnpj, qrCodeText);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/qrcode-template", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity< byte[]> qrCodeTemplate() throws Exception {
        byte[] bytes = service.gerarQrCodeContaZap("bit.ly/ZapGanhei5");
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

    @ApiOperation(value = "Comprovante de Pagamentos", httpMethod = "GET",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    @GetMapping(path = "/pagamento/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity< byte[]> comprovantePagamento(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovantePagamentoVo comprovante = new ComprovantePagamentoVo();
        comprovante.setIdTransacao(idTransacao);
        comprovante.setProtocolo("12348564");
        try {
            byte[] bytes = service.gerarComprovantePagamento(comprovante);
            return ResponseEntity.ok().body(bytes);
        }catch (TransacaoNaoLocalizadaException tnle){
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Comprovante de Transferências P2P", httpMethod = "GET",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    @GetMapping(path = "/transferencia-p2p/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity< byte[]> comprovanteTransferenciaP2p(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaP2PVo comprovante = new ComprovanteTransferenciaP2PVo();
        comprovante.setProtocolo(idTransacao);
        try {
            byte[] bytes = service.gerarComprovanteTransferenciaP2p(comprovante);
            return ResponseEntity.ok().body(bytes);
        }catch (TransacaoNaoLocalizadaException tnle){
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Comprovante de Transferências de Mesma Titularidade", httpMethod = "GET",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    @GetMapping(path = "/transferencia-mesma-titularidade/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity< byte[]> comprovanteTransferenciaMesmaTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaMesmaTitularidadeVo comprovante = new ComprovanteTransferenciaMesmaTitularidadeVo();
        comprovante.setProtocolo(idTransacao);
        try {
            byte[] bytes = service.gerarComprovanteTransferenciaMesmaTitularidade(comprovante);
            return ResponseEntity.ok().body(bytes);
        }catch (TransacaoNaoLocalizadaException tnle){
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Comprovante de Transferências de Outra Titularidade", httpMethod = "GET",response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    @GetMapping(path = "/transferencia-outra-titularidade/{idTransacao}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity< byte[]> comprovanteTransferenciaOutraTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception {
        ComprovanteTransferenciaOutraTitularidadeVo comprovante = new ComprovanteTransferenciaOutraTitularidadeVo();
        comprovante.setProtocolo(idTransacao);
        try {
            byte[] bytes = service.gerarComprovanteTransferenciaOutraTitularidade(comprovante);
            return ResponseEntity.ok().body(bytes);
        }catch (TransacaoNaoLocalizadaException tnle){
            return ResponseEntity.notFound().build();
        }

    }
}
