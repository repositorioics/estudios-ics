var ids;
var SearchHemoParticipant = function () {
    return {
        init: function (parametros) {
            var table  = $('#tablePartHemo').DataTable({
                searching: false,
                paging: true,
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                }
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
                    buscar(parametros);
                }
            });

            function buscar(parametros){
                $.getJSON(parametros.ListaHoja, {parametro : $('#parametro').val(), ajax : 'true' },function(data){
                    var len = data.length;
                    if(len==0){
                        toastr.info("No se encontró Información", "INFORMACION!",{timeOut:6000});
                        $("#parametro").val("");
                        $("#parametro").focus();
                    }else{
                        for ( var i = 0; i < len; i++) {
                            var valor ="";
                            var partsUrl = parametros.edithemoUrl + '/'+data[i].idDatoHemo+ '/';
                            var editUrl = parametros.listDetailsHemoUrl + '/'+data[i].idDatoHemo+ '/';
                            var pdf = parametros.pdfUrl+ '/'+data[i].idDatoHemo+ '/';
                            var d =new Date(data[i].fecha);
                            var datestring =  ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                            var fReg = new Date(data[i].recordDate);
                            console.log(data[i].recordDate);
                            var fregistro =  ("0" + fReg.getDate()).slice(-2) + "/" + ("0"+(fReg.getMonth()+1)).slice(-2) + "/" + fReg.getFullYear() +" "+  fReg.getHours().toString() + ":"+ fReg.getMinutes();
                            var getCode = data[i].participante.codigo;
                            var NameComplete =  data[i].participante.nombre1+' '+ data[i].participante.nombre2+' '+ data[i].participante.apellido1+' '+data[i].participante.apellido1;
                            var edad = data[i].edad.substring(0,7);
                            table.row.add([
                                getCode,
                                NameComplete,
                                edad,
                                fregistro,
                                datestring,
                                valor = '<a class="btn btn-outline-primary btn-sm" target="_blank" href='+ partsUrl + '><i class="fa fa-edit"></i></a>',
                                valor = '<a class="btn btn-outline-success btn-sm" target="_blank" href='+ editUrl + '><i class="fa fa-history"></i></a>',
                                valor = '<a class="btn btn-outline-info btn-sm btnReporte" data-id="' + data[i].idDatoHemo + '"><i class="fa fa-book"></i></a>'
                            ]).draw( false );
                        }
                    }
                }).fail(function() {
                    toastr.error("Error Interno del Servidor","Error!", {timeOut:6000});
                    $("#parametro").focus();
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