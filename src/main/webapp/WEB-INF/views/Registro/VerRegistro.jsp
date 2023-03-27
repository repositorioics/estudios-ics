<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 17/08/2020
  Time: 14:12
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

    <spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
    <link href="${uiCss}" rel="stylesheet" type="text/css"/>

    <style>
    .alertas-casos {
        background-color: #ff5454;
        display: inline-block;
        padding: 0.25em 0.4em;
        font-size: 75%;
        font-weight: bold;
        color: #fff;
        text-align: left;
    }

    .nav-tabs .nav-link.active{
        font-size: 1.1rem;
        background-color: #f3f3f3 !important;
        border-color: #ddd #ddd #f3f3f3 !important;
    }

       input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; font-family: Roboto }
       input[type="text"]{color: #000000; font-family: Roboto
       }

        #myUL li:hover:not(.header) {
            background-color: #eee; /* Add a hover effect to all links, except for headers */
        }
        .card-body { background-color: #ffffff /* rgba(21, 20, 22, 0.1)*/ !important; }
        .card {
            background-color: #ffffff;
            border: 1px solid rgba(0, 34, 51, 0.1);
            box-shadow: 2px 4px 10px 0 rgba(0, 34, 51, 0.05), 2px 4px 10px 0 rgba(0, 34, 51, 0.05);
            border-radius: 0.15rem;
        }

        /* Tabs Card */

        .tab-card {
            border:1px solid #eee;
        }

        .tab-card-header {
            background:none;
        }
        /* Default mode */
        .tab-card-header > .nav-tabs {
            border: none;
            margin: 0px;
        }
        .tab-card-header > .nav-tabs > li {
            margin-right: 2px;
        }
        .tab-card-header > .nav-tabs > li > a {
            border: 0;
            border-bottom:2px solid transparent;
            margin-right: 0;
            color: #737373;
            padding: 2px 15px;
        }

        .tab-card-header > .nav-tabs > li > a.show {
            border-bottom:2px solid #007bff;
            color: #007bff;
        }
        .tab-card-header > .nav-tabs > li > a:hover {
            color: #007bff;
        }

        .tab-card-header > .tab-content {
            padding-bottom: 0;
        }


        /*inicio mi css */
        .main-box.no-header {
            padding-top: 20px;
        }
        .main-box {
            background: #FFFFFF;
            -webkit-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -moz-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -o-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -ms-box-shadow: 1px 1px 2px 0 #CCCCCC;
            box-shadow: 1px 1px 2px 0 #CCCCCC;
            margin-bottom: 16px;
            -webikt-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }
        .table a.table-link.danger {
            color: #e74c3c;
        }
        .label {
            border-radius: 3px;
            font-size: 0.875em;
            font-weight: 600;
        }
        .user-list tbody td .user-subhead {
            font-size: 0.875em;
            font-style: italic;
        }
        .user-list tbody td .user-link {
            display: block;
            font-size: 1.25em;
            padding-top: 3px;
            margin-left: 60px;
        }
        a {
            color: #3498db;
            outline: none!important;
        }
        .user-list tbody td>img {
            position: relative;
            max-width: 50px;
            float: left;
            margin-right: 15px;
        }

        .table thead tr th {
            text-transform: uppercase;
            font-size: 0.875em;
        }
        .table thead tr th {
            border-bottom: 2px solid #e7ebee;
        }
        .table tbody tr td:first-child {
            font-size: 1.125em;
            font-weight: 300;
        }
        .table tbody tr td {
            font-size: 0.875em;
            vertical-align: middle;
            border-top: 1px solid #e7ebee;
            padding: 12px 8px;
        }
        a:hover{
            text-decoration:none;
        }
        /* fin  EFFECT 1 ========================================== */
        .effect-1 {
            position: relative;
        }
        .effect-1::before, .effect-1::after {
            z-index: -1;
            position: absolute;
            content: "";
            bottom: 15px;
            left: 10px;
            width: 50%;
            top: 80%;
            max-width: 300px;
            background: #aaa;
            box-shadow: 0 15px 10px #aaa;
            transform: rotate(-3deg);
        }
        .effect-1::after {
            transform: rotate(3deg);
            right: 10px;
            left: auto;
        }

        /* search*/
       .search-result-box .tab-content {
           padding: 30px 30px 10px 30px;
           -webkit-box-shadow: none;
           box-shadow: none;
           -moz-box-shadow: none
       }

       .search-result-box .search-item {
           padding-bottom: 20px;
           border-bottom: 1px solid #e3eaef;
           margin-bottom: 20px
       }
       .text-success {
           color: #0acf97!important;
       }
       a {
           color: #007bff;
           text-decoration: none;
           background-color: transparent;
       }
       .btn-custom {
           background-color: #02c0ce;
           border-color: #02c0ce;
       }

       .btn-custom, .btn-danger, .btn-info, .btn-inverse, .btn-pink, .btn-primary, .btn-purple, .btn-success, .btn-warning {
           color: #fff!important;
       }
        /*inicio tab 1*/
       .main-body {
           padding: 15px;
       }

       .card {
           box-shadow: 0 1px 3px 0 rgba(0,0,0,.1), 0 1px 2px 0 rgba(0,0,0,.06);
       }

       .card {
           position: relative;
           display: flex;
           flex-direction: column;
           min-width: 0;
           word-wrap: break-word;
           background-color: #fff;
           background-clip: border-box;
           border: 0 solid rgba(0,0,0,.125);
           border-radius: .25rem;
       }
       .card1:hover{
           transform: scale(1.05);
           box-shadow: 0 10px 20px rgba(0,0,0,.12), 0 4px 8px rgba(0,0,0,.06);
       }

       .card-body {
           flex: 1 1 auto;
           min-height: 1px;
           padding: 1rem;
       }

       .gutters-sm {
           margin-right: -8px;
           margin-left: -8px;
       }

       .gutters-sm>.col, .gutters-sm>[class*=col-] {
           padding-right: 8px;
           padding-left: 8px;
       }
       .mb-3, .my-3 {
           margin-bottom: 1rem!important;
       }

       .bg-gray-300 {
           background-color: #e2e8f0;
       }
       .h-100 {
           height: 100%!important;
       }
       .shadow-none {
           box-shadow: none!important;
       }

        /*fin*/

       .btn-circle {
           width: 45px;
           height: 45px;
           line-height: 45px;
           text-align: center;
           padding: 0;
           border-radius: 50%;
       }

       .btn-circle i {
           position: relative;
           top: -1px;
       }

       .btn-circle-sm {
           width: 35px;
           height: 35px;
           line-height: 35px;
           font-size: 0.9rem;
       }

       .btn-circle-lg {
           width: 55px;
           height: 55px;
           line-height: 55px;
           font-size: 1.1rem;
       }

       .btn-circle-xl {
           width: 70px;
           height: 70px;
           line-height: 70px;
           font-size: 1.3rem;
       }
       .badge-cyan {
           color: #fff;
           background-color: #007bff;
       } .btn-primary {
             background-color: #3f51b5 !important;
             color: #fff
         }
       .rounded-pill {
           border-radius: 50rem !important;
       }
       .anchos{
           width: 15px;
       }
       .modal-lg {
           max-width: 1000px !important;
       }
        .error{
            color: red;
        }
       #tblParticipantes_filter {
           visibility: hidden;
       }

    /*
    table.dataTable.dataTable_width_auto {
          width: auto;
        }
    */
    </style>
   <!-- <spring:url value="/resources/css/animate.css" var="anime" />
    <link rel="stylesheet" href="${anime}" type="text/css"/> -->

    <spring:url value="/resources/css/sweetalert2/sweetalert2.min.css" var="sweetalert2" />
    <link rel="stylesheet" href="${sweetalert2}">

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
                <a href="<spring:url value="/Registro/BuscarInfor" htmlEscape="true "/>">
                    <spring:message code="Datos Generales" />
                </a>
            </li>
        </ol>
        <div class="container col-md-12 col-lg-12">
            <div class="animated fadeIn">
                <div class="card shadow w-100 bg-white rounded">
                    <spring:url value="/Registro/searchParticipant" var="searchPartUrl"/>
                    <div class="card-header">
                        <h5 class="text-gray-dark" style="font-family: Roboto">
                            <i class="fa fa-list" aria-hidden="true"></i>
                            <spring:message code="Datos Generales del Participante" /></h5>
                    </div>
                    <div class="card-body text-dark p-2 effect-1">
                        <div class="container">

                            <spring:url value="/Registro/searchParticipant" var="searchPartUrl"/>
                            <spring:url value="/Registro/participantsByCasa" var="participantsByCodeCasaUrl"/>
                            <spring:url value="/Registro/allParticipants" var="allParticipantsUrl"/>
                            <spring:url value="/Registro/getNombre1" var="getNombre1Url"/>
                            <spring:url value="/Registro/getApellido1" var="getApellido1Url"/>
                            <spring:url value="/Registro/getPartNombreApellido" var="getPartNombreApellidoUrl"/>

                            <form action="#" autocomplete="off" id="select-participante-form" name="select-participante-form" class="form-horizontal">
                                <div class="form-group row">
                                    <label class="form-control-label col-md-2" for="username"><spring:message code="participant.code" />
                                        <span class="required">*</span>
                                    </label>
                                    <div class="input-group col-md-10">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="parametro" name="parametro" type="text" value="" class="form-control"/>
                                        <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right">
                                            <i class="fa fa-search" aria-hidden="true"></i>
                                            <spring:message code="search" />
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- Tabs -->
                        <div class="">
                            <div class="">
                                <div class="">
                                    <div class="card mt-3 tab-card">
                                        <div class="card-header tab-card-header">
                                            <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                                                <li class="nav-item">
                                                    <a class="nav-link active" id="one-tab" data-toggle="tab" href="#one" role="tab" aria-controls="One" aria-selected="true">
                                                        <i class="fa fa-users" aria-hidden="true"></i> Datos del Participante</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="four-tab" data-toggle="tab" href="#four" role="tab" aria-controls="One" aria-selected="true">
                                                        <i class="fa fa-users" aria-hidden="true"></i> Verificación MA</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="two-tab" data-toggle="tab" href="#two" role="tab" aria-controls="Two" aria-selected="false">
                                                        <i class="fa fa-user-times" aria-hidden="true"></i> Información del Retiro</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="three-tab" data-toggle="tab" href="#three" role="tab" aria-controls="Three" aria-selected="false">
                                                        <i class="fa fa-search" aria-hidden="true"></i> Buscar Nombre</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active p-3 animate__headShake" id="one" role="tabpanel" aria-labelledby="one-tab">
                                                <!-- 1er tabs -->
                                                <div class="container">
                                                    <div class="main-body">
                                                        <div class="row gutters-sm">
                                                            <div class="col-md-5 mb-3">
                                                                <div class="card card1">
                                                                    <div class="card-body">
                                                                        <div class="d-flex flex-column align-items-center text-center">
                                                                            <spring:url value="/resources/img/default-profile.jpg" var="userDefault" />
                                                                            <img class="rounded-circle" width="150" src="${userDefault}" alt="<spring:message code="Admin" />" />
                                                                            <div class="mt-3">
                                                                                <span class="badge ba   dge-pill text-primary" id="nombreComplete" style="font-size: 15px"></span>
                                                                                <div class="table-responsive">

                                                                                    <table class="table table-hover" style="width:100%">

                                                                                        <tr>
                                                                                            <td class="text-left"> <h6> Código Participante:</h6></td>
                                                                                            <td class="text-right"><span class="badge badge-pill text-primary" id="idParticipante" style="font-size: 15px"></span> </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="text-left"> <h6>Estado:</h6> </td>

                                                                                            <td class="text-right"><span class="badge badge-pill text-white" id="estado" style="font-size: 15px"></span> </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="text-left"> <h6>Estudios:</h6> </td>
                                                                                            <td class="text-right"><span class="badge badge-pill text-primary" id="estudios" style="font-size: 15px"></span> </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td class="text-left"> <h6>Fecha Nacimiento:</h6> </td>
                                                                                            <td class="text-right">	<span id="fnac"></span> </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td class="text-left"> <h6>PBMC:</h6> </td>
                                                                                            <td class="text-right"><span class="badge badge-pill text-white pull-rigth" style="background-color: #d562da; font-size: 15px" id="pbmc2"></span></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="text-left"> <h6>PaxGene:</h6> </td>
                                                                                            <td class="text-right"><span class="badge badge-pill text-white" style="background-color: #00dd00; font-size: 15px" id="paxgene2"></span></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><button id="btnCodeLineal" class="btn btn-primary float-left" data-toggle="tooltip" data-placement="top" title="Imprimir Lineal">
                                                                                                <i class="fa fa-barcode"></i>
                                                                                            </button>
                                                                                            </td>
                                                                                            <td>
                                                                                                <button id="btnCodeBidi" class="btn btn-warning float-right" data-toggle="tooltip" data-placement="bottom" title="Imprimir QRCode">
                                                                                                    <i class="fa fa-qrcode"></i>
                                                                                                </button>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="card card1 mb-3">
                                                                   <div class="card-body">
                                                                       <h4><span id="alertas" class="alertas-casos"></span></h4>
                                                                       <h5 class="d-flex align-items-center mb-3"><i class="material-icons text-warning mr-2"> <strong>Procesos Pendientes </strong></i> <span id="contador" class="badge badge-primary badge-pill"></span></h5>

                                                                       <ul id="myUL" class="list-group mb-3">
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Pendiente</small>
                                                                                   <h6 class="my-0">BHC</h6>
                                                                               </div>
                                                                               <h2><span id="bhc-pendiente" class="badge badge-primary badge-pill text-white"></span> </h2>
                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Pendiente</small>
                                                                                   <h6 class="my-0">Serología</h6>
                                                                               </div>
                                                                               <h2><span id="serologia-pendiente" class="badge badge-primary badge-pill text-white"></span> </h2>
                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small  class="text-muted">Realizar</small>
                                                                                   <h6 class="my-0">Consentimiento Flu</h6>
                                                                               </div>
                                                                               <h2> <span id="cons_flu" class="badge badge-primary badge-pill text-dark"></span> </h2>

                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar </small>
                                                                                   <h6 class="my-0">Consentimiento Dengue</h6>
                                                                               </div>
                                                                               <h2><span id="cons_Den" class="badge badge-primary badge-pill text-dark"></span> </h2>
                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar </small>
                                                                                   <h6 class="my-0">Consentimiento Chf</h6>
                                                                               </div>
                                                                               <h2> <span id="cons_chf" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Tiene</small>
                                                                                   <h6 class="my-0">Vacunas</h6>
                                                                               </div>
                                                                               <h2><span id="vacuna" class="badge badge-primary badge-pill text-white"></span> </h2>
                                                                           </li>
                                                                           <!--<li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar encuesta</small>
                                                                                   <h6 class="my-0">Perímetro Abdominal</h6>
                                                                               </div>
                                                                               <h2><span id="perimetroAbdominal" class="badge badge-primary badge-pill text-white"></span> </h2>
                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar encuesta</small>
                                                                                   <h6 class="my-0">Satisfacción de Usuario</h6>
                                                                               </div>
                                                                               <h2><span id="encSatUsu" class="badge badge-primary badge-pill text-white"></span> </h2>
                                                                           </li>-->
                                                                       </ul>
                                                                   </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-7">
                                                                <div class="card card1 mb-3">
                                                                    <div class="card-body">
                                                                        <h6 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2"> <strong> Información Del Participante </strong></i></h6>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Casa Pediátrica</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="idCasa"></span>
                                                                                <button id="VerAll" class="btn btn-info rounded-pill float-right" data-toggle="tooltip" data-placement="bottom" title="Participantes en Casa">
                                                                                <i class="fa fa-users" aria-hidden="true"></i>
                                                                                </button>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Casa Familia</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="CodigoCasaFamilia"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Sexo</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="sexo"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Edad</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                               <span id="edad"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Dirección</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                               <span id="direc"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Barrio</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="barrio"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">N° Manzana</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="manzana"></span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-8">
                                                                    <div class="card card1 mb-3">
                                                                        <div class="card-body">
                                                                            <h6 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2"> <strong> Datos Familiares</strong> </i></h6>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Jefe de Familia</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="jefe"></span>
                                                                                </div>
                                                                            </div>
                                                                            <hr>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Padre</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="padre"></span>
                                                                                </div>
                                                                            </div>
                                                                            <hr>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Madre</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="madre"></span>
                                                                                </div>
                                                                            </div>
                                                                            <hr>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Tutor</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="tutor"></span>
                                                                                </div>
                                                                            </div>
                                                                            <hr>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Relación</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="relfam"></span>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-12">
                                                                    <div class="card card1">
                                                                        <div class="card-body">
                                                                            <h5 class="d-flex align-items-center mb-3"><i class="material-icons text-warning mr-2">
                                                                                <strong>Encuestas Pendientes </strong></i> </h5>
                                                                            <ul id="myUL1" class="list-group mb-3">
                                                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                                    <div>
                                                                                        <small class="text-muted"> Realizar Encuesta </small>
                                                                                        <h6 class="my-0"> Casa Cohorte</h6>
                                                                                    </div>
                                                                                    <h2><span id="enc_casa_cohorte" class="badge badge-primary badge-pill text-dark"></span> </h2>
                                                                                </li>
                                                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                                    <div>
                                                                                        <small class="text-muted">Realizar Encuesta</small>
                                                                                        <h6 class="my-0"> Casa Familia</h6>
                                                                                    </div>
                                                                                    <h2> <span id="enc_casa_Fam" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                                </li>
                                                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                                    <div>
                                                                                        <small class="text-muted">Realizar encuesta</small>
                                                                                        <h6 class="my-0">Peso y Talla</h6>
                                                                                    </div>
                                                                                    <h2><span id="pyt" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                                </li>
                                                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                                    <div>
                                                                                        <small class="text-muted">Realizar encuesta</small>
                                                                                        <h6 class="my-0">Lactancia Materna</h6>
                                                                                    </div>
                                                                                    <h2><span id="lact" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                                </li>
                                                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                                    <div>
                                                                                        <small class="text-muted">Realizar encuesta</small>
                                                                                        <h6 class="my-0">Cuestionario COVID19</h6>
                                                                                    </div>
                                                                                    <h2><span id="cuest_covid" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                                </li>
                                                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                                    <div>
                                                                                        <small class="text-muted">Realizar encuesta</small>
                                                                                        <h6 class="my-0">Participante</h6>
                                                                                    </div>
                                                                                    <h2><span id="enc_part" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                                </li>
                                                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                                    <div>
                                                                                        <small class="text-muted">Realizar encuesta</small>
                                                                                        <h6 class="my-0">Perímetro Abdominal</h6>
                                                                                    </div>
                                                                                    <h2><span id="perimetroAbdominal" class="badge badge-primary badge-pill text-white"></span> </h2>
                                                                                </li>
                                                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                                    <div>
                                                                                        <small class="text-muted">Realizar encuesta</small>
                                                                                        <h6 class="my-0">Satisfacción de Usuario</h6>
                                                                                    </div>
                                                                                    <h2><span id="encSatUsu" class="badge badge-primary badge-pill text-white"></span> </h2>
                                                                                </li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- Fin 1er tabs-->
                                            </div>
                                            <div class="tab-pane fade p-3 animate__headShake" id="four" role="tabpanel" aria-labelledby="four-tab">
                                                <!-- Fin 1er tabs-->
                                                   <form name="form_verification" action="#" id="form_verification">
                                                                        <div class="row">
                                                                            <div class="col-lg-1 col-md-1 col-sm-12">
                                                                            </div>
                                                                            <div class="col-lg-10 col-md-10 col-sm-12">
                                                                                <div class="row">
                                                                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                                                                        <h4 class="text-capitalize"><spring:message code="Datos Generales" /></h4>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-4 col-md-4 col-sm-12">
                                                                                        <div class="form-group">
                                                                                            <label for="codigoVeri" class="form-control-label"><spring:message code="lbl.code" /></label>
                                                                                            <input type="text" class="form-control" id="codigoVeri" name="codigoVeri" value="" readonly />
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-lg-8 col-md-8 col-sm-12">
                                                                                        <div class="form-group">
                                                                                            <label for="nombreVeri" class="form-control-label"><spring:message code="lbl.name" /></label>
                                                                                            <input type="text" class="form-control" id="nombreVeri" name="nombreVeri" value="" readonly />
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-7 col-md-12 col-sm-12 col-12">
                                                                                        <div class="form-group">
                                                                                            <label for="conQuien" class="form-control-label"><spring:message code="Con quién acude:" />
                                                                                                <span class="required text-danger"> * </span>
                                                                                            </label>
                                                                                            <input type="text" class="form-control" id="conQuien" name="conQuien" value=""/>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-4 col-md-5 col-sm-6 col-12">
                                                                                        <div class="form-group">
                                                                                            <label><spring:message code="family.relationship" /> <span class="required text-danger"> * </span></label>
                                                                                            <select name="relacionFam" id="relacionFam" class="form-control" required>
                                                                                                <option selected value=""><spring:message code="select" />...</option>
                                                                                                <c:forEach items="${relFam}" var="rel">
                                                                                                    <option value="${rel.catKey}">${rel.spanish}</option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-lg-6 col-md-7 col-sm-12 col-12">
                                                                                        <div class="form-group">
                                                                                            <label for="otraRelacionFam" class="form-control-label"><spring:message code="Otra relación familiar:" />
                                                                                                <span class="required text-danger"> * </span>
                                                                                            </label>
                                                                                            <input type="text" class="form-control" id="otraRelacionFam" name="otraRelacionFam" disabled value=""/>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-lg-2 col-md-5 col-sm-12 col-12">
                                                                                        <div class="form-group">
                                                                                            <label><spring:message code="lbl.assent" /> <span class="required text-danger"> * </span></label>
                                                                                            <select name="asentimiento" id="asentimiento" class="form-control" required>
                                                                                                <option selected value=""><spring:message code="select" />...</option>
                                                                                                <c:forEach items="${SiNoNA}" var="s">
                                                                                                    <option value="${s.catKey}">${s.catKey} - <spring:message code="${s.spanish}" /></option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <br>
                                                                                <div class="row">
                                                                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                                                                        <h4 class="text-capitalize"><spring:message code="Cambios de Domicilio" /></h4>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                                                                        <div class="form-group">
                                                                                            <label for="direccion">Nueva Dirección:</label>
                                                                                            <input type="text" class="form-control focusNext" id="direccion" name="direccion" placeholder="Ingrese la Dirección" tabindex="6"/>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-lg-4 col-md-6 col-sm-12 col-12">
                                                                                        <div class="form-group">
                                                                                            <label for="barrioVeri">Barrio:</label>
                                                                                            <select  class="form-control" name="barrioVeri" id="barrioVeri">
                                                                                                <option selected value=""><spring:message code="select" />...</option>
                                                                                                <c:forEach items="${barrios}" var="barrio">
                                                                                                    <option value="${barrio.codigo}">${barrio.nombre}</option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                                                                        <h4 class="text-capitalize"><spring:message code="Contactado" /></h4>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-8 col-md-12 col-sm-12 col-12">
                                                                                        <div class="form-group">
                                                                                            <label>Como fue contactado para asisitir a Muestreo Anual:</label>
                                                                                            <span class="required text-danger"> * </span>
                                                                                            <select  class="form-control" name="contactado" id="contactado" required="required" multiple="multiple">
                                                                                                <c:forEach items="${contactado}" var="s">
                                                                                                    <option value="${s.catKey}">${s.catKey} - <spring:message code="${s.spanish}" /></option>
                                                                                                </c:forEach>
                                                                                            </select>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-lg-4 col-md-12 col-sm-12 col-12">
                                                                                        <div class="form-group">
                                                                                            <label for="otraFormaContacto" class="form-control-label"><spring:message code="Otra forma de ser contactado:" />
                                                                                                <span class="required text-danger"> * </span>
                                                                                            </label>
                                                                                            <input type="text" class="form-control" id="otraFormaContacto" name="otraFormaContacto" disabled value=""/>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                                                                        <h4 class="text-capitalize"><spring:message code="observacion" /></h4>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                                                                        <div class="form-group">
                                                                                            <label for="observacion"><spring:message code="observacion" /></label>
                                                                                            <textarea class="form-control"  id="observacion" name="observacion" placeholder="<spring:message code="observacion" />" rows="3"></textarea>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="col-lg-4 col-md-4 col-sm-2"></div>
                                                                                    <div class="col-lg-2 col-md-2 col-sm-4">
                                                                                        <div class="form-group">
                                                                                            <button class="btn btn-success  btn-ladda btn-block btn-lg"
                                                                                                    type="submit" id="btnSave">
                                                                                                <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" />
                                                                                            </button>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-lg-2 col-md-2 col-sm-4">
                                                                                        <div class="form-group">
                                                                                            <button class="btn btn-warning  btn-ladda btn-block btn-lg"
                                                                                                    type="button" id="btnCancelar">
                                                                                                <i class="fa fa-ban" aria-hidden="true"></i>
                                                                                                <spring:message code="cancel" />
                                                                                            </button>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-lg-4 col-md-4 col-sm-2"></div>
                                                                                </div>

                                                                            </div>
                                                                            <div class="col-lg-1 col-md-1 col-sm-12">
                                                                            </div>
                                                                        </div>
                                                                    </form>
                                            </div>
                                            <div class="tab-pane fade p-3 animate__headShake" id="two" role="tabpanel" aria-labelledby="two-tab">
                                                <div class="container">
                                                    <div class="table-responsive">
                                                        <table id="tblVerRetiro" class="table table-hover table-bordered">
                                                        <thead>
                                                        <tr>
                                                            <th class="text-center"><spring:message code="CÓDIGO" /></th>
                                                            <th class="text-center"><spring:message code="FECHA RETIRO" /></th>
                                                            <th class="text-center"><spring:message code="COMUNICADO POR" /></th>
                                                            <th class="text-center"><spring:message code="RELACIÓN" /></th>
                                                            <th><spring:message code="MOTIVO" /></th>
                                                            <th class="text-center"><spring:message code="OBSERVACIÓN" /></th>
                                                            <th class="text-center"><spring:message code="ESTUDIO" /></th>
                                                            <th class="text-center"><spring:message code="FECHA FALLECIDO" /></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody></tbody>
                                                        <tfoot>
                                                        <tr>
                                                            <th class="text-center"><spring:message code="CÓDIGO" /></th>
                                                            <th class="text-center"><spring:message code="FECHA RETIRO" /></th>
                                                            <th class="text-center"><spring:message code="COMUNICADO POR" /></th>
                                                            <th class="text-center"><spring:message code="RELACIÓN" /></th>
                                                            <th><spring:message code="MOTIVO" /></th>
                                                            <th class="text-center"><spring:message code="OBSERVACIÓN" /></th>
                                                            <th class="text-center"><spring:message code="ESTUDIO" /></th>
                                                            <th class="text-center"><spring:message code="FECHA FALLECIDO" /></th>
                                                        </tr>
                                                        </tfoot>
                                                    </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade p-3 animate__headShake" id="three" role="tabpanel" aria-labelledby="three-tab">
                                                <div class="container">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <form name="form_by_nombre" action="#" id="form_by_nombre">

                                                            <div class="row">
                                                                <div class="col-md-1"></div>

                                                                <div class="col-md-4">
                                                                    <div class="form-group">
                                                                        <label for="nombre1">Nombre <span class="required">*</span></label>
                                                                        <input type="text" class="form-control focusNext" placeholder="Primer Nombre" name="nombre1" id="nombre1" required tabindex="1">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <div class="form-group">
                                                                        <label for="apellido1">Apellido <span class="required">*</span></label>
                                                                        <input type="text" class="form-control focusNext" placeholder="Primer Apellido" name="apellido1" id="apellido1" required="" tabindex="2">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-2" style="margin-top: 10px; padding-top: 17px; padding-left: 0px; padding-bottom: 10px">
                                                                    <button id="cargarParticipantes"  type="submit" class="btn btn-success btn-ladda btn-block" data-style="expand-right"><i class="fa fa-search"></i></button>
                                                                </div>
                                                                <div class="col-md-1"></div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                    <div class="" style="width: 100%">
                                                        <div class="table-responsive">
                                                            <table class="table table-hover table-bordered dt-responsive nowrap" id="tblParticipantes" style="width:100%">
                                                                <thead>
                                                                <tr>
                                                                    <th class="text-center" scope="col"><spring:message code="code" /></th>
                                                                    <th class="text-center" scope="col"><spring:message code="1er.Nombre" /></th>
                                                                    <th class="text-center" scope="col"><spring:message code="2do.Nombre" /></th>
                                                                    <th class="text-center" scope="col"><spring:message code="1er Apellido" /></th>
                                                                    <th class="text-center" scope="col"><spring:message code="2do.Apellido" /></th>
                                                                    <th class="text-center" scope="col"><spring:message code="Fecha Nac." /></th>
                                                                    <th class="text-center" scope="col"><spring:message code="Sexo" /></th>
                                                                    <th class="text-center" scope="col"><spring:message code="actions" /></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                </tbody>
                                                                <tfoot>
                                                                <tr>
                                                                    <th scope="col"><spring:message code="code" /></th>
                                                                    <th scope="col"><spring:message code="1er.Nombre" /></th>
                                                                    <th scope="col"><spring:message code="2do.Nombre" /></th>
                                                                    <th scope="col"><spring:message code="1er Apellido" /></th>
                                                                    <th scope="col"><spring:message code="2do.Apellido" /></th>
                                                                    <th scope="col"><spring:message code="Fecha Nac." /></th>
                                                                    <th scope="col"><spring:message code="Sexo" /></th>
                                                                    <th scope="col"><spring:message code="actions" /></th>
                                                                </tr>
                                                                </tfoot>
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
                        <!-- ./Tabs -->
                        <div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
    <!-- Modal -->
    <div id="exampleModal" class="modal fade bd-example-modal-lg"  tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        <i class="fa fa-users"></i> Participantes.</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="table-responsive">
                         <table id="tblCasa_Participants" class="table table-bordered table-hover" style="width:100%">
                        <thead>
                        <tr>
                            <th class="text-center">C.Pediátrica</th>
                            <th class="text-center">Casa CHF</th>
                            <th class="text-center">Código</th>
                            <th class="text-center">Nombre Completo</th>
                            <th class="text-center">Edad</th>
                            <th class="text-center">Estudio</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <i class="fa fa-times-circle" aria-hidden="true"></i>
                        Cerrar</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal -->

    <!-- /.conainer-fluid -->
