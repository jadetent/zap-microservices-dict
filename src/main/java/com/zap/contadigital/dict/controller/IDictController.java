package com.zap.contadigital.dict.controller;

import com.zap.contadigital.dict.dto.request.PreCadastroRequest;
import com.zap.contadigital.dict.dto.response.ImagemResponse;
import com.zap.contadigital.vo.response.CustomErrorResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IDictController {


    @ApiOperation(value = "Gerar imagem do comprovante", response = ImagemResponse.class, httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Código da falha: 500.000 = Erro interno sem causa mapeada.", response = CustomErrorResponse.class)
    })
    ResponseEntity<String> preCadastro(
            @ApiParam(name = "Dados do usuário", required = true)
            @RequestBody @Valid PreCadastroRequest preCadastroRequest) throws Exception;

}