<%-- 
    Document   : index
    Created on : 01/04/2018, 16:11:47
    Author     : ArtVin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%
            String msg = (String) request.getAttribute("msg");
            if(msg != null){
                %>
                <a style="color:red">
                    <% out.print(msg); %>
                </a>
                <%
            }
        %>
        <form action="LoginServlet" method="POST">
            Login: <input type="text" name="login" value=""/><br/>
            Senha: <input type="password" name="senha" value=""/><br/>
            <input type="submit" value="Entrar">
        </form>
    </body>
    <footer>
        Em caso de problemas contatar o administrador :
        <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
        <jsp:getProperty name="configuracao" property="email"/> 
        
    </footer>
</html>