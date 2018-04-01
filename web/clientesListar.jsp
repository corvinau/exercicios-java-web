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
        <jsp:getProperty name="loginBean" property="nome" /><br><br><br>
        <table>
            <tr>
                <th>CPF</th>
                <th>Nome</th>
                <th>Email</th>
                <th> </th>
            </tr>
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
                        out.print("<a href=\"VisualizarClienteServlet?id="+aux.getId_cliente()+"\"> Visualizar </a>");
                        out.print("<a href=\"FormAlterarClienteServlet?id="+aux.getId_cliente()+"\"> Alterar </a>");
                        out.print("<a href=\"RemoverClienteServlet?id="+aux.getId_cliente()+"\"> Remover </a>");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                }
            %>
        </table><br><br>
        <a href="FormNovoClienteServlet">Novo Cliente</a>
    </body>
</html>
