<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="app-header navbar">
    <button class="navbar-toggler mobile-sidebar-toggler hidden-lg-up" type="button">☰</button>
    <a class="navbar-brand" href="#"></a>
    <ul class="nav navbar-nav hidden-md-down float-left">
        <li class="nav-item">
            <a class="nav-link navbar-toggler sidebar-toggler" href="#">☰</a>
        </li>
    </ul>
    <ul class="nav navbar-nav ml-auto">
        <!-- BEGIN USER LOGIN DROPDOWN -->
        <li class="nav-item dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                <i class="fa fa-user"></i>
                    <span class="username">
                         <sec:authentication property="principal.username" />
                    </span>
            </a>
            <ul class="dropdown-menu dropdown-menu-right">
                <li class="dropdown-item">
                    <a href="<spring:url value="/users/profile" htmlEscape="true" />"><i class="fa fa-user"></i> <spring:message code="profile" /></a>
                </li>
                <li class="dropdown-item">
                    <a href="<spring:url value="/users/chgpass" htmlEscape="true" />"><i class="fa fa-key"></i> <spring:message code="changepass" /></a>
                </li>
                <li class="dropdown-item">
                    <a href="<spring:url value="/logout" htmlEscape="true" />"><i class="fa fa-sign-out"></i> <spring:message code="logout" /></a>
                </li>
            </ul>
        </li>
        <!-- END USER LOGIN DROPDOWN -->
     </ul>
</header>