/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.dao.AtendimentoDAO;
import com.ufpr.tads.web2.dao.ProdutoDAO;
import com.ufpr.tads.web2.dao.TipoAtendimentoDAO;
import com.ufpr.tads.web2.exceptions.ErroBuscandoClienteException;
import java.util.List;

/**
 *
 * @author ArtVin
 */
public class AtendimentoFacade {
    
    public static List<Atendimento> getListaAtendimento() throws ErroBuscandoClienteException{
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        return atendimentoDAO.getListaAtendimento();
    }
    public static List<Produto> getListaProduto(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.getListaProduto();
    }
    public static Atendimento getAtendimento(int id) throws ErroBuscandoClienteException{
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        return atendimentoDAO.getAtendimento(id);
    }
    public static List<TipoAtendimento> getListaTipoAtendimento(){
        TipoAtendimentoDAO tipoAtendimentoDAO = new TipoAtendimentoDAO();
        
        return tipoAtendimentoDAO.getListaTipoAtendimento();
    }

    public static boolean insertAtendimento(Atendimento a) {
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        return atendimentoDAO.insertAtendimento(a);
    }
}
