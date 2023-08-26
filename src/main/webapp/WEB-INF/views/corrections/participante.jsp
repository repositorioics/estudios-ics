<%--
  Created by IntelliJ IDEA.
  User: lserrano
  Date: 06/06/2023
  Time: 03:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/CorrectionParticipant/participantForm" htmlEscape="true "/>"> <spring:message code="edit" /> <spring:message code="participant" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="card">
                    <div class="card-header">
                        <h4>  <i class="fa fa-refresh"></i> Actualizar Información del Participante</h4>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title"></h5>
                        <spring:url value="/CorrectionParticipant/GetCartasParticipante" var="searchParticipantUrl"/>
                        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                        <spring:url value="/CorrectionParticipant/updateParticipante" var="saveUrl"/>
                        <div class="container">
                            <form action="#" autocomplete="off" name="select-participante-form" id="select-participante-form" class="form-horizontal" novalidate="novalidate">
                                <div class="form-group row">
                                    <div class="input-group col-md-12">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="parametro" name="parametro" type="text" placeholder="<spring:message code="participant.code" />" class="form-control">
                                        <button id="buscar" type="submit" class="btn btn-success btn-ladda ladda-button" data-style="expand-right"><span class="ladda-label">
                                            <i class="fa fa-search" aria-hidden="true"></i>
                                             <spring:message code="search" />
                                        </span><span class="ladda-spinner"></span>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <hr/>
                        <form autocomplete="off" name="FormParticipante" id="FormParticipante" class="form-horizontal" novalidate="novalidate">

                            <div class="form-group">
                                <label for="codigo"> <spring:message code="participant.code" /></label>
                                <input type="text" class="form-control" id="codigo" name="codigo" readonly>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <span class="required text-danger"> * </span>
                                    <label for="nombre1">1er. Nombre Participante</label>
                                    <input type="text" class="form-control onlyText" id="nombre1" name="nombre1" required="required">
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="nombre2">2do. Nombre Participante</label>
                                    <input type="text" class="form-control onlyText" id="nombre2" name="nombre2">
                                </div>

                                <div class="form-group col-md-3">
                                    <span class="required text-danger"> * </span>
                                    <label for="apellido1">1er Apellido Participante</label>
                                    <input type="text" class="form-control onlyText" id="apellido1" name="apellido1" required="required">
                                </div>


                                <div class="form-group col-md-3">
                                    <label for="apellido2">2do. Apellido Participante</label>
                                    <input type="text" class="form-control onlyText" id="apellido2" name="apellido2">
                                </div>
                            </div>


                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <span class="required text-danger"> * </span>
                                    <label for="nombre1Madre">1er. Nombre Madre</label>
                                    <input type="text" class="form-control onlyText" id="nombre1Madre" name="nombre1Madre" required="required">
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="nombre2Madre">2do. Nombre Madre</label>
                                    <input type="text" class="form-control onlyText" id="nombre2Madre" name="nombre2Madre">
                                </div>

                                <div class="form-group col-md-3">
                                    <span class="required text-danger"> * </span>
                                    <label for="apellido1Madre">1er. Apellido Madre</label>
                                    <input type="text" class="form-control onlyText" id="apellido1Madre" name="apellido1Madre" required="required">
                                </div>


                                <div class="form-group col-md-3">
                                    <label for="apellido2Madre">2do. Apellido Madre</label>
                                    <input type="text" class="form-control onlyText" id="apellido2Madre" name="apellido2Madre">
                                </div>
                            </div>


                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <span class="required text-danger"> * </span>
                                    <label for="nombre1Padre">1er. Nombre Padre</label>
                                    <input type="text" class="form-control onlyText" id="nombre1Padre" name="nombre1Padre" required="required">
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="nombre2Padre">2do. Nombre Padre</label>
                                    <input type="text" class="form-control onlyText" id="nombre2Padre" name="nombre2Padre" placeholder="">
                                </div>

                                <div class="form-group col-md-3">
                                    <span class="required text-danger"> * </span>
                                    <label for="apellido1Padre">1er. Apellido Padre</label>
                                    <input type="text" class="form-control onlyText" id="apellido1Padre" name="apellido1Padre" required="required">
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="apellido2Padre">2do. Apellido Padre</label>
                                    <input type="text" class="form-control onlyText" id="apellido2Padre" name="apellido2Padre" placeholder="">
                                </div>

                            </div>


                            <div class="form-row">

                                <div class="form-group  col-md-3">
                                    <span class="required text-danger"> * </span>
                                    <label for="nombre1Tutor">1er. Nombre Tutor</label>
                                    <input type="text" class="form-control onlyText" id="nombre1Tutor" name="nombre1Tutor" required="required">
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="nombre2Tutor">2do. Nombre Tutor</label>
                                    <input type="text" class="form-control onlyText" id="nombre2Tutor" name="nombre2Tutor" placeholder="">
                                </div>

                                <div class="form-group col-md-3">
                                    <span class="required text-danger"> * </span>
                                    <label for="apellido1Tutor">1er. Apellido Tutor</label>
                                    <input type="text" class="form-control onlyText" id="apellido1Tutor" name="apellido1Tutor" required="required">
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="apellido2Tutor">2do. Apellido Tutor</label>
                                    <input type="text" class="form-control onlyText" id="apellido2Tutor" name="apellido2Tutor" placeholder="">

                            </div>

                            <div class="form-row">
                                </div> <div class="form-group col-md-4">
                                    <label for="fechaNac">Fecha Nac.</label>
                                    <input type="text" required="required" class="form-control" id="fechaNac" name="fechaNac"  data-date-end-date="+0d" />
                                </div>

                                <div class="form-group  col-md-4">
                                    <label for="relfam"><spring:message code="family.relationship" /> </label>
                                    <span class="required text-danger"> * </span>
                                    <select name="relfam" id="relfam" class="form-control" required>
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${relFam}" var="rel">
                                            <option value="${rel.catKey}"> <spring:message code="${rel.spanish}" />  </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group col-md-4">
                                    <span class="required text-danger"> * </span>
                                    <label for="sexo">Sexo</label>
                                    <select id="sexo" name="sexo" class="form-control" required>
                                        <option selected value=""><spring:message code="select"/>...</option>
                                        <option value="F"><spring:message code="Femenino"/></option>
                                        <option value="M"><spring:message code="Masculino"/></option>
                                    </select>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-warning">
                                <i class="fa fa-refresh"></i>
                                <spring:message code="lbl.update"/> <spring:message code="Information"/>
                            </button>

                        </form>
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

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/sweetalert.js" var="sweet" />
<script type="text/javascript" src="${sweet}"></script>



