<%--
  Created by IntelliJ IDEA.
  User: ICS_Inspiron3
  Date: 22/05/2019
  Time: 10:57
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
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>
    <style>
         input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; font-family: Roboto }
         input[type="text"]{color: #000000; font-family: Roboto}
         input[type="select"]{color: #000000; font-family: Roboto}
    </style>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <title>Hemodinámica</title>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/hemo/listado2/" htmlEscape="true "/>">LISTADO</a>
                <i class="fa fa-angle-right"></i><a href="<spring:url value="/hemo/create/" htmlEscape="true "/>">
                    <spring:message code="Hemodinámica" />
                </a>
            </li>
        </ol>
        <spring:url value="/hemo/addHemodinamica" var="saveHemoUrl"/>
        <spring:url value="/hemo/listado2" var="ListadoUrl"/>
        <spring:url value="/hemo/GetRange" var="GetRangeUrl"/>
        <c:set var="successmessage"><spring:message code="process.success" /></c:set>
        <c:set var="errormessage"><spring:message code="process.errors" /></c:set>
        <div class="container-fluid">
            <div class="container-fluid">
                <div class="animated fadeIn">
                    <div class="card text-black-50 bg-light border-primary">
                        <div class="card-header">
                            <h5 class="text-gray-dark" style="font-family: Roboto">
                                <i class="fa fa-search" aria-hidden="true"></i>   <spring:message code="Búscar Participante" /></h5>
                        </div>
                        <div class="card-block">
                            <spring:url value="/hemo/searchParticipant" var="searchPartUrl"/>
                            <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal ">
                                <div class="row">
                                    <div class="form-group col-sm-12 col-md-6 col-lg-12">
                                        <label>Código del Participante</label>
                                        <input type="text" class="form-control" placeholder="Ingrese el código" id="parametro" name="parametro">
                                        <div id="gendererror" class="text-danger"></div>
                                    </div>
                                </div>
                            </form>
                                <div class="row">
                                    <form action="#" class="form-horizontal" id="save-hemo-form" method="post" autocomplete="off" name="save-hemo-form" role="form">
                                        <div id="errorIMC" hidden="hidden" class="error text-danger"></div>
                                        <div class="row">
                                        <div hidden="hidden">
                                            <div class="form-group col-sm-4">
                                                <label for="silais">Silais:</label>
                                                <input type="text" class="form-control" name="silais" id="silais" value="Managua" readonly >
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="silais">Unidad de Salud:</label>
                                                <input type="text" name="uSalud" id="uSalud" readonly value="Sócrates Flores Vivas" class="form-control" />
                                            </div>

                                            <div class="form-group col-sm-4">
                                                <label for="municipio">Municipio:</label>
                                                <input type="text" class="form-control" id="municipio" name="municipio" value="Managua" readonly >
                                            </div>
                                        </div>
                                            <div hidden="hidden">
                                                <div class="form-group col-sm-3">
                                                    <label for="idParticipante">Participante:</label>
                                                    <input type="text" name="idParticipante" id="idParticipante" placeholder="Código del Participante" readonly class="form-control"/>
                                                </div>
                                            </div>

                                            <div class="form-group col-sm-4">
                                                <label for="sector">Sector:</label>
                                                <span class="required text-danger"> * </span>
                                                <select name="sector" id="sector" name="sector" class="form-control" required="required">
                                                    <option selected value=""><spring:message code="select" />...</option>
                                                    <c:forEach items="${barrios}" var="barrio">
                                                        <option value="${barrio.codigo}">${barrio.nombre}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-sm-8">
                                                <label for="direccion">Dirección de Participante :</label>
                                                <input type="text" name="direccion" id="direccion" class="form-control" placeholder="Ingrese la dirección"/>
                                            </div>
                                            <div id="bar" class="form-group col-sm-12" style="display: none">
                                                <label for="barrioF">Barrio :</label>
                                                <input type="text" class="form-control" id="barrioF" name="barrioF" style="text-transform:uppercase" />
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="fecha">Fecha de Nacimiento:</label>
                                                <input type="text" id="fecha" name="fecha" readonly class="form-control" />
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="expediente">Expediente:</label>
                                                <span class="required text-danger"> * </span>
                                                <input type="text" class="form-control" id="expediente" name="expediente" readonly  placeholder="Número de Expediente">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="telefono">Teléfono:</label>
                                                <input type="text" class="form-control focusNext" id="telefono" name="telefono" placeholder="Número de telefono" tabindex="1">
                                            </div>

                                            <div class="form-group col-sm-6">
                                                <label for="nombre">Nombre:</label>
                                                <input type="text" class="form-control" placeholder="Nombre del Participante" id="nombre" required readonly>
                                            </div>
                                            <div class="form-group col-sm-6">
                                                <label for="edad">Edad:</label>
                                                <input type="text" class="form-control" id="edad" placeholder="Edad" name="edad" readonly>
                                            </div>
                                            <div hidden="hidden">
                                                <div class="form-group col-sm-3">
                                                    <input type="text" name="anios" id="anios" />
                                                </div>
                                                <div class="form-group col-sm-3">
                                                    <input type="text" name="mes" id="mes"/>
                                                </div>
                                                <div class="form-group col-sm-3">
                                                    <input type="text" id="dias" name="dias"/>
                                                </div>
                                                <div class="form-group col-sm-3">
                                                    <input type="text" id="sexo" name="sexo"/>
                                                </div>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <h4 class="text-capitalize" style="font-family: Roboto">Cálculos</h4>
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="peso">Peso(kg):</label>
                                                <span class="required text-danger"> * </span>
                                                <input type="text" class="form-control focusNext"  name="peso" id="peso" placeholder="Peso" tabindex="2">
                                            </div>

                                            <div class="form-group col-sm-4">
                                                <label for="talla">Talla(cm):</label>
                                                <span class="required text-danger"> * </span>
                                                <input type="text" class="form-control focusNext" name="talla"  id="talla" placeholder="Talla" tabindex="3">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="numParametro">Parametros:</label>
                                                <span class="required text-danger"> * </span>
                                                <input type="text" class="form-control focusNext" name="numParametro"  id="numParametro" placeholder="Cantidad de Parámetros" tabindex="4">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="asc">A.S.C(m2):</label>
                                                <input type="text" class="form-control" id="asc" name="asc" readonly>
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="imc">IMC:</label>
                                                <input type="text" class="form-control" id="imc" name="imc" readonly>
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="IMCdetallado">Detalle Imc:</label>
                                                <input type="text" class="form-control" id="IMCdetallado" name="IMCdetallado" readonly>
                                            </div>

                                            <div class="form-group col-sm-4">
                                                <label for="fconsulta">Fecha Consulta:</label>
                                                <span class="required text-danger"> * </span>
                                                <input type="text" class="form-control focusNext" id="fconsulta" name="fconsulta" data-date-end-date="+0d" required tabindex="5"/>
                                            </div>

                                            <div class="form-group col-sm-4">
                                                <label for="fie">Fecha Inicio de Enfermedad:</label>
                                                <span class="required text-danger"> * </span>
                                                <input type="text" class="form-control focusNext" id="fie" name="fie" required tabindex="6" data-date-end-date="+0d"/>
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="diasenf">Días de Enfermedad:</label>
                                                <input type="text" class="form-control" id="diasenf" name="diasenf" value="${obj.diasenf}"  readonly/>
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
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <p class="text-center">
                                                        <br/>
                                                        <input type="radio" id="chkpositivo0" value="0" name="chkpositivo" class="custom-control-input">
                                                    <label class="custom-control-label" for="chkpositivo0">Negativo.</label>
                                                    </p>
                                                </div>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <p class="text-center">
                                                        <br/>
                                                    <input type="radio" id="chkpositivo1" value="1" name="chkpositivo" class="custom-control-input">
                                                    <label class="custom-control-label" for="chkpositivo1">Positivo.</label>
                                                    </p>
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
                                                <button class="btn btn-dark btn-block btn-lg" type="button" tabindex="5" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                                    <i class="fa fa-stethoscope" aria-hidden="true"></i>  Rangos
                                                </button>
                                            </div>
                                            <div class="form-group col-sm-4">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <button id="btnObtenerRango" class="btn btn-primary btn-block btn-lg" type="button"> Obtener</button>
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
                                                        <input type="text" class="form-control font-weight-bold focusNext" id="sdMin" name="sdMin" placeholder="Mínima" readonly tabindex="6">
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control font-weight-bold focusNext" id="sdMed" name="sdMed" placeholder="Media" readonly tabindex="7">
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <input type="text" class="form-control font-weight-bold focusNext" id="sdMax" name="sdMax" placeholder="Máxima" readonly tabindex="8">
                                                    </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <label class="text-capitalize"  style="font-family: Roboto">Presión Arterial Media (PAM): </label>
                                                </div>
                                                <div class="form-group col-sm-4">
                                                    <input type="text" class="form-control font-weight-bold focusNext" id="pamMin" name="pamMin" placeholder="Minima" readonly tabindex="9">
                                                </div>
                                                <div class="form-group col-sm-4">
                                                    <input type="text" class="form-control font-weight-bold focusNext" id="pamMed" name="pamMed" placeholder="Media" readonly tabindex="10">
                                                </div>
                                                <div class="form-group col-sm-4">
                                                    <input type="text" class="form-control font-weight-bold focusNext" id="pamMax" name="pamMax" placeholder="Máxima" readonly tabindex="11">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <label style="font-family: Roboto">Rango de Frecuencias Cárdiacas: </label>
                                                </div>
                                                <div class="form-group col-sm-4">
                                                    <input type="text" class="form-control font-weight-bold focusNext" id="fcMin" name="fcMin" placeholder="Minima" readonly tabindex="12">
                                                </div>

                                                <div class="form-group col-sm-4">
                                                    <input type="text" class="form-control font-weight-bold focusNext" id="fcMed" name="fcMed" placeholder="Máxima" readonly tabindex="13">
                                                </div>
                                                <div class="form-group col-sm-4">
                                                    <input type="text" class="form-control font-weight-bold focusNext" id="fcProm" name="fcProm" placeholder="Promedio" readonly tabindex="14">
                                                </div>
                                            </div>
                                            <div class="row">
                                            <div class="col-sm-12">
                                                <label class="text-capitalize"  style="font-family: Roboto">Rango Frecuencias Respiratorias </label>
                                            </div>
                                            <div class="form-group col-sm-6">
                                                <input type="text" class="form-control font-weight-bold focusNext" id="frMin" name="frMin" placeholder="Minima" readonly tabindex="15">
                                            </div>
                                            <div class="form-group col-sm-6">
                                                <input type="text" class="form-control font-weight-bold focusNext" id="frMax" name="frMax" placeholder="Máxima" readonly tabindex="16">
                                            </div>
                                        </div>
        </div>
    </div>
</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <button type="submit" id="btnSave" class="btn btn-info btn-block btn-lg" tabindex="17">
                                                        <i class="fa fa-save"></i> Guardar</button>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group"  id="showBtnDetails">

                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <a class="btn btn-warning btn-block btn-lg" tabindex="18" href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
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
        </div>
    </div>
</div>

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



<spring:url value="/resources/js/libs/jquery.maskedinput.js" var="maskJs" />
<script type="text/javascript" src="${maskJs}"></script>

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

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<!-- <spring:url value="/resources/js/libs/mySelect2/select2_locale_es.min.js" var="select_esJs" />
<script type="text/javascript" src="${select_esJs}"></script> -->
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
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $("#parametro").focus();
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
        $("#sector").select2();
        $("#sector").on("change", function(){
           if(this.value == 18 ){
                $("#bar").fadeIn("slow");
               $("#barrioF").attr("required", "true");
            }else{
                $("#bar").fadeOut("slow");
               $("#barrioF").val("").attr("required", "false");
            }
        });
        function ClearRangos(){
                $("#sdMin").val("");
                $("#sdMed").val("");
                $("#sdMax").val("");
                $("#pamMin").val("");
                $("#pamMed").val("");
                $("#pamMax").val("");
                $("#fcMin").val("");
                $("#fcMed").val("");
                $("#fcProm").val("");
                $("#frMin").val("");
                $("#frMax").val("");
        }

        $("#btnObtenerRango").on("click", function(){
            GetRange();
        });
        function GetRange(){
            $.getJSON(parameters.GetRangeUrl,{ sexo : $('#sexo').val(), fecha : $('#fecha').val(), ajax : 'true'  }, function(data){
                if (data.result == null){
                $("#sdMin").val(data.objPsdmin);
                $("#sdMed").val(data.objPsdmed);
                $("#sdMax").val(data.objPsdmax);
                $("#pamMin").val(data.objPammin);
                $("#pamMed").val(data.objPammed);
                $("#pamMax").val(data.objPammax);
                $("#fcMin").val(data.objfcMin);
                $("#fcMed").val(data.objfcMax);
                $("#fcProm").val(data.objfcProm);
                $("#frMin").val(data.objfrMin);
                $("#frMax").val(data.objfrMax);

                    $("input[name=chkRange2]").attr("checked", true);
                }else{
                    ClearRangos();

                    $("input[name=chkRange2]").attr("checked", false);
                }
            })
        }

        $("#select-participante-form").validate({
            rules:{
                parametro: {required: true}
            },
            errorElement: 'em',
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    error.insertAfter( element );
                }
                if (element.attr("name") == "parametro") {
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
                searchParticipante();
                ClearRangos();
            }
        })
        var parametros = {searchPartUrl: "${searchPartUrl}"};
        function searchParticipante(){
            $.getJSON(parametros.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                var len = data.length;
                if(len==0){
                    swal("Error!","Código no encontrado","error");
                    $("input[name=chkRange2]").attr("checked", false);
                    $("#parametro").focus();
                }
                else{
                    if(data.estado == "0"){
                        swal("Advertencia!", "Participante está retirado!", "warning");
                        $("#nombre").val("");
                        $("#fconsulta").val("");
                        $("#idParticipante").val("");
                        $("#edad").val("");
                        $("#direccion").val("");
                        $("#sector").val("").change();
                        $("#anios").val("");
                        $("#mes").val("");
                        $("#dias").val("");
                        $("#sexo").val("");
                        $("input[name=chkRange2]").attr("checked", false);
                        $("#parametro").focus();
                    }else{
                        var elemento = data.edad;
                        var fecha = elemento.split('/');
                        var datestring = ( fecha[0] + " Años " + fecha[1] + " Meses " + fecha[2] + " Dias");
                        $("#anios").val(fecha[0]);
                        $("#mes").val(fecha[1]);
                        $("#dias").val(fecha[2]);
                        $("#sexo").val(data.sexo);
                        var myExpediente = data.fecha;
                        $("#nombre").val(data.nombre);
                        $("#fecha").val(data.fecha);
                        $("#idParticipante").val(data.codigo);
                        $("#edad").val(datestring);
                        $("#direccion").val(data.direccion);
                        $("#sector").val(data.barrio).change();
                        var exp = myExpediente.split('/');
                        var verExpdiente = (exp[0]+""+exp[1]+""+exp[2].substr(2,2))
                        $("#expediente").val(verExpdiente);
                        GetRange();
                        $("#peso").focus();
                    }
                }
            }).fail(function() {
                swal("Error!","Código no existe!", "error");
                $("input[name=chkRange2]").attr("checked", false);
                $("#parametro").focus();
            });
        }
        $("#fie").prop("disabled", true);

        $("#fconsulta").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            endDate: '-0d',
            todayBtn:true
    }).on("change", function(e){
            $("#fie").prop("disabled", false);
            var f1 = $("#fconsulta").val();
            var f2= $("#fie").val();
            $("#diasenf").val(0);
            var validaFeecha = 0;
            if (f2 ==="" || f2 ===null ){
                $("#diasenf").val(0);
            }else{
                validaFeecha = restaFechas(f1,f2);
                $("#diasenf").val(validaFeecha);
            }

        });

        $("#fie").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true,
            endDate:$("#fecha1").val()
        }).on("change", function(e){
            debugger;
            var f1 = $("#fconsulta").val();
            var f2= $("#fie").val();
            var validaFeecha = 0;
            if(f1<f2){
                $("#diasenf").val(validaFeecha);
                swal("Error", "Inicio Enfermedad no debe ser Mayor que Consulta","error");
                var f2= $("#fie").val(null);
                $("#diasenf").val(0);
                return;
            }  else if(f2===null||f2===""){
                $("#diasenf").val(0);
            }else{
                $("#diasenf").val(restaFechas(f1,f2));
            }
        });
        restaFechas = function(f1,f2){
            var fechaConsulta = new Date(f1);
            var fechaInicioEnf = new Date(f2);
            if(Date.parse(fechaConsulta) < Date.parse(fechaInicioEnf)){
                swal("Error", "Inicio Enfermedad no debe ser Mayor que Consulta","error");
                $("#fie").val("");
                $("#diasenf").val("");
                return;
            }
            var aFecha1 = f1.split("/");
            var aFecha2 = f2.split("/");
            var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]);
            var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]);
            var dif =  fFecha1 - fFecha2;
            var dias = Math.floor(dif / (1000 * 60 * 60 * 24));
            return dias +1;
        }

        /*$('#sistolica').keyup(function(){
            calculoPresion();
        });
        $('#diastolica').keyup(function(){
            calculoPresion();
        });
        function calculoPresion() {
            var sistolica = document.getElementById('sistolica').value;
            if (isNaN($('#sistolica').val())) {
                toastr.info('Debe ingresar sólo números');
                return;
            }
            var diastolica = document.getElementById('diastolica').value;

            var pam = ((parseFloat(diastolica) * 2) + parseFloat(sistolica)) / 3;
            document.getElementById('pam').value = Math.round(pam);
            var pp = parseFloat(sistolica) - parseFloat(diastolica);
            document.getElementById('pp').value = Math.round(pp);
        }*/
        $("#peso").keyup(function(){
            calculoimc();
            AsuperCorporal();
        });
        $("#talla").keyup(function(){
            calculoimc();
            AsuperCorporal();
        });

        /*Calculo imc*/
        function calculoimc() {
            var peso = document.getElementById("peso").value;
            var altura = document.getElementById("talla").value;
            if(peso != null & altura != null &  peso != "" & altura != ""){
                altura = altura.toString().replace(',', '.');
                var alturaMetro = altura / 100;
                /*CALCULO IMC*/
                var alturaCuadrado = alturaMetro * alturaMetro;
                var imc = peso / alturaCuadrado;
                document.getElementById("imc").value = Math.round(imc * 100) / 100;
                if(altura == "" || altura == null){
                    alturaCuadrado =0;
                }
                /*CALCULO DESCRIPCION IMC*/

                if (imc < 16) {
                    document.getElementById("IMCdetallado").value = "Delgadez Severa";
                } else if (imc < 17) {
                    document.getElementById("IMCdetallado").value = "Delgadez Moderada";
                } else if (imc < 18.5) {
                    document.getElementById("IMCdetallado").value = "Delgadez Aceptable";
                } else if (imc < 25) {
                    document.getElementById("IMCdetallado").value = "Peso Normal";
                } else if (imc < 30) {
                    document.getElementById("IMCdetallado").value = "Sobrepeso";
                } else if (imc < 35) {
                    document.getElementById("IMCdetallado").value = "Obeso: Tipo I";
                } else if (imc < 40) {
                    document.getElementById("IMCdetallado").value = "Obeso: Tipo II";
                } else if (imc >= 40) {
                    document.getElementById("IMCdetallado").value = "Obeso: Tipo III";
                }

                if (altura == "") {
                    document.getElementById("errorIMC").innerHTML = "Por favor, introduce tu altura.";
                } else if (altura < 0) {
                    document.getElementById("errorIMC").innerHTML = "La altura que introduzca debe ser positiva.";
                } else if (altura < 20) {
                    document.getElementById("errorIMC").innerHTML = "Ha introducido la altura en metros. Por favor, multipliquela por 100 para introducirla en centimetros.";
                } else {
                    document.getElementById("errorIMC").innerHTML = "";
                    if (peso == "") {
                        document.getElementById("errorIMC").innerHTML = "Por favor, introduce tu peso.";
                    } else if (peso < 0) {
                        document.getElementById("errorIMC").innerHTML = "El peso que introduzca debe ser positivo.";
                    } else {
                        document.getElementById("errorIMC").innerHTML = "";
                    }
                }
            }
            else{
                $("#asc").val("");
                $("#imc").val("");
                $("#IMCdetallado").val("");
            }

        } /* fin function Calculo Imc*/
        function AsuperCorporal(){
            var talla = document.getElementById("talla").value;
            var peso = document.getElementById("peso").value;

            if(peso != null & talla != null & peso != "" & talla != ""){
                var areasc =Math.sqrt((peso * talla) / 3600);
                if(isNaN(areasc))
                    $("#asc").val("");
                else
                document.getElementById("asc").value = parseFloat(areasc).toFixed(2);
            }else{
                $("#asc").val("");
                $("#imc").val("");
                $("#IMCdetallado").val("");
            }
        }

        var parameters = { saveHemoUrl: "${saveHemoUrl}",
                           ListadoUrl:"${ListadoUrl}",
            GetRangeUrl:"${GetRangeUrl}"};
        var form1 = $('#save-hemo-form');

        jQuery.validator.addMethod("myComa", function (value, element) {
            return this.optional(element) || /^[0-9]+([,][0-9]+)?$/.test(value);
        }, "Sólo números separado por coma.");

        $.validator.addMethod("validaSelect", function(value, element, arg){
            return arg != value;
        }, "Seleccione una opción");

        form1.validate({
            rules:{
                idParticipante :{required: true},
                asc:{
                    required: true,
                    number: true
                },
                imc:{
                    required: true,
                    number: true
                },
                chkpositivo:{required:true},
                IMCdetallado:{required: true},
                nombre:{required: true},
                silais:{required: true},
                municipio:{required:true},
                fecha:{required: true},
                fconsulta:{required: true},
                numParametro:{
                    required:true,
                    number: true
                },
                sector: {required: true,
                    validaSelect:"Seleccione una opción",
                 highlight: function(input) {
                    $(input).parents('.form-group').addClass('has-danger');
                },
                unhighlight: function(input) {
                    $(input).parents('.form-group').removeClass('has-danger');
                },
                errorPlacement: function(error, element) {
                    $(element).parents('.form-group').append(error);
                }
                },
                expediente:{required: true},
                    telefono:{
                    maxlength: 8,
                    minlength: 8,
                    digits: true},
                    edad:{required: true},
                    peso:{required:true,
                    number: true},
                    talla:{required:true,
                    number: true},
                    diasenf:{required:true},
                    uSalud:{required:true}
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
                SaveHemo(parameters);
            }
        });
        function SaveHemo(dir){
            console.log(form1.serialize());
            $.post(dir.saveHemoUrl, form1.serialize(), function(data){
                swal("Éxito!", "Información guardada!", "success")
                window.setTimeout(function(){
                    window.location.href = parameters.ListadoUrl;
                }, 1500);
            }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                swal("Error al guardar la información.","error");
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
<jsp:include page="../fragments/bodyFooter.jsp" />
</body>
</html>
