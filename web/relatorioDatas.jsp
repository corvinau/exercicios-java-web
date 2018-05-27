<%-- 
    Document   : atendimento
    Created on : 09/05/2018, 23:42:47
    Author     : ArtVin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page errorPage="erro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/ui/jquery-ui.js"></script>
        <script type="text/javascript" >
            $(function(){
                var dateFormat = "mm/dd/yy",
                    from = $( "#from" )
                        .datepicker({
                            defaultDate: "+1w",
                            changeMonth: true,
                            numberOfMonths: 1
                        })
                        .on( "change", function() {
                            to.datepicker( "option", "minDate", getDate( this ) );
                        }),
                    to = $( "#to" ).datepicker({
                        defaultDate: "+1w",
                        changeMonth: true,
                        numberOfMonths: 1
                    })
                    .on( "change", function() {
                        from.datepicker( "option", "maxDate", getDate( this ) );
                    });

                function getDate(element) {
                    var date;
                    try {
                        date = $.datepicker.parseDate( dateFormat, element.value );
                    } catch( error ) {
                        date = null;
                    }
                    return date;
                }
            });
        </script>
        <title>Relatorio</title>
    </head>
    <body>
        <c:if test="${(empty loginBean)}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
            <span class="navbar-brand">Relatorio</span>
        </nav>
        
        <c:if test="${not empty msg}">
            <div class="container alert alert-danger" role="alert" id="alert">
                <span>${msg}</span>
            </div>
        </c:if>
        
        <div class="container" id="client-form">
            <form action="GeradorRelatorio?action=geraRelatorioIntervalDates" method="POST">
                <div class="form-group row">
                    <label for="from" class="col-sm-2 col-form-label">Data e hora inicial*</label>
                    <div class="col-sm-10">
                        <input id="from" type="text" class="form-control" name="DataInicio" required/>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label for="to" class="col-sm-2 col-form-label">Data e hora final*</label>
                    <div class="col-sm-10">
                        <input id="to" type="text" class="form-control" name="DataFim" required/>
                    </div>
                </div>
                
                <input class="btn btn-primary" id="new-client" type="submit" value="Gerar">
                <a class="btn btn-secondary" id="cancel" href="relatorios.jsp">Voltar</a>
            </form>
        </div>
        
    </body>
</html>
