/**
 * Created by ICS on 18/01/2021.
 */
var proccessMuestra = function(){

    return {
        init: function(parametros){
            var form1 = $('#search-participant-form');
            form1.validate({
                rules:{
                    participantCode: {
                        required: true,
                        maxlength: 5
                    }
                },errorPlacement: function ( error, element ) {
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
                    searchParticipante();
                }
            });

            function searchParticipante(){
                debugger;
                $.getJSON(parametros.searchParticipantUrl,  form1.serialize(), function(resp) {
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
            });

            function eliminarMx(id, col1){
                swal({
                        title: "Eliminar? ",
                        text: "Código: " + id + " con Fecha: " + col1,
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Yes, delete it!",
                        cancelButtonText: "No, cancel plx!",
                        closeOnConfirm: false,
                        closeOnCancel: false
                    },
                    function(isConfirm) {
                        if (isConfirm) {

                            $.post(parametros.deleteMuestraUrl,{codigoMuestra : id, fechamuestra: col1,ajax : 'true'}, function(data){
                                swal("Eliminado!", "Con éxito!.", "success");
                                setTimeout(function () {
                                    location.reload();
                                }, 1300);
                            }).fail(function() {
                                setTimeout(function () {
                                    swal("Error!","Servidor no respode!","error");
                                }, 1500);
                            });
                        } else {
                            swal("Cancelado", "Registro está seguro! :)", "error");
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
                    fechaReg:{
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
                $.post( parametros.saveMuestraUrl, formMuestra.serialize(), function( data ){
                    registro = JSON.parse(data);
                    console.log(registro);
                    if (registro.mId.codigo === undefined) {
                        swal("Error!",data,"error");
                    }
                    else {
                        swal("Buen Trabajo!","Guardado éxitosamente!","success");
                        window.setTimeout(function () {
                            window.location.href = parametros.refreshPageUrl;
                        }, 1500);
                    }
                },'text' ).fail(function() {
                    swal("Error!","Servidor no responde!","error");
                });
            }


        }//fin init
    }// fin return
}();