/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.ClientesFacade;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Tatiane
 */
@WebServlet(name = "GeradorRelatorio", urlPatterns = {"/GeradorRelatorio"})
public class GeradorRelatorio extends HttpServlet {

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
            throws ServletException, IOException {;
        String action = (String) request.getAttribute("redirecionar");
        
        if(action == null){
            action = request.getParameter("action");
        }
        RequestDispatcher rd = null;
        HashMap map;
        if(action != null){
            switch (action){
                case "gerarRelatorioAll":
                    geraRelatorio("/ReportAll.jasper", new HashMap(), request, response);
                    break;
                case "formRelatorioTipo":
                    rd = request.getRequestDispatcher("relatorioAtendimento.jsp");
                    request.setAttribute("tipos", AtendimentoFacade.getListaTipoAtendimento());
                    rd.forward(request, response);
                    break;
                case "formRelatorioData":
                    rd = request.getRequestDispatcher("relatorioDatas.jsp");
                    rd.forward(request, response);
                    break;
                case "geraRelatorioResolvido":
                    geraRelatorio("/ReportSolved.jasper", new HashMap(), request, response);
                    break;
                case "geraRelatorioIntervalDates":
                    map = new HashMap();
                    Date a = null;
                    Date b = null;
                    try {
                        a = formataDataBeanParaSql(formataDataTelaParaBean(request.getParameter("DataInicio")));
                        b = formataDataBeanParaSql(formataDataTelaParaBean(request.getParameter("DataFim")));
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("a : "  + a);
                    System.out.println("b : "  +b);
                    map.put("DataInicio", a);
                    map.put("DataFim", b);
                    geraRelatorio("/ReportIntervalDates.jasper", map, request, response);
                    break;
                case "geraRelatorioTypeService" :
                    Integer tipo = Integer.parseInt(request.getParameter("tipoAtendimento"));
                    System.out.println("Pro ctrl f batata " + tipo.intValue());
                    map = new HashMap();
                    map.put("IdAtendimento",tipo);
                    geraRelatorio("/ReportTypeService.jasper", map, request, response);
                    break;
                case "batata" :
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
        
        
        
    }
    //converter data de string para data java
	public static java.util.Date formataDataTelaParaBean(String data) throws ParseException{        
        SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date date = formato.parse(data);
            System.out.println("date" + date);
            System.out.println("data" + data);
        return date;
	}
	
	//converter java date para sql date
	public static java.sql.Date formataDataBeanParaSql(java.util.Date data){
		java.sql.Date sqlDate = new java.sql.Date(data.getTime());
		return sqlDate;
	}
	
    //converter sql date para java date
	public static java.util.Date formataDataSqlParaBean(java.sql.Date data){
		java.sql.Date sqlDate = data;
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        return utilDate;
	}
	
	//converter data e hora de string para data e hora java
	public static java.util.Date formataDataHoraTelaParaBean(String data) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.util.Date date = formato.parse(data);
        return date;
	}
	
	//converter data e hora java para data e hora string para exibir na tela
	public static String formataDataHoraBeanParaTela(java.util.Date data) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date = formato.format(data);
        return date;
	}
	
	//converter sql timestamp com data e hora para java date com data e hora
	public static java.util.Date formataDataHoraSqlParaBean(java.sql.Timestamp dataHora){
        java.util.Date utilDate = new java.util.Date(dataHora.getTime());
        return utilDate;
	}

	//converter java date com data e hora para sql timestamp com data e hora
	public static java.sql.Timestamp formataDataHoraBeanParaSql(java.util.Date dataHora){
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(dataHora.getTime());
        return sqlTimestamp ;
	}
    
    public void geraRelatorio(String relatorio,HashMap params,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        Connection con = null;
        try{
            con = ConnectionFactory.getConnection();
            // Caminho contextualizado do relatório compilado
            String jasper = request.getContextPath() + relatorio;
            // Host onde o servlet esta executando
            String host = "http://" + request.getServerName() + ":" + request.getServerPort();
            // URL para acesso ao relatório
            URL jasperURL = new URL(host + jasper);
            // Parâmetros do relatório
            // Geração do relatório
            byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);

            if (bytes != null) {
                // A página será mostrada em PDF
                response.setContentType("application/pdf");
                // Envia o PDF para o Cliente
                OutputStream ops = response.getOutputStream();
                ops.write(bytes);
            }
        }
        catch(ClassNotFoundException e) {
            request.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
        catch(SQLException e) {
            request.setAttribute("mensagem", "Erro de conexão ou query: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
        catch(JRException e) {
            request.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
        finally {
            if (con!=null)
                try { con.close(); } catch(Exception e) {}
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
