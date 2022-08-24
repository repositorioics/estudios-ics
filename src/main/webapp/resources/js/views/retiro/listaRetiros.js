/**
 * Created by ICS on 16/11/2020.
 */

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
                },errorPlacement: function ( error, element ) {
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
                    table.clear().draw( false );
                    searchParticipante();
                }
            });

            //Buscar un Participante
            function searchParticipante(){
                $.getJSON(parametros.ListaHojaRetiroUrl, { parametro : $('#parametro').val(), ajax : 'true' }, function(data) {
                    var len = data.length;
                    if(len==0){
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
                                valor = '<div class="btn-group dropleft">' +
                                        '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Accion </button>'
                             +'<div class="dropdown-menu">'
                                    + '<a class="dropdown-item btnView" data-id=' + data[i].idretiro + '><i class="fa fa-eye"></i> Detalles Retiro  </a>'
                                    + '<div class="dropdown-divider"></div>'
                                    + '<a class="dropdown-item btnReport" data-id='+ data[i].idretiro + '><i class="fa fa-file-text-o"></i> Reporte  </a>'
                                    + '</div>'
                             +'</div>'
                            ]).draw( false );
                        }
                    }
                }).fail(function() {
                    toastr.error("Falló el servidor!",{timeout:1500});
                    $('#smartwizard').smartWizard("reset");
                });
            }
        }
    }
}();