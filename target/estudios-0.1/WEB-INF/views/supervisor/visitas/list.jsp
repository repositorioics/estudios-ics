<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../../fragments/sideBar.jsp" />
    <!-- Main content -->
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/reportes/super/visitas/" htmlEscape="true "/>"><spring:message code="visits" /></a>
            </li>
        </ol>
        <c:set var="exportar"><spring:message code="export" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="cerrarCaso"><spring:message code="close.case" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <c:set var="recordDisabledLabel"><spring:message code="recordDisabled" /></c:set>
        <c:set var="successLabel"><spring:message code="process.success" /></c:set>
        <c:set var="seleccionarCaso"><spring:message code="select.case" /></c:set>
        <c:set var="casoInactivo"><spring:message code="inactive.case" /></c:set>

        <spring:url value="/super/particaso/getParticipantsCasos" var="getCodesUrl"/>
        <spring:url value="/super/casacaso/closeCase" var="closeUrl"/>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-list-alt"></i> <spring:message code="visits" />
                </div>
                <div class="card-block">

                    <div class="row">
                        <div class="col-md-12">
                            <form action="#" autocomplete="off" id="search-form" class="form-horizontal">
                                <div class="form-group row">
                                    <label class="form-control-label col-md-2" for="codigoCasa"><spring:message code="house" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-home"></i>
                                    </span>
                                        <input id="codigoCasa" name="codigoCasa" type="text" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-2" for="codigoParticipante"><spring:message code="participant.code" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-user"></i>
                                    </span>
                                        <input id="codigoParticipante" name="codigoParticipante" type="text" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-2" for="fechaInicio"><spring:message code="start.visitDate" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        <input name="fechaInicio" id="fechaInicio" class="form-control from_date" type="text" data-date-end-date="+0d"  />
                                    </div>
                                    <label class="form-control-label col-md-2" for="fechaFin"><spring:message code="end.visitDate" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        <input name="fechaFin" id="fechaFin" class="form-control to_date" type="text" data-date-end-date="+0d"  />
                                    </div>
                                </div>
                                <br>
                                <br>
                                <div class="row">
                                    <div class="col-md-12" align="center">
                                        <button id="btnSearch" type="submit" name="btnSearch" class="btn btn-primary btn-ladda">
                                            <spring:message code="search" /> <i class="fa fa-search"></i>
                                        </button>
                                        <br>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <br>

                    <div class="row table-toolbar">
                        <div class="col-md-12">
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered datatable" id="lista_casos">
                            <thead>
                            <tr>
                                <th width="10%"><spring:message code="house" /></th>
                                <th width="15%"><spring:message code="logindate" /></th>
                                <th width="15%"><spring:message code="participant" /></th>
                                <th width="15%"><spring:message code="visit" /></th>
                                <th width="15%"><spring:message code="visitDate" /></th>
                                <th width="15%"><spring:message code="failVisit" /></th>
                                <th width="15%"><spring:message code="razon" /></th>
                                <th width="15%"><spring:message code="otra_razon" /></th>
                            </tr>
                            </thead>
                            <c:forEach items="${visitas}" var="parti">
                                <tr>
                                    <td><c:out value="${parti[1]}" /></td>
                                    <td><fmt:formatDate value="${parti[9]}" pattern="dd/MM/yyyy" /></td>
                                    <td><c:out value="${parti[2]}" /></td>
                                    <td><c:out value="${parti[3]}" /></td>
                                    <td><fmt:formatDate value="${parti[4]}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                                    <td><c:out value="${parti[8]}" /></td>
                                    <td><c:out value="${parti[5]}" /></td>
                                    <td><c:out value="${parti[6]}" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <br>
                    <br>
                    <br>
                </div>
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../../fragments/bodyFooter.jsp" />
<jsp:include page="../../fragments/corePlugins.jsp" />
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/scripts/reportes/visitas.js" var="reportVisits" />
<script src="${reportVisits}"></script>

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
<spring:url value="/reportes/super/getVisitas" var="searchUrl"/>
<script>
    jQuery(document).ready(function() {
        var parametros = {dataTablesLang: "${dataTablesLang}",
            sSwfPath: "${dataTablesTTSWF}",
            exportar:"${exportar}",
            searchUrl:"${searchUrl}"};
        ReportVisit.init(parametros);
        handleDatePickers("${lenguaje}");

    });


</script>
</body>
</html>