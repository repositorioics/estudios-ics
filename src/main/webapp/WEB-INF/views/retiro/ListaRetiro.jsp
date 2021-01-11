<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 15/11/2020
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/dtresponsive/twitter-bootstrap.css" var="boot1" />
    <link href="${boot1}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
    <link rel="stylesheet" href="${bdt}" type="text/css"/>

    <spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
    <link rel="stylesheet" href="${bdat4}" type="text/css"/>

    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>
    <style>

        #page-loader {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 1000;
            background: #FFF none repeat scroll 0% 0%;
            z-index: 99999;
        }

        #page-loader .preloader-interior {
            display: block;
            position: relative;
            left: 50%;
            top: 50%;
            width: 150px;
            height: 150px;
            margin: -75px 0 0 -75px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #3498db;

            -webkit-animation: spin 2s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 2s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        #page-loader .preloader-interior:before {
            content: "";
            position: absolute;
            top: 5px;
            left: 5px;
            right: 5px;
            bottom: 5px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #e74c3c;

            -webkit-animation: spin 3s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 3s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        #page-loader .preloader-interior:after {
            content: "";
            position: absolute;
            top: 15px;
            left: 15px;
            right: 15px;
            bottom: 15px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #f9c922;

            -webkit-animation: spin 1.5s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 1.5s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        @-webkit-keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(0deg);
                /* IE 9 */
                transform: rotate(0deg);
                /* Firefox 16+, IE 10+, Opera */
            }

            100% {
                -webkit-transform: rotate(360deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(360deg);
                /* IE 9 */
                transform: rotate(360deg);
                /* Firefox 16+, IE 10+, Opera */
            }
        }

        @keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(0deg);
                /* IE 9 */
                transform: rotate(0deg);
                /* Firefox 16+, IE 10+, Opera */
            }

            100% {
                -webkit-transform: rotate(360deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(360deg);
                /* IE 9 */
                transform: rotate(360deg);
                /* Firefox 16+, IE 10+, Opera */
            }
        }
    </style>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <!-- Main content -->
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/retiro/ListRetiro/" htmlEscape="true "/>">
                <spring:message code="Lista Retiros" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div id="page-loader">
                    <span class="preloader-interior"></span>
                </div>
                <div class="">

                    <div class="row">
                        <div class="col-sm-12 col-lg-12">
                            <div class="card shadow bg-white rounded">
                                <div class="card-body">
                                    <spring:url value="/retiro/ListaHojaRetiro" var="ListaHojaRetiroUrl"/>
                                    <spring:url value="/retiro/DetallesRetiro" var="DetallesRetiroUrl"/>
                                    <h5 class="card-header text-capitalize" style="font-family: Roboto"> <i class="fa fa-list" aria-hidden="true"></i>
                                        Lista de Retirados</h5>
                                    <br/>
                                    <a class="btn btn-primary btn-lg" href="<spring:url value="/retiro/saveRetiroForm" htmlEscape="true "/>">
                                        <i class="fa fa-minus-circle" aria-hidden="true"></i>
                                        <spring:message code="Realizar Retiro"/></a>
                                    <hr/>

                                    <div class="container">
                                        <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                                            <div class="row">
                                                <div class="form-group col-sm-12 col-md-6 col-lg-12">
                                                    <label>Código del Participante</label>
                                                    <input type="text" class="form-control" placeholder="Ingrese el código" id="parametro" name="parametro">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <hr/>
                                    <div class="col-sm-12">
                                        <div class="table-responsive">
                                            <table id="tableRetiro" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                                <thead>
                                                <tr>
                                                    <th class="text-center"><spring:message code="Código" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Fecha Retiro"/></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Nombre del Participante"/></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Retiro" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Motivo" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Observación" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Fecha Registro" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Fallecido" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Detalle" /></th>
                                                </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="exampleModal" class="modal fade"  tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"> <i class="fa fa-user-times"></i> Detalles del Retiro.</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="casapediatrica">Casa PDCS</label>
                                    <input type="text" readonly class="form-control" id="casapediatrica" >
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="casafamilia">Casa Familia</label>
                                    <input type="text" class="form-control" id="casafamilia" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="otrosmotivo">Otros Motivos</label>
                                <input type="text" class="form-control" id="otrosmotivo" readonly>
                            </div>
                            <div class="form-group">
                                <label for="medicosupervisor">Supervisado Por</label>
                                <input type="text" class="form-control" id="medicosupervisor" readonly>
                            </div>
                            <div class="form-group">
                                <label for="personadocumenta">Documentado Por</label>
                                <input type="text" class="form-control" id="personadocumenta" readonly>
                            </div>
                            <div class="form-group">
                                <label for="quiencomunica">Comunicado Por</label>
                                <input type="text" class="form-control" id="quiencomunica" readonly>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="relFam">Relación Familiar</label>
                                    <input type="text" class="form-control" id="relFam" readonly>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="carnet">Devolvió Carnet?</label>
                                    <input type="text" class="form-control" id="carnet" readonly>
                                </div>

                            </div>


                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            <i class="fa fa-times-circle" aria-hidden="true"></i>
                            Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>


<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/notify.min.js" var="noty" />
<script type="text/javascript" src="${noty}"></script>

<spring:url value="/resources/js/views/retiro/listaRetiros.js" var="listret"/>
<script type="application/javascript" src="${listret}"></script>

<script type="text/javascript">
    $(document).ready(function(){
        setTimeout(function () {
            $('#page-loader').fadeOut('slow');
        }, 1400);
        var parametros={
            ListaHojaRetiroUrl: "${ListaHojaRetiroUrl}",
            DetallesRetiroUrl:"${DetallesRetiroUrl}",
            dataTablesLang: "${dataTablesLang}"
    };
        ListadoRetiros.init(parametros);

        $('#tableRetiro tbody').on('click', '.btnView', function () {
            var id = $(this).data('id');
            ver(id);
        });

        function ver(id){
            var jqxhr = $.getJSON(parametros.DetallesRetiroUrl, { idretiro : id,   ajax : 'true'  }, function(data){
                if(data.mensaje != null) $.notify(data.mensaje,"error");
                $("#casafamilia").val(data.casafamilia);
                $("#casapediatrica").val(data.casapediatrica);
                $("#medicosupervisor").val(data.medicosupervisor);
                $("#observacion").val(data.observacion);
                $("#otrosmotivo").val(data.otrosmotivo);
                $("#personadocumenta").val(data.personadocumenta);
                $("#quiencomunica").val(data.quiencomunica);
                $("#relFam").val(data.relFam);
                $("#carnet").val(data.carnet);
                $("#exampleModal").modal("show");
            }).fail(function(){
                swal("Error","falló el servidor","error");
            })
        }
        $("#parametro").focus();
    });
</script>
</body>
</html>