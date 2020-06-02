<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 28/04/2020
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
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
    </style>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />


<spring:url value="/resources/css/bootstrap.min.css" var="boot" />
<link href="${boot}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/dtresponsive/twitter-bootstrap.css" var="boot1" />
<link href="${boot1}" rel="stylesheet" type="text/css"/>

<spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
<link rel="stylesheet" href="${bdt}" type="text/css"/>

<spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
<link rel="stylesheet" href="${bdat4}" type="text/css"/>

<spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
<link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

<spring:url value="/resources/css/smartWizardCss/smarthWizardCss.css" var="smw" />
<link href="${smw}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/css/smartWizardCss/smart_wizard_theme_arrows.min.css" var="smwtheme" />
<link href="${smwtheme}" rel="stylesheet" type="text/css"/>
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>


<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <!-- Main content -->
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/CatalogoCarta/CrearNuevaCarta" htmlEscape="true "/>">
                    <spring:message code="Carta" />
                </a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div id="page-loader">
                    <span class="preloader-interior"></span>
                </div>
                <!-- SmartWizard html -->
                <div id="smartwizard">
                    <ul>
                        <li><a href="#step-1">1. Crear<br /><small>Carta.</small></a></li>
                        <li><a href="#step-2">2. Listado<br /><small> de Cartas.</small></a></li>
                    </ul>
                    <div>
                        <div id="step-1" class="">
                            <h3 class="border-bottom border-gray pb-2">Crear Carta.</h3>
                            <form id="frmCarta" class="form-horizontal" name="frmCarta">
                                <div hidden="hidden" class="form-group">
                                    <label for="idcarta">Id</label>
                                    <input type="text" class="form-control" id="idcarta" name="idcarta">
                                </div>
                                <div class="form-group">
                                    <label for="NameCarta">Carta: </label>
                                    <input type="text" class="form-control" id="NameCarta" name="NameCarta" required="required" placeholder="Ingrese el nombre de la carta.">
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <button type="submit" id="btnSave" class="btn btn-primary btn-block btn-lg">
                                            <i class="fa fa-save" aria-hidden="false"></i> Guardar</button>
                                    </div>
                                    <div class="col-md-3"></div>
                                    <div class="col-md-3"></div>
                                    <div class="col-md-3">
                                        <button type="reset" class="btn btn-warning btn-block btn-lg" id="btnCancel">
                                            <i class="fa fa-arrow-circle-left" aria-hidden="true"></i> Cancel</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div id="step-2" class="">
                            <h3 class="border-bottom border-gray pb-2">Listado Cartas.</h3>
                            <div class="card">
                                <div class="card-block">
                                    <div class="container">
                                        <spring:url value="/CatalogoCarta/GetCarta" var="GetCartaByIDUrl"/>
                                        <spring:url value="/CatalogoCarta/SaveCarta" var="saveCatalogCartaUrl"/>
                                        <spring:url value="/CatalogoCarta/DelCarta" var="delCatalogCartaUrl"/>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover" id="tblCartas" width="90%">
                                                <thead>
                                                <tr>
                                                    <th  class="text-center">#</th>
                                                    <th  class="text-center">Creado</th>
                                                    <th  class="text-center">Carta</th>
                                                    <th  class="text-center">Activo</th>
                                                    <th  class="text-center">Editar</th>
                                                    <th  class="text-center">Opción</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${cartas}" var ="c">
                                                    <spring:url value="/cartas/HabYDesCarta/bloq/{idcarta}" var="DeshabCartaUrl">
                                                        <spring:param name="idcarta" value="${c.idcarta}" />
                                                    </spring:url>
                                                    <spring:url value="/cartas/HabYDesCarta/Unbloq/{idcarta}" var="enableCartaUrl">
                                                        <spring:param name="idcarta" value="${c.idcarta}" />
                                                    </spring:url>
                                                    <tr>
                                                        <td>${c.idcarta}</td>
                                                        <td class="text-center"><fmt:formatDate timeStyle = "medium" value="${c.recordDate}" pattern="dd/MM/yyyy hh:mm"/></td>
                                                        <td>${c.carta}</td>
                                                        <c:choose>
                                                            <c:when test="${c.activo eq 'true'}">
                                                                <td class="text-center"> <span class="badge badge-success"><spring:message code="SI" /></span></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td class="text-center"> <span class="badge badge-danger"><spring:message code="NO" /></span></td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td class="text-center">
                                                            <button class="btn btn-warning btn-md btnEdit" id="btnEdit" data-id="${c.idcarta}">
                                                                <i class="fa fa-edit"></i></button>
                                                        </td>
                                                        <td class="text-center">
                                                            <c:choose>
                                                                <c:when test="${c.activo eq 'true'}">
                                                                    <a data-toggle="modal" data-id= "${fn:escapeXml(DeshabCartaUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-lock"></i></a>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a data-toggle="modal" data-id="${fn:escapeXml(enableCartaUrl)}" class="btn btn-outline-primary btn-sm Activar"><i class="fa fa-unlock"></i></a>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
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
                <!-- fin  -->

            </div>
        </div>

        <div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
                        <div id="titulo"></div>
                    </div>
                    <div class="modal-body">
                        <input type=hidden  class="form-control" id="accionUrl"/>
                        <div id="cuerpo"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                        <button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
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
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>


