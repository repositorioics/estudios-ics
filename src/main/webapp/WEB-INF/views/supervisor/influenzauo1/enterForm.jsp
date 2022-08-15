<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../../fragments/headTag.jsp" />
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <style>
        /*Nav Tabs & Pills */
    .nav-tabs .nav-link {
        color: #223035;
        font-size: 12px;
        text-align: center;
        letter-spacing: 1px;
        font-weight: 600;
        margin: 2px;
        margin-bottom: 0;
        padding: 12px 20px;
        text-transform: uppercase;
        border: 1px solid transparent;
        border-top-left-radius: .25rem;
        border-top-right-radius: .25rem;

    }
    .nav-tabs .nav-link:hover{
        border: 1px solid transparent;
    }
    .nav-tabs .nav-link i {
        margin-right: 2px;
        font-weight: 600;
    }

    .top-icon.nav-tabs .nav-link i{
        margin: 0px;
        font-weight: 500;
        display: block;
        font-size: 20px;
        padding: 5px 0;
    }

    .nav-tabs-primary.nav-tabs{
        border-bottom: 1px solid #008cff;
    }

    .nav-tabs-primary .nav-link.active, .nav-tabs-primary .nav-item.show>.nav-link {
        color: #008cff;
        background-color: #fff;
        border-color: #008cff #008cff #fff;
        border-top: 3px solid #008cff;
    }

    .nav-tabs-success.nav-tabs{
        border-bottom: 1px solid #15ca20;
    }

    .nav-tabs-success .nav-link.active, .nav-tabs-success .nav-item.show>.nav-link {
        color: #15ca20;
        background-color: #fff;
        border-color: #15ca20 #15ca20 #fff;
        border-top: 3px solid #15ca20;
    }

    .nav-tabs-info.nav-tabs{
        border-bottom: 1px solid #0dceec;
    }

    .nav-tabs-info .nav-link.active, .nav-tabs-info .nav-item.show>.nav-link {
        color: #0dceec;
        background-color: #fff;
        border-color: #0dceec #0dceec #fff;
        border-top: 3px solid #0dceec;
    }

    .nav-tabs-danger.nav-tabs{
        border-bottom: 1px solid #fd3550;
    }

    .nav-tabs-danger .nav-link.active, .nav-tabs-danger .nav-item.show>.nav-link {
        color: #fd3550;
        background-color: #fff;
        border-color: #fd3550 #fd3550 #fff;
        border-top: 3px solid #fd3550;
    }

    .nav-tabs-warning.nav-tabs{
        border-bottom: 1px solid #ff9700;
    }

    .nav-tabs-warning .nav-link.active, .nav-tabs-warning .nav-item.show>.nav-link {
        color: #ff9700;
        background-color: #fff;
        border-color: #ff9700 #ff9700 #fff;
        border-top: 3px solid #ff9700;
    }

    .nav-tabs-dark.nav-tabs{
        border-bottom: 1px solid #223035;
    }

    .nav-tabs-dark .nav-link.active, .nav-tabs-dark .nav-item.show>.nav-link {
        color: #223035;
        background-color: #fff;
        border-color: #223035 #223035 #fff;
        border-top: 3px solid #223035;
    }

    .nav-tabs-secondary.nav-tabs{
        border-bottom: 1px solid #75808a;
    }
    .nav-tabs-secondary .nav-link.active, .nav-tabs-secondary .nav-item.show>.nav-link {
        color: #75808a;
        background-color: #fff;
        border-color: #75808a #75808a #fff;
        border-top: 3px solid #75808a;
    }

    .tabs-vertical .nav-tabs .nav-link {
        color: #223035;
        font-size: 12px;
        text-align: center;
        letter-spacing: 1px;
        font-weight: 600;
        margin: 2px;
        margin-right: -1px;
        padding: 12px 1px;
        text-transform: uppercase;
        border: 1px solid transparent;
        border-radius: 0;
        border-top-left-radius: .25rem;
        border-bottom-left-radius: .25rem;
    }

    .tabs-vertical .nav-tabs{
        border:0;
        border-right: 1px solid #dee2e6;
    }

    .tabs-vertical .nav-tabs .nav-item.show .nav-link, .tabs-vertical .nav-tabs .nav-link.active {
        color: #495057;
        background-color: #fff;
        border-color: #dee2e6 #dee2e6 #fff;
        border-bottom: 1px solid #dee2e6;
        border-right: 0;
        border-left: 1px solid #dee2e6;
    }

    .tabs-vertical-primary.tabs-vertical .nav-tabs{
        border:0;
        border-right: 1px solid #008cff;
    }

    .tabs-vertical-primary.tabs-vertical .nav-tabs .nav-item.show .nav-link, .tabs-vertical-primary.tabs-vertical .nav-tabs .nav-link.active {
        color: #008cff;
        background-color: #fff;
        border-color: #008cff #008cff #fff;
        border-bottom: 1px solid #008cff;
        border-right: 0;
        border-left: 3px solid #008cff;
    }

    .tabs-vertical-success.tabs-vertical .nav-tabs{
        border:0;
        border-right: 1px solid #15ca20;
    }

    .tabs-vertical-success.tabs-vertical .nav-tabs .nav-item.show .nav-link, .tabs-vertical-success.tabs-vertical .nav-tabs .nav-link.active {
        color: #15ca20;
        background-color: #fff;
        border-color: #15ca20 #15ca20 #fff;
        border-bottom: 1px solid #15ca20;
        border-right: 0;
        border-left: 3px solid #15ca20;
    }

    .tabs-vertical-info.tabs-vertical .nav-tabs{
        border:0;
        border-right: 1px solid #0dceec;
    }

    .tabs-vertical-info.tabs-vertical .nav-tabs .nav-item.show .nav-link, .tabs-vertical-info.tabs-vertical .nav-tabs .nav-link.active {
        color: #0dceec;
        background-color: #fff;
        border-color: #0dceec #0dceec #fff;
        border-bottom: 1px solid #0dceec;
        border-right: 0;
        border-left: 3px solid #0dceec;
    }

    .tabs-vertical-danger.tabs-vertical .nav-tabs{
        border:0;
        border-right: 1px solid #fd3550;
    }

    .tabs-vertical-danger.tabs-vertical .nav-tabs .nav-item.show .nav-link, .tabs-vertical-danger.tabs-vertical .nav-tabs .nav-link.active {
        color: #fd3550;
        background-color: #fff;
        border-color: #fd3550 #fd3550 #fff;
        border-bottom: 1px solid #fd3550;
        border-right: 0;
        border-left: 3px solid #fd3550;
    }

    .tabs-vertical-warning.tabs-vertical .nav-tabs{
        border:0;
        border-right: 1px solid #ff9700;
    }

    .tabs-vertical-warning.tabs-vertical .nav-tabs .nav-item.show .nav-link, .tabs-vertical-warning.tabs-vertical .nav-tabs .nav-link.active {
        color: #ff9700;
        background-color: #fff;
        border-color: #ff9700 #ff9700 #fff;
        border-bottom: 1px solid #ff9700;
        border-right: 0;
        border-left: 3px solid #ff9700;
    }

    .tabs-vertical-dark.tabs-vertical .nav-tabs{
        border:0;
        border-right: 1px solid #223035;
    }

    .tabs-vertical-dark.tabs-vertical .nav-tabs .nav-item.show .nav-link, .tabs-vertical-dark.tabs-vertical .nav-tabs .nav-link.active {
        color: #223035;
        background-color: #fff;
        border-color: #223035 #223035 #fff;
        border-bottom: 1px solid #223035;
        border-right: 0;
        border-left: 3px solid #223035;
    }

    .tabs-vertical-secondary.tabs-vertical .nav-tabs{
        border:0;
        border-right: 1px solid #75808a;
    }

    .tabs-vertical-secondary.tabs-vertical .nav-tabs .nav-item.show .nav-link, .tabs-vertical-secondary.tabs-vertical .nav-tabs .nav-link.active {
        color: #75808a;
        background-color: #fff;
        border-color: #75808a #75808a #fff;
        border-bottom: 1px solid #75808a;
        border-right: 0;
        border-left: 3px solid #75808a;
    }

    .nav-pills .nav-link {
        border-radius: .25rem;
        color: #223035;
        font-size: 12px;
        text-align: center;
        letter-spacing: 1px;
        font-weight: 600;
        text-transform: uppercase;
        margin: 3px;
        padding: 12px 20px;
        -webkit-transition: all 0.3s ease;
        -moz-transition: all 0.3s ease;
        -o-transition: all 0.3s ease;
        transition: all 0.3s ease;

    }

    .nav-pills .nav-link:hover {
        background-color:#f4f5fa;
    }

    .nav-pills .nav-link i{
        margin-right:2px;
        font-weight: 600;
    }

    .top-icon.nav-pills .nav-link i{
        margin: 0px;
        font-weight: 500;
        display: block;
        font-size: 20px;
        padding: 5px 0;
    }

    .nav-pills .nav-link.active, .nav-pills .show>.nav-link {
        color: #fff;
        background-color: #008cff;
        box-shadow: 0 4px 20px 0 rgba(0,0,0,.14), 0 7px 10px -5px rgba(0, 140, 255, 0.5);
    }

    .nav-pills-success .nav-link.active, .nav-pills-success .show>.nav-link {
        color: #fff;
        background-color: #15ca20;
        box-shadow: 0 4px 20px 0 rgba(0,0,0,.14), 0 7px 10px -5px rgba(21, 202, 32, .5);
    }

    .nav-pills-info .nav-link.active, .nav-pills-info .show>.nav-link {
        color: #fff;
        background-color: #0dceec;
        box-shadow: 0 4px 20px 0 rgba(0,0,0,.14), 0 7px 10px -5px rgba(13, 206, 236, 0.5);
    }

    .nav-pills-danger .nav-link.active, .nav-pills-danger .show>.nav-link{
        color: #fff;
        background-color: #fd3550;
        box-shadow: 0 4px 20px 0 rgba(0,0,0,.14), 0 7px 10px -5px rgba(253, 53, 80, .5);
    }

    .nav-pills-warning .nav-link.active, .nav-pills-warning .show>.nav-link {
        color: #fff;
        background-color: #ff9700;
        box-shadow: 0 4px 20px 0 rgba(0,0,0,.14), 0 7px 10px -5px rgba(255, 151, 0, .5);
    }

    .nav-pills-dark .nav-link.active, .nav-pills-dark .show>.nav-link {
        color: #fff;
        background-color: #223035;
        box-shadow: 0 4px 20px 0 rgba(0,0,0,.14), 0 7px 10px -5px rgba(34, 48, 53, .5);
    }

    .nav-pills-secondary .nav-link.active, .nav-pills-secondary .show>.nav-link {
        color: #fff;
        background-color: #75808a;
        box-shadow: 0 4px 20px 0 rgba(0,0,0,.14), 0 7px 10px -5px rgba(117, 128, 138, .5);
    }
    .card .tab-content{
        padding: 1rem 0 0 0;
    }

    .z-depth-3 {
        -webkit-box-shadow: 0 11px 7px 0 rgba(0,0,0,0.19),0 13px 25px 0 rgba(0,0,0,0.3);
        box-shadow: 0 11px 7px 0 rgba(0,0,0,0.19),0 13px 25px 0 rgba(0,0,0,0.3);
    }
    .form-control-feedback {
        margin-top: 0.25rem;
        width: 95%;
        text-align: center;
    }
    </style>

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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/super/casacaso/" htmlEscape="true "/>"><spring:message code="uo1.positives" /></a>
            </li>
        </ol>
        <spring:url value="/super/UO1/saveCase" var="saveUrl"/>
        <spring:url value="/super/UO1/searchParticipant" var="searchUrl"/>
        <spring:url value="/super/UO1/" var="listaUrl"/>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h3 class="page-title">
                        <small><spring:message code="search" /> <spring:message code="positive" /></small>
                    </h3>
                </div>
                <div class="card-block">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="#" autocomplete="off" id="search-participant-form" class="form-horizontal">
                                    <div class="form-group row">
                                        <label class="form-control-label col-md-2" for="participantCode"><spring:message code="participant.code" />
                                            <span class="required">*</span>
                                        </label>
                                        <div class="input-group col-md-10">
                                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                            <input id="participantCode" name="participantCode" type="text" value="" class="form-control"/>
                                            <button type="submit" id="buscar"  class="btn btn-success btn-ladda" data-style="expand-right">
                                                <i class="fa fa-search" aria-hidden="true"></i>
                                                <spring:message code="search" />
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!--init tabs-->
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card z-depth-3">
                                    <div class="card-body">
                                        <ul class="nav nav-pills nav-pills-primary nav-justified">
                                            <li class="nav-item">
                                                <a href="javascript:void();" data-target="#profile" data-toggle="pill" class="nav-link active show">
                                                    <i class="icon-user"></i>
                                                    <span class="hidden-xs"> <c:choose>
                                                        <c:when test="${agregando}">
                                                            <spring:message code="add" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <spring:message code="edit" />
                                                        </c:otherwise>
                                                    </c:choose></span></a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="javascript:void();" data-target="#messages" data-toggle="pill" class="nav-link">
                                                    <i class="fa fa-flask" aria-hidden="true"></i>
                                                    <span class="hidden-xs"><spring:message code="Muestra" /> <spring:message code="in_last" /></span>
                                                    <span class="badgenumMuestra" id="numDiasMuestra"></span>
                                                    <span class="badgenumMuestra"><spring:message code="lbl.days" /></span>
                                                    <span class="badgenumMuestra" id="numMuestra"></span></a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="javascript:void();" data-target="#edit" data-toggle="pill" class="nav-link">
                                                    <i class="fa fa-flask" aria-hidden="true"></i>
                                                    <span class="hidden-xs"><spring:message code="Chf_Muestra" /> <spring:message code="in_last" /></span>
                                                    <span class="badgenumMuestra" id="numDiasCHFMuestra"></span>
                                                    <span class="badgenumMuestra"><spring:message code="lbl.days" /></span>
                                                    <span class="badgenumMuestra" id="numCHFMuestra"></span></a>
                                            </li>
                                        </ul>
                                        <div class="tab-content p-1">
                                            <div class="tab-pane active show" id="profile">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div id="msgAlert"  class="alert alert-info alert-dismissible fade show" role="alert">
                                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                            <strong><span id="alertas"></span></strong>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <form action="#" autocomplete="off" id="enterform" class="form-horizontal">
                                                            <input id="codigo" name="codigo" type="text" value="${caso.codigoCasoParticipante}" hidden="hidden" class="form-control"/>
                                                            <div class="form-group row">
                                                                <label class="form-control-label col-md-3" for="codigoParticipante"><spring:message code="positive" />
                                                                    <span class="required">*</span>
                                                                </label>
                                                                <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                                                    <input id="codigoParticipante" name="codigoParticipante" type="text" readonly value="${caso.participante.codigo}" class="form-control"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label class="form-control-label col-md-3" for="codigoCasa"><spring:message code="house" />
                                                                    <span class="required">*</span>
                                                                </label>
                                                                <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                                                    <input id="codigoCasa" name="codigoCasa" type="text" readonly value="${caso.participante.casa.codigo}" class="form-control"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label class="form-control-label col-md-3" for="fechaInicio"><spring:message code="logindate" />
										<span class="required">
											 *
										</span>
                                                                </label>
                                                                <div class="input-group col-md-9">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                            </span>
                                                                    <input name="fechaInicio" id="fechaInicio" class="form-control date-picker" type="text" data-date-end-date="+0d"
                                                                           value="<fmt:formatDate value="${caso.fechaIngreso}" pattern="dd/MM/yyyy" />" />
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label class="col-md-3 form-control-label" for="positivoPor"><spring:message code="lbl.positive.by"/><span class="required">*</span></label>
                                                                <div class="col-md-9">
                                                                    <select name="positivoPor" id="positivoPor" class="form-control select2-single">
                                                                        <option selected value=""><spring:message code="select" />...</option>
                                                                        <c:forEach items="${positivoPor}" var="cat">
                                                                            <c:choose>
                                                                                <c:when test="${caso.positivoPor eq cat.catKey}">
                                                                                    <option selected value="${cat.catKey}">${cat.spanish}</option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option value="${cat.catKey}">${cat.spanish}</option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label class="form-control-label col-md-3" for="fis"><spring:message code="FIS" />
										<span class="required">
											 *
										</span>
                                                                </label>
                                                                <div class="input-group col-md-9">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                            </span>
                                                                    <input name="fis" id="fis" class="form-control date-picker" type="text" data-date-end-date="+0d"
                                                                           value="<fmt:formatDate value="${caso.fis}" pattern="dd/MM/yyyy" />"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label class="form-control-label col-md-3" for="fif"><spring:message code="fif" />
										<span class="required">
											 *
										</span>
                                                                </label>
                                                                <div class="input-group col-md-9">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                            </span>
                                                                    <input name="fif" id="fif" class="form-control date-picker" type="text" data-date-end-date="+0d"
                                                                           value="<fmt:formatDate value="${caso.fif}" pattern="dd/MM/yyyy" />" />
                                                                </div>
                                                            </div>
                                                            <div class="form-actions fluid">
                                                                <div class="col-md-offset-6 col-md-8">
                                                                    <button id="guardar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right"><spring:message code="save" /></button>
                                                                    <a href="${fn:escapeXml(listaUrl)}" class="btn btn-danger btn-ladda" data-style="expand-right"><spring:message code="cancel" /></a>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <!--/row-->
                                            </div>
                                            <div class="tab-pane" id="messages">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="table-responsive">
                                                            <table class="table table-hover table-bordered" id="tblMuestra">
                                                                <thead>
                                                                <tr>
                                                                    <th class="text-center"><spring:message code="code"/></th>
                                                                    <th class="text-center"><spring:message code="dateCreated"/></th>
                                                                    <th class="text-center"><spring:message code="fechaToma"/></th>
                                                                    <th class="text-center"><spring:message code="Terreno"/></th>
                                                                    <th class="text-center"><spring:message code="usuario"/></th>
                                                                    <th class="text-center"><spring:message code="Rojo"/></th>
                                                                    <th class="text-center"><spring:message code="Pbmc"/></th>
                                                                    <th class="text-center"><spring:message code="BHC"/></th>
                                                                    <th class="text-center"><spring:message code="study"/></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody></tbody>
                                                            </table>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="edit">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="table-responsive">
                                                            <table class="table table-hover table-bordered" id="tblChfMuestra">
                                                                <thead>
                                                                <tr>
                                                                    <th class="text-center"><spring:message code="code"/></th>
                                                                    <th class="text-center"><spring:message code="dateCreated"/></th>
                                                                    <th class="text-center"><spring:message code="fechaToma"/></th>
                                                                    <th class="text-center"><spring:message code="volumen"/></th>
                                                                    <th class="text-center"><spring:message code="code"/> <spring:message code="sample"/> </th>
                                                                    <th class="text-center"><spring:message code="Tipo Tubo"/></th>
                                                                    <th class="text-center"><spring:message code="Proposito"/></th>
                                                                    <th class="text-center"><spring:message code="Tipo Muestra"/></th>
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
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--fin tabs-->
                    <div class="row">
                        <!--   <div class="col-md-8">
                             <form action="#" autocomplete="off" id="search-participant-form" class="form-horizontal">
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="participantCode"><spring:message code="participant.code" />
                                        <span class="required">*</span>
                                    </label>
                                    <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                        <input id="participantCode" name="participantCode" type="text" value="" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="col-md-offset-6 col-md-8">
                                        <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right"><spring:message code="search" /></button>
                                    </div>
                                </div>
                            </form>
                        </div>-->
                    </div>
                </div>
            </div>
            <!-- /.conainer-fluid -->
        </div>
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
<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
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
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/scripts/influenzauo1/process-case.js" var="processUo1Js" />
<script src="${processUo1Js}"></script>
<script>
    $(function () {
        $("li.supervision").addClass("open");
        $("li.intensiveMonitoring").addClass("open");
    });
</script>
<script>
    jQuery(document).ready(function() {
        $("#msgAlert").hide();
        var parameters = {
            error          : "${errorProcess}",
            saveUrl        : "${saveUrl}",
            listaUrl       : "${listaUrl}",
            searchUrl      : "${searchUrl}",
            successmessage : "${successMessage}",
            dataTablesLang : "${dataTablesLang}"
        };
        handleDatePickers("${lenguaje}");
        ProcessCaseUO1.init(parameters);
    });
</script>
</body>
</html>