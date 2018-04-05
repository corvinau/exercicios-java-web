<%-- 
    Document   : Erro
    Created on : 29/03/2018, 13:20:59
    Author     : ArtVin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <title>Erro</title>
    </head>
    <body>
        <a> 
            
        </a>
        <div class="container alert alert-danger" role="alert" id="alert">
            <span>
                <% 
                    String a = (String)request.getAttribute("msg");
                    out.println(a);
                %>
            </span>
        </div>
            
        <div class="container" style="text-align: center;">
            <a class="btn btn-primary" style="width: 25%;" 
               href=<%
                String b = (String) request.getAttribute("page");
                out.print(b);
                %>>Voltar a tela de Login
            </a>
        </div>
    </body>
    
    <footer class="footer">
        <div class="container">
            <span>
                Em caso de problemas contatar o administrador :
                <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
                <jsp:getProperty name="configuracao" property="email"/>
            </span>
        </div>
    </footer>
</html>
