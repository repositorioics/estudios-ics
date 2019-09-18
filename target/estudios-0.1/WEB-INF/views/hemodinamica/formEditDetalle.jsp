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
    <!-- END DATE PICKER -->
    <style>

        input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; font-family: Roboto }
        input[type="text"]{color: #000000; font-family: Roboto}
        input[type="select"]{color: #000000; font-family: Roboto}
        body {
            height: 100vh;
            padding: 0;
            margin: 0;
        }

        .bg {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }

        .span_pseudo, .chiller_cb span:before, .chiller_cb span:after {
            content: "";
            display: inline-block;
            background: #fff;
            width: 0;
            height: 0.2rem;
            position: absolute;
            transform-origin: 0% 0%;
        }

        .chiller_cb {
            position: relative;
            height: 2rem;
            display: flex;
            align-items: center;
        }
        .chiller_cb input {
            display: none;
        }
        .chiller_cb input:checked ~ span {
            background: dodgerblue;
            border-color: dodgerblue;
        }
        .chiller_cb input:checked ~ span:before {
            width: 1rem;
            height: 0.15rem;
            transition: width 0.1s;
            transition-delay: 0.3s;
        }
        .chiller_cb input:checked ~ span:after {
            width: 0.4rem;
            height: 0.15rem;
            transition: width 0.1s;
            transition-delay: 0.2s;
        }
        .chiller_cb input:disabled ~ span {
            background: #ececec;
            border-color: #dcdcdc;
        }
        .chiller_cb input:disabled ~ label {
            color: #dcdcdc;
        }
        .chiller_cb input:disabled ~ label:hover {
            cursor: default;
        }
        .chiller_cb label {
            padding-left: 2rem;
            position: relative;
            z-index: 2;
            cursor: pointer;
            margin-bottom:0;
        }
        .chiller_cb span {
            display: inline-block;
            width: 1.2rem;
            height: 1.2rem;
            border: 2px solid #ccc;
            position: absolute;
            left: 0;
            transition: all 0.2s;
            z-index: 1;
            box-sizing: content-box;
        }
        .chiller_cb span:before {
            transform: rotate(-55deg);
            top: 1rem;
            left: 0.37rem;
        }
        .chiller_cb span:after {
            transform: rotate(35deg);
            bottom: 0.35rem;
            left: 0.2rem;
        }

        /* - ---------------------------------------- -*/
    </style>
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
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
                <a href="<spring:url value="/hemo" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/hemo/listDetailsHemo/{idDatoHemo}">
                         <spring:param name="idDatoHemo" value="${objDet.datoshemodinamica.idDatoHemo}" />
                         </spring:url>">Listado
                </a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(listDetailsHemoUrl)}">Actualizar Detalle <strong> ${objDet.datoshemodinamica.participante.nombre1} ${objDet.datoshemodinamica.participante.nombre2} ${objDet.datoshemodinamica.participante.apellido1} ${objDet.datoshemodinamica.participante.apellido2}</strong></a>
            </li>
        </ol>
        <spring:url value="/hemo/UpdateDetalleHemo" var="upateDetHemoUrl"/>
        <spring:url value="/hemo/listado" var="ListadoHemoUrl"/>
        <spring:url value="/hemo/listDetailsHemo/{idDatoHemo}" var="listDetailsHemoUrl">
            <spring:param name="idDatoHemo" value="${objDet.datoshemodinamica.idDatoHemo}" />
        </spring:url>
        <c:set var="successmessage"><spring:message code="process.success" /></c:set>
        <c:set var="errormessage"><spring:message code="process.errors" /></c:set>
        <div class="container-fluid">
            <div class="container-fluid">
                <div class="animated fadeIn">
                    <div class="card text-black-50 bg-secondary">
                        <div class="card-header">
                            <i class="fa fa-list"></i> <spring:message code="Detalles Hemodinamica" />
                        </div>
                        <div class="card-block">
                            <form name="formDetailHemo" autocomplete="off" role="form" action="#" id="formDetailHemo" method="post" class="form-horizontal">
                                <div class="row">
                                    <div class="form-group col-sm-12">
                                        <h5 class="font-weight-bold" style="font-family: Roboto">Clasificación del Dengue: </h5>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <c:choose>
                                                <c:when test="${objDet.signo eq 'Sin signo de Alarma'}">
                                                    <input type="radio" id="customRadio1" name="signo" class="custom-control-input" value="Sin signo de Alarma" checked="checked">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="radio" id="customRadio1" name="signo" class="custom-control-input" value="Sin signo de Alarma">
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="custom-control-label" for="customRadio1">
                                                <spring:message code="CLASIFICACIONDENGUE_SinAlarma"></spring:message>
                                            </label>
                                        </div>

                                        <div class="custom-control custom-radio custom-control-inline">
                                            <c:choose>
                                                <c:when test="${objDet.signo eq 'Con signo de Alarma'}">
                                                    <input type="radio" id="customRadio2" name="signo" class="custom-control-input" value="Con signo de Alarma"  checked>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="radio" id="customRadio2" name="signo" class="custom-control-input" value="Con signo de Alarma" >
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="custom-control-label" for="customRadio2">
                                                <spring:message code="CLASIFICACIONDENGUE_ConAlarma"></spring:message>
                                            </label>
                                        </div>

                                        <div class="custom-control custom-radio custom-control-inline">
                                            <c:choose>
                                                <c:when test="${objDet.signo eq 'Grave'}">
                                                    <input type="radio" id="customRadio3" name="signo" class="custom-control-input"  value="Grave" checked="checked">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="radio" id="customRadio3" name="signo" class="custom-control-input"  value="Grave" >
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="custom-control-label" for="customRadio3">
                                                <spring:message code="CLASIFICACIONDENGUE_Grave"></spring:message>
                                            </label>
                                        </div>
                                        <div id="gendererror" class="text-danger"></div>
                                    </div>
                                    <div class="form-group col-sm-12">
                                        <label for="dx">Diagnóstico:</label>
                                        <textarea  class="form-control focusNext" id="dx" name="dx" cols="30" rows="2" placeholder="Ingrese el diagnóstico" tabindex="1">${objDet.dx}</textarea>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="fecha">Fecha:</label>
                                        <input type="text" class="form-control focusNext" tabindex="2" value="<fmt:formatDate value="${objDet.fecha}" pattern="dd/MM/yyyy"/>"  id="fecha" name="fecha" required>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="hora">Hora:</label>
                                        <input type="time" class="form-control focusNext" tabindex="3" id="hora" name="hora" value="${objDet.hora}" required>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="nivelConciencia">Nivel de Consciencia:</label>
                                        <select class="form-control focusNext" id="nivelConciencia" name="nivelConciencia" tabindex="4" required="required" tabindex="4">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${nivelConciencia}" var="nivel">
                                                <c:choose>
                                                    <c:when test="${nivel.catKey eq objDet.nivelConciencia}">
                                                        <option selected value="${nivel.catKey}"><spring:message code="${nivel.spanish}" /></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${nivel.catKey}"><spring:message code="${nivel.spanish}" /></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>


                                    <div class="form-group col-sm-4">
                                        <label for="pa">P/A mmHg:</label>
                                        <input type="text" class="form-control focusNext" id="pa" name="pa" value="${objDet.pa}" placeholder="P/A mmHg" required tabindex="5">
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="pp">PP mmHg:</label>
                                        <input type="text" class="form-control focusNext" id="pp" name="pp" value="${objDet.pp}" placeholder="PP mmHg" readonly required tabindex="6">
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="pam">PAM mmHg:</label>
                                        <input type="text" class="form-control focusNext" id="pam" name="pam" value="${objDet.pam}" placeholder="PAM mmHg" readonly  required tabindex="7">
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="fc">F.C por Minuto:</label>
                                        <input type="text" class="form-control focusNext" id="fc" name="fc" placeholder="F.C por Minuto" value="${objDet.fc}" required tabindex="8">
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="fr">F.R por Minuto:</label>
                                        <input type="text" class="form-control focusNext" id="fr" name="fr" value="${objDet.fr}" placeholder="F.R por Minuto" required tabindex="9">
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="tc">T°C:</label>
                                        <input type="text" class="form-control focusNext" id="tc" name="tc" value="${objDet.tc}" placeholder="T°C" tabindex="10">
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="sa">SA02%:</label>
                                        <input type="text" class="form-control focusNext" id="sa" value="${objDet.sa}" name="sa" placeholder="SA02%" tabindex="11">
                                    </div>


                                    <div class="form-group col-sm-4">
                                        <label for="extremidades">Extremidades:</label>
                                        <select name="extremidades" id="extremidades" class="form-control focusNext" tabindex="12">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${extremidades}" var="extrem">
                                                <c:choose>
                                                    <c:when test="${extrem.catKey eq objDet.extremidades}">
                                                        <option selected value="${extrem.catKey}"><spring:message code="${extrem.spanish}" /></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${extrem.catKey}"><spring:message code="${extrem.spanish}" /></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="llenadoCapilar">Llenado Capilar (seg):</label>
                                        <select name="llenadoCapilar" id="llenadoCapilar" class="form-control focusNext" required tabindex="13"/>
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${llenadoCapilar}" var="llenado">
                                            <c:choose>
                                                <c:when test="${llenado.catKey eq objDet.llenadoCapilar}">
                                                    <option selected value="${llenado.catKey}"><spring:message code="${llenado.spanish}" /></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${llenado.catKey}"><spring:message code="${llenado.spanish}" /></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="pulsoCalidad">Pulso (Calidad):</label>
                                        <select name="pulsoCalidad" id="pulsoCalidad" class="form-control focusNext" required tabindex="14">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${pulsoCalidad}" var="pulso">
                                                <c:choose>
                                                    <c:when test="${pulso.catKey eq objDet.pulsoCalidad}">
                                                        <option selected value="${pulso.catKey}"><spring:message code="${pulso.spanish}" /></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${pulso.catKey}"><spring:message code="${pulso.spanish}" /></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="diuresis">Diuresis/ml/Kg/Hr:</label>
                                        <select name="diuresis" id="diuresis" class="form-control focusNext" tabindex="15">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${diuresis}" var="d">
                                                <c:choose>
                                                    <c:when test="${d.catKey eq objDet.diuresis}">
                                                        <option selected value="${d.catKey}"><spring:message code="${d.spanish}" /></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${d.catKey}"><spring:message code="${d.spanish}" /></option>
                                                    </c:otherwise>
                                                </c:choose>

                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="densidadUrinaria">Densidad Urinaria:</label>
                                        <input type="text" class="form-control focusNext" id="densidadUrinaria" tabindex="16" value="${objDet.densidadUrinaria}" name="densidadUrinaria" placeholder="Densidad Urinaria">
                                    </div>

                                    <div class="form-group col-sm-12">
                                        <label for="personaValida">Valorado Por:</label>
                                        <select name="personaValida" id="personaValida" required class="form-control focusNext" tabindex="17">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${personaValida}" var="person">
                                                <c:choose>
                                                    <c:when test="${person.catKey eq objDet.personaValida}">
                                                        <option selected value="${person.catKey}"><spring:message code="${person.spanish}" /></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${person.catKey}"><spring:message code="${person.spanish}" /></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div hidden="hidden">
                                        <input type="text" readonly name="idDatoHemo" id="idDatoHemo" value="${objDet.datoshemodinamica.idDatoHemo}" class="form-control"/>
                                        <input type="text" readonly name="idHemoDetalle" id="idHemoDetalle" class="form-control" value="${objDet.idHemoDetalle}"/>
                                        <spring:url value="/hemo/listDetailsHemo/{idHemoDetalle}" var="volverUrl">
                                            <spring:param name="idHemoDetalle" value="${objDet.idHemoDetalle}" />
                                        </spring:url>
                                    </div>
                                    <spring:url value="/hemo/listDetailsHemo/{idDatoHemo}" var="listDetailsHemoUrl">
                                        <spring:param name="idDatoHemo" value="${objDet.datoshemodinamica.idDatoHemo}" />
                                    </spring:url>
                                    <spring:url value="/hemo/edithemo/{idDatoHemo}" var="edithemoUrl">
                                        <spring:param name="idDatoHemo" value="${objDet.datoshemodinamica.idDatoHemo}" />
                                    </spring:url>
                                    <div hidden="hidden">
                                    <div class="form-group col-sm-6">
                                        <div class="bg">
                                            <div>
                                                <div class="chiller_cb">
                                                    <input class="form-control form-check-input" checked="${objDet.impreso}" type="checkbox" name="impreso" id="impreso">
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
                                                    type="submit" id="btnModificar">
                                                <i class="fa fa-refresh" aria-hidden="true"></i> Actualizar
                                            </button>
                                        </div>
                                    </div>
                                    <div class="col-sm-4"></div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <a href="${fn:escapeXml(listDetailsHemoUrl)}" data-toggle="tooltip" title="Volver" data-placement="top"
                                               class="btn btn-warning btn-block btn-lg">
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

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<!--  Select2 scripts-->
<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="select2Js" />
<script type="text/javascript" src="${select2Js}"></script>


<spring:url value="/resources/js/libs/jquery.maskedinput.js" var="maskJs" />
<script type="text/javascript" src="${maskJs}"></script>

<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<script>
    $(document).ready(function(){
        $("#dx").focus();
        $("#nivelConciencia").select2();
        $("#extremidades").select2();
        $("#llenadoCapilar").select2();
        $("#pulsoCalidad").select2();
        $("#personaValida").select2();
        $("#diuresis").select2();
        $("#pa").mask("999/999");
        $("#fecha").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true,
            endDate: '-0d'
        });
        var parameters = {upateDetHemoUrl: "${upateDetHemoUrl}",
            listDetailsHemoUrl: "${listDetailsHemoUrl}"
        };
        $.validator.addMethod('customphone', function (value, element) {
            return this.optional(element) || /^[0-9]+([/][0-9]+)?$/.test(value);
        }, "Por favor entre un número fracción válido");

        var form1 = $("#formDetailHemo");
        form1.validate({
            rules:{
                pa:{required:true,
                    customphone:true
                },
                signo:{required:true},
                nivelConciencia:{
                    required:true
                },
                tc: {

                    number: true,
                    min:36,
                    max:41
                },
                sa:{

                    min:70,
                    max:100
                },
                fc:{
                    required: true,
                    number: true,
                    min:50,
                    max:180
                },
                fr:{
                    required:true,
                    min:12,
                    max:80
                },
                extremidades:{required:true},
                densidadUrinaria:{
                    number: true,
                    min:1005
                    ,max:1030
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
                UpdateDetHemo(parameters);
               // console.log(form1.serialize());
            }
        });
        function UpdateDetHemo(){
            var url = parameters.upateDetHemoUrl;
            var dir2 = parameters.listDetailsHemoUrl;
            $.post(url, form1.serialize(), function(data){
                swal("Éxito!", "Información Actualizada.!", "success");
                window.setTimeout(function(){
                    window.location.href = dir2;
                }, 1500);
            }).fail(function(){
                swal("Error!","intente de nuevo!", "error")
            })
        }
        $("#pa").keyup(function(){
            var pa = $("#pa").val();
            var result = pa.split("/");
            var part1 = result[0];
            var part2 = result[1];
            var diferencia = parseInt(part1)-parseInt(part2);
            var pam = ((parseInt(part2) * 2) + parseInt(part1)) / 3;
            $("#pp").val(diferencia);
            $("#pam").val(Math.round(pam));
        })

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

