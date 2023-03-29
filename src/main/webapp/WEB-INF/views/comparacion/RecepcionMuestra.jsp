<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 17/01/2021
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
    <link href="${uiCss}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss"/>
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <style>
        .bg-primary {
            background-color: #eee !important;
        }
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #eee;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #eee;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 4px solid #f9fdfc;
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
            border-bottom: 3px solid #ffffff;
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
        /* EFFECT 1 ========================================== */

        .effect-1 {
            position: relative;
        }
        .effect-1::before, .effect-1::after {
            z-index: -1;
            position: absolute;
            content: "";
            bottom: 15px;
            left: 10px;
            width: 50%;
            top: 80%;
            max-width: 300px;
            background: #aaa;
            box-shadow: 0 15px 10px #aaa;
            transform: rotate(-3deg);
        }
        .effect-1::after {
            transform: rotate(3deg);
            right: 10px;
            left: auto;
        }
        /**********/
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

        .shadow {
            overflow: hidden;
            position: relative;
            transform: translate3d(0, 0, 0)
        }

        .shadow:before {
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

        .shadow:active:before {
            transform: scale(0, 0);
            opacity: .1;
            transition: 0s
        }

        .btn-primary {
           background-color: #3f51b5 !important;
           color: #fff
        }
        .rounded-pill {
            border-radius: 50rem !important;
        }
        .label-success {
            background-color: #5cb85c;
        }
        .label-warning {
            background-color: #f0ad4e;
        }
        .label-danger {
            background-color: #d9534f;
        }
        .label-default {
            background-color: #777;
        }
        .label {
            display: inline;
            padding: .2em .6em .3em;
            font-size: 75%;
            font-weight: 700;
            line-height: 1;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: .25em;
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

        #participantCode-error{
            margin-right:700px;
            text-align: center !important;
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
                <a href="<spring:url value="/comparacion/muestra/" htmlEscape="true "/>"><spring:message code="Reception"/> <spring:message code="sample" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div class="">
            <div class="row">
            <div class="col-md-12 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-dark pb-3"style="font-family: Roboto"> <spring:message code="sample"/> </h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block text-dark"> <spring:message code="Form"/> </span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                            <span class="d-block d-md-none"><i class="ti-export"></i></span>
                            <span class="d-none d-md-block text-dark"> <spring:message code="List"/>  </span>
                        </a>
                    </li>

                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
            <div>
                <spring:url value="/comparacion/searchParticipant" var="searchParticipantUrl"/>
                <spring:url value="/comparacion/saveMuestra" var="saveMuestraUrl"/>
                <spring:url value="/comparacion/muestra/" var="refreshPageUrl"/>
                <spring:url value="/comparacion/getUserName" var="getUserNameUrl"/>
                <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                <form action="#" autocomplete="off" id="search-participant-form" name="search-participant-form" class="form-horizontal">
                    <div class="form-group row">
                        <label class="form-control-label col-md-2 text-right" for="<spring:message code="participant.code" />"> <spring:message code="participant.code" />
                            <span class="required">*</span>
                        </label>
                        <div class="input-group col-md-10">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input id="participantCode" name="participantCode" type="text" value="" class="form-control"/>
                            <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right">
                                <i class="fa fa-search" aria-hidden="true"></i>
                                <spring:message code="search" />
                            </button>
                        </div>
                    </div>
                </form>
                <hr/>
                <form class="form-horizontal" action="#" id="FormMuestra"  name="FormMuestra" autocomplete="off" role="form">
                    <div class="form-row" hidden="hidden">
                        <div class="form-group col-md-6">
                            <input type="text" class="form-control" readonly name="codigoMuestra" id="codigoMuestra" value="${caso.mId.codigo}"/>
                        </div>
                        <div class="form-group col-md-6">
                            <input type="text" class="form-control" readonly name="fechamuestra" id="fechamuestra" value="<fmt:formatDate value="${caso.movilInfo.today}" pattern="dd/MM/yyyy" />"/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-2">
                            <label for="codigoMx"><spring:message code="sample.code"/></label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control focusNext" name="codigoMx" id="codigoMx" value="${caso.mId.codigo}" tabindex="2">
                        </div>

                        <div class="form-group col-md-3">
                            <label for="fechaMx"><spring:message code="lbl.date"/></label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control focusNext" tabindex="3" name="fechaMx" id="fechaMx" data-date-end-date="+0d" value="<fmt:formatDate value="${caso.mId.fechaMuestra}" pattern="dd/MM/yyyy" />">
                        </div>

                        <div class="form-group col-md-3">
                            <label for="fechaReg"><spring:message code="dateCreated"/></label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control focusNext" tabindex="4" name="fechaReg" id="fechaReg" value="<fmt:formatDate value="${caso.movilInfo.today}" pattern="dd/MM/yyyy HH:mm:ss" />">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="estudiosAct"><spring:message code="userstudies"/></label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control focusNext" tabindex="5" name="estudiosAct" readonly id="estudiosAct" value="${caso.estudiosAct}">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label for="recurso1">Recurso 1:</label>
                            <span class="required text-danger"> * </span>
                            <select id="recurso1" name="recurso1" class="form-control focusNext" tabindex="6" required="required">
                                <option selected value=""><spring:message code="select"/>...</option>
                                <c:forEach items="${recurso}" var="r">
                                    <c:choose>
                                        <c:when test="${r.personal.idpersonal eq caso.movilInfo.recurso1}">
                                            <option selected="selected" value="${r.personal.idpersonal}">${r.personal.idpersonal} - ${r.personal.nombreApellido}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${r.personal.idpersonal}">${r.personal.idpersonal} - ${r.personal.nombreApellido}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="recurso2">Recurso 2:</label>
                            <span class="required text-danger"> * </span>
                            <select id="recurso2" name="recurso2" class="form-control focusNext" required="required" tabindex="7">
                                <option selected value=""><spring:message code="select"/>...</option>
                                <c:forEach items="${recurso}" var="r2">
                                    <c:choose>
                                        <c:when test="${r2.personal.idpersonal eq caso.movilInfo.recurso2}">
                                            <option selected="selected" value="${r2.personal.idpersonal}">${r2.personal.idpersonal} - ${r2.personal.nombreApellido}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${r2.personal.idpersonal}">${r2.personal.idpersonal} - ${r2.personal.nombreApellido}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="username"><spring:message code="username"/></label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control focusNext" id="username" name="username" required="required" tabindex="8"/>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="terrenoMx"><spring:message code="lbl.land"/></label>
                            <span class="required text-danger"> * </span>
                            <select id="terrenoMx" name="terrenoMx" class="form-control focusNext" tabindex="9" required="required">
                                <span class="required text-danger"> * </span>
                                <option selected value=""><spring:message code="select"/>...</option>
                                <c:forEach items="${SiNo}" var="sn">
                                    <c:choose>
                                        <c:when test="${sn.spanish eq caso.terreno}">
                                            <option selected="selected" value="${sn.spanish}">${sn.spanish}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${sn.spanish}">${sn.spanish}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label for="txtpinchazo"><spring:message code="numPinchazos"/></label>
                            <input type="number" class="form-control focusNext" tabindex="10" id="txtpinchazo" name="txtpinchazo" min="1" max="4" value="${caso.pinchazos}">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="tubobhc"><spring:message code="lbl.BHC"/> </label>
                            <input type="text" class="form-control focusNext" tabindex="11" id="tubobhc" name="tubobhc" min="1" max="1" value="${caso.tuboBHC}">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="tuboleuco"><spring:message code="lbl.Leucosep"/></label>
                            <input type="text" class="form-control focusNext" tabindex="12" id="tuboleuco" name="tuboleuco" min="1" max="1" value="${caso.tuboLeu}">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="tuborojo"><spring:message code="lbl.serologia"/></label>
                            <input type="text" class="form-control focusNext" tabindex="13" id="tuborojo" name="tuborojo" min="1" max="2" value="${caso.tuboRojo}">
                        </div>

                        <div class="form-group col-md-12">
                            <label for="tuborojo"><spring:message code="observacion"/></label>
                            <textarea class="form-control" name="observacion" id="observacion" rows="3">${caso.observacion}</textarea>
                        </div>

                    </div>

                    <div class="form-group">
                        <div class="form-check">
                            <c:choose>
                                <c:when test="${caso.movilInfo.estado eq '1'}">
                                    <input class="form-check-input" type="checkbox" id="chkEstado" name="chkEstado" checked="checked">
                                </c:when>
                                <c:otherwise>
                                    <input class="form-check-input" type="checkbox" id="chkEstado" name="chkEstado">
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label" for="chkEstado"><spring:message code="lbl.State"/></label>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-row">
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-lg btn-block btn-primary shadow">
                                <i class="fa fa-save" aria-hidden="true"></i>
                                <spring:message code="save" />
                            </button>
                        </div>
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <a href="${fn:escapeXml(refreshPageUrl)}" class="btn btn-warning btn-block btn-lg shadow">
                                <i class="fa fa-minus-circle" aria-hidden="true"></i>
                                <spring:message code="cancel" />
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            </div>

            <div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
                <div class="table-responsive">
                    <table id="tblMuestra" class="table email-table no-wrap table-hover v-middle mb-0 font-14 table-bordered table-striped">
                        <thead>
                        <tr>
                            <th class="text-center"><spring:message code="code" /></th>
                            <th class="text-center"><spring:message code="dateCreated" /></th>
                            <th class="text-center"><spring:message code="sample.date" /></th>
                            <th class="text-center"><spring:message code="username" /></th>
                            <th class="text-center"><spring:message code="lbl.land" /></th>
                            <th class="text-center"><spring:message code="numPinchazos" /></th>
                            <th class="text-center"><spring:message code="lbl.BHC" /></th>
                            <th class="text-center"><spring:message code="Rojo" /></th>
                            <th class="text-center"><spring:message code="lbl.Leucosep" /></th>
                            <th class="text-center"><spring:message code="Pax" /></th>
                            <th class="text-center"><spring:message code="userstudies" /></th>
                            <th class="text-center"><spring:message code="observacion" /></th>
                            <th class="text-center"><spring:message code="actions" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listaMx}" var="mx">
                            <spring:url value="/comparacion/editMuestra/{id}/{fechaMx}" var="editMuestraUrl">
                                <spring:param name="id" value="${mx.mId.codigo}" />
                                <spring:param name="fechaMx" value="${mx.mId.fechaMuestra}" />
                            </spring:url>
                            <spring:url value="/comparacion/deletemuestra" var="deleteMuestraUrl"></spring:url>
                            <tr>
                                <td class="text-center">${mx.mId.codigo}</td>
                                <td class="text-center"><fmt:formatDate value="${mx.movilInfo.today}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                <td class="text-center"><fmt:formatDate value="${mx.mId.fechaMuestra}" pattern="dd/MM/yyyy"/></td>
                                <td class="text-center">${mx.movilInfo.username}</td>
                                <c:choose>
                                    <c:when test="${mx.terreno eq 'Si'}">
                                        <td class="text-center"><span class="text-primary badge badge-verde" style="font-size: 0.875em !important; font-weight: 600; font-family: Roboto; border-radius: 3px;"><i class="fa fa-check" aria-hidden="true"></i></span></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-center"><span class="text-danger badge badge-verde" style="font-size: 0.875em !important; font-weight: 600; font-family: Roboto; border-radius: 3px"><i class="fa fa-times" aria-hidden="true"></i></span></td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${mx.pinchazos >= 1}">
                                        <td class="text-center"> <span class="label label-success"> ${mx.pinchazos} </span> </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-center"> <span class="label label-warning"> <i class="fa fa-times"></i> </span> </td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${mx.tuboBHC >= 1 }">
                                        <td class="text-center"> <span class="label label-success"> ${mx.tuboBHC} </span> </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-center"> <span class="label label-danger"> <i class="fa fa-times" aria-hidden="true"></i> </span> </td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${mx.tuboRojo >= 1}">
                                        <td class="text-center"> <span class="label label-success"> ${mx.tuboRojo} </span> </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-center"> <span class="label label-danger"> <i class="fa fa-times" aria-hidden="true"></i> </span> </td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${mx.tuboLeu >= 1 }">
                                        <td class="text-center"> <span class="label label-success"> ${mx.tuboLeu} </span> </td>
                                    </c:when>

                                    <c:otherwise>
                                        <td class="text-center"> <span class="label label-danger"> <i class="fa fa-times" aria-hidden="true"></i> </span> </td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${mx.tuboPax >= 1}">
                                        <td class="text-center"> <span class="label label-success"> ${mx.tuboPax} </span> </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-center"> <span class="label label-danger"> <i class="fa fa-times"></i> </span> </td>
                                    </c:otherwise>
                                </c:choose>

                                <td class="text-center"> <span class="label label-default"> ${mx.estudiosAct} </span> </td>
                                <td class="text-center">  ${mx.observacion} </td>
                                <td class="text-center">
                                    <!-- <a href="${fn:escapeXml(editMuestraUrl)}" data-toggle="tooltip" data-placement="top" title="Edit" class="btn btn-primary btn-sm">
                                                <i class="fa fa-edit"></i>
                                         </a> -->
                                    <button id="btndelete" title="<spring:message code="delete" />" data-id="${mx.mId.codigo}" data-toggle="tooltip" data-placement="bottom" class="btn btn-danger btn-sm btndelete">
                                        <i class="fa fa-trash text-white" aria-hidden="true"></i>
                                    </button>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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

<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>

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
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>

<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs"/>
<script src="${validateJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs"/>
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin"/>
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs"/>
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/jquery.maskedinput.js" var="maskJs" />
<script type="text/javascript" src="${maskJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sweet" />
<script type="text/javascript" src="${sweet}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/comparacion/muestra.js" var="RecepcionMuestra"/>
<script type="application/javascript" src="${RecepcionMuestra}"></script>

<script type="text/javascript">
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
    $(document).ready(function(){
        $("#fechaReg").mask("99/99/9999 99:99:99");
        $("#recurso1").select2();
        $("#recurso2").select2();
        //$("#usernameMx").select2();
        $("#terrenoMx").select2();
        $("#fechaMx").datepicker({
            format: "dd/mm/yyyy",
            todayBtn: true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });
        var parametro = {
            dataTablesLang:"${dataTablesLang}",
            saveMuestraUrl: "${saveMuestraUrl}",
            refreshPageUrl: "${refreshPageUrl}",
            editMuestraUrl: "${editMuestraUrl}",
            searchParticipantUrl: "${searchParticipantUrl}",
            getUserNameUrl   : "${getUserNameUrl}",
            deleteMuestraUrl: "${deleteMuestraUrl}",
            successMessage:"${successMessage}"
        };
        handleDatePickers("${lenguaje}");
        proccessMuestra.init(parametro);
        $("#tblMuestra").DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });


        $( "#username" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parametro.getUserNameUrl, {username: $('#username').val().trim(), ajax: 'true'},function(data){
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
        $("#participantCode").focus();
    });
</script>
</body>
</html>