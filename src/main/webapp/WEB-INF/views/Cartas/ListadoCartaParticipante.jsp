<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

    <style>
        .dropdown-item2 {
            display: block;
            cursor: pointer;
            padding: .25rem 1.5rem;
            clear: both;
            font-weight: 400;
            color: #212529;
            text-align: inherit;
            white-space: nowrap;
            background-color: transparent;
            border: 0;
        }
        .dropdown-item2:hover {
            text-decoration: none;
            background-color: #f3f3f3;
        }
        .btn-primary{
            background-color: #6610f2 !important;
        }
        #parametro-error {
            margin-right: 685px;
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
                <a href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                <spring:message code="List"/>
            </a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="row">
                    <div class="container col-sm-12 col-md-12 col-lg-12">
                        <div class="card shadow bg-white rounded bg-white text-black-50" >
                            <div class="card-header">
                                <h5>  <i class="fa fa-clipboard" aria-hidden="true"></i>  <spring:message code="letters" /> <spring:message code="participant" /></h5>
                            </div>
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <div class="p-2 bd-highlight">
                                        <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="Asignar Carta" href="<spring:url value="/cartas/Crear" htmlEscape="true "/>">
                                            <i class="fa fa-file-text-o" aria-hidden="true"></i>
                                            <spring:message code="lbl.To.assign" /> <spring:message code="letters" />
                                        </a>
                                    </div>
                                    <div class="p-2 bd-highlight"></div>
                                    <div class="p-2 bd-highlight">
                                        <a class="btn btn-success btn-lg" data-toggle="tooltip" data-placement="bottom" title="Extensiones" href="<spring:url value="/cartas/ListExtension" htmlEscape="true "/>">
                                            <i class="fa fa-list-ol" aria-hidden="true"></i>
                                            <spring:message code="List" /> <spring:message code="Extension" />
                                        </a>
                                    </div>

                                </div>
                                <spring:url value="/Domicilio/searchParticipant" var="searchPartUrl"/>
                                <br/>
                                <div class="row">
                                    <div class="col-sm-2 col-md-2 col-lg-2"></div>
                                    <div class="col-sm-8 col-md-8 col-lg-8">
                                        <form action="#" autocomplete="off" id="select-participante-form" class="form-horizontal">
                                            <div class="form-group row">
                                                <label class="form-control-label col-md-2" for="username"><spring:message code="participant.code" />
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
                                    <div class="col-sm-2 col-md-2 col-lg-2"></div>
                                </div>
                                <div class="col-md-12">
                                    <div class="table-responsive" style="min-height: 400px;">
                                        <table id="tableCartParticipnt" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                            <thead>
                                            <tr>
                                                <th class="text-center"><spring:message code="id" /></th>
                                                <th class="text-center"><spring:message code="code" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="lbl.names.surnames"/></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="letters" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="version" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="dateAdded" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="lbl.invalid" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="why.invalid" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="actions" /></th>
                                              <!--  <th data-hide="phone,tablet" class="text-center"><spring:message code="reports" /></th>
                                                 <th data-hide="phone,tablet" class="text-center"><spring:message code="Extension" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Editar" /></th>-->
                                            </tr>
                                            </thead>
                                            <tbody class="fixed-table-body"></tbody>
                                        </table>
                                    </div>
                                </div>
                            </div> <!-- fin card body -->
                        </div>  <!-- fin card body -->
                    </div>
                </div>
                <spring:url value="/cartas/GetCartasParticipante" var="GetCartasParticipanteUrl"/>
                <spring:url value="/cartas/ListadoCartaParticipant" var="Lista2AllUrl"/>
                <spring:url value="/cartas/VerParteCarta/" var="searchPartesUrl"></spring:url>
                <spring:url value="/cartas/EditCarta" var="EditCartaUrl"></spring:url>
                <spring:url value="/cartas/VerExtension" var="VerExtensionUrl"></spring:url>
                <spring:url value="/cartas/UpdateRetiro" var="UpdateRetiroUrl"/>
                <spring:url value="/reportes/ReporteCarta/" var="pdfCartaUrl"/>
                <spring:url value="/cartas/MasCartas" var="MasCartaUrl"/>
                <spring:url value="/cartas/extension" var="extensionUrl"/>
                <spring:url value="/cartas/updateDetalleParte" var="updateDetalleParteUrl"/>
                <spring:url value="/cartas/Delete" var="DeleteUrl"/>
                <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" data-backdrop="static" data-keyboard="false" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-primary" id="exampleModalLabel" style="font-family: Roboto, sans-serif">
                            <i class="fa fa-newspaper-o" aria-hidden="true"></i>
                            <spring:message code="Letter.Parts" /></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <div class="alert alert-danger alert-dismissible fade show" id="mialerta" role="alert" style="display: none">
                                <strong><spring:message code="lbl.invalidate" />!</strong> <spring:message code="select.options" />
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-hover table-bordered table-lg display" id="tblDetalleParte" width="100%">
                                    <thead>
                                    <tr>
                                        <th scope="col">iddetalle</th>
                                        <th scope="col">idparticipantecarta</th>
                                        <th scope="col" class="text-center"><spring:message code="Letter.Parts" /></th>
                                        <th scope="col" class="text-center">IdParte</th>
                                        <th scope="col" class="text-center"><spring:message code="lbl.ok" /></th>
                                    </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger float-left" data-dismiss="modal">
                            <i class="fa fa-times-circle" aria-hidden="true"></i>
                            <spring:message code="lbl.close" /></button>
                        <button type="button" id="btnActualizar" class="btn btn-primary float-right">
                            <i class="fa fa-pencil-square" aria-hidden="true"></i>
                            <spring:message code="lbl.update" /></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.conainer-fluid -->
        <div id="exampleModal2" class="modal fade bd-example-modal-lg" data-backdrop="static" data-keyboard="false" tabindex="-1" role="modal" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" style="font-weight: bold" id="exampleModalLabel2">
                    <i class="fa fa-thumbs-o-down" aria-hidden="true"></i> <spring:message code="lbl.invalidate" />  <strong id="codpart"> </strong></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div hidden="hidden">
                        <input type="text" id="IdParticipante" class="form-control"/>
                        <input type="text" id="IdParticipanteCartaModalAnular" class="form-control"/>
                    </div>

                    <br/>
                    <h5 class="modal-title  text-center" style="font-weight: bold" id="cuerpo">
                        <span id="nombre"></span> <br/>
                        <span id="carta"></span>,
                        <span id="version"></span>
                    </h5>
                    <br/>
                    <div class="container">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="observacion"><spring:message code="rason.invalid" /></label>
                                <textarea class="form-control" id="observacion" name="observacion" cols="2"></textarea>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div id="errror_alert" class="alert alert-danger alert-dismissible d-none" role="alert">
                                <strong><spring:message code="lbl.error" /></strong> <spring:message code="observacion" /> <spring:message code="lbl.required" />.
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <i class="fa fa-times-circle" aria-hidden="true"></i>
                        <spring:message code="cancel" /></button>
                    <button type="button" id="btnRetirar"  class="btn btn-warning">
                        <i class="fa fa-refresh"></i>
                        <spring:message code="lbl.invalidate" /> <spring:message code="letters" />
                    </button>
                </div>
            </div>
        </div>
    </div>
        <!-- Modal-lg -->
        <div class="modal fade bd-example-modal-lg" id="exampleModalxl" tabindex="-1" aria-labelledby="exampleModalLabel" data-backdrop="static" data-keyboard="false" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabelxl">AGREGAR EXTESIONES DE CARTA.</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="idParticipanteCartaModal" class="col-form-label">idParticipanteCartaModal:</label>
                                <input type="text" class="form-control" id="idParticipanteCartaModal" name="idParticipanteCartaModal">
                            </div>
                            <div class="form-group">
                                <label for="extensiones">Extensión :</label>
                                <span class="required text-danger"> * </span>
                                <select class="form-control select2-multiple" multiple id="extensiones" name="extensiones" required="required"></select>
                                <span class="error">Seleccione al menos una opción.</span>
                            </div>
                            <div hidden="hidden">
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Recipient:</label>
                                <input type="text" class="form-control" id="recipient-name">
                            </div>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Message:</label>
                                <textarea class="form-control" id="message-text"></textarea>
                            </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal"> <span aria-hidden="true">&times;</span> Cerrar</button>
                        <button type="button" class="btn btn-primary"> <i class="fa fa-save" aria-hidden="true"></i> Guardar</button>
                    </div>
                </div>
            </div>
        </div>
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
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/views/Cartas/ListadoCartaParticipnt.js" var="BuscaCartaPScript" />
<script src="${BuscaCartaPScript}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sweet" />
<script type="text/javascript" src="${sweet}"></script>
 <%--ROLE_SCAN -> se habilita para poder anular una carta--%>
