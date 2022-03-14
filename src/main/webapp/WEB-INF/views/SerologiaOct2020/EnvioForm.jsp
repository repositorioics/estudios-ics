<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 18/10/2020
  Time: 17:55
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
    <jsp:include page="../fragments/headTag.jsp" />
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
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
            width: 95%;
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
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /> <i class="fa fa-angle-right"></i></a> <spring:message code="reports" />
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/Serologia/listSerologia" htmlEscape="true "/>"><spring:message code="Reporte Envio" /></a>
            </li>
        </ol>
        <spring:url value="/reportes/downloadFileEnviosSerologia/" var="pdfEnvioSeroUrl"/>
        <spring:url value="/reportes/downloadFileSerologiaExcel/" var="excelEnvioSeroUrl"/>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
        <div class="container-fluid col-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                        <i class="fa fa-file-text" aria-hidden="true"></i>
                        <small><spring:message code="Generar" />
                            <spring:message code="Reporte Envios" />
                            <spring:message code="Serologia" />
                        </small>
                    </h5>
                </div>
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-10">
                            <form autocomplete="off" id="search-participant-form" class="form-horizontal">
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label" for="Envio"><spring:message code="Envio"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <select id="nEnvios" name="nEnvios" class="form-control" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${numero_envio}" var="n">
                                                <option value="${n.catKey}">${n.spanish}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="fechaInicio"><spring:message code="Fecha Inicio" />
                                    </label>
                                    <div class="input-group col-md-6">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        <input name="fechaInicio" id="fechaInicio" class="form-control from_date" type="text" data-date-end-date="+0d" required="required" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="fechaFin"><spring:message code="Fecha Final" />
                                    </label>
                                    <div class="input-group col-md-6">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        <input name="fechaFin" id="fechaFin" class="form-control to_date" type="text" data-date-end-date="+0d"  required="required"/>
                                    </div>
                                </div>

                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-6 text-center">
                                            <button id="toPdf" type="submit" data-toggle="tooltip" data-placement="top" title="Serologia en Pdf" class="btn btn-success btn-ladda" data-style="expand-right">
                                                <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                                                <spring:message code="generate" /> <spring:message code="Serologia" />
                                            </button>
                                        </div>
                                        <div class="col-md-6 text-center">
                                            <button id="toExcel" type="submit" data-toggle="tooltip" data-placement="top" title="Serologia en Excel" class="btn btn-info btn-ladda" data-style="expand-right">
                                                <i class="fa fa-file-excel-o" aria-hidden="true"></i>
                                                <spring:message code="generate" /> <spring:message code="Serologia" />
                                            </button>
                                        </div>
                                        <%--<div class="col-md-6 text-center mt-1">
                                            <button id="Sero_PBMC_PDF" type="submit" data-toggle="tooltip" data-placement="bottom" title="Serologia de Pbmc en Pdf" class="btn btn-danger btn-ladda" data-style="expand-right">
                                                <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                                                <spring:message code="generate" />  <spring:message code="Serologia" /> <spring:message code="Pbmc" />
                                            </button>
                                        </div>
                                        <div class="col-md-6 text-center mt-1">
                                            <button id="Sero_PBMC_Excel" type="submit" class="btn btn-danger btn-ladda" data-toggle="tooltip" data-placement="bottom" title="Serologia de Pbmc en Excel" data-style="expand-right">
                                                <i class="fa fa-file-excel-o" aria-hidden="true"></i>
                                                <spring:message code="generate" />  <spring:message code="Serologia" /> <spring:message code="Pbmc" />
                                            </button>
                                        </div>--%>
                                    </div>
                                </div>

                           <%--

                                <div class="form-actions fluid">
                                    <div class="col-md-offset-6 col-md-10">
                                        <button id="toPdf" type="submit" class="btn btn-success btn-ladda" data-style="expand-right"><spring:message code="generate" /> <i class="fa fa-file-pdf-o"></i></button>
                                    </div>
                                </div>--%>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/casos/process-case.js" var="processVersionJs" />
<script src="${processVersionJs}"></script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
        $("li.reports").addClass("open");
        $("li.filedata").addClass("open");
    });
</script>
<script>
    jQuery(document).ready(function() {
        handleDatePickers("${lenguaje}");
        var form1 = $('#search-participant-form');
        var $validator = form1.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                nEnvios: {
                    required: true
                },
                fechaFin: {required: function () {
                    return $('#fechaInicio').val().length > 0;
                }},
                fechaInicio: {required: function () {
                    return $('#fechaFin').val().length > 0;
                }}
            },
            errorPlacement: function ( error, element ) {
                console.log(element.prop( 'type' ));
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                }else if ( element.prop( 'type' ) === 'text' ){
                    error.insertAfter(element.parent('.input-group'));
                } else {
                    error.insertAfter( element ); //cuando no es input-group
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
                var $validarForm = form1.valid();
                if (!$validarForm) {
                    $validator.focusInvalid();
                    return false;
                } else {
                    window.open("${pdfEnvioSeroUrl}?"+form1.serialize(), '_blank');
                }
            }
        });

        $("#toExcel").on("click", function(){
            var $validarForm = form1.valid();
            if (!$validarForm) {
                $validator.focusInvalid();
                return false;
            } else {
                window.open("${excelEnvioSeroUrl}?" + form1.serialize())
            }
        });

        $("#Sero_PBMC_Excel").on("click", function(){
            var $validarForm = form1.valid();
            if (!$validarForm) {
                $validator.focusInvalid();
                return false;
            } else {
                window.open("${EnvioSeroPbmcExcelUrl}?" + form1.serialize())
            }
        });

        $("#Sero_PBMC_PDF").on("click", function(){
            var $validarForm = form1.valid();
            if (!$validarForm) {
                $validator.focusInvalid();
                return false;
            } else {
                window.open("${EnvioSeroPbmcPdfUrl}?" + form1.serialize())
            }
        });

    });
</script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>
</body>
</html>
