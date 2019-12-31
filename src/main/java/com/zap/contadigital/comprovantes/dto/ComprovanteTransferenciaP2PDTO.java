package com.zap.contadigital.comprovantes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComprovanteTransferenciaP2PDTO {

    private String protocolo;
    private String cliente;
    private String cpfCnpj;
    private String telefone;
    private String valor;
    private String data;

}
