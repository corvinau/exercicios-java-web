/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.beans.Estado;
import com.ufpr.tads.web2.dao.CidadeDAO;
import com.ufpr.tads.web2.dao.EstadoDAO;
import java.util.List;

/**
 *
 * @author ArtVin
 */
public class CidadeEstadoFacade {
    
    static public List<Cidade> getListaCidade (int idEstado){
        CidadeDAO dao = new CidadeDAO();
        
        return dao.getListaCidade(idEstado);
    }
    static public Cidade getCidade(int idCidade){
        CidadeDAO dao = new CidadeDAO();
        
        return dao.getCidade(idCidade);
    }
    static public List<Estado> getListaEstado(){
        EstadoDAO dao = new EstadoDAO();
        
        return dao.getListaEstado();
    }
    
}
