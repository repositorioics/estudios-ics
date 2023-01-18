<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<style>
    .text-bhc {
        color: #debece !important;
    }
    .text-pbmc {
        color: #b294de !important;
    }
    .sidebar-nav li a:hover {
        text-decoration: none;
        color: #fff;
        background: rgba(255, 255, 255, 0.2);
    }
</style>
<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/" htmlEscape="true "/>">
                    <i class="fa fa-tachometer" aria-hidden="true"></i>
                    <spring:message code="dashboard" /></a>
            </li>
            <sec:authorize access="hasRole('ROLE_ROOT')">
                <li class="nav-item nav-dropdown administracion">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-wrench"></i>
                        <strong>  <spring:message code="admin" /> </strong>
                    </a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/admin/users/" htmlEscape="true "/>">
                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                <spring:message code="users" /></a>
                        </li>

                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/admin/personal/list" htmlEscape="true "/>">
                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                <spring:message code="Personal" /></a>
                        </li>

                        <li class="nav-item nav-dropdown administracion">
                            <a class="nav-link nav-dropdown-toggle" href="#">
                                <i class="fa fa-cc" aria-hidden="true"></i>
                                <strong> <spring:message code="Catálogos" /> </strong>
                            </a>
                            <ul class="nav-dropdown-items">
                                <li class="nav-item">
                                    <a class="nav-link" href="<spring:url value="/CatalogoVersion/CrearNuevaVersion" htmlEscape="true "/>">
                                        <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                        <spring:message code="cat.version" /> </a>
                                </li>
                                <li class="nav-item cartas">
                                    <a class="nav-link" href="<spring:url value="/CatalogoParte/CrearNuevaParte/" htmlEscape="true "/>">
                                        <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                        <spring:message code="cat.part" /></a>
                                </li>
                                <li class="nav-item cartas">
                                    <a class="nav-link" href="<spring:url value="/CatalogoVersion/extension/" htmlEscape="true "/>">
                                        <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                        <spring:message code="cat.extension" /></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
            <!--  **  PROCESOS **  -->
            <sec:authorize access="hasAnyRole('ROLE_DIG,ROLE_SUPER,ROLE_LABORATORY,ROLE_DOM')">
                <li class="nav-item nav-dropdown procesos">
                <a class="nav-link nav-dropdown-toggle" href="#">
                    <i class="fa fa-cogs" aria-hidden="true"></i>
                    <strong><spring:message code="processes" /></strong>
                </a>
                <ul class="nav-dropdown-items">
                    <sec:authorize access="hasAnyRole('ROLE_LABORATORY,ROLE_ADMISION,ROLE_DIG,ROLE_SUPER')">
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value="/Registro/BuscarInfor" htmlEscape="true "/>">
                          <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                           <spring:message code="view" /></a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_SUPER')">
                    <li class="nav-item filedata">
                        <a class="nav-link" href="<spring:url value="/reportes/pdf/fileData/" htmlEscape="true "/>">
                            <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                            <spring:message code="report.file.data" /></a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="hasAnyRole('ROLE_DIG,ROLE_SUPER')">
                    <li class="nav-item retiro">
                        <a class="nav-link" href="<spring:url value="/retiro/ListRetiro" htmlEscape="true "/>">
                            <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                            <spring:message code="remove" /></a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_DOM')">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/Domicilio/Listado" htmlEscape="true "/>">
                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                <spring:message code="address" /></a>
                        </li>
                    </sec:authorize>
                </ul>
            </li>
            </sec:authorize>
            <!--  **  CARTAS CONSENTIMIENTOS **  -->
            <sec:authorize access="hasAnyRole('ROLE_DIG,ROLE_SUPER')">
            <li class="nav-item nav-dropdown consentimiento">
                <a class="nav-link nav-dropdown-toggle" href="#">
                    <i class="icon-notebook"></i>
                    <strong>    <spring:message code="letters" /> </strong>
                </a>
                <ul class="nav-dropdown-items">
                    <sec:authorize access="hasRole('ROLE_DIG')">
                        <li class="nav-item nav-dropdown">
                            <a class="nav-link nav-dropdown-toggle" href="#">
                                <i class="fa fa-sign-in" aria-hidden="true"></i>
                                <spring:message code="Ingreso" />
                            </a>
                            <ul class="nav-dropdown-items">
                                <li class="nav-item">
                                    <a class="nav-link" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                        <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                        <spring:message code="consent" /> </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="<spring:url value="/cartas/CartaParticipantTmp" htmlEscape="true "/>">
                                        <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                        <spring:message code="consent" /> <spring:message code="Temporal" /> </a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_SUPER')">
                    <li class="nav-item nav-dropdown">
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="fa fa-cc" aria-hidden="true"></i>
                            <spring:message code="Control Calidad" /></a>
                        <ul class="nav-dropdown-items">

                            <li class="nav-item">
                                <a class="nav-link" href="<spring:url value="/cartas/comparacion" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="Diferencia Cartas" /></a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="<spring:url value="/correction/CorrectionForm" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="corrections" />  <spring:message code="letters" /></a>
                            </li>
                        </ul>
                    </li>

                    <li class="nav-item nav-dropdown">
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="fa fa-cc" aria-hidden="true"></i>
                            <spring:message code="Informe" /></a>
                        <ul class="nav-dropdown-items">
                            <li class="nav-item">
                                <a class="nav-link" href="<spring:url value="/cartas/informacion" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="Productividad" /></a>
                            </li>
                        </ul>
                    </li>
                    </sec:authorize>
                </ul>
            </li>
            </sec:authorize>
            <!-- **  MUESTREO ANUAL  ** -->
            <sec:authorize access="hasAnyRole('ROLE_SUPER,ROLE_LABORATORY')">
            <li class="nav-item nav-dropdown">
                <a class="nav-link nav-dropdown-toggle" href="#">
                    <i class="fa fa-maxcdn" aria-hidden="true"></i>
                    <strong>    <spring:message code="Muestreo Anual" /> </strong>
                </a>
                <ul class="nav-dropdown-items">
                    <sec:authorize access="hasAnyRole('ROLE_LABORATORY,ROLE_SUPER')">
                    <li class="nav-item nav-dropdown">
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="fa fa-tablet" aria-hidden="true"></i>
                            <spring:message code="Aplicación" /></a>
                        <ul class="nav-dropdown-items">
                            <li class="nav-item">
                                <a class="nav-link" href="<spring:url value="/comparacion/muestra" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="sample" /></a>
                            </li>
                        </ul>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="hasAnyRole('ROLE_LABORATORY,ROLE_SUPER')">
                        <li class="nav-item nav-dropdown Laboratorio">
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="fa fa-flask" aria-hidden="true"></i>
                            <spring:message code="Laboratorio" /></a>
                            <ul class="nav-dropdown-items">
                                <!--  ** SEROLOGIA **  -->
                                <li class="nav-item nav-dropdown Serologia">
                                    <a class="nav-link nav-dropdown-toggle" href="#">
                                        <i class="fa fa-tint text-danger" aria-hidden="true"></i>
                                        <spring:message code="Serologia" /></a>
                                    <ul class="nav-dropdown-items">
                                        <li class="nav-item filedata">
                                            <a class="nav-link" href="<spring:url value="/Serologia/listSerologia" htmlEscape="true "/>">
                                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                                <spring:message code="Listado" /></a>
                                        </li>
                                        <li class="nav-item filedata">
                                            <a class="nav-link" href="<spring:url value="/Serologia/listEnviosMuestras" htmlEscape="true "/>">
                                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                                <spring:message code="reports" /> <spring:message code="Envios" /></a>
                                        </li>
                                    </ul>
                                </li>
                                <!--  **  PBMC **  -->
                                <li class="nav-item nav-dropdown Pbmc">
                                    <a class="nav-link nav-dropdown-toggle" href="#">
                                        <i class="fa fa-tint text-pbmc" aria-hidden="true"></i>
                                        <spring:message code="Pbmc" /></a>
                                    <ul class="nav-dropdown-items">
                                        <li class="nav-item">
                                            <a class="nav-link" href="<spring:url value="/Pbmc/listPbmc" htmlEscape="true "/>">
                                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                                <spring:message code="List" /> <spring:message code="Pbmc" /></a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<spring:url value="/Pbmc/listEnviosPbmc" htmlEscape="true "/>">
                                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                                <spring:message code="reports" /> <spring:message code="Pbmc" /></a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<spring:url value="/Pbmc/listSeroConPbmc" htmlEscape="true "/>">
                                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                                <spring:message code="Pbmc Con Serologia" /></a>
                                        </li>
                                    </ul>
                                </li>
                                <!--  **  BHC **  -->
                                <li class="nav-item nav-dropdown">
                                <a class="nav-link nav-dropdown-toggle" href="#">
                                    <i class="fa fa-tint text-bhc" aria-hidden="true"></i>
                                    <spring:message code="Bhc" /></a>
                                <ul class="nav-dropdown-items">
                                    <li class="nav-item">
                                        <a class="nav-link" href="<spring:url value="/Bhc/list" htmlEscape="true "/>">
                                            <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                            <spring:message code="List" /></a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link" href="<spring:url value="/Bhc/EnviadasBhc" htmlEscape="true "/>">
                                            <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                            <spring:message code="reports" /> <spring:message code="Bhc" /></a>
                                    </li>
                                </ul>
                            </li>
                            </ul>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="hasAnyRole('ROLE_SUPER,ROLE_LABORATORY')">
                    <li class="nav-item nav-dropdown">
                            <a class="nav-link nav-dropdown-toggle" href="#">
                                <i class="fa fa-user-secret" aria-hidden="true"></i>
                                <spring:message code="Supervisor" /></a>
                            <ul class="nav-dropdown-items">
                                <li class="nav-item">
                                    <a class="nav-link" href="<spring:url value="/comparacion/bhc" htmlEscape="true "/>">
                                        <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                        <spring:message code="lbl.BHC" /></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="<spring:url value="/comparacion/serologia" htmlEscape="true "/>">
                                        <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                        <spring:message code="lbl.serologia" /></a>
                                </li>
                            </ul>
                        </li>

                        <li class="nav-item nav-dropdown">
                            <a class="nav-link nav-dropdown-toggle" href="#">
                                <i class="fa fa-cc" aria-hidden="true"></i>
                                <spring:message code="Control Calidad" /></a>
                            <ul class="nav-dropdown-items">
                                <li class="nav-item differences">
                                    <a class="nav-link" href="<spring:url value="/comparacion/reporte-diferencias" htmlEscape="true "/>">
                                        <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                        <spring:message code="lbl.differences" /> <spring:message code="M.A" /></a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>
                </ul>
            </li>
            </sec:authorize>
            <!-- ** POSITIVOS ** -->
            <sec:authorize access="hasRole('ROLE_SUPER')">
            <li class="nav-item nav-dropdown comparison">
                <a class="nav-link nav-dropdown-toggle" href="#">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                    <strong><spring:message code="Positivos" /></strong>
                </a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item posUO1">
                        <a class="nav-link" href="<spring:url value="/super/UO1/" htmlEscape="true "/>">
                            <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                            <spring:message code="uo1.positives" /></a>
                    </li>

                    <li class="nav-item nav-dropdown">
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                            <spring:message code="T. Influenza" /></a>
                        <ul class="nav-dropdown-items">

                            <li class="nav-item intensiveMonitoring">
                                <a class="nav-link" href="<spring:url value="/super/casacaso/" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="intensiveMonitoring" /></a>
                            </li>

                            <li class="nav-item chfcasossearch">
                                <a class="nav-link" href="<spring:url value="/chf/editarcaso/" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="chfcasossearch" /></a>
                            </li>
                            <li class="nav-item visitsIM">
                                <a class="nav-link" href="<spring:url value="/reportes/super/visitas/" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="visits" /></a>
                            </li>
                        </ul>
                    </li>

                    <li class="nav-item nav-dropdown">
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                            <spring:message code="T. Covid" /></a>
                        <ul class="nav-dropdown-items">
                            <li class="nav-item candCovid19">
                                <a class="nav-link" href="<spring:url value="/super/covid/listCandidates/" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="covid19.candidates" /></a>
                            </li>
                            <li class="nav-item posCovid19">
                                <a class="nav-link" href="<spring:url value="/covid/listCovid/" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="covid19.positives" /></a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            </sec:authorize>
            <!-- ** HEMODINAMICA ** -->
            <sec:authorize access="hasRole('ROLE_HEMODINAMICA')">
                <li class="nav-item nav-dropdown hemodinamica">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-sticky-note-o"  aria-hidden="true"></i>
                        <strong><spring:message code="Hemodinamica" /></strong>
                    </a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                <spring:message code="List" /></a>
                        </li>
                        <sec:authorize access="hasRole('ROLE_SUPER')">
                            <li class="nav-item reporte">
                                <a class="nav-link" href="<spring:url value="/hemo/reporteHemodinamica" htmlEscape="true "/>">
                                    <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                    <spring:message code="reports" /></a>
                            </li>
                        </sec:authorize>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ENTO')">
                <li class="nav-item nav-dropdown cuestionarios">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-bug" aria-hidden="true"></i>
                        <strong><spring:message code="lbl.ento" /></strong></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/ento/informacion" htmlEscape="true "/>">
                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                <spring:message code="lbl.letters.info" /> </a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/logout" htmlEscape="true" />">
                    <i class="icon-lock"></i>
                    <strong><spring:message code="logout" /></strong>
                </a>
            </li>
        </ul>
    </nav>
</div>