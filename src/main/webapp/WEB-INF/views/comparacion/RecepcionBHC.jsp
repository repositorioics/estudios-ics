<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss"/>
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <style>
        .bg-primary {
            background-color: #eee!important;
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
            border-bottom: 4px solid #ffffff;
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
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled:hover,
        .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #eee;
        }
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #eee;
        }
        .btn-raised {
            transition: box-shadow .4s cubic-bezier(.25, .8, .25, 1), transform .4s cubic-bezier(.25, .8, .25, 1);
            box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .26)
        }

        .btn-raised:not([disabled]):active,
        .btn-raised:not([disabled]):focus,
        .btn-raised:not([disabled]):hover {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, .4);
            transform: translate3d(0, -1px, 0)
        }

        .shadow {
            overflow: hidden;
            position: relative;
            transform: translate3d(0, 0, 0)
        }

        .shadow:before {
            content: "";
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            width: auto;
            height: auto;
            pointer-events: none;
            background-image: radial-gradient(circle, #000 10%, transparent 10.01%);
            background-repeat: no-repeat;
            background-position: 50%;
            transform: scale(10, 10);
            opacity: 0;
            transition: transform .5s, opacity 1.5s
        }

        .shadow:active:before {
            transform: scale(0, 0);
            opacity: .1;
            transition: 0s
        }
        .bg-white .fill {
            fill: #448bff
        }
        .mybtn:hover {
            color: #fff
        }
        .input-text:focus {
            box-shadow: 0px 0px 0px;
            border-color: #f8c146;
            outline: 0px
        }
        .form-control2 {
            border: 1px solid #f8c146
        }

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
            border-radius: 12px;
            /*width: calc(500px + 10 * ((100vw - 320px) / 680));*/
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.8)
        }
        .card-headermodal {
            border-radius: 12px !important
        }
        .modal-body .btn-danger {
            border-radius: 11px;
            box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.2)
        }
        .btn-light {
            background: transparent !important;
            border: 0px !important
        }

        .btn-light:hover {
            border-color: #fff !important
        }

        .btn-light:active {
            border-color: #fff !important
        }

        @media (max-width: 526px) {
            .cardmodal {
                width: unset
            }
        }
        #spanmodal {
            font-size: 30px !important
        }
        .modal-content {
            background: transparent !important
        }
        /*.form-control:disabled, .form-control[readonly] {
            background-color: #fff;
            opacity: 1;
            border-block-color: none;
            border: 0;
        }*/
        .badge-verde {
            color: #fff;
            background-color: #1BA407 !important;
        }
        .badge-cyan {
            color: #fff;
            background-color: #007bff;
        }
        .btn-primary {
            background-color: #3f51b5 !important;
            color: #fff
        }
        .rounded-pill {
            border-radius: 50rem !important;
        }
    </style>


    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>


    <spring:url value="/resources/css/ClockPicker.css" var="clockcss" />
    <link href="${clockcss}" rel="stylesheet" type="text/css"/>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="app-body">
<jsp:include page="../fragments/sideBar.jsp"/>
<!-- Main content -->
<div class="main">
<!-- Breadcrumb -->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home"/></a>
        <i class="fa fa-angle-right"></i>
        <a href="<spring:url value="/comparacion/bhc/" htmlEscape="true "/>"><spring:message code="Recepción BHC" /></a>
    </li>
