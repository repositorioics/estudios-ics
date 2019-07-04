var CreateVisit = function () {
	
	var handleInit = function () {
		$("#consTerrenoG").hide();
    	$("#referidoCsG").hide();
    	$("#tratamientoG").hide();
    	$("#cualAntibioticoG").hide();
    	$("#fiebreG").hide();
    	$("#fifG").hide();
    	$("#fffG").hide();
    	$("#tosG").hide();
    	$("#fitosG").hide();
    	$("#fftosG").hide();
    	$("#dolorGargantaG").hide();
    	$("#figgG").hide();
    	$("#ffggG").hide();
    	$("#secrecionNasalG").hide();
    	$("#fisnG").hide();
    	$("#ffsnG").hide();
    	
    	if ($('#enfermo').val() == "S") $("#consTerrenoG").show();
    	if ($('#enfermo').val() == "S") $("#referidoCsG").show();
    	if ($('#enfermo').val() == "S") $("#tratamientoG").show();
    	var trats = $('#tratamiento').val();
    	if (trats.indexOf("ANTBIO")!=-1) $("#cualAntibioticoG").show();
    	
    	if ($('#sintResp').val() == "S") $("#fiebreG").show();
    	if ($('#sintResp').val() == "S") $("#tosG").show();
    	if ($('#sintResp').val() == "S") $("#dolorGargantaG").show();
    	if ($('#sintResp').val() == "S") $("#secrecionNasalG").show();
    	if ($('#fiebre').val() == "1") $("#fifG").show();
    	if ($('#fiebre').val() == "1") $("#fffG").show();
    	if ($('#tos').val() == "1") $("#fitosG").show();
    	if ($('#tos').val() == "1") $("#fftosG").show();
    	if ($('#dolorGarganta').val() == "1") $("#figgG").show();
    	if ($('#dolorGarganta').val() == "1") $("#ffggG").show();
    	if ($('#secrecionNasal').val() == "1") $("#fisnG").show();
    	if ($('#secrecionNasal').val() == "1") $("#ffsnG").show();
    };
	
    return {
        //main function to initiate the module
        init: function (parametros) {
        	$('#tratamiento').select2();
        	$('#fechaVisita, #fif, #fff, #fitos, #fftos, #figg, #ffgg, #fisn, #ffsn').datepicker({
	            language: 'es',
	            format:'dd/mm/yyyy',
	            autoclose: true,
	            startDate: parametros.fechaInicio
	        });
        	handleInit();
        	var form1 = $('#visit-form');
        	$("#fechaVisita, #fif, #fff, #fitos, #fftos, #figg, #ffgg, #fisn, #ffsn").mask("99/99/9999");
        	$("#horaVisita").mask("99:99");
        	
        	jQuery.validator.addMethod("noNingunoyOtro", function(value, select) { 
            	var isValid = true;
            	var numero = 0;
            	$('option:selected', select).each(function() {
            		numero ++;
            	 });            		
                if (numero > 1 && select.options[select.options.length-1].selected) {
                	isValid = false;
                }
                return isValid;
	      	}, "Invalido");
        	
        	form1.validate( {
                rules: {
                	visita: 'required',
                	fechaVisita: 'required',
                	enfermo: 'required',
                	consTerreno: 'required',
                	referidoCs: 'required',
                	tratamiento: {
                    	required: {
		                	depends: function(element){
		                        return $("#enfermo option:selected").val()=="S";
		                    }
                    	},
                    	noNingunoyOtro:true
                    },
                    cualAntibiotico: 'required',
                    sintResp:'required',
                    fiebre:'required',
                    tos:'required',
                    dolorGarganta:'required',
                    secrecionNasal:'required',
                    fif:'required',
                    fff:'required',
                    fitos:'required',
                    fftos:'required',
                    figg:'required',
                    ffgg:'required',
                    fisn:'required',
                    ffsn:'required'
                },
                ignore: ':hidden:not("#tratamiento")',
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
                	processVisit();
                }
              });
        	
        	function processVisit(){
        	    $.post( parametros.saveVisitFinalUrl
        	            , form1.serialize()
        	            , function( data ){
        	    			visitaFinal = JSON.parse(data);
        	    			if (visitaFinal.codigoParticipanteCaso === undefined) {
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
        						$('#codigoParticipanteCaso').val(visitaFinal.codigoParticipanteCaso.codigoCasoParticipante);
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
        						toastr.success(parametros.processSuccess,visitaFinal.codigoParticipanteCaso.codigoCasoParticipante);
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
        	
        	$('#enfermo').change(function () {
	    		if ($('#enfermo').val() != "") {
	    			if ($('#enfermo').val() == "S") {
	    				$("#consTerrenoG").show();
	    		    	$("#referidoCsG").show();
	    		    	$("#tratamientoG").show();
	    			} else {
	    				$("#consTerrenoG").hide();
	    		    	$("#referidoCsG").hide();
	    		    	$("#tratamientoG").hide();
	    		    	$("#cualAntibioticoG").hide();
	    		    	$("#consTerreno").val("");
	    		    	$("#referidoCs").val("");
	    		    	$("#tratamiento").select2("data",null);
	    		    	$("#cualAntibiotico").val("");
	    			}
	    		}
	    	});
        	
        	$('#tratamiento').change(function () {
	    		if ($('#tratamiento').val() != "") {
	    			var trats = $('#tratamiento').val();
	    	    	if (trats.indexOf("ANTBIO")!=-1){
	    				$("#cualAntibioticoG").show();
	    			} else {
	    		    	$("#cualAntibioticoG").hide();
	    		    	$("#cualAntibiotico").val("");
	    			}
	    		}
	    	});
        	
        	$('#sintResp').change(function () {
	    		if ($('#sintResp').val() != "") {
	    			if ($('#sintResp').val() == "S") {
	    				$("#fiebreG").show();
	    				$("#tosG").show();
	    				$("#dolorGargantaG").show();
	    				$("#secrecionNasalG").show();
	    			} else {
	    				$("#fiebreG").hide();
	    		    	$("#fifG").hide();
	    		    	$("#fffG").hide();
	    		    	$("#tosG").hide();
	    		    	$("#fitosG").hide();
	    		    	$("#fftosG").hide();
	    		    	$("#dolorGargantaG").hide();
	    		    	$("#figgG").hide();
	    		    	$("#ffggG").hide();
	    		    	$("#secrecionNasalG").hide();
	    		    	$("#fisnG").hide();
	    		    	$("#ffsnG").hide();
	    		    	$("#fiebre").val("");
	    				$("#tos").val("");
	    				$("#dolorGarganta").val("");
	    				$("#secrecionNasal").val("");
	    				$("#fif").val("");
	    		    	$("#fff").val("");
	    		    	$("#fitos").val("");
	    		    	$("#fftos").val("");
	    		    	$("#figg").val("");
	    		    	$("#ffgg").val("");
	    		    	$("#fisn").val("");
	    		    	$("#ffsn").val("");
	    			}
	    		}
	    	});
        	
        	$('#fiebre').change(function () {
	    		if ($('#fiebre').val() != "") {
	    			if ($('#fiebre').val() == "1") {
	    				$("#fifG").show();
	    		    	$("#fffG").show();
	    			} else {
	    		    	$("#fifG").hide();
	    		    	$("#fffG").hide();
	    		    	$("#fif").val("");
	    		    	$("#fff").val("");
	    			}
	    		}
	    	});
        	
        	$('#tos').change(function () {
	    		if ($('#tos').val() != "") {
	    			if ($('#tos').val() == "1") {
	    				$("#fitosG").show();
	    		    	$("#fftosG").show();
	    			} else {
	    		    	$("#fitosG").hide();
	    		    	$("#fftosG").hide();
	    		    	$("#fitos").val("");
	    		    	$("#fftos").val("");
	    			}
	    		}
	    	});
        	
        	$('#dolorGarganta').change(function () {
	    		if ($('#dolorGarganta').val() != "") {
	    			if ($('#dolorGarganta').val() == "1") {
	    				$("#figgG").show();
	    		    	$("#ffggG").show();
	    			} else {
	    		    	$("#figgG").hide();
	    		    	$("#ffggG").hide();
	    		    	$("#figg").val("");
	    		    	$("#ffgg").val("");
	    			}
	    		}
	    	});
        	
        	$('#secrecionNasal').change(function () {
	    		if ($('#secrecionNasal').val() != "") {
	    			if ($('#secrecionNasal').val() == "1") {
	    				$("#fisnG").show();
	    		    	$("#ffsnG").show();
	    			} else {
	    		    	$("#fisnG").hide();
	    		    	$("#ffsnG").hide();
	    		    	$("#fisn").val("");
	    		    	$("#ffsn").val("");
	    			}
	    		}
	    	});
        }
    };

}();