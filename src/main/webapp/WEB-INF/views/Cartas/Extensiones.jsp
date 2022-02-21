<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 21/05/2021
  Time: 08:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <jsp:include page="../fragments/headTag.jsp"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss"/>
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
            border-top: 1px solid rgba(120, 130, 140, .13);
        }

        .font-light {
            font-weight: 300;
        }

        .box {
            color: #fff;
            padding: 20px;
            display: none;
            margin-top: 20px;
        }

        .red {
            background: #ff0000;
        }

        .green {
            background: #228B22;
        }

        .blue {
            background: #0000ff;
        }

        /**/

        .main-box.no-header {
            padding-top: 20px;
        }

        .main-box {
            background: #FFFFFF;
            -webkit-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -moz-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -o-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -ms-box-shadow: 1px 1px 2px 0 #CCCCCC;
            box-shadow: 1px 1px 2px 0 #CCCCCC;
            margin-bottom: 16px;
            -webikt-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }

        .table a.table-link.danger {
            color: #e74c3c;
        }

        .label {
            border-radius: 3px;
            font-size: 0.875em;
            font-weight: 600;
        }

        .user-list tbody td .user-subhead {
            font-size: 0.875em;
            font-style: italic;
        }

        .user-list tbody td .user-link {
            display: block;
            font-size: 1.25em;
            padding-top: 3px;
            margin-left: 60px;
        }

        a {
            color: #3498db;
            outline: none !important;
        }

        .user-list tbody td > img {
            position: relative;
            max-width: 50px;
            float: left;
            margin-right: 15px;
        }

        .table thead tr th {
            text-transform: uppercase;
            font-size: 0.875em;
        }

        .table thead tr th {
            border-bottom: 2px solid #e7ebee;
        }

        .table tbody tr td:first-child {
            font-size: 1.125em;
            font-weight: 300;
        }

        .table tbody tr td {
            font-size: 0.875em;
            vertical-align: middle;
            border-top: 1px solid #e7ebee;
            padding: 12px 8px;
        }

        a:hover {
            text-decoration: none;
        }

        .alert-dark {
            color: #1b1e21;
            background-color: #d6d8d9;
            border-color: #c6c8ca;
        }
        .form-control:disabled, .daterangepicker .input-mini:disabled, .input-group > .ui-select-bootstrap > input.ui-select-search.form-control:disabled, .form-control[readonly], .daterangepicker [readonly].input-mini, .input-group > .ui-select-bootstrap > input[readonly].ui-select-search.form-control {
            background-color: #e9ebec00;
            opacity: 1;
        }

        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 5px solid #fff
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
<spring:url value="/cartas/ListadoCartaParticipant" var="listCartaUrl"></spring:url>
<spring:url value="/cartas/saveExtensCarta" var="saveExtensCartaUrl"></spring:url>
<spring:url value="/cartas/deletExt" var="delUrl"></spring:url>
<!-- Breadcrumb -->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home"/></a>
        <i class="fa fa-angle-right"></i>
        <a href="${fn:escapeXml(listCartaUrl)}"><spring:message code="Volver"/></a>
        <i class="fa fa-angle-right"></i>
    </li>
</ol>

<c:set var="successMessage"><spring:message code="process.success"/></c:set>
<c:set var="errorProcess"><spring:message code="process.error"/></c:set>
<div class="container-fluid">
<div class="animated fadeIn">

<div class="">
<div class="row">
<div class="col-md-12 col-lg-12 col-xl-12">
<div class="card">
<div class="card-body bg-primary text-white mailbox-widget pb-0">
    <h2 class="text-white ml-1 pb-3 mt-3" style="font-family: Roboto"><spring:message code="Extension"/> <spring:message code="letters"/></h2>
    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab"
               aria-selected="true">
                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                <span class="d-none d-md-block"> <spring:message code="Form"/></span>
            </a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
