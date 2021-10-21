<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 01/06/2020
  Time: 13:04
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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/covid/list/" htmlEscape="true "/>"><spring:message code="covid19.positives" /></a>
                </li>
            </ol>
            <c:set var="recordDisabledLabel"><spring:message code="recordDisabled" /></c:set>
            <c:set var="successLabel"><spring:message code="process.success" /></c:set>
            <c:set var="cerrarCaso"><spring:message code="close.case" /></c:set>
            <c:set var="confirmar"><spring:message code="confirm" /></c:set>
            <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
            <spring:url value="/covid/closeCase" var="closeUrl"/>
            <div class="container-fluid">
                <div class="animated fadeIn">
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-list-alt"></i> <spring:message code="covid19.positives" />
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <div class="col-md-12">
                                <a href="<spring:url value="/covid/SaveForm/" htmlEscape="true"/>" class="btn btn-success btn-lg">
                                    <i class="fa fa-plus" aria-hidden="true"></i><spring:message code="add" /> <spring:message code="positive" /> </a>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <br/>
                            <hr/>
                            <div class="table-responsive">
                                <table id="lista_casos" class="table table-hover table-bordered">
                                    <thead>
                                    <tr>
                                        <th width="12%"><spring:message code="positive" /></th>
                                        <th width="12%"><spring:message code="chf.house" /></th>
                                        <th width="12%"><spring:message code="logindate" /></th>
                                        <th width="12%"><spring:message code="lbl.positive.by" /></th>
                                        <th width="12%"><spring:message code="FIS" /></th>
                                        <th width="12%"><spring:message code="fif" /></th>
                                        <th width="12%"><spring:message code="logoutdate" /></th>
                                        <th width="16%"><spring:message code="actions" /></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${casosCovid}" var="l">
                                        <spring:url value="/covid/actions/disable/{codigo}"
                                                    var="disableUrl">
                                            <spring:param name="codigo" value="${l.codigoCaso.codigoCaso}*${l.participante.codigo}" />
                                        </spring:url>

                                        <spring:url value="/covid/editCase/{codigoCasoParticipante}"
                                                    var="editUrl">
                                            <spring:param name="codigoCasoParticipante" value="${l.codigoCasoParticipante}" />
                                        </spring:url>
                                        <spring:url value="/covid/participants/{codigo}"
                                                    var="participantsUrl">
                                            <spring:param name="codigo" value="${l.codigoCaso.codigoCaso}" />
                                        </spring:url>
                                        <spring:url value="/super/covid/otrosPositivosCovid/{codigo}" var="addOtrosPositivosUrl">
                                            <spring:param name="codigo" value="${l.codigoCaso.codigoCaso}" />
                                        </spring:url>
                                        <tr>
                                            <td><c:out value="${l.participante.codigo}" /></td>
                                            <td><c:out value="${l.codigoCaso.casa.codigoCHF}" /></td>
                                            <td><fmt:formatDate value="${l.codigoCaso.fechaIngreso}" pattern="dd/MM/yyyy" /></td>
                                            <td>
                                                <c:forEach items="${positivoPor}" var="cat">
                                                    <c:if test="${cat.catKey eq l.positivoPor}">
                                                        <c:out value="${cat.spanish}" />
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td><fmt:formatDate value="${l.fis}" pattern="dd/MM/yyyy" /></td>
                                            <td><fmt:formatDate value="${l.fif}" pattern="dd/MM/yyyy" /></td>
                                            <td><fmt:formatDate value="${l.codigoCaso.fechaInactivo}" pattern="dd/MM/yyyy" /></td>
                                            <td align="center">
                                                <c:choose>
                                                    <c:when test="${l.codigoCaso.inactivo=='1'}">
                                                        <button title="<spring:message code="edit" />" class="btn btn-outline-primary btn-sm" disabled><i class="fa fa-edit"></i></button>
                                                        <a title="<spring:message code="participants" />" href="${fn:escapeXml(participantsUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-users"></i></a>
                                                        <button title="<spring:message code="close.case" />" class="btn btn-outline-primary btn-sm" disabled><i class="fa fa-sign-out"></i></button>
                                                        <button title="<spring:message code="disable" />" class="btn btn-outline-primary btn-sm" disabled><i class="fa fa-trash-o"></i></button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a title="<spring:message code="edit" />" href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
                                                        <a title="<spring:message code="participants" />" href="${fn:escapeXml(participantsUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-users"></i></a>
                                                        <a title="<spring:message code="close.case" />" data-toggle="modal" data-id="${l.codigoCaso.codigoCaso}" class="btn btn-outline-primary btn-sm salida"><i class="fa fa-sign-out"></i></a>
                                                        <a title="<spring:message code="disable" />" data-toggle="modal" data-id="${fn:escapeXml(disableUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash-o"></i></a>
                                                    </c:otherwise>
                                                </c:choose>
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
                            <form action="#" autocomplete="off" id="close-form" class="form-horizontal">
                                <div id="dvSalida" class="form-group row">
                                    <label class="form-control-label col-md-3" for="fechaSalida"><spring:message code="logoutdate" />
                                            <span class="required">
                                                 *
                                            </span>
                                    </label>
                                    <div class="input-group col-md-9">
                                                <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                                </span>
                                        <input name="fechaSalida" id="fechaSalida" class="form-control date-picker" type="text" data-date-end-date="+0d" value="" />
                                    </div>
                                </div>
                            </form>
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

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>

