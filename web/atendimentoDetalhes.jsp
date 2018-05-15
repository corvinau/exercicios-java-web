<%-- 
    Document   : atendimentoDetalhes
    Created on : 09/05/2018, 23:43:20
    Author     : ArtVin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page errorPage="erro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <title>Atendimento</title>
    </head>
    <body>
        <c:if test="${(empty loginBean)}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">Visualizar Atendimento</span>
        </nav>
        
        <c:if test="${not empty msg}">
            <div class="container alert alert-danger" role="alert" id="alert">
                <span>${msg}</span>
            </div>
        </c:if>
        
        <div class="container" id="client-form">
            <div class="form-group row">
                <label for="dataHora" class="col-sm-2 col-form-label">Data e hora</label>
                <div class="col-sm-10">
                    <input type="datetime" readonly class="form-control" value="<fmt:formatDate value="${atendimento.dtHrAtendimento}" pattern="dd/MM/yyyy HH:mm:ss"/>"/>
                </div>
            </div>

            <div class="form-group row">
                <label for="produto" class="col-sm-2 col-form-label">Produto *</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${atendimento.produtoAtendimento.nomeProduto}"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="tipoAtendimento" class="col-sm-2 col-form-label">Tipo Atendimento *</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${atendimento.tipoAtendimento.nomeTipoAtendimento}"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="cliente" class="col-sm-2 col-form-label">Cliente *</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${atendimento.clienteAtendimento.nomeCliente}"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Descricao *</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${atendimento.dscAtendimento}"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="cliente" class="col-sm-2 col-form-label">Resolvido</label>
                <div class="col-sm-10">
                    <c:choose>
                        <c:when test="${atendimento.resAtendimento eq 'S'.charAt(0)}">
                            <input class="form-check" disabled type="checkbox" name="resolvido" value="true" checked><br>
                        </c:when>
                        <c:otherwise>
                            <input class="form-check" disabled type="checkbox" name="resolvido" value="true"><br>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <a class="btn btn-secondary" id="cancel" href="AtendimentoServlet?action=listaAtendimento">Voltar</a>
        </div>
    </body>
</html>
