package com.zap.contadigital.comprovantes.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ImagemRequest {

    @ApiModelProperty(value = "Número do telefone", required = true, example = "5511999999999")
    @NotNull
    private String telefone;

    @ApiModelProperty(value = "Protocolo da transação", required = true, example = "0003958517")
    @NotNull
    private String protocolo;
}
