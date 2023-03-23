/**
 * Created by ICS on 18/01/2022.
 */
var ProcesarTutor = function(){
    return{
        init: function(parametros){

            $("#relacionFamNuevo").select2();

            $( '#select-participante-form' ).validate( {
                rules: {
                    parametro: 'required'
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
                }
            });

            $( '#corregir-tutor-form' ).validate( {
                /*rules: {
                    parametro: 'required'
                },*/
                errorElement: 'em',
                errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
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
                    save();
                }
            });

            $("#btnCancelar").click(function () {
                clearFields();

            });


            //Buscar un Participante
            function searchParticipante(){
                $.getJSON(parametros.searchUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    if (data.mensaje != undefined) {
                        swal("Aviso!", data.mensaje, 'info');
                        //$("#parametro").focus();
                    }
                    else{
                        $("#codigo").val(data.codigo);
                        $("#nombre").val(data.nombre);
                        $("#fechanac").val(data.fechaNac);
                        $("#edadPart").val(data.edad);
                        $("#sexoPart").val(data.sexo);

                        $("#nombre1ActTutor").val(data.nombre1ActTutor);
                        $("#nombre2ActTutor").val(data.nombre2ActTutor);
                        $("#apellido1ActTutor").val(data.apellido1ActTutor);
                        $("#apellido2ActTutor").val(data.apellido2ActTutor);
                        $("#relacionFamAct").val(data.relacionFamAct);

                        $("#nombre1NuevoTutor").val(data.nombre1ActTutor);
                        $("#nombre2NuevoTutor").val(data.nombre2ActTutor);
                        $("#apellido1NuevoTutor").val(data.apellido1ActTutor);
                        $("#apellido2NuevoTutor").val(data.apellido2ActTutor);
                        $("#relacionFamNuevo").val(data.relacionFamActKey).change();

                    }
                }).fail(function() {
                    swal("Error", "Falló el servidor!", 'error');
                });
            }

            function clearFields() {
                $("#parametro").val('');
                $("#codigo").val('');
                $("#nombre").val('');
                $("#fechanac").val('');
                $("#edadPart").val('');
                $("#sexoPart").val('');
                $("#nombre1ActTutor").val('');
                $("#nombre2ActTutor").val('');
                $("#apellido1ActTutor").val('');
                $("#apellido2ActTutor").val('');
                $("#relacionFamAct").val('');
                $("#nombre1NuevoTutor").val('');
                $("#nombre2NuevoTutor").val('');
                $("#apellido1NuevoTutor").val('');
                $("#apellido2NuevoTutor").val('');
                $("#relacionFamNuevo").val('').change();
                $("#observacion").val('');
                $("#parametro").focus();
            }

            function save() {
                console.log("save");
                var correccion = {};
                correccion['codigoParticipante'] = $("#codigo").val();
                correccion['nombre1NuevoTutor'] = $("#nombre1NuevoTutor").val();
                correccion['nombre2NuevoTutor'] = $("#nombre2NuevoTutor").val();
                correccion['apellido1NuevoTutor'] = $("#apellido1NuevoTutor").val();
                correccion['apellido2NuevoTutor'] = $("#apellido2NuevoTutor").val();
                correccion['relacionFamNuevo'] = $("#relacionFamNuevo").val();
                correccion['observacion'] = $("#observacion").val();
                $.ajax(
                    {
                        url: parametros.saveUrl,
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify(correccion),
                        contentType: 'application/json',
                        mimeType: 'application/json',
                        async: false,
                        success: function (data) {
                            console.log(data);
                            if (data.error != undefined && data.error.length > 0) {
                                swal("Error", data.error, 'error');
                            } else {
                                clearFields();
                                swal("Exito!", data.mensaje, 'success');
                            }
                            //desbloquearUI();
                        },
                        error: function (jqXHR) {
                            swal("Error", jqXHR, 'error');
                        }
                    });
            }

        }
    }
}();