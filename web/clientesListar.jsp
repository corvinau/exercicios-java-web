<%-- 
    Document   : clientesListar
    Created on : 01/04/2018, 17:06:01
    Author     : ArtVin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page import="java.util.List"%>
<%@page errorPage="erro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Listar Clientes</title>
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
        
        <div class="container" id="info-table">
            <table class="table table-hover">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">CPF</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Email</th>
                        <th scope="col">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="cliente" >
                        <tr>
                            <td>${cliente.cpfCliente}</td>
                            <td>${cliente.nomeCliente}</td>
                            <td>${cliente.emailCliente}</td>
                            <td>
                                <a href="ClientesServlet?action=show&id=${cliente.idCliente}">
                                    <i class="material-icons">visibility</i> 
                                </a>
                                <a href="ClientesServlet?action=formUpdate&id=${cliente.idCliente}">
                                    <i class="material-icons">edit</i> 
                                </a>
                                <a href="ClientesServlet?action=remove&id=${cliente.idCliente}">
                                    <i class="material-icons">delete</i> 
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a class="btn btn-primary" id="new-client" href="ClientesServlet?action=formNew">Novo Cliente</a>
            <a class="btn btn-secondary" id="cancel" href="portal.jsp">Voltar</a>
        </div>
    </body>
</html>
