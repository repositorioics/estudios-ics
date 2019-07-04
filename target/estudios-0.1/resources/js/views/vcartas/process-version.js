var ProcessVersion = function () {
	
    return {
        //main function to initiate the module
        init: function (parametros) {

            //$('#estudio, #version').select2();

            var form1 = $('#version-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    estudio: {
                        required: true
                    },
                    version: {
                        required: true
                    },
                    validFrom: {
                        required: true
                    },
                    validUntil: {
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
            
            function processVersion()
        	{
        	    $.post( parametros.saveUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			registro = JSON.parse(data);
        	    			if (registro.codigo === undefined) {
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
        		    		alert( "error:" + errorThrown);
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