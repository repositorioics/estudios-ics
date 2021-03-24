package ni.org.ics.estudios.appmovil.helpers.influenzauo1;

import android.content.ContentValues;
import android.database.Cursor;
import net.sqlcipher.database.SQLiteStatement;
import ni.org.ics.estudios.appmovil.domain.cohortefamilia.casos.VisitaSeguimientoCasoSintomas;
import ni.org.ics.estudios.appmovil.domain.influenzauo1.SintomasVisitaCasoUO1;
import ni.org.ics.estudios.appmovil.utils.InfluenzaUO1DBConstants;
import ni.org.ics.estudios.appmovil.utils.InfluenzaUO1DBConstants;
import ni.org.ics.estudios.appmovil.utils.MainDBConstants;

import java.util.Date;

/**
 * Created by William Aviles 6/9/2017.
 * V1.0
 */
public class SintomasVisitaCasoUO1Helper {

    public static ContentValues crearSintomasVisitaCasoUO1ContentValues(SintomasVisitaCasoUO1 sintomaCaso){
        ContentValues cv = new ContentValues();
        cv.put(InfluenzaUO1DBConstants.codigoSintoma, sintomaCaso.getCodigoSintoma());
        cv.put(InfluenzaUO1DBConstants.codigoCasoVisita, sintomaCaso.getCodigoCasoVisita().getCodigoCasoVisita());
        if (sintomaCaso.getFechaSintomas() != null) cv.put(InfluenzaUO1DBConstants.fechaSintomas, sintomaCaso.getFechaSintomas().getTime());
        cv.put(InfluenzaUO1DBConstants.dia, sintomaCaso.getDia());
        cv.put(InfluenzaUO1DBConstants.consultaInicial, sintomaCaso.getConsultaInicial());
        cv.put(InfluenzaUO1DBConstants.fiebre, sintomaCaso.getFiebre());
        cv.put(InfluenzaUO1DBConstants.fiebreIntensidad, sintomaCaso.getFiebreIntensidad());
        cv.put(InfluenzaUO1DBConstants.tos, sintomaCaso.getTos());
        cv.put(InfluenzaUO1DBConstants.tosIntensidad, sintomaCaso.getTosIntensidad());
        cv.put(InfluenzaUO1DBConstants.secrecionNasal, sintomaCaso.getSecrecionNasal());
        cv.put(InfluenzaUO1DBConstants.secrecionNasalIntensidad, sintomaCaso.getSecrecionNasalIntensidad());
        cv.put(InfluenzaUO1DBConstants.dolorGarganta, sintomaCaso.getDolorGarganta());
        cv.put(InfluenzaUO1DBConstants.dolorGargantaIntensidad, sintomaCaso.getDolorGargantaIntensidad());
        cv.put(InfluenzaUO1DBConstants.congestionNasal, sintomaCaso.getCongestionNasal());
        cv.put(InfluenzaUO1DBConstants.dolorCabeza, sintomaCaso.getDolorCabeza());
        cv.put(InfluenzaUO1DBConstants.dolorCabezaIntensidad, sintomaCaso.getDolorCabezaIntensidad());
        cv.put(InfluenzaUO1DBConstants.faltaApetito, sintomaCaso.getFaltaApetito());
        cv.put(InfluenzaUO1DBConstants.dolorMuscular, sintomaCaso.getDolorMuscular());
        cv.put(InfluenzaUO1DBConstants.dolorMuscularIntensidad, sintomaCaso.getDolorMuscularIntensidad());
        cv.put(InfluenzaUO1DBConstants.dolorArticular, sintomaCaso.getDolorArticular());
        cv.put(InfluenzaUO1DBConstants.dolorArticularIntensidad, sintomaCaso.getDolorArticularIntensidad());
        cv.put(InfluenzaUO1DBConstants.dolorOido, sintomaCaso.getDolorOido());
        cv.put(InfluenzaUO1DBConstants.respiracionRapida, sintomaCaso.getRespiracionRapida());
        cv.put(InfluenzaUO1DBConstants.dificultadRespiratoria, sintomaCaso.getDificultadRespiratoria());
        cv.put(InfluenzaUO1DBConstants.faltaEscuelta, sintomaCaso.getFaltaEscuelta());
        cv.put(InfluenzaUO1DBConstants.quedoCama, sintomaCaso.getQuedoCama());

        if (sintomaCaso.getRecordDate() != null) cv.put(MainDBConstants.recordDate, sintomaCaso.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, sintomaCaso.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(sintomaCaso.getPasive()));
        cv.put(MainDBConstants.estado, String.valueOf(sintomaCaso.getEstado()));
        cv.put(MainDBConstants.deviceId, sintomaCaso.getDeviceid());
        return cv;
    }

