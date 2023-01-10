<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <title>Formulario Detalle</title>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>


    <style>
        input[type="text"]:read-only:not([read-only="false"]) {
            color: #000000; background-color: #ffffff;
            font-family: Roboto
        }
        input[type="text"]{color: #000000; font-family: Roboto}
        input[type="select"]{color: #000000; font-family: Roboto}
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
                <a href="<spring:url value="/hemo/listado2" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(listDetailsHemoUrl)}">AGREGAR DETALLE</a>
                <i class="fa fa-angle-right"></i>
                <strong>${h.participante.nombre1} ${h.participante.nombre2} ${h.participante.apellido1} ${h.participante.apellido2}</strong>
            </li>
        </ol>
        <spring:url value="/hemo/addHemoDetalle" var="addDetalleHemoUrl"/>
        <spring:url value="/hemo/listado2" var="ListadoHemoUrl"/>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
        <spring:url value="/hemo/listDetailsHemo/{idDatoHemo}" var="listDetailsHemoUrl">
            <spring:param name="idDatoHemo" value="${idDatoHemo}" />
        </spring:url>
        <div class="container-fluid">
            <div class="container-fluid">
                <div class="animated fadeIn">
                    <div class="card text-black-50">
                        <div class="card-header">
                           <h5 style="font-family: Roboto">
                               <i class="fa fa-list"></i> <spring:message code="Detalles Hemodinámica" />
                           </h5>
                        </div>
                        <div class="card-block">
                            <form name="formSaveDetailHemo" autocomplete="off" role="form" action="#" id="formSaveDetailHemo" method="post" class="form-horizontal">
                                <div class="row">
                                    <div class="form-group col-sm-12">
                                        <h5 class="font-weight-bold" style="font-family: Roboto">Clasificación del Dengue: </h5>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" id="customRadio1" name="signo" class="custom-control-input" value="Sin signo de Alarma">
                                            <label class="custom-control-label" for="customRadio1"><spring:message code="CLASIFICACIONDENGUE_SinAlarma"></spring:message></label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" id="customRadio2" name="signo" class="custom-control-input" value="Con signo de Alarma">
                                            <label class="custom-control-label" for="customRadio2"> <spring:message code="CLASIFICACIONDENGUE_ConAlarma"></spring:message></label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" id="customRadio3" name="signo" class="custom-control-input" value="Grave">
                                            <label class="custom-control-label" for="customRadio3"> <spring:message code="CLASIFICACIONDENGUE_Grave"></spring:message></label>
                                        </div>
                                        <div id="gendererror" class="text-danger"></div>
                                    </div>
                                    <div class="form-group col-sm-12">
                                        <label for="dx">Diagnóstico:</label>
                                        <textarea name="dx" class="form-control focusNext" id="dx" cols="30" rows="2" placeholder="Ingrese el diagnóstico" tabindex="1"></textarea>
                                    </div>

                                    <div class="form-group col-sm-3">
                                        <label for="fecha">Fecha:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" data-date-end-date="+0d"  id="fecha" name="fecha" required tabindex="2">
                                    </div>
                                    <div class="form-group col-sm-3">
                                        <label for="hora">Hora:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="time" class="form-control focusNext" id="hora" name="hora" required tabindex="3">
                                    </div>
                                <div class="form-group col-sm-6">
                                    <label for="nivelConciencia">Nivel de Consciencia:</label>
                                    <span class="required text-danger"> * </span>
                                    <select class="form-control focusNext" id="nivelConciencia" name="nivelConciencia" required tabindex="4">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${nivelConciencia}" var="nivel">
                                            <option value="${nivel.catKey}">${nivel.spanish}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group col-sm-6">
                                    <label for="pa">P/S mmHg:</label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control focusNext" id="pa" name="pa" placeholder="Sistólica mmHg" required tabindex="5">
                                </div>
                                    <div class="form-group col-sm-6">
                                        <label for="pd">P/D mmHg:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" id="pd" name="pd" placeholder="Diastólica mmHg" required tabindex="6">
                                    </div>
                                        <div hidden="hidden">
                                        <div class="form-group col-sm-2">
                                            <label for="pp">PP mmHg:</label>
                                            <input type="text" class="form-control focusNext" id="pp" name="pp" placeholder="PP mmHg" readonly required >
                                        </div>
                                        <div class="form-group col-sm-2">
                                            <label for="pam">PAM mmHg:</label>
                                            <input type="text" class="form-control focusNext" id="pam" name="pam" placeholder="PAM mmHg" readonly  required >
                                        </div>
                                    </div>
                                <div class="clearfix"></div>
                                <div class="form-group col-sm-3">
                                    <label for="fc">F.C por Minuto:</label>
                                    <input type="text" class="form-control focusNext" id="fc" name="fc" placeholder="F.C por Minuto" required tabindex="7">
                                </div>
                                <div class="form-group col-sm-3">
                                    <label for="fr">F.R por Minuto:</label>
                                    <input type="text" class="form-control focusNext" id="fr" name="fr" placeholder="F.R por Minuto" required tabindex="8">
                                </div>
                                <div class="form-group col-sm-3">
                                    <label for="tc">T°C:</label>
                                    <input type="text" class="form-control focusNext" id="tc" name="tc" placeholder="T°C" tabindex="9">
                                </div>

                                <div class="form-group col-sm-3">
                                    <label for="sa">SA02%:</label>
                                    <input type="text" class="form-control focusNext" id="sa" name="sa" placeholder="SA02%"  tabindex="10">
                                </div>

                                <div class="form-group col-sm-4">
                                    <label for="extremidades">Extremidades:</label>
                                    <span class="required text-danger"> * </span>
                                    <select name="extremidades" id="extremidades" class="form-control focusNext" required tabindex="11">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${extremidades}" var="extrem">
                                            <option value="${extrem.catKey}">${extrem.spanish}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label for="llenadoCapilar">Llenado Capilar (seg):</label>
                                    <span class="required text-danger"> * </span>
                                    <select name="llenadoCapilar" id="llenadoCapilar" class="form-control focusNext" required tabindex="12"/>
                                    <option selected value=""><spring:message code="select" />...</option>
                                    <c:forEach items="${llenadoCapilar}" var="llenado">
                                        <option value="${llenado.catKey}">${llenado.spanish}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label for="pulsoCalidad">Pulso (Calidad):</label>
                                    <span class="required text-danger"> * </span>
                                    <select name="pulsoCalidad" id="pulsoCalidad" class="form-control focusNext" required tabindex="13">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${pulsoCalidad}" var="pulso">
                                            <option value="${pulso.catKey}">${pulso.spanish}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group col-sm-4">
                                    <label for="diuresis">Diuresis/ml/Kg/Hr:</label>
                                    <select name="diuresis" id="diuresis" class="form-control focusNext" tabindex="14">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${diuresis}" var="d">
                                            <option value="${d.catKey}">${d.spanish}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group col-sm-2">
                                    <label for="densidadUrinaria">Densidad Urinaria:</label>
                                    <input type="text" class="form-control focusNext" id="densidadUrinaria" tabindex="15"
                                           name="densidadUrinaria" placeholder="Densidad Urinaria">
                                </div>

                                <div class="form-group col-sm-6">
                                    <label for="personaValida">Valorado Por:</label>
                                    <span class="required text-danger"> * </span>
                                    <select name="personaValida" id="personaValida" required class="form-control focusNext" tabindex="16">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${person}" var="person">
                                            <option value="${person.personal.idpersonal}">${person.personal.idpersonal} - ${person.personal.nombreApellido}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                    <div hidden="hidden">
                                        <div class="form-group col-sm-6">
                                            <input type="text" name="idDatoHemo2" id="idDatoHemo2" value="${idDatoHemo}" class="form-control"/>
                                        </div>

                                        <div class="form-group col-sm-3">
                                            <input type="text" name="numParams" id="numParams" value="${numParameter}" class="form-control"/>
                                        </div>
                                        <div class="form-group col-sm-3">
                                            <input type="text" name="contParams" id="contParams" value="${contParams}" class="form-control"/>
                                        </div>

                                        <spring:url value="/hemo/editdetails/{idHemoDetalle}" var="editDetailsUrl">
                                            <spring:param name="idHemoDetalle" value="${idDatoHemo}" />
                                        </spring:url>
                                    <div class="form-group col-sm-12">
                                        <input type="text" name="idDatoHemo" id="idDatoHemo" value="${idDatoHemo}" class="form-control"/>
                                    </div>
                                    <div class="form-group col-sm-12">
                                        <div class="bg">
                                            <div>
                                                <div class="chiller_cb">
                                                    <input class="form-control form-check-input" type="checkbox" name="impreso" id="impreso">
                                                    <label for="impreso">Impreso</label>
                                                    <span></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <br/><br/>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <button class="btn btn-primary btn-block btn-lg"
                                                    type="submit" id="btnGuardar">
                                                <i class="fa fa-save"></i> Guardar
                                            </button>
                                        </div>
                                    </div>
                                    <div class="col-sm-4"></div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <a href="${fn:escapeXml(listDetailsHemoUrl)}" id="volver"  data-toggle="tooltip" title="Volver" data-placement="top"
                                               class="btn btn-warning btn-block btn-lg desabilitado">
                                                <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                Cancelar
                                            </a>
                                        </div>
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
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<!-- GenesisUI main scripts
<spring:url value="/resources/js/views/hemodinamica/formhemodetalle.js" var="saveDetHemoUrlJS" />
<script src="${saveDetHemoUrlJS}" type="text/javascript"></script>
-->
<spring:url value="/resources/js/libs/jquery.maskedinput.js" var="maskJs" />
<script type="text/javascript" src="${maskJs}"></script>
<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/mySelect2/select2_locale_es.min.js" var="select_esJs" />
<script type="text/javascript" src="${select_esJs}"></script>
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>
<spring:url value="/resources/js/libs/notify.min.js" var="noty" />
<script type="text/javascript" src="${noty}"></script>


<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<script>
    $( window ).bind("load",function() {
        if($("#numParams").val() === $("#contParams").val()){
            window.setTimeout(function () {
                var p = $("#contParams").val();
                toastr.info("Total de Parámetros: " +p,"Finalizado", {timeOut:6000});
                $("#btnGuardar").prop( "disabled", true );
            }, 1500);
        }else{
            $("#btnGuardar").prop( "disabled", false );
        }
        if($("#numParams").val() != $("#contParams").val()){
            $('#volver').bind("click", function (e) {
                e.preventDefault();
            });
        }
    });
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
        $("#dx").focus();
        $("#nivelConciencia").select2();
        $("#extremidades").select2();
        $("#llenadoCapilar").select2();
        $("#pulsoCalidad").select2();
        $("#personaValida").select2();
        $("#diuresis").select2();
        $("#fecha").datepicker({
            format: "dd/mm/yyyy",
            todayHighlight: true,
            todayBtn:true,
            endDate: '-0d',
            startDate: '-4y',
            autoclose: true
        });
        var parameters = {addDetalleHemoUrl: "${addDetalleHemoUrl}"};
        $.validator.addMethod('customphone', function (value, element) {
         return this.optional(element) || /^[0-9]+([/][0-9]+)?$/.test(value);
         }, "Por favor entre un número fraccion valido");
        var form1 = $("#formSaveDetailHemo");
        form1.validate({
            rules:{
                    pa:{required:true,
                        number: true
                    },
                pd:{required:true,
                    number: true
                },
                signo:{required:true},
                nivelConciencia:{required:true},
                extremidades:{required:true},
                densidadUrinaria:{
                    number: true,
                    min:1000, max:1030
                },
                pulsoCalidad:{required: true},
                llenadoCapilar:{required:true},
                fr: {
                    required: true,
                    number: true,
                    min:12, max:80
                },
                fc:{
                    required: true,
                    number:true,
                    min:50,
                    max:180
                },
                sa: {
                    number: true,
                    min:70,
                    max:100
                },
                tc: {
                    number: true,
                    min:36,
                    max:41
                }
            },
            messages:{
                signo: { required: "Clasificación es obligatoria"
                }
            },
            errorElement: 'em',
            errorPlacement: function ( error, element ) {
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    error.insertAfter( element );
                }
                if (element.attr("name") == "signo") {
                    error.insertAfter("#gendererror");
                } else {
                    error.insertAfter(element);
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
                SaveDetailHemo(parameters);
            }
        })
        function SaveDetailHemo(){
            var url = {
                addDetalleHemoUrl:"${addDetalleHemoUrl}",
                listDetailsHemoUrl:"${listDetailsHemoUrl}",
                successmessage      : "${successMessage}"
            };
            if( isNaN($('#pp').val()) || isNaN($('#pam').val()) || $('#pp').val()=== '0' || $('#pam').val() === '0' ){
                $('#pa').css('border-color','#FF0000');
                $.notify("Error! Valores en cero","error");
                return false;
            }else{
                $.post(url.addDetalleHemoUrl, form1.serialize(), function (data) {
                    if (data.msj != null) {
                        toastr.error( data.msj, "ERROR",{timeOut:6000});
                    } else {
                        toastr.success(url.successmessage, "success",{timeOut:6000});
                        window.setTimeout(function () {
                            location.reload();
                        }, 1500);
                    }
                }).fail(function () {
                    toastr.error("Error Interno del Servidor!", "ERROR",{timeOut:6000});
                })
            }
        }

        $("#pd").keypress(function(e) {
            var code = (e.keyCode ? e.keyCode : e.which);
            if(code==13){
                obtenerDatos();
            }
        });

        function obtenerDatos(){
            debugger;
            var sistolica = $("#pa").val();
            var diastolica = $("#pd").val();
            var diferencia = 0;
            if(parseInt(sistolica) > parseInt(diastolica))
                diferencia = parseInt(sistolica)-parseInt(diastolica);
            else{
                diferencia = parseInt(diastolica)-parseInt(sistolica);
            }
            if(isNaN(diferencia)){
                $("#pp").val(0);
                $("#pam").val(0);
            }
            else{
                $("#pp").val(diferencia);
            }
            if(isNaN(sistolica) || isNaN(diastolica)){
                $("#pam").val(0);
            }else{
                var pam = ((parseInt(diastolica) * 2) + parseInt(sistolica)) / 3;
                $("#pam").val(Math.round(pam));
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
    });
</script>
</body>>
</html>
