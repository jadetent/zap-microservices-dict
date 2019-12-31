package com.zap.contadigital.comprovantes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComprovanteTransferenciaMesmaTitularidadeDTO {

    private String protocolo;
    private String cliente;
    private String cpfCnpj;
    private String favorecido;
    private String banco;
    private String agencia;
    private String cpfCnpjFavorecido;
    private String valor;
    private String data;

}
