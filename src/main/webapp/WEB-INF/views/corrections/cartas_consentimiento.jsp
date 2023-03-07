<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/11/2022
  Time: 03:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />

    <spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
    <link href="${uiCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

    <style>
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
            background-color: #008cba;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #008cba;
        }
        .form-control-feedback {
            margin-top: 0.25rem;
            width: 16%;
            text-align: center;
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
                <a href="<spring:url value="/correction/CorrectionForm/" htmlEscape="true "/>"> <spring:message code="corrections" /> <spring:message code="App" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="container col-md-12 col-lg-12">
                <div class="row">
                <div class="col-md-12">
                <div class="card">
                <div class="card-body bg-primary text-white mailbox-widget pb-0">
                    <h2 class="text-white pb-3"> <spring:message code="corrections" />  <spring:message code="letters" /> <spring:message code="consent" /> <spring:message code="App" />  </h2>
                    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                                <span class="d-none d-md-block"><spring:message code="search" /> <spring:message code="participant" /></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                                <span class="d-block d-md-none"><i class="ti-export"></i></span>
                                <span class="d-none d-md-block"><spring:message code="Form" /> <spring:message code="corrections" /> <spring:message code="App" /></span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
                <div>
                <div class="p-4 no-gutters align-items-center">
                    <spring:url value="/correction/searchParticipant" var="searchPartesUrl"></spring:url>
                    <spring:url value="/correction/VerConsentimiento" var="verConsentUrl"></spring:url>
                    <spring:url value="/correction/saveCarta" var="saveCartaUrl"/>
                    <spring:url value="/correction/CorrectionForm" var="CorrectionFormUrl"/>
                    <spring:url value="/cartas/getNombre1" var="getNombre1Url"/>
                    <spring:url value="/cartas/getNombre2" var="getNombre2Url"/>
                    <spring:url value="/cartas/getApellido1" var="getApellido1Url"/>
                    <spring:url value="/cartas/getApellido2" var="getApellido2Url"/>
                    <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                    <form action="#" autocomplete="off" name="select-participante-form" id="select-participante-form" class="form-horizontal" novalidate="novalidate">
                        <div class="form-group row">
                            <div class="input-group col-md-12">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input id="parametro" name="parametro" type="text" placeholder="<spring:message code="participant.code" />" class="form-control">
                                <button id="buscar" type="submit" class="btn btn-success btn-ladda ladda-button" data-style="expand-right"><span class="ladda-label">
                                            <i class="fa fa-search" aria-hidden="true"></i>
                                             <spring:message code="search" />
                                        </span><span class="ladda-spinner"></span>
                                </button>
                            </div>
                        </div>
                    </form>

                </div>
                <!-- Mail list-->
                <div class="table-responsive-xl">
                    <table id="tableCartParticipnt" class="table email-table no-wrap table-hover v-middle mb-0 font-14"  style="width:100%">
                        <thead>
                        <tr>
                            <th class="text-center"><spring:message code="code"/></th>
                            <th data-hide="phone,tablet" class="text-center"><spring:message code="lbl.names.surnames"/></th>
                            <th data-hide="phone,tablet" class="text-center"><spring:message code="letters" /></th>
                            <th data-hide="phone,tablet" class="text-center"><spring:message code="version" /></th>
                            <th data-hide="phone,tablet" class="text-center"><spring:message code="dateAdded" /></th>
                            <th data-hide="phone,tablet" class="text-center"><spring:message code="username" /></th>
                            <th data-hide="phone,tablet" class="text-center"><spring:message code="actions" /></th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                </div>
                </div>
                <div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
                    <div class="container col-md-12 col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <spring:message code="lbl.personal.data"/> <spring:message code="lbl.witness"/>
                            </div>
                            <div class="card-body">
                                <form id="formCartaApp" name="formCartaApp" autocomplete="off" novalidate>

                                    <input type="hidden" class="form-control" id="codigo" name="codigo">
                                    <input type="hidden" class="form-control" id="participanteCode" name="participanteCode">

                                    <div class="form-row">
                                        <div class="form-group col-md-12">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="testigoPresente" name="testigoPresente">
                                                <label class="form-check-label" for="testigoPresente">
                                                    <spring:message code="lbl.witness.present" />
                                                </label>
                                            </div>
                                        </div>

                                        <div class="form-group col-md-3">
                                            <label for="nombre1Testigo"> <spring:message code="first.name"/> <spring:message code="lbl.witness"/></label>
                                            <input type="text" class="form-control" id="nombre1Testigo" name="nombre1Testigo" readonly >
                                        </div>

                                        <div class="form-group col-md-3">
                                            <label for="nombre2Testigo"><spring:message code="second.name"/> <spring:message code="lbl.witness"/></label>
                                            <input type="text" class="form-control" id="nombre2Testigo" name="nombre2Testigo" readonly>
                                        </div>
                                        <div class="form-group col-md-3">
                                            <label for="apellido1Testigo"><spring:message code="first.surname"/> <spring:message code="lbl.witness"/></label>
                                            <input type="text" class="form-control" id="apellido1Testigo" name="apellido1Testigo" readonly>
                                        </div>
                                        <div class="form-group col-md-3">
                                            <label for="apellido2Testigo"><spring:message code="second.surname"/> <spring:message code="lbl.witness"/></label>
                                            <input type="text" class="form-control" id="apellido2Testigo" name="apellido2Testigo" readonly>
                                        </div>
                                    </div>

                                    <div class="card">
                                        <div class="card-header">
                                            <spring:message code="lbl.correction.tutor" />
                                        </div>
                                        <div class="card-body">
                                            <div class="form-row">

                                                <div class="form-group col-md-6">
                                                    <div class="form-check mt-4">
                                                        <input class="form-check-input" type="checkbox" id="mismoTutor" name="mismoTutor">
                                                        <label class="form-check-label" for="mismoTutor">
                                                            <spring:message code="Mismo" /> <spring:message code="lbl.tutor" />?
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="form-group col-md-6">
                                                    <label for="relacionFamiliarTutor"> <spring:message code="family.relationship"/></label>
                                                    <select id="relacionFamiliarTutor" name="relacionFamiliarTutor" class="form-control">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${relFam}" var="rf">
                                                            <option value="${rf.catKey}">${rf.catKey} - ${rf.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            <div class="form-group col-md-3">
                                                <label for="NombreTutor1"> <spring:message code="first.name"/> <spring:message code="lbl.tutor"/></label>
                                                <input type="text" class="form-control" id="NombreTutor1" name="NombreTutor1">
                                            </div>

                                            <div class="form-group col-md-3">
                                                <label for="NombreTutor2"> <spring:message code="second.name"/> <spring:message code="lbl.tutor"/></label>
                                                <input type="text" class="form-control" id="NombreTutor2" name="NombreTutor2">
                                            </div>
                                            <div class="form-group col-md-3">
                                                <label for="ApellidoTutor1"> <spring:message code="first.surname"/> <spring:message code="lbl.tutor"/></label>
                                                <input type="text" class="form-control" id="ApellidoTutor1" name="ApellidoTutor1">
                                            </div>

                                            <div class="form-group col-md-3">
                                                <label for="ApellidoTutor2"> <spring:message code="second.surname"/> <spring:message code="lbl.tutor"/></label>
                                                <input type="text" class="form-control" id="ApellidoTutor2" name="ApellidoTutor2">
                                            </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="card">
                                    <div class="card-header">
                                        <spring:message code="Information"/>  <spring:message code="letters"/>
                                    </div>
                                        <div class="card-block">
                                            <div class="form-row">
                                                <div class="form-group col-md-2">
                                                    <label for="carta">  <spring:message code="letters"/>    </label>
                                                    <input type="text" class="form-control" id="carta" readonly>
                                                </div>

                                                <div class="form-group col-md-2">
                                                    <label for="version"> <spring:message code="version"/></label>
                                                    <input type="text" class="form-control" id="version" readonly>
                                                </div>

                                                <div class="form-group col-md-4">
                                                    <label for="fechaCarta"><spring:message code="lbl.letters.start.date"/>  </label>
                                                    <input type="text" class="form-control date-picker" id="fechaCarta" name="fechaCarta" data-date-end-date="+0d">
                                                </div>

                                                <div class="form-group col-md-4">
                                                    <label for="contactoFuturo"> <spring:message code="lbl.Accept.future.contact"/></label>
                                                    <select id="contactoFuturo" name="contactoFuturo" class="form-control">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${catSiNo}" var="sn">
                                                            <option value="${sn.catKey}"> ${sn.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>

                                            <div class="form-row">
                                                <div class="form-group col-md-4">
                                                    <label for="parteA">Parte A</label>
                                                    <select id="parteA" name="parteA" class="form-control parte">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${catSiNo}" var="sn">
                                                            <option value="${sn.catKey}"> ${sn.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="parteB">Parto B</label>
                                                    <select id="parteB" name="parteB" class="form-control parte">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${catSiNo}" var="sn">
                                                            <option value="${sn.catKey}"> ${sn.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="parteC">Parte C</label>
                                                    <select id="parteC" name="parteC" class="form-control parte">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${catSiNo}" var="sn">
                                                            <option value="${sn.catKey}"> ${sn.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>

                                            <div class="form-row">

                                                <div class="form-group col-md-3">
                                                    <label for="parteD">Parte D</label>
                                                    <select id="parteD" name="parteD" class="form-control parte">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${catSiNo}" var="sn">
                                                            <option value="${sn.catKey}"> ${sn.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-3">
                                                    <label for="parteE">Parte E</label>
                                                    <select id="parteE" name="parteE" class="form-control parte">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${catSiNo}" var="sn">
                                                            <option value="${sn.catKey}"> ${sn.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="form-group col-md-3">
                                                    <label for="parteF">Parte F</label>
                                                    <select id="parteF" name="parteF" class="form-control parte">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${catSiNo}" var="sn">
                                                            <option value="${sn.catKey}"> ${sn.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="form-group col-md-3">
                                                    <label for="parteG">Parte G</label>
                                                    <select id="parteG" name="parteG" class="form-control parte">
                                                        <option selected value=""><spring:message code="select"/>...</option>
                                                        <c:forEach items="${catSiNo}" var="sn">
                                                            <option value="${sn.catKey}"> ${sn.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>


                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <button type="submit" class="btn btn-primary  btn-block btn-lg"> <i  class="fa fa-refresh"></i> <spring:message code="lbl.update" /></button>
                                        </div>
                                        <div class="form-group col-md-4"></div>
                                        <div class="form-group col-md-4">
                                            <a href="${fn:escapeXml(CorrectionFormUrl)}" class="btn btn-warning btn-block btn-lg btn-ladda" data-style="expand-right">
                                                <i class="fa fa-ban" aria-hidden="true"></i>
                                                <spring:message code="cancel" />
                                            </a>
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

            </div>
        </div>
        <!-- /.conainer-fluid -->
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
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>

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

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/views/corrections/cartaConsentimientoCorreccion.js" var="CorrectionJS" />
<script type="text/javascript" src="${CorrectionJS}"></script>
<script>
    $(document).ready(function(){
        $("#fechaCarta").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });
        $("#contactoFuturo").select2();
        $("#relacionFamiliarTutor").select2();
        $(".parte").select2();
        parametros = {
            "searchPartesUrl": "${searchPartesUrl}",
            "verConsentUrl"  : "${verConsentUrl}",
            "dataTablesLang" : "${dataTablesLang}",
            "successmessage" : "${successMessage}",
            "saveCartaUrl"   : "${saveCartaUrl}",
            "CorrectionFormUrl" : "${CorrectionFormUrl}",
            getNombre1Url       : "${getNombre1Url}",
            getNombre2Url       : "${getNombre2Url}",
            getApellido1Url     : "${getApellido1Url}",
            getApellido2Url     : "${getApellido2Url}"
        };
        cartaConsentimiento.init(parametros);
    });
    $("#parametro").focus();
</script>
</body>
</html>
