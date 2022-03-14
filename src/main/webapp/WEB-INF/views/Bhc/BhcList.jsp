<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 16/02/2022
  Time: 12:14 PM
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
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot"/>
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>
    <style>
        body{
            background: #edf1f5;
            margin-top:20px;
        }
        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid transparent;
            border-radius: 0;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link {
            border: 0;
            color: #fff;
            border-bottom: 3px solid transparent;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 3px solid #2cd07e;
        }
        .no-wrap td, .no-wrap th {
            white-space: nowrap;
        }
        .table td, .table th {
            padding: .9375rem .4rem;
            vertical-align: top;
            border-top: 1px solid rgba(120,130,140,.13);
        }
        .font-light {
            font-weight: 300;
        }
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #028dba;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #028dba;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 5px solid #fff
        }
    </style>
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
            <spring:url value="/Bhc/closeCase" var="closeUrl"/>
            <c:set var="cerrarCaso"><spring:message code="lbl.invalidate" /></c:set>
            <c:set var="successLabel"><spring:message code="process.success" /></c:set>
            <spring:url value="/Bhc/list" var="listBhcUrl"/>
            <spring:url value="/Bhc/sendBhc" var="sendBhcUrl"/>
                <!-- init card -->
                <div class="">
                <div class="row">
                <div class="col-md-10 col-lg-12">
                <div class="card">
                <div class="card-body bg-primary text-white mailbox-widget pb-0">
                    <h2 class="text-white pb-3">  <i class="fa fa-flask text-white" aria-hidden="true"></i>
                        <spring:message code="List" />  <spring:message code="Bhc" /></h2>
                    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                                <span class="d-none d-md-block"> <spring:message code="List" /></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                                <span class="d-block d-md-none"><i class="ti-export"></i></span>
                                <span class="d-none d-md-block"><spring:message code="Form" /> <spring:message code="Envío" /></span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
                <div>
                    <a class="btn btn-info btn-lg" href="<spring:url value="/Bhc/recepcion" htmlEscape="true "/>">
                        <i class="fa fa-plus" aria-hidden="true"></i>
                        <spring:message code="Recepción" /> <spring:message code="Bhc" /></a>
                    <hr/>
                    <div class="table-responsive">
                        <table id="tblBhc" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="text-center" width="12%"><spring:message code="Fecha" /></th>
                                <th class="text-center" width="12%"><spring:message code="code" /></th>
                                <th class="text-center" width="12%"><spring:message code="Volumen" /></th>
                                <th class="text-center" width="12%"><spring:message code="Estudios" /></th>
                                <th class="text-center" width="12%"><spring:message code="Enviado" /></th>
                                <th class="text-center" width="16%"><spring:message code="actions" /></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${bhcs}" var="b">
                                <spring:url value="/Bhc/editBhc/{bhc_id}" var="editUrl">
                                    <spring:param name="bhc_id" value="${b.bhc_id}" />
                                </spring:url>
                                <tr>
                                    <td><fmt:formatDate value="${b.fecha_bhc}" pattern="dd/MM/yyyy" /></td>
                                    <td><c:out value="${b.codigo_participante}" /></td>
                                    <td><c:out value="${b.volumen}" /></td>
                                    <td><c:out value="${b.estudios}" /></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${b.enviado=='1'}">
                                                <h4 class="text-danger text-center">
                                                    <i class="fa fa-check text-success" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Si"></i>
                                                </h4>
                                            </c:when>
                                            <c:otherwise>
                                                <h4 class="text-success text-center">
                                                    <i class="fa fa-times text-danger" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="No"></i>
                                                </h4>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td align="center">
                                        <a title="<spring:message code="edit" />" data-toggle="tooltip" data-placement="left" title="Editar" href="${fn:escapeXml(editUrl)}" class="btn btn-warning btn-sm">
                                            <i class="fa fa-edit"></i></a>
                                        <a title="<spring:message code="delete" />" data-toggle="tooltip" data-placement="right" title="Anular" data-toggle="modal" data-id="${b.bhc_id}" class="btn btn-danger btn-sm salida">
                                            <i class="fa fa-trash text-white"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                </div>
                <div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
                    <hr/>
                    <!-- inicia form inline -->
                    <div class="container">
                        <form name="envio-bhc-form" id="envio-bhc-form" autocomplete="off">
                            <div class="form-group row">
                                <label for="fechaEnvio" class="col-sm-2 col-form-label"><spring:message code="dateAdded" /></label>
                                <div class="col-sm-10">
                                    <input name="fechaEnvio" id="fechaEnvio" class="form-control date-picker" type="text" data-date-end-date="+0d" required="required" />
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="horaEnvio" class="col-sm-2 col-form-label"><spring:message code="lbl.Hour" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="horaEnvio" name="horaEnvio">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="desde" class="col-sm-2 col-form-label"><spring:message code="lbl.from" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control from_date datepicker" id="desde" name="desde" data-date-end-date="+0d">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="desde" class="col-sm-2 col-form-label"><spring:message code="lbl.until" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control to_date datepicker" id="hasta" name="hasta" data-date-end-date="+0d">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label" for="numenvio"><spring:message code="lbl.send" /></label>
                                <div class="col-sm-10">
                                    <select id="numenvio" name="numenvio" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${numero_envio}" var="n">
                                            <option value="${n.catKey}">${n.spanish}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="temperatura" class="col-sm-2 col-form-label"><spring:message code="Temperatura" /></label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="temperatura" name="temperatura" minlength="1" maxlength="4"  required="required">
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-primary btn-lg float-right"> <i class="fa fa-send" aria-hidden="false"></i>
                                        <spring:message code="lbl.dispatch" />
                                        <spring:message code="sample" /> <spring:message code="BHC" />
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- fin form inline -->
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                <!-- fin card -->
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
                        <div id="cuerpo"></div>
                        <form action="#" autocomplete="off" id="close-form" name="close-form" class="form-horizontal">
                            <input type=hidden class="form-control" id="bhc_id" name="bhc_id"/>
                            <div id="dvSalida" class="form-group row">
                                <div class="form-group col-md-12">
                                    <label for="message_razon" class="col-form-label"> <spring:message code="rason.invalid" />
                                        <span class="required">* </span>
                                    </label>
                                    <textarea class="form-control" id="message_razon" name="message_razon"></textarea>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between">
                                <div class="p-2 bd-highlight"><button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    <i class="fa fa-times" aria-hidden="true"></i>
                                    <spring:message code="cancel" /></button></div>
                                <div class="p-2 bd-highlight"></div>
                                <div class="p-2 bd-highlight">
                                    <button type="submit" class="btn btn-primary">  <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.conainer-fluid -->
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
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
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
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>
<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>
<spring:url value="/resources/js/views/Bhc/processbhc.js" var="processbhcJs" />
<script type="text/javascript" src="${processbhcJs}"></script>

