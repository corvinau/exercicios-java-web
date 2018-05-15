/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.beans;

import java.util.Date;

/**
 *
 * @author ArtVin
 */
public class Atendimento {
    private int idAtendimento;
    private Date dtHrAtendimento;
    private String dscAtendimento;
    private Produto produtoAtendimento;
    private TipoAtendimento tipoAtendimento;
    private Usuario usuarioAtendimento;
    private Cliente clienteAtendimento;
    private char resAtendimento;

    public int getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(int idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Date getDtHrAtendimento() {
        return dtHrAtendimento;
    }

    public void setDtHrAtendimento(Date dtHrAtendimento) {
        this.dtHrAtendimento = dtHrAtendimento;
    }

    public String getDscAtendimento() {
        return dscAtendimento;
    }

    public void setDscAtendimento(String dscAtendimento) {
        this.dscAtendimento = dscAtendimento;
    }

    public Produto getProdutoAtendimento() {
        return produtoAtendimento;
    }

    public void setProdutoAtendimento(Produto produtoAtendimento) {
        this.produtoAtendimento = produtoAtendimento;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public Usuario getUsuarioAtendimento() {
        return usuarioAtendimento;
    }

    public void setUsuarioAtendimento(Usuario usuarioAtendimento) {
        this.usuarioAtendimento = usuarioAtendimento;
    }

    public char getResAtendimento() {
        return resAtendimento;
    }

    public void setResAtendimento(char resAtendimento) {
        this.resAtendimento = resAtendimento;
    }

    public Cliente getClienteAtendimento() {
        return clienteAtendimento;
    }

    public void setClienteAtendimento(Cliente clienteAtendimento) {
        this.clienteAtendimento = clienteAtendimento;
    }
    
    
    
}
