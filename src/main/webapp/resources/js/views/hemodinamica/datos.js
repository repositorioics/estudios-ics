/**
 * Created by ICS on 27/09/2020.
 */
var Urls = {};
var datosHemo = function(){
    return{
        init : function(endPoint){

            Urls = endPoint;
            //inicio Buscar Participante
            $("#select-participante-form").validate({
                rules:{
                    parametro: {required: true}
                },
                errorElement: 'em',
                errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        error.insertAfter( element );
                    }
                    if (element.attr("name") == "parametro") {
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
                    searchParticipante();
                    ClearRangos();
                }
            });

            function ClearRangos(){
                $("#sdMin").val("");
                $("#sdMed").val("");
                $("#sdMax").val("");
                $("#pamMin").val("");
                $("#pamMed").val("");
                $("#pamMax").val("");
                $("#fcMin").val("");
                $("#fcMed").val("");
                $("#fcProm").val("");
                $("#frMin").val("");
                $("#frMax").val("");
            }

            function searchParticipante(){
                $.getJSON(Urls.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    console.log(data);
                    var len = data.length;
                    if(len==0){
                        toastr.error("Código no encontrado!","ERROR",{timeOut:6000});
                        $("input[name=chkRange2]").attr("checked", false);
                        $("#parametro").focus();
                    }
                    else{
                        if(data.estado == "0"){
                            toastr.warning("Participante está retirado!", "warning", {timeOut:6000});
                            $("#nombre").val("");
                            $("#fconsulta").val("");
                            $("#idParticipante").val("");
                            $("#edad").val("");
                            $("#direccion").val("");
                            $("#fecha").val("");
                            $("#expediente").val("");
                            $("#sector").val("").change();
                            $("#anios").val("");
                            $("#mes").val("");
                            $("#dias").val("");
                            $("#sexo").val("");
                            $("input[name=chkRange2]").attr("checked", false);
                            $("#parametro").focus();
                        }else{
                            var elemento = data.edad;
                            var fecha = elemento.split('/');
                            var datestring = ( fecha[0] + " Años " + fecha[1] + " Meses " + fecha[2] + " Dias");
                            $("#anios").val(fecha[0]);
                            $("#mes").val(fecha[1]);
                            $("#dias").val(fecha[2]);
                            $("#sexo").val(data.sexo);
                            var myExpediente = data.fecha;
                            $("#nombre").val(data.nombre);
                            $("#fecha").val(data.fecha);
                            $("#idParticipante").val(data.codigo);
                            //$("#edad").val(datestring);
                            $("#direccion").val(data.direccion);
                            $("#sector").val(data.barrio).change();
                            var exp = myExpediente.split('/');
                            var verExpdiente = (exp[0]+""+exp[1]+""+exp[2].substr(2,2))
                            $("#expediente").val(verExpdiente);
                            GetRange();
                            $("#telefono").focus();
                        }
                    }
                }).fail(function() {
                    toastr.error("Error Interno del Servidor!", "ERROR",{timeOut:6000});
                    $("input[name=chkRange2]").attr("checked", false);
                    $("#parametro").val("");
                    $("#parametro").focus();
                });
            }
            $("#telefono").keypress(function(e) {
                if (e.which == 13) {
                   // nextPass();
                }
            });
            function nextPass() {
                if ($("#idParticipante").val().trim() == null || $("#idParticipante").val() == "" || $("#nombre").val().trim()=="" || $("#fecha").val().trim()=="") {
                    var step_index = $(this).val() - 1;
                    $('#smartwizard').smartWizard("goToStep", step_index);
                } else {
                    $('#smartwizard').smartWizard("next");
                }
            }
            $(".sw-btn-next").on("click", function() {
                // Navigate next
                if ($("#idParticipante").val().trim() == null || $("#idParticipante").val().trim() == "" || $("#nombre").val().trim()=="" || $("#fecha").val().trim()=="") {
                    $('#smartwizard').smartWizard("goToStep", 0);
                    //toastr.error("Campos Requerido!",'ERROR',{timeOut:6000});
                    $("#parametro").focus();
                }else{
                    $('#smartwizard').smartWizard("next");
                }
                return true;
            });
            // Demo Button Events
            $("#got_to_step").on("change", function() {
                // Go to step
                var step_index = $(this).val() - 1;
                $('#smartwizard').smartWizard("goToStep", step_index);
                return true;
            });
            // Step show event
            $("#smartwizard").on("showStep", function(e, anchorObject, stepNumber, stepDirection, stepPosition) {
                $("#prev-btn").removeClass('disabled');
                $("#next-btn").removeClass('disabled');
                if(stepPosition === 'first') {
                    $("#prev-btn").addClass('disabled');
                } else if(stepPosition === 'last') {
                    $("#next-btn").addClass('disabled');
                } else {
                    $("#prev-btn").removeClass('disabled');
                    $("#next-btn").removeClass('disabled');
                }
            });

            $("#smartwizard").on("leaveStep", function(e, anchorObject, stepNumber, stepDirection) {
                if ( $("#nombre").val() =="") {
                    $("#smartwizard").smartWizard("goToStep", 0);
                    toastr.error("Ingresa código del Participante!",{timeOut:5000});
                    $("#parametro").focus();
                    return false;
                }
                if($("#nombre").val()== "" || $("#fecha").val()== "" || $("#expediente").val()== "" || $("#sector").select2().val() =="" ){
                    $("#smartwizard").smartWizard("goToStep", 0);
                    toastr.error("Informacion pendiente por ingresar!","ERROR",{timeOut:6000});
                    $("#parametro").focus();
                    return false;
                }
                if(stepNumber == 1 && stepDirection=="forward" ){
                    if( $("#nombre").val() == "") {
                        toastr.error("Nombre es requerido!", {timeOut: 5000});
                        return false;
                    } if( $("#sector").val().trim() == "") {
                        toastr.error("Seleccione el sector!", {timeOut: 5000});
                        return false;
                    }if( $("#direccion").val().trim() == "") {
                        toastr.error("Direccion es requerida!", {timeOut: 5000});
                        return false;
                    }if(  $("#expediente").val()=="" ) {
                        toastr.error("Seleccione el Recurso!", {timeOut: 5000});
                        return false;
                    }
                    $("#peso").focus();
                }

            });
            $("#btnObtenerRango").on("click", function(){
                GetRange();
            });
            function GetRange(){
                $.getJSON(Urls.GetRangeUrl,{ sexo : $('#sexo').val(), fecha : $('#fecha').val(), ajax : 'true'  }, function(data){
                    if (data.result == null){
                        $("#sdMin").val(data.objPsdmin);
                        $("#sdMed").val(data.objPsdmed);
                        $("#sdMax").val(data.objPsdmax);
                        $("#pamMin").val(data.objPammin);
                        $("#pamMed").val(data.objPammed);
                        $("#pamMax").val(data.objPammax);
                        $("#fcMin").val(data.objfcMin);
                        $("#fcMed").val(data.objfcMax);
                        $("#fcProm").val(data.objfcProm);
                        $("#frMin").val(data.objfrMin);
                        $("#frMax").val(data.objfrMax);
                        $("input[name=chkRange2]").attr("checked", true);
                    }else{
                        ClearRangos();

                        $("input[name=chkRange2]").attr("checked", false);
                    }
                })
            }
            //fin Buscar Participante
        }
    }

}();



