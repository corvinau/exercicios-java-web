<%-- 
    Document   : Portal
    Created on : 29/03/2018, 13:18:37
    Author     : ArtVin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page errorPage="erro.jsp" %>
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
        <jsp:useBean id="loginBean" class="com.ufpr.tads.web2.beans.LoginBean" scope="session">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </jsp:useBean>
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">${loginBean.nome}</span>
        </nav>
            
        <div class="container list-group" id="info">
            <a href="ClientesServlet" class="list-group-item list-group-item-action">Cadastro de clientes</a>
            <a href="LogoutServlet" class="list-group-item list-group-item-action">Sair</a>
        </div>
        
    </body>

    <footer class="footer">
        <div class="container">
            <span>
                Em caso de problemas contatar o administrador : ${configuracao.email}
            </span>
        </div>
    </footer>
</html>
