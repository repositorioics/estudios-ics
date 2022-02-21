<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 09/05/2020
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>


    <style>
        .accordion .card {
            border: none;
            margin-bottom: 2px;
            border-radius: 0;
            box-shadow: 0 0 6px rgba(0,0,0,0.2);
        }
        .accordion .card .card-header {
            /*background: #6245dd;*/
            background: #008cba;
            padding-top: 7px;
            padding-bottom: 7px;
            border-radius: 0;
        }
        .accordion .card-header h2 {
            color: #fff;
            font-size: 1rem;
            font-weight: 500;
            font-family: "Roboto", sans-serif;
        }
        .accordion img {
            width: 150px;
        }
        .accordion .card-header h2 span {
            float: left;
            margin-top: 10px;
        }
        .accordion .card-header .btn {
            font-weight: 500;
        }
        .accordion .card-header i {
            color: #fff;
            font-size: 1.3rem;
            margin: 0 6px 0 -10px;
            font-weight: bold;
            position: relative;
            top: 5px;
        }
        .accordion .card-header button:hover {
            color: #23384e;
        }
        .accordion .card-body {
            color: #666;
        }
        #parametro-error {
            margin-right: 759px;
        }

        .form-control:disabled, .daterangepicker .input-mini:disabled, .input-group > .ui-select-bootstrap > input.ui-select-search.form-control:disabled, .form-control[readonly], .daterangepicker [readonly].input-mini, .input-group > .ui-select-bootstrap > input[readonly].ui-select-search.form-control {
            background-color: #e9ebec00;
            opacity: 1;
            font-stretch: semi-condensed;

        }
        #parametro-error {
            margin-right: 759px;
        }
    </style>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp"/>
    <!-- Main content -->
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home"/></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/CatalogoVersion/ListadoVersion" htmlEscape="true "/>">
                    <spring:message code="Listado"/></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">

                <div class="container col-sm-12 col-lg-12">
                    <div class="">
                        <div class="card">
                            <div class="card-header">
                                <h5 style="font-family: Roboto">
                                    <i class="fa fa-users"></i> <spring:message code="Asignar Carta"/>
                                </h5>
                            </div>
                            <c:set var="userEnabledLabel"><spring:message code="login.userEnabled"/></c:set>
                            <c:set var="userDisabledLabel"><spring:message code="login.userDisabled"/></c:set>
                            <spring:url value="/cartas/searchParticipant" var="searchPartUrl"/>
                            <spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
                            <spring:url value="/cartas/ParteVersion" var="ParteVersionUrl"/>
                            <spring:url value="/cartas/saveScanCarta" var="saveScanCartaUrl"/>
                            <spring:url value="/cartas/ListadoCartaParticipant" var="Lista2ScanCartaUrl"/>
                            <spring:url value="/cartas/cartaSaveEdit" var="cartaSaveEditUrl"/>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom"
                                           title="Nueva Versión"
                                           href="<spring:url value="/CatalogoVersion/CrearNuevaVersion" htmlEscape="true "/>">
                                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                            <spring:message code="List"/>
                                        </a>
                                    </div>
                                </div>
                                <br/>
                                <!-- INIT COLLAPSE -->

                                <div class="container-lg">
                                    <div class="row">
                                        <div class="col-lg-12 mx-auto">
                                            <div class="accordion mt-5" id="accordionExample">
                                                <div class="card">
                                                    <div class="card-header" id="headingOne">
                                                        <h2 class="clearfix mb-0">
                                                            <a class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                                <i class="fa fa-plus-circle"></i> <spring:message code="Información del Paticipante" /></a>
                                                        </h2>
                                                    </div>
                                                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                                                        <div class="card-body">
                                                            <div class="media">
                                                                <div class="media-body">
                                                                    <div class="container">
                                                                        <form action="#" autocomplete="off" id="select-participante-form" name="select-participante-form" class="form-horizontal">
                                                                            <div class="form-group row">
                                                                                <label class="form-control-label col-md-2 text-right" for="username"><spring:message code="participant.code" />
                                                                                    <span class="required">*</span>
                                                                                </label>
                                                                                <div class="input-group col-md-10">
                                                                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                                                    <input id="parametro" name="parametro" type="text" value="" class="form-control"/>
                                                                                    <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right">
                                                                                        <i class="fa fa-search" aria-hidden="true"></i>
                                                                                        <spring:message code="search" />
                                                                                    </button>
                                                                                </div>
                                                                            </div>
                                                                        </form>
                                                                    </div>
                                                                    <hr/>
                                                                    <form>
                                                                        <div class="form-row">
                                                                            <div class="form-group col-md-4">
                                                                                <label>Código :</label>
                                                                                <input type="text" class="form-control form-control-sm" name="codigo" id="codigo" disabled/>
                                                                                <span class="error">Código es requerido</span>
                                                                            </div>
                                                                            <div class="form-group col-md-8">
                                                                                <label for="txtNombreCompleto">Participante :</label>
                                                                                <input type="text" class="form-control form-control-sm" id="txtNombreCompleto" name="txtNombreCompleto" disabled>
                                                                            </div>
                                                                        </div>

                                                                        <div class="form-row">
                                                                            <div class="form-group col-md-1">
                                                                                <label for="edadyear">Edad en Años:</label>
                                                                                <input type="text" class="form-control form-control-sm" id="edadyear" name="edadyear" disabled>
                                                                            </div>

                                                                            <div class="form-group col-md-1">
                                                                                <label for="edadmeses">Edad en Meses:</label>
                                                                                <input type="text" class="form-control form-control-sm" id="edadmeses" name="edadmeses" disabled>
                                                                            </div>

                                                                            <div class="form-group col-md-1">
                                                                                <label for="edaddias">Edad en Días:</label>
                                                                                <input type="text" class="form-control form-control-sm" id="edaddias" name="edaddias" disabled>
                                                                            </div>

                                                                            <div class="form-group col-md-5">
                                                                            <label for="estudios">Estudios :</label>
                                                                            <input type="text" class="form-control form-control-sm" id="estudios" name="estudios" disabled>
                                                                            </div>

                                                                            <div class="form-group col-md-4">
                                                                              <label for="padre">Padre :</label>
                                                                              <input type="text" class="form-control form-control-sm" id="padre" name="padre" disabled>
                                                                            </div>

                                                                            <div class="form-group col-md-4">
                                                                               <label for="madre">Madre :</label>
                                                                               <input type="text" class="form-control form-control-sm" id="madre" name="madre" disabled>
                                                                            </div>

                                                                            <div class="form-group col-md-4">
                                                                              <label for="relacionFam">Relación del tutor con el niño :</label>
                                                                              <input type="text" class="form-control form-control-sm" id="relacionFam" name="relacionFam" disabled>
                                                                            </div>

                                                                            <div class="form-group col-md-4">
                                                                              <label for="tutor">Tutor :</label>
                                                                              <input type="text" class="form-control form-control-sm" id="tutor" name="tutor" disabled>
                                                                            </div>
                                                                        </div>

                                                                        <div class="form-row">
                                                                            <div class="form-group col-md-6">
                                                                                <label for="inputCity">City</label>
                                                                                <input type="text" class="form-control" id="inputCity">
                                                                            </div>
                                                                            <div class="form-group col-md-4">
                                                                                <label for="inputState">State</label>
                                                                                <select id="inputState" class="form-control">
                                                                                    <option selected>Choose...</option>
                                                                                    <option>...</option>
                                                                                </select>
                                                                            </div>
                                                                            <div class="form-group col-md-2">
                                                                                <label for="inputZip">Zip</label>
                                                                                <input type="text" class="form-control" id="inputZip">
                                                                            </div>
                                                                        </div>
                                                                        <div class="d-flex justify-content-between">
                                                                            <div class="p-2 bd-highlight">
                                                                                <button type="submit" class="btn btn-primary"> <i class="fa fa-save"></i> <spring:message code="save" /></button>
                                                                            </div>
                                                                            <div class="p-2 bd-highlight">Flex item 2</div>
                                                                            <div class="p-2 bd-highlight">
                                                                                <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                                                                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                                                    <spring:message code="Cancelar" /></a>
                                                                            </div>
                                                                        </div>

                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card">
                                                    <div class="card-header" id="headingTwo">
                                                        <h2 class="mb-0">
                                                            <a class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                                <i class="fa fa-plus-circle"></i> Collapsible Group Item #2</a>
                                                        </h2>
                                                    </div>
                                                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                                                        <div class="card-body">
                                                            <div class="media">
                                                                <div class="media-body">

                                                                    <div class="row">
                                                                        <div class="col-md-12"></div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <label for="fechacarta">Fecha :</label>
                                                                                <span class="required text-danger"> * </span>
                                                                                <input type="text" class="form-control" required="required" name="fechacarta" id="fechacarta" data-date-end-date="+0d"/>
                                                                                <span class="error">Seleccione la fecha.</span>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <label for="carta">Carta :</label>
                                                                                <span class="required text-danger"> * </span>
                                                                                <select name="carta" id="carta" name="carta" class="form-control" required="required">
                                                                                    <option selected value=""><spring:message code="select" />...</option>
                                                                                </select>
                                                                                <span class="error">Seleccione la carta.</span>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <label for="version">Versión:</label>
                                                                                <span class="required text-danger"> * </span>
                                                                                <select name="version" id="version" class="form-control" required="required">
                                                                                    <option selected value=""><spring:message code="select" />...</option>
                                                                                </select>
                                                                                <span class="error">Seleccione la versión.</span>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-md-8">
                                                                            <div class="form-group">
                                                                                <label for="partes">Partes :</label>
                                                                                <span class="required text-danger"> * </span>
                                                                                <select class="form-control select2-multiple" multiple id="partes" name="partes" required="required">
                                                                                </select>
                                                                                <span class="error">Seleccione al menos una opción.</span>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <label for="person">Recurso:</label>
                                                                                <span class="required text-danger"> * </span>
                                                                                <select name="person" id="person" class="form-control" required="required">
                                                                                    <option selected value=""><spring:message code="select"/>...</option>
                                                                                    <c:forEach items="${person}" var="p">
                                                                                        <option value="${p.personal.codigo}">${p.personal.codigo} - ${p.personal.nombre}</option>
                                                                                    </c:forEach>
                                                                                </select>
                                                                                <span class="error">Seleccione el recurso.</span>
                                                                            </div>
                                                                        </div>


                                                                        <div class="col-md-4"  id="divAsentimiento" style="display: none">
                                                                            <div class="form-group">
                                                                                <label for="asentimiento">Asentimiento: </label>
                                                                                <span class="required text-danger"> * </span>
                                                                                <select name="asentimiento" id="asentimiento" class="form-control">
                                                                                    <option selected value=""><spring:message code="select" />...</option>
                                                                                    <c:forEach items="${SiNoNA}" var="s">
                                                                                        <option value="${s.catKey}"><spring:message code="${s.spanish}" /></option>
                                                                                    </c:forEach>
                                                                                </select>
                                                                                <span class="error">Seleccione tipo asentimiento.</span>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-md-4" id="divTipoAsentimiento" style="display: none">
                                                                            <div id="DivtipoAsent">
                                                                                <div class="form-group">
                                                                                    <label for="tipoasentimiento">Tipo Asentimiento: </label>
                                                                                    <select name="tipoasentimiento" id="tipoasentimiento" class="form-control">
                                                                                        <option selected value=""><spring:message code="select" />...</option>
                                                                                        <c:forEach items="${tpoasent}" var="ta">
                                                                                            <option value="${ta.catKey}"><spring:message code="${ta.spanish}" /></option>
                                                                                        </c:forEach>
                                                                                    </select>
                                                                                    <span class="error">Seleccione tipo asentimiento.</span>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <label for="proyecto">Proyecto: </label>
                                                                                <select name="proyecto" id="proyecto" class="form-control">
                                                                                    <option selected value=""><spring:message code="select" />...</option>
                                                                                    <c:forEach items="${proyecto}" var="p">
                                                                                        <option value="${p.catKey}"><spring:message code="${p.spanish}" /></option>
                                                                                    </c:forEach>
                                                                                </select>
                                                                                <span class="error">Seleccione el proyecto.</span>
                                                                            </div>
                                                                        </div>

                                                                    </div><!-- fin row -->







                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card">
                                                    <div class="card-header" id="headingThree">
                                                        <h2 class="mb-0">
                                                            <a class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                                <i class="fa fa-plus-circle"></i> Collapsible Group Item #3</a>
                                                        </h2>
                                                    </div>
                                                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                                                        <div class="card-body">
                                                            <div class="media">

                                                                <div class="media-body">
                                                                    <h5 class="mt-0">Find a Real Estate Agent</h5>
                                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu sem tempor, varius quam at, luctus dui. Mauris magna metus, dapibus nec turpis vel, semper malesuada ante. Vestibulum id metus ac nisl bibendum scelerisque non non purus. Suspendisse varius nibh non aliquet sagittis. In tincidunt orci sit amet elementum vestibulum.</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card">
                                                    <div class="card-header" id="headingFour">
                                                        <h2 class="mb-0">
                                                            <a class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                                                <i class="fa fa-plus-circle"></i> Collapsible Group Item #4</a>
                                                        </h2>
                                                    </div>
                                                    <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionExample">
                                                        <div class="card-body">
                                                            <div class="media">

                                                                <div class="media-body">
                                                                    <h5 class="mt-0">Get Details of the Locality</h5>
                                                                    <p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et.</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                               <!-- FIN COLLAPSE -->
                            </div>
                            <div class="card-footer text-muted"></div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div><jsp:include page="../fragments/bodyFooter.jsp" />
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
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<!-- Custom scripts required by this view -->
<spring:url value="/resources/js/views/Cartas/Cartas.js" var="cartaScript" />
<script src="${cartaScript}" type="text/javascript"></script>
<c:set var="notFound"><spring:message code="noResults" /></c:set>

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>

