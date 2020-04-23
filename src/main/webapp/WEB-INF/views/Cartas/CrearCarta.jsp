<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <style>
        span.error {
            display:block;
            visibility:hidden;
            color:red;
            font-size:90%;
        }
        #error {
            color:red;
            display:none;
        }
        /*Inicio Preload*/

        #page-loader {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 1000;
            background: #FFF none repeat scroll 0% 0%;
            z-index: 99999;
        }

        #page-loader .preloader-interior {
            display: block;
            position: relative;
            left: 50%;
            top: 50%;
            width: 150px;
            height: 150px;
            margin: -75px 0 0 -75px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #3498db;

            -webkit-animation: spin 2s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 2s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        #page-loader .preloader-interior:before {
            content: "";
            position: absolute;
            top: 5px;
            left: 5px;
            right: 5px;
            bottom: 5px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #e74c3c;

            -webkit-animation: spin 3s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 3s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        #page-loader .preloader-interior:after {
            content: "";
            position: absolute;
            top: 15px;
            left: 15px;
            right: 15px;
            bottom: 15px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #f9c922;

            -webkit-animation: spin 1.5s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 1.5s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        @-webkit-keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(0deg);
                /* IE 9 */
                transform: rotate(0deg);
                /* Firefox 16+, IE 10+, Opera */
            }

            100% {
                -webkit-transform: rotate(360deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(360deg);
                /* IE 9 */
                transform: rotate(360deg);
                /* Firefox 16+, IE 10+, Opera */
            }
        }

        @keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(0deg);
                /* IE 9 */
                transform: rotate(0deg);
                /* Firefox 16+, IE 10+, Opera */
            }

            100% {
                -webkit-transform: rotate(360deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(360deg);
                /* IE 9 */
                transform: rotate(360deg);
                /* Firefox 16+, IE 10+, Opera */
            }
        }



        /*Fin preload*/
    </style>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/smartWizardCss/smarthWizardCss.css" var="smw" />
    <link href="${smw}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/smartWizardCss/smart_wizard_theme_arrows.min.css" var="smwtheme" />
    <link href="${smwtheme}" rel="stylesheet" type="text/css"/>
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
                <a href="<spring:url value="/cartas/ListadoCartaParticipant/" htmlEscape="true "/>">LISTADO</a>
                <i class="fa fa-angle-right">
                </i> <a href="<spring:url value="/cartas/Crear" htmlEscape="true"/>">CARTAS</a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div id="page-loader">
                <span class="preloader-interior"></span>
            </div>
                    <!-- inicio smart wizard  class="needs-validation" novalidate-->
                    <div class="card shadow-lg p-3 mb-5 bg-white">
                        <div id="error" class="alert alert-default alert-dismissible fade show" role="alert" id="message-box">
                         <h1 class="text-danger" id="msj"><strong>Error!</strong> revisa todos campos. </h1>
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                    <div class="card-header">
                        <h3 class="text-gray-dark" style="font-family: Roboto">
                            <i class="fa fa-file-text" aria-hidden="true"></i>
                            <spring:message code="Cartas del Participante "  />
                        </h3>
                    </div>
                    <div class="card-body">
                    <spring:url value="/cartas/searchParticipant" var="searchPartUrl"/>
                    <spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
                    <spring:url value="/cartas/ParteVersion" var="ParteVersionUrl"/>
                    <spring:url value="/cartas/saveScanCarta" var="saveScanCartaUrl"/>
                    <spring:url value="/cartas/ListadoCartaParticipant" var="Lista2ScanCartaUrl"/>
                    <!-- SmartWizard html -->
                    <div id="smartwizard">
                    <ul>
                        <li><a href="#step-1">1 - Búscar<br /><small>Datos del Participante.</small></a></li>
                        <li><a href="#step-2">2 - Tipo<br /><small>Carta.</small></a></li>
                       <!--  <li><a href="#step-3">3 - Otros<br /><small>Datos.</small></a></li> -->
                        <li><a href="#step-3">3 - Información<br /><small>de Tutor.</small></a></li>
                    </ul>
                    <div>
                    <div id="step-1">
                        <h3 class="border-bottom border-gray pb-2" style="font-family: Roboto">
                            <i class="fa fa-search" aria-hidden="true"></i>
                            Búscar Participante
                        </h3>
                        <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                            <div class="row">
                                <div class="form-group col-sm-12 col-md-6 col-lg-12">
                                    <label>Código Participante :</label>
                                    <input type="text" class="form-control" placeholder="Ingrese el código" id="parametro" name="parametro">
                                    <div id="gendererror" class="text-danger"></div>
                                </div>
                            </div>
                        </form>

                        <div class="row">
                            <div class="form-group col-md-3">
                                <label>Código :</label>
                                <input type="text" class="form-control" name="codigo" id="codigo" disabled/>
                                <span class="error">Código es requerido</span>
                            </div>
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label for="txtNombreCompleto">Participante :</label>
                                    <input type="text" class="form-control" id="txtNombreCompleto" name="txtNombreCompleto" disabled>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="edad">Edad :</label>
                                    <input type="text" class="form-control" id="edad" name="edad" disabled>
                                </div>
                            </div>


                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="estudios">Estudios :</label>
                                    <input type="text" class="form-control" id="estudios" name="estudios" disabled>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="padre">Padre :</label>
                                    <input type="text" class="form-control" id="padre" name="padre" disabled>
                                </div>
                            </div>


                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="madre">Madre :</label>
                                    <input type="text" class="form-control" id="madre" name="madre" disabled>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="relacionFam">Relación del tutor con el niño :</label>
                                    <input type="text" class="form-control" id="relacionFam" name="relacionFam" disabled>
                                </div>
                            </div>


                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="tutor">Tutor :</label>
                                    <input type="text" class="form-control" id="tutor" name="tutor" disabled>
                                </div>
                            </div>
                        </div><!-- row -->
                    </div><!-- fin step 01  -->

                    <div id="step-2">
                        <form id="form-scan" class="needs-validation" novalidate>
                            <h3 class="border-bottom border-gray pb-2" style="font-family: Roboto">
                                <i class="fa fa-file-text-o" aria-hidden="true"></i> Tipo Carta</h3>
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
                                            <c:forEach items="${carta}" var="c">
                                                <option value="${c.idcarta}">${c.carta} </option>
                                            </c:forEach>
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

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="partes">Partes :</label>
                                        <span class="required text-danger"> * </span>
                                        <select class="form-control select2-multiple" multiple id="partes" name="partes" required="required">
                                        </select>
                                        <span class="error">Seleccione al menos una opción.</span>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="person">Recurso:</label>
                                        <span class="required text-danger"> * </span>
                                        <select name="person" id="person" class="form-control" required="required">
                                            <option selected value=""><spring:message code="select"/>...</option>
                                            <c:forEach items="${person}" var="p">
                                                <option value="${p.codigo}">${p.idPersona} - ${p.nombre}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="error">Seleccione el recurso.</span>
                                    </div>
                                </div>

                            </div><!-- fin row -->

                            <div class="row">
                                <div class="col-md-12"></div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="asentimiento">Asentimiento: </label>
                                        <span class="required text-danger"> * </span>
                                        <select name="asentimiento" id="asentimiento" class="form-control" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${SiNoNA}" var="s">
                                                <option value="${s.catKey}"><spring:message code="${s.spanish}" /></option>
                                            </c:forEach>
                                        </select>
                                        <span class="error">Seleccione tipo asentimiento.</span>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div id="DivtipoAsent">
                                        <div class="form-group">
                                            <label for="tipoasentimiento">Tipo Asentimiento: </label>
                                            <select name="tipoasentimiento" id="tipoasentimiento" class="form-control" required="required">
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
                                        <select name="proyecto" id="proyecto" class="form-control" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${proyecto}" var="p">
                                                <option value="${p.catKey}"><spring:message code="${p.spanish}" /></option>
                                            </c:forEach>
                                        </select>
                                        <span class="error">Seleccione el proyecto.</span>
                                    </div>
                                </div>


                            </div>


                    </div>

                    <!-- <div id="step-3"></div> -->

                    <div id="step-3">
                        <div class="row">
                            <div class="col-md-12">
                                <h3 class="border-bottom border-gray pb-2" style="font-family: Roboto">
                                    <i class="fa fa-id-card" aria-hidden="true"></i> Información del Tutor.</h3>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nombfirma">1er. Nombre : </label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="nombfirma" name="nombfirma" placeholder="Nombre 1" required/>
                                    <span class="error">1er. Nombre es requerido.</span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nombre2Firma">2do. Nombre :</label>
                                    <input type="text" class="form-control" id="nombre2Firma" name="nombre2Firma" placeholder="Nombre 2">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="apellido1Firma">1er. Apellido :</label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="apellido1Firma" required name="apellido1Firma" placeholder="Apellido 1">
                                    <span class="error">1er. Apellido es requerido.</span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nombre2Firma">2do. Apellido :</label>
                                    <input type="text" class="form-control" id="apellido2Firma" name="apellido2Firma" placeholder="Apellido 2">
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group has-danger">
                                    <label for="relfam">Relación Familiar: </label>
                                    <span class="required text-danger"> * </span>
                                    <select name="relfam" id="relfam" class="form-control" required>
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${relFam}" var="rel">
                                            <option value="${rel.catKey}">${rel.spanish}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="error">Selecciona el tipo de relación</span>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="observacion">Observación:</label>
                                    <input type="text" class="form-control" id="observacion" name="observacion" placeholder="observacion">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" name="contactoFuturo" id="contactoFuturo">
                                    <label class="custom-control-label" for="contactoFuturo">Contacto Futuro</label>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="retiro" name="retiro">
                                    <label class="custom-control-label" for="retiro">Retirado</label>
                                </div>
                            </div>
                            <br/>
                            <br/>

                            <hr/>
                            <div class="col-md-4">
                                <button type="submit" id="btnSave" class="btn btn-primary btn-block btn-lg">
                                    <i class="fa fa-floppy-o" aria-hidden="true"></i>
                                    Guardar
                                </button>
                            </div>
                            <div class="col-md-4">
                            </div>
                            <div class="col-md-4">
                               <button type="button" id="btnCancel" class="btn btn-warning btn-block btn-lg">
                                    <i class="fa fa-refresh" aria-hidden="true"></i>
                                    Cancelar
                                </button>

                                <!--     <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                    <spring:message code="Cancelar" /></a>

                                -->
                            </div>

                        </div>
                    </div>

                    </form>
                    </div>
                    </div>
                    <!-- smart Wizard  -->
                    </div> <!-- card-body  -->
                    </div><!-- fin card  -->
                    <!-- inicio smart wizard  -->
                </div><!-- animated  -->
            </div><!-- container   -->
        </div><!-- main  -->
    </div><!-- </div> app-boddy -->
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


