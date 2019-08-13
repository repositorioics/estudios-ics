/**
 * Created by ICS_Inspiron3 on 06/08/2019.
 */
var SearchPdvi = function () {
    return {
        //main function to initiate the module
        init: function (parametros2) {
            var table  = $('#tblCoor2').DataTable({
                "order": [[ 4, "desc" ]],
                "ordering": false,
                "oLanguage": {
                    "sUrl": parametros2.dataTablesLang
                },
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false,
                        "searchable": false
                    }
                ],
                searching: false,
                paging: false
            });


            $( '#select-Coordenadas2-form' ).validate( {
                rules: {
                    parametro2: 'required'
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
                    searchPdvi();
                }
            });
            function searchPdvi(){
                $.getJSON(parametros2.ListaPdviUrl, {parametro2 : $('#parametro2').val(), ajax : 'true' }, function(data) {
                    console.log(data);
                    var len = data.length;
                    if(len==0){
                        swal("Advertencia!", "Datos no encontrados!", "warning");
                    }
                    else{
                        for ( var i = 0; i < len; i++) {
                            var d = new Date(data[i].fechacambio);
                            var datestring = ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                            var day;
                            switch (data[i].barrio) {
                                case 0:
                                case null:
                                    day = "-";
                                    break;
                                case 1:
                                    day = "Monseñor Lezcano";
                                    break;
                                case 2:
                                    day = "Cuba";
                                    break;
                                case 3:
                                    day = "Santa Ana Sur";
                                    break;
                                case 4:
                                    day = "Santa Ana Norte";
                                    break;
                                case 5:
                                    day = "La Cruz";
                                    break;
                                case  6:
                                    day = "Cristo del Rosario";
                                    break;
                                case 7:
                                    day = "San Sebastián";
                                    break;
                                case 8:
                                    day = "San Antonio";
                                    break;
                                case 9:
                                    day = "San José";
                                    break;
                                case 10:
                                    day = "Bóer";
                                    break;
                                case 11:
                                    day = "William Diaz";
                                    break;
                                case 12:
                                    day = "Martha Quezada";
                                    break;
                                case 13:
                                    day = "El Carmen y Reforma";
                                    break;
                                case 14:
                                    day = "Manuel Olivares";
                                    break;
                                case 15:
                                    day = "Julio Buitrago";
                                    break;
                                case 16:
                                    day = "Javier Cuadra";
                                    break;
                                case 17:
                                    day = "Manchester";
                                    break;
                                case 18:
                                    day = "Fuera de Sector";
                                    break;
                                case 19:
                                    day = "Las Palmas"
                            }
                            table.row.add([
                                data[i].id,
                                data[i].codigo,
                                (data[i].codigo_casa_pdcs != null)? data[i].codigo_casa_pdcs : "-",
                                (data[i].codigo_casa_chf != null)? data[i].codigo_casa_chf  : "-",
                                datestring,
                                day,
                                data[i].ndireccion,
                                (data[i].manzana != null)? data[i].manzana : "-",
                                (data[i].origen != null)? data[i].origen : "-" ])
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