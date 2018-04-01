<%-- 
    Document   : clientesNovo
    Created on : 01/04/2018, 19:35:41
    Author     : ArtVin
--%>

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
        <form action="NovoClienteServlet" method="POST">
            CPF: <input type="text" name="cpf" value=""/><br/>
            Nome: <input type="text" name="nome" value=""/><br/>
            Email: <input type="text" name="email" value=""/><br/>
            Data: <input type="date" name="data" value=""/><br/>
            Rua: <input type="text" name="rua" value=""/><br/>
            Numero: <input type="text" name="numero" value=""/><br/>
            CEP: <input type="text" name="cep" value=""/><br/>
            Cidade: <input type="text" name="cidade" value=""/><br/>
            UF: <input type="text" name="uf" value=""/><br/>
            <input type="submit" value="Criar">
            <a href="ClientesServlet"> Cancelar </a>
        </form>
    </body>
</html>
