/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.TipoAtendimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArtVin
 */
public class TipoAtendimentoDAO {
    
    Connection con;
    ResultSet rs;
    
    public TipoAtendimentoDAO(){
         try {
            con = ConnectionFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public TipoAtendimento getTipoAtendimento(int idTipoAtendimento){
        TipoAtendimento tipoAtendimento = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_tipo_atendimento, nome_tipo_atendimento"
                            + " from tb_tipo_atendimento"
                            + " where id_tipo_atendimento = ?"
                    );
            st.setInt(1, idTipoAtendimento);
            
            rs = st.executeQuery();

            while (rs.next()) {
                tipoAtendimento = new TipoAtendimento();
                tipoAtendimento.setIdTipoAtendimento(rs.getInt("id_tipo_atendimento"));
                tipoAtendimento.setNomeTipoAtendimento(rs.getString("nome_tipo_atendimento"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return tipoAtendimento;
    }
    
    public List<TipoAtendimento> getListaTipoAtendimento(){
        List<TipoAtendimento> listaTipoAtendimento = new ArrayList<TipoAtendimento>();
        TipoAtendimento tipoAtendimento = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_tipo_atendimento, nome_tipo_atendimento"
                            + " from tb_tipo_atendimento"
                    );
            
            rs = st.executeQuery();

            while (rs.next()) {
                
                tipoAtendimento = new TipoAtendimento();
                tipoAtendimento.setIdTipoAtendimento(rs.getInt("id_tipo_atendimento"));
                tipoAtendimento.setNomeTipoAtendimento(rs.getString("nome_tipo_atendimento"));
                listaTipoAtendimento.add(tipoAtendimento);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("asdasd" + listaTipoAtendimento.get(0));
        return listaTipoAtendimento;
    }
}
