package com.zap.contadigital.comprovantes.exception;

import com.zap.contadigital.enums.TipoInternalErrorCodesEnum;

public class ProtocoloNaoEncontradoException extends ComprovanteException {

    public ProtocoloNaoEncontradoException() {
        super(TipoInternalErrorCodesEnum.E422003);
    }
}
