<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 01/06/2020
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

    <title></title>
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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/covid/listCovid/" htmlEscape="true "/>"><spring:message code="Listado Covid-19" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/covid/SaveForm/" htmlEscape="true "/>"><spring:message code="Formulario Positivo" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="card bg-dark">
                <div class="card-header">
                    <h3 class="page-title">
                        <small><spring:message code="search" /> <spring:message code="positive" /></small>
                    </h3>
                </div>
                <div class="card-body">
                    <div class="container">
                        <spring:url value="/covid/listCovid/" var="listaUrl"/>
                        <div class="row">
                            <div class="col-md-12">
                                <br/>
                                <hr/>
                                <form action="#" autocomplete="off" id="search-participant-form" class="form-horizontal">
                                    <div class="form-group row">
                                        <label class="form-control-label col-md-2" for="username"><spring:message code="participant.code" />
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
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="card">
                <div class="card-header">
                    <h3 class="page-title">
                       hola
                    </h3>
                </div>
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-8">
                            <form action="#" autocomplete="off" id="version-form" class="form-horizontal">
                                <input id="codigo" name="codigo" type="text"  class="form-control"/>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="codigoCasa"><spring:message code="house" />
                                        <span class="required">*</span>
                                    </label>
                                    <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                        <input id="codigoCasa" name="codigoCasa" type="text" readonly  class="form-control"/>
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
                                               value="" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="codigoParticipante"><spring:message code="positive" />
                                        <span class="required">*</span>
                                    </label>
                                    <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                        <input id="codigoParticipante" name="codigoParticipante" type="text" readonly  class="form-control"/>
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
                                               value="" />
                                    </div>
                                </div>

                                <div class="form-actions fluid">
                                    <div class="col-md-12">
                                        <button id="guardar" type="submit" class="btn btn-primary btn-lg btn-ladda" data-style="expand-right">
                                           <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>

                                        <a href="${fn:escapeXml(listaUrl)}" class="btn btn-warning btn-lg btn-ladda" data-style="expand-right">
                                             <i class="fa fa-minus-circle" aria-hidden="true"></i><spring:message code="cancel" /></a>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-2">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
