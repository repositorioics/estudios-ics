<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 09/05/2020
  Time: 16:28
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
                <a href="<spring:url value="/CatalogoVersion/ListadoVersion" htmlEscape="true "/>">
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
                                        <i class="fa fa-users"></i> <spring:message code="Listado de Versión" />
                                    </h5>
                                </div>
                                <c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
                                <c:set var="userDisabledLabel"><spring:message code="login.userDisabled" /></c:set>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="Nueva Versión"
                                               href="<spring:url value="/CatalogoVersion/CrearNuevaVersion" htmlEscape="true "/>">
                                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                                <spring:message code="Nueva Version" />
                                            </a>
                                        </div>
                                    </div>
                                    <br/>
                                    <hr/>
                                    <div class="table-responsive">
                                        <table id="tableVersion" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                            <thead>
                                            <tr>
                                                <th data-class="expand" class="text-center"><spring:message code="Código" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Creado"/></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Carta" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Versión" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Estado" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Editar" /></th>
                                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Opción" /></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${version}" var="v">
                                                <tr>
                                                    <spring:url value="/CatalogoVersion/editVersion/{idversion}" var="editVersionUrl">
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
                                                    <td>${v.carta.carta} </td>
                                                    <td class="text-center">${v.version}</td>
                                                    <c:choose>
                                                        <c:when test="${v.activo eq 'true'}">
                                                            <td class="text-center"> <span class="badge badge-success"><spring:message code="SI" /></span></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-center"> <span class="badge badge-danger"><spring:message code="NO" /></span></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td class="text-center">
                                                        <a href="${fn:escapeXml(editVersionUrl)}" data-toggle="tooltip" data-placement="bottom" title="Editar"
                                                           class="btn btn-outline-primary btn-sm">
                                                            <i class="fa fa-edit"></i>
                                                        </a>
                                                    </td>
                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${v.activo eq 'true'}">
                                                                <a data-toggle="modal" data-id= "${fn:escapeXml(delVersionUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-lock"></i></a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a data-toggle="modal" data-id="${fn:escapeXml(enableVersionUrl)}" class="btn btn-outline-primary btn-sm Activar"><i class="fa fa-unlock"></i></a>
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
        $("#tableVersion").DataTable();
        if ("${usuarioHabilitado}"){
            toastr.success("${userEnabledLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioDeshabilitado}"){
            toastr.error("${userDisabledLabel}", "${nombreUsuario}" );
        }

        $('#tableVersion tbody').on('click', '.Activar', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col3 = currentRow.find("td:eq(3)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Habilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-warning">'+ col3 + '</h3>');
            $('#basic').modal('show');
        });

        $('#tableVersion tbody').on('click', '.desact', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col3 = currentRow.find("td:eq(3)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Deshabilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-danger">'+ col3 + '</h3>');
            $('#basic').modal('show');
        });
    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>
