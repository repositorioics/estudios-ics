<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <style>
        input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff }
        input[type="text"]{color: #000000; font-family: Roboto}
        input[type="select"]{color: #000000; font-family: Roboto}
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
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">LISTADO</a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(edithemoUrl)}">ACTUALIZAR </a>
                <i class="fa fa-angle-right"></i>
                <strong>${obj.participante.nombre1} ${obj.participante.nombre2} ${obj.participante.apellido1} ${obj.participante.apellido2}</strong>
            </li>
        </ol>
        <spring:url value="/hemo/UpdateHemodinamica" var="updateHemoUrl"/>
        <spring:url value="/hemo/listado2" var="Listado2Url"/>
        <div class="container-fluid">
            <div class="animated fadeIn">
               <div class="card text-black-50 bg-secondary border-primary">
                    <div class="card-header">
                        <h5 class="text-gray-dark" style="font-family: Roboto">
                            <i class="fa fa-refresh" aria-hidden="true"></i>  <spring:message code="Actualizar Información" /></h5>
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <form action="#" modelAttribute="obj" autocomplete="off"  class="form-horizontal" id="update-hemo-form" name="update-hemo-form"  role="form">
                                <div hidden="hidden" >
                                    <input type="text" readonly id="idDatoHemo" name="idDatoHemo" value="${obj.idDatoHemo}"/>
                                </div>
                                <div class="row">
                                    <div hidden="hidden">
                                    <div class="form-group col-sm-4">
                                        <label for="silais">Silais:</label>
                                        <input type="text" class="form-control" name="silais" id="silais" value="${obj.silais}" readonly />
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="uSalud">Unidad de Salud:</label>
                                        <input type="text" name="uSalud" id="uSalud" readonly value="${obj.uSalud}" class="form-control" />
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="municipio">Municipio:</label>
                                        <input type="text" class="form-control" id="municipio"
                                               path="municipio" name="municipio" value="${obj.municipio}" readonly />
                                    </div>
                                </div>
                                    <div hidden="hidden">
                                        <div class="form-group col-sm-3">
                                            <label for="idParticipante">Participante:</label>
                                            <input type="text" class="form-control" name="idParticipante"  id="idParticipante" readonly value="${obj.participante.codigo}"  />
                                        </div>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="sector">Sector:</label>
                                        <span class="required text-danger"> * </span>
                                        <select name="sector" id="sector" class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${barrios}" var="barrio">
                                                <c:choose>
                                                    <c:when test="${barrio.codigo eq obj.sector}">
                                                        <option selected value="${barrio.codigo}"><spring:message code="${barrio.nombre}" /></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${barrio.codigo}"><spring:message code="${barrio.nombre}" /></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-8">
                                        <label for="direccion">Dirección de Participante :</label>
                                        <input type="text" name="direccion" class="form-control" value="${obj.direccion}"
                                               placeholder="Ingrese la dirección"  id="direccion" name="direccion"/>
                                    </div>
                                    <div id="bar" class="form-group col-sm-12" style="display: none">
                                        <label for="barrioF">Barrio :</label>
                                        <input type="text" class="form-control" id="barrioF" name="barrioF" value="${obj.barrioF}" style="text-transform:uppercase" />
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="fecha">Fecha de Nacimiento:</label>
                                        <input type="text" class="form-control" id="fecha" name="fecha"
                                               value="<fmt:formatDate value="${obj.fecha}" pattern="dd/MM/yyyy"/>" readonly />
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="expediente">Expediente:</label>
                                        <input type="text" class="form-control" id="expediente" required name="expediente" value="${obj.nExpediente}" readonly  placeholder="Número de Expediente" />
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="telefono">Teléfono:</label>
                                        <input type="text" class="form-control focusNext" id="telefono" name="telefono"  value="${obj.telefono}" placeholder="Número de telefono"tabindex="1"/>
                                    </div>
                                    <div class="form-group col-sm-6">
                                        <label for="nombre" class="control-label">Nombre:</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre"
                                               value=" ${obj.participante.nombre1} ${obj.participante.nombre2} ${obj.participante.apellido1} ${obj.participante.apellido2}"
                                               readonly />
                                    </div>
                                    <div class="form-group col-sm-6">
                                        <label for="edad">Edad:</label>
                                        <input type="text" class="form-control"  name="edad" id="edad" value="${obj.edad}" readonly />
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="peso">Peso(kg):</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" tabindex="2" name="peso" id="peso" placeholder="Peso" value="${obj.peso}"/>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="talla">Talla(cm):</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" tabindex="3" name="talla" id="talla" placeholder="Talla" value="${obj.talla}"/>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="numParametro">Parametros:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" name="numParametro"  id="numParametro" value="${obj.numParametros}" tabindex="4">
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="asc">A.S.C(m2):</label>
                                        <input type="text" class="form-control" id="asc" name="asc" readonly value="${obj.asc}"/>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="imc">IMC:</label>
                                        <input type="text" class="form-control" id="imc" name="imc" readonly value="${obj.imc}"/>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="IMCdetallado">Detalle Imc:</label>
                                        <input type="text" class="form-control" id="IMCdetallado" name="IMCdetallado" readonly value="${obj.IMCdetallado}"/>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="fconsulta">Fecha Consulta:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" id="fconsulta" name="fconsulta"  data-date-end-date="+0d" value="<fmt:formatDate value="${obj.fecha}" pattern="dd/MM/yyyy"/>"/>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="fie">Fecha Inicio de Enfermedad:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" tabindex="3" id="fie" name="fie"  data-date-end-date="+0d" value="<fmt:formatDate value="${obj.fie}" pattern="dd/MM/yyyy"/>" />
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="diasenf">Días de Enfermedad:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control" id="diasenf" name="diasenf" value="${obj.diasenf}" readonly/>
                                    </div>
                                    <div hidden="hidden" class="form-group col-sm-6">
                                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                            <p class="text-center">
                                                <br/>
                                            <div class="custom-control custom-switch">
                                                <input type="checkbox" class="custom-control-input" id="chkRange2" name="chkRange2">
                                                <label class="custom-control-label" for="chkRange2">Frecuencias Respiratorias.</label>
                                            </div>
                                            </p>
                                        </div>
                                    </div>

                                    <div class="form-group col-sm-12">
                                        <div id="positivoerror" class="text-danger"></div>
                                        <br/>
                                        <div class="custom-control custom-radio custom-control-inline">
                                        <c:choose>
                                            <c:when test="${obj.esPositivo eq false}">
                                                <input type="radio" id="chkpositivo0" value="${obj.positivo}" name="chkpositivo" checked="checked" class="custom-control-input"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="radio" id="chkpositivo0" value="0" name="chkpositivo" class="custom-control-input"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <label class="custom-control-label" for="chkpositivo0">Negativo.</label>
                                    </div>

                                    <div class="custom-control custom-radio custom-control-inline">
                                        <c:choose>
                                            <c:when test="${obj.esPositivo eq  true}">
                                                <input type="radio" id="chkpositivo1" value="${obj.positivo}" name="chkpositivo" checked="checked" class="custom-control-input"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="radio" id="chkpositivo1" value="1" name="chkpositivo" class="custom-control-input"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <label class="custom-control-label" for="chkpositivo1">Positivo.</label>
                                    </div>

                                    </div>

                                </div>
                                    <br/>
                                <div hidden="hidden">
                                    <div class="row">
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <p class="text-center">
                                                <br/>
                                                <input type="radio" id="customRadio2" name="positivo" class="custom-control-input" value="0">
                                                <label class="custom-control-label" for="customRadio2"> Negativo</label>
                                            </p>
                                        </div>
                                        <div class="form-group col-sm-4">
                                            <button class="btn btn-dark btn-lg btn-block" type="button" data-toggle="collapse" tabindex="6" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                                <i class="fa fa-stethoscope" aria-hidden="true"></i>  Rangos
                                            </button>
                                        </div>
                                        <div class="form-group col-sm-4">

                                        </div>
                                        <div class="form-group col-sm-4">

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="collapse" id="collapseExample">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <label style="font-family: Roboto">Rango de Presión PS/PD:</label>
                                                    </div>

                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="sdMin" name="sdMin" placeholder="Mínima" tabindex="7" value="${obj.sdMin}">
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="sdMed" name="sdMed" placeholder="Media" tabindex="8" value="${obj.sdMed}">
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="sdMax" name="sdMax" placeholder="Máxima" tabindex="9" value="${obj.sdMax}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <label class="text-capitalize"  style="font-family: Roboto">Presión Arterial Media (PAM): </label>
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="pamMin" name="pamMin" placeholder="Minima" tabindex="10" value="${obj.pamMin}">
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="pamMed" name="pamMed" placeholder="Media" tabindex="11" value="${obj.pamMed}">
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="pamMax" name="pamMax" placeholder="Máxima" tabindex="12" value="${obj.pamMax}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <label style="font-family: Roboto">Rango de Frecuencias Cárdiacas: </label>
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="fcMin" name="fcMin" placeholder="Minima" tabindex="13" value="${obj.fcMin}">
                                                    </div>

                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="fcMed" name="fcMed" placeholder="Máxima" tabindex="14" value="${obj.fcMed}">
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control focusNext" id="fcProm" name="fcProm" placeholder="Promedio" tabindex="15" value="${obj.fcProm}">
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <label class="text-capitalize"  style="font-family: Roboto">Rango Frecuencias Respiratorias </label>
                                                    </div>
                                                    <div class="form-group col-sm-6">
                                                        <input type="text" class="form-control focusNext" id="frMin" name="frMin" placeholder="Minima" tabindex="16" value="${obj.frMin}">
                                                    </div>
                                                    <div class="form-group col-sm-6">
                                                        <input type="text" class="form-control focusNext" id="frMax" name="frMax" placeholder="Máxima" tabindex="17" value="${obj.frMax}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <button type="submit" id="btnUpdate" class="btn btn-info btn-lg btn-block">
                                                <i class="fa fa-refresh" aria-hidden="true"></i> Actualizar</button>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">

                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
                                                <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                <spring:message code="Cancelar" /></a>
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
        <div hidden="hidden" id="errorIMC"></div>
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
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />



<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>



<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>


<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
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
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/jquery.maskedinput.js" var="maskJs" />
<script type="text/javascript" src="${maskJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/hemodinamica/formEdit.js" var="formEditJS" />
<script src="${formEditJS}" type="text/javascript"></script>
<script>
    $(document).ready(function(){
        $("#sector").select2();
        $('#peso').on('change', function() {
            if(isNaN(this.value)){
                this.value = "";
            }else{
                this.value = parseFloat(this.value).toFixed(2);
            }
        });
        $('#talla').on('change', function() {
            if(isNaN(this.value)){
                this.value = "";
            }else{
                this.value = parseFloat(this.value).toFixed(2);
            }
        });
        loadSelect();
        $("#sector").on("change", function(){
            if(this.value == 18 ){
                $("#bar").fadeIn("slow");
                $("#barrioF").attr("required", "true");
            }else{
                $("#bar").fadeOut("slow");
                $("#barrioF").val("").attr("required", "false");
            }
        });
        if ($("#sdMin").val() == null || $("#sdMin").val() == ""){
            $("input[name=chkRange2]").attr("checked", false);
        }else{
            $("input[name=chkRange2]").attr("checked", true);
        }
        function loadSelect(){
            if($("#sector").val() ==18){
                $("#bar").show();
                $("#barrioF").attr("required", "true");
            }else{
                $("#bar").hide();
                $("#barrioF").val("").attr("required", "false");
            }
        }
            var parametros = {updateHemoUrl: "${updateHemoUrl}",
                                 Listado2Url:"${Listado2Url}"};
            var form2 = $('#update-hemo-form');
            form2.validate({
                rules:{
                    idParticipante :{required: true},
                    nombre:{required:true},
                    parametro :{required: true},
                    uSalud:{required:true},
                    chkpositivo:{required: true},
                    silais:{required: true},
                    municipio:{required:true},
                    fconsulta:{required: true},
                    numParametro:{
                        required:true,
                        digits: true
                    },
                    sector: {required: true},
                    expediente:{required: true},
                    telefono:{
                        digits: true,
                        maxlength: 8,
                        minlength: 8
                    },
                    edad:{required: true},
                    direccion:{required: true},
                    peso:{required:true,
                        number: true},
                    talla:{required:true,
                        number: true},
                    diasenf:{required:true},
                    pam:{required:true},
                    IMCdetallado:{required:true}
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
                    UpdateHemo(parametros);
                }
            });
            function UpdateHemo(dir){
                $.post(dir.updateHemoUrl, form2.serialize(), function(data){
                    swal("Éxito!", "Información actualizada!", "success");
                    window.setTimeout(function(){
                        window.location.href = parametros.Listado2Url;
                    }, 1500);
                }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                    swal("Error!","intente nuevamente!", "error");
                });
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
    })
</script>
</body>
</html>