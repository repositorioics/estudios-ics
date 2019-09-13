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

    <%-- Comentario en JSP --%>
    <%-- Comentario en JSP
    <spring:url value="/resources/css/dtresponsive/twitter-bootstrap.css" var="boot1" />
     <link href="${boot1}" rel="stylesheet" type="text/css"/>--%>

    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

    <spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="bdat4" />
    <link rel="stylesheet" href="${bdat4}" type="text/css"/>

<%--<spring:url value="/resources/css/bootstrapdt.css" var="bdt" />
<link rel="stylesheet" href="${bdt}" type="text/css"/>--%>

<spring:url value="/resources/css/bootstrap.min.css" var="boot" />
<link href="${boot}" rel="stylesheet" type="text/css"/>

<style>
  tr:hover {
      background-color: #dddddd !important;
  }
  input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; font-family: Roboto }
  .swal-modal {
      background-color: rgba(63,127,191,90);
      border: 3px solid white;
  }
  .swal-title{
   color: #ffffff;
  }
  .swal-text{
   color: #ffffff;
  }
  .hide{
      visibility: hidden
  }
</style>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden footer-fixed">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="">
<jsp:include page="../fragments/sideBar.jsp" />
<!-- Main content -->
<div class="main">
  <br/>
  <br/>
  <!-- Breadcrumb -->
  <ol class="breadcrumb">
      <li class="breadcrumb-item">
          <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
          <i class="fa fa-angle-right"></i>
          <a href="<spring:url value="/Domicilio/Listado/" htmlEscape="true "/>">Coordenadas</a>
      </li>
  </ol>
  <c:set var="confirmar"><spring:message code="confirm" /></c:set>
  <div class="container-fluid">
      <div class="animate fadeIn">
          <div class="card bg-secondary text-black-50">
              <div class="card-header">
                  <h5 class="card-title" style="font-weight: bold"><i class="fa fa-list"></i> Lista Coordenadas </h5>
              </div>
              <div class="card-body">
                  <div class="container">
                      <spring:url value="/Domicilio/ListPdvi" var="Pdvi"></spring:url>
                      <spring:url value="/Domicilio/Home" var="FormUrl"></spring:url>
                          <div class="row">
                              <div class="col-sm-4">
                                  <a href="${FormUrl}" id="btnModal" class="btn btn-primary btn-block">
                                      <i class="fa fa-plus"></i>
                                      Agregar Coordenadas
                                  </a>
                              </div>
                              <div class="col-sm-4"> </div>
                              <div class="col-sm-4"> </div>
                          </div>
                      <br/>
                      <form action="#" id="select-Coordenadas-form" autocomplete="off" class="form-horizontal ">
                          <div class="row">
                              <div class="col-sm-12">
                                  <div class="form-group">
                                      <label class="form-control-label" for="parametro"><spring:message code="please.enter"/> <spring:message code="parameter"/></label>
                                      <input type="text" class="form-control" id="parametro" name="parametro" placeholder="<spring:message code="please.enter"/> <spring:message code="parameter"/>">
                                  </div>
                              </div>
                          </div>
                      </form>
                      <hr/>
                      <hr/>
                      <div class="table-responsive">
                          <table class="table table-striped dt-responsive nowrap" style="width:100%" id="tblCoor">
                              <thead>
                              <tr>
                                  <th scope="col" class="text-center hide ">Id</th>
                                  <th scope="col" class="text-center">ParticipanteId</th>
                                  <th scope="col" class="text-center"> NombreCompleto</th>
                                  <th scope="col"> Dirección</th>
                                  <th scope="col" class="text-center"> Manzana</th>
                                  <th scope="col" class="text-center"> Barrio</th>
                                  <th scope="col" class="text-center">Otro Barrio</th>
                                  <th scope="col" class="text-center">Casa</th>
                                  <th scope="col" class="text-center">Casa Familia</th>
                                  <th scope="col" class="text-center">Estudios</th>
                                  <th scope="col" class="text-center">Actual</th>
                                  <th scope="col" class="text-center">Registrado</th>
                                  <th scope="col" class="text-center hide">Acción</th>
                              </tr>
                              </thead>
                              <tbody></tbody>
                              <tfoot>
                              <tr>
                                  <th scope="col" class="text-center hide">Id</th>
                                  <th scope="col" class="text-center">ParticipanteId</th>
                                  <th scope="col" class="text-center"> NombreCompleto</th>
                                  <th scope="col"> Dirección</th>
                                  <th scope="col" class="text-center"> Manzana</th>
                                  <th scope="col" class="text-center"> Barrio</th>
                                  <th scope="col" class="text-center">Otro Barrio</th>
                                  <th scope="col" class="text-center">Casa</th>
                                  <th scope="col" class="text-center">Casa Familia</th>
                                  <th scope="col" class="text-center">Estudios</th>
                                  <th scope="col" class="text-center">Actual</th>
                                  <th scope="col" class="text-center">Registrado</th>
                                  <th scope="col" class="text-center hide">Acción</th>
                              </tr>
                              </tfoot>
                          </table>
                      </div>
                  </div>
              </div>
              <!-- Modal para cambiar estado actual -->
              <div id="exampleModal" class="modal fade" tabindex="-1" role="modal" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                      <div class="modal-content">
                          <div class="modal-header">
                              <h5 class="modal-title" style="font-weight: bold" id="exampleModalLabel">
                                  <i class="fa fa-refresh"></i>
                                  Actualizar Dirección</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                              </button>
                          </div>
                          <div class="modal-body">
                              <input type="hidden" id="accionUrl" class="form-control"/>
                              <br/>
                                  <h5 class="modal-title  text-center" style="font-weight: bold" id="cuerpo"></h5>
                              <br/>
                          </div>
                          <div class="modal-footer">
                              <button type="button" class="btn btn-danger" data-dismiss="modal">
                                  <i class="fa fa-times-circle" aria-hidden="true"></i>
                                  Cancelar</button>
                              <button type="button" id="btnSend" class="btn btn-primary"><i class="fa fa-refresh"></i> Realizar Cambios</button>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="card-footer text-muted">
                  <div class="custom-control custom-checkbox my-1 mr-sm-2">
                      <input type="checkbox" class="custom-control-input" id="cbxShowHide">
                      <label class="custom-control-label" for="cbxShowHide"> <strong style="font-weight: bold">Búscar Cambios de Domicilio.</strong> </label>
                  </div>
              </div>
          </div>
          <hr/>
          <div id="block" style="display: none;">
              <div class="card bg-secondary text-black-50">
              <div class="card-header">
                <h5 style="font-weight: bold">
                    <i class="fa fa-history" aria-hidden="true"></i>
                    Lista Cambios de Domicilios
                </h5>
              </div>
              <div class="card-body">
                  <div class="container">
                      <br/>
                      <form action="#" id="select-Coordenadas2-form" autocomplete="off" class="form-horizontal">
                          <div class="row">
                              <div class="col-sm-12">
                                  <div class="form-group">
                                      <label class="form-control-label" for="parametro2"><spring:message code="please.enter"/> <spring:message code="parameter"/></label>
                                      <input type="text" class="form-control" id="parametro2" name="parametro2" placeholder="<spring:message code="please.enter"/> <spring:message code="parameter"/>">
                                  </div>
                              </div>
                          </div>
                      </form>
                      <hr/>

                      <div class="table-responsive">
                          <table class="table table-striped dt-responsive nowrap" style="width:100%" id="tblCoor2">
                              <thead>
                              <tr>
                                  <th scope="col" class="text-center">ID</th>
                                  <th scope="col" class="text-center">Participante</th>
                                  <th scope="col" class="text-center">PDCS</th>
                                  <th scope="col" class="text-center">CHF</th>
                                  <th scope="col" class="text-center">FechaCambio</th>
                                  <th scope="col" class="text-center">Barrio</th>
                                  <th scope="col" class="text-center">Direccion</th>
                                  <th scope="col" class="text-center">Manzana</th>
                                  <th scope="col" class="text-center">Origen</th>
                              </tr>
                              </thead>
                              <tbody></tbody>
                              <tfoot>
                              <tr>
                                  <th scope="col" class="text-center">ID</th>
                                  <th scope="col" class="text-center">Participante</th>
                                  <th scope="col" class="text-center">PDCS</th>
                                  <th scope="col" class="text-center">CHF</th>
                                  <th scope="col" class="text-center">FechaCambio</th>
                                  <th scope="col" class="text-center">Barrio</th>
                                  <th scope="col" class="text-center">Direccion</th>
                                  <th scope="col" class="text-center">Manzana</th>
                                  <th scope="col" class="text-center">Origen</th>
                              </tr>
                              </tfoot>
                          </table>
                      </div>
                  </div>
              </div>
              <div class="card-footer text-muted"></div>
          </div></div>
      </div>
  <!-- /.conainer-fluid -->
