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
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/lab/muestras/" htmlEscape="true "/>"><spring:message code="print.codes" /></a>
            </li>
        </ol>
        <c:set var="generar"><spring:message code="generate" /></c:set>
        <c:set var="buscar"><spring:message code="search" /></c:set>
        <c:set var="ingresarParametro"><spring:message code="enter.parameter" /></c:set>
        <c:set var="seleccioneMuestra"><spring:message code="select.sample" /></c:set>
        <spring:url value="/lab/muestras/searchSamples" var="searchUrl"/>
        <spring:url value="/lab/muestras/searchLabel" var="searchLabelUrl"/>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-list-alt"></i> <spring:message code="print.codes" />
                </div>
                <div class="card-block">
                    <div class="row table-toolbar">
                        <div class="col-md-12">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form action="#" autocomplete="off" id="search-form" class="form-horizontal">
                                <div class="form-group row">
                                    <label class="col-md-1 form-control-label"><spring:message code="registered" />?</label>
                                    <div class="col-md-2">
                                        <label class="checkbox-inline" for="registrado">
                                            <input type="checkbox" id="registrado" checked name="registrado" value="registrado">
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-2" for="codigoParticipante"><spring:message code="participant.code" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-user"></i>
                                    </span>
                                        <input id="codigoParticipante" name="codigoParticipante" type="text" class="form-control"/>
                                    </div>
                                    <label class="form-control-label col-md-2" for="codigoMx"><spring:message code="sample.code" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-eyedropper"></i>
                                    </span>
                                        <input id="codigoMx" name="codigoMx" type="text" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-2" for="codigoCasa"><spring:message code="house" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-home"></i>
                                    </span>
                                        <input id="codigoCasa" name="codigoCasa" type="text" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-2" for="fechaInicio"><spring:message code="start.date.registration" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        <input name="fechaInicio" id="fechaInicio" class="form-control from_date" type="text" data-date-end-date="+0d"  />
                                    </div>
                                    <label class="form-control-label col-md-2" for="fechaFin"><spring:message code="end.date.registration" />:
                                    </label>
                                    <div class="input-group col-md-4">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                        <input name="fechaFin" id="fechaFin" class="form-control to_date" type="text" data-date-end-date="+0d"  />
                                    </div>
                                </div>
                                <!--<div class="form-group row">
                                    <label class="form-control-label col-md-2" for="tipoEtiquetas"><spring:message code="label.types.to.generate" />:
                                    </label>
                                    <div class="input-group col-md-6">
                                    <span class="input-group-addon">
                                        <i class="fa fa-barcode"></i>
                                    </span>
                                        <select class="form-control select2-multiple" multiple="" disabled id="tipoEtiquetas" name="tipoEtiquetas">
                                            <c:forEach items="${etiquetas}" var="etiqueta">
                                                <option value="${etiqueta.messageKey}">${etiqueta.spanish}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>-->
                                <br>
                                <br>
                                <div class="row">
                                    <div class="col-md-12" align="center">
                                        <button id="btnSearch" type="submit" name="btnSearch" class="btn btn-primary btn-ladda">
                                            <spring:message code="search" /> <i class="fa fa-search"></i>
                                        </button>
                                        <button id="btnGenerate" type="submit" name="btnGenerate" class="btn btn-primary btn-ladda">
                                            <spring:message code="generate" /> <i class="fa fa-search"></i>
                                        </button>
                                        <br>
                                        <br>
                                        <button id="btnGenerateLabel" type="submit" name="btnGenerateLabel" class="btn btn-primary btn-ladda">
                                            <spring:message code="Generar Labels" /> <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <br>
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered" id="lista_muestras">
                            <thead>
                            <tr>
                                <th width="10%"></th>
                                <th width="20%"><spring:message code="sample.code" /></th>
                                <th width="20%"><spring:message code="participant" /></th>
                                <th width="15%"><spring:message code="sample.date" /></th>
                                <th width="15%"><spring:message code="fif" /></th>
                                <th width="10%"><spring:message code="house" /></th>
                                <th width="10%"><spring:message code="house" /> CHF</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>            <spring:message code="100" />                           </td>
                                <td>            <spring:message code="101" />                           </td>
                                <td>            <spring:message code="02/02/2021" />                           </td>
                                <td>            <spring:message code="01/02/2021" />                           </td>
                                <td>            <spring:message code="2" />                           </td>
                                <td>            <spring:message code="3" />                           </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <br>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <form action="#" autocomplete="off" id="print-form" class="form-horizontal">
                                <br>
                                <div class="row">
                                    <div class="col-md-12" align="center">
                                        <button id="btnPrint" type="submit" name="btnPrint" class="btn btn-success">
                                            <spring:message code="print.selected" /> <i class="fa fa-print"></i>
                                        </button>
                                        <br>
                                        <br>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="confirModal" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
                            <div><spring:message code="confirm" /></div>
                        </div>
                        <div class="modal-body">
                            <div><spring:message code="print.selected" />?</div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                            <button type="button" class="btn btn-info" id="okPrint" name="okPrint"><spring:message code="ok" /></button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
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
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
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
<!-- loading buttons-->
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<!-- JQUERY BAR CODE -->
<spring:url value="/resources/js/libs/jquery-barcode.min.js" var="jqueryBarCodeM" />
<script src="${jqueryBarCodeM}"></script>
<!-- JQUERY QR CODE -->
<spring:url value="/resources/js/libs/jquery.classyqr.min.js" var="jqueryQRCode" />
<script src="${jqueryQRCode}"></script>
<script>
    jQuery(document).ready(function() {

        handleDatePickers("${lenguaje}");
        //$('#tipoEtiquetas').select2();
        //$("#divEtiquetas").hide();
        $("#btnGenerate").hide();

        var table = $('#lista_muestras').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });

        var tt = new $.fn.dataTable.TableTools( table, {
            "sSwfPath": "${dataTablesTTSWF}",
            "sRowSelect": "multi"
        } );

        function parametroIngresado(){
            return $('#codigoParticipante').val().length > 0 || $('#codigoCasa').val().length > 0
            || $('#codigoMx').val().length > 0
            || $('#fechaInicio').val().length > 0 || $('#fechaFin').val().length > 0;
        }

        var form1 = $('#search-form');
        form1.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                codigoParticipante: {
                    pattern: /^\+?[0-9]*\.?[0-9]+$/,
                    maxlength: 5
                },
                codigoCasa: {
                    pattern: /^\+?[0-9]*\.?[0-9]+$/,
                    maxlength: 4
                },
                fechaFin: {required: function () {
                    return $('#fechaInicio').val().length > 0;
                }},
                fechaInicio: {required: function () {
                    return $('#fechaFin').val().length > 0;
                }},
                tipoEtiquetas: {
                    required:  function () {return !$('#registrado').is(':checked')}
                }
            },
            ignore: ':hidden:not("#tipoEtiquetas")',
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback col-md-4' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                   error.insertAfter( element ); //cuando no es input-group
                   //error.insertAfter(element.parent('.input-group'));
                }
            },
            highlight: function ( element, errorClass, validClass ) {
                $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                //$( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );//para toda la fila
                $( element ).parents( '.input-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );//para solo el elemento
            },
            unhighlight: function (element, errorClass, validClass) {
                $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                //$( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' ); //para toda la fila
                $( element ).parents( '.input-group' ).addClass( 'has-success' ).removeClass( 'has-danger' ); //para solo el elemento
            },
            submitHandler: function (form) {
                search();
            }
        });

        var form2 = $('#print-form');
        form2.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            },
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback col-md-4' );
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
                printSelected();
            }
        });

        $("#registrado").change(function() {
            form1.valid();
            if(this.checked) {
                $("#btnSearch").show();
                $("#btnGenerate").hide();
                $("#fechaInicio").prop('disabled', false).val("").parents( '.input-group' ).removeClass( 'has-success' ).removeClass( 'has-danger' );
                $("#fechaFin").prop('disabled', false).val("").parents( '.input-group' ).removeClass( 'has-success' ).removeClass( 'has-danger' );
                $("#codigoMx").prop('disabled', false).val("");
            }else{
                $("#btnGenerate").show();
                $("#btnSearch").hide();
                $("#fechaInicio").prop('disabled', true).val("").parents( '.input-group' ).removeClass( 'has-success' ).removeClass( 'has-danger' );
                $("#fechaFin").prop('disabled', true).val("").parents( '.input-group' ).removeClass( 'has-success' ).removeClass( 'has-danger' );
                $("#codigoMx").prop('disabled', true).val("");
            }
        });

        $('#okPrint').click(function () {
            var oTT = TableTools.fnGetInstance('lista_muestras');
            var aSelectedTrs = oTT.fnGetSelected();
            var len = aSelectedTrs.length;
            var codigos="";
            //var codesLabLineal = [];
            for (var i = 0; i < len; i++) {
                //console.log(table.row(aSelectedTrs[i]).data());
                var codigoLab = table.row(aSelectedTrs[i]).data()[1];
                var fif = table.row(aSelectedTrs[i]).data()[4];
                var ftoma = table.row(aSelectedTrs[i]).data()[3];
                var inputTipo = codigoLab.substring(codigoLab.lastIndexOf("<"), codigoLab.length);
                var codigoCasa = table.row(aSelectedTrs[i]).data()[6];
                var tipoCodigo = $(inputTipo).val();
                codigoLab = codigoLab.substring(0,codigoLab.lastIndexOf("<")).trim();
                var codigo = codigoLab;
                if (tipoCodigo==="1") {
                    codigo = fif + "" + ftoma + "  *" + codigoLab + "*" + codigoCasa + "*1*" + tipoCodigo;
                }else{
                    codigo = codigoLab + "*1*" + tipoCodigo;
                }
                if (i + 1 < len) {
                    codigos += codigo + ",";
                } else {
                    codigos += codigo;
                }
            }
            imprimir(codigos);
        });

        function printSelected() {
            var oTT = TableTools.fnGetInstance('lista_muestras');
            var aSelectedTrs = oTT.fnGetSelected();
            var len = aSelectedTrs.length;
            if (len > 0) {
                $('#confirModal').modal('show');

            } else {
                toastr.warning("${seleccioneMuestra}");
            }
        }
            $("#btnGenerateLabel").on("click", function(){
                buscarLabel();
            });
        function buscarLabel(){
            if(parametroIngresado()){
                $.getJSON("${searchLabelUrl}", form1.serialize(), function (data) {
                            toastr.success("God");
                        });
            }else{
                toastr.warning("${ingresarParametro}");
            }

        }
        function search()
        {
            if (parametroIngresado()) {
                $.getJSON("${searchUrl}"
                        , form1.serialize()
                        , function (data) {
                            table.clear().draw();
                            var len = Object.keys(data).length;
                            if (data.mensaje === undefined) {
                                var settings = {
                                    moduleSize: 2,
                                    barHeight: 30,
                                    showHRI: false
                                };
                                for (var i = 0; i < len; i++) {
                                    var idDiv = "divBarcode" + i;
                                    table.row.add(
                                            ['<div align="center" id="' + idDiv + '"></div>',data[i].codLab + " <input type='hidden' value='" + data[i].tipo + "'/>", data[i].idMx, getFormattedDate(new Date(data[i].fechaTomaMx)), getFormattedDate(new Date(data[i].fif)),data[i].casa,data[i].casachf])
                                            .draw();
                                    //$('#' + idDiv + '').html("").show().barcode(codigo, "datamatrix", settings);
                                    if (data[i].tipo=="1") {
                                        var codigo = getFormattedDate(new Date(data[i].fif))+""+getFormattedDate(new Date(data[i].fechaTomaMx))+"  "+data[i].codLab;
                                        $('#' + idDiv + '').ClassyQR({
                                            create: true, // signals the library to create the image tag inside the container div.
                                            type: 'text', // text/url/sms/email/call/locatithe text to encode in the QR. on/wifi/contact, default is TEXT
                                            size: 35,
                                            text: codigo // the text to encode in the QR.
                                        });
                                    }else{
                                        $('#' + idDiv + '').html("").barcode(data[i].codLab, "codabar", settings);
                                    }
                                }

                                $(".dataTables_paginate").on('click', function () {
                                    var count = table.rows().data().length;
                                    for (var i = 0; i < count; i++) {
                                        var idDiv = "divBarcode" + i;
                                        var data2 = table.row(i).data();
                                        var codigoLab = data2[1];
                                        var fif = data2[4];
                                        var ftoma = data2[3];
                                        codigoLab = codigoLab.substring(0,codigoLab.lastIndexOf("<")).trim();
                                        var inputTipo = codigoLab.substring(codigoLab.lastIndexOf("<"), codigoLab.length);
                                        var tipoCodigo = $(inputTipo).val();
                                        if (tipoCodigo=="1") {
                                            var codigo = fif+""+ftoma+"  "+codigoLab;
                                            $('#' + idDiv + '').html("").ClassyQR({
                                                create: true, // signals the library to create the image tag inside the container div.
                                                type: 'text', // text/url/sms/email/call/locatithe text to encode in the QR. on/wifi/contact, default is TEXT
                                                size: 35,
                                                text: codigo // the text to encode in the QR.
                                            });
                                        }else{
                                            $('#' + idDiv + '').html("").barcode(codigoLab, "codabar", settings);
                                        }
                                    }
                                });

                            } else {
                                toastr.warning(data.mensaje);
                            }
                        }
                ).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                            toastr.error(errorThrown,"Error",{timeOut: 0});
                        });
            }else toastr.warning("${ingresarParametro}");
        }

        function getFormattedDate(date) {
            var year = date.getFullYear();

            var month = (1 + date.getMonth()).toString();
            month = month.length > 1 ? month : '0' + month;

            var day = date.getDate().toString();
            day = day.length > 1 ? day : '0' + day;

            return day + '/' + month + '/' +  year;
        }

        function imprimir(strBarCodes){
            $.getJSON("http://localhost:13001/print", {
                barcodes: strBarCodes,
                copias: 1,
                ajax:'false'
            }, function (data) {
                console.log(data);
                $('#confirModal').modal('toggle');
                toastr.success("etiquetas impresas");
            }).fail(function (jqXHR) {
                console.log(jqXHR);
                $('#confirModal').modal('toggle');
                if (jqXHR.status!=200 && jqXHR.status!=0) {
                    toastr.error("error al imprimir etiquetas","Error",{timeOut: 0});
                }
            });
        }

    });

</script>
</body>
</html>