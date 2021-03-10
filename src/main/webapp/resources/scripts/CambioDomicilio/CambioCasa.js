/**
 * Created by 14ac129la on 06/03/2021.
 */

var CambioCasa = function(){

    return{
        init:function(parametro){

            var table = $('#tblExample').DataTable({
                responsive: true,
                searching: true,
                paging: true,
                "oLanguage": {
                    "sUrl": parametro.dataTablesLang
                }
            });
            $("#telefono").mask("99999999");
            $(".num").keydown(function(event) {
                if(event.shiftKey){
                    event.preventDefault();
                }
                if (event.keyCode == 46 || event.keyCode == 8) { }
                else {
                    if (event.keyCode < 95) {
                        if (event.keyCode < 48 || event.keyCode > 57) {
                            event.preventDefault();
                        }
                    }
                    else {
                        if (event.keyCode < 96 || event.keyCode > 105) {
                            event.preventDefault();
                        }
                    }
                }
            });
            $("#barrio").on("change", function () {
                if (this.value == 18) {
                    $("#bar").show("slow");
                    $("#otroBarrio").attr("required", "true");
                } else {
                    $("#bar").hide("slow");
                    $("#otroBarrio").val("").attr("required", "false");
                }
            });

            $("#btnObtChecked").on("click", function(e){
                e.preventDefault();
                var isAllValid = true;
                var list = [];
                $("#tblExample input[type=checkbox]:checked").each(function () {
                    var row = $(this).closest("tr")[0];
                    list.push( parseInt(row.cells[1].innerHTML));
                });
                if (list.length == 0 || $.isEmptyObject(list)) {
                    toastr.error("Debes checkar al menos un código.");
                    isAllValid = false;
                    return;
                }

                if ($('#fecha_reportado').val().trim() == '') {
                    toastr.error("Debes seleccionar la fecha.");
                    $('#fecha_reportado').focus()
                    isAllValid = false;
                    return;
                }
                if ($('#manzana').val().trim() == '' || isNaN($('#manzana').val())) {
                    toastr.error("Ingresa número de manzana.");
                    $('#manzana').focus();
                    isAllValid = false;
                    return;
                }

                if ($('#dir').val().trim() == '') {
                    toastr.error('Ingresa la dirección.');
                    $('#dir').focus();
                    isAllValid = false;
                    return;
                }
                //
                if (isAllValid) {
                    debugger;
                    var data={
                        list : list,
                        casaP: $("#casaP").val(),
                        casaFam: $("#casaFam").val(),
                        barrio: $("#barrio").val().trim(),
                        manzana: $("#manzana").val().trim(),
                        dir: $("#dir").val().trim(),
                        telefono: $("#telefono").val().trim(),
                        razonnogeoref: $("#razonnogeoref").val().trim(),
                        recurso1: $("#recurso1").val().trim(),
                        fecha_reportado: $("#fecha_reportado").val().trim(),
                        observacion: $("#observacion").val().trim(),
                        otroBarrio : $("#otroBarrio").val()
                    };

                    console.log(data);
                    $.ajax({
                        type: 'POST',
                        url: parametro.saveUrl,
                        dataType: "JSON",
                        data: JSON.stringify(data),
                        contentType:'application/json;charset=utf-8',
                        success: function (data) {
                            console.log(data.list);
                            toastr.success(parametro.successmessage);
                            list = [];
                            setTimeout(function(){
                                window.location.href = parametro.FormHouseUrl;
                            },1000);
                        },
                        error: function (error) {
                            toastr.error("Falló Servidor!",{timeOut: 5000});
                            console.log(error);
                        }
                    });
                }
            });

            var form1 = $('#select-Casa-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    codCasa: {
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
                    table.clear().draw( false );
                    search();
                }
            });

            function search(){
                $.getJSON( parametro.searchByHouseUrl , form1.serialize() , function( data )   {
                   //console.log(data);
                    var len = data.length;
                    if(len == 0){
                        toastr.warning("No se encontro informacion.");
                    }else{
                        $.each(data, function(index, element){
                            var valor ='<input type="checkbox" class="selected" value="'+ element[0].codigo + '"/>';
                            var codigo = element[0].codigo;
                            var NameComplete = element[0].nombre1 +" "+ element[0].nombre2 +" "+ element[0].apellido1 +" "+element[0].apellido2;
                            var casaPed = element[0].casa.codigo;
                            var dir = element[0].casa.direccion;
                            var casaFam;
                            var stado = (element[1].estPart == 1) ? '<h4><span class="badge badge-success"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></span> </h4>': '<h4> <span class="badge badge-danger"> <i class="fa fa-hand-o-down"></i></span> </h4>';

                            if(element[1].casaFam == 0 || element[1].casaFam == "" || element[1].casaFam == null){
                                casaFam = "-"
                            }else{
                             casaFam = element[i].casaFam;

                            }
                            table.row.add([
                                valor,
                                codigo,
                                NameComplete,
                                casaPed,
                                dir,
                                casaFam,
                                stado
                            ]).draw(false);
                        });
                    }
                    }).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error( "error:" + errorThrown);
                    });
            }

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
