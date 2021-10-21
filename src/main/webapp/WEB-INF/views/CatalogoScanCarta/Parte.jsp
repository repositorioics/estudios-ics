<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 10/05/2020
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/dtresponsive/twitter-bootstrap.css" var="boot1"/>
    <link href="${boot1}" rel="stylesheet" type="text/css"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <spring:url value="/resources/css/bootstrapdt.css" var="bdt"/>
    <link rel="stylesheet" href="${bdt}" type="text/css"/>

    <style>
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
        /**/

        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid transparent;
            border-radius: 0;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link {
            border: 0;
            color: #fff;
            border-bottom: 3px solid transparent;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 3px solid #2cd07e;
        }
        .no-wrap td, .no-wrap th {
            white-space: nowrap;
        }
        .table td, .table th {
            padding: .9375rem .4rem;
            vertical-align: top;
            border-top: 1px solid rgba(120,130,140,.13);
        }
        .font-light {
            font-weight: 300;
        }
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #007bff;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #007bff;
        }
        /**/
    </style>
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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/CatalogoParte/CrearNuevaParte/" htmlEscape="true "/>">
                    <spring:message code="Asignar Parte a la Versión" />
                </a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
               <%-- <div id="page-loader">
                    <span class="preloader-interior"></span>
                </div>--%>

                <div class="">
                <div class="row">
                <div class="col-md-12 col-lg-12">
                <div class="card">
                <div class="card-body bg-primary text-white mailbox-widget pb-0">
                    <h2 class="text-white pb-3"><spring:message code="Asignar Parte"/></h2>
                    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                                <span class="d-none d-md-block"> <spring:message code="Formulario"/></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                                <span class="d-block d-md-none"><i class="ti-export"></i></span>
                                <span class="d-none d-md-block"><spring:message code="Listado"/></span>
                            </a>
                        </li>

                    </ul>
                </div>
                <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
                <div>
                    <spring:url value="/CatalogoParte/saveParte" var="saveParteUrl"/>
                    <spring:url value="/CatalogoParte/CrearNuevaParte" var="crearNuevaParteUrl"/>
                    <spring:url value="/CatalogoParte/GetVersion" var="GetVersionUrl"/>
                    <spring:url value="/CatalogoParte/delete" var="deleteUrl"/>
                    <spring:url value="/CatalogoParte/activar" var="activarUrl"/>
                    <div class="container col-md-12 col-xl-12">
                        <br>
                        <form autocomplete="off" id="frmParte" name="frmParte" class="form-horizontal">
                            <div hidden="hidden">
                                <div  class="form-group col-md-6">
                                    <label for="idparte">idparte:</label>
                                    <input type="text" class="form-control" id="idparte" name="idparte" value="${caso.idparte}">
                                </div>
                                <div  class="form-group col-md-6">
                                    <label for="editando">editando:</label>
                                    <input type="text" class="form-control" id="editando" name="editando" value="${editando}">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="idcarta" class="col-sm-2 col-form-label text-right"><spring:message code="Estudio" /></label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="idcarta" name="idcarta" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${estudios}" var="e">
                                            <c:choose>
                                                <c:when test="${caso.version.estudio.codigo eq e.codigo}">
                                                    <option selected value="${e.codigo}">${e.codigo} - ${e.nombre}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${e.codigo}">${e.codigo} - ${e.nombre}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="idversion" class="col-sm-2 col-form-label text-right"><spring:message code="Versión" /></label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="idversion" name="idversion" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${version}" var="v">
                                            <c:choose>
                                                <c:when test="${caso.version.idversion eq v.idversion}">
                                                    <option selected value="${v.idversion}"><spring:message code="${v.version}" /></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${v.idversion}"><spring:message code="${v.version}" /></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="parte" class="col-sm-2 col-form-label text-right"><spring:message code="Parte" /></label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" placeholder="Nomrbre de la Parte" id="parte" name="parte" required="required" value="${caso.parte}"/>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="parte" class="col-sm-2 col-form-label text-right"><spring:message code="Fecha" /> <spring:message code="Parte" /></label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control date-picker years" required="required" value="${caso.fecha_parte}" name="fecha_parte" id="fecha_parte" data-date-end-date="+0d"/>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="activo" class="col-sm-2 col-form-label text-right"><spring:message code="Activar?" /></label>
                                <div class="col-sm-8">
                                    <c:choose>
                                        <c:when test="${caso.activo eq true }">
                                            <p><input type="checkbox" class="custom-control-input" checked="checked" id="activo" name="activo"></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p><input type="checkbox" class="custom-control-input" id="activo" name="activo"></p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="activo" class="col-sm-2 col-form-label text-right"><spring:message code="Principal?" /></label>
                                <div class="col-sm-8">
                                    <c:choose>
                                        <c:when test="${caso.principal eq true }">
                                            <p><input type="checkbox" class="custom-control-input" checked="checked" id="principal" name="principal"></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p><input type="checkbox" class="custom-control-input" id="principal" name="principal"></p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-8">
                                    <div class="d-flex justify-content-between">
                                        <div class="p-2 bd-highlight">
                                            <button type="submit" id="btnSave" class="btn btn-info btn-block btn-lg btn-ladda" data-style="expand-right">
                                            <i class="fa fa-save"></i> <spring:message code="save" /></button>
                                        </div>
                                        <div class="p-2 bd-highlight"></div>
                                        <div class="p-2 bd-highlight">
                                            <a href="${fn:escapeXml(crearNuevaParteUrl)}" class="btn btn-warning btn-lg btn-ladda" data-style="expand-right">
                                                <i class="fa fa-minus-circle" aria-hidden="true">  </i><spring:message code="cancel" /></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2"></div>
                            </div>
                        </form>
                    </div>
                <!-- fin formulario-->

                </div>
                </div>

                <div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
                    <div class="row p-3 text-dark">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table id="tableParte" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th data-class="expand" class="text-center"><spring:message code="Código" /></th>
                                        <th data-hide="phone,tablet" class="text-center"><spring:message code="Creado"/></th>
                                        <th data-hide="phone,tablet" class="text-center"><spring:message code="Carta"/></th>
                                        <th data-hide="phone,tablet" class="text-center"><spring:message code="Versión" /></th>
                                        <th data-hide="phone,tablet" class="text-center"><spring:message code="Parte" /></th>
                                        <th data-hide="phone,tablet" class="text-center"><spring:message code="Principal" /></th>
                                        <th data-hide="phone,tablet" class="text-center"><spring:message code="Activo" /></th>
                                        <th data-hide="phone,tablet" class="text-center"><spring:message code="Editar" /></th>
                                        <sec:authorize access="hasRole('ROLE_WEB')">
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Opcion" /></th>
                                        </sec:authorize>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${parte}" var="p">
                                        <tr>
                                            <spring:url value="/CatalogoParte/editParte/{idparte}" var="editVersionUrl">
                                                <spring:param name="idparte" value="${p.idparte}" />
                                            </spring:url>
                                            <spring:url value="/CatalogoParte/Delete/{idparte}" var="delParteUrl">
                                                <spring:param name="idparte" value="${p.idparte}" />
                                            </spring:url>
                                            <spring:url value="/CatalogoParte/HabYDesParte/bloq/{idparte}" var="delParteUrl">
                                                <spring:param name="idparte" value="${p.idparte}" />
                                            </spring:url>
                                            <spring:url value="/CatalogoParte/HabYDesParte/Unbloq/{idparte}" var="enableParteUrl">
                                                <spring:param name="idparte" value="${p.idparte}" />
                                            </spring:url>
                                            <td class="text-center">${p.idparte}</td>
                                            <td class="text-center"><fmt:formatDate timeStyle = "medium" value="${p.recordDate}" pattern="dd/MM/yyyy hh:mm"/></td>
                                            <td>${p.version.estudio.nombre} </td>
                                            <td>${p.version.version} </td>
                                            <td class="text-center">${p.parte}</td>
                                            <c:choose>
                                                <c:when test="${p.principal eq true}">
                                                    <td class="text-center"> <span class="badge badge-success"><spring:message code="SI" /></span></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="text-center"> <span class="badge badge-danger"><spring:message code="NO" /></span></td>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${p.activo eq true}">
                                                    <td class="text-center"> <span class="badge badge-success"><spring:message code="SI" /></span></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="text-center"> <span class="badge badge-danger"><spring:message code="NO" /></span></td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td class="text-center">
                                                <a href="${fn:escapeXml(editVersionUrl)}" data-toggle="tooltip" data-placement="bottom" title="Editar" class="btn btn-warning btn-sm">
                                                    <i class="fa fa-edit"></i>
                                                </a>
                                            </td>
                                            <sec:authorize access="hasRole('ROLE_WEB')">
                                            <td class="text-center">
                                                <c:choose>
                                                    <c:when test="${p.activo eq true}">
                                                        <button  class="btn btn-primary btn-sm desact"><i class="fa fa-unlock"></i></button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-primary btn-sm activar"><i class="fa fa-lock"></i></button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            </sec:authorize>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
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
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

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
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/sweetalert.js" var="sweet"/>
<script type="text/javascript" src="${sweet}"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/libs/lc_switch.js" var="lc"/>
<script type="text/javascript" src="${lc}"></script>

