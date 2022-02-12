package ni.org.ics.estudios.appmovil.influenzauo1.activities.enterdata;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import ni.org.ics.estudios.appmovil.MyIcsApplication;
import ni.org.ics.estudios.appmovil.R;
import ni.org.ics.estudios.appmovil.catalogs.MessageResource;
import ni.org.ics.estudios.appmovil.cohortefamilia.activities.ListaMuestrasActivity;
import ni.org.ics.estudios.appmovil.cohortefamilia.activities.ListaMuestrasParticipantesCasosActivity;
import ni.org.ics.estudios.appmovil.cohortefamilia.forms.MuestraTuboRojoForm;
import ni.org.ics.estudios.appmovil.cohortefamilia.forms.MuestrasFormLabels;
import ni.org.ics.estudios.appmovil.database.EstudiosAdapter;
import ni.org.ics.estudios.appmovil.domain.Participante;
import ni.org.ics.estudios.appmovil.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.appmovil.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.appmovil.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.appmovil.domain.influenzauo1.ParticipanteCasoUO1;
import ni.org.ics.estudios.appmovil.domain.influenzauo1.VisitaCasoUO1;
import ni.org.ics.estudios.appmovil.domain.influenzauo1.VisitaVacunaUO1;
import ni.org.ics.estudios.appmovil.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.appmovil.influenzauo1.activities.list.ListaMuestrasParticipanteCasoUO1Activity;
import ni.org.ics.estudios.appmovil.influenzauo1.activities.list.ListaMuestrasVacunasUO1Activity;
import ni.org.ics.estudios.appmovil.influenzauo1.forms.MuestraCasoUO1Form;
import ni.org.ics.estudios.appmovil.preferences.PreferencesActivity;
import ni.org.ics.estudios.appmovil.utils.CasosDBConstants;
import ni.org.ics.estudios.appmovil.utils.CatalogosDBConstants;
import ni.org.ics.estudios.appmovil.utils.Constants;
import ni.org.ics.estudios.appmovil.utils.DeviceInfo;
import ni.org.ics.estudios.appmovil.wizard.model.*;
import ni.org.ics.estudios.appmovil.wizard.ui.PageFragmentCallbacks;
import ni.org.ics.estudios.appmovil.wizard.ui.ReviewFragment;
import ni.org.ics.estudios.appmovil.wizard.ui.StepPagerStrip;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Miguel Salinas on 5/17/2017.
 * V1.0
 */
