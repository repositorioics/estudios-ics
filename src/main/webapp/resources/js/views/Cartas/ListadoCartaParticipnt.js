/**
 * Created by ICS on 18/02/2020.
 *
 * ,columnDefs: [
 {
      targets: 0,
      className: 'text-center'
  },{
                    targets: 3,
                    className: 'text-center'
                },{
                    targets: 5,
                    className: 'text-center'
                },{
                    targets: 6,
                    className: 'text-center'
                },{
                    targets: 7,
                    className: 'text-center'
                },{
                    targets: 9,
                    className: 'text-center'
                }
 ]
 */
var urls={};
var SearchCartaParticipant = function () {
    return {
    init: function(parametros){
        urls = parametros;
        var table  = $('#tableCartParticipnt').DataTable({
            searching: false,
            "autoWidth": true,
            "info" : false,
            paging: true,
            "oLanguage": {
                "sUrl": parametros.dataTablesLang
            },columnDefs: [
                {
                    targets: 0,
                    className: 'text-center',
                    visible: false
                },{
                    targets: 1,
                    className: 'text-center'
                },{
                    targets: 4,
                    className: 'text-center'
                },{
                    targets: 5,
                    className: 'text-center'
                },{
                    targets: 6,
                    className: 'text-center'
                },{
                    targets: 7,
                    className: 'text-center'
                }
            ]
        });


        $("#select-participante-form").validate( {
            rules: {
                parametro: 'required',
                number: true
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
                Buscarcp(parametros);
            }

        });// fin validate

        /*debugger;
        if(parametros.isAuthorizeAny == 'true'){
            console.log('hi ROLE_WEB');
        }

        if(parametros.haRoleAdmin == true){
            console.log('hi ADMINISTRADOR');
        }*/

        function Buscarcp(dir){
            $.getJSON(dir.GetCartasParticipanteUrl,{parametro : $('#parametro').val(), ajax : 'true' },function(data){
                console.log(data);
                var len = data.length;
                if(len == 0){
                    toastr.warning("No se encontró información de: "+'<strong>' + $('#parametro').val() +'</strong>',{timeOut: 5000});
                    $("#parametro").val("");
                    $("#parametro").focus();
                }else{
                    for ( var i = 0; i < len; i++) {
                        var valor ="";
                        var reporte="";
                        var id = data[i].idParticipanteCarta;
                        var partsUrl = parametros.extensionUrl +'/'+data[i].idParticipanteCarta;
                        var editarUrl = parametros.EditCartaUrl+'/'+data[i].idParticipanteCarta;
                        var getCode =       data[i].codigoParticipante;
                        var NameComplete = (data[i].anulada == true)  ? '<span  style="text-decoration:line-through;" class="text-danger">' + data[i].nombreCompleto + '</span>' : data[i].nombreCompleto;
                        var carta =        (data[i].anulada == true)  ? '<span  style="text-decoration:line-through;" class="text-danger">' + data[i].nmobreEstudio  + '</span>' : data[i].nmobreEstudio;
                        var version =      (data[i].anulada == true)  ? '<span  style="text-decoration:line-through;" class="text-danger">' + data[i].nombreVersion  + '</span>' : data[i].nombreVersion;
                        var datestring =   (data[i].anulada == true)  ? '<span  style="text-decoration:line-through;" class="text-danger">' + data[i].fechaCarta     + '</span>' : data[i].fechaCarta ;
                        var anulada =      (data[i].anulada == false) ? '<span class="badge badge-success" style="width:30px; height:20px;">No</span>' : '<span style="width:30px; height:20px;" class="badge badge-danger">Si</span>';
                        var extension =     data[i].tieneExtesion == true ? '<a class="dropdown-item2 btnExtension" href="'+ partsUrl +'"><i class="fa fa-plus-square" aria-hidden="true"></i> Extensión <span class="badge badge-primary" style="border-radius: 5px"> '+ data[i].cantidadExtension +' </span> </a>':'';
                        var pqAnulada =     data[i].pqAnulada; //(data[i].estado == "1" ) ? "<span class='badge badge-success'>No</span>" : "<span class='badge badge-danger'>Si</span>";
                        var permisoEditParte = (parametros.isAuthorizeAny == 'true') ? '<a class="dropdown-item2 btnViewParte" style="cursor: pointer"  data-id="' + data[i].idParticipanteCarta + '"><i class="fa fa-refresh text-warning" aria-hidden="true"></i> Modificar Partes</a> <div class="dropdown-divider"></div>': '<a hidden="hidden" class="dropdown-item2"><i class="fa fa-refresh text-warning" aria-hidden="true" ></i> Modificar Partes</a>';
                        var permisoDeleteAll = (parametros.isAuthorizeAny == 'true') ? '<a class="dropdown-item2 btnDelete" style="cursor: pointer" data-id="' + data[i].idParticipanteCarta + '"><i class="fa fa-trash text-danger" aria-hidden="true"></i> Anular</a> <div class="dropdown-divider"></div>' : '<a  class="dropdown-item2 btnDelete" hidden="hidden" ><i class="fa fa-trash text-danger" aria-hidden="true"></i> Anular</a>';
                        var divider = '<div class="dropdown-divider"></div>';
                        var dropdown  = (data[i].anulada == false) ? '<div class="btn-group">'+
                            '<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Acción</button>'+
                            '<div class="dropdown-menu dropdown-menu-right">'+
                            '<a class="dropdown-item2 btnEditar" href='+ editarUrl +' data-id="' + data[i].idParticipanteCarta + '"><i class="fa fa-pencil text-warning" aria-hidden="true"></i>  Editar Carta</a>' +
                            '<div class="dropdown-divider"></div>'+   permisoEditParte +
                            '<a class="dropdown-item2 btnReporte" data-id="' + data[i].idParticipanteCarta + '"><i class="fa fa-book text-info" aria-hidden="true"></i>  Reporte</a>' +
                            divider  +  permisoDeleteAll + extension + '</div> ' + '</div>' :
                            '<div class="btn-group">'+
                            '<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Acción</button>'+
                            '<div class="dropdown-menu dropdown-menu-right">'+
                            '<a class="dropdown-item2 btnReporte" data-id="' + data[i].idParticipanteCarta + '"><i class="fa fa-book" aria-hidden="true"></i>  Reporte</a>' +
                            '</div> ' + '</div>';
                        table.row.add([
                            id,
                            getCode,
                            NameComplete,
                            carta,
                            version,
                            datestring,
                            anulada,
                            pqAnulada,
                            dropdown
                        ]).draw( false );
                    }
                }
            }).fail(function() {
                toastr.error( parametros.error,{timeOut: 2000});
                $("#parametro").focus();
            });
        }
        /*valor     = '<a class="btn btn-outline-info btn-sm btnViewParte"  data-id="' + data[i].idParticipanteCarta + '"><i class="fa fa-history"></i></a>',
         reporte   = '<a class="btn btn-outline-success btn-sm btnReporte" data-id="' + data[i].idParticipanteCarta + '"><i class="fa fa-book"></i></a>',
        $('#tableCartParticipnt tbody').on('click', '.btnEditar', function(){
            var id = $(this).data('id');
        });*/
        $('#tableCartParticipnt tbody').on('click', '.btnViewParte', function () {
            var id = $(this).data('id');
            VerPartes(id);
        });
        function VerPartes(id){
            $.getJSON(urls.searchPartesUrl, { idparticipantecarta : id,   ajax : 'true'  }, function(data){
                //console.log(data);
                table2.clear().draw( false );
                var len = data.length;
                        for(var i=0; i < len; i++){
                            var iddetalle =  data[i].iddetalle;
                            var idparticipantecarta = data[i].participantecarta.idparticipantecarta;
                            var parte = data[i].parte.parte;
                            var idParte = data[i].parte.idparte;
                            var acepta ="";
                            if (data[i].acepta){
                                acepta = '<input type="checkbox" class="filter-ck" id="'+ data[i].iddetalle +'" checked />';
                            }else{
                                acepta = '<input type="checkbox" class="filter-ck" id="'+ data[i].iddetalle +'" />';
                            }
                            table2.row.add([
                                iddetalle,
                                idparticipantecarta,
                                parte,
                                idParte,
                                acepta
                            ]).draw( false );
                        }
                $("#exampleModal").modal("show");
            }).fail(function() {
                swal("Error!", "Falló al obtener la información!", "error");
            });
        }

        $("#tableCartParticipnt tbody").on("click", '.btnExtension', function(){
            debugger;
            var  id = $(this).data('id');
            var currentRow = $(this).closest("tr");
            var col0 = currentRow.find("td:eq(0)").html();
            $("#idParticipanteCartaModal").val(col0);
            $.getJSON(parametros.extensionUrl,{idParticipanteCarta: col0, ajax: 'true'}, function(data){


            });
            verExtensiones(id);
            //recorrer();
            //$('#exampleModalxl').modal('show')

        });

      function recorrer(){
          $('#tableCartParticipnt tr').each(function () {
              var currentRow = $(this).closest("tr");
              var col1 = currentRow.find("td:eq(0)").text();
              console.log(col1);
          });
      }


        function verExtensiones(id){
            var $ele = $("#extensiones");
            $ele.val([]).trigger('change');
            $.getJSON(urls.VerExtensionUrl,{idversion: id, ajax: 'true'}, function(data){
                console.log(data);
                $ele.empty();
                if(data.length > 0){
                    bandera=true;
                    $.each(data, function (i, val) {
                        $ele.append($('<option/>').val(val.id).text(val.extension));
                    })
                }else{
                    $ele.empty();
                }
            });
        }


        $("#btnRetirar").on("click", function(){//Metodo Anular
            debugger;
            var id = $("#IdParticipanteCartaModalAnular").val();
            if($("#observacion").val() == null || $("#observacion").val() == ""){
                 $('#errror_alert').removeClass('d-none').addClass('show');
                return false;
            }else{
                $('#errror_alert').addClass('d-none').removeClass('show');
                Modificar(id);
            }
        });
        function Modificar(id) {
            var obs  = $("#observacion").val();
            $.getJSON(urls.UpdateRetiroUrl,{IdParticipanteCartaModalAnular : id, observacion: obs,  ajax : 'true'}, function(data){
                //console.log(data);
                limpiarModalInput();
                $("#exampleModal2").modal('hide');
                window.setTimeout(function(){
                    window.location.href = urls.Lista2AllUrl;
                }, 1500);
            }).fail(function() {
                limpiarModalInput();
                $("#exampleModal2").modal('hide');
                toastr.error("Falló al enviar la información!","ERROR",{timeOut: 5000});
            });
        }

        function limpiarModalInput(){
            $("#IdParticipante").val('');
            $("#IdParticipanteCartaModalAnular").val('');
            $("#observacion").val('');
        }
        var table2 = $("#tblDetalleParte").DataTable({
            searching  : false,
            paging     : false,
            "ordering"  : false,
            "info"      : false,
            "oLanguage" : {
                "sUrl"  : parametros.dataTablesLang
            },
            columnDefs:[
                {
                    "targets":[0],
                    "visible": false,
                    "searchable":false
                }, {
                    "targets":[1],
                    "visible": false,
                    "searchable":false
                }, {
                    "targets":[3],
                    "visible": false,
                    "searchable":false
                }
            ]
        });

        $("#btnActualizar").on("click", function(e){
            var arrData=[];
            $("#tblDetalleParte tbody tr").each(function(){
                var currentRow = $(this);
                var col0_value = table2.row( this ).data()[0];
                var col1_value = table2.row(this).data()[1];
                var col3_value = table2.row(this).data()[3];
                var col4_value = currentRow.find(":checkbox").prop("checked");
                var obj={};
                obj.iddetalle = parseInt(col0_value);
                obj.idparticipantecarta = parseInt(col1_value);
                obj.idparte = parseInt( col3_value );
                obj.acepta = col4_value;
                arrData.push(obj);
            });
            debugger;
            if ($('input[type=checkbox]:checked').length === 0) {
                e.preventDefault();
                myFunctionAlert();
                return;
            }
            ActualizarPartesPrincipales(arrData);
        });
        function myFunctionAlert() {
            $('#mialerta').fadeIn(1000);
            setTimeout(function() {
            $('#mialerta').fadeOut(1000);
            }, 5000);
        }
        function ActualizarPartesPrincipales(arrData){
            $.ajax({
                url: parametros.updateDetalleParteUrl,
                type: "post",
                data: JSON.stringify(arrData),
                dataType: "JSON",
                contentType: "application/json; charset=utf-8",
                success: function(response, textStatus, jQxhr){
                    if(response.mensaje != null){
                        $("#exampleModal").modal("hide");
                        toastr.success(parametros.successmessage);
                    }
                },error: function(jqXhr, textStatus, errorThrown ){
                    toastr.error( "Han ocurrido errores en el proceso!",{timeOut: 5000});
                }
            });
        }


        $("#tableCartParticipnt tbody").on("click", ".btnDelete", function(e){
            var id = $(this).data('id');
            var row = $(this).parents('tr');
            var obj = {};
            var correntRow = $(this).closest('tr').find('td');
            var col0_value =  $(correntRow).eq(0).text();
            var col2_value =  $(correntRow).eq(2).text();
            var col3_value =  $(correntRow).eq(3).text();
            var col4_value =  $(correntRow).eq(4).text();

            var codigoidParticipanteCarta = table.row(correntRow).data()[0];
            obj.idParticipantecarta = parseInt(id);
            obj.idParticipante = parseInt(col0_value);
            obj.estudio = col2_value;
            obj.version = col3_value;
            obj.fechaCarta = col4_value;

            $("#IdParticipante").val(col0_value);
            $("#IdParticipanteCartaModalAnular").val(codigoidParticipanteCarta);
            $("#codpart").text(col0_value);
            $("#nombre").text(col2_value);
            $("#carta").text(col3_value);
            $("#version").text(col4_value);
            $("#exampleModal2").modal('show');

            /*swal({
                    title: "Eliminar? ",
                    text: "Código: " + obj.idParticipante + " con Fecha: "+ obj.fechaCarta,
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Si, Bórralo!",
                    cancelButtonText: "No, Borres plx!",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        row.remove();
                        $.post(parametros.DeleteUrl, {idParticpanteCarta: obj.idParticipantecarta, ajax: 'true'}, function (data) {
                            swal("Eliminado!", "con éxito!", "success");
                        }).fail(function () {
                            setTimeout(function () {
                                swal("Error!", "Servidor no respode!", "error");
                            }, 2000);
                        });
                    } else {
                        swal("Cancelado!", "Registro está seguro! :)", "error");
                    }
                });*/
        });

        }//fin function anonima

    };// fin return
}();

