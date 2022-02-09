/**
 * Created by ICS on 26/05/2021.
 */
var saveExtensiones = function(){
    return{
        init: function(urls){
            var form1 = $("#formExt");
            form1.validate({
                rules:{
                    fechaExtension : { required: true },
                    nombre1tutor   : { required: true },
                    apellido1tutor : { required: true }
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
                        toastr.error("Seleccione la Extensión",{timeOut: 4000});
                        $('#idExtension').select2('open');
                        return;
                    }
                }
            });


            function SaveExtension(url){
                $.post(url.saveExtensCarta,form1.serialize(),function( data ){
                   //console.log(data);
                    if(data.msj != null){
                        var respuesta =  data;
                        toastr.error(data.msj,"Error",{timeOut: 5000});
                    }else{
                        var registro = JSON.parse(data);
                        if(registro.idParticipantExtension === undefined){
                            toastr.error(data,"Error",{timeOut: 5000});
                        }else {
                            CleanInput();
                            toastr.success(url.successmessage);
                            setTimeout(function () {
                                //window.location.reload();
                               window.location.href = url.listCartaUrl;
                            }, 1400);
                        }
                    }
                },'text').fail(function(XMLHttpRequest, textStatus, errorThrown) {
                    toastr.error("Fail Server!",{timeOut: 6000});
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
