/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
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
public class ClienteDAO {
    Connection con;
    ResultSet rs;
    
    public ClienteDAO() {
        try {
            con = ConnectionFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Cliente> buscaClientes(){
        List<Cliente> lista = new ArrayList<Cliente>();
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_cliente, cpf_cliente,"
                            + " nome_cliente, email_cliente,"
                            + " data_cliente, rua_cliente,"
                            + " nr_cliente, cep_cliente,"
                            + " cidade_cliente, uf_cliente"
                            + " from tb_cliente"
                    );
            
            rs = st.executeQuery();

            Cliente c = null;
            while (rs.next()) {
                c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setCpfCliente(rs.getString("cpf_cliente"));
                c.setNomeCliente(rs.getString("nome_cliente"));
                c.setEmailCliente(rs.getString("email_cliente"));
                c.setDataCliente(new java.util.Date(rs.getDate("data_cliente").getTime()));
                c.setRuaCliente(rs.getString("rua_cliente"));
                c.setNrCliente(rs.getInt("nr_cliente"));
                c.setCepCliente(rs.getString("cep_cliente"));
                c.setCidadeCliente(rs.getString("cidade_cliente"));
                c.setUfCliente(rs.getString("uf_cliente"));
                lista.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

            
        
        return lista;
    }
    public Cliente buscaCliente(int id){
        Cliente c = null;
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_cliente, cpf_cliente,"
                            + " nome_cliente, email_cliente,"
                            + " data_cliente, rua_cliente,"
                            + " nr_cliente, cep_cliente,"
                            + " cidade_cliente, uf_cliente"
                            + " from tb_cliente"
                            + " where id_cliente = ?"
                    );
            st.setInt(1, id);
            
            rs = st.executeQuery();

            
            while (rs.next()) {
                c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setCpfCliente(rs.getString("cpf_cliente"));
                c.setNomeCliente(rs.getString("nome_cliente"));
                c.setEmailCliente(rs.getString("email_cliente"));
                c.setDataCliente(rs.getDate("data_cliente"));
                c.setRuaCliente(rs.getString("rua_cliente"));
                c.setNrCliente(rs.getInt("nr_cliente"));
                c.setCepCliente(rs.getString("cep_cliente"));
                c.setCidadeCliente(rs.getString("cidade_cliente"));
                c.setUfCliente(rs.getString("uf_cliente"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
    public boolean deleteCliente(int id){
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "delete from tb_cliente where id_cliente = ?"
                    );
            st.setInt(1, id);
            
            st.executeUpdate();

            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    public boolean updateCliente(Cliente c){
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "update tb_cliente "
                            + " set cpf_cliente=?, nome_cliente=?, email_cliente=?, "
                            + " data_cliente=?, rua_cliente=?, nr_cliente=?, "
                            + " cep_cliente=?, cidade_cliente=?, uf_cliente=? "
                            + " where id_cliente=?"
                    );
            st.setString(1, c.getCpfCliente());
            st.setString(2, c.getNomeCliente());
            st.setString(3, c.getEmailCliente());
            st.setDate(4, new java.sql.Date(c.getDataCliente().getTime()));
            st.setString(5, c.getRuaCliente());
            st.setInt(6, c.getNrCliente());
            st.setString(7, c.getCepCliente());
            st.setString(8, c.getCidadeCliente());
            st.setString(9, c.getUfCliente());
            st.setInt(10, c.getIdCliente());
            st.executeUpdate();

            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    public boolean insertCliente(Cliente c){
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "insert into tb_cliente(cpf_cliente,nome_cliente,email_cliente,data_cliente,"
                            + " rua_cliente, nr_cliente,cep_cliente, cidade_cliente,uf_cliente) "
                            + " values(?,?,?,?,?,?,?,?,?)"
                    );
            st.setString(1, c.getCpfCliente());
            st.setString(2, c.getNomeCliente());
            st.setString(3, c.getEmailCliente());
            st.setDate(4, new java.sql.Date(c.getDataCliente().getTime()));
            st.setString(5, c.getRuaCliente());
            st.setInt(6, c.getNrCliente());
            st.setString(7, c.getCepCliente());
            st.setString(8, c.getCidadeCliente());
            st.setString(9, c.getUfCliente());
           
            st.executeUpdate();

            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
