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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/vcartas/" htmlEscape="true "/>"><spring:message code="versionLetters" /></a>
            </li>
        </ol>
        <c:set var="exportar"><spring:message code="export" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <c:set var="recordDisabledLabel"><spring:message code="recordDisabled" /></c:set>

        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-list-alt"></i> <spring:message code="versionLetters" />
                </div>
                <div class="card-block">
                    <div class="row table-toolbar">
                        <div class="col-md-12">
                            <div class="btn-group">
                                <spring:url value="/admin/vcartas/newVersion"	var="newVersion"/>
                                <button id="new_version" onclick="location.href='${fn:escapeXml(newVersion)}'" class="btn btn-success">
                                    <spring:message code="add" /> <i class="fa fa-plus"></i>
                                </button>
                            </div>
                            <br>
                            <br>
                        </div>
                    </div>
                    <div class="table-responsive">

                        <table class="table table-hover table-bordered" id="lista_versiones">
                            <thead>
                            <tr>
                                <th><spring:message code="userstudies" /></th>
                                <th><spring:message code="version" /></th>
                                <th><spring:message code="validFrom" /></th>
                                <th><spring:message code="validUntil" /></th>
                                <th><spring:message code="addedBy" /></th>
                                <th><spring:message code="dateCreated" /></th>
                                <th><spring:message code="actions" /></th>
                            </tr>
                            </thead>
                            <c:forEach items="${versiones}" var="version">
                                <tr>
                                    <spring:url value="/admin/users/editUser/{username}"
                                                var="editUrl">
                                        <spring:param name="username" value="${version.codigo}" />
                                    </spring:url>
                                    <spring:url value="/admin/vcartas/habdes/disable/{codigo}"
                                                var="disableUrl">
                                        <spring:param name="codigo" value="${version.codigo}-${version}" />
                                    </spring:url>
                                    <td><c:out value="${version.estudio.nombre}" /></td>
                                    <td><c:out value="${version.version.spanish}" /></td>
                                    <td><fmt:formatDate value="${version.fechaInicio}" pattern="dd/MM/yyyy" /></td>
                                    <td><fmt:formatDate value="${version.fechaFin}" pattern="dd/MM/yyyy" /></td>
                                    <td><c:out value="${version.recordUser}" /></td>
                                    <td><c:out value="${version.recordDate}" /></td>
                                    <td>
                                        <a href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
                                        <!--<a data-toggle="modal" data-id= "${fn:escapeXml(disableUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash-o"></i></a>
                                        -->
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
                            <button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
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
        var table = $('#lista_versiones').DataTable({
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

        if ("${deshabilitado}"){
            toastr.error("${recordDisabledLabel}", "${registroVersion}" );
        }
        $(".desact").click(function(){
            $('#accionUrl').val($(this).data('id').substr(0,$(this).data('id').lastIndexOf("-")));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+decodeURIComponent($(this).data('id').substr($(this).data('id').lastIndexOf("-")+1))+'?</h3>');
            $('#basic').modal('show');
        });

    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>