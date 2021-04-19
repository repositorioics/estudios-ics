/**
 * Created by ICS on 16/11/2020.
 */

//var ListadoRetiros
var ListadoRetiros = function(){
    return{
        init: function(parametros){
            var table  = $('#tableRetiro').DataTable({
                searching: false,
                paging: true,
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                }, "columnDefs": [
                    {
                        "targets": [ 4 ],
                        "visible": false
                    }
                ]
            });

            $( '#select-participante-form' ).validate( {
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
                    searchParticipante();
                }
            });

            //Buscar un Participante
            function searchParticipante(){
                $.getJSON(parametros.ListaHojaRetiroUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    //console.log(data);
                    var len = data.length;
                    if(len==0){
                        //$.notify("No se encontró información", 'warning');
                        toastr.warning("No se encontró información",{timeOut: 1500});
                        $('#smartwizard').smartWizard("reset");
                        $("#parametro").focus();
                    }
                    else{
                        for ( var i = 0; i < len; i++) {
                            var getCode = data[i].participante.codigo;
                            var fr = new Date(data[i].fecharetiro);
                            var ffallecido ="";
                            if(data[i].fechafallecido !== null){
                                var objfecha = new Date(data[i].fechafallecido);
                                var datefallecido =  ("0" + objfecha.getDate()).slice(-2) + "/" + ("0"+(objfecha.getMonth()+1)).slice(-2) + "/" + objfecha.getFullYear();
                                ffallecido = '<span class="badge badge-danger"> '+ datefallecido +'</span>';
                            }else{
                                ffallecido = '<span class="badge badge-success"> NO</span>';
                            }
                            var datestring =  ("0" + fr.getDate()).slice(-2) + "/" + ("0"+(fr.getMonth()+1)).slice(-2) + "/" + fr.getFullYear();
                            var fReg = new Date(data[i].recordDate);
                            var nombre2 =   (data[i].participante.nombre2 === null) ? "-" : data[i].participante.nombre2;
                            var apellido2 = (data[i].participante.apellido2 == null) ? "-" : data[i].participante.apellido2;
                            var NameComplete =  data[i].participante.nombre1 +' '+ nombre2 +' '+ data[i].participante.apellido1+' '+ apellido2;
                            var fregistro = ("0" + fReg.getDate()).slice(-2) + "/" + ("0"+(fReg.getMonth()+1)).slice(-2) + "/" + fReg.getFullYear() +" "+ ('0' + fReg.getHours().toString()).slice(-2)  + ":"+  ('0' + fReg.getMinutes()).slice(-2) ;
                            table.row.add([
                                getCode,
                                datestring,
                                NameComplete,
                                data[i].estudioretirado,
                                data[i].motivo,
                                data[i].observaciones,
                                fregistro,
                                ffallecido,
                                valor = '<a class="btn btn-link btn-info like btnView" data-id='+ data[i].idretiro + '> <i class="text-info fa fa-eye"></i> </a>'
                            ]).draw( false );
                        }
                    }
                }).fail(function() {
                   //swal("Error","Falló el servidor!","error");
                    toastr.error("Falló el servidor!",{timeout:1500});
                    $('#smartwizard').smartWizard("reset");
                });
            }
        }
    }
}();