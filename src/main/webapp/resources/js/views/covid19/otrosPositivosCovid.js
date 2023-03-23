/**
 * Created by ICS on 13/09/2021.
 */

var GuardarOtrosPositivos = function(){
    return {
        init: function(parametro){
            var form = $('#frm_otros_positivos');
            $("#frm_otros_positivos").validate( {
                rules: {
                    positivoPor:{
                        required: true
                    },
                    idparticipante: {
                        required: true
                    },
                    estudio:{
                        required: true
                    },
                    casaChf:{
                        required: true
                    },
                    fis: {
                        required: true
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
                    guardarOtrosPositivos(parametro);
                }
            });// fin validate

            function guardarOtrosPositivos(parametro){
                     $.post(parametro.saveOtrosCandidateTCovidUrl, form.serialize(), function( data ){
                         registro = JSON.parse(data);
                         if(registro.msj != null){
                             toastr.warning(registro.msj,"Advertencia!", {timeOut:6000});
                         } else if(registro.codigo!= undefined ){
                             toastr.success(parametro.successmessage);
                             window.setTimeout(function () {
                                 window.location.reload();
                             }, 1500);
                         }
                     },'text' )
                         .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                             toastr.error(errorThrown,"ERROR:",{timeOut:6000} );
                     });
            }

            var table_otrosPositivos = $("#lista_otros_positivos").DataTable({
                "oLanguage": {
                    "sUrl": parametro.dataTablesLang
                },columnDefs: [{
                    targets: 0,
                    className: 'text-center'
                }]
            });
            $("#lista_otros_positivos").on("click",'.swalPasivo', function(){
                var currentRow = $(this).closest("tr");
                var col0 = currentRow.find("td:eq(0)").text();
                var col1 = currentRow.find("td:eq(1)").text();
                var col3 = currentRow.find("td:eq(3)").text();
                ponerPasivo(col0,col1,col3);
            });

            function ponerPasivo(colmn0,column1, column3){
                swal({
                        title: "Desactivar? ",
                        text: "Registro: " + column1 + " con Fecha: " + column3,
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Si, Desactivar!",
                        cancelButtonText: "No, Desactivar plx!",
                        closeOnConfirm: true,
                        closeOnCancel: true
                    },
                    function (isConfirm) {
                        if (isConfirm) {
                            $.post(parametro.setPasivoUrl, {codigo: colmn0, ajax: 'true'}, function (data) {
                                swal("Desactivado!", "con éxito!", "success");
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            }).fail(function () {
                                setTimeout(function () {
                                    swal("Error!", "Servidor no respode!", "error");
                                }, 2000);
                            });
                        } else {
                            swal("Cancelado!", "Registro está seguro! :)", "error");
                        }
                    });
            }
            $('#lista_otros_positivos thead tr').clone(true).appendTo( '#lista_otros_positivos thead' );
            $('#lista_otros_positivos thead tr:eq(1) th').each( function (i) {
                var title = $(this).text();
                $(this).html( '<input type="text" placeholder="Búscar '+title+'" />' );
                $( 'input', this ).on( 'keyup change', function () {
                    if ( table_otrosPositivos.column(i).search() !== this.value ) {
                        table_otrosPositivos.column(i).search( this.value ).draw();
                    }
                });
            });
        }
    }
}();