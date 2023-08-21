<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.time.LocalDate"%>
<footer class="app-footer"><!-- yyyy/mm/dd-->
    2017 - ${LocalDate.now().year} &copy; <a href="http://sustainablesciences.org/"><spring:message code="footer"/>.</a> v6.4.1-22-09-09
    <span class="float-right">
        
    </span>
</footer>