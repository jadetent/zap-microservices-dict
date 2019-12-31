package com.zap.contadigital.comprovantes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComprovantePagamentoDTO {

    private String idTransacao;
    private String protocolo;
    private String terminal;
    private String agente;
    private String autenticacao;
    private String autorizacao;
    private String banco;
    private String codigoBarras;
    private String beneficiario;
    private String beneficiarioCpfCnpj;
    private String pagador;
    private String pagadorCpfCnpj;
    private String dataVencimento;
    private String dataPagamento;
    private String valorTitulo;
    private String valorCobrado;

}
