<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 10/05/2020
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <style>



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

                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                Asignar Versión a la Carta.
                            </div>
                            <div class="card-block">
                                <spring:url value="/CatalogoVersion/saveVersion" var="saveVersionUrl"/>
                                <spring:url value="/CatalogoVersion/UpdateVersion" var="updateVersionUrl"/>
                                <spring:url value="/CatalogoVersion/ListadoVersion" var="ListadoVersionUrl"/>
                                <form autocomplete="off" id="frmVersion" name="frmVersion" class="form-horizontal">
                                    <br/>
                                    <div hidden="hidden" class="form-group col-md-6">
                                        <label for="idversion">Versión:</label>
                                        <input type="text" class="form-control" id="idversion" name="idversion" value="${vers.idversion}">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="idcarta">Carta:</label>
                                        <select class="form-control" id="idcarta" name="idcarta" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${cartas}" var="c">
                                                <c:choose>
                                                    <c:when test="${c.idcarta eq vers.carta.idcarta}">
                                                        <option selected value="${c.idcarta}"><spring:message code="${c.carta}" /></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${c.idcarta}"><spring:message code="${c.carta}" /></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="version">Versión:</label>
                                        <input type="text" class="form-control" id="version" name="version" required="required" value="${vers.version}">
                                    </div>

                                    <div class="form-group col-md-12">
                                        <div class="custom-control custom-checkbox">
                                            <c:choose>
                                                <c:when test="${vers.activo eq 'true'}">
                                                    <input type="checkbox" id="activo" name="activo" class="custom-control-input" value="${vers.activo}"  checked="checked">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="activo" name="activo" class="custom-control-input">
                                                </c:otherwise>
                                            </c:choose>
                                            <label class="custom-control-label" for="activo">Activo</label>
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <button type="submit" id="btnSave" class="btn btn-info btn-block btn-lg">
                                                    <i class="fa fa-save"></i> Guardar</button>
                                            </div>
                                        </div>
                                        <div class="col-md-1">
                                            <div class="form-group">

                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/CatalogoVersion/ListadoVersion" htmlEscape="true "/>">
                                                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                    <spring:message code="Cancelar" /></a>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">

                                            </div>
                                        </div>
                                    </div>
                                </form>
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
<!-- GenesisUI main scripts -->

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

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


<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<script type="text/javascript">
    $(document).ready(function(){

        var parameters = {
            updateVersionUrl: "${updateVersionUrl}",
            ListadoVersionUrl:"${ListadoVersionUrl}",
            GetVersionUrl:"${GetVersionUrl}"
        };
        $("#idcarta").select2();
        var formV = $("#frmVersion");
        formV.validate({
            rules:{
                idcarta :{required: true},
                version :{required: true}
            },
            errorElement: 'em',
            errorPlacement: function ( error, element ) {
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    error.insertAfter( element );
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
                UpdateVersion(parameters);
            }
        });
        function UpdateVersion(dir){
            $.post(dir.updateVersionUrl, formV.serialize(), function(data){
                swal("Éxito!", "Información guardada!", "success");
                window.setTimeout(function(){
                    window.location.href = parameters.ListadoVersionUrl;
                }, 1500);
            }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                swal("Error al guardar la información.","error");
            });
        }
    })
</script>
</body>
</html>
