/**
 * Created by ICS on 18/02/2020.
 */
var urls={};
var SearchCartaParticipant = function () {
    return {
    init: function(parametros){
        urls = parametros;
        var table  = $('#tableCartParticipnt').DataTable({
            searching: false,
            "info" : false,
            paging: true,
            "oLanguage": {
                "sUrl": parametros.dataTablesLang
            },
            "columnDefs": [
            {
                "targets": [5],
                "visible": false,
                "searchable": false
            }
        ]
        });
        var table2 = $("#tblDetalleParte").DataTable({
             searching  : false,
             paging     : false,
            "ordering"  : false,
            "info"      : false,
            "oLanguage" : {
                "sUrl"  : parametros.dataTablesLang
            },
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }
            ]
        });

        $("#select-participante-form").validate( {
            rules: {
                parametro: 'required',
                number: true
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
                Buscarcp(parametros);
            }


        });// fin validate



        function Buscarcp(dir){
            $.getJSON(dir.GetCartasParticipanteUrl,{parametro : $('#parametro').val(), ajax : 'true' },function(data){
                //console.log(data);
                var len = data.length;
                if(len == 0){
                    swal("Advertencia!", "No existe Información!", "warning");
                    $("#parametro").val("");
                    $("#parametro").focus();
                }else{
                    for ( var i = 0; i < len; i++) {
                        var valor ="";
                        var reporte="";
                        var id = data[i].idparticipantecarta;
                        var btntRetirar ="";
                        var partsUrl = parametros.EditCartaUrl +'/'+data[i].idparticipantecarta;
                        var getCode = data[i].participante.codigo;
                        var NameComplete =  data[i].participante.nombre1 +' '+ data[i].participante.nombre2 +' '+ data[i].participante.apellido1 +' '+ data[i].participante.apellido1;
                        var carta = data[i].version.carta.carta;
                        var version = data[i].version.version;
                        var d = new Date( data[i].fechacarta);
                        var retirado = (data[i].esRetirado == false )? "No" : "Si";
                        var datestring =  ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                        table.row.add([
                            getCode,
                            NameComplete,
                            carta,
                            version,
                            datestring,
                            id,
                            retirado,
                            valor       = '<a class="btn btn-outline-warning btn-sm" target="_blank" href='+ partsUrl + '><i class="fa fa-edit"></i></a>',
                            valor       = '<a class="btn btn-outline-info btn-sm btnViewParte"  data-id="' + data[i].idparticipantecarta + '"><i class="fa fa-history"></i></a>',
                            reporte     = '<a class="btn btn-outline-success btn-sm btnReporte" data-id="' + data[i].idparticipantecarta + '"><i class="fa fa-book"></i></a>',
                            btntRetirar = '<a class="btn btn-outline-danger btn-sm btnRetiro" data-id="' + data[i].idparticipantecarta + '"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a>'
                        ]).draw( false );
                    }
                }
            }).fail(function() {
                swal("Error!", "Código no existe!","error");
                $("#parametro").focus();
            });
        }
            //
        $('#tableCartParticipnt tbody').on('click', '.btnViewParte', function () {
            var id = $(this).data('id');
            VerPartes(id);
        });
        function VerPartes(id){
            $.getJSON(urls.searchPartesUrl, { idparticipantecarta : id,   ajax : 'true'  }, function(data){
                table2.clear().draw( false );
                var len = data.length;
                        for(var i=0; i < len; i++){
                            var id =  data[i].iddetalle;
                            var parte = data[i].parte.parte;
                            var acepta ="";
                            if (data[i].acepta){
                                acepta = '<input type="checkbox" id="'+ data[i].iddetalle +'" checked disabled/>';
                            }else{
                                acepta = '<input type="checkbox" id="'+ data[i].iddetalle +'" disabled/>';
                            }
                            table2.row.add([
                                id,
                                parte,
                                acepta
                            ]).draw( false );
                        }
                $("#exampleModal").modal("show");
            }).fail(function() {
                swal("Error!", "Falló al obtener la información!", "error");
            });
        }
        }//fin function anonima

    };// fin return
}();



$("#btnRetirar").on("click", function(){
    var id = $("#IdParticipante").val();
    Modificar(id);
})
function Modificar(id) {
    $.getJSON(parametros.UpdateRetiroUrl,{codigo : id, ajax : 'true'}, function(){
    }).fail(function() {
        swal("Error!","Falló al obtener la información!");
    });
}