/**
 * Created by ICS on 26/05/2021.
 */
var saveExtensiones = function(){
    return{
        init: function(urls){
            var form1 = $("#formExt");
            form1.validate({
                rules:{
                    nombre1Tutor    :{ required: true, letras_espacios:true },
                    nombre2Tutor    :{ letras_espacios:true },
                    apellido1Tutor  :{ required: true, letras_espacios:true },
                    apellido2Tutor  :{ letras_espacios:true },
                    fechaExtension  :{ required: true },
                    idExtension     :{ required: true}
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
                    if(!$("#idExtension").val() == ""){
                        SaveExtension(urls);
                    }else{
                        swal({
                            title: "Error!",
                            text:  "Seleccione la Extensión",
                            type: "error",
                            closeOnConfirm: true,
                            timer: 2000
                        });
                        $('#idExtension').select2('open');
                        return;
                    }
                }
            });


            function SaveExtension(url){
                $.post(url.saveExtensCarta,form1.serialize(),function( data ){
                   console.log(data);
                    if(data.msj != null){
                        var respuesta =  data;
                        swal({
                            title: "Error!",
                            text: data.msj,
                            type: "error",
                            closeOnConfirm: true,
                            timer: 2200
                        });
                    }else{
                        var registro = JSON.parse(data);
                        if(registro.idParticipantExtension === undefined){
                            swal({
                                title: "Error!",
                                text: data,
                                type: "error",
                                closeOnConfirm: true,
                                timer: 2200
                            });
                        }else {
                            CleanInput();
                            swal({
                                title: "Buen trabajo!",
                                text:  url.successmessage,
                                type: "success",
                                closeOnConfirm: true,
                                timer: 2200
                            });
                            setTimeout(function () {
                               window.location.href = url.listCartaUrl;
                            }, 1400);
                        }
                    }
                },'text').fail(function(XMLHttpRequest, textStatus, errorThrown) {
                    swal({
                        title: "Error 500!",
                        text:  "Interno del Servidor.",
                        type: "error",
                        closeOnConfirm: true,
                        timer: 2200
                    });
                });
            }

            function CleanInput(){
                $("#nombre1tutor").val('');
                $("#nombre2tutor").val('');
                $("#apellido1tutor").val('');
                $("#apellido2tutor").val('');
                $("#nombre1Testigo").val('');
                $("#nombre2Testigo").val('');
                $("#apellido1Testigo").val('');
                $("#apellido2Testigo").val('');
                $("#idExtension").val('').change();
            }


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




        }//fin init

    }//fin return

}();
