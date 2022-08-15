/**
 * Created by ICS on 02/06/2020.
 */

var processCandidateCovid = function(){

    return {
        init: function (parametros) {
            var muestra  = $('#tblMuestra').DataTable({
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                }
            });
            var chf_muestra  = $('#tblChfMuestra').DataTable({
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                }
            });
            $('#tblMuestra thead tr').clone(true).appendTo( '#tblMuestra thead' );
            $('#tblMuestra thead tr:eq(1) th').each( function (i) {
                var title = $(this).text();
                if (title != 'Acciones') {
                    $(this).html('<input type="text" placeholder="Buscar '+title+'" class="form-control-buscar" />');
                    $('input', this).on('keyup change', function () {
                        if (muestra.column(i).search() !== this.value) {
                            muestra.column(i).search(this.value).draw();
                        }
                    });
                } else {
                    $(this).html('');
                }
            });
            $('#tblChfMuestra thead tr').clone(true).appendTo( '#tblChfMuestra thead' );
            $('#tblChfMuestra thead tr:eq(1) th').each( function (i) {
                var title = $(this).text();
                if (title != 'Acciones') {
                    $(this).html('<input type="text" placeholder="Buscar '+title+'" class="form-control-buscar" />');
                    $('input', this).on('keyup change', function () {
                        if (chf_muestra.column(i).search() !== this.value) {
                            chf_muestra.column(i).search(this.value).draw();
                        }
                    });
                } else {
                    $(this).html('');
                }
            });

            var form1 = $('#search-participant-form');
            form1.validate({
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
                    muestra.clear().draw( false );
                    chf_muestra.clear().draw( false );
                    search();
                }
            });

            function clearSpand(){
                $("#numMuestra").text("0/0");
                $("#numCHFMuestra").text("0/0");
                $("#numDiasCHFMuestra").text('0');
                $("#numDiasMuestra").text('0');
            }
            $("#numMuestra").text("0/0");
            $("#numCHFMuestra").text("0/0");
            function search(){
                $("#numDiasCHFMuestra").text('0');
                $("#numDiasMuestra").text('0');
                $.getJSON( parametros.searchUrl , form1.serialize() , function( data ) {
                        console.log(data);
                        if (data.mensaje != undefined) {
                            toastr.error(data.mensaje,"Error",{timeOut: 5000});
                            $("#casaCHF").val("");
                            $("#codigoParticipante").val("");
                            $("#estudios").val("");
                            clearSpand();
                        }
                        else {
                            $("#casaCHF").val(data.casaFamilia);
                            $("#codigoParticipante").val(data.codigo);
                            $("#estudios").val(data.estudios);
                            if (data.validacion != null){
                                toastr.warning(data.validacion);
                            }
                            if(data.alertas != '' ){
                                $("#alertas").html(data.alertas).show();
                                $("#alertas").show();
                                $("#msgAlert").show();
                            }else{
                                $("#alertas").hide();
                                $("#msgAlert").hide();
                            }

                            if(data.muestras.length ==0 && data.chf_muestras.length ==0 ){
                                $("#numMuestra").text("0/0");
                                $("#numCHFMuestra").text("0/0");
                            }

                            var contadorMuestraByCodigo = 0;
                            var contadorChf_MuestraByCodigo = 0;
                            $.each(data.muestras, function(index, element){
                                if(element.codigoParticipante === data.codigo){
                                    contadorMuestraByCodigo++;
                                }
                                muestra.row.add([
                                    element.codigoParticipante,
                                    element.fechaRegistro,
                                    element.fechaToma,
                                    element.terreno,
                                    element.usuario,
                                    element.tuboRojo,
                                    element.tuboPbmc,
                                    element.tuboBhc,
                                    element.estudiosActuales
                                ]).draw(false);
                            });
                            $("#numMuestra").text(contadorMuestraByCodigo +"/"+ data.muestras.length);
                            $("#numDiasMuestra").text(data.intervalo);
                            $("#numDiasCHFMuestra").text(data.intervalo);

                            // 1= Muestreo anual, 2= Muestra enfermo, 3= Muestra transmision (MI), 4= UO1 Positivo, 5= UO1 Vacuna
                            $.each(data.chf_muestras, function(index, element){
                                if(element.codigoParticipante === data.codigo){
                                    contadorChf_MuestraByCodigo++;
                                }
                                $("#numCHFMuestra").text(contadorChf_MuestraByCodigo+"/"+data.chf_muestras.length);
                                var proposito = "";
                                switch (element.proposito){
                                    case "1":
                                        proposito = "Muestreo anual";
                                        break;
                                    case "2":
                                        proposito = "Muestra enfermo";
                                        break;
                                    case "3":
                                        proposito="Muestra transmision (MI)";
                                        break;
                                    case "4":
                                        proposito ="UO1 Positivo";
                                        break;
                                    case "5":
                                        proposito ="UO1 Vacuna";
                                        break;
                                    default:
                                        proposito = "Desconocido";
                                }
                                //1=Rojo, 2=BHC, 3=PBMC, 4=Medio, 5=MEM, 9=SM
                                var tipoTubo='';
                                switch (element.tipoTubo){
                                    case "1":
                                        tipoTubo = "Rojo";
                                        break;
                                    case "2":
                                        tipoTubo="BHC";
                                        break;
                                    case "3":
                                        tipoTubo="PBMC";
                                        break;
                                    case "4":
                                        tipoTubo="Medio";
                                        break;
                                    case "5":
                                        tipoTubo="MEM";
                                        break;
                                    case "9":
                                        tipoTubo="SM";
                                        break;
                                    default :
                                        tipoTubo="Desconocido";
                                }
                                //1=Sangre, 2=Hisopado Faringeo, 3=Hisopado Nasal, 4=Hisopado Nasal y Faringeo, 5=Lavado Nasal, 9=Sin muestra
                                var tipoMx='';
                                switch (element.tipoMuestra){
                                    case "1":
                                        tipoMx="Sangre";
                                        break;
                                    case "2":
                                        tipoMx="Hisopado Faringeo";
                                        break;
                                    case "3":
                                        tipoMx="Hisopado Nasal";
                                        break;
                                    case "4":
                                        tipoMx="Hisopado Nasal y Faringeo";
                                        break;
                                    case "5":
                                        tipoMx="Lavado Nasal";
                                        break;
                                    case "9":
                                        tipoMx="Sin muestra";
                                        break;
                                    default :
                                        tipoMx="Desconocido";
                                }
                                //1=Muestra dificil, 2=Se descanalizó, 3=Se pinchó mas de 2 veces, 4=Padre/niño no aceptó tomar muestra, 5=Padre o tutor despues de pinchadazo no desea que se le tome mx al niño, 998=Otra razon
                                var razonNotomaMx ="";
                                switch (element.razonNoToma){
                                    case "1":
                                        razonNotomaMx="Muestra dificil";
                                        break;
                                    case "2":
                                        razonNotomaMx="Se descanalizó";
                                        break;
                                    case "3":
                                        razonNotomaMx="Se pinchó mas de 2 veces";
                                        break;
                                    case "4":
                                        razonNotomaMx="Padre/niño no aceptó tomar muestra";
                                        break;
                                    case "5":
                                        razonNotomaMx="Padre o tutor despues de pinchadazo no desea que se le tome mx al niño";
                                        break;
                                    case "998":
                                        razonNotomaMx="Otra razon";
                                        break;
                                    default :
                                        razonNotomaMx="-"
                                }
                                var mxTomada = '';
                                switch (element.muestraTomada){
                                    case "0":
                                        mxTomada = "No";
                                        break;
                                    case "1":
                                        mxTomada = "Si";
                                        break;
                                    default :"-";
                                }
                                chf_muestra.row.add([
                                    element.codigoParticipante,
                                    element.fechaRegistro,
                                    element.fechaToma,
                                    element.volumen,
                                    element.codLabMuestra,
                                    tipoTubo,
                                    proposito,
                                    tipoMx,
                                    mxTomada,
                                    razonNotomaMx
                                ]).draw(false);
                            });
                        }
                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                   toastr.error( "error:" + errorThrown);
                        $("#tblMuestra").DataTable().clear().draw();
                        $("#tblChfMuestra").DataTable().clear().draw();
                        $("#alertas").hide();
                        $("#msgAlert").hide();
                        clearSpand();
                });
            }

            var form2 = $('#candiate-form');
            form2.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    positivoPor: {
                        required: true
                    },
                    codigoParticipante: {
                        required: true
                    },
                    fis: {
                        required: true
                    },
                    fechaIngreso: {
                        required: true
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
                    processCandidate();
                }
            });

            function processCandidate(){
                $.post( parametros.saveUrl, form2.serialize(), function( data, status ){
                        registro = JSON.parse(data);
                        if(registro.mensaje !=null){
                            toastr.error(registro.mensaje,"Error",{timeOut: 6000});
                         }else if(registro.tienemaspositivos == true) {
                            toastr.success(parametros.successmessage);
                            window.setTimeout(function () {
                                window.location.href = parametros.detailsOPositivosUrl +"/"+registro.codigo;
                            }, 1100);
                        }else{
                            toastr.success(parametros.successmessage);
                            window.setTimeout(function () {
                                window.location.href = parametros.FormUrl;
                            }, 1100);
                        }
                    },'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error("error:" + errorThrown);
                    });
            }

        }
    }

}();
