<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>Hemodinámica</title>
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
                <a href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
                    <spring:message code="Listado" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="col-sm-12 col-lg-12">
                    <div class="card">
                        <div class="card-header">
                            <h5 style="font-family: Roboto">
                                <i class="fa fa-users"></i> <spring:message code="Participante" />
                            </h5>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="Nueva Hoja" href="<spring:url value="/hemo/create" htmlEscape="true "/>">
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                        <spring:message code="Datos Generales" />
                                    </a>
                                </div>
                                <hr/>
                                <div class="col-sm-12">
                                <form action="#" id="select-Participante-form" autocomplete="off" class="form-horizontal">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label class="form-control-label" for="parametro"><spring:message code="please.enter"/> <spring:message code="parameter"/></label>
                                                <input type="text" class="form-control" id="parametro" name="parametro" placeholder="<spring:message code="please.enter"/> <spring:message code="parameter"/>">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                </div>
                                <hr/>
                                <br/>
                                <div class="col-sm-12">
                                    <br/>
                                    <hr/>
                                    <br/>
                                <div class="table-responsive">
                                    <table id="tablePartHemo" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                        <thead>
                                        <tr>
                                            <th class="text-center"><spring:message code="Código" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Nombre del Participante"/></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Edad" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Fecha Consulta" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Editar" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Detalle" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Reporte" /></th>
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
                <spring:url value="/hemo/ListaHoja" var="ListaHoja"/>
                <spring:url value="/hemo/edithemo/{idDatoHemo}" var="edithemoUrl">
                    <spring:param name="idDatoHemo" value="${lista.idDatoHemo}" />
                </spring:url>
                <spring:url value="/hemo/listDetailsHemo/{idDatoHemo}" var="listDetailsHemoUrl">
                    <spring:param name="idDatoHemo" value="${lista.idDatoHemo}" />
                </spring:url>
                <spring:url value="/reportes/ReporteHemodinamica" var="pdfUrl"/>
            </div>
        </div> <!-- /.conainer-fluid -->
    </div>
</div>

<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
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
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>


<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<!-- Custom scripts required by this view -->
<spring:url value="/resources/js/views/hemodinamica/SearchParticipante.js" var="BuscaPartiScript" />
<script src="${BuscaPartiScript}" type="text/javascript"></script>
<script>
    $(document).ready(function(){
        $("#tablePartHemo tbody").on("click", ".btnReporte",function(){
            var id = $(this).data('id');
            SendId(id);
        });
        function SendId(id){
            // http://localhost:8081/estudios_ics/reportes/ReporteHemodinamica/?idDatoHemo=e868722a-a855-4929-ba00-076df1b7ea5f
            debugger;
            window.open("${pdfUrl}?idDatoHemo="+id, '_blank');

        }
        $("#parametro").focus();
        var parametros={
            ListaHoja: "${ListaHoja}",
            dataTablesLang: "${dataTablesLang}",
            edithemoUrl: "${edithemoUrl}",
            listDetailsHemoUrl:"${listDetailsHemoUrl}",
            pdfUrl:"${pdfUrl}"
        };
        SearchHemoParticipant.init(parametros);
    });
</script>
</body>
</html>