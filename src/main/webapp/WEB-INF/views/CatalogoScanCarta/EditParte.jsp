<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 10/05/2020
  Time: 18:31
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
        #page-loader {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 1000;
            background: #FFF none repeat scroll 0% 0%;
            z-index: 99999;
        }

        #page-loader .preloader-interior {
            display: block;
            position: relative;
            left: 50%;
            top: 50%;
            width: 150px;
            height: 150px;
            margin: -75px 0 0 -75px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #3498db;

            -webkit-animation: spin 2s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 2s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        #page-loader .preloader-interior:before {
            content: "";
            position: absolute;
            top: 5px;
            left: 5px;
            right: 5px;
            bottom: 5px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #e74c3c;

            -webkit-animation: spin 3s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 3s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        #page-loader .preloader-interior:after {
            content: "";
            position: absolute;
            top: 15px;
            left: 15px;
            right: 15px;
            bottom: 15px;
            border-radius: 50%;
            border: 3px solid transparent;
            border-top-color: #f9c922;

            -webkit-animation: spin 1.5s linear infinite;
            /* Chrome, Opera 15+, Safari 5+ */
            animation: spin 1.5s linear infinite;
            /* Chrome, Firefox 16+, IE 10+, Opera */
        }

        @-webkit-keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(0deg);
                /* IE 9 */
                transform: rotate(0deg);
                /* Firefox 16+, IE 10+, Opera */
            }

            100% {
                -webkit-transform: rotate(360deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(360deg);
                /* IE 9 */
                transform: rotate(360deg);
                /* Firefox 16+, IE 10+, Opera */
            }
        }

        @keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(0deg);
                /* IE 9 */
                transform: rotate(0deg);
                /* Firefox 16+, IE 10+, Opera */
            }

            100% {
                -webkit-transform: rotate(360deg);
                /* Chrome, Opera 15+, Safari 3.1+ */
                -ms-transform: rotate(360deg);
                /* IE 9 */
                transform: rotate(360deg);
                /* Firefox 16+, IE 10+, Opera */
            }
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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/CatalogoParte/ListadoParte" htmlEscape="true "/>">
                    <spring:message code="Listado" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(editParteUrl)}">
                    <spring:message code="Editar Información" />
                </a>
                <i class="fa fa-angle-right"></i>
                <spring:message code="${part.parte}" />
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div id="page-loader">
                    <span class="preloader-interior"></span>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <i class="fa fa-window-restore" aria-hidden="true"></i>  Asignar Parte a la Versión.
                            </div>
                            <div class="card-block">
                                <spring:url value="/CatalogoParte/saveParte" var="saveParteUrl"/>
                                <spring:url value="/CatalogoParte/UpdateParte" var="updateParteUrl"/>
                                <spring:url value="/CatalogoParte/ListadoParte" var="ListadoParteUrl"/>
                                <spring:url value="/CatalogoParte/GetVersion" var="GetVersionUrl"/>
                                <spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
                                <form autocomplete="off" id="frmParte" name="frmParte" class="form-horizontal">
                                    <br/>
                                    <div hidden="hidden" class="form-group col-md-12">
                                        <label for="idparte">idparte</label>
                                        <input type="text" id="idparte" name="idparte" class="form-control" value="${part.idparte}" />
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="idcarta">Carta:</label>
                                        <select class="form-control" id="idcarta" name="idcarta" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${cartas}" var="c">
                                                <c:choose>
                                                    <c:when test="${part.version.carta.idcarta eq c.idcarta}">
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
                                        <label for="idcarta">Versión:</label>
                                        <select class="form-control" id="idversion" name="idversion" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${version}" var="v">
                                                <c:choose>
                                                    <c:when test="${v.idversion eq part.version.idversion}">
                                                        <option selected value="${v.idversion}"><spring:message code="${v.version}" /></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${v.idversion}"><spring:message code="${v.version}" /></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="parte">Parte:</label>
                                        <input type="text" class="form-control" id="parte" name="parte" required="required" value="${part.parte}">
                                    </div>

                                    <div class="form-group col-md-12">
                                        <div class="custom-control custom-checkbox">
                                            <c:choose>
                                                <c:when test="${part.activo eq 'true'}">
                                                    <input type="checkbox" class="custom-control-input" id="activo" name="activo" value="${part.activo}" checked="checked">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" class="custom-control-input" id="activo" name="activo">
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
                                                    <i class="fa fa-refresh" aria-hidden="true"></i> Actualizar</button>
                                            </div>
                                        </div>
                                        <div class="col-md-1">
                                            <div class="form-group">

                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <a class="btn btn-warning btn-block btn-lg" href="<spring:url value="/CatalogoParte/ListadoParte" htmlEscape="true "/>">
                                                    <i class="fa fa-undo" aria-hidden="true"></i>
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
        setTimeout(function () {
            $('#page-loader').fadeOut('slow');
        }, 1200);
        var parameters = {
            updateParteUrl: "${updateParteUrl}",
            ListadoParteUrl:"${ListadoParteUrl}",
            GetVersionUrl:"${GetVersionUrl}",
            GetParteUrl:"${GetParteUrl}",
            VersionCartatUrl: "${VersionCartatUrl}"
        };
        $("#idcarta").select2();
        $("#idversion").select2();
        var formP = $("#frmParte");
        $("#idcarta").on("change", function(){
            $("#idversion option").remove();
            $("#idversion").select2('val', '');
            ObtenerVersion(parameters);
        });
        function ObtenerVersion(dir){
            console.log(dir);
            var idcarta = document.getElementById('idcarta').value;
            var $version = $('#idversion');
            $version.empty();
            $.getJSON(dir.VersionCartatUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
                var len = data.objV.length;
                if(len == 0){
                    alert("Advertencia, Cartas no tiene Versión.");
                }else{
                    var d = data.objV;
                    $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                    $.each(d, function (i, val) {
                        $version.append($('<option></option>').val(val.idversion).html(val.version));
                    });
                }
            });
        };
        formP.validate({
            rules:{

                idcarta :{required: true},
                idversion :{required: true},
                parte:{required: true}
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
                UpdateParte(parameters);
            }
        });
        function UpdateParte(dir){
            $.post(dir.updateParteUrl, formP.serialize(), function(data){
                swal("Éxito!", "Información guardada!", "success")
                window.setTimeout(function(){
                    window.location.href = parameters.ListadoParteUrl;
                }, 1500);
            }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                swal("Error al guardar la información.","error");
            });
        }
    })
</script>
</body>
</html>
