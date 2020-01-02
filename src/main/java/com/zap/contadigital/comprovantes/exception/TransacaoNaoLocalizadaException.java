package com.zap.contadigital.comprovantes.exception;

import com.zap.contadigital.enums.TipoInternalErrorCodesEnum;

public class TransacaoNaoLocalizadaException extends ComprovanteException {

    public TransacaoNaoLocalizadaException(String idTransacao) {
        super(TipoInternalErrorCodesEnum.E422000, idTransacao);
    }

}

