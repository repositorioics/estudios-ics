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
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
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

        span.error {
            display:block;
            visibility:hidden;
            color:red;
            font-size:90%;
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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/Serologia/listSerologia/" htmlEscape="true "/>"><spring:message code="List" /></a>
                <i class="fa fa-angle-right"></i>
                <spring:message code="Form" />
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
              <div class="container">
                  <div class="card">
                      <div class="card-header">
                          <h3 class="page-title">
                              Serologia -
                              <small>
                                  <c:choose>
                                      <c:when test="${agregando}">
                                          <spring:message code="add" />
                                      </c:when>
                                      <c:otherwise>
                                          <spring:message code="edit" />
                                      </c:otherwise>
                                  </c:choose>
                              </small>
                          </h3>
                          <button id="btnmyModal"  class="btn btn-primary pull-right"><i class="fa fa-plus"></i> <spring:message code="lbl.new.entry" /></button>
                      </div>
                      <div class="card-body">
                          <spring:url value="/Serologia/GuardarSerologia" var="saveFormUrl"/>
                          <spring:url value="/Serologia/GuardarNuevaSerologia" var="GuardarNuevaSerologiaUrl"/>
                          <spring:url value="/Serologia/searchParticipant" var="searchPartUrl"/>
                          <spring:url value="/Serologia/listSerologia/" var="listaUrl"/>
                          <spring:url value="/Serologia/BuscarUltGradilla/" var="ultGradillaUrl"/>
                          <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                          <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
                          <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                              <div class="row">
                                  <div class="form-group col-sm-12 col-md-6 col-lg-12">
                                      <label><spring:message code="participant.code" /></label>
                                      <input type="text" class="form-control" placeholder="Ingrese el código" id="parametro" name="parametro" tabindex="1">
                                      <div id="gendererror" class="text-danger"></div>
                                  </div>
                              </div>
                          </form>

                          <form class="form-horizontal" name="save-Serologia-form" id="save-Serologia-form" autocomplete="off">
                              <div hidden="hidden" class="form-row">
                                  <div class="col-md-3">
                                      <input id="idSerologia" name="idSerologia" type="text" class="form-control" value="${caso.idSerologia}"/>
                                  </div>
                                  <div class="col-md-3">
                                      <input type="text" class="form-control" id="idParticipante" name="idParticipante" value="${caso.participante.codigo}" >
                                  </div>

                                  <div class="col-md-3">
                                      <input type="text" class="form-control" id="edadMeses" name="edadMeses" value="${caso.edadMeses}"  />
                                  </div>
                                  <input type="text" class="form-control" id="tiporequest" name="tiporequest" value="${editando}" />
                                  <input type="text" class="form-control" id="totalGradilla" name="totalGradilla" value="50"  />

                                  <div class="col-md-12">
                                      <label for="fecha">Fecha Nacimiento</label>
                                      <input type="text" class="form-control" id="fechaNac" name="fechaNac"  value="<fmt:formatDate value="${caso.participante.fechaNac}"  pattern="yyyy-MM-dd"/>"  >
                                      <small id="fechaHelpInline" class="text-muted"> yyyy/mm/dd.</small>
                                  </div>
                              </div>


                              <div   class="form-row">
                                  <div class="form-group col-md-6">
                                      <label for="nombreCompleto"><spring:message code="lbl.names.surnames" /></label>
                                      <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" value="${caso.participante.nombreCompleto}"  disabled="disabled">
                                  </div>
                                  <div class="form-group col-md-6">
                                      <label for="estudios"><spring:message code="userstudies" /></label>
                                      <input type="text" class="form-control" name="estudios" id="estudios"  value="${caso.estudio}" readonly>
                                  </div>

                                  <div class="form-group col-md-6">
                                      <label for="casaCHF"><spring:message code="chf.house" /></label>
                                      <input type="text" class="form-control" name="casaCHF" id="casaCHF" value="${caso.casaCHF}" readonly>
                                  </div>

                                  <div class="form-group col-md-6">
                                      <label for="nombreCompleto"><spring:message code="lbl.age" /></label>
                                      <input type="text" class="form-control" id="edadPart" name="edadPart" value="${caso.participante.edad}"  disabled="disabled">
                                      <small id="edadParthelp" class="text-muted"> Años/Meses.</small>
                                  </div>

                              </div>

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
                                  </div>

                              </div>

                              <div class="form-row">
                                  <div class="form-group col-md-12">
                                      <label for="observacion"><spring:message code="observacion" /></label>
                                      <input type="text" class="form-control" id="observacion" name="observacion" >
                                  </div>
                                <!--  <div class="form-group col-sm-6">
                                      <label for="precepciona">Recepciona:</label>
                                      <span class="required text-danger"> * </span>
                                      <select name="precepciona" id="precepciona" required class="form-control focusNext" tabindex="4">
                                          <option selected value=""><spring:message code="select" />...</option>
                                          <c:forEach items="${personaValida}" var="person">
                                              <c:choose>
                                                  <c:when test="${person.idPersona eq caso.precepciona}">
                                                      <option selected value="${person.idPersona}"> <spring:message code="${person.idPersona} - ${person.nombre}" /></option>
                                                  </c:when>
                                                  <c:otherwise>
                                                      <option value="${person.idPersona}"> ${person.idPersona} - ${person.nombre}</option>
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:forEach>
                                      </select>
                                  </div> -->

                              </div>

                              <div class="form-row">
                                  <div class="col-md-4">
                                      <button type="submit" class="btn btn-primary btn-block btn-lg">
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
            <!--modal -->

            <div class="modal fade" id="exampleModalNew" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"><i class="fa fa-user-plus"></i> Nuevo Ingreso</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="#" id="FormnuevoIngreso" name="FormnuevoIngreso">

                                <div class="form-group">
                                    <input type="text" class="form-control date-picker" id="fechaNuevoIng" name="fechaNuevoIng" />
                                </div>
                                <div class="form-group">
                                    <label for="txtNewParticipante" class="col-form-label">Código Nuevo:</label>
                                    <input type="text" class="form-control" name="txtNewParticipante" id="txtNewParticipante">
                                    <span class="error text-danger">Código Requerido.</span>
                                </div>

                                <div class="form-group">
                                    <label for="txtvolumen" class="col-form-label">Volumen:</label>
                                    <input type="text" class="form-control" id="txtvolumen" name="txtvolumen">
                                    <span class="error text-danger">Volumen Requerido.</span>
                                </div>

                                <div class="form-group">
                                    <label for="txtObservacion" class="col-form-label">Observación:</label>
                                    <input type="text" class="form-control" id="txtObservacion" name="txtObservacion" value="Nuevo Ingreso">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">
                                <i class="fa fa-close"></i> <spring:message code="lbl.close" /></button>
                            <button type="button" id="btnGuardarNuevo" class="btn btn-success">
                                <i class="fa fa-save"></i>
                                <spring:message code="save" /> <spring:message code="lbl.new.entry" /> </button>
                        </div>
                    </div>
                </div>
            </div>
            <!--fin Modal -->
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

<spring:url value="/resources/js/libs/notify.min.js" var="noty" />
<script type="text/javascript" src="${noty}"></script>

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
        var points ={
            "searchPartUrl": "${searchPartUrl}",
            "saveFormUrl" : "${saveFormUrl}",
            ultGradillaUrl: "${ultGradillaUrl}",
            "GuardarNuevaSerologiaUrl":"${GuardarNuevaSerologiaUrl}",
            "successMessage":"${successMessage}"
        };
        Serologia2020.init(points);


        $("#volumen").on("keyup",function(){
            debugger;
            var valor = $("#volumen").val();
            var mensaje ='';
            if(valor === '12'){
                mensaje ='2 Tubos de 6 ml.'
                $("#observacion").val(mensaje);
            }else if(valor === '13'){
                mensaje = '2 Tubos de 6.5 ml.'
                $("#observacion").val(mensaje);
            }else if(valor === '14'){
                mensaje = '2 Tubos de 6.5 y 7 ml.'
                $("#observacion").val(mensaje);
            }else{
                $("#observacion").val(mensaje);
            }
        });



        $("#btngetGradilla").on("click",function(){
            getGradilla(points);

        });
        function getGradilla(endPointSero){
            debugger;
            $.getJSON(endPointSero.ultGradillaUrl, { fechaInicio : $('#fecha').val(),   ajax : 'true'  }, function(data) {
                if(data != null){
                    console.log(JSON.stringify(data));
                }else{
                    $.notify("Error","error");
                }
            });
        }


        $('#btnmyModal').on("click",function() {
            $("#exampleModalNew").modal('show');
            $("#txtNewParticipante").focus();
        });

        $('#txtNewParticipante').keyup(function (e) {
            if (e.keyCode === 13) {
                $("#txtvolumen").focus();
            }
        });


        $("#btnGuardarNuevo").on("click",function(){
            var isValidItem = true;
            if (!($('#txtvolumen').val().trim() != '' && !isNaN($('#volumen').val().trim()))) {
                isValidItem = false;
                $('#txtvolumen').siblings('span.error').css('visibility', 'visible');
            }
            else {
                $('#txtvolumen').siblings('span.error').css('visibility', 'hidden');
            }
            if (!($('#txtNewParticipante').val() != '' && !isNaN($('#txtNewParticipante').val()))) {
                isValidItem = false;
                $('#txtNewParticipante').siblings('span.error').css('visibility', 'visible');
            }
            else {
                $('#txtNewParticipante').siblings('span.error').css('visibility', 'hidden');
            }

            if (isValidItem) {
                GuardarNuevoIngreso(points);
            }

        });

        function GuardarNuevoIngreso(urls){
            var FormNuevaSero = $("#FormnuevoIngreso");
        $.post(urls.GuardarNuevaSerologiaUrl, FormNuevaSero.serialize(), function (data) {
                if (data.msj != null) {
                    toastr.warning( data.msj, "ERROR!",{timeOut:6000});
                } else {
                    toastr.success(urls.successMessage, {timeOut:6000});
                    Limpiartxt();
                }
            }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                toastr.error("500 Internal Server","ERROR!",{timeOut:6000});
            });
        };
        function Limpiartxt(){
            $("#txtvolumen").val("");
            $("#txtNewParticipante").val("");
            $("#exampleModalNew").modal('hide');
        };


        var today = moment().format('YYYY-MM-DD');
        $("#fecha").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));
        $("#fechaNuevoIng").val(today);
        function actDesact(){
            var verif = "${editando}";
            if(verif === "true"){
                $("#parametro").prop("disabled", true);
            }else{
                $("#parametro").removeAttr("disabled");
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
