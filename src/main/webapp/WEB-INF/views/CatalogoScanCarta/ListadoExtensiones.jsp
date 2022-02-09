<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 14/10/2021
  Time: 14:32
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

    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
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
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="card">
                    <div class="card-header">
                       <h4>   <spring:message code="List" /> <spring:message code="Extension" />  </h4>
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom"
                                   title="<spring:message code="back.list"/>"
                                   href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                    <i class="fa fa-list-ul" aria-hidden="true"></i>
                                    <spring:message code="List"/>
                                </a>
                            </div>
                        </div>
                        <hr/>
                        <div class="table-responsive">
                            <table id="tblextension" class="table table-hover table-bordered" style="width: 100%">
                                <thead>
                                <tr>
                                    <th data-class="expand" class="text-center"><spring:message code="code" /></th>
                                    <th class="text-center"><span><spring:message code="dateAdded" /></span></th>
                                    <th class="text-center"><span>ID_Extension</span></th>
                                    <th class="text-center"><span><spring:message code="Extension" /></span></th>
                                    <th class="text-center"><spring:message code="lbl.tutor" /></th>
                                    <th class="text-center"><spring:message code="observacion" /></th>
                                    <th class="text-center"><span><spring:message code="actions" /></span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${participanteExtensionObj}" var="ex">
                                    <spring:url value="/cartas/edit/{idParticipantExtension}" var="editUrl">
                                        <spring:param name="idParticipantExtension" value="${ex.idParticipantExtension}"/>
                                    </spring:url>
                                    <tr>
                                        <td class="text-center"><c:out value="${ex.idParticipantExtension}"/></td>
                                        <td class="text-center"><fmt:formatDate value="${ex.fechaExtension}" pattern="dd/MM/yyyy"/></td>
                                        <td>${ex.extensiones.id}</td>
                                        <td>${ex.extensiones.extension}</td>
                                        <td>${ex.nombre1Tutor} ${ex.nombre2Tutor} ${ex.apellido1Tutor} ${ex.apellido2Tutor}</td>
                                        <td>${ex.observacion}</td>
                                        <td class="text-center">
                                            <a title="<spring:message code="edit" />" href="${fn:escapeXml(editUrl)}" class="btn btn-primary btn-sm"><i class="fa fa-edit"></i></a>
                                            <a title="<spring:message code="disable"/>" href="${fn:escapeXml(delUrl)}" class="btn btn-danger btn-sm deleteExt"><i aria-hidden="true" class="fa fa-trash-o"></i></a>
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
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
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
        var table = $('#tblextension').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });
    });
</script>
</body>
</html>