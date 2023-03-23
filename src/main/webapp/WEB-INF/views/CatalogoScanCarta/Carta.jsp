<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 28/04/2020
  Time: 19:47
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
    <jsp:include page="../fragments/headTag.jsp"/>
    <style>
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

        /**/

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
            background-color: #007bff;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #007bff;
        }
        /**/
    </style>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp"/>


<spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
<link href="${boot}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/dtresponsive/twitter-bootstrap.css" var="boot1"/>
<link href="${boot1}" rel="stylesheet" type="text/css"/>

<spring:url value="/resources/css/bootstrapdt.css" var="bdt"/>
<link rel="stylesheet" href="${bdt}" type="text/css"/>

<spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4"/>
<link rel="stylesheet" href="${bdat4}" type="text/css"/>

<spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4"/>
<link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

<spring:url value="/resources/css/smartWizardCss/smarthWizardCss.css" var="smw"/>
<link href="${smw}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/smartWizardCss/smart_wizard_theme_arrows.min.css" var="smwtheme"/>
<link href="${smwtheme}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw"/>
<script type="text/javascript" src="${sw}"></script>


<div class="app-body">
<jsp:include page="../fragments/sideBar.jsp"/>
<!-- Main content -->
<div class="main">
<!-- Breadcrumb -->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home"/></a>
        <i class="fa fa-angle-right"></i>
        <a href="<spring:url value="/CatalogoCarta/CrearNuevaCarta" htmlEscape="true "/>">
            <spring:message code="Carta"/>
        </a>
    </li>
</ol>
<div class="container-fluid">
<div class="animated fadeIn">
<div class="">
<div class="row">
<div class="col-md-12 col-lg-12">
<div class="card">
<div class="card-body bg-primary text-white mailbox-widget pb-0">
    <h2 class="text-white pb-3">Your Mailbox</h2>
    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab"
               aria-selected="true">
                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                <span class="d-none d-md-block"> INBOX</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab"
               aria-selected="false">
                <span class="d-block d-md-none"><i class="ti-export"></i></span>
                <span class="d-none d-md-block">SENT</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="spam-tab" data-toggle="tab" aria-controls="spam" href="#spam" role="tab"
               aria-selected="false">
                <span class="d-block d-md-none"><i class="ti-panel"></i></span>
                <span class="d-none d-md-block">SPAM</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="delete-tab" data-toggle="tab" aria-controls="delete" href="#delete" role="tab"
               aria-selected="false">
                <span class="d-block d-md-none"><i class="ti-trash"></i></span>
                <span class="d-none d-md-block">DELETED</span>
            </a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
<div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
<div>
<div class="row p-4 no-gutters align-items-center">
    <div class="col-sm-12 col-md-6">
        <h3 class="font-light mb-0"><i class="ti-email mr-2"></i>350 Unread emails</h3>
    </div>
    <div class="col-sm-12 col-md-6">
        <ul class="list-inline dl mb-0 float-left float-md-right">
            <li class="list-inline-item text-info mr-3">
                <a href="#">
                    <button class="btn btn-circle btn-success text-white" href="javascript:void(0)">
                        <i class="fa fa-plus"></i>
                    </button>
                    <span class="ml-2 font-normal text-dark">Compose</span>
                </a>
            </li>
            <li class="list-inline-item text-danger">
                <a href="#">
                    <button class="btn btn-circle btn-danger text-white" href="javascript:void(0)">
                        <i class="fa fa-trash"></i>
                    </button>
                    <span class="ml-2 font-normal text-dark">Delete</span>
                </a>
            </li>
        </ul>
    </div>
