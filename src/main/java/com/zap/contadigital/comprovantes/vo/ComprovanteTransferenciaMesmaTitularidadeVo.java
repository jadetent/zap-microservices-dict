package com.zap.contadigital.comprovantes.vo;

public class ComprovanteTransferenciaMesmaTitularidadeVo {
    private String protocolo;
    private String cliente;
    private String cpfCnpj;
    private String favorecido;
    private String banco;
    private String agencia;
    private String cpfCnpjFavorecido;
    private String valor;
    private String data;

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getCpfCnpjFavorecido() {
        return cpfCnpjFavorecido;
    }

    public void setCpfCnpjFavorecido(String cpfCnpjFavorecido) {
        this.cpfCnpjFavorecido = cpfCnpjFavorecido;
    }
}
