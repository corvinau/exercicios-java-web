<%-- 
    Document   : clientesVisualizar
    Created on : 01/04/2018, 18:09:35
    Author     : ArtVin
--%>

<%@page import="beans.Cliente"%>
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
            <% Cliente c = (Cliente) request.getAttribute("cliente"); %>
            
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">CPF</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getCpf_cliente()); %>"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Nome</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getNome_cliente()); %>"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">E-mail</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getEmail_cliente()); %>"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Data</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getData_cliente()); %>"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Rua</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getRua_cliente()); %>"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Número</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getNr_cliente()); %>"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">CEP</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getCep_cliente()); %>"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">Cidade</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getCidade_cliente()); %>"/>
                </div>
            </div>
                
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">UF</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control" value="<% out.println(c.getUf_cliente()); %>"/>
                </div>
            </div>

            <a class="btn btn-secondary" id="cancel" href="ClientesServlet">Voltar</a>
        </div>
    </body>
</html>
