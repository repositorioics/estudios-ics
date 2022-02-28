
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp"/>

<spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
<link href="${uiCss}" rel="stylesheet" type="text/css"/>

<spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
<link href="${boot}" rel="stylesheet" type="text/css"/>
<!-- DATE PICKER -->
<spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
<link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

<style>
    span.error {
        display:block;
        visibility:hidden;
        color:red;
        font-size:90%;
    }
    #error {
        color:red;
        display:none;
    }
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
    .accordion .card {
        border: none;
        margin-bottom: 2px;
        border-radius: 0;
        box-shadow: 0 0 6px rgba(0,0,0,0.2);
    }
    .accordion .card .card-header {
        /*background: #6245dd;*/
        background: #5cc0de;
        padding-top: 7px;
        padding-bottom: 7px;
        border-radius: 0;
    }
    .accordion .card-header h2 {
        color: #fff;
        font-size: 1rem;
        font-weight: 500;
        font-family: "Roboto", sans-serif;
    }
    .accordion img {
        width: 150px;
    }
    .accordion .card-header h2 span {
        float: left;
        margin-top: 10px;
    }
    .accordion .card-header .btn {
        font-weight: 500;
    }
    .accordion .card-header i {
        color: #fff;
        font-size: 1.3rem;
        margin: 0 6px 0 -10px;
        font-weight: bold;
        position: relative;
        top: 5px;
    }
    .accordion .card-header button:hover {
        color: #23384e;
    }
    .accordion .card-body {
        color: #666;
    }
    #parametro-error {
        margin-right: 759px;
    }

    .form-control:disabled, .daterangepicker .input-mini:disabled, .input-group > .ui-select-bootstrap > input.ui-select-search.form-control:disabled, .form-control[readonly], .daterangepicker [readonly].input-mini, .input-group > .ui-select-bootstrap > input[readonly].ui-select-search.form-control {
        background-color: #e9ebec00;
        opacity: 1;
        font-stretch: semi-condensed;

    }
    #parametro-error {
        margin-right: 759px;
    }
</style>
<spring:url value="/resources/css/sweetalert.css" var="swalcss"/>
<link href="${swalcss}" rel="stylesheet" type="text/css"/>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="app-body">
<jsp:include page="../fragments/sideBar.jsp"/>
<!-- Main content -->
<div class="main">
<!-- Breadcrumb -->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home"/></a>
        <i class="fa fa-angle-right"></i>
        <a href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
            <spring:message code="List"/>
        </a>
        <i class="fa fa-angle-right"></i>
        <a href="<spring:url value="/cartas/Crear" htmlEscape="true "/>">
            <spring:message code="Form"/></a>
    </li>
</ol>
<div class="container-fluid">
<div class="animated fadeIn">

<div class="container col-sm-12 col-lg-12">
<div class="">
<div class="card">
<div class="card-header">
    <h5 style="font-family: Roboto">
        <i class="fa fa-users"></i> <spring:message code="lbl.To.assign"/> <spring:message code="letters"/>
    </h5>
</div>
<c:set var="userEnabledLabel"><spring:message code="login.userEnabled"/></c:set>
<c:set var="userDisabledLabel"><spring:message code="login.userDisabled"/></c:set>
<spring:url value="/cartas/searchParticipant" var="searchPartUrl"/>
<spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
<spring:url value="/cartas/ParteVersion" var="ParteVersionUrl"/>
<spring:url value="/cartas/saveScanCarta" var="saveScanCartaUrl"/>
<spring:url value="/cartas/ListadoCartaParticipant" var="Lista2ScanCartaUrl"/>
<spring:url value="/cartas/cartaSaveEdit" var="cartaSaveEditUrl"/>
<spring:url value="/cartas/listExtensiones" var="listExtensionesUrl"/>
<spring:url value="/cartas/getNombre1" var="getNombre1Url"/>
<spring:url value="/cartas/getNombre2" var="getNombre2Url"/>
<spring:url value="/cartas/getApellido1" var="getApellido1Url"/>
<spring:url value="/cartas/getApellido2" var="getApellido2Url"/>
<c:set var="successMessage"><spring:message code="process.success" /></c:set>
<c:set var="errorProcess"><spring:message code="process.error" /></c:set>
<div class="card-body">
<div class="row">
    <div class="col-sm-12">
        <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom"
           title="<spring:message code="back.list"/>"
           href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
            <i class="fa fa-list-ul" aria-hidden="true"></i>
            <spring:message code="List"/>
        </a>
    </div>
