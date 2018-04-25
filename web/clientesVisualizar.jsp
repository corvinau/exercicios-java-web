<%-- 
    Document   : clientesVisualizar
    Created on : 01/04/2018, 18:09:35
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
        <title>Visualizar Cliente</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">Visualizar Cliente</span>
        </nav>
        
        <div class="container" id="client-form">
            
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">CPF</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.cpfCliente}"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Nome</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.nomeCliente}"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">E-mail</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.emailCliente}"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Data</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.dataCliente}"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Rua</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.ruaCliente}"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Número</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.nrCliente}"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">CEP</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.cepCliente}"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Cidade</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.cidadeCliente}"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">UF</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="${cliente.ufCliente}"/>
                </div>
            </div>

            <a class="btn btn-secondary" id="cancel" href="ClientesServlet">Voltar</a>
        </div>
    </body>
</html>
