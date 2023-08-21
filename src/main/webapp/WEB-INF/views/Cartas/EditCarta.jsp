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
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
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
        /* Inicio Preload*/

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
        /* Fin Preload*/
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
        .accordion .card {
            border: none;
            margin-bottom: 2px;
            border-radius: 0;
            box-shadow: 0 0 6px rgba(0,0,0,0.2);
        }
        .accordion .card .card-header {
            background: #5dc0de;
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
        .form-control:disabled, .daterangepicker .input-mini:disabled, .input-group > .ui-select-bootstrap > input.ui-select-search.form-control:disabled, .form-control[readonly], .daterangepicker [readonly].input-mini, .input-group > .ui-select-bootstrap > input[readonly].ui-select-search.form-control {
            background-color: #e9ebec00;
            opacity: 1;
            font-stretch: semi-condensed;

        }

    </style>

    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
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
                <a href="<spring:url value="/cartas/ListadoCartaParticipant/" htmlEscape="true "/>"><spring:message code="List"/></a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(editPartCartaUrl)}"><spring:message code="lbl.update" /> </a>
                <i class="fa fa-angle-right"></i>
                <strong>${obj.participante.nombreCompleto}</strong>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="container col-sm-11 col-lg-12">
                    <spring:url value="/cartas/ListadoCartaParticipant" var="listUrl"/>
                    <spring:url value="/cartas/searchParticipant" var="searchPartUrl"/>
                    <spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
                    <spring:url value="/cartas/ParteVersion" var="ParteVersionUrl"/>
                    <spring:url value="/cartas/saveScanCarta" var="saveScanCartaUrl"/>
                    <spring:url value="/cartas/cartaSaveEdit" var="cartaSaveEditUrl"/>
                    <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                    <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
                    <div class="card">
                        <div class="card-header">
                            <h5 style="font-family: Roboto">
                                <i class="fa fa-users"></i> <spring:message code="lbl.To.assign"/> <spring:message code="letters"/>
                                <i class="fa fa-angle-right"></i>
                                <strong>${obj.participante.nombreCompleto}</strong>
                            </h5>
                        </div>
                        <div class="card-body">
                            <div class="container-lg">
                                  <div class="row">
                        <div class="col-lg-12 mx-auto">
                            <div class="accordion mt-5" id="accordionExample">
                                <div class="card">
                                    <div class="card-header" id="headingOne">
                                        <h2 class="clearfix mb-0">
                                            <a class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                <i class="fa fa-plus-circle"></i> <spring:message code="Information"/> <spring:message code="participants"/></a>
                                        </h2>
                                    </div>
                                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <!-- init test principal -->
                                            <div class="row" hidden="hidden" >
                                                <ul id="ul_Items">
                                                    <ol>
                                                        <c:forEach items="${select2}" var="p">
                                                            <li data-id="${p.iddetalle}" class="${p.idparticipantecarta}" id="${p.idparte}" aria-disabled="${p.locked}" value="${p.acepta}">${p.nombreparte}</li>
                                                        </c:forEach>
                                                    </ol>
                                                </ul>
                                            </div>
                                            <!-- fin test principal -->
                                            <div class="media">
                                                <div class="media-body">
                                                    <div class="row">

                                                            <div class="col-md-6" hidden="hidden">
                                                                <div class="form-group">
                                                                    <input type="text" class="form-control" name="codigo" id="codigo" value="${obj.idparticipantecarta}">
                                                                </div>
                                                            </div>

                                                            <div class="col-md-6" hidden="hidden">
                                                                <div class="form-group">
                                                                    <input type="text" class="form-control" name="fecha_registro" id="fecha_registro" value="<fmt:formatDate value="${obj.recordDate}" pattern="dd-MM-yyyy HH:mm:ss" />">
                                                                </div>
                                                            </div>

                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="idparticipante"> <spring:message code="code" />: </label>
                                                                <input type="text" class="form-control form-control-sm" id="idparticipante" name="idparticipante" value="${obj.participante.codigo}" disabled>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-9">
                                                            <div class="form-group">
                                                                <label for="txtNombreCompleto"><spring:message code="participant" />: </label>
                                                                <input type="text" class="form-control form-control-sm" value="${obj.participante.nombreCompleto}" id="txtNombreCompleto" name="txtNombreCompleto" disabled>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-1">
                                                            <div class="form-group">
                                                                <label for="edadyear"><spring:message code="age"/> <spring:message code="lbl.years"/></label>
                                                                <input type="text" class="form-control form-control-sm" id="edadyear" name="edadyear" value="${obj.edadyears}" disabled>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label for="edadmeses"><spring:message code="age"/> <spring:message code="lbl.mounths"/></label>
                                                                <input type="text" class="form-control form-control-sm" id="edadmeses" name="edadmeses" disabled value="${obj.edadmeses}">
                                                            </div>
                                                        </div>

                                                        <div class="col-md-1">
                                                            <div class="form-group">
                                                                <label for="edaddias"><spring:message code="age"/> <spring:message code="lbl.days"/></label>
                                                                <input type="text" class="form-control form-control-sm" id="edaddias" name="edaddias" disabled value="${obj.edaddias}">
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="estudios"><spring:message code="userstudies"/>:</label>
                                                                <input type="text" class="form-control form-control-sm" id="estudios" name="estudios" value="${procesos.estudio}" disabled>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="padre"><spring:message code="lbl.father"/></label>
                                                                <input type="text" class="form-control form-control-sm" id="padre" name="padre" disabled value="${participante.nombre1Padre} ${participante.nombre2Padre} ${participante.apellido1Padre} ${participante.apellido2Padre}">
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="madre"><spring:message code="lbl.mother"/></label>
                                                                <input type="text" class="form-control form-control-sm" id="madre" name="madre" value="${participante.nombre1Madre} ${participante.nombre2Madre} ${participante.apellido1Madre} ${participante.apellido2Madre}" disabled>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="relacionFam"><spring:message code="lbl.family.relationship"/></label>
                                                                <input type="text" class="form-control form-control-sm" id="relacionFam" name="relacionFam" value="${participante.relacionFamiliarTutor}"disabled>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="tutor"><spring:message code="lbl.tutor"/></label>
                                                                <input type="text" class="form-control form-control-sm" id="tutor" name="tutor" value="${participante.tutor}" disabled>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-header" id="headingTwo">
                                        <h2 class="mb-0">
                                            <a class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                <i class="fa fa-plus-circle"></i>
                                                <spring:message code="Information" /> <spring:message code="letters" /></a>
                                        </h2>
                                    </div>
                                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                                        <div class="card-body">
                                            <div class="media">
                                                <div class="media-body">
                                                    <form action="#" id="form-scan" class="needs-validation" autocomplete="off" novalidate role="form" data-toggle="validator" method="post" accept-charset="utf-8">
                                                    <div class="row">

                                                        <div class="col-md-12"></div>

                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label for="fechacarta"><spring:message code="lbl.date" /></label>
                                                                <span class="required text-danger"> * </span>
                                                                <input type="text" required="required" class="form-control focusNext" tabindex="5" id="fechacarta" name="fechacarta"  data-date-end-date="+0d" value="<fmt:formatDate value="${obj.fechacarta}" pattern="dd/MM/yyyy"/>"/>
                                                                <div class="invalid-feedback">
                                                                    <spring:message code="lbl.date" /> <spring:message code="lbl.required" />
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="carta"><spring:message code="letters" /> :</label>
                                                                <span class="required text-danger"> * </span>
                                                                <select name="carta" id="carta" name="carta" class="form-control" disabled="disabled">
                                                                    <option selected value=""><spring:message code="select" />...</option>
                                                                    <c:forEach items="${carta}" var="c">
                                                                        <c:choose>
                                                                            <c:when test="${c.codigo eq obj.version.estudio.codigo}">
                                                                                <option selected value="${c.codigo}"> <spring:message code="${c.nombre}" />  </option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${c.codigo}">${c.nombre}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </select>
                                                                <div class="invalid-feedback">
                                                                    <spring:message code="letters" /> <spring:message code="lbl.required" />
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
                                                            <spring:url value="/cartas/UpdateAll" var="UpdateAllUrl"/>
                                                            <spring:url value="/cartas/ListadoCartaParticipant" var="Lista2AllUrl"/>
                                                            <spring:url value="/cartas/cartaSaveEdit" var="cartaSaveEditUrl"/>
                                                            <div class="form-group">
                                                                <label for="version"><spring:message code="versionLetters" />:</label>
                                                                <span class="required text-danger"> * </span>
                                                                <select name="version" id="version" class="form-control" disabled="disabled">
                                                                    <c:forEach items="${version}" var="v">
                                                                        <c:choose>
                                                                            <c:when test="${v.idversion eq obj.version.idversion}">
                                                                                <option selected value="${v.idversion}"> <spring:message code="${v.version}" />  </option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${v.idversion}"><spring:message code="${v.version}" /> </option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </select>
                                                                <span class="error"><spring:message code="versionLetters" /> <spring:message code="lbl.required" /></span>
                                                            </div>
                                                        </div>


                                                        <div class="col-md-4">
                                                            <div class="col-md-12">
                                                            <div class="form-group">
                                                                   <label for="partes"><spring:message code="Letter.Parts" />:</label>
                                                                   <span class="required text-danger"> * </span>
                                                                       <select class="form-control form-control-sm select2-multiple" multiple id="partes" name="partes"/>
                                                                          <c:forEach items="${dp}" var="d">
                                                                               <c:choose>
                                                                                   <c:when test="${d.acepta eq 'true'}">
                                                                                       <c:if test="${d.parte.principal eq 'true'}">
                                                                                            <option data-id="${d.iddetalle}" id="${d.participantecarta.idparticipantecarta}" selected value="${d.parte.idparte}">${d.parte.parte}</option>
                                                                                       </c:if>
                                                                                       <c:if test="${d.parte.principal eq 'false'}">
                                                                                            <option data-id="${d.iddetalle}" id="${d.participantecarta.idparticipantecarta}" selected value="${d.parte.idparte}">${d.parte.parte}</option>
                                                                                       </c:if>
                                                                                   </c:when>
                                                                                   <c:otherwise>
                                                                                       <option data-id="${d.iddetalle}" id="${d.participantecarta.idparticipantecarta}" value="${d.parte.idparte}">${d.parte.parte}</option>
                                                                                   </c:otherwise>
                                                                               </c:choose>
                                                                           </c:forEach>
                                                                       </select>
                                                                   <span class="error"><spring:message code="lbl.required" /></span>
                                                               </div>
                                                             </div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="person"><spring:message code="lbl.resource"/></label>
                                                                <span class="required text-danger"> * </span>
                                                                <select name="person" id="person" class="form-control">
                                                                    <option selected value=""><spring:message code="select"/>...</option>
                                                                    <c:forEach items="${person}" var="p">
                                                                        <c:choose>
                                                                            <c:when test="${p.personal.idpersonal eq obj.personal.idpersonal}">
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

                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="proyecto"><spring:message code="lbl.project" /> </label>
                                                                <span class="required text-danger"> * </span>
                                                                <select name="proyecto" id="proyecto" class="form-control">
                                                                    <option selected value=""><spring:message code="select" />...</option>
                                                                    <c:forEach items="${proyecto}" var="p">
                                                                        <c:choose>
                                                                            <c:when test="${p.catKey eq obj.proyecto}">
                                                                                <option selected value="${p.catKey}"> <spring:message code="${p.spanish}" />  </option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${p.catKey}"> <spring:message code="${p.spanish}" />  </option>
                                                                            </c:otherwise>
                                                                        </c:choose>
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
                                                                <select name="asentimiento" id="asentimiento" class="form-control">
                                                                    <option selected value=""><spring:message code="select" />...</option>
                                                                    <c:forEach items="${SiNoNA}" var="s">
                                                                        <c:choose>
                                                                            <c:when test="${s.catKey  eq obj.asentimiento}">
                                                                                <option selected value="${s.catKey}">${s.catKey} -<spring:message code="${s.spanish}" /></option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${s.catKey}">${s.catKey} - <spring:message code="${s.spanish}" /></option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </select>

                                                                <div class="invalid-feedback">
                                                                    <spring:message code="lbl.assent" /> <spring:message code="lbl.required" />
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-3">
                                                            <div id="DivtipoAsent">
                                                                <div class="form-group">
                                                                    <label for="tipoasentimiento"><spring:message code="type.assent" /> </label>
                                                                    <span class="required text-danger"> * </span>
                                                                    <select name="tipoasentimiento" id="tipoasentimiento" class="form-control">
                                                                        <option value=""><spring:message code="select" />...</option>
                                                                        <c:forEach items="${tpoasent}" var="ta">
                                                                            <c:choose>
                                                                                <c:when test="${ta.catKey eq obj.tipoasentimiento}">
                                                                                    <option selected value="${ta.catKey}">${ta.catKey} - <spring:message code="${ta.spanish}" /></option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option value="${ta.catKey}"> ${ta.catKey} - <spring:message code="${ta.spanish}" /></option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:forEach>
                                                                    </select>

                                                                    <div class="invalid-feedback">
                                                                        <spring:message code="type.assent" /><spring:message code="lbl.required" />.
                                                                    </div>
                                                                    <span class="error"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div id="">
                                                        <div class="row">
                                                            <div class="col-md-3">
                                                                <div class="form-group">
                                                                    <label for="nombfirma"><spring:message code="first.name" /> <spring:message code="lbl.tutor" /> </label>
                                                                    <span class="required text-danger"> * </span>
                                                                    <input type="text" class="form-control onlytext" id="nombfirma" name="nombfirma" value="${obj.quienfirma}"/>
                                                                    <div class="invalid-feedback">
                                                                        <spring:message code="first.name" /> <spring:message code="lbl.required" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <div class="form-group">
                                                                    <label for="nombre2Firma"><spring:message code="second.name" /> <spring:message code="lbl.tutor" /></label>
                                                                    <input type="text" class="form-control onlytext" id="nombre2Firma" name="nombre2Firma" value="${obj.nombre2Firma}">
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <div class="form-group">
                                                                    <label for="apellido1Firma"><spring:message code="first.surname" /> <spring:message code="lbl.tutor" /></label>
                                                                    <span class="required text-danger"> * </span>
                                                                    <input type="text" class="form-control onlytext" id="apellido1Firma" name="apellido1Firma" value="${obj.apellido1Firma}">
                                                                    <div class="invalid-feedback">
                                                                        <spring:message code="first.surname" /> <spring:message code="lbl.required" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <div class="form-group">
                                                                    <label for="nombre2Firma"><spring:message code="second.surname" /> <spring:message code="lbl.tutor" /></label>
                                                                    <input type="text" class="form-control onlytext" id="apellido2Firma" name="apellido2Firma" value="${obj.apellido2Firma}">
                                                                </div>
                                                            </div>

                                                            <div class="col-md-3">
                                                                <div class="form-group">
                                                                    <label for="relfam"><spring:message code="family.relationship" /> </label>
                                                                    <span class="required text-danger"> * </span>
                                                                    <select name="relfam" id="relfam" class="form-control">
                                                                        <option selected value=""><spring:message code="select" />...</option>
                                                                        <c:forEach items="${relFam}" var="rel">
                                                                            <c:choose>
                                                                                <c:when test="${rel.catKey eq obj.relfam}">
                                                                                    <option selected value="${rel.catKey}"> <spring:message code="${rel.spanish}" />  </option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option value="${rel.catKey}"> <spring:message code="${rel.spanish}" />  </option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:forEach>
                                                                    </select>
                                                                    <div class="invalid-feedback">
                                                                        <spring:message code="family.relationship" /> <spring:message code="lbl.required" />
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="col-md-3">
                                                                        <div class="form-group">
                                                                            <label for="tipoCaso"><spring:message code="cases" />: </label>
                                                                            <select name="tipoCaso" id="tipoCaso" class="form-control">
                                                                                <option selected value=""><spring:message code="select" />...</option>
                                                                                <c:forEach items="${TipoCaso}" var="t">
                                                                                    <c:choose>
                                                                                        <c:when test="${t.catKey eq obj.esIndiceOrMiembro}">
                                                                                            <option selected value="${t.catKey}">${t.spanish}</option>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <option value="${t.catKey}">${t.catKey} - ${t.spanish}</option>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </c:forEach>
                                                                            </select>
                                                                            <div class="invalid-feedback">
                                                                                <spring:message code="cases" /> <spring:message code="lbl.required" />
                                                                            </div>
                                                                        </div>
                                                            </div>

                                                            <div class="col-md-2">
                                                                <div class="form-group">
                                                                    <label for="contactoFuturo">
                                                                        <spring:message code="lbl.Accept.future.contact" />
                                                                    </label>
                                                                    <select name="contactoFuturo" id="contactoFuturo" class="form-control" required="required">
                                                                        <option selected value=""><spring:message code="select" />...</option>
                                                                        <c:forEach items="${contactoFuturo}" var="c">
                                                                            <c:choose>
                                                                                <c:when test="${c.catKey eq obj.contactoFuturo}">
                                                                                    <option selected value="${c.catKey}">${c.catKey} - ${c.spanish}</option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option value="${c.catKey}">${c.catKey} - <spring:message code="${c.spanish}" /></option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>

                                                            <%--<div class="col-md-2">
                                                                <div class="form-group">
                                                                    <div class="form-check mt-4 text-center">
                                                                        <c:choose>
                                                                            <c:when test="${obj.esContacto eq true}">
                                                                                <input type="checkbox" value="${obj.contactoFuturo}" checked="checked" name="contactoFuturo" id="contactoFuturo"  class="lcs_check" autocomplete="off" />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <input type="checkbox" name="contactoFuturo" id="contactoFuturo" value="1" class="lcs_check" autocomplete="off" />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <label class="form-check-label" for="contactoFuturo">
                                                                            <spring:message code="lbl.Accept.future.contact" />
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>--%>

                                                            <div class="col-md-2">
                                                                <div class="form-group">
                                                                    <div class="form-check mt-4 text-center pt-2">
                                                                        <c:choose>
                                                                            <c:when test="${obj.testigopresent eq true}">
                                                                                <input type="checkbox" value="${obj.testigopresent}" checked="checked" id="chktestigo" name="chktestigo"  class="chktestigo"  class="lcs_check" autocomplete="off" />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <input type="checkbox" id="chktestigo" name="chktestigo"  class="lcs_check chktestigo" value="1" class="chktestigo" autocomplete="off" />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <label class="form-check-label" for="chktestigo">
                                                                            <spring:message code="lbl.witness.present" />
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>


                                                            <div class="col-md-1">
                                                                <div class="form-group">
                                                                    <div class="form-check mt-4 text-center pt-2">
                                                                        <c:choose>
                                                                            <c:when test="${obj.reactivacion eq '1'}">
                                                                                <input type="checkbox" value="${obj.reactivacion}" checked="checked" id="reactivacion" name="reactivacion"  class="form-check-input"  class="lcs_check" autocomplete="off" />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <input type="checkbox" id="reactivacion" name="reactivacion"  class="form-check-input" value="0" class="reactivacion" autocomplete="off" />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <label class="form-check-label" for="reactivacion"><spring:message code="Reactivacion" /></label>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                        </div>

                                                        <div id="selectt" style="display: none">
                                                            <div class="row">
                                                                <div class="col-md-3">
                                                                    <div class="form-group">
                                                                        <label for="nombre1Testigo"><spring:message code="first.name" /> <spring:message code="lbl.witness" /> </label>
                                                                        <span class="required text-danger"> * </span>
                                                                        <input type="text" class="form-control focusNext" tabindex="5" id="nombre1Testigo" name="nombre1Testigo" value="${obj.nombre1testigo}"/>
                                                                        <span class="error"><spring:message code="first.name" /> <spring:message code="lbl.required" />.</span>
                                                                    </div>

                                                                </div>
                                                                <div class="col-md-3">
                                                                    <div class="form-group">
                                                                        <label for="nombre2Testigo"><spring:message code="second.name" /> <spring:message code="lbl.witness" /></label>
                                                                        <input type="text" class="form-control focusNext" tabindex="6" id="nombre2Testigo" name="nombre2Testigo" value="${obj.nombre2testigo}">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-3">
                                                                    <div class="form-group">
                                                                        <label for="apellido1Testigo"><spring:message code="first.surname" /> <spring:message code="lbl.witness" /></label>
                                                                        <span class="required text-danger"> * </span>
                                                                        <input type="text" class="form-control focusNext" tabindex="7" id="apellido1Testigo" name="apellido1Testigo" value="${obj.apellido1testigo}">
                                                                        <span class="error"><spring:message code="first.surname" /> <spring:message code="lbl.required" />.</span>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-3">
                                                                    <div class="form-group">
                                                                        <label for="apellido2Testigo"><spring:message code="second.surname" /> <spring:message code="lbl.witness" /></label>
                                                                        <input type="text" class="form-control focusNext" tabindex="8" id="apellido2Testigo" name="apellido2Testigo" value="${obj.apellido2testigo}">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="observacion"><spring:message code="observacion" /></label>
                                                                <textarea class="form-control form-control-sm"  id="observacion" name="observacion" placeholder="observacion" rows="3">${obj.observacion}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                        <br/>
                                                        <br/>
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <button type="button" id="btnUpdate" class="btn btn-info btn-block btn-lg">
                                                                    <i class="fa fa-refresh" aria-hidden="true"></i>
                                                                    <spring:message code="lbl.update" />
                                                                </button>
                                                            </div>
                                                            <div class="col-md-4"></div>
                                                            <div class="col-md-4">
                                                                <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                                                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                                    <spring:message code="cancel" /></a>
                                                            </div>
                                                        </div>
                                                        </div>
                                                        <br/>
                                                        <br/>
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
                             </div>
                        </div>
                    </div>
                 </div>
            <!-- fin test smarthwizard -->
            </div> <!-- animated fadeIn -->
        </div><!-- /.conainer-fluid -->
    </div> <!-- Main -->
