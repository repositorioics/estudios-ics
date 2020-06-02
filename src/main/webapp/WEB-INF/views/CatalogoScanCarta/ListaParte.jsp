<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 10/05/2020
  Time: 17:08
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

    <spring:url value="/resources/css/dtresponsive/twitter-bootstrap.css" var="boot1" />
    <link href="${boot1}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
    <link rel="stylesheet" href="${bdt}" type="text/css"/>

    <spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
    <link rel="stylesheet" href="${bdat4}" type="text/css"/>

    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

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
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <!-- Main content -->
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/CatalogoParte/ListadoParte" htmlEscape="true "/>">
                    <spring:message code="Listado" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div id="page-loader">
                    <span class="preloader-interior"></span>
                </div>
                <div class="container">
                    <div class="col-sm-12 col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <h5 style="font-family: Roboto">
                                    <i class="fa fa-users"></i> <spring:message code="Listado de Partes" />
                                </h5>
                            </div>
                            <div class="card-body">
                                <c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
                                <c:set var="userDisabledLabel"><spring:message code="login.userDisabled" /></c:set>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="Asignar Parte"
                                           href="<spring:url value="/CatalogoParte/CrearNuevaParte" htmlEscape="true "/>">
                                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                            <spring:message code="Asignar Parte" />
                                        </a>
                                    </div>
                                </div>
                                <br/>
                                <hr/>
                                <div class="table-responsive">
                                    <table id="tableParte" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                        <thead>
                                        <tr>
                                            <th data-class="expand" class="text-center"><spring:message code="Código" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Creado"/></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Carta"/></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Versión" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Parte" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Activo" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Editar" /></th>
                                            <th data-hide="phone,tablet" class="text-center"><spring:message code="Opcion" /></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${parte}" var="p">
                                            <tr>
                                                <spring:url value="/CatalogoParte/editParte/{idparte}" var="editParteUrl">
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
                                                <td>${p.version.carta.carta} </td>
                                                <td>${p.version.version} </td>
                                                <td class="text-center">${p.parte}</td>
                                                <c:choose>
                                                    <c:when test="${p.activo eq 'true'}">
                                                        <td class="text-center"> <span class="badge badge-success"><spring:message code="SI" /></span></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="text-center"> <span class="badge badge-danger"><spring:message code="NO" /></span></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td class="text-center">
                                                    <a href="${fn:escapeXml(editParteUrl)}" data-toggle="tooltip" data-placement="bottom" title="Editar"
                                                       class="btn btn-outline-primary btn-sm">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                </td>
                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${p.activo eq 'true'}">
                                                            <a data-toggle="modal" data-id= "${fn:escapeXml(delParteUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-lock"></i></a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a data-toggle="modal" data-id="${fn:escapeXml(enableParteUrl)}" class="btn btn-outline-primary btn-sm Activar"><i class="fa fa-unlock"></i></a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="card-footer text-muted"></div>
                        </div>
                    </div>
                </div>
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
    <c:set var="confirmar"><spring:message code="confirm" /></c:set>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>
<script type="text/javascript">
    $(document).ready(function(){
        setTimeout(function () {
            $('#page-loader').fadeOut('slow');
        }, 1200);
        $("#tableParte").DataTable();

        if ("${usuarioHabilitado}"){
            toastr.success("${userEnabledLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioDeshabilitado}"){
            toastr.error("${userDisabledLabel}", "${nombreUsuario}" );
        }

        $('#tableParte tbody').on('click', '.Activar', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col4 = currentRow.find("td:eq(4)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Habilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-warning">'+ col4 + '</h3>');
            $('#basic').modal('show');
        });
        $('#tableParte tbody').on('click', '.desact', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col4 = currentRow.find("td:eq(4)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Deshabilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-danger">'+ col4 + '</h3>');
            $('#basic').modal('show');
        });
    })
    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>
