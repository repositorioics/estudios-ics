package ni.org.ics.estudios.appmovil.muestreoanual.adapters;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ni.org.ics.estudios.appmovil.R;

public class MenuInfoAdapter extends ArrayAdapter<String> {

	private final String[] values;
	private final int numVisitas;
	private final int numPesosT;
	private final int numEncCasa;
	private final int numEncPart;
	private final int numEncLact;
	private final int numVacunas;
	private final int numMuestras;
	private final int numObsequios;
	private final int numSeroprev;
	private final int numPartos;
	private final int numDatosCasa;
	private final int numDocs;
    private final int numEncCasaChf;
    //MA2020
    //private final int numEncCasaSa;
    //private final int numEncPartSa;
    private final int numDatosCoord;
    private final int numPabdominal;
    private final int numEncSatUsuario;

	public MenuInfoAdapter(Context context, int textViewResourceId,
			String[] values, int visitas, int pyts
			, int ecasa, int epart, int elact, int vacunas
			, int muestras, int obsequios, int seroprev, int partos, int datoscasas, int docs
            , int ecasachf, int numDatosCoord, int pAbdominal, int mEncSatUsuario) {
		super(context, textViewResourceId, values);
		this.values = values;
		this.numVisitas=visitas;
		this.numPesosT=pyts;
		this.numEncCasa=ecasa;
		this.numEncPart=epart;
		this.numEncLact=elact;
		this.numVacunas=vacunas;
		this.numMuestras=muestras;
		this.numObsequios=obsequios;
		this.numSeroprev =seroprev;
		this.numPartos=partos;
		this.numDatosCasa=datoscasas;
		this.numDocs = docs;
        this.numEncCasaChf = ecasachf;
        this.numDatosCoord = numDatosCoord;
        this.numPabdominal = pAbdominal; //Perimetro Abdominal
        this.numEncSatUsuario = mEncSatUsuario;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.menu_item_2, null);
		}
		TextView textView = (TextView) v.findViewById(R.id.label);
		textView.setTypeface(null, Typeface.NORMAL);
		textView.setTextColor(Color.BLACK);
		textView.setText(values[position]);

		// Change icon based on position
		Drawable img = null;
		switch (position) {
            case 0:
                img = getContext().getResources().getDrawable(R.drawable.ic_data_persona);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            case 1:
                img = getContext().getResources().getDrawable(R.drawable.ic_map);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numVisitas + ")");
                if (numVisitas < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 2:
                img = getContext().getResources().getDrawable(R.drawable.ic_pesotalla);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numPesosT + ")");
                if (numPesosT < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 3:
                img = getContext().getResources().getDrawable(R.drawable.ic_survey_casa);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numEncCasa + ")");
                if (numEncCasa < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 4:
                img = getContext().getResources().getDrawable(R.drawable.ic_survey_persona);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numEncPart + ")");
                if (numEncPart < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 5:
                img = getContext().getResources().getDrawable(R.drawable.ic_breastfeeding);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numEncLact + ")");
                if (numEncLact < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 6:
                img = getContext().getResources().getDrawable(R.drawable.ic_vacc);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numVacunas + ")");
                if (numVacunas < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 7:
                img = getContext().getResources().getDrawable(R.drawable.ic_cohorte);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            case 8:
                img = getContext().getResources().getDrawable(R.drawable.ic_blood);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numMuestras + ")");
                if (numMuestras < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 9:
                img = getContext().getResources().getDrawable(R.drawable.ic_gift);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numObsequios + ")");
                if (numObsequios < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 10:
                img = getContext().getResources().getDrawable(R.drawable.ic_consentimiento);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numSeroprev + ")");
                if (numSeroprev < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 11:
                img = getContext().getResources().getDrawable(R.drawable.ic_post);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numPartos + ")");
                if (numPartos < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 12:
                img = getContext().getResources().getDrawable(R.drawable.ic_casa);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numDatosCasa + ")");
                if (numDatosCasa < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 13:
                img = getContext().getResources().getDrawable(R.drawable.ic_docs);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numDocs + ")");
                if (numDocs < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 14:
                img = getContext().getResources().getDrawable(R.drawable.ic_survey_casa);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numEncCasaChf + ")");
                if (numEncCasaChf < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            //MA2020
                /*case 17:
                img = getContext().getResources().getDrawable(R.drawable.ic_survey_casa);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numEncCasaSa + ")");
                if (numEncCasaSa < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 18:
                img = getContext().getResources().getDrawable(R.drawable.ic_survey_persona);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numEncPartSa + ")");
                if (numEncPartSa < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;*/
            case 15:
                img = getContext().getResources().getDrawable(R.drawable.ic_menu_call);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
            case 16:
                img = getContext().getResources().getDrawable(R.drawable.ic_placemarker);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numDatosCoord + ")");
                if (numDatosCoord < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 17:
                img = getContext().getResources().getDrawable(R.drawable.ic_pesotalla);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numPabdominal + ")");
                if (numPabdominal < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            case 18:
                img = getContext().getResources().getDrawable(R.drawable.ic_survey_persona);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                textView.setText(values[position] + "(" + numEncSatUsuario + ")");
                if (numEncSatUsuario < 1) {
                    textView.setTextColor(Color.RED);
                }
                break;
            default:
                img = getContext().getResources().getDrawable(R.drawable.ic_survey_casa);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);
                break;
        }

		return v;
	}
}
