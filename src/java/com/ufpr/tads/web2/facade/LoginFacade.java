/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.dao.UsuarioDAO;

/**
 *
 * @author ArtVin
 */
public class LoginFacade {
    
    public static Usuario login(String login, String senha){
        UsuarioDAO dao = new UsuarioDAO();
        return dao.getUsuario(login, senha);
    }
    
}
