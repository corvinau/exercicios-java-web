/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatiane
 */
public class UsuarioDAO {

    private Connection con = null;
    private ResultSet rs = null;

    public UsuarioDAO(){
        try {
            con = ConnectionFactory.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario getUsuario(String login, String senha) {
        if (login != null && senha != null) {
            try {
                PreparedStatement st;

                st = con.prepareStatement(
                        "select id_usuario, login_usuario, nome_usuario, senha_usuario from tb_usuario where login_usuario = '" + login + "'"
                );
                rs = st.executeQuery();

                Usuario u = null;
                while (rs.next()) {
                    u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setLoginUsuario(rs.getString("login_usuario"));
                    u.setNomeUsuario(rs.getString("nome_usuario"));
                    u.setSenhaUsuario(rs.getString("senha_usuario"));
                }
                MessageDigest algorithm = MessageDigest.getInstance("MD5");
                byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
                StringBuilder hexString = new StringBuilder();
                for (byte b : messageDigest) {
                    hexString.append(String.format("%02X", 0xFF & b));
                }
                String senhaHex = hexString.toString();
                if (u != null && senhaHex.equals(u.getSenhaUsuario())) {
                    return u;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public Usuario getUsuario(int idUsuario) { 
        Usuario u = null;
        try {
            PreparedStatement st;

            st = con.prepareStatement(
                    "select id_usuario, login_usuario, nome_usuario from tb_usuario where id_usuario = ?"
            );
            st.setInt(1, idUsuario);
            
            rs = st.executeQuery();
            
            while (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setLoginUsuario(rs.getString("login_usuario"));
                u.setNomeUsuario(rs.getString("nome_usuario"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;
    }
    
    public void insertUsuario(Usuario u) {
        try {
            PreparedStatement st;

            st = con.prepareStatement(
                    "insert into tb_usuario(login_usuario, nome_usuario, senha_usuario) values (?,?,?)"
            );
            st.setString(1, u.getLoginUsuario());
            st.setString(2, u.getNomeUsuario());
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(u.getSenhaUsuario().getBytes("UTF-8"));
            String hexSenha = "";
            for (byte b : messageDigest) {
                hexSenha += (String.format("%02X", 0xFF & b));
            }
            st.setString(3, hexSenha);
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
