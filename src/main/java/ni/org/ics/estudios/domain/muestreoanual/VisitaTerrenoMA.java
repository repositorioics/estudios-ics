package ni.org.ics.estudios.domain.muestreoanual;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa los datos de la visita al terreno
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "visitasterreno", catalog = "estudios_ics")
public class VisitaTerrenoMA {

	/**
	 * 
	 */
	private VisitaTerrenoId visitaId;
	private Integer visitaSN;
	private Integer motNoVisita;
	private String otroMotNoVisita;//MA2020
	private String acomp;
	private Integer relacionFam;
	private Integer asentimiento;
	
	private String otraRelacionFam;
	private String carnetSN;
	//para peso y talla muestreo 2020. Una vez iniciadas las encuestas de casa y participante se quitaron éstas preguntas, el 13022020
	private String estudiaSN;
	private String nEscuela;
	private String otraEscuela;
	private String turno;
	
	private MovilInfo movilInfo;
	private Integer otrorecurso1;
	private Integer otrorecurso2;

    private Boolean encuestaValida; //13-02-2024

	@EmbeddedId
	public VisitaTerrenoId getVisitaId() {
		return visitaId;
	}

	public void setVisitaId(VisitaTerrenoId visitaId) {
		this.visitaId = visitaId;
	}

	@Column(name = "visitasn", nullable = false, length = 1)
	public Integer getVisitaSN() {
		return visitaSN;
	}

	public void setVisitaSN(Integer visitaSN) {
		this.visitaSN = visitaSN;
	}

	@Column(name = "motnovisita", nullable = true)
	public Integer getMotNoVisita() {
		return motNoVisita;
	}

	public void setMotNoVisita(Integer motNoVisita) {
		this.motNoVisita = motNoVisita;
	}

	@Column(name = "acomp", nullable = true)
	public String getAcomp() {
		return acomp;
	}

	public void setAcomp(String acomp) {
		this.acomp = acomp;
	}

	@Column(name = "relacion_fam", nullable = true)
	public Integer getRelacionFam() {
		return relacionFam;
	}

	public void setRelacionFam(Integer relacionFam) {
		this.relacionFam = relacionFam;
	}

	@Column(name = "asentimiento", nullable = true)
	public Integer getAsentimiento() {
		return asentimiento;
	}

	public void setAsentimiento(Integer asentimiento) {
		this.asentimiento = asentimiento;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}

	@Column(name = "otrorecurso1", nullable = true, length = 10)
	public Integer getOtrorecurso1() {
		return otrorecurso1;
	}

	public void setOtrorecurso1(Integer otrorecurso1) {
		this.otrorecurso1 = otrorecurso1;
	}

	@Column(name = "otrorecurso2", nullable = true, length = 10)
	public Integer getOtrorecurso2() {
		return otrorecurso2;
	}

	public void setOtrorecurso2(Integer otrorecurso2) {
		this.otrorecurso2 = otrorecurso2;
	}

	@Column(name = "otraRelacionFam", nullable = true, length = 50)
	public String getOtraRelacionFam() {
		return otraRelacionFam;
	}

	public void setOtraRelacionFam(String otraRelacionFam) {
		this.otraRelacionFam = otraRelacionFam;
	}

	@Column(name = "carnetSN", nullable = true, length = 2)
	public String getCarnetSN() {
		return carnetSN;
	}

	public void setCarnetSN(String carnetSN) {
		this.carnetSN = carnetSN;
	}

	@Column(name = "estudiaSN", nullable = true, length = 2)
	public String getEstudiaSN() {
		return estudiaSN;
	}

	public void setEstudiaSN(String estudiaSN) {
		this.estudiaSN = estudiaSN;
	}

	@Column(name = "nEscuela", nullable = true, length = 4)
	public String getnEscuela() {
		return nEscuela;
	}

	public void setnEscuela(String nEscuela) {
		this.nEscuela = nEscuela;
	}

	@Column(name = "otraEscuela", nullable = true, length = 100)
	public String getOtraEscuela() {
		return otraEscuela;
	}

	public void setOtraEscuela(String otraEscuela) {
		this.otraEscuela = otraEscuela;
	}

	@Column(name = "turno", nullable = true, length = 2)
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Column(name = "otroMotNoVisita")
	public String getOtroMotNoVisita() {
		return otroMotNoVisita;
	}

	public void setOtroMotNoVisita(String otroMotNoVisita) {
		this.otroMotNoVisita = otroMotNoVisita;
	}

    @JsonIgnore
    @Column(name = "ENCUESTA_VALIDA")
    public Boolean getEncuestaValida() {
        return encuestaValida;
    }

    public void setEncuestaValida(Boolean encuestaValida) {
        this.encuestaValida = encuestaValida;
    }
}
