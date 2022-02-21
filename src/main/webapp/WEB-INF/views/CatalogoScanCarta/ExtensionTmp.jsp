<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 10/05/2020
  Time: 18:31
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
    <spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
    <link href="${uiCss}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss"/>
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

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
        .alert-warning {
            background-color: #fcf8e3;
            border-color: #faf2cc;
            color: #8a6d3b;
        }
    .mailbox-widget .custom-tab .nav-item .nav-link.active {
        background: 0 0;
        color: #fff;
        border-bottom: 5px solid #fff
    }
    .form-control:disabled, .daterangepicker .input-mini:disabled, .input-group > .ui-select-bootstrap > input.ui-select-search.form-control:disabled, .form-control[readonly], .daterangepicker [readonly].input-mini, .input-group > .ui-select-bootstrap > input[readonly].ui-select-search.form-control {
        background-color: #e9ebec00;
        opacity: 1;
        cursor: not-allowed;
    }
    </style>
    <spring:url value="/resources/css/sweetalert.css" var="swalcss"/>
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

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
                <a href="<spring:url value="/CatalogoParte/ListadoParte" htmlEscape="true "/>">
                    <spring:message code="List" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(editParteUrl)}">
                    <spring:message code="Extension" />
                </a>
                <i class="fa fa-angle-right"></i>
                <spring:message code="${part.parte}" />
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div class="">
            <div class="row">
            <div class="col-md-12 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-white pb-3"> <spring:message code="Extension"/> <spring:message code="letters"/> <spring:message code="lbl.temporary"/> </h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block"><spring:message code="Form" /></span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
            <div>
            <form id="frmExtensionTmp" class="needs-validation" novalidate autocomplete="off">
                <spring:url value="/cartas/ListadoCartaParticipant" var="listCartaUrl"></spring:url>
                <spring:url value="/cartas/saveExtensionTmp" var="saveExtensionTmpUrl"></spring:url>
                <spring:url value="/cartas/ExtensionTmpPasive" var="ExtensionTmpPasiveUrl"/>
                <c:set var="successMessage"><spring:message code="process.success"/></c:set>
                <c:set var="errorProcess"><spring:message code="process.error"/></c:set>
                <spring:url value="/cartas/deletExt" var="delUrl"></spring:url>
                <spring:url value="/cartas/CartaParticipantTmp" var="formTmpUrl"></spring:url>
                <spring:url value="/cartas/getNombre1" var="getNombre1Url"/>
                <spring:url value="/cartas/getNombre2" var="getNombre2Url"/>
                <spring:url value="/cartas/getApellido1" var="getApellido1Url"/>
                <spring:url value="/cartas/getApellido2" var="getApellido2Url"/>
                <spring:url value="/cartas/listExtensionTmp" var="listExtensionTmpUrl"/>
                <div class="form-row" hidden="hidden">
                    <div class="form-group col-md-3">
                        <label for="idParticipantExtensiontmp">idParticipantExtensiontmp</label>
                        <input type="text" class="form-control" id="idParticipantExtensiontmp" name="idParticipantExtensiontmp"  value="${caso.idParticipantExtensiontmp}">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="editando">editando</label>
                        <input type="text" class="form-control" id="editando" name="editando"  value="${editando}">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="participantecartatmp">participantecartatmp</label>
                        <input type="text" class="form-control" id="participantecartatmp" name="participantecartatmp"  value="${participantecartatmp.id}">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="idversion">idversion</label>
                        <input type="text" class="form-control" id="idversion" name="idversion"  value="${participantecartatmp.version.idversion}">
                    </div>
                </div>

                <div class="form-row">
                <div class="col-md-2">
                    <div class="form-group">
                        <label for="idparticipante"><spring:message code="participant"/>:</label>
                        <input type="text" class="form-control" disabled name="idparticipante" id="idparticipante" value="${participantecartatmp.idparticipante}"/>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="form-group">
                        <label for="estudio"><spring:message code="study"/>:</label>
                        <input type="text" class="form-control" disabled name="estudio" id="estudio" value="${participantecartatmp.version.estudio.nombre}"/>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label for="version"><spring:message code="version"/>:</label>
                        <input type="text" class="form-control" disabled name="version" id="version" value="${participantecartatmp.version.version}"/>
                    </div>
                </div>

                    <div class="form-group col-md-4">
                        <label for="partesAccept"><spring:message code="lbl.Parts" /> <spring:message code="lbl.Accepted" />:</label>
                        <input type="text" class="form-control" id="partesAccept" name="partesAccept" value="${partesAccept}" readonly>
                    </div>

                </div>

                <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="fechaExtension"><spring:message code="Fecha Extension:" /></label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control" required="required" name="fechaExtension" id="fechaExtension" data-date-end-date="+0d"
                                   value="<fmt:formatDate value="${caso.fechaExtension}" pattern="dd/MM/yyyy" />" />
                            <div class="invalid-feedback">
                                Campo requerido.
                            </div>
                        </div>

                    <div class="form-group col-md-4">
                        <label for="idExtension"><spring:message code="Extension:" /></label>
                        <span class="required text-danger"> * </span>
                        <select class="form-control" name="idExtension" id="idExtension" required="required">
                            <option selected value=""><spring:message code="select"/>...</option>
                            <c:forEach items="${exts}" var="e">
                                <c:choose>
                                    <c:when test="${caso.extensiones.id eq e.id}">
                                        <option selected value="${e.id}">${e.extension}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${e.id}"> ${e.extension} </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Seleccione la extensión.
                        </div>
                    </div>

                    <div class="form-group col-md-4">
                        <div class="">
                            <label for="person"><spring:message code="lbl.resource"/></label>
                            <span class="required text-danger"> * </span>
                            <select name="person" id="person" class="form-control" required="required">
                                <option selected value=""><spring:message code="select"/>...</option>
                                <c:forEach items="${person}" var="p">
                                    <c:choose>
                                        <c:when test="${caso.personal.idpersonal eq p.personal.idpersonal}">
                                            <option selected value="${p.personal.idpersonal}">${p.personal.idpersonal} - ${p.personal.nombreApellido}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${p.personal.idpersonal}">${p.personal.idpersonal} - ${p.personal.nombreApellido}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                <spring:message code="lbl.resource"/> <spring:message code="lbl.required" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="nombre1Tutor"><spring:message code="1er. Nombre Tutor:" /></label>
                        <input type="text" class="form-control onlytext" id="nombre1Tutor" name="nombre1Tutor" required="required" value="${nombre1Tutor}">
                        <div class="invalid-feedback">
                            Campo reqierido.
                        </div>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="nombre2Tutor"><spring:message code="2do. Nombre Tutor:" /></label>
                        <input type="text" class="form-control onlytext" id="nombre2Tutor" name="nombre2Tutor"  value="${nombre2Tutor}">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="apellido1Tutor"><spring:message code="1er. Apellido Tutor:" /></label>
                        <input type="text" class="form-control onlytext" id="apellido1Tutor" name="apellido1Tutor" required="required"  value="${Surname1tutor}">
                        <div class="invalid-feedback">
                            Campo Requerido.
                        </div>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="apellido2Tutor"><spring:message code="2do. Apellido Tutor:" /></label>
                        <input type="text" class="form-control onlytext" id="apellido2Tutor" name="apellido2Tutor" value="${Surname2tutor}">
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="relfam"><spring:message code="family.relationship" /> </label>
                            <span class="required text-danger"> * </span>
                            <select name="relfam" id="relfam" class="form-control" required>
                                <option selected value=""><spring:message code="select" />...</option>
                                <c:forEach items="${relFam}" var="rel">
                                    <c:choose>
                                        <c:when test="${relFamTutor eq rel.catKey }">
                                            <option selected value="${rel.catKey}">${rel.catKey}-${rel.spanish}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${rel.catKey}">${rel.catKey}-${rel.spanish}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                <spring:message code="family.relationship" /> <spring:message code="lbl.required" />
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-check mt-4">
                            <c:choose>
                                <c:when test="${caso.testigoPresente eq true}">
                                    <input type="checkbox" checked="checked" id="chktestigo" name="chktestigo" />
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="chktestigo" name="chktestigo"   class="chktestigo"  />
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label" for="chktestigo">
                                <spring:message code="lbl.witness.present" />
                            </label>
                        </div>
                    </div>
                </div>
                <br/>
                <div id="showDivTestigo" style="display: none">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="nombre1Testigo"><spring:message code="first.name" /> <spring:message code="lbl.witness" />: </label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext onlytext" tabindex="5" id="nombre1Testigo"
                                       name="nombre1Testigo" value="${caso.nombre1Testigo}"/>
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="nombre2Testigo"><spring:message code="second.name" /> <spring:message code="lbl.witness" />:</label>
                                <input type="text" class="form-control focusNext onlytext" tabindex="6" id="nombre2Testigo"
                                       name="nombre2Testigo" value="${caso.nombre2Testigo}">
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="apellido1Testigo"><spring:message code="first.surname" /> <spring:message code="lbl.witness" />:</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext onlytext" tabindex="7" id="apellido1Testigo"
                                       name="apellido1Testigo" value="${caso.apellido1Testigo}">
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="apellido2Testigo"><spring:message code="second.surname" /> <spring:message code="lbl.witness" />:</label>
                                <input type="text" class="form-control focusNext onlytext" tabindex="8" id="apellido2Testigo"
                                       name="apellido2Testigo" value="${caso.apellido2Testigo}">
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label for="observacion"><spring:message code="observacion" />:</label>
                            <textarea name="observacion" id="observacion" cols="10" rows="5" class="form-control focusNext"
                                      tabindex="9">${caso.observacion}</textarea>
                        </div>
                    </div>
                <div class="form-row">
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-primary btn-lg"> <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>
                    </div>
                    <div class="col-md-3"></div>
                    <div class="col-md-3"></div>
                    <div class="col-md-3">
                        <a href="${fn:escapeXml(formTmpUrl)}" class="btn btn-warning btn-lg btn-ladda float-right" data-style="expand-right">
                            <i class="fa fa-minus-circle" aria-hidden="true"></i> <spring:message code="cancel" /></a>
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
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

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

