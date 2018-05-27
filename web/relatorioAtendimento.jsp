<%-- 
    Document   : atendimento
    Created on : 09/05/2018, 23:42:47
    Author     : ArtVin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page errorPage="erro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <script src="js/moment.js"></script>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/ui/jquery-ui.js"></script>
        <title>Relatório</title>
    </head>
    <body>
        <c:if test="${(empty loginBean)}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">Relatório</span>
        </nav>
        
        <c:if test="${not empty msg}">
            <div class="container alert alert-danger" role="alert" id="alert">
                <span>${msg}</span>
            </div>
        </c:if>
        
        <div class="container" id="client-form">
            <form action="GeradorRelatorio?action=geraRelatorioTypeService" method="POST">
                <div class="form-group row">
                    <label for="tipoAtendimento" class="col-sm-2 col-form-label">Tipo Atendimento *</label>
                    <div class="col-sm-10">
                        <select id="tipoAtendimento" name="tipoAtendimento" class="form-control">
                            <c:forEach items="${tipos}" var="tipo">
                                <option value="${tipo.idTipoAtendimento}">${tipo.nomeTipoAtendimento}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <input class="btn btn-primary" id="new-client" type="submit" value="Gerar">
                <a class="btn btn-secondary" id="cancel" href="relatorios.jsp">Voltar</a>
            </form>
        </div>
        
    </body>
</html>
