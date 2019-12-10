package com.zap.contadigital.comprovantes.enums;

public enum EstabelecimentoTemplate {
    CAMISA12_ESTABELECIMENTOS,
    GAVIOES_ESTABELECIMENTOS;
    public String getGrupoTemplate(){
        return this.name().split("\\_")[0] + "_QRCODE";
    }
}
