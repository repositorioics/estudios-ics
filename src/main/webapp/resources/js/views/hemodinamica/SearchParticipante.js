
























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