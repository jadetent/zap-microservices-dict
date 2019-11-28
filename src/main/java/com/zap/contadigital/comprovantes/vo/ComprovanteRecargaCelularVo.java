package com.zap.contadigital.comprovantes.vo;

public class ComprovanteRecargaCelularVo {
    private String idTransacao;
    private String protocolo;
    private String transacao;
    private String dataHora;
    private String operadora;
    private String nsuOperadora;
    private String terminal;
    private String agente;
    private String autenticacao;
    private String valor;
    private String mensagem;

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public String getTransacao() {
        return transacao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getOperadora() {
        return operadora;
    }

    public String getNsuOperadora() {
        return nsuOperadora;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getAgente() {
        return agente;
    }

    public String getAutenticacao() {
        return autenticacao;
    }

    public String getValor() {
        return valor;
    }

    public String getMensagem() {
        return mensagem;
    }
}
