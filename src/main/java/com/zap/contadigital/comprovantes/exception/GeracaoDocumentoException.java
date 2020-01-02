package com.zap.contadigital.comprovantes.exception;

import com.zap.contadigital.enums.TipoInternalErrorCodesEnum;

public class GeracaoDocumentoException extends ComprovanteException {

    public GeracaoDocumentoException() {
        super(TipoInternalErrorCodesEnum.E422001);
    }
}


