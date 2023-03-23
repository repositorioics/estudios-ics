var CreateCont = function () {
	
    return {
        //main function to initiate the module
        init: function (parametros) {
        	$('#partContacto, #tiempoInteraccion,  #tipoInteraccion').select2();
        	$('#fechaContacto').datepicker({
	            language: 'es',
	            format:'dd/mm/yyyy',
	            autoclose: true,
	            startDate: parametros.fechaInicio,
	            endDate: parametros.fechaFin
	        });
        	var form1 = $('#cont-form');
        	$("#fechaContacto").mask("99/99/9999");
        	form1.validate( {
                rules: {
                	fechaContacto: {
                        required: true
                    },
                	partContacto: {
                        required: true
                    },
                    tiempoInteraccion: {
                        required: true
                    },
                    tipoInteraccion: {
                    	required: {
		                	depends: function(element){
		                        return $("#tiempoInteraccion option:selected").val()!="5";
		                    }
                    	}
                    }
                },
                ignore: ':hidden:not("#partContacto, #tiempoInteraccion, #tipoInteraccion")',

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
                	processCont();
                }
              });
        	
        	function processCont(){
    	    $.post( parametros.saveContUrl
	            , form1.serialize()
	            , function( data ){
	    			contacto = JSON.parse(data);
	    			if (contacto.codigoCasoContacto === undefined) {
	    				data = data.replace(/u0027/g,"");
	    				toastr.options = {
	    						  "closeButton": true,
	    						  "onclick": null,
	    						  "showDuration": "300",
	    						  "hideDuration": "1000",
	    						  "timeOut": 0,
	    						  "extendedTimeOut": 0,
	    						  "tapToDismiss": false
	    						};
	    				toastr["error"](data, "Error!!");      						
					}
					else{
						$('#codigoCasoContacto').val(contacto.codigoCasoContacto);
						$('a#finishlink').text('Terminar');
						toastr.options = {
    						  "closeButton": true,
    						  "onclick": null,
    						  "showDuration": "300",
    						  "hideDuration": "1000",
    						  "timeOut": 2000,
    						  "extendedTimeOut": 0,
    						  "tapToDismiss": false
    						};
						toastr.success(parametros.processSuccess,contacto.codigoCasoContacto);
					}
	            	$('#partContacto').focus();
	            }
	            , 'text' )
		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
		  			toastr.options = {
						  "closeButton": true,
						  "onclick": null,
						  "showDuration": "300",
						  "hideDuration": "1000",
						  "timeOut": 0,
						  "extendedTimeOut": 0,
						  "tapToDismiss": false
						};
		  			toastr["error"](errorThrown, "Error!!");  
		  		});
        	}
        	
        	// register jQuery extension
        	jQuery.extend(jQuery.expr[':'], {
        	    focusable: function (el, index, selector) {
        	        return $(el).is('a, button, :input, [tabindex]');
        	    }
        	});

        	$(document).on('keypress', 'input,select', function (e) {
        	    if (e.which == 13) {
        	        e.preventDefault();
        	        // Get all focusable elements on the page
        	        var $canfocus = $(':focusable');
        	        var index = $canfocus.index(document.activeElement) + 1;
        	        if (index >= $canfocus.length) index = 0;
        	        $canfocus.eq(index).focus();
        	    }
        	});
        	
        	$('#tiempoInteraccion').change(function () {
	    		if ($('#tiempoInteraccion').val() != "") {
	    			if ($('#tiempoInteraccion').val() == "5") {
	    				$("#tipoInteraccionGroup").hide("slow");
	    				$('#tipoInteraccion').select2("data",null);
	    			} else {
	    				$("#tipoInteraccionGroup").show("slow");
	    			}
	    		}
	    	});
        }
    };

}();