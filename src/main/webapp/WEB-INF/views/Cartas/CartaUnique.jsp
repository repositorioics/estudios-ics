<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 25/06/2021
  Time: 14:06
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
    <spring:url value="/resources/css/sweetalert.css" var="swalcss"/>
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>
    <style>
        /*==================================================
  * Effect 2
  * ===============================================*/
        .effect2 {
            position: relative;
        }

        .effect2:before, .effect2:after {
            z-index: -1;
            position: absolute;
            content: "";
            bottom: 15px;
            left: 10px;
            width: 50%;
            top: 80%;
            max-width: 300px;
            background: #777;
            -webkit-box-shadow: 0 15px 10px #777;
            -moz-box-shadow: 0 15px 10px #777;
            box-shadow: 0 15px 10px #777;
            -webkit-transform: rotate(-3deg);
            -moz-transform: rotate(-3deg);
            -o-transform: rotate(-3deg);
            -ms-transform: rotate(-3deg);
            transform: rotate(-3deg);
        }

        .effect2:after {
            -webkit-transform: rotate(3deg);
            -moz-transform: rotate(3deg);
            -o-transform: rotate(3deg);
            -ms-transform: rotate(3deg);
            transform: rotate(3deg);
            right: 10px;
            left: auto;
        }


    </style>
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
    </li>
</ol>
<div class="container-fluid">
<div class="animated fadeIn">
<div class="card effect2">
<div class="card-header">
    <h4 style="font-family: Roboto, sans-serif">
        <i class="fa fa-clipboard" aria-hidden="true"></i>
        <spring:message code="letters"/></h4>
</div>
<div class="card-body">
<div class="col-xs-12 col-md-12">
<br/>
    <div class="col-md-12">
        <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="lbl.To.assign" /> <spring:message code="letters" />" href="<spring:url value="/cartas/Crear" htmlEscape="true "/>">
            <i class="fa fa-plus-circle" aria-hidden="true"></i>
            <spring:message code="lbl.To.assign" /> <spring:message code="letters" />
        </a>
    </div>
    <hr/>
<div class="table-responsive" style="min-height: 450px;">
    <table id="tblSaved" class="table table-hover table-bordered table-striped">
        <thead>
        <tr>
            <th class="text-center" width="10%"><spring:message code="code"/></th>
            <th class="text-center" width="20%"><spring:message code="lbl.names.surnames"/></th>
            <th class="text-center" width="12%"><spring:message code="letters"/></th>
            <th class="text-center" width="12%"><spring:message code="version"/></th>
            <th class="text-center" width="10%"><spring:message code="dateAdded"/> <spring:message code="letters"/></th>
            <th class="text-center" width="12%"><spring:message code="actions"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <spring:url value="/cartas/EditCarta/{idCartaParticipante}" var="EditCartaUrl">
                <spring:param name="idCartaParticipante" value="${pc.idparticipantecarta}"/>
            </spring:url>
            <spring:url value="/cartas/extension/{idCartaParticipante}" var="extensionUrl">
                <spring:param name="idCartaParticipante" value="${pc.idparticipantecarta}"/>
            </spring:url>
            <spring:url value="/reportes/ReporteCarta/" var="pdfCartaUrl"/>
            <spring:url value="/cartas/VerParteCarta/" var="searchPartesUrl"/>
            <c:set var="successMessage"><spring:message code="process.success"/></c:set>
            <c:set var="errorProcess"><spring:message code="process.error"/></c:set>
            <spring:url value="/cartas/updateDetalleParte" var="updateDetalleParteUrl"/>
            <spring:url value="/cartas/Delete" var="DeleteUrl"/>
            <td>
                ${pc.participante.codigo}
            </td>
            <td>
                ${pc.participante.nombreCompleto}
            </td>
            <td>
                ${pc.version.estudio.nombre}
            </td>
            <td>
                ${pc.version.version}
            </td>
            <td>
                <fmt:formatDate value="${pc.fechacarta}" pattern="dd/MM/yyyy"/>
            </td>
            <td>
                <div class="btn-group">
                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        <spring:message code="actions"/>
                    </button>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a title="<spring:message code="edit" />" href="${fn:escapeXml(EditCartaUrl)}"
                           class="dropdown-item">
                            <i class="fa fa-pencil text-warning" aria-hidden="true"></i> <spring:message code="edit"/>
                        </a>
                        <!--<a class="dropdown-item btnViewParte" data-id="${pc.idparticipantecarta}">
                            <i class="fa fa-refresh text-warning" aria-hidden="true"></i> <spring:message
                                code="lbl.update"/> <spring:message code="parte"/>
                        </a>-->

                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item btnReporte" data-id="${pc.idparticipantecarta}">
                            <i class="fa fa-book text-info" aria-hidden="true"></i> <spring:message code="reports"/>
                        </a>
                        <c:choose>
                            <c:when test="${tieneExt eq true}">
                                <a class="dropdown-item btnExtension" href="${fn:escapeXml(extensionUrl)}">
                                    <i class="fa fa-plus-square" aria-hidden="true"></i>
                                    <spring:message code="Extension"/>
                                    <span class="badge badge-primary" style="border-radius: 5px"> ${partExt} </span>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <button class="dropdown-item" disabled="disabled">
                                    <i class="fa fa-times" aria-hidden="true"></i>
                                    <spring:message code="WithOut.Extension"/>
                                </button>
                                <!--<a id="extDisable" class="dropdown-item btnExtension" href="" disabled="disabled">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                Extensión
                                <span class="badge badge-primary" style="border-radius: 5px"> ${partExt} </span>
                                </a>-->
                            </c:otherwise>
                        </c:choose>
                        <!--<div class="dropdown-divider"></div>
                               <a class="dropdown-item btnDelete"  data-id="${pc.idparticipantecarta}">
                                   <i class="fa fa-trash text-danger" aria-hidden="true"></i> <spring:message code="Eliminar Registro" />
                               </a>-->
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</div>
<!-- fin -->
</div>
<div class="card-footer text-muted">
</div>
</div>
</div>
</div>

