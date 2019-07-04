var SaveHemo = function(){
    return {
        init : function (parameters){
            var formhemo = $('#save-hemo-form');
            formhemo.validate({
                    rules:{
                        nombre: {
                            required:true,
                            minlength: 5,
                            maxlength: 50
                        },
                        apellido: {
                            required:true,
                            minlength: 5,
                            maxlength: 50
                        },
                        expediente: {required: true},
                        telefono:{required: true},
                        edad:{required: true},
                        direccion:{required:true},
                        peso:{required:true},
                        talla:{required:true},
                        peso:{required:true},
                        areaSupCorp:{required: true},
                        imc:{required:true},
                        sistolica:{required:true},
                        diastolica:{required:true},
                        pp:{required:true},
                        pam:{required:true}
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
            },highlight: function ( element, errorClass, validClass ) {
                $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
            },unhighlight: function (element, errorClass, validClass) {
                $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
            },submitHandler: function (form) {
               processHemo();
            }
            });
            function processHemo(){
                alert("procesos hemo");
               /* $("#btnSave").click(function(){
                    $("#result").text($("formhemo").serialize());
                });*/
            }
        }
    }
}();

