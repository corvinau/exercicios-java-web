<%-- 
    Document   : index
    Created on : 01/04/2018, 16:11:47
    Author     : ArtVin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <title>Login</title>
    </head>
    <body>
        <%
            String msg = (String) request.getAttribute("msg");
            if(msg != null){
                %>
                <div class="container alert alert-danger" role="alert" id="alert">
                    <span><% out.print(msg); %></span>
                </div>
                <%
            }
        %>
        
        <div class="container" id="login-form">
            <form action="LoginServlet" method="POST">
                <div class="form-group">
                    <!--<label for="login" class="col-form-label">Login</label>-->
                    <input type="text" class="form-control" name="login" value="" placeholder="Login"/>
                </div>
                <div class="form-group">
                    <!--<label for="senha" class="col-form-label">Senha</label>-->
                    <input type="password" class="form-control" name="senha" value="" placeholder="Senha"/>
                </div>
                <input type="submit" class="btn btn-primary" value="Entrar"/>
            </form>
        </div>
        
        <footer class="footer">
            <div class="container">
                <span>
                    Em caso de problemas contatar o administrador :
                    <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
                    <jsp:getProperty name="configuracao" property="email"/> 
                </span>
            </div>
        </footer>
    </body>
</html>