<script>
    $(document).ready(function(){
        var parametros = {
            "listBhcUrl"    : "${listBhcUrl}",
            "dataTablesLang": "${dataTablesLang}",
            "closeUrl"      : "${closeUrl}",
            "successLabel"  : "${successLabel}",
            "sendBhcUrl"    : "${sendBhcUrl}"
        }
        Bhc.init(parametros);

        $("#desde").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));
        $("#hasta").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));
        var hora = moment().format('HH:mm');
        $("#horaEnvio").val(hora);
        $("#fechaEnvio").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        }).val(moment().format('DD/MM/YYYY'));

        /*******************************/
        $("#close-form").validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                message_razon: {
                    required: true
                }
            }, errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
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
            },
            submitHandler: function (form) {
                PbmcPasive();
            }
        });
        function PbmcPasive(){
            var form2 =$("#close-form");
            $.post( parametros.closeUrl, form2.serialize(), function( data ){
                registro = JSON.parse(data);
                debugger;
                if (registro.bhc_id ===undefined || registro.bhc_id==null) {
                    swal({
                        title: "¡INFORMACIÓN!",
                        text: data.msj,
                        type: "error",
                        timer: 2000
                    });
                }
                else {
                    $("#basic").modal('hide');
                    swal({
                        title: "¡Buen trabajo!!",
                        text:  parametros.successLabel,
                        type: "success",
                        timer: 2000
                    });
                    window.setTimeout(function () {
                        window.location.reload(true);
                    }, 1500);
                }
            },'text' ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                swal({
                    title: "¡INFORMACIÓN!",
                    text: errorThrown,
                    type: "error",
                    timer: 2000
                });
            });
        }
    });
    $(".salida").click(function(){
        $('#bhc_id').val($(this).data('id'));
        $('#titulo').html('<h2 class="modal-title">'+"${cerrarCaso}"+'</h2>');
        $('#cuerpo').html('Deseas Anular el registro?');
        $('#basic').modal('show');
    });
    function ejecutarAccion() {
        window.location.href = $('#pbmc_id').val();
    }
</script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>
</body>
</html>
