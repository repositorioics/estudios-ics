/**
 * Created by ICS on 14/01/2021.
 */
var GuardarSero = function(){
    return{
        init: function(param){

            $("#tblSero").DataTable({
                "columnDefs": [{
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }],"oLanguage": {
                    "sUrl": param.dataTablesLang
                }
            });

            var form1 = $('#FormSero');
            form1.validate({
                focusInvalid: false,
                errorElement: 'span',
                rules: {
                    codigoparticipante: {required: true,
                        number: true
                    },
                    fechaSero: {
                        required: true
                    },
                    fechaReg: {
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
                    SaveSero(param);
                }
            });


            function SaveSero(d) {
                $.post(d.saveSeroUrl, form1.serialize(), function (data) {
                    if(data.msjError != null){
                        swal("Error!",data.msjError,"error");
                    }
                    if(data.msj !=null){
                       // swal("¡Buen trabajo!", data.msj, "success");
                        toastr.success(data.msj);
                    }else{
                        //swal("¡Buen trabajo!", "Guardado con éxito", "success");
                        toastr.success(param.successmessage);
                    }
                    setTimeout(function(){
                        window.location.href = param.serologiaUrl;
                      },1000);
                }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                    toastr.error("Falló Servidor!",{timeOut: 5000});
                });
            }

            $("#volumen").keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });

            $("#tblSero tbody").on("click",'.swalDelSero', function(){
                var id = $(this).data('id');
                var currentRow = $(this).closest("tr");
                var col0 = currentRow.find("td:eq(0)").text();
                var col1 = currentRow.find("td:eq(1)").text();
                var col2 = currentRow.find("td:eq(2)").text();
                console.warn("columns: "+ col0 + "-" + col1 + "-" +col2);
                eliminar(id,col1,col2);
            })

            $("#codigoparticipante").keyup(function (){
                this.value = (this.value + '').replace(/[^0-9]/g, '');
            });

            function eliminar(id,col1,col2) {

                swal({
                        title: "Eliminar? ",
                        text: "Registro: " + col1 + "Fecha: " + col2,
                        type: "warning",

                        dangerMode: true,
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Si, bórralo!",
                        cancelButtonText: "No, borres plx!",
                        closeOnConfirm: false,
                        closeOnCancel: false
                    },
                    function(isConfirm) {
                        if (isConfirm) {
                            $.post(param.deleteseroUrl,{id : id, ajax : 'true'}, function(data){
                                swal("Eliminado!", "con éxito!.", "success");
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            }).fail(function() {
                                setTimeout(function () {
                                    swal("Error!","Servidor no respode!","error");
                                }, 1500);
                            });

                        } else {
                            swal("Cancelado", "registro está seguro :)", "error");
                        }
                    });
            }

            $( "#username" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(param.getUserNameUrl, {username: $('#username').val().trim(), ajax: 'true'},function(data){
                        response($.map(data, function (value, key) {
                            return {
                                label: value
                            };
                        }));
                    });
                },minLength: 3,
                scroll: true,
                highlight: true
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
    }
}();