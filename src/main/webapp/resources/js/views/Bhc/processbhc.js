/**
 * Created by ICS on 16/02/2022.
 */
var Bhc = function(){
    return{
        init: function(parametros){
            var table = $("#tblBhc").DataTable({
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                },
                "columnDefs": [
                    {
                        targets: [0,1,2,3],
                        className: 'text-center'
                    }
                ]
            });

            //***********************/
            var form1 = $('#envio-bhc-form');
            var $validator = form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    horaEnvio:{
                        required:true
                    },
                    numenvio: {
                        required: true
                    },
                    desde:{
                        required: true
                    },
                    hasta:{
                        required: true
                    },
                    desde: {required: function () {
                        return $('#desde').val().length > 0;
                    }},
                    hasta: {required: function () {
                        return $('#hasta').val().length > 0;
                    }},
                    temperatura:{
                        required: true,
                        number: true
                    }
                },errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    }else if ( element.prop( 'type' ) === 'text' ){
                        error.insertAfter(element.parent('.input-group'));
                    } else {
                        error.insertAfter( element ); //cuando no es input-group
                    }
                },highlight: function ( element, errorClass, validClass ) {
                    $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                    $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
                },unhighlight: function (element, errorClass, validClass) {
                    $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                    $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
                },submitHandler: function (form) {
                    var $validarForm = form1.valid();
                    if (!$validarForm) {
                        $validator.focusInvalid();
                        return false;
                    } else {
                        crearEnvio();
                    }
                }
            });

            function crearEnvio(){
                swal({
                    title: "Deseas enviar las Muestras...",
                    text: "para generar el Reporte?",
                    type: "info",
                    showCancelButton: true,
                    cancelButtonClass:"btn-warning",
                    cancelButtonText: "Cancelar!",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true,
                    confirmButtonText: "Si, enviar!"
                }, function (isConfirm) {
                    console.warn(isConfirm);
                    $.post(parametros.sendBhcUrl, form1.serialize(), function (data) {
                        //debugger;
                        //console.log(data);
                        if (data.mensaje != null) {
                            swal("INFORMACIÓN",data.mensaje,"info");
                        }
                        window.setTimeout(function () {
                            location.reload();
                        }, 1300);
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        swal(textStatus, errorThrown, "error");
                    });
                });
            }

            /*******************************/






        }
    }

}();