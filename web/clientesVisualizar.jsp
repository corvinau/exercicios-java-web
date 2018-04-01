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
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Cliente c = (Cliente) request.getAttribute("cliente");
            out.println("<a> Nome : " + c.getNome_cliente()+ "</a><br>");
        %>
        <a href="ClientesServlet"> Voltar</a>
    </body>
</html>
