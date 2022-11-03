<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/dtresponsive/twitter-bootstrap.css" var="boot1" />
    <link href="${boot1}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
    <link rel="stylesheet" href="${bdt}" type="text/css"/>

    <spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
    <link rel="stylesheet" href="${bdat4}" type="text/css"/>

    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

    <spring:url value="/resources/css/dataTable.1.12/jquery.dataTables.min.css" var="jqueryDataTablecss" />
    <link rel="stylesheet" href="${jqueryDataTablecss}"/>

    <spring:url value="/resources/css/dataTable.1.12/select.dataTables.min.css" var="selectDataTablecss" />
    <link rel="stylesheet" href="${selectDataTablecss}"/>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden footer-fixed">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <div class="main">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/hemo/listado" htmlEscape="true "/>">
                    <spring:message code="Listado" /></a>
            </li>
        </ol>
        <spring:url value="/hemo/listado" var="listHemoUrl"/>
        <spring:url value="/reportes/ReporteHemodinamica/" var="pdfUrl"/>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div class="container">
            <div class="col-sm-12 col-lg-12">
            <div class="card">
                <div class="card-header">
                   <h5 style="font-family: Roboto">
                       <i class="fa fa-users"></i> <spring:message code="Participante" />
                   </h5>
                    <button type="button" class="btn btn-primary float-right" id="btnSelect">Seleccione</button>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table id="tableHemo" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                            <thead>
                            <tr>
                                <th data-class="expand" class="text-center"><spring:message code="Código" /></th>
                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Nombre del Participante"/></th>
                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Edad" /></th>
                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Expediente" /></th>
                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Fecha Registro" /></th>
                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Editar" /></th>
                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Detalle" /></th>
                                <th data-hide="phone,tablet" class="text-center"><spring:message code="Reporte" /></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${lista}" var="lista">
                                <tr>
                                    <spring:url value="/hemo/edithemo/{idDatoHemo}" var="edithemoUrl">
                                        <spring:param name="idDatoHemo" value="${lista.idDatoHemo}" />
                                    </spring:url>
                                    <spring:url value="/hemo/listDetailsHemo/{idDatoHemo}" var="listDetailsHemoUrl">
                                        <spring:param name="idDatoHemo" value="${lista.idDatoHemo}" />
                                    </spring:url>
                                    <td class="text-center">${lista.participante.codigo}</td>
                                    <td>${lista.participante.nombre1} ${lista.participante.nombre2} ${lista.participante.apellido1} ${lista.participante.apellido2}</td>
                                    <td class="text-center">${lista.edad}</td>
                                    <td class="text-center">${lista.nExpediente}</td>
                                    <td class="text-center"><fmt:formatDate timeStyle = "medium" value="${lista.recordDate}" pattern="dd/MM/yyyy hh:mm"/></td>
                                    <td class="text-center">
                                        <a href="${fn:escapeXml(edithemoUrl)}" data-toggle="tooltip" data-placement="bottom" title="Editar"
                                           class="btn btn-outline-primary btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                    </td>
                                    <td class="text-center">
                                        <a href="${fn:escapeXml(listDetailsHemoUrl)}" data-toggle="tooltip" data-placement="top" title="Historial"
                                           class="btn btn-outline-success btn-sm">
                                            <i class="fa fa-history" aria-hidden="true"></i>
                                        </a>
                                    </td>
                                    <td class="text-center">

                                        <button type="button" data-toggle="tooltip" id="btnReporte" data-placement="top" title="Reporte"
                                           class="btn btn-outline-info btn-sm btnReporte" data-id="${lista.idDatoHemo}">
                                            <i class="fa fa-book" aria-hidden="true"></i>
                                        </button>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="card-footer text-muted"></div>
            </div>
            <hr>
            </div>
            </div>
            </div>

            </div>
        </div>
  </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
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


<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>

<spring:url value="/resources/js/libs/dataTable1.12/jquery-3.5.1.js" var="jqueryJs" />
<script type="text/javascript" src="${jqueryJs}"></script>

<spring:url value="/resources/js/libs/dataTable1.12/jquery.dataTables.min.js" var="jqueryDataTableJs" />
<script type="text/javascript" src="${jqueryDataTableJs}"></script>

<spring:url value="/resources/js/libs/dataTable1.12/dataTables.select.min.js" var="dataTableSelectJs" />
<script type="text/javascript" src="${dataTableSelectJs}"></script>

<script>
    $(document).ready(function(){
        $("#tableHemo tbody").on("click", ".btnReporte",function(){
            debugger;
            var id = $(this).data('id');
            SendId(id);
        });
        function SendId(id){
            window.open("${pdfUrl}?idDatoHemo="+id, '_blank');

        }
      $('#tableHemo').DataTable({
          "oLanguage": {
              "sUrl": "${dataTablesLang}"
          },
          select: {
              style: 'multi'
          }
      });
        const array = [];
        $("#btnSelect").on("click", function(){
            var count = $('#tableHemo').DataTable().rows( '.selected' ).count();
            var checked_rows = $('#tableHemo').DataTable().rows( '.selected' ).data();

            var array2 = [];
            for(var i=0; i<checked_rows.length; i++) {
                array.push(checked_rows[i][0]);
                for(var j=0; j<array.length; j++){
                    if(verificaSiExiste(checked_rows[i][0],array2)){
                        console.log("array[j]: "+ array[j] + " checked_rows: " + checked_rows[i][0]);
                        array2.push(checked_rows[i][0]);
                    }
                }
            }
            console.log(JSON.stringify(array2));
        });

        function verificaSiExiste(item, array){
           if(array.indexOf(item)=== -1){
               return true;
           }else{
               return false;
           }
        }


    });
</script>
</body>
</html>
