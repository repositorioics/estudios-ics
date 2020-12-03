package ni.org.ics.estudios.appmovil.muestreoanual.forms;

import android.content.res.Resources;
import ni.org.ics.estudios.appmovil.MyIcsApplication;
import ni.org.ics.estudios.appmovil.R;

/**
 * Created by Miguel Salinas on 02/12/2020.
 * V1.0
 */
public class ConsDengueParteEFormLabels {


    private String visExit;
    private String razonVisNoExit;
    private String otraRazonVisitaNoExitosa;
    private String otraRazonVisitaNoExitosaHint;
    private String personaCasa;
    private String personaCasaHint;
    private String relacionFamPersonaCasa;
    private String relacionFamPersonaCasaHint;
    private String otraRelacionPersonaCasa;
    private String telefonoPersonaCasa;

    private String aceptaTamizajePersona;
    private String aceptaTamizajePersonaHint;
    private String razonNoParticipaPersona;
    private String razonNoParticipaPersonaHint;
    private String otraRazonNoParticipaPersona;
    private String otraRazonNoParticipaPersonaHint;

    private String tipoAsentimiento;
    private String tipoAsentimientoHint;
    private String noAsentimiento;
    private String formato712Asentimiento;
    private String formato1317Asentimiento;
    private String aceptaDengueParteE;
    private String aceptaDengueParteEHint;
    private String razonNoAceptaParteE;
    private String razonNoAceptaParteEHint;
    private String otraRazonNoAceptaParteE;
    private String otraRazonNoAceptaParteEHint;

    private String nombrept;
    private String nombrept2;
    private String apellidopt;
    private String apellidopt2;
    private String relacionFam;
    private String otraRelacionFam;
    private String mismoTutorSN;
    private String motivoDifTutor;
    private String otroMotivoDifTutor;
    private String alfabetoTutor;
    private String testigoSN;
    private String testigoSNHint;
    private String nombretest1;
    private String nombretest2;
    private String apellidotest1;
    private String apellidotest2;
    private String tutor;
    private String verifTutor;
    private String noCumpleIncDen;

    public ConsDengueParteEFormLabels() {
        Resources res = MyIcsApplication.getContext().getResources();
        visExit = res.getString(R.string.visExit);
        razonVisNoExit = res.getString(R.string.razonVisNoExit);
        otraRazonVisitaNoExitosa = res.getString(R.string.otraRazonVisitaNoExitosa);
        otraRazonVisitaNoExitosaHint = res.getString(R.string.otraRazonVisitaNoExitosaHint);

        personaCasa = res.getString(R.string.personaCasa);
        personaCasaHint = res.getString(R.string.personaCasaHint);
        relacionFamPersonaCasa = res.getString(R.string.relacionFamPersonaCasa);
        relacionFamPersonaCasaHint = res.getString(R.string.relacionFamPersonaCasaHint);
        otraRelacionPersonaCasa = res.getString(R.string.otraRelacionPersonaCasa);
        telefonoPersonaCasa = res.getString(R.string.telefonoPersonaCasa);

        aceptaTamizajePersona = res.getString(R.string.aceptaTamizajePersona);
        aceptaTamizajePersonaHint = res.getString(R.string.aceptaTamizajePersonaHint);
        razonNoParticipaPersona = res.getString(R.string.razonNoParticipaPersona);
        razonNoParticipaPersonaHint = res.getString(R.string.razonNoParticipaPersonaHint);
        otraRazonNoParticipaPersona = res.getString(R.string.otraRazonNoParticipaPersona);
        otraRazonNoParticipaPersonaHint = res.getString(R.string.otraRazonNoParticipaPersonaHint);

        tipoAsentimiento = res.getString(R.string.tipoAsentimiento);
        tipoAsentimientoHint = res.getString(R.string.asentimientoHint);
        noAsentimiento = res.getString(R.string.noAsentimientoVerbEsc);
        formato712Asentimiento= res.getString(R.string.formato712Asentimiento);
        formato1317Asentimiento= res.getString(R.string.formato1317Asentimiento);

        aceptaDengueParteE = res.getString(R.string.aceptaParteEDengue);
        aceptaDengueParteEHint = res.getString(R.string.aceptaParteEDengueHint);
        razonNoAceptaParteE = res.getString(R.string.razonNoAceptaParteE);
        razonNoAceptaParteEHint = res.getString(R.string.razonNoAceptaParteEHint);
        otraRazonNoAceptaParteE = res.getString(R.string.otraRazonNoAceptaParteE);
        otraRazonNoAceptaParteEHint = res.getString(R.string.otraRazonNoAceptaParteEHint);

        tutor = res.getString(R.string.tutor);
        nombrept = res.getString(R.string.nombrept);
        nombrept2 = res.getString(R.string.nombrept2);
        apellidopt = res.getString(R.string.apellidopt);
        apellidopt2 = res.getString(R.string.apellidopt2);
        relacionFam = res.getString(R.string.relacionFam);
        otraRelacionFam = res.getString(R.string.otraRelacionFam);
        mismoTutorSN = res.getString(R.string.mismoTutorSN);
        motivoDifTutor = res.getString(R.string.motivoDifTutor);
        otroMotivoDifTutor = res.getString(R.string.otroMotivoDifTutor);
        alfabetoTutor = res.getString(R.string.participanteOTutorAlfabetoHint);
        testigoSN = res.getString(R.string.testigoSN);
        testigoSNHint = res.getString(R.string.testigoSNHint);
        nombretest1 = res.getString(R.string.nombretest1);
        nombretest2 = res.getString(R.string.nombretest2);
        apellidotest1 = res.getString(R.string.apellidotest1);
        apellidotest2 = res.getString(R.string.apellidotest2);

        verifTutor = res.getString(R.string.verifTutor);

        noCumpleIncDen = res.getString(R.string.noCumpleIncUO1);

    }

