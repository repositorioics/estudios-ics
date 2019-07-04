$(document).ready(function(){
/*    $.validator.addMethod('customphone', function (value, element) {
        return this.optional(element) || /^\d{8}(?:[-\s]\d{4})?$/.test(value);
    }, "Por favor entre un número de teléfono válido");*/

    $("#fie").datepicker({
        autoclose: true,
        format: "dd/mm/yyyy",
        todayBtn:true,
        endDate: '-0d'
    }).on("changeDate", function(e){
        var f1 = new Date();
        var f2= $("#fie").val();
        $("#diasenf").val(restaFechas(f1,f2));
    });
    // Función para calcular los días transcurridos entre dos fechas
    restaFechas = function(f1,f2){
        var datestring = ("0" + f1.getDate()).slice(-2) + "/" + ("0"+(f1.getMonth()+1)).slice(-2) + "/" + f1.getFullYear();
        var aFecha1 = datestring.split("/");
        var aFecha2 = f2.split("/");
        var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]);
        var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]);
        var dif =  fFecha1 - fFecha2;
        var dias = Math.floor(dif / (1000 * 60 * 60 * 24));
        return dias;
    };
    //--------------------------------------------------------------------
    $('#sistolica').keyup(function(){
        calculoPresion();
    });
    $('#diastolica').keyup(function(){
        calculoPresion();
    });
    function calculoPresion() {
        var sistolica = document.getElementById("sistolica").value;
        var diastolica = document.getElementById("diastolica").value;
        var pam = ((parseFloat(diastolica) * 2) + parseFloat(sistolica)) / 3;
        document.getElementById('pam').value = Math.round(pam);
        var pp = parseFloat(sistolica) - parseFloat(diastolica);
        document.getElementById('pp').value = Math.round(pp);
    }
    //--------------------------------------------------------------------
    $("#peso").keyup(function(){
        calculoimc();
        AsuperCorporal();
    });
    $("#talla").keyup(function(){
        calculoimc();
        AsuperCorporal();
    });
    function calculoimc() {
        var altura = document.getElementById("talla").value;
        altura = altura.toString().replace(',', '.');
        var alturaMetro = altura / 100;

        var peso = document.getElementById("peso").value;

        if (altura == "") {
            document.getElementById("errorIMC").innerHTML = "Por favor, introduce tu altura.";
        } else if (altura < 0) {
            document.getElementById("errorIMC").innerHTML = "La altura que introduzca debe ser positiva.";
        } else if (altura < 20) {
            document.getElementById("errorIMC").innerHTML = "Ha introducido la altura en metros. Por favor, multipliquela por 100 para introducirla en centimetros.";
        } else {
            document.getElementById("errorIMC").innerHTML = "";
            if (peso == "") {
                document.getElementById("errorIMC").innerHTML = "Por favor, introduce tu peso.";
            } else if (peso < 0) {
                document.getElementById("errorIMC").innerHTML = "El peso que introduzca debe ser positivo.";
            } else {
                document.getElementById("errorIMC").innerHTML = "";

                /*CALCULO IMC*/
                var alturaCuadrado = alturaMetro * alturaMetro;
                var imc = peso / alturaCuadrado;
                document.getElementById("imc").value = Math.round(imc * 100) / 100;
                /*CALCULO DESCRIPCION IMC*/

                if (imc < 16) {
                    document.getElementById("IMCdetallado").value = "Delgadez Severa";
                } else if (imc < 17) {
                    document.getElementById("IMCdetallado").value = "Delgadez Moderada";
                } else if (imc < 18.5) {
                    document.getElementById("IMCdetallado").value = "Delgadez Aceptable";
                } else if (imc < 25) {
                    document.getElementById("IMCdetallado").value = "Peso Normal";
                } else if (imc < 30) {
                    document.getElementById("IMCdetallado").value = "Sobrepeso";
                } else if (imc < 35) {
                    document.getElementById("IMCdetallado").value = "Obeso: Tipo I";
                } else if (imc < 40) {
                    document.getElementById("IMCdetallado").value = "Obeso: Tipo II";
                } else if (imc >= 40) {
                    document.getElementById("IMCdetallado").value = "Obeso: Tipo III";
                }
            }
        }
    } /* fin function Calculo Imc*/

    function AsuperCorporal(){
        var talla = document.getElementById("talla").value;
        var peso = document.getElementById("peso").value;
        var areasc =Math.sqrt((peso * talla) / 3600);
        document.getElementById("asc").value = Math.round(areasc);
    }
    //toastr.info("Actualizar información!");
})
