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
        <spring:url value="/reportes/diferencias-mx-excel/" var="fileUrl"/>
        <spring:url value="/reportes/buscarMuestras/" var="filtrarMxUrl"/>
        <div class="container-fluid col-8">
            <div class="card">
                <div class="card-header">
                    <h3  class="page-title">
                        <i class="fa fa-database"></i>&nbsp;<spring:message code="lbl.samples.differences" />
                    </h3>
                </div>
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                            <form autocomplete="off" id="search-participant-form" class="form-horizontal">
                                <div class="form-actions fluid">
                                    <div class="col-12">
                                        <a href="<spring:url value="/reportes/diferencias-mx-excel" htmlEscape="true"/>" class="btn btn-success btn-lg">
                                            <spring:message code="generate" /> <i class="fa fa-file-excel-o" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    <h3  class="page-title">
                        <i class="fa fa-search"></i>&nbsp; <spring:message code="search" /> <spring:message code="muestra" />
                    </h3>
                </div>
                <div class="card-body">
                    <div class="container mt-2">
                        <form id="form-buscar" name="form-buscar" autocomplete="off">
                            <div class="form-group row">
                                <label for="fechaInicio" class="col-sm-3 col-form-label text-center"><spring:message code="fi" /></label>
                                <div class="input-group col-sm-9">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control to_date" type="text" data-date-end-date="+0d"  required="required" id="fechaInicio" name="fechaInicio">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="fechaFin" class="col-sm-3 col-form-label text-center"><spring:message code="ff" /></label>
                                <div class="input-group col-sm-9">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control to_date" type="text" data-date-end-date="+0d"  required="required" id="fechaFin" name="fechaFin">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="tipoMuestra" class="col-sm-3 col-form-label text-center"><spring:message code="tipoMuestra" /></label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="tipoMuestra" name="tipoMuestra" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${tipo_muestra}" var="t">
                                            <option value="${t.catKey}">${t.spanish}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row" style="align-content: center">
                                <div class="col-sm-2"> </div>
                                <div class="col-sm-10 text-center">
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fa fa-search"></i>
                                        <spring:message code="reports" />
                                    </button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>

            <!-- /.conainer-fluid -->
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
<script>
    $(function () {
        $("li.comparison").addClass("open");
        $("li.differences").addClass("open");
    });

    var parameter = {
        "filtrarMxUrl":"${filtrarMxUrl}"
    };

    $(document).ready(function(){
        $("#fechaInicio").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });
        $("#fechaFin").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });
        var form = $('#form-buscar');
        var $validator = form.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                tipoMuestra: {
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
            submitHandler: function () {
                var $validarForm = form.valid();
                if (!$validarForm) {
                    $validator.focusInvalid();
                    return false;
                } else {
                    console.log(form.serialize());
                    window.open("${filtrarMxUrl}?"+ form.serialize(), '_blank');
                }
            }
        });

    });
</script>
</body>
</html>