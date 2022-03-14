<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 02/02/2022
  Time: 02:00 PM
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
    <spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
    <link href="${uiCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

    <style>
        .form-control:disabled, .form-control[readonly] {
            background-color: #eee0;
            opacity: 1;
            cursor: no-drop;
        }
        .fondowhite{
            background-color: #fff;
            text-align: center;
        }
        .borderPbmc{
            border: 1px solid #11BDF7;
        }
        .borderRojo{
            border: 1px solid #ff0000;
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
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
                <spring:url value="/Pbmc/searchParticipant" var="searchPartUrl"/>
                <spring:url value="/Pbmc/GuardarPbmc" var="savePbmctUrl"/>
                <spring:url value="/Pbmc/recepcion" var="reloadUrl"/>
                <spring:url value="/Pbmc/getObservaciones" var="getObservacionesUrl"/>
                <spring:url value="/Pbmc/listPbmc" var="listPbmcUrl"/>
                <div class="container col-md-10">
                    <div class="card">
                        <div class="card-header">
                            <div class="container ml-0">
                                <button type="button" class="btn btn-primary mt-1 float-right">
                                    <spring:message code="Total" /> <spring:message code="Pbmc" />:
                                    &nbsp; &nbsp;
                                    <span id="contadorPbmc" class="badge badge-light">${CuentaPbmc}</span>
                                </button>
                                <h4 class="float-left mt-1">
                                    <i class="fa fa-flask" aria-hidden="true"></i>
                                    <c:choose>
                                        <c:when test="${agregando}">
                                            <spring:message code="add" />
                                        </c:when>
                                        <c:otherwise>
                                            <spring:message code="edit" />
                                        </c:otherwise>
                                    </c:choose>
                                    <spring:message code="Recepción" /> <spring:message code="sample" />  <spring:message code="PBMC" />
                                </h4>
                            </div>
                        </div>
                        <div class="card-body">
                            <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                                <div class="row">
                                    <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="well search-result">
                                            <c:choose>
                                                <c:when test="${editando}">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control form-control-lg" disabled="disabled"  placeholder="Ingrese el código" id="parametro1" name="parametro1">
                                                          <span class="input-group-btn">
                                                              <button id="btnBuscar1" class="btn btn-info btn-lg" disabled="disabled" type="submit">
                                                                  <i class="fa fa-search" aria-hidden="true"></i>
                                                              </button>
                                                          </span>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="input-group">
                                                        <input type="text" class="form-control form-control-lg"  placeholder="Ingrese el código" id="parametro" name="parametro" tabindex="1">
                                                          <span class="input-group-btn">
                                                              <button id="btnBuscar" class="btn btn-info btn-lg" type="submit">
                                                                  <i class="fa fa-search" aria-hidden="true"></i>
                                                              </button>
                                                          </span>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            <div id="gendererror" class="text-danger"></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <hr/>
                            <form class="form-horizontal" name="save-pbmc-form" id="save-pbmc-form" autocomplete="off">
                                <div class="form-row" hidden="hidden">
                                    <div class="col-md-4">
                                        <label for="codigo_pbmc">codigo_pbmc</label>
                                        <input id="codigo_pbmc" name="codigo_pbmc" type="text" class="form-control" value="${caso.codigo_pbmc}"/>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="idSerologia">idSerologia</label>
                                        <input id="idSerologia" name="idSerologia" type="text" class="form-control" value="${caso.id_serologia}"/>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="fecha">Fecha Nacimiento</label>
                                        <input type="text" class="form-control" id="fechaNac" name="fechaNac"  value="<fmt:formatDate value="${caso.fechaNacimiento}"  pattern="yyyy-MM-dd"/>"  >
                                        <small id="fechaHelpInline" class="text-muted"> yyyy-mm-dd.</small>
                                    </div>
                                    <div class="col-md-3">
                                        <label for="edadEnMeses">edadEnMeses</label>
                                        <input type="text" class="form-control" id="edadEnMeses" name="edadEnMeses" value="${caso.edadEnMeses}"  />
                                    </div>
                                    <div class="col-md-3">
                                        <label for="editando">tiporequest</label>
                                        <input type="text" class="form-control" id="editando" name="editando" value="${editando}" />
                                    </div>
                                    <div class="col-md-3">
                                        <label for="codigo_casa_PDCS">codigo_casa_PDCS</label>
                                        <input type="text" class="form-control" id="codigo_casa_PDCS" name="codigo_casa_PDCS" value="${caso.codigo_casa_PDCS}" />
                                    </div>
                                    <div class="col-md-3">
                                        <label for="codigo_casa_familia">codigo_casa_familia</label>
                                        <input type="text" class="form-control" id="codigo_casa_familia" name="codigo_casa_familia" value="${caso.codigo_casa_familia}" />
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-2">
                                        <label for="codigo_participante"><spring:message code="code" /></label>
                                        <input type="text" class="form-control" id="codigo_participante" name="codigo_participante" value="${caso.codigo_participante}" style="text-align: center; font-family: Roboto" readonly/>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="estudios"><spring:message code="userstudies" /></label>
                                        <input type="text" class="form-control" name="estudios" id="estudios" value="${caso.estudios}" style="text-align: center" readonly />
                                    </div>

                                    <div class="form-group col-md-1">
                                        <label for="edadA"><spring:message code="Años" /></label>
                                        <input type="text" class="form-control" name="edadA" id="edadA" value="${caso.edadA}" readonly>
                                    </div>

                                    <div class="form-group col-md-1">
                                        <label for="edadM"><spring:message code="Meses" /></label>
                                        <input type="text" class="form-control" name="edadM" id="edadM" value="${caso.edadM}" readonly>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="estado"><spring:message code="Estado" /></label>
                                        <input type="text" class="form-control" name="estado" id="estado" value="${caso.estado}" style="text-align: center;" readonly>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="card">
                                            <div class="card-header">
                                                <div class="form-inline float-right">
                                                    <div class="input-group mb-2 mr-sm-2 borderPbmc">
                                                        <div class="input-group-prepend">
                                                            <div class="input-group-text"><spring:message code="Volumen" /> <spring:message code="sugerido" /> <spring:message code="Pbmc" />:</div>
                                                        </div>
                                                        <input type="text" class="form-control fondowhite borderPbmc" data-toggle="tooltip" data-placement="top" title="Volumen sugerido Pbmc" name="volumen_pbmc_desde_bd" id="volumen_pbmc_desde_bd" readonly="readonly"  value="${caso.volumen_pbmc_desde_bd}" >
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                        <label for="fecha"><spring:message code="lbl.date" /></label>
                                                        <input type="text" class="form-control date-picker" value="${caso.fecha}" id="fecha" name="fecha" data-date-end-date="+0d"/>
                                                        <small id="fechaHelpInline1" class="text-muted"> dd/mm/yyyy.</small>
                                                    </div>

                                                    <div class="form-group col-md-6">
                                                        <label for="volumen_pbmc"><spring:message code="Pbmc" />:</label>
                                                        <input type="text" name="volumen_pbmc" id="volumen_pbmc" class="form-control focusNext" placeholder="Volumen" value="${caso.volumen_pbmc}" required="required" tabindex="2">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="card">
                                            <div class="card-header">
                                                <div class="form-inline">
                                                    <div class="input-group mb-2 mr-sm-2 borderRojo">
                                                        <div class="input-group-prepend">
                                                            <div class="input-group-text"><spring:message code="Volumen" /> <spring:message code="sugerido" />  <spring:message code="Rojo" /> <spring:message code="Adicional" /> :</div>
                                                        </div>
                                                        <input id="volumen_adicional_desde_bd" data-toggle="tooltip" data-placement="top" title="Volumen sugerido Rojo" name="volumen_adicional_desde_bd" type="text" class="form-control fondowhite borderRojo" readonly="readonly" value="${caso.volumen_adicional_desde_bd}"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <div class="form-row pb-2">
                                                        <div class="form-group col-md-12">
                                                            <label for="volumen_rojo_adic"><spring:message code="Volumen" />  <spring:message code="Adicional" />:</label>
                                                            <input type="text"  class="form-control focusNext" id="volumen_rojo_adic" name="volumen_rojo_adic" value="${caso.volumen_rojo_adic}" tabindex="3" placeholder="Volumen Rojo"/>
                                                            <small class="text-muted"> &nbsp;</small>
                                                        </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="observacion"><spring:message code="observacion" /></label>
                                        <textarea class="form-control" id="observacion" name="observacion" rows="2">${caso.observacion}</textarea>
                                        <div class="invalid-feedback">
                                            <spring:message code="Este campo es obligatorio." />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-md-4">
                                        <button type="submit" class="btn btn-primary btn-block btn-lg focusNext" tabindex="4">
                                            <i class="fa fa-save"  aria-hidden="true"></i>
                                            <spring:message code="save" />
                                        </button>
                                    </div>
                                    <div class="col-md-4"></div>
                                    <div class="col-md-4">
                                        <a href="${fn:escapeXml(listPbmcUrl)}" class="btn btn-warning btn-block btn-lg">
                                            <i class="fa fa-minus-circle" aria-hidden="true"></i>
                                            <spring:message code="cancel" /></a>
                                    </div>
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
<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>
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

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/views/Pbmc/processPbmc.js" var="processPbmc" />
<script src="${processPbmc}"></script>

<script>
    jQuery(document).ready(function(){
        var parameters = {
            "searchPartUrl"  : "${searchPartUrl}",
            "successMessage" : "${successMessage}",
            "error"          : "${errorProcess}",
            "savePbmctUrl"   : "${savePbmctUrl}",
            "listPbmcUrl"    : "${listPbmcUrl}",
            "reloadUrl"      : "${reloadUrl}",
            "getObservacionesUrl":"${getObservacionesUrl}"
        };
        handleDatePickers("${lenguaje}");
        processPbmc.init(parameters);
        moment.suppressDeprecationWarnings = true;
        $("#fecha").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));


        $("#tieneRojoAdicional").on("click", function(){
            var status = $(this).prop("checked");
            if(status == true){
                $("#div_VolumenRojo").fadeIn("slow");
                $("#volumen_rojo_adic").prop('required', true).focus();
            }else{
                $("#div_VolumenRojo").fadeOut("slow");
                $("#volumen_rojo_adic").prop('required', false);
            }
        });

        document.addEventListener('keypress', function(evt) {
            // Si el evento NO es una tecla Enter
            if (evt.key !== 'Enter') {
                return;
            }
            let element = evt.target;
            // Si el evento NO fue lanzado por un elemento con class "focusNext"
            if (!element.classList.contains('focusNext')) {
                return;
            }
            // AQUI logica para encontrar el siguiente
            let tabIndex = element.tabIndex + 1;
            var next = document.querySelector('[tabindex="'+tabIndex+'"]');
            // Si encontramos un elemento
            if (next) {
                next.focus();
                event.preventDefault();
            }
        });

        $("#parametro").focus();
    })
</script>
</body>
</html>