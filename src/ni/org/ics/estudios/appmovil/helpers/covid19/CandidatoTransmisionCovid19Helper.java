package ni.org.ics.estudios.appmovil.helpers.covid19;

import android.content.ContentValues;
import android.database.Cursor;
import net.sqlcipher.database.SQLiteStatement;
import ni.org.ics.estudios.appmovil.domain.covid19.CandidatoTransmisionCovid19;
import ni.org.ics.estudios.appmovil.utils.Covid19DBConstants;
import ni.org.ics.estudios.appmovil.utils.MainDBConstants;

import java.util.Date;

public class CandidatoTransmisionCovid19Helper {
    public static ContentValues crearCandidatoTransmisionCovid19ContentValues(CandidatoTransmisionCovid19 candidato){
        ContentValues cv = new ContentValues();
        cv.put(Covid19DBConstants.codigo, candidato.getCodigo());
        cv.put(Covid19DBConstants.participante, candidato.getParticipante().getCodigo());
        cv.put(Covid19DBConstants.estActuales, candidato.getEstActuales());
        cv.put(Covid19DBConstants.consentimiento, candidato.getConsentimiento());
        cv.put(Covid19DBConstants.positivoPor, candidato.getPositivoPor());
        cv.put(Covid19DBConstants.casaCHF, candidato.getCasaCHF());
        if (candidato.getFis() != null) cv.put(Covid19DBConstants.fis, candidato.getFis().getTime());
        if (candidato.getFif() != null) cv.put(Covid19DBConstants.fif, candidato.getFif().getTime());
        if (candidato.getFechaIngreso() != null) cv.put(Covid19DBConstants.fechaIngreso, candidato.getFechaIngreso().getTime());

        if (candidato.getRecordDate() != null) cv.put(MainDBConstants.recordDate, candidato.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, candidato.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(candidato.getPasive()));
        cv.put(MainDBConstants.estado, String.valueOf(candidato.getEstado()));
        cv.put(MainDBConstants.deviceId, candidato.getDeviceid());
        return cv;
    }

    public static CandidatoTransmisionCovid19 crearCandidatoTransmisionCovid19(Cursor cursor){
        CandidatoTransmisionCovid19 mCandidato = new CandidatoTransmisionCovid19();

        mCandidato.setCodigo(cursor.getString(cursor.getColumnIndex(Covid19DBConstants.codigo)));
        mCandidato.setParticipante(null);
        mCandidato.setEstActuales(cursor.getString(cursor.getColumnIndex(Covid19DBConstants.estActuales)));
        mCandidato.setConsentimiento(cursor.getString(cursor.getColumnIndex(Covid19DBConstants.consentimiento)));
        mCandidato.setPositivoPor(cursor.getString(cursor.getColumnIndex(Covid19DBConstants.positivoPor)));
        mCandidato.setCasaCHF(cursor.getString(cursor.getColumnIndex(Covid19DBConstants.casaCHF)));
        if(cursor.getLong(cursor.getColumnIndex(Covid19DBConstants.fis))>0) mCandidato.setFis(new Date(cursor.getLong(cursor.getColumnIndex(Covid19DBConstants.fis))));
        if(cursor.getLong(cursor.getColumnIndex(Covid19DBConstants.fif))>0) mCandidato.setFif(new Date(cursor.getLong(cursor.getColumnIndex(Covid19DBConstants.fif))));
        if(cursor.getLong(cursor.getColumnIndex(Covid19DBConstants.fechaIngreso))>0) mCandidato.setFechaIngreso(new Date(cursor.getLong(cursor.getColumnIndex(Covid19DBConstants.fechaIngreso))));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) mCandidato.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        mCandidato.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        mCandidato.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        mCandidato.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.estado)).charAt(0));
        mCandidato.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.deviceId)));
        return mCandidato;
    }

    public static void fillCandidatoTransmisionCovid19Statement(SQLiteStatement stat, CandidatoTransmisionCovid19 candidato){
        if (candidato.getCodigo() != null) stat.bindString(1, candidato.getCodigo());
        if (candidato.getParticipante() != null) stat.bindLong(2, candidato.getParticipante().getCodigo());
        if (candidato.getCasaCHF() != null) stat.bindString(3, candidato.getCasaCHF());
        if (candidato.getFis() != null) stat.bindLong(4, candidato.getFis().getTime());
        if (candidato.getFif() != null) stat.bindLong(5, candidato.getFif().getTime());
        if (candidato.getPositivoPor() != null) stat.bindString(6, candidato.getPositivoPor());
        if (candidato.getConsentimiento() != null) stat.bindString(7, candidato.getConsentimiento());
        if (candidato.getEstActuales() != null) stat.bindString(8, candidato.getEstActuales());
        if (candidato.getFechaIngreso() != null) stat.bindLong(9, candidato.getFechaIngreso().getTime());

        if (candidato.getRecordDate() != null) stat.bindLong(10, candidato.getRecordDate().getTime());
        if (candidato.getRecordUser() != null) stat.bindString(11, candidato.getRecordUser());
        stat.bindString(12, String.valueOf(candidato.getPasive()));
        if (candidato.getDeviceid() != null) stat.bindString(13, candidato.getDeviceid());
        stat.bindString(14, String.valueOf(candidato.getEstado()));
    }
}
