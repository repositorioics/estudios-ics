<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 29/04/2020
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp"/>
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>

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
            border-bottom: 5px solid #f7f7f7;
        }

        .no-wrap td, .no-wrap th {
            white-space: nowrap;
        }

        .table td, .table th {
            padding: .9375rem .4rem;
            vertical-align: top;
            border-top: 1px solid rgba(120, 130, 140, .13);
        }

        .font-light {
            font-weight: 300;
        }

        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: #008cba;
            background-color: #008cba;
        }

        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #008cba;
        }

        /**/

        /*ini*/
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
        /*fin*/
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #008cba;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #008cba;
        }
    .form-control-feedback {
        margin-top: 0.25rem;
        width: 95%;
        text-align: left;
    }
    </style>
    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

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
        <a href="<spring:url value="/CatalogoVersion/CrearNuevaVersion/" htmlEscape="true "/>">
            <spring:message code="Asignar Versión a la Carta"/>
        </a>
    </li>
</ol>
<div class="container-fluid">
<div class="animated fadeIn">
<!--<div id="page-loader">
    <span class="preloader-interior"></span>
</div> -->

<div class="">
<div class="row">
<div class="col-md-12 col-lg-12">
<div class="card">
<div class="card-body bg-primary text-white mailbox-widget pb-0">
    <h2 class="text-white pb-3" style="font-family: Roboto, sans-serif"><spring:message code="Versión"/></h2>
    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab"
               aria-selected="true">
                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                <span class="d-none d-md-block">  <spring:message code="Formulario"/></span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab"
               aria-selected="false">
                <span class="d-block d-md-none"><i class="ti-export"></i></span>
                <span class="d-none d-md-block">  <spring:message code="Listado"/> </span>
            </a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
<div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
    <!-- inicio div -->
<div>
    <div class="row">

        <div class="col-md-12">
            <spring:url value="/CatalogoVersion/saveVersion" var="saveVersionUrl"/>
            <spring:url value="/CatalogoVersion/CrearNuevaVersion" var="ListadoVersionUrl"/>
            <spring:url value="/CatalogoVersion/delete" var="deleteUrl"/>
            <spring:url value="/CatalogoVersion/activar" var="activarUrl"/>
            <spring:url value="/CatalogoVersion/saveParte" var="saveParteUrl"/>
            <spring:url value="/CatalogoVersion/saveExt" var="saveExtUrl"/>
            <c:set var="successMessage"><spring:message code="process.success" /></c:set>
            <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
            <div class="container col-md-12 col-xl-12">
                <form autocomplete="off" id="frmVersion" name="frmVersion" class="form-horizontal">
                    <div class="container">
                        <div class="row" hidden="hidden">
                            <div  class="form-group col-md-6">
                                <label for="idversion">Versión:</label>
                                <input type="text" class="form-control" id="idversion" name="idversion" value="${caso.idversion}">
                            </div>
                            <div  class="form-group col-md-6">
                                <label for="idversion">editando:</label>
                                <input type="text" class="form-control" id="editando" name="editando" value="${editando}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="idcarta" class="col-sm-2 col-form-label text-right"><spring:message code="Estudio" /></label>
                            <div class="col-sm-10">
                                <select class="form-control" id="idcarta" name="idcarta" required="required">
                                    <option selected value=""><spring:message code="select" />...</option>
                                    <c:forEach items="${cartas}" var="e">
                                        <c:choose>
                                            <c:when test="${caso.estudio.codigo eq e.codigo}">
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
                            <label for="fecha_version" class="col-sm-2 col-form-label text-right"><spring:message code="Fecha" /> <spring:message code="Versión" /></label>
                            <div class="col-sm-10">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupPrepend"><i class="fa fa-calendar-o text-info"></i></span>
                                    </div>
                                    <input name="fecha_version" id="fecha_version" class="form-control date-picker years" type="text" data-date-end-date="+0d"
                                           value="${caso.fecha_version}" required="required" />
                                </div>
                            </div>
                        </div>
                        <div hidden="hidden" class="form-group row">
                            <label for="fecha_version" class="col-sm-2 col-form-label text-right"><spring:message code="Fecha" /> <spring:message code="Versión" /></label>
                            <div class="col-sm-10">
                                <input name="fecha_format" id="fecha_format" class="form-control" value="${caso.fecha_format}" />
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="version" class="col-sm-2 col-form-label text-right"><spring:message code="Nombre" /> <spring:message code="Versión" /></label>
                            <div class="col-sm-10">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupPrepend1"><i class="fa fa-vimeo text-info"></i></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Nombre de la versión" id="version" name="version" required="required" value="${caso.version}" >
                                </div>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="activo" class="col-sm-2 col-form-label text-right"></label>
                            <div class="col-sm-8">
                                <div class="form-check">
                                    <c:choose>
                                        <c:when test="${caso.activo eq true }">
                                            <input type="checkbox" class="form-check-input" checked="checked" id="activo" name="activo">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="form-check-input" id="activo" name="activo">
                                        </c:otherwise>
                                    </c:choose>
                                    <label class="form-check-label" for="activo">
                                        <spring:message code="Activar?" />
                                    </label>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <div class="d-flex justify-content-between">
                                    <div class="p-2 bd-highlight">
                                        <button type="submit" id="btnSave" class="btn btn-info btn-lg">
                                            <i class="fa fa-save"></i> <spring:message code="save" /></button>
                                    </div>
                                    <div class="p-2 bd-highlight"></div>
                                    <div class="p-2 bd-highlight">
                                        <a href="${fn:escapeXml(ListadoVersionUrl)}" class="btn btn-warning btn-lg btn-ladda" data-style="expand-right">
                                            <i class="fa fa-minus-circle" aria-hidden="true">  </i><spring:message code="cancel" /></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                    </div>
                </form>
            </div>
        </div>

    </div>

