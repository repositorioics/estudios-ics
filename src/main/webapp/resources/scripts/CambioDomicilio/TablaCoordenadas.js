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
                    },
                    {
                        "targets": [12],
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
                        for ( var i = 0; i < len; i++) {
                            var d = new Date(data[i].movilInfo.today);
                            var datestring =  ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                            NameComplete =  data[i].participante.nombre1+' '+ data[i].participante.nombre2+' '+ data[i].participante.apellido1+' '+data[i].participante.apellido1;
                            var codigo =  data[i].codigo;
                            ids = codigo;
                            var text = "";
                            var valor ="";
                            switch (data[i].actual){
                                case "0":
                                    text = "No";
                                    break;
                                case "1":
                                    text = "Si";
                                    break;
                                default:
                                    text = "No value found";
                            }
                            var oBarrio;
                            if (data[i].otroBarrio == "" || data[i].otroBarrio == null){
                                oBarrio ="-"
                            }else{
                                oBarrio =  data[i].otroBarrio
                            }
                            if(data[i].actual == '0'){
                                valor = '<a class="btn btn-info disabled" href='+ parametros.UpdateActualUrl + '><i class="fa fa-refresh"></i></a>'

                            }else{
                                valor = '<a class="btn btn-info" onclick="Modificar()"><i class="fa fa-refresh"></i></a>'
                            }
                            var getCode = data[i].codigo; //"<strong class='pd-name'>"+ data[i].codigo +"</strong>";

                            table.row.add([
                                getCode,
                                data[i].participante.codigo,
                                NameComplete,
                                data[i].direccion,
                                (data[i].manzana != null) ? data[i].manzana : "-",
                                data[i].barrio.nombre,
                                oBarrio,
                                data[i].codigoCasa,
                                (data[i].casacohortefamilia != null) ? data[i].casacohortefamilia : "-",
                                data[i].estudios,
                                text,
                                datestring,
                                valor])
                                .draw( false );
                        }
                    }
                })
                    .fail(function() {
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