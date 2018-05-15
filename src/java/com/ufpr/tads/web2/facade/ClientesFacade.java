/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ClienteDAO;
import com.ufpr.tads.web2.exceptions.ClienteNaoExisteException;
import com.ufpr.tads.web2.exceptions.ErroBuscandoClienteException;
import com.ufpr.tads.web2.exceptions.ErroInserindoClienteException;
import com.ufpr.tads.web2.exceptions.ErroRemovendoClienteException;
import com.ufpr.tads.web2.exceptions.ErroUpdateClienteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArtVin
 */
public class ClientesFacade {
   public static String getErroInsertUpdate(String email){
        ClienteDAO clientes = new ClienteDAO();
        Cliente c;        
        String str;
        try {
            c = clientes.getClienteEmail(email);
            str = "E-mail invalido";
        } catch (ClienteNaoExisteException ex) {
            str = "CPF invalido";
        }
        return str;
    }
    public static boolean inserir(Cliente c) throws ErroInserindoClienteException{
        ClienteDAO clientes = new ClienteDAO();
        return clientes.insertCliente(c);
    }
    public static boolean alterar(Cliente c) throws ErroUpdateClienteException{
        ClienteDAO clientes = new ClienteDAO();
        return clientes.updateCliente(c);
    }
    public static boolean remover(int id) throws ErroRemovendoClienteException{
        ClienteDAO clientes = new ClienteDAO();
        return clientes.deleteCliente(id);
    }
    public static Cliente buscar(int id) throws ErroBuscandoClienteException{
        ClienteDAO clientes = new ClienteDAO();
        Cliente c = clientes.buscaCliente(id);
        
        return c;
    }
    
    public static List<Cliente> buscarTodos(){
        ClienteDAO clientes = new ClienteDAO();
        List<Cliente> lista = clientes.buscaClientes();
        
        return lista;
    }
}
