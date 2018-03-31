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
        <title>JSP Page</title>
    </head>
    <body>
        <a> 
            <% 
                String a = (String)request.getAttribute("msg");
                out.println(a);
            %>
        
        </a>
            <a href=<%String b = (String) request.getAttribute("page");
                      out.print(b);%>
            >Voltar a tela de Login </a>
    </body>
    <footer>
        Em caso de problemas contatar o administrador :
        <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
        <jsp:getProperty name="configuracao" property="email"/>
        
    </footer>
</html>
