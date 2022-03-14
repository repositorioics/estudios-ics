<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 16/02/2022
  Time: 12:13 PM
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
    <spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
    <link href="${uiCss}" rel="stylesheet" type="text/css"/>

    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <style>
        .form-control:disabled, .form-control[readonly] {
            background-color: #eee0;
            opacity: 1;
            cursor: not-allowed;
        }
        em {
            color: #ff5454;
        }
        .borderBhc{
            border: 1px solid #B941E0;
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
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <spring:message code="Form" />
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="container col-md-10">
                    <div class="card">
                        <div class="card-header">
                            <h4> <i class="fa fa-flask" aria-hidden="true"></i>
                                <c:choose>
                                    <c:when test="${agregando}">
                                        <spring:message code="add" />
                                    </c:when>
                                    <c:otherwise>
                                        <spring:message code="edit" />
                                    </c:otherwise>
                                </c:choose>
                                <spring:message code="Recepción" /> <spring:message code="sample" />  <spring:message code="BHC" />
                            </h4>
                        </div>
                        <div class="card-body">
                            <spring:url value="/Bhc/searchParticipant" var="searchPartUrl"/>
                            <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                            <spring:url value="/Bhc/saveBhc" var="saveFormUrl"/>
                            <spring:url value="/Bhc/recepcion" var="recepcionUrl"/>
                            <spring:url value="/Bhc/list" var="listUrl"/>
                            <spring:url value="/Bhc/getObservaciones" var="getObservacionesUrl"/>
                            <c:choose>
                                <c:when test ="${editando=='true'}">
                                    <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="well search-result">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control form-control-lg"  placeholder="Ingrese el código" disabled="disabled" >
                                              <span class="input-group-btn">
                                                  <button class="btn btn-info btn-lg" type="submit" disabled="disabled">
                                                      <i class="fa fa-search" aria-hidden="true"></i>
                                                  </button>
                                              </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <div class="well search-result">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control form-control-lg"  placeholder="Ingrese el código" id="parametro" name="parametro" tabindex="1">
                                              <span class="input-group-btn">
                                                  <button id="btnBuscar" class="btn btn-info btn-lg" type="submit">
                                                      <i class="fa fa-search" aria-hidden="true"></i>
                                                  </button>
                                              </span>
                                                    </div>
                                                    <div id="gendererror" class="text-danger"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                            <hr/>
                            <form class="form-horizontal" name="save-bhc-form" id="save-bhc-form" autocomplete="off">
                                <div class="form-row" hidden="hidden">
                                    <div class="col-md-2">
                                        <label for="bhc_id">bhc_id</label>
                                        <input id="bhc_id" name="bhc_id" type="text" class="form-control" value="${caso.bhc_id}"/>
                                    </div>

                                    <div class="col-md-2">
                                        <label for="edadMeses">edadMeses</label>
                                        <input type="text" class="form-control" id="edadMeses" name="edadMeses" value="${caso.edadEnMeses}"  />
                                    </div>

                                    <div class="col-md-2">
                                        <label for="tiporequest">tiporequest</label>
                                        <input type="text" class="form-control" id="tiporequest" name="tiporequest" value="${editando}" />
                                    </div>
                                    <div class="col-md-3">
                                        <label for="fecha">Fecha Nacimiento</label>
                                        <input type="text" class="form-control" id="fechaNac" name="fechaNac"  value="<fmt:formatDate value="${caso.fechaNacimiento}"  pattern="yyyy-MM-dd"/>"  >
                                        <small id="fechaHelpInline" class="text-muted"> yyyy-mm-dd.</small>
                                    </div>
                                    <div class="col-md-3">
                                        <label for="estado">estado</label>
                                        <input type="text" class="form-control" id="estado" name="estado" value="${caso.estado}" />
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="col-md-2">
                                        <label for="idParticipante"><spring:message code="code" /></label>
                                        <input type="text" class="form-control" id="idParticipante" name="idParticipante" value="${caso.codigo_participante}" required="required" readonly="readonly"/>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="nombreCompleto"><spring:message code="lbl.names.surnames" /></label>
                                        <input type="text" class="form-control text-center" id="nombreCompleto" name="nombreCompleto" value="${caso.nombreCompleto}" disabled="disabled">
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="estudios"><spring:message code="userstudies" /></label>
                                        <input type="text" class="form-control text-center" name="estudios" id="estudios"  value="${caso.estudios}" readonly>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="card">
                                            <div class="card-body">
                                                <h5 class="card-title"><spring:message code="Casa"/></h5>
                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                        <input type="text" class="form-control" name="casaCHF" id="casaCHF" value="${caso.codigo_casa_familia}" readonly>
                                                        <small class="text-muted"> <spring:message code="chf.house" /></small>
                                                    </div>

                                                    <div class="form-group col-md-6">
                                                        <input type="text" class="form-control" name="casaPDCS" id="casaPDCS" value="${caso.codigo_casa_PDCS}" readonly>
                                                        <small class="text-muted"> <spring:message code="PDCS"/></small>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="card">
                                            <div class="card-body">
                                                <h5 class="card-title">Edad del Participante.</h5>
                                                <div class="form-row">

                                                    <div class="form-group col-md-4">
                                                        <input type="text" class="form-control" id="edad_year" name="edad_year" value="${caso.edadA}"  disabled="disabled">
                                                        <small class="text-muted"> Años.</small>
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <input type="text" class="form-control" id="edad_meses" name="edad_meses" value="${caso.edadM}"  disabled="disabled">
                                                        <small  class="text-muted"> Meses.</small>
                                                    </div>

                                                    <div class="form-group col-md-4">
                                                        <input type="text" class="form-control" id="edad_dias" name="edad_dias" value="${caso.edadD}"  disabled="disabled">
                                                        <small class="text-muted"> Dias.</small>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="col-sm-6">
                                        <div class="card">
                                            <div class="card-header">
                                                <h4><spring:message code="Volumen" /> <spring:message code="Sugerido" /></h4>
                                            </div>
                                            <div class="card-body">
                                                <div class="form-group col-md-12">
                                                <label for="fecha"><spring:message code="volumen" /></label>
                                                <input type="text" class="form-control text-center borderBhc" data-toggle="tooltip" data-placement="top" title="Volumen sugerido BHC"
                                                       id="volumen_bhc_desde_bd"
                                                       name="volumen_bhc_desde_bd" readonly="readonly"
                                                       value="${caso.volumen_bhc_desde_bd}" />
                                                    <small class="text-muted"> &nbsp;</small>
                                               </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">

                                        <div class="card">
                                            <div class="card-header">
                                                <h4><spring:message code="Muestra" /> </h4>
                                            </div>
                                            <div class="card-body">
                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                        <label for="fecha"><spring:message code="lbl.date" /></label>
                                                        <input type="text" class="form-control date-picker" id="fecha"
                                                               value="<fmt:formatDate value="${caso.fecha}" pattern="dd/MM/yyyy" />"
                                                               name="fecha" data-date-end-date="+0d" >
                                                        <small id="fechaHelpInline1" class="text-muted"> dd/mm/yyyy.</small>
                                                    </div>

                                                    <div class="form-group col-md-6">
                                                        <label for="volumen"><spring:message code="volumen" /></label>
                                                        <input type="text" name="volumen" id="volumen" class="form-control focusNext" placeholder="Volumen" value="${caso.volumen_bhc}" tabindex="2">
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <label for="observacion"><spring:message code="observacion" /></label>
                                        <textarea class="form-control" id="observacion" name="observacion">${caso.observacion} </textarea>
                                        <div class="invalid-feedback">
                                            <spring:message code="Este campo es obligatorio." />
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="col-md-4">
                                        <button type="submit" class="btn btn-primary btn-block btn-lg focusNext" tabindex="3">
                                            <i class="fa fa-save"></i>
                                            <spring:message code="save" />
                                        </button>
                                    </div>
                                    <div class="col-md-4"></div>
                                    <div class="col-md-4">
                                        <a href="${fn:escapeXml(listUrl)}" class="btn btn-warning btn-block btn-lg">
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
<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<spring:url value="/resources/js/libs/jquery.maskedinput.min.js" var="mask" />
<script type="text/javascript" src="${mask}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/Bhc/bhcForm.js" var="bhcFormJs" />
<script type="text/javascript" src="${bhcFormJs}"></script>
<script>
    $(document).ready(function(){
        var parametros={
            "searchPartUrl"  : "${searchPartUrl}",
            "successMessage" : "${successMessage}",
            "saveFormUrl"    : "${saveFormUrl}",
            "recepcionUrl"   : "${recepcionUrl}",
            "listUrl"        : "${listUrl}",
            "getObservacionesUrl":"${getObservacionesUrl}"
        }
        saveOrUpdateBhc.init(parametros);
        moment.suppressDeprecationWarnings = true;
        $("#fecha").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));



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

    });
</script>
</body>
</html>
