<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 28/09/2021
  Time: 12:38
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
    <jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
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
            border-bottom: 5px solid #eb290c;
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
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #008cba;
        }
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #008cba;
        }
        input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff;}
        input[type="text"]{color: #000000; }
        input[type="select"]{color: #000000;}
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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/super/covid/listCandidates/" htmlEscape="true "/>"><spring:message code="covid19.candidates" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/super/covid/otherPositive" htmlEscape="true "/>"><spring:message code="others" /> <spring:message code="positive" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <spring:url value="/super/covid/listCandidates/" var="listaUrl"/>
            <spring:url value="/super/covid/otherPositive/" var="otherPositiveUrl"/>
            <spring:url value="/super/covid/saveOtrosCandidateTCovid" var="saveOrUpdateUrl"/>
            <div class="">
            <div class="row">
            <div class="col-md-12 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-white pb-3"><spring:message code="edit" /> <spring:message code="others" /> <spring:message code="positive" /></h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block"> <spring:message code="Form" /></span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                            <span class="d-block d-md-none"><i class="ti-export"></i></span>
                            <span class="d-none d-md-block"><spring:message code="List" /></span>
                        </a>
                    </li>

                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
            <div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <form name="frm_otros_positivos" id="frm_otros_positivos" class="mt-5" action="#" autocomplete="off">

                    <div class="form-row"  hidden="hidden">
                        <div class="form-group col-md-3">
                            <label for="casoIndice"><spring:message code="cases.index" /></label>
                            <input type="text" class="form-control" id="casoIndice" name="casoIndice" value="${caso.candidatoTransmisionCovid19.codigo}" readonly>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="editando"><spring:message code="editando" /></label>
                            <input type="text" class="form-control" id="editando" name="editando" value="${editando}" readonly>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="fecha_ingreso"><spring:message code="fecha_ingreso" /></label>
                            <input type="text" class="form-control" id="fecha_ingreso" name="fecha_ingreso" value="<fmt:formatDate value="${caso.candidatoTransmisionCovid19.fechaIngreso}" pattern="dd/MM/yyyy" />" readonly>
                        </div>

                        <div class="form-group col-md-3">
                            <label for="covid_participantes_casos"><spring:message code="covid_participantes_casos" /></label>
                            <input type="text" class="form-control" id="covid_participantes_casos" name="covid_participantes_casos" value="${caso.codigo}" readonly>
                        </div>

                    </div>

                                <div class="form-group row">
                                    <label for="idparticipante" class="col-sm-2 col-form-label"><spring:message code="code" />
                                        <span class="text-danger">*</span>
                                    </label>
                                    <div class="input-group col-md-10">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                        <input type="text" class="form-control" id="idparticipante" name="idparticipante" value="${caso.codigo_participante}"  readonly>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="estudio" class="col-sm-2 col-form-label"><spring:message code="study" />
                                        <span class="text-danger">*</span>
                                    </label>
                                    <div class="input-group col-md-10">
                                                <span class="input-group-addon">
													<i class="fa fa-list"></i>
												</span>
                                        <input type="text" class="form-control" id="estudio" name="estudio" readonly value="${caso.estActuales}">
                                    </div>
                                </div>

                                 <div class="form-group row">
                                    <label for="casaChf" class="col-sm-2 col-form-label"><spring:message code="chf.house" />
                                        <span class="text-danger">*</span>
                                    </label>
                                     <div class="input-group col-md-10">
                                                <span class="input-group-addon">
													<i class="fa fa-home"></i>
												</span>
                                         <input type="text" class="form-control" id="casaChf" name="casaChf" readonly value="${caso.casaCHF}">
                                     </div>
                                </div>

                                 <div class="form-group row">
                                    <label for="positivoPor" class="col-sm-2 col-form-label"><spring:message code="lbl.positive.by"/>
                                        <span class="text-danger">*</span>
                                    </label>
                                    <div class="col-sm-10">
                                        <select name="positivoPor" id="positivoPor" class="form-control" required="required">
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
                                    <label for="FIS" class="col-sm-2 col-form-label"><spring:message code="FIS" />
                                        <span class="text-danger">*</span>
                                    </label>
                                    <div class="input-group col-md-10">
                                                <span class="input-group-addon">
													 <i class="fa fa-clock-o" aria-hidden="true"></i>
												</span>
                                        <input name="fis" id="fis" class="form-control datepicker" type="text" data-date-end-date="+0d" required="required"
                                               value="<fmt:formatDate value="${caso.fis}" pattern="dd/MM/yyyy" />"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="fif" class="col-sm-2 col-form-label"><spring:message code="fif" /></label>
                                    <div class="input-group col-md-10">
                                                <span class="input-group-addon">
													 <i class="fa fa-clock-o" aria-hidden="true"></i>
												</span>
                                        <input name="fif" id="fif" class="form-control datepicker" type="text" data-date-end-date="+0d"
                                               value="<fmt:formatDate value="${caso.fif}" pattern="dd/MM/yyyy" />"/>
                                    </div>
                                </div>
                    <br/>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label"></label>
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary btn-lg btn-ladda float-left" data-style="expand-right">
                                            <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>

                                        <a href="${fn:escapeXml(listaUrl)}" class="btn btn-warning btn-lg btn-ladda float-right" data-style="expand-right">
                                            <i class="fa fa-minus-circle" aria-hidden="true"></i> <spring:message code=" cancel" /></a>
                                    </div>
                                </div>
                </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
                <div>
                    <div class="col-md-12">
                        <!-- Mail list-->
                        <div class="table-responsive">
                        <table id="otrosPositivos" class="table table-hover table-bordered dt-responsive nowrap" style="width:100%">
                            <thead>
                            <tr>
                                <th  hidden="hidden"  class="text-center"  width="12%"><spring:message code="#" /></th>
                                <th class="text-center" width="12%"><spring:message code="candidate" /></th>
                                <th class="text-center" width="12%"><spring:message code="chf.house" /></th>
                                <th class="text-center" width="12%"><spring:message code="logindate" /></th>
                                <th class="text-center" width="12%"><spring:message code="lbl.positive.by" /></th>
                                <th class="text-center" width="12%"><spring:message code="FIS" /></th>
                                <th class="text-center" width="12%"><spring:message code="fif" /></th>
                                <th class="text-center" width="12%"><spring:message code="disable" /></th>
                                <th class="text-center" width="16%"><spring:message code="actions" /></th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- row -->
                            <c:forEach items="${ListOtrosPositivos}" var="l">
                                <spring:url value="/super/covid/otrosPositivosCovid" var="addOtrosPositivosUrl"/>
                                <spring:url value="/super/covid/setPasive" var="setPasivoUrl"/>
                                <spring:url value="/super/covid/editOtherPositivo/{codigo}"
                                            var="editOtherPositivoUrl">
                                    <spring:param name="codigo" value="${l.codigo}" />
                                </spring:url>
                                <spring:url value="/super/covid/setPasive/{codigo}"
                                            var="pasiveUrl">
                                    <spring:param name="codigo" value="${l.codigo}*${l.candidatoTransmisionCovid19.codigo}" />
                                </spring:url>
                                <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                                <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
                                <tr>
                                    <c:choose>
                                        <c:when  test = "${l.pasive eq '1'}">
                                            <td hidden="hidden" class="text-center">
                                                <span class="text-danger" style="text-decoration:line-through;"><c:out value="${l.codigo}" /></span>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td hidden="hidden" class="text-center"><c:out value="${l.codigo}" /></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when  test ="${l.pasive eq '1'}">
                                            <td class="text-center"> <span class="text-danger" style="text-decoration:line-through;"> <c:out value="${l.codigo_participante}" />  </span> </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center"> <span> <c:out value="${l.codigo_participante}" />  </span> </td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when  test = "${l.pasive eq '1'}">
                                            <td class="text-center"> <span class="text-danger" style="text-decoration:line-through;"> <c:out value="${l.casaCHF}" />  </span> </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center"><c:out value="${l.casaCHF}" /></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when  test ="${l.pasive eq '1'}">
                                            <td class="text-center"> <span class="text-danger" style="text-decoration:line-through;"> <fmt:formatDate value="${l.recordDate}" pattern="dd/MM/yyyy" />  </span> </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center"><fmt:formatDate value="${l.recordDate}" pattern="dd/MM/yyyy" /></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when  test = "${l.pasive eq '1'}">
                                            <td class="text-center"> <span class="text-danger" style="text-decoration:line-through;">
                                                <c:forEach items="${positivoPor}" var="cat">
                                                    <c:if test="${cat.catKey eq l.positivoPor}">
                                                       <span class="text-danger" style="text-decoration:line-through;"> <c:out value="${cat.spanish}" /> </span>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center">
                                                <c:forEach items="${positivoPor}" var="cat">
                                                    <c:if test="${cat.catKey eq l.positivoPor}">
                                                        <c:out value="${cat.spanish}" />
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when  test = "${l.pasive eq '1'}">
                                            <td class="text-center"> <span class="text-danger" style="text-decoration:line-through;">
                                                <fmt:formatDate value="${l.fis}" pattern="dd/MM/yyyy" /> </span>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center"><fmt:formatDate value="${l.fis}" pattern="dd/MM/yyyy" /></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when  test = "${l.pasive eq '1'}">
                                            <td class="text-center"> <span class="text-danger" style="text-decoration:line-through;">
                                                <fmt:formatDate value="${l.fif}" pattern="dd/MM/yyyy" /> </span>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center"><fmt:formatDate value="${l.fif}" pattern="dd/MM/yyyy" /></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test = "${l.pasive eq '1'}">
                                            <td class="text-center"><span class="badge badge-danger"><spring:message code="SI" /></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-center"><span class="badge badge-success"><spring:message code="NO" /></span></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <td align="center">
                                        <c:choose>
                                            <c:when test="${l.pasive eq '1'}">
                                                <button disabled="disabled" title="<spring:message code="edit" />" class="btn btn-outline-warning btn-sm" ><i class="fa fa-edit"></i></button>
                                                <button disabled="disabled" title="<spring:message code="disable" />" class="btn btn-outline-danger btn-sm" ><i class="fa fa-trash-o"></i></button>
                                            </c:when>
                                            <c:otherwise>
                                                <a title="<spring:message code="edit" />" href="${fn:escapeXml(editOtherPositivoUrl)}" class="btn btn-outline-warning btn-sm"><i class="fa fa-edit"></i></a>
                                                <button id="swalPasivo" title="<spring:message code="Eliminar" />" data-toggle="tooltip" data-placement="bottom" class="btn btn-danger btn-sm swalPasivo">
                                                    <i class="fa fa-trash text-white" aria-hidden="true"></i></button>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            <!-- row -->
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
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../../fragments/bodyFooter.jsp" />
<jsp:include page="../../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />
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
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/sweetalert.js" var="sweet" />
<script type="text/javascript" src="${sweet}"></script>
<script>
    $(document).ready(function(){
        $(".datepicker").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });

        var parametro = {
            saveOrUpdateUrl         : "${saveOrUpdateUrl}",
            setPasivoUrl            : "${setPasivoUrl}",
            successmessage          : "${successMessage}",
            error                   : "${errorProcess}",
            otherPositiveUrl        : "${otherPositiveUrl}"
        };

        var table = $('#otrosPositivos').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });

        $('#otrosPositivos thead tr').clone(true).appendTo( '#otrosPositivos thead' );
        $('#otrosPositivos thead tr:eq(1) th').each( function (i) {
            var title = $(this).text();
            $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
            $( 'input', this ).on( 'keyup change', function () {
                if ( table.column(i).search() !== this.value ) {
                    table.column(i).search( this.value ).draw();
                }
            });
        });

        var form = $('#frm_otros_positivos');
        form.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                positivoPor: {
                    required: true
                },
                idparticipante: {
                    required: true
                },
                fis: {
                    required: true
                },
                casaChf: {
                    required: true
                },
                estudio: {
                    required: true
                }
            },
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    //error.insertAfter( element ); //cuando no es input-group
                    error.insertAfter(element.parent('.input-group'));
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
               EditOtrosPositivos();
            }
        });

        function EditOtrosPositivos(){
            $.post(parametro.saveOrUpdateUrl, form.serialize(), function( data ){
                registro = JSON.parse(data);
                if(registro.msj != null){
                    toastr.warning(registro.msj,"Advertencia!", {timeOut:6000});
                } else if(registro.codigo != undefined ){
                    toastr.success(parametro.successmessage);
                    window.setTimeout(function () {
                        window.location.href= parametro.otherPositiveUrl;
                    }, 1500);
                }
            },'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error(errorThrown,"ERROR:",{timeOut:6000} );
            });
        }

        $("#otrosPositivos").on("click",'.swalPasivo', function(){
            var currentRow = $(this).closest("tr");
            var col0 = currentRow.find("td:eq(0)").text();
            var col1 = currentRow.find("td:eq(1)").text();
            var col3 = currentRow.find("td:eq(3)").text();
            ponerPasivo(col0,col1,col3);
        });

        function ponerPasivo(colmn0,column1, column3){
            swal({
                        title: "Desactivar? ",
                        text: "Registro: " + column1 + " con Fecha: " + column3,
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Si, Desactivar!",
                        cancelButtonText: "No, Desactivar plx!",
                        closeOnConfirm: true,
                        closeOnCancel: true
                    },
                    function (isConfirm) {
                        if (isConfirm) {
                            $.post(parametro.setPasivoUrl, {codigo: colmn0, ajax: 'true'}, function (data) {
                                response = JSON.stringify(data);
                                registro = JSON.parse(response);
                                if(registro.msj != null){
                                    swal("Error!", registro.msj, "error");
                                }else{
                                    swal("Desactivado!", "con éxito!", "success");
                                    setTimeout(function () {
                                        window.location.href= parametro.otherPositiveUrl;
                                     }, 1000);
                                }

                            }).fail(function () {
                                setTimeout(function () {
                                    swal("Error!", "Servidor no respode!", "error");
                                }, 2000);
                            });
                        } else {
                            swal("Cancelado!", "Registro está seguro! :)", "error");
                        }
                    });
        }
    });
</script>

</body>
</html>
