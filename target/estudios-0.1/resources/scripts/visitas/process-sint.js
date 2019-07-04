var CreateSint = function () {
	
    return {
        //main function to initiate the module
        init: function (parametros) {
        	$('#fechaSintomas, #fif, #ftos, #fsn, #frr').datepicker({
	            language: 'es',
	            format:'dd/mm/yyyy',
	            autoclose: true,
	            startDate: parametros.fechaInicio,
	            endDate: parametros.fechaFin
	        });
        	var form1 = $('#sint-form');
        	$("#fechaSintomas").mask("99/99/9999");
        	$("#fif").mask("99/99/9999");
        	$("#ftos").mask("99/99/9999");
        	$("#fsn").mask("99/99/9999");
        	$("#frr").mask("99/99/9999");
        	form1.validate( {
                rules: {
                	visita: 'required',
                	fechaSintomas: 'required',
                	fiebre: 'required',
                	fiebreCuantificada: 'required',
                	valorFiebreCuantificada:{
                		required: true,
                        min: 35,
                        max: 44,
                	},
                	dolorCabeza: 'required',
                	dolorArticular: 'required',
                	dolorMuscular: 'required',
                	dificultadRespiratoria: 'required',
                	secrecionNasal: 'required',
                	tos: 'required',
                	pocoApetito: 'required',
                	dolorGarganta: 'required',
                	diarrea: 'required',
                	quedoCama: 'required',
                	respiracionRuidosa: 'required',
                	oseltamivir: 'required',
                	antibiotico: 'required',
                	cualAntibiotico: 'required',
                	prescritoMedico: 'required'
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
                	processSint();
                }
              });
        	
        	function processSint(){
    	    $.post( parametros.saveSintUrl
	            , form1.serialize()
	            , function( data ){
	    			sintoma = JSON.parse(data);
	    			if (sintoma.codigoCasoSintoma === undefined) {
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
						$('#codigoCasoSintoma').val(sintoma.codigoCasoSintoma);
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
						toastr.success(parametros.processSuccess,sintoma.codigoCasoSintoma);
					}
	            	$('#visita').focus();
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
        	
        	$('#fiebre').change(function () {
	    		if ($('#fiebre').val() != "") {
	    			if ($('#fiebre').val() == "1") {
	    				$("#fifGroup").show("slow");
	    			} else {
	    				$("#fifGroup").hide("slow");
	    				$('#fif').val("");
	    			}
	    		}
	    	});
        	
        	$('#fiebreCuantificada').change(function () {
	    		if ($('#fiebreCuantificada').val() != "") {
	    			if ($('#fiebreCuantificada').val() == "1") {
	    				$("#fiebreCuantificadaGroup").show("slow");
	    			} else {
	    				$("#fiebreCuantificadaGroup").hide("slow");
	    				$('#valorFiebreCuantificada').val('');
	    			}
	    		}
	    	});
        	
        	$('#tos').change(function () {
	    		if ($('#tos').val() != "") {
	    			if ($('#tos').val() == "1") {
	    				$("#ftosGroup").show("slow");
	    			} else {
	    				$("#ftosGroup").hide("slow");
	    				$('#ftos').val("");
	    			}
	    		}
	    	});
        	
        	$('#secrecionNasal').change(function () {
	    		if ($('#secrecionNasal').val() != "") {
	    			if ($('#secrecionNasal').val() == "1") {
	    				$("#fsnGroup").show("slow");
	    			} else {
	    				$("#fsnGroup").hide("slow");
	    				$('#fsn').val("");
	    			}
	    		}
	    	});
        	
        	$('#dolorGarganta').change(function () {
	    		if ($('#dolorGarganta').val() != "") {
	    			if ($('#dolorGarganta').val() == "1") {
	    				$("#frrGroup").show("slow");
	    			} else {
	    				$("#frrGroup").hide("slow");
	    				$('#frr').val("");
	    			}
	    		}
	    	});
        	
        	$('#antibiotico').change(function () {
	    		if ($('#antibiotico').val() != "") {
	    			if ($('#antibiotico').val() == "1") {
	    				$("#antibioticoGroup").show("slow");
	    			} else {
	    				$("#antibioticoGroup").hide("slow");
	    				$("#prescritoMedico").val('');
	    				$("#cualAntibiotico").val('');
	    			}
	    		}
	    	});
        }
    };

}();