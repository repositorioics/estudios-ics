/**
 * Created by ICS on 07/01/2021.
 */

var GuardarBhc = function(){
    return{
        init: function(params){
            var form1 = $('#FormBHC');
            form1.validate({
                focusInvalid: false,
                errorElement: 'span',
                rules: {
                    codigo: {required: true,
                        number: true
                    },
                    fechaBhc: {
                        required: true
                    },
                    volumen: {
                        required: true,
                        number: true
                    }
                },
                errorElement: 'em',
                errorPlacement: function (error, element) {
                    error.addClass('form-control-feedback');
                    if (element.prop('type') === 'checkbox') {
                        error.insertAfter(element.parent('label'));
                    } else {
                        error.insertAfter(element);
                    }
                },
                highlight: function (element, errorClass, validClass) {
                    $(element).addClass('form-control-danger').removeClass('form-control-success');
                    $(element).parents('.form-group').addClass('has-danger').removeClass('has-success');
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).addClass('form-control-success').removeClass('form-control-danger');
                    $(element).parents('.form-group').addClass('has-success').removeClass('has-danger');
                },
                submitHandler: function (form) {
                    SaveBHC(params);
                }
            });

            function SaveBHC(d) {
               $.post(d.saveBHCUrl, form1.serialize(), function (data) {
                   if(data.msjError != null){
                       swal("Error!",data.msjError,"error");
                   }
                   if(data.msj !=null){
                       swal("¡Buen trabajo!", data.msj, "success");
                   }else{
                       swal("¡Buen trabajo!", "Guardado con éxito", "success");
                   }
                   setTimeout(function(){
                       window.location.href = params.bhcUrl;
                   },1000);
                }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                   swal("Error!","Servidor no respode!","error");
                });
            }
            $("#codigo").keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });
            $("#volumen").keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });

            $("#tblbhc").on("click",'#swalDelete', function(){
                var currentRow = $(this).closest("tr");
                var col0 = currentRow.find("td:eq(0)").text();
                var col1 = currentRow.find("td:eq(1)").text();
                eliminar(col0,col1);
            })
            function eliminar(col0,col1) {
                swal({
                    title: "Eliminar? ",
                    text: "Registro: " + col0 + " con Fecha: " + col1,
                    icon: "warning",
                    buttons: [
                        'No, cancélalo!',
                        'Si, Eliminar!'
                    ],
                    dangerMode: true
                }).then(function(isConfirm) {
                    if (isConfirm) {
                        swal({
                            title: 'Eliminado!',
                            text: 'Registro eliminado éxitosamente!',
                            icon: 'success'
                        }).then(function() {
                            $.post(params.deletebhcUrl,{idbhc : col0, datebhc: col1, ajax : 'true'}, function(data){
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            }).fail(function() {
                                setTimeout(function () {
                                    swal("Error!","Servidor no respode!","error");
                                }, 2000);
                            });
                        });
                    } else {
                        swal("Cancelado!", "Registro seguro. :)", "error");
                    }
                });

                document.addEventListener('keypress', function(evt) {
                    // Si el evento NO es una tecla Enter
                    if (evt.key !== 'Enter') {
                        return;
                    }
                    let element = evt.target;
                    // Si el evento NO fue lanzado por un elemento con class "focusNext"
                    if (!element.classList.contains('focusNext')) {
                        return;
                    }
                    // AQUI logica para encontrar el siguiente
                    let tabIndex = element.tabIndex + 1;
                    var next = document.querySelector('[tabindex="'+tabIndex+'"]');
                    // Si encontramos un elemento
                    if (next) {
                        next.focus();
                        event.preventDefault();
                    }
                });
            }
        }//fin function init
    }//fin return
}();


var BuscarParticipantebhc = function(){

    return{
        init: function(params){
            var table  = $('#tblbhc').DataTable({
                searching: true,
                paging: true,
                "oLanguage": {
                    "sUrl": params.dataTablesLang
                }
            });
            var form2 = $('#search-participant-form');
            form2.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    participantCode: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        required: true,
                        maxlength: 5
                    }
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
                    //table.clear().draw( false );
                    //search();
                }
            });
                //var partsUrl = params + '/'+data[i].+ '/';
            function search(){
                $.getJSON( params.searchPartUrl , { parametro : $('#parametro').val(),   ajax : 'true'  } , function( data )   {
                        var len = data.length;
                        console.log(data);
                        if(len==0){
                            toastr.warning("Código no encontrado");
                            $("#parametro").focus();
                        }
                        else {
                            if (data.mensaje != undefined) {
                                $.notify(data.mensaje,"error");
                                toastr.error(data.mensaje,"Error",{timeOut: 5000});
                            }else{
                                for ( var i = 0; i < len; i++) {
                                    var valor ="";
                                    var fecha_registro = new Date(data[i].fecreg);
                                    var registrado =  ("0" + fecha_registro.getDate()).slice(-2) + "/" + ("0"+(fecha_registro.getMonth()+1)).slice(-2) + "/" + fecha_registro.getFullYear();
                                    var fecha_bhc = new Date(data[i].recBhcId.fechaRecBHC);
                                    var fechaRecBHC =  ("0" + fecha_bhc.getDate()).slice(-2) + "-" + ("0"+(fecha_bhc.getMonth()+1)).slice(-2) + "-" + fecha_bhc.getFullYear();

                                    var editUrl = params.editbhcUrl + '/'+data[i].recBhcId.codigo + '/'+fechaRecBHC ;
                                    var status;
                                    if(data[i].estado == "1"){
                                        status= '<h3> <span class="badge badge-success"> <i class="fa fa-thumbs-up" aria-hidden="true"></i></span> </h3>';
                                    }else{
                                        status= '<h3> <span class="badge badge-danger"> <i class="fa fa-thumbs-down" aria-hidden="true"></i></span> </h3>';
                                    }
                                    var pax;
                                    if(data[i].paxgene == true){
                                        pax = '<h3> <span class="badge badge-success"> <i class="fa fa-check-circle" aria-hidden="true"></i></span> </h3>';

                                    }else{
                                        pax = '<h3> <span class="badge badge-danger"> <i class="fa fa-times-circle" aria-hidden="true"></i> </span> </h3>';
                                    }
                                    table.row.add([
                                        data[i].recBhcId.codigo,
                                        fechaRecBHC,
                                        status,
                                        registrado,
                                        data[i].lugar,
                                        data[i].observacion,
                                        pax,
                                        data[i].username,
                                        data[i].volumen,
                                        valor = '<a class="btn btn-outline-primary btn-sm" target="_blank" href='+ editUrl + '><i class="fa fa-edit"></i></a>'
                                    ]).draw( false );
                                }
                            }
                        }
                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error( "error:" + errorThrown);
                    });
            }

        }
    }
}();