    public static SintomasVisitaCasoUO1 crearSintomasVisitaCasoUO1(Cursor cursor){
        SintomasVisitaCasoUO1 mVisitaSeguimientoCasoSintomas = new SintomasVisitaCasoUO1();
        mVisitaSeguimientoCasoSintomas.setCodigoSintoma(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.codigoSintoma)));
        mVisitaSeguimientoCasoSintomas.setCodigoCasoVisita(null);
        if(cursor.getLong(cursor.getColumnIndex(InfluenzaUO1DBConstants.fechaSintomas))>0) mVisitaSeguimientoCasoSintomas.setFechaSintomas(new Date(cursor.getLong(cursor.getColumnIndex(InfluenzaUO1DBConstants.fechaSintomas))));
        mVisitaSeguimientoCasoSintomas.setDia(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dia)));
        mVisitaSeguimientoCasoSintomas.setConsultaInicial(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.consultaInicial)));
        mVisitaSeguimientoCasoSintomas.setFiebre(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.fiebre)));
        mVisitaSeguimientoCasoSintomas.setFiebreIntensidad(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.fiebreIntensidad)));
        mVisitaSeguimientoCasoSintomas.setTos(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.tos)));
        mVisitaSeguimientoCasoSintomas.setTosIntensidad(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.tosIntensidad)));
        mVisitaSeguimientoCasoSintomas.setSecrecionNasal(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.secrecionNasal)));
        mVisitaSeguimientoCasoSintomas.setSecrecionNasalIntensidad(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.secrecionNasalIntensidad)));
        mVisitaSeguimientoCasoSintomas.setDolorGarganta(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorGarganta)));
        mVisitaSeguimientoCasoSintomas.setDolorGargantaIntensidad(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorGargantaIntensidad)));
        mVisitaSeguimientoCasoSintomas.setCongestionNasal(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.congestionNasal)));
        mVisitaSeguimientoCasoSintomas.setDolorCabeza(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorCabeza)));
        mVisitaSeguimientoCasoSintomas.setDolorCabezaIntensidad(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorCabezaIntensidad)));
        mVisitaSeguimientoCasoSintomas.setFaltaApetito(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.faltaApetito)));
        mVisitaSeguimientoCasoSintomas.setDolorMuscular(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorMuscular)));
        mVisitaSeguimientoCasoSintomas.setDolorMuscularIntensidad(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorMuscularIntensidad)));
        mVisitaSeguimientoCasoSintomas.setDolorArticular(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorArticular)));
        mVisitaSeguimientoCasoSintomas.setDolorArticularIntensidad(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorArticularIntensidad)));
        mVisitaSeguimientoCasoSintomas.setDolorOido(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dolorOido)));
        mVisitaSeguimientoCasoSintomas.setRespiracionRapida(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.respiracionRapida)));
        mVisitaSeguimientoCasoSintomas.setDificultadRespiratoria(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.dificultadRespiratoria)));
        mVisitaSeguimientoCasoSintomas.setQuedoCama(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.quedoCama)));
        mVisitaSeguimientoCasoSintomas.setFaltaEscuelta(cursor.getString(cursor.getColumnIndex(InfluenzaUO1DBConstants.faltaEscuelta)));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) mVisitaSeguimientoCasoSintomas.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        mVisitaSeguimientoCasoSintomas.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        mVisitaSeguimientoCasoSintomas.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        mVisitaSeguimientoCasoSintomas.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.estado)).charAt(0));
        mVisitaSeguimientoCasoSintomas.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.deviceId)));
        return mVisitaSeguimientoCasoSintomas;
    }

    public static void fillSintomasVisitaCasoUO1Statement(SQLiteStatement stat, SintomasVisitaCasoUO1 sintomaCaso){
        stat.bindString(1, sintomaCaso.getCodigoSintoma());
        stat.bindString(2, sintomaCaso.getCodigoCasoVisita().getCodigoCasoVisita());
        if (sintomaCaso.getFechaSintomas() != null) stat.bindLong(3, sintomaCaso.getFechaSintomas().getTime());
        if (sintomaCaso.getDia() != null) stat.bindString(4, sintomaCaso.getDia());
        if (sintomaCaso.getConsultaInicial() != null) stat.bindString(5, sintomaCaso.getConsultaInicial());
        if (sintomaCaso.getFiebre() != null) stat.bindString(6, sintomaCaso.getFiebre());
        if (sintomaCaso.getFiebreIntensidad() != null) stat.bindString(7, sintomaCaso.getFiebreIntensidad());
        if (sintomaCaso.getTos() != null) stat.bindString(8, sintomaCaso.getTos());
        if (sintomaCaso.getTosIntensidad() != null) stat.bindString(9, sintomaCaso.getTosIntensidad());
        if (sintomaCaso.getSecrecionNasal() != null) stat.bindString(10, sintomaCaso.getSecrecionNasal());
        if (sintomaCaso.getSecrecionNasalIntensidad() != null) stat.bindString(11, sintomaCaso.getSecrecionNasalIntensidad());
        if (sintomaCaso.getDolorGarganta() != null) stat.bindString(12, sintomaCaso.getDolorGarganta());
        if (sintomaCaso.getDolorGargantaIntensidad() != null) stat.bindString(13, sintomaCaso.getDolorGargantaIntensidad());
        if (sintomaCaso.getCongestionNasal() != null) stat.bindString(14, sintomaCaso.getCongestionNasal());
        if (sintomaCaso.getDolorCabeza() != null) stat.bindString(15, sintomaCaso.getDolorCabeza());
        if (sintomaCaso.getDolorCabezaIntensidad() != null) stat.bindString(16, sintomaCaso.getDolorCabezaIntensidad());
        if (sintomaCaso.getFaltaApetito() != null) stat.bindString(17, sintomaCaso.getFaltaApetito());
        if (sintomaCaso.getDolorMuscular() != null) stat.bindString(18, sintomaCaso.getDolorMuscular());
        if (sintomaCaso.getDolorMuscularIntensidad() != null) stat.bindString(19, sintomaCaso.getDolorMuscularIntensidad());
        if (sintomaCaso.getDolorArticular() != null) stat.bindString(20, sintomaCaso.getDolorArticular());
        if (sintomaCaso.getDolorArticularIntensidad() != null) stat.bindString(21, sintomaCaso.getDolorArticularIntensidad());
        if (sintomaCaso.getDolorOido() != null) stat.bindString(22, sintomaCaso.getDolorOido());
        if (sintomaCaso.getRespiracionRapida() != null) stat.bindString(23, sintomaCaso.getRespiracionRapida());
        if (sintomaCaso.getDificultadRespiratoria() != null) stat.bindString(24, sintomaCaso.getDificultadRespiratoria());
        if (sintomaCaso.getFaltaEscuelta() != null) stat.bindString(25, sintomaCaso.getFaltaEscuelta());
        if (sintomaCaso.getQuedoCama() != null) stat.bindString(26, sintomaCaso.getQuedoCama());

        if (sintomaCaso.getRecordDate() != null) stat.bindLong(27, sintomaCaso.getRecordDate().getTime());
        if (sintomaCaso.getRecordUser() != null) stat.bindString(28, sintomaCaso.getRecordUser());
        stat.bindString(29, String.valueOf(sintomaCaso.getPasive()));
        if (sintomaCaso.getDeviceid() != null) stat.bindString(30, sintomaCaso.getDeviceid());
        stat.bindString(31, String.valueOf(sintomaCaso.getEstado()));
    }
}
