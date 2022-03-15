package ni.org.ics.estudios.appmovil.cohortefamilia.activities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.widget.*;
import ni.org.ics.estudios.appmovil.AbstractAsyncListActivity;
import ni.org.ics.estudios.appmovil.MainActivity;
import ni.org.ics.estudios.appmovil.MyIcsApplication;
import ni.org.ics.estudios.appmovil.R;

import ni.org.ics.estudios.appmovil.catalogs.MessageResource;
import ni.org.ics.estudios.appmovil.cohortefamilia.activities.enterdata.NuevaMuestraBHCPaxgeneActivity;
import ni.org.ics.estudios.appmovil.cohortefamilia.activities.enterdata.NuevaMuestraRespActivity;
import ni.org.ics.estudios.appmovil.cohortefamilia.activities.enterdata.NuevaMuestraTuboPbmcActivity;
import ni.org.ics.estudios.appmovil.cohortefamilia.activities.enterdata.NuevaMuestraTuboRojoActivity;
import ni.org.ics.estudios.appmovil.cohortefamilia.adapters.MuestraAdapter;
import ni.org.ics.estudios.appmovil.database.EstudiosAdapter;
import ni.org.ics.estudios.appmovil.domain.Participante;
import ni.org.ics.estudios.appmovil.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.appmovil.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.appmovil.domain.cohortefamilia.casos.VisitaFinalCaso;
import ni.org.ics.estudios.appmovil.domain.cohortefamilia.casos.VisitaSeguimientoCaso;
import ni.org.ics.estudios.appmovil.muestreoanual.activities.NewSampleActivity;
import ni.org.ics.estudios.appmovil.utils.CatalogosDBConstants;
import ni.org.ics.estudios.appmovil.utils.Constants;
import ni.org.ics.estudios.appmovil.utils.MainDBConstants;
import ni.org.ics.estudios.appmovil.utils.MuestrasDBConstants;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ni.org.ics.estudios.appmovil.utils.muestreoanual.ConstantsDB;

public class ListaMuestrasParticipantesCasosActivity extends AbstractAsyncListActivity {
	
	private TextView textView;
	private Drawable img = null;
	private Button mAddBhcButton;
	private Button mAddRojoButton;
	private Button mAddPbmcButton;
	private Button mAddRespButton;
	private Button mReviewButton;
	private Button mButton;
	private Button mMAButton;//MA2022. Tomar mx de muestreo anual en la visita final
	//Viene de la actividad principal
	private static VisitaSeguimientoCaso visitaCaso = new VisitaSeguimientoCaso();
    private static VisitaFinalCaso visitaFinalCaso = new VisitaFinalCaso();
	//Tipo de lista
    private static ParticipanteCohorteFamilia participantechf = new ParticipanteCohorteFamilia();
    private static Date fechaVisita = new Date();
    //Adaptador del objeto de la lista
	private ArrayAdapter<Muestra> mMuestraAdapter;
	//Lista de objetos 
	private List<Muestra> mMuestras = new ArrayList<Muestra>();
	private List<MessageResource> mTiposMuestra = new ArrayList<MessageResource>();
	