</div>
    <!-- fin div -->
</div>


<div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
    <div class="row p-3 text-dark">
        <div class="col-md-6">
        </div>
        <div class="col-md-6 text-right">
        </div>
        <div class="table-responsive">
            <table id="tableVersion" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                <thead>
                <tr>
                    <th data-class="expand" class="text-center"><spring:message code="Código" /></th>
                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Creado"/></th>
                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Carta" /></th>
                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Versión" /></th>
                    <th data-hide="phone,tablet" class="text-center"><spring:message code="Activo" /></th>
                    <th data-hide="phone,tablet" class="text-center"><spring:message code="actions" /></th>
                    <sec:authorize access="hasRole('ROLE_WEB')">
                        <th data-hide="phone,tablet" class="text-center"><spring:message code="Opcion" /></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <!--<c:forEach items="${myDto}" var="dto">
                    <tr>
                        <spring:url value="/CatalogoVersion/editVersion/{idversion}" var="editVersionUrl">
                            <spring:param name="idversion" value="${dto.idversion}" />
                        </spring:url> <spring:url value="/CatalogoVersion/addParte/{idversion}" var="addParteUrl">
                        <spring:param name="idversion" value="${dto.idversion}" />
                    </spring:url>
                        <spring:url value="/CatalogoVersion/DeleteVersion/bloq/{idversion}" var="delVersionUrl">
                            <spring:param name="idversion" value="${dto.idversion}" />
                        </spring:url>
                        <spring:url value="/CatalogoVersion/DeleteVersion/Unbloq/{idversion}" var="enableVersionUrl">
                            <spring:param name="idversion" value="${dto.idversion}" />
                        </spring:url>
                        <td class="text-center">${dto.idversion}</td>
                        <td class="text-center"><fmt:formatDate timeStyle = "medium" value="${dto.fecha_version}" pattern="MM/yyyy hh:mm"/></td>
                        <td>${dto.estudio} </td>
                        <td class="text-center">${dto.version}</td>
                        <td class="text-center"> ${dto.partes} </td>
                        <c:choose>
                            <c:when test="${dto.activo eq true}">
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

                            <button data-toggle="tooltip" data-placement="bottom" title="Agregar Parte"  data-id="${dto.idversion}" class="btn btn-success btn-sm btnAddParte">
                                <i class="fa fa-plus"></i>
                            </button>

                            <button data-toggle="tooltip" data-placement="bottom" title="Agregar Extensión"  data-id="${dto.idversion}" class="btn btn-info btn-sm btnAddExt">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </button>
                        </td>
                        <sec:authorize access="hasRole('ROLE_WEB')">
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${dto.activo eq true}">
                                        <button  class="btn btn-primary btn-sm desact"><i class="fa fa-unlock"></i></button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-primary btn-sm activar"><i class="fa fa-lock"></i></button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>-->

                <c:forEach items="${version}" var="v">
                    <tr>
                        <spring:url value="/CatalogoVersion/editVersion/{idversion}" var="editVersionUrl">
                            <spring:param name="idversion" value="${v.idversion}" />
                        </spring:url> <spring:url value="/CatalogoVersion/addParte/{idversion}" var="addParteUrl">
                            <spring:param name="idversion" value="${v.idversion}" />
                        </spring:url>
                        <spring:url value="/CatalogoVersion/DeleteVersion/bloq/{idversion}" var="delVersionUrl">
                            <spring:param name="idversion" value="${v.idversion}" />
                        </spring:url>
                        <spring:url value="/CatalogoVersion/DeleteVersion/Unbloq/{idversion}" var="enableVersionUrl">
                            <spring:param name="idversion" value="${v.idversion}" />
                        </spring:url>
                        <td class="text-center">${v.idversion}</td>
                        <td class="text-center"><fmt:formatDate timeStyle = "medium" value="${v.recordDate}" pattern="dd/MM/yyyy hh:mm"/></td>
                        <td>${v.estudio.nombre} </td>
                        <td class="text-center">${v.version}</td>
                        <c:choose>
                            <c:when test="${v.activo eq true}">
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

                            <button data-toggle="tooltip" data-placement="bottom" title="Agregar Parte"  data-id="${v.idversion}" class="btn btn-success btn-sm btnAddParte">
                                <i class="fa fa-plus"></i>
                            </button>

                            <button data-toggle="tooltip" data-placement="bottom" title="Agregar Extensión"  data-id="${v.idversion}" class="btn btn-info btn-sm btnAddExt">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </button>
                        </td>
                        <sec:authorize access="hasRole('ROLE_WEB')">
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${v.activo eq true}">
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
<!-- /.conainer-fluid -->
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"></h5>   <span> -> </span>   <h5 class="modal-title" id="exampleModalLabel2"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form name="formParteModal" id="formParteModal" autocomplete="off">
                    <div class="form-row">
                        <div hidden="hidden" class="form-group col-md-6">
                            <label for="version2"><spring:message code="version2"/> </label>
                            <input type="text" name="version2" class="form-control" id="version2">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="fecha"><spring:message code="fecha"/> </label>
                            <input type="text" class="form-control date-picker years" required="required" name="fecha" id="fecha" data-date-end-date="+0d"/>
                            <span class="error">Fecha es requerida.</span>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="parte"><spring:message code="Parte"/> </label>
                            <input type="text" class="form-control" id="parte" name="parte" placeholder="Parte Principal">
                            <span class="error">Parte principal es requerido.</span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="close"/> </button>
                <button type="button" class="btn btn-primary" id="btnSaveParte"> <i class="fa fa-save"></i> <spring:message code="save"/> </button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Extension -->
