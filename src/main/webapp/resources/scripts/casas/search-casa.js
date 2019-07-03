var SearchCasa = function () {
    return {
        //main function to initiate the module
        init: function (parametros) {
            var table  = $('#tabla_casas').DataTable({
                "oLanguage": {
        			"sUrl": parametros.dataTablesLang
                },
                searching: false,
    	        paging: false
            });
    		
            $( '#select-house-form' ).validate( {
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
                    table.clear().draw( false );
                    searchCasas();//llama la funcion searchCasa
                }
              });

            //Buscar una Casa
            function searchCasas()
        	{
            	$.getJSON(parametros.casasUrl, {
            		parametro : $('#parametro').val(),
    				ajax : 'true'
    			}, function(data) {
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
	    				for ( var i = 0; i < len; i++) {
	    					var d = new Date(data[i].fechaInicio);
	    					var partsUrl = parametros.partsUrl + '/'+data[i].codigoCaso+ '/';
	    					var datestring = ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
	    					table.row.add([data[i].casa.codigoCHF,
	    					               data[i].casa.nombre1JefeFamilia+ " " +data[i].casa.apellido1JefeFamilia,
	    					               datestring,'<a class="btn btn-info" href='+ partsUrl + '><i class="fa fa-search-plus "></i></a>'])
	    					               .draw( false );
	    				}
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
        }
    };

}();