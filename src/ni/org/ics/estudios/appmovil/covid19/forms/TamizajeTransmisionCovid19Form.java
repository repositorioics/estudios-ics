package ni.org.ics.estudios.appmovil.covid19.forms;

import android.content.Context;
import ni.org.ics.estudios.appmovil.catalogs.Barrio;
import ni.org.ics.estudios.appmovil.database.EstudiosAdapter;
import ni.org.ics.estudios.appmovil.utils.CatalogosDBConstants;
import ni.org.ics.estudios.appmovil.utils.Constants;
import ni.org.ics.estudios.appmovil.wizard.model.*;

import java.util.List;

/**
 * Created by Miguel Salinas on 02/08/2018.
 * V1.0
 */
public class TamizajeTransmisionCovid19Form extends AbstractWizardModel {

    int index = 0;
    private TamizajeTransmisionCovid19FormLabels labels;
    private EstudiosAdapter estudiosAdapter;

    private String[] fillCatalog(String codigoCatalogo){
        return estudiosAdapter.getSpanishMessageResources(CatalogosDBConstants.catRoot + "='"+codigoCatalogo+"'", CatalogosDBConstants.order);
    }

    private String[] fillBarrios(){
        String[] catalogo;
        List<Barrio> barrios = estudiosAdapter.getBarrios(null, CatalogosDBConstants.nombre);
        catalogo = new String[barrios.size()];
        index = 0;
        for (Barrio message: barrios){
            catalogo[index] = message.getNombre();
            index++;
        }
        return catalogo;
    }

    public TamizajeTransmisionCovid19Form(Context context, String pass) {
        super(context,pass);
    }

