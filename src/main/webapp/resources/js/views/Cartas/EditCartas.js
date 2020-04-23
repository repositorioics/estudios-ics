/**
 * Created by ICS on 10/04/2020.
 */
$(document).ready(function(){
    $('#smartwizard').smartWizard({
        selected: 0,
        theme: 'arrows',
        lang: {  // Language variables
            next: 'Sig',
            previous: 'Prev'
        }
    });
    $("#fechacarta").datepicker({
        autoclose: true,
        format: "dd/mm/yyyy",
        todayBtn:true
    });


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

});
