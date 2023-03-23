var ProcessCaseUO1 = function () {
	
    return {
        //main function to initiate the module
        init: function (parametros) {
            /*tablas Muestras y chf_muestra*/
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
            /* Fin Table */

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

            $("#numMuestra").text("0/0");
            $("#numCHFMuestra").text("0/0");
            function search() {
                $("#numDiasCHFMuestra").text('0');
                $("#numDiasMuestra").text('0');
                $.getJSON( parametros.searchUrl , form1.serialize() , function( data )   {
                        console.log(data);
                        if (data.mensaje != undefined) {
                            toastr.error(data.mensaje,"Error",{timeOut: 0});
                            $("#codigoCasa").val("");
                            $("#codigoParticipante").val("");
                            $("#alertas").hide();
                            $("#msgAlert").hide();
                            $("#tblMuestra").DataTable().clear().draw();
                            $("#tblChfMuestra").DataTable().clear().draw();
                            $("#numMuestra").text("0/0");
                            $("#numCHFMuestra").text("0/0");
                        }
                        else {
                            $("#codigoCasa").val(data.casaPediatrica);
                            $("#codigoParticipante").val(data.codigo);
                            if(data.alertas != '' ){
                                $("#alertas").html(data.alertas).show();
                                $("#msgAlert").show();
                            }else{
                                $("#alertas").html('').show();
                                $("#alertas").hide();
                                $("#msgAlert").hide();
                            }

                            var contadorMuestraByCodigo = 0;
                            var contadorChf_MuestraByCodigo = 0;

                            if(data.muestras.length ==0  ){
                                $("#numMuestra").text("0/0");
                                $("#tblMuestra").DataTable().clear().draw();
                            }else{
                                $("#numDiasMuestra").text(data.intervalo);
                                $.each(data.muestras, function(index, element){
                                    if(element.codigoParticipante === data.codigo){
                                        contadorMuestraByCodigo++;
                                    }
                                    $("#numMuestra").text(contadorMuestraByCodigo+"/"+data.muestras.length);
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

                            }

                            if(data.chf_muestras.length ==0){
                                $("#numCHFMuestra").text("0/0");
                                $("#tblChfMuestra").DataTable().clear().draw();
                            }else{
                                $("#numDiasCHFMuestra").text(data.intervalo);
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
                                        case "6":
                                            proposito ="Compartido Positivo UO1 y positivo familia **En Desuso";
                                            break;
                                        case "7":
                                            proposito ="Covid Flu y UO1";
                                            break;
                                        case "8":
                                            proposito ="Seguimiento Transmisión Covid";
                                            break;
                                        case "9":
                                            proposito ="Muestra adicional CHF Covid19";
                                            break;
                                        case "10":
                                            proposito ="Muestra adicional Dengue Parte E";
                                            break;
                                        case "11":
                                            proposito ="FLU positivos para influenza CEIRS";
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
                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                    toastr.error( "error:" + errorThrown);
                        $("#tblMuestra").DataTable().clear().draw();
                        $("#tblChfMuestra").DataTable().clear().draw();
                        $("#alertas").hide();
                        $("#msgAlert").hide();
                        $("#numMuestra").text("0/0");
                        $("#numCHFMuestra").text("0/0");
                });
            }

            var form2 = $('#enterform');
            form2.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    codigoCasa: {
                        required: true
                    },
                    fechaInicio: {
                        required: true
                    },
                    codigoParticipante: {
                        required: true
                    },
                    fis: {required: function () {
                        return $('#fif').val().length <= 0;
                    }},
                    fif: {required: function () {
                        return $('#fis').val().length <= 0;
                    }},
                    positivoPor: {
                        required: true
                    }
                },
                messages: {
                    fis: {
                        required: "FIS o FIF es requerida"
                    },
                    fif: {
                        required: "FIS o FIF es requerida"
                    }
                },
                errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    console.log(element.prop( 'type' ));
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } if ( element.prop( 'type' ) === 'select-one' ) {
                        error.insertAfter( element );
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
                    saveCaseUO1();
                }
            });

            function saveCaseUO1()
        	{
        	    $.post( parametros.saveUrl
        	            , form2.serialize()
        	            , function( data )
        	            {
        	    			registro = JSON.parse(data);
        	    			if (registro.codigoCasoParticipante === undefined) {
        						toastr.error(registro.mensaje,"Error",{timeOut: 0});
        					}
        					else {
                                toastr.success(parametros.successmessage);

                                window.setTimeout(function () {
                                    window.location.href = parametros.listaUrl;
                                }, 1500);
                            }
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error("error:" + errorThrown);
        		  		});
        	}

        }
    };

}();