<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
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
                <i class="fa fa-angle-right"></i>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div id="page-loader">
                    <span class="preloader-interior"></span>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-12 col-lg-12">
                        <div class="card shadow-lg p-3 mb-5 bg-white text-black-50">
                            <div class="card-header">
                                <h5> <i class="fa fa-id-card-o" aria-hidden="true"></i> Cartas Participante</h5>
                            </div>
                            <div class="card-body">
                                <spring:url value="/Domicilio/searchParticipant" var="searchPartUrl"/>
                                <br/>
                                <div class="col-md-12">
                                    <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="Asignar Carta" href="<spring:url value="/cartas/Crear" htmlEscape="true "/>">
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                        <spring:message code="Asignar Carta" />
                                    </a>
                                </div>
                                <br/>
                                <div class="col-sm-12 col-md-12 col-lg-12">
                                    <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal ">
                                        <div class="row">
                                            <div class="form-group col-sm-12 col-md-6 col-lg-12">
                                                <label>Código del Participante:</label>
                                                <input type="text" class="form-control" placeholder="Ingrese el código Participante" id="parametro" name="parametro">
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <div class="col-md-12">
                                    <div class="table-responsive">
                                        <table id="tableCartParticipnt" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                            <thead>
                                            <tr>
                                                <th class="text-center"><spring:message code="Código" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Nombre del Participante"/></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Carta" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Versión" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Fecha" /></th>
                                                <th class="text-center"><spring:message code="Id" /></th>
                                                <th class="text-center"><spring:message code="Retirado" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Editar" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Detalle" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Reporte" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Retirar" /></th>
                                            </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                </div>

                            </div> <!-- fin card body -->
                        </div>  <!-- fin card body -->
                    </div>
                </div>
                <spring:url value="/cartas/GetCartasParticipante" var="GetCartasParticipanteUrl"/>
                <spring:url value="/cartas/VerParteCarta/" var="searchPartesUrl"></spring:url>
                <spring:url value="/cartas/EditCarta" var="EditCartaUrl"></spring:url>
                <spring:url value="/cartas/UpdateRetiro" var="UpdateRetiroUrl"/>
                <spring:url value="/reportes/ReporteCarta/" var="pdfCartaUrl"/>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Detalle Parte</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <div class="table-responsive">
                                <table class="table table-hover table-bordered table-lg" id="tblDetalleParte" width="100%">
                                    <thead>
                                    <tr class="table-info">
                                        <th scope="col">#</th>
                                        <th scope="col" class="text-center">Parte</th>
                                        <th scope="col" class="text-center">Acepta</th>
                                    </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.conainer-fluid -->
        <div id="exampleModal2" class="modal fade" tabindex="-1" role="modal" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" style="font-weight: bold" id="exampleModalLabel2">
                    <i class="fa fa-thumbs-o-down" aria-hidden="true"></i>  Retirar a  <strong id="codpart"> </strong></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="IdParticipante" class="form-control"/>
                    <input type="hidden" id="retirado" class="form-control"/>
                    <br/>
                    <h5 class="modal-title  text-center" style="font-weight: bold" id="cuerpo">
                        <span id="nombre"></span> <br/>
                        <span id="carta"></span>,
                        <span id="version"></span>
                    </h5>
                    <br/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <i class="fa fa-times-circle" aria-hidden="true"></i>
                        Cancelar</button>
                    <button type="button" id="btnRetirar"  class="btn btn-primary">
                        <i class="fa fa-refresh"></i>
                            Realizar Retiro
                    </button>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/views/Cartas/ListadoCartaParticipnt.js" var="BuscaCartaPScript" />
<script src="${BuscaCartaPScript}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>


<script>
    $(document).ready(function(){
        setTimeout(function () {
            $('#page-loader').fadeOut('slow');
        }, 1500);
        $("#tableCartParticipnt tbody").on("click", ".btnRetiro", function () {
            var currow = $(this).closest('tr');
            var col0 = currow.find('td:eq(0)').text();
            var col1 = currow.find('td:eq(1)').text();
            var col2 = currow.find('td:eq(2)').text();
            var col3 = currow.find('td:eq(3)').text();
            var col5 = currow.find('td:eq(5)').text();
            $("#codpart").text(col0);
            $("#nombre").text(col1);
            $("#carta").text(col2);
            $("#version").text(col3);
            $("#IdParticipante").val($(this).data("id"));
            $("#exampleModal2").modal("show");
        })
        $('#tableCartParticipnt tbody').on('click', '.btnReporte', function () {
            var id = $(this).data('id');
            CrearReporte(id);
        });
        function CrearReporte(id){
            if(id != null){
                window.open("${pdfCartaUrl}?idparticipantecarta="+id, '_blank');
            }
        }
        $("#parametro").focus();
            var parametros = {
                dataTablesLang: "${dataTablesLang}",
                listCartaParticipntUrl : "${listCartaParticipntUrl}",
                GetCartasParticipanteUrl : "${GetCartasParticipanteUrl}",
                searchPartesUrl : "${searchPartesUrl}",
                EditCartaUrl : "${EditCartaUrl}",
                UpdateRetiroUrl:"${UpdateRetiroUrl}",
                pdfCartaUrl:"${pdfCartaUrl}"
            };
        SearchCartaParticipant.init(parametros);

    });
</script>
</body>
</html>
