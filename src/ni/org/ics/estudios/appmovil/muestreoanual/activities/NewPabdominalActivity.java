package ni.org.ics.estudios.appmovil.muestreoanual.activities;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ni.org.ics.estudios.appmovil.AbstractAsyncActivity;
import ni.org.ics.estudios.appmovil.MyIcsApplication;
import ni.org.ics.estudios.appmovil.R;
import ni.org.ics.estudios.appmovil.database.EstudiosAdapter;
import ni.org.ics.estudios.appmovil.domain.Participante;
import ni.org.ics.estudios.appmovil.domain.muestreoanual.MovilInfo;
import ni.org.ics.estudios.appmovil.domain.muestreoanual.PerimetroAbdominal;
import ni.org.ics.estudios.appmovil.domain.muestreoanual.PerimetroAbdominalId;
import ni.org.ics.estudios.appmovil.domain.muestreoanual.PesoyTallaId;
import ni.org.ics.estudios.appmovil.muestreoanual.parsers.PerimetroAbdominalXml;
import ni.org.ics.estudios.appmovil.muestreoanual.parsers.PyTXml;
import ni.org.ics.estudios.appmovil.preferences.PreferencesActivity;
import ni.org.ics.estudios.appmovil.utils.Constants;
import ni.org.ics.estudios.appmovil.utils.FileUtils;
import ni.org.ics.estudios.appmovil.utils.MainDBConstants;
import ni.org.ics.estudios.appmovil.utils.muestreoanual.ConstantsDB;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Ing. Santiago Carballo
 **/

