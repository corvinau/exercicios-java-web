<%-- 
    Document   : Portal
    Created on : 29/03/2018, 13:18:37
    Author     : ArtVin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Portal</title>
    </head>
    <body>
        <c:if test="${(empty loginBean)}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">${loginBean.nome}</span>
        </nav>
            
        <div class="container" id="info-table">
            <table class="table table-hover">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Relatorio</th>
                        <th scope="col">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Todos os cliente</td>
                        <td>
                            <a href="GeradorRelatorio?action=gerarRelatorioAll">
                                <i class="material-icons">print</i> 
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>Todos os atendimentos em um intervalo de datas</td>
                        <td>
                            <a href="GeradorRelatorio?action=formRelatorioData">
                                <i class="material-icons">print</i> 
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>Todos os atendimentos resolvidos</td>
                        <td>
                            <a href="GeradorRelatorio?action=geraRelatorioResolvido">
                                <i class="material-icons">print</i> 
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>Todos os atendimentos de um determinado tipo</td>
                        <td>
                            <a href="GeradorRelatorio?action=formRelatorioTipo">
                                <i class="material-icons">print</i> 
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
                <a class="btn btn-secondary" id="cancel" href="portal.jsp">Voltar</a>
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
