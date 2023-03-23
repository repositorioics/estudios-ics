<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

    <style>
        .error{
            color: #ff5454;
        }
    </style>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden footer-fixed">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <div class="main">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/hemo/listado" htmlEscape="true "/>">
                    <spring:message code="Listado" /></a>
                <i class="fa fa-angle-right"></i>
                <spring:message code="reports" />
            </li>
        </ol>
        <spring:url value="/hemo/listado2" var="listHemoUrl"/>
        <spring:url value="/reportes/hemoExcel" var="hemoExcelUrl"/>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div class="container">
            <div class="col-sm-12 col-lg-12">
            <div class="card">
                <div class="card-header">
                   <h5 style="font-family: Roboto">
                       <i class="fa fa-search"></i> <spring:message code="search" /> <spring:message code="Participante" />
                   </h5>
                </div>
                <div class="card-body">
                    <form autocomplete="off" name="form-report" id="form-report">

                        <div class="form-group row">
                            <label for="participantCode" class="col-sm-2 col-form-label"><spring:message code="Participante" /></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="participantCode" id="participantCode" required="">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="currentYear" class="col-sm-2 col-form-label"><spring:message code="Año" /></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" data-date-end-date="+0d" name="currentYear" id="currentYear" required="required">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-4"></div>
                            <div class="col-sm-4">
                                <button type="submit" id="btnExcel" class="btn btn-primary btn-block">
                                    <i class="fa fa-file-excel-o" aria-hidden="true"></i>
                                    <spring:message code="reports" />
                                </button>
                            </div>
                            <div class="col-sm-4"></div>
                        </div>
                    </form>
                </div>
                <div class="card-footer text-muted"></div>
            </div>
            </div>
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
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>


<script>
    $(document).ready(function(){

        $("#currentYear").datepicker({
            changeYear: true,
            format: "yyyy",
            viewMode: "years",
            minViewMode: "years",
            startDate: '-3y',
            autoclose: true
        });

        var form = $("#form-report");
        form.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                participantCode: {
                    pattern: /^\+?[0-9]*\.?[0-9]+$/,
                    required: true,
                    maxlength: 5
                },
                currentYear:{
                    pattern: /^\+?[0-9]*\.?[0-9]+$/,
                    required: true,
                    maxlength: 4
                },
                estudio:{required:true}
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
                generarExcel();
            }
        });

        function generarExcel(){
            var $validarForm = form.valid();
            if (!$validarForm) {
                $validator.focusInvalid();
                return false;
            } else {
                window.open("${hemoExcelUrl}"+"/?"+form.serialize());
            }
        }
    });
</script>
</body>
</html>