</div>
<br/>

<div class="container-lg">
<div class="row">
<div class="col-lg-12 mx-auto">
<div class="accordion mt-2" id="accordionExample">
<div class="card">
    <div class="card-header" id="headingOne">
        <h2 class="clearfix mb-0">
            <a id="one" class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                <i class="fa fa-plus-circle"></i> <spring:message code="Information"/> <spring:message code="participants"/></a>
        </h2>
    </div>
    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
        <div class="card-body">
            <div class="media">
                <div class="media-body">
                    <div class="container">
                        <form action="#" autocomplete="off" id="select-participante-form" name="select-participante-form" class="form-horizontal">
                            <div class="form-group row">
                                <label class="form-control-label col-md-2 text-right" for="username"><spring:message code="participant.code" />
                                    <span class="required">*</span>
                                </label>
                                <div class="input-group col-md-10">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input id="parametro" name="parametro" type="text" value="" class="form-control"/>
                                    <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right">
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                        <spring:message code="search" />
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <hr/>

                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label> <spring:message code="code" />:</label>
                            <input type="text" class="form-control form-control-sm" name="codigo" id="codigo" disabled/>
                            <span class="error"><spring:message code="code" /> <spring:message code="lbl.required" /></span>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="txtNombreCompleto"><spring:message code="participant" /> :</label>
                            <input type="text" class="form-control form-control-sm" id="txtNombreCompleto" name="txtNombreCompleto" disabled>
                        </div>

                        <div class="form-group col-md-1">
                            <label for="edadyear"><spring:message code="age"/> <spring:message code="lbl.years"/></label>
                            <input type="text" class="form-control form-control-sm" id="edadyear" name="edadyear" disabled>
                        </div>

                        <div class="form-group col-md-1">
                            <label for="edadmeses"><spring:message code="age"/> <spring:message code="lbl.mounths"/></label>
                            <input type="text" class="form-control form-control-sm" id="edadmeses" name="edadmeses" disabled>
                        </div>

                        <div class="form-group col-md-1">
                            <label for="edaddias"><spring:message code="age"/> <spring:message code="lbl.days"/></label>
                            <input type="text" class="form-control form-control-sm" id="edaddias" name="edaddias" disabled>
                        </div>

                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="estudios"><spring:message code="userstudies"/> :</label>
                            <input type="text" class="form-control form-control-sm" id="estudios" name="estudios" disabled>
                        </div>

                        <div class="form-group col-md-4">
                            <label for="padre"><spring:message code="lbl.father"/></label>
                            <input type="text" class="form-control form-control-sm" id="padre" name="padre" disabled>
                        </div>

                        <div class="form-group col-md-4">
                            <label for="madre"><spring:message code="lbl.mother"/> </label>
                            <input type="text" class="form-control form-control-sm" id="madre" name="madre" disabled>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="relacionFam"><spring:message code="lbl.family.relationship"/></label>
                            <input type="text" class="form-control form-control-sm" id="relacionFam" name="relacionFam" disabled>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="tutor"><spring:message code="lbl.tutor"/></label>
                            <input type="text" class="form-control form-control-sm" id="tutor" name="tutor" disabled>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="card">
    <div class="card-header" id="headingTwo">
        <h2 class="mb-0" style="font-family: Roboto, sans-serif">
            <a id="two" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                <i class="fa fa-plus-circle"></i>
                <spring:message code="Information" /> <spring:message code="consent" /></a>
        </h2>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
        <div class="card-body">
            <div class="media">
                <div class="media-body">
                    <form id="form-scan" class="needs-validation" autocomplete="off" novalidate>
                    <div class="row"  hidden="hidden">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="principal">principal</label>
                                <input type="text" class="form-control" disabled="disabled" id="principal"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group"  >
                                <label for="principal2">principal2</label>
                                <input type="text" class="form-control" disabled="disabled" id="principal2"/>
                            </div>
                        </div>
                    </div>

                        <div class="row">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="fechacarta"><spring:message code="lbl.date" /></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" required="required" name="fechacarta" id="fechacarta" data-date-end-date="+0d"/>
                                    <div class="invalid-feedback">
                                        <spring:message code="lbl.date" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="carta"><spring:message code="letters" />:</label>
                                    <span class="required text-danger"> * </span>
                                    <select name="carta" id="carta" name="carta" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        <spring:message code="letters" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="version"><spring:message code="versionLetters" />:</label>
                                    <span class="required text-danger"> * </span>
                                    <select name="version" id="version" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        <spring:message code="versionLetters" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="partes"><spring:message code="Letter.Parts" />:</label>
                                    <span class="required text-danger"> * </span>
                                    <select class="form-control select2-multiple" multiple id="partes" name="partes" required="required">
                                    </select>
                                    <div class="invalid-feedback">
                                        <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="person"><spring:message code="lbl.resource"/></label>
                                    <span class="required text-danger"> * </span>
                                    <select name="person" id="person" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select"/>...</option>
                                        <c:forEach items="${person}" var="p">
                                            <option value="${p.personal.idpersonal}">${p.personal.idpersonal} - ${p.personal.nombreApellido}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback">
                                        <spring:message code="lbl.resource"/> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="proyecto"><spring:message code="lbl.project" /> </label>
                                    <span class="required text-danger"> * </span>
                                    <select name="proyecto" id="proyecto" class="form-control">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${proyecto}" var="p">
                                            <option value="${p.catKey}"><spring:message code="${p.spanish}" /></option>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback">
                                        <spring:message code="lbl.project" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="asentimiento"><spring:message code="lbl.assent" /> </label>
                                    <span class="required text-danger"> * </span>
                                    <select name="asentimiento" id="asentimiento" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${SiNoNA}" var="s">
                                            <option value="${s.catKey}">${s.catKey} - <spring:message code="${s.spanish}" /></option>
                                        </c:forEach>
                                    </select>

                                    <div class="invalid-feedback">
                                        <spring:message code="lbl.assent" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="tipoasentimiento"><spring:message code="type.assent" /> </label>
                                    <span class="required text-danger"> * </span>
                                    <select name="tipoasentimiento" id="tipoasentimiento" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${tpoasent}" var="ta">
                                            <option value="${ta.catKey}"> ${ta.catKey} - <spring:message code="${ta.spanish}" /></option>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback">
                                        <spring:message code="type.assent" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>
                        </div><!-- fin row -->

                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="nombfirma"><spring:message code="first.name" /> <spring:message code="lbl.tutor" /> </label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control onlytext" id="nombfirma" name="nombfirma" placeholder="1er. Nombre tutor" required value="${obj.name1Tutor}"/>
                                    <div class="invalid-feedback">
                                        <spring:message code="first.name" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="nombre2Firma"><spring:message code="second.name" /> <spring:message code="lbl.tutor" /></label>
                                    <input type="text" class="form-control onlytext" id="nombre2Firma" name="nombre2Firma" placeholder="2do. Nombre tutor"  value="${obj.name2Tutor}">
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="apellido1Firma"><spring:message code="first.surname" /> <spring:message code="lbl.tutor" /></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control onlytext" id="apellido1Firma" required name="apellido1Firma" placeholder="1er. Apellido tutor" value="${obj.surname1Tutor}">
                                    <div class="invalid-feedback">
                                        <spring:message code="first.surname" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="apellido2Firma"><spring:message code="second.surname" /> <spring:message code="lbl.tutor" /></label>
                                    <input type="text" class="form-control onlytext" id="apellido2Firma" name="apellido2Firma" placeholder="2do. Apellido tutor" value="${obj.surname2Tutor}">
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="relfam"><spring:message code="family.relationship" /> </label>
                                    <span class="required text-danger"> * </span>
                                    <select name="relfam" id="relfam" class="form-control" required>
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${relFam}" var="rel">
                                            <option value="${rel.catKey}">${rel.catKey} - ${rel.spanish}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback">
                                        <spring:message code="family.relationship" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-2">

                                <div class="form-group">
                                    <label for="tipoCaso"><spring:message code="cases" />: </label>
                                    <select name="tipoCaso" id="tipoCaso" class="form-control">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${TipoCaso}" var="t">
                                            <option value="${t.catKey}">${t.spanish}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback">
                                        <spring:message code="cases" /> <spring:message code="lbl.required" />
                                    </div>
                                </div>


                            </div>

                            <div class="col-md-2">
                                <div class="form-group">
                                    <div class="form-check mt-4 text-center">
                                        <input class="form-check-input" type="checkbox" id="contactoFuturo" name="contactoFuturo">
                                        <label class="form-check-label" for="contactoFuturo">
                                            <spring:message code="lbl.Accept.future.contact" />
                                        </label>
                                    </div>
                                </div>
                               <%-- <p> <h5 class="text-primary" style="font-family: Roboto"><spring:message code="lbl.Accept.future.contact" /></h5> </p>
                                <p><input type="checkbox" name="contactoFuturo" id="contactoFuturo"  class="lcs_check" autocomplete="off" /></p>--%>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <div class="form-check mt-4 text-center">
                                        <input class="form-check-input chktestigo" type="checkbox" id="chkTestigo" name="chkTestigo">
                                        <label class="form-check-label" for="chkTestigo">
                                            <spring:message code="lbl.witness.present" />
                                        </label>
                                    </div>
                                </div>
                               <%-- <p> <h5 class="text-primary" style="font-family: Roboto"><spring:message code="lbl.witness.present" /></h5> </p>
                                <p><input type="checkbox" id="chktestigo" name="chktestigo"  class="lcs_check chktestigo" autocomplete="off" /></p>--%>
                            </div>
                            <br/>
                            <input type="text" name="aptoCovid" id="aptoCovid" hidden="hidden" disabled/>
                        </div>

                        <div id="selectt" style="display: none">
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="nombre1Testigo"><spring:message code="first.name" /> <spring:message code="lbl.witness" /> </label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control onlytext focusNext" tabindex="5" id="nombre1Testigo" name="nombre1Testigo" />
                                        <span class="error"><spring:message code="first.name" /> <spring:message code="lbl.required" />.</span>
                                    </div>

                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="nombre2Testigo"><spring:message code="second.name" /> <spring:message code="lbl.witness" /></label>
                                        <input type="text" class="form-control onlytext focusNext" tabindex="6" id="nombre2Testigo" name="nombre2Testigo">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="apellido1Testigo"><spring:message code="first.surname" /> <spring:message code="lbl.witness" /></label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control onlytext focusNext" tabindex="7" id="apellido1Testigo" name="apellido1Testigo">
                                        <span class="error"><spring:message code="first.surname" /> <spring:message code="lbl.required" />.</span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="apellido2Testigo"><spring:message code="second.surname" /> <spring:message code="lbl.witness" /></label>
                                        <input type="text" class="form-control onlytext focusNext" tabindex="8" id="apellido2Testigo" name="apellido2Testigo">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="observacion"><spring:message code="observacion" /></label>
                                    <textarea class="form-control"  id="observacion" name="observacion" placeholder="observacion" rows="3"></textarea>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-4">
                                <button type="submit" id="btnSave" class="btn btn-primary btn-block btn-lg">
                                    <i class="fa fa-floppy-o" aria-hidden="true"></i>
                                    <spring:message code="save" />
                                </button>
                            </div>
                            <div class="form-group col-md-4"></div>
                            <div class="form-group col-md-4">
                                <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                    <spring:message code="cancel" /></a>
                            </div>
                        </div>
                        <br/><br/>

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

