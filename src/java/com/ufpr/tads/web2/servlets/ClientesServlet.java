/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.exceptions.ErroBuscandoClienteException;
import com.ufpr.tads.web2.exceptions.ErroInserindoClienteException;
import com.ufpr.tads.web2.exceptions.ErroRemovendoClienteException;
import com.ufpr.tads.web2.exceptions.ErroUpdateClienteException;
import com.ufpr.tads.web2.facade.CidadeEstadoFacade;
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
            Cliente c;
            if(action != null){
                switch (action){
                    case "new":
                        c = getClientePostData(request);
                        try {
                            if(ClientesFacade.inserir(c) == true){
                                request.setAttribute("redirecionar", "lista");
                                rd = request.getRequestDispatcher("ClientesServlet");
                            }
                            else{
                                rd = request.getRequestDispatcher("portal.jsp");
                            }
                        } catch (ErroInserindoClienteException ex) {
                            request.setAttribute("cliente", c);
                            request.setAttribute("msg", ClientesFacade.getErroInsertUpdate(c.getEmailCliente()));
                            rd = request.getRequestDispatcher("clientesForm.jsp");
                            request.setAttribute("estados", CidadeEstadoFacade.getListaEstado());
                        }
                        break;
                    case "formNew":
                        request.setAttribute("estados", CidadeEstadoFacade.getListaEstado());
                        rd = request.getRequestDispatcher("clientesForm.jsp");
                        break;
                    case "update" :
                        c = getClientePostData(request);
                        
                        try {
                            if(ClientesFacade.alterar(c) == true){
                                request.setAttribute("redirecionar", "lista");
                                rd = request.getRequestDispatcher("ClientesServlet");
                            }
                            else{
                                rd = request.getRequestDispatcher("portal.jsp");
                            }
                        } catch (ErroUpdateClienteException ex) {
                            request.setAttribute("cliente", c);
                            request.setAttribute("msg", ClientesFacade.getErroInsertUpdate(c.getEmailCliente()));
                            rd = request.getRequestDispatcher("clientesForm.jsp");
                            request.setAttribute("estados", CidadeEstadoFacade.getListaEstado());
                        }
                        break;
                    case "remove" :
                        id = Integer.parseInt(request.getParameter("id"));

                        try{
                            ClientesFacade.remover(id);
                            request.setAttribute("redirecionar", "lista");
                            rd = request.getRequestDispatcher("ClientesServlet");
                        } catch (ErroRemovendoClienteException ex){
                            request.setAttribute("msg", "Erro ao remover o cliente");
                            request.setAttribute("redirecionar", "lista");
                            rd = request.getRequestDispatcher("ClientesServlet");
                        }
                        break;
                    case "formUpdate" :
                        id = Integer.parseInt(request.getParameter("id"));
                        try{
                            rd = request.getRequestDispatcher("clientesForm.jsp");
                            request.setAttribute("cliente", ClientesFacade.buscar(id));
                            request.setAttribute("estados", CidadeEstadoFacade.getListaEstado());
                        } catch (ErroBuscandoClienteException ex){
                            request.setAttribute("msg", "Cliente não encontrado");
                            request.setAttribute("redirecionar", "lista");
                            rd = request.getRequestDispatcher("ClientesServlet");
                        }
                        break;
                    case "show" :
                        id = Integer.parseInt(request.getParameter("id"));
                        try{
                            rd = request.getRequestDispatcher("clientesVisualizar.jsp");
                            request.setAttribute("cliente", ClientesFacade.buscar(id));
                        } catch (ErroBuscandoClienteException ex){
                            request.setAttribute("msg", "Cliente não encontrado");
                            request.setAttribute("redirecionar", "lista");
                            rd = request.getRequestDispatcher("ClientesServlet");
                        }
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
        c.setCpfCliente(null);
        if(aux != null  ){
            c.setIdCliente(Integer.parseInt(aux));
        }
        String cpf = (String) request.getParameter("cpf");
        cpf = cpf.replace(".","");
        cpf = cpf.replace("-","");
        if(c.validaCPF(cpf)){
            c.setCpfCliente( cpf);
        }
        c.setNomeCliente((String) request.getParameter("nome"));
        c.setEmailCliente((String) request.getParameter("email"));
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            c.setDataCliente(format.parse((String) request.getParameter("data").replace("/", "-")));
        } catch (ParseException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setRuaCliente((String) request.getParameter("rua"));
        c.setNrCliente(Integer.parseInt(request.getParameter("numero")));
        c.setCepCliente((String) request.getParameter("cep").replace("-", ""));
        c.setCidadeCliente(CidadeEstadoFacade.getCidade(Integer.parseInt(request.getParameter("cidade"))));
                    
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
