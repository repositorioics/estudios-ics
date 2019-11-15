<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />

    <spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
    <link rel="stylesheet" href="${bdt}" type="text/css"/>

    <spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
    <link rel="stylesheet" href="${bdat4}" type="text/css"/>

    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>


    <style>
        input[type="text"]:disabled {
            background: #ffffff;
            font-family: Roboto;
            font-weight: bold;
        }
        [disabled] { /* Text and background colour, medium red on light yellow */
            color:#000000;
            background-color:#ffffff;
            font-weight: bold;
            font-family: Roboto;
            font-size: 1em;
        }
        div.dataTables_filter input{
            border: 1px solid gainsboro;
            border-radius: 5px;
        }
    </style>
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="">
    <jsp:include page="../fragments/sideBar.jsp" />
    <!-- Main content -->
    <div class="main">
    <br/>
    <br/>
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/hemo/listado2/" htmlEscape="true "/>">LISTADO</a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(editDetailsUrl)}">LISTA DE DETALLES </a>
                <i class="fa fa-angle-right"></i>
                ${nombre}
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="row">

                    <div class="col-sm-12 col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <h5 style="font-family: Roboto">
                                    <i class="fa fa-info" aria-hidden="true"></i> Información Participante
                                </h5>
                            </div>
                            <spring:url value="/hemo/adddetails/{idDatoHemo}" var="addDetailsUrl">
                                <spring:param name="idDatoHemo" value="${idDatoHemo}" />
                            </spring:url>
                            <spring:url value="/hemo/ViewResutl" var="searchResultUrl">
                                <spring:param name="idHemoDetalle" value="${idHemoDetalle}" />
                            </spring:url>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label for="codigo" class="col-form-label">Código :</label>
                                            <input type="text" class="form-control" id="codigo" value="${codigo}" disabled>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label for="nombre" class="col-form-label">Participante :</label>
                                            <input type="text" class="form-control" id="nombre" value="${nombre}" disabled>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-sm-4">
                                        <label for="edad" class="col-form-label">Edad :</label>
                                        <input type="text" class="form-control" id="edad" value="${edad}" disabled>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="expediente" class="col-form-label">N° Expediente :</label>
                                        <input type="text" class="form-control" id="expediente" value="${expediente}" disabled="disabled">
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="fechanac" class="col-form-label">F. Nacimiento :</label>
                                            <input type="text" class="form-control" id="fechanac" value="<fmt:formatDate value="${fechanac}" pattern="dd/MM/yyyy"/>" disabled="disabled">
                                        </div>
                                    </div>

                                    <div hidden="hidden" class="col-sm-12">
                                        <div class="form-group">
                                            <label for="idDatoHemo" class="col-form-label">idDatoHemo :</label>
                                            <input type="text" class="form-control" id="idDatoHemo" value="${idDatoHemo}" name="idDatoHemo" readonly>
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div class="card-footer">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <a class="btn btn-info btn-md btn-lg btn-block" data-toggle="tooltip" data-placement="bottom"
                                           title="Agregar Detalles" href="${fn:escapeXml(addDetailsUrl)}"
                                           htmlEscape="true "/>
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                        <spring:message code="Agregar Detalles" />
                                        </a>
                                    </div>
                                    <div class="col-sm-4"></div>
                                    <div class="col-sm-4">
                                        <a class="btn btn-warning btn-md btn-lg btn-block" data-toggle="tooltip" data-placement="bottom" title="Ir al Listado"
                                           href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
                                            <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                            <spring:message code="Cancelar" /></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <h5 style="font-family: Roboto">
                                    Detalles Hemodinámicos
                                </h5>
                                <strong> <i class="fa fa-align-justify"></i> -  </strong>
                                <small></small>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                <table class="table table-striped table-bordered dt-responsive nowrap" style="width:100%" id="tblHistorial">
                                    <thead>
                                    <tr>
                                        <th class="text-center hide">Id</th>
                                        <th class="text-center">F. Registro</th>
                                        <th class="text-center">Hora</th>
                                        <th class="text-center">Clasificación</th>
                                        <th class="text-center">N. Consciencia</th>
                                        <th class="text-center">Extremidades</th>
                                        <th class="text-center">Pulso</th>
                                        <th class="text-center">llenado Capilar</th>
                                        <th class="text-center">Editar</th>
                                        <th class="text-center">Ver</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${hemo}" var="h">
                                        <spring:url value="/hemo/listDetailsHemo/{idHemoDetalle}" var="volverUrl">
                                            <spring:param name="idHemoDetalle" value="${h.idHemoDetalle}" />
                                        </spring:url>
                                        <spring:url value="/hemo/editdetails/{idHemoDetalle}" var="editDetailsUrl">
                                            <spring:param name="idHemoDetalle" value="${h.idHemoDetalle}" />
                                        </spring:url>
                                        <spring:url value="/hemo/ViewResutl/" var="searchResultUrl">
                                        </spring:url>
                                        <tr>
                                            <td class="hide">${h.idHemoDetalle}</td>
                                            <td><fmt:formatDate value="${h.fecha}" pattern="dd/MM/yyyy"/></td>
                                            <td>${h.hora}</td>
                                            <td class="text-center">${h.signo}</td>
                                            <c:forEach items="${nivel}" var="n">
                                                <c:if test="${n.catKey eq h.nivelConciencia}">
                                                    <td class="text-center">${n.spanish}</td>
                                                </c:if>
                                            </c:forEach>

                                            <c:forEach items="${extremidades}" var="ext">
                                                <c:if test="${ext.catKey eq h.extremidades}">
                                                    <td class="text-center">${ext.spanish}</td>
                                                </c:if>
                                            </c:forEach>
                                            <c:forEach items="${pulsoCalidad}" var="pulso">
                                                <c:if test="${pulso.catKey eq h.pulsoCalidad}">
                                                    <td class="text-center">${pulso.spanish}</td>
                                                </c:if>
                                            </c:forEach>
                                            <c:forEach items="${llenadoCapilar}" var="llenado">
                                                <c:if test="${llenado.catKey eq h.llenadoCapilar}">
                                                    <td class="text-center">${llenado.spanish}</td>
                                                </c:if>
                                            </c:forEach>

                                            <td class="text-center">
                                                <a href="${fn:escapeXml(editDetailsUrl)}"
                                                   class="btn btn-outline-warning btn-sm">
                                                    <span style="color: orange;">
                                                       <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                     </span>
                                                </a>
                                            </td>
                                            <td class="text-center">
                                                <a class="btn btn-outline-dark btn-sm btnView" data-id="${h.idHemoDetalle}">
                                                <i class="fa fa-eye" aria-hidden="true"></i>
                                            </a>
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
        <hr/>
        <!-- Modal -->
        <div id="exampleModal" class="modal fade"  tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Sintomas</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <ul class="list-group">
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold"> Presión Arterial:</label>
                                <span class="badge badge-primary badge-pill" id="pa" style="font-family: Roboto; font-size: 1em; font-weight: bold"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label  style="font-family: Roboto; font-size: 1em; font-weight: bold">Presión Arterial Promedio:</label>
                                <span class="badge badge-primary badge-pill" id="pp" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold"> Presión Arterial Media:</label>
                                <span class="badge badge-primary badge-pill" id="pam" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Rango Frecuencia Cardíaca:</label>
                                <span class="badge badge-primary badge-pill" id="fcardi" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Rango Frecuencia Respiratoria: </label>
                                <span class="badge badge-primary badge-pill" id="fr" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">T°C:</label>
                                <span class="badge badge-primary badge-pill" id="tc" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">SA02%:</label>
                                <span class="badge badge-primary badge-pill" id="sa" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Diuresis/ml/Kg/Hr:</label>
                                <span class="badge badge-primary badge-pill" id="diuresis" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold"> Densidad Urinaria:</label>
                                <span class="badge badge-primary badge-pill" id="densidadU" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            <i class="fa fa-times-circle" aria-hidden="true"></i>
                             Cerrar</button>
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

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>

