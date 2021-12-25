/**
 * Created by ICS on 18/10/2020.
 */
var EnviarSerologiasForm = function(){

    return {
        init: function(urls){

            var table = $("#Lista_Muestra").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false,
                        "searchable": false
                    },{
                        "targets": [9],
                        "visible": false,
                        "searchable": false
                    }, {
                        targets: [1,2,4,5,7,8],
                        className: 'text-center'
                    }
                ]
            });
            CargarDatos();
            function CargarDatos(){
                var myUrl = urls.MxNoEnviadasUrl
                $.getJSON(myUrl, function(data){
                    var len = data.length;
                    if(data==0){
                        toastr.warning("No se encontraron registro","ADVERTENCIA!",{timeOut:6000});
                    }else{
                        for ( var i = 0; i < len; i++) {
                            var d = new Date(data[i].fecha);
                            var partsUrl = "${editUrl}" + '/'+data[i].idSerologia + '/';
                            var codParticipante;

                            if( data[i].participante == null ||  data[i].participante ==''){
                                codParticipante = data[i].codigonuevoparticipante;
                            }else{
                                codParticipante = data[i].participante.codigo
                            }
                            var datestring = ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                            var fregistro = new Date(data[i].recordDate);
                            var datestring2 = ("0" + fregistro.getDate()).slice(-2) + "/" + ("0"+(fregistro.getMonth()+1)).slice(-2) + "/" + fregistro.getFullYear();
                            var botonUpdate;
                            if(data[i].estudio == '-'){
                                botonUpdate =  '<button type="button" id="btnDisabled"  class="btn btn-warning btn-sm" disabled=disabled> <i class="fa fa-edit" aria-hidden="true"></i>  </button>';
                            }   else{
                                botonUpdate ='<a class="btn btn-warning" href='+ partsUrl + '><i class="fa fa-edit"></i></a>';
                            }
                            var botonEnvio  = '<button type="button" id="btnEnviar"  class="btn btn-primary btn-sm btnEnviar" data-id='+ data[i].idSerologia + '> <i class="fa fa-send" aria-hidden="true"></i>  </button>';
                            table.row.add([
                                data[i].idSerologia,
                                datestring,
                                (data[i].cerrado==0)?"<span class='badge badge-danger'> <i class='fa fa-arrow-circle-o-down' aria-hidden='false'></i></span>":"<span class='badge badge-success'><i class='fa fa-arrow-circle-o-up'></i></span>",
                                data[i].estudio,
                                codParticipante,
                                data[i].volumen,
                                data[i].observacion,
                                data[i].casaCHF,
                                botonUpdate,
                                botonEnvio
                            ]).draw(false);
                        }
                    }
                });
            }//fin funccion


            var form1 = $('#envio-allserologia-form');
            var $validator = form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    horaEnvio:{
                        required:true
                    },
                    numenvio: {
                        required: true
                    },
                    desde:{
                        required: true
                    },
                    hasta:{
                        required: true
                    },
                    desde: {required: function () {
                        return $('#desde').val().length > 0;
                    }},
                    hasta: {required: function () {
                        return $('#hasta').val().length > 0;
                    }}
                },errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    }else if ( element.prop( 'type' ) === 'text' ){
                        error.insertAfter(element.parent('.input-group'));
                    } else {
                        error.insertAfter( element ); //cuando no es input-group
                    }
                },highlight: function ( element, errorClass, validClass ) {
                    $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                    $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
                },unhighlight: function (element, errorClass, validClass) {
                    $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                    $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
                },submitHandler: function (form) {
                    var $validarForm = form1.valid();
                    if (!$validarForm) {
                        $validator.focusInvalid();
                        return false;
                    } else {
                        $.post(urls.sendAllSerologiasUrl, form1.serialize(), function (data) {
                            debugger;
                            console.log(data);
                            if (data.mensaje != null) {
                            toastr.info(data.mensaje, "INFORMACIÓN", {timeOut:6000});
                                window.setTimeout(function () {
                                    table.clear().draw( false );
                                    CargarDatos();
                                    location.reload();
                                }, 1300);
                            }
                        }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                           toastr.error("Error 500 Internal Server", 'EROOR!',{timeOut:6000});
                        });
                    }
                }
            });




            $("#Lista_Muestra tbody").on("click", ".btnEnviar",function(){
                var id = $(this).data('id');
                SendId(id);
            });
            function SendId(id){
                var isValidData = true;
                if ($("#fechaEnvio").val().trim() == null || $("#fechaEnvio").val().trim() == "") {
                    toastr.error("Fecha de envío es requerido", 'ERROR!',{timeOut:6000});
                    isValidData = false;
                    $("#fechaEnvio").focus();
                    return false;
                }
                if ($("#horaEnvio").val().trim() == null || $("#horaEnvio").val().trim() == "" || $("#horaEnvio").val().trim() === 'NaN') {
                    toastr.error("Hora de envío es requerido", 'ERROR',{timeOut:6000});
                    isValidData = false;
                    $("#horaEnvio").focus();
                    return false;
                }

                if ($("#nEnvios").val().trim() == null || $("#nEnvios").val().trim() == "" || $("#nEnvios").val().trim() === 'NaN') {
                    toastr.error("Número de envío es requerido", 'ERROR',{timeOut:6000});
                    isValidData = false;
                    $("#nEnvios").focus();
                    return false;
                }

                if (isValidData) {
                    var muestraForEnvio ={
                        fechaenvio : $('#fechaEnvio').val(),
                        hora : $('#horaEnvio').val(),
                        nenvios : parseInt($('#nEnvios').val()),
                        idserologia  :parseInt(id)
                    };
                    if (muestraForEnvio != null) {
                        var direccion = misUrl.envioUrl; // "/estudios-ics/Serologia/enviarMuestra";
                        $.ajax({
                            type: "POST",
                            url: direccion,
                            data: JSON.stringify(muestraForEnvio),
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function(response) {
                                if (response != null) {
                                    toastr.success(misUrl.successMessage, {timeOut:6000});
                                    window.setTimeout(function () {
                                        table.clear().draw( false );
                                        CargarDatos();
                                        location.reload();
                                    }, 1300);

                                } else {
                                    toastr.error("Error al Guardar",{timeOut:6000});
                                }
                            },error: function(response) {
                                toastr.error("Error 500 Internal Server",{timeOut:6000});
                            }
                        });
                    }
                }
            };




        }
    }
}();
