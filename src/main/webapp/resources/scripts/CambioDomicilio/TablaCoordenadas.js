/**
 * Created by ICS_Inspiron3 on 07/08/2019.
 */
    var ids;
    var NameComplete;
    var direct={};
var SearchCoordenadas = function () {
    return {
        //main function to initiate the module
        init: function (parametros) {
            direct = parametros;
            var table  = $('#tblCoor').DataTable({
                "order": [[ 10, "desc" ]],
                "ordering": false,
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                },
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false,
                        "searchable": false
                    },{
                        "targets": [5],
                        "visible": false,
                        "searchable": false
                    },{
                        "targets": [10],
                        "visible": false,
                        "searchable": false
                    }
                ],
                searching: false,
                paging: false

            });
            $( '#select-Coordenadas-form' ).validate( {
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
                    searchCoordenada();//llama la funcion searchCasa
                }
            });

            //Buscar una lista de la tabla datos_coordenadas
            function searchCoordenada(){$.getJSON(parametros.ListaCoordenadasUrl, {parametro : $('#parametro').val(), ajax : 'true' }, function(data) {
                    var len = data.length;
                    if(len==0){
                        swal("Advertencia!", "Datos no encontrados!", "warning");
                    }
                    else{
                        $.each(data, function(index, element){
                            var codigo0 = element[0];
                            var codigo1 = element[1];
                            var codigo2 = element[2];
                            var codigo3 = element[3];
                            var codigo4 = element[4];
                            var codigo5 = element[5];
                            var codigo6 = element[6];
                            var codigo7 = element[7];
                            var codigo8 = element[8];
                            var codigo9 = element[9];
                            var codigo10 = element[10];
                            var codigo11 = element[11];
                            var codigo12 = element[12];
                            table.row.add([
                                codigo0,
                                codigo1,
                                codigo2,
                                codigo3,
                                codigo4,
                                codigo5,
                                codigo6,
                                codigo7,
                                codigo8,
                                codigo9,
                                codigo10,
                                codigo11,
                                codigo12
                            ]).draw( false );
                        })
                    }
                }).fail(function() {
                        swal("Error!", "Falló al obtener la información!", "error");
                    });
            }
        }
    };

}();

function Modificar(){
    var tables = $("#tblCoor").DataTable();
    var codigo="";
    $("#tblCoor tbody").on("click", ".btn", function(){
        var data = tables.row( this ).data();
        codigo = data[0];
        $("#accionUrl").val(codigo);
        $("#cuerpo").text(NameComplete);
        $("#exampleModal").modal("show");
        //alert( 'You clicked on '+data[0]+'\'s row' );
    });

}
//direct.parametros
$("#btnSend").on("click", function(){
    $.getJSON(direct.UpdateActualUrl,{ codigo : $("#accionUrl").val(),   ajax : 'true'  }, function(data){
        var len = data.length;
        if(len == 0 ){
            swal("Oops!","No se actualizó ningún dato","warning");
        }
        if(data == "Datos Actualizados"){
            $("#exampleModal").modal("hide");
            location.reload();
            window.setTimeout(function(){
                window.location.href = parametros.usuarioUrl;
            }, 1500);
        }else{
            swal("Error!", "intente nuevamente", "error");
            $("#exampleModal").modal("hide");
        }
    });



})