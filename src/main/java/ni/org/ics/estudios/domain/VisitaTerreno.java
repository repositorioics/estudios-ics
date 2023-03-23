package ni.org.ics.estudios.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Created by FIRSTICT on 4/28/2017.
 * V1.0
 */
@Entity
@Table(name = "visitas", catalog = "estudios_ics")
public class VisitaTerreno extends BaseMetaData {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoVisita;
	private Casa casa;
	private Date fechaVisita;
	private String visitaExitosa;
	private String razonVisitaNoExitosa;
    private String otraRazonVisitaNoExitosa;

	@Id
	@Column(name = "CODIGO_VISITA", nullable = false, length = 50)
    public String getCodigoVisita() {
		return codigoVisita;
	}

	public void setCodigoVisita(String codigoVisita) {
		this.codigoVisita = codigoVisita;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASA", nullable = false)
    @ForeignKey(name = "FK_CASA_VISITA")
	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

	@Column(name = "FECHA_VISITA", nullable = false)
	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	@Column(name = "VISITA_EXITOSA", nullable = false, length = 5)
	public String getVisitaExitosa() {
		return visitaExitosa;
	}

	public void setVisitaExitosa(String visitaExitosa) {
		this.visitaExitosa = visitaExitosa;
	}

	@Column(name = "RAZON_VISITA_NO_EXITOSA", nullable = true, length = 5)
	public String getRazonVisitaNoExitosa() {
		return razonVisitaNoExitosa;
	}

	public void setRazonVisitaNoExitosa(String razonVisitaNoExitosa) {
		this.razonVisitaNoExitosa = razonVisitaNoExitosa;
	}

    @Column(name = "OTRA_RAZON_VISITA_NO_EXITOSA", nullable = true)
    public String getOtraRazonVisitaNoExitosa() {
        return otraRazonVisitaNoExitosa;
    }

    public void setOtraRazonVisitaNoExitosa(String otraRazonVisitaNoExitosa) {
        this.otraRazonVisitaNoExitosa = otraRazonVisitaNoExitosa;
    }

    @Override
    public String toString() {
        return "'" + codigoVisita + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaTerreno)) return false;

        VisitaTerreno visita = (VisitaTerreno) o;

        return (!codigoVisita.equals(visita.codigoVisita));
    }

    @Override
    public int hashCode() {
        return codigoVisita.hashCode();
    }
}
