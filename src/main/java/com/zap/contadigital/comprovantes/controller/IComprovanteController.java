package com.zap.contadigital.comprovantes.controller;

import com.zap.contadigital.comprovantes.dto.request.ComprovanteRequest;
import com.zap.contadigital.comprovantes.dto.response.ComprovanteResponse;
import com.zap.contadigital.vo.response.CustomErrorResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IComprovanteController {

    @ApiOperation(value = "Listar os últimos pagamentos realizados.", response = ComprovanteResponse.class, httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "HttpStatus 400 = Falhas do Produto. \n" +
                    "Código da falha: 400.000 = Número do telefone informado não é valido: [NUMERO DO TELEFONE].\n" +
                    "Código da falha: 400.001 = Número do telefone não pode ser vazio.\n" +
                    "Código da falha: 400.003 = Erro ao gerar a chave de acesso para a conta digital.\n" +
                    "Código da falha: 413.000 = Número máximo de contatos excedido.\n" +
                    "Código da falha: 413.100 = Contato não encontrado: [NUMERO DO TELEFONE].\n" +
                    "Código da falha: 413.103 = Conta não encontrada pelo telefone: [NUMERO DO TELEFONE].\n" +
                    "Código da falha: 413.108 = A conta informada possui mais de um endereço primário cadastrado.\n" +
                    "Código da falha: 413.109 = A conta informada não possui endereço cadastrado na API da conta digital.\n" +
                    "Código da falha: 419.000 = O valor [VALOR INFORMADO] está abaixo do valor minimo de recarga ([VALOR MINIMO DE RECARGA]).\n" +
                    "Código da falha: 419.001 = O valor [VALOR INFORMADO] excede o valor máximo de recargas no més ([VALOR MAXIMO DE RECARGA]), valor total de recargas no mês até o momento: [VALOR DE RECARGA NO MES].\n" +
                    "Código da falha: 441.002 = Botkey não informado. \n" +
                    "Código da falha: 460.XXX = Erros genéricos da conta digital.", response = CustomErrorResponse.class
            ),
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada", response = CustomErrorResponse.class)
    })
    ResponseEntity<ComprovanteResponse> listarPagamentosRealizados(
            @ApiParam(name = "Informações da conta e do valor desejado", required = true)
            @RequestBody @Valid ComprovanteRequest comprovanteRequest) throws Exception;

}