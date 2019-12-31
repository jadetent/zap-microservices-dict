package com.zap.contadigital.comprovantes.exception;

import com.zap.contadigital.enums.TipoInternalErrorCodesEnum;
import com.zap.contadigital.exception.CodigoDeErroException;

public abstract class ComprovanteException extends CodigoDeErroException {

    protected ComprovanteException(TipoInternalErrorCodesEnum errorcode) {
        super(errorcode);
    }

    protected ComprovanteException(TipoInternalErrorCodesEnum errorcode, Object... args) {
        super(errorcode, args);
    }
}
