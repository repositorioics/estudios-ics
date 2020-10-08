/**
 * Created by ICS on 07/10/2020.
 */
//region Valida Fecha
function validarFecha(fecha) {
    try {
        var fecha = fecha.split("/");
        var dia = fecha[0];
        var mes = fecha[1];
        var ano = fecha[2];
        var estado = true;

        if ((dia.length == 2) && (mes.length == 2) && (ano.length == 4)) {
            switch (parseInt(mes)) {
                case 1:
                    dmax = 31;
                    break;
                case 2:
                    if (ano % 4 == 0) dmax = 29; else dmax = 28;
                    break;
                case 3:
                    dmax = 31;
                    break;
                case 4:
                    dmax = 30;
                    break;
                case 5:
                    dmax = 31;
                    break;
                case 6:
                    dmax = 30;
                    break;
                case 7:
                    dmax = 31;
                    break;
                case 8:
                    dmax = 31;
                    break;
                case 9:
                    dmax = 30;
                    break;
                case 10:
                    dmax = 31;
                    break;
                case 11:
                    dmax = 30;
                    break;
                case 12:
                    dmax = 31;
                    break;
            }
            dmax != "" ? dmax : dmax = -1;
            if ((dia >= 1) && (dia <= dmax) && (mes >= 1) && (mes <= 12)) {
                for (var i = 0; i < fecha[0].length; i++) {
                    diaC = fecha[0].charAt(i).charCodeAt(0);
                    (!((diaC > 47) && (diaC < 58))) ? estado = false : '';
                    mesC = fecha[1].charAt(i).charCodeAt(0);
                    (!((mesC > 47) && (mesC < 58))) ? estado = false : '';
                }
            }
            for (var i = 0; i < fecha[2].length; i++) {
                anoC = fecha[2].charAt(i).charCodeAt(0);
                (!((anoC > 47) && (anoC < 58))) ? estado = false : '';
            }
        } else estado = false;
        return estado;
    } catch (err) {
        $.notify("Error fechas", 'error');
    }
}

//endregion


//region Edad

function daysMonthsYearsInDates(dateStart, dateEnd) {
    var daysTotals = numDayInDates(dateStart, dateEnd);
    var daysCal = 0;
    var cantDays = 0;
    var cantMonths = 0;
    var cantYears = 0;
    while (daysCal < daysTotals) {
        var arrayDateStart = dateStart.split('/');
        var daysOfMonth = daysInMonth(arrayDateStart[1], arrayDateStart[2]);
        daysCal = daysCal + daysOfMonth;
        if (daysCal <= daysTotals) {
            cantMonths++;
            if (cantMonths == 12) {
                cantYears++;
                cantMonths = cantMonths - 12;
            }
        } else {
            cantDays = Math.abs(numDayInDates(dateStart, dateEnd));
        }
        dateStart = sumDaysToDate(daysOfMonth, dateStart);
    }

    var msg = '';
    if (cantYears > 0)
        msg = cantYears + ' años';
    if (cantMonths > 0) {
        if (cantYears > 0) msg += ' y ';
        msg += cantMonths + ' meses ';
    }

    if (cantDays > 0) {
        if (cantMonths > 0) msg += ' y ';
        msg += cantDays + ' días';
    }
    if(msg == null || msg == ''){
        msg='0';
    }
    $("#edad").val(msg);
    return msg;
}

function numDayInDates(dateStart, dateEnd) {
    var arrayDateStart = dateStart.split('/');
    var arrayDateEnd = dateEnd.split('/');
    var msegDateStart = Date.UTC(arrayDateStart[2], arrayDateStart[1] - 1, arrayDateStart[0]);
    var msegDateEnd = Date.UTC(arrayDateEnd[2], arrayDateEnd[1] - 1, arrayDateEnd[0]);
    var diff = msegDateEnd - msegDateStart;
    var days = Math.floor(diff / (1000 * 60 * 60 * 24));
    return days;
}

function daysInMonth(month, year) {
    return new Date(year, month, 0).getDate();
}

function sumDaysToDate(numDays, date) {
    var arrayDate = date.split('/');
    var newDate = new Date(arrayDate[2] + '/' + arrayDate[1] + '/' + arrayDate[0]);
    newDate.setDate(newDate.getDate() + parseInt(numDays));
    return newDate.getDate() + '/' + (newDate.getMonth() + 1) + '/' + newDate.getFullYear();
}

//endregion


