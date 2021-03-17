<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
    <link rel="stylesheet" href="${bdt}" type="text/css"/>

    <spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
    <link rel="stylesheet" href="${bdat4}" type="text/css"/>

    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

    <style>

        tfoot input {
            width: 100%;
            padding: 3px;
            box-sizing: border-box;
        }


      /*  .table thead tr th {
            text-transform: uppercase;
            font-size: 0.875em;
        }
        .table tfoot tr th {
            text-transform: uppercase;
            font-size: 0.875em;
        }
        .table tbody tr td {
           font-family: Roboto bold;
           padding-top: 3px;
           margin-left: 60px;
           text-align: center;
            Word-wrap: break-Word;
            max-width: 400px;
        }
         .container {
            max-width: 1350px !important;
        }
        */

      table.table thead .sorting {
          background: none !important;
      }
    </style>

    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/Domicilio/Listado" htmlEscape="true "/>"><spring:message code="Lista Coordenadas" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animate fadeIn">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title" style="font-weight: bold"><i class="fa fa-list"></i> <spring:message code="Lista Coordenadas" /> </h5>
                    </div>
                    <div class="card-block">
                        <spring:url value="/Domicilio/ListPdvi" var="Pdvi"></spring:url>
                        <spring:url value="/Domicilio/Home" var="FormUrl"></spring:url>
                        <spring:url value="/Domicilio/Coordenadas" var="ListaCoordenadasUrl"/>
                        <spring:url value="/Domicilio/CambioPorCasa" var="CambioDomUrl"></spring:url>

                        <div class="row">
                            <div class="col-md-2">
                                <a href="${FormUrl}" id="btnModal" class="btn btn-primary btn-block">
                                    <i class="fa fa-plus"></i>
                                    <spring:message code="Agregar Coordenadas" />
                                </a>
                            </div>
                            <div class="col-md-8"> </div>
                            <div class="col-md-2">
                                <a href="${CambioDomUrl}" id="btnPorCasa" class="btn btn-primary btn-block">
                                    <i class="fa fa-home" aria-hidden="true"></i>
                                    <spring:message code="Cambio Domicilio por Casa" />
                                </a></div>
                        </div>

                        <br/>

                        <form action="#" id="select-Coordenadas-form" autocomplete="off" class="form-horizontal">
                            <div class="row">
                                <div class="col-md-2">

                                </div>
                                <div class="col-md-8">
                                    <div class="form-group">
                                        <label class="form-control-label" for="parametro"><spring:message code="please.enter"/> <spring:message code="parameter"/></label>
                                        <input type="text" class="form-control" id="parametro" name="parametro" placeholder="<spring:message code="please.enter"/> <spring:message code="parameter"/>">
                                    </div>
                                </div>
                                <div class="col-md-2">

                                </div>
                            </div>
                        </form>
                        <div class="row">
                            <div class="container">
                                <div class="col-md-12">
                                    <div class="table-responsive">
                                        <table class="table table-hover dt-responsive nowrap" style="width:100%" id="tblCoor">
                                            <thead>
                                            <tr>
                                                <th class="text-center">Id</th>
                                                <th class="text-center"><spring:message code="PARTICIPANTE"/></th>
                                                <th class="text-center"><spring:message code="CASA"/></th>
                                                <th class="text-center"><spring:message code="CASA FAM"/></th>
                                                <th class="text-center"><spring:message code="FECHA CAMBIO"/></th>
                                                <th class="text-center"> BarrioId</th>
                                                <th class="text-center"><spring:message code="BARRIO"/></th>
                                                <th class="text-center"><spring:message code="OTRO BARRIO"/></th>
                                                <th class="text-center"><spring:message code="MANZANA"/></th>
                                                <th > <spring:message code="DIRECCIÓN"/></th>
                                                <th class="text-center">PersonalId</th>
                                                <th class="text-center"><spring:message code="RECURSO"/></th>
                                                <th class="text-center"><spring:message code="OBSERVACIÓN"/></th>
                                            </tr>
                                            </thead>
                                            <tbody></tbody>
                                            <tfoot>
                                            <tr>
                                                <th  class="text-center">Id</th>
                                                <th  class="text-center"><spring:message code="PARTICIPANTE"/></th>
                                                <th  class="text-center"> <spring:message code="CASA"/></th>
                                                <th  class="text-center"><spring:message code="CASA FAM"/></th>
                                                <th  class="text-center"> <spring:message code="FECHA CAMBIO"/></th>
                                                <th  class="text-center"> BarrioId</th>
                                                <th  class="text-center"> <spring:message code="BARRIO"/></th>
                                                <th  class="text-center"><spring:message code="OTRO BARRIO"/></th>
                                                <th  class="text-center"><spring:message code="MANZANA"/></th>
                                                <th > <spring:message code="DIRECCIÓN"/></th>
                                                <th  class="text-center">PersonalId</th>
                                                <th  class="text-center"><spring:message code="RECURSO"/></th>
                                                <th  class="text-center"><spring:message code="OBSERVACIÓN"/></th>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="card-footer text-muted">

                    </div>
                </div>
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>

<!-- GenesisUI main scripts -->
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
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/jquery.validate.js" var="jqValidation" />
<script src="${jqValidation}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<!-- Custom scripts required by this view -->
<spring:url value="/resources/scripts/CambioDomicilio/TablaCoordenadas.js" var="coordenaUrl" />
<script src="${coordenaUrl}" type="text/javascript"></script>

<!-- Custom scripts required by this view -->
<spring:url value="/resources/scripts/CambioDomicilio/TablaPdvi.js" var="pdviUrl" />
<script src="${pdviUrl}" type="text/javascript"></script>


<script>
    jQuery(document).ready(function() {
        var parametros = {ListaCoordenadasUrl: "${ListaCoordenadasUrl}",
            notFound: "${notFound}",
            UpdateActualUrl:"${UpdateActualUrl}",
            dataTablesLang: "${dataTablesLang}" };
        SearchCoordenadas.init(parametros);

        var parametro2 = {ListaPdviUrl: "${ListaPdviUrl}",
            notFound: "${notFound}",
            dataTablesLang: "${dataTablesLang}" };
        SearchPdvi.init(parametro2);
        $("#parametro").focus();

    });

</script>

</body>
</html>
