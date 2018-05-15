/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.exceptions.ErroBuscandoClienteException;
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
public class AtendimentoDAO {
    
    Connection con;
    ResultSet rs;
    
    public AtendimentoDAO(){
         try {
            con = ConnectionFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean insertAtendimento(Atendimento a){
        
        try {
            PreparedStatement st;

            st = con.prepareStatement(
                    "insert into tb_atendimento"
                            + "(dt_hr_atendimento, dsc_atendimento, res_atendimento,"
                            + " id_produto, id_tipo_atendimento,id_usuario,id_cliente) "
                            + "values (?,?,?,?,?,?,?)"
            );
            st.setTimestamp(1, new java.sql.Timestamp(a.getDtHrAtendimento().getTime()));
            st.setString(2, a.getDscAtendimento());
            st.setString(3, "" + a.getResAtendimento());
            st.setInt(4, a.getProdutoAtendimento().getIdProduto());
            st.setInt(5, a.getTipoAtendimento().getIdTipoAtendimento());
            st.setInt(6, a.getUsuarioAtendimento().getIdUsuario());
            st.setInt(7, a.getClienteAtendimento().getIdCliente());
       
            if(st.executeUpdate()!= 0) return true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
        
        
        return false;
    }
    
    public boolean updateAtendimento(Atendimento a){
        
        try {
            PreparedStatement st;

            st = con.prepareStatement(
                    "update tb_atendimento"
                            + "dt_hr_atendimento = ? , dsc_atendimento = ?, res_atendimento = ?,"
                            + "where id_atendimento = ?"
            );
            st.setDate(1, new java.sql.Date(a.getDtHrAtendimento().getTime()));
            st.setString(2, a.getDscAtendimento());
            st.setString(3, "" + a.getResAtendimento());
            st.setInt(4, a.getIdAtendimento());
            
       
            if(st.executeUpdate()!= 0) return true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
        
        
        return false;
    }
    
    public Atendimento getAtendimento(int idAtendimento) throws ErroBuscandoClienteException{
        Atendimento atendimento = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select    id_atendimento, dt_hr_atendimento,dsc_atendimento,"
                            + "id_produto,id_tipo_atendimento,id_usuario,"
                            + "id_cliente,res_atendimento"
                            + " from tb_atendimento"
                            + " where id_atendimento = ?"
                    );
            st.setInt(1, idAtendimento);
            
            rs = st.executeQuery();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            TipoAtendimentoDAO tipoAtendimentoDAO = new TipoAtendimentoDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            while (rs.next()) {
                atendimento = new Atendimento();
                atendimento.setIdAtendimento(rs.getInt("id_produto"));
                atendimento.setDtHrAtendimento(rs.getTimestamp("dt_hr_atendimento"));
                atendimento.setDscAtendimento(rs.getString("dsc_atendimento"));
                atendimento.setResAtendimento(rs.getString("res_atendimento").charAt(0));
                atendimento.setUsuarioAtendimento(usuarioDAO.getUsuario(rs.getInt("id_usuario")));
                atendimento.setClienteAtendimento(clienteDAO.buscaCliente(rs.getInt("id_cliente")));
                atendimento.setTipoAtendimento(tipoAtendimentoDAO.getTipoAtendimento(rs.getInt("id_tipo_atendimento")));
                atendimento.setProdutoAtendimento(produtoDAO.getProduto(rs.getInt("id_produto")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return atendimento;
    }
    
    public List<Atendimento> getListaAtendimento() throws ErroBuscandoClienteException{
        List<Atendimento> listaAtendimento = new ArrayList<Atendimento>();
        Atendimento atendimento = null;
        
        PreparedStatement st;
        try {
            st = con.prepareStatement(
                    "select    id_atendimento, dt_hr_atendimento,dsc_atendimento,"
                            + "id_produto,id_tipo_atendimento,id_usuario,"
                            + "id_cliente,res_atendimento "
                            + "from tb_atendimento"
                    );
            
            rs = st.executeQuery();
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            TipoAtendimentoDAO tipoAtendimentoDAO = new TipoAtendimentoDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            while (rs.next()) {
                atendimento = new Atendimento();
                atendimento.setIdAtendimento(rs.getInt("id_atendimento"));
                atendimento.setDtHrAtendimento(rs.getTimestamp("dt_hr_atendimento"));
                atendimento.setDscAtendimento(rs.getString("dsc_atendimento"));
                atendimento.setResAtendimento(rs.getString("res_atendimento").charAt(0));
                atendimento.setUsuarioAtendimento(usuarioDAO.getUsuario(rs.getInt("id_usuario")));
                atendimento.setClienteAtendimento(clienteDAO.buscaCliente(rs.getInt("id_cliente")));
                atendimento.setTipoAtendimento(tipoAtendimentoDAO.getTipoAtendimento(rs.getInt("id_tipo_atendimento")));
                atendimento.setProdutoAtendimento(produtoDAO.getProduto(rs.getInt("id_produto")));
                listaAtendimento.add(atendimento);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaAtendimento;
    }
}
