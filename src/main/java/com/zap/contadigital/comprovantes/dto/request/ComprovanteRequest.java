package com.zap.contadigital.comprovantes.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ComprovanteRequest {

    @ApiModelProperty(value = "Número do telefone", required = true, example = "5511999999999")
    @NotNull
    private String telefone;

    @ApiModelProperty(value = "CPF / CNPJ", required = true, example = "60734254571")
    @NotNull
    private String codigoPessoa;

}