	private EstudiosAdapter estudiosAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_add_casos);
		//Obtener objeto que viene del menú
        visitaCaso = (VisitaSeguimientoCaso) getIntent().getExtras().getSerializable(Constants.VISITA);
        visitaFinalCaso = (VisitaFinalCaso) getIntent().getExtras().getSerializable(Constants.VISITA_FINAL);
        if (visitaCaso!=null) {
            participantechf = visitaCaso.getCodigoParticipanteCaso().getParticipante();
            fechaVisita = visitaCaso.getFechaVisita();
        }
        if (visitaFinalCaso!=null) {
            participantechf= visitaFinalCaso.getCodigoParticipanteCaso().getParticipante();
            fechaVisita = visitaFinalCaso.getFechaVisita();
        }
        textView = (TextView) findViewById(R.id.label);
		img=getResources().getDrawable(R.drawable.ic_samples_seg);
		textView.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
		String mPass = ((MyIcsApplication) this.getApplication()).getPassApp();
		estudiosAdapter = new EstudiosAdapter(this.getApplicationContext(),mPass,false,false);
		//Obtener objetos para llenar la lista
		new FetchDataMuestrasCasosTask().execute();
		
		mButton = (Button) findViewById(R.id.add_part_button);
		mButton.setVisibility(View.GONE);
		
		mButton = (Button) findViewById(R.id.datos_casa_button);
		mButton.setVisibility(View.GONE);
		
		mButton = (Button) findViewById(R.id.add_visit_button);
		mButton.setVisibility(View.GONE);
		
		mButton = (Button) findViewById(R.id.new_sint_button);
		mButton.setVisibility(View.GONE);
		
		mButton = (Button) findViewById(R.id.new_cont_button);
		mButton.setVisibility(View.GONE);
		
		mButton = (Button) findViewById(R.id.fail_visit_button);
		mButton.setVisibility(View.GONE);

        mButton = (Button) findViewById(R.id.final_visit_button);
        mButton.setVisibility(View.GONE);

        mButton = (Button) findViewById(R.id.new_surface_button);
        mButton.setVisibility(View.GONE);

        mButton = (Button) findViewById(R.id.new_hand_button);
        mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.sensors_button);
		mButton.setVisibility(View.GONE);

		mMAButton = (Button) findViewById(R.id.new_MA_button);
		mMAButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				new OpenMuestrasMATask().execute();
			}
		});

        mAddBhcButton = (Button) findViewById(R.id.new_bhc_button);
		mAddRojoButton = (Button) findViewById(R.id.new_rojo_button);
		mAddPbmcButton = (Button) findViewById(R.id.new_pbmc_button);
		mAddRespButton = (Button) findViewById(R.id.new_resp_button);
		mReviewButton = (Button) findViewById(R.id.view_samp_button);

		mAddBhcButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				new OpenDataEnterActivityTask().execute(Constants.CODIGO_TUBO_BHC);
			}
		});
		
		mAddRojoButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				new OpenDataEnterActivityTask().execute(Constants.CODIGO_TUBO_ROJO);
			}
		});
		
		mAddRespButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				new OpenDataEnterActivityTask().execute(Constants.CODIGO_TIPO_RESP);
			}
		});
		
		mAddPbmcButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				new OpenDataEnterActivityTask().execute(Constants.CODIGO_TUBO_PBMC);
			}
		});
		
		mReviewButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				Bundle arguments = new Bundle();
				if (participantechf != null) arguments.putSerializable(Constants.PARTICIPANTE, participantechf);
	            Intent i = new Intent(getApplicationContext(),
	                    ListaMuestrasActivity.class);
	            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            i.putExtra(Constants.ACCION, Constants.REVIEWING);
	            i.putExtras(arguments);
	            startActivity(i);
			}
		});

        if (visitaFinalCaso!=null){
            mAddBhcButton.setVisibility(View.GONE);
            mAddRespButton.setVisibility(View.GONE);
        } else {
			mMAButton.setVisibility(View.GONE);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.general, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.MENU_BACK:
			finish();
			return true;
		case R.id.MENU_HOME:
			i = new Intent(getApplicationContext(),
					MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position,
			long id) {
        
        // Opcion de menu seleccionada tiene que editar o quitar
       
	}
	
	@Override
	public void onBackPressed (){
		//Llamar al menu anterior
		Bundle arguments = new Bundle();
		Intent i;
        if (visitaCaso!=null) {
            arguments.putSerializable(Constants.VISITA, visitaCaso);
            i = new Intent(getApplicationContext(),
                    MenuVisitaSeguimientoCasoActivity.class);
        }else{
            arguments.putSerializable(Constants.VISITA_FINAL, visitaFinalCaso);
            i = new Intent(getApplicationContext(),
                    MenuVisitaFinalCasoActivity.class);
        }
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtras(arguments);
		startActivity(i);
		finish();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	
	private class FetchDataMuestrasCasosTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			// before the request begins, show a progress indicator
			showLoadingProgressDialog();
		}

		@Override
		protected String doInBackground(String... values) {
			try {
				estudiosAdapter.open();
				//Llenar el objeto de lista de esta View
				mMuestras = estudiosAdapter.getMuestras(MuestrasDBConstants.participante + " = " + participantechf.getParticipante().getCodigo()
						+" and " + MainDBConstants.pasive + " ='0' and " + MuestrasDBConstants.proposito + " ='" + Constants.CODIGO_PROPOSITO_TX + "'" +
								" and " + MainDBConstants.recordDate + "= " + fechaVisita.getTime(), MuestrasDBConstants.tipoMuestra);
				mTiposMuestra = estudiosAdapter.getMessageResources(CatalogosDBConstants.catRoot + "='CHF_CAT_TIP0_MX_RESP'" + " or " +CatalogosDBConstants.catRoot + "='CHF_CAT_TIP_TUBO_MX'" + " or " + CatalogosDBConstants.catRoot + "='CHF_CAT_RAZON_NO_MX'", null);
				estudiosAdapter.close();
			} catch (Exception e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return "error";
			}
			return "exito";
		}

		protected void onPostExecute(String resultado) {
			// after the request completes, hide the progress indicator
			//Actualizar el encabezado de esta view y enlazar el adapter
			textView.setText("");
			textView.setTextColor(Color.BLACK);
            if (visitaFinalCaso==null) {
                textView.setText(getString(R.string.main_1) + "\n" + getString(R.string.follow_up_list_samp) + "\n" +
                        getString(R.string.code) + ": " + participantechf.getParticipante().getCodigo() + " - " + getString(R.string.visit) + ": " + visitaCaso.getVisita());
            }else{
                textView.setText(getString(R.string.main_1) + "\n" + getString(R.string.follow_up_list_samp) + "\n" +
                        getString(R.string.code) + ": " + participantechf.getParticipante().getCodigo() + " - " + getString(R.string.visit_final));
            }
			mMuestraAdapter = new MuestraAdapter(getApplication().getApplicationContext(), R.layout.complex_list_item, mMuestras, mTiposMuestra);
			setListAdapter(mMuestraAdapter);
			dismissProgressDialog();
		}

	}
	
	// ***************************************
	// Private classes
	// ***************************************
	private class OpenDataEnterActivityTask extends AsyncTask<String, Void, String> {
		String opcion;
		@Override
		protected void onPreExecute() {
			// before the request begins, show a progress indicator
			showLoadingProgressDialog();
		}

		@Override
		protected String doInBackground(String... values) {
			try {
				opcion = values[0];
				Bundle arguments = new Bundle();				
				if (visitaCaso != null) arguments.putSerializable(Constants.VISITA, visitaCaso);
                if (visitaFinalCaso != null) arguments.putSerializable(Constants.VISITA, visitaFinalCaso);
				Intent i;
				if(opcion.equals(Constants.CODIGO_TUBO_BHC)){
					i = new Intent(getApplicationContext(),
							NuevaMuestraBHCPaxgeneActivity.class);
					i.putExtra(Constants.ACCION, Constants.CODIGO_PROPOSITO_TX);
				}
				else if(opcion.equals(Constants.CODIGO_TUBO_ROJO)){
					i = new Intent(getApplicationContext(),
							NuevaMuestraTuboRojoActivity.class);
					i.putExtra(Constants.ACCION, Constants.CODIGO_PROPOSITO_TX);
				}
				else if(opcion.equals(Constants.CODIGO_TUBO_PBMC)){
					i = new Intent(getApplicationContext(),
							NuevaMuestraTuboPbmcActivity.class);
					i.putExtra(Constants.ACCION, Constants.CODIGO_PROPOSITO_TX);
				}
				else if(opcion.equals(Constants.CODIGO_TIPO_RESP)){
					i = new Intent(getApplicationContext(),
							NuevaMuestraRespActivity.class);
					i.putExtra(Constants.ACCION, Constants.CODIGO_PROPOSITO_TX);
				}
				else{
					return "error";
				}
				i.putExtras(arguments);
		        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			} catch (Exception e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return "error";
			}
			return "exito";
		}

		protected void onPostExecute(String resultado) {
			// after the request completes, hide the progress indicator
			dismissProgressDialog();
		}

	}

	private class OpenMuestrasMATask extends AsyncTask<String, Void, String> {
		ArrayList<ni.org.ics.estudios.appmovil.domain.muestreoanual.Muestra> mMuestrasMA = new ArrayList<ni.org.ics.estudios.appmovil.domain.muestreoanual.Muestra>();
		@Override
		protected void onPreExecute() {
			// before the request begins, show a progress indicator
			showLoadingProgressDialog();
		}

		@Override
		protected String doInBackground(String... values) {
			try {
				estudiosAdapter.open();

				mMuestrasMA =estudiosAdapter.getListaMuestras(participantechf.getParticipante().getCodigo());
				/**/
				estudiosAdapter.close();
			} catch (Exception e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return "error";
			}
			return "exito";
		}

		protected void onPostExecute(String resultado) {
			if (mMuestrasMA.size() <= 0) {
				Intent i = new Intent(getApplicationContext(),
						NewSampleActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.putExtra(ConstantsDB.COD_CASA, participantechf.getParticipante().getCasa().getCodigo());
				i.putExtra(ConstantsDB.CODIGO, participantechf.getParticipante().getCodigo());
				i.putExtra(ConstantsDB.VIS_EXITO, true);
				Bundle arguments = new Bundle();
				arguments.putSerializable(Constants.VISITA_FINAL, visitaFinalCaso);
				i.putExtras(arguments);
				startActivity(i);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.samples_MA_exist),Toast.LENGTH_LONG);
				toast.show();
			}
			dismissProgressDialog();
		}

	}

}