    @Override
    protected PageList onNewRootPageList() {
        labels = new TamizajeTransmisionCovid19FormLabels();
        this.estudiosAdapter = new EstudiosAdapter(mContext,mPass,false,false);

        estudiosAdapter.open();
        String[] catSiNo = fillCatalog("CHF_CAT_SINO");
        String[] catVisNoExitosa = fillCatalog("CP_CAT_NV");
        String[] catRelacionFamiliar = fillCatalog("CP_CAT_RFTUTOR");
        String[] catDifTutor = fillCatalog("CP_CAT_DIFTUTOR");
        String[] catTipoTel = fillCatalog("CAT_TIPO_TEL");
        String[] catOperadora = fillCatalog("CAT_OPER_TEL");
        String[] catVerificaTutor = fillCatalog("CP_CAT_VERIFTUTOR");
        String[] catMotivoRechazo = fillCatalog("CPD_CAT_MOTRECHAZO");
        String[] catRazonNoParticipaPersona = fillCatalog("CHF_CAT_NPP");
        String[] catCriteriosInclusionI = fillCatalog("COVID_CAT_CI_INDICE");
        String[] catCriteriosInclusionM = fillCatalog("COVID_CAT_CI_MIEMBRO");
        estudiosAdapter.close();

        Page visExit = new SingleFixedChoicePage(this,labels.getVisExit(), "", Constants.WIZARD, true).setChoices(catSiNo).setRequired(true);
        Page visNoExit = new SingleFixedChoicePage(this,labels.getRazonVisNoExit(), "", Constants.WIZARD, false).setChoices(catVisNoExitosa).setRequired(true);
        Page otraRazonVisitaNoExitosa = new TextPage(this,labels.getOtraRazonVisitaNoExitosa(), labels.getOtraRazonVisitaNoExitosaHint(), Constants.WIZARD, false).setRequired(true);
        Page personaCasa = new TextPage(this,labels.getPersonaCasa(), labels.getPersonaCasaHint(), Constants.WIZARD, false).setRequired(true);
        Page relacionFamPersonaCasa = new SingleFixedChoicePage(this,labels.getRelacionFamPersonaCasa(), labels.getRelacionFamPersonaCasaHint(), Constants.WIZARD, false).setChoices(catRelacionFamiliar).setRequired(true);
        Page otraRelacionPersonaCasa = new TextPage(this,labels.getOtraRelacionPersonaCasa(), "", Constants.WIZARD, false).setRequired(true);
        Page telefonoPersonaCasa = new NumberPage(this,labels.getTelefonoPersonaCasa(), "", Constants.WIZARD, false).setRangeValidation(true, Constants.MINIMO_NUM_CONVENCIONAL, Constants.MAXIMO_NUM_CELULAR).setRequired(false);

        Page aceptaTamizajePersona = new SingleFixedChoicePage(this,labels.getAceptaTamizajePersona(), labels.getAceptaTamizajePersonaHint(), Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page razonNoParticipaPersona = new SingleFixedChoicePage(this,labels.getRazonNoParticipaPersona(), labels.getRazonNoParticipaPersonaHint(), Constants.WIZARD, false).setChoices(catRazonNoParticipaPersona).setRequired(true);
        Page otraRazonNoParticipaPersona = new TextPage(this,labels.getOtraRazonNoParticipaPersona(),labels.getOtraRazonNoParticipaPersonaHint(),Constants.WIZARD, false).setRequired(true);
        Page criteriosInclusionI = new MultipleFixedChoicePage(this,labels.getCriteriosInclusionIndice(), labels.getCriteriosInclusionHint(), Constants.WIZARD, false).setChoices(catCriteriosInclusionI).setRequired(true);
        Page criteriosInclusionM = new MultipleFixedChoicePage(this,labels.getCriteriosInclusionMiembro(), labels.getCriteriosInclusionHint(), Constants.WIZARD, false).setChoices(catCriteriosInclusionM).setRequired(true);
        //Page aceptaParticipar = new SingleFixedChoicePage(this,labels.getAceptaParticipar(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        //Page razonNoAceptaParticipar = new SingleFixedChoicePage(this,labels.getRazonNoAceptaParticipar(), "", Constants.WIZARD, false).setChoices(catRazonNoParticipaPersona).setRequired(true);
        //Page otraRazonNoAceptaParticipar = new TextPage(this,labels.getOtraRazonNoAceptaParticipar(),labels.getOtraRazonNoAceptaParticiparHint(),Constants.WIZARD, false).setRequired(true);
        Page asentimiento = new SingleFixedChoicePage(this,labels.getAsentimiento(), labels.getAsentimientoHint(), Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page aceptaParteA = new SingleFixedChoicePage(this,labels.getAceptaParteA(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page razonNoAceptaParteA = new SingleFixedChoicePage(this,labels.getRazonNoAceptaParteA(), labels.getRazonNoAceptaParteAHint(), Constants.WIZARD, false).setChoices(catMotivoRechazo).setRequired(true);
        Page otraRazonNoAceptaParteA = new TextPage(this,labels.getOtraRazonNoAceptaParteA(),labels.getOtraRazonNoAceptaParteAHint(),Constants.WIZARD, false).setRequired(true);
        Page aceptaParteB = new SingleFixedChoicePage(this,labels.getAceptaParteB(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page aceptaContactoFuturo = new SingleFixedChoicePage(this,labels.getAceptaContactoFuturo(), labels.getAceptaContactoFuturoHint(), Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);

        Page tutor = new LabelPage(this,labels.getTutor(), "", Constants.WIZARD, false).setRequired(false);
        Page mismoTutorSN = new SingleFixedChoicePage(this,labels.getMismoTutorSN(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page nombrept = new TextPage(this,labels.getNombrept(), "", Constants.WIZARD, false).setRequired(true);
        Page nombrept2 = new TextPage(this,labels.getNombrept2(), "", Constants.WIZARD, false).setRequired(false);
        Page apellidopt = new TextPage(this,labels.getApellidopt(), "", Constants.WIZARD, false).setRequired(true);
        Page apellidopt2 = new TextPage(this,labels.getApellidopt2(), "", Constants.WIZARD, false).setRequired(false);
        Page relacionFam = new SingleFixedChoicePage(this,labels.getRelacionFam(), "", Constants.WIZARD, false).setChoices(catRelacionFamiliar).setRequired(true);
        Page otraRelacionFam = new TextPage(this,labels.getOtraRelacionFam(), "", Constants.WIZARD, false).setRequired(true);
        Page motivoDifTutor = new SingleFixedChoicePage(this,labels.getMotivoDifTutor(), "", Constants.WIZARD, false).setChoices(catDifTutor).setRequired(true);
        Page otroMotivoDifTutor = new TextPage(this,labels.getOtroMotivoDifTutor(), "", Constants.WIZARD, false).setRequired(true);

        Page alfabetoTutor = new SingleFixedChoicePage(this,labels.getAlfabetoTutor(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page testigoSN = new SingleFixedChoicePage(this,labels.getTestigoSN(), labels.getTestigoSNHint(), Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page nombretest1 = new TextPage(this,labels.getNombretest1(), "", Constants.WIZARD, false).setRequired(true);
        Page nombretest2 = new TextPage(this,labels.getNombretest2(), "", Constants.WIZARD, false).setRequired(false);
        Page apellidotest1 = new TextPage(this,labels.getApellidotest1(), "", Constants.WIZARD, false).setRequired(true);
        Page apellidotest2 = new TextPage(this,labels.getApellidotest2(), "", Constants.WIZARD, false).setRequired(false);

        Page domicilio = new LabelPage(this,labels.getDomicilio(), "", Constants.WIZARD, false).setRequired(false);
        Page cmDomicilio = new SingleFixedChoicePage(this,labels.getCmDomicilio(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page notaCmDomicilio = new LabelPage(this,labels.getNotaCmDomicilio(), "", Constants.ROJO, false).setRequired(false);

        Page telefono1SN = new SingleFixedChoicePage(this,labels.getTelefono1SN(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page telefonoClasif1 = new SingleFixedChoicePage(this,labels.getTelefonoClasif1(), "", Constants.WIZARD, false).setChoices(catTipoTel).setRequired(true);
        Page telefonoCel1 = new NumberPage(this,labels.getTelefonoCel1(), "", Constants.WIZARD, false).setRequired(true);
        Page telefonoOper1 = new SingleFixedChoicePage(this,labels.getTelefonoOper1(), "", Constants.WIZARD, false).setChoices(catOperadora).setRequired(true);
        Page telefono2SN = new SingleFixedChoicePage(this,labels.getTelefono2SN(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page telefonoClasif2 = new SingleFixedChoicePage(this,labels.getTelefonoClasif2(), "", Constants.WIZARD, false).setChoices(catTipoTel).setRequired(true);
        Page telefonoCel2 = new NumberPage(this,labels.getTelefonoCel2(), "", Constants.WIZARD, false).setRequired(true);
        Page telefonoOper2 = new SingleFixedChoicePage(this,labels.getTelefonoOper2(), "", Constants.WIZARD, false).setChoices(catOperadora).setRequired(true);
        Page telefono3SN = new SingleFixedChoicePage(this,labels.getTelefono3SN(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page telefonoClasif3 = new SingleFixedChoicePage(this,labels.getTelefonoClasif3(), "", Constants.WIZARD, false).setChoices(catTipoTel).setRequired(true);
        Page telefonoCel3 = new NumberPage(this,labels.getTelefonoCel3(), "", Constants.WIZARD, false).setRequired(true);
        Page telefonoOper3 = new SingleFixedChoicePage(this,labels.getTelefonoOper3(), "", Constants.WIZARD, false).setChoices(catOperadora).setRequired(true);

        Page padre = new LabelPage(this,labels.getPadre(), "", Constants.WIZARD, false).setRequired(false);
        Page cambiarPadre = new SingleFixedChoicePage(this,labels.getCambiarPadre(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page nombrepadre = new TextPage(this,labels.getNombrepadre(), "", Constants.WIZARD, false).setRequired(true);
        Page nombrepadre2 = new TextPage(this,labels.getNombrepadre2(), "", Constants.WIZARD, false).setRequired(false);
        Page apellidopadre = new TextPage(this,labels.getApellidopadre(), "", Constants.WIZARD, false).setRequired(true);
        Page apellidopadre2 = new TextPage(this,labels.getApellidopadre2(), "", Constants.WIZARD, false).setRequired(false);
        Page madre = new LabelPage(this,labels.getMadre(), "", Constants.WIZARD, false).setRequired(false);
        Page cambiarMadre = new SingleFixedChoicePage(this,labels.getCambiarMadre(), "", Constants.WIZARD, false).setChoices(catSiNo).setRequired(true);
        Page nombremadre = new TextPage(this,labels.getNombremadre(), "", Constants.WIZARD, false).setRequired(true);
        Page nombremadre2 = new TextPage(this,labels.getNombremadre2(), "", Constants.WIZARD, false).setRequired(false);
        Page apellidomadre = new TextPage(this,labels.getApellidomadre(), "", Constants.WIZARD, false).setRequired(true);
        Page apellidomadre2 = new TextPage(this,labels.getApellidomadre2(), "", Constants.WIZARD, false).setRequired(false);

        Page verifTutor = new MultipleFixedChoicePage(this,labels.getVerifTutor(), "", Constants.WIZARD, false).setChoices(catVerificaTutor).setRequired(true);

        return new PageList(visExit, visNoExit, otraRazonVisitaNoExitosa, personaCasa, relacionFamPersonaCasa, otraRelacionPersonaCasa, telefonoPersonaCasa,
                aceptaTamizajePersona, razonNoParticipaPersona, otraRazonNoParticipaPersona, criteriosInclusionI, criteriosInclusionM, asentimiento,
                aceptaParteA, razonNoAceptaParteA, otraRazonNoAceptaParteA, aceptaParteB, aceptaContactoFuturo,
                tutor, mismoTutorSN, nombrept, nombrept2, apellidopt, apellidopt2, relacionFam, otraRelacionFam, motivoDifTutor, otroMotivoDifTutor, alfabetoTutor, testigoSN, nombretest1, nombretest2, apellidotest1, apellidotest2,
                domicilio, cmDomicilio, notaCmDomicilio, telefono1SN, telefonoClasif1, telefonoCel1, telefonoOper1, telefono2SN, telefonoClasif2, telefonoCel2, telefonoOper2, telefono3SN, telefonoClasif3, telefonoCel3, telefonoOper3,
                padre, cambiarPadre, nombrepadre, nombrepadre2, apellidopadre, apellidopadre2, madre, cambiarMadre, nombremadre, nombremadre2, apellidomadre, apellidomadre2, verifTutor);
    }
}
