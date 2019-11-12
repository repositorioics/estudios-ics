var NameComplete;
var ids;
var SearchHemoParticipant = function () {
    return {
        init: function (parametros) {
            var table  = $('#tablePartHemo').DataTable({
                searching: false,
                paging: false
            });
            $("#select-Participante-form").validate( {
                rules: {
                    parametro: 'required'
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
                    table.clear().draw( false );
                    bucar(parametros);
                }
            });

            function bucar(parametros){
                $.getJSON(parametros.ListaHoja, {parametro : $('#parametro').val(), ajax : 'true' },function(data){
                    var len = data.length;
                    if(len==0){
                        swal("Advertencia!", "Datos no encontrados!", "warning");
                    }else{

                        for ( var i = 0; i < len; i++) {
                            console.log(data[i].fecha);
                            var valor ="";
                            //var datestring =  ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                            var getCode = data[i].participante.codigo;
                            var NameComplete =  data[i].participante.nombre1+' '+ data[i].participante.nombre2+' '+ data[i].participante.apellido1+' '+data[i].participante.apellido1;
                            var edad = data[i].edad;
                            var expe = data[i].nExpediente;
                            var f =data[i].fecha;
                            table.row.add([
                                getCode,
                                NameComplete,
                                edad,
                                expe,
                                data[i].fecha,
                                valor = '<a class="btn btn-info" href='+ parametros.UpdateActualUrl + '><i class="fa fa-refresh"></i></a>',
                                valor = '<a class="btn btn-info" href='+ parametros.UpdateActualUrl + '><i class="fa fa-refresh"></i></a>',
                                valor = '<a class="btn btn-info" href='+ parametros.UpdateActualUrl + '><i class="fa fa-refresh"></i></a>'
                            ]).draw( false );
                        }

                    }

                }).fail(function() {
                    swal("Error!", "Falló al obtener la información!", "error");
                });

            }


        }
    };// fin return
}();

















/**
var SearchParticipante = function(){
    return{
       init: function(parametros){

           $( '#select-participante-form' ).validate( {
               rules: {
                   parametro: 'required'
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
                   searchParticipante();//llama la funcion searchCasa
               }
           });

           //Buscar un Participante
           function searchParticipante()
           {
               $.getJSON(parametros.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                   var len = data.length;
                   if(len==0){
                       toastr.options = {
                           "closeButton": true,
                           "onclick": null,
                           "showDuration": "300",
                           "hideDuration": "1000",
                           "timeOut": 3000,
                           "extendedTimeOut": 0,
                           "tapToDismiss": false
                       };
                       toastr["warning"](parametros.notFound);
                   }
                   else{
                        console.log(data);

                   }
               })
                   .fail(function() {
                       toastr.options = {
                           "closeButton": true,
                           "onclick": null,
                           "showDuration": "300",
                           "hideDuration": "1000",
                           "timeOut": 0,
                           "extendedTimeOut": 0,
                           "tapToDismiss": false
                       };
                       toastr["error"]("error", "Error!!");
                   });
           }

           $(document).on('keypress','form input',function(event)
           {
               event.stopImmediatePropagation();
               if( event.which == 13 )
               {
                   event.preventDefault();
                   var $input = $('form input');
                   if( $(this).is( $input.last() ) )
                   {
                       //Time to submit the form!!!!
                       //alert( 'Hooray .....' );
                   }
                   else
                   {
                       $input.eq( $input.index( this ) + 1 ).focus();
                   }
               }
           });
       }


    }

}();
**/