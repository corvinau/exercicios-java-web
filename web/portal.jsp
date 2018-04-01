<%-- 
    Document   : Portal
    Created on : 29/03/2018, 13:18:37
    Author     : ArtVin
--%>

<%@page import="beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="loginBean" class="beans.LoginBean" scope="session">
            <%   
                RequestDispatcher rd = request.
                getRequestDispatcher("index.jsp");
                request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
                rd.forward(request, response); 
            %>
        </jsp:useBean>
        <jsp:getProperty name="loginBean" property="nome" /><br><br><br>
        
        <a href="ClientesServlet"> Cadastro de clientes </a><br>
        <a href="LogoutServlet">Sair</a><br><br>
    </body>
    <footer>
        Em caso de problemas contatar o administrador :
        <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
        <jsp:getProperty name="configuracao" property="email"/> 
        
    </footer>
</html>