</ol>
    <div class="container-fluid">
            <div class="animated fadeIn">
            <spring:url value="/comparacion/GuardarBhc" var="saveBHCUrl"/>
            <spring:url value="/comparacion/bhc" var="bhcUrl"/>
            <div class="">
            <div class="row">
            <div class="col-md-12 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-dark pb-3"style="font-family: Roboto">
                    <i class="fa fa-tint" aria-hidden="true"></i> <spring:message code="Recepción BHC"/> </h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#sent"  role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block text-dark"> <spring:message code="Formulario"/> </span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#inbox" role="tab" aria-selected="false">
                            <span class="d-block d-md-none"><i class="ti-export"></i></span>
                            <span class="d-none d-md-block text-dark"> <spring:message code="Listado"/>  </span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">

            <div class="tab-pane fade active show" id="sent" aria-labelledby="sent-tab" role="tabpanel">
                <div class="p-6 text-dark">
                    <form id="FormBHC" method="post" autocomplete="off" name="FormBHC" role="form">
                        <div hidden="hidden" class="form-row">
                            <div class="form-group col-md-6">
                                <input type="text" class="form-control" id="accion" name="accion" value="${editando}"/>
                            </div>
                            <div class="form-group col-md-6">
                                <input type="text" class="form-control" id="fechaToEdit" name="fechaToEdit" value="<fmt:formatDate value="${caso.recBhcId.fechaRecBHC}" pattern="dd/MM/yyyy HH:mm:ss" />"/>
                            </div>
                        </div>
                        <div  class="form-row">
                            <div class="form-group col-md-4">
                                <label for="codigo">Código:</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext" name="codigo" id="codigo" value="${caso.recBhcId.codigo}" tabindex="1">
                                <!--<c:choose>
                                    <c:when test = "${editando eq true}">
                                        <input type="text" class="form-control focusNext" name="codigo" id="codigo" value="${caso.recBhcId.codigo}" readonly>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" class="form-control focusNext" name="codigo" id="codigo" tabindex="1">
                                    </c:otherwise>
                                </c:choose>-->
                            </div>
                            <div class="form-group col-md-4">
                                <label for="fechaBhc">Fecha BHC:</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext" name="fechaBhc" id="fechaBhc" data-date-end-date="+0d" tabindex="2"
                                       value="<fmt:formatDate value="${caso.recBhcId.fechaRecBHC}" pattern="dd/MM/yyyy" />">
                            </div>

                            <div class="form-group col-md-4">
                                <label for="fechaRegistBhc">Fecha Registro:</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="date_time form-control focusNext" name="fechaRegistBhc" id="fechaRegistBhc" tabindex="3"
                                       value="<fmt:formatDate value="${caso.fecreg}" pattern="dd/MM/yyyy HH:mm:ss" />">
                            </div>
                            <!--
                            <div class="form-group col-md-2">
                                <label for="fechaRegistBhc">Hora Registro:</label>
                                <div class="input-group clock">
                                    <input type="text" class="form-control focusNext" id="hora" name="hora" placeholder="Ahora" tabindex="4">
                                        <span class="input-group-addon">
                                            <span class="fa fa-clock-o"></span>
                                        </span>
                                </div>
                            </div>-->
                        </div>
                        <div class="">
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="lugar">Lugar:</label>
                                    <span class="required text-danger"> * </span>
                                    <select id="lugar" name="lugar" class="form-control focusNext" required="required" tabindex="5">
                                        <span class="required text-danger"> * </span>
                                        <option selected value=""><spring:message code="select"/>...</option>
                                        <c:forEach items="${lugar}" var="l">
                                            <c:choose>
                                                <c:when test="${caso.lugar eq l.spanish}">
                                                    <option selected value="${l.spanish}">${l.spanish}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${l.spanish}">${l.spanish}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="username">Usuario:</label>
                                    <span class="required text-danger"> * </span>
                                    <select id="username" name="username" class="form-control focusNext" required="required" tabindex="6">
                                        <option selected value=""><spring:message code="select"/>...</option>
                                        <c:forEach items="${usuarios}" var="u">
                                            <c:choose>
                                                <c:when test="${caso.username eq u.username}">
                                                    <option selected value="${u.username}">${u.username}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${u.username}"> ${u.username}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label for="observacion">Observación:</label>
                                <input type="text" class="form-control focusNext" id="observacion" name="observacion" value="${caso.observacion}"
                                       placeholder="Observación" tabindex="7">
                            </div>

                            <div class="form-group col-md-4">
                                <label for="volumen">Vólumen:</label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext" name="volumen" id="volumen" value="${caso.volumen}" tabindex="8">
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${caso.paxgene eq true}">
                                        <input class="form-check-input" type="checkbox" name="chkPaxGene" id="chkPaxGene" checked="checked">
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="checkbox" name="chkPaxGene" id="chkPaxGene" >
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label" for="chkPaxGene"> <spring:message code="PaxGene" /></label>
                            </div>


                            <div class="form-check form-check-inline">
                                <c:choose>
                                    <c:when test="${caso.estado eq '1'}">
                                        <input class="form-check-input" type="checkbox" name="chkEstado" id="chkEstado" checked="checked">
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="checkbox" name="chkEstado" id="chkEstado">
                                    </c:otherwise>
                                </c:choose>
                                <label class="form-check-label" for="chkEstado"><spring:message code="Estado" /></label>
                            </div>

                        </div>

                        <div class="form-row">
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary btn-lg btn-block btn-raised shadow rounded-pill">
                                    <i class="fa fa-save" aria-hidden="true"></i>   <spring:message code="save" />
                                </button>
                            </div>
                            <div class="col-md-4">
                            </div>
                            <div class="col-md-4">
                                <a href="${fn:escapeXml(bhcUrl)}" class="btn btn-warning btn-lg btn-block btn-raised shadow rounded-pill">
                                    <i class="fa fa-ban" aria-hidden="true"></i>
                                    <spring:message code="Limpiar Formulario" />
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="tab-pane fade" id="inbox" aria-labelledby="inbox-tab" role="tabpanel"><div>
                <div class="row p-4 no-gutters align-items-center"></div>
                <spring:url value="/comparacion/deletebhc" var="deletebhcUrl"/>
                <spring:url value="/comparacion/editBhc" var="editBhcUrl"/>
                <!-- Mail list-->
                <div class="table-responsive">
                    <table id="tblbhc" class="table email-table no-wrap table-hover v-middle mb-0 font-14">
                        <thead>
                        <th width="11%"><spring:message code="code" /></th>
                        <th><spring:message code="dateAdded" /> </th>
                        <th><spring:message code="lbl.State" /> </th>
                        <th><spring:message code="logindate" /> </th>
                        <th><spring:message code="lbl.Place" /> </th>
                        <th> <spring:message code="observacion" /></th>
                        <th><spring:message code="lbl.Paxgene" /></th>
                        <th><spring:message code="username" /></th>
                        <th> <spring:message code="volumen" /> </th>
                        <th width="5%"><spring:message code="actions" /></th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaBhc}" var="c">
                                <spring:url value="/comparacion/editBhc/{id}/{fechaRecBHC}" var="editbhcUrl">
                                    <spring:param name="id" value="${c.recBhcId.codigo}" />
                                    <spring:param name="fechaRecBHC" value="${c.recBhcId.fechaRecBHC}"  />
                                </spring:url>
                            <tr>
                                <td><c:out value="${c.recBhcId.codigo}" /></td>
                                <td><fmt:formatDate value="${c.recBhcId.fechaRecBHC}" pattern="dd/MM/yyyy" /></td>
                                <c:choose>
                                    <c:when test="${c.estado eq '1' }">
                                        <td><span class="badge badge-cyan" style="font-size: 15px !important; font-family: Roboto; border-radius: 10px"><spring:message code="CHF_CAT_SINO_SI" /></span></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><span class="badge badge-danger" style="font-size: 15px !important; font-family: Roboto; border-radius: 10px"><spring:message code="CHF_CAT_SINO_NO" /></span></td>
                                    </c:otherwise>
                                </c:choose>
                                <td><fmt:formatDate value="${c.fecreg}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                                <td><c:out value="${c.lugar}" /></td>
                                <td><c:out value="${c.observacion}" /></td>
                                <c:choose>
                                  <c:when test="${c.paxgene}">
                                      <td><span class="badge badge-verde" style="font-size: 15px !important; font-family: Roboto; border-radius: 10px;"><i class="fa fa-check" aria-hidden="true"></i></span></td>
                                </c:when>
                                <c:otherwise>
                                    <td><span class="badge badge-verde" style="font-size: 15px !important; font-family: Roboto; border-radius: 10px"><i class="fa fa-times" aria-hidden="true"></i></span></td>
                                </c:otherwise>
                                </c:choose>
                                <td><c:out value="${c.username}" /></td>
                                <td><c:out value="${c.volumen}" /></td>
                                <td>
                                    <button id="swalDelete" title="<spring:message code="Eliminar" />" data-toggle="tooltip" data-placement="bottom" class="btn btn-danger btn-sm salida">
                                        <i class="fa fa-trash text-white" aria-hidden="true"></i></button>
                                    <a href="${fn:escapeXml(editbhcUrl)}"  data-toggle="tooltip" data-placement="bottom" title="<spring:message code="edit" />" class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></a>
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
            </div>
            </div>
            </div>
    </div>
