<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 19/02/2022
  Time: 08:40 PM
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
    <jsp:include page="../fragments/headTag.jsp" />

    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

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
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="container col-md-10">
                    <div class="card">
                        <h5 class="card-header">
                            <i class="fa fa-file-text" aria-hidden="true"></i>
                            <small> <spring:message code="Reporte Envios" /> <spring:message code="Pbmc" /></small>
                        </h5>
                        <div class="card-body">
                            <spring:url value="/reportes/EnvioBhcPdf/" var="EnvioBhcPdfUrl"/>
                            <spring:url value="/reportes/EnvioBhcExcel/" var="EnvioBhcExcelUrl"/>
                            <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                            <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
                            <form autocomplete="off" id="send-pbmc-form" class="form-horizontal">
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

                                <div class="d-flex justify-content-between">
                                    <div class="p-2 bd-highlight">

                                    </div>
                                    <div class="p-2 bd-highlight">
                                        <button id="toPdf" type="submit" class="btn btn-success btn-ladda" data-toggle="tooltip" data-placement="top" title="Reporte Bhc pdf" data-style="expand-right">
                                            <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                                            <spring:message code="generate" /> <spring:message code="Bhc" /> </button>
                                        <button id="BhctoExcel" type="submit" class="btn btn-info btn-ladda" data-toggle="tooltip" data-placement="top" title="Reporte Bhc excel" data-style="expand-right">
                                            <i class="fa fa-file-excel-o" aria-hidden="true"></i>
                                            <spring:message code="generate" /> <spring:message code="Bhc" /> </button>
                                    </div>
                                    <div class="p-2 bd-highlight"></div>
                                </div>

                            </form>
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
<script>
    jQuery(document).ready(function() {
        handleDatePickers("${lenguaje}");
        var form1 = $('#send-pbmc-form');
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
            errorPlacement: function (error, element) {
                console.log(element.prop('type'));
                // Add the `help-block` class to the error element
                error.addClass('form-control-feedback');
                if (element.prop('type') === 'checkbox') {
                    error.insertAfter(element.parent('label'));
                } else if (element.prop('type') === 'text') {
                    error.insertAfter(element.parent('.input-group'));
                } else {
                    error.insertAfter(element); //cuando no es input-group
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
                var $validarForm = form1.valid();
                if (!$validarForm) {
                    $validator.focusInvalid();
                    return false;
                } else {
                    window.open("${EnvioBhcPdfUrl}?" + form1.serialize(), '_blank');
                }
            }
        });


        $("#BhctoExcel").on("click", function(){
            var $validarForm = form1.valid();
            if (!$validarForm) {
                $validator.focusInvalid();
                return false;
            } else {
                window.open("${EnvioBhcExcelUrl}?" + form1.serialize());
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