</div>
<!-- Mail list-->
<div class="table-responsive">
    <table class="table email-table no-wrap table-hover v-middle mb-0 font-14">
        <tbody>
        <!-- row -->
        <tr>
            <!-- label -->
            <td class="pl-3">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="cst1"/>
                    <label class="custom-control-label" for="cst1">&nbsp;</label>
                </div>
            </td>
            <!-- star -->
            <td><i class="fa fa-star text-warning"></i></td>
            <td>
                <span class="mb-0 text-muted">Hritik Roshan</span>
            </td>
            <!-- Message -->
            <td>
                <a class="link" href="javascript: void(0)">
                    <span class="badge badge-pill text-white font-medium badge-danger mr-2">Work</span>
                    <span class="text-dark">Lorem ipsum perspiciatis-</span>
                </a>
            </td>
            <!-- Attachment -->
            <td><i class="fa fa-paperclip text-muted"></i></td>
            <!-- Time -->
            <td class="text-muted">May 13</td>
        </tr>
        <!-- row -->
        <tr>
            <!-- label -->
            <td class="pl-3">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="cst2"/>
                    <label class="custom-control-label" for="cst2">&nbsp;</label>
                </div>
            </td>
            <!-- star -->
            <td><i class="fa fa-star"></i></td>
            <!-- User -->
            <td>
                <span class="mb-0 text-muted">Genelia Roshan</span>
            </td>
            <!-- Message -->
            <td>
                <a class="link" href="javascript: void(0)">
                    <span class="badge badge-pill text-white font-medium badge-info mr-2">Business</span>
                    <span class="text-dark">Inquiry about license for Admin </span>
                </a>
            </td>
            <!-- Attachment -->
            <td><i class="fa fa-paperclip text-muted"></i></td>
            <!-- Time -->
            <td class="text-muted">May 13</td>
        </tr>
        <!-- row -->
        <tr>
            <!-- label -->
            <td class="pl-3">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="cst3"/>
                    <label class="custom-control-label" for="cst3">&nbsp;</label>
                </div>
            </td>
            <!-- star -->
            <td><i class="fa fa-star text-warning"></i></td>
            <!-- User -->
            <td class="user-name max-texts">
                <span class="mb-0 text-muted font-light">Ritesh Deshmukh</span>
            </td>
            <!-- Message -->
            <td>
                <a class="link" href="javascript: void(0)">
                    <span class="badge badge-pill text-white font-medium badge-warning mr-2">Friend</span>
                    <span class="font-light text-dark">Bitbucket (commit Pushed) by Ritesh</span>
                </a>
            </td>
            <!-- Attachment -->
            <td><i class="fa fa-paperclip text-muted"></i></td>
            <!-- Time -->
            <td class="text-muted font-light">May 13</td>
        </tr>
        <!-- row -->
        <tr class="">
            <!-- label -->
            <td class="pl-3">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="cst4"/>
                    <label class="custom-control-label" for="cst4">&nbsp;</label>
                </div>
            </td>
            <!-- star -->
            <td><i class="fa fa-star"></i></td>
            <!-- User -->
            <td>
                <span class="mb-0 text-muted font-light">Akshay Kumar</span>
            </td>
            <!-- Message -->
            <td>
                <a class="link" href="javascript: void(0)">
                    <span class="badge badge-pill text-white font-medium badge-info mr-2">Work</span><span
                        class="font-light text-dark">Perspiciatis unde omnis- iste Lorem ipsum</span>
                </a>
            </td>
            <!-- Attachment -->
            <td><i class="fa fa-paperclip text-muted"></i></td>
            <!-- Time -->
            <td class="text-muted font-light">May 9</td>
        </tr>
        <!-- row -->
        <tr class="">
            <!-- label -->
            <td class="pl-3">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="cst5"/>
                    <label class="custom-control-label" for="cst5">&nbsp;</label>
                </div>
            </td>
            <!-- star -->
            <td><i class="fa fa-star"></i></td>
            <!-- User -->
            <td>
                <span class="mb-0 text-muted font-light">John Abraham</span>
            </td>
            <!-- Message -->
            <td>
                <a class="link" href="javascript: void(0)">
                    <span class="badge badge-pill text-white font-medium badge-success mr-2">Work</span> <span
                        class="font-light text-dark">Lorem ipsum perspiciatis- unde omnis</span>
                </a>
            </td>
            <!-- Attachment -->
            <td><i class="fa fa-paperclip text-muted"></i></td>
            <!-- Time -->
            <td class="text-muted font-light">Mar 10</td>
        </tr>
        <!-- row -->
        <tr class="">
            <!-- label -->
            <td class="pl-3">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="cst6"/>
                    <label class="custom-control-label" for="cst6">&nbsp;</label>
                </div>
            </td>
            <!-- star -->
            <td><i class="fa fa-star text-warning"></i></td>
            <!-- User -->
            <td>
                <span class="mb-0 text-muted font-light">Akshay Kumar</span>
            </td>
            <!-- Message -->
            <td>
                <a class="link" href="javascript: void(0)">
                    <span class="badge badge-pill text-white font-medium badge-success mr-2">Work</span> <span
                        class="font-light text-dark">Lorem ipsum perspiciatis - unde</span>
                </a>
            </td>
            <!-- Attachment -->
            <td><i class="fa fa-paperclip text-muted"></i></td>
            <!-- Time -->
            <td class="text-muted font-light">Mar 09</td>
        </tr>
        <!-- row -->
        <tr class="">
            <!-- label -->
            <td class="pl-3">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="cst7"/>
                    <label class="custom-control-label" for="cst7">&nbsp;</label>
                </div>
            </td>
            <!-- star -->
            <td><i class="fa fa-star text-warning"></i></td>
            <!-- User -->
            <td>
                <span class="mb-0 text-muted font-light">Hanna Gover</span>
            </td>
            <!-- Message -->
            <td>
                <a class="link" href="javascript: void(0)">
                    <span class="badge badge-pill text-white font-medium badge-danger mr-2">Work</span><span
                        class="font-light text-dark"> Unde omnis Lorem ipsum perspiciatis</span>
                </a>
            </td>
            <!-- Attachment -->
            <td><i class="fa fa-paperclip text-muted"></i></td>
            <!-- Time -->
            <td class="text-muted font-light">Mar 09</td>
        </tr>
        </tbody>
    </table>
