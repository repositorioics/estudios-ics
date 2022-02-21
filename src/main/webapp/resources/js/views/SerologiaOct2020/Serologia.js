/**
 * Created by ICS on 15/10/2020.
 */
var endPointSero = {};
var SerologiaMA = function(){
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
                    var len = data.length;
                    if(data.msj != undefined || data.msj != null){
                        swal({
                            title: "¡ERROR!",
                            type: "error",
                            text: data.msj,
                            timer: 2000
                        });
                        Limpiartxt();
                        $("#fechaNac").val("");
                        $("#edadMeses").val("");
                    }
                    if(len==0){
                        swal({
                            title: "¡ERROR!",
                            type: "info",
                            text: data.msj,
                            timer: 2000
                        });
                        Limpiartxt();
                        $("#fechaNac").val("");
                        $("#edadMeses").val("");
                        $("#parametro").focus();
                    } else{
                        console.log(data);
                        var hoy=moment();
                        $("#idParticipante").val(data.idparticipante);
                        $("#nombreCompleto").val(data.nombreCompleto);
                        $("#estudios").val(data.estudios);
                        $("#casaCHF").val(data.codigo_casa_Familia);
                        $("#casaPDCS").val(data.codigo_casa_PDCS);
                        $("#edad_year").val(data.edad_year);
                        $("#edad_meses").val(data.edad_meses);
                        $("#edad_dias").val(data.edad_dias);
                        $("#volumen_adicional_desde_bd").val(data.volumen_adicional_desde_bd);
                        $("#volumen_serologia_desde_bd").val(data.volumen_serologia_desde_bd);
                        $("#edadMeses").val(data.edadEnMeses);
                        $("#observacion").val(data.observacion);
                        $("#estado").val(data.estado);
                        $("#volumen").val("");
                    }
                }).fail(function() {
                    Limpiartxt();
                    //toastr.error("Código no existe!", "Error!",{timeOut:6000});
                    swal({
                        title: "¡ERROR 500!",
                        type: "error",
                        text: "Interno del Servidor",
                        timer: 2000
                    });
                    $("#parametro").focus().val("");
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

            $('.submit_on_enter').on('keyup',function(event) {
                if (event.which ===13) {
                    event.preventDefault();
                    $("#save-Serologia-form").submit();
                }
            });


            function saveSerologia(urls){
                if($("#estado").val()===0 || $("#estado")=="") {
                    swal({
                        title: "Desear continuar?",
                        text: "Participante Nuevo Ingreso!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Si, continuar!",
                        cancelButtonText: "No, cancelar plx!",
                        closeOnConfirm: false,
                        closeOnCancel: false
                    }, function (isConfirm) {
                        if (isConfirm) {
                            swal("Buen Trabajo!", urls.successMessage, "success");
                        } else {
                            swal("Cancelado!", "Operación ha sido cancelada :)", "info");
                        }
                    });
                }else if($("#estado").val()===3){
                    swal({
                        title: "Deseas continuar?",
                        text: "Reactivar Participante",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Si, continuar!",
                        cancelButtonText: "No, cancelar plx!",
                        closeOnConfirm: false,
                        closeOnCancel: false
                    }, function (isConfirm) {
                        if (isConfirm) {
                            swal("Buen Trabajo!", urls.successMessage, "success");
                        } else {
                            swal("Información!", "Operación ha sido cancelada :)", "info");
                        }
                    });
                }else{
                    saveSerologiaAjax(urls);
                }
            }//fin de la funcion saveSerologia


            function saveSerologiaAjax(urls){
                var volumen = parseInt($("#volumen").val());
                var volumen_desde_db = parseInt($("#volumen_serologia_desde_bd").val());

                var formSerologia = $("#save-Serologia-form");
                if(volumen!=volumen_desde_db){
                    swal({
                            title: "Diferencia de Volumen?",
                            text: "Deseas continuar?\n"+" Volumen sugerido para Serologia: "+ volumen_desde_db,
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonClass: "btn-danger",
                            confirmButtonText: "Si, continuar!",
                            cancelButtonText: "No, cancelar plx!",
                            closeOnConfirm: false,
                            closeOnCancel: false
                        },
                        function(isConfirm) {
                            if (isConfirm) {
                                $.post(urls.saveFormUrl, formSerologia.serialize(), function (data) {
                                    if (data.msj != null) {
                                        swal({
                                            title: "¡ERROR!",
                                            text: data.msj,
                                            type: "error",
                                            timer: 2000
                                        });
                                        window.setTimeout(function () {
                                            location.reload(true);
                                        }, 3000);
                                    } else {
                                        swal({
                                            title: "¡Buen trabajo!",
                                            text: urls.successMessage,
                                            type: 'success',
                                            timer: 2000
                                        });
                                        window.setTimeout(function () {
                                            window.location.href = urls.createUrl;
                                        }, 3000);
                                        $("#parametro").focus().val("");
                                    }
                                }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                                    console.log("XMLHttpRequest: "+XMLHttpRequest, "textStatus: "+textStatus, "errorThrown:"+errorThrown);
                                    swal({
                                        title: "¡ERROR 500!",
                                        text: "Interno del Servidor.",
                                        type: 'error',
                                        timer: 2000
                                    });
                                });
                            } else {
                                swal("Cancelado", "Tu registro está seguro :)", "info");
                            }
                        });
                }else {
                    $.post(urls.saveFormUrl, formSerologia.serialize(), function (data) {
                        if (data.msj != null) {
                            swal({
                                title: "¡ERROR!",
                                text: data.msj,
                                type: "error",
                                timer: 2000
                            });
                            window.setTimeout(function () {
                                location.reload(true);
                            }, 3000);
                        } else {
                            swal({
                                title: "¡Buen trabajo!",
                                text: urls.successMessage,
                                type: 'success',
                                timer: 2000
                            });
                            window.setTimeout(function () {
                                window.location.href = urls.createUrl;
                            }, 3000);
                            $("#parametro").focus().val("");
                        }
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("XMLHttpRequest: "+XMLHttpRequest, "textStatus: "+textStatus, "errorThrown:"+errorThrown);
                        swal({
                            title: "¡ERROR 500!",
                            text: "Interno del Servidor.",
                            type: 'error',
                            timer: 2000
                        });
                    });
                }
            }

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
                $("#casaPDCS").val();
                $("#edad_year").val();
                $("#edad_meses").val();
                $("#edad_dias").val();
                $("#parametro").focus().val("");
            }
        }
    }
}();
