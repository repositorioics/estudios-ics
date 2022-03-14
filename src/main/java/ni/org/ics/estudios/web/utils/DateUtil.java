package ni.org.ics.estudios.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Miguel Salinas on 7/3/2017.
 * V1.0
 */
public final class DateUtil {

    /**
    * Convierte un string a Date segun el formato indicado
    * @param strFecha cadena a convertir
    * @param formato formato solicitado
    * @return Fecha
    * @throws java.text.ParseException
    */
    public static Date StringToDate(String strFecha, String formato) throws ParseException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
            return simpleDateFormat.parse(strFecha);
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * Convierte una Date a String, segun el formato indicado
     * @param dtFecha Fecha a convertir
     * @param format formato solicitado
     * @return String
     */
    public static String DateToString(Date dtFecha, String format)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if(dtFecha!=null)
            return simpleDateFormat.format(dtFecha);
        else
            return null;
    }

    /* - inicio de la function fecha */
    public static String obtenerEdad(Date fechan) {
        Calendar today = Calendar.getInstance();
        GregorianCalendar fechaNacimiento = new GregorianCalendar();
        fechaNacimiento.setTime(fechan);
        int age = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int month = (age)*12 + today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);

        if(today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH)){
            month = month - 1;
        }

        if(month == 0) {
            Long tDias = (today.getTimeInMillis() - fechaNacimiento.getTimeInMillis())  / (1000 * 60 * 60 * 24);
            return new StringBuffer().append(tDias).append(" dias").toString();
        }
        else if(age == 0) {
            age = today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
            if(age == 0) {
                age = today.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);
                return new StringBuffer().append(age).append(" dias").toString();
            }else {
                int diaFechaActual = today.get(Calendar.DAY_OF_MONTH);
                int diaFechaNac = fechaNacimiento.get(Calendar.DAY_OF_MONTH);
                if (diaFechaActual < diaFechaNac) {
                    age = age - 1;
                    return new StringBuffer().append(age).append(" meses").toString();
                } else {
                    return new StringBuffer().append(age).append(" meses").toString();
                }

            }
        } else if (month > 0 && month < 12) {
            return new StringBuffer().append(month).append(" meses").toString();

        }else {
            if (today.get(Calendar.MONTH) < fechaNacimiento.get(Calendar.MONTH)) {
                age--;
            } else if (today.get(Calendar.MONTH) == fechaNacimiento.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
            return new StringBuffer().append(age).append(" años").toString();
        }
       /* Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);

        if (age == 0) {
            age = (today.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH));

            if (age == 0) {
                age = (today.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH));
                return new StringBuffer().append(age).append(" dias").toString();
            } else {
                return new StringBuffer().append(age).append(" meses").toString();
            }
        } else {
            if (today.get(Calendar.MONTH) < fechaNacimiento.get(Calendar.MONTH)) {
                age--;
            } else if (today.get(Calendar.MONTH) == fechaNacimiento.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
            return new StringBuffer().append(age).append(" años").toString();
        }*/
    }

    /* ** fin de la funcion fecha*/

    public static int CalcularDiferenciaDiasFechas(Date fecha1, Date fecha2){
        // Crear 2 instancias de Calendar
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha1);
        cal2.setTime(fecha2);
        // conseguir la representacion de la fecha en milisegundos
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();
        // calcular la diferencia en milisengundos
        long diff = milis2 - milis1;
        // calcular la diferencia en dias
        Long diffHours = diff / (24 * 60 * 60 * 1000);
        return diffHours.intValue();
    }

}
