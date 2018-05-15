<%-- 
    Document   : atendimentoListar
    Created on : 09/05/2018, 23:42:57
    Author     : ArtVin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>Listar Atendimentos</title>
    </head>
    <body>
        <c:if test="${(empty loginBean)}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>

        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">${loginBean.nome}</span>
        </nav>
        
        <div class="container" id="info-table">
            <table class="table table-hover">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Data e Hora</th>
                        <th scope="col">Produto</th>
                        <th scope="col">Nome Cliente</th>
                        <th scope="col">Detalhes</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="atendimento" >
                        <tr>
                            <td> <fmt:formatDate value="${atendimento.dtHrAtendimento}" pattern="dd/MM/yyyy HH:mm:ss"/> </td>
                            <td>${atendimento.produtoAtendimento.nomeProduto}</td>
                            <td>${atendimento.clienteAtendimento.nomeCliente}</td>
                            <td>
                                <a href="AtendimentoServlet?action=show&id=${atendimento.idAtendimento}">
                                    <i class="material-icons">visibility</i> 
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
                <a class="btn btn-secondary" id="cancel" href="portal.jsp">Voltar</a>
        </div>
    </body>
</html>