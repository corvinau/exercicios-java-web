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
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <title>Portal</title>
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
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand"><jsp:getProperty name="loginBean" property="nome"/></span>
        </nav>
            
        <div class="container list-group" id="info">
            <a href="ClientesServlet" class="list-group-item list-group-item-action">Cadastro de clientes</a>
            <a href="LogoutServlet" class="list-group-item list-group-item-action">Sair</a>
        </div>
        
    </body>

    <footer class="footer">
        <div class="container">
            <span>
                Em caso de problemas contatar o administrador :
                <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
                <jsp:getProperty name="configuracao" property="email"/>
            </span>
        </div>
    </footer>
</html>
