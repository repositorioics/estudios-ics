<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 19/11/2021
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/multi-select.css" var="multiselectCss" />
    <link href="${multiselectCss}" rel="stylesheet" type="text/css"/>
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
                <c:if test="${editando}">
                    <c:set var="successMessage"><spring:message code="user.updated" /></c:set>
                </c:if>
                <c:if test="${agregando}">
                    <c:set var="successMessage"><spring:message code="user.created" /></c:set>
                </c:if>
                <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
            <div class="card-header">
                <h3 class="page-title">
                    <spring:message code="Personal" />
                    <small>
                        <c:choose>
                            <c:when test="${agregando}">
                                <spring:message code="add" />
                            </c:when>
                            <c:otherwise>
                                <spring:message code="edit" />
                            </c:otherwise>
                        </c:choose>
                    </small>
                </h3>
            </div>
            <div class="card-block">
            <div class="row">
            <div class="col-md-2">
            </div>
            <div class="col-md-8">
            <form action="#" autocomplete="off" id="persona_cargo_form" class="form-horizontal" style="border: 1px">
                <spring:url value="/admin/personal/savePerson" var="savePersonUrl"/>
                <spring:url value="/admin/personal/list" var="personListUrl"/>
            <div class="form-group row">
                <label class="form-control-label col-md-3" for="codigoPersonal"><spring:message code="code" />
                    <span class="required">*</span>
                </label>
                <div class="input-group col-md-9">
                   <span class="input-group-addon">
						<i class="fa fa-code"></i>
					</span>
                    <c:choose>
                        <c:when test="${editando eq true}">
                            <input id="codigoPersonal" name="codigoPersonal" type="text" value="${persona.idpersonal}" readonly class="form-control"/>
                        </c:when>
                        <c:otherwise>
                            <input id="codigoPersonal" name="codigoPersonal" type="text" value="${persona.idpersonal}"  class="form-control"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row password-strength">
                <label class="form-control-label col-md-3" for="completeName"><spring:message code="lbl.names.surnames" />
                    <span class="required"> * </span>
                </label>
                <div class="input-group col-md-9">
                     <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input id="completeName" name="completeName" type="text" value="${persona.nombreApellido}"  class="form-control"/>
                </div>
            </div>
                    <div class="form-group row">
                        <label class="form-control-label col-md-3" for="studies"><spring:message code="lbl.Position" />
                            <span class="required"> *</span>
                        </label>

                        <div class="input-group col-md-9">
                            <select multiple="multiple" class="multi-select" id="cargos" name="cargos">
                                <c:forEach items="${cargosPersona}" var="study" varStatus="stat">
                                    <c:set var="estudiosUsuario" value="${stat.first ? '' : cargosPersona} ${study.cargo.idcargo}" />
                                </c:forEach>
                                <c:forEach items="${cargos}" var="estudio">
                                    <c:choose>
                                        <c:when test="${fn:contains(estudiosUsuario, estudio.idcargo)}">
                                            <option selected value="${estudio.idcargo}">${estudio.idcargo} - ${estudio.nombreCargo}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${estudio.idcargo}">${estudio.idcargo} - ${estudio.nombreCargo}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                <div class="form-group row">
                    <div class="col-md-3"></div>
                    <div class="col-md-9">

                        <div class="d-flex justify-content-between">
                            <div class="p-2 bd-highlight">
                                <button id="guardar" type="submit" class="btn btn-success btn-lg btn-block btn-ladda" data-style="expand-right">
                                    <i class="fa fa-save" aria-hidden="true"></i>
                                    <spring:message code="save" /></button>
                            </div>
                            <div class="p-2 bd-highlight"></div>
                            <div class="p-2 bd-highlight">
                                <a href="${fn:escapeXml(personListUrl)}" class="btn btn-warning btn-lg btn-block btn-ladda" data-style="expand-right">
                                    <i class="fa fa-minus-circle"></i>
                                    <spring:message code="cancel" /></a>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
            </form>
            </div>
            <div class="col-md-2">
            </div>
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
<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- jQuery Password Meter-->
<spring:url value="/resources/js/libs/pwstrength.js" var="pwStrength" />
<script src="${pwStrength}" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- GenesisUI main scripts -->
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
<spring:url value="/resources/js/libs/jquery.multi-select.js" var="jQueryMultiSelect" />
<script type="text/javascript" src="${jQueryMultiSelect}"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/views/users/process-user.js" var="editUserJs" />
<script src="${editUserJs}" type="text/javascript"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<script>
    $(document).ready(function(){
        var parametros ={
            savePersonUrl   : "${savePersonUrl}",
            successmessage  : "${successMessage}",
            error           : "${errorProcess}",
            personListUrl   : "${personListUrl}"
        };
        var handleMultiSelect = function () {
            $('#cargos').multiSelect();
        };
        handleMultiSelect();
        var form1 = $('#persona_cargo_form');
        form1.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                codigoPersonal:{
                    required: true,
                    number:true
                },
                completeName: {
                    minlength: 3,
                    maxlength: 250,
                    required: true
                },
                cargos: {
                    required: true
                }
            },
            errorPlacement: function ( error, element ) {
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
                processUser();
            }
        });

        function processUser(){
            $.post( parametros.savePersonUrl, form1.serialize(), function( data ) {
                  usuario = JSON.parse(data);
                        console.warn(usuario);
                debugger;
                         if (usuario.nombre ===undefined) {
                            toastr.error(usuario.msj,"Error",{timeOut: 6000});
                         }
                        else{
                            toastr.success(parametros.successmessage,usuario.nombre);
                        }
                        window.setTimeout(function(){
                            window.location.href = parametros.personListUrl;
                        }, 1500);
                    }, 'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error( "error:" + errorThrown, {timeOut:6000});
                    });
            $('#completeName').focus();
        }
    });
</script>
</body>
</html>