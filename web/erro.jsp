<%-- 
    Document   : erro
    Created on : 17/04/2018, 22:20:49
    Author     : ArtVin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <title>Erro</title>
    </head>
    
    <body>
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">Erro</span>
        </nav>
        
        <div class="container" id="error">
            <a>
                ${pageContext.exception.message}
            </a>
            <br>
            <a>
                ${pageContext.out.flush()}
                ${pageContext.exception.printStackTrace(pageContext.response.writer)}
            </a>
        </div>
            
    </body>
    
    <footer class="footer">
        <div class="container">
            <span>
                Em caso de problemas contatar o administrador : ${configuracao.email}
            </span>
        </div>
    </footer>
</html>