<div class="modal fade" id="exampleModalExt" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel3"> </h5>  <h5><span> -> </span></h5> <h5 class="modal-title" id="exampleModalLabel4"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form name="formExtModal" id="formExtModal" autocomplete="off">
                    <div class="form-row">
                        <div class="form-group col-md-6" hidden="hidden">
                            <label for="version3"><spring:message code="version3"/> </label>
                            <input type="text" name="version3" class="form-control" id="version3">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="fechaExt"><spring:message code="fecha"/> <spring:message code="Extensión"/> </label>
                            <input type="text" class="form-control date-picker" required="required" name="fechaExt" id="fechaExt" data-date-end-date="+0d"/>
                            <span class="error">Fecha es requerida.</span>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="extension"><spring:message code="Extensión"/>  </label>
                            <input type="text" class="form-control" id="extension" name="extension" placeholder="Extensión">
                            <span class="error">Extensión es requerido.</span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="close"/> </button>
                <button type="button" class="btn btn-primary" id="btnSaveExt"> <i class="fa fa-save"></i> <spring:message code="save"/> </button>
            </div>
        </div>
    </div>
</div>
</div>

<jsp:include page="../fragments/bodyFooter.jsp"/>
<jsp:include page="../fragments/corePlugins.jsp"/>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs"/>
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs"/>
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/app.js" var="App"/>
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>

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
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${jQValidationLoc}"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs"/>
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sweet"/>
<script type="text/javascript" src="${sweet}"></script>


