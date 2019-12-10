package com.zap.contadigital.comprovantes.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class QRCodeEstabelecimento {
    @ApiModelProperty(value = "Constante na lista de templates nas configurações", required = true, example = "CAMISA12_QRCODE_TEMPLATE1")
    @NotNull
    private String template;
    @ApiModelProperty(value = "Conteúdo que deseja ser processado pelo Qr Code", required = true, example = "bit.ly/ZapGanhei5")
    @NotNull
    private String conteudo;

    public QRCodeEstabelecimento() {
    }

    public QRCodeEstabelecimento(String template, String conteudo) {
        this.template = template;
        this.conteudo = conteudo;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