public class NuevaMuestraTuboRojoUO1Activity extends FragmentActivity implements
        PageFragmentCallbacks,
        ReviewFragment.Callbacks,
        ModelCallbacks {

    private ViewPager mPager;
    private MyPagerAdapter mPagerAdapter;
    private boolean mEditingAfterReview;
    private AbstractWizardModel mWizardModel;
    private boolean mConsumePageSelectedEvent;
    private Button mNextButton;
    private Button mPrevButton;

    private List<Page> mCurrentPageSequence;
    private StepPagerStrip mStepPagerStrip;

    private MuestrasFormLabels labels;
    private EstudiosAdapter estudiosAdapter;
    private DeviceInfo infoMovil;
    private static ParticipanteCasoUO1 participanteCasoUO1 = new ParticipanteCasoUO1();
    private static VisitaCasoUO1 visitaCaso = new VisitaCasoUO1();
    private static VisitaVacunaUO1 visitaVacuna = new VisitaVacunaUO1();
    private String username;
    private SharedPreferences settings;
    private static final int EXIT = 1;
    private AlertDialog alertDialog;
    private boolean notificarCambios = true;
    private String horaTomaMx;
    private Double volumenMaximoPermitido = 0D;
    private String accion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_enter);
        settings =
                PreferenceManager.getDefaultSharedPreferences(this);
        username =
                settings.getString(PreferencesActivity.KEY_USERNAME,
                        null);
        infoMovil = new DeviceInfo(NuevaMuestraTuboRojoUO1Activity.this);
        accion = getIntent().getStringExtra(Constants.ACCION);
        if (getIntent().getExtras().getSerializable(Constants.VISITA) instanceof VisitaCasoUO1)
            visitaCaso = (VisitaCasoUO1) getIntent().getExtras().getSerializable(Constants.VISITA);
        else
            visitaVacuna = (VisitaVacunaUO1) getIntent().getExtras().getSerializable(Constants.VISITA);

        if (visitaCaso!=null) participanteCasoUO1 = visitaCaso.getParticipanteCasoUO1();

        volumenMaximoPermitido = getIntent().getDoubleExtra(Constants.VOLUMEN, 6);
        String mPass = ((MyIcsApplication) this.getApplication()).getPassApp();
        mWizardModel = new MuestraCasoUO1Form(this,mPass);
        if (savedInstanceState != null) {
            mWizardModel.load(savedInstanceState.getBundle("model"));
        }
        mWizardModel.registerListener(this);
        labels = new MuestrasFormLabels();

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        mStepPagerStrip = (StepPagerStrip) findViewById(R.id.strip);
        mStepPagerStrip.setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {
            @Override
            public void onPageStripSelected(int position) {
                position = Math.min(mPagerAdapter.getCount() - 1, position);
                if (mPager.getCurrentItem() != position) {
                    mPager.setCurrentItem(position);
                }
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mPrevButton = (Button) findViewById(R.id.prev_button);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mStepPagerStrip.setCurrentPage(position);

                if (mConsumePageSelectedEvent) {
                    mConsumePageSelectedEvent = false;
                    return;
                }

                mEditingAfterReview = false;
                updateBottomBar();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPager.getCurrentItem() == mCurrentPageSequence.size()) {
                    DialogFragment dg = new DialogFragment() {
                        @Override
                        public Dialog onCreateDialog(Bundle savedInstanceState) {
                            return new AlertDialog.Builder(getActivity())
                                    .setMessage(R.string.submit_confirm_message)
                                    .setPositiveButton(R.string.submit_confirm_button, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            saveData();
                                        }
                                    })
                                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            createDialog(EXIT);
                                        }
                                    }).create();
                        }
                    };
                    dg.show(getSupportFragmentManager(), "guardar_dialog");
                } else {
                    if (mEditingAfterReview) {
                        mPager.setCurrentItem(mPagerAdapter.getCount() - 1);
                    } else {
                        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                    }
                }
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });

        //seter el máximo permitido para el volumen de la muestra
        NumberPage vol = (NumberPage)mWizardModel.findByKey(labels.getVolumen());
        vol.setRangeValidation(true, 0, volumenMaximoPermitido.intValue());
        BarcodePage pagetmp = (BarcodePage) mWizardModel.findByKey(labels.getCodigoMx());
        pagetmp.setmCodePosicion(1);
        if (accion.equalsIgnoreCase(Constants.CODIGO_PROPOSITO_UO1)) //positivo
            pagetmp.setPatternValidation(true, "^\\d{1,5}\\.\\d{2}\\.[U|F]R[I|F]$");
        else //vacuna. permitir código de mx o código de participante
            pagetmp.setPatternValidation(true, "(^\\d{1,5}\\.\\d{2}\\.VR[I|F]$)|(^\\d{1,5}?$)");

        onPageTreeChanged();
        updateBottomBar();
    }

    @Override
    public AbstractWizardModel onGetModel() {
        return mWizardModel;
    }

    @Override
    public void onEditScreenAfterReview(String key) {
        for (int i = mCurrentPageSequence.size() - 1; i >= 0; i--) {
            if (mCurrentPageSequence.get(i).getKey().equals(key)) {
                mConsumePageSelectedEvent = true;
                mEditingAfterReview = true;
                mPager.setCurrentItem(i);
                updateBottomBar();
                break;
            }
        }
    }

    @Override
    public void onPageDataChanged(Page page) {
        updateModel(page);
        updateConstrains();
        if (recalculateCutOffPage()) {
            if (notificarCambios) mPagerAdapter.notifyDataSetChanged();
            updateBottomBar();
        }
        notificarCambios = true;
    }

    @Override
    public void onPageTreeChanged() {
        mCurrentPageSequence = mWizardModel.getCurrentPageSequence();
        mStepPagerStrip.setPageCount(mCurrentPageSequence.size() + 1); // + 1 = review step
        mPagerAdapter.notifyDataSetChanged();
        updateBottomBar();
    }

    @Override
    public Page onGetPage(String key) {
        return mWizardModel.findByKey(key);
    }

    @Override
    public void onBackPressed (){
        createDialog(EXIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWizardModel.unregisterListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("model", mWizardModel.save());
    }

    private void createDialog(int dialog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(dialog){
            case EXIT:
                builder.setTitle(this.getString(R.string.confirm));
                builder.setMessage(this.getString(R.string.exiting));
                builder.setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Finish app
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton(this.getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void updateBottomBar() {
        int position = mPager.getCurrentItem();
        if (position == mCurrentPageSequence.size()) {
            mNextButton.setText(R.string.finish);
            mNextButton.setBackgroundResource(R.drawable.finish_background);
            mNextButton.setTextAppearance(this, R.style.TextAppearanceFinish);
        } else {
            mNextButton.setText(mEditingAfterReview
                    ? R.string.review
                    : R.string.next);
            mNextButton.setBackgroundResource(R.drawable.selectable_item_background);
            TypedValue v = new TypedValue();
            getTheme().resolveAttribute(android.R.attr.textAppearanceMedium, v, true);
            mNextButton.setTextAppearance(this, v.resourceId);
            mNextButton.setEnabled(position != mPagerAdapter.getCutOffPage());
        }
        mPrevButton.setVisibility(position <= 0 ? View.INVISIBLE : View.VISIBLE);
    }

    private boolean recalculateCutOffPage() {
        // Cut off the pager adapter at first required page that isn't completed
        int cutOffPage = mCurrentPageSequence.size() + 1;
        for (int i = 0; i < mCurrentPageSequence.size(); i++) {
            Page page = mCurrentPageSequence.get(i);
            String clase = page.getClass().toString();
            if (page.isRequired() && !page.isCompleted()) {
                cutOffPage = i;
                break;
            }
            if (!page.getData().isEmpty() && clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.NumberPage")) {
                NumberPage np = (NumberPage) page;
                String valor = np.getData().getString(NumberPage.SIMPLE_DATA_KEY);
                if((np.ismValRange() && (np.getmGreaterOrEqualsThan() > Double.valueOf(valor) || np.getmLowerOrEqualsThan() < Double.valueOf(valor)))
                        || (np.ismValPattern() && !valor.matches(np.getmPattern()))){
                    cutOffPage = i;
                    break;
                }
            }
            if (!page.getData().isEmpty() && clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.TextPage")) {
                TextPage tp = (TextPage) page;
                if (tp.ismValPattern()) {
                    String valor = tp.getData().getString(TextPage.SIMPLE_DATA_KEY);
                    if(!valor.matches(tp.getmPattern())){
                        cutOffPage = i;
                        break;
                    }
                }
            }
            if (!page.getData().isEmpty() && clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.BarcodePage")) {
                BarcodePage tp = (BarcodePage) page;
                if (tp.ismValPattern()) {
                    String valor = tp.getData().getString(BarcodePage.SIMPLE_DATA_KEY);
                    if(!valor.matches(tp.getmPattern())){
                        Toast.makeText( this.getApplicationContext(),R.string.error1CodigoMx, Toast.LENGTH_LONG).show();
                        cutOffPage = i;
                        break;
                    } else {
                        String codigoTmp = valor;
                        if (valor.contains(".")){
                            codigoTmp = valor.substring(0,valor.indexOf(".",0));
                        }
                        String codigoParticipante = "";
                        if (participanteCasoUO1 != null) codigoParticipante = String.valueOf(participanteCasoUO1.getParticipante().getCodigo());
                        else codigoParticipante = String.valueOf(visitaVacuna.getParticipante().getCodigo());
                        if (!codigoTmp.equalsIgnoreCase(codigoParticipante)) {
                            Toast.makeText( this.getApplicationContext(),this.getString(R.string.error2CodigoMx, codigoParticipante), Toast.LENGTH_LONG).show();
                            cutOffPage = i;
                            break;
                        }
                    }
                }
            }
        }

        if (mPagerAdapter.getCutOffPage() != cutOffPage) {
            mPagerAdapter.setCutOffPage(cutOffPage);
            return true;
        }

        return false;
    }

    public void updateConstrains(){
    }
    public void updateModel(Page page){
        try {
            boolean visible = false;
            if (page.getTitle().equals(labels.getTomaMxSn())) {
                visible = page.getData().getString(TextPage.SIMPLE_DATA_KEY).matches(Constants.YES);
                changeStatus(mWizardModel.findByKey(labels.getCodigoMx()), visible);
                //notificarCambios = false;
                changeStatus(mWizardModel.findByKey(labels.getVolumen()), visible);
                //notificarCambios = false;
                changeStatus(mWizardModel.findByKey(labels.getObservacion()), visible);
                //notificarCambios = false;
                //changeStatus(mWizardModel.findByKey(labels.getNumPinchazos()), visible);
                ////notificarCambios = false;
                visible = page.getData().getString(TextPage.SIMPLE_DATA_KEY).matches(Constants.NO);
                changeStatus(mWizardModel.findByKey(labels.getRazonNoToma()), visible);
                if (visible) horaTomaMx = null;
                //notificarCambios = false;
                horaTomaMx = DateToString(new Date(), "HH:mm:ss");
                onPageTreeChanged();
            }
            /*if (page.getTitle().equals(labels.getCodigoMx())) {
                horaTomaMx = DateToString(new Date(), "HH:mm:ss");
                //notificarCambios = false;
                onPageTreeChanged();
            }*/
            if (page.getTitle().equals(labels.getObservacion())) {
                visible = page.getData().getString(TextPage.SIMPLE_DATA_KEY)!=null && page.getData().getString(TextPage.SIMPLE_DATA_KEY).equalsIgnoreCase("Otra razon");
                changeStatus(mWizardModel.findByKey(labels.getDescOtraObservacion()), visible);
                //notificarCambios = false;
                onPageTreeChanged();
            }
            if (page.getTitle().equals(labels.getRazonNoToma())) {
                visible = page.getData().getString(TextPage.SIMPLE_DATA_KEY)!=null && page.getData().getString(TextPage.SIMPLE_DATA_KEY).equalsIgnoreCase("Otra razon");
                changeStatus(mWizardModel.findByKey(labels.getDescOtraRazonNoToma()), visible);
                //notificarCambios = false;
                onPageTreeChanged();
            }
            }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void changeStatus(Page page, boolean visible){
        String clase = page.getClass().toString();
        if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.SingleFixedChoicePage")){
            SingleFixedChoicePage modifPage = (SingleFixedChoicePage) page; modifPage.resetData(new Bundle()); modifPage.setmVisible(visible);
        }
        else if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.BarcodePage")){
            BarcodePage modifPage = (BarcodePage) page; modifPage.resetData(new Bundle()); modifPage.setmVisible(visible);
        }
        else if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.LabelPage")){
            LabelPage modifPage = (LabelPage) page; modifPage.setmVisible(visible);
        }
        else if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.TextPage")){
            TextPage modifPage = (TextPage) page; modifPage.resetData(new Bundle()); modifPage.setmVisible(visible);
        }
        else if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.NumberPage")){
            NumberPage modifPage = (NumberPage) page; modifPage.resetData(new Bundle()); modifPage.setmVisible(visible);
        }
        else if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.MultipleFixedChoicePage")){
            MultipleFixedChoicePage modifPage = (MultipleFixedChoicePage) page; modifPage.resetData(new Bundle()); modifPage.setmVisible(visible);
        }
        else if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.DatePage")){
            DatePage modifPage = (DatePage) page; modifPage.resetData(new Bundle()); modifPage.setmVisible(visible);
        }
        else if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.SelectParticipantPage")){
            SelectParticipantPage modifPage = (SelectParticipantPage) page; modifPage.resetData(new Bundle()); modifPage.setmVisible(visible);
        }
        else if (clase.equals("class ni.org.ics.estudios.appmovil.wizard.model.NewDatePage")){
            NewDatePage modifPage = (NewDatePage) page; modifPage.resetData(new Bundle()); modifPage.setmVisible(visible);
        }
    }

    private boolean tieneValor(String entrada){
        return (entrada != null && !entrada.isEmpty());
    }

    /**
     * Convierte una Date a String, según el formato indicado
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

    public void saveData() {
        try {
            Map<String, String> mapa = mWizardModel.getAnswers();
            Bundle datos = new Bundle();
            for (Map.Entry<String, String> entry : mapa.entrySet()) {
                datos.putString(entry.getKey(), entry.getValue());
            }

            String tomaMxSn = datos.getString(this.getString(R.string.tomaMxSn));
            String codigoMx = datos.getString(this.getString(R.string.codigoMx));
            //String hora = datos.getString(this.getString(R.string.hora));
            String volumen = datos.getString(this.getString(R.string.volumen));
            String observacion = datos.getString(this.getString(R.string.observacion));
            String descOtraObservacion = datos.getString(this.getString(R.string.descOtraObservacion));
            String razonNoToma = datos.getString(this.getString(R.string.razonNoToma));
            String descOtraRazonNoToma = datos.getString(this.getString(R.string.descOtraRazonNoToma));

            String mPass = ((MyIcsApplication) this.getApplication()).getPassApp();
            estudiosAdapter = new EstudiosAdapter(this.getApplicationContext(), mPass, false, false);
            estudiosAdapter.open();

            Muestra muestra = new Muestra();
            muestra.setCodigo(infoMovil.getId());
            Participante participante = null;
            if (participanteCasoUO1!=null)
                participante = participanteCasoUO1.getParticipante();
            else
                participante = visitaVacuna.getParticipante();
            muestra.setParticipante(participante);
            muestra.setTipoMuestra(Constants.CODIGO_TIPO_SANGRE); //Sangre
            muestra.setTubo(Constants.CODIGO_TUBO_ROJO); //Rojo

            //Validar si la casa a la que pertenece esta actualmente en seguimiento.. si es asi, agregar el participante al seguimiento
            /*if (participante.getProcesos().getCasaCHF()!= null && !participante.getProcesos().getCasaCHF().isEmpty()) {
                CasaCohorteFamiliaCaso casaCaso = estudiosAdapter.getCasaCohorteFamiliaCaso(CasosDBConstants.casa + "='" + participante.getProcesos().getCasaCHF() + "'", null);
                if (casaCaso != null) {
                    ParticipanteCohorteFamiliaCaso existePartCaso = estudiosAdapter.getParticipanteCohorteFamiliaCaso(CasosDBConstants.codigoCaso + "='" + casaCaso.getCodigoCaso() + "' and " + CasosDBConstants.participante + "=" + participante.getCodigo(), null);
                    //solo agregar si no existe
                    if (existePartCaso != null) {
                        muestra.setProposito(Constants.CODIGO_PROPOSITO_UO1_CHF);
                    }
                }
            }else {
                muestra.setProposito(accion);//positivo UO1 o vacunaUO1
            }*///quitar compartido esta dando error. 270092019
            muestra.setProposito(accion);//positivo UO1 o vacunaUO1
            //listas
            if (tieneValor(tomaMxSn)){
                MessageResource mstomaMxSn = estudiosAdapter.getMessageResource(CatalogosDBConstants.spanish + "='" + tomaMxSn + "' and "
                        + CatalogosDBConstants.catRoot + "='CHF_CAT_SINO'", null);
                if (mstomaMxSn != null) muestra.setTomaMxSn(mstomaMxSn.getCatKey());
            }
            if (tieneValor(observacion)){
                MessageResource msobservacion = estudiosAdapter.getMessageResource(CatalogosDBConstants.spanish + "='" + observacion + "' and "
                        + CatalogosDBConstants.catRoot + "='CHF_CAT_OBSERV_MX'", null);
                if (msobservacion != null) muestra.setObservacion(msobservacion.getCatKey());
            }
            if (tieneValor(razonNoToma)){
                MessageResource msrazonNoToma = estudiosAdapter.getMessageResource(CatalogosDBConstants.spanish + "='" + razonNoToma + "' and "
                        + CatalogosDBConstants.catRoot + "='CHF_CAT_RAZON_NO_MX'", null);
                if (msrazonNoToma != null) muestra.setRazonNoToma(msrazonNoToma.getCatKey());
            }
            //Numericos
            if (tieneValor(volumen)) muestra.setVolumen(Double.valueOf(volumen));
            //textos
            muestra.setHora(horaTomaMx);
            muestra.setCodigoMx(codigoMx);
            muestra.setDescOtraRazonNoToma(descOtraRazonNoToma);
            muestra.setDescOtraObservacion(descOtraObservacion);
            //Metadata
            if (visitaCaso.getFechaVisita()!=null)
                muestra.setRecordDate(visitaCaso.getFechaVisita());
            else
                muestra.setRecordDate(visitaVacuna.getFechaVisita());
            muestra.setRecordUser(username);
            muestra.setDeviceid(infoMovil.getDeviceId());
            muestra.setEstado('0');
            muestra.setPasive('0');
            estudiosAdapter.crearMuestras(muestra);
            estudiosAdapter.close();
            Intent i;
            Bundle arguments = new Bundle();
            if (accion.equalsIgnoreCase(Constants.CODIGO_PROPOSITO_UO1)) {
                arguments.putSerializable(Constants.VISITA, visitaCaso);
                i = new Intent(getApplicationContext(),
                        ListaMuestrasParticipanteCasoUO1Activity.class);
            }else {
                arguments.putSerializable(Constants.VISITA, visitaVacuna);
                i = new Intent(getApplicationContext(),
                        ListaMuestrasVacunasUO1Activity.class);
            }
            i.putExtras(arguments);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.success),Toast.LENGTH_LONG);
            toast.show();
            finish();
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error),Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        private int mCutOffPage;
        private Fragment mPrimaryItem;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (i >= mCurrentPageSequence.size()) {
                return new ReviewFragment();
            }

            return mCurrentPageSequence.get(i).createFragment();
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO: be smarter about this
            if (object == mPrimaryItem) {
                // Re-use the current fragment (its position never changes)
                return POSITION_UNCHANGED;
            }

            return POSITION_NONE;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            mPrimaryItem = (Fragment) object;
        }

        @Override
        public int getCount() {
            return Math.min(mCutOffPage + 1, (mCurrentPageSequence != null ? mCurrentPageSequence.size() : 0) + 1);
        }

        public void setCutOffPage(int cutOffPage) {
            if (cutOffPage < 0) {
                cutOffPage = Integer.MAX_VALUE;
            }
            mCutOffPage = cutOffPage;
        }

        public int getCutOffPage() {
            return mCutOffPage;
        }
    }
}
