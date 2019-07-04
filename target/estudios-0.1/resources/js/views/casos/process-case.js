var ProcessCase = function () {
	
    return {
        //main function to initiate the module
        init: function (parametros) {

            var form1 = $('#search-participant-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    participantCode: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        required: true,
                        maxlength: 5
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
                    search();
                }
            });

            var form2 = $('#version-form');
            form2.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    codigoCasa: {
                        required: true
                    },
                    fechaInicio: {
                        required: true
                    },
                    codigoParticipante: {
                        required: true
                    },
                    fif: {
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

            function search()
            {
                $.getJSON( parametros.searchUrl , form1.serialize() , function( data )   {
                        //registro = JSON.parse(data);
                        console.log(data);
                        if (data.mensaje != undefined) {
                            toastr.error(data.mensaje,"Error",{timeOut: 0});
                            $("#codigoCasa").val("");
                            $("#codigoParticipante").val("");
                        }
                        else {
                            $("#codigoCasa").val(data.casaCHF.codigoCHF);
                            $("#codigoParticipante").val(data.participante.codigo);
                        }
                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error( "error:" + errorThrown);
                    });
            }

            function processVersion()
        	{
        	    $.post( parametros.saveUrl
        	            , form2.serialize()
        	            , function( data )
        	            {
        	    			registro = JSON.parse(data);
                            console.log(registro);
        	    			if (registro.codigoCaso === undefined) {
        						toastr.error(data,"Error",{timeOut: 0});
        					}
        					else {
                                toastr.success(parametros.successmessage);

                                window.setTimeout(function () {
                                    window.location.href = parametros.listaUrl;
                                }, 1500);
                            }
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error("error:" + errorThrown);
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
    };

}();