<spring:url value="/resources/js/libs/lc_switch.js" var="lc" />
<script type="text/javascript" src="${lc}"></script>


<script type="text/javascript">

    $(document).ready(function(){
// Add minus icon for collapse element which is open by default
        $(".collapse.show").each(function(){
            $(this).siblings(".card-header").find(".btn i").addClass("fa-minus-circle").removeClass("fa-plus-circle");
        });

        // Toggle plus minus icon on show hide of collapse element
        $(".collapse").on('show.bs.collapse', function(){
            $(this).parent().find(".card-header .btn i").removeClass("fa-plus-circle").addClass("fa-minus-circle");
        }).on('hide.bs.collapse', function(){
            $(this).parent().find(".card-header .btn i").removeClass("fa-minus-circle").addClass("fa-plus-circle");
        });
        $('.collapse').on('show.bs.collapse', function () {
            // do something…
            debugger;
        });
        lc_switch('#contactoFuturo',{
            on_txt: 'Si',
            off_txt: 'No'
        }); lc_switch('#chktestigo',{
            on_txt: 'Si',
            off_txt: 'No'
        });
        $("#carta").select2();
        $("#version").select2();
        $("#person").select2();
        $("#relfam").select2();
        $("#proyecto").select2();
        $("#asentimiento").select2();
        $("#tipoasentimiento").select2();

        var parametros = {
            searchPartUrl       : "${searchPartUrl}",
            Lista2ScanCartaUrl  : "${Lista2ScanCartaUrl}",
            VersionCartatUrl    : "${VersionCartatUrl}",
            ParteVersionUrl     : "${ParteVersionUrl}",
            saveScanCartaUrl    : "${saveScanCartaUrl}",
            UpdateRetiroUrl     : "${UpdateRetiroUrl}",
            notFound            : "${notFound}",
            cartaSaveEditUrl    : "${cartaSaveEditUrl}"
        };
        scanCarta.init(parametros);
        var elementos = [];

        $("#version").prop('disabled',true);
        $("#partes").prop('disabled',true);
        $("#partes").select2({placeholder: "Selección parte"});
        $("#fechacarta").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true
        });




        $("#parametro").focus();
    });
</script>
</body>
</html>