<script type="text/javascript">
    $(document).ready(function(){
        $("#relfam").select2();
        $("#sexo").select2();
        $("#fechaNac").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true
        });

        //Validar las cajas de texto...
        $('.onlyText').keypress(function (e) {
            var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
            return !((tecla > 47 && tecla < 58) || tecla == 46);
        });

        var url = {
            "searchParticipantUrl" : "${searchParticipantUrl}",
            "successmessage": "${successMessage}",
            "saveUrl": "${saveUrl}"
        };

        $("#select-participante-form").validate( {
            rules: {
                parametro: 'required',
                number: true
            },errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    //error.insertAfter( element ); //cuando no es input-group
                    error.insertAfter(element.parent('.input-group'));
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
                search();
            }

        });// fin validate

        function search(){
            $.getJSON(url.searchParticipantUrl,{parametro : $('#parametro').val(), ajax : 'true' },function(data){
                if(data.estPart ==0 ){
                    swal("Información!", "Participante retirado!","info");
                    $(':button[type="submit"]').prop('disabled', true);
                }else{
                    $(':button[type="submit"]').prop('disabled', false);
                }

                if(data.mensaje != null){
                    swal("Información", data.mensaje,"info");
                    limpiar();
                }else{
                    $("#codigo").val(data.codigo);
                    $("#nombre1").val(data.nombre1);
                    $("#nombre2").val(data.nombre2);
                    $("#apellido1").val(data.apellido1);
                    $("#apellido2").val(data.apellido2);
                    $("#nombre1Madre").val(data.nombre1Madre);
                    $("#nombre2Madre").val(data.nombre2Madre);
                    $("#apellido1Madre").val(data.apellido1Madre);
                    $("#apellido2Madre").val(data.apellido2Madre);
                    $("#nombre1Padre").val(data.nombre1Padre);
                    $("#nombre2Padre").val(data.nombre2Padre);
                    $("#apellido1Padre").val(data.apellido1Padre);
                    $("#apellido2Padre").val(data.apellido2Padre);
                    $("#nombre1Tutor").val(data.nombre1Tutor);
                    $("#nombre2Tutor").val(data.nombre2Tutor);
                    $("#apellido1Tutor").val(data.apellido1Tutor);
                    $("#apellido2Tutor").val(data.apellido2Tutor);
                    var d = new Date(data.fechaNac);
                    var datestring =  ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                    $("#fechaNac").val(datestring);

                    if(data.relacionFamTutor != null || data.relacionFamTutor!="") {
                        var optionUnselectedRelFam = $('#relfam option:not(:selected)');
                        for (var i = 0; i < optionUnselectedRelFam.length; i++) {
                            if (data.relacionFamTutor === optionUnselectedRelFam[i].value) {
                                $("#relfam").select2().val(optionUnselectedRelFam[i].value).trigger("change");
                                break;
                            }
                        }
                    }

                    if(data.sexo != null || data.sexo !=""){
                        var optionUnselectedSexo = $("#sexo option:not(:selected)");
                        for(var i = 0; i<optionUnselectedSexo.length; i++){
                            if(data.sexo === optionUnselectedSexo[i].value){
                                $("#sexo").select2().val(optionUnselectedSexo[i].value).trigger("change");
                                break;
                            }
                        }
                    }
                    $("#parametro").val("");
                }
            }).fail(function() {
                swal({
                    title: "¡Opps!",
                    text: "Error al obtener el participante!",
                    type: "error",
                    timer: 2000
                });
                $("#parametro").focus();
            });
        }

        var form1 = $('#FormParticipante');
        form1.validate({
            rule:{
                codigo: {required:true},
                nombre1: {required:true},
                apellido1: {required:true},
                nombre1Madre:{required:true},
                apellido1Madre:{required:true},
                nombre1Padre:{required:true},
                apellido1Padre:{required:true},
                nombre1Tutor:{required:true},
                apellido1Tutor:{required:true},
                fechaNac:{required:true},
                relfam :{required:true},
                sexo:{required:true}
            },
            errorElement: 'em',
            errorPlacement: function ( error, element ) {
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    error.insertAfter( element );
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
                updateParticipante();
            }
        });

        function updateParticipante(){
            $.post( url.saveUrl, form1.serialize(), function( data ){
                registro = JSON.parse(data);
                if (registro.codigo === undefined) {
                    swal("!Error¡",registro.mensaje,"error");
                }else if(registro.mensaje != null){
                    swal("¡Error!",registro.mensaje,"error");
                }
                else if(registro.codigo != null || registro.codigo != ""){
                    swal("¡Buen Trabajo!",url.successmessage,"success");
                    window.setTimeout(function () {
                        window.location.reload();
                    }, 1500);
                }
            },'text' ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                       toastr.error("error:" + errorThrown);
            });
        }


        function limpiar(){
            $("#codigo").val("");
            $("#nombre1").val("");
            $("#nombre2").val("");
            $("#apellido1").val("");
            $("#apellido2").val("");
            $("#nombre1Madre").val("");
            $("#nombre2Madre").val("");
            $("#apellido1Madre").val("");
            $("#apellido2Madre").val("");
            $("#nombre1Padre").val("");
            $("#nombre2Padre").val("");
            $("#apellido1Padre").val("");
            $("#apellido2Padre").val("");
            $("#nombre1Tutor").val("");
            $("#nombre2Tutor").val("");
            $("#apellido1Tutor").val("");
            $("#apellido2Tutor").val("");
            $("#fechaNac").val("");
            $("#relfam").select2().val("").trigger("change");
            $("#sexo").select2().val("").trigger("change");

        }

        $("#parametro").focus();

    })

</script>
</body>
</html>
