/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.exceptions.ErroBuscandoClienteException;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.ClientesFacade;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "AtendimentoServlet", urlPatterns = {"/AtendimentoServlet"})
public class AtendimentoServlet extends HttpServlet {

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
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            session.invalidate();
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
            rd.forward(request, response);
        }
        
        String action = (String) request.getAttribute("redirecionar");
        
        if(action == null){
            action = (String) request.getParameter("action");
        }
        RequestDispatcher rd = null;
        if(action != null){
            switch (action){
                case "new":
                    Atendimento a = getPostAtendimento(request,login);
                    AtendimentoFacade.insertAtendimento(a);
                    request.setAttribute("redirecionar", "listaAtendimento");
                    rd = request.getRequestDispatcher("AtendimentoServlet");
                    break;
                case "show":
                    int id = Integer.parseInt((String)request.getParameter("id"));
                    try{
                        request.setAttribute("atendimento", AtendimentoFacade.getAtendimento(id));
                        rd = request.getRequestDispatcher("atendimentoDetalhes.jsp");
                    } catch (ErroBuscandoClienteException ex) {
                        request.setAttribute("msg", "Cliente não encontrado");
                        request.setAttribute("redirecionar", "lista");
                        rd = request.getRequestDispatcher("AtendimentoServlet");
                    }
                    break;
                case "newAtendimento":
                    rd = request.getRequestDispatcher("atendimento.jsp");
                    request.setAttribute("clientes", ClientesFacade.buscarTodos());
                    request.setAttribute("tipos", AtendimentoFacade.getListaTipoAtendimento());
                    request.setAttribute("produtos", AtendimentoFacade.getListaProduto());
                    break;
                case "listaAtendimento":
                    try {
                        request.setAttribute("lista", AtendimentoFacade.getListaAtendimento());
                        rd = request.getRequestDispatcher("atendimentoListar.jsp");
                    }catch (ErroBuscandoClienteException ex){
                        rd = request.getRequestDispatcher("portal.jsp");
                    }
                    break;
                default :
                    try {
                        request.setAttribute("lista", AtendimentoFacade.getListaAtendimento());
                        rd = request.getRequestDispatcher("atendimentoListar.jsp");
                    }catch (ErroBuscandoClienteException ex){
                        rd = request.getRequestDispatcher("portal.jsp");
                    }
                    break;
            }
        }
        else{
            rd = request.getRequestDispatcher("portal.jsp");
        }
        rd.forward(request, response);

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

    private Atendimento getPostAtendimento(HttpServletRequest request, LoginBean login) {
        Atendimento a = new Atendimento();
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
        
        Date data = new Date();
        a.setDtHrAtendimento(data);
        a.setDscAtendimento((String) request.getParameter("dsc"));
        String aux = (String)request.getParameter("resolvido");
        if(aux == null){
            a.setResAtendimento('N');
        }
        else{
            a.setResAtendimento('S');
        }
        Cliente c = new Cliente();
        c.setIdCliente(Integer.parseInt((String) request.getParameter("cliente")));
        a.setClienteAtendimento(c);
        
        TipoAtendimento tipo = new TipoAtendimento();
        tipo.setIdTipoAtendimento(Integer.parseInt((String) request.getParameter("tipoAtendimento")));
        a.setTipoAtendimento(tipo);
        
        Produto produto = new Produto();
        produto.setIdProduto(Integer.parseInt((String) request.getParameter("produto")));
        a.setProdutoAtendimento(produto);
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(login.getId());
        a.setUsuarioAtendimento(usuario);
        
        /*String aux = request.getParameter("idCliente");
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
        
        c.setRuaCliente((String) request.getParameter("rua"));
        c.setNrCliente(Integer.parseInt(request.getParameter("numero")));
        c.setCepCliente((String) request.getParameter("cep").replace("-", ""));
        c.setCidadeCliente(CidadeEstadoFacade.getCidade(Integer.parseInt(request.getParameter("cidade"))));
        */
        return a;
    }

}
