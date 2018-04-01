/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Usuario;
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
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setLogin_usuario(rs.getString("login_usuario"));
                    u.setNome_usuario(rs.getString("nome_usuario"));
                    u.setSenha_usuario(rs.getString("senha_usuario"));
                }
                if (u != null && senha.equals(u.getSenha_usuario())) {
                    return u;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
