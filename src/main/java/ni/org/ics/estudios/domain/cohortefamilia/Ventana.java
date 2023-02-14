package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.AreaAmbiente;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@DiscriminatorValue("ventana")
public class Ventana extends AreaAmbiente {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private AreaAmbiente areaAmbiente;
    private String abierta;
    private Banio areaBanio;

    @Column(name = "ABIERTA", length = 1)
    public String getAbierta() {
        return abierta;
    }

    public void setAbierta(String abierta) {
        this.abierta = abierta;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_AREA", nullable = true)
    @ForeignKey(name = "FK_AREA_AREA")
    public AreaAmbiente getAreaAmbiente() {
        return areaAmbiente;
    }

    public void setAreaAmbiente(AreaAmbiente areaAmbiente) {
        this.areaAmbiente = areaAmbiente;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_BANIO", nullable = true)
    @ForeignKey(name = "FK_AREA_BANIO")

    public Banio getAreaBanio() {
        return areaBanio;
    }

    public void setAreaBanio(Banio areaBanio) {
        this.areaBanio = areaBanio;
    }
}