var GuardarDinamica = function(){
    return{
        init : function(params){
            var form1 = $('#save-hemo-form');
            jQuery.validator.addMethod("myComa", function (value, element) {
                return this.optional(element) || /^[0-9]+([,][0-9]+)?$/.test(value);
            }, "Sólo números separado por coma.");

            $.validator.addMethod("validaSelect", function(value, element, arg){
                return arg != value;
            }, "Seleccione una opción");

            form1.validate({
                rules:{
                    idParticipante :{required: true},
                    asc:{
                        required: true,
                        number: true
                    },
                    imc:{
                        required: true,
                        number: true
                    },
                    chkpositivo:{required:true},
                    IMCdetallado:{required: true},
                    nombre:{required: true},
                    silais:{required: true},
                    municipio:{required:true},
                    fie:{required: true},
                    fconsulta:{required: true},
                    numParametro:{
                        required:true,
                        number: true
                    },
                    sector: {required: true,
                        validaSelect:"Seleccione una opción",
                        highlight: function(input) {
                            $(input).parents('.form-group').addClass('has-danger');
                        },
                        unhighlight: function(input) {
                            $(input).parents('.form-group').removeClass('has-danger');
                        },
                        errorPlacement: function(error, element) {
                            $(element).parents('.form-group').append(error);
                        }
                    },
                    expediente:{required: true},
                    telefono :{
                        //pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        maxlength: 8,
                        minlength: 8,
                        digits: true},
                    edad:{required: true},
                    peso:{required:true,
                        number: true},
                    talla:{required:true,
                        number: true},
                    diasenf:{
                        required:true,
                        min:1
                    },
                    uSalud:{required:true}
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
                    SaveHemo(Urls);
                }
            });
            function SaveHemo(dir) {
                var isValidData = true;
                //inicio valid
                if ($("#peso").val().trim() == null || $("#peso").val().trim() == "") {
                    $.notify("Peso es requerido", 'error');
                    isValidData = false;
                    $("#peso").focus();
                    return false;
                }

                if ($("#talla").val().trim() == null || $("#talla").val().trim() == "" || $("#talla").val().trim() === 'NaN') {
                    $.notify("Talla es requerido", 'error');
                    isValidData = false;
                    $("#talla").focus();
                    return false;
                }

                if (!($('#numParametro').val().trim() != '' && !isNaN($('#numParametro').val().trim()))) {
                    isValidData = false;
                    $('#numParametro').siblings('span.error').css('visibility', 'visible');
                    $.notify("Cantidad de parámetros es requerido", 'error');
                    $('#numParametro').focus();
                    return false;
                }
                if ($("#fconsulta").val() == "" || $("#fconsulta").val().trim() == null) {
                    isValidData = false;
                    $.notify("Fecha Consulta es requerida!", 'error');
                    $("#fconsulta").focus();
                    return false;
                }
                if (!validarFecha($("#fconsulta").val())) {
                    isValidData = false;
                    $.notify("Fecha Consulta formato incorrecto!", 'error');
                    return false;
                }
                if ($("#fie").val() == "" || $("#fie").val().trim() == null) {
                    isValidData = false;
                    $.notify("Fecha Inicio Enfermedad es requerida!", 'error');
                    $("#fie").focus();
                    return false;
                }
                if (!validarFecha($("#fie").val())) {
                    isValidData = false;
                    $.notify("Fecha Inicio Enfermedad Incorrecto!", 'ERROR',{timeOut:6000});
                    return false;
                }
                if (!$('input[name=chkpositivo]:checked').val()){
                    $.notify("Positivo o Negativo?");
                    isValidData = false;
                    return false;
                }
                //fin valid
                if (isValidData) {
                    $.post(dir.saveHemoUrl, form1.serialize(), function (data) {
                        console.log(data.msj);
                        debugger;

                        if(data.msj != null) {
                            toastr.error(data.msj,"ERROR",{timeOut:6000});
                            return false;
                        }else{
                        if (data.idDatoHemo != undefined) {
                            toastr.success(dir.successmessage, "success", {timeOut: 6000});
                            window.setTimeout(function () {
                                window.location.href = dir.adddetailsUrl + "/" + data.idDatoHemo;
                            }, 1300);
                        }
                    }
                     }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                     toastr.error("Interno del Servidor!","ERROR", {timeOut: 6000});
                     });
                }else{toastr.error("Datos Incorrectos!","ERROR",{timeOut:6000})}

            }
        }
    }
}();