<!-- /.conainer-fluid -->

<div class=""><!-- /.id="my-modal" -->
        <div id="my-modal2" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content border-0">
                <div class="modal-body p-0">
                    <div class="card border-0 p-sm-3 p-2 justify-content-center">
                        <div class="card-header pb-0 bg-white border-0 ">
                            <div class="row">
                                <div class="col ml-auto"><button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span id="spanmodal" aria-hidden="true">&times;</span> </button></div>
                            </div>
                            <p class="font-weight-bold mb-2"> <i class="fa fa-trash" aria-hidden="true"></i> Deseas eliminar el Registro? </p>
                            <hr/>
                            <p class="text-muted "></p>
                        </div>
                        <div class="card-body px-sm-4 mb-2 pt-1 pb-0">
                            <form id="form-delete-bhc" name="form-delete-bhc">
                                <div class="form-row">
                                    <div class="col-6">
                                        <label class="text-muted label-control" for="idbhc">Código</label>
                                        <input type="text" class="form-control" id="idbhc" name="idbhc" readonly>
                                    </div>
                                    <div class="col-6">
                                        <label class="label-control text-muted" for="datebhc">Fecha</label>
                                        <input type="text" class="form-control" id="datebhc" name="datebhc"  readonly>
                                    </div>
                                </div>
                            </form>
                            <br/>
                            <div class="row justify-content-end no-gutters">
                                <div class="col-auto pull-left"><button type="button" class="btn btn-light text-muted " data-dismiss="modal"><spring:message code="Limpiar Formulario" /></button></div>
                                <div class="col-auto"><button id="btnDelete" type="button" class="btn btn-danger px-4 pull-right" data-dismiss="modal"><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</button></div>
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
<jsp:include page="../fragments/bodyFooter.jsp"/>
<jsp:include page="../fragments/corePlugins.jsp"/>