</div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
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

<spring:url value="/resources/js/libs/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/sweetalert2/sweetalert2.min.js" var="sweetalert2" />
<script src="${sweetalert2}"></script>

<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>

<spring:url value="/Registro/guardar-verificacion" var="saveVeriUrl"/>

<script type="text/javascript">
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        });
    $(document).ready(function(){
        var table  = $('#tblVerRetiro').DataTable({
            "autoWidth": true,
            searching: true,
            paging: true,
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },"columnDefs": [{
                "className": "text-center",
                "targets": "_all"
                }],
            initComplete: function () {
                this.api().columns().every( function () {
                    var that = this;
                    $('input', this.footer() ).on( 'keyup change clear', function () {
                        if ( that.search() !== this.value ) {
                            that.search( this.value ).draw();
                        }
                    });
                });
            }
        });

        $("#relacionFam").select2();
        $("#asentimiento").select2();
        $("#barrioVeri").select2();
        $("#contactado").select2();

        $('#tblVerRetiro tfoot th').each( function () {
            var title = $(this).text();
            $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
        });
        $("#parametro").focus();
        var parametros = {searchPartUrl: "${searchPartUrl}",
            participantsByCodeCasaUrl: "${participantsByCodeCasaUrl}",
            allParticipantsUrl: "${allParticipantsUrl}",
            getNombre1Url: "${getNombre1Url}",
            getApellido1Url: "${getApellido1Url}",
            getPartNombreApellido:"${getPartNombreApellidoUrl}"
        };
        $("#select-participante-form").validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false,
            rules:{
                parametro: {required: true}
            },
            //errorElement: 'em',
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
                var id = $('#parametro').val();
                searchParticipante(id);
            }
        });
        function searchParticipante(id){
            $.getJSON(parametros.searchPartUrl, { parametro : id,   ajax : 'true'  }, function(data) {
                console.log(data);
                LimpiarCtrls();
                var len = data.length;
                if(len==0){
                    swal("Error!","Código no encontrado","error");
                    $("#parametro").focus().val("");
                }
                else{
                    if(data.estPart == "0"){
                        toastr.warning("Participante está retirado!");
                    }else{
                        toastr.success("Participante está Activo!");
                    }
                        var elemento = data.edad;
                        var fecha = elemento.split('/');
                        var datestring = ( fecha[0] + " Años " + fecha[1] + " Meses " + fecha[2] + " Dias");
                        $("#anios").val(fecha[0]);
                        $("#mes").val(fecha[1]);
                        $("#dias").val(fecha[2]);
                        $("#idParticipante").text(data.codigo_participante);
                    $("#codigoVeri").val(data.codigo_participante);

                        $("#idCasa").text(data.codigoCasa);
                        $("#CodigoCasaFamilia").text(data.CodigoCasaFamilia);
                        $("#manzana").text(data.manzana);
                        $("#nombreComplete").text(data.nombreCompletoParticipante);
                    $("#nombreVeri").val(data.nombreCompletoParticipante);
                    if (fecha[0] >= 6 && fecha[0] < 18 ) {
                        $('#asentimiento').val(''); // Select the option with a value of '3'
                        $('#asentimiento').trigger('change'); // Notify any JS components that the value changed
                        $("#asentimiento").prop("disabled", false);
                    } else {
                        $('#asentimiento').val('3'); // Select the option with a value of '3'
                        $('#asentimiento').trigger('change'); // Notify any JS components that the value changed
                        $("#asentimiento").prop("disabled", true);
                    }

                    if (fecha[0] >= 18 ) {
                        $("#conQuien").val(data.nombreCompletoParticipante);
                        $('#relacionFam').val('8').trigger('change');
                    } else {
                        $("#conQuien").val("").focus();
                        $("#conQuien").val(data.tutor);
                        $('#relacionFam').val(data.relacionFamTutor).trigger('change');
                    }

                        $("#sexo").text(data.sexo);
                        $("#fnac").text(data.fechaNac);
                        $("#edad").text(datestring);
                        $("#jefe").text(data.jefe);
                        $("#tutor").text(data.tutor);
                    var relacion="";
                        switch(data.relacionFamTutor){
                            case '1':
                                relacion = "Madre";
                                break;
                            case '2':
                                relacion = "Padre";
                                break;
                            case '3':
                                relacion = "Abuelo(a)";
                                break;
                            case '4':
                                relacion = "Tío(a)";
                                break;
                            case '5':
                                relacion = "Hermano(a)";
                                break;
                            case '8':
                                relacion = "Participante";
                                break;
                            case '998':
                                relacion = "Otra relación familiar";
                        }
                        $("#relfam").text(relacion);
                        $("#madre").text(data.nombreCompletoMadre);
                        $("#padre").text(data.nombreCompletoPadre);
                        $("#direc").text(data.direccion);
                        $("#barrio").text(data.nombreBarrio);
                        $("#estudios").text(data.estudios);
                        $("#pbmc").val(data.isPbmc);
                        $("#pbmc2").text(data.isPbmc);
                        $("#paxgene").val(data.isPaxgene);
                        $("#paxgene2").text(data.isPaxgene);


                        if(data.estPart ===1){
                            $("#estado").text("ACTIVO").removeClass('badge-danger').addClass('badge badge-success badge-pill');
                        }else{
                            $("#estado").text("RETIRADO").removeClass('badge-success').addClass('badge badge-danger badge-pill');
                        }

                        if(data.pesotalla != null && data.pesotalla =='Si'){//11979- codigo
                            $("#pyt").text(data.pesotalla).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#pyt").text(data.pesotalla).removeClass('badge-danger');
                        }

                        if(data.lactMat != null && data.lactMat =='Si'){
                            $("#lact").text(data.lactMat).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#lact").text(data.lactMat).removeClass('badge-danger');
                        }

                        if(data.encPart != null && data.encPart =='Si'){
                            $("#enc_part").text(data.encPart).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#enc_part").text(data.encPart).removeClass('badge-danger');
                        }

                        if(data.consFlu != null && data.consFlu =='Si'){
                            $("#cons_flu").text(data.consFlu).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#cons_flu").text(data.consFlu).removeClass('badge-danger');
                        }

                        if(data.consDeng != null && data.consDeng =='Si'){
                            $("#cons_Den").text(data.consDeng).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#cons_Den").text(data.consDeng).removeClass('badge-danger');
                        }

                        if(data.vacunas != null && data.vacunas =='Si'){
                            $("#vacuna").text(data.vacunas).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#vacuna").text(data.vacunas).removeClass('badge-danger');
                        }

                        if(data.encCasaCoh != null && data.encCasaCoh =='Si'){
                            $("#enc_casa_cohorte").text(data.encCasaCoh).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#enc_casa_cohorte").text(data.encCasaCoh).removeClass('badge-danger');
                        }

                        if(data.encCHF != null && data.encCHF =='Si'){
                            $("#enc_casa_Fam").text(data.encCHF).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#enc_casa_Fam").text("No").removeClass('badge-danger');
                        }

                        if(data.consChf != null && data.consChf =='Si'){
                            $("#cons_chf").text(data.consChf).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#cons_chf").text("No").removeClass('badge-danger');
                        }

                    if(data.cuestCovid != null && data.cuestCovid !='No'){
                        $("#cuest_covid").text("Si").addClass('badge badge-danger badge-pill text-dark');
                    }else{
                        $("#cuest_covid").text("No").removeClass('badge-danger');
                    }

                    if(data.tieneBhc =='No'){
                        $("#bhc-pendiente").text("Si").addClass('badge badge-danger badge-pill text-dark');
                    }else{
                        $("#bhc-pendiente").text("No").removeClass('badge-danger');
                    }

                    if(data.tieneSerologia =='No'){
                        $("#serologia-pendiente").text("Si").addClass('badge badge-danger badge-pill text-dark');
                    }else{
                        $("#serologia-pendiente").text("No").removeClass('badge-danger');
                    }

                    if(data.perimetroAbdominal != null && data.perimetroAbdominal =='Si'){
                        $("#perimetroAbdominal").text(data.perimetroAbdominal).addClass('badge badge-danger badge-pill text-dark');
                    }else{
                        $("#perimetroAbdominal").text(data.perimetroAbdominal).removeClass('badge-danger');
                    }

                    if(data.encSatUsu != null && data.encSatUsu =='Si'){
                        $("#encSatUsu").text(data.encSatUsu).addClass('badge badge-danger badge-pill text-dark');
                    }else{
                        $("#encSatUsu").text(data.encSatUsu).removeClass('badge-danger');
                    }

                    if(data.alertas != '' ){
                        $("#alertas").html(data.alertas).show();
                    }else{
                        $("#alertas").hide();
                    }

                        recorrerUL();
                        table.clear().draw( false );
                        var len = data.retiroList.length;
                        for( var i = 0; i < len; i++ ) {
                            var relacion;
                            switch(data.retiroList[i].relfam){
                                case 1:
                                    relacion = "Madre";
                                    break;
                                case 2:
                                    relacion = "Padre";
                                    break;
                                case 3:
                                    relacion = "Abuelo(a)";
                                    break;
                                case 4:
                                    relacion = "Tío(a)";
                                    break;
                                case 5:
                                    relacion = "Hermano(a)";
                                    break;
                                case 8:
                                    relacion = "Participante";
                                    break;
                                case 998:
                                    relacion = "Otra relación familiar";
                            }
                            var getCode = data.codigo_participante;
                            var fr = new Date(data.retiroList[i].fecharetiro);
                            var ffallecido ="";
                            if(data.retiroList[i].fechafallecido === undefined){
                                ffallecido = '<h3> <span class="badge badge-success"> NO</span> </h3>';
                            }else{
                                var objfecha = new Date(data.retiroList[i].fechafallecido);
                                var datefallecido =  ("0" + objfecha.getDate()).slice(-2) + "/" + ("0"+(objfecha.getMonth()+1)).slice(-2) + "/" + objfecha.getFullYear();
                                ffallecido = '<h3> <span class="badge badge-danger"> '+ datefallecido.toUpperCase() +'</span> </h3>';
                            }
                            var datestring =  ("0" + fr.getDate()).slice(-2) + "/" + ("0"+(fr.getMonth()+1)).slice(-2) + "/" + fr.getFullYear();
                            var comunicador = data.retiroList[i].quiencomunica == "" ? "-" : data.retiroList[i].quiencomunica;
                            var observacion = ( data.retiroList[i].observaciones == "") ? "-":  data.retiroList[i].observaciones;
                            table.row.add([
                                getCode,
                                datestring,
                                comunicador.toUpperCase(),
                                relacion.toUpperCase(),
                                data.retiroList[i].descripcionretiro.toUpperCase(),
                               observacion.toUpperCase(),
                                data.retiroList[i].estudioretirado.toUpperCase(),
                                ffallecido
                            ]).draw( false );
                        }
                    $("#parametro").focus().val("");
                }
            }).fail(function() {
                toastr.error("Código no existe!");
                $("#parametro").focus();
            });
        }
        function recorrerUL(){
            var cont=0;
            $("#myUL li span").each(function(indice, elemento) {
                if($(elemento).text() == 'Si'){
                    cont++;
                }
                $('#contador').text(""+cont);
                $("#pendientes").text(""+cont);
            });

            $("#myUL1 li span").each(function(indice, elemento) {
                if($(elemento).text() == 'Si'){
                    cont++;
                }
                $('#contador').text(""+cont);
                $("#pendientes").text(""+cont);
            });
        }


        function imprimir(strBarCodes,inputValue,tipo){
            $.getJSON("http://localhost:13001/print", { barcodes: strBarCodes+'*'+inputValue+'*'+tipo, ajax:'false' }, function (data) {
                //console.log(data);
                Swal.fire({
                    icon: 'success',
                    title: 'Stickers!',
                    text: "impresos: " + inputValue,
                    timer: 2000
                })
            }).fail(function (jqXHR) {
                //console.log(jqXHR);
                if (jqXHR.status!=200 && jqXHR.status!=0) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error al imprimir etiquetas!',
                        timer: 2000
                    })
                }

            });
        }

        var tableParticipantCasa = $("#tblCasa_Participants").DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}",
            },
            "columnDefs":[
                {"className": "text-center", "targets": "_all"}
            ],
            autoWidth: false
        });

        /* Metodo para vizualizar participantes por casa pediatrica*/
        $('#VerAll').on('click', function(){
            const codCasa = $('#idCasa').text();

            const idParticipante = $('#idParticipante').text();
            if( !isNaN(codCasa) || (codCasa != null && codCasa!=9999) ){
                $.getJSON(parametros.participantsByCodeCasaUrl,{casaCode: codCasa, codParticipante: idParticipante, ajax:'true'}, function(data){
                    console.log(data);
                    tableParticipantCasa.clear().draw( false );
                    $.each(data, function(i, item) {
                        var cPediatrica = (data[i].codCasaPediatrica);
                        var casaFam = (data[i].codCasaFamilia);
                        var cParticipante = (data[i].idParticipante);
                        var nombre = (data[i].nombreParticipante);
                        var edad = data[i].anios+" años "+ data[i].meses + " meses " + data[i].dias +" dias";
                        var status = (data[i].estado=="ACTIVO") ? '<h4><span class="badge badge-success badge-pill"> Activo </span></h4>': '<h4><span class="badge badge-danger badge-pill"> Retirado </span></h4>';
                        var estudio = (data[i].estudio);
                        tableParticipantCasa.row.add([
                            cPediatrica,
                            casaFam,
                            cParticipante,
                            nombre,
                            edad,
                            estudio
                        ]).draw(false);

                    });
                }).fail(function(){
                    toastr.error("Error, Intenta de nuevo!","Error",{timeOut: 0});
                });
            }else{
                toastr.error("Error, Código de Casa no válido!","Error",{timeOut: 0});
                return false;
            }

            $("#exampleModal").modal("show");
        })

        $('#tblParticipantes tfoot th').each( function () {
            var title = $(this).text();
            $(this).html( '<input type="text" placeholder="Búscar ' + title + ' " />' );
        });

        var tablaParticipantes = $("#tblParticipantes").DataTable({
            searching: true,
            lengthChange: false,
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            columnDefs: [
                {
                    targets: -1,
                    className: 'text-center'
                }, {
                targets: 5,
                className: 'text-center'
            }, {
                targets: 6,
                className: 'text-center'
            },{
                targets: 7,
                className: 'text-center'
            }
        ],
            initComplete: function () {
                this.api().columns().every( function () {
                    var that = this;
                    $('input', this.footer() ).on( 'keyup change clear', function () {
                        if ( that.search() !== this.value ) {
                            that.search( this.value ).draw();
                        }
                    });
                });
            }
        });


        $("#cargarParticipantes").on("click", function(){
            $("form[name='form_by_nombre']").validate({
                rules: {
                    nombre1: {required: true},
                    apellido1: {required: true}
                },messages: {
                    nombre1: "Ingrese el Nombre.",
                    apellido1:"Ingrese el Apellido."
                }, errorElement: 'em',
                errorPlacement: function ( error, element ) {
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        error.insertAfter( element );
                    }
                    if (element.attr("name") == "signo") {
                        error.insertAfter("#gendererror");
                    } else {
                        error.insertAfter(element);
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
                    getParticipantes()
                }
            });

        });

        function getParticipantes(){
            tablaParticipantes.clear().draw( false );
            $.getJSON(parametros.getPartNombreApellido,{nombre1: $("#nombre1").val().trim(), apellido1: $("#apellido1").val().trim()}, function(data){
                $.each(data, function(key, item){
                    var codigo = (data[key].codigo);
                    var nombre1 = (data[key].nombre1);
                    var nombre2 = (data[key].nombre2);
                    var apellido1 = (data[key].apellido1);
                    var apellido2 = (data[key].apellido2);
                    var d =new Date(data[key].fechaNac);
                    var datestring =  ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                    var sexo = data[key].sexo;

                    tablaParticipantes.row.add([
                        codigo,
                        nombre1,
                        nombre2,
                        apellido1,
                        apellido2,
                        datestring,
                        sexo,
                        valor = '<a class="btn btn-info btn-sm btnSendId" data-id="' + data[key].codigo + '"><i class="fa fa-search"></i></a>'
                    ]).draw(false);
                });
            }).fail(function(){
                toastr.error("Error, Intenta de nuevo!","Error",{timeOut: 0});
            });
        }

        $("#tblParticipantes tbody").on("click", ".btnSendId",function(){
            var id = $(this).data('id');
            searchParticipante(id);
            debugger;
            $("#one").addClass("active show");
            $("#one-tab").addClass("active");
            $("#three-tab").removeClass("active");
            $("#three").removeClass("active");
        });
        $( "#nombre1" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parametros.getNombre1Url, {nombre1: $('#nombre1').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        $( "#apellido1" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parametros.getApellido1Url, {apellido1: $('#apellido1').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        document.addEventListener('keypress', function(evt) {
            // Si el evento NO es una tecla Enter
            if (evt.key !== 'Enter') {
                return;
            }
            let element = evt.target;
            // Si el evento NO fue lanzado por un elemento con class "focusNext"
            if (!element.classList.contains('focusNext')) {
                return;
            }
            // AQUI logica para encontrar el siguiente
            let tabIndex = element.tabIndex + 1;
            var next = document.querySelector('[tabindex="'+tabIndex+'"]');
            // Si encontramos un elemento
            if (next) {
                next.focus();
                event.preventDefault();
            }
        });

        /***VERIFICAION****/
        $("#form_verification").validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false,
            ignore: [],
            rules:{
                codigoVeri: {required: true},
                conQuien: {required: true},
                relacionFam: {required: true},
                asentimiento: {required: true},
                contactado: {required: true},
                otraRelacionFam: {
                    required: function () {
                        var otro = "6";
                        return  $('#relacionFam').val() === otro;
                    }
                },
                otraFormaContacto: {
                    required: function () {
                        var otro = "998";
                        var relFam = $('#contactado').val();
                        return  $.inArray(otro, relFam) !== -1
                    }
                }
            },
            //errorElement: 'em',
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                }if ( element.prop( 'type' ) === 'select-one' ) {
                    error.insertAfter( element );
                } else {
                    error.insertAfter( element ); //cuando no es input-group
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
                guardarVerificacion();
            }
        });

        $("#relacionFam").on("change", function(){
            var valor = $(this).val();
            if(valor == "6"){
                $("#otraRelacionFam").val('').prop("disabled", false).focus();
            } else {
                $("#otraRelacionFam").val('').prop("disabled", true);
            }
        });

        $("#contactado").on("change", function(){
            var valor = $(this).val();
            if ($.inArray('998', valor) !== -1) {
                $("#otraFormaContacto").val('').prop("disabled", false);
                $("#otraFormaContacto").focus();
            } else {
                $("#otraFormaContacto").val('').prop("disabled", true);
            }
        });

        function guardarVerificacion() {
            var verificacion = {};
            verificacion['codigoParticipante'] = $("#codigoVeri").val();
            verificacion['conQuienAcude'] = $("#conQuien").val();
            verificacion['relFamAcude'] = $("#relacionFam").val();
            verificacion['otraRelFamAcude'] = $("#otraRelacionFam").val();
            verificacion['asentimiento'] = $("#asentimiento").val();
            verificacion['nuevaDireccion'] = $("#direccion").val();
            verificacion['codigoBarrio'] = $("#barrioVeri").val();
            verificacion['otraFormaContacto'] = $("#otraFormaContacto").val();
            verificacion['observacion'] = $("#observacion").val();
            var valores = $('#contactado').val();
            var strValores = '';
            for (var i = 0; i < valores.length; i++) {
                if (i == 0)
                    strValores = +valores[i];
                else
                    strValores = strValores + ',' + valores[i];
            }
            verificacion['contacto'] = strValores;
            $.ajax(
                    {
                        url: "${saveVeriUrl}",
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify(verificacion),
                        contentType: 'application/json',
                        mimeType: 'application/json',
                        async: false,
                        success: function (data) {
                            if (data.error != undefined && data.error.length > 0) {
                                Swal.fire({
                                    icon: 'error',
                                    title: data.error,
                                    timer: 4000
                                })
                            } else {
                                limpiarCtrlsVerificacion();
                                Swal.fire({
                                    icon: 'success',
                                    title: data.mensaje,
                                    timer: 2000
                                })
                            }
                            //desbloquearUI();
                        },
                        error: function (jqXHR) {
                            Swal.fire({
                                icon: 'error',
                                title: jqXHR,
                                timer: 4000
                            })
                        }
                    });
        }

        $("#btnCancelar").on("click", function(){
            limpiarCtrlsVerificacion();
        });

        function limpiarCtrlsVerificacion() {
            $("#conQuien").val('');
            $('#asentimiento').val('').trigger('change');
            $('#relacionFam').val('').trigger('change');
            $('#contactado').val('').trigger('change');
            $("#otraRelacionFam").val('');
            $("#direccion").val('');
            $("#barrioVeri").val('');
            $("#otraFormaContacto").val('');
            $("#observacion").val('');
        }
        /******FIN VERIFICACION******/

        function LimpiarCtrls(){
            $("#idParticipante").text("");
            $("#estudios").text("");
            $("#fnac").text("");
            $("#pbmc2").text("");
            $("#paxgene2").text("");
            $("#idCasa").text("");
            $("#CodigoCasaFamilia").text("");
            $("#sexo").text("");
            $("#edad").text("");
            $("#direc").text("");
            $("#barrio").text("");
            $("#manzana").text("");
            $("#contador").text("");
            $("#pyt").text("");
            $("#lact").text("");
            $("#enc_part").text("");
            $("#cons_flu").text("");
            $("#cons_Den").text("");
            $("#vacuna").text("");
            $("#enc_casa_cohorte").text("");
            $("#cons_chf").text("");
            $("#jefe").text("");
            $("#padre").text("");
            $("#madre").text("");
            $("#tutor").text("");
            $("#relfam").text("");
            $("#nombreComplete").text("");
        }

        $("#btnCodeLineal").on("click", function() {
            var id = $("#idParticipante").text();
            Swal.fire({
                title: "Imprimir Lineal!",
                input: 'text',
                inputLabel: "Número de copias: " + id,
                inputValue: 1,
                confirmButtonText: 'Imprimir',
                cancelButtonText: 'Cancelar',
                showCancelButton: true,
                inputValidator: (value) => {
                if (!value) {
                return 'Favor ingresar la cantidad de copias!'
            } else {
                imprimir(id, value, '2');
            }
        }
    })
        });
        $("#btnCodeBidi").on("click", function() {
            var id = $("#idParticipante").text();
            Swal.fire({
                title: "Imprimir QR!",
                input: 'text',
                inputLabel: "Número de copias: " + id,
                inputValue: 3,
                confirmButtonText: 'Imprimir',
                cancelButtonText: 'Cancelar',
                showCancelButton: true,
                inputValidator: (value) => {
                if (!value) {
                return 'Favor ingresar la cantidad de copias!'
            } else {
                imprimir(id, value, '3');
            }
        }
        })
        });

        });

</script>
</body>
</html>
