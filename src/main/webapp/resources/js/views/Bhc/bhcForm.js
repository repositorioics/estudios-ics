/**
 * Created by ICS on 16/02/2022.
 */
var saveOrUpdateBhc = function(){
    return{
        init: function(parametro){

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
                    searchParticipante(parametro);
                    $("#volumen").focus();
                }
            });
            function searchParticipante(parametro){
                $.getJSON(parametro.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    console.log(data);
                    var len = data.length;
                    if(data.msj != undefined || data.msj != null){
                        swal({
                            title: "¡ERROR!",
                            type: "error",
                            text: data.msj,
                            timer: 2000
                        });
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
                        $("#fechaNac").val("");
                        $("#edadMeses").val("");
                        $("#parametro").focus();
                    } else{
                        let fecha1Formateada = moment(data.fechaNacimiento).format('YYYY/MM/DD');
                        $("#fechaNac").val(fecha1Formateada);
                        $("#idParticipante").val(data.codigo_participante);
                        $("#nombreCompleto").val(data.nombreCompleto);
                        $("#estudios").val(data.estudios);
                        $("#casaCHF").val(data.codigo_casa_familia);
                        $("#casaPDCS").val(data.codigo_casa_PDCS);
                        $("#edad_year").val(data.edadA);
                        $("#edad_meses").val(data.edadM);
                        $("#edad_dias").val(data.edadD);
                        $("#volumen_bhc_desde_bd").val(data.volumen_bhc_desde_bd);
                        $("#edadMeses").val(DifenciaMeses());
                        $("#observacion").val(data.observacion);
                        $("#estado").val(data.estado);
                        $("#volumen").val("");
                    }
                }).fail(function() {
                    swal({
                        title: "¡ERROR 500!",
                        type: "error",
                        text: "Interno del Servidor",
                        timer: 2000
                    });
                    $("#parametro").focus().val("");
                });
            }

            var form1 = $('#save-bhc-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules:{
                    volumen:{
                        required:true,
                        number: true,
                        min:0
                    },
                    fecha:{
                        required:true
                    },
                    idParticipante:{
                        required:true
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
                    Bhc(parametro);
                }
            });

            function Bhc(parametros){
                if($("#estado").val()==="0"){
                    swal({
                        title: "Participante Inactivo!",
                        text: "Seguro que deseas continuar?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-warning",
                        confirmButtonText: "Si, Guardar!",
                        closeOnConfirm: false,
                        closeOnCancel: false
                    }, function(){
                        save(parametros);
                    });
                }else if($("#estado").val()==="2"){
                    swal({
                        title: "Advertencia!",
                        text: "Deseas ingresar participante?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-warning",
                        confirmButtonText: "Si, Ingresar!",
                        closeOnConfirm: false,
                        closeOnCancel: false
                    }, function(){
                        save(parametros);
                    });
                }else {
                    save(parametros);
                }//fin valida estado
            }

            function save(parametro){
                var volumen_bhc_desde_bd = parseInt($("#volumen_bhc_desde_bd").val());
                if($("#volumen").val() != volumen_bhc_desde_bd) {
                    if(validObservacion()){+
                    swal({
                            title: "Diferencia en volumen!",
                            text:  "Volúmenes sugerido para Bhc: " + volumen_bhc_desde_bd + "\nDeseas continuar?",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonClass: "btn-warning",
                            confirmButtonText: "Si, Continuar!",
                            closeOnConfirm: false,
                            closeOnCancel: false
                        },
                        function () {
                            $.post(parametro.saveFormUrl, form1.serialize(), function (data) {
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
                                        text: parametro.successMessage,
                                        type: 'success',
                                        timer: 2000
                                    });
                                    window.setTimeout(function () {
                                        window.location.href = parametro.recepcionUrl;
                                    }, 3000);
                                    $("#parametro").focus().val("");
                                }
                            }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                                console.log("XMLHttpRequest: " + XMLHttpRequest, "textStatus: " + textStatus, "errorThrown:" + errorThrown);
                                swal({
                                    title: textStatus,
                                    text: errorThrown,
                                    type: 'error',
                                    timer: 2100
                                });
                            });
                        });
                    }
                }else{
                    $.post(parametro.saveFormUrl, form1.serialize(), function (data) {
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
                                text: parametro.successMessage,
                                type: 'success',
                                timer: 2000
                            });
                            window.setTimeout(function () {
                                window.location.href = parametro.recepcionUrl;
                            }, 3000);
                            $("#parametro").focus().val("");
                        }
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("XMLHttpRequest: "+XMLHttpRequest, "textStatus: "+textStatus, "errorThrown:"+errorThrown);
                        swal({
                            title: textStatus,
                            text: errorThrown,
                            type: 'error',
                            timer: 2100
                        });
                    });
                }
            }


            function validObservacion (){
                var isAllValid = true;
                if($("#observacion").val() == null || $("#observacion").val() == ""){
                    isAllValid = false;
                    $('#observacion').addClass('is-invalid').focus();
                }
                else{
                    $('#observacion').removeClass('is-invalid');
                }
                return isAllValid;
            }


            $( "#observacion").autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametro.getObservacionesUrl, {observacion: $('#observacion').val().trim(), ajax: 'true'},function(data){
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


            function DifenciaMeses(){
                debugger;
                var a = moment();
                var b = moment($("#fechaNac").val()).format('L');
                var months = a.diff(b, 'months', true);
                return months.toFixed(2);
            };

        }
    }
}();
