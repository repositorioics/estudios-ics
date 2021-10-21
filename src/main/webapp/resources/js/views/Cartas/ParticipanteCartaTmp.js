/**
 * Created by ICS on 10/04/2020.
 */
var saveCartaTMP = function(){
    return{
        init: function(parametros){
            $('#form_carta_tmp').submit(function(e){
                e.preventDefault();
                var isOK = ValidateForm();
                if (isOK) {
                    guardarCartaTmp(parametros);
                }
            });
            function ValidateForm() {
                var isAllValid = true;
                $('.form-group').removeClass('is-invalid');
                if( isNaN( $("#idparticipante").val() )){
                    isAllValid = false;
                    $("#idparticipante").addClass('is-invalid');
                }else{
                    $("#idparticipante").removeClass('is-invalid');
                }
                if($("#idcarta").val().trim() == "" || $("#idcarta").val().trim() == null){
                    isAllValid = false;
                    $('#idcarta').addClass('is-invalid');
                }
                else{
                    $('#idcarta').removeClass('is-invalid');
                }

                if($("#version").val() == null || $("#version").val() == ""){
                    isAllValid = false;
                    $('#version').addClass('is-invalid');
                }
                else{
                    $('#version').removeClass('is-invalid');
                }

                var num = $("#parte").select2().val();
                if($.isEmptyObject(num)){
                    $('#parte').addClass('is-invalid');
                    isAllValid = false;
                }else{
                    $('#parte').removeClass('is-invalid');
                }

                if ($('#relfam').val() == null || $('#relfam').val() == "") {
                    $('#relfam').addClass('is-invalid');
                    isAllValid = false;
                }
                else {
                    $('#relfam').removeClass('is-invalid');
                }

                if($("#proyecto").val()=="" || $("#proyecto").val()== null){
                    $('#proyecto').addClass('is-invalid');
                    isAllValid = false;
                }else{
                    $('#proyecto').removeClass('is-invalid');

                }
                if($("#person").val()=="" || $("#person").val()== null){
                    isAllValid = false;
                    $('#person').addClass('is-invalid');
                }
                else{
                    $('#person').removeClass('is-invalid');
                }

                if($("#tipoasentimiento").val() == "" || $("#tipoasentimiento").val() == null){
                    isAllValid = false;
                    $('#tipoasentimiento').addClass('is-invalid');
                }else{
                    $('#tipoasentimiento').removeClass('is-invalid');
                }
                return isAllValid;
            }
            /*
            function confirmaCodigoParticipante(){
                var result = true;
                if($("#idparticipante").val() =="" || $("#idparticipante").val() == null){
                   toastr.error("Ingresa el codigo de participante","ERROR",{ timeOut : 6000});
                    $("#idparticipante").addClass('is-invalid');
                    $("#idparticipante").focus();
                    result = false;
                }else{
                    $("#idparticipante").removeClass('is-invalid');
                    $.getJSON(parametros.buscaCodigoUrl,{ idparticipante : $("#idparticipante").val(), ajax : 'true' }, function(data){
                        if(data.mensaje != ""){
                            toastr.error(data.mensaje,"ERROR!",{ timeOut : 6000 });
                            result = false;
                        }
                    });
                    return result;
                }
            }
            */

            /*form.validate({
                rules: {
                    idparticipante: {
                        required: true,
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        maxlength: 5
                    },
                    name1tutor: {
                        required: true
                    },
                    apellido1tutor: {
                        required: true
                    },
                    fechacarta: {
                        required: true
                    },
                    relfam: {
                        required: true
                    }
                },
                errorPlacement: function ( error, element ) {
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        error.insertAfter( element );
                    }
                    if (element.attr("name") == "signo") {
                        error.insertAfter("#gendererror");
                    } else {
                        error.insertAfter(element);
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
                submitHandler: function () {
                    saveexample(parametros);
                }
            });
                function saveexample(){
                    debugger;
                    $.post(parametros.saveCartaExampleUrl, form.serialize(), function (data) {
                        swal("Éxito!", "Información guardada!", "success")
                        window.setTimeout(function () {
                            window.location.href = dir.ListadoUrl;
                        }, 1400);
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        swal("Error", "Problemas al Guardar!", "error");
                    });
                }*/

            jQuery("#idparticipante").on('input', function (evt) {
                jQuery(this).val(jQuery(this).val().replace(/[^0-9]/g, ''));
            });

            var parts = [];
            function guardarCartaTmp(dir){
                debugger; var parts = [];
                var x = document.getElementById('parte');
                 for(var i = 0; i < x.options.length; i++){
                 console.log("options"+[i]+" selected: "+ x.options[i].acepta);
                 var obj = {};
                 obj.idparte = parseInt(x.options[i].value);
                 obj.acepta =  (x.options[i].selected == true) ? true : false;
                 parts.push(obj);
                 }

                    var obj = {
                        codigo              : parseInt($("#id_participante_carta_tmp").val()),
                        idparticipante      : parseInt($("#idparticipante").val().trim()),
                        version             : parseInt($("#version").val().trim()),
                        asentimiento        : ($('input:checkbox[name=asentimiento]').prop('checked') == true) ? '1' : '0',
                        tipoasentimiento    : $("#tipoasentimiento").val().trim(),
                        relfam              : parseInt($("#relfam").val().trim()),
                        nombfirma           : $("#name1tutor").val().trim(),
                        nombre2Firma        : $("#name2tutor").val().trim(),
                        apellido1Firma      : $("#apellido1tutor").val().trim(),
                        apellido2Firma      : $("#apellido2Firma").val().trim(),
                        recurso             : parseInt($("#person").val().trim()),
                        fechacarta          : $("#fechacarta").val(),
                        proyecto            : $("#proyecto").val(),
                        contactoFuturo      : ($('input:checkbox[name=contactoFuturo]').prop('checked') == true) ? '1' : '0',
                        testigopresente     : ($('input:checkbox[name=chkTestigo]').prop('checked') == true) ? '1' : '0',
                        nombre1testigo      : $("#nombre1Testigo").val().trim(),
                        nombre2testigo      : $("#nombre2Testigo").val().trim(),
                        apellido1testigo    : $("#apellido1Testigo").val().trim(),
                        apellido2testigo    : $("#apellido2Testigo").val().trim(),
                        observacion         : $("#observacion").val().trim(),
                        edadyears           : parseInt(0),
                        edadmeses           : parseInt(0),
                        edaddias            : parseInt(0),
                        accion              : $("#accion").val().trim(),
                        parte               : parts,
                        estudios_actuales: $("#estudios").val()
                    };
                    var url = dir.saveUrl;
                    $.ajax({
                        url: url,
                        type: 'POST',
                        contentType: 'application/json;charset=utf-8',
                        dataType: 'json',
                        data: JSON.stringify(obj),
                        success: function (data, status) {
                            if(data.msjCodigo != null){
                                toastr.error(data.msjCodigo,"ERROR", {timeOut: 5000});
                            }else if (data.msj != null){
                                toastr.warning(data.msj, {timeOut: 5000});
                            } else {
                                toastr.success(dir.successmessage);
                                window.setTimeout(function () {
                                    window.location.href = parametros.CartaParticipantTmpUrl;
                                }, 1500);
                            }
                        }, error: function (request, status, error) {
                            console.log("error: ", request.responseText);
                        }
                    });
            };

            var $version = $('#version');
            $("#idcarta").on("change", function(){
                $('#version').val("").trigger('change.select2');
                $('#version').select2().empty();
                $("#parte").val("").trigger('change.select2');
                $("#parte").empty();
                if ($(this).val() == 3) {
                    $('#proyecto').val(3).trigger('change.select2');
                } else if ($(this).val() == 5) {
                    $('#proyecto').val(5).trigger('change.select2');
                } else if ($(this).val() == 4) {
                    $('#proyecto').val(2).trigger('change.select2');
                } else {
                    $('#proyecto').val(4).trigger('change.select2');
                }
                if($(this).val() === ""){
                    $("#version").select2("val", "").change();
                    $("#version").empty();
                    $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                    $("#parte").val("").trigger('change.select2');
                    $("#parte").select2().empty();
                }else{
                    ObtenerVersion(parametros);
                }
            });
            function ObtenerVersion(parametros){
                var idcarta = document.getElementById('idcarta').value;
                $.getJSON(parametros.VersionCartatUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
                    $version.empty();
                    var len = data.objV.length;
                    if(len == 0){
                        $("#version").select2("val", "").change();
                        $("#version").empty();
                    }else{
                        var d = data.objV;
                        $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                        $.each(d, function (i, val) {
                            $version.append($('<option></option>').val(val.idversion).html(val.version));
                        });
                    }
                });
            }
            var $ele = $("#parte");
            $("#version").on("change", function(){
                var valor = $(this).val();
                if(valor === ""){
                    $("#parte").val("").trigger('change.select2');
                    $("#parte").select2().empty();
                }else{
                   ObtenerParte(parametros);
                }
            });//fin

            var elementos = [];
            function ObtenerParte(parametros){
                $('#parte').select2().empty();
                var idversion = document.getElementById('version').value;
                $.getJSON(parametros.ParteVersionUrl,{idversion : idversion, ajax:'true'}, function(data){
                    //console.log(data);
                    $ele.empty();
                   elementos = [];
                    for(var i=0; i < data.parte.length; i++){
                        var obj = {};
                        obj.idparte = parseInt(data.parte[i].idparte);
                        obj.acepta  = (data.parte[i].acepta == "true") ? true : false;
                        obj.locked  = data.parte[i].principal;
                        elementos.push(obj);
                    }
                    if(data.parte.length > 0){
                        bandera=true;
                        $("#principal").val('');
                        $.each(data.parte, function (i, val) {
                            debugger;
                            var option = new Option(val.parte, val.idparte, false, val.principal );
                            $ele.append(option).trigger('change');
                            if(val.principal){
                                seleccionar(val.idparte);
                            }
                        });
                        $("#principal").val($("#parte").find('option:selected').text());
                    }else{
                        $ele.empty();
                    }
                })
            }
            $("#parte").on("select2-removing", function(e) {
                debugger;
                var p = $("#principal").val();
                if (e.choice.text === p) {
                    e.preventDefault();
                    $(this).select2("close");
                }
            });

            $("#parte").change(function (e) {
                if (e.added != null){
                    seleccionar(e.added.id);
                }
                if (e.removed != null){
                    deseleccionar(e.removed.id);
                }
            });

            function seleccionar(id){
                var cod = parseInt(id);
                for (var i = 0; i < elementos.length; i++) {
                    if (elementos[i].idparte === cod){
                        elementos[i].acepta = true;
                        break;
                    }
                }
            }

            /*
            aqui vacio el select2
             $('#parte').select2().empty();
            aqui recorro los options del select2
            $("#parte option").each(function(){
                alert('opcion '+$(this).text()+' valor '+ $(this).attr('value') +' selected '+ $(this).prop('selected') )
            });
            */
            function deseleccionar(id) {
                var cod = parseInt(id);
                for (var i = 0; i < elementos.length; i++) {
                    if (elementos[i].idparte === cod) {
                        elementos[i].acepta = false;
                        break;
                    }
                }
            }


            $( "#name1tutor" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametros.getNombre1Url, {nombre1: $('#name1tutor').val().trim(), ajax: 'true'},function(data){
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

            $( "#name2tutor" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametros.getNombre2Url, {nombre2: $('#name2tutor').val().trim(), ajax: 'true'},function(data){
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


            $( "#apellido1tutor" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametros.getApellido1Url, {apellido1: $('#apellido1tutor').val().trim(), ajax: 'true'},function(data){
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

            $( "#apellido2Firma" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametros.getApellido2Url, {apellido2: $('#apellido2Firma').val().trim(), ajax: 'true'},function(data){
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


        }//fin init
    }

}();