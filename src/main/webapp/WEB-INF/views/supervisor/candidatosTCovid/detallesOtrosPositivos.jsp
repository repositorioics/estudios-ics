<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 28/09/2021
  Time: 09:50
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
    <jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <style>
        /*ini*/
        .toast-title {
            font-weight: bold;
        }
        .toast-message {
            -ms-word-wrap: break-word;
            word-wrap: break-word;
        }
        .toast-message a,
        .toast-message label {
            color: #ffffff;
        }
        .toast-message a:hover {
            color: #cccccc;
            text-decoration: none;
        }

        .toast-close-button {
            position: relative;
            right: -0.3em;
            top: -0.3em;
            float: right;
            font-size: 20px;
            font-weight: bold;
            color: #ffffff;
            -webkit-text-shadow: 0 1px 0 #ffffff;
            text-shadow: 0 1px 0 #ffffff;
            opacity: 0.8;
            -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=80);
            filter: alpha(opacity=80);
        }
        .toast-close-button:hover,
        .toast-close-button:focus {
            color: #000000;
            text-decoration: none;
            cursor: pointer;
            opacity: 0.4;
            -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=40);
            filter: alpha(opacity=40);
        }
        button.toast-close-button {
            padding: 0;
            cursor: pointer;
            background: transparent;
            border: 0;
            -webkit-appearance: none;
        }
        .toast-top-full-width {
            top: 0;
            right: 0;
            width: 100%;
        }
        .toast-bottom-full-width {
            bottom: 0;
            right: 0;
            width: 100%;
        }
        .toast-top-left {
            top: 12px;
            left: 12px;
        }
        .toast-top-right {
            top: 12px;
            right: 12px;
        }
        .toast-bottom-right {
            right: 12px;
            bottom: 12px;
        }
        .toast-bottom-left {
            bottom: 12px;
            left: 12px;
        }
        #toast-container {
            position: fixed;
            z-index: 999999;
            /*overrides*/

        }
        #toast-container * {
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }
        #toast-container > div {
            margin: 0 0 6px;
            padding: 15px 15px 15px 50px;
            width: 300px;
            -moz-border-radius: 3px 3px 3px 3px;
            -webkit-border-radius: 3px 3px 3px 3px;
            border-radius: 3px 3px 3px 3px;
            background-position: 15px center;
            background-repeat: no-repeat;
            -moz-box-shadow: 0 0 12px #999999;
            -webkit-box-shadow: 0 0 12px #999999;
            box-shadow: 0 0 12px #999999;
            color: #ffffff;
            opacity: 0.8;
            -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=80);
            filter: alpha(opacity=80);
        }
        #toast-container > :hover {
            -moz-box-shadow: 0 0 12px #000000;
            -webkit-box-shadow: 0 0 12px #000000;
            box-shadow: 0 0 12px #000000;
            opacity: 1;
            -ms-filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=100);
            filter: alpha(opacity=100);
            cursor: pointer;
        }

        #toast-container.toast-top-full-width > div,
        #toast-container.toast-bottom-full-width > div {
            width: 96%;
            margin: auto;
        }
        .toast {
            background-color: #030303;
        }
        .toast-success {
            background-color: #51a351;
        }
        .toast-error {
            background-color: #bd362f;
        }
        .toast-info {
            background-color: #2f96b4;
        }
        .toast-warning {
            background-color: #f89406;
        }
        /**/
        @media all and (max-width: 240px) {
            #toast-container > div {
                padding: 8px 8px 8px 50px;
                width: 11em;
            }
            #toast-container .toast-close-button {
                right: -0.2em;
                top: -0.2em;
            }
        }
        @media all and (min-width: 241px) and (max-width: 480px) {
            #toast-container > div {
                padding: 8px 8px 8px 50px;
                width: 18em;
            }
            #toast-container .toast-close-button {
                right: -0.2em;
                top: -0.2em;
            }
        }
        @media all and (min-width: 481px) and (max-width: 768px) {
            #toast-container > div {
                padding: 15px 15px 15px 50px;
                width: 25em;
            }
        }
        /*fin*/
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
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #008cba;
        }
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #008cba;
        }
        input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; }
        input[type="text"]{color: #000000; }
        input[type="select"]{color: #000000;}
    </style>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../../fragments/sideBar.jsp" />
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
            <spring:url value="/super/covid/listCandidates/" var="listaUrl"/>
            <spring:url value="/super/covid/saveCandidateForm/" var="saveCandidateFormUrl"/>
            <spring:url value="/super/covid/saveOtrosCandidateTCovid" var="saveOtrosCandidateTCovidUrl"/>
            <c:set var="successMessage"><spring:message code="process.success" /></c:set>
            <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
            <spring:url value="/covid/participants/{codigo}" var="participantsUrl">
                <spring:param name="codigo" value="${casoCovid19}" />
            </spring:url>
            <spring:url value="/covid/participants/{codigo}" var="listparticipantsUrl"/>
            <div class="">
            <div class="row">
            <div class="col-md-12 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-white pb-3"><spring:message code="others" /> <spring:message code="positive" /></h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block"> <spring:message code="Form" /></span>
                        </a>
                    </li>    </ul>
            </div>
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
            <div>

                 <div class="row">
                 <div class="col-md-6">
                     <table id="participant_casaChf" class="table table-bordered table-hover display nowrap" style="width:100%">
                         <thead>
                         <tr>
                             <th class="text-center"><spring:message code="select" /></th>
                             <th class="text-center"><spring:message code="code" /></th>
                             <th class="text-center"><spring:message code="chf.house" /></th>
                             <th class="text-center"><spring:message code="userstudies" /></th>
                         </tr>
                         </thead>
                         <tbody>
                         <c:forEach items="${procesos}" var="p">
                             <tr>
                                 <c:choose>
                                     <c:when test="${indice.participante.codigo == p.codigo}">
                                        <td>
                                               <div class="form-check">
                                                   <input class="form-check-input id" disabled type="radio" name="codigo" id="${p.codigo}" value="${p.codigo}">
                                                   <label class="form-check-label" for="${p.codigo}">
                                                           ${p.codigo}
                                                   </label>
                                               </div>
                                        </td>
                                     </c:when>
                                     <c:otherwise>
                                       <td>
                                               <div class="form-check">
                                                   <input class="form-check-input id" type="radio" name="codigo" id="${p.codigo}" value="${p.codigo}">
                                                   <label class="form-check-label" for="${p.codigo}">
                                                           ${p.codigo}
                                                   </label>
                                               </div>
                                       </td>
                                     </c:otherwise>
                                 </c:choose>
                                 <td class="codigo">${p.codigo}</td>
                                 <td class="casachf">${p.casaCHF}</td>
                                 <td class="estudio">${p.estudio}</td>
                             </tr>
                         </c:forEach>
                         </tbody>
                     </table>
                 </div>
                 <div class="col-md-6">
                     <div class="mt-1"></div>
                     <form name="frm_otros_positivos" id="frm_otros_positivos" class="mt-5" action="#" autocomplete="off">
                         <div class="form-row" hidden="hidden">
                             <div class="form-group col-md-3">
                                 <label for="casoIndice"><spring:message code="cases.index" /></label>
                                 <input type="text" class="form-control" id="casoIndice" name="casoIndice" value="${indice.codigo}" readonly>
                             </div>
                             <div class="form-group col-md-2">
                                 <label for="editando"><spring:message code="editando" /></label>
                                 <input type="text" class="form-control" id="editando" name="editando" value="${editando}" readonly>
                             </div>
                             <div class="form-group col-md-3">
                                 <label for="codigo_otro_positivo"><spring:message code="codigo_otro_positivo" /></label>
                                 <input type="text" class="form-control" id="codigo_otro_positivo" name="codigo_otro_positivo" value="${caso.codigo}" readonly>
                             </div>
                             <div class="form-group col-md-3">
                                 <label for="fecha_ingreso"><spring:message code="fecha_ingreso" /></label>
                                 <input type="text" class="form-control" id="fecha_ingreso" name="fecha_ingreso" value="<fmt:formatDate value="${indice.fechaIngreso}" pattern="dd/MM/yyyy" />" readonly>
                             </div>
                         </div>
                         <div class="form-row">
                             <div class="form-group col-md-4">
                                 <label for="idparticipante"><spring:message code="code" /></label>
                                 <input type="text" class="form-control" id="idparticipante" name="idparticipante" value="${caso.codigo_participante}"  readonly>
                             </div>
                             <div class="form-group col-md-4">
                                 <label for="estudio"><spring:message code="study" /></label>
                                 <input type="text" class="form-control" id="estudio" name="estudio" readonly value="${caso.estActuales}">
                             </div>
                             <div class="form-group col-md-4">
                                 <label for="casaChf"><spring:message code="chf.house" /></label>
                                 <input type="text" class="form-control" id="casaChf" name="casaChf" readonly value="${caso.casaCHF}">
                             </div>
                         </div>

                         <div class="form-group row">
                             <label class="col-md-3 form-control-label" for="positivoPor"><spring:message code="lbl.positive.by"/><span class="required">*</span></label>
                             <div class="col-md-9">
                                 <select name="positivoPor" id="positivoPor" class="form-control" required="required">
                                     <option selected value=""><spring:message code="select" />...</option>
                                     <c:forEach items="${positivoPor}" var="cat">
                                         <c:choose>
                                             <c:when test="${caso.positivoPor eq cat.catKey}">
                                                 <option selected value="${cat.catKey}">${cat.spanish}</option>
                                             </c:when>
                                             <c:otherwise>
                                                 <option value="${cat.catKey}">${cat.spanish}</option>
                                             </c:otherwise>
                                         </c:choose>
                                     </c:forEach>
                                 </select>
                             </div>
                         </div>
                         <div class="form-group row">
                             <label class="form-control-label col-md-3" for="FIS"><spring:message code="FIS" />
                                     <span class="required">
                                          *
                                     </span>
                             </label>
                             <div class="input-group col-md-9">
                                         <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                         </span>
                                 <input name="fis" id="fis" class="form-control date-picker" type="text" data-date-end-date="+0d" required="required"
                                        value="<fmt:formatDate value="${caso.fis}" pattern="dd/MM/yyyy" />"/>
                             </div>
                         </div>
                         <div class="form-group row">
                             <label class="form-control-label col-md-3" for="fif"><spring:message code="fif" /> </label>
                             <div class="input-group col-md-9">
                                         <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                         </span>
                                 <input name="fif" id="fif" class="form-control date-picker" type="text" data-date-end-date="+0d"
                                        value="<fmt:formatDate value="${caso.fif}" pattern="dd/MM/yyyy" />"/>
                             </div>
                         </div>
                         <div class="d-flex justify-content-between mt-5">
                             <div class="p-2 bd-highlight">
                                 <button id="guardar" type="submit" class="btn btn-primary btn-lg btn-ladda" data-style="expand-right">
                                     <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>
                             </div>
                             <div class="p-2 bd-highlight"></div>
                             <div  id="tag-cloud-widget" class="p-2 bd-highlight">
                                 <button type="submit" id="cancel" class="btn btn-warning btn-lg btn-ladda" data-style="expand-right">
                                     <i class="fa fa-minus-circle" aria-hidden="true"></i> <spring:message code="cancel" /></button>
                             </div>
                         </div>
                     </form>


                 </div>
             </div>

            </div>
            </div>

            </div>
            </div>
            </div>
            </div>
            </div>


            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../../fragments/bodyFooter.jsp" />
<jsp:include page="../../fragments/corePlugins.jsp" />
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>


<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>

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
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/views/covid19/otrosPositivosCovid.js" var="processCovidJs" />
<script src="${processCovidJs}"></script>
<script>
    $(document).ready(function(){
        var parametros = {
            listaUrl                    : "${listaUrl}",
            saveOtrosCandidateTCovidUrl : "${saveOtrosCandidateTCovidUrl}",
            successmessage              : "${successMessage}",
            error                       : "${errorProcess}",
            editOtroPositivoUrl         : "${editOtroPositivoUrl}",
            setPasivoUrl                : "${setPasivoUrl}",
            saveCandidateFormUrl        : "${saveCandidateFormUrl}",
            dataTablesLang              : "${dataTablesLang}",
            participantsUrl             : "${participantsUrl}"
        };
        handleDatePickers("${lenguaje}");
        GuardarOtrosPositivos.init(parametros);

        var table = $('#participant_casaChf').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
                "columnDefs": [{
                    targets: 0,
                    className: 'text-center'
                }, {
                    targets: 1,
                    className: 'text-center'
                },{
                    targets: 2,
                    className: 'text-center'
                },{
                    targets: 3,
                    className: 'text-center'
                }],
                "scrollY": 200,
                "scrollX": true
        });
        $("#participant_casaChf").on('change', '.id', function(e) {
            var id = this.id;
            var currentRow = $(this).closest("tr");
            var col1 = currentRow.find(".codigo").html();
            var col2 = currentRow.find(".casachf").html();
            var col3 = currentRow.find(".estudio").html();
            $("#idparticipante").val(col1);
            $("#casaChf").val(col2);
            $("#estudio").val(col3);
            $("#"+$(this).val()).prop('disabled', true);
            var cod = $(this).val();
            var nuevovalor = new Array();
            var getlocal = localStorage.getItem("datos");
            var cont;
            var parslocal;
            if($(this).is(":checked")){
                if(getlocal != null && getlocal != "" && getlocal != false && getlocal != undefined){
                    parslocal = JSON.parse(getlocal);
                    $.each(parslocal, function(index, value){
                        cont = index;
                        nuevovalor[index] = value;
                    });
                    cont++;
                    nuevovalor[cont] = cod;
                    localStorage.setItem("datos", JSON.stringify(nuevovalor));
                } else {
                    var saveLocal = new Array();
                    saveLocal[0] = cod;
                    localStorage.setItem("datos", JSON.stringify(saveLocal));
                }
            }else {
                if(getlocal != null && getlocal != "" && getlocal != false && getlocal != undefined){
                    parslocal = JSON.parse(getlocal);
                    var contador = 0;
                    $.each(parslocal, function(index, value){
                        cont = index;
                        if(value != cod && value != null && value != undefined && value != false){
                            nuevovalor[contador] = value;
                            contador++;
                        }
                    });
                    if(cont == 0){
                        localStorage.removeItem("datos");
                    }else{
                        localStorage.setItem("datos", JSON.stringify(nuevovalor));
                    }
                }
            }
        });
        GetDatos();
        function GetDatos(){
            var getlocal = localStorage.getItem("datos");
            parslocal = JSON.parse(getlocal);
            $.each(parslocal, function(index, value){
                console.info("index: "+index + " valor: "+value);
                $("#"+value).prop('disabled', true);
            });
        }
        $("#cancel").on("click",function(e) {
            e.preventDefault();
            localStorage.removeItem('datos');
            setTimeout(function () {
                window.location.href = parametros.saveCandidateFormUrl;
            }, 1000);
        });
    });
</script>
</body>
</html>