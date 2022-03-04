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
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>

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
                <i class="fa fa-angle-right"></i> <spring:message code="corrections" /> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/correcion/tutor/" htmlEscape="true "/>"><spring:message code="lbl.tutor" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">

                <div class="">

                    <div class="row">
                        <div class="col-sm-12 col-lg-12">
                            <div class="card shadow bg-white rounded">
                                <div class="card-body">
                                    <h5 class="card-header text-capitalize">
                                        <i class="fas fa-check-double" aria-hidden="true"></i> <spring:message code="lbl.correction.tutor"/> </h5>
                                    <br/>
                                    <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="well search-result">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control form-control-lg"  placeholder="Ingrese el código" id="parametro" name="parametro" tabindex="1">
                                              <span class="input-group-btn">
                                          <button class="btn btn-info btn-lg" type="submit">
                                              <i class="fa fa-search"></i> <spring:message code="search" />
                                          </button>
                                              </span>
                                                    </div>
                                                    <div id="gendererror" class="text-danger"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <!-- fin -->
                                    <hr/>

                                        <div class="row">
                                            <div class="col-lg-12 col-md-12 col-sm-12">
                                                <h4 class="text-capitalize"><spring:message code="datos_personales" /> <spring:message code="lbl.participant" /></h4>
                                            </div>
                                            <div class="col-lg-12 col-md-12 col-sm-12">
                                                <div class="row">
                                                    <div class="col-lg-2 col-md-12 col-sm-12">
                                                        <div class="form-group">
                                                            <label for="codigo" class="form-control-label"><spring:message code="code" /></label>
                                                            <input type="text" class="form-control" id="codigo" name="codigo"
                                                                   value=""
                                                                   readonly />
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-md-12 col-sm-12">
                                                        <div class="form-group">
                                                            <label for="nombre" class="form-control-label"><spring:message code="nombre" /></label>
                                                            <input type="text" class="form-control" id="nombre" name="nombre"
                                                                   value=""
                                                                   readonly />
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-4 col-sm-6">
                                                        <div class="form-group">
                                                            <label for="fechanac"><spring:message code="fecha_nacimiento" /></label>
                                                            <input type="text" class="form-control" id="fechanac" name="fechanac"
                                                                   value="" readonly />
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-md-4 col-sm-6">
                                                        <div class="form-group">
                                                            <label for="edadPart"><spring:message code="edad" /></label>
                                                            <input type="text" class="form-control" id="edadPart" name="edadPart" value="" readonly />
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-1 col-md-4 col-sm-6">
                                                        <div class="form-group">
                                                            <label for="sexoPart"><spring:message code="sexo" /></label>
                                                            <input type="text" class="form-control" id="sexoPart" required name="sexoPart" value="" readonly />
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-12 col-md-12 col-sm-12">
                                                <h4 class="text-capitalize"><spring:message code="lbl.correction.tutor.actual" /></h4>
                                            </div>
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div class="row">
                                                    <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                        <div class="form-group">
                                                            <label for="nombre1ActTutor" class="form-control-label"><spring:message code="first.name" /></label>
                                                            <input type="text" class="form-control" id="nombre1ActTutor" name="codigo"
                                                                   value=""
                                                                   readonly />
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                        <div class="form-group">
                                                            <label for="nombre2ActTutor" class="form-control-label"><spring:message code="second.name" /></label>
                                                            <input type="text" class="form-control" id="nombre2ActTutor" name="nombre"
                                                                   value=""
                                                                   readonly />
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                        <div class="form-group">
                                                            <label for="apellido1ActTutor"><spring:message code="first.surname" /></label>
                                                            <input type="text" class="form-control" id="apellido1ActTutor" name="fechanac"
                                                                   value="" readonly />
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                        <div class="form-group">
                                                            <label for="apellido2ActTutor"><spring:message code="second.surname" /></label>
                                                            <input type="text" class="form-control" id="apellido2ActTutor" name="edadPart" value="" readonly />
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                        <div class="form-group">
                                                            <label for="relacionFamAct"><spring:message code="family.relationship" /></label>
                                                            <input type="text" class="form-control" id="relacionFamAct" required name="sexoPart" value="" readonly />
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    <form action="#" autocomplete="off" id="corregir-tutor-form" name="corregir-tutor-form" class="form-horizontal">
                                                <div class="row">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <h4 class="text-capitalize"><spring:message code="lbl.correction.tutor.new" /></h4>
                                                </div>
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                            <div class="form-group">
                                                                <label for="nombre1NuevoTutor" class="form-control-label"><spring:message code="first.name" /> <span class="required text-danger"> * </span></label>
                                                                <input type="text" class="form-control" id="nombre1NuevoTutor" name="nombre1NuevoTutor"
                                                                       value="" required="required" />
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                            <div class="form-group">
                                                                <label for="nombre2NuevoTutor" class="form-control-label"><spring:message code="second.name" /></label>
                                                                <input type="text" class="form-control" id="nombre2NuevoTutor" name="nombre2NuevoTutor"
                                                                       value=""/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                            <div class="form-group">
                                                                <label for="apellido1NuevoTutor"><spring:message code="first.surname" /> <span class="required text-danger"> * </span></label>
                                                                <input type="text" class="form-control" id="apellido1NuevoTutor" name="apellido1NuevoTutor"
                                                                       value=""  required="required" />
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                            <div class="form-group">
                                                                <label for="apellido2NuevoTutor"><spring:message code="second.surname" /></label>
                                                                <input type="text" class="form-control" id="apellido2NuevoTutor" name="apellido2NuevoTutor" value=""/>
                                                            </div>

                                                        </div>
                                                        <div class="col-lg-2 col-md-6 col-sm-12 col-12">
                                                            <div class="form-group">
                                                                <label for="relacionFamNuevo"><spring:message code="family.relationship" /> <span class="required text-danger"> * </span></label>
                                                                <select name="relacionFamNuevo" id="relacionFamNuevo" class="form-control" required="required">
                                                                    <option selected value=""><spring:message code="select" />...</option>
                                                                    <c:forEach items="${relFam}" var="rel">
                                                                        <option value="${rel.catKey}">${rel.spanish}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                                            <div class="form-group">
                                                                <label for="observacion"><spring:message code="lbl.correction.observacion" /> <span class="required text-danger"> * </span></label>
                                                                <textarea class="form-control"  id="observacion" name="observacion" placeholder="<spring:message code="lbl.correction.observacion" />" rows="3" required="required" ></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="row">
                                                <div class="col-lg-4 col-md-4 col-sm-2"></div>
                                                <div class="col-lg-2 col-md-2 col-sm-4">
                                                    <div class="form-group">
                                                        <button class="btn btn-success  btn-ladda btn-block btn-lg"
                                                                type="submit" id="btnModificar">
                                                            <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" />
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-4">
                                                    <div class="form-group">
                                                        <button class="btn btn-warning  btn-ladda btn-block btn-lg"
                                                                type="button" id="btnCancelar">
                                                            <i class="fa fa-ban" aria-hidden="true"></i>
                                                            <spring:message code="cancel" />
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 col-md-4 col-sm-2"></div>
                                            </div>
                                    </form>
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
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<!-- Custom scripts required by this view -->
<spring:url value="/resources/js/views/Cartas/Cartas.js" var="cartaScript" />
<script src="${cartaScript}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/select2.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/corrections/process-tutor.js" var="jsTutor"/>
<script type="application/javascript" src="${jsTutor}"></script>

<spring:url value="/correcion/tutor/buscar" var="searchUrl"/>
<c:set var="notFound"><spring:message code="noResults" /></c:set>
<spring:url value="/correcion/tutor/guardar" var="saveUrl"/>

<script type="text/javascript">
    $(document).ready(function(){
        var parametros={
            searchUrl: "${searchUrl}",
            saveUrl: "${saveUrl}"
        };
        ProcesarTutor.init(parametros);

        $("#parametro").focus();
    });
</script>
</body>
</html>