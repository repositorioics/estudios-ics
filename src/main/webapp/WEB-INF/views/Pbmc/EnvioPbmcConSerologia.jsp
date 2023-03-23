<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 15/02/2022
  Time: 03:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

    <style>

        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid transparent;
            border-radius: 0;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link {
            border: 0;
            color: #fff;
            border-bottom: 3px solid transparent;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 3px solid #2cd07e;
        }
        .no-wrap td, .no-wrap th {
            white-space: nowrap;
        }
        .table td, .table th {
            padding: .9375rem .4rem;
            vertical-align: top;
            border-top: 1px solid rgba(120,130,140,.13);
        }
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #028dba;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #028dba;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 5px solid #fff
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
                <a href="<spring:url value="/Pbmc/listPbmc" htmlEscape="true "/>"><spring:message code="List" /> <spring:message code="Pbmc" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <!-- init card -->
                <div class="">
                <div class="row">
                <div class="col-md-10 col-lg-12">
                <div class="card">
                <div class="card-body bg-primary text-white mailbox-widget pb-0">
                    <h2 class="text-white pb-3">  <spring:message code="Envío" /> <spring:message code="Serologia" /> <spring:message code="Pbmc" /></h2>
                    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                                <span class="d-none d-md-block"><spring:message code="List" /> <spring:message code="Serologia" /> <spring:message code="Pbmc" /></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                                <span class="d-block d-md-none"><i class="ti-export"></i></span>
                                <span class="d-none d-md-block"><spring:message code="Form" /> <spring:message code="Envío" /></span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
                <div>
                    <div class="table-responsive">
                        <c:set var="successLabel"><spring:message code="process.success" /></c:set>
                        <spring:url value="/Pbmc/sendSerologiaConPbmc" var="sendSerologiaConPbmcUrl"/>
                        <table id="tblSeroConPbmc" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="text-center" width="12%"><spring:message code="sample.date" /></th>
                                <th class="text-center" width="12%"><spring:message code="code" /></th>
                                <th class="text-center" width="12%"><spring:message code="volumen" /></th>
                                <th class="text-center" width="12%"><spring:message code="userstudies" /></th>
                                <th class="text-center" width="12%"><spring:message code="userdesc" /></th>
                                <th class="text-center" width="12%"><spring:message code="lbl.envoy" /></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pbmcConSerologia}"  var="pbmc">
                                <tr>
                                    <td><fmt:formatDate value="${pbmc.fecha}" pattern="dd/MM/yyyy" /></td>
                                    <td><c:out value="${pbmc.participante}" /></td>
                                    <td><c:out value="${pbmc.volumen}" /></td>
                                    <td><c:out value="${pbmc.estudio}" /></td>
                                    <td><c:out value="${pbmc.descripcion}" /></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${pbmc.enviado =='1'}">
                                                <i class="fa fa-check text-success" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Si"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa fa-times text-danger" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="No"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                </div>
                <div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
                    <div class="container">
                        <form name="envio-allpbmcConSero-form" id="envio-allpbmcConSero-form" autocomplete="off">

                            <div class="form-group row">
                                <label for="fechaEnvio" class="col-sm-2 col-form-label"><spring:message code="dateAdded" /></label>
                                <div class="col-sm-10">
                                    <input name="fechaEnvio" id="fechaEnvio" class="form-control date-picker" type="text" data-date-end-date="+0d" required="required" />
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="horaEnvio" class="col-sm-2 col-form-label"><spring:message code="lbl.Hour" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="horaEnvio" name="horaEnvio">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="desde" class="col-sm-2 col-form-label"><spring:message code="lbl.from" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control from_date datepicker" id="desde" name="desde" data-date-end-date="+0d">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="desde" class="col-sm-2 col-form-label"><spring:message code="lbl.until" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control to_date datepicker" id="hasta" name="hasta" data-date-end-date="+0d">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label" for="numenvio"><spring:message code="lbl.send" /></label>
                                <div class="col-sm-10">
                                    <select id="numenvio" name="numenvio" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${numero_envio}" var="n">
                                            <option value="${n.catKey}">${n.spanish}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="temperatura" class="col-sm-2 col-form-label"><spring:message code="temp" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="temperatura" name="temperatura" minlength="1" maxlength="4"  required="required">
                                </div>
                            </div>

                            <!-- Lugar de envio MA2023 -->
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label" for="numenvio"><spring:message code="lbl.sendTo" /></label>
                                <div class="col-sm-10">
                                    <select id="lugarEnvio" name="lugarEnvio" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${lugar_envio}" var="l">
                                            <option value="${l.catKey}">${l.spanish}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-primary btn-lg float-right"> <i class="fa fa-send" aria-hidden="false"></i>
                                        <spring:message code="lbl.dispatch" />
                                        <spring:message code="sample" /> <spring:message code="Serologia" /> <spring:message code="Pbmc" />
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                <!-- fin card-->
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
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
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
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

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/views/Pbmc/EnvioPbmcConSerologia.js" var="EnvioPbmcConSerologiaJs" />
<script type="text/javascript" src="${EnvioPbmcConSerologiaJs}"></script>

<script>
    $(document).ready(function(){
        var parametros = {
            "successLabel"           : "${successLabel}",
            "dataTablesLang"         : "${dataTablesLang}",
            "sendSerologiaConPbmcUrl": "${sendSerologiaConPbmcUrl}"
        }
        EnvioPbmcConSerologia.init(parametros);
        $("#desde").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));
        $("#hasta").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));
        var hora = moment().format('HH:mm');
        $("#horaEnvio").val(hora);
        $("#fechaEnvio").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));

    });
</script>

</body>
</html>