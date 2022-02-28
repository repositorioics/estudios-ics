var direct={};
var scanCarta = function(){
    return{
        init : function(parametroII){
            direct = parametroII;
            clearInput();
            $("#select-participante-form").validate({
                rules: {
                    parametro: 'required',
                    digits: true
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
                    searchParticipante();
                }
            });

            function clearInput(){
                $("#txtNombreCompleto").val("");
                $("#principal").val("");
                $("#edad").val("");
                $("#idParticipante").val("");
                $("#estudios").val("");
                $("#madre").val("");
                $("#padre").val("").change();
                $("#relacionFam").val("");
                $("#tutor").val("");
                $("#codigo").val("");
                $("#fechacarta").val('');
                $('#carta').val('').trigger('change.select2');
                $('#version').val('').trigger('change.select2');
                $("#version").select2("val", "");
                $('#partes').val('').trigger('change.select2');
                $('#partes').val(null).trigger('change');
                $('#person').val('').trigger('change.select2');
                $('#proyecto').val('').trigger('change.select2');
                $("#edadyear").val("");
                $("#edadmeses").val("");
                $("#edaddias").val("");
                $("#parametro").val("");
            }
            function searchParticipante(){
                $("#carta").select2().empty();
                $("#carta").select2().val("").change();
                $("#version").select2().empty();
                $("#version").select2().val("").change();
                $("#partes").select2().empty();
                $("#partes").select2().val("").change();
                $.getJSON(parametroII.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    var len = data.length;
                    if(len==0){
                        swal("Error!","Código no encontrado","error");
                        clearInput();
                        $("#carta").select2().empty();
                        $("#carta").select2().val("").change();
                        $("#version").select2().empty();
                        $("#version").select2().val("").change();
                        $("#partes").select2().empty();
                        $("#partes").select2().val("").change();
                        $("#parametro").focus();
                    }
                    else{
                        if(data.estado == "0"){
                            swal({
                                title: "Advertencia!",
                                text: "Participante Retirado",
                                type: "info",
                                closeOnConfirm: true,
                                timer: 2200
                            });
                            clearInput();
                            $("#carta").select2().empty();
                            $("#carta").select2().val("").change();
                            $("#version").select2().empty();
                            $("#version").select2().val("").change();
                            $("#partes").select2().empty();
                            $("#partes").select2().val("").change();
                            $("#parametro").focus();
                        }else{
                            clearInput();
                            console.warn(data);
                            $("#codigo").val(data.codigoParticipante);
                            $("#tutor").val(data.nombreTutor);
                            $("#txtNombreCompleto").val(data.nombreCompleto);
                            $("#edadyear").val(data.edadAnios);
                            $("#edadmeses").val(data.edadMes);
                            $("#edaddias").val(data.edadDia);
                            $("#estudios").val(data.estudios);
                            $("#padre").val(data.nombrePadre);
                            $("#madre").val(data.nombreMadre);
                            $("#nombfirma").val(data.name1Tutor);
                            $("#nombre2Firma").val(data.name2Tutor);
                            $("#apellido1Firma").val(data.surname1Tutor);
                            $("#apellido2Firma").val(data.surname2Tutor);
                            var relfamiliar = parseInt(data.realFam);
                            $("#relfam").val(relfamiliar).trigger('change.select2');
                            debugger;
                            if(data.menorEdad == true){
                                $("#asentimiento").select2().val(1).trigger('change.select2');
                                $("#asentimiento").select2().prop('required', true);

                                $("#tipoasentimiento").prop('required', true);
                                $("#tipoasentimiento").select2().val(1).trigger('change.select2');
                            }else{
                                $("#asentimiento").prop('required', false);
                                $("#asentimiento").val(3).trigger('change.select2');

                                $("#tipoasentimiento").prop('required', false);
                                $("#tipoasentimiento").val(4).trigger('change.select2');
                                $("#relfam").val(8).trigger('change.select2');
                            }
                            var text = "";
                            switch (data.realFam){
                                case "1":
                                    text = "Madre";
                                    break;
                                case "2":
                                    text = "Padre";
                                    break;
                                case "3":
                                    text="Abuelo(a)";
                                    break;
                                case "4":
                                    text ="Tio(a)";
                                    break;
                                case "5":
                                    text ="Hermano(a)";
                                    break;
                                case "6":
                                    text="Otra Relación Familiar";
                                    break;
                                case "9":
                                    text="Hijo(a)";
                                    break;
                                default:
                                    text = "Participante";
                            }
                            $("#relacionFam").val(text);
                            var $carta = $('#carta');
                            $carta.empty();
                            $carta.append('<option selected value="">'+ "Seleccione..." +'</option>');
                            $.each(data.listEstudios, function (i, val) {
                                $carta.append($('<option></option>').val(val.prioridad).html(val.nombreEstudio));
                            });
                            $("#peso").focus();
                        }
                    }
                }).fail(function() {
                    swal({
                        title: "Error 500!",
                        text: "Interno del Servidor",
                        type: "error",
                        closeOnConfirm: true,
                        timer: 2000
                    });
                    $("#carta").select2().empty();
                    $("#carta").select2().val("").change();
                    $("#version").select2().empty();
                    $("#version").select2().val("").change();
                    $("#partes").select2().empty();
                    $("#partes").select2().val("").change();
                    $("#parametro").focus();
                });
            }

            $("#asentimiento").on("change", function(){
               var valor = $(this).val();
                if(valor == "2" || valor == "0"){
                    $("#tipoasentimiento").val("0").trigger('change.select2');
                }
            });

            $("#carta").on("change", function(){
                $('#version').val("").trigger('change.select2');
                $('#version').select2().empty();
                $("#partes").select2().empty();
                //$("#partes").append($('<option></option>').val('').html('Seleccione'));
                $('#partes').select2().empty().trigger('change.select2');
                $("#partes").select2("val", "");
                $("#principal").val('');
                $("#principal2").val('');
                if ($('#version').prop("disabled") == false) {}
                if ($("#carta").val() == null  || $("#carta").val() == "") {
                    $('#version').empty();
                    $('#version').append($('<option></option>').val('').html('Seleccion Versión'));
                    $("#version").prop('disabled',true);
                    $('#proyecto').val('').trigger('change.select2');
                    return;
                }
                if ($(this).val() == 3) {
                    $('#proyecto').val(3).trigger('change.select2');
                } else if ($(this).val() == 5) {
                    $('#proyecto').val(5).trigger('change.select2');
                } else if ($(this).val() == 4) {
                    $('#proyecto').val(2).trigger('change.select2');
                } else {
                    $('#proyecto').val(4).trigger('change.select2');
                }
                if($(this).val() ==="6"){
                    $("#tipoCaso").prop('disabled',false);
                }else{
                    $("#tipoCaso").select2().val(0).trigger("change").prop('disabled',true);
                }

                $("#partes").prop('disabled',false);
                $("#version").prop('disabled',false);
                ObtenerVersion(parametroII);
            });
            function ObtenerVersion(parametros){
                var idcarta = document.getElementById('carta').value;
                var $version = $('#version');
                $.getJSON(parametros.VersionCartatUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
                    $version.empty();
                    var len = data.objV.length;
                    if(len == 0){

                    }else{
                        var d = data.objV;
                        $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                        $.each(d, function (i, val) {
                        $version.append($('<option></option>').val(val.idversion).html(val.version));
                        });
                    }
                });
            }

            $("#version").on("change", function(){
                //debugger;
                $("#partes").select2("val", "");
                $("#partes").select2().empty();
                $("#principal").val('');
                $("#principal2").val('');
                if($("#version").val() == "") {
                    $("#DivPartes").hide(1000);
                    $("#DivPartes").empty();
                 }
                else {
                    $("#partes").prop('disabled',false);
                    $("#partes").val(null).trigger("change");
                    $("#DivPartes").show(1000);
                    $("#partes").empty();
                    ObtenerParte(parametroII);
                }
            });//fin
            var bandera = false;
            $("#partes").change(function (e) {
                if (e.added != null){
                    seleccionar(e.added.id);
                }
                if (e.removed != null){
                    deseleccionar(e.removed.id);
                }
            });

            $("#partes").on("select2-removing", function(e) {
                var p = $("#principal").val();
                if (e.choice.text === p) {
                    e.preventDefault();
                    $(this).select2("close");
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

            function deseleccionar(id) {
                var cod = parseInt(id);
                for (var i = 0; i < elementos.length; i++) {
                    if (elementos[i].idparte === cod) {
                        elementos[i].acepta = false;
                        break;
                    }
                }
            }

            var elementos = [];
            function ObtenerParte(parametros){
                var idversion = document.getElementById('version').value;
                var $ele = $("#partes");
                $.getJSON(parametros.ParteVersionUrl,{idversion : idversion, ajax:'true'}, function(data){
                     elementos = [];
                    var texto="";
                    for(var i=0; i < data.parte.length; i++){
                        var obj = {};
                        obj.idparte = parseInt(data.parte[i].idparte);
                        obj.acepta =  (data.parte[i].acepta == "true") ? true : false;
                        obj.locked =   data.parte[i].principal;
                        elementos.push(obj);
                    }
                    if(data.parte.length > 0){
                        bandera=true;
                        $("#principal").val('');
                        $("#principal2").val('');
                        $.each(data.parte, function (i, val) {
                            var option = new Option(val.parte, val.idparte, false, val.principal);
                            $ele.append(option).trigger('change');
                            if(val.principal){
                                seleccionar(val.idparte);
                            }
                        });
                        $("#principal").val(data.partesPrincipales[0]);
                        $("#principal2").val(data.partesPrincipales[1]);
                    }else{
                        $ele.empty();
                    }
                })
            }

            //Validar las cajas de texto...
            $('.onlytext').keypress(function (e) {
                var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
                return !((tecla > 47 && tecla < 58) || tecla == 46);
            });

            $("#btnCancel").on("click", function(e){
                var num= $("#partes").select2().val();
                if($.isEmptyObject(num)){
                    alert("Selecciona al menos una opción");
                    return;
                }else{
                    console.log("else Id : " + num);
                }
            });
            $('#form-scan').submit(function(e){
                e.preventDefault();
                var isOK = ValidateForm();
                if (isOK) {
                    var text = $("#person option:selected").html();
                    var separador = "-";
                    var textoseparado = text.split(separador);
                    data = {
                        codigo: parseInt($("#codigo").val().trim()),
                        version: parseInt($("#version").val().trim()),
                        asentimiento: $("#asentimiento").val().trim(),
                        relfam: parseInt($("#relfam").val().trim()),
                        nombfirma: $("#nombfirma").val().trim(),
                        nombre2Firma: $("#nombre2Firma").val().trim(),
                        apellido1Firma: $("#apellido1Firma").val().trim(),
                        apellido2Firma: $("#apellido2Firma").val().trim(),
                        person: parseInt($("#person").val().trim()),
                        fechacarta: $("#fechacarta").val(),
                        proyecto: $("#proyecto").val(),
                        contactoFuturo: ($('input:checkbox[name=contactoFuturo]').prop('checked') == true) ? '1' : '0',
                        testigopresente: ($('input:checkbox[name=chktestigo]').prop('checked') == true) ? '1' : '0',
                        nombre1testigo: $("#nombre1Testigo").val().trim(),
                        nombre2testigo: $("#nombre2Testigo").val().trim(),
                        apellido1testigo: $("#apellido1Testigo").val().trim(),
                        apellido2testigo: $("#apellido2Testigo").val().trim(),
                        observacion :$("#observacion").val().trim(),
                        edadyears: parseInt( $("#edadyear").val().trim()),
                        edadmeses: parseInt( $("#edadmeses").val().trim()),
                        edaddias: parseInt( $("#edaddias").val().trim()),
                        recurso: textoseparado[0],
                        tipoasentimiento: $("#tipoasentimiento").val().trim(),
                        parte: elementos,
                        estudios_actuales: $("#estudios").val(),
                        esIndiceOrMiembro:parseInt($("#tipoCaso").val().trim())
                    };
                    GuardarScan(data);
                }
            });
            function ValidateForm() {
                var isAllValid = true;
                $('.form-group').removeClass('is-invalid');
                if( isNaN( $("#codigo").val() )){
                    isAllValid = false;
                    $("#codigo").addClass('is-invalid');
                }else{
                    $("#idparticipante").removeClass('is-invalid');
                }
                if($("#carta").val().trim() == "" || $("#carta").val().trim() == null){
                    isAllValid = false;
                    $('#carta').addClass('is-invalid');
                }
                else{
                    $('#carta').removeClass('is-invalid');
                }

                if($("#version").val() == null || $("#version").val() == ""){
                    isAllValid = false;
                    $('#version').addClass('is-invalid');
                }
                else{
                    $('#version').removeClass('is-invalid');
                }

                var num = $("#partes").select2().val();
                if($.isEmptyObject(num)){
                    $('#partes').addClass('is-invalid');
                    isAllValid = false;
                }else{
                    $('#partes').removeClass('is-invalid');
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
                if($("#asentimiento").val() == "" || $("#asentimiento").val() == null){
                    isAllValid = false;
                    $('#asentimiento').addClass('is-invalid');
                }else{
                    $('#asentimiento').removeClass('is-invalid');
                }

                if($("#tipoasentimiento").val() == "" || $("#tipoasentimiento").val() == null){
                    isAllValid = false;
                    $('#tipoasentimiento').addClass('is-invalid');
                }else{
                    $('#tipoasentimiento').removeClass('is-invalid');
                }
                return isAllValid;
            }


            function GuardarScan(obj){
                $.ajax({
                    url: direct.saveScanCartaUrl,
                    type: "POST",
                    data: JSON.stringify(obj),
                    dataType: "JSON",
                    contentType:'application/json;charset=utf-8',
                    success: function(response){
                        console.log(response);
                        if(response.msj != null){
                            swal({
                                title: "Advertencia!",
                                text: response.msj,
                                type: "info",
                                closeOnConfirm: true,
                                timer: 2000
                            });
                        }else{
                            clearInput();
                            swal({
                                title: "Buen trabajo!",
                                text: direct.successmessage,
                                type: "success",
                                closeOnConfirm: true,
                                timer: 2000
                            });
                            window.setTimeout(function(){
                                window.location.href = direct.cartaSaveEditUrl+"/"+response.idparticipantecarta;
                            }, 1500);
                        }
                    },error: function(jqXHR, textStatus,e){
                        swal({
                            title: "Error 500!",
                            text: "Interno del Servidor",
                            type: "error",
                            closeOnConfirm: true,
                            timer: 2000
                        });
                    }
                });
            }

            $("#chkTestigo").on("click", function(){
                var status = $(this).prop("checked");
                if(status == true){
                    $("#selectt").fadeIn("slow");
                    $("#nombre1Testigo").prop('required',true);
                    $("#apellido1Testigo").prop('required',true);
                }else{
                    $("#selectt").fadeOut("slow");
                    $("#nombre1Testigo").prop('required', false);
                    $("#apellido1Testigo").prop('required', false);
                }
            });

            $( "#nombfirma" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getNombre1Url, {nombre1: $('#nombfirma').val().trim(), ajax: 'true'},function(data){
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

            $( "#nombre2Firma" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getNombre2Url, {nombre2: $('#nombre2Firma').val().trim(), ajax: 'true'},function(data){
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

            $( "#apellido1Firma" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getApellido1Url, {apellido1: $('#apellido1Firma').val().trim(), ajax: 'true'},function(data){
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
                    $.getJSON(direct.getApellido2Url, {apellido2: $('#apellido2Firma').val().trim(), ajax: 'true'},function(data){
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

            //testigo
            $( "#nombre1Testigo" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getNombre1Url, {nombre1: $('#nombre1Testigo').val().trim(), ajax: 'true'},function(data){
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

            $( "#nombre2Testigo" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getNombre2Url, {nombre2: $('#nombre2Testigo').val().trim(), ajax: 'true'},function(data){
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

            $( "#apellido1Testigo" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getApellido1Url, {apellido1: $('#apellido1Testigo').val().trim(), ajax: 'true'},function(data){
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

            $( "#apellido2Testigo" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getApellido2Url, {apellido2: $('#apellido2Testigo').val().trim(), ajax: 'true'},function(data){
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
        }
    }
}();





