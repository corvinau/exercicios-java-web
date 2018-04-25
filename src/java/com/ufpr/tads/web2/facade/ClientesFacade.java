/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ClienteDAO;
import java.util.List;

/**
 *
 * @author ArtVin
 */
public class ClientesFacade {
    public static void inserir(Cliente c){
        ClienteDAO clientes = new ClienteDAO();
        clientes.insertCliente(c);
    }
    public static void alterar(Cliente c){
        ClienteDAO clientes = new ClienteDAO();
        clientes.updateCliente(c);
    }
    public static void remover(int id){
        ClienteDAO clientes = new ClienteDAO();
        clientes.deleteCliente(id);
    }
    public static Cliente buscar(int id){
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
