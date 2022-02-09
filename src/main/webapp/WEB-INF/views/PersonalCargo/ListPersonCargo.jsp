<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 22/11/2021
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
                        <i class="fa fa-group"></i> <spring:message code="Personal" />
                    </div>
                    <div class="card-block">
                        <div class="row table-toolbar">
                            <div class="col-md-12">
                                <div class="btn-group">
                                    <spring:url value="/admin/personal/person" var="newPerson"/>
                                    <button id="lista_usuarios_new" onclick="location.href='${fn:escapeXml(newPerson)}'" class="btn btn-info">
                                        <spring:message code="add" /> <i class="fa fa-user-plus"></i>
                                    </button>
                                </div>
                                <br>
                                <br>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <c:set var="recordDisabledLabel"><spring:message code="recordDisabled" /></c:set>
                            <c:set var="successLabel"><spring:message code="process.success" /></c:set>
                            <c:set var="cerrarCaso"><spring:message code="close.case" /></c:set>
                            <c:set var="confirmar"><spring:message code="confirm" /></c:set>
                            <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
                            <spring:url value="/admin/personal/searchPersonCargo" var="searchPersonCargoUrl"/>
                            <table class="table table-hover table-bordered" id="lista_personal">
                                <thead>
                                <tr>
                                    <th><spring:message code="code" /></th>
                                    <th><spring:message code="lbl.names.surnames" /></th>
                                    <th><spring:message code="code" /> <spring:message code="lbl.Person" /></th>
                                    <th><spring:message code="Activo" /></th>
                                    <th><spring:message code="actions" /> </th>
                                </tr>
                                </thead>
                                <c:forEach items="${personalCargoDtoList}" var="list" varStatus="loop">
                                    <tr>
                                        <spring:url value="/admin/personal/{codigo}"
                                                    var="personalUrl">
                                            <spring:param name="codigo" value="${list.codigo}" />
                                        </spring:url>
                                        <spring:url value="/admin/personal/editPerson/{codigo}"
                                                    var="editUrl">
                                            <spring:param name="codigo" value="${list.codigo}" />
                                        </spring:url>
                                        <spring:url value="/admin/personal/actions/enable/{codigo}"
                                                    var="enableUrl">
                                            <spring:param name="codigo" value="${list.codigo}" />
                                        </spring:url>
                                        <spring:url value="/admin/personal/actions/disable/{codigo}"
                                                    var="disableUrl">
                                            <spring:param name="codigo" value="${list.codigo}" />
                                        </spring:url>
                                        <td><c:out value="${loop.index +1}" /></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${list.estado eq true}">
                                                    <a href="${fn:escapeXml(editUrl)}"><c:out value="${list.nombre}" /></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="text-decoration:line-through;" class="text-danger"><c:out value="${list.nombre}" /></span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${list.estado eq true}">
                                                     <span>
                                                         <c:out value="${list.codigo}" />
                                                     </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="text-decoration:line-through;" class="text-danger">
                                                        <c:out value="${list.codigo}" />
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                            <c:when test="${list.estado eq true}">
                                                <h4 class="badge badge-primary text-white">
                                                    <i class="fa fa-check" aria-hidden="true"></i>
                                                </h4>
                                            </c:when>
                                            <c:otherwise>
                                                <h4 class="badge badge-danger text-white">
                                                    <i class="fa fa-times" aria-hidden="true"></i>
                                                </h4>
                                            </c:otherwise>
                                            </c:choose>
                                         </td>
                                               <td>
                                                   <c:choose>
                                                       <c:when test="${list.estado eq true}">
                                                           <a href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
                                                       </c:when>
                                                       <c:otherwise>
                                                           <button disabled="disabled" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></button>
                                                       </c:otherwise>
                                                   </c:choose>

                                                   <c:choose>
                                                       <c:when test="${list.estado eq true}">
                                                           <a data-toggle="modal" data-id= "${fn:escapeXml(disableUrl)}" class="btn btn-outline-danger btn-sm desact"><i class="fa fa-lock"></i></a>
                                                       </c:when>
                                                       <c:otherwise>
                                                           <a data-toggle="modal" data-id= "${fn:escapeXml(enableUrl)}" class="btn btn-outline-primary btn-sm unlock"><i class="fa fa-unlock"></i></a>
                                                       </c:otherwise>
                                                   </c:choose>
                                               </td>
                                    </tr>
                                </c:forEach>
                            </table>
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
                        <input type=text class="form-control" id="accionUrl"/>
                        <div id="cuerpo"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                        <button type="button" id="btnOkAct" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
                        <button type="button" id="btnOkClose" class="btn btn-info"><spring:message code="ok" /></button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
        <c:set var="userLockedLabel"><spring:message code="login.accountLocked" /></c:set>
        <c:set var="desbloquear"><spring:message code="unlock" /></c:set>

        <!-- Modal -->
        <div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><spring:message code="Persona y sus Cargos" /></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type=text class="form-control" id="accion2Url"/>

                        <form>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="code-person" class="col-form-label"><spring:message code="code" />:</label>
                                    <input type="text" class="form-control" id="code-person">
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="name-person" class="col-form-label"><spring:message code="Nombre" />:</label>
                                    <input type="text" class="form-control" id="name-person">
                                </div>

                            </div>
                        </form>

                       <div class="table-responsive">
                           <table id="tblPersonal_Cargo" class="table table-sm table-bordered table-hover">
                               <thead>
                               <tr>
                                   <th scope="col"><spring:message code="code" /></th>
                                   <th scope="col"><spring:message code="Cargo" /></th>
                                   <th scope="col"><spring:message code="Activo" /></th>
                               </tr>
                               </thead>
                               <tbody></tbody>
                           </table>
                       </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="close" /></button>
                        <button type="button" class="btn btn-primary"><spring:message code="save" /></button>
                    </div>
                </div>
            </div>
        </div>
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
    $(document).ready(function(){
        var parametros = {
            searchPersonCargoUrl:"${searchPersonCargoUrl}"
        };
        var table = $("#lista_personal").DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },columnDefs: [{
                targets: 2,
                className: 'text-center'
            },{
                targets: 3,
                className: 'text-center'
            },{
                targets: 4,
                className: 'text-center'
            }]
        });

        var tblDetails_Cargo = $("#tblPersonal_Cargo").DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });

        if ("${usuarioDeshabilitado}"){
            toastr.success("${userLockedLabel}", "${totalcargo}" );
        }
        if ("${usuarioBloqueado}"){
            toastr.error("${userLockedLabel}", "${totalcargo}" );
        }


        $(".desact").click(function(){
            var id = $(this).data('id');
            console.log("este es el ID: "+id);
            $('#accionUrl').val($(this).data('id'));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+decodeURIComponent($(this).data('id').substr($(this).data('id').lastIndexOf("*")+1))+'?</h3>');
            $('#btnOkAct').show();
            $('#dvSalida').hide();
            $('#btnOkClose').hide();
            $('#basic').modal('show');
        });

        $(".unlock").click(function(){
            $('#accionUrl').val($(this).data('id'));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${desbloquear}"+' '+$(this).data('id').substr($(this).data('id').lastIndexOf("/")+1)+'?</h3>');
            $('#btnOkAct').show();
            $('#dvSalida').hide();
            $('#btnOkClose').hide();
            $('#basic').modal('show');
        });


        $(".permisos").click(function(){
            $('#accion2Url').val($(this).data('id'));
            ObtenerCargos($(this).data('id'));
            $('#exampleModal').modal('show');
        });


        function ObtenerCargos(codigo){
            $.getJSON(parametros.searchPersonCargoUrl,{parametro: codigo, ajax: 'true'}, function( data ){
              debugger;
                tblDetails_Cargo.clear().draw( false );
                var status;
                for(var j in data){
                    console.warn(data[j].cargos);
                    console.log("Persona: "+data[j].nombre +" codigo: "+data[j].codigo);
                    $.each(data[j].cargos, function(i, item) {
                        var cargoId = data[i].cargos.codigoCargo;
                        var cargo = data[i].cargos.nombreCargo;
                        var inputCheck = '<input type="checkbox" id="cargoId" name="cargoId" value="' + status +'">';
                        tblDetails_Cargo.row.add([
                            cargoId,
                            cargo,
                            inputCheck
                        ]).draw(false);
                    });
                }
            }, 'text' )
               .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                  toastr.error( "error:" + errorThrown,{timeOut: 0});
            });
        }
    });


    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>
