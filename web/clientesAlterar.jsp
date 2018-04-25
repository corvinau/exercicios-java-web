<%-- 
    Document   : clientesAlterar
    Created on : 01/04/2018, 18:25:14
    Author     : ArtVin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page errorPage="erro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <title>Alterar Cadastro</title>
    </head>
    <body>
        <jsp:useBean id="loginBean" class="com.ufpr.tads.web2.beans.LoginBean" scope="session">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </jsp:useBean>
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">Alterar Cliente</span>
        </nav>
        
        <div class="container" id="client-form">
            <form action="ClientesServlet?action=update" method="POST">
                <div class="form-group row hidden">
                    <div class="col-sm-10 hidden">
                        <input type="hidden" name="idCliente" value="${cliente.idCliente}"/>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="cpf" class="col-sm-2 col-form-label">CPF</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="cpf" placeholder="CPF" value="${cliente.cpfCliente}"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="nome" class="col-sm-2 col-form-label">Nome</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="nome" placeholder="Nome" value="${cliente.nomeCliente}"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">E-mail</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="email" placeholder="E-mail" value="${cliente.emailCliente}"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="data" class="col-sm-2 col-form-label">Data</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" name="data" value="${cliente.dataCliente}"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="rua" class="col-sm-2 col-form-label">Rua</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="rua" placeholder="Rua" value="${cliente.ruaCliente}"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="numero" class="col-sm-2 col-form-label">Número</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="numero" placeholder="Nº" value="${cliente.nrCliente}"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cep" class="col-sm-2 col-form-label">CEP</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="cep" placeholder="CEP" value="${cliente.cepCliente}"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cidade" class="col-sm-2 col-form-label">Cidade</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="cidade" placeholder="Cidade" value="${cliente.cidadeCliente}"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="uf" class="col-sm-2 col-form-label">UF</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="uf" placeholder="UF" value="${cliente.ufCliente}"/>
                    </div>
                </div>
                <input class="btn btn-primary" id="new-client" type="submit" value="Alterar">
                <a class="btn btn-secondary" id="cancel" href="ClientesServlet">Cancelar</a>
            </form>
        </div>
    </body>
</html>
