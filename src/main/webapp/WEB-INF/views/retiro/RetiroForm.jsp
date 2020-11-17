<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 29/10/2020
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/smartWizardCss/smarthWizardCss.css" var="smw" />
    <link href="${smw}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/smartWizardCss/smart_wizard_theme_arrows.min.css" var="smwtheme" />
    <link href="${smwtheme}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <style>
        input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; font-family: Roboto; font-size: 14 }
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
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">

                <div class="card shadow w-100 bg-white rounded">
                    <h5 class="card-header text-capitalize" style="font-family: Roboto"> <i class="fa fa-trash" aria-hidden="true"></i>
                         Retiro</h5>
                    <div class="card-body">
                        <spring:url value="/retiro/searchParticipant" var="BuscarParticipanteUrl"/>
                        <spring:url value="/retiro/GuardarRetiro" var="savePartRetiradoUrl"/>
                        <spring:url value="/retiro/getMotivo" var="MotivosUrl"/>


                        <!-- inicio smart wizard -->
                        <!-- SmartWizard html -->
                        <div id="smartwizard">

                            <ul class="nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="#step-1">
                                        <strong> <i class="fa fa-search-plus" aria-hidden="true"></i> Búscar</strong> <br>Participante
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#step-2">
                                        <strong> <i class="fa fa-list" aria-hidden="true"></i> Datos</strong> <br>Personales
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#step-3">
                                        <strong> <i class="fa fa-user-times"></i> Realizar</strong> <br>Retiro
                                    </a>
                                </li>
                            </ul>

                            <div class="tab-content">
                                <div id="step-1" class="tab-pane" role="tabpanel" aria-labelledby="step-1">
                                    <h3 class="h3 text-muted" style="font-family: Roboto">Buscar Participante</h3>
                                    <div class="container">
                                        <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                                            <div class="row">
                                                <div class="form-group col-sm-12 col-md-6 col-lg-12">
                                                    <label>Código del Participante</label>
                                                    <input type="text" class="form-control" placeholder="Ingrese el código" id="parametro" name="parametro">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div id="step-2" class="tab-pane" role="tabpanel" aria-labelledby="step-2">
                                    <h3 class="h3 text-muted" style="font-family: Roboto">Datos Personales</h3>
                                    <div class="container">

                                        <div class="form-row">

                                            <input type="text" hidden="hidden" class="form-control" id="estado" name="estado">

                                            <div class="form-group col-md-6">
                                                <label for="nombrePadre">Nombre de la Madre</label>
                                                <input type="text" class="form-control" id="nombrePadre" readonly="readonly"/>
                                            </div>


                                            <div class="form-group col-md-6">
                                                <label for="nombreMadre">Nombre del Padre</label>
                                                <input type="text" class="form-control" id="nombreMadre" readonly="readonly"/>
                                            </div>

                                            <div class="form-group col-md-6">
                                            <label for="edad">Edad del Participante</label>
                                            <input type="text" id="edad" class="form-control" readonly="readonly">
                                            <small id="passwordHelpBlock" class="form-text text-muted">
                                                Años/Meses/Días.
                                            </small>
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="nombretutor">Nombre del Tutor</label>
                                                <input type="text" class="form-control" id="nombretutor" readonly="readonly">
                                            </div>

                                        </div>

                                    </div>
                                </div>
                                <div id="step-3" class="tab-pane" role="tabpanel" aria-labelledby="step-3">
                                    <h3 class="h3 text-muted" style="font-family: Roboto">Información del Retiro</h3>
                                   <div >
                                       <form  action="#" id="retiro-participante-form" name="retiro-participante-form" autocomplete="off" class="form-horizontal">
                                           <div class="form-row">
                                               <div hidden="hidden" class="form-group col-md-4">
                                                   <label for="codigoParticipante">Código</label>
                                                   <input type="text" class="form-control" id="codigoParticipante" name="codigoParticipante" >
                                               </div>

                                               <div class="form-group col-md-8">
                                                   <label for="nombreCompleto">Nombre del Participante</label>
                                                   <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" readonly="readonly">
                                               </div>

                                               <div class="form-group col-md-2">
                                                   <label for="casaPDCS">Casa Pedíatrica</label>
                                                   <input type="text" class="form-control" id="casaPDCS" name="casaPDCS" readonly="readonly">
                                               </div>

                                               <div class="form-group col-md-2">
                                                   <label for="casaFamilia">Casa Familia</label>
                                                   <input type="text" class="form-control" id="casaFamilia" name="casaFamilia" readonly="readonly">
                                               </div>

                                           </div>


                                           <div class="form-row">

                                               <div class="form-group col-md-2">
                                                   <label for="fehaRetiro">Fecha de Retiro</label>
                                                   <input name="fehaRetiro" id="fehaRetiro" class="form-control date-picker" type="text" data-date-end-date="+0d" required="required" />
                                               </div>

                                               <div class="form-group col-md-4">
                                                   <label for="quiencomunica">Padre o Tutor con quien había comunicación sobre el retiro</label>
                                                   <input type="text" class="form-control letras" name="quiencomunica" id="quiencomunica" >
                                               </div>

                                               <div class="form-group col-md-3">
                                                   <label for="parentesco">Parentesco</label>
                                                   <select name="parentesco" id="parentesco" class="form-control">
                                                       <option selected value=""><spring:message code="select" />...</option>
                                                       <c:forEach items="${parentesco}" var="p">
                                                           <option value="${p.catKey}"> <spring:message code="${p.spanish}" /></option>
                                                       </c:forEach>
                                                   </select>
                                               </div>

                                               <div class="form-group col-md-3">
                                                   <label for="recibidaPor">Personal del Estudio quien recibe el retiro</label>
                                                   <select name="recibidaPor" id="recibidaPor" required class="form-control">
                                                       <option selected value=""><spring:message code="select" />...</option>
                                                       <c:forEach items="${supervisor}" var="rp">
                                                           <option value="${rp.idPersona}"> <spring:message code="${rp.nombre}" /></option>
                                                       </c:forEach>
                                                   </select>
                                               </div>


                                           </div>

                                           <div class="form-row">

                                               <div class="form-group col-md-4">
                                                   <label for="medicosupervisor">Personal quien llena el Documento</label>
                                                   <select name="medicosupervisor" id="medicosupervisor" required class="form-control">
                                                       <option selected value=""><spring:message code="select" />...</option>
                                                       <c:forEach items="${supervisor}" var="s">
                                                           <option value="${s.idPersona}">${s.nombre}</option>
                                                       </c:forEach>
                                                   </select>
                                               </div>

                                               <div class="form-group col-md-4">
                                                   <label for="causa">Causas de la No Participación</label>
                                                   <select name="causa" id="causa" required class="form-control">
                                                       <option selected value=""><spring:message code="select" />...</option>
                                                       <c:forEach items="${causaRetiro}" var="rz">
                                                           <option value="${rz.catKey}"> <spring:message code="${rz.spanish}" /></option>
                                                       </c:forEach>
                                                   </select>
                                               </div>

                                               <div class="form-group col-md-4">
                                                   <label for="razonretiro">Razones del Retiro</label>
                                                   <select name="razonretiro" id="razonretiro" required class="form-control">
                                                       <option selected value=""><spring:message code="select" />...</option>
                                                       <c:forEach items="${razonRetiro}" var="rz">
                                                           <option value="${rz.catKey}"> <spring:message code="${rz.spanish}" /></option>
                                                       </c:forEach>
                                                   </select>
                                               </div>

                                               <div id="bar" class="form-group col-md-12" style="display: none">
                                                   <label for="fehaRetiro">Fecha Fallecido</label>
                                                   <input name="fechaFallecido" id="fechaFallecido" class="form-control date-picker" type="text" data-date-end-date="+0d"/>
                                               </div>

                                               <div id="otherMot" class="form-group col-md-12" style="display: none">
                                                   <label for="otromotivo">Otro Motivo</label>
                                                   <input type="text" class="form-control" id="otromotivo" name="otromotivo" >
                                               </div>
                                           </div>


                                           <div class="form-row">
                                               <div class="form-group col-md-6">
                                                   <label for="aretiro">Estudio a Retirar</label>
                                                   <select name="aretiro" id="aretiro" required class="form-control">
                                                       <option selected value=""><spring:message code="select" />...</option>
                                                   </select>
                                               </div>

                                               <div class="form-group col-md-6">
                                                   <label for="observacion">Observación</label>
                                                   <input type="text" class="form-control" id="observacion" name="observacion" >
                                               </div>
                                           </div>


                                           <div class="form-group form-check">
                                               <input type="checkbox" class="form-check-input" name="devolcioCarnet" id="devolcioCarnet">
                                               <label class="form-check-label" for="devolcioCarnet">Devolvió Carnet</label>
                                           </div>


                                           <div class="form-row">
                                               <div class="form-group col-md-4">
                                                   <button type="submit" class="btn btn-primary btn-block btn-lg"> <i class="fa fa-save" aria-hidden="true"></i> Guardar Retiro</button>
                                               </div>
                                               <div class="form-group col-md-4">

                                               </div>
                                               <div class="form-group col-md-4">
                                                   <a class="btn btn-warning btn-block btn-lg"  href="<spring:url value="/retiro/saveRetiroForm" htmlEscape="true "/>">
                                                       <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                       <spring:message code="Cancelar" /></a>
                                               </div>

                                           </div>
                                       </form>
                                   </div>
                                </div>

                            </div>
                        </div>

                        <!-- SmartWizard html -->

                        <!-- fin smart wizard -->
                        <hr/>
                    </div>
                    <div class="card-footer text-muted">

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
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
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

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>