<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
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
    jQuery(document).ready(function() {
        var table = $('#lista_casos').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            "order" : [[6, 'asc'], [0, 'asc']]
        });

        $("#fechaSalida").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });
        var form2 = $('#close-form');
        form2.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                fechaSalida: {
                    required: true
                }
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
        $("#btnOkClose").click(function(){
            if (form2.valid()){
                processCase();
            }
        });
        function processCase(){
            $.post( "${closeUrl}"
                    , {codigo: $('#accionUrl').val(), fechaInactivo: $('#fechaSalida').val()}
                    , function( data )
                    {
                        var registro = JSON.parse(data);
                        console.log(registro);
                        if (registro.codigoCaso === undefined) {
                            toastr.error(data,"Error",{timeOut: 0});
                        }
                        else {
                            toastr.success("${successLabel}");
                            window.setTimeout(function () {
                                window.location.reload();
                            }, 1500);
                        }
                    }
                    , 'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error( "error:" + errorThrown,{timeOut: 0});
                    });


        }

        $('#lista_casos thead tr').clone(true).appendTo( '#lista_casos thead' );
        $('#lista_casos thead tr:eq(1) th').each( function (i) {
            var title = $(this).text();
            $(this).html( '<input type="text" placeholder="Búscar '+title+'" />' );
            $( 'input', this ).on( 'keyup change', function () {
                if ( table.column(i).search() !== this.value ) {
                    table.column(i).search( this.value ).draw();
                }
            });
        });

    });

    if ("${deshabilitado}"){
        toastr.error("${recordDisabledLabel}");
    }
    if ("${cerrado}"){
        toastr.success("${successLabel}");
    }

    $(".desact").click(function(){
        $('#accionUrl').val($(this).data('id').substr(0,$(this).data('id').lastIndexOf("*")));
        $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
        $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+decodeURIComponent($(this).data('id').substr($(this).data('id').lastIndexOf("*")+1))+'?</h3>');
        $('#btnOkAct').show();
        $('#dvSalida').hide();
        $('#btnOkClose').hide();
        $('#basic').modal('show');
    });


    $(".salida").click(function(){
        $('#accionUrl').val($(this).data('id'));
        $('#titulo').html('<h2 class="modal-title">'+"${cerrarCaso}"+'</h2>');
        $('#cuerpo').html('');
        $('#btnOkAct').hide();
        $('#btnOkPrint').hide();
        $('#dvSalida').show();
        $('#btnOkClose').show();
        $('#basic').modal('show');
    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>
