<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 29/06/2021
  Time: 07:40
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
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>
    <style>
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
            border-bottom: 5px solid #ffffff;
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
            background-color: #008cba;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #008cba;
        }

        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 5px solid #fff
        }
        .form-control-feedback {
            margin-top: 0.25rem;
            width: 95%;
            text-align: left;
        }
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
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div class="">
            <div class="row">
            <div class="col-md-12 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-white pb-3"><spring:message code="Extension"/> <spring:message code="letters"/></h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block"> <spring:message code="Form" /></span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                            <span class="d-block d-md-none"><i class="ti-export"></i></span>
                            <span class="d-none d-md-block"><spring:message code="List"/></span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
            <div>
            <div class="row p-4 no-gutters align-items-center">
                <div class="col-sm-12 col-md-6">
                </div>
                <div class="col-sm-12 col-md-6">
                </div>
            </div>
                <form id="frmExtension" name="frmExtension" autocomplete="off">
                    <spring:url value="/CatalogoVersion/saveExtension" var="saveExtensionUrl"/>
                    <spring:url value="/CatalogoVersion/obtenerVersion" var="obtenerVersionUrl"/>
                    <spring:url value="/CatalogoVersion/CrearNuevaParte" var="CrearNuevaParteUrl"/>
                    <spring:url value="/CatalogoVersion/extension" var="extensionUrl"/>
                    <div hidden="hidden">
                        <input type="text" name="idextension" id="idextension" class="form-control"  value="${caso.id}"/>
                        <input type="text" name="editando" id="editando" class="form-control" value="${editando}"/>
                    </div>
                    <div class="form-group row">
                        <label for="idcarta" class="col-sm-2 col-form-label text-right"><spring:message code="Estudio" /></label>
                        <div class="col-sm-8">
                            <select class="form-control" id="idcarta" name="idcarta" required="required">
                                <option selected value=""><spring:message code="select" />...</option>
                                <c:forEach items="${cartas}" var="e">
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
                        <label for="fecha" class="col-sm-2 col-form-label text-right"><spring:message code="Fecha" /></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control date-picker years" required="required" value="${caso.fecha_extension}" name="fecha" id="fecha" data-date-end-date="+0d"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="extension" class="col-sm-2 col-form-label text-right"><spring:message code="Extensión" /></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="extension" name="extension" value="${caso.extension}" placeholder="Nombre de la Extensión">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                            <div class="d-flex justify-content-between">
                                <div class="p-2 bd-highlight">
                                    <button type="submit" id="btnSave" class="btn btn-primary btn-block btn-lg btn-ladda" data-style="expand-right">
                                        <i class="fa fa-save"></i> <spring:message code="save" /></button>
                                </div>
                                <div class="p-2 bd-highlight"></div>
                                <div class="p-2 bd-highlight">
                                    <a href="${fn:escapeXml(extensionUrl)}" class="btn btn-warning btn-lg btn-ladda" data-style="expand-right">
                                        <i class="fa fa-minus-circle" aria-hidden="true">  </i><spring:message code="cancel" /></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                </form>
            </div>
            </div>
            <div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
               <div>
                   <div class="table-responsive">
                       <table  id="tblExtension" class="table table-hover table-bordered">
                           <thead>
                           <tr>
                               <th data-class="expand" class="text-center"><spring:message code="Código" /></th>
                               <th data-class="expand" class="text-center"><spring:message code="Fecha" /></th>
                               <th data-class="expand" class="text-center"><spring:message code="Estudio" /></th>
                               <th data-hide="phone,tablet" class="text-center"><spring:message code="Versión"/></th>
                               <th data-hide="phone,tablet" class="text-center"><spring:message code="Extensión" /></th>
                               <th data-hide="phone,tablet" class="text-center"><spring:message code="Activo" /></th>
                               <th data-hide="phone,tablet" class="text-center"><spring:message code="actions" /></th>
                           </tr>
                           </thead>
                           <tbody>
                            <c:forEach items="${extension}" var="e">
                                <spring:url value="/CatalogoVersion/editExtension/{idextension}"
                                            var="editUrl">
                                    <spring:param name="idextension" value="${e.id}"/>
                                </spring:url>
                                <tr>
                                   <td class="text-center">${e.id}</td>
                                   <td class="text-center">${e.fecha_extension}</td>
                                   <td class="text-center">${e.version.estudio.nombre}</td>
                                   <td class="text-center">${e.version.version}</td>
                                   <td class="text-center">${e.extension}</td>
                                   <c:choose>
                                       <c:when test="${e.active eq true}">
                                           <td class="text-center"> <span class="badge badge-success"><spring:message code="SI" /></span></td>
                                       </c:when>
                                       <c:otherwise>
                                           <td class="text-center"> <span class="badge badge-danger"><spring:message code="NO" /></span></td>
                                       </c:otherwise>
                                   </c:choose>
                                   <td class="text-center">
                                       <a title="<spring:message code="edit" />" href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm">
                                           <i class="fa fa-edit"></i></a>
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
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sweet"/>
<script type="text/javascript" src="${sweet}"></script>
<script>
    $(document).ready(function(){
        $("#idcarta").select2();
        $("#idversion").select2();
        $(".years").datepicker({
            changeMonth: true,
            changeYear: true,
            format: "mm-yyyy",
            startView: "months",
            minViewMode: "months",
            autoclose: true
        });
        var parametros = {
            saveExtensionUrl : "${saveExtensionUrl}",
            obtenerVersionUrl    : "${obtenerVersionUrl}",
            CrearNuevaParteUrl: "${CrearNuevaParteUrl}",
            extensionUrl:"${extensionUrl}"
        };

        var table = $("#tblExtension").DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });

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
            $.getJSON(parametros.obtenerVersionUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
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

        var formExt = $("#frmExtension");
        formExt.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules:{
                idcarta :{required: true},
                idversion :{required: true},
                parte :{required: true},
                extension :{required: true}
            },
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
                SaveExt(parametros);
            }
        });
        function SaveExt(dir){
            $.post(dir.saveExtensionUrl, formExt.serialize(), function(data){
                console.info(data);
                if (data.msj != null) {
                    swal("Error!", data.msj, "error");
                }else{
                    swal("Éxito!", "Información guardada!", "success");
                    window.setTimeout(function(){
                        window.location.href = parametros.extensionUrl;
                    }, 1500);
                }
            }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                swal("Error","Interno del Servidor.","error");
            });
        }
    })
</script>
</body>
</html>
