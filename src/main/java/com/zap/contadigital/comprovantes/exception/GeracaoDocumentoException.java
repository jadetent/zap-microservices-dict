package com.zap.contadigital.comprovantes.exception;

import com.zap.contadigital.exception.ContaException;
import static com.zap.contadigital.enums.TipoInternalErrorCodesEnum.E422001;

public class GeracaoDocumentoException extends ContaException {
    public GeracaoDocumentoException() {
        super(E422001);
    }
}