    public String getVisExit() {
        return visExit;
    }

    public String getRazonVisNoExit() {
        return razonVisNoExit;
    }

    public String getOtraRazonVisitaNoExitosa() {
        return otraRazonVisitaNoExitosa;
    }

    public String getOtraRazonVisitaNoExitosaHint() {
        return otraRazonVisitaNoExitosaHint;
    }

    public String getPersonaCasa() {
        return personaCasa;
    }

    public String getPersonaCasaHint() {
        return personaCasaHint;
    }

    public String getRelacionFamPersonaCasa() {
        return relacionFamPersonaCasa;
    }

    public String getRelacionFamPersonaCasaHint() {
        return relacionFamPersonaCasaHint;
    }

    public String getOtraRelacionPersonaCasa() {
        return otraRelacionPersonaCasa;
    }

    public String getTelefonoPersonaCasa() {
        return telefonoPersonaCasa;
    }

    public String getAceptaTamizajePersona() {
        return aceptaTamizajePersona;
    }

    public String getAceptaTamizajePersonaHint() {
        return aceptaTamizajePersonaHint;
    }

    public String getRazonNoParticipaPersona() {
        return razonNoParticipaPersona;
    }

    public String getRazonNoParticipaPersonaHint() {
        return razonNoParticipaPersonaHint;
    }

    public String getOtraRazonNoParticipaPersona() {
        return otraRazonNoParticipaPersona;
    }

    public String getOtraRazonNoParticipaPersonaHint() {
        return otraRazonNoParticipaPersonaHint;
    }

    public String getTipoAsentimiento() {
        return tipoAsentimiento;
    }

    public String getTipoAsentimientoHint() {
        return tipoAsentimientoHint;
    }

    public String getNoAsentimiento() {
        return noAsentimiento;
    }

    public String getAceptaDengueParteE() {
        return aceptaDengueParteE;
    }

    public String getAceptaDengueParteEHint() {
        return aceptaDengueParteEHint;
    }

    public String getRazonNoAceptaParteE() {
        return razonNoAceptaParteE;
    }

    public String getRazonNoAceptaParteEHint() {
        return razonNoAceptaParteEHint;
    }

    public String getOtraRazonNoAceptaParteE() {
        return otraRazonNoAceptaParteE;
    }

    public String getOtraRazonNoAceptaParteEHint() {
        return otraRazonNoAceptaParteEHint;
    }

    public String getNombrept() {
        return nombrept;
    }

    public String getNombrept2() {
        return nombrept2;
    }

    public String getApellidopt() {
        return apellidopt;
    }

    public String getApellidopt2() {
        return apellidopt2;
    }

    public String getRelacionFam() {
        return relacionFam;
    }

    public String getOtraRelacionFam() {
        return otraRelacionFam;
    }

    public String getMismoTutorSN() {
        return mismoTutorSN;
    }

    public String getMotivoDifTutor() {
        return motivoDifTutor;
    }

    public String getOtroMotivoDifTutor() {
        return otroMotivoDifTutor;
    }

    public String getAlfabetoTutor() {
        return alfabetoTutor;
    }

    public String getTestigoSN() {
        return testigoSN;
    }

    public String getTestigoSNHint() {
        return testigoSNHint;
    }

    public String getNombretest1() {
        return nombretest1;
    }

    public String getNombretest2() {
        return nombretest2;
    }

    public String getApellidotest1() {
        return apellidotest1;
    }

    public String getApellidotest2() {
        return apellidotest2;
    }

    public String getTutor() {
        return tutor;
    }

    public String getVerifTutor() {
        return verifTutor;
    }

    public String getNoCumpleIncDen() {
        return noCumpleIncDen;
    }

    public String getFormato712Asentimiento() {
        return formato712Asentimiento;
    }

    public String getFormato1317Asentimiento() {
        return formato1317Asentimiento;
    }
}
