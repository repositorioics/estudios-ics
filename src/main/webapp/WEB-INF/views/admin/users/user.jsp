<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../../fragments/headTag.jsp" />
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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/" htmlEscape="true "/>"><spring:message code="users" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/${user.username}" htmlEscape="true "/>">${user.username}</a>
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
            <spring:url value="/admin/users/editUser/{username}" var="editUrl">
                <spring:param name="username" value="${user.username}" />
            </spring:url>
            <spring:url value="/admin/users/chgpass/{username}" var="chgpassUrl">
                <spring:param name="username" value="${user.username}" />
            </spring:url>
            <spring:url value="/admin/users/habdes/disable2/{username}" var="disableUrl">
                <spring:param name="username" value="${user.username}" />
            </spring:url>
            <spring:url value="/admin/users/habdes/enable2/{username}" var="enableUrl">
                <spring:param name="username" value="${user.username}" />
            </spring:url>
            <spring:url value="/admin/users/lockunl/lock2/{username}" var="lockUrl">
                <spring:param name="username" value="${user.username}" />
            </spring:url>
            <spring:url value="/admin/users/lockunl/unlock2/{username}" var="unlockUrl">
                <spring:param name="username" value="${user.username}" />
            </spring:url>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-user"></i><c:out value="${user.username}" />
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="btn-group">
                                    <spring:url value="/admin/users/"	var="listUser"/>
                                    <button id="lista_usuarios_new" onclick="location.href='${fn:escapeXml(listUser)}'" class="btn btn-info">
                                        <spring:message code="users" /> <i class="fa fa-reply"></i>
                                    </button>
                                </div>
                                <div class="btn-group pull-right">
                                    <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><spring:message code="actions" />
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right">
                                        <li class="dropdown-item">
                                            <a href="${fn:escapeXml(editUrl)}"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
                                        </li>
                                        <li class="dropdown-item">
                                            <a href="${fn:escapeXml(chgpassUrl)}"><i class="fa fa-key"></i> <spring:message code="changepass" /></a>
                                        </li>
                                        <c:choose>
                                            <c:when test="${user.enabled}">
                                                <li class="dropdown-item">
                                                    <a data-toggle="modal" data-id= "${fn:escapeXml(disableUrl)}" class="desact"><i class="fa fa-trash-o"></i> <spring:message code="disable" /></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="dropdown-item">
                                                    <a data-toggle="modal" data-id= "${fn:escapeXml(enableUrl)}" class="act"><i class="fa fa-check"></i> <spring:message code="enable" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${user.accountNonLocked}">
                                                <li class="dropdown-item">
                                                    <a data-toggle="modal" data-id= "${fn:escapeXml(lockUrl)}" class="lock"><i class="fa fa-lock"></i> <spring:message code="lock" /></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="dropdown-item">
                                                    <a data-toggle="modal" data-id= "${fn:escapeXml(unlockUrl)}" class="unlock"><i class="fa fa-unlock"></i> <spring:message code="unlock" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <form action="" method="post" class="form-horizontal ">
                            <div class="row">
                                <div class="col-md-6 col-lg-6">
                                    <div class="form-group row">
                                        <label class="col-md-3 form-control-label"><spring:message code="userdesc" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static"><c:out value="${user.completeName}" /></p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-6 col-lg-6">
                                    <div class="form-group row">
                                        <label class="form-control-label col-md-3"><spring:message code="useremail" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:out value="${user.email}" />
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <!--/span-->
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="enabled" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:choose>
                                                    <c:when test="${user.enabled}">
                                                        <spring:message code="CHF_CAT_SINO_SI" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <spring:message code="CHF_CAT_SINO_NO" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="locked" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:choose>
                                                    <c:when test="${user.accountNonLocked}">
                                                        <spring:message code="CHF_CAT_SINO_NO" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <spring:message code="CHF_CAT_SINO_SI" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                            <div class="row">
                                <!--/span-->
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="usercred" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:choose>
                                                    <c:when test="${user.credentialsNonExpired}">
                                                        <spring:message code="CHF_CAT_SINO_NO" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <spring:message code="CHF_CAT_SINO_SI" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="userexp" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:choose>
                                                    <c:when test="${user.accountNonExpired}">
                                                        <spring:message code="CHF_CAT_SINO_NO" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <spring:message code="CHF_CAT_SINO_SI" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="createdBy" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:out value="${user.createdBy}" />
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="dateCreated" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:out value="${user.created}" />
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                            <!--/row-->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="modifiedBy" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:out value="${user.modifiedBy}" />
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="dateModified" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:out value="${user.modified}" />
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                            <!--/row-->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="lastAccess" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:out value="${user.lastAccess}" />
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="dateCredentials" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:out value="${user.lastCredentialChange}" />
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                            <!--/row-->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="permission" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:if test="${permisos.muestra}">
                                                    <spring:message code="muestra" />,
                                                </c:if>
                                                <c:if test="${permisos.vacunas}">
                                                    <spring:message code="vacunas" />,
                                                </c:if>
                                                <c:if test="${permisos.consentimiento}">
                                                    <spring:message code="consentimiento" />,
                                                </c:if>
                                                <c:if test="${permisos.recepcion}">
                                                    <spring:message code="recepcion" />,
                                                </c:if>
                                                <c:if test="${permisos.visitas}">
                                                    <spring:message code="visits" />,
                                                </c:if>
                                                <c:if test="${permisos.obsequio}">
                                                    <spring:message code="obsequio" />,
                                                </c:if>
                                                <c:if test="${permisos.pesoTalla}">
                                                    <spring:message code="pesotalla" />,
                                                </c:if>
                                                <c:if test="${permisos.datosparto}">
                                                    <spring:message code="datosparto" />
                                                </c:if>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="control-label col-md-3"><spring:message code="encuestas" />:</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">
                                                <c:if test="${permisos.encuestaCasa}">
                                                    <spring:message code="ecasa" />,
                                                </c:if>
                                                <c:if test="${permisos.encuestaParticipante}">
                                                    <spring:message code="participants" />,
                                                </c:if>
                                                <c:if test="${permisos.encuestaLactancia}">
                                                    <spring:message code="elactancia" />,
                                                </c:if>
                                                <c:if test="${permisos.encuestaSatisfaccion}">
                                                    <spring:message code="esatisfaccion" />
                                                </c:if>
                                                <c:if test="${permisos.pAbdominal}"> <%--Perimetro Abdominal--%>
                                                    <spring:message code="pAbdominal" />
                                                </c:if>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                            <!--/row-->
                        </form>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-group"></i> <spring:message code="userroles" />
                    </div>
                    <div class="card-block">
                        <div class="table-responsive">
                            <table class="table" id="lista_roles">
                                <thead>
                                <tr>
                                    <th><spring:message code="userroles" /></th>
                                    <th><spring:message code="enabled" /></th>
                                    <th><spring:message code="addedBy" /></th>
                                    <th><spring:message code="dateAdded" /></th>
                                </tr>
                                </thead>
                                <c:forEach items="${rolesusuario}" var="rol">
                                    <tr>
                                        <td><spring:message code="${rol.rol.authority}" /></td>
                                        <c:set var="pasiveAsString">${rol.pasive}</c:set>
                                        <c:choose>
                                            <c:when test="${pasiveAsString eq '0'}">
                                                <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td><c:out value="${rol.recordUser}" /></td>
                                        <td><c:out value="${rol.recordDate}" /></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-group"></i> <spring:message code="userstudies" />
                    </div>
                    <div class="card-block">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover table-bordered" id="lista_estudios">
                                <thead>
                                <tr>
                                    <th><spring:message code="userstudies" /></th>
                                    <th><spring:message code="enabled" /></th>
                                    <th><spring:message code="addedBy" /></th>
                                    <th><spring:message code="dateAdded" /></th>
                                </tr>
                                </thead>
                                <c:forEach items="${estudiosUsuario}" var="estudio">
                                    <tr>
                                        <td><spring:message code="${estudio.estudio.nombre}" /></td>
                                        <c:choose>
                                            <c:when test="${estudio.pasive=='0'}">
                                                <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td><c:out value="${estudio.recordUser}" /></td>
                                        <td><c:out value="${estudio.recordDate}" /></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-key"></i> <spring:message code="access" />
                    </div>
                    <div class="card-block">
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered" id="lista_accesos">
                                <thead>
                                <tr>
                                    <th class="hidden-xs"><spring:message code="session" /></th>
                                    <th class="hidden-xs"><spring:message code="ipaddress" /></th>
                                    <th><spring:message code="logindate" /></th>
                                    <th><spring:message code="logoutdate" /></th>
                                    <th class="hidden-xs"><spring:message code="logouturl" /></th>
                                </tr>
                                </thead>
                                <c:forEach items="${accesses}" var="acceso">
                                    <tr>
                                        <td class="hidden-xs"><c:out value="${acceso.sessionId}" /></td>
                                        <td class="hidden-xs"><c:out value="${acceso.remoteIpAddress}" /></td>
                                        <td><c:out value="${acceso.loginDate}" /></td>
                                        <td><c:out value="${acceso.logoutDate}" /></td>
                                        <td class="hidden-xs"><c:out value="${acceso.logoutRefererUrl}" /></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-pencil"></i> <spring:message code="audittrail" />
                    </div>
                    <div class="card-block">
                        <div class="table-responsive">
                            <table class="table table-hover table-bordered" id="lista_cambios">
                                <thead>
                                <tr>
                                    <th><spring:message code="entityClass" /></th>
                                    <th><spring:message code="entityName" /></th>
                                    <th><spring:message code="entityProperty" /></th>
                                    <th><spring:message code="entityPropertyOldValue" /></th>
                                    <th><spring:message code="entityPropertyNewValue" /></th>
                                    <th><spring:message code="modifiedBy" /></th>
                                    <th><spring:message code="dateModified" /></th>
                                </tr>
                                </thead>
                                <c:forEach items="${bitacora}" var="cambio">
                                    <tr>
                                        <td><spring:message code="${cambio.entityClass}" /></td>
                                        <td><c:out value="${cambio.entityName}" /></td>
                                        <td><c:out value="${cambio.entityProperty}" /></td>
                                        <td><c:out value="${cambio.entityPropertyOldValue}" /></td>
                                        <td><c:out value="${cambio.entityPropertyNewValue}" /></td>
                                        <td><c:out value="${cambio.username}" /></td>
                                        <td><c:out value="${cambio.operationDate}" /></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- modal-dialog -->
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
        $(function () {
            $("li.administracion").addClass("open");
            $("li.users").addClass("open");
        });
    </script>

<script>
    jQuery(document).ready(function() {
        $('#lista_accesos').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });
        $('#lista_cambios').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });
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