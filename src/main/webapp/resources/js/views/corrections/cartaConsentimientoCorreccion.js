/**
 * Created by PC on 16/11/2022.
 */
var cartaConsentimiento = function(){
    return{
        init: function(parametros){
            var table = $("#tableCartParticipnt").DataTable({
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                },
                "columnDefs":[{
                        targets: [0,1,2,3,4,5],
                        className: 'text-center'
                    }
                ]
            });

            $('#tableCartParticipnt thead tr').clone(true).appendTo( '#tableCartParticipnt thead' );
            $('#tableCartParticipnt thead tr:eq(1) th').each( function (i) {
                var title = $(this).text();
                if (title != 'Acciones') {
                    $(this).html('<input type="text" placeholder="Buscar '+title+'" class="form-control-buscar form-control-xs" />');
                    $('input', this).on('keyup change', function () {
                        if (table.column(i).search() !== this.value) {
                            table.column(i).search(this.value).draw();
                        }
                    });
                } else {
                    $(this).html('');
                }
            });

            //region form
            var form1 = $('#formCartaApp');
            form1.validate({
                rule:{
                    codigo: {required:true},
                    paricipanteCode: {required:true}
                },
                errorElement: 'em',
                errorPlacement: function ( error, element ) {
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
                    SaveCarta(parametros);
                }
            });

            function SaveCarta(dir){
                $.post(dir.saveCartaUrl, form1.serialize(), function (data) {
                    debugger;
                    if (data.codigo != undefined){
                        swal({
                            title: "¡Buen trabajo!",
                            type: "success",
                            text: dir.successmessage,
                            timer: 1500
                        });
                        window.setTimeout(function () {
                            window.location.href = dir.CorrectionFormUrl;
                        }, 1000);
                    }
                }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                    swal({
                        title: "¡Ocurrió un error!",
                        type: "error",
                        text: "Interno del Servidor!",
                        timer: 2000
                    });
                });
            }
            //endregion

            $("#tableCartParticipnt tbody").on("click", '.btnForm', function(e){
                e.preventDefault();
                $("#sent-tab").addClass('active');
                $("#sent-tab").attr('aria-expanded', true);

                $("#inbox-tab").removeClass('active');
                $("#inbox-tab").attr('aria-expanded', false);

                $("#inbox").removeClass('active show');
                $("#inbox").attr('aria-expanded', false);
                $("#sent").addClass('active show');
                $("#sent").attr('aria-expanded', true);

                var  codigo = $(this).data('id');
                $.getJSON(parametros.verConsentUrl,{codigo: codigo, ajax: 'true'}, function(data){
                    resetForm();
                    console.log(data);
                    $("#codigo").val(data.codigo);
                    $("#participanteCode").val(data.participante.codigo);
                    /* Nombre Participante */
                    $("#nombre1Testigo").val(data.nombre1Testigo);
                    $("#nombre2Testigo").val(data.nombre2Testigo);
                    $("#apellido1Testigo").val(data.apellido1Testigo);
                    $("#apellido2Testigo").val(data.apellido2Testigo);

                    /* Nombres del TUTOR */
                    $("#NombreTutor1").val(data.nombre1Tutor);
                    $("#NombreTutor2").val(data.nombre2Tutor);
                    $("#ApellidoTutor1").val(data.apellido1Tutor);
                    $("#ApellidoTutor2").val(data.apellido2Tutor);
                    $("#carta").val(data.tamizaje.estudio.nombre);
                    $("#version").val(data.version);
                    (data.testigoPresente==="1")?$("#testigoPresente").prop('checked', true):$("#testigoPresente").prop('checked', false);
                    verifcarTestigo();

                    (data.mismoTutor==="1")?$("#mismoTutor").prop('checked', true):$("#mismoTutor").prop('checked', false);
                    var d =new Date(data.fechaFirma);
                    var datestring =  ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                    $("#fechaCarta").val(datestring);
                    /* CONTACTO FUTURO*/
                    if(data.aceptaContactoFuturo != null || data.aceptaContactoFuturo!="") {
                        var optionUnselectedContacto = $('#contactoFuturo option:not(:selected)');
                        for (var i = 0; i < optionUnselectedContacto.length; i++) {
                            if (data.aceptaContactoFuturo === optionUnselectedContacto[i].value) {
                                $("#contactoFuturo").select2().val(optionUnselectedContacto[i].value).trigger("change");
                                break;
                            }
                        }
                    }
                    /*  RELACION FAMILIAR */
                    if(data.relacionFamiliarTutor != null || data.relacionFamiliarTutor != ""){
                        var optionUnselectedRelFam= $('#relacionFamiliarTutor option:not(:selected)');
                        for (var i = 0; i < optionUnselectedRelFam.length; i++) {
                            if (data.relacionFamiliarTutor === optionUnselectedRelFam[i].value){
                                $("#relacionFamiliarTutor").select2().val( optionUnselectedRelFam[i].value ).trigger("change");
                                break;
                            }
                        }
                    }

                    /*  PARTE A */
                    if(data.aceptaParteA != null || data.aceptaParteA != ""){
                        var optionUnselectedParteA= $('#parteA option:not(:selected)');
                        for (var i = 0; i < optionUnselectedParteA.length; i++) {
                            if (data.aceptaParteA === optionUnselectedParteA[i].value){
                                $("#parteA").select2().val( optionUnselectedParteA[i].value ).trigger("change");
                                break;
                            }
                        }
                    }

                     /*  PARTE B */
                    if(data.aceptaParteB != null || data.aceptaParteB != ""){
                        var optionUnselectedParteB= $('#parteB option:not(:selected)');
                        for (var i = 0; i < optionUnselectedParteB.length; i++) {
                            if (data.aceptaParteB === optionUnselectedParteB[i].value){
                                $("#parteB").select2().val( optionUnselectedParteB[i].value ).trigger("change");
                                break;
                            }
                        }
                    }

                    /*  PARTE C */
                    if(data.aceptaParteC != null || data.aceptaParteC != ""){
                        var optionUnselectedParteC= $('#parteC option:not(:selected)');
                        for (var i = 0; i < optionUnselectedParteC.length; i++) {
                            if (data.aceptaParteC === optionUnselectedParteC[i].value){
                                $("#parteC").select2().val( optionUnselectedParteC[i].value ).trigger("change");
                                break;
                            }
                        }
                    }

                    /*  PARTE D */
                    if(data.aceptaParteD!=null || data.aceptaParteD !=""){
                        var optionUnselectedParteD= $('#parteD option:not(:selected)');
                        for (var i = 0; i < optionUnselectedParteD.length; i++) {
                            if (data.aceptaParteD === optionUnselectedParteD[i].value){
                                $("#parteD").select2().val( optionUnselectedParteD[i].value ).trigger("change");
                                break;
                            }
                        }
                    }

                    /*  PARTE E */
                    if(data.aceptaParteE!=null || data.aceptaParteE !=""){
                        var optionUnselectedParteE= $('#parteE option:not(:selected)');
                        for (var i = 0; i < optionUnselectedParteE.length; i++) {
                            if (data.aceptaParteE === optionUnselectedParteE[i].value){
                                $("#parteE").select2().val( optionUnselectedParteE[i].value ).trigger("change");
                                break;
                            }
                        }
                    }

                    /*  PARTE F */
                    if(data.aceptaParteF!=null || data.aceptaParteF !=""){
                        var optionUnselectedParteF= $('#parteF option:not(:selected)');
                        for (var i = 0; i < optionUnselectedParteF.length; i++) {
                            if (data.aceptaParteF === optionUnselectedParteF[i].value){
                                $("#parteF").select2().val( optionUnselectedParteF[i].value ).trigger("change");
                                break;
                            }
                        }
                    }

                    /*  PARTE G */
                    if(data.aceptaParteG!=null || data.aceptaParteG !=""){
                        var optionUnselectedParteG= $('#parteG option:not(:selected)');
                        for (var i = 0; i < optionUnselectedParteG.length; i++) {
                            if (data.aceptaParteG === optionUnselectedParteG[i].value){
                                $("#parteG").select2().val( optionUnselectedParteG[i].value ).trigger("change");
                                break;
                            }
                        }
                    }

                });//fin getJson
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

            });


            function Buscarcp(parametros){
                $.getJSON(parametros.searchPartesUrl,{parametro : $('#parametro').val(), ajax : 'true' },function(data){
                    console.log(data);
                    if(data.mensaje != null){
                        swal("Información", data.mensaje,"info");
                        return;
                    }
                    var len = data.length;
                    if(len == 0){
                        swal({
                            title: "¡Información!",
                            text: "No se encontraron registros!",
                            type: "info",
                            timer: 2000
                        });
                        $("#parametro").val("").focus();
                    }else{
                        for ( var i = 0; i < len; i++) {
                            var id = data[i].codigo;
                            var getCode = data[i].participante.codigo;
                            var NameComplete = data[i].participante.nombre1 +" "+ data[i].participante.apellido1;
                            var datestring = getFormattedDate(new Date(data[i].fechaFirma));
                            var carta = data[i].tamizaje.estudio.nombre;
                            var version = data[i].version;
                            var usuario = data[i].recordUser;
                            var boton = '<a class="btn btn-warning btnForm" data-id="' + data[i].codigo + '"> <i class="fa fa-pencil-square text-white" aria-hidden="true"></i> </a>';
                            table.row.add([
                                getCode,
                                NameComplete,
                                carta,
                                version,
                                datestring,
                                usuario,
                                boton
                            ]).draw( false );
                        }
                    }
                }).fail(function() {
                    swal({
                        title: "¡ERROR!",
                        text: parametros.error,
                        type: "error",
                        timer: 2000
                    });
                    $("#parametro").focus();
                });
            }

            function getFormattedDate(date) {
                var year = date.getFullYear();
                var month = (1 + date.getMonth()).toString();
                month = month.length > 1 ? month : '0' + month;
                var day = date.getDate().toString();
                day = day.length > 1 ? day : '0' + day;
                return day + '/' + month + '/' + year;
            }

            function resetForm(){
                document.getElementById('formCartaApp').reset();
                $("#contactoFuturo").select2().val("").trigger('change.select2');
                $("#relacionFamiliarTutor").select2().val("").trigger('change.select2');
                $("#parteA").select2().val("").trigger('change.select2');
                $("#parteB").select2().val("").trigger('change.select2');
                $("#parteC").select2().val("").trigger('change.select2');
                $("#parteD").select2().val("").trigger('change.select2');
                $("#parteE").select2().val("").trigger('change.select2');
                $("#parteF").select2().val("").trigger('change.select2');
                $("#parteG").select2().val("").trigger('change.select2');
            }

            function verifcarTestigo(){
                var result = $('#testigoPresente').prop('checked');
                console.log(result);
                if (result){
                    $("#nombre1Testigo, #nombre2Testigo, #apellido1Testigo, #apellido2Testigo").removeAttr('readonly');
                    $("#nombre1Testigo").focus();
                }else{
                    $("#nombre1Testigo, #nombre2Testigo, #apellido1Testigo, #apellido2Testigo").val('');
                    $("#nombre1Testigo, #nombre2Testigo, #apellido1Testigo, #apellido2Testigo").attr('readonly', true);
                }
            }

            $( "#NombreTutor1" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametros.getNombre1Url, {nombre1: $('#NombreTutor1').val().trim(), ajax: 'true'},function(data){
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

            $( "#NombreTutor2" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametros.getNombre2Url, {nombre2: $('#NombreTutor2').val().trim(), ajax: 'true'},function(data){
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

            $( "#ApellidoTutor1" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametros.getApellido1Url, {apellido1: $('#ApellidoTutor1').val().trim(), ajax: 'true'},function(data){
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

            $( "#ApellidoTutor2" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(parametros.getApellido2Url, {apellido2: $('#ApellidoTutor2').val().trim(), ajax: 'true'},function(data){
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
                    $.getJSON(parametros.getNombre1Url, {nombre1: $('#nombre1Testigo').val().trim(), ajax: 'true'},function(data){
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
                    $.getJSON(parametros.getNombre2Url, {nombre2: $('#nombre2Testigo').val().trim(), ajax: 'true'},function(data){
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
                    $.getJSON(parametros.getApellido1Url, {apellido1: $('#apellido1Testigo').val().trim(), ajax: 'true'},function(data){
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
                    $.getJSON(parametros.getApellido2Url, {apellido2: $('#apellido2Testigo').val().trim(), ajax: 'true'},function(data){
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

        }//fin return
    }
}();