<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/" htmlEscape="true "/>"><i class="icon-speedometer"></i><spring:message code="dashboard" /></a>
            </li>
            <sec:authorize access="hasRole('ROLE_ROOT')">
            <li class="nav-item nav-dropdown administracion">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-wrench"></i><spring:message code="admin" /></a>
	            <ul class="nav-dropdown-items">
	                <li class="nav-item users">
	                    <a class="nav-link" href="<spring:url value="/admin/users/" htmlEscape="true "/>"><i class="icon-people"></i><spring:message code="users" /></a>
	                </li>
                    <!--<li class="nav-item versionLetters">
                        <a class="nav-link" href="<spring:url value="/admin/vcartas/" htmlEscape="true "/>"><i class="icon-docs"></i><spring:message code="versionLetters" /></a>
                    </li> -->
	            </ul>
	        </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_SUPER')">
                <li class="nav-item nav-dropdown chfcasos">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-notebook"></i><spring:message code="chfcasos" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item chfcasossearch">
                            <a class="nav-link" href="<spring:url value="/chf/editarcaso/" htmlEscape="true "/>"><i class="icon-home"></i><spring:message code="chfcasossearch" /></a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item nav-dropdown supervision">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-eye"></i><spring:message code="superv" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item intensiveMonitoring">
                        <a class="nav-link" href="<spring:url value="/super/casacaso/" htmlEscape="true "/>"><i class="icon-location-pin"></i><spring:message code="intensiveMonitoring" /></a>
                    </li>
                    <li class="nav-item visitsIM">
                        <a class="nav-link" href="<spring:url value="/reportes/super/visitas/" htmlEscape="true "/>"><i class="icon-list"></i><spring:message code="visits" /></a>
                    </li>
                    <li class="nav-item posUO1">
                        <a class="nav-link" href="<spring:url value="/super/UO1/" htmlEscape="true "/>"><i class="icon-user-follow"></i><spring:message code="uo1.positives" /></a>
                    </li>
                    <li class="nav-item posCovid19">
                        <a class="nav-link" href="<spring:url value="/covid/listCovid/" htmlEscape="true "/>"><i class="icon-user-follow"></i><spring:message code="covid19.positives" /></a>
                    </li>
                </ul>
            </li>

                <!--     <li class="nav-item nav-dropdown supervision">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="fa fa-home"></i><spring:message code="Casas" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item intensiveMonitoring">
                            <a class="nav-link" href="<spring:url value="/CrearCasa/CrearCasa" htmlEscape="true "/>"><i class="fa fa-plus-circle"></i><spring:message code="Crear Casa" /></a>
                        </li>
                    </ul>
                </li>-->
            </sec:authorize>
            <!--<li class="nav-item nav-dropdown laboratory">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-chemistry"></i><spring:message code="laboratory" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item printcodes">
                        <a class="nav-link" href="<spring:url value="/lab/muestras/" htmlEscape="true "/>"><i class="icon-printer"></i><spring:message code="print.codes" /></a>
                    </li>
                </ul>
            </li>-->

            <li class="nav-item nav-dropdown reports">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-docs"></i><spring:message code="reports" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item filedata">
                        <a class="nav-link" href="<spring:url value="/reportes/pdf/fileData/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="report.file.data" /></a>
                    </li>
                </ul>
            </li>

            <sec:authorize access="hasAnyRole('ROLE_DIG','ROLE_ADMIN')">
                <li class="nav-item nav-dropdown hemodinamica">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-sticky-note-o"  aria-hidden="true"></i>
                        <spring:message code="Hemodinamica" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
                                <i class="fa fa-list"></i>
                                <spring:message code="Listado" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_DOM')">
            <li class="nav-item nav-dropdown Domicilio">
                <a class="nav-link nav-dropdown-toggle" href="#">
                    <i class="fa fa-home" aria-hidden="true"></i>
                    <spring:message code="Cambios Domicilio" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value="/Domicilio/Listado" htmlEscape="true "/>">
                            <i class="fa fa-bank" aria-hidden="true"></i>
                            <spring:message code="Domicilios" /></a>
                    </li>
                </ul>
            </li>
            </sec:authorize>
	        <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/logout" htmlEscape="true" />"><i class="icon-lock"></i><spring:message code="logout" /></a>
            </li>
        </ul>
    </nav>
</div>