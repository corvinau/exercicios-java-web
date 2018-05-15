<%-- 
    Document   : index
    Created on : 01/04/2018, 16:11:47
    Author     : ArtVin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page errorPage="erro.jsp" %>
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
        <c:choose>
            <c:when test="${msg != null}">
                <div class="container alert alert-danger" role="alert" id="alert">
                    <span>${msg}</span>
                </div>
            </c:when>
            <c:when test="${param.msg != null}">
                <div class="container alert alert-danger" role="alert" id="alert">
                    <span>${param.msg}</span>
                </div>
            </c:when>
        </c:choose>       

        <div id="login-form">
            <form action="LoginServlet" method="POST">
                <div class="form-group">
                    <input type="text" class="form-control" name="login" value="" placeholder="Login"/>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="senha" value="" placeholder="Senha"/>
                </div>
                <input type="submit" class="btn btn-primary" value="Entrar"/>
            </form>
        </div>

        <footer class="footer">
            <div class="container">
                <span>
                    Em caso de problemas contatar o administrador : ${configuracao.email}
                </span>
            </div>
        </footer>
    </body>
</html>