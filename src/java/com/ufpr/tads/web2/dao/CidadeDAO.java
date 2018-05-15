/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cidade;
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
public class CidadeDAO {
    
    Connection con;
    ResultSet rs;
    
    public CidadeDAO(){
         try {
            con = ConnectionFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Cidade getCidade(int idCidade){
        Cidade cidade = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_cidade, nome_cidade, id_estado"
                            + " from tb_cidade"
                            + " where id_cidade = ?"
                    );
            st.setInt(1, idCidade);
            
            rs = st.executeQuery();

            EstadoDAO estadoDAO = new EstadoDAO();
            while (rs.next()) {
                cidade = new Cidade();
                cidade.setIdCidade(rs.getInt("id_cidade"));
                cidade.setNomeCidade(rs.getString("nome_cidade"));
                cidade.setEstado(estadoDAO.getEstado(rs.getInt("id_estado")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cidade;
    }
    
    public List<Cidade> getListaCidade(int idEstado){
        List<Cidade> listaCidade = new ArrayList<Cidade>();
        Cidade cidade = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_cidade, nome_cidade, id_estado"
                            + " from tb_cidade"
                            + " where id_estado = ?"
                    );
            st.setInt(1, idEstado);
            
            rs = st.executeQuery();

            while (rs.next()) {
                cidade = new Cidade();
                cidade.setIdCidade(rs.getInt("id_cidade"));
                cidade.setNomeCidade(rs.getString("nome_cidade"));
                cidade.setEstado(null);
                listaCidade.add(cidade);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaCidade;
    }
}
