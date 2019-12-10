package com.zap.contadigital.comprovantes.enums;

public enum EstabelecimentoTemplate {
    CAMISA12_ESTABELECIMENTOS,
    GAVIOES_ESTABELECIMENTOS;
    public String getEstabelecimento(){
        return this.name().split("\\_")[0];
    }
}
