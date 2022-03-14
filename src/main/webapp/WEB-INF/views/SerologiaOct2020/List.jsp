<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 17/10/2020
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>
    <style>
        /*ini*/
        .toast-title {
            font-weight: bold;
        }
        .toast-message {
            -ms-word-wrap: break-word;
            word-wrap: break-word;
        }
        .toast-message a,
        .toast-message label {
            color: #ffffff;
        }
        .toast-message a:hover {
            color: #cccccc;
            text-decoration: none;
        }

        .toast-close-button {
            position: relative;
            right: -0.3em;
            top: -0.3em;
            float: right;
            font-size: 20px;
            font-weight: bold;
            color: #ffffff;
            -webkit-text-shadow: 0 1px 0 #ffffff;
            text-shadow: 0 1px 0 #ffffff;
            opacity: 0.8;
            -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=80);
            filter: alpha(opacity=80);
        }
        .toast-close-button:hover,
        .toast-close-button:focus {
            color: #000000;
            text-decoration: none;
            cursor: pointer;
            opacity: 0.4;
            -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=40);
            filter: alpha(opacity=40);
        }
        button.toast-close-button {
            padding: 0;
            cursor: pointer;
            background: transparent;
            border: 0;
            -webkit-appearance: none;
        }
        .toast-top-full-width {
            top: 0;
            right: 0;
            width: 100%;
        }
        .toast-bottom-full-width {
            bottom: 0;
            right: 0;
            width: 100%;
        }
        .toast-top-left {
            top: 12px;
            left: 12px;
        }
        .toast-top-right {
            top: 12px;
            right: 12px;
        }
        .toast-bottom-right {
            right: 12px;
            bottom: 12px;
        }
        .toast-bottom-left {
            bottom: 12px;
            left: 12px;
        }
        #toast-container {
            position: fixed;
            z-index: 999999;
            /*overrides*/

        }
        #toast-container * {
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }
        #toast-container > div {
            margin: 0 0 6px;
            padding: 15px 15px 15px 50px;
            width: 300px;
            -moz-border-radius: 3px 3px 3px 3px;
            -webkit-border-radius: 3px 3px 3px 3px;
            border-radius: 3px 3px 3px 3px;
            background-position: 15px center;
            background-repeat: no-repeat;
            -moz-box-shadow: 0 0 12px #999999;
            -webkit-box-shadow: 0 0 12px #999999;
            box-shadow: 0 0 12px #999999;
            color: #ffffff;
            opacity: 0.8;
            -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=80);
            filter: alpha(opacity=80);
        }
        #toast-container > :hover {
            -moz-box-shadow: 0 0 12px #000000;
            -webkit-box-shadow: 0 0 12px #000000;
            box-shadow: 0 0 12px #000000;
            opacity: 1;
            -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=100);
            filter: alpha(opacity=100);
            cursor: pointer;
        }

        #toast-container.toast-top-full-width > div,
        #toast-container.toast-bottom-full-width > div {
            width: 96%;
            margin: auto;
        }
        .toast {
            background-color: #030303;
        }
        .toast-success {
            background-color: #51a351;
        }
        .toast-error {
            background-color: #bd362f;
        }
        .toast-info {
            background-color: #2f96b4;
        }
        .toast-warning {
            background-color: #f89406;
        }
        /**/
        @media all and (max-width: 240px) {
            #toast-container > div {
                padding: 8px 8px 8px 50px;
                width: 11em;
            }
            #toast-container .toast-close-button {
                right: -0.2em;
                top: -0.2em;
            }
        }
        @media all and (min-width: 241px) and (max-width: 480px) {
            #toast-container > div {
                padding: 8px 8px 8px 50px;
                width: 18em;
            }
            #toast-container .toast-close-button {
                right: -0.2em;
                top: -0.2em;
            }
        }
        @media all and (min-width: 481px) and (max-width: 768px) {
            #toast-container > div {
                padding: 15px 15px 15px 50px;
                width: 25em;
            }
        }
        /*fin*/

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
        .font-light {
            font-weight: 300;
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
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/Serologia/listSerologia/" htmlEscape="true "/>"><spring:message code="List" /> <spring:message code="lbl.serologia" /></a>
            </li>
        </ol>
        <c:set var="recordDisabledLabel"><spring:message code="recordDisabled" /></c:set>
        <c:set var="successLabel"><spring:message code="process.success" /></c:set>
        <c:set var="cerrarCaso"><spring:message code="close.case" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <spring:url value="/Serologia/closeCase" var="closeUrl"/>
        <spring:url value="/Serologia/listSerologia" var="listSerologiaUrl"/>
        <spring:url value="/Serologia/editMuestra" var="editUrl"/>
        <spring:url value="/Serologia/enviarMuestra" var="envioUrl"/>
        <spring:url value="/Serologia/sendAllSerologias" var="sendAllSerologiasUrl"/>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div class="">
            <div class="row">
            <div class="col-md-10 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-white pb-3"><i class="fa fa-flask text-white" aria-hidden="true"></i> <spring:message code="lbl.serologia" /></h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block">  <spring:message code="List" /> <spring:message code="lbl.serologia" /></span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                            <span class="d-block d-md-none"><i class="ti-export"></i></span>
                            <span class="d-none d-md-block"> <spring:message code="Form" />
                                <spring:message code="Envío" />
                                <spring:message code="lbl.serologia" /></span>
                        </a>
                    </li>


                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
            <div>
                <div class="">
                    <br/>
                    <div hidden="hidden">
                        <button id="loadData" class="btn btn-primary"><i class="fa fa-download"></i> <spring:message code="Cargar Muestras" /></button>
                    </div>
                    <div class="col-md-12">
                        <a href="<spring:url value="/Serologia/create" htmlEscape="true"/>" class="btn btn-success btn-lg">
                            <i class="fa fa-plus" aria-hidden="true"></i> <spring:message code="lbl.new" /> <spring:message code="lbl.serologia" />  </a>
                    </div>
                    <hr/>
                    <div class="table-responsive">
                        <spring:url value="/Serologia/listMuestrasNoEnviadas" var="MxNoEnviadasUrl"/>
                        <table id="Lista_Muestra" class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th width="12%" hidden="hidden"><spring:message code="IDSEROLOGIA" /></th>
                                <th width="12%" class="text-center"><spring:message code="dateAdded" /></th>
                                <th width="10%" class="text-center"><spring:message code="lbl.envoy" /></th>
                                <th width="12%" class="text-center"><spring:message code="userstudies" /></th>
                                <th width="12%" class="text-center"><spring:message code="code" /></th>
                                <th width="10%" class="text-center"><spring:message code="volumen" /></th>
                                <th width="12%" class="text-center"><spring:message code="observacion" /></th>
                                <th width="16%" class="text-center"><spring:message code="edit" /></th>
                                <th width="16%" class="text-center"><spring:message code="delete" /></th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
            </div>
            <div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
                <div class="row">
                    <!-- inicia form inline -->
                    <div class="container">
                        <form class="" name="envio-allserologia-form" id="envio-allserologia-form">

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
                                <label for="temperatura" class="col-sm-2 col-form-label"><spring:message code="Temperatura" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="temperatura" name="temperatura" minlength="1" maxlength="4"  required="required">
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-primary btn-lg float-right"> <i class="fa fa-send" aria-hidden="false"></i>
                                        <spring:message code="lbl.dispatch" />
                                        <spring:message code="sample" />
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- fin form inline -->
                </div>

            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-lg" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-keyboard="false" data-aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel"><i class="fa fa-trash" aria-hidden="true"></i>
                    <spring:message code="lbl.invalidate" />
                </h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="#" autocomplete="off" id="close-form" name="close-form" class="form-horizontal">
                    <input type="hidden" class="form-control" id="idAccion" name="idAccion"/>
                    <div id="dvSalida" class="form-group row">
                        <div class="form-group col-md-12">
                            <label for="message_razon" class="col-form-label"> <spring:message code="rason.invalid" />
                                <span class="required">* </span>
                            </label>
                            <textarea class="form-control" id="message_razon" name="message_razon"></textarea>
                        </div>
                    </div>
                    <div class="d-flex justify-content-between">
                        <div class="p-2 bd-highlight"><button type="button" class="btn btn-secondary" data-dismiss="modal">
                            <i class="fa fa-times" aria-hidden="true"></i>
                            <spring:message code="cancel" /></button></div>
                        <div class="p-2 bd-highlight"></div>
                        <div class="p-2 bd-highlight">
                            <button type="submit" class="btn btn-primary">  <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
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

<spring:url value="/resources/js/views/SerologiaOct2020/ListSerologia.js" var="SeroJs" />
<script type="text/javascript" src="${SeroJs}"></script>

<script>
    jQuery(document).ready(function() {
        var misUrl ={
            "sendAllSerologiasUrl"  : "${sendAllSerologiasUrl}",
            "successMessage"        : "${successMessage}",
            "envioUrl"              : "${envioUrl}",
            "dataTablesLang"        : "${dataTablesLang}",
            "MxNoEnviadasUrl"       : "${MxNoEnviadasUrl}",
            "editUrl"               : "${editUrl}",
            "closeUrl"              : "${closeUrl}",
            "listSerologiaUrl"        : "${listSerologiaUrl}"
        }
        EnviarSerologiasForm.init(misUrl);

        var dt = new Date();
        var strDate =  dt.getDate() + "/" +  (dt.getMonth()+1)  + "/" + dt.getFullYear();
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
        }).val(moment().format('DD/MM/YYYY'));//.val(strDate);
    });
</script>
</body>
</html>

