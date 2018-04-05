<%-- 
    Document   : clientesAlterar
    Created on : 01/04/2018, 18:25:14
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
        <title>Alterar Cadastro</title>
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
            <span class="navbar-brand">Alterar Cliente</span>
        </nav>
        
        <div class="container" id="client-form">
            <form action="AlterarClienteServlet" method="POST">
                <% Cliente c = (Cliente) request.getAttribute("cliente");%>
                <div class="form-group row hidden">
                    <div class="col-sm-10 hidden">
                        <input type="hidden" name="idCliente" value=<%out.print("\""+c.getId_cliente()+"\""); %>/>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="cpf" class="col-sm-2 col-form-label">CPF</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="cpf" placeholder="CPF" value=<%out.print("\""+c.getCpf_cliente()+"\""); %>/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="nome" class="col-sm-2 col-form-label">Nome</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="nome" placeholder="Nome" value=<%out.print("\""+c.getNome_cliente()+"\""); %>/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">E-mail</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="email" placeholder="E-mail" value=<%out.print("\""+c.getEmail_cliente()+"\""); %>/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="data" class="col-sm-2 col-form-label">Data</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" name="data" value=<%out.print("\""+c.getData_cliente()+"\""); %>/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="rua" class="col-sm-2 col-form-label">Rua</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="rua" placeholder="Rua" value=<%out.print("\""+c.getRua_cliente()+"\""); %>/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="numero" class="col-sm-2 col-form-label">Número</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="numero" placeholder="Nº" value=<%out.print("\""+c.getNr_cliente()+"\""); %>/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cep" class="col-sm-2 col-form-label">CEP</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="cep" placeholder="CEP" value=<%out.print("\""+c.getCep_cliente()+"\""); %>/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cidade" class="col-sm-2 col-form-label">Cidade</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="cidade" placeholder="Cidade" value=<%out.print("\""+c.getCidade_cliente()+"\""); %>/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="uf" class="col-sm-2 col-form-label">UF</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="uf" placeholder="UF" value=<%out.print("\""+c.getUf_cliente()+"\""); %>/>
                    </div>
                </div>
                <input class="btn btn-primary" id="new-client" type="submit" value="Alterar">
                <a class="btn btn-secondary" id="cancel" href="ClientesServlet">Cancelar</a>
            </form>
        </div>
    </body>
</html>