<div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
<div>

    <!-- Form -->
   <form action="#" name="formExt" id="formExt" method="post" role="form" autocomplete="off">
    <div class="row" hidden="hidden">
        <div class="col-md-4">
            <div class="form-group">
                <label for="idParticipanteCarta">idParticipanteCarta</label>
                <input type="text" class="form-control" id="idParticipanteCarta" name="idParticipanteCarta"
                       value="${idParticipanteCarta}">
            </div>
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <label for="idVersion">idVersion</label>
                <input type="text" class="form-control" id="idVersion" name="idVersion"
                       value="${idVersion}">
            </div>
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <label for="accion">accion</label>
                <input type="text" class="form-control" id="accion" name="accion" value="${editando}">
            </div>
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <label for="idParticipante">idParticipante</label>
                <input type="text" class="form-control" id="idParticipante" name="idParticipante"
                       value="${idParticipante}">
            </div>

        </div>
        <div class="col-md-2">
        <div class="form-group">
                <label for="idParticipante">idParticipantExtension</label>
                <input type="text" class="form-control" id="idParticipantExtension"
                       name="idParticipantExtension" value="${caso.idParticipantExtension}"/>
            </div>
        </div>
    </div>

    <div class="row">

        <div class="col-md-6">
            <div class="form-group">
                <label for="nombreCompleto"><spring:message code="lbl.names.surnames"/></label>
                <input type="text" class="form-control" disabled name="nombreCompleto" id="nombreCompleto" value="${nombreCompleto}"/>
            </div>
        </div>

        <div class="col-md-3">
            <div class="form-group">
                <label for="estudio"><spring:message code="study"/>:</label>
                <input type="text" class="form-control" disabled name="estudio" id="estudio" value="${estudio}"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label for="version"><spring:message code="version"/>:</label>
                <input type="text" class="form-control" disabled name="version" id="version" value="${version}"/>
            </div>
        </div>

        <div class="col-md-4">
            <div class="form-group">
                <label for="fechaExtension"><spring:message code="dateAdded"/> <spring:message code="letters"/>:</label>
                <span class="required text-danger"> * </span>
                <input type="text" class="form-control" required="required" name="fechaExtension" id="fechaExtension" data-date-end-date="+0d"
                       value="<fmt:formatDate value="${caso.fechaExtension}" pattern="dd/MM/yyyy" />"/>
            </div>
        </div>

        <div class="col-md-4">
            <div class="form-group">
                <label for="idExtension"><spring:message code="Extension"/></label>
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
                    <spring:message code="Extension"/> <spring:message code="lbl.required" />
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="form-group">
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

        <div class="col-md-3">
            <div class="form-group">
                <label for="nombre1tutor"><spring:message code="first.name" /> <spring:message code="lbl.tutor" />: </label>
                <span class="required text-danger"> * </span>
                <input type="text" class="form-control focusNext" tabindex="1" id="nombre1tutor" name="nombre1tutor" value="${nombre1Tutor}" required/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label for="nombre2tutor"><spring:message code="second.name" /> <spring:message code="lbl.tutor" />:</label>
                <input type="text" class="form-control focusNext" tabindex="2" id="nombre2tutor" name="nombre2tutor" value="${nombre2Tutor}">
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label for="apellido1tutor"><spring:message code="first.surname" /> <spring:message code="lbl.tutor" />:</label>
                <span class="required text-danger"> * </span>
                <input type="text" class="form-control focusNext" tabindex="3" id="apellido1tutor" required name="apellido1tutor" value="${apellido1Tutor}">
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label for="apellido2tutor"><spring:message code="second.surname" /> <spring:message code="lbl.tutor" />:</label>
                <input type="text" class="form-control focusNext" tabindex="4" id="apellido2tutor" name="apellido2tutor" value="${apellido2Tutor}">
            </div>
        </div>

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
                           <input type="checkbox" checked="checked" id="chktestigo" name="chktestigo"  class="chktestigo" />
                       </c:when>
                       <c:otherwise>
                           <input type="checkbox" id="chktestigo" name="chktestigo"  class="chktestigo"/>
                       </c:otherwise>
                   </c:choose>
                   <label class="form-check-label" for="chktestigo">
                       <spring:message code="lbl.witness.present" />
                   </label>
               </div>
           </div>
    </div>

    <div id="showDivTestigo" style="display: none">
        <div class="card w-100">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombre1Testigo"><spring:message code="first.name" /> <spring:message code="lbl.witness" />: </label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control focusNext" tabindex="5" id="nombre1Testigo"
                                   name="nombre1Testigo" value="${caso.nombre1Testigo}"/>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombre2Testigo"><spring:message code="second.name" /> <spring:message code="lbl.witness" />:</label>
                            <input type="text" class="form-control focusNext" tabindex="6" id="nombre2Testigo"
                                   name="nombre2Testigo" value="${caso.nombre2Testigo}">
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="apellido1Testigo"><spring:message code="first.surname" /> <spring:message code="lbl.witness" />:</label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control focusNext" tabindex="7" id="apellido1Testigo"
                                   name="apellido1Testigo" value="${caso.apellido1Testigo}">
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="apellido2Testigo"><spring:message code="second.surname" /> <spring:message code="lbl.witness" />:</label>
                            <input type="text" class="form-control focusNext" tabindex="8" id="apellido2Testigo"
                                   name="apellido2Testigo" value="${caso.apellido2Testigo}">
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="form-group">
                <label for="observacion"><spring:message code="observacion" />:</label>
                <textarea name="observacion" id="observacion" cols="10" rows="3" class="form-control focusNext"
                          tabindex="9">${caso.observacion}</textarea>
            </div>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-md-4">
            <button class="btn btn-primary btn-block btn-lg focusNext btn-ladda" data-style="expand-right"
                    tabindex="10" type="submit">
                <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save"/>
            </button>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <a href="${fn:escapeXml(listCartaUrl)}" data-toggle="tooltip" title="Volver" data-placement="top"
               class="btn btn-warning btn-block btn-lg btn-ladda" data-style="expand-right">
                <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                <spring:message code="cancel"/>
            </a>
        </div>
    </div>
    </form>
    <!-- fin Form -->
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
<jsp:include page="../fragments/bodyFooter.jsp"/>
<jsp:include page="../fragments/corePlugins.jsp"/>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App"/>
<script src="${App}" type="text/javascript"></script>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />

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

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs"/>
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs"/>
<script src="${loadingButtonsJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs"/>
<script src="${validateAMJs}" type="text/javascript"></script>


