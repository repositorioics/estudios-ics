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
    <jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <title></title>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../../fragments/sideBar.jsp" />
        <div class="main">
            <!-- Breadcrumb -->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/super/covid/listCandidates/" htmlEscape="true "/>"><spring:message code="covid19.candidates" /></a>
                </li>
            </ol>
            <c:set var="recordDisabledLabel"><spring:message code="recordDisabled" /></c:set>
            <c:set var="confirmar"><spring:message code="confirm" /></c:set>
            <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-list-alt"></i> <spring:message code="covid19.candidates.list" />
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <div class="col-md-12">
                                <a href="<spring:url value="/super/covid/saveCandidateForm" htmlEscape="true"/>" class="btn btn-success btn-lg">
                                    <i class="fa fa-plus" aria-hidden="true"></i><spring:message code="add" /> <spring:message code="candidate" /> </a>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <br/>
                            <hr/>
                            <div class="table-responsive">
                                <table id="lista_candidatos" class="table table-hover table-bordered">
                                    <thead>
                                    <tr>
                                        <th width="12%"><spring:message code="candidate" /></th>
                                        <th width="12%"><spring:message code="chf.house" /></th>
                                        <th width="12%"><spring:message code="dateCreated" /></th>
                                        <th width="12%"><spring:message code="lbl.positive.by" /></th>
                                        <th width="12%"><spring:message code="FIS" /></th>
                                        <th width="12%"><spring:message code="fif" /></th>
                                        <th width="12%"><spring:message code="consentimiento" /></th>
                                        <th width="16%"><spring:message code="actions" /></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${candidatos}" var="l">
                                        <spring:url value="/super/covid/candidate/actions/disable/{codigo}"
                                                    var="disableUrl">
                                            <spring:param name="codigo" value="${l.codigo}*${l.participante.codigo}" />
                                        </spring:url>
                                        <spring:url value="/super/covid/editCandidate/{codigo}"
                                                    var="editUrl">
                                            <spring:param name="codigo" value="${l.codigo}" />
                                        </spring:url>
                                        <tr>
                                            <td><c:out value="${l.participante.codigo}" /></td>
                                            <td><c:out value="${l.casaCHF}" /></td>
                                            <td><fmt:formatDate value="${l.recordDate}" pattern="dd/MM/yyyy" /></td>
                                            <td>
                                                <c:forEach items="${positivoPor}" var="cat">
                                                    <c:if test="${cat.catKey eq l.positivoPor}">
                                                        <c:out value="${cat.spanish}" />
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td><fmt:formatDate value="${l.fis}" pattern="dd/MM/yyyy" /></td>
                                            <td><fmt:formatDate value="${l.fif}" pattern="dd/MM/yyyy" /></td>
                                            <td><c:out value="${l.consentimiento}" /></td>
                                            <td align="center">
                                                <c:choose>
                                                    <c:when test="${l.pasive=='1'}">
                                                        <button title="<spring:message code="edit" />" class="btn btn-outline-primary btn-sm" disabled><i class="fa fa-edit"></i></button>
                                                        <button title="<spring:message code="disable" />" class="btn btn-outline-primary btn-sm" disabled><i class="fa fa-trash-o"></i></button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a title="<spring:message code="edit" />" href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
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

<jsp:include page="../../fragments/bodyFooter.jsp" />
<jsp:include page="../../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>

<spring:url value="/resources/js/libs/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>

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

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script>
    jQuery(document).ready(function() {
        $('#lista_candidatos').DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            }
        });
    });

    if ("${deshabilitado}"){
        toastr.error("${recordDisabledLabel}");
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

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>
