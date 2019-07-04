<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../../fragments/sideBar.jsp" />
    <!-- Main content -->
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/super/casacaso/" htmlEscape="true "/>"><spring:message code="intensiveMonitoring" /></a>
            </li>
        </ol>
        <c:set var="exportar"><spring:message code="export" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-list-alt"></i> <spring:message code="intensiveMonitoring" /> <spring:message code="house" />
                    <c:out value="${caso.casa.codigoCHF}" /> (<fmt:formatDate value="${caso.fechaInicio}" pattern="dd/MM/yyyy" /> - <fmt:formatDate value="${caso.fechaInactiva}" pattern="dd/MM/yyyy" />)
                </div>
                <div class="card-block">
                    <div class="row table-toolbar">
                        <div class="col-md-12">
                            <div class="btn-group">
                                <spring:url value="/super/casacaso/"	var="listUrl"/>
                                <button id="new_version" onclick="location.href='${fn:escapeXml(listUrl)}'" class="btn btn-info">
                                    <spring:message code="cases" /> <i class="fa fa-reply"></i>
                                </button>
                            </div>
                            <br>
                            <br>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered" id="lista_participantes">
                            <thead>
                            <tr>
                                <th><spring:message code="participant.code" /></th>
                                <th><spring:message code="participant" /></th>
                                <th><spring:message code="age" /></th>
                                <th><spring:message code="positive" /></th>
                                <th><spring:message code="fif" /></th>
                                <th><spring:message code="actions" /></th>
                            </tr>
                            </thead>
                            <c:forEach items="${participantes}" var="parti">
                                <spring:url value="/super/casacaso/actions/disablepart/{codigo}"
                                            var="disableUrl">
                                    <spring:param name="codigo" value="${parti.codigoCasoParticipante}*${parti.participante.participante.codigo}" />
                                </spring:url>
                                <tr>
                                    <c:set var="edadParts" value="${fn:split(parti.participante.participante.edad, '/')}" />
                                    <td><c:out value="${parti.participante.participante.codigo}" /></td>
                                    <td><c:out value="${parti.participante.participante.nombreCompleto}" /></td>
                                    <td><c:out value="${edadParts[0]} años ${edadParts[1]} meses ${edadParts[2]} dias" /></td>
                                    <c:choose>
                                        <c:when test="${parti.enfermo eq 'S'}">
                                            <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td><fmt:formatDate value="${parti.fechaEnfermedad}" pattern="dd/MM/yyyy" /></td>
                                    <td>
                                        <c:choose>
                                        <c:when test="${parti.codigoCaso.inactiva=='1' or parti.enfermo eq 'S'}">
                                            <button title="<spring:message code="disable" />" class="btn btn-outline-primary btn-sm" disabled><i class="fa fa-trash-o"></i></button>
                                        </c:when>
                                            <c:otherwise>
                                                <a title="<spring:message code="disable" />" data-toggle="modal" data-id="${fn:escapeXml(disableUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash-o"></i></a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
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
                            <input type=hidden id="accionUrl"/>
                            <div id="cuerpo"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                            <button type="button" id="btnOk" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../../fragments/bodyFooter.jsp" />
<jsp:include page="../../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />

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
<script>
    jQuery(document).ready(function() {
        var table = $('#lista_participantes').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });

        var tt = new $.fn.dataTable.TableTools( table, {
            "sSwfPath": "${dataTablesTTSWF}",
            "aButtons": [
                {
                    "sExtends":    "collection",
                    "sButtonText": "${exportar}",
                    "aButtons": [
                        {
                            "sExtends": "csv",
                            "oSelectorOpts": { filter: 'applied', order: 'current' },
                            "mColumns": [ 0, 1, 2, 3, 4 ]
                        },
                        {
                            "sExtends": "pdf",
                            "oSelectorOpts": { filter: 'applied', order: 'current' },
                            "mColumns": [ 0, 1, 2, 3, 4 ],
                            "sPdfOrientation": "landscape"
                        }
                    ]
                }
            ]
        } );

        $( tt.fnContainer() ).insertBefore('div.table-toolbar');

        $(".desact").click(function(){
            $('#accionUrl').val($(this).data('id').substr(0,$(this).data('id').lastIndexOf("*")));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+decodeURIComponent($(this).data('id').substr($(this).data('id').lastIndexOf("*")+1))+'?</h3>');
            $('#btnOk').show();
            $('#basic').modal('show');
        });

    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>