<spring:url value="/resources/js/libs/select2.min.js" var="selectJs"/>
<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/moment.js" var="moment"/>
<script type="text/javascript" src="${moment}"></script>
<spring:url value="/resources/js/libs/sweetalert.js" var="sweet"/>
<script type="text/javascript" src="${sweet}"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin"/>
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/views/Cartas/Extensiones.js" var="extensioneScript"/>
<script src="${extensioneScript}" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        var table = $('#tblextension').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }, columnDefs: [{
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                },{
                    "targets": [2],
                    "visible": false,
                    "searchable": false
                }
            ]
        });
        var parametrosUrl = {
            successmessage: "${successMessage}",
            error: "${errorProcess}",
            saveExtensCarta: "${saveExtensCartaUrl}",
            listCartaUrl: "${listCartaUrl}",
            delUrl: "${delUrl}"
        };
        saveExtensiones.init(parametrosUrl);

        $("#tblextension").on('click', '.desact', function () {
            var currentRow = $(this).closest("tr");
            var id = $(this).value;
            var idParticipantExtension = currentRow.find("td:eq(0)").text();
            var col2 = currentRow.find("td:eq(1)").text();
            var col3 = currentRow.find("td:eq(2)").text();
            $.getJSON(parametrosUrl.disableUrl, {idParticipantExtension: idParticipantExtension, ajax: 'true'}, function (data) {
                console.log(data);
            });
        });

        var fechaNow = moment().format("DD/MM/YYYY");
        $("#fechaExtension").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn: true
        }).val(fechaNow);
        $("#idExtension").select2();
        $("#person").select2();
        $("#relfam").select2();

        $("#chktestigo").click(function () {
            $("#nombTestigo").focus();
            if ($(this).is(":checked")) {
                $("#showDivTestigo").fadeIn("slow");
                $("#nombre1Testigo").attr("required", "true");
                $("#apellido1Testigo").attr("required", "true");
            } else {
                $("#showDivTestigo").fadeOut("slow");
                $("#nombre1Testigo").val("").attr("required", "false");
                $("#apellido1Testigo").val("").attr("required", "false");
            }
        });

        function verExtensiones(id) {
            var $ele = $("#extensiones");
            $ele.val([]).trigger('change');
            $.getJSON(urls.VerExtensionUrl, {idversion: id, ajax: 'true'}, function (data) {
                console.log(data);
                $ele.empty();
                if (data.length > 0) {
                    bandera = true;
                    $ele.append($('<option/>').val("").text('Seleccione...'));
                    $.each(data, function (i, val) {
                        $ele.append($('<option/>').val(val.id).text(val.extension));
                    })
                } else {
                    $ele.empty();
                }
            });
        }

        $('body').delegate('.chktestigo', 'lcs-statuschange', function () {
            var status = ($(this).is(':checked')) ? 'checked' : 'unchecked';
            if (status == 'checked') {
                $("#showDivTestigo").fadeIn("slow");
                $("#nombre1Testigo").attr("required", "true");
                $("#apellido1Testigo").attr("required", "true");
            } else {
                $("#showDivTestigo").fadeOut("slow");
                $("#nombre1Testigo").val("").attr("required", "false");
                $("#nombre2Testigo").val("").attr("required", "false");
                $("#apellido1Testigo").val("").attr("required", "false");
                $("#apellido2tutor").val("").attr("required", "false");
            }
            $("#nombre1Testigo").focus();
        });
        /*VerificaTestigo();
        function VerificaTestigo() {
            debugger;
            if ($('#chktestigo').prop('checked')) {
                $("#selectt").fadeIn("slow");
            }
        }*/

        mostrarTestigo();
        function mostrarTestigo(){
            debugger;
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

        $('#tblextension').on('click', '.deleteExt', function(e) {
            e.preventDefault();
            var currentRow = $(this).closest("tr");
            var row = $(this).parents('tr');
            var column00 = table.row(currentRow).data()[0];
            var column01 = table.row(currentRow).data()[1];
            var column02 = table.row(currentRow).data()[3];
            swal({
                   title: "Anular? ",
                   text: "Extension: " + column02 + " con Fecha: " + column01,
                   type: "warning",
                   showCancelButton: true,
                   confirmButtonClass: "btn-danger",
                   confirmButtonText: "Si, Anular!",
                   cancelButtonText: "No, Anules plx!",
                   closeOnConfirm: false,
                   closeOnCancel: false
                 },
                    function(isConfirm) {
                        if (isConfirm) {
                            $.post(parametrosUrl.delUrl, { idParticipantExtension: column00, ajax: 'true'}).done(function(data) {
                                debugger;
                                console.log(data);
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

        const id = $("#idParticipanteCarta").val();
        //toastr.info("Codigo Carta: " + id, {timeOut: 5000});

    });
</script>
</body>
</html>
