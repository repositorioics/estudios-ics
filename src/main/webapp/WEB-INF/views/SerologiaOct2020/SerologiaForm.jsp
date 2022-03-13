<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 15/10/2020
  Time: 10:43
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

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
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
        .borderRojo{
            border: 1px solid #ff0000;
        }
        span.error {
            display:block;
            visibility:hidden;
            color:red;
            font-size:90%;
        }
        .form-control:disabled, .form-control[readonly] {
            background-color: #eee0;
            opacity: 1;
            cursor: not-allowed;
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
                <a href="<spring:url value="/Serologia/listSerologia/" htmlEscape="true "/>"><spring:message code="List" /></a>
                <i class="fa fa-angle-right"></i>
                <spring:message code="Form" />
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
              <div class="container col-md-10 col-lg-12">
                  <div class="card">
                      <div class="card-header">
                          <h4 class="float-left mt-1">
                              <i class="fa fa-flask text-danger" aria-hidden="true"></i>
                                  <c:choose>
                                      <c:when test="${agregando}">
                                          <spring:message code="add" />
                                      </c:when>
                                      <c:otherwise>
                                          <spring:message code="edit" />
                                      </c:otherwise>
                                  </c:choose>
                              <spring:message code="Recepción" /> <spring:message code="sample" />   <spring:message code="lbl.serologia" />
                          </h4>
                      </div>
                      <div class="card-body">
                          <spring:url value="/Serologia/GuardarSerologia" var="saveFormUrl"/>
                          <spring:url value="/Serologia/getObservaciones" var="getObservacionesUrl"/>
                          <spring:url value="/Serologia/GuardarNuevaSerologia" var="GuardarNuevaSerologiaUrl"/>
                          <spring:url value="/Serologia/searchParticipant" var="searchPartUrl"/>
                          <spring:url value="/Serologia/listSerologia/" var="listaUrl"/>
                          <spring:url value="/Serologia/create/" var="createUrl"/>
                          <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                          <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
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
                            <hr/>
                          <form class="form-horizontal" name="save-Serologia-form" id="save-Serologia-form" autocomplete="off">
                              <div class="form-row" hidden="hidden">
                                  <div class="col-md-4">
                                      <label for="idSerologia">idSerologia</label>
                                      <input id="idSerologia" name="idSerologia" type="text" class="form-control" value="${caso.idSerologia}"/>
                                  </div>

                                  <div class="col-md-4">
                                      <label for="edadMeses">edadMeses</label>
                                      <input type="text" class="form-control" id="edadMeses" name="edadMeses" value="${caso.edadMeses}"  />
                                  </div>

                                  <div class="col-md-4">
                                      <label for="tiporequest">tiporequest</label>
                                    <input type="text" class="form-control" id="tiporequest" name="tiporequest" value="${editando}" />
                                  </div>

                                  <div class="col-md-4">
                                      <label for="estado">estado</label>
                                      <input type="text" class="form-control" id="estado" name="estado" value="${caso.estado}" />
                                  </div>

                                  <div class="col-md-4">
                                      <label for="volumen_adicional_desde_bd">volumen_adicional_desde_bd</label>
                                      <input type="text" class="form-control" id="volumen_adicional_desde_bd" name="volumen_adicional_desde_bd" value="${caso.volumen_adicional_desde_bd}"  />
                                  </div>
                                  <div class="col-md-4">
                                      <label for="fecha">Fecha Nacimiento</label>
                                      <input type="text" class="form-control" id="fechaNac" name="fechaNac"  value="<fmt:formatDate value="${caso.fechaNacimiento}"  pattern="yyyy-MM-dd"/>"  >
                                      <small id="fechaHelpInline" class="text-muted"> yyyy-mm-dd.</small>
                                  </div>
                              </div>

                              <div class="form-row">
                                  <div class="col-md-2">
                                      <label for="idParticipante"><spring:message code="code" /></label>
                                      <input type="text" class="form-control" id="idParticipante" name="idParticipante" value="${caso.idparticipante}" readonly/>
                                  </div>

                                  <div class="form-group col-md-6">
                                      <label for="nombreCompleto"><spring:message code="lbl.names.surnames" /></label>
                                      <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" value="${caso.nombreCompleto}"  disabled="disabled">
                                  </div>

                                  <div class="form-group col-md-4">
                                      <label for="estudios"><spring:message code="userstudies" /></label>
                                      <input type="text" class="form-control" name="estudios" id="estudios"  value="${caso.estudios}" readonly>
                                  </div>
                                  <div class="col-sm-6">
                                      <div class="card">
                                          <div class="card-body">
                                              <h5 class="card-title"><spring:message code="Casa"/></h5>
                                              <div class="form-row">
                                                  <div class="form-group col-md-6">
                                                      <input type="text" class="form-control" name="casaCHF" id="casaCHF" value="${caso.codigo_casa_Familia}" readonly>
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
                                                      <input type="text" class="form-control" id="edad_year" name="edad_year" value="${caso.edad_year}"  disabled="disabled">
                                                      <small class="text-muted"> Años.</small>
                                                  </div>

                                                  <div class="form-group col-md-4">
                                                      <input type="text" class="form-control" id="edad_meses" name="edad_meses" value="${caso.edad_meses}"  disabled="disabled">
                                                      <small  class="text-muted"> Meses.</small>
                                                  </div>

                                                  <div class="form-group col-md-4">
                                                      <input type="text" class="form-control" id="edad_dias" name="edad_dias" value="${caso.edad_dias}"  disabled="disabled">
                                                      <small class="text-muted"> Dias.</small>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                  </div>

                              <div class="col-md-6">
                              <div class="card">
                                  <div class="card-body">
                                      <h5 class="card-title">Volumen Sugerido</h5>
                                      <div class="form-row">
                                          <div class="form-group col-md-12">
                                              <label for="volumen_serologia_desde_bd"><spring:message code="Volumen Sugerido"/></label>
                                              <input type="text" class="form-control text-center borderRojo" id="volumen_serologia_desde_bd" name="volumen_serologia_desde_bd"
                                                     data-toggle="tooltip" data-placement="top" title="Volumen sugerido"  value="${caso.volumen_serologia_desde_bd}" readonly="readonly" >
                                              <small id="fechaHelpInline2" class="text-muted"> &nbsp;</small>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                              </div>
                              <div class="col-md-6">
                                  <div class="card">
                                      <div class="card-body">
                                          <h5 class="card-title">Muestra</h5>
                                            <div class="form-row">
                                              <div class="form-group col-md-6">
                                                  <label for="fecha"><spring:message code="lbl.date" /></label>
                                                  <input type="text" class="form-control date-picker" id="fecha" name="fecha" data-date-end-date="+0d"
                                                         value="<fmt:formatDate value="${caso.fecha}" pattern="dd/MM/yyyy" />"   />
                                                  <small id="fechaHelpInline1" class="text-muted"> dd/mm/yyyy.</small>
                                              </div>

                                              <div class="form-group col-md-6">
                                                  <label for="volumen"><spring:message code="volumen" /></label>
                                                  <input type="text" name="volumen" id="volumen" class="form-control focusNext" placeholder="Volumen" value="${caso.volumen}" tabindex="2">
                                                  <small class="text-muted"> </small>
                                              </div>
                                            </div>
                                      </div>
                                  </div>
                              </div>
                      </div>
                              <div class="form-row">
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
                                      <button type="submit" class="btn btn-primary btn-block btn-lg focusNext" tabindex="3">
                                          <i class="fa fa-save"></i>
                                          <spring:message code="save" />
                                      </button>
                                  </div>
                                  <div class="col-md-4"></div>
                                  <div class="col-md-4">
                                      <a href="${fn:escapeXml(listaUrl)}" class="btn btn-warning btn-block btn-lg">
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

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/views/hemodinamica/dinamicatools.js" var="tools"/>
<script type="application/javascript" src="${tools}"></script>

<spring:url value="/resources/js/views/SerologiaOct2020/Serologia.js" var="serologia"/>
<script type="application/javascript" src="${serologia}"></script>

<script type="application/javascript">
    $(document).ready(function(){
        $('#fechaNac').mask("9999-99-99", {placeholder: 'yyyy-MM-dd' });
        actDesact();
        var today = moment().format('YYYY-MM-DD');
        moment.suppressDeprecationWarnings = true;
        $("#fecha").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));

        var points ={
            "searchPartUrl"           : "${searchPartUrl}",
            "saveFormUrl"             : "${saveFormUrl}",
            "createUrl"               : "${createUrl}",
            "successMessage"          : "${successMessage}",
            "GuardarNuevaSerologiaUrl": "${GuardarNuevaSerologiaUrl}",
            "getObservacionesUrl"       :"${getObservacionesUrl}"
        };
        SerologiaMA.init(points);


        $( "#observacion" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(points.getObservacionesUrl, {observacion: $('#observacion').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        function actDesact(){
            var verif = "${editando}";
            if(verif === "true"){
                $("#parametro").prop("disabled", true);
                $("#btnBuscar").prop("disabled", true);
            }else{
                $("#parametro").removeAttr("disabled");
                $("#btnBuscar").removeAttr("disabled");
                $("#volumen").val(0);
            }
        }


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
