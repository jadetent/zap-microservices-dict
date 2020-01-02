package com.zap.contadigital.comprovantes.exception;

import com.zap.contadigital.enums.TipoInternalErrorCodesEnum;

public class TemplateNaoLocalizadoException extends ComprovanteException {

    public TemplateNaoLocalizadoException() {
        super(TipoInternalErrorCodesEnum.E422002);
    }

}


