<%-- 
    Document   : Portal
    Created on : 29/03/2018, 13:18:37
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
                /*LoginBean asd = (LoginBean) session.getAttribute(loginBean);
                if(asd.getEmail() == null){
                RequestDispatcher rd = request.
                getRequestDispatcher("Erro.jsp");
                request.setAttribute("msg", "Usuário não existe");
                request.setAttribute("page", "\"index.html\"");
                rd.forward(request, response);
                }*/
                
            %>
        </jsp:useBean>
        <jsp:getProperty name="loginBean" property="nome" />
        
        <a href="LogoutServlet">Logout</a>
    </body>
    <footer>
        Em caso de problemas contatar o administrador :
        <jsp:useBean id="configuracao" class="beans.ConfigBean" scope="application"/>
        <jsp:getProperty name="configuracao" property="email"/> 
        
    </footer>
</html>
