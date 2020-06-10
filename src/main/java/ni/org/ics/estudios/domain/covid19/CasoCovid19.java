package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 01/06/2020.
 * V1.0
 */
@Entity
@Table(name = "covid_casos", catalog = "estudios_ics")
public class CasoCovid19 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCaso;
	private CasaCohorteFamilia casa;
	private Date fechaIngreso;
	private String inactivo;
	private Date fechaInactivo;
	
    
	@Id
    @Column(name = "CODIGO_CASO", length = 36, nullable = false)
    public String getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(String codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	@ManyToOne(optional = true)
    @JoinColumn(name = "CODIGO_CASA_CHF", nullable = true, referencedColumnName = "CODIGO_CHF")
    @ForeignKey(name = "FK_CASO_CASA_COVID")
	public CasaCohorteFamilia getCasa() {
		return casa;
	}

	public void setCasa(CasaCohorteFamilia casa) {
		this.casa = casa;
	}


	@Column(name = "FECHA_INGRESO", nullable = false)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaInicio) {
		this.fechaIngreso = fechaInicio;
	}
	
	@Column(name = "INACTIVO", length = 2, nullable = false)
	public String getInactivo() {
		return inactivo;
	}

	public void setInactivo(String inactivo) {
		this.inactivo = inactivo;
	}

	@Column(name = "FECHA_INACTIVO", nullable = true)
	public Date getFechaInactivo() {
		return fechaInactivo;
	}

	public void setFechaInactivo(Date fechaInactivo) {
		this.fechaInactivo = fechaInactivo;
	}

	@Override
	public String toString(){
		return casa.getCodigoCHF() + "-" + fechaIngreso;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CasoCovid19)) return false;

        CasoCovid19 that = (CasoCovid19) o;

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
