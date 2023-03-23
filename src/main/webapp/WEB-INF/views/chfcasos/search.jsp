<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/chf/editarcaso/" htmlEscape="true "/>"><spring:message code="chfcasossearch" /></a>
                </li>
            </ol>
            <div class="container-fluid">
            	<div class="animated fadeIn">
                    <div class="card">
                        <div class="card-header">
                            <i class="fa fa-search"></i> <spring:message code="housesearch" />

                        </div>
                        <div class="card-block">
                        	<form action="#" id="select-house-form" autocomplete="off" class="form-horizontal ">
                        	<div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label class="form-control-label" for="parametro"><spring:message code="please.enter"/> <spring:message code="parameter"/></label>
                                        <input type="text" class="form-control" id="parametro" name="parametro" placeholder="<spring:message code="please.enter"/> <spring:message code="parameter"/>">
                                    </div>
                                </div>
                            </div>
                            </form>
                            <table class="table table-striped table-bordered datatable" id="tabla_casas">
                                <thead>
                                    <tr>
                                        <th><spring:message code="code" /></th>
		                                <th><spring:message code="familyChief" /></th>
		                                <th><spring:message code="initDate" /></th>
		                                <th><spring:message code="actions" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
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
	
	<c:set var="lenguaje" value="es"/>
	
	<!-- Plugins and scripts required by this views -->
	<spring:url value="/resources/js/libs/jquery.dataTables.min.js" var="dataTableJs" />
	<script src="${dataTableJs}" type="text/javascript"></script>

	<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
	<script type="text/javascript" src="${dataTablesBS}"></script>
	
	<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
	    <spring:param name="language" value="${lenguaje}" />
	</spring:url>
	
	<spring:url value="/resources/js/libs/jquery.validate.js" var="jqValidation" />
	<script src="${jqValidation}" type="text/javascript"></script>
	
	<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
	    <spring:param name="language" value="${lenguaje}" />
	</spring:url>
	<script src="${jQValidationLoc}"></script>

	
	<spring:url value="/chf/editarcaso/casas" var="casasUrl"/>
	<spring:url value="/chf/editarcaso/participants" var="partsUrl"/>
	<c:set var="notFound"><spring:message code="noResults" /></c:set>

	
	<!-- Custom scripts required by this view -->
	<spring:url value="/resources/scripts/casas/search-casa.js" var="casaScript" />
	<script src="${casaScript}" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {			
			var parametros = {casasUrl: "${casasUrl}",
				partsUrl: "${partsUrl}",
				notFound: "${notFound}",
				dataTablesLang: "${dataTablesLang}"};
			SearchCasa.init(parametros);
		});
	</script>
</body>
</html>