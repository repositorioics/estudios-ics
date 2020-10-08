<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <style>
        span.error {
            display:block;
            visibility:hidden;
            color:red;
            font-size:90%;
        }
        #error {
            color:red;
            display:none;
        }
        /* Inicio Preload*/

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
        /* Fin Preload*/
    </style>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/smartWizardCss/smarthWizardCss.css" var="smw" />
    <link href="${smw}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/smartWizardCss/smart_wizard_theme_arrows.min.css" var="smwtheme" />
    <link href="${smwtheme}" rel="stylesheet" type="text/css"/>
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
                <a href="<spring:url value="/cartas/ListadoCartaParticipant/" htmlEscape="true "/>">LISTADO</a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(editPartCartaUrl)}">ACTUALIZAR </a>
                <i class="fa fa-angle-right"></i>
                <strong>${obj.participante.nombreCompleto}</strong>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div id="page-loader">
                <span class="preloader-interior"></span>
            </div>
            <!-- inicio smart wizard  -->
            <div class="card shadow-lg p-3 mb-5 bg-white">
            <div id="error" class="alert alert-default alert-dismissible fade show" role="alert" id="message-box">
                <h1 class="text-danger" id="msj"><strong>Error!</strong> revisa todos campos. </h1>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="card-header">
                <h3 class="text-gray-dark" style="font-family: Roboto">
                    <i class="fa fa-pencil" aria-hidden="true"></i>
                    <spring:message code="Actualizar Información de: "  />
                    <strong>${obj.participante.nombreCompleto}</strong>
                </h3>
            </div>
            <div class="card-body">
            <spring:url value="/cartas/searchParticipant" var="searchPartUrl"/>
            <spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
            <spring:url value="/cartas/ParteVersion" var="ParteVersionUrl"/>
            <spring:url value="/cartas/saveScanCarta" var="saveScanCartaUrl"/>
            <br />
            <form action="#" id="form-scan" role="form" data-toggle="validator" method="post" accept-charset="utf-8">
            <!-- SmartWizard html -->
            <div id="smartwizard">
            <ul>
                <li><a href="#step-1">1 - Información <br /><small>del Participante.</small></a></li>
                <li><a href="#step-2">2 - Tipo  <br /><small>Carta.</small></a></li>
                <li><a href="#step-3">3 - Información<br /><small> del Tutor.</small></a></li>
            </ul>
            <div>
            <div id="step-1">
                <h3 class="border-bottom border-gray pb-2" style="font-family: Roboto">
                    <i class="fa fa-search" aria-hidden="true"></i>
                    Información del Participante
                </h3>
                <div class="row">
                    <div class="col-md-12">
                        <input type="hidden" class="form-control" name="codigo" id="codigo" value="${obj.idparticipantecarta}"/>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="idparticipante">Código: </label>
                            <input type="text" class="form-control" id="idparticipante" name="idparticipante" value="${obj.participante.codigo}" disabled>
                        </div>
                    </div>

                    <div class="col-md-9">
                        <div class="form-group">
                            <label for="txtNombreCompleto">Participante: </label>
                            <input type="text" class="form-control" value="${obj.participante.nombreCompleto}" id="txtNombreCompleto" name="txtNombreCompleto" disabled>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="edad">Edad :</label>
                            <input type="text" class="form-control" id="edad" name="edad" value="${participante.edad}" disabled>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="estudios">Estudios :</label>
                            <input type="text" class="form-control" id="estudios" name="estudios" value="${procesos.estudio}" disabled>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="padre">Padre :</label>
                            <input type="text" class="form-control" id="padre" name="padre" disabled value="${participante.nombre1Padre} ${participante.nombre2Padre} ${participante.apellido1Padre} ${participante.apellido2Padre}">
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="madre">Madre :</label>
                            <input type="text" class="form-control" id="madre" name="madre" value="${participante.nombre1Madre} ${participante.nombre2Madre} ${participante.apellido1Madre} ${participante.apellido2Madre}" disabled>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="relacionFam">Relación del tutor con el niño :</label>
                            <input type="text" class="form-control" id="relacionFam" name="relacionFam" value="${procesos.relacionFam}"disabled>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="tutor">Tutor :</label>
                            <input type="text" class="form-control" id="tutor" name="tutor" value="${procesos.tutor}" disabled>
                        </div>
                    </div>
                </div>
            </div>

            <div id="step-2">
                <h3 class="border-bottom border-gray pb-2" style="font-family: Roboto">
                    <i class="fa fa-file-text-o" aria-hidden="true"></i> Tipo Carta
                </h3>
                <div class="row">
                    <div class="col-md-12"></div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="fechacarta">Fecha :</label>
                            <span class="required text-danger"> * </span>
                            <input type="text" required="required" class="form-control focusNext" tabindex="5" id="fechacarta" name="fechacarta"  data-date-end-date="+0d" value="<fmt:formatDate value="${obj.fechacarta}" pattern="dd/MM/yyyy"/>"/>
                            <span class="error">Seleccione la fecha.</span>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="carta">Carta :</label>
                            <span class="required text-danger"> * </span>
                            <select name="carta" id="carta" name="carta" class="form-control" required="required">
                                <option selected value=""><spring:message code="select" />...</option>
                                <c:forEach items="${carta}" var="c">
                                    <c:choose>
                                        <c:when test="${c.idcarta eq obj.version.carta.idcarta}">
                                            <option selected value="${c.idcarta}"> <spring:message code="${c.carta}" />  </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${c.idcarta}">${c.carta}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <span class="error">Seleccione la carta.</span>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
                        <spring:url value="/cartas/UpdateAll" var="UpdateAllUrl"/>
                        <spring:url value="/cartas/ListadoCartaParticipant" var="Lista2AllUrl"/>
                        <div class="form-group">
                            <label for="version">Versión:</label>
                            <span class="required text-danger"> * </span>
                            <select name="version" id="version" class="form-control" required="required">
                                <c:forEach items="${version}" var="v">
                                    <c:choose>
                                        <c:when test="${v.idversion eq obj.version.idversion}">
                                            <option selected value="${v.idversion}"> <spring:message code="${v.version}" />  </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${v.idversion}"><spring:message code="${v.version}" /> </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <span class="error">Seleccione la versión.</span>
                        </div>
                    </div>


                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="partes">Partes :</label>
                            <span class="required text-danger"> * </span>
                            <select class="form-control select2-multiple" multiple id="partes" name="partes" required="required">
                                <c:forEach items="${dp}" var="d">
                                    <c:choose>
                                        <c:when test="${d.acepta eq true}">
                                            <option selected value="${d.parte.idparte}">${d.parte.parte}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${d.parte.idparte}">${d.parte.parte}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <span class="error">Seleccione al menos una opción.</span>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="person">Recurso:</label>
                            <span class="required text-danger"> * </span>
                            <select name="person" id="person" class="form-control">
                                <option selected value=""><spring:message code="select"/>...</option>
                                <c:forEach items="${person}" var="p">
                                    <c:choose>
                                        <c:when test="${p.codigo eq obj.personal.codigo}">
                                            <option selected value="${p.codigo}"><spring:message code="${p.idPersona} - ${p.nombre}" /></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${p.codigo}"><spring:message code="${p.idPersona} - ${p.nombre}" /></option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <span class="error">Seleccione el recurso.</span>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="asentimiento">Asentimiento: </label>
                            <span class="required text-danger"> * </span>
                            <select name="asentimiento" id="asentimiento" class="form-control">
                                <option selected value=""><spring:message code="select" />...</option>
                                <c:forEach items="${scanca}" var="sc">
                                    <c:choose>
                                        <c:when test="${sc.catKey eq obj.asentimiento}">
                                            <option selected value="${sc.catKey}"> <spring:message code="${sc.spanish}" />  </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${sc.catKey}"> <spring:message code="${sc.spanish}" />  </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <span class="error">Seleccione Asentimiento.</span>
                        </div>
                    </div>


                    <div class="col-md-4">
                        <div id="DivtipoAsent">
                        <div class="form-group">
                            <label for="tipoasentimiento">Tipo Asentimiento: </label>
                            <select name="tipoasentimiento" id="tipoasentimiento" class="form-control">
                                <option selected value=""><spring:message code="select" />...</option>
                                <c:forEach items="${tpoasent}" var="ta">
                                    <c:choose>
                                        <c:when test="${ta.catKey eq obj.tipoasentimiento}">
                                            <option selected value="${ta.catKey}"><spring:message code="${ta.spanish}" /></option>
                                        </c:when>
                                    </c:choose>
                                    <option value="${ta.catKey}"><spring:message code="${ta.spanish}" /></option>
                                </c:forEach>
                            </select>
                            <span class="error">Seleccione tipo asentimiento.</span>
                        </div>
                        </div>
                    </div>


                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="proyecto">Proyecto: </label>
                            <span class="required text-danger"> * </span>
                            <select name="proyecto" id="proyecto" class="form-control">
                                <option selected value=""><spring:message code="select" />...</option>
                                <c:forEach items="${proyecto}" var="p">
                                    <c:choose>
                                        <c:when test="${p.catKey eq obj.proyecto}">
                                            <option selected value="${p.catKey}"> <spring:message code="${p.spanish}" />  </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${p.catKey}"> <spring:message code="${p.spanish}" />  </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <span class="error">Seleccione el proyecto.</span>
                        </div>
                    </div>
                </div>
            </div>

            <div id="step-3">
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="border-bottom border-gray pb-2" style="font-family: Roboto">
                            <i class="fa fa-user-circle-o" aria-hidden="true"></i> Datos del Tutor.</h3>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="nombfirma">1er. Nombre:</label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control" id="nombfirma" name="nombfirma" value="${obj.quienfirma}"/>
                            <span class="error">1er. Nombre es requerido.</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="nombre2Firma">2do. Nombre:</label>
                            <input type="text" class="form-control" id="nombre2Firma" name="nombre2Firma" value="${obj.nombre2Firma}">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="apellido1Firma">1er. Apellido:</label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control" id="apellido1Firma" name="apellido1Firma" value="${obj.apellido1Firma}">
                            <span class="error">1er. Apellido es requerido.</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="nombre2Firma">2do. Apellido:</label>
                            <input type="text" class="form-control" id="apellido2Firma" name="apellido2Firma" value="${obj.apellido2Firma}">
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="relfam">Relación Familiar: </label>
                            <span class="required text-danger"> * </span>
                            <select name="relfam" id="relfam" class="form-control">
                                <option selected value=""><spring:message code="select" />...</option>
                                <c:forEach items="${relFam}" var="rel">
                                    <c:choose>
                                        <c:when test="${rel.catKey eq obj.relfam}">
                                            <option selected value="${rel.catKey}"> <spring:message code="${rel.spanish}" />  </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${rel.catKey}"> <spring:message code="${rel.spanish}" />  </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <span class="error">Selecciona el tipo de relación</span>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="observacion">Observación:</label>
                            <input type="text" class="form-control" id="observacion" name="observacion" value="${obj.observacion}">
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="custom-control custom-checkbox">
                            <c:choose>
                                <c:when test="${obj.esContacto eq true}">
                                    <input type="checkbox" class="custom-control-input" value="${obj.contactoFuturo}" checked="checked" name="contactoFuturo" id="contactoFuturo">
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" class="custom-control-input" value="1" name="contactoFuturo" id="contactoFuturo">
                                </c:otherwise>
                            </c:choose>
                            <label class="custom-control-label" for="contactoFuturo">Contacto Futuro</label>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="custom-control custom-checkbox">
                            <c:choose>
                                <c:when test="${obj.esRetirado eq true}">
                                    <input type="checkbox" id="retiro" value="${obj.retirado}" name="retiro" checked="checked" class="custom-control-input"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="retiro" name="retiro" class="custom-control-input"/>
                                </c:otherwise>
                            </c:choose>
                            <label class="custom-control-label" for="retiro">Retirado</label>
                        </div>
                    </div>
                </div>
                <br/>
                <br/>
                <div class="row">
                    <hr/>
                    <div class="col-md-4">
                        <button type="button" id="btnUpdate" class="btn btn-info btn-block btn-lg">
                            <i class="fa fa-refresh" aria-hidden="true"></i>
                            Actualizar
                        </button>
                    </div>
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4">
                      <!--  <button type="button" id="btnCancel" class="btn btn-default btn-block btn-lg">
                            <i class="fa fa-minus-circle" aria-hidden="true"></i>
                            Cancelar
                        </button> -->
                        <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                            <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                            <spring:message code="Cancelar" /></a>
                    </div>
                    <br/>
                    <br/>
                </div>


            </div>
            </div>
            </div>

            </form>

            </div>
            </div>

            <!-- fin test smarthwizard -->
            </div> <!-- animated fadeIn -->
        </div><!-- /.conainer-fluid -->
    </div> <!-- Main -->
