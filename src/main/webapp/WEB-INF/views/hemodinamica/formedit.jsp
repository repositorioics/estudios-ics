<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
   <%-- <spring:url value="/resources/css/animate.css" var="anime" />
    <link rel="stylesheet" href="${anime}" type="text/css"/>--%>
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <style>
        input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff }
        input[type="text"]{color: #000000; font-family: Roboto}
        input[type="select"]{color: #000000; font-family: Roboto}
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
    </style>

    <%--<spring:url value="/resources/css/smartWizardCss/smarthWizardCss.css" var="smw" />
    <link href="${smw}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/smartWizardCss/smart_wizard_theme_arrows.min.css" var="smwtheme" />
    <link href="${smwtheme}" rel="stylesheet" type="text/css"/>--%>
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
                <a href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">LISTADO</a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(edithemoUrl)}">ACTUALIZAR </a>
                <i class="fa fa-angle-right"></i>
                <strong>${obj.participante.nombre1} ${obj.participante.nombre2} ${obj.participante.apellido1} ${obj.participante.apellido2}</strong>
            </li>
        </ol>
        <spring:url value="/hemo/UpdateHemodinamica" var="updateHemoUrl"/>
        <spring:url value="/hemo/listado2" var="Listado2Url"/>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
        <div class="container-fluid">
            <div class="animated fadeIn">
               <div class="card text-black-50">
                    <div class="card-header">
                        <h5 class="text-gray-dark" style="font-family: Roboto">
                            <i class="fa fa-refresh" aria-hidden="true"></i>  <spring:message code="Actualizar Información" /></h5>
                    </div>
                    <div class="card-block">
                    <!-- init form -->
                    <form action="#" autocomplete="off"  class="form-horizontal" id="update-hemo-form" name="update-hemo-form"  role="form">
                        <div class="row">
                            <div class="col-sm-12">
                                <h4 class="text-capitalize" style="font-family: Roboto">Datos Personales.</h4>
                            </div>
                            <div class="col-sm-12">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="nombre" class="control-label">Nombre:</label>
                                            <input type="text" class="form-control" id="nombre" name="nombre"
                                                   value=" ${obj.participante.nombre1} ${obj.participante.nombre2} ${obj.participante.apellido1} ${obj.participante.apellido2}"
                                                   readonly />
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <label for="fecha">Fecha de Nacimiento:</label>
                                            <input type="text" class="form-control" id="fecha" name="fecha"
                                                   value="<fmt:formatDate value="${obj.participante.fechaNac}" pattern="dd/MM/yyyy"/>" readonly />
                                        </div>

                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="expediente">Expediente:</label>
                                            <input type="text" class="form-control" id="expediente" required name="expediente" value="${obj.nExpediente}" readonly  placeholder="Número de Expediente" />
                                        </div>

                                    </div>
                                </div>
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="sector">Sector:</label>
                                <span class="required text-danger"> * </span>
                                <select name="sector" id="sector" class="form-control">
                                    <option selected value=""><spring:message code="select" />...</option>
                                    <c:forEach items="${barrios}" var="barrio">
                                        <c:choose>
                                            <c:when test="${barrio.codigo eq obj.sector}">
                                                <option selected value="${barrio.codigo}"><spring:message code="${barrio.nombre}" /></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${barrio.codigo}"><spring:message code="${barrio.nombre}" /></option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group col-sm-8">
                                <label for="direccion">Dirección de Participante :</label>
                                <input type="text" name="direccion" class="form-control" value="${obj.direccion}"
                                       placeholder="Ingrese la dirección"  id="direccion" name="direccion"/>
                            </div>
                            <div id="bar" class="form-group col-sm-12" style="display: none">
                                <label for="barrioF">Barrio :</label>
                                <input type="text" class="form-control" id="barrioF" name="barrioF" value="${obj.barrioF}" style="text-transform:uppercase" />
                            </div>
                            <div class="form-group col-sm-4">
                                <label for="telefono">Teléfono:</label>
                                <input type="text" class="form-control focusNext" id="telefono" name="telefono"  value="${obj.telefono}" placeholder="Número de telefono"tabindex="1"/>
                            </div>

                            <div class="form-group col-md-4">
                                <label for="peso">Peso(kg):</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext" tabindex="2" name="peso" id="peso" placeholder="Peso" value="${obj.peso}"/>
                            </div>

                            <div class="form-group col-md-4">
                                <label for="talla">Talla(cm):</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext" tabindex="3" name="talla" id="talla" placeholder="Talla" value="${obj.talla}"/>
                            </div>

                        </div>
                        <div class="row">

                            <div class="form-group col-sm-4">
                                <label for="numParametro">Parametros:</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext" name="numParametro"  id="numParametro" value="${obj.numParametros}" tabindex="4">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="fconsulta">Fecha Consulta:</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext" tabindex="5" id="fconsulta" name="fconsulta"  data-date-end-date="+0d" value="<fmt:formatDate value="${obj.fecha}" pattern="dd/MM/yyyy"/>"/>
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="fie">Fecha Inicio de Enfermedad:</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext" tabindex="6" id="fie" name="fie"  data-date-end-date="+0d" value="<fmt:formatDate value="${obj.fie}" pattern="dd/MM/yyyy"/>" />
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="form-group col-sm-12">
                                        <div id="positivoerror" class="text-danger"></div>
                                        <br/>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <c:choose>
                                                <c:when test="${obj.esPositivo eq false}">
                                                    <input type="radio" id="chkpositivo0" value="${obj.positivo}" name="chkpositivo" checked="checked" class="custom-control-input"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="radio" id="chkpositivo0" value="0" name="chkpositivo" class="custom-control-input"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="custom-control-label" for="chkpositivo0">Negativo.</label>
                                        </div>

                                        <div class="custom-control custom-radio custom-control-inline">
                                            <c:choose>
                                                <c:when test="${obj.esPositivo eq true}">
                                                    <input type="radio" id="chkpositivo1" value="${obj.positivo}" name="chkpositivo" checked="checked" class="custom-control-input"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="radio" id="chkpositivo1" value="1" name="chkpositivo" class="custom-control-input"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="custom-control-label" for="chkpositivo1">Positivo.</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <button type="submit" id="btnUpdate" class="btn btn-info btn-lg btn-block">
                                            <i class="fa fa-refresh" aria-hidden="true"></i> <spring:message code="save" /></button>
                                    </div>
                                    <div class="col-sm-4">

                                    </div>
                                    <div class="form-group col-sm-4">
                                        <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
                                            <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                            <spring:message code="cancel" /></a>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div hidden="hidden" class="container col-sm-12 col-lg-12">
                            <div class="row">
                                    <div class="form-group col-md-4">
                                        <label for="idDatoHemo">idDatoHemo:</label>
                                        <input type="text" class="form-control" name="idDatoHemo"  id="idDatoHemo" readonly value="${obj.idDatoHemo}"  />
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="idParticipante">Participante:</label>
                                        <input type="text" class="form-control" name="idParticipante"  id="idParticipante" readonly value="${obj.participante.codigo}"  />
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="edad">Edad:</label>
                                        <input type="text" class="form-control"  name="edad" id="edad" value="${obj.edad}" readonly />
                                    </div>

                                <div class="form-group col-md-4">
                                    <label for="asc">A.S.C(m2):</label>
                                    <input type="text" class="form-control" id="asc" name="asc" readonly value="${obj.asc}"/>
                                </div>

                                <div class="form-group col-md-4">
                                    <label for="imc">IMC:</label>
                                    <input type="text" class="form-control" id="imc" name="imc" readonly value="${obj.imc}"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="IMCdetallado">Detalle Imc:</label>
                                    <input type="text" class="form-control" id="IMCdetallado" name="IMCdetallado" readonly value="${obj.IMCdetallado}"/>
                                </div>
                                <div class="form-group col-sm-12">
                                    <label for="diasenf">Días de Enfermedad:</label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="diasenf" name="diasenf" value="${obj.diasenf}" readonly/>
                                </div>
                                <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                    <p class="text-center">
                                        <br/>
                                    <div class="custom-control custom-switch">
                                        <input type="checkbox" class="custom-control-input" id="chkRange2" name="chkRange2">
                                        <label class="custom-control-label" for="chkRange2">Frecuencias Respiratorias.</label>
                                    </div>
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-goup col-md-12">
                                    <button class="btn btn-dark btn-lg btn-block" type="button" data-toggle="collapse" tabindex="6" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                        <i class="fa fa-stethoscope" aria-hidden="true"></i>  Rangos
                                    </button>
                                </div>

                                <div class="col-md-12">
                                    <div class="collapse" id="collapseExample">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label style="font-family: Roboto">Rango de Presión PS/PD:</label>
                                            </div>

                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="sdMin" name="sdMin" placeholder="Mínima" tabindex="7" value="${obj.sdMin}">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="sdMed" name="sdMed" placeholder="Media" tabindex="8" value="${obj.sdMed}">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="sdMax" name="sdMax" placeholder="Máxima" tabindex="9" value="${obj.sdMax}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label class="text-capitalize"  style="font-family: Roboto">Presión Arterial Media (PAM): </label>
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="pamMin" name="pamMin" placeholder="Minima" tabindex="10" value="${obj.pamMin}">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="pamMed" name="pamMed" placeholder="Media" tabindex="11" value="${obj.pamMed}">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="pamMax" name="pamMax" placeholder="Máxima" tabindex="12" value="${obj.pamMax}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label style="font-family: Roboto">Rango de Frecuencias Cárdiacas: </label>
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="fcMin" name="fcMin" placeholder="Minima" tabindex="13" value="${obj.fcMin}">
                                            </div>

                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="fcMed" name="fcMed" placeholder="Máxima" tabindex="14" value="${obj.fcMed}">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <input type="text" class="form-control focusNext" id="fcProm" name="fcProm" placeholder="Promedio" tabindex="15" value="${obj.fcProm}">
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-12">
                                                <label class="text-capitalize"  style="font-family: Roboto">Rango Frecuencias Respiratorias </label>
                                            </div>
                                            <div class="form-group col-sm-6">
                                                <input type="text" class="form-control focusNext" id="frMin" name="frMin" placeholder="Minima" tabindex="16" value="${obj.frMin}">
                                            </div>
                                            <div class="form-group col-sm-6">
                                                <input type="text" class="form-control focusNext" id="frMax" name="frMax" placeholder="Máxima" tabindex="17" value="${obj.frMax}">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- finish form -->
                    </div>
                </div>
                </div>
        </div>
        <!-- /.conainer-fluid -->
        <div hidden="hidden" id="errorIMC"></div>
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
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/notify.min.js" var="noty" />
<script type="text/javascript" src="${noty}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>

<spring:url value="/resources/js/views/hemodinamica/dinamicatools.js" var="tools"/>
<script type="application/javascript" src="${tools}"></script>

<spring:url value="/resources/js/views/hemodinamica/formEdit.js" var="formEditJS" />
<script src="${formEditJS}" type="text/javascript"></script>
<script>
    $(document).ready(function(){
        /*$('#smartwizard').smartWizard({
            selected: 0,
            theme: 'arrows',
            justified: true,
            darkMode: true,
            lang: {  // Language variables
                next: 'Sig',
                previous: 'Prev'
            }
        });*/
        var params ={
            updateHemoUrl   :   "${updateHemoUrl}",
            Listado2Url     :   "${Listado2Url}",
            successmessage  :   "${successMessage}"
        }
        editDatos.init(params);
        $("#sector").select2();
        loadSelect();
        $("#sector").on("change", function(){
            if(this.value == 18 ){
                $("#bar").fadeIn("slow");
                $("#barrioF").attr("required", "true");
            }else{
                $("#bar").fadeOut("slow");
                $("#barrioF").val("").attr("required", "false");
            }
        });
        if ($("#sdMin").val() == null || $("#sdMin").val() == ""){
            $("input[name=chkRange2]").attr("checked", false);
        }else{
            $("input[name=chkRange2]").attr("checked", true);
        }
        function loadSelect(){
            if($("#sector").val() ==18){
                $("#bar").show();
                $("#barrioF").attr("required", "true");
            }else{
                $("#bar").hide();
                $("#barrioF").val("").attr("required", "false");
            }
        }
        document.addEventListener('keypress', function(evt) {
            // Si el evento NO es una tecla Enter
            if (evt.key !== 'Enter') {
                return;
            }
            let element = evt.target;
            // Si el evento NO fue lanzado por un elemento con class "focusNext"
            if (!element.classList.contains('focusNext')) {
                return;
            }
            // AQUI logica para encontrar el siguiente
            let tabIndex = element.tabIndex + 1;
            var next = document.querySelector('[tabindex="'+tabIndex+'"]');
            // Si encontramos un elemento
            if (next) {
                next.focus();
                event.preventDefault();
            }
        });
        $("#telefono").focus();
    })
</script>
</body>
</html>
