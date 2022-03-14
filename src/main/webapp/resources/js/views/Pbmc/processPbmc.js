/**
 * Created by ICS on 03/02/2022.
 */
var processPbmc = function(){
    return {
        init:function(parametros){
            var form1 = $('#select-participante-form');
            form1.validate({
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
                    $("#volumen_pbmc").focus();
                }
            });

            function searchParticipante(){
                $.getJSON(parametros.searchPartUrl, { parametro : $('#parametro').val(), ajax : 'true' }, function(data) {
                    //console.log(data);
                    if(data.mensaje!=null){
                        swal({
                         title: 'Advertencia!',
                         text: data.mensaje,
                         timer: 2000,
                         type: "warning"
                         });
                        window.setTimeout(function(){
                            window.location.href = parametros.reloadUrl;
                        }, 2000);
                    }
                    if(data.es_pbmc ==='No'){
                        swal({
                            title: "¡Advertencia!",
                            type: "warning",
                            text: "Participante no Aplica para PBMC!",
                            timer: 2000
                        });
                    }
                    var len = data.length;
                    if(len==0){
                        swal({
                            title: "¡ERROR!",
                            type: "info",
                            text: data.mensaje,
                            timer: 2000
                        });
                    }else {
                        var estado = data.estado;
                        $("#codigo_participante").val(data.codigo_participante);
                        let fecha1Formateada = moment(data.fechaNacimiento).format('YYYY/MM/DD');
                        $("#fechaNac").val(fecha1Formateada);
                        $("#estudios").val(data.estudios);
                        $("#edadA").val(data.edadA);
                        $("#edadM").val(data.edadM);
                        $("#estado").val(data.estado);
                        $("#edadEnMeses").val(data.edadEnMeses);
                        $("#codigo_casa_PDCS").val(data.codigo_casa_PDCS);
                        $("#codigo_casa_familia").val(data.codigo_casa_familia);
                        $("#volumen_pbmc_desde_bd").val(data.volumen_pbmc_desde_bd);
                        $("#volumen_adicional_desde_bd").val(data.volumen_adicional_desde_bd);
                        $("#edadEnMeses").val(DifenciaMeses());
                    }
                });
            }


            function DifenciaMeses(){
                debugger;
                var a = moment();
                var b = moment($("#fechaNac").val()).format('L');
                var months = a.diff(b, 'months', true);
                return months.toFixed(2);
            };

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
                    $.getJSON(parametros.getObservacionesUrl, {observacion: $('#observacion').val().trim(), ajax: 'true'},function(data){
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

            /**/
            var form_Pbmc = $('#save-pbmc-form');
            form_Pbmc.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules:{
                    codigo_participante:{
                        required:true
                    },
                    fecha:{
                        required:true
                    },
                    volumen_pbmc:{
                        required:true,
                        number: true,
                        min:0
                    },
                    volumen_rojo_adic:{
                        number: true,
                        min:0
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
                    Pbmc(parametros);
                }
            });

            function Pbmc(parametros){
                if($("#estado").val()==="Inactivo"){
                    swal({
                            title: "Participante Inactivo!",
                            text: "Seguro que deseas continuar?",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonClass: "btn-warning",
                            confirmButtonText: "Si, Guardar!",
                            closeOnConfirm: false
                        }, function(){
                           save(parametros);
                        });
                }else if($("#estado").val()==="Reactivar"){
                    swal({
                            title: "Advertencia!",
                            text: "Deseas reactivar participante?",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonClass: "btn-warning",
                            confirmButtonText: "Si, Reactivar!",
                            closeOnConfirm: false
                        }, function(){
                            save(parametros);
                        });
                }else {
                   save(parametros);
                }//fin valida estado
            }

            function save(parametros){
                var volumen_pbmc_desde_bd = parseInt($("#volumen_pbmc_desde_bd").val());
                var volumen_adicional_desde_bd = parseInt($("#volumen_adicional_desde_bd").val());
                if($("#volumen_pbmc").val() != volumen_pbmc_desde_bd || $("#volumen_rojo_adic").val() != volumen_adicional_desde_bd ){
                    if(validObservacion ()) {
                        swal({
                                title: "Diferencia en volumen!",
                                text:  "Volúmenes sugerido para PBMC: " + volumen_pbmc_desde_bd + " y Rojo: " + volumen_adicional_desde_bd + "\nDeseas continuar?",
                                type: "warning",
                                showCancelButton: true,
                                confirmButtonClass: "btn-warning",
                                confirmButtonText: "Si, Continuar!",
                                closeOnConfirm: false
                            },
                            function () {
                                $.post(parametros.savePbmctUrl, form_Pbmc.serialize(), function (data) {
                                    if (data.msj != null) {
                                        swal({
                                            title: "Advertencia!",
                                            text: data.msj,
                                            timer: 2000,
                                            type: "info"
                                        });
                                    } else {
                                        swal({
                                            title: "Éxito!",
                                            text: parametros.successMessage,
                                            timer: 2000,
                                            type: "success"
                                        });
                                    }
                                    window.setTimeout(function () {
                                        window.location.href = parametros.reloadUrl;
                                    }, 2000);
                                });
                            });
                    }
                }else{
                    $.post(parametros.savePbmctUrl, form_Pbmc.serialize(), function (data) {
                        //console.log(data);
                        if(data.msj!=null){
                            swal({
                                title: "Advertencia!",
                                text : data.msj,
                                timer: 2000,
                                type: "info"
                            });
                        }else{
                            swal({
                                title: "Éxito!",
                                text : parametros.successMessage,
                                timer: 2000,
                                type: "success"
                            });
                        }
                        window.setTimeout(function(){
                            window.location.href = parametros.reloadUrl;
                        }, 2000);
                    });
                }
            }
        }//fin init
    }//fin return
}();