<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/css/multi-select.css" var="multiselectCss" />
    <link href="${multiselectCss}" rel="stylesheet" type="text/css"/>
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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/editUser/${user.username}" htmlEscape="true "/>">${user.username}</a>
                </li>
            </ol>
            <spring:url value="/admin/users/saveUser" var="saveUserUrl"/>
            <spring:url value="/admin/users/{username}"
                        var="usuarioUrl">
                <spring:param name="username" value="${user.username}" />
            </spring:url>
            <c:if test="${editando}">
                <c:set var="successMessage"><spring:message code="user.updated" /></c:set>
            </c:if>
            <c:if test="${agregando}">
                <c:set var="successMessage"><spring:message code="user.created" /></c:set>
            </c:if>
            <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <spring:message code="users" />
                            <small>
                                <c:choose>
                                    <c:when test="${agregando}">
                                        <spring:message code="add" />
                                    </c:when>
                                    <c:otherwise>
                                        <spring:message code="edit" />
                                    </c:otherwise>
                                </c:choose>
                            </small>
                        </h3>
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-8">
                                <form action="#" autocomplete="off" id="edit-user-form" class="form-horizontal" style="border: 1px">
                                        <div class="form-group row">
                                            <label class="form-control-label col-md-3" for="username"><spring:message code="username" />
										        <span class="required">*</span>
                                            </label>
                                            <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                                <input id="username" name="username" type="text" readonly value="${user.username}" class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group row password-strength">
                                            <label class="form-control-label col-md-3" for="completeName"><spring:message code="userdesc" />
                                                <span class="required"> * </span>
                                            </label>
                                            <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
                                                <input id="completeName" name="completeName" type="text" value="${user.completeName}" class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="form-control-label col-md-3" for="email"><spring:message code="useremail" />
                                            </label>
                                            <div class="input-group col-md-9">
                                                <span class="input-group-addon">
													<i class="fa fa-envelope"></i>
												</span>
                                                <input id="email" name="email" type="text" value="${user.email}" class="form-control"/>
                                            </div>
                                        </div>
                                    <div id="divPass" class="form-group row password-strength">
                                        <label class="form-control-label col-md-3" for="grpPassword"><spring:message code="login.password" />
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="input-group col-md-9" id="grpPassword">
                                            <span class="input-group-addon">
                                                <i class="fa fa-lock"></i>
                                            </span>
                                            <input id="password" name="password" type="password" class="form-control"/>
                                        </div>
                                        <!--<span class="help-block">
                                            <spring:message code="Pattern.password.format" />
                                        </span>-->
                                    </div>
                                    <div id="divPassRepeat" class="form-group row">
                                        <label class="form-control-label col-md-3" for="confirm_password"><spring:message code="password.repeat" />
                                            <span class="required"> * </span>
                                        </label>
                                        <div class="input-group col-md-9">
                                            <span class="input-group-addon">
                                                <i class="fa fa-lock"></i>
                                            </span>
                                            <input id="confirm_password" name="confirm_password" type="password" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 form-control-label">
                                            <spring:message code="permission" />
                                        </label>
                                        <div class="col-lg-9 col-md-9">
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.muestra}">
                                                        <input type="checkbox" name="chk_muestra" id="chk_muestra" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" name="chk_muestra" id="chk_muestra">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="muestra" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.vacunas}">
                                                        <input type="checkbox" name="chk_vacunas" id="chk_vacunas" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" name="chk_vacunas" id="chk_vacunas">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="vacunas" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.consentimiento}">
                                                        <input type="checkbox" name="chk_consent" id="chk_consent" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" name="chk_consent" id="chk_consent">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="consentimiento" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.recepcion}">
                                                        <input type="checkbox" name="chk_recepcion" id="chk_recepcion" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" name="chk_recepcion" id="chk_recepcion">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="recepcion" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.visitas}">
                                                        <input type="checkbox" name="chk_visitas" id="chk_visitas" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" name="chk_visitas" id="chk_visitas">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="visits" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.obsequio}">
                                                        <input type="checkbox" name="chk_obsequio" id="chk_obsequio" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" name="chk_obsequio" id="chk_obsequio">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="obsequio" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.pesoTalla}">
                                                        <input type="checkbox" name="chk_pesotalla" id="chk_pesotalla" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" name="chk_pesotalla" id="chk_pesotalla">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="pesotalla" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.datosparto}">
                                                        <input type="checkbox" name="chk_datosparto" id="chk_datosparto" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" name="chk_datosparto" id="chk_datosparto">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="datosparto" />
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 form-control-label">
                                            <spring:message code="encuestas" />
                                        </label>
                                        <div class="col-lg-9">
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.encuestaCasa}">
                                                        <input type="checkbox" id="chk_ecasa" name="chk_ecasa" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" id="chk_ecasa" name="chk_ecasa">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="ecasa" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.encuestaParticipante}">
                                                        <input type="checkbox" id="chk_eparticipante" name="chk_eparticipante" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" id="chk_eparticipante" name="chk_eparticipante">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="participants" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.encuestaLactancia}">
                                                        <input type="checkbox" id="chk_elactancia" name="chk_elactancia" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" id="chk_elactancia" name="chk_elactancia">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="elactancia" />
                                            </label>
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.encuestaSatisfaccion}">
                                                        <input type="checkbox" id="chk_esatisfaccion" name="chk_esatisfaccion" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" id="chk_esatisfaccion" name="chk_esatisfaccion">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="esatisfaccion" />
                                            </label>
                                            <!-----ENCUESTA DE PERMITRO ABDOMINAL----->
                                            <label class="checkbox-inline">
                                                <c:choose>
                                                    <c:when test="${permisos.pAbdominal}">
                                                        <input type="checkbox" id="chk_pAbdominal" name="chk_pAbdominal" checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox" id="chk_pAbdominal" name="chk_pAbdominal">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="Abdominal Perimeter" />
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="form-control-label col-md-3" for="authorities"><spring:message code="userroles" />
										<span class="required">
											 *
										</span>
                                        </label>
                                        <div class="input-group col-md-9">
                                            <select multiple="multiple" class="multi-select" id="authorities" name="authorities">
                                                <c:forEach items="${rolesusuario}" var="auth" varStatus="stat">
                                                    <c:set var="rolesUsuario" value="${stat.first ? '' : rolesusuario} ${auth.authId.authority}" />
                                                </c:forEach>
                                                <c:forEach items="${roles}" var="rol">
                                                    <c:choose>
                                                        <c:when test="${fn:contains(rolesUsuario, rol.authority)}">
                                                            <option selected value="${rol.authority}"><spring:message code="${rol.authority}" /></option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${rol.authority}"><spring:message code="${rol.authority}" /></option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="form-control-label col-md-3" for="studies"><spring:message code="userstudies" />
										<span class="required">
											 *
										</span>
                                        </label>
                                        <div class="input-group col-md-9">
                                            <select multiple="multiple" class="multi-select" id="studies" name="studies">
                                                <c:forEach items="${estudiosusuario}" var="study" varStatus="stat">
                                                    <c:set var="estudiosUsuario" value="${stat.first ? '' : estudiosusuario} ${study.estudio.codigo}" />
                                                </c:forEach>
                                                <c:forEach items="${estudios}" var="estudio">
                                                    <c:choose>
                                                        <c:when test="${fn:contains(estudiosUsuario, estudio.codigo)}">
                                                            <option selected value="${estudio.codigo}">${estudio.nombre}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${estudio.codigo}">${estudio.nombre}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-actions fluid">
                                        <div class="col-md-offset-6 col-md-6">
                                            <button id="guardar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right"><spring:message code="save" /></button>
                                            <a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-danger btn-ladda" data-style="expand-right"><spring:message code="cancel" /></a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-2">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.conainer-fluid -->
        </div>
    </div>
    <jsp:include page="../../fragments/bodyFooter.jsp" />
    <jsp:include page="../../fragments/corePlugins.jsp" />
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <!-- jQuery Password Meter-->
    <spring:url value="/resources/js/libs/pwstrength.js" var="pwStrength" />
    <script src="${pwStrength}" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- GenesisUI main scripts -->
    <c:choose>
        <c:when test="${cookie.eIcsLang.value == null}">
            <c:set var="lenguaje" value="es"/>
        </c:when>
        <c:otherwise>
            <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
        </c:otherwise>
    </c:choose>
    <spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
    <script type="text/javascript" src="${selectJs}"></script>
    <spring:url value="/resources/js/libs/jquery.multi-select.js" var="jQueryMultiSelect" />
    <script type="text/javascript" src="${jQueryMultiSelect}"></script>
    <spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
    <script src="${validateJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
    <script src="${validateAMJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/app.js" var="App" />
    <script src="${App}" type="text/javascript"></script>
    <spring:url value="/resources/js/views/users/process-user.js" var="editUserJs" />
    <script src="${editUserJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
    <script src="${loadingButtonsJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <script src="${jQValidationLoc}"></script>
    <script>
        $(function () {
            $("li.administracion").addClass("open");
            $("li.users").addClass("open");
        });
    </script>
    <script>
        jQuery(document).ready(function() {
            var parameters = {saveUserUrl: "${saveUserUrl}",
                usuarioUrl: "${usuarioUrl}",
                successmessage: "${successMessage}",
                error: "${errorProcess}"
            };
            EditUser.init(parameters);
            if (${editando}) {
                $('#divPass').hide();
                $('#divPassRepeat').hide();
                $('#username').prop('readonly', true);
                $('#completeName').focus();
            }
            if (${agregando}) {
                $('#divPass').show();
                $('#divPassRepeat').show();
                $('#username').prop('readonly', false).focus();
            }
        });
    </script>
</body>
</html>