<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin"/>
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<script type="text/javascript">
    $(document).ready(function(){
      $("#idExtension").select2();
      $("#person").select2();
      $("#relfam").select2();
        var parameters = {
            saveExtensionTmpUrl   : "${saveExtensionTmpUrl}",
            successmessage        : "${successMessage}",
            error                 : "${errorProcess}",
            ExtensionTmpPasiveUrl : "${ExtensionTmpPasiveUrl}",
            getNombre1Url         : "${getNombre1Url}",
            getNombre2Url         : "${getNombre2Url}",
            getApellido1Url       : "${getApellido1Url}",
            getApellido2Url       : "${getApellido2Url}",
            listExtensionTmpUrl   : "${listExtensionTmpUrl}",
            formTmpUrl            : "${formTmpUrl}"
        };
        $("#fechaExtension").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true
        });
        var table = $("#tblExtensionesTmp").DataTable( {
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });
        $("#chktestigo").click(function () {
            if ($(this).is(":checked")) {
                $("#showDivTestigo").fadeIn("slow");
                $("[name='nombre1Testigo']").prop("required", true);
                $("[name='apellido1Testigo']").prop("required", true);
            } else {
                limpiarTxtTestigo();
                $("#showDivTestigo").fadeOut("slow");
                $("[name='nombre1Testigo']").prop("required", false);
                $("[name='apellido1Testigo']").prop("required", false);
            }
        });

        function limpiarTxtTestigo(){
            $("#nombre1Testigo").val('');
            $("#nombre2Testigo").val('');
            $("#apellido1Testigo").val('');
            $("#apellido2Testigo").val('');
        }


        $('.onlytext').keypress(function (e) {
            var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
            return !((tecla > 47 && tecla < 58) || tecla == 46);
        });
        var form = $("#frmExtensionTmp");
        jQuery.validator.addMethod("letras_espacios", function(value, element) {
            return this.optional(element) || /^[a-zA-Z\s]+$/.test(value);
        }, "Sólo letras y espacios");

        form.validate({
            rules:{
                nombre1Tutor    :{ required: true, letras_espacios:true },
                nombre2Tutor    :{ letras_espacios:true },
                apellido1Tutor  :{ required: true, letras_espacios:true },
                apellido2Tutor  :{ letras_espacios:true },
                fechaExtension  :{ required: true },
                idExtension     :{ required: true}
            },
            errorElement: 'em',
            errorPlacement: function ( error, element ) {
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    error.insertAfter( element );
                }
            },
            highlight: function ( element, errorClass, validClass ) {
                $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
            },
            unhighlight: function (element, errorClass, validClass) {
                $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
            },
            submitHandler: function (form) {
                GuardarExtensionTmp(parameters);
            }
        });
        function GuardarExtensionTmp(dir){
            $.post(dir.saveExtensionTmpUrl, form.serialize(), function(data){
                if(data.mensaje!=null){
                    swal({
                        title: "Error!",
                        text:  data.mensaje,
                        type: "error",
                        closeOnConfirm: true,
                        timer: 2000
                    });
                }else{
                    swal({
                        title: "Buen Trabajo!",
                        text:  dir.successmessage,
                        type: "success",
                        closeOnConfirm: true,
                        timer: 2000
                    });
                    window.setTimeout(function(){
                     window.location.href = parameters.formTmpUrl;
                     }, 1000);
                }
            }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                swal("Error!", "Interno del Servidor.","error");
            });
        }

        $('#tblExtensionesTmp').on('click', '.deleteExtTmp', function(e) {
            e.preventDefault();
            var currentRow = $(this).closest("tr");
            var row = $(this).parents('tr');
            var id_tabla_ext_tmp = table.row(currentRow).data()[0];
            var idparticipant = table.row(currentRow).data()[1];
            var fecha_ext_tmp = table.row(currentRow).data()[2];
            swal({
               title: "Anular? ",
               text: "Extension: " + idparticipant + " con Fecha: " + fecha_ext_tmp,
               type: "warning",
               showCancelButton: true,
               confirmButtonClass: "btn-danger",
               confirmButtonText: "Si,",
               cancelButtonText: "No, plx!",
               closeOnConfirm: false,
               closeOnCancel: false
              }, function(isConfirm) {
                  if (isConfirm) {
                      $.post(parameters.ExtensionTmpPasiveUrl, { codigo: id_tabla_ext_tmp, ajax: 'true'}).done(function(data) {
                          //debugger;
                          //console.log(data);
                          swal("Anulado!", "con éxito!", "success");
                          row.remove();
                      }).fail(function() {
                          setTimeout(function() {
                              swal("Error!", "Servidor no respode!", "error");
                          }, 2000);
                      });
                  } else {
                      swal("Cancelado!", "Registro está seguro! :)", "error");
                        }
                    });
        });


        mostrarTestigo();
        function mostrarTestigo(){
            var status = ($("#chktestigo").is(':checked')) ? 'checked' : 'unchecked';
            if($("#chktestigo").prop('checked')){
                $("#showDivTestigo").fadeIn("slow");
                $("#nombre1Testigo").prop('required',true);
                $("#apellido1Testigo").prop('required',true);
            }else{
                $("#showDivTestigo").fadeOut("slow");
                $("#nombre1Testigo").prop('required', false);
                $("#apellido1Testigo").prop('required', false);
            }
        }

        $( "#nombre1Tutor" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parameters.getNombre1Url, {nombre1: $('#nombre1Tutor').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        $( "#nombre2Tutor" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parameters.getNombre2Url, {nombre2: $('#nombre2Tutor').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        $( "#apellido1Tutor" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parameters.getApellido1Url, {apellido1: $('#apellido1Tutor').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        $( "#apellido2Tutor" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parameters.getApellido2Url, {apellido2: $('#apellido2Tutor').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });
    })
</script>
</body>
</html>