<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin"/>
<script src="${datepickerPlugin}"></script>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App"/>
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs"/>
<script type="text/javascript" src="${selectJs}"></script>
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

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs"/>
<script src="${validateJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs"/>
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${jQValidationLoc}"></script>

<spring:url value="/resources/js/views/comparacion/bhc.js" var="RecepcionBHC"/>
<script type="application/javascript" src="${RecepcionBHC}"></script>

<spring:url value="/resources/js/libs/jquery.maskedinput.js" var="Mascara"/>
<script src="${Mascara}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/jquery.maskedinput.js" var="maskJs" />
<script type="text/javascript" src="${maskJs}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sweet" />
<script type="text/javascript" src="${sweet}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>


<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
    jQuery(document).ready(function () {
        var hoy = moment().format('DD/MM/YYYY');
        $("#fechaBhc").datepicker({
            format: "dd/mm/yyyy",
            todayBtn: true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });
        $("#fechaRegistBhc").mask("99/99/9999 99:99:99");
        $("#lugar").select2();
        $("#username").select2();
        $('#volumen').on('change', function () {
            if (isNaN(this.value)) {
                this.value = "";
            } else {
                this.value = parseFloat(this.value).toFixed(2);
            }
        });
        var direciones = {
            saveBHCUrl: "${saveBHCUrl}",
            searchPartUrl: "${searchPartUrl}",
            editbhcUrl:"${editbhcUrl}",
            deletebhcUrl:"${deletebhcUrl}",
            bhcUrl: "${bhcUrl}",
            dataTablesLang:"${dataTablesLang}"
        };
        GuardarBhc.init(direciones);
        BuscarParticipantebhc.init(direciones);

        $("#codigo").focus();
    });
</script>
</body>
</html>
