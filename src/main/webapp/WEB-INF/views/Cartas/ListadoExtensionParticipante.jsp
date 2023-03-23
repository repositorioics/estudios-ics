<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 20/10/2021
  Time: 10:27
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

    <style>
        #list_participante_extension_filter {
            float: left !important;
        }
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
                <a href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                    <spring:message code="List" />
                </a>
                <i class="fa fa-angle-right"></i>
                <spring:message code="List" /> <spring:message code="Extension" />
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="container col-md-10 col-lg-12">
                 <div class="row">
                     <div class="col-md-8 col-lg-8">
                         <div class="card">
                             <div class="card-header">
                                 <h5>
                                     <i class="fa fa-list-alt" aria-hidden="true"></i>
                                     <spring:message code="List"/>  <spring:message code="Extension"/>
                                 </h5>
                             </div>
                             <div class="card-body">
                                 <spring:url value="/cartas/ListadoCartaParticipant" var="ListadoCartaParticipantUrl"/>
                                 <div class="row">
                                     <div class="col-md-4">
                                         <div class="col-md-12">
                                             <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom"
                                                title="<spring:message code="List"/> <spring:message code="letters" />" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                                 <i class="fa fa-undo" aria-hidden="true"></i>
                                                 <spring:message code="back.list"/>
                                             </a>
                                         </div>
                                     </div>
                                     <div class="col-md-4"></div>
                                     <div class="col-md-4"></div>
                                 </div>
                                 <hr/>
                                 <div class="table-responsive" style="min-height: 400px;">
                                     <table  id="list_participante_extension" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                         <thead>
                                         <tr>
                                             <th hidden="hidden" class="text-center">#</th>
                                             <th class="text-center"> <spring:message code="participant"/></th>
                                             <th class="text-center"> <spring:message code="dateAdded"/></th>
                                             <th class="text-center"> <spring:message code="study"/></th>
                                             <th class="text-center"> <spring:message code="version"/></th>
                                             <th class="text-center"><spring:message code="Extension"/></th>
                                             <th class="text-center"><spring:message code="lbl.tutor"/></th>
                                             <th class="text-center"><spring:message code="lbl.vigente"/></th>
                                             <th class="text-center"><spring:message code="lbl.fec.fic.vig"/></th>
                                             <th class="text-center"><spring:message code="observacion"/></th>
                                             <th class="text-center"><spring:message code="actions"/></th>
                                         </tr>
                                         </thead>
                                         <tbody>
                                         <c:forEach items="${listExtensiones}" var="e" varStatus="theCount">
                                             <spring:url value="/cartas/disableExtension/{idParticipantExtensiontmp}" var="disableExtensionUrl">
                                                 <spring:param name="idParticipantExtension" value="${e.idParticipantExtension}" />
                                             </spring:url>
                                             <c:set var="confirmar"><spring:message code="confirm" /></c:set>
                                             <spring:url value="/cartas/editExtension/{idParticipantExtension}" var="editExtensionUrl">
                                                 <spring:param name="idParticipantExtension" value="${e.idParticipantExtension}" />
                                             </spring:url>
                                             <tr>
                                                 <td hidden="hidden" class="text-center">${e.idParticipantExtension}</td>
                                                 <td class="text-center">${e.participantecarta.participante.codigo}</td>
                                                 <td class="text-center"><fmt:formatDate value="${e.fechaExtension}" pattern="dd/MM/yyyy"/></td>
                                                 <td class="text-center">
                                                     <c:choose>
                                                         <c:when test = "${fn:contains(e.participantecarta.version.estudio.nombre, 'UO1')}">
                                                             <p>UO1</p>
                                                         </c:when>
                                                         <c:when test="${fn:contains(e.participantecarta.version.estudio.nombre, 'Dengue')}">
                                                             <p>Dengue</p>
                                                         </c:when>
                                                         <c:when test="${fn:contains(e.participantecarta.version.estudio.nombre, 'Influenza')}">
                                                             <p>Influenza</p>
                                                         </c:when>
                                                         <c:when test="${fn:contains(e.participantecarta.version.estudio.nombre, 'Familia')}">
                                                             <p>CH Familia</p>
                                                         </c:when>
                                                         <c:otherwise>
                                                             <c:out value="${e.participantecarta.version.estudio.nombre}"></c:out>
                                                         </c:otherwise>
                                                     </c:choose>
                                                 </td>
                                                 <td class="text-center">${e.participantecarta.version.version}</td>
                                                 <td class="text-center">${e.extensiones.extension}</td>
                                                 <td class="text-center">${e.nombre1Tutor} ${e.apellido1Tutor}</td>
                                                 <c:choose>
                                                     <c:when test="${e.vigente}">
                                                         <td class="text-center"><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                                     </c:when>
                                                     <c:otherwise>
                                                         <td class="text-center"><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                                     </c:otherwise>
                                                 </c:choose>
                                                 <td class="text-center"><fmt:formatDate value="${e.fecFinVigencia}" pattern="dd/MM/yyyy"/></td>
                                                 <td class="">${e.observacion}</td>
                                                 <td class="text-center">
                                                     <div class="btn-group dropleft">
                                                         <button type="button" class="btn btn-warning dropdown-toggle btn-xs" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                             <spring:message code="actions"/>
                                                         </button>
                                                         <div class="dropdown-menu">
                                                             <a title="<spring:message code="edit" />" href="${fn:escapeXml(editExtensionUrl)}" class="dropdown-item">
                                                                 <strong><i class="fa fa-edit text-white" aria-hidden="true"></i>
                                                                     <spring:message code="edit" /></strong></a>
                                                             <div class="dropdown-divider"></div>
                                                             <a title="<spring:message code="disable" />" data-toggle="modal" class="dropdown-item desact">
                                                                 <strong> <i class="fa fa-trash-o text-white" aria-hidden="true"></i>
                                                                     <spring:message code="disable" /> </strong>
                                                             </a>
                                                         </div>
                                                     </div>
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
        <!-- /.conainer-fluid -->
    </div>