</div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>
<c:choose>
<c:when test="${cookie.eIcsLang.value == null}">
  <c:set var="lenguaje" value="es"/>
</c:when>
<c:otherwise>
  <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
</c:otherwise>
</c:choose>
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
  <spring:param name="language" value="${lenguaje}" />
</spring:url>


<spring:url value="/resources/js/libs/jquery.validate.js" var="jqValidation" />
<script src="${jqValidation}" type="text/javascript"></script>

<spring:url value="/Domicilio/Coordenadas" var="ListaCoordenadasUrl"/>
<spring:url value="/Domicilio/ListaPdvi" var="ListaPdviUrl"/>

<spring:url value="/Domicilio/UpdateActual" var="UpdateActualUrl"></spring:url>
<c:set var="confirmar"><spring:message code="confirm" /></c:set>
<c:set var="notFound"><spring:message code="noResults" /></c:set>

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
  <spring:param name="language" value="${lenguaje}" />
</spring:url>

<!-- Custom scripts required by this view -->
<spring:url value="/resources/scripts/CambioDomicilio/TablaCoordenadas.js" var="casaScript" />
<script src="${casaScript}" type="text/javascript"></script>


<!-- Custom scripts required by this view -->
<spring:url value="/resources/scripts/CambioDomicilio/TablaPdvi.js" var="casaScript" />
<script src="${casaScript}" type="text/javascript"></script>


<script>
jQuery(document).ready(function() {

    $('#cbxShowHide').click(function(){
        this.checked? $('#block').show(1000):$('#block').hide(1000);
        $("#parametro2").focus();
    });
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
