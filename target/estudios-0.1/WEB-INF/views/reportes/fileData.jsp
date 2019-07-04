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
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /> <i class="fa fa-angle-right"></i></a> <spring:message code="reports" />
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/reportes/pdf/fileData/" htmlEscape="true "/>"><spring:message code="report.file.data" /></a>
            </li>
        </ol>
        <spring:url value="/reportes/downloadFileDataReport/" var="pdfUrl"/>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
        <div class="container-fluid col-8">
            <div class="card">
                <div class="card-header">
                    <h3 class="page-title">
                        <small><spring:message code="search" /> <spring:message code="participant" /></small>
                    </h3>
                </div>
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-10">
                            <form autocomplete="off" id="search-participant-form" class="form-horizontal">
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label" for="estudio"><spring:message code="study"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <select name="estudio" id="estudio" class="form-control select2-single">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${estudios}" var="estudio">
                                                <option value="${estudio.codigo}">${estudio.nombre}</option>
                                            </c:forEach>
                                            <option value="0"><spring:message code="lbl.all" /></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="fechaInicio"><spring:message code="start.date.registration" />
                                    </label>
                                    <div class="input-group col-md-6">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        <input name="fechaInicio" id="fechaInicio" class="form-control from_date" type="text" data-date-end-date="+0d"  />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="fechaFin"><spring:message code="end.date.registration" />
                                    </label>
                                    <div class="input-group col-md-6">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        <input name="fechaFin" id="fechaFin" class="form-control to_date" type="text" data-date-end-date="+0d"  />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="codigoParticipante"><spring:message code="participant.code" />
                                    </label>
                                    <div class="input-group col-md-6">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                        <input id="codigoParticipante" name="codigoParticipante" type="text" value="" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="col-md-offset-6 col-md-10">
                                        <button id="toPdf" type="submit" class="btn btn-success btn-ladda" data-style="expand-right"><spring:message code="generate" /> <i class="fa fa-file-pdf-o"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>
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
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<spring:url value="/resources/js/views/casos/process-case.js" var="processVersionJs" />
<script src="${processVersionJs}"></script>
<script>
    $(function () {
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
                estudio: {
                    required: true
                },
                codigoParticipante: {
                    pattern: /^\+?[0-9]*\.?[0-9]+$/,
                    maxlength: 5
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
                    /*$(this).attr("href", "${pdfUrl}"+$('#participantCode').val());
                    event.preventDefault();
                    event.stopPropagation();*/
                    window.open("${pdfUrl}?"+form1.serialize(), '_blank');
                }
            }
        });
    });
</script>
</body>
</html>