package ni.org.ics.estudios.appmovil.domain.cohortefamilia;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
public class Ventana extends AreaAmbiente {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AreaAmbiente areaAmbiente; //area a la que pertenece la ventana.. puede ser bano, sala, habitación, cocina o comedor
    private String abierta;
    private Banio areaBanio;

    public String getAbierta() {
        return abierta;
    }

    public void setAbierta(String abierta) {
        this.abierta = abierta;
    }

    public AreaAmbiente getAreaAmbiente() {
        return areaAmbiente;
    }

    public void setAreaAmbiente(AreaAmbiente areaAmbiente) {
        this.areaAmbiente = areaAmbiente;
    }

    public Banio getAreaBanio() {
        return areaBanio;
    }

    public void setAreaBanio(Banio areaBanio) {
        this.areaBanio = areaBanio;
    }
}