</div>
</div>
</div>
<div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
    <div class="row p-3 text-dark">
        <div class="col-md-6">
            <h3 class="font-light">Lets check profile</h3>
            <h4 class="font-light">you can use it with the small code</h4>
        </div>
        <div class="col-md-6 text-right">
            <p>Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet
                a.</p>
        </div>
    </div>
</div>
<div class="tab-pane fade" id="spam" aria-labelledby="spam-tab" role="tabpanel">
    <div class="row p-3 text-dark">
        <div class="col-md-6">
            <h3 class="font-light">Come on you have a lot message</h3>
            <h4 class="font-light">you can use it with the small code</h4>
        </div>
        <div class="col-md-6 text-right">
            <p>Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet
                a.</p>
        </div>
    </div>
</div>
<div class="tab-pane fade" id="delete" aria-labelledby="delete-tab" role="tabpanel">
    <div class="row p-3 text-dark">
        <div class="col-md-6">
            <h3 class="font-light">Just do Settings</h3>
            <h4 class="font-light">you can use it with the small code</h4>
        </div>
        <div class="col-md-6 text-right">
            <p>Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet
                a.</p>
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

<div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
                <div id="titulo"></div>
            </div>
            <div class="modal-body">
                <input type=hidden class="form-control" id="accionUrl"/>

                <div id="cuerpo"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="cancel"/></button>
                <button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message
                        code="ok"/></button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.conainer-fluid -->
</div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp"/>
<jsp:include page="../fragments/corePlugins.jsp"/>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App"/>
<script src="${App}" type="text/javascript"></script>
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs"/>
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs"/>
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive"/>
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4"/>
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive"/>
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4"/>
<script type="text/javascript" src="${TResponsiveb4}"></script>


<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>


