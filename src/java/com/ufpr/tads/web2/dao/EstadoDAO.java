/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Estado;
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
public class EstadoDAO {
    
    Connection con;
    ResultSet rs;
    
    public EstadoDAO(){
         try {
            con = ConnectionFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Estado getEstado(int idEstado){
        Estado estado = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_estado, nome_estado, sigla_estado"
                            + " from tb_estado"
                            + " where id_estado = ?"
                    );
            st.setInt(1, idEstado);
            
            rs = st.executeQuery();

            
            while (rs.next()) {
                estado = new Estado();
                estado.setIdEstado(idEstado);
                estado.setNomeEstado(rs.getString("nome_estado"));
                estado.setSiglaEstado(rs.getString("sigla_estado"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return estado;
    }
    public List<Estado> getListaEstado(){
        List<Estado> listaEstados = new ArrayList<Estado>();
        Estado estado = null;
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_estado, nome_estado, sigla_estado"
                            + " from tb_estado"
                    );
            
            rs = st.executeQuery();

            
            while (rs.next()) {
                estado = new Estado();
                estado.setIdEstado(rs.getInt("id_estado"));
                estado.setNomeEstado(rs.getString("nome_estado"));
                estado.setSiglaEstado(rs.getString("sigla_estado"));
                listaEstados.add(estado);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaEstados;
    }
    
}