<!-- FIN COLLAPSE -->
</div>
<div class="card-footer text-muted"></div>
</div>

</div>
</div>
</div>
</div>
<!-- /.conainer-fluid -->
</div>
</div><jsp:include page="../fragments/bodyFooter.jsp" />
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
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<!-- Custom scripts required by this view -->
<spring:url value="/resources/js/views/Cartas/Cartas.js" var="cartaScript" />
<script src="${cartaScript}" type="text/javascript"></script>
<c:set var="notFound"><spring:message code="noResults" /></c:set>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/select2.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/select2.min.js" var="selectMinJs" />
<script type="text/javascript" src="${selectMinJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>
<%--
<spring:url value="/resources/js/libs/lc_switch.js" var="lc" />
<script type="text/javascript" src="${lc}"></script>--%>

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        $('[data-toggle="tooltip"]').tooltip()
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

<script type="text/javascript">

    $(document).ready(function(){
        $(".collapse.show").each(function(){
            $(this).siblings(".card-header").find(".btn i").addClass("fa-minus-circle").removeClass("fa-plus-circle");
        });
        $(".collapse").on('show.bs.collapse', function(){
            $(this).parent().find(".card-header .btn i").removeClass("fa-plus-circle").addClass("fa-minus-circle");
        }).on('hide.bs.collapse', function(){
            $(this).parent().find(".card-header .btn i").removeClass("fa-minus-circle").addClass("fa-plus-circle");
        });
        /* $('.collapse').on('show.bs.collapse', function () {
         // do something…
         debugger;
         });
         $('.collapse').on('shown.bs.collapse', function (e) {
         console.log(e.target.id);
         });
         $('.collapse').on('hidden.bs.collapse', function (e) {
         console.log(e.target.id);
         });

         $("#two").on("click", function(e){
         debugger;
         if($("#codigo").val() == null || $("#codigo").val() == ""){
         console.error(e.target.id);
         return false;
         }else {
         return true;
         }
         });
 */
        $("#carta").select2();
        $("#version").select2();
        $("#person").select2();
        $("#relfam").select2();
        $("#proyecto").select2();
        $("#asentimiento").select2();
        $("#tipoasentimiento").select2();
        $("#tipoCaso").select2().val(0).trigger("change").prop('disabled',true);

        var parametros = {
            searchPartUrl       : "${searchPartUrl}",
            Lista2ScanCartaUrl  : "${Lista2ScanCartaUrl}",
            VersionCartatUrl    : "${VersionCartatUrl}",
            ParteVersionUrl     : "${ParteVersionUrl}",
            saveScanCartaUrl    : "${saveScanCartaUrl}",
            UpdateRetiroUrl     : "${UpdateRetiroUrl}",
            notFound            : "${notFound}",
            cartaSaveEditUrl    : "${cartaSaveEditUrl}",
            successmessage      : "${successMessage}",
            listExtensionesUrl  : "${listExtensionesUrl}",
            getNombre1Url       : "${getNombre1Url}",
            getNombre2Url       : "${getNombre2Url}",
            getApellido1Url     : "${getApellido1Url}",
            getApellido2Url     : "${getApellido2Url}"
        };
        scanCarta.init(parametros);
        var elementos = [];
        $("#version").prop('disabled',true);
        $("#partes").prop('disabled',true);
        $("#partes").select2({placeholder: "Selección parte"});
        $("#fechacarta").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true
        });

        $("#parametro").focus();
    });
</script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
</script>
</body>
</html>
