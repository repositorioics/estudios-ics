package ni.org.ics.estudios.web.utils;

import java.util.Random;

public final class StringUtil {

    public static String getCadenaAlfanumAleatoria (int longitud, boolean usarSeparador){
        StringBuilder  cadenaAleatoria = new StringBuilder();
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        int longitudSeparador = 0;
        while ( i < longitud){
            if (usarSeparador && longitudSeparador==4){
                cadenaAleatoria.append("-");
                i++;
                longitudSeparador=0;
            }else {
                char c = (char) r.nextInt(255);
                if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    cadenaAleatoria.append(c);
                    i++;
                    longitudSeparador++;
                }
            }
        }
        return cadenaAleatoria.toString();
    }
}
