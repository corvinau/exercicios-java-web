/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.exceptions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
                            + " id_cidade"
                            + " from tb_cliente"
                    );
            
            rs = st.executeQuery();
            CidadeDAO cidadeDao = new CidadeDAO();
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
                c.setCidadeCliente(cidadeDao.getCidade(rs.getInt("id_cidade")));
                lista.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

            
        
        return lista;
    }

    /**
     *
     * @param id
     * @return
     */
    public Cliente buscaCliente(int id) throws ErroBuscandoClienteException {
        Cliente c = null;
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_cliente, cpf_cliente,"
                            + " nome_cliente, email_cliente,"
                            + " data_cliente, rua_cliente,"
                            + " nr_cliente, cep_cliente,"
                            + " id_cidade"
                            + " from tb_cliente"
                            + " where id_cliente = ?"
                    );
            st.setInt(1, id);
            
            rs = st.executeQuery();

            CidadeDAO cidadeDao = new CidadeDAO();
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
                c.setCidadeCliente(cidadeDao.getCidade(rs.getInt("id_cidade")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if( c == null){
            throw new ErroBuscandoClienteException();
        }
        return c;
    }
    public boolean deleteCliente(int id) throws ErroRemovendoClienteException{
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
        throw new ErroRemovendoClienteException();
        
    }
    public boolean updateCliente(Cliente c) throws ErroUpdateClienteException{
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "update tb_cliente "
                            + " set cpf_cliente=?, nome_cliente=?, email_cliente=?, "
                            + " data_cliente=?, rua_cliente=?, nr_cliente=?, "
                            + " cep_cliente=?, id_cidade=? "
                            + " where id_cliente=?"
                    );
            if(c.getCpfCliente() == null){
                return false;
            }
            st.setString(1, c.getCpfCliente());
            st.setString(2, c.getNomeCliente());
            st.setString(3, c.getEmailCliente());
            st.setDate(4, new java.sql.Date(c.getDataCliente().getTime()));
            st.setString(5, c.getRuaCliente());
            st.setInt(6, c.getNrCliente());
            st.setString(7, c.getCepCliente());
            st.setInt(8, c.getCidadeCliente().getIdCidade());
            st.setInt(9, c.getIdCliente());
            st.executeUpdate();

            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new ErroUpdateClienteException();
    }
    public boolean insertCliente(Cliente c) throws ErroInserindoClienteException{
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "insert into tb_cliente(cpf_cliente,nome_cliente,email_cliente,data_cliente,"
                            + " rua_cliente, nr_cliente,cep_cliente, id_cidade) "
                            + " values(?,?,?,?,?,?,?,?)"
                    );
            if(c.getCpfCliente() == null){
                return false;
            }
            st.setString(1, c.getCpfCliente());
            st.setString(2, c.getNomeCliente());
            st.setString(3, c.getEmailCliente());
            st.setDate(4, new java.sql.Date(c.getDataCliente().getTime()));//c.getDataCliente().getTime()
            st.setString(5, c.getRuaCliente());
            st.setInt(6, c.getNrCliente());
            st.setString(7, c.getCepCliente());
            st.setInt(8, c.getCidadeCliente().getIdCidade());
            st.executeUpdate();

            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new ErroInserindoClienteException();
    }
    public Cliente getClienteEmail(String email) throws ClienteNaoExisteException{
       Cliente c = null;
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select id_cliente, cpf_cliente,"
                            + " nome_cliente, email_cliente,"
                            + " data_cliente, rua_cliente,"
                            + " nr_cliente, cep_cliente,"
                            + " id_cidade"
                            + " from tb_cliente"
                            + " where email_cliente = ?"
                    );
            st.setString(1, email);
            
            rs = st.executeQuery();

            CidadeDAO cidadeDao = new CidadeDAO();
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
                c.setCidadeCliente(cidadeDao.getCidade(rs.getInt("id_cidade")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(c == null){
            throw new ClienteNaoExisteException();
        }
        return c;
    }
    
}
