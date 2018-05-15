/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Produto;
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
public class ProdutoDAO {
    
    Connection con;
    ResultSet rs;
    
    public ProdutoDAO(){
         try {
            con = ConnectionFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Produto getProduto(int idProduto){
        Produto produto = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_produto, nome_produto"
                            + " from tb_produto"
                            + " where id_produto = ?"
                    );
            st.setInt(1, idProduto);
            
            rs = st.executeQuery();

            while (rs.next()) {
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return produto;
    }
    
    public List<Produto> getListaProduto(){
        List<Produto> listaProduto = new ArrayList<Produto>();
        Produto produto = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_produto, nome_produto"
                            + " from tb_produto"
                    );
            
            rs = st.executeQuery();

            while (rs.next()) {
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                listaProduto.add(produto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaProduto;
    }
}
