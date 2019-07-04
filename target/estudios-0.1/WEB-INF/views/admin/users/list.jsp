<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/" htmlEscape="true "/>"><spring:message code="users" /></a>
            </li>
        </ol>
        <c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
        <c:set var="userDisabledLabel"><spring:message code="login.userDisabled" /></c:set>
        <c:set var="userLockedLabel"><spring:message code="login.accountLocked" /></c:set>
        <c:set var="userUnlockedLabel"><spring:message code="login.accountNotLocked" /></c:set>
        <c:set var="habilitar"><spring:message code="enable" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <c:set var="bloquear"><spring:message code="lock" /></c:set>
        <c:set var="desbloquear"><spring:message code="unlock" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="exportar"><spring:message code="export" /></c:set>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-group"></i> <spring:message code="users" />
                </div>
                <div class="card-block">
                    <div class="row table-toolbar">
                        <div class="col-md-12">
                            <div class="btn-group">
                                <spring:url value="/admin/users/newUser"	var="newUser"/>
                                <button id="lista_usuarios_new" onclick="location.href='${fn:escapeXml(newUser)}'" class="btn btn-success">
                                    <spring:message code="add" /> <i class="fa fa-plus"></i>
                                </button>
                            </div>
                            <br>
                            <br>
                        </div>
                    </div>
                    <div class="table-responsive">

                        <table class="table table-hover table-bordered" id="lista_usuarios">
                            <thead>
                            <tr>
                                <th><spring:message code="username" /></th>
                                <th class="hidden-xs"><spring:message code="userdesc" /></th>
                                <th class="hidden-xs"><spring:message code="useremail" /></th>
                                <th><spring:message code="enabled" /></th>
                                <th><spring:message code="userlock" /></th>
                                <th><spring:message code="usercred" /></th>
                                <th><spring:message code="actions" /></th>
                            </tr>
                            </thead>
                            <c:forEach items="${usuarios}" var="usuario">
                                <tr>
                                    <spring:url value="/admin/users/{username}"
                                                var="usuarioUrl">
                                        <spring:param name="username" value="${usuario.username}" />
                                    </spring:url>
                                    <spring:url value="/admin/users/editUser/{username}"
                                                var="editUrl">
                                        <spring:param name="username" value="${usuario.username}" />
                                    </spring:url>
                                    <spring:url value="/admin/users/habdes/disable1/{username}"
                                                var="disableUrl">
                                        <spring:param name="username" value="${usuario.username}" />
                                    </spring:url>
                                    <spring:url value="/admin/users/habdes/enable1/{username}"
                                                var="enableUrl">
                                        <spring:param name="username" value="${usuario.username}" />
                                    </spring:url>
                                    <spring:url value="/admin/users/lockunl/lock1/{username}"
                                                var="lockUrl">
                                        <spring:param name="username" value="${usuario.username}" />
                                    </spring:url>
                                    <spring:url value="/admin/users/lockunl/unlock1/{username}"
                                                var="unlockUrl">
                                        <spring:param name="username" value="${usuario.username}" />
                                    </spring:url>
                                    <spring:url value="/admin/users/chgpass/{username}"
                                                var="chgpassUrl">
                                        <spring:param name="username" value="${usuario.username}" />
                                    </spring:url>
                                    <td><a href="${fn:escapeXml(usuarioUrl)}"><c:out value="${usuario.username}" /></a></td>
                                    <td class="hidden-xs"><c:out value="${usuario.completeName}" /></td>
                                    <td class="hidden-xs"><c:out value="${usuario.email}" /></td>
                                    <c:choose>
                                        <c:when test="${usuario.enabled}">
                                            <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${usuario.accountNonLocked}">
                                            <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${usuario.credentialsNonExpired}">
                                            <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        <a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-search"></i></a>
                                        <a href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
                                        <a href="${fn:escapeXml(chgpassUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-key"></i></a>
                                        <c:choose>
                                            <c:when test="${usuario.enabled}">
                                                <a data-toggle="modal" data-id= "${fn:escapeXml(disableUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash-o"></i></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a data-toggle="modal" data-id= "${fn:escapeXml(enableUrl)}" class="btn btn-outline-primary btn-sm act"><i class="fa fa-check"></i></a>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${usuario.accountNonLocked}">
                                                <a data-toggle="modal" data-id= "${fn:escapeXml(lockUrl)}" class="btn btn-outline-primary btn-sm lock"><i class="fa fa-lock"></i></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a data-toggle="modal" data-id= "${fn:escapeXml(unlockUrl)}" class="btn btn-outline-primary btn-sm unlock"><i class="fa fa-unlock"></i></a>
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
        var table = $('#lista_usuarios').DataTable({
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


        if ("${usuarioHabilitado}"){
            toastr.success("${userEnabledLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioDeshabilitado}"){
            toastr.error("${userDisabledLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioBloqueado}"){
            toastr.error("${userLockedLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioNoBloqueado}"){
            toastr.success("${userUnlockedLabel}", "${nombreUsuario}" );
        }

        $(".act").click(function(){
            $('#accionUrl').val($(this).data('id'));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${habilitar}"+' '+$(this).data('id').substr($(this).data('id').lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        });

        $(".desact").click(function(){
            $('#accionUrl').val($(this).data('id'));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+$(this).data('id').substr($(this).data('id').lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        });

        $(".lock").click(function(){
            $('#accionUrl').val($(this).data('id'));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${bloquear}"+' '+$(this).data('id').substr($(this).data('id').lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        });

        $(".unlock").click(function(){
            $('#accionUrl').val($(this).data('id'));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${desbloquear}"+' '+$(this).data('id').substr($(this).data('id').lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        });
    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>