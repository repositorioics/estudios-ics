var ProcessCaseUO1 = function () {
	
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

            var form2 = $('#enterform');
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
                    fis: {required: function () {
                        return $('#fif').val().length <= 0;
                    }},
                    fif: {required: function () {
                        return $('#fis').val().length <= 0;
                    }},
                    positivoPor: {
                        required: true
                    }
                },
                messages: {
                    fis: {
                        required: "FIS o FIF es requerida"
                    },
                    fif: {
                        required: "FIS o FIF es requerida"
                    }
                },
                errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    console.log(element.prop( 'type' ));
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } if ( element.prop( 'type' ) === 'select-one' ) {
                        error.insertAfter( element );
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
                    saveCaseUO1();
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
                            $("#codigoCasa").val(data.casa.codigo);
                            $("#codigoParticipante").val(data.codigo);
                        }
                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error( "error:" + errorThrown);
                    });
            }

            function saveCaseUO1()
        	{
        	    $.post( parametros.saveUrl
        	            , form2.serialize()
        	            , function( data )
        	            {
        	    			registro = JSON.parse(data);
        	    			if (registro.codigoCasoParticipante === undefined) {
        						toastr.error(registro.mensaje,"Error",{timeOut: 0});
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