<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />

    <!-- datatables-->
    <spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
    <link rel="stylesheet" href="${bdat4}" type="text/css"/>

    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

    <spring:url value="/resources/css/dtresponsive/buttons/buttons.bootstrap.min.css" var="dtbuttons" />
    <link rel="stylesheet" href="${dtbuttons}" type="text/css"/>
    <!-- fin datables-->
    <style>
        a.buttons-collection {
            margin-left: 1em;
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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/comparacion/cartas" htmlEscape="true "/>"><spring:message code="comparison" /> <spring:message code="letters" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.letters.comparison.1" /></strong>
                        </h3>
                    </div>
                            <div class="card-body">
                                <div class="card-block">
                                <div class="table-responsive">
                                <table id="lista_cartas1" class="table table-striped table-bordered" style="width:100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="code"/></th>
                                            <th><spring:message code="fecha"/></th>
                                            <th><spring:message code="usuario"/></th>
                                            <th><spring:message code="edad_actual"/></th>
                                            <th><spring:message code="cont_futuro"/></th>
                                            <th><spring:message code="asent"/></th>
                                            <th><spring:message code="A_App"/></th>
                                            <th><spring:message code="B_App"/></th>
                                            <th><spring:message code="C_App"/></th>
                                            <th><spring:message code="quien_firma_App"/></th>
                                            <th><spring:message code="rel_fam_App"/></th>
                                            <th><spring:message code="version"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                    </div>
                                 </div>
                            </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.letters.comparison.2" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <div class="card-block">
                                <div class="table-responsive">
                                    <table id="lista_cartas2"  class="table table-striped table-bordered dt-responsive" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="code"/></th>
                                            <th><spring:message code="fecha"/></th>
                                            <th><spring:message code="usuario"/></th>
                                            <th><spring:message code="edad_act"/></th>
                                            <th><spring:message code="edad_fdo"/></th>
                                            <th><spring:message code="A_App"/></th>
                                            <th><spring:message code="A_Car"/></th>
                                            <th><spring:message code="B_App"/></th>
                                            <th><spring:message code="B_Car"/></th>
                                            <th><spring:message code="C_App"/></th>
                                            <th><spring:message code="C_Car"/></th>
                                            <th><spring:message code="D_App"/></th>
                                            <th><spring:message code="D_Car"/></th>
                                            <th><spring:message code="E_App"/></th>
                                            <th><spring:message code="E_Car"/></th>
                                            <th><spring:message code="F_App"/></th>
                                            <th><spring:message code="F_Car"/></th>
                                            <th><spring:message code="ver_App"/></th>
                                            <th><spring:message code="ver_Car"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.letters.comparison.3" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <div class="card-block">
                                <div class="table-responsive">
                                    <table id="lista_cartas3"  class="table table-striped table-bordered dt-responsive" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="code"/></th>
                                            <th><spring:message code="fecha"/></th>
                                            <th><spring:message code="usuario"/></th>
                                            <th><spring:message code="edad_firma"/></th>
                                            <th><spring:message code="quien_firma_App"/></th>
                                            <th><spring:message code="quien_firma_Car"/></th>
                                            <th><spring:message code="rel_fam_App"/></th>
                                            <th><spring:message code="rel_fam_Car"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.conainer-fluid -->
        </div>
    </div>
    <jsp:include page="../fragments/bodyFooter.jsp" />
    <jsp:include page="../fragments/corePlugins.jsp" />
    <c:choose>
        <c:when test="${cookie.eIcsLang.value == null}">
            <c:set var="lenguaje" value="es"/>
        </c:when>
        <c:otherwise>
            <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
        </c:otherwise>
    </c:choose>
    <!-- GenesisUI main scripts -->
    <!-- datatables-->
    <spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
    <script type="text/javascript" src="${TablesResponsive}"></script>

    <spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
    <script type="text/javascript" src="${Tablesb4}"></script>

    <spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
    <script type="text/javascript" src="${TablesResponsive}"></script>

    <spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
    <script type="text/javascript" src="${TResponsiveb4}"></script>

    <spring:url value="/resources/js/libs/dataTableResponsive/buttons/dataTables.buttons.min.js" var="dTButtons" />
    <script type="text/javascript" src="${dTButtons}"></script>

    <spring:url value="/resources/js/libs/dataTableResponsive/buttons/buttons.bootstrap.min.js" var="buttonsBT" />
    <script type="text/javascript" src="${buttonsBT}"></script>

    <spring:url value="/resources/js/libs/jszip/jszip.min.js" var="buttonsJszip" />
    <script type="text/javascript" src="${buttonsJszip}"></script>

    <spring:url value="/resources/js/libs/pdfmake/pdfmake.min.js" var="buttonsPdf" />
    <script type="text/javascript" src="${buttonsPdf}"></script>

    <spring:url value="/resources/js/libs/pdfmake/vfs_fonts.js" var="pdfFonts" />
    <script type="text/javascript" src="${pdfFonts}"></script>

    <spring:url value="/resources/js/libs/dataTableResponsive/buttons/buttons.html5.min.js" var="buttonsHtml5" />
    <script type="text/javascript" src="${buttonsHtml5}"></script>
    <!-- fin datatables-->

    <spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
    <script src="${validateJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
    <script src="${loadingButtonsJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <!-- bootstrap datepicker -->
    <spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
    <script src="${datepickerPlugin}"></script>

    <script src="${jQValidationLoc}"></script>
    <spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
    <script src="${validateAMJs}" type="text/javascript"></script>

    <spring:url value="/resources/js/app.js" var="App" />
    <script src="${App}" type="text/javascript"></script>

    <spring:url value="/cartas/comparacion/getCartasSinDigitar" var="sCartas1Url"/>
    <spring:url value="/cartas/comparacion/getCartasPartes" var="sCartas2Url"/>
    <spring:url value="/cartas/comparacion/getCartasRelFam" var="sCartas3Url"/>

<script>
    jQuery(document).ready(function() {
        var table = $('#lista_cartas1').DataTable({
            dom:    "<'row'<'col-sm-4'B><'col-sm-4'f><'col-sm-4'l>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            buttons: [ 'excel'],
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            responsive: true,
            searching: true,
            paging: true,
            "ajax":{
                url: "${sCartas1Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigoParticipante', defaultContent: ""},
                { data: 'fechaFirma', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""},
                { data: 'edadActual', defaultContent: ""},
                { data: 'contactoFuturo', defaultContent: ""},
                { data: 'asentimiento', defaultContent: ""},
                { data: 'parteA', defaultContent: ""},
                { data: 'parteB', defaultContent: ""},
                { data: 'parteC', defaultContent: ""},
                { data: 'quienFirma', defaultContent: ""},
                { data: 'relacionFamiliar', defaultContent: ""},
                { data: 'versionCarta', defaultContent: ""}
            ]
        });

        $('#lista_cartas2').DataTable({
            dom:    "<'row'<'col-sm-4'B><'col-sm-4'f><'col-sm-4'l>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            "bFilter": true,
            "bInfo": true,
            "bPaginate": true,
            "bDestroy": true,
            "responsive": true,
            "pageLength": 10,
            "bLengthChange": true,
            "buttons": [
                {
                    extend: 'excel'
                }
            ],
            "ajax":{
                url: "${sCartas2Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigo', defaultContent: ""},
                { data: 'fechaFirma', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""},
                { data: 'edadActualMeses', defaultContent: ""},
                { data: 'edadMeses', defaultContent: ""},
                { data: 'aceptaParteASc', defaultContent: ""},
                { data: 'aceptaParteACc', defaultContent: ""},
                { data: 'aceptaParteBCc', defaultContent: ""},
                { data: 'aceptaParteBSc', defaultContent: ""},
                { data: 'aceptaParteCCc', defaultContent: ""},
                { data: 'aceptaParteCSc', defaultContent: ""},
                { data: 'aceptaParteDCc', defaultContent: ""},
                { data: 'aceptaParteDSc', defaultContent: ""},
                { data: 'aceptaParteECc', defaultContent: ""},
                { data: 'aceptaParteEc', defaultContent: ""},
                { data: 'aceptaParteFCc', defaultContent: ""},
                { data: 'aceptaParteFSc', defaultContent: ""},
                { data: 'versionCc', defaultContent: ""},
                { data: 'versionSc', defaultContent: ""}
            ]
        });

        $('#lista_cartas3').DataTable({
            dom:    "<'row'<'col-sm-4'B><'col-sm-4'f><'col-sm-4'l>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-5'i><'col-sm-7'p>>",
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            "bFilter": true,
            "bInfo": true,
            "bPaginate": true,
            "bDestroy": true,
            "responsive": true,
            "pageLength": 10,
            "bLengthChange": true,
            "buttons": [
                {
                    extend: 'excel'
                }
            ],
            "ajax":{
                url: "${sCartas3Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigoParticipante', defaultContent: ""},
                { data: 'fechaFirma', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""},
                { data: 'edadFirma', defaultContent: ""},
                { data: 'quienFirmaC', defaultContent: ""},
                { data: 'quienFirmaS', defaultContent: ""},
                { data: 'relacionFamiliarC', defaultContent: ""},
                { data: 'relacionFamiliarS', defaultContent: ""}
            ]
        });

    });


    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();
</script>
</body>
</html>