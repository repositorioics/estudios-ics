package ni.org.ics.estudios.appmovil.covid19.activities.list;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import ni.org.ics.estudios.appmovil.AbstractAsyncListActivity;
import ni.org.ics.estudios.appmovil.MainActivity;
import ni.org.ics.estudios.appmovil.MyIcsApplication;
import ni.org.ics.estudios.appmovil.R;
import ni.org.ics.estudios.appmovil.catalogs.MessageResource;
import ni.org.ics.estudios.appmovil.covid19.activities.MenuVisitaCasoCovid19Activity;
import ni.org.ics.estudios.appmovil.covid19.activities.enterdata.NuevaVisitaFallidaCovid19Activity;
import ni.org.ics.estudios.appmovil.covid19.adapters.VisitaFallidaCasoCovid19Adapter;
import ni.org.ics.estudios.appmovil.database.EstudiosAdapter;
import ni.org.ics.estudios.appmovil.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.appmovil.domain.covid19.VisitaFallidaCasoCovid19;
import ni.org.ics.estudios.appmovil.utils.*;

import java.util.ArrayList;
import java.util.List;

public class ListaVisitasFallidasCasoCovid19Activity extends AbstractAsyncListActivity {
	
	private TextView textView;
	private Drawable img = null;
	private Button mButton;
	private Button mAddVisitButton;
	private Button mFailVisitsButton;
	private static ParticipanteCasoCovid19 partCaso = new ParticipanteCasoCovid19();
    private VisitaFallidaCasoCovid19 visitaFallidaCasoCovid19 = new VisitaFallidaCasoCovid19();
	private ArrayAdapter<VisitaFallidaCasoCovid19> mVisitaFallidaCasoAdapter;
	private List<VisitaFallidaCasoCovid19> mVisitaFallidasCasos = new ArrayList<VisitaFallidaCasoCovid19>();
	private List<MessageResource> mRazonNoVisita = new ArrayList<MessageResource>();
	private EstudiosAdapter estudiosAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_add_casos_covid19);
		
		partCaso = (ParticipanteCasoCovid19) getIntent().getExtras().getSerializable(Constants.PARTICIPANTE);
		textView = (TextView) findViewById(R.id.label);
		img=getResources().getDrawable(R.drawable.ic_menu_today);
		textView.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
		String mPass = ((MyIcsApplication) this.getApplication()).getPassApp();
		estudiosAdapter = new EstudiosAdapter(this.getApplicationContext(),mPass,false,false);
		new FetchDataVisitasCasosTask().execute(partCaso.getCodigoCasoParticipante());

		mButton = (Button) findViewById(R.id.new_sint_button_cv19);
		mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.new_bhc_button_cv19);
		mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.new_rojo_button_cv19);
		mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.new_pbmc_button_cv19);
		mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.new_resp_button_cv19);
		mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.view_samp_button_cv19);
		mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.final_visit_button_cv19);
		mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.send_case_cv19);
		mButton.setVisibility(View.GONE);

		mButton = (Button) findViewById(R.id.new_MA_button_cv19);
		mButton.setVisibility(View.GONE);

        mAddVisitButton = (Button) findViewById(R.id.add_visit_button_cv19);
		mAddVisitButton.setText(getString(R.string.new_fail_visit));
		mAddVisitButton.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_menu_btn_add), null, null);
		mAddVisitButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				Bundle arguments = new Bundle();
				arguments.putSerializable(Constants.PARTICIPANTE , partCaso);
				Intent i = new Intent(getApplicationContext(),
						NuevaVisitaFallidaCovid19Activity.class);
				i.putExtras(arguments);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				finish();
			}
		});
		
		mFailVisitsButton = (Button) findViewById(R.id.fail_visit_button_cv19);
		mFailVisitsButton.setText(getString(R.string.visit_back));
		mFailVisitsButton.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_menu_back), null, null);
		mFailVisitsButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				Bundle arguments = new Bundle();
				arguments.putSerializable(Constants.PARTICIPANTE , partCaso);
				Intent i = new Intent(getApplicationContext(),
						ListaVisitasParticipanteCasoCovid19Activity.class);
				i.putExtras(arguments);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				finish();
			}
		});
		
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
        visitaFallidaCasoCovid19 = (VisitaFallidaCasoCovid19) this.getListAdapter().getItem(position);
        // Opcion de menu seleccionada
        Bundle arguments = new Bundle();
		Intent i;
		arguments.putSerializable(Constants.VISITA , visitaFallidaCasoCovid19);
		i = new Intent(getApplicationContext(),
				MenuVisitaCasoCovid19Activity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtras(arguments);
		startActivity(i);
		finish();
	}
	
	@Override
	public void onBackPressed (){
		Bundle arguments = new Bundle();
		arguments.putSerializable(Constants.PARTICIPANTE , partCaso);
		Intent i = new Intent(getApplicationContext(),
				ListaVisitasParticipanteCasoCovid19Activity.class);
		i.putExtras(arguments);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
	
	
	private class FetchDataVisitasCasosTask extends AsyncTask<String, Void, String> {
		private String codigoCaso = null;
		@Override
		protected void onPreExecute() {
			// before the request begins, show a progress indicator
			showLoadingProgressDialog();
		}

		@Override
		protected String doInBackground(String... values) {
			codigoCaso = values[0];
			try {
				estudiosAdapter.open();
				mVisitaFallidasCasos = estudiosAdapter.getVisitasFallidasCasoCovid19(Covid19DBConstants.codigoParticipanteCaso +" = '" + codigoCaso +"'", MainDBConstants.fechaVisita);
				mRazonNoVisita = estudiosAdapter.getMessageResources(CatalogosDBConstants.catRoot + "='CHF_CAT_VISITA_NO_P' or " +CatalogosDBConstants.catRoot + "='CHF_CAT_VISITA_NO_C'" , null);
				estudiosAdapter.close();
			} catch (Exception e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return "error";
			}
			return "exito";
		}

		protected void onPostExecute(String resultado) {
			// after the request completes, hide the progress indicator
			textView.setText("");
			textView.setTextColor(Color.BLACK);
			textView.setText(getString(R.string.main_4) +"\n"+ getString(R.string.follow_up_list_fail_visit)+"\n"+ getString(R.string.code)+ " "+ getString(R.string.participant)+ ": "+partCaso.getParticipante().getCodigo());
			mVisitaFallidaCasoAdapter = new VisitaFallidaCasoCovid19Adapter(getApplication().getApplicationContext(), R.layout.complex_list_item,mVisitaFallidasCasos,mRazonNoVisita);
			setListAdapter(mVisitaFallidaCasoAdapter);
			dismissProgressDialog();
		}

	}	

}