<script>
    var table;

    $(document).ready(function(){
        table = $("#tblHistorial").dataTable({
            responsive: true,
            "language": {
                "sProcessing":     "Procesando...",
                "sLengthMenu":     "Mostrar _MENU_ registros",
                "sZeroRecords":    "No se encontraron resultados",
                "sEmptyTable":     "Ningún dato disponible en esta tabla",
                "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix":    "",
                "sSearch":         "Buscar:",
                "sUrl":            "",
                "sInfoThousands":  ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
            },"columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }
            ]
        });
        $('#tblHistorial tbody').on('click', '.btnView', function () {
            var id = $(this).data('id');
            ver(id);
        });
    });

    /*$(function(){
        table = $("#tblHistorial").dataTable({
            responsive: true,
            "language": {
                "sProcessing":     "Procesando...",
                "sLengthMenu":     "Mostrar _MENU_ registros",
                "sZeroRecords":    "No se encontraron resultados",
                "sEmptyTable":     "Ningún dato disponible en esta tabla",
                "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix":    "",
                "sSearch":         "Buscar:",
                "sUrl":            "",
                "sInfoThousands":  ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
            }
        });
        $('[data-toggle="tooltip"]').tooltip();



        $(".viewResponse").on("click", function(){
            console.log($(this).data('id'));
            $.getJSON("${searchResultUrl}", { idHemoDetalle : $(this).data('id'),   ajax : 'true'  }, function(data){
                $("#exampleModal").modal("show");
                console.log(data);
                $("#pa").text(data.pa);
                $("#pp").text(data.pp);
                $("#pam").text(data.pam);
                $("#fcardi").text(data.fcardi);
                $("#fr").text(data.fr);
                $("#tc").text(data.tc);
                $("#sa").text(data.sa);
                $("#diuresis").text(data.diuresis);
                $("#densidadU").text(data.densidadU);
            })
        });


    });*/

    function ver(id){
        $.getJSON("${searchResultUrl}", { idHemoDetalle : id,   ajax : 'true'  }, function(data){
            $("#exampleModal").modal("show");
            $("#pa").text(data.pa);
            $("#pp").text(data.pp);
            $("#pam").text(data.pam);
            $("#fcardi").text(data.fcardi);
            $("#fr").text(data.fr);
            $("#tc").text(data.tc);
            $("#sa").text(data.sa);
            $("#diuresis").text(data.diuresis);
            $("#densidadU").text(data.densidadU);
        })
    }
</script>
</body>
</html>