public class NewPabdominalActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewPabdominalActivity.class.getSimpleName();

    private static Integer casaId = 0;
    private static Integer codigo = 0;
    public static final int ADD_PART =1;
    private String username;
    private SharedPreferences settings;
    private static Participante mParticipante = new Participante();
    private boolean visExitosa = false;

    private static PerimetroAbdominal mPabdominal = new PerimetroAbdominal();
    Dialog dialogInit;

    private EstudiosAdapter estudiosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!FileUtils.storageReady()) {
            Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error, R.string.storage_error),Toast.LENGTH_LONG);
            toast.show();
            finish();
        }

        settings =
                PreferenceManager.getDefaultSharedPreferences(this);
        username =
                settings.getString(PreferencesActivity.KEY_USERNAME,
                        null);

        String mPass = ((MyIcsApplication) this.getApplication()).getPassApp();
        estudiosAdapter = new EstudiosAdapter(this.getApplicationContext(),mPass,false,false);

        casaId = getIntent().getIntExtra(ConstantsDB.COD_CASA,-1);
        codigo = getIntent().getIntExtra(ConstantsDB.CODIGO,-1);
        visExitosa = getIntent().getBooleanExtra(ConstantsDB.VIS_EXITO,false);
        getData();
        createInitDialog();
    }

    /**
     * Presenta dialogo inicial
     */

    private void createInitDialog() {
        dialogInit = new Dialog(this, R.style.FullHeightDialog);
        dialogInit.setContentView(R.layout.yesno);
        dialogInit.setCancelable(false);

        //to set the message
        TextView message =(TextView) dialogInit.findViewById(R.id.yesnotext);
        message.setText(getString(R.string.verify_pabdominal));

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addPabdominal();
            }
        });

        Button no = (Button) dialogInit.findViewById(R.id.yesnoNo);
        no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Cierra
                dialogInit.dismiss();
                finish();
            }
        });
        dialogInit.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MENU_BACK:
                finish();
                return true;
            case R.id.MENU_HOME:
                Intent i = new Intent(getApplicationContext(),
                        MenuMuestreoAnualActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        //Si todo salio bien en ODK Collect
        if (resultCode == RESULT_OK) {
            Uri instanceUri = intent.getData();
            //Busca la instancia resultado
            String[] projection = new String[] {
                    "_id","instanceFilePath", "status","displaySubtext"};
            Cursor c = getContentResolver().query(instanceUri, projection,
                    null, null, null);
            c.moveToFirst();
            //Captura la id de la instancia y la ruta del archivo para agregarlo al participante
            Integer idInstancia = c.getInt(c.getColumnIndex("_id"));
            String instanceFilePath = c.getString(c.getColumnIndex("instanceFilePath"));
            String complete = c.getString(c.getColumnIndex("status"));
            String cambio = c.getString(c.getColumnIndex("displaySubtext"));
            //cierra el cursor
            if (c != null) {
                c.close();
            }
            if (complete.matches("complete")){
                //Parsear el resultado obteniendo un participante si esta completa
                parsePabAbdominal(idInstancia,instanceFilePath,cambio);
            }
            else{
                showToast(getApplicationContext().getString(R.string.err_not_completed),1);
            }
        }
        else{
            finish();
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    public void parsePabAbdominal(Integer idInstancia, String instanceFilePath, String ultimoCambio) {
        Serializer serializer = new Persister();
        File source = new File(instanceFilePath);
        try {
            PerimetroAbdominalXml em = new PerimetroAbdominalXml();
            em = serializer.read(PerimetroAbdominalXml.class, source);

            PerimetroAbdominalId pAId = new PerimetroAbdominalId();
            pAId.setCodigo(codigo);
            pAId.setFecha(new Date());
            mPabdominal.setPaId(pAId);
            mPabdominal.setPabdominal1(em.getPabdominal1());
            mPabdominal.setPabdominal2(em.getPabdominal2());
            mPabdominal.setPabdominal3(em.getPabdominal3());
            mPabdominal.setDifpabdominal(em.getDifpabdominal());
            mPabdominal.setOtrorecurso1(em.getOtrorecurso1());
            mPabdominal.setOtrorecurso2(em.getOtrorecurso2());
            mPabdominal.setTomoMedidaSn(em.getToma_medidas());
            mPabdominal.setRazonNoTomoMedidas(em.getRazon_no_toma_medidas());
            mPabdominal.setEstudiosAct(mParticipante.getProcesos().getEstudio());
            mPabdominal.setMovilInfo(new MovilInfo(idInstancia,
                    instanceFilePath,
                    Constants.STATUS_NOT_SUBMITTED,
                    ultimoCambio,
                    em.getStart(),
                    em.getEnd(),
                    em.getDeviceid(),
                    em.getSimserial(),
                    em.getPhonenumber(),
                    em.getToday(),
                    username,
                    false, em.getRecurso1(), em.getRecurso2()));
            //Guarda en la base de datos local
            estudiosAdapter.open();
            estudiosAdapter.crearPerimetroAbdominal(mPabdominal);
            mParticipante.getProcesos().setPerimetroAbdominal("No");
            mParticipante.getProcesos().setMovilInfo(new MovilInfo(idInstancia,
                    instanceFilePath,
                    Constants.STATUS_NOT_SUBMITTED,
                    ultimoCambio,
                    em.getStart(),
                    em.getEnd(),
                    em.getDeviceid(),
                    em.getSimserial(),
                    em.getPhonenumber(),
                    em.getToday(),
                    username,
                    false, em.getRecurso1(), em.getRecurso2()));

            estudiosAdapter.actualizarParticipanteProcesos(mParticipante.getProcesos());
            estudiosAdapter.close();
            showToast(getApplicationContext().getString(R.string.success),0);
            Intent i = new Intent(getApplicationContext(),
                    MenuInfoActivity.class);
            i.putExtra(ConstantsDB.COD_CASA, casaId);
            i.putExtra(ConstantsDB.CODIGO, codigo);
            i.putExtra(ConstantsDB.VIS_EXITO, visExitosa);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        } catch (Exception e) {
            // Presenta el error al parsear el xml
            showToast(e.toString(),1);
            e.printStackTrace();
        }
    }

    private void addPabdominal() {
        try{
            //campos de proveedor de collect
            String[] projection = new String[] {
                    "_id","jrFormId","displayName"};
            //cursor que busca el formulario
            Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
                    "jrFormId = 'estacionperimetroabdominal' and displayName = 'Datos de Estacion Perimetro Abdominal'", null, null);
            c.moveToFirst();
            //captura el id del formulario
            Integer id = Integer.parseInt(c.getString(0));
            //cierra el cursor
            if (c != null) {
                c.close();
            }
            //forma el uri para ODK Collect
            Uri formUri = ContentUris.withAppendedId(Constants.CONTENT_URI,id);
            //Arranca la actividad ODK Collect en busca de resultado
            Intent odkA =  new Intent(Intent.ACTION_EDIT,formUri);
            startActivityForResult(odkA,ADD_PART);
        }
        catch(Exception e){
            //No existe el formulario en el equipo
            Log.e(TAG, e.getMessage(), e);
            showToast(e.getLocalizedMessage(),1);
        }
    }

    private void getData() {
        estudiosAdapter.open();
        mParticipante = estudiosAdapter.getParticipante(MainDBConstants.codigo + "=" + codigo, null);
        estudiosAdapter.close();
    }

    private void showToast(String mensaje, int numImage){
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);

        switch(numImage){
            case 0:
                image.setImageResource(R.drawable.ic_ok);
                break;
            case 1:
                image.setImageResource(R.drawable.ic_error);
                break;
            default:
                image.setImageResource(R.drawable.ic_launcher);
                break;
        }


        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(mensaje);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}
