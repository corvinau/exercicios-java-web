<%-- 
    Document   : clientesListar
    Created on : 01/04/2018, 17:06:01
    Author     : ArtVin
--%>

<%@page import="beans.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Listar Clientes</title>
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

        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand"><jsp:getProperty name="loginBean" property="nome"/></span>
        </nav>
        
        <div class="container" id="info-table">
            <table class="table table-hover">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">CPF</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Email</th>
                        <th scope="col">Ação</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    List<Cliente> lista = (List<Cliente>) request.getAttribute("listaClientes");
                    if(lista.size()!= 0){
                        for(int i = 0; i < lista.size(); i++){
                            Cliente aux = lista.get(i);
                            out.print("<tr>");
                            out.print("<td>"+ aux.getCpf_cliente() +"</td>");
                            out.print("<td>"+ aux.getNome_cliente()+"</td>");
                            out.print("<td>"+ aux.getEmail_cliente() +"</td>");
                            out.print("<td>");
                            out.print("<a href=\"VisualizarClienteServlet?id="+aux.getId_cliente()+"\"><i class=\"material-icons\">visibility</i> </a>");
                            out.print("<a href=\"FormAlterarClienteServlet?id="+aux.getId_cliente()+"\"><i class=\"material-icons\">edit</i> </a>");
                            out.print("<a href=\"RemoverClienteServlet?id="+aux.getId_cliente()+"\"><i class=\"material-icons\">delete</i> </a>");
                            out.print("</td>");
                            out.print("</tr>");
                        }
                    }
                %>
                </tbody>
            </table>
            <a class="btn btn-primary" id="new-client" href="FormNovoClienteServlet">Novo Cliente</a>
            <a class="btn btn-secondary" id="cancel" href="portal.jsp">Voltar</a>
        </div>
    </body>
</html>
