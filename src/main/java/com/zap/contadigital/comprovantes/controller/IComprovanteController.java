package com.zap.contadigital.comprovantes.controller;

import com.zap.contadigital.comprovantes.dto.request.ComprovanteRequest;
import com.zap.contadigital.comprovantes.dto.request.ImagemRequest;
import com.zap.contadigital.comprovantes.dto.response.ComprovanteResponse;
import com.zap.contadigital.comprovantes.dto.response.ImagemResponse;
import com.zap.contadigital.vo.response.CustomErrorResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IComprovanteController {

    @ApiOperation(value = "Comprovante de pagamento de Recarga de Celular", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Comprovante não localizado pelo id da transação", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    ResponseEntity<byte[]> comprovanteRecargaCelular(@PathVariable("idTransacao") String idTransacao) throws Exception;

    @ApiOperation(value = "Comprovante de Pagamentos", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    ResponseEntity<byte[]> comprovantePagamento(@PathVariable("idTransacao") String idTransacao) throws Exception;

    @ApiOperation(value = "Comprovante de Transferências P2P", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    ResponseEntity<byte[]> comprovanteTransferenciaP2p(@PathVariable("idTransacao") String idTransacao) throws Exception;

    @ApiOperation(value = "Comprovante de Transferências de Mesma Titularidade", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    ResponseEntity<byte[]> comprovanteTransferenciaMesmaTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception;

    @ApiOperation(value = "Comprovante de Transferências de Outra Titularidade", httpMethod = "GET", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Transação não Localizada", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    ResponseEntity<byte[]> comprovanteTransferenciaOutraTitularidade(@PathVariable("idTransacao") String idTransacao) throws Exception;

    @ApiOperation(value = "Listar os últimos pagamentos realizados.", response = ComprovanteResponse.class, httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Lista de pagamentos não Localizado", response = CustomErrorResponse.class),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    ResponseEntity<ComprovanteResponse> listarPagamentosRealizados(
            @ApiParam(name = "Informações da conta e do valor desejado", required = true)
            @RequestBody @Valid ComprovanteRequest comprovanteRequest) throws Exception;

    @ApiOperation(value = "Gerar imagem do comprovante", response = ImagemResponse.class, httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    ResponseEntity<String> gerarImagem(
            @ApiParam(name = "Ptocolo da transação", required = true)
            @RequestBody @Valid ImagemRequest imagemRequest) throws Exception;

}