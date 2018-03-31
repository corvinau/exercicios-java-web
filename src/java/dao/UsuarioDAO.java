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
/**
 *
 * @author Tatiane
 */
public class UsuarioDAO {
    Connection con = null;
    ResultSet rs = null;

    public UsuarioDAO() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuario", "root", "root");
    }
    
    public Usuario getUsuario(String login, String senha) throws SQLException {
        
        if(login != null && senha != null){
            PreparedStatement st = con.prepareStatement(
                "select id_usuario, login_usuario, nome_usuario, senha_usuario from tb_usuario where login_usuario = '"+login+"'"
                        );
            //            "select id_usuario, login_usuario, nome_usuario, senha_usuario from tb_usuario where login_usuario = '?'"


            rs = st.executeQuery();

            Usuario u = null;
            while (rs.next()) {
                u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setLogin_usuario(rs.getString("login_usuario"));
                u.setNome_usuario(rs.getString("nome_usuario"));
                u.setSenha_usuario(rs.getString("senha_usuario"));
            }
            if( u != null && senha.equals(u.getSenha_usuario()) ){
                return u;

            }
        }
        return null;
    }
}
