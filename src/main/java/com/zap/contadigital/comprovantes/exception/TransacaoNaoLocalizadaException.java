package com.zap.contadigital.comprovantes.exception;

import com.zap.contadigital.exception.ContaException;
import static com.zap.contadigital.enums.TipoInternalErrorCodesEnum.E422000;

public class TransacaoNaoLocalizadaException extends ContaException {
    public TransacaoNaoLocalizadaException(String idTransacao) {
        super(E422000, idTransacao);
    }
}

