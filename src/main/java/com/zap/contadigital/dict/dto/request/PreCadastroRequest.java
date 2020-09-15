package com.zap.contadigital.dict.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreCadastroRequest {

    @ApiModelProperty(value = "Número do telefone", required = true, example = "5511999999999")
    @NotNull
    private String telefone;

    @ApiModelProperty(value = "CPF / CNPJ", required = true, example = "60734254571")
    @NotNull
    private String codigoPessoa;

    @ApiModelProperty(value = "email", required = true, example = "email@email.com")
    @NotNull
    private String email;
}
