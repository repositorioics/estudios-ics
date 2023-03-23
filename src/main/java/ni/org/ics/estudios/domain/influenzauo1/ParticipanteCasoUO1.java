package ni.org.ics.estudios.domain.influenzauo1;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 20/08/2019.
 * V1.0
 */
@Entity
@Table(name = "uo1_participantes_casos", catalog = "estudios_ics")
public class ParticipanteCasoUO1 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoParticipante;
	private Participante participante;
	private String activo;
	private String positivoPor;
	private Date fif;
	private Date fechaIngreso;
	private Date fechaDesactivacion;
    private Date fis;
    private String observacion;
	
    
	@Id
    @Column(name = "CODIGO_PARTICIPANTE_CASO", length = 50, nullable = false)
	public String getCodigoCasoParticipante() {
		return codigoCasoParticipante;
	}

	public void setCodigoCasoParticipante(String codigoCasoParticipante) {
		this.codigoCasoParticipante = codigoCasoParticipante;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CASOUO1")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	@Column(name = "ACTIVO", length = 1, nullable = false)
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	@Column(name = "POSITIVO_POR", length = 2, nullable = false)
	public String getPositivoPor() {
		return positivoPor;
	}

	public void setPositivoPor(String positivoPor) {
		this.positivoPor = positivoPor;
	}

	@Column(name = "FIF")
	public Date getFif() {
		return fif;
	}

	public void setFif(Date fif) {
		this.fif = fif;
	}

	@Column(name = "FECHA_INGRESO", nullable = false)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Column(name = "FECHA_DESACTIVACION")
	public Date getFechaDesactivacion() {
		return fechaDesactivacion;
	}

	public void setFechaDesactivacion(Date fechaDesactivacion) {
		this.fechaDesactivacion = fechaDesactivacion;
	}

    @Column(name = "FIS", nullable = true)
    public Date getFis() {
        return fis;
    }

    public void setFis(Date fis) {
        this.fis = fis;
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
		return participante.getCodigo().toString();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteCasoUO1)) return false;

        ParticipanteCasoUO1 that = (ParticipanteCasoUO1) o;

        if (!codigoCasoParticipante.equals(that.codigoCasoParticipante)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoParticipante.hashCode();
        result = 31 * result + participante.hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
}
