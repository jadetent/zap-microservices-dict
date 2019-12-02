package com.zap.contadigital.comprovantes.exception;

public class TransacaoNaoLocalizadaException extends  Exception {
    public TransacaoNaoLocalizadaException(String id){
        super(String.format("Não existe transação com o id %s informado ", id));
    }
}
