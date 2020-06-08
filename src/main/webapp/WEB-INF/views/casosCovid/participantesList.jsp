<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 04/06/2020
  Time: 14:34
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
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-list-alt"></i> <spring:message code="Transmisión Covid" /> <spring:message code="house" />
                </div>
                <div class="card-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <br>
                                <br>
                                    <spring:url value="/covid/listCovid/" var="listUrl"/>
                                    <button id="new_version" onclick="location.href='${fn:escapeXml(listUrl)}'" class="btn btn-info btn-lg">
                                        <spring:message code="cases" /> <i class="fa fa-reply"></i>
                                    </button>
                                <br>
                                <br>
                            </div>
                        </div>
                    </div>

                <div class="container">
                    <div class="col-md-12">
                        <table class="table table-striped table-bordered" id="lista_participantes">
                            <thead>
                            <tr>
                                <th><spring:message code="code" /></th>
                                <th><spring:message code="participant" /></th>
                                <th><spring:message code="age" /></th>
                                <th><spring:message code="positive" /></th>
                                <th><spring:message code="fif" /></th>
                                <th><spring:message code="actions" /></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${participantes}" var="parti">
                                <tr>
                                    <c:set var="edadParts" value="${fn:split(parti.participante.participante.edad, '/')}" />
                                    <td>${parti.participante.participante.codigo}</td>
                                    <td>${parti.participante.participante.nombreCompleto}</td>
                                    <td> <c:out value="${edadParts[0]} años ${edadParts[1]} meses ${edadParts[2]} dias" /> </td>
                                    <c:choose>
                                        <c:when test="${parti.enfermo eq 'S'}">
                                            <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td> <fmt:formatDate value="${parti.fis}" pattern="dd/MM/yyyy" /> </td>
                                    <td><a  class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a></td>
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
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
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
    $(document).ready(function(){
        $("#lista_participantes").DataTable();
    })
</script>
</body>
</html>