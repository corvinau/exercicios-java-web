/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.facade.ClientesFacade;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ArtVin
 */
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        LoginBean login = (LoginBean) session.getAttribute("loginBean");
        String action = (String) request.getAttribute("redirecionar");
        
        if(action == null){
            action = request.getParameter("action");
        }
        if(login == null || login.getNome() == null){
            RequestDispatcher rd = request.
            getRequestDispatcher("index.jsp");
            session.invalidate();
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
            rd.forward(request, response);
        }
        else{
            RequestDispatcher rd = null;
            int id;
            if(action != null){
                switch (action){
                    case "new":
                        ClientesFacade.inserir(getClientePostData(request));
                        request.setAttribute("redirecionar", "lista");
                        rd = request.getRequestDispatcher("ClientesServlet");
                        break;
                    case "formNew":
                        rd = request.getRequestDispatcher("clientesNovo.jsp");
                        break;
                    case "update" :
                        ClientesFacade.alterar(getClientePostData(request));
                        request.setAttribute("redirecionar", "lista");
                        rd = request.getRequestDispatcher("ClientesServlet");
                        break;
                    case "remove" :
                        id = Integer.parseInt(request.getParameter("id"));
                        ClientesFacade.remover(id);
                        request.setAttribute("redirecionar", "lista");
                        rd = request.getRequestDispatcher("ClientesServlet");
                        
                        break;
                    case "formUpdate" :
                        id = Integer.parseInt(request.getParameter("id"));
                        rd = request.getRequestDispatcher("clientesAlterar.jsp");
                        request.setAttribute("cliente", ClientesFacade.buscar(id));
                        break;
                    case "show" :
                        id = Integer.parseInt(request.getParameter("id"));
                        rd = request.getRequestDispatcher("clientesVisualizar.jsp");
                        request.setAttribute("cliente", ClientesFacade.buscar(id));
                        break;
                    default :
                        rd = request.getRequestDispatcher("clientesListar.jsp");
                        request.setAttribute("lista", ClientesFacade.buscarTodos());
                        break;
                }
            }
            else{
                rd = request.getRequestDispatcher("clientesListar.jsp");
                request.setAttribute("lista", ClientesFacade.buscarTodos());
            }
            rd.forward(request, response);
        }
        
        
    }
    
    private Cliente getClientePostData(HttpServletRequest request){
        Cliente c = new Cliente();
        String aux = request.getParameter("idCliente");
        if(aux != null){
            c.setIdCliente(Integer.parseInt(aux));
        }
        c.setCpfCliente((String) request.getParameter("cpf"));
        c.setNomeCliente((String) request.getParameter("nome"));
        c.setEmailCliente((String) request.getParameter("email"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setDataCliente(format.parse(request.getParameter("data")));
        } catch (ParseException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setRuaCliente((String) request.getParameter("rua"));
        c.setNrCliente(Integer.parseInt(request.getParameter("numero")));
        c.setCepCliente((String) request.getParameter("cep"));
        c.setCidadeCliente((String) request.getParameter("cidade"));
        c.setUfCliente((String) request.getParameter("uf"));
                    
        return c;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
