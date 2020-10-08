var Urls = {};
var editDatos=function(){
    return{
        init:function(params){
            urls = params;
            var form2 = $('#update-hemo-form');
            form2.validate({
                rules:{
                    idParticipante :{required: true},
                    nombre:{required:true},
                    parametro :{required: true},
                    uSalud:{required:true},
                    chkpositivo:{required: true},
                    silais:{required: true},
                    municipio:{required:true},
                    fconsulta:{required: true},
                    sector: {required: true},
                    expediente:{required: true},
                    numParametro:{
                        required: true,
                        digits: true
                    },
                    telefono:{
                        digits: true,
                        maxlength: 8,
                        minlength: 8
                    },
                    edad:{required: true},
                    direccion:{required: true},
                    peso:{required:true,
                        number: true},
                    talla:{required:true,
                        number: true},
                    diasenf:{required:true},
                    pam:{required:true},
                    IMCdetallado:{required:true}
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
                    UpdateHemo(urls);
                }
            });
            function UpdateHemo(dir){
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
                    $.notify("Fecha Inicio Enfermedad Incorrecto!", 'error');
                    return false;
                }
                if (!$('input[name=chkpositivo]:checked').val()){
                    $.notify("Positivo o Negativo?");
                    isValidData = false;
                    return false;
                }
                if (isValidData) {
                $.post(dir.updateHemoUrl, form2.serialize(), function(data){
                    swal("Éxito!", "Información actualizada!", "success");
                    window.setTimeout(function(){
                        window.location.href = dir.Listado2Url;
                    }, 1400);
                }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                    swal("Error!","intente nuevamente!", "error");
                });
                }else{$.notify("Datos Incorrectos!","error")}

            }
        }
    }
}();

$(document).ready(function(){
    var getDate = function(input){
        return new Date(input.date.valueOf());
    };
    $("#fconsulta, #fie").datepicker({
        format: "dd/mm/yyyy",
        todayBtn:true,
        todayHighlight: true,
        autoclose: true,
        endDate: '-0d'
    });
    $("#fconsulta").on("changeDate", function(selected){
        $("#fie").val("");
        $("#diasenf").val(0);
        $('#fie').datepicker('setEndDate', getDate(selected));
    });
    $("#fie").on("changeDate", function(){
        var f1 = $("#fconsulta").val();
        var f2= $("#fie").val();
        var diasEnfermo = restaFechas(f1,f2);
        $("#diasenf").val(diasEnfermo);
        $.notify("Días Enfermo: "+ diasEnfermo, "info");
    })
    restaFechas = function(f1,f2){
        var aFecha1 = f1.split("/");
        var aFecha2 = f2.split("/");
        var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]);
        var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]);
        var dif =  fFecha1 - fFecha2;
        var dias = Math.floor(dif / (1000 * 60 * 60 * 24));
        if(dias === NaN){
            dias = 0;
        }
        return dias +1;
    }

    $("#telefono").keypress(function(e) {
        if (e.which == 13) {
            nextPass();
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

})