<script type="text/javascript">
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
    $(document).ready(function () {
       /* setTimeout(function () {
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
        $("#idcarta").select2();

        var parameters = {
            saveVersionUrl: "${saveVersionUrl}",
            ListadoVersionUrl: "${ListadoVersionUrl}",
            dataTablesLang:"${dataTablesLang}",
            deleteUrl:"${deleteUrl}",
            activarUrl:"${activarUrl}",
            successmessage: "${successMessage}",
            error: "${errorProcess}",
            saveParteUrl:"${saveParteUrl}",
            saveExtUrl:"${saveExtUrl}"
        };
        handleDatePickers("${lenguaje}");
        var table = $("#tableVersion").DataTable({
            "columnDefs": [{
                "targets": [0],
                "visible": false,
                "searchable": false
            }],
            "oLanguage": {
                "sUrl": parameters.dataTablesLang
            }
        });

        var formV = $("#frmVersion");
        formV.validate({
            rules: {
                idcarta: {required: true},
                version: {required: true},
                fecha_version: {required: true}
            },errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    //error.insertAfter( element ); //cuando no es input-group
                    error.insertAfter(element.parent('.input-group'));
                }
            },highlight: function ( element, errorClass, validClass ) {
                $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
            },unhighlight: function (element, errorClass, validClass) {
                $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
            },submitHandler: function (form) {
                SaveVersion(parameters);
            }
        });
        function SaveVersion(dir) {
            $.post(dir.saveVersionUrl, formV.serialize(), function (data) {
                console.log(data);
                if (data.msj != null) {
                    swal("Advertencia!!", data.msj, "warning");
                } else {
                    toastr.success(dir.successmessage);
                    //swal("Éxito!", "Información guardada!", "success");
                    window.setTimeout(function () {
                        window.location.href = parameters.ListadoVersionUrl;
                    }, 1000);
                }
            }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Error!","Interno en el Servisor.","error");
            });
        }


        $("#tableVersion tbody").on("click", ".desact", function(){
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
                            $.post(parameters.deleteUrl, { idversion: column01, ajax: 'true' }).done(function (data) {
                                swal("Desactivado!", "con éxito!", "success");
                                window.setTimeout(function () {
                                    window.location.href = parameters.ListadoVersionUrl;
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
        $("#tableVersion tbody").on("click", ".activar", function(){
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
                    $.post(parameters.activarUrl, { idversion: column01, ajax: 'true' }).done(function (data) {
                        swal("Registro!", "activo!", "success");
                        window.setTimeout(function () {
                            window.location.href = parameters.ListadoVersionUrl;
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

        $("#tableVersion tbody").on("click", ".btnAddParte", function(){
            $("#version2").val('');
            var currentRow = $(this).closest("tr");
            var column0 = table.row(currentRow).data()[0];
            var column01 = table.row(currentRow).data()[2];
            var column02 = table.row(currentRow).data()[3];

            $("#version2").val(column0);
            $("#exampleModalLabel").text(column01);
            $("#exampleModalLabel2").text(column02);


            $('#exampleModal').modal('show');
        });

        $("#btnSaveParte").on("click", function(){
            var isValidItem = true;
            if ($('#fecha').val().trim() == "" || $("#fecha").val().trim() == null) {
                $('#fecha').siblings('span.error').css('visibility', 'visible');
                $('#fecha').parents('.form-group').addClass('has-danger');
                isAllValid = false;
                return;
            }
            else {
                $('#fecha').siblings('span.error').css('visibility', 'hidden');
                $("#fecha").parents('.form-group').removeClass('has-danger');
            }

            if ($('#parte').val().trim() == "" || $("#parte").val().trim() == null) {
                $('#parte').siblings('span.error').css('visibility', 'visible');
                $('#parte').parents('.form-group').addClass('has-danger');
                isAllValid = false;
                return;
            }
            else {
                $('#parte').siblings('span.error').css('visibility', 'hidden');
                $("#parte").parents('.form-group').removeClass('has-danger');
            }

            if(isValidItem){
                GuardarParteModal(parameters);
            }
        });
        var formParteModal = $("#formParteModal");
        function GuardarParteModal(dir){
            $.post(dir.saveParteUrl, formParteModal.serialize(), function (data) {
                if (data.msj != null) {
                    swal("Advertencia!!", data.msj, "warning");
                } else {
                    toastr.success(dir.successmessage);
                    //swal("Éxito!", "Información guardada!", "success");
                    window.setTimeout(function () {
                        window.location.href = parameters.ListadoVersionUrl;
                    }, 1000);
                }
            }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Error!","Interno en el Servisor.","error");
            });
        }

        var formExtModal = $("#formExtModal");
        $("#tableVersion tbody").on("click", ".btnAddExt", function(){
            debugger;
            $("#version3").val('');
            var currentRow = $(this).closest("tr");
            var column0 = table.row(currentRow).data()[0];
            var column01 = table.row(currentRow).data()[2];
            var column02 = table.row(currentRow).data()[3];

            $("#version3").val(column0);
            $("#exampleModalLabel3").text(column01);
            $("#exampleModalLabel4").text(column02);


            $('#exampleModalExt').modal('show');


        });

        $("#btnSaveExt").on("click", function(){
            var isValidItem = true;
            if ($('#fechaExt').val().trim() == "" || $("#fechaExt").val().trim() == null) {
                $('#fechaExt').siblings('span.error').css('visibility', 'visible');
                $('#fechaExt').parents('.form-group').addClass('has-danger');
                isAllValid = false;
                return;
            }
            else {
                $('#fechaExt').siblings('span.error').css('visibility', 'hidden');
                $("#fechaExt").parents('.form-group').removeClass('has-danger');
            }

            if ($('#extension').val().trim() == "" || $("#extension").val().trim() == null) {
                $('#extension').siblings('span.error').css('visibility', 'visible');
                $('#extension').parents('.form-group').addClass('has-danger');
                isAllValid = false;
                return;
            }
            else {
                $('#extension').siblings('span.error').css('visibility', 'hidden');
                $("#extension").parents('.form-group').removeClass('has-danger');
            }
            if(isValidItem){
                saveExtensionModal(parameters);
            }
        });

        function saveExtensionModal(dir){
            $.post(dir.saveExtUrl, formExtModal.serialize(), function (data) {
                if (data.msj != null) {
                    swal("Advertencia!!", data.msj, "warning");
                } else {
                    toastr.success(dir.successmessage);
                    window.setTimeout(function () {
                        window.location.href = parameters.ListadoVersionUrl;
                    }, 1000);
                }
            }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                swal("Error!","Interno en el Servisor.","error");
            });


        }
    })
</script>
</body>
</html>