<script type="text/javascript">
    <sec:authorize access="hasAnyRole('ROLE_WEB')" var="isAuthorizeAny"></sec:authorize>
    <sec:authorize access="hasRole('ADMINISTRADOR')" var="haRoleAdmin"></sec:authorize>

    $(document).ready(function(){
        /*setTimeout(function () {
            $('#page-loader').fadeOut('slow');
        }, 1000);*/

        $(".years").datepicker({
            changeMonth: true,
            changeYear: true,
            format: "mm-yyyy",
            startView: "months",
            minViewMode: "months",
            autoclose: true
        });
        debugger;
        if('${isAuthorizeAny}' == 'true'){
            console.log('hi ROLE_WEB');
        }

        if('${haRoleAdmin}' == true){
            console.log('hei ROLE_ADMIN');
        }

        var parametros = {
            saveParteUrl       : "${saveParteUrl}",
            crearNuevaParteUrl : "${crearNuevaParteUrl}",
            GetVersionUrl      : "${GetVersionUrl}",
            dataTablesLang     : "${dataTablesLang}",
            deleteUrl          : "${deleteUrl}",
            activarUrl         : "${activarUrl}"

        };
        lc_switch('#activo', {
            on_txt: 'Si',
            off_txt: 'No'
        });
        lc_switch('#principal', {
            on_txt: 'Si',
            off_txt: 'No'
        });
        var table = $("#tableParte").DataTable({
            "columnDefs": [{
                "targets": [0],
                "visible": false,
                "searchable": false
            }],"oLanguage": {
                "sUrl": parametros.dataTablesLang
            }
        });
        $("#idcarta").select2();
        $("#idversion").select2();
        $("#idcarta").on("change", function(){
            $("#idversion option").remove();
            $("#idversion").select2('val', '');
            ObtenerVersion(parametros);
        });
        function ObtenerVersion(parametros){
            debugger;
            var idcarta = document.getElementById('idcarta').value;
            var $version = $('#idversion');
            $version.empty();
            $.getJSON(parametros.GetVersionUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
                var len = data.length;
                if(len == 0){
                    toastr.error("Advertencia, Cartas no tiene Versión.","Error",{timeOut: 5000});
                }else{
                    var d = data.version;
                    $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                    $.each(d, function (i, val) {
                        console.log("cod: "+val.idversion + "  version" +val.version)
                        $version.append($('<option></option>').val(val.idversion).html(val.version));
                    });
                }
            });
        }
        var formP = $("#frmParte");
        formP.validate({
            rules:{
                idcarta :{required: true},
                idversion :{required: true},
                parte :{required: true}
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
                SaveParte(parametros);
            }
        });
        function SaveParte(dir){
            $.post(dir.saveParteUrl, formP.serialize(), function(data){
                if (data.msj != null) {
                    swal("Error!", data.msj, "error");
                }else{
                    swal("Éxito!", "Información guardada!", "success");
                    window.setTimeout(function(){
                        window.location.href = parametros.crearNuevaParteUrl;
                    }, 1500);
                }
            }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                swal("Error","Interno del Servidor.","error");
            });
        }




        $("#tableParte tbody").on("click", ".desact", function(){
            var currentRow = $(this).closest("tr");
            var row = $(this).parents('tr');
            var column01 = table.row(currentRow).data()[0];
            var column02 = table.row(currentRow).data()[2];
            var column03 = table.row(currentRow).data()[3];
            swal({
                title: "Desactivar? ",
                text: "Versión: " + column03 + "\n estudio: " + column02,
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Si, Desactivar!",
                cancelButtonText: "No, Desactives plx!",
                closeOnConfirm: false,
                closeOnCancel: false
            },function (isConfirm) {
                if (isConfirm) {
                    //row.remove();
                    $.post(parametros.deleteUrl, { idparte: column01, ajax: 'true' }).done(function (data) {
                        swal("Desactivado!", "con éxito!", "success");
                        window.setTimeout(function () {
                            window.location.href = parametros.crearNuevaParteUrl;
                            debugger;
                            $("#sent").addClass("active show");
                            $("#sent-tab").addClass("active");
                            $("#inbox-tab").removeClass("active");
                            $("#inbox").removeClass("active show");
                        }, 1500);

                    }).fail(function () {
                        setTimeout(function () {
                            swal("Error!", "Servidor no respode!", "error");
                        }, 2000);
                    });
                } else {
                    swal("Cancelado!", "Registro está seguro! :)", "error");
                }
            });

        });
        $("#tableParte tbody").on("click", ".activar", function(){
            var currentRow = $(this).closest("tr");
            var column01 = table.row(currentRow).data()[0];
            var column02 = table.row(currentRow).data()[2];
            var column03 = table.row(currentRow).data()[3];

            swal({
                title: "Activar? ",
                text: "Versión: " + column03 + "\n estudio: " + column02,
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-info",
                confirmButtonText: "Si, Activar!",
                cancelButtonText: "No, Actives plx!",
                closeOnConfirm: false,
                closeOnCancel: false
            },function (isConfirm) {
                if (isConfirm) {
                    //row.remove();
                    $.post(parametros.activarUrl, { idparte: column01, ajax: 'true' }).done(function (data) {
                        swal("Registro!", "activo!", "success");
                        window.setTimeout(function () {
                            window.location.href = parametros.crearNuevaParteUrl;
                            debugger;
                            $("#sent").addClass("active show");
                            $("#sent-tab").addClass("active");
                            $("#inbox-tab").removeClass("active");
                            $("#inbox").removeClass("active show");
                        }, 1500);

                    }).fail(function () {
                        setTimeout(function () {
                            swal("Error!", "Servidor no respode!", "error");
                        }, 2000);
                    });
                } else {
                    swal("Cancelado!", "Registro está seguro! :)", "error");
                }
            });
        })
    })
</script>
</body>
</html>
