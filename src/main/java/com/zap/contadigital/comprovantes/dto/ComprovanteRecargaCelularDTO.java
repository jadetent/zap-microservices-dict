package com.zap.contadigital.comprovantes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComprovanteRecargaCelularDTO {

    private String idTransacao;
    private String protocolo;
    private String dataHora;
    private String operadora;
    private String nsuOperadora;
    private String terminal;
    private String agente;
    private String autenticacao;
    private String valor;
    private String mensagem;

}