<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>



<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<!-- Custom scripts required by this view -->
<spring:url value="/resources/js/views/Cartas/Cartas.js" var="cartaScript" />
<script src="${cartaScript}" type="text/javascript"></script>
<c:set var="notFound"><spring:message code="noResults" /></c:set>

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>


<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>

<script>
    $(document).ready(function(){
        setTimeout(function () {
            $('#page-loader').fadeOut('slow');
        }, 1500);
        $('#smartwizard').smartWizard({
            selected: 0,
            theme: 'arrows',
            lang: {  // Language variables
                next: 'Sig',
                previous: 'Prev'
            }
        });
        var parametros = {
            searchPartUrl: "${searchPartUrl}",
            Lista2ScanCartaUrl :"${Lista2ScanCartaUrl}",
            VersionCartatUrl: "${VersionCartatUrl}",
            ParteVersionUrl:"${ParteVersionUrl}",
            saveScanCartaUrl:"${saveScanCartaUrl}",
            notFound: "${notFound}"};
        scanCarta.init(parametros);
        var elementos = [];
        $("#btnCancel").click(function(){
            /*var selected=[];
            var sel ={};
            $('#partes').select2().each(function(){
                selected[$(this).val()]=$(this).val();
                sel = {"id": $(this).val(),
                       "text": $(this).text()
                }
            });
            var ar=$("#partes").find(':selected');
            console.log(ar);*/
        })
        $("#version").prop('disabled',true);
        $("#partes").prop('disabled',true);
        $("#partes").select2({placeholder: "Selección parte"});
        $("#fechacarta").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true
        });
/*
        $("#asentimiento").on("change", function(){
            if(this.value == 1 ){
                $("#DivtipoAsent").fadeIn("slow");
                $("#tipoasentimiento").attr("required", "true");
            }else{
                $("#DivtipoAsent").fadeOut("slow");
                $("#tipoasentimiento").val("").attr("required", "false");
            }
        });
*/
        $("#parametro").focus();
    });
</script>
</body>
</html>