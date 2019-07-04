var ReportVisit = function () {
	
    return {
        //main function to initiate the module
        init: function (parametros) {
            var table = $('#lista_casos').DataTable({
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                }
            });
            var laddaButton = Ladda.create( document.querySelector( '.btn-ladda' ) );
            var tt = new $.fn.dataTable.TableTools( table, {
                "sSwfPath": parametros.sSwfPath,
                "sRowSelect": "single",
                "aButtons": [
                    {
                        "sExtends":    "collection",
                        "sButtonText": parametros.exportar,
                        "aButtons": [
                            {
                                "sExtends": "csv",
                                "sTitle": "VisitasSeguimiento",
                                "oSelectorOpts": { filter: 'applied', order: 'current' },
                                "mColumns": [ 0, 1, 2, 3, 4 ]
                            },
                            {
                                "sExtends": "pdf",
                                "sTitle": "VisitasSeguimiento",
                                "oSelectorOpts": { filter: 'applied', order: 'current' },
                                "mColumns": [ 0, 1, 2, 3, 4 ],
                                "sPdfOrientation": "landscape"
                            }
                        ]
                    }
                ]
            } );

            $( tt.fnContainer() ).insertBefore('div.table-toolbar');

            var form1 = $('#search-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    codigoParticipante: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        maxlength: 5
                    },
                    casa: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        maxlength: 4
                    },
                    fechaFin: {required: function () {
                        return $('#fechaInicio').val().length > 0;
                    }},
                    fechaInicio: {required: function () {
                        return $('#fechaFin').val().length > 0;
                    }}
                },
                errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback col-md-4' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        error.insertAfter( element ); //cuando no es input-group
                        //error.insertAfter(element.parent('.input-group'));
                    }
                },
                highlight: function ( element, errorClass, validClass ) {
                    $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                    //$( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );//para toda la fila
                    $( element ).parents( '.input-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );//para solo el elemento
                },
                unhighlight: function (element, errorClass, validClass) {
                    $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                    //$( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' ); //para toda la fila
                    $( element ).parents( '.input-group' ).addClass( 'has-success' ).removeClass( 'has-danger' ); //para solo el elemento
                },
                submitHandler: function (form) {
                    search();
                }
            });

            function startLoadingButtons(){
                // Start loading
                laddaButton.start();
            }

            function stopLoadingButtons(){
                // Stop loading
                laddaButton.stop();
            }

            function search()
            {
                startLoadingButtons();
                $.getJSON(parametros.searchUrl
                    , form1.serialize()
                    , function (data) {
                        table.clear().draw();
                        var len = Object.keys(data).length;
                        if (data.mensaje === undefined) {
                            for (var i = 0; i < len; i++) {
                                table.row.add(
                                    [data[i][1], getFormattedDate(new Date(data[i][9]), false), data[i][2], data[i][3], getFormattedDate(new Date(data[i][4]), true), data[i][8], data[i][5], data[i][6]])
                                    .draw();
                            }
                        } else {
                            toastr.warning(data.mensaje);
                        }
                        stopLoadingButtons();
                    }
                ).fail(
                    function (XMLHttpRequest, textStatus, errorThrown) {
                        stopLoadingButtons();
                        toastr.error(errorThrown,"Error",{timeOut: 0});
                    }
                );
            }

            function getFormattedDate(date, time) {
                var year = date.getFullYear();

                var month = (1 + date.getMonth()).toString();
                month = month.length > 1 ? month : '0' + month;

                var day = date.getDate().toString();
                day = day.length > 1 ? day : '0' + day;
                if (time==false)
                    return day + '/' + month + '/' +  year;
                else{
                    var h = addZero(date.getHours());
                    var m = addZero(date.getMinutes());
                    var s = addZero(date.getSeconds());
                    return day + '/' + month + '/' +  year + " "+ h + ":" + m + ":" + s;
                }
            }

            function addZero(i) {
                if (i < 10) {
                    i = "0" + i;
                }
                return i;
            }
        }
    };

}();