<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>


<script type="text/javascript">
    $(document).ready(function(){
        setTimeout(function () {
            $('#page-loader').fadeOut('slow');
        }, 1100);
        $('#smartwizard').smartWizard({
            selected: 0,
            theme: 'arrows',
            lang: {  // Language variables
                next: 'Sig',
                previous: 'Prev'
            }
        });

        var parametro ={
            GetCartaByIDUrl:"${GetCartaByIDUrl}",
            saveCatalogCartaUrl:"${saveCatalogCartaUrl}",
            delCatalogCartaUrl:"${delCatalogCartaUrl}"
        };

        $('#tblCartas tbody').on('click', '.Activar', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col2 = currentRow.find("td:eq(2)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Habilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-warning">'+ col2 + '</h3>');
            $('#basic').modal('show');
        });

        $('#tblCartas tbody').on('click', '.desact', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col2 = currentRow.find("td:eq(2)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Deshabilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-danger">'+ col2 + '</h3>');
            $('#basic').modal('show');
        });

        $("#frmCarta").validate({
            rules:{
                NameCarta: {required: true}
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
                Save(parametro);
            }
        });

        function Save(urls){
            var form1 = $("#frmCarta");
            $.post(urls.saveCatalogCartaUrl, form1.serialize(), function(data){
                if (data.msj != null) {
                    swal("Error!", data.msj, "error");
                }else{
                    swal("Éxito!", "Información guardada!", "success");
                    window.setTimeout(function(){
                        window.location.reload();
                    }, 1500);
                    ClsInput();
                }
            }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                swal("Error al guardar la información.","error");
                ClsInput();
            });
        };
        function ClsInput(){
            $("#NameCarta").val("");
            $("#idcarta").val("");
        }

        $("#tblCartas").DataTable({
            responsive: true
        });
        $("#tblCartas tbody").on("click", ".btnEdit",function(){
            debugger;
            var id = $(this).data('id');
            GetCartaId(id);
        });
        $("#tblCartas tbody").on("click", ".btnDelete",function(){
            var id = $(this).data('id');
            DeleteById(id);
        });
        function DeleteById(id) {
            if(confirm("Desea eliminar")){
                $.ajax({
                    url: parametro.delCatalogCartaUrl+"/?idcarta="+id,
                    method: 'POST',
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    sync: true,
                    processData: false,
                    cache: false,
                    success: function(result) {
                        swal("Eliminado!", "Registro eliminado.", "success");
                        window.setTimeout(function(){
                            window.location.reload();
                        }, 1500);
                        ClsInput();
                    },
                    error: function(request,msg,error) {
                        swal("Error", "Intenta de nuevo.","error");
                    }
                });
            }

        }

        function GetCartaId(cod){
            $.getJSON(parametro.GetCartaByIDUrl,{ idcarta : cod, ajax : 'true'  }, function(data){
                if (data.result == null){
                    console.log(data);
                    $("#idcarta").val(data.idcarta);
                    $("#NameCarta").val(data.carta);
                    $("#btnSave").text("Actualizar");
                }else{
                    swal("Error", "Intenta de nuevo.","error");
                }
            });
        }
    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>
