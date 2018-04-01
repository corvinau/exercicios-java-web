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
        <title>JSP Page</title>
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
        <form action="AlterarClienteServlet" method="POST">
            <% Cliente c = (Cliente) request.getAttribute("cliente");%>
            <input type="hidden" name="idCliente" value=<%out.print("\""+c.getId_cliente()+"\""); %>/><br/>
            CPF: <input type="text" name="cpf" value=<%out.print("\""+c.getCpf_cliente()+"\""); %>/><br/>
            Nome: <input type="text" name="nome" value=<%out.print("\""+c.getNome_cliente()+"\""); %>/><br/>
            Email: <input type="text" name="email" value=<%out.print("\""+c.getEmail_cliente()+"\""); %>/><br/>
            Data: <input type="date" name="data" value=<%out.print("\""+c.getData_cliente()+"\""); %>/><br/>
            Rua: <input type="text" name="rua" value=<%out.print("\""+c.getRua_cliente()+"\""); %>/><br/>
            Numero: <input type="text" name="numero" value=<%out.print("\""+c.getNr_cliente()+"\""); %>/><br/>
            CEP: <input type="text" name="cep" value=<%out.print("\""+c.getCep_cliente()+"\""); %>/><br/>
            Cidade: <input type="text" name="cidade" value=<%out.print("\""+c.getCidade_cliente()+"\""); %>/><br/>
            UF: <input type="text" name="uf" value=<%out.print("\""+c.getUf_cliente()+"\""); %>/><br/>
            <input type="submit" value="Alterar">
            <a href="ClientesServlet"> Cancelar </a>
        </form>
    </body>
</html>
