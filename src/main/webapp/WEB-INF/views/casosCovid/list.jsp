<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 01/06/2020
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <title></title>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
        <div class="main">
            <!-- Breadcrumb -->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/super/casacaso/" htmlEscape="true "/>"><spring:message code="intensiveMonitoring" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-list-alt"></i> <spring:message code="Monitoreo Covid-19" />
                    </div>
                    <div class="card-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <br/>
                                    <a href="<spring:url value="/covid/SaveForm/" htmlEscape="true"/>" class="btn btn-primary btn-lg">
                                        <i class="fa fa-plus" aria-hidden="true"></i> Agregar Positivo</a>
                                </div>

                                <div class="col-md-12" style="border: 2px solid red">
                                    <br/>
                                    <br/>
                                    <hr/>
                                    <div class="table-responsive">
                                        <table class="table table-hover table-bordered">
                                            <thead>
                                            <tr>
                                                <th width="12%"><spring:message code="house" /></th>
                                                <th width="20%"><spring:message code="logindate" /></th>
                                                <th width="12%"><spring:message code="positive" /></th>
                                                <th width="20%"><spring:message code="fif" /></th>
                                                <th width="20%"><spring:message code="logoutdate" /></th>
                                                <th width="16%"><spring:message code="actions" /></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>2</td>
                                                <td>3</td>
                                                <td>4</td>
                                                <td>5</td>
                                                <td>6</td>
                                            </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>




                            </div>
                        </div>
                    </div>
                </div>
             </div>
        </div>
</div>

</body>
</html>
