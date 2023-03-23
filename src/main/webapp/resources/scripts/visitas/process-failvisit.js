var CreateFailVisit = function () {
	
    return {
        //main function to initiate the module
        init: function (parametros) {
        	$('#fechaVisita').datepicker({
	            language: 'es',
	            format:'dd/mm/yyyy',
	            autoclose: true,
	            startDate: parametros.fechaInicio
	        });
        	var form1 = $('#visit-form');
        	$("#fechaVisita").mask("99/99/9999");
        	$("#horaVisita").mask("99:99");
        	form1.validate( {
                rules: {
                	fechaVisita: 'required',
                	horaVisita: 'required',
                	razonVisitaFallida: 'required',
                	otraRazon:{
                		required: true
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
                	processFailVisit();
                }
              });
        	
        	function processFailVisit(){
        	    $.post( parametros.saveCasoFailVisitUrl
        	            , form1.serialize()
        	            , function( data ){
		        	    	caso = JSON.parse(data);
			    			if (caso.codigoCaso === undefined) {
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
								$('#guardar').prop( "disabled", true );
								$('a#finishlink').prop( "disabled", true );
								toastr.options = {
		    						  "closeButton": true,
		    						  "onclick": null,
		    						  "showDuration": "300",
		    						  "hideDuration": "1000",
		    						  "timeOut": 2000,
		    						  "extendedTimeOut": 0,
		    						  "tapToDismiss": false
		    						};
								toastr.success(parametros.processSuccess,caso.codigoCaso);
								window.setTimeout(function(){
							        window.location.href = parametros.casoDataUrl;
							    }, 1000);
							}
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
        	
        	$('#razonVisitaFallida').change(function () {
	    		if ($('#razonVisitaFallida').val() != "") {
	    			if ($('#razonVisitaFallida').val() != "9") {
	    				$("#otraRazonGroup").hide("slow");
	    				$('#otraRazon').val("");
	    			} else {
	    				$("#otraRazonGroup").show("slow");
	    			}
	    		}
	    	});
        }
    };

}();