<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>


<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/notify.min.js" var="noty" />
<script type="text/javascript" src="${noty}"></script>


<spring:url value="/resources/js/views/retiro/retiro.js" var="ret"/>
<script type="application/javascript" src="${ret}"></script>


<script type="text/javascript">
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    $(document).ready(function(){
        var hoy = moment().format('DD/MM/YYYY');
        $("#parentesco").select2().prop('disabled', true).trigger('change');
        $("#relFam").select2();
        $("#recibidaPor").select2();
        $("#aretiro").select2();
        $("#medicosupervisor").select2();
        $("#razonretiro").select2();
        $("#causa").select2();
        $("#fehaRetiro").val(hoy);
        var endPoints={
            BuscarParticipanteUrl : "${BuscarParticipanteUrl}",
            savePartRetiradoUrl : "${savePartRetiradoUrl}",
            saveUrl:"${saveUrl}",
            MotivosUrl:"${MotivosUrl}"
        }
        handleDatePickers("${lenguaje}");
        RealizarRetiro.init(endPoints);
        GuardarRetiro.init(endPoints);
        // SmartWizard initialize
        $('#smartwizard').smartWizard({
            selected: 0,
            theme: 'arrows',
            lang: {  // Language variables
                next: 'Sig',
                previous: 'Prev'
            },
            keyboardSettings: {
                keyNavigation: false // Enable/Disable keyboard navigation(left and right keys are used if enabled)
               // keyLeft: [37], // Left key code
               // keyRight: [39] // Right key code
            },
            enableURLhash: false
        });
        $("#parametro").focus();
    });
</script>
</body>
</html>