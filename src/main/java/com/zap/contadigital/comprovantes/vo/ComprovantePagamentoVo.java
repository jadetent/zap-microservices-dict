package com.zap.contadigital.comprovantes.vo;

public class ComprovantePagamentoVo {
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

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(String autenticacao) {
        this.autenticacao = autenticacao;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getBeneficiarioCpfCnpj() {
        return beneficiarioCpfCnpj;
    }

    public void setBeneficiarioCpfCnpj(String beneficiarioCpfCnpj) {
        this.beneficiarioCpfCnpj = beneficiarioCpfCnpj;
    }

    public String getPagador() {
        return pagador;
    }

    public void setPagador(String pagador) {
        this.pagador = pagador;
    }

    public String getPagadorCpfCnpj() {
        return pagadorCpfCnpj;
    }

    public void setPagadorCpfCnpj(String pagadorCpfCnpj) {
        this.pagadorCpfCnpj = pagadorCpfCnpj;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getValorTitulo() {
        return valorTitulo;
    }

    public void setValorTitulo(String valorTitulo) {
        this.valorTitulo = valorTitulo;
    }

    public String getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(String valorCobrado) {
        this.valorCobrado = valorCobrado;
    }
}
