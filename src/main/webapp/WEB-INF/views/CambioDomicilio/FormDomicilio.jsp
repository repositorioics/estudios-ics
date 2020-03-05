<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/Domicilio/Listado/" htmlEscape="true "/>">Listado</a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/Domicilio/Home/" htmlEscape="true "/>">Cambio de Domicilio</a>
            </li>
        </ol>
        <div class="container-fluid">
           <div class="animated fadeIn">
               <div class="card bg-secondary text-black-50">
                   <div class="card-header">
                       <h5> <i class="fa fa-home" aria-hidden="true"></i> Cambio de Domicilio</h5>
                   </div>
                   <div class="card-body">
                       <spring:url value="/Domicilio/searchParticipant" var="searchPartUrl"/>
                       <spring:url value="/Domicilio/Listado" var="ListadoUrl"/>
                       <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal ">
                           <div class="row">
                               <div class="form-group col-sm-12 col-md-6 col-lg-12">
                                   <label>Código del Participante:</label>
                                   <input type="text" class="form-control" placeholder="Búscar por código Participante" id="parametro" name="parametro">
                                   <div id="gendererror" class="text-danger"></div>
                               </div>
                           </div>
                       </form>

                       <spring:url value="/Domicilio/SaveInfo" var="saveUrl"/>
                       <div class="row">
                       <form action="#" class="form-horizontal" id="formDom" name="formDom" autocomplete="off" method="post" role="form">
                               <div class="row">
                                   <div class="form-group col-sm-12">
                                       <label for="nombre">Nombre:</label>
                                       <input type="text" class="form-control" id="nombre" readonly/>
                                   </div>
                                       <div class="form-group col-sm-3">
                                           <label for="IdParticipante">Código Participante:</label>
                                           <input type="text"name="IdParticipante" id="IdParticipante" min="0" class="form-control focusNext" required="required" readonly/>
                                       </div>
                                       <div class="form-group col-sm-3">
                                           <label for="codigoCasa">Casa Pediátrica:</label>
                                           <span class="required text-danger"> * </span>
                                           <input type="text" id="codigoCasa" name="codigoCasa" min="0" class="form-control focusNext" required="required" tabindex="1"/>
                                       </div>
                                   <div class="form-group col-sm-3">
                                       <label for="casacohortefamilia">Casa Familia:</label>
                                       <input type="text" class="form-control focusNext" min="0" id="casacohortefamilia" name="casacohortefamilia" tabindex="2"/>

                                   </div>
                                   <div class="form-group col-sm-3">
                                       <label for="estudios">Estudio:</label>
                                       <input type="text" class="form-control focusNext" id="estudios" name="estudios" tabindex="3" readonly/>

                                   </div>
                               </div>

                           <div class="form-row">
                               <div class="form-group col-sm-6">
                                   <label for="barrio">Barrio:</label>
                                   <span class="required text-danger"> * </span>
                                   <select  class="form-control" name="barrio" id="barrio" required="required">
                                       <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${barrios}" var="barrio">
                                            <option value="${barrio.codigo}">${barrio.nombre}</option>
                                        </c:forEach>
                                   </select>
                               </div>

                               <div class="form-group col-md-3">
                                   <label for="manzana">Manzana:</label>
                                   <span class="required text-danger"> * </span>
                                   <input type="text" class="form-control focusNext" required="required" min="0" name="manzana" id="manzana" tabindex="4"/>
                               </div>
                               <div class="form-group col-md-3">
                                   <label for="manzana">Tel.:</label>
                                   <input type="tel" class="form-control focusNext" min="0" name="telefono" id="telefono" tabindex="5"/>
                               </div>

                               <div id="bar" class="form-group col-sm-12" style="display: none">
                                   <label for="otroBarrio">Barrio:</label>
                                   <input type="text" class="form-control" id="otroBarrio" name="otroBarrio" style="text-transform:uppercase" />
                               </div>
                               <div class="form-group col-sm-12">
                                   <label for="direccion">Dirección:</label>
                                   <span class="required text-danger"> * </span>
                                   <input type="text" class="form-control focusNext" id="direccion" required="required" name="direccion" placeholder="Metrocentro 2c al lago" tabindex="6"/>
                               </div>
                                   <div class="form-group col-sm-6">
                                       <label for="razonnogeoref">GeoReferencia:</label>
                                       <span class="required text-danger"> * </span>
                                       <select name="razonnogeoref" id="razonnogeoref" class="form-control" required="required">
                                           <option selected value=""> Seleccione...</option>
                                                <c:forEach items="${NoGeo}" var="NoGeo">
                                                    <option value="${NoGeo.catKey}">${NoGeo.spanish}</option>
                                                </c:forEach>
                                       </select>
                                   </div>

                                   <div class="form-group col-sm-3">
                                       <label for="recurso1">Usuario:</label>
                                       <span class="required text-danger"> * </span>
                                       <select name="recurso1" id="recurso1" class="form-control" required="required">
                                           <option selected value=""> Seleccione...</option>
                                           <c:forEach items="${person}" var="person">
                                               <option value="${person.idPersona}">${person.nombre}</option>
                                           </c:forEach>
                                       </select>
                                   </div>
                               <div class="form-group col-sm-3">
                                   <label for="fecha_reportado">Fecha:</label>
                                   <span class="required text-danger"> * </span>
                                   <input type="text" id="fecha_reportado" name="fecha_reportado" class="form-control" data-date-end-date="+0d"/>
                               </div>
                               <div class="col-md-12">
                                   <div class="form-group">
                                       <label for="observacion">Observación: </label>
                                       <input type="text" class="form-control" id="observacion" name="observacion"/>
                                   </div>
                               </div>
                               <br>
                               <br>
                               <hr/>
                               <div class="autoUpdate col-md-12" hidden="hidden">
                               <div class="form-group col-sm-12">
                                   <div class="form-check-inline">
                                       <div class="custom-control custom-checkbox">
                                           <input type="checkbox" class="custom-control-input" id="actual" name="actual" required="required">
                                           <label class="custom-control-label" for="actual">Actual</label>
                                       </div>
                                   </div>
                                   <div class="form-check-inline">
                                   <div class="custom-control custom-checkbox">
                                       <input type="checkbox" class="custom-control-input" id="conpunto" name="conpunto" required="required">
                                       <label class="custom-control-label" for="conpunto">Punto GPS</label>
                                   </div>
                               </div>
                               </div>
                               </div>

                               <div class="form-group col-sm-3">
                                   <button type="submit" id="btnSave" class="btn btn-primary btn-block btn-lg">
                                       <i class="fa fa-save" aria-hidden="true"></i>
                                       Enviar
                                   </button>
                               </div>
                               <div class="form-group col-sm-6"> </div>
                               <div class="form-group col-sm-3">
                                   <a href="<spring:url value='/Domicilio/Listado' htmlEscape='true'/>" class="btn btn-danger btn-block btn-lg"><i class="fa fa-undo" aria-hidden="true"></i>
                                       Cancel</a>
                               </div>
                           </div>
                       </form>
                       </div>
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
<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<script type="text/javascript">
    var f1 = $("#formDom");
    $("#fecha_reportado").datepicker({
        format: "dd/mm/yyyy",
        todayBtn:true,
        todayHighlight: true,
        autoclose: true,
        endDate: '-0d'
    });
    $(document).ready(function(){
        var parametros = {searchPartUrl: "${searchPartUrl}",
                                   list:"${ListadoUrl}"
        };
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
            }
        });

        function searchParticipante(){
            $.getJSON(parametros.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                var len = data.length;
                if(len == 0){
                    swal("Error!","Código no encontrado","error");
                    $("#IdParticipante").val("");
                    $("#direccion").val("");
                    $("#codigoCasa").val("");
                    $("#barrio").val("").change();
                    $("#manzana").val("");
                    $("#casacohortefamilia").val("");
                    $("#estudios").val("");
                    $("#nombre").val("");
                    $("#observacion").val("");
                    $("#parametro").focus();
                }
                else{
                    if(data.estado == "0"){
                        swal("Advertencia!", "Participante está retirado!", "warning");
                        $("#IdParticipante").val("");
                        $("#direccion").val("");
                        $("#codigoCasa").val("");
                        $("#barrio").val("").change();
                        $("#manzana").val("");
                        $("#casacohortefamilia").val("");
                        $("#estudios").val("");
                        $("#nombre").val("");
                        $("#observacion").val("");
                        $("#parametro").focus();
                    }else{
                        $("#IdParticipante").val(data.codigo);
                        $("#direccion").val(data.direccion);
                        $("#codigoCasa").val(data.casaP);
                        $("#barrio").val(data.barrio).change();
                        $("#manzana").val(data.manzana);
                        $("#casacohortefamilia").val(data.CFamilia);
                        $("#estudios").val(data.estudios);
                        $("#nombre").val(data.nombre);
                    }
                }
            }).fail(function() {
                swal("Error!","Código no existe!", "error");
                $("#IdParticipante").val("");
                $("#direccion").val("");
                $("#codigoCasa").val("");
                $("#barrio").val("").change();
                $("#manzana").val("");
                $("#casacohortefamilia").val("");
                $("#estudios").val("");
                $("#nombre").val("");
                $("#observacion").val("");
                $("#parametro").focus();
            });
        }
        $("#parametro").focus();
        $("#barrio").select2()
        $("#razonnogeoref").select2();
        $("#recurso1").select2();
        $("#barrio").on("change", function () {
            if (this.value == 18) {
                $("#bar").show("slow");
                $("#otroBarrio").attr("required", "true");
            } else {
                $("#bar").hide("slow");
                $("#otroBarrio").val("").attr("required", "false");
            }
        });

        var send = {saving: "${saveUrl}", List:"${ListadoUrl}"};
        var f1 = $("#formDom");
        f1.validate({
            rules: {
                IdParticipante: {
                    required: true,
                    number: true
                },
                cpds: {
                    required: true,
                    number: true
                },
                manzana: {
                    digits: true
                },
                telefono:{number: true},
                recurso1:{required: true},
                actual:{required: true},
                fecha_reportado:{required: true}
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
                Save(send);
            }
        });


    });
    function Save(dir){
        debugger;
        console.log(f1.serialize());
        $.post(dir.saving, f1.serialize(), function(data){
            swal("Éxito!", "Información guardada!", "success");
            window.setTimeout(function(){
                window.location.href = dir.List;
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
</script>
</body>
</html>