<sec:authorize access="hasAnyRole('ROLE_SCAN')" var="isAuthorizeAny"></sec:authorize>
<sec:authorize access="hasRole('ADMINISTRADOR')" var="haRoleAdmin"></sec:authorize>

<script>
    $(document).ready(function(){
        $("#tableCartParticipnt tbody").on("click", ".btnRetiro", function () {
            var currow = $(this).closest('tr');
            var col0 = currow.find('td:eq(0)').text();
            var col1 = currow.find('td:eq(1)').text();
            var col2 = currow.find('td:eq(2)').text();
            var col3 = currow.find('td:eq(3)').text();
            var col5 = currow.find('td:eq(5)').text();
            $("#codpart").text(col0);
            $("#nombre").text(col1);
            $("#carta").text(col2);
            $("#version").text(col3);
            $("#IdParticipante").val($(this).data("id"));
            $("#exampleModal2").modal("show");
        });
        $(".years").datepicker({
            changeMonth: true,
            changeYear: true,
            format: "mm-yyyy",
            startView: "months",
            minViewMode: "months",
            autoclose: true
        });
        $('#tableCartParticipnt tbody').on('click', '.btnReporte', function () {
            var id = $(this).data('id');
            CrearReporte(id);
        });
        function CrearReporte(id){
            if(id != null){
                window.open("${pdfCartaUrl}?idparticipantecarta="+id, '_blank');
            }
        }
        $("#parametro").focus();
            var parametros = {
                dataTablesLang           : "${dataTablesLang}",
                GetCartasParticipanteUrl : "${GetCartasParticipanteUrl}",
                searchPartesUrl          : "${searchPartesUrl}",
                EditCartaUrl             : "${EditCartaUrl}",
                UpdateRetiroUrl          : "${UpdateRetiroUrl}",
                pdfCartaUrl              : "${pdfCartaUrl}",
                MasCartaUrl              : "${MasCartaUrl}",
                VerExtensionUrl          : "${VerExtensionUrl}",
                extensionUrl             : "${extensionUrl}",
                updateDetalleParteUrl    : "${updateDetalleParteUrl}",
                successmessage           : "${successMessage}",
                error                    : "${errorProcess}",
                DeleteUrl                : "${DeleteUrl}",
                haRoleAdmin              : "${haRoleAdmin}",
                isAuthorizeAny           : "${isAuthorizeAny}",
                Lista2AllUrl             : "${Lista2AllUrl}"
            };
        SearchCartaParticipant.init(parametros);
    });
</script>
</body>
</html>
