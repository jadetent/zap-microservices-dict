package com.zap.contadigital.comprovantes.exception;

import com.zap.contadigital.exception.ContaException;

import static com.zap.contadigital.enums.TipoInternalErrorCodesEnum.E422001;
import static com.zap.contadigital.enums.TipoInternalErrorCodesEnum.E422002;

public class TemplateNaoLocalizadoException extends ContaException {
    public TemplateNaoLocalizadoException() {
        super(E422002);
    }
}


