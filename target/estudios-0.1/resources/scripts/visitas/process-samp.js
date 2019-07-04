var CreateSamp = function () {
	
	var handleInit = function () {
		$("#tuboGroup").hide();
    	$("#conMxGroup").hide();
    	$("#sinMxGroup").hide();
    	$("#razonNoTomaSangGroup").hide();
    	$("#razonNoTomaRespGroup").hide();
    	$("#otraRazonGroup").hide();
    	$("#otraObservacionGroup").hide();
    	$("#numPinchazosGroup").hide();
    	$("#realizaPaxgeneGroup").hide();
    	$("#horaPaxgeneGroup").hide();
    	$("#codigoMxGroup").hide();
    	if ($('#tipoMuestra').val() == "1") $("#tuboGroup").show();
    	if ($('#tomaMxSn').val() == "S") $("#conMxGroup").show();
    	if ($('#tomaMxSn').val() == "N") $("#sinMxGroup").show();
    	if ($('#tipoMuestra').val() == "1" && $('#tomaMxSn').val() == "N") $("#razonNoTomaSangGroup").show();
    	if ($('#tipoMuestra').val() == "1" && $('#tomaMxSn').val() == "S") $("#numPinchazosGroup").show();
    	if ($('#tipoMuestra').val() != "1" && $('#tomaMxSn').val() == "N") $("#razonNoTomaRespGroup").show();
    	if ($('#razonNoTomaSangGroup').val() == "998" || $('#razonNoTomaRespGroup').val() == "998") $("#otraRazonGroup").show();
    	if ($('#observacion').val() == "998") $("#otraObservacionGroup").show();
    	if ($('#tipoMuestra').val() == "1" && $('#tomaMxSn').val() == "S" && $('#tubo').val() == "2") $("#realizaPaxgeneGroup").show();
    	if ($('#realizaPaxgene').val() == "S") $("#horaPaxgeneGroup").show();
    };
    
    var handleReset = function () {
    	$("#conMxGroup").hide();
    	$("#sinMxGroup").hide();
    	$("#razonNoTomaSangGroup").hide();
    	$("#razonNoTomaRespGroup").hide();
    	$("#otraRazonGroup").hide();
    	$("#otraObservacionGroup").hide();
    	$("#numPinchazosGroup").hide();
    	$("#realizaPaxgeneGroup").hide();
    	$("#horaPaxgeneGroup").hide();
    	$('#razonNoTomaSang').select2("data",null);
    	$('#razonNoTomaResp').select2("data",null);
    	$('#descOtraRazonNoToma').val("");
    	$('#codigoMx').val("");
    	$('#hora').val("");
    	$('#volumen').val("");
    	$('#observacion').select2("data",null);
    	$('#descOtraObservacion').val("");
    	$('#numPinchazos').select2("data",null);
    	$('#realizaPaxgene').select2("data",null);
    	$("#horaInicioPax").val("");
		$("#horaFinPax").val("");
    };
	
    return {
        //main function to initiate the module
        init: function (parametros) {
        	
        	$('#tipoMuestra, #tubo, #observacion, #realizaPaxgene, #numPinchazos, #razonNoTomaResp, #razonNoTomaSang').select2();
        	$("#hora").mask("99:99");
        	$("#horaInicioPax").mask("99:99");
        	$("#horaFinPax").mask("99:99");
        	handleInit();
        	var maximo = 0;
        	if ($('#tubo').val() == "2") {
        		maximo = 2;
    		} else {
    			maximo = 12;
    		}
        	var form1 = $('#samp-form');
        	form1.validate( {
                rules: {
                	tipoMuestra: {
                        required: true
                    },
                    tubo: {
                    	required: {
		                	depends: function(element){
		                        return $("#tipoMuestra option:selected").val()=="1";
		                    }
                    	}
                    },
                    tomaMxSn: {
                        required: true
                    },
                    hora: {
                    	required: {
	                    	depends: function(element){
		                        return $("#tomaMxSn option:selected").val()=="S";
		                    }
                    	}
                    },
                    codigoMx: {
                    	required: false
                    },
                    volumen: {
                    	required: {
	                    	depends: function(element){
		                        return $("#tomaMxSn option:selected").val()=="S";
		                    }
                    	},
                    	min:0,
      				  	max: maximo
                    },
                    observacion: {
                    	required: {
		                	depends: function(element){
		                        return $("#tomaMxSn option:selected").val()=="S";
		                    }
                    	}
                    },
                    descOtraObservacion: {
                    	required: {
	                    	depends: function(element){
		                        return $("#tomaMxSn option:selected").val()=="S";
		                    }
                    	}
                    },
                    numPinchazos: {
                    	required: {
	                    	depends: function(element){
	                    		return ($("#tomaMxSn option:selected").val()=="S" && $("#tipoMuestra option:selected").val()=="1");
		                    }
                    	}
                    },
                    realizaPaxgene: {
                    	required: {
                    		depends: function(element){
                    			return ($("#tomaMxSn option:selected").val()=="S" && $("#tubo option:selected").val()=="2");
                    		}
                    	}
                    },
                    horaInicioPax: {
                    	required: {
	                    	depends: function(element){
		                        return ($("#tomaMxSn option:selected").val()=="S" && $("#tubo option:selected").val()=="2");
		                    }
                    	}
                    },
                    horaFinPax: {
                    	required: {
	                    	depends: function(element){
	                    		return ($("#tomaMxSn option:selected").val()=="S" && $("#tubo option:selected").val()=="2");
		                    }
                    	}
                    },
                    razonNoTomaSang: {
                    	required: {
	                    	depends: function(element){
		                        return ($("#tomaMxSn option:selected").val()=="N" && $("#tipoMuestra option:selected").val()=="1");
		                    }
                    	}
                    },
                    razonNoTomaResp: {
                    	required: {
	                    	depends: function(element){
	                    		return ($("#tomaMxSn option:selected").val()=="N" && $("#tipoMuestra option:selected").val()!="1");
		                    }
                    	}
                    },
                    descOtraRazonNoToma: {
                    	required: {
	                    	depends: function(element){
	                    		return ($("#razonNoTomaResp option:selected").val()=="998" || $("#razonNoTomaSang option:selected").val()=="998");
		                    }
                    	}
                    }
                },
                ignore: ':hidden:not("#tipoMuestra, #tubo, #observacion, #realizaPaxgene, #numPinchazos, #razonNoTomaResp, #razonNoTomaSang")',

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
                	processSamp();
                }
              });
        	
        	function processSamp(){
    	    $.post( parametros.saveSampUrl
	            , form1.serialize()
	            , function( data ){
	    			sample = JSON.parse(data);
	    			if (sample.codigo === undefined) {
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
						$('#codigo').val(sample.codigo);
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
						toastr.success(parametros.processSuccess,sample.codigo);
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
        	
        	$('#tipoMuestra').change(function () {
	    		if ($('#tipoMuestra').val() != "") {
	    			$('#tomaMxSn').val("");
	    			handleReset();
	    			$( "#volumen" ).rules( "remove", "min max" );
	    			if ($('#tipoMuestra').val() != "1") {
	    				$("#tuboGroup").hide("slow");
	    				$('#tubo').select2("data",null);
	    				$( "#volumen" ).rules( "add", {
      					  min: 0,
      					  max: 5
      					});
	    			} else {
	    				$("#tuboGroup").show("slow");
	    			}
	    		}
	    	});
        	
        	$('#tomaMxSn').change(function () {
	    		if ($('#tomaMxSn').val() != "" && $('#tipoMuestra').val() != "") {
	    			handleReset();
	    			if ($('#tomaMxSn').val() == "S") {
	    				$("#conMxGroup").show();
	    		    	$("#sinMxGroup").hide();
	    		    	if ($('#tipoMuestra').val() == "1") $("#numPinchazosGroup").show();
	    		    	if ($('#tipoMuestra').val() == "1" && $('#tomaMxSn').val() == "S" && $('#tubo').val() == "2") $("#realizaPaxgeneGroup").show();
	    			} else {
	    				$("#conMxGroup").hide();
	    		    	$("#sinMxGroup").show();
	    		    	if ($('#tipoMuestra').val() == "1") $("#razonNoTomaSangGroup").show();
	    		    	if ($('#tipoMuestra').val() != "1") $("#razonNoTomaRespGroup").show();
	    			}
	    		}
	    	});
        	
        	$('#razonNoTomaSang').change(function () {
	    		if ($('#razonNoTomaSang').val() != "") {
	    			if ($('#razonNoTomaSang').val() == "998") {
	    				$("#otraRazonGroup").show("slow");
	    			} else {
	    				$("#otraRazonGroup").hide("slow");
	    				$("#otraRazon").val("");
	    			}
	    		}
	    	});
        	
        	$('#razonNoTomaResp').change(function () {
	    		if ($('#razonNoTomaResp').val() != "") {
	    			if ($('#razonNoTomaResp').val() == "998") {
	    				$("#otraRazonGroup").show("slow");
	    			} else {
	    				$("#otraRazonGroup").hide("slow");
	    				$("#otraRazon").val("");
	    			}
	    		}
	    	});
        	
        	$('#tubo').change(function () {
	    		if ($('#tubo').val() != "") {
	    			$( "#volumen" ).rules( "remove", "min max" );
	    			if ($('#tubo').val() == "2") {
	    				$("#realizaPaxgeneGroup").show("slow");
	    				$( "#volumen" ).rules( "add", {
        					  min: 0,
        					  max: 2
        					});
	    			} else {
	    				$("#realizaPaxgeneGroup").hide("slow");
	    				$("#horaPaxgeneGroup").hide("slow");
	    				$("#realizaPaxgene").select2("data",null);
	    				$("#horaInicioPax").val("");
	    				$("#horaFinPax").val("");
	    				$( "#volumen" ).rules( "add", {
        					  min: 0,
        					  max: 12
        					});
	    			}
	    		}
	    	});
        	
        	$('#observacion').change(function () {
	    		if ($('#observacion').val() != "") {
	    			if ($('#observacion').val() == "998") {
	    				$("#otraObservacionGroup").show("slow");
	    			} else {
	    				$("#otraObservacionGroup").hide("slow");
	    				$("#descOtraObservacion").val("");
	    			}
	    		}
	    	});
        	
        	$('#realizaPaxgene').change(function () {
	    		if ($('#realizaPaxgene').val() != "") {
	    			if ($('#realizaPaxgene').val() == "S") {
	    				$("#horaPaxgeneGroup").show("slow");
	    			} else {
	    				$("#horaPaxgeneGroup").hide("slow");
	    				$("#horaInicioPax").val("");
	    				$("#horaFinPax").val("");
	    			}
	    		}
	    	});
        	
        }
    };

}();