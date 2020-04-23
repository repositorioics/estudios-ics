var direct={};
var scanCarta = function(){
    return{
        init : function(parametroII){
            direct = parametroII;
            $("#select-participante-form").validate({
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
                    searchParticipante();
                }
            });
            function clearInput(){
                $("#txtNombreCompleto").val("");
                $("#edad").val("");
                $("#idParticipante").val("");
                $("#estudios").val("");
                $("#madre").val("");
                $("#padre").val("").change();
                $("#relacionFam").val("");
                $("#tutor").val("");
                $("#codigo").val("");
                $("#parametro").val("");
            }
            function searchParticipante(){
                $.getJSON(parametroII.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    var len = data.length;
                    if(len==0){
                        swal("Error!","Código no encontrado","error");
                        clearInput();
                        $("#parametro").focus();
                    }
                    else{
                        if(data.estado == "0"){
                            swal("Advertencia!", "Participante está retirado!", "warning");
                            clearInput();
                            $("#parametro").focus();
                        }else{
                            var elemento = data.edad;
                            var fecha = elemento.split('/');
                            var datestring = ( fecha[0] + " Años " + fecha[1] + " Meses " + fecha[2] + " Dias");
                            $("#anios").val(fecha[0]);
                            $("#mes").val(fecha[1]);
                            $("#dias").val(fecha[2]);
                            $("#codigo").val(data.codigo);
                            $("#tutor").val(data.tutor);
                            $("#txtNombreCompleto").val(data.nombre);
                            $("#fecha").val(data.fecha);
                            $("#idParticipante").val(data.codigo);
                            $("#edad").val(datestring);
                            $("#estudios").val(data.estudios);
                            $("#padre").val(data.padre);
                            $("#madre").val(data.madre);
                            var text = "";
                            switch (data.relacionFam){
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
                                default:
                                    text = "Participante";
                            }
                            $("#relacionFam").val(text);
                            $("#peso").focus();
                        }
                    }
                }).fail(function() {
                    swal("Error!","Código no existe!", "error");
                    $("#parametro").focus();
                });
            }
            $("#carta").on("change", function(){
                if ($('#version').prop("disabled") == false) {}
                if ($("#carta").val() == null  || $("#carta").val() == "") {
                    $('#version').empty();
                    $('#version').append($('<option></option>').val('').html('Seleccion Versión'));
                    $("#version").prop('disabled',true);
                    return;
                }
                //$("#partes").prop('disabled',false);
                $("#version").prop('disabled',false);
                ObtenerVersion(parametroII);
            });//fin Select carta $("#version").val() > 0 ||
            $("#version").on("change", function(){
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
            var elementos = [];
            function ObtenerParte(parametros){
                var idversion = document.getElementById('version').value;
                var $ele = $("#partes");
                $.getJSON(parametros.ParteVersionUrl,{idversion : idversion, ajax:'true'}, function(data){
                    elementos = [];
                    for(var i=0; i < data.parte.length; i++){
                        var obj = {};
                        obj.idparte = parseInt(data.parte[i].idparte);
                        obj.acepta =  (data.parte[i].acepta == "true") ? true : false;
                        elementos.push(obj);
                    }
                    if(data.parte.length > 0){
                        bandera=true;
                        $.each(data.parte, function (i, val) {
                           $ele.append($('<option/>').val(val.idparte).text(val.parte));
                        })
                    }else{
                        $ele.empty();
                    }
                })
            }

            function ObtenerVersion(parametros){
                var idcarta = document.getElementById('carta').value;
                var $version = $('#version');
                $version.empty();
                $.getJSON(parametros.VersionCartatUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
                    var len = data.objV.length;
                    if(len == 0){
                        //swal("Advertencia!", "Carta no tiene Versión!", "warning");
                    }else{
                        var d = data.objV;
                        $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                        $.each(d, function (i, val) {
                            $version.append($('<option></option>').val(val.idversion).html(val.version));
                        });
                    }
                });
            }

            //Validar las cajas de texto...
            $('#nombfirma').keypress(function (e) {
                var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
                return !((tecla > 47 && tecla < 58) || tecla == 46);
            });
            //Validar las cajas de texto...
            $('#nombre2Firma').keypress(function (e) {
                var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
                return !((tecla > 47 && tecla < 58) || tecla == 46);
            });
            $('#apellido1Firma').keypress(function (e) {
                var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
                return !((tecla > 47 && tecla < 58) || tecla == 46);
            });
            $('#apellido1Firma').keypress(function (e) {
                var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
                return !((tecla > 47 && tecla < 58) || tecla == 46);
            });
            $('#apellido2Firma').keypress(function (e) {
                var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
                return !((tecla > 47 && tecla < 58) || tecla == 46);
            });

            $("#btnCancel").on("click", function(e){
                debugger;
                var num= $("#partes").select2().val();
                if($.isEmptyObject(num)){
                    alert("Selecciona al menos una opción");
                    return;
                }else{
                    console.log("else Id : " + num);
                }
            });

            $("#btnSave").on("click", function(e){
                e.preventDefault();
                var isValidItem = true;
                $('#error').empty();
                var data = {};
                if($("#fechacarta").val() == "" || $("#fechacarta").val()== null){
                    $('#fechacarta').siblings('span.error').css('visibility', 'visible');
                    $('#fechacarta').parents('.form-group').addClass('has-danger');
                    isAllValid = false;
                }
                else{
                    $('#fechacarta').siblings('span.error').css('visibility', 'hidden');
                    $('#fechacarta').parents('.form-group').removeClass('has-danger');
                }

                if ($('#nombfirma').val().trim() == "" || $("#nombfirma").val().trim() == null) {
                    $('#nombfirma').siblings('span.error').css('visibility', 'visible');
                    $('#nombfirma').parents('.form-group').addClass('has-danger');
                    isAllValid = false;
                }
                else {
                    $('#nombfirma').siblings('span.error').css('visibility', 'hidden');
                    $("#nombfirma").parents('.form-group').removeClass('has-danger');
                }

                if($("#apellido1Firma").val().trim() == "" || $("#apellido1Firma").val().trim() == null){
                   $('#apellido1Firma').siblings('span.error').css('visibility', 'visible');
                   $('#apellido1Firma').parents('.form-group').addClass('has-danger');
                    isValidItem = false;
                }else{
                    $('#apellido1Firma').siblings('span.error').css('visibility', 'hidden');
                    $("#apellido1Firma").parents('.form-group').removeClass('has-danger');
                }

                if ($('#relfam').val() == null || $('#relfam').val() == "") {
                    $('#relfam').siblings('span.error').css('visibility', 'visible');
                    $('#relfam').parents('.form-group').addClass('has-danger');
                    isAllValid = false;
                }
                else {
                    $('#relfam').siblings('span.error').css('visibility', 'hidden');
                }

                if($("#codigo").val().trim() == "" || $("#codigo").val()== null){
                    $('#codigo').siblings('span.error').css('visibility', 'visible');
                    $('#codigo').parents('.form-group').addClass('has-danger');
                    $('#codigo').focus();
                    isValidItem = false;
                }else{
                    $('#codigo').siblings('span.error').css('visibility', 'hidden');
                    $('#codigo').parents('.form-group').removeClass('has-danger');
                }

                if($("#carta").val().trim() == "" || $("#carta").val().trim() == null){
                    isValidItem = false;
                    $('#carta').siblings('span.error').css('visibility', 'visible');
                }
                else{
                    $('#carta').siblings('span.error').css('visibility', 'hidden');
                }

                if($("#version").val().trim() == "" || $("#version").val().trim() == null){
                    isValidItem = false;
                    $('#version').siblings('span.error').css('visibility', 'visible');
                    $('#version').parents('.form-group').addClass('has-danger');
                }
                else{
                    $('#version').siblings('span.error').css('visibility', 'hidden');
                }

                if($("#person").val()=="" || $("#person").val()== null){
                    isValidItem = false;
                    $('#person').siblings('span.error').css('visibility', 'visible');
                    $('#person').parents('.form-group').addClass('has-danger');
                }
                else{
                    $('#person').siblings('span.error').css('visibility', 'hidden');
                }

                if($("#asentimiento").val() == "" || $("#asentimiento").val() == null){
                    isValidItem = false;
                    $('#asentimiento').siblings('span.error').css('visibility', 'visible');
                    $('#asentimiento').parents('.form-group').addClass('has-danger');
                }else{
                    $('#asentimiento').siblings('span.error').css('visibility', 'hidden');
                }

                if($("#proyecto").val()=="" || $("#proyecto").val()== null){
                    $('#proyecto').siblings('span.error').css('visibility', 'visible');
                    $('#proyecto').parents('.form-group').addClass('has-danger');
                    isValidItem = false;
                }else{
                    $('#proyecto').siblings('span.error').css('visibility', 'hidden');
                }

                if($("#tipoasentimiento").val()=="" || $("#tipoasentimiento").val()== null){
                    $('#tipoasentimiento').siblings('span.error').css('visibility', 'visible');
                    $('#tipoasentimiento').parents('.form-group').addClass('has-danger');
                    isValidItem = false;
                }else{
                    $('#tipoasentimiento').siblings('span.error').css('visibility', 'hidden');
                }
                var num = $("#partes").select2().val();
                if($.isEmptyObject(num)){
                    $('#partes').siblings('span.error').css('visibility', 'visible');
                    $('#partes').parents('.form-group').addClass('has-danger');
                    isValidItem = false;
                }else{
                    $('#partes').siblings('span.error').css('visibility', 'hidden');
                }
                if(isValidItem){
                    //if(bandera){}
                    if (typeof elementos !== 'undefined' && elementos.length > 0) {
                        console.log('MyArrayPartes is not empty.');
                    }else{
                        console.log('MyArrayPartes is empty.');
                    }
                    var text = $("#person option:selected").html();
                    var separador ="-";
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
                        retiro:   ($('input:checkbox[name=retiro]').prop('checked') == true) ? '1' : '0',
                        observacion :$("#observacion").val().trim(),
                        recurso: textoseparado[0],
                        tipoasentimiento: $("#tipoasentimiento").val().trim(),
                        parte: elementos
                    };
                    GuardarScan(data);
                }
            })

            function GuardarScan(obj){
                console.log(obj);
                $.ajax({
                    url: '/estudios_ics/cartas/saveScanCarta/',
                    type: "POST",
                    data: JSON.stringify(obj),
                    dataType: "JSON",
                    contentType: "application/json",
                    success: function(d){
                        clearInput();
                        swal("Éxito!", "Información guardada!", "success")
                        window.setTimeout(function(){
                            window.location.href = direct.Lista2ScanCartaUrl;
                        }, 1500);
                    },error: function(jqXHR, textStatus,e){
                        var myAlert="";
                        myAlert += "<div class='alert alert-danger alert-dismissible fade show' role='alert'>";
                        myAlert += "<strong>Error!</strong> al enviar la información.";
                        myAlert += "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>";
                        myAlert += "<span aria-hidden='true'>&times;</span>";
                        myAlert += "</button>";
                        myAlert += "</div>";
                        $('#error').html(myAlert).show();
                    }
                });
            }

            function seleccionar(id){
                var cod = parseInt(id);
                for (var i = 0; i < elementos.length; i++) {
                    if (elementos[i].idparte === cod){
                        elementos[i].acepta = true;
                        break;
                    }
                }
            }
            function deseleccionar(id){
                var cod = parseInt(id);
                for (var i = 0; i < elementos.length; i++) {
                    if (elementos[i].idparte === cod){
                        elementos[i].acepta = false;
                        break;
                    }
                }
            }

       }
    }
}();