</div> <!-- app-boddy -->
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/views/Cartas/EditCartas.js" var="editcartas"/>
<script type="text/javascript" src="${editcartas}"></script>


<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>


<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>

<script>
    $(document).ready(function(){
        $("#carta").select2();
        $("#version").select2();
        $("#person").select2();
        $("#asentimiento").select2();
        $("#tipoasentimiento").select2();
        $("#proyecto").select2();
        setTimeout(function () {
            $('#page-loader').fadeOut('slow');
        }, 1300);
        /*
        loadSelect();
        function loadSelect(){
            if($("#asentimiento").val() ==1){
                $("#DivtipoAsent").show();
                $("#tipoasentimiento").attr("required", "true");
            }else{
                $("#DivtipoAsent").hide();
                $("#tipoasentimiento").val("").attr("required", "false");
            }
        }
        */
        var parametros ={
            VersionCartatUrl: "${VersionCartatUrl}",
            ParteVersionUrl:"${ParteVersionUrl}",
            UpdateAllUrl:"${UpdateAllUrl}",
            Lista2AllUrl:"${Lista2AllUrl}"
        };

        $("#btnUpdate").on("click", function(e){
            e.preventDefault();
            var isValidItem = true;
            $('#error').empty();
            if($("#codigo").val().trim() == "" || $("#codigo").val()== null){
                $('#codigo').siblings('span.error').css('visibility', 'visible');
                $('#codigo').parents('.form-group').addClass('has-danger');
                $('#codigo').focus();
                isValidItem = false;
            }else{
                $('#codigo').siblings('span.error').css('visibility', 'hidden');
                $('#codigo').parents('.form-group').removeClass('has-danger');
            }

            if($("#fechacarta").val() == "" || $("#fechacarta").val()== null){
                $('#fechacarta').siblings('span.error').css('visibility', 'visible');
                $('#fechacarta').parents('.form-group').addClass('has-danger');
                isAllValid = false;
            }
            else{
                $('#fechacarta').siblings('span.error').css('visibility', 'hidden');
                $('#fechacarta').parents('.form-group').removeClass('has-danger');
            }
            if ($('#nombfirma').val().trim() == "" || $("#nombfirma").val().trim() == null) {
                $('#nombfirma').siblings('span.error').css('visibility', 'visible');
                $('#nombfirma').parents('.form-group').addClass('has-danger');
                isAllValid = false;
            }
            else {
                $('#nombfirma').siblings('span.error').css('visibility', 'hidden');
                $("#nombfirma").parents('.form-group').removeClass('has-danger');
            }
            if($("#apellido1Firma").val().trim() == "" || $("#apellido1Firma").val().trim() == null){
                $('#apellido1Firma').siblings('span.error').css('visibility', 'visible');
                $('#apellido1Firma').parents('.form-group').addClass('has-danger');
                isValidItem = false;
            }else{
                $('#apellido1Firma').siblings('span.error').css('visibility', 'hidden');
                $("#apellido1Firma").parents('.form-group').removeClass('has-danger');
            }

            if ($('#relfam').val() == null || $('#relfam').val() == "") {
                $('#relfam').siblings('span.error').css('visibility', 'visible');
                $('#relfam').parents('.form-group').addClass('has-danger');
                isAllValid = false;
            }
            else {
                $('#relfam').siblings('span.error').css('visibility', 'hidden');
            }

            if($("#carta").val().trim() == "" || $("#carta").val().trim() == null){
                isValidItem = false;
                $('#carta').siblings('span.error').css('visibility', 'visible');
            }
            else{
                $('#carta').siblings('span.error').css('visibility', 'hidden');
            }

            if($("#version").val().trim() == "" || $("#version").val().trim() == null){
                isValidItem = false;
                $('#version').siblings('span.error').css('visibility', 'visible');
                $('#version').parents('.form-group').addClass('has-danger');
            }
            else{
                $('#version').siblings('span.error').css('visibility', 'hidden');
            }
            if($("#person").val()=="" || $("#person").val()== null){
                isValidItem = false;
                $('#person').siblings('span.error').css('visibility', 'visible');
                $('#person').parents('.form-group').addClass('has-danger');
            }
            else{
                $('#person').siblings('span.error').css('visibility', 'hidden');
            }
            if($("#asentimiento").val() == "" || $("#asentimiento").val() == null){
                isValidItem = false;
                $('#asentimiento').siblings('span.error').css('visibility', 'visible');
                $('#asentimiento').parents('.form-group').addClass('has-danger');
            }else{
                $('#asentimiento').siblings('span.error').css('visibility', 'hidden');
            }

            if($("#proyecto").val()=="" || $("#proyecto").val()== null){
                $('#proyecto').siblings('span.error').css('visibility', 'visible');
                $('#proyecto').parents('.form-group').addClass('has-danger');
                isValidItem = false;
            }else{
                $('#proyecto').siblings('span.error').css('visibility', 'hidden');
            }

            if($("#tipoasentimiento").val()=="" || $("#tipoasentimiento").val()== null){
                $('#tipoasentimiento').siblings('span.error').css('visibility', 'visible');
                $('#tipoasentimiento').parents('.form-group').addClass('has-danger');
                isValidItem = false;
            }else{
                $('#tipoasentimiento').siblings('span.error').css('visibility', 'hidden');
            }
            var num = $("#partes").select2().val();
            if($.isEmptyObject(num)){
                $('#partes').siblings('span.error').css('visibility', 'visible');
                $('#partes').parents('.form-group').addClass('has-danger');
                isValidItem = false;
            }else{
                $('#partes').siblings('span.error').css('visibility', 'hidden');
            }

                if(isValidItem){
                    var MyArrayPartes=[];
                    $("#partes option").each(function(index, value){
                        var objPartes ={};
                        objPartes.idparte = parseInt($(this).val());
                        objPartes.acepta = value.selected;
                        MyArrayPartes.push(objPartes);
                    });
                    var text = $("#person option:selected").html();
                    var separador ="-";
                    var textoseparado = text.split(separador);
                    data = {
                        codigo: parseInt($("#codigo").val().trim()),
                        version: parseInt($("#version").val().trim()),
                        asentimiento: $("#asentimiento").val().trim(),
                        relfam: parseInt($("#relfam").val().trim()),
                        nombfirma: $("#nombfirma").val().trim(),
                        nombre2Firma: $("#nombre2Firma").val().trim(),
                        apellido1Firma: $("#apellido1Firma").val().trim(),
                        apellido2Firma: $("#apellido2Firma").val().trim(),
                        person: parseInt($("#person").val().trim()),
                        fechacarta: $("#fechacarta").val(),
                        proyecto: $("#proyecto").val(),
                        contactoFuturo: ($('input:checkbox[name=contactoFuturo]').prop('checked') == true) ? '1' : '0',
                        retiro: ($('input:checkbox[name=retiro]').prop('checked') == true) ? '1' : '0',
                        observacion :$("#observacion").val().trim(),
                        idparticipante: parseInt($("#idparticipante").val().trim()),
                        recurso: textoseparado[0],
                        tipoasentimiento:$("#tipoasentimiento").val().trim(),
                        parte: MyArrayPartes
                    };
                    ActualizarScan(data);
                }
        })
        function ActualizarScan(obj){
            console.log(obj);
            $.ajax({
                url:  parametros.UpdateAllUrl,//'/estudios_ics/cartas/UpdateAll/',
                type: "POST",
                data: JSON.stringify(obj),
                dataType: "JSON",
                contentType: "application/json",
                success: function(d){
                    swal("Éxito!", "Información actualizada!", "success")
                    window.setTimeout(function(){
                         window.location.href = parametros.Lista2AllUrl;
                    }, 1300);
                },error: function(jqXHR, textStatus,e){
                    var myAlert="";
                    myAlert += "<div class='alert alert-danger alert-dismissible fade show' role='alert'>";
                    myAlert += "<strong>Error!</strong> contacte a su proveedor.";
                    myAlert += "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>";
                    myAlert += "<span aria-hidden='true'>&times;</span>";
                    myAlert += "</button>";
                    myAlert += "</div>";
                    $('#error').html(myAlert).show();
                }
            });
        }
        var text = "";
        var rela = $("#relacionFam").val();
        switch (rela){
            case "1":
                text = "Madre";
                break;
            case "2":
                text = "Padre";
                break;
            case "3":
                text="Abuelo(a)";
                break;
            case "4":
                text ="Tio(a)";
                break;
            case "5":
                text ="Hermano(a)";
                break;
            case "6":
                text="Otra Relación Familiar";
                break;
            default:
                text = "Participante";
        }
        $("#relacionFam").val(text);
        $('#partes').select2({
            placeholder: '-- Selecciona Parte --'
        });
        $("#version").select2({
            placeholder: '-- Selecciona Versión --'
        });


        $("#carta").on("change", function(){
            $("#version option").remove();
            $("#version").select2('val', '');
                ObtenerVersion(parametros);
        });
        function ObtenerVersion(parametros){
            var idcarta = document.getElementById('carta').value;
            var $version = $('#version');
            $version.empty();
            $.getJSON(parametros.VersionCartatUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
                var len = data.objV.length;
                if(len == 0){
                    alert("Advertencia, Carta no tiene Versión.");
                }else{
                    var d = data.objV;
                    $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                    $.each(d, function (i, val) {
                        $version.append($('<option></option>').val(val.idversion).html(val.version));
                    });
                }
            });
        }

        $("#version").on("change", function(){
            ObtenerParte(parametros);
            $("#partes").select2().val(null).trigger("change");
            $("#partes").select2({placeholder: "Selecciona Parte"});
            $('#partes').empty();
            $("#partes").val(null).trigger('change');
            $('#partes').find('option').remove() ;
        })



        var elementos2=[];
        function ObtenerParte(parametros){
            var idversion = document.getElementById('version').value;
            var $version = $('#version');
            var $ele = $("#partes");
            $.getJSON(parametros.ParteVersionUrl,{idversion : idversion, ajax:'true'}, function(data){
                /*elementos2=[];
                for(var i=0; i < data.parte.length; i++){
                    var obj = {};
                    obj.idparte = parseInt(data.parte[i].idparte);
                    obj.acepta =  (data.parte[i].acepta == "true") ? true : false;
                    elementos2.push(obj);
                }*/
                if(data.parte.length > 0){
                    bandera=true;
                    //var myPartes="";
                    $.each(data.parte, function (i, val) {
                        $ele.append($('<option/>').val(val.idparte).text(val.parte));
                    })
                }else{
                    $ele.empty();
                }
            })
        }
        $("#partes").change(function (e) {
            if (e.added != null){
                seleccionar(e.added.id);
            }
            if (e.removed != null){
                deseleccionar(e.removed.id);
            }
        });
        function seleccionar(id){
            //debugger;
            var cod = parseInt(id);
            for (var i = 0; i < elementos2.length; i++) {
                if (elementos2[i].idparte === cod){
                    elementos2[i].acepta = true;
                    break;
                }
            }
        }
        function deseleccionar(id){
            //debugger;
            var cod = parseInt(id);
            for (var i = 0; i < elementos2.length; i++) {
                if (elementos2[i].idparte === cod){
                    elementos2[i].acepta = false;
                    break;
                }
            }
        }

    });
</script>
</body>
</html>
