package ni.org.ics.estudios.domain.influenzauo1;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 30/09/2019.
 * V1.0
 */
@Entity
@Table(name = "uo1_sintomas_visita_caso", catalog = "estudios_ics")
public class SintomasVisitaCasoUO1 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoSintoma;
	private VisitaCasoUO1 codigoCasoVisita;
	private Date fechaSintomas;
	private String dia;
	private String consultaInicial;
	private String fiebre;
	private String fiebreIntensidad;
	private String tos;
	private String tosIntensidad;
	private String secrecionNasal;
	private String secrecionNasalIntensidad;
	private String dolorGarganta;
	private String dolorGargantaIntensidad;
	private String congestionNasal;
	private String dolorCabeza;
	private String dolorCabezaIntensidad;
	private String faltaApetito;
	private String dolorMuscular;
	private String dolorMuscularIntensidad;
	private String dolorArticular;
	private String dolorArticularIntensidad;
	private String dolorOido;
	private String respiracionRapida;
	private String dificultadRespiratoria;
	private String faltaEscuelta;
	private String quedoCama;

	@Id
	@Column(name = "CODIGO_SINTOMA", length = 50, nullable = false)
	public String getCodigoSintoma() {
		return codigoSintoma;
	}

	public void setCodigoSintoma(String codigoSintoma) {
		this.codigoSintoma = codigoSintoma;
	}

	@ManyToOne
	@JoinColumn(name = "CODIGO_VISITA_CASO", referencedColumnName = "CODIGO_VISITA_CASO", nullable = false)
	@ForeignKey(name = "FK_SINTOMA_VISITACASOUO1")
	public VisitaCasoUO1 getCodigoCasoVisita() {
		return codigoCasoVisita;
	}

	public void setCodigoCasoVisita(VisitaCasoUO1 codigoCasoVisita) {
		this.codigoCasoVisita = codigoCasoVisita;
	}

	@Column(name = "FECHA_SINTOMAS", nullable = false)
	public Date getFechaSintomas() {
		return fechaSintomas;
	}

	public void setFechaSintomas(Date fechaSintomas) {
		this.fechaSintomas = fechaSintomas;
	}

	@Column(name = "DIA", length = 2, nullable = true)
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	@Column(name = "CONSULTA_INICIAL", length = 3, nullable = false)
	public String getConsultaInicial() {
		return consultaInicial;
	}

	public void setConsultaInicial(String consultaInicial) {
		this.consultaInicial = consultaInicial;
	}

	@Column(name = "FIEBRE", length = 3, nullable = false)
	public String getFiebre() {
		return fiebre;
	}

	public void setFiebre(String fiebre) {
		this.fiebre = fiebre;
	}

	@Column(name = "FIEBRE_INTENSIDAD", length = 2)
	public String getFiebreIntensidad() {
		return fiebreIntensidad;
	}

	public void setFiebreIntensidad(String fiebreIntensidad) {
		this.fiebreIntensidad = fiebreIntensidad;
	}

	@Column(name = "TOS", length = 3, nullable = false)
	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

	@Column(name = "TOS_INTENSIDAD", length = 2)
	public String getTosIntensidad() {
		return tosIntensidad;
	}

	public void setTosIntensidad(String tosIntensidad) {
		this.tosIntensidad = tosIntensidad;
	}

	@Column(name = "SECRECION_NASAL", length = 3, nullable = false)
	public String getSecrecionNasal() {
		return secrecionNasal;
	}

	public void setSecrecionNasal(String secrecionNasal) {
		this.secrecionNasal = secrecionNasal;
	}

	@Column(name = "SECRECION_NASAL_INTENSIDAD", length = 2)
	public String getSecrecionNasalIntensidad() {
		return secrecionNasalIntensidad;
	}

	public void setSecrecionNasalIntensidad(String secrecionNasalIntensidad) {
		this.secrecionNasalIntensidad = secrecionNasalIntensidad;
	}

	@Column(name = "DOLOR_GARGANTA", length = 3, nullable = false)
	public String getDolorGarganta() {
		return dolorGarganta;
	}

	public void setDolorGarganta(String dolorGarganta) {
		this.dolorGarganta = dolorGarganta;
	}

	@Column(name = "DOLOR_GARGANTA_INTENSIDAD", length = 2)
	public String getDolorGargantaIntensidad() {
		return dolorGargantaIntensidad;
	}

	public void setDolorGargantaIntensidad(String dolorGargantaIntensidad) {
		this.dolorGargantaIntensidad = dolorGargantaIntensidad;
	}

	@Column(name = "CONGESTION_NASAL", length = 3, nullable = false)
	public String getCongestionNasal() {
		return congestionNasal;
	}

	public void setCongestionNasal(String congestionNasal) {
		this.congestionNasal = congestionNasal;
	}

	@Column(name = "DOLOR_CABEZA", length = 3, nullable = false)
	public String getDolorCabeza() {
		return dolorCabeza;
	}

	public void setDolorCabeza(String dolorCabeza) {
		this.dolorCabeza = dolorCabeza;
	}

	@Column(name = "DOLOR_CABEZA_INTENSIDAD", length = 2)
	public String getDolorCabezaIntensidad() {
		return dolorCabezaIntensidad;
	}

	public void setDolorCabezaIntensidad(String dolorCabezaIntensidad) {
		this.dolorCabezaIntensidad = dolorCabezaIntensidad;
	}

	@Column(name = "FALTA_APETITO", length = 3, nullable = false)
	public String getFaltaApetito() {
		return faltaApetito;
	}

	public void setFaltaApetito(String faltaApetito) {
		this.faltaApetito = faltaApetito;
	}

	@Column(name = "DOLOR_MUSCULAR", length = 3, nullable = false)
	public String getDolorMuscular() {
		return dolorMuscular;
	}

	public void setDolorMuscular(String dolorMuscular) {
		this.dolorMuscular = dolorMuscular;
	}

	@Column(name = "DOLOR_MUSCULAR_INTENSIDAD", length = 2)
	public String getDolorMuscularIntensidad() {
		return dolorMuscularIntensidad;
	}

	public void setDolorMuscularIntensidad(String dolorMuscularIntensidad) {
		this.dolorMuscularIntensidad = dolorMuscularIntensidad;
	}

	@Column(name = "DOLOR_ARTICULAR", length = 3, nullable = false)
	public String getDolorArticular() {
		return dolorArticular;
	}

	public void setDolorArticular(String dolorArticular) {
		this.dolorArticular = dolorArticular;
	}

	@Column(name = "DOLOR_ARTICULAR_INTENSIDAD", length = 2)
	public String getDolorArticularIntensidad() {
		return dolorArticularIntensidad;
	}

	public void setDolorArticularIntensidad(String dolorArticularIntensidad) {
		this.dolorArticularIntensidad = dolorArticularIntensidad;
	}

	@Column(name = "DOLOR_OIDO", length = 3, nullable = false)
	public String getDolorOido() {
		return dolorOido;
	}

	public void setDolorOido(String dolorOido) {
		this.dolorOido = dolorOido;
	}

	@Column(name = "RESPIRACION_RAPIDA", length = 3, nullable = false)
	public String getRespiracionRapida() {
		return respiracionRapida;
	}

	public void setRespiracionRapida(String respiracionRapida) {
		this.respiracionRapida = respiracionRapida;
	}

	@Column(name = "DIFICULTAD_RESPIRAR", length = 3, nullable = false)
	public String getDificultadRespiratoria() {
		return dificultadRespiratoria;
	}

	public void setDificultadRespiratoria(String dificultadRespiratoria) {
		this.dificultadRespiratoria = dificultadRespiratoria;
	}

	@Column(name = "FALTA_ESCUELA", length = 3, nullable = false)
	public String getFaltaEscuelta() {
		return faltaEscuelta;
	}

	public void setFaltaEscuelta(String faltaEscuelta) {
		this.faltaEscuelta = faltaEscuelta;
	}

	@Column(name = "QUEDO_CAMA", length = 3, nullable = false)
	public String getQuedoCama() {
		return quedoCama;
	}

	public void setQuedoCama(String quedoCama) {
		this.quedoCama = quedoCama;
	}

	@Override
	public String toString(){
		return codigoCasoVisita.getParticipanteCasoUO1().getParticipante().getCasa().getCodigo() + "-" + codigoCasoVisita.getParticipanteCasoUO1().getParticipante().getCodigo() + "-" + codigoCasoVisita.getParticipanteCasoUO1().getFechaIngreso();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SintomasVisitaCasoUO1)) return false;

        SintomasVisitaCasoUO1 that = (SintomasVisitaCasoUO1) o;

        if (!codigoSintoma.equals(that.codigoSintoma)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoSintoma.hashCode();
        result = 31 * result + codigoCasoVisita.getParticipanteCasoUO1().getParticipante().hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
}
