var EditUser = function () {
	
	var handleMultiSelect = function () {
        $('#authorities').multiSelect();
        $('#studies').multiSelect();
    };
    
    var handlePasswordStrengthChecker = function () {
        var initialized = false;
        var input = $("#password");

        input.keydown(function () {
            if (initialized === false) {
                // set base options
                input.pwstrength({
                    raisePower: 1.4,
                    minChar: 8,
                    verdicts: ["Weak", "Normal", "Medium", "Strong", "Very Strong"],
                    showVerdicts:false,
                    scores: [17, 26, 40, 50, 60]
                });

                // add your own rule to calculate the password strength
                input.pwstrength("addRule", "demoRule", function (options, word, score) {
                    return word.match(/[a-z].[0-9]/) && score;
                }, 10, true);

                // set as initialized 
                initialized = true;
            }
        });
    };

    return {
        //main function to initiate the module
        init: function (parametros) {

            // wrapper function to  block element(indicate loading)
            function blockUI(el, centerY) {
                var el = jQuery(el);
                var loc = window.location;
                var pathName = loc.pathname.substring(0,loc.pathname.indexOf('/', 1)+1);
                var mess = '<img src=' + pathName + 'resources/img/ajax-loading.gif align="">';
                if (el.height() <= 400) {
                    centerY = true;
                }
                el.block({
                    message: mess,
                    centerY: centerY != undefined ? centerY : true,
                    css: {
                        top: '10%',
                        border: 'none',
                        padding: '2px',
                        backgroundColor: 'none'
                    },
                    overlayCSS: {
                        backgroundColor: '#000',
                        opacity: 0.05,
                        cursor: 'wait'
                    }
                });
            }

// wrapper function to  un-block element(finish loading)
            function unblockUI (el) {
                jQuery(el).unblock({
                    onUnblock: function () {
                        jQuery(el).removeAttr("style");
                    }
                });
            }

            handleMultiSelect();
            handlePasswordStrengthChecker();
            var form1 = $('#edit-user-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                	username: {
                        minlength: 5,
                        maxlength: 50,
                        noSpace:true,
                        required: true
                    },
                    completeName: {
                        minlength: 5,
                        maxlength: 250,
                        required: true
                    },
                    email: {
                        minlength: 3,
                        maxlength: 100,
                        email: true
                    },
                    password: {
                        minlength: 4,
                        maxlength: 150,
                        noSpace:true,
                        required: true
                        //pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[!@#$%^&*()?])).+$/
                    },
                    confirm_password: {
                        minlength: 4,
                        maxlength: 150,
                        required: true,
                        noSpace:true,
                        equalTo: "#password"
                    },
                    authorities: {
                        required: true
                    },
                    /*studies: {
                        required: true
                    },*/
                    firstname: {
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
                    processUser();
                }
            });
            
            function processUser()
        	{
            	blockUI();
        	    $.post( parametros.saveUserUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			usuario = JSON.parse(data);
        	    			if (usuario.username === undefined) {
        						toastr.error(data,"Error",{timeOut: 0});
        					}
        					else{
        						$('#username').val(usuario.username);
        						toastr.success(parametros.successmessage,usuario.username);
        					}
        	            	$('#completeName').focus();
        	    			unblockUI();
                            window.setTimeout(function(){
                                window.location.href = parametros.usuarioUrl;
                            }, 1500);
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
        		    		alert( "error:" + errorThrown);
        		    		unblockUI();
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