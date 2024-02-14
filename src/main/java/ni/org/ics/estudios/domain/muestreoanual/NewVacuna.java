package ni.org.ics.estudios.domain.muestreoanual;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa los datos de vacunas recibidas
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "nuevas_vacunas", catalog = "estudios_ics")
public class NewVacuna {

	/**
	 * 
	 */
	private NewVacunaId vacunaId;
	private String vacuna_sn;
	private String tvacunano;
	private String otroMotivoTvacunano;
	
	private MovilInfo movilInfo;
	private Integer otrorecurso1;

    private Boolean encuestaValida; //13-02-2024

	@Id
	public NewVacunaId getVacunaId() {
		return vacunaId;
	}

	public void setVacunaId(NewVacunaId vacunaId) {
		this.vacunaId = vacunaId;
	}

	@Column(name = "vacuna", nullable = true, length = 10)
	public String getVacuna_sn() {
		return vacuna_sn;
	}

	public void setVacuna_sn(String vacuna_sn) {
		this.vacuna_sn = vacuna_sn;
	}

	@Column(name = "tvacunano", nullable = true, length = 10)
	public String getTvacunano() {
		return tvacunano;
	}

	public void setTvacunano(String tvacunano) {
		this.tvacunano = tvacunano;
	}

	@Column(name = "otroMotivoTvacunano", nullable = true, length = 750)
	public String getOtroMotivoTvacunano() {
		return otroMotivoTvacunano;
	}

	public void setOtroMotivoTvacunano(String otroMotivoTvacunano) {
		this.otroMotivoTvacunano = otroMotivoTvacunano;
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

    public Boolean getEncuestaValida() {
        return encuestaValida;
    }

    @JsonIgnore
    @Column(name = "ENCUESTA_VALIDA")
    public void setEncuestaValida(Boolean encuestaValida) {
        this.encuestaValida = encuestaValida;
    }

}
