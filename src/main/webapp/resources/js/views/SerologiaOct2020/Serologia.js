/**
 * Created by ICS on 15/10/2020.
 */
var endPointSero = {};
var Serologia2020 = function(){
    return{
        init: function(urls){
            endPointSero = urls;
            $("#select-participante-form").validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules:{
                    parametro: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        maxlength: 5,
                        required: true
                    }
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
                    $("#volumen").focus();
                }
            });

            function searchParticipante(){
                $.getJSON(endPointSero.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    debugger;
                    var len = data.length;
                    if(data.mensaje != undefined){
                        toastr.error(data.mensaje,"ERROR!",{timeOut:6000});
                        Limpiartxt();
                        $("#fechaNac").val("");
                        $("#edadMeses").val("");
                    }
                    if(len==0){
                        toastr.warning("Código no encontrado","ADVERTENCIA!",{timeOut:6000});
                        Limpiartxt();
                        $("#precepciona").val("").change();
                        $("#fechaNac").val("");
                        $("#edadMeses").val("");
                        $("#parametro").focus();
                    } else{
                        if(data.estado == 0){
                            toastr.error("Participante Retirado!", "ERROR!",{timeOut:6000});
                            Limpiartxt();
                        }else{
                            var hoy=moment();
                            $("#idParticipante").val(data.codigo);
                            $("#nombreCompleto").val(data.nombreCompleto);
                            $("#estudios").val(data.estudios);
                            $("#casaCHF").val(data.casaFamilia);
                            $("#volumen").val("");
                            var dob = new Date(data.fechaNacimiento);
                            var fnac = moment(dob).format('YYYY-MM-DD');
                            var anios = hoy.diff(fnac,"years");
                            var meses = hoy.diff(fnac,"months");
                            var amd = daysMonthsYearsInDates(moment(dob).format('DD/MM/YYYY'),moment().format('DD/MM/YYYY'));
                            $("#edadPart").val(amd);
                            $("#fechaNac").val(fnac);
                            $("#edadMeses").val(DifenciaMeses);
                        }
                    }
                }).fail(function() {
                    Limpiartxt();
                    toastr.error("Código no existe!", "Error!",{timeOut:6000});
                    $("#parametro").val("");
                    $("#parametro").focus();
                });
            }


            var form1 = $('#save-Serologia-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules:{
                    volumen:{
                        required:true,
                        number: true,
                        min:1
                    }
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
                    saveSerologia(endPointSero);
                }
            });

            function saveSerologia(urls){
                var isAllValid = true;
                debugger;
                $("#edadMeses").val(DifenciaMeses);
                var edad = $("#edadMeses").val();
                var vol = $("#volumen").val();
                if(edad < 6 && vol > 2){
                    toastr.error("Volumen permitido de la Muestra para esta edad es hasta 2 cc","Error",{timeOut:6000});
                    isAllValid = false;
                    return;
                }else if(edad >= 6 && edad < 24 && vol > 4){
                    toastr.error("Volumen permitido de la Muestra para esta edad 4 cc","Error",{timeOut:6000});
                    isAllValid = false;
                    return;
                }else if(edad >= 24 && edad < 168 && vol > 8 ){
                    toastr.error("Volumen permitido de la Muestra para esta edad 8 cc","Error",{timeOut:6000});
                    isAllValid = false;
                    return;
                }else if(edad >= 168 && vol > 18){
                    toastr.error("Volumen permitido de la Muestra para esta edad 12 cc","Error",{timeOut:6000});
                    isAllValid = false;
                    return;
                }

                if (isAllValid) {
                    var formSerologia = $("#save-Serologia-form");
                    $.post(urls.saveFormUrl, formSerologia.serialize(), function (data) {
                        if (data.msj != null) {
                            toastr.error( data.msj, "Error!",{timeOut:6000});
                        } else {
                            toastr.success(urls.successMessage);
                            Limpiartxt();
                            $("#parametro").val("");
                            $("#parametro").focus();
                        }
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error("Error 500 Internal Server", "ERROR!",{timeOut:6000});
                    });

                }//fin isValid
            }

            function DifenciaMeses(){
                var numeroMeses;
                var dateA = moment();
                var dateB = moment($("#fechaNac").val()).format('YYYY-MM-DD');
                var years = moment().diff($("#fechaNac").val(), 'years');
                var years = moment().diff(dateB, 'years', false);
                return numeroMeses = (years) * 12;
            };


            $("#volumen").on("keypress",function (e) {
                if(e.which === 13){
                    if(this.value === 12){
                        $("#observacion").val('2 Tubos');
                    }else{
                        $("#observacion").val('');
                    }
                }
            })

            function Limpiartxt(){
                $("#idSerologia").val("");
                $("#idParticipante").val("");
                $("#fechaNac").val("");
                $("#edadMeses").val("");
                $("#nombreCompleto").val("");
                $("#estudios").val("");
                $("#casaCHF").val("");
                $("#volumen").val("");
                $("#edadPart").val("");
                $("#observacion").val("");
                $("#precepciona").val("").change();
                $("#parametro").focus();
            }
        }
    }
}();
