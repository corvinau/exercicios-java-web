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
        <script>
            $(document).ready(function(){
               
               $("#data").val(moment().format('DD MM YYYY h:mm:ss'));
                
            });
        </script>
        <title>Novo Atendimento</title>
    </head>
    <body>
        <c:if test="${(empty loginBean)}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">Novo Atendimento</span>
        </nav>
        
        <c:if test="${not empty msg}">
            <div class="container alert alert-danger" role="alert" id="alert">
                <span>${msg}</span>
            </div>
        </c:if>
        
        <div class="container" id="client-form">
            <form action="AtendimentoServlet?action=new" method="POST">
                <div class="form-group row">
                    <label for="dataHora" class="col-sm-2 col-form-label">Data e hora</label>
                    <div class="col-sm-10">
                        <input type="datetime" class="form-control" id="data" name="data" disabled/>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="produto" class="col-sm-2 col-form-label">Produto *</label>
                    <div class="col-sm-10">
                        <select id="produto" name="produto" class="form-control">
                            <c:forEach items="${produtos}" var="produto">
                                <option value="${produto.idProduto}">${produto.nomeProduto}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
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
                <div class="form-group row">
                    <label for="cliente" class="col-sm-2 col-form-label">Cliente *</label>
                    <div class="col-sm-10">
                        <select id="cliente" name="cliente" class="form-control">
                            <c:forEach items="${clientes}" var="cliente">
                                <option value="${cliente.idCliente}">${cliente.nomeCliente}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cpf" class="col-sm-2 col-form-label">Descricao *</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control cpf" name="dsc" placeholder="Descricao" maxlength="255" required/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cliente" class="col-sm-2 col-form-label">Resolvido</label>
                    <div class="col-sm-10">
                        <input class="form-check" type="checkbox" name="resolvido" value="true"><br>
                    </div>
                </div>
                
                <input class="btn btn-primary" id="new-client" type="submit" value="Salvar">
                <a class="btn btn-secondary" id="cancel" href="AtendimentoServlet">Cancelar</a>
            </form>
        </div>
        
    </body>
</html>
