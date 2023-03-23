<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <i class="fa fa-group"></i> <spring:message code="intensiveMonitoring" /> <spring:message code="house" />
                    									<c:out value="${participante.codigoCaso.casa.codigoCHF}" />
                        </div>
                        <div class="card-block">
                        	<form action="" method="post" enctype="multipart/form-data" class="form-horizontal ">
                        		<div class="form-group row">
	                                <label class="col-md-3 form-control-label"><spring:message code="code" /></label>
	                                <div class="col-md-9">
	                                    <p class="form-control-static"><c:out value="${participante.participante.participante.codigo}"/></p>
	                                </div>
	                            </div>
	                            <div class="form-group row">
	                                <label class="col-md-3 form-control-label"><spring:message code="participant" /></label>
	                                <div class="col-md-9">
	                                    <p class="form-control-static"><c:out value="${participante.participante.participante.nombreCompleto}"/></p>
	                                </div>
	                            </div>
                            </form>
                            <spring:url value="/chf/editarcaso/participants/{codigo}/" var="partsUrl">
                                <spring:param name="codigo" value="${participante.codigoCaso.codigoCaso}" />
                            </spring:url>
                            <spring:url value="/chf/editarcaso/newvisit/{codigo}/" var="newVisitUrl">
                                <spring:param name="codigo" value="${participante.codigoCasoParticipante}" />
                            </spring:url>
                            <table class="table table-striped table-bordered datatable">
                                <thead>
                                    <tr>
                                        <th><spring:message code="visit" /></th>
		                                <th><spring:message code="visitDate" /></th>
		                                <th><spring:message code="visitHour" /></th>
		                                <th><spring:message code="visitExp" /></th>
		                                <th><spring:message code="visitTemp" /></th>
		                                <th><spring:message code="actions" /></th>
                                    </tr>
                                </thead>
                                <tbody>
	                                <c:forEach items="${visitas}" var="visita">
	                                	<spring:url value="/chf/editarcaso/visitdata/{codigo}/" var="visitUrl">
		                                    <spring:param name="codigo" value="${visita.codigoCasoVisita}" />
		                                </spring:url>
		                                <spring:url value="/chf/editarcaso/editVisit/{codigoCasoVisita}/" var="editUrl">
		                                    <spring:param name="codigoCasoVisita" value="${visita.codigoCasoVisita}" />
		                                </spring:url>
		                                <spring:url value="/chf/editarcaso/voidVisit/disable/{codigoCasoVisita}" var="voidUrl">
		                                    <spring:param name="codigoCasoVisita" value="${visita.codigoCasoVisita}" />
		                                </spring:url>
		                                <spring:url value="/chf/editarcaso/voidVisit/enable/{codigoCasoVisita}" var="unvoidUrl">
		                                    <spring:param name="codigoCasoVisita" value="${visita.codigoCasoVisita}" />
		                                </spring:url>
		                                <fmt:parseNumber var = "anulada" type = "number" value = "${visita.pasive}" />
		                                
		                                <tr>
		                                    <td><c:out value="${visita.visita}" /></td>
		                                    <td><fmt:formatDate value="${visita.fechaVisita}" pattern="dd/MM/yyyy HH:mm" /></td>
		                                    <td><c:out value="${visita.horaProbableVisita}" /></td>
		                                    <c:choose>
		                                        <c:when test="${visita.expCS eq 'S'}">
		                                            <td><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
		                                        </c:when>
		                                        <c:otherwise>
		                                            <td><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
		                                        </c:otherwise>
		                                    </c:choose>
		                                    <td><c:out value="${visita.temp}" /></td>
		                                    <td><a href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
		                                    <c:choose>
	                                            <c:when test="${anulada < 1}">
	                                                <a href="" data-toggle="modal" data-id= "${fn:escapeXml(voidUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash"></i></a>
	                                            </c:when>
	                                            <c:otherwise>
	                                                <a data-toggle="modal" data-id= "${fn:escapeXml(unvoidUrl)}" class="btn btn-outline-primary btn-sm act"><i class="fa fa-check"></i></a>
	                                            </c:otherwise>
	                                        </c:choose>
		                                    <a href="${fn:escapeXml(visitUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-clone"></i></a></td>
		                                </tr>
		                            </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <a href="${fn:escapeXml(newVisitUrl)}" class="btn btn-sm btn-success"><i class="fa fa-plus"></i> <spring:message code="add" /> <spring:message code="visit" /></a>
                            <a href="${fn:escapeXml(partsUrl)}" class="btn btn-sm btn-primary"><i class="fa fa-mail-reply"></i> <spring:message code="back" /></a>
                        </div>
                    </div>
                    <div class="row">
	                    <div class="col-sm-12">
		                    <div class="card">
		                        <div class="card-header">
		                            <i class="fa fa-group"></i> <spring:message code="failVisit" />
		                        </div>
		                        <div class="card-block">
		                            <spring:url value="/chf/editarcaso/participants/{codigo}/" var="partsUrl">
		                                <spring:param name="codigo" value="${participante.codigoCaso.codigoCaso}" />
		                            </spring:url>
		                            <spring:url value="/chf/editarcaso/newfailvisitpart/{codigo}" var="newFailVisitUrl">
		                                <spring:param name="codigo" value="${participante.codigoCasoParticipante}" />
		                            </spring:url>
		                            <table class="table table-striped table-bordered datatable">
		                                <thead>
		                                    <tr>
                                                <th><spring:message code="visit" /></th>
				                                <th><spring:message code="visitDate" /></th>
				                                <th><spring:message code="razonVisitaFallida" /></th>
				                                <th><spring:message code="otraRazon" /></th>
				                                <th><spring:message code="actions" /></th>
		                                    </tr>
		                                </thead>
		                                <tbody>
			                                <c:forEach items="${fallidas}" var="fallida">
				                                <spring:url value="/chf/editarcaso/editfailvisit/{codigoFallaVisita}/" var="editUrl">
				                                    <spring:param name="codigoFallaVisita" value="${fallida.codigoFallaVisita}" />
				                                </spring:url>
				                                <spring:url value="/chf/editarcaso/voidFailVisit/disable/{codigoFallaVisita}" var="voidUrl">
				                                    <spring:param name="codigoFallaVisita" value="${fallida.codigoFallaVisita}" />
				                                </spring:url>
				                                <tr>
                                                    <td><c:out value="${fallida.visita}" /></td>
				                                    <td><fmt:formatDate value="${fallida.fechaVisita}" pattern="dd/MM/yyyy HH:mm" /></td>
				                                    <td><c:out value="${fallida.razonVisitaFallida}" /></td>
				                                    <td><c:out value="${fallida.otraRazon}" /></td>
				                                    <td><a href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
				                                    <a href="" data-toggle="modal" data-id= "${fn:escapeXml(voidUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash"></i></a></td>
				                                </tr>
				                            </c:forEach>
		                                </tbody>
		                            </table>
		                        </div>
		                        <div class="card-footer">
		                            <a href="${fn:escapeXml(newFailVisitUrl)}" class="btn btn-sm btn-warning"><i class="fa fa-plus"></i> <spring:message code="add" /> <spring:message code="failVisit" /></a>
		                            <a href="${fn:escapeXml(partsUrl)}" class="btn btn-sm btn-primary"><i class="fa fa-mail-reply"></i> <spring:message code="back" /></a>
		                        </div>
		                    </div>
	                    </div>
                    </div>
                    <div class="row">
	                    <div class="col-sm-12">
		                    <div class="card">
		                        <div class="card-header">
		                            <i class="fa fa-group"></i> <spring:message code="visitaFinal" />
		                        </div>
	                            <spring:url value="/chf/editarcaso/visfinal/{codigoCasoParticipante}/" var="editVisitFinalUrl">
	                                <spring:param name="codigoCasoParticipante" value="${participante.codigoCasoParticipante}" />
	                            </spring:url>
		                        <div class="card-block">
		                        	<c:choose>
                                        <c:when test="${visFinal.fechaVisita != null}">
                                            <div class="form-group row">
				                                <div class="form-group col-sm-6">
		                                            <label class="form-control-label"><spring:message code="visitDate" /></label>
		                                            <p class="form-control-static"><c:out value="${visFinal.fechaVisita}"/></p>
		                                        </div>
		                                        <div class="form-group col-sm-6">
		                                            <label class="form-control-label"><spring:message code="enfermo" /></label>
		                                            <p class="form-control-static"><c:out value="${visFinal.enfermo}"/></p>
		                                        </div>
			                            	</div>
			                            	<div class="form-group row">
				                                <div class="form-group col-sm-3">
		                                            <label class="form-control-label"><spring:message code="fiebre" /></label>
		                                            <p class="form-control-static"><c:out value="${visFinal.fiebre}"/></p>
		                                        </div>
		                                        <div class="form-group col-sm-3">
		                                            <label class="form-control-label"><spring:message code="tos" /></label>
		                                            <p class="form-control-static"><c:out value="${visFinal.tos}"/></p>
		                                        </div>
		                                        <div class="form-group col-sm-3">
		                                            <label class="form-control-label"><spring:message code="dolorGarganta" /></label>
		                                            <p class="form-control-static"><c:out value="${visFinal.dolorGarganta}"/></p>
		                                        </div>
		                                        <div class="form-group col-sm-3">
		                                            <label class="form-control-label"><spring:message code="secrecionNasal" /></label>
		                                            <p class="form-control-static"><c:out value="${visFinal.secrecionNasal}"/></p>
		                                        </div>
			                            	</div>
			                            	<spring:url value="/chf/editarcaso/newsamp/{codigoCasoVisita}/2/" var="newSampUrl">
				                                <spring:param name="codigoCasoVisita" value="${visFinal.codigoParticipanteCaso.codigoCasoParticipante}" />
				                            </spring:url>
			                            	<table class="table table-striped table-bordered">
				                               <thead>
				                                   <tr>
					                                <th><spring:message code="tipoMuestra" /></th>
					                                <th><spring:message code="tubo" /></th>
					                                <th><spring:message code="tomaMxSn" /></th>
					                                <th><spring:message code="volumen" /></th>
					                                <th></th>
				                                   </tr>
				                               </thead>
				                               <tbody>
				                                <c:forEach items="${muestras}" var="muestra">
					                                <spring:url value="/chf/editarcaso/editSamp/{codigoCasoVisita}/{codigo}/2/" var="sampleUrl">
					                                	<spring:param name="codigoCasoVisita" value="${visFinal.codigoParticipanteCaso.codigoCasoParticipante}" />
					                                    <spring:param name="codigo" value="${muestra.codigo}" />
					                                </spring:url>
					                                <spring:url value="/chf/editarcaso/voidSamp/{codigoCasoVisita}/{codigo}/2/" var="voidUrl">
					                                	<spring:param name="codigoCasoVisita" value="${visFinal.codigoParticipanteCaso.codigoCasoParticipante}" />
					                                    <spring:param name="codigo" value="${muestra.codigo}" />
					                                </spring:url>
					                                <tr>
					                                    <td><c:out value="${muestra.tipoMuestra}" /></td>
					                                    <td><c:out value="${muestra.tubo}" /></td>
					                                    <td><c:out value="${muestra.tomaMxSn}" /></td>
					                                    <td><c:out value="${muestra.volumen}" /></td>
					                                    <td><a href="${fn:escapeXml(sampleUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
					                                    <a href="" data-toggle="modal" data-id= "${fn:escapeXml(voidUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash"></i></a></td>
					                                </tr>
					                            </c:forEach>
				                               </tbody>
				                           </table>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="form-group row">
                                            	<label class="form-control-label"><spring:message code="visitaFinal" /> <spring:message code="pend" /></label>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
	                        		
		                        </div>
		                        <div class="card-footer">
		                        	<a href="${fn:escapeXml(editVisitFinalUrl)}" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> <spring:message code="visitaFinal" /></a>
		                        	<c:choose>
			                        	<c:when test="${visFinal.fechaVisita != null}">
			                        		<a href="${fn:escapeXml(newSampUrl)}" class="btn btn-sm btn-success"><i class="fa fa-plus"></i> <spring:message code="add" /> <spring:message code="sample" /></a>
			                        	</c:when>
		                        	</c:choose>
		                        </div>
		                    </div>
	                    </div>
                    </div>
                </div>
            	
            </div>
            <!-- /.conainer-fluid -->
            <div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
                            <div id="titulo"></div>
                        </div>
                        <div class="modal-body">
                            <input type=hidden id="accionUrl"/>
                            <div id="cuerpo"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                            <button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>
    </div>
    <jsp:include page="../fragments/bodyFooter.jsp" />
    <jsp:include page="../fragments/corePlugins.jsp" />
    <c:set var="processSuccess"><spring:message code="process.success" /></c:set>
    <c:set var="confirmar"><spring:message code="confirm" /></c:set>
    <c:set var="deshabilitar"><spring:message code="delete" /></c:set>
    <!-- GenesisUI main scripts -->
	<spring:url value="/resources/js/app.js" var="App" />
	<script src="${App}" type="text/javascript"></script>
	<script type="text/javascript">
    if ("${visitaDeshabilitado}"){
        toastr.success("${processSuccess}");
    }
	$(".desact").click(function(){
        $('#accionUrl').val($(this).data('id'));
        $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
        $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+$(this).data('id').substr($(this).data('id').lastIndexOf("/")+1)+'?</h3>');
        $('#basic').modal('show');
    });
    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
	</script>
</body>
</html>