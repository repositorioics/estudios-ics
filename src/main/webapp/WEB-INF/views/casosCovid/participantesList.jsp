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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/covid/listCovid/" htmlEscape="true "/>"><spring:message code="covid19.positives" /></a>
                <i class="fa fa-angle-right"></i><spring:message code="covid19.participants.list" />
            </li>
        </ol>
        <c:set var="exportar"><spring:message code="export" /></c:set>
        <c:set var="cerrarCaso"><spring:message code="close.case" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <c:set var="successLabel"><spring:message code="process.success" /></c:set>
        <c:set var="seleccionarCaso"><spring:message code="select.case" /></c:set>
        <c:set var="casoInactivo"><spring:message code="inactive.case" /></c:set>
        <c:set var="recordDisabledLabel"><spring:message code="recordDisabled" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <spring:url value="/covid/desactParticipanteCase" var="DisabledPartiUrl"/>
        <div class="container-fluid">
            <div class="animated fadeIn"></div>
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-list-alt"></i> <spring:message code="covid19.positives" /> - <spring:message code="covid19.participants.list" />
                </div>
                <div class="card-block">
                        <div class="row">
                            <div class="col-md-12">
                                    <spring:url value="/covid/listCovid/" var="listUrl"/>
                                    <button id="new_version" onclick="location.href='${fn:escapeXml(listUrl)}'" class="btn btn-info btn-lg">
                                        <spring:message code="cases" /> <i class="fa fa-reply"></i>
                                    </button>
                            </div>
                        </div>
                    <div class="col-md-12">
                        <br/>
                        <hr/>
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered" id="lista_participantes">
                            <thead>
                            <tr>
                                <th width="11%"><spring:message code="code" /></th>
                                <th width="25%"><spring:message code="participant" /></th>
                                <th width="15%"><spring:message code="age" /></th>
                                <th width="10%"><spring:message code="positive" /></th>
                                <th width="10%"><spring:message code="lbl.positive.by" /></th>
                                <th width="12%"><spring:message code="FIS" /></th>
                                <th width="12%"><spring:message code="fif" /></th>
                                <th width="5%"><spring:message code="actions" /></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${participantes}" var="parti">
                                <spring:url value="/covid/actions/disablepart/{codigo}"
                                            var="disableUrl">
                                    <spring:param name="codigo" value="${parti.codigoCasoParticipante}*${parti.participante.codigo}" />
                                </spring:url>
                                <spring:url value="/covid/otrosPositivosCovid/{codigoCaso}/{idparticpante}" var="addOtrosPositivosUrl">
                                    <spring:param name="codigoCaso" value="${parti.codigoCaso.codigoCaso}" />
                                    <spring:param name="idparticpante" value="${parti.participante.codigo}" />
                                </spring:url>
                                <tr>
                                    <c:set var="edadParts" value="${fn:split(parti.participante.edad, '/')}" />
                                    <td>${parti.participante.codigo}</td>
                                    <td>${parti.participante.nombreCompleto}</td>
                                    <td> <c:out value="${edadParts[0]} años ${edadParts[1]} meses ${edadParts[2]} dias" /> </td>
                                    <c:choose>
                                        <c:when test="${parti.enfermo eq 'S' or parti.enfermo eq 'I'}">
                                            <td align="center"><span class="badge badge-success"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td align="center"><span class="badge badge-danger"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        <c:forEach items="${positivoPor}" var="cat">
                                            <c:if test="${cat.catKey eq parti.positivoPor}">
                                                <c:out value="${cat.spanish}" />
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td> <fmt:formatDate value="${parti.fis}" pattern="dd/MM/yyyy" /> </td>
                                    <td> <fmt:formatDate value="${parti.fif}" pattern="dd/MM/yyyy" /> </td>
                                    <td align="center">
                                          <c:if test="${casoCovid19 eq '0'}">
                                        <c:choose>
                                        <c:when test="${parti.pasive=='1' or parti.enfermo eq 'S' or parti.enfermo eq 'I'}">
                                            <button  title="<spring:message code="disable" />" class="btn btn-outline-primary btn-sm" disabled><i class="fa fa-user-plus"></i></button>
                                        </c:when>
                                            <c:otherwise>
                                                <a title="<spring:message code="more" /> <spring:message code="covid19.candidates" />" href="${fn:escapeXml(addOtrosPositivosUrl)}" class="btn btn-outline-warning btn-sm"><i class="fa fa-user-plus"></i></a>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${parti.pasive=='1' or parti.enfermo eq 'S' or parti.enfermo eq 'I'}">
                                                <button title="<spring:message code="disable" />" class="btn btn-outline-primary btn-sm" disabled><i class="fa fa-trash-o"></i></button>
                                            </c:when>
                                            <c:otherwise>
                                                <a title="<spring:message code="disable" />" data-toggle="modal" data-id="${fn:escapeXml(disableUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash-o"></i></a>
                                            </c:otherwise>
                                        </c:choose>
                                          </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            
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
                            <button type="button" id="btnOk" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>

        <div class="modal fade bd-example-modal-lg" id="desactModalParti" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="title" class="modal-title" id="exampleModalLabel">${confirmar}</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form autocomplete="off" class="form-horizontal" id="desactive-form">
                            <div class="form-group">
                                <input type="hidden" class="form-control" id="codigo" name="codigo">
                            </div>
                            <div id="cuerpo1"></div>
                            <div class="form-group">
                                <label for="motivo" class="col-form-label">Motivo:</label>
                                <textarea class="form-control" id="motivo" name="motivo"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" id="resetForm"><spring:message code="cancel" /></button>
                        <button type="button" class="btn btn-primary" id="btnDesact"><spring:message code="ok" /></button>
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
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />

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

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<script>
    $(document).ready(function(){
        $("#lista_participantes").DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });

        $(".desact").click(function(){
            $('#codigo').val('');
            $('#cuerpo1').html();
            var url = decodeURIComponent($(this).data('id').substr($(this).data('id').lastIndexOf("/")+1));
            var codigo_caso = decodeURIComponent(url.substr(0,url.indexOf("*")));
            $('#codigo').val(codigo_caso);
            var codigo_participante = decodeURIComponent( url.substr(url.lastIndexOf("*")+1));
            $('#cuerpo1').html('<h3>'+"${deshabilitar}"+' '+ codigo_participante +'?</h3>');
            $('#desactModalParti').modal('show');
            /*$('#accionUrl').val($(this).data('id').substr(0,$(this).data('id').lastIndexOf("*")));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+decodeURIComponent($(this).data('id').substr($(this).data('id').lastIndexOf("*")+1))+'?</h3>');
            $('#btnOk').show();
            $('#basic').modal('show');*/
        });

        var form = $('#desactive-form');
        form.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                codigo: { required: true },
                motivo: { required: true }
            },
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback col-md-8' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    //error.insertAfter( element ); //cuando no es input-group
                    error.insertAfter(element.parent('.input-group'));
                }
            },
            highlight: function ( element, errorClass, validClass ) {
                $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
            },
            unhighlight: function (element, errorClass, validClass) {
                $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
            }
        });

        $("#btnDesact").click(function(){
            if (form.valid()){
                desactParticipante();
            }
        });


        function desactParticipante(){
            $.post( "${DisabledPartiUrl}", {codigo: $('#codigo').val(), motivo: $('#motivo').val()}, function( data ){
                var registro = JSON.parse(data);
                console.log(registro);
                if (registro.codigoCaso === undefined) {
                    toastr.error(data,"Error",{timeOut: 0});
                }else {
                    if(registro.codigoParticipante != undefined){
                        toastr.success("${successLabel}" + "\n Retirado: " + registro.codigoParticipante);
                    }else{
                        toastr.success("${successLabel}");
                    }
                    window.setTimeout(function () {
                        window.location.reload();
                    }, 1500);
                }
            }, 'text' ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                toastr.error( "error:" + errorThrown,{timeOut: 0});
            });
        }

        $('#resetForm').on('click', function(){
            document.getElementById("desactive-form").reset();
            $('#codigo').val('');
            $('#cuerpo1').html('');
            $('#motivo').val('');
            $('#desactModalParti').modal('hide');
        });

    });
    if ("${deshabilitado}"){
        toastr.error("${recordDisabledLabel}", "${participante}");
    }

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>