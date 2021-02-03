/**
 * Created by ICS on 18/01/2021.
 */
var BuscarEstudios = function(){

    return {
        init: function(parametro){
            $("#search-participant-form").validate({
                rules:{
                    participantCode: {
                        required: true,
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        required: true,
                        maxlength: 5
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
                }
            });

            function searchParticipante(){
                $.getJSON(parametro.searchParticipant, { participantCode : $('#participantCode').val(),   ajax : 'true'  }, function(resp) {
                    if(resp.mensaje != null){
                        swal("Advertencia!",resp.mensaje,"warning");
                        $("#estudiosAct").val("");
                        $("#codigoMx").val("");
                        $("#FormMuestra").trigger("reset");
                    }else{
                        $("#codigoMx").val(resp.codigo);
                        $("#estudiosAct").val(resp.estudios);
                    }
                });
            }

            $("#codigoMx").keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });
            $("#tubobhc").keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });
            $("#tuboleuco").keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });
            $("#tuborojo").keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });

            $("#tblMuestra tbody").on("click",'.btndelete', function(){
                var id = $(this).data('id');
                var currentRow = $(this).closest("tr");
                var col0 = currentRow.find("td:eq(0)").text();
                var col1 = currentRow.find("td:eq(1)").text();
                eliminarMx(id,col1);
            })
            function eliminarMx(id, col1){
                swal({
                    title: "Eliminar? ",
                    text: "Código: " + id + " con Fecha: " + col1,
                    icon: "warning",
                    buttons: [
                        'No, cancélalo!',
                        'Si, Eliminar!'
                    ],
                    dangerMode: true
                }).then(function(isConfirm) {
                    if (isConfirm) {
                        swal({
                            title: 'Eliminado!',
                            text: 'Registro eliminado éxitosamente!',
                            icon: 'success'
                        }).then(function() {
                            $.post(parametro.deleteMuestraUrl,{codigoMuestra : id, fechamuestra: col1,ajax : 'true'}, function(data){
                                setTimeout(function () {
                                    location.reload();
                                }, 1300);
                            }).fail(function() {
                                setTimeout(function () {
                                    swal("Error!","Servidor no respode!","error");
                                }, 1500);
                            });
                        });
                    } else {
                        swal("Cancelado!", "Registro seguro. :)", "error");
                    }
                });
            }


            var formMuestra = $('#FormMuestra');
            formMuestra.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    codigoMx: {
                        required: true
                    },
                    fechaMx: {
                        required: true
                    },
                    recurso1: {
                        required: true
                    },
                    recurso2: {
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
                    processVersion();
                }
            });
            function processVersion(){
                if($("#tubobhc").val().trim() == "" && $("#tuboleuco").val().trim() == "" && $("#tuborojo").val().trim() == ""){
                    swal("Error!","Debes ingresar algún tubo", "warning");
                    $("#tubobhc").focus();
                    return;
                }

                $.post( parametro.saveMuestraUrl, formMuestra.serialize(), function( data ){
                    registro = JSON.parse(data);
                    console.log(registro);
                    if (registro.mId.codigo === undefined) {
                        swal("Error!",data,"error");
                    }
                    else {
                        swal("Buen Trabajo!","Guardado éxitosamente!","success");
                        window.setTimeout(function () {
                            window.location.href = parametro.refreshPageUrl;
                        }, 1500);
                    }
                },'text' ).fail(function() {
                     swal("Error!","Servidor no responde!","error");
                });
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
    }
}();
