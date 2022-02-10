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
                            <a class="nav-link" href="<spring:url value="/admin/users/" htmlEscape="true "/>">
                                <i class="icon-people">
                                </i><spring:message code="users" /></a>
                        </li>
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/admin/personal/list" htmlEscape="true "/>">
                                <i class="icon-people">
                                </i><spring:message code="Personal" /></a>
                        </li>
                        <li class="nav-item casas">
                            <a class="nav-link" href="<spring:url value="/casas/obtenerCasas/" htmlEscape="true "/>">
                                <i class="fa fa-home" ></i>
                                <spring:message code="ecasa.Pediatric" /></a>
                        </li>
                        <li class="nav-item casas">
                        <a class="nav-link" href="<spring:url value="/casasfamilia/getCasasFamilia/" htmlEscape="true "/>">
                            <i class="fa fa-university" aria-hidden="true"></i>
                            <spring:message code="ecasa.Family" /></a>
                    </li>

                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/CatalogoVersion/CrearNuevaVersion" htmlEscape="true "/>">
                                <i class="fa fa-vimeo" aria-hidden="true"></i>
                                <spring:message code="cat.version" /> </a>
                        </li>

                        <li class="nav-item cartas">
                            <a class="nav-link" href="<spring:url value="/CatalogoParte/CrearNuevaParte/" htmlEscape="true "/>">
                                <i class="fa fa-product-hunt" aria-hidden="true"></i>
                                <spring:message code="cat.part" /></a>
                        </li>
                        <li class="nav-item cartas">
                            <a class="nav-link" href="<spring:url value="/CatalogoVersion/extension/" htmlEscape="true "/>">
                                <i class="fa fa-eur" aria-hidden="true"></i>
                                <spring:message code="cat.extension" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_SUPER')">
                <li class="nav-item nav-dropdown comparison">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-clipboard" aria-hidden="true"></i>
                        <spring:message code="comparison" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/comparacion/bhc" htmlEscape="true "/>">
                                <i class="fa fa-tint" aria-hidden="true"></i>
                                <spring:message code="lbl.BHC" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/comparacion/serologia" htmlEscape="true "/>">
                                <i class="fa fa-flask" aria-hidden="true"></i>
                                <spring:message code="lbl.serologia" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/comparacion/muestra" htmlEscape="true "/>">
                                <i class="fa fa-tint" aria-hidden="true"></i>
                                <spring:message code="sample" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_DIG')">
                <li class="nav-item nav-dropdown consentimiento">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-notebook"></i><spring:message code="letters" /></a>
                    <ul class="nav-dropdown-items">

                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                <i class="fa fa-file-text-o" aria-hidden="true"></i>
                                <spring:message code="consent" /> </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/cartas/CartaParticipantTmp" htmlEscape="true "/>">
                                <i class="fa fa-file-text-o" aria-hidden="true"></i>
                                <spring:message code="consent" /> <spring:message code="Temporal" /> </a>
                        </li>
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
                        <li class="nav-item candCovid19">
                            <a class="nav-link" href="<spring:url value="/super/covid/listCandidates/" htmlEscape="true "/>"><i class="icon-user-following"></i><spring:message code="covid19.candidates" /></a>
                        </li>
                        <li class="nav-item posCovid19">
                            <a class="nav-link" href="<spring:url value="/covid/listCovid/" htmlEscape="true "/>"><i class="fa fa-user-plus"></i><spring:message code="covid19.positives" /></a>
                        </li>
                    </ul>
                </li>

            </sec:authorize>
            <!--<li class="nav-item nav-dropdown laboratory">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-chemistry"></i><spring:message code="laboratory" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item printcodes">
                        <a class="nav-link" href="<spring:url value="/lab/muestras/" htmlEscape="true "/>"><i class="icon-printer"></i><spring:message code="print.codes" /></a>
                    </li>
                </ul>
            </li>-->


            <sec:authorize access="hasRole('ROLE_ADMISION')">
            <li class="nav-item nav-dropdown procesos">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="fa fa-retweet" aria-hidden="true"></i>
                    <spring:message code="processes" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value="/Registro/BuscarInfor" htmlEscape="true "/>">
                            <i class="fa fa-newspaper-o" aria-hidden="true"></i>
                            <spring:message code="view" /></a>
                    </li>
                </ul>
            </li>
            </sec:authorize>


            <sec:authorize access="hasAnyRole('ROLE_DIG')">
                <li class="nav-item nav-dropdown reports">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-docs"></i><spring:message code="reports" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/reportes/pdf/fileData/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="report.file.data" /></a>
                        </li>
                    </ul>
                </li>

                <li class="nav-item nav-dropdown hemodinamica">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-sticky-note-o"  aria-hidden="true"></i>
                        <spring:message code="Hemodinamica" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
                                <i class="fa fa-list"></i>
                                <spring:message code="List" /></a>
                        </li>
                    </ul>
                </li>
                <!-- <li class="nav-item">
                    <a class="nav-link" href="<spring:url value="/reactivacion/ListaReactivados" htmlEscape="true "/>">
                        <i class="fa fa-user-plus" aria-hidden="true"></i>
                        <spring:message code="Reactivar" /> </a>
                </li>

                <li class="nav-item nav-dropdown">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-user-plus" aria-hidden="true"></i>
                        <spring:message code="Reactivar" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/reactivacion/ListaReactivados" htmlEscape="true "/>">
                                <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
                                <spring:message code="Reactivar" /> </a>
                        </li>
                    </ul>
                </li> -->
                <li class="nav-item nav-dropdown">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-user-times" aria-hidden="true"></i>
                        <spring:message code="retirement" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item retiro">
                            <a class="nav-link" href="<spring:url value="/retiro/ListRetiro" htmlEscape="true "/>">
                                <i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
                                <spring:message code="remove" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_DIG','ROLE_ADMIN')">
                <li class="nav-item nav-dropdown Serologia">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-ambulance"  aria-hidden="true"></i>
                        <spring:message code="Serologia" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/Serologia/listSerologia" htmlEscape="true "/>">
                                <i class="fa fa-list"></i>
                                <spring:message code="Listado" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/Serologia/listEnviosMuestras" htmlEscape="true "/>">
                                <i class="fa fa-send"></i>
                                <spring:message code="Envios" /></a>
                        </li>
                    </ul>
                </li>
        </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_DIG','ROLE_ADMIN')">
                <li class="nav-item nav-dropdown Pbmc">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-flask" aria-hidden="true"></i>
                        <spring:message code="Pbmc" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/Pbmc/listEnviosPbmc" htmlEscape="true "/>">
                                <i class="fa fa-send"></i>
                                <spring:message code="Envios" /> <spring:message code="Pbmc" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/Pbmc/listPbmc" htmlEscape="true "/>">
                                <i class="fa fa-list" aria-hidden="true"></i>
                                <spring:message code="List" /> <spring:message code="Pbmc" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>


            <sec:authorize access="hasRole('ROLE_ROOT')">
                <li class="nav-item nav-dropdown Domicilio">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-home" aria-hidden="true"></i>
                        <spring:message code="change.address" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/Domicilio/Listado" htmlEscape="true "/>">
                                <i class="fa fa-bank" aria-hidden="true"></i>
                                <spring:message code="address" /></a>
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


