/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.dao.ClienteDAO;
import java.io.IOException;
import java.sql.Date;
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
@WebServlet(name = "NovoClienteServlet", urlPatterns = {"/NovoClienteServlet"})
public class NovoClienteServlet extends HttpServlet {

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
        
        if(login == null || login.getNome() == null){
            RequestDispatcher rd = request.
            getRequestDispatcher("index.jsp");
            session.invalidate();
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
            rd.forward(request, response);
        }
        else{
            ClienteDAO clientes = new ClienteDAO();
            Cliente c = new Cliente();
            c.setCpfCliente((String) request.getParameter("cpf"));
            c.setNomeCliente((String) request.getParameter("nome"));
            c.setEmailCliente((String) request.getParameter("email"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date data = null;
            try {
                data = new Date(format.parse(request.getParameter("data")).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(AlterarClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.setDataCliente(data);
            c.setRuaCliente((String) request.getParameter("rua"));
            c.setNrCliente(Integer.parseInt(request.getParameter("numero")));
            c.setCepCliente((String) request.getParameter("cep"));
            c.setCidadeCliente((String) request.getParameter("cidade"));
            c.setUfCliente((String) request.getParameter("uf"));
            clientes.insertCliente(c);
            
            RequestDispatcher rd = request.
            getRequestDispatcher("ClientesServlet");
            rd.forward(request, response);
        }
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
