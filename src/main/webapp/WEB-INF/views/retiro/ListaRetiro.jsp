<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 15/11/2020
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
    <link rel="stylesheet" href="${bdt}" type="text/css"/>

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
    .form-control-feedback {
        margin-top: 0.25rem;
        width: 17%;
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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/retiro/ListRetiro/" htmlEscape="true "/>">
                <spring:message code="List" /> <spring:message code="retirement" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="">
                    <div class="row">
                        <div class="col-sm-12 col-lg-12">
                            <div class="card shadow bg-white rounded">
                                <div class="card-body">
                                    <spring:url value="/retiro/ListaHojaRetiro" var="ListaHojaRetiroUrl"/>
                                    <spring:url value="/retiro/DetallesRetiro" var="DetallesRetiroUrl"/>
                                    <spring:url value="/reportes/reporteRetiro/" var="pdfRetiroUrl"/>
                                    <h5 class="card-header text-capitalize" style="font-family: Roboto"> <i class="fa fa-list" aria-hidden="true"></i>
                                        <spring:message code="List"/>  <spring:message code="retirement"/></h5>
                                    <br/>
                                    <a class="btn btn-primary btn-lg" href="<spring:url value="/retiro/saveRetiroForm" htmlEscape="true "/>">
                                        <i class="fa fa-minus-circle" aria-hidden="true"></i>
                                        <spring:message code="realize"/> <spring:message code="retirement"/></a>
                                    <hr/>
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
                                    </div>
                                    <hr/>
                                    <div class="col-md-12">
                                        <div class="table-responsive">
                                            <table id="tableRetiro" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                                <thead>
                                                <tr>
                                                    <th class="text-center"><spring:message code="code" /> <spring:message code="participant"/></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="dateAdded"/></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="lbl.names.surnames"/></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Retiro" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Motivo" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="observacion" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="dateCreated" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="demise" /></th>
                                                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Details" /></th>
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

        <div id="exampleModal" class="modal fade"  tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"> <i class="fa fa-user-times"></i> <spring:message code="Details" /> <spring:message code="retirement"/> .</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="casapediatrica"><spring:message code="ecasa.Pediatric"/></label>
                                    <input type="text" readonly class="form-control" id="casapediatrica" >
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="casafamilia"> <spring:message code="ecasa.Family"/> </label>
                                    <input type="text" class="form-control" id="casafamilia" readonly>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="motivo"><spring:message code="reason"/></label>
                                    <input type="text" class="form-control" id="motivo" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="motivoDetalle"><spring:message code="Details"/> <spring:message code="reason"/></label>
                                <input type="text" class="form-control" id="motivoDetalle" readonly>
                            </div>
                            <div class="form-group">
                                <label for="otrosmotivo"><spring:message code="others"/> <spring:message code="reason"/></label>
                                <input type="text" class="form-control" id="otrosmotivo" readonly>
                            </div>
                            <div class="form-group">
                                <label for="medicosupervisor"><spring:message code="supervised_by"/></label>
                                <input type="text" class="form-control" id="medicosupervisor" readonly>
                            </div>
                            <div class="form-group">
                                <label for="personadocumenta"><spring:message code="documented_by"/></label>
                                <input type="text" class="form-control" id="personadocumenta" readonly>
                            </div>
                            <div class="form-group">
                                <label for="quiencomunica"><spring:message code="communicated_by"/></label>
                                <input type="text" class="form-control" id="quiencomunica" readonly>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="relFam"><spring:message code="family.relationship"/> </label>
                                    <input type="text" class="form-control" id="relFam" readonly>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="carnet"><spring:message code="returned_card"/></label>
                                    <input type="text" class="form-control" id="carnet" readonly>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            <i class="fa fa-times-circle" aria-hidden="true"></i>
                            <spring:message code="lbl.close"/></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
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
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>


<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/notify.min.js" var="noty" />
<script type="text/javascript" src="${noty}"></script>

<spring:url value="/resources/js/views/retiro/listaRetiros.js" var="listret"/>
<script type="application/javascript" src="${listret}"></script>

<script type="text/javascript">
    $(document).ready(function(){
        var parametros={
            ListaHojaRetiroUrl : "${ListaHojaRetiroUrl}",
            DetallesRetiroUrl  : "${DetallesRetiroUrl}",
            dataTablesLang     : "${dataTablesLang}",
            pdfRetiroUrl       : "${pdfRetiroUrl}"
        };
        ListadoRetiros.init(parametros);

        $('#tableRetiro tbody').on('click', '.btnView', function () {
            var id = $(this).data('id');
            ver(id);
        });

        function ver(id){
            var jqxhr = $.getJSON(parametros.DetallesRetiroUrl, { idretiro : id,   ajax : 'true'  }, function(data){
                if(data.mensaje != null) $.notify(data.mensaje,"error");
                $("#casafamilia").val(data.casafamilia);
                $("#casapediatrica").val(data.casapediatrica);
                $("#medicosupervisor").val(data.medicosupervisor);
                $("#observacion").val(data.observacion);
                $("#otrosmotivo").val(data.otrosmotivo);
                $("#personadocumenta").val(data.personadocumenta);
                $("#quiencomunica").val(data.quiencomunica);
                $("#relFam").val(data.relFam);
                $("#carnet").val(data.carnet);
                $("#motivo").val(data.motivo);
                $('#motivoDetalle').val(data.motivoDetalle);
                $("#exampleModal").modal("show");
            }).fail(function(){
                toastr.error("Interno del Servidor","ERROR",{timeOut:6000});
            })
        }

        $('#tableRetiro tbody').on('click', '.btnReport', function () {
            var id = $(this).data('id');
            CrearReporteRetiro(id);
        });
        function CrearReporteRetiro(id){
            if(id != null){
                window.open("${pdfRetiroUrl}?parametro="+id, '_blank');
            }
        }
        $("#parametro").focus();
    });
</script>
</body>
</html>