</div> <!-- app-boddy -->
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
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sweet" />
<script type="text/javascript" src="${sweet}"></script>

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
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
<script>
    $(document).ready(function(){
        // Add minus icon for collapse element which is open by default
        $(".collapse.show").each(function(){
            $(this).siblings(".card-header").find(".btn i").addClass("fa-minus-circle").removeClass("fa-plus-circle");
        });
        // Toggle plus minus icon on show hide of collapse element
        $(".collapse").on('show.bs.collapse', function(){
            $(this).parent().find(".card-header .btn i").removeClass("fa-plus-circle").addClass("fa-minus-circle");
        }).on('hide.bs.collapse', function(){
            $(this).parent().find(".card-header .btn i").removeClass("fa-minus-circle").addClass("fa-plus-circle");
        });
        var parametros ={
            VersionCartatUrl    : "${VersionCartatUrl}",
            ParteVersionUrl     : "${ParteVersionUrl}",
            UpdateAllUrl        : "${UpdateAllUrl}",
            Lista2AllUrl        : "${Lista2AllUrl}",
            successmessage      : "${successMessage}",
            error               : "${errorProcess}",
            cartaSaveEditUrl    : "${cartaSaveEditUrl}",
            dataTablesLang      : "${dataTablesLang}",
            "listUrl"           : "${listUrl}"
        };

        var list = [];
        armarParte();
        function armarParte(){
            $("#ul_Items ol li").each(function(index, value){
                var self=$(this);
                var partDetalleItem = {
                    iddetalle: self.attr('data-id'),
                    id: self.attr('id'),
                    parte: self.text(),
                    acepta: self.attr('value'),
                    principal: self.attr('aria-disabled')
                };
                list.push(partDetalleItem);
            });
        }

        $("#partes").on("select2-removing", function(e) {
            debugger;
            var partName;
            for(var i=0; i<list.length; i++){
                partName="";
                if(list[i].principal=='true') {
                    partName = list[i].parte;
                    console.log("parte: "+ list[i].parte +" acepta: " + list[i].acepta +" principal: "+ list[i].principal);
                }
                if (e.choice.text === partName) {
                    e.preventDefault();
                    $(this).select2("close");
                }
            }
        });

        var table2 = $("#tblDetalleParte").DataTable({
            searching  : false,
            paging     : false,
            "ordering"  : false,
            "info"      : false,
            "oLanguage" : {
                "sUrl"  : parametros.dataTablesLang
            },
            columnDefs:[
                {
                    "targets":[0],
                    "visible": false,
                    "searchable":false
                }, {
                    "targets":[1],
                    "visible": false,
                    "searchable":false
                }, {
                    "targets":[3],
                    "visible": false,
                    "searchable":false
                }
            ]
        });

        $("#carta").select2();
        $("#version").select2();
        $("#person").select2();
        $("#relfam").select2();
        $("#contactoFuturo").select2();
        $("#asentimiento").select2();
        $("#tipoasentimiento").select2();
        $("#proyecto").select2();
        $("#tipoCaso").select2();
        $("#btnUpdate").on("click", function(e){
            e.preventDefault();
            var isValidItem = true;
            $('#error').empty();
            if($("#codigo").val().trim() == "" || $("#codigo").val()== null){
                $('#codigo').siblings('span.error').css('visibility', 'visible');
                $('#codigo').parents('.form-group').addClass('has-danger');
                $('#codigo').focus();
                isValidItem = false;
                return
            }else{
                $('#codigo').siblings('span.error').css('visibility', 'hidden');
                $('#codigo').parents('.form-group').removeClass('has-danger');
            }

            if($("#fechacarta").val() == "" || $("#fechacarta").val()== null){
                $('#fechacarta').siblings('span.error').css('visibility', 'visible');
                $('#fechacarta').parents('.form-group').addClass('has-danger');
                toastr.error("Seleccione la Fecha!",{timeOut: 5000});
                isAllValid = false;
                return;
            }
            else{
                $('#fechacarta').siblings('span.error').css('visibility', 'hidden');
                $('#fechacarta').parents('.form-group').removeClass('has-danger');
            }
            if ($('#nombfirma').val().trim() == "" || $("#nombfirma").val().trim() == null) {
                $('#nombfirma').siblings('span.error').css('visibility', 'visible');
                $('#nombfirma').parents('.form-group').addClass('has-danger');
                isAllValid = false;
                toastr.error("Nombre tutor requerido!",{timeOut: 5000});
                return;
            }
            else {
                $('#nombfirma').siblings('span.error').css('visibility', 'hidden');
                $("#nombfirma").parents('.form-group').removeClass('has-danger');
            }
            if($("#apellido1Firma").val().trim() == "" || $("#apellido1Firma").val().trim() == null){
                $('#apellido1Firma').siblings('span.error').css('visibility', 'visible');
                $('#apellido1Firma').parents('.form-group').addClass('has-danger');
                isValidItem = false;
                toastr.error("Apellido tutor requerido!",{timeOut: 5000});
                return;
            }else{
                $('#apellido1Firma').siblings('span.error').css('visibility', 'hidden');
                $("#apellido1Firma").parents('.form-group').removeClass('has-danger');
            }

            if ($('#relfam').val() == null || $('#relfam').val() == "") {
                $('#relfam').siblings('span.error').css('visibility', 'visible');
                $('#relfam').parents('.form-group').addClass('has-danger');
                isAllValid = false;
                toastr.error("Realción Familiar requerido!",{timeOut: 5000});
                return;
            }
            else {
                $('#relfam').siblings('span.error').css('visibility', 'hidden');
            }

            if($("#carta").val().trim() == "" || $("#carta").val().trim() == null){
                $('#carta').siblings('span.error').css('visibility', 'visible');
                isValidItem = false;
                return;
            }
            else{
                $('#carta').siblings('span.error').css('visibility', 'hidden');
            }

            if($("#version").val().trim() == "" || $("#version").val().trim() == null){
                $('#version').siblings('span.error').css('visibility', 'visible');
                $('#version').parents('.form-group').addClass('has-danger');
                isValidItem = false;
                return;
            }
            else{
                $('#version').siblings('span.error').css('visibility', 'hidden');
            }
            if($("#person").val()=="" || $("#person").val()== null){
                $('#person').siblings('span.error').css('visibility', 'visible');
                $('#person').parents('.form-group').addClass('has-danger');
                isValidItem = false;
                toastr.error("Recurso es requerido!",{timeOut: 5000});
                return;
            }
            else{
                $('#person').siblings('span.error').css('visibility', 'hidden');
            }

            if($("#proyecto").val()=="" || $("#proyecto").val()== null){
                $('#proyecto').siblings('span.error').css('visibility', 'visible');
                $('#proyecto').parents('.form-group').addClass('has-danger');
                isValidItem = false;
                toastr.error("Proyecto es requerido!",{timeOut: 5000});
                return;
            }else{
                $('#proyecto').siblings('span.error').css('visibility', 'hidden');
            }
            var status = ($("#chktestigo").is(':checked')) ? 'checked' : 'unchecked';
            if(status == 'checked'){
                if($("#nombre1Testigo").val() == "" || $("#nombre1Testigo").val() == null){
                    $('#nombre1Testigo').siblings('span.error').css('visibility', 'visible');
                    $('#nombre1Testigo').parents('.form-group').addClass('has-danger');
                    toastr.error("1er. Nombre del testigo es requerido,",{timeOut: 5000});
                    isValidItem = false;
                    return;
                }
                else{
                    $('#nombre1Testigo').siblings('span.error').css('visibility', 'hidden');
                    $('#nombre1Testigo').parents('.form-group').removeClass('has-danger');
                    $('#nombre1Testigo').parents('.form-group').addClass('has-success');
                }

                if($('#apellido1Testigo').val() == "" || $('#apellido1Testigo').val() == null){
                    $('#apellido1Testigo').siblings('span.error').css('visibility', 'visible');
                    $('#apellido1Testigo').parents('.form-group').addClass('has-danger');
                    toastr.error("1er. Apellido del testigo es requerido,",{timeOut: 5000});
                    isValidItem = false;
                    return;
                }else{
                    $('#apellido1Testigo').siblings('span.error').css('visibility', 'hidden');
                    $('#apellido1Testigo').parents('.form-group').removeClass('has-danger');
                    $('#apellido1Testigo').parents('.form-group').addClass('has-success');
                }
            }

            if($("#asentimiento").val() == "" || $("#asentimiento").val() == null){
                isAllValid = false;
                $('#asentimiento').addClass('is-invalid');
                return;
            }else{
                $('#asentimiento').removeClass('is-invalid');
            }

            if($("#tipoasentimiento").val() == "" || $("#tipoasentimiento").val() == null){
                isAllValid = false;
                $('#tipoasentimiento').addClass('is-invalid');
                return;
            }else{
                $('#tipoasentimiento').removeClass('is-invalid');
            }

            if($("#contactoFuturo").val() == "" || $("#contactoFuturo").val() == null){
                isAllValid = false;
                $('#contactoFuturo').addClass('is-invalid');
                return;
            }else{
                $('#contactoFuturo').removeClass('is-invalid');
            }


            if( $("#asentimiento").val() == "1" && $("#tipoasentimiento").val() == ""){
                $('#tipoasentimiento').siblings('span.error').css('visibility', 'visible');
                $('#tipoasentimiento').parents('.form-group').addClass('has-danger');
                toastr.error("Verifica el tipo de asentimiento!",{timeOut: 5000});
                isValidItem = false;
                return;
            }else if($("#asentimiento").val() == "1" && $("#tipoasentimiento").val() == "0" || $("#tipoasentimiento").val() == ""){
                $('#tipoasentimiento').siblings('span.error').css('visibility', 'visible');
                $('#tipoasentimiento').parents('.form-group').addClass('has-danger');
                toastr.error("Tipo de asentimiento requerido!",{timeOut: 5000});
                $("#tipoasentimiento").select2("open");
                isValidItem = false;
                return;
            }
            else{
                $('#tipoasentimiento').siblings('span.error').css('visibility', 'hidden');
                $('#tipoasentimiento').parents('.form-group').removeClass('has-danger');
                $('#tipoasentimiento').parents('.form-group').addClass('has-success');
            }
                if(isValidItem){
                    var MyArrayPartes=[];
                    $("#partes option").each(function(index, element) {
                        debugger;
                        if($(this).attr('data-id')!=null || $(this).attr('data-id') !=undefined) {
                            var obj = {};
                            obj.iddetalle = $(this).attr('data-id'),
                                    obj.idparte = $(this).val(),
                                    obj.acepta = element.selected,
                                    obj.idparticipantecarta = $(this).attr('id'),
                                    obj.locked = false;
                            MyArrayPartes.push(obj);
                        }
                    });
                    var text = $("#person option:selected").html();
                    var separador ="-";
                    var textoseparado = text.split(separador);
                    data = {
                        codigo: parseInt($("#codigo").val().trim()),
                        idparticipante: parseInt($("#idparticipante").val().trim()),
                        version: parseInt($("#version").val().trim()),
                        asentimiento: $("#asentimiento").val().trim(),
                        relfam: parseInt($("#relfam").val().trim()),
                        nombfirma: $("#nombfirma").val().trim(),
                        nombre2Firma: $("#nombre2Firma").val().trim(),
                        apellido1Firma: $("#apellido1Firma").val().trim(),
                        apellido2Firma: $("#apellido2Firma").val().trim(),
                        person: parseInt($("#person").val().trim()),
                        fechacarta: $("#fechacarta").val(),
                        proyecto: $("#proyecto").val(),
                        contactoFuturo: $("#contactoFuturo").val().trim(), //($('input:checkbox[name=contactoFuturo]').prop('checked') == true) ? '1' : '0',
                        testigopresente: ($('input:checkbox[name=chktestigo]').prop('checked') == true) ? '1' : '0',
                        nombre1testigo: $("#nombre1Testigo").val().trim(),
                        nombre2testigo: $("#nombre2Testigo").val().trim(),
                        apellido1testigo: $("#apellido1Testigo").val().trim(),
                        apellido2testigo: $("#apellido2Testigo").val().trim(),
                        observacion :$("#observacion").val().trim(),
                        edadyears: parseInt( $("#edadyear").val().trim()),
                        edadmeses: parseInt( $("#edadmeses").val().trim()),
                        edaddias: parseInt( $("#edaddias").val().trim()),
                        recurso: textoseparado[0].trim(),
                        tipoasentimiento: $("#tipoasentimiento").val().trim(),
                        fecha_registro : ""+$("#fecha_registro").val(),
                        parte: MyArrayPartes,
                        estudios_actuales: $("#estudios").val(),
                        esIndiceOrMiembro:parseInt($("#tipoCaso").val().trim()),
                        reactivacion: ($('input:checkbox[name=reactivacion]').prop('checked') == true) ? '1' : '0'
                    };
                    ActualizarScan(data);
                }
        });
        function ActualizarScan(obj){
            $.ajax({
                url:  parametros.UpdateAllUrl,
                type: "POST",
                data: JSON.stringify(data),
                dataType: "JSON",
                contentType: "application/json",
                success: function(response){
                    swal({
                        title: "Buen trabajo!",
                        text: parametros.successmessage,
                        type: "success",
                        timer: 2000
                    });
                   window.setTimeout(function(){
                        window.location.href = parametros.listUrl;
                    }, 1500);
                },error: function(jqXHR, textStatus,e){
                    swal({
                        title: "Error 500!",
                        text: "Interno del Servidor",
                        type: "error",
                        timer: 2000
                    });
                }
            });
        }
        var text = "";
        var rela = $("#relacionFam").val();
        switch (rela){
            case "1":
                text = "Madre";
                break;
            case "2":
                text = "Padre";
                break;
            case "3":
                text="Abuelo(a)";
                break;
            case "4":
                text ="Tio(a)";
                break;
            case "5":
                text ="Hermano(a)";
                break;
            case "6":
                text="Otra Relación Familiar";
                break;
            default:
                text = "Participante";
        }
        $("#relacionFam").val(text);
        $('#partes').select2({
            placeholder: '-- Selecciona Parte --'
        });
        $("#version").select2({
            placeholder: '-- Selecciona Versión --'
        });


        $("#carta").on("change", function(){
            $("#version option").remove();
            $("#version").select2('val', '');
            ObtenerVersion(parametros);
        });
        function ObtenerVersion(parametros){
            var idcarta = document.getElementById('carta').value;
            var $version = $('#version');
            $version.empty();
            $.getJSON(parametros.VersionCartatUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
                var len = data.objV.length;
                if(len == 0){
                    alert("Advertencia, Carta no tiene Versión.");
                }else{
                    var d = data.objV;
                    $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                    $.each(d, function (i, val) {
                        $version.append($('<option></option>').val(val.idversion).html(val.version));
                    });
                }
            });
        }

        $("#version").on("change", function(){
            ObtenerParte(parametros);
            $("#partes").select2().val(null).trigger("change");
            $("#partes").select2({placeholder: "Selecciona Parte"});
            $('#partes').empty();
            $("#partes").val(null).trigger('change');
            $('#partes').find('option').remove() ;
        });
        $("#fechacarta").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true
        });
        //Validar las cajas de texto...
        $('.onlytext').keypress(function (e) {
            var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
            return !((tecla > 47 && tecla < 58) || tecla == 46);
        });

        ObtenerParte(parametros);
        var elementos2=[];
        function ObtenerParte(parametros){
            var idversion = document.getElementById('version').value;
            var $version = $('#version');
            var $ele = $("#partes");
            $.getJSON(parametros.ParteVersionUrl,{idversion : idversion, ajax:'true'}, function(data){
                if(data.parte.length > 0){
                    bandera=true;
                    $.each(data.parte, function (i, val) {
                        $ele.append($('<option></option>').val(val.idversion).html(val.version));
                    })
                }else{
                    $ele.empty();
                }
            })
        }
        $("#partes").change(function (e) {
            if (e.added != null){
                seleccionar(e.added.id);
            }
            if (e.removed != null){
                deseleccionar(e.removed.id);
            }
        });
        function seleccionar(id){
            var cod = parseInt(id);
            for (var i = 0; i < elementos2.length; i++) {
                if (elementos2[i].idparte === cod){
                    elementos2[i].acepta = true;
                    break;
                }
            }
        }
        function deseleccionar(id){
            var cod = parseInt(id);
            for (var i = 0; i < elementos2.length; i++) {
                if (elementos2[i].idparte === cod){
                    elementos2[i].acepta = false;
                    break;
                }
            }
        }
        testCheckbox();

        $("#chktestigo").on("click", function(){
            var status = ($(this).is(':checked')) ? 'checked' : 'unchecked';
            if(status == 'checked'){
                $("#selectt").fadeIn("slow");
                $("#nombre1Testigo").prop('required',true);
                $("#apellido1Testigo").prop('required',true);

                $("#nombre1Testigo").attr("required", "true");
                $("#apellido1Testigo").attr("required", "true");
            }else{
                $("#selectt").fadeOut("slow");
                limpiarTxtTestigo();
                $("#nombre1Testigo").val("").attr("required", "false");
                $("#apellido1Testigo").val("").attr("required", "false");
            }
        });

        function limpiarTxtTestigo(){
            $("#nombre1Testigo").val('');
            $("#nombre2Testigo").val('');
            $("#apellido1Testigo").val('');
            $("#apellido2Testigo").val('');
        }


            $('body').delegate('.chktestigo', 'lcs-statuschange', function () {
                var status = ($(this).is(':checked')) ? 'checked' : 'unchecked';
                if (status == 'checked') {
                    $("#selectt").fadeIn("slow");
                    $("#nombre1Testigo").attr("required", "true");
                    $("#apellido1Testigo").attr("required", "true");
                } else {
                    $("#selectt").fadeOut("slow");
                    $("#nombre1Testigo").val("").attr("required", "false");
                    $("#apellido1Testigo").val("").attr("required", "false");
                }
                $("#nombre1Testigo").focus();
            });

        function testCheckbox() {
            if($(".chktestigo").prop('checked')){
                $("#selectt").fadeIn("slow");
            }else{
                $("#selectt").fadeOut("slow");
            }
        }

        HabilitarOrDisabledCaso();
        function HabilitarOrDisabledCaso(){
            if($("#carta").val() ==="6"){
                $("#tipoCaso").prop('disabled',false);
            }else{
                $("#tipoCaso").select2().val(0).trigger("change").prop('disabled',true);
            }
        }
    });
</script>
</body>
</html>
