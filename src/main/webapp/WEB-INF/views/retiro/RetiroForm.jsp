<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 29/10/2020
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/smartWizardCss/smarthWizardCss.css" var="smw" />
    <link href="${smw}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/smartWizardCss/smart_wizard_theme_arrows.min.css" var="smwtheme" />
    <link href="${smwtheme}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <style>
        input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; font-family: Roboto; font-size: 14 }

        .btn-raised {
            transition: box-shadow .4s cubic-bezier(.25, .8, .25, 1), transform .4s cubic-bezier(.25, .8, .25, 1);
            box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .26)
        }

        .btn-raised:not([disabled]):active,
        .btn-raised:not([disabled]):focus,
        .btn-raised:not([disabled]):hover {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, .4);
            transform: translate3d(0, -1px, 0)
        }

        .shadowbtn {
            overflow: hidden;
            position: relative;
            transform: translate3d(0, 0, 0)
        }

        .shadowbtn:before {
            content: "";
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            width: auto;
            height: auto;
            pointer-events: none;
            background-image: radial-gradient(circle, #000 10%, transparent 10.01%);
            background-repeat: no-repeat;
            background-position: 50%;
            transform: scale(10, 10);
            opacity: 0;
            transition: transform .5s, opacity 1.5s
        }

        .shadowbtn:active:before {
            transform: scale(0, 0);
            opacity: .1;
            transition: 0s
        }

        .my-button,
        .my-2 {
            margin-bottom: .5rem !important
        }

        .w-xs {
            width: 100px
        }

        .bg-white .fill {
            fill: #448bff
        }

        .red {
            background-color: #f44336;
            color: #fff
        }

        .indigo {
            background-color: #3f51b5;
            color: #fff
        }

        .blue {
            background-color: blue;
            color: #fff
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

        .card-box {
            padding: 20px;
            border-radius: 3px;
            margin-bottom: 30px;
            background-color: #fff;
        }
        .search-result-box .tab-content {
            padding: 30px 30px 10px 30px;
            -webkit-box-shadow: none;
            box-shadow: none;
            -moz-box-shadow: none
        }

        .search-result-box .search-item {
            padding-bottom: 20px;
            border-bottom: 1px solid #e3eaef;
            margin-bottom: 20px
        }
        .text-success {
            color: #0acf97!important;
        }
        a {
            color: #007bff;
            text-decoration: none;
            background-color: transparent;
        }
        .btn-custom {
            background-color: #02c0ce;
            border-color: #02c0ce;
        }

        .btn-custom, .btn-danger, .btn-info, .btn-inverse, .btn-pink, .btn-primary, .btn-purple, .btn-success, .btn-warning {
            color: #fff!important;
        }
        .form-control-feedback {
            margin-top: 0.25rem;
            width: 16%;
            text-align: center;
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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/retiro/ListRetiro/" htmlEscape="true "/>"><spring:message code="List"/> <spring:message code="retirement"/></a>
                <i class="fa fa-angle-right"></i><a href="<spring:url value="/retiro/saveRetiroForm/" htmlEscape="true "/>">
                <spring:message code="Form" /> <spring:message code="retirement"/>
            </a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div id="page-loader">
                <span class="preloader-interior"></span>
            </div>
            <div class="row">
                <div class="col-md-12">
                <div class="card shadow w-100 rounded">
                <h5 class="card-header text-capitalize" style="font-family: Roboto"> <i class="fa fa-trash" aria-hidden="true"></i>
                    <spring:message code="retirement"/> </h5>
                <div class="card-body">
                <spring:url value="/retiro/searchParticipant" var="BuscarParticipanteUrl"/>
                <spring:url value="/retiro/GuardarRetiro" var="savePartRetiradoUrl"/>
                <spring:url value="/retiro/getMotivo" var="MotivosUrl"/>
                <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
                <!-- inicio smart wizard -->
                <!-- SmartWizard html -->
                <div id="smartwizard">

                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#step-1">
                            <strong> <i class="fa fa-search-plus" aria-hidden="true"></i>  <spring:message code="search"/></strong> <br><spring:message code="participant"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#step-2">
                            <strong> <i class="fa fa-list" aria-hidden="true"></i> <spring:message code="Information"/></strong> <br><spring:message code="lbl.family"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#step-3">
                            <strong> <i class="fa fa-user-times"></i> <spring:message code="realize"/></strong> <br><spring:message code="retirement"/>
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="step-1" class="tab-pane" role="tabpanel" aria-labelledby="step-1">
                        <h3 class="h3 text-muted" style="font-family: Roboto"><spring:message code="search"/> <spring:message code="participant"/></h3>
                        <div class="container">
                            <form action="#" autocomplete="off" name="select-participante-form" id="select-participante-form" class="form-horizontal">
                                <div class="form-group row">
                                    <div class="input-group col-md-12">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="parametro" name="parametro" type="text" placeholder="<spring:message code="participant.code" />" class="form-control"/>
                                        <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right">
                                            <i class="fa fa-search" aria-hidden="true"></i>
                                            <spring:message code="search" />
                                        </button>
                                    </div>
                                </div>
                            </form>
                           <%-- <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">

                            <div class="col-md-8 offset-md-2">
                                <div class="pt-3 pb-4">
                                    <div class="input-group">
                                        <input type="text" id="parametro" name="parametro" class="form-control" placeholder="<spring:message code="participant.code" />" >
                                        <div class="input-group-append">
                                            <button type="submit" class="btn waves-effect waves-light btn-custom btn-ladda" data-style="expand-right">
                                                <i class="fa fa-search mr-1"></i>
                                                <spring:message code="search" />
                                            </button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </form>--%>
                        </div>

                    </div>
                    <div id="step-2" class="tab-pane" role="tabpanel" aria-labelledby="step-2">
                        <h3 class="h3 text-muted" style="font-family: Roboto"> <spring:message code="Information" /> <spring:message code="Familiares" /> </h3>
                        <div class="container">

                            <div class="form-row">

                                <input type="text" hidden="hidden" class="form-control" id="estado" name="estado">

                                <div class="form-group col-md-6">
                                    <label for="nombreMadre"> <spring:message code="entityName"/> <spring:message code="lbl.mother" /></label>
                                    <input type="text" class="form-control" id="nombreMadre" readonly="readonly"/>
                                </div>


                                <div class="form-group col-md-6">
                                    <label for="nombrePadre"> <spring:message code="entityName"/> <spring:message code="lbl.father" /></label>
                                    <input type="text" class="form-control" id="nombrePadre" readonly="readonly"/>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="edad"> <spring:message code="age" /> <spring:message code="participant" /></label>
                                    <input type="text" id="edad" class="form-control" readonly="readonly">
                                    <small id="passwordHelpBlock" class="form-text text-muted">
                                        <spring:message code="lbl.years"/>/<spring:message code="lbl.mounths"/>/<spring:message code="lbl.days"/>.
                                    </small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="nombretutor"> <spring:message code="entityName"/> <spring:message code="lbl.tutor" /></label>
                                    <input type="text" class="form-control" id="nombretutor" readonly="readonly">
                                </div>

                            </div>

                        </div>
                    </div>
                    <div id="step-3" class="tab-pane" role="tabpanel" aria-labelledby="step-3">
                        <h3 class="h3 text-muted" style="font-family: Roboto"> <spring:message code="Information" /> <spring:message code="retirement"/></h3>
                        <div >
                            <form  action="#" id="retiro-participante-form" name="retiro-participante-form" autocomplete="off" class="form-horizontal">
                                <div class="form-row">
                                    <div hidden="hidden" class="form-group col-md-4">
                                        <label for="codigoParticipante"> <spring:message code="participant.code" /></label>
                                        <input type="text" class="form-control" id="codigoParticipante" name="codigoParticipante" >
                                    </div>

                                    <div class="form-group col-md-8">
                                        <label for="nombreCompleto"> <spring:message code="entityName" />  </label>
                                        <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" readonly="readonly">
                                    </div>

                                    <div class="form-group col-md-2">
                                        <label for="casaPDCS"> <spring:message code="ecasa.Pediatric" /> </label>
                                        <input type="text" class="form-control" id="casaPDCS" name="casaPDCS" readonly="readonly">
                                    </div>

                                    <div class="form-group col-md-2">
                                        <label for="casaFamilia"> <spring:message code="ecasa.Family" /> </label>
                                        <input type="text" class="form-control" id="casaFamilia" name="casaFamilia" readonly="readonly">
                                    </div>

                                </div>


                                <div class="form-row">

                                    <div class="form-group col-md-2">
                                        <label for="fehaRetiro"> <spring:message code="dateAdded" /> <spring:message code="retirement"/></label>
                                        <input name="fehaRetiro" id="fehaRetiro" class="form-control date-picker" type="text" data-date-end-date="+0d" required="required" />
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="quiencomunica"> <spring:message code="person_communicates" /></label>
                                        <input type="text" class="form-control letras" name="quiencomunica" id="quiencomunica" >
                                    </div>

                                    <div class="form-group col-md-3">
                                        <label for="parentesco"> <spring:message code="relationship" /> </label>
                                        <select name="parentesco" id="parentesco" class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${parentesco}" var="p">
                                                <option value="${p.catKey}"> <spring:message code="${p.spanish}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-3">
                                        <label for="recibidaPor"> <spring:message code="receives_document" /> </label>
                                        <select name="recibidaPor" id="recibidaPor" required class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${supervisor}" var="rp">
                                                <option value="${rp.personal.idpersonal}"> <spring:message code="${rp.personal.idpersonal} - ${rp.personal.nombreApellido}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="medicosupervisor"> <spring:message code="fill_document" /> </label>
                                        <select name="medicosupervisor" id="medicosupervisor" required class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${supervisorYdigitador}" var="s">
                                                <option value="${s.personal.idpersonal}">${s.personal.idpersonal} - ${s.personal.nombreApellido}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="aretiro"> <spring:message code="study"/> <spring:message code="remove"/> </label>
                                        <select name="aretiro" id="aretiro" required class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row">

                                    <div class="form-group col-md-6">
                                        <label for="causa"> <spring:message code="cause_withdrawal" /></label>
                                        <select name="causa" id="causa" required class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${causaRetiro}" var="rz">
                                                <option value="${rz.catKey}"> <spring:message code="${rz.spanish}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="razonretiro"> <spring:message code="reason" /> <spring:message code="retirement"/></label>
                                        <select name="razonretiro" id="razonretiro" required class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${razonRetiro}" var="rz">
                                                <option value="${rz.catKey}"> <spring:message code="${rz.spanish}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div id="bar" class="form-group col-md-12" style="display: none">
                                        <label for="fehaRetiro"> <spring:message code="dateAdded" /> <spring:message code="demise" /> </label>
                                        <input name="fechaFallecido" id="fechaFallecido" class="form-control date-picker" type="text" data-date-end-date="+0d"/>
                                    </div>

                                    <div id="otherMot" class="form-group col-md-12" style="display: none">
                                        <label for="otromotivo"> <spring:message code="others" /> <spring:message code="reason" /> </label>
                                        <input type="text" class="form-control" id="otromotivo" name="otromotivo" >
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="observacion"> <spring:message code="observacion" /> </label>
                                        <textarea class="form-control" id="observacion" name="observacion" rows="3"></textarea>
                                    </div>
                                </div>

                                <div class="form-group form-check">
                                    <input type="checkbox" class="form-check-input" name="devolcioCarnet" id="devolcioCarnet">
                                    <label class="form-check-label" for="devolcioCarnet"> <spring:message code="returned_card" /></label>
                                </div>


                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <button type="submit" class="btn btn-primary btn-raised shadowbtn my-button indigo btn-block btn-lg btn-ladda" data-style="expand-right">
                                            <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>
                                    </div>
                                    <div class="form-group col-md-4">

                                    </div>
                                    <div class="form-group col-md-4">
                                        <a class="btn btn-warning btn-raised shadowbtn my-button btn-block btn-lg"  href="<spring:url value="/retiro/saveRetiroForm" htmlEscape="true "/>">
                                            <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                            <spring:message code="cancel" />  </a>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                </div>

                <!-- SmartWizard html -->

                <!-- fin smart wizard -->
                <hr/>
                </div>
                <div class="card-footer text-muted">
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
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>

<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/notify.min.js" var="noty" />
<script type="text/javascript" src="${noty}"></script>

<spring:url value="/resources/js/views/retiro/retiro.js" var="ret"/>
<script type="application/javascript" src="${ret}"></script>

<script type="text/javascript">
    $(document).ready(function(){
        var hoy = moment().format('DD/MM/YYYY');
        $("#parentesco").select2().prop('disabled', true).trigger('change');
        $("#relFam").select2();
        $("#recibidaPor").select2();
        $("#aretiro").select2();
        $("#medicosupervisor").select2();
        $("#razonretiro").select2();
        $("#causa").select2();
        $("#fehaRetiro").val(hoy);
        var endPoints={
            BuscarParticipanteUrl : "${BuscarParticipanteUrl}",
            savePartRetiradoUrl : "${savePartRetiradoUrl}",
            MotivosUrl:"${MotivosUrl}",
            successmessage: "${successMessage}",
            error: "${errorProcess}"
        }
        handleDatePickers("${lenguaje}");
        RealizarRetiro.init(endPoints);
        GuardarRetiro.init(endPoints);
        // SmartWizard initialize
        $('#smartwizard').smartWizard({
            selected: 0,
            theme: 'arrows',
            lang: {  // Language variables
                next: 'Sig',
                previous: 'Prev'
            },
            keyboardSettings: {
               keyNavigation: false, // Enable/Disable keyboard navigation(left and right keys are used if enabled)
               keyLeft: [37], // Left key code
               keyRight: [39] // Right key code
            },
            enableURLhash: false
        });
        $("#parametro").focus();
    });
</script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
</body>
</html>