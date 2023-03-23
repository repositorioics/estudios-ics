package ni.org.ics.estudios.domain.cohortefamilia.casos;

import java.util.Date;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

/**
 * Created by William Aviles on 6/07/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_casas_casos", catalog = "estudios_ics", uniqueConstraints={@UniqueConstraint(columnNames = {"CODIGO_CASA_CHF","FECHA_INICIO"})})
public class CasaCohorteFamiliaCaso extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCaso;
	private CasaCohorteFamilia casa;
	private Date fechaInicio;
	private String inactiva;
	private Date fechaInactiva;
    private String observacion;
    
	@Id
    @Column(name = "CODIGO_CASO", length = 50, nullable = false)
    public String getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(String codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_CASA_CHF", nullable = false)
    @ForeignKey(name = "FK_CASO_CASA")
	public CasaCohorteFamilia getCasa() {
		return casa;
	}

	public void setCasa(CasaCohorteFamilia casa) {
		this.casa = casa;
	}


	@Column(name = "FECHA_INICIO", nullable = false)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@Column(name = "INACTIVA", length = 2, nullable = false)
	public String getInactiva() {
		return inactiva;
	}

	public void setInactiva(String inactiva) {
		this.inactiva = inactiva;
	}

	@Column(name = "FECHA_INACTIVA", nullable = true)
	public Date getFechaInactiva() {
		return fechaInactiva;
	}

	public void setFechaInactiva(Date fechaInactiva) {
		this.fechaInactiva = fechaInactiva;
	}

    @JsonIgnore
    @Column(name = "OBSERVACION", nullable = true, length = 100)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
	public String toString(){
		return casa.getCodigoCHF() + "-" + fechaInicio;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CasaCohorteFamiliaCaso)) return false;

        CasaCohorteFamiliaCaso that = (CasaCohorteFamiliaCaso) o;

        if (!codigoCaso.equals(that.codigoCaso)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCaso.hashCode();
        result = 31 * result + casa.hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
