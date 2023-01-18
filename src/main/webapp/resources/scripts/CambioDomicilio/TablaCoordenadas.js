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
                initComplete: function () {
                    // Apply the search
                    this.api().columns().every( function () {
                        var that = this;

                        $( 'input', this.footer() ).on( 'keyup change clear', function () {
                            if ( that.search() !== this.value ) {
                                that
                                    .search( this.value )
                                    .draw();
                            }
                        } );
                    } );
                },
                "autoWidth": false,
                "order": [[ 4, "asc" ]],
                "ordering": true,
                searching: true,
                paging: true,
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                },
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false,
                        "searchable": false
                    },{
                        "targets": [6],
                        "visible": false,
                        "searchable": false
                    },{
                        "targets": [10],
                        "visible": false,
                        "searchable": false
                    },{
                        "targets": [11],
                        "visible": false,
                        "searchable": false
                    }
                ]
            });
            $('#tblCoor tfoot th').each( function () {
                var title = $(this).text();
                $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
            } );
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
                //console.log( data);
                    var len = data.length;
                    if(len==0){
                        swal("Advertencia!", "Datos no encontrados!", "warning");
                    }
                    else{
                        for(var i = 0; i < len; i++){
                            var d = new Date(data[i].fecha_REGISTRO);
                            var datestring = ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                            table.row.add([
                                data[i].codigo,
                                data[i].codigo_PARTICIPANTE,
                                data[i].codigo_CASA,
                                data[i].codigo_CHF,
                                datestring,
                                data[i].fecha_REPORTADO,
                                data[i].codigo_BARRIO,
                                data[i].nombre,
                                data[i].otro_BARRIO,
                                data[i].string_MANZANA,
                                data[i].direccion,
                                data[i].idPersona,
                                data[i].nombrePersona,
                                data[i].nombre_USUARIO,
                                data[i].observacion
                            ]).draw( false );
                        }
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