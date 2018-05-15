<%-- 
    Document   : clientesAlterar
    Created on : 01/04/2018, 18:25:14
    Author     : ArtVin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page errorPage="erro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <title>Cadastro</title>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/ui/jquery-ui.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.min.js"></script>
        <script type="text/javascript" >
            
            $(document).ready(function(){
                var date_input=$('input[name="data"]'); //our date input has the name "date"
                var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
                var options={
                  format: 'dd/mm/yyyy',
                  container: container,
                  todayHighlight: true,
                  autoclose: true
                };
                date_input.datepicker(options);
                
                getCidades(true);
                
                $( "#uf" ).change(function() {
                  getCidades(false);
                });
            });
            
            function getCidades(preencherPrimeiroForm){
                var estadoId = $("#uf").val();
                var url = "AJAXServlet";
                $.ajax({
                        url : url, // URL da sua Servlet
                        data : {
                            estadoId : estadoId
                        }, // Parâmetro passado para a Servlet
                        dataType : 'json',
                        success : function(data) {
                            // Se sucesso, limpa e preenche a combo de cidade
                            // alert(JSON.stringify(data));
                            if(!preencherPrimeiroForm){
                                $("#cidade").empty();
                            }
                            $.each(data, function(i, obj) {
                                $("#cidade").append('<option value=' + obj.idCidade + '>' + obj.nomeCidade + '</option>');
                            });
                        },
                        error : function(request, textStatus, errorThrown) {
                            alert(request.status + ', Error: ' + request.statusText);
                             // Erro
                        }
                    });
            }
            
        </script>

    </head>
    <body>
        <c:if test="${(empty loginBean)}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <c:choose>
                <c:when test="${(empty cliente.idCliente) && cliente.idCliente != 0}">
                    <span class="navbar-brand">Alterar Cliente</span>
                </c:when>
                <c:otherwise>
                    <span class="navbar-brand">Novo Cliente</span>
                </c:otherwise>
            </c:choose>
        </nav>
        
        <c:if test="${not empty msg}">
            <div class="container alert alert-danger" role="alert" id="alert">
                <span>${msg}</span>
            </div>
        </c:if>
        
        <div class="container" id="client-form">
            <c:choose>
                <c:when test="${(empty cliente.idCliente) && cliente.idCliente != 0}">
                    <form action="ClientesServlet?action=update" method="POST">
                </c:when>
                <c:otherwise>
                    <form action="ClientesServlet?action=new" method="POST">
                </c:otherwise>
            </c:choose>
                <div class="form-group row hidden">
                    <div class="col-sm-10 hidden">
                        <c:choose>
                            <c:when test="${(empty cliente.idCliente) && cliente.idCliente != 0}">
                                <input type="hidden" name="idCliente" value="${cliente.idCliente}"/>
                            </c:when>
                         </c:choose>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="cpf" class="col-sm-2 col-form-label">CPF *</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(empty cliente)}">
                                <input type="text" class="form-control cpf" name="cpf" placeholder="CPF" maxlength="11" required value="${cliente.cpfCliente}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control cpf" name="cpf" placeholder="CPF" maxlength="11" required/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="nome" class="col-sm-2 col-form-label">Nome *</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(empty cliente)}">
                                <input type="text" class="form-control" name="nome" placeholder="Nome" maxlength="100" required value="${cliente.nomeCliente}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control" name="nome" placeholder="Nome" maxlength="100" required/>
                            </c:otherwise>
                         </c:choose>
                        
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">E-mail *</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(empty cliente)}">
                                <input type="text" class="form-control" name="email" placeholder="E-mail" maxlength="100" required value="${cliente.emailCliente}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control" name="email" placeholder="E-mail" maxlength="100" required/>
                            </c:otherwise>
                         </c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="data" class="col-sm-2 col-form-label">Data *</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(empty cliente)}">
                                <input id="datepicker" type="text" class="form-control" name="data" required value="<fmt:formatDate value="${cliente.dataCliente}" pattern="dd/MM/yyyy"/>"/>
                            </c:when>
                            <c:otherwise>
                                <input id="datepicker" type="text" class="form-control" name="data" required/>
                            </c:otherwise>
                         </c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="rua" class="col-sm-2 col-form-label">Rua *</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(empty cliente)}">
                                <input type="text" class="form-control" name="rua" placeholder="Rua" maxlength="50" required value="${cliente.ruaCliente}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control" name="rua" placeholder="Rua" maxlength="50" required/>
                            </c:otherwise>
                         </c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="numero" class="col-sm-2 col-form-label">Número *</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(empty cliente)}">
                                <input type="text" class="form-control" name="numero" placeholder="Nº" maxlength="5" required value="${cliente.nrCliente}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control" name="numero" placeholder="Nº" maxlength="5" required/>
                            </c:otherwise>
                         </c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cep" class="col-sm-2 col-form-label">CEP *</label>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(empty cliente)}">
                                <input type="text" class="form-control cep" name="cep" placeholder="CEP" maxlength="8" required value="${cliente.cepCliente}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control cep" name="cep" placeholder="CEP" maxlength="8" required/>
                            </c:otherwise>
                         </c:choose>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="uf" class="col-sm-2 col-form-label">UF *</label>
                    <div class="col-sm-10">
                        <select id="uf" name="uf">
                            <c:forEach items="${estados}" var="estado">
                                <c:choose>
                                    <c:when test="${cliente.cidadeCliente.estado.idEstado == estado.idEstado}">
                                        <option value="${estado.idEstado}" selected>${estado.nomeEstado} - ${estado.siglaEstado}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${estado.idEstado}">${estado.nomeEstado} - ${estado.siglaEstado}</option>
                                    </c:otherwise>
                                 </c:choose>
                            </c:forEach>
                            
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cidade" class="col-sm-2 col-form-label">Cidade *</label>
                    <div class="col-sm-10">
                        <select id="cidade" name="cidade">
                            <option value="${cliente.cidadeCliente.idCidade}" selected>${cliente.cidadeCliente.nomeCidade}</option>
                        </select>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${(empty cliente.idCliente) && cliente.idCliente != 0}">
                        <input class="btn btn-primary" id="new-client" type="submit" value="Alterar">
                    </c:when>
                    <c:otherwise>
                        <input class="btn btn-primary" id="new-client" type="submit" value="Salvar">
                    </c:otherwise>
                </c:choose>
                
                <a class="btn btn-secondary" id="cancel" href="ClientesServlet">Cancelar</a>
            </form>
        </div>
    </body>
</html>
