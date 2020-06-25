/**
 * Created by ICS on 02/06/2020.
 */

var processCandidateCovid = function(){

    return {
        init: function (parametros) {
            var form1 = $('#search-participant-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    participantCode: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        required: true,
                        maxlength: 5
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
                    search();
                }
            });

            function search(){
                $.getJSON( parametros.searchUrl , form1.serialize() , function( data )   {
                        //registro = JSON.parse(data);
                        console.log(data);
                        if (data.mensaje != undefined) {
                            toastr.error(data.mensaje,"Error",{timeOut: 5000});
                            $("#casaCHF").val("");
                            $("#codigoParticipante").val("");
                            $("#estudios").val("");
                        }
                        else {
                            $("#casaCHF").val(data.casaFamilia);
                            $("#codigoParticipante").val(data.codigo);
                            $("#estudios").val(data.estudios);
                            if (data.validacion != null){
                                toastr.warning(data.validacion);
                            }
                        }
                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error( "error:" + errorThrown);
                    });
            }

            var form2 = $('#candiate-form');
            form2.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    positivoPor: {
                        required: true
                    },
                    codigoParticipante: {
                        required: true
                    },
                    fis: {
                        required: true
                    },
                    fechaIngreso: {
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
                    processCandidate();
                }
            });

            function processCandidate(){
                $.post( parametros.saveUrl, form2.serialize(), function( data ){
                        registro = JSON.parse(data);
                        console.log(registro);
                        if (registro.codigo === undefined) {
                            toastr.error(data,"Error",{timeOut: 5000});
                        }
                        else {
                            toastr.success(parametros.successmessage);
                            window.setTimeout(function () {
                                window.location.href = parametros.listaUrl;
                            }, 1500);
                        }
                    },'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error("error:" + errorThrown);
                    });
            }

        }
    }

}();