<!-- Modal  -->
<div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" data-backdrop="static" data-keyboard="false" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-primary" id="exampleModalLabel" style="font-family: Roboto, sans-serif">
                    <i class="fa fa-newspaper-o" aria-hidden="true"></i>
                    Detalle Parte Principal</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container text-center">
                    <div class="alert alert-danger alert-dismissible fade show" id="mialerta" role="alert"
                         style="display: none">
                        <strong>Error!</strong> checka al menos una opción.
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered table-lg display" id="tblDetalleParte"
                               width="100%">
                            <thead>
                            <tr>
                                <th scope="col">iddetalle</th>
                                <th scope="col">idparticipantecarta</th>
                                <th scope="col" class="text-center">Parte</th>
                                <th scope="col" class="text-center">IdParte</th>
                                <th scope="col" class="text-center">Acepta</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger float-left" data-dismiss="modal">
                    <i class="fa fa-times-circle" aria-hidden="true"></i>
                    Cerrar
                </button>
                <button type="button" id="btnActualizar" class="btn btn-primary float-right">
                    <i class="fa fa-pencil-square" aria-hidden="true"></i>
                    Actualizar
                </button>
            </div>
        </div>
    </div>
</div>
<!-- /.conainer-fluid -->
</div>
</div>

<jsp:include page="../fragments/bodyFooter.jsp"/>
<jsp:include page="../fragments/corePlugins.jsp"/>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />

<spring:url value="/resources/js/app.js" var="App"/>
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
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs"/>
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs"/>
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>

<spring:url value="/resources/js/libs/sweetalert.js" var="sweet"/>
<script type="text/javascript" src="${sweet}"></script>

<script>

    $(document).ready(function () {
        var parametros = {
            dataTablesLang: "${dataTablesLang}",
            GetCartasParticipanteUrl: "${GetCartasParticipanteUrl}",
            searchPartesUrl: "${searchPartesUrl}",
            EditCartaUrl: "${EditCartaUrl}",
            UpdateRetiroUrl: "${UpdateRetiroUrl}",
            pdfCartaUrl: "${pdfCartaUrl}",
            MasCartaUrl: "${MasCartaUrl}",
            VerExtensionUrl: "${VerExtensionUrl}",
            extensionUrl: "${extensionUrl}",
            updateDetalleParteUrl: "${updateDetalleParteUrl}",
            successmessage: "${successMessage}",
            error: "${errorProcess}",
            DeleteUrl: "${DeleteUrl}",
            haRoleAdmin: "${haRoleAdmin}",
            isAuthorizeAny: "${isAuthorizeAny}"
        };

        $("#extDisable").on("click", function (e) {
            e.preventDefault();
        });
        var table = $('#tblSaved').DataTable({
            "paging": false,
            "ordering": false,
            "info": false,
            searching: false,
            "autoWidth": true,
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }, columnDefs: [
                {
                    targets: 0,
                    className: 'text-center'

                },
                {
                    targets: 4,
                    className: 'text-center'
                },
                {
                    targets: 5,
                    className: 'text-center'
                }
            ]
        });
        var table2 = $("#tblDetalleParte").DataTable({
            searching: false,
            paging: false,
            "ordering": false,
            "info": false,
            "oLanguage": {
                "sUrl": parametros.dataTablesLang
            },
            columnDefs: [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [1],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [3],
                    "visible": false,
                    "searchable": false
                }
            ]
        });

        $('#tblSaved tbody').on('click', '.btnReporte', function () {
            var id = $(this).data('id');
            CrearReporte(id);
        });
        function CrearReporte(id) {
            if (id != null) {
                window.open("${pdfCartaUrl}?idparticipantecarta=" + id, '_blank');
            }
        }

        $('#tblSaved tbody').on('click', '.btnViewParte', function () {
            var id = $(this).data('id');
            VerPartes(id);
        });
        function VerPartes(id) {
            $.getJSON(parametros.searchPartesUrl, { idparticipantecarta: id, ajax: 'true'  }, function (data) {
                console.log(data);
                table2.clear().draw(false);
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    var iddetalle = data[i].iddetalle;
                    var idparticipantecarta = data[i].participantecarta.idparticipantecarta;
                    var parte = data[i].parte.parte;
                    var idParte = data[i].parte.idparte;
                    var acepta = "";
                    if (data[i].acepta) {
                        acepta = '<input type="checkbox" class="filter-ck" id="' + data[i].iddetalle + '" checked />';
                    } else {
                        acepta = '<input type="checkbox" class="filter-ck" id="' + data[i].iddetalle + '" />';
                    }
                    table2.row.add([
                        iddetalle,
                        idparticipantecarta,
                        parte,
                        idParte,
                        acepta
                    ]).draw(false);
                }
                $("#exampleModal").modal("show");
            }).fail(function () {
                swal("Error!", "Falló al obtener la información!", "error");
            });
        }

        $("#btnActualizar").on("click", function (e) {
            var arrData = [];
            $("#tblDetalleParte tbody tr").each(function () {
                var currentRow = $(this);
                var col0_value = table2.row(this).data()[0];
                var col1_value = table2.row(this).data()[1];
                var col3_value = table2.row(this).data()[3];
                var col4_value = currentRow.find(":checkbox").prop("checked");
                var obj = {};
                obj.iddetalle = parseInt(col0_value);
                obj.idparticipantecarta = parseInt(col1_value);
                obj.idparte = parseInt(col3_value);
                obj.acepta = col4_value;
                arrData.push(obj);
            });
            debugger;
            if ($('input[type=checkbox]:checked').length === 0) {
                e.preventDefault();
                myFunctionAlert();
                return;
            }
            ActualizarPartesPrincipales(arrData);
        });
        function ActualizarPartesPrincipales(arrData) {
            $.ajax({
                url: parametros.updateDetalleParteUrl,
                type: "post",
                data: JSON.stringify(arrData),
                dataType: "JSON",
                contentType: "application/json; charset=utf-8",
                success: function (response, textStatus, jQxhr) {
                    if (response.mensaje != null) {
                        $("#exampleModal").modal("hide");
                        toastr.success(parametros.successmessage);
                    }
                    console.log(response);
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        }


        $("#tblSaved tbody").on("click", ".btnDelete", function (e) {
            var id = $(this).data('id');
            var row = $(this).parents('tr');
            var obj = {};
            var correntRow = $(this).closest('tr').find('td');
            var col0_value = $(correntRow).eq(0).text();
            var col2_value = $(correntRow).eq(2).text();
            var col3_value = $(correntRow).eq(3).text();
            var col4_value = $(correntRow).eq(4).text();
            obj.idParticipantecarta = parseInt(id);
            obj.idParticipante = parseInt(col0_value);
            obj.estudio = col2_value;
            obj.version = col3_value;
            obj.fechaCarta = col4_value;
            swal({
                        title: "Eliminar? ",
                        text: "Código: " + obj.idParticipante + " con Fecha: " + obj.fechaCarta,
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Si, Bórralo!",
                        cancelButtonText: "No, Borres plx!",
                        closeOnConfirm: false,
                        closeOnCancel: false
                    },
                    function (isConfirm) {
                        if (isConfirm) {
                            row.remove();
                            $.post(parametros.DeleteUrl, {idParticpanteCarta: obj.idParticipantecarta, ajax: 'true'}, function (data) {
                                swal("Eliminado!", "con éxito!", "success");
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
    });
</script>
</body>
</html>