</div>

<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
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
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/views/Cartas/ListadoCartaParticipnt.js" var="BuscaCartaPScript" />
<script src="${BuscaCartaPScript}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sweet" />
<script type="text/javascript" src="${sweet}"></script>
<script>
    $(document).ready(function(){
        var parametros ={
            disableExtensionUrl: "${disableExtensionUrl}",
            ListadoCartaParticipantUrl:"${ListadoCartaParticipantUrl}"
        };
        var table = $('#list_participante_extension').DataTable({
            "oLanguage"  : {
                "sUrl"   : "${dataTablesLang}"
            },
            "autoWidth": true,
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                },
                { "width": "1%", "targets": 0 },
                { "width": "2%", "targets": 2 }
            ]
        });

        $('#list_participante_extension thead tr').clone(true).appendTo( '#list_participante_extension thead' );
        $('#list_participante_extension thead tr:eq(1) th').each( function (i) {
            var title = $(this).text();
            if (title != 'Acciones') {
                $(this).html('<input type="text" placeholder="Buscar '+title+'" class="form-control-buscar" />');
                $('input', this).on('keyup change', function () {
                    if (table.column(i).search() !== this.value) {
                        table.column(i).search(this.value).draw();
                    }
                });
            } else {
                $(this).html('');
            }
        });

        $("#list_participante_extension tbody").on("click", ".desact", function(e){
            var correntRow = $(this).closest('tr').find('td');
            var idParticipantExtension = table.row(correntRow).data()[0];
            var idparticipante = table.row(correntRow).data()[1];
            var fecha = table.row(correntRow).data()[2];

            swal({
                title: "Deshabilitar? ",
                text: "código: " + idparticipante + " con fecha: "+ fecha,
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Si!",
                cancelButtonText: "No, plx!",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                    $.post(parametros.disableExtensionUrl, {idParticipantExtension: idParticipantExtension, ajax: 'true'}, function (data) {
                        swal("Deshabilitado!", "con éxito!", "success");
                        setTimeout(function () {
                            window.location.reload();
                        }, 1500);
                    }).fail(function () {
                        setTimeout(function () {
                            swal("Error!", "500 Internal Server!", "error");
                        }, 2000);
                    });
                } else {
                    swal("Cancelado!", "Registro está seguro! :)", "error");
                }
            });
        });
    });
</script>
</body>
</html>