<script type="text/javascript">
    $(document).ready(function () {

        /*$('#smartwizard').smartWizard({
            selected: 0,
            theme: 'arrows',
            lang: {  // Language variables
                next: 'Sig',
                previous: 'Prev'
            }
        });*/

        var parametro = {
            GetCartaByIDUrl: "${GetCartaByIDUrl}",
            saveCatalogCartaUrl: "${saveCatalogCartaUrl}",
            delCatalogCartaUrl: "${delCatalogCartaUrl}"
        };

        $('#tblCartas tbody').on('click', '.Activar', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col2 = currentRow.find("td:eq(2)").text();
            $('#titulo').html('<h2 class="modal-title">' + "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Habilitar?" + '</h2>');
            $('#cuerpo').html('<h3 class="text-warning">' + col2 + '</h3>');
            $('#basic').modal('show');
        });

        $('#tblCartas tbody').on('click', '.desact', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col2 = currentRow.find("td:eq(2)").text();
            $('#titulo').html('<h2 class="modal-title">' + "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Deshabilitar?" + '</h2>');
            $('#cuerpo').html('<h3 class="text-danger">' + col2 + '</h3>');
            $('#basic').modal('show');
        });

        $("#frmCarta").validate({
            rules: {
                NameCarta: {required: true}
            },
            errorElement: 'em',
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass('form-control-feedback');
                if (element.prop('type') === 'checkbox') {
                    error.insertAfter(element.parent('label'));
                } else {
                    error.insertAfter(element);
                }
                if (element.attr("name") == "parametro") {
                    error.insertAfter("#gendererror");
                } else {
                    error.insertAfter(element);
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('form-control-danger').removeClass('form-control-success');
                $(element).parents('.form-group').addClass('has-danger').removeClass('has-success');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).addClass('form-control-success').removeClass('form-control-danger');
                $(element).parents('.form-group').addClass('has-success').removeClass('has-danger');
            },
            submitHandler: function (form) {
                Save(parametro);
            }
        });

        function Save(urls) {
            var form1 = $("#frmCarta");
            $.post(urls.saveCatalogCartaUrl, form1.serialize(), function (data) {
                if (data.msj != null) {
                    swal("Error!", data.msj, "error");
                } else {
                    swal("Éxito!", "Información guardada!", "success");
                    window.setTimeout(function () {
                        window.location.reload();
                    }, 1500);
                    ClsInput();
                }
            }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Error al guardar la información.", "error");
                ClsInput();
            });
        };
        function ClsInput() {
            $("#NameCarta").val("");
            $("#idcarta").val("");
        }

        $("#tblCartas").DataTable({
            responsive: true
        });
        $("#tblCartas tbody").on("click", ".btnEdit", function () {
            debugger;
            var id = $(this).data('id');
            GetCartaId(id);
        });
        $("#tblCartas tbody").on("click", ".btnDelete", function () {
            var id = $(this).data('id');
            DeleteById(id);
        });
        function DeleteById(id) {
            if (confirm("Desea eliminar")) {
                $.ajax({
                    url: parametro.delCatalogCartaUrl + "/?idcarta=" + id,
                    method: 'POST',
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    sync: true,
                    processData: false,
                    cache: false,
                    success: function (result) {
                        swal("Eliminado!", "Registro eliminado.", "success");
                        window.setTimeout(function () {
                            window.location.reload();
                        }, 1500);
                        ClsInput();
                    },
                    error: function (request, msg, error) {
                        swal("Error", "Intenta de nuevo.", "error");
                    }
                });
            }

        }

        function GetCartaId(cod) {
            $.getJSON(parametro.GetCartaByIDUrl, { idcarta: cod, ajax: 'true'  }, function (data) {
                if (data.result == null) {
                    console.log(data);
                    $("#idcarta").val(data.idcarta);
                    $("#NameCarta").val(data.carta);
                    $("#btnSave").text("Actualizar");
                } else {
                    swal("Error", "Intenta de nuevo.", "error");
                }
            });
        }
    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>
