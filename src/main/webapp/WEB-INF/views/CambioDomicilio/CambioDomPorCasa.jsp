<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 04/03/2021
  Time: 14:51
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
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
    <link rel="stylesheet" href="${bdt}" type="text/css"/>

    <spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
    <link rel="stylesheet" href="${bdat4}" type="text/css"/>

    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

    <style>

        /* begin toast*/
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
        /* fin toastr*/

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
                <a href="<spring:url value="/Domicilio/Listado" htmlEscape="true "/>"><spring:message code="Lista Coordenadas" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/Domicilio/CambioPorCasa" htmlEscape="true "/>"><spring:message code="Cambio de Domicilio" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <spring:url value="/Domicilio/CambioPorCasa" var="CambioDomUrl"></spring:url>
            <div class="animated fadeIn">
                <div class="container col-sm-12 col-md-12 col-lg-12">
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <spring:url value="/Domicilio/searchByHouse" var="searchByHouseUrl"></spring:url>
                        <spring:url value="/Domicilio/SaveListDom" var="saveUrl"></spring:url>
                        <spring:url value="/Domicilio/CambioPorCasa" var="FormHouseUrl"></spring:url>
                        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                        <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
                        <div class="card">
                            <div class="card-header">
                                <h4 class="text-dark pb-3"style="font-family: Roboto">
                                    <i class="fa fa-refresh" aria-hidden="true"></i> <spring:message code="Cambio de Domicilio."/> </h4>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="col-md-12">
                                            <form action="#" id="select-Casa-form" autocomplete="off" class="form-horizontal">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <div class="form-group">
                                                            <label class="form-control-label" for="codCasa"><spring:message code="please.enter"/> <spring:message code="parameter"/></label>
                                                            <input type="text" class="form-control" id="codCasa" name="codCasa" placeholder="<spring:message code="please.enter"/> <spring:message code="parameter"/>">
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <div class="table-responsive">

                                            <table id="tblExample" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                                <thead>
                                                <tr>
                                                    <th scope="col"><i class="fa fa-check-square-o" aria-hidden="true"></i></th>
                                                    <th scope="col"><spring:message code="CÓDIGO" /></th>
                                                    <th scope="col"><spring:message code="NOMBRE" /></th>
                                                    <th scope="col"><spring:message code="C.PEDIATRICA" /></th>
                                                    <th scope="col"><spring:message code="DIRECCIÓN" /></th>
                                                    <th scope="col"><spring:message code="C.FAMILIA" /></th>
                                                    <th scope="col"><spring:message code="ACTIVO" /></th>
                                                </tr>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div>
                                    </div><!-- fin md 6 -->
                                    <div class="col-md-8">
                                        <form>
                                            <div class="row" hidden="hidden">
                                                <div class="form-group col-md-6">
                                                    <label for="casaP">Casa Pedíatrica</label>
                                                    <input type="text" class="form-control" id="casaP"name="casaP"  >
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="casaFam">Casa Familia</label>
                                                    <input type="text" class="form-control" id="casaFam" name="casaFam" >
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6">
                                                    <label for="barrio">Barrio</label>
                                                    <span class="required text-danger"> * </span>
                                                    <select  class="form-control focusNext" name="barrio" id="barrio" required="required" tabindex="1">
                                                        <option selected value=""><spring:message code="select" />...</option>
                                                        <c:forEach items="${barrios}" var="barrio">
                                                            <option value="${barrio.codigo}">${barrio.nombre}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div id="bar" class="form-group col-sm-12" style="display: none">
                                                    <label for="otroBarrio">Barrio:</label>
                                                    <input type="text" class="form-control" id="otroBarrio" name="otroBarrio" style="text-transform:uppercase" />
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="manzana">Manzana</label>
                                                    <input type="text" class="form-control num focusNext" id="manzana" name="manzana" tabindex="4" required="required">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="dir">Dirección</label>
                                                <input type="text" class="form-control focusNext" id="dir" name="dir" tabindex="5" required="required">
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6">
                                                    <label for="telefono">Teléfono</label>
                                                    <input type="text" class="form-control num focusNext" id="telefono" name="telefono" tabindex="5">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="razonnogeoref">Georeferencia</label>
                                                    <span class="required text-danger"> * </span>
                                                    <select name="razonnogeoref" id="razonnogeoref" class="form-control focusNext" required="required" tabindex="6">
                                                        <option selected value=""> <spring:message code="select" /></option>
                                                        <c:forEach items="${NoGeo}" var="NoGeo">
                                                            <option value="${NoGeo.catKey}">${NoGeo.spanish}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="form-group col-md-6">
                                                    <label for="recurso1">Usuario</label>
                                                    <span class="required text-danger"> * </span>
                                                    <select name="recurso1" id="recurso1" class="form-control focusNext" required="required" tabindex="7">
                                                        <option selected value=""><spring:message code="select" /></option>
                                                        <c:forEach items="${person}" var="person">
                                                            <option value="${person.personal.idPersona}">${person.personal.idPersona} - ${person.personal.nombre}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="fecha_reportado">Fecha</label>
                                                    <span class="required text-danger"> * </span>
                                                    <input type="text" id="fecha_reportado" name="fecha_reportado" class="form-control focusNext" data-date-end-date="+0d" tabindex="8" required="required"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="observacion">Observación</label>
                                                <input type="text" class="form-control focusNext" id="observacion" name="observacion" tabindex="9"/>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-md-4">
                                                    <button type="button" id="btnObtChecked" class="btn btn-primary btn-block btn-lg">
                                                        <i class="fa fa-save" aria-hidden="true"></i>
                                                        <spring:message code="save" />
                                                    </button>
                                                </div>
                                                <div class="form-group col-md-4">

                                                </div>
                                                <div class="form-group col-md-4">
                                                    <button type="reset" class="btn btn-warning btn-block btn-lg">
                                                        <i class="fa fa-refresh" aria-hidden="true"></i>
                                                        <spring:message code="cancel" /></button>
                                                </div>
                                            </div>

                                        </form>

                                    </div><!-- fin md 6 -->
                                </div>
                            </div>
                            <div class="card-footer text-muted">
                            </div>
                        </div>
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

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>

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
<!-- GenesisUI main scripts -->

<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/jquery.maskedinput.js" var="maskJs" />
<script type="text/javascript" src="${maskJs}"></script>

<!-- Custom scripts required by this view -->
<spring:url value="/resources/scripts/CambioDomicilio/CambioCasa.js" var="CambiarCasaScript" />
<script src="${CambiarCasaScript}" type="text/javascript"></script>


<script type="text/javascript">
    $(document).ready(function(){
        $("#barrio").select2();
        $("#razonnogeoref").select2();
        $("#recurso1").select2();
        $("#fecha_reportado").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });
        var parametro = {
            searchByHouseUrl:"${searchByHouseUrl}",
            dataTablesLang: "${dataTablesLang}",
            saveUrl:"${saveUrl}",
            FormHouseUrl: "${FormHouseUrl}",
            successmessage: "${successMessage}",
            error: "${errorProcess}"
        }
        CambioCasa.init(parametro);
        $('#codCasa').focus();
    });
</script>
</body>
</html>