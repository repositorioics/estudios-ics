package ni.org.ics.estudios.domain.muestreoanual;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa los datos de peso y talla de los
 * participantes en el estudio
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "pesoytalla", catalog = "estudios_ics")
public class PesoyTalla {

	/**
	 * 
	 */
	
	private PesoyTallaId ptId;
    private String tomoMedidaSn;
    private String razonNoTomoMedidas;
	private Double peso1;
	private Double peso2;
	private Double peso3;
	private Double talla1;
	private Double talla2;
	private Double talla3;
	private Double imc1;
	private Double imc2;
	private Double imc3;
	private Double difPeso;
	private Double difTalla;
	private MovilInfo movilInfo;
	private Integer otrorecurso1;
	private Integer otrorecurso2;
	private String estudiosAct; // estudios actuales al momento de llenar la encuesta
    private Boolean encuestaValida; //18-01-2024

	@Column(name = "peso1", nullable = true)
	public Double getPeso1() {
		return peso1;
	}

	public void setPeso1(Double peso1) {
		this.peso1 = peso1;
	}

	@Column(name = "peso2", nullable = true)
	public Double getPeso2() {
		return peso2;
	}

	public void setPeso2(Double peso2) {
		this.peso2 = peso2;
	}

	@Column(name = "peso3", nullable = true)
	public Double getPeso3() {
		return peso3;
	}

	public void setPeso3(Double peso3) {
		this.peso3 = peso3;
	}

	@Column(name = "talla1", nullable = true)
	public Double getTalla1() {
		return talla1;
	}

	public void setTalla1(Double talla1) {
		this.talla1 = talla1;
	}

	@Column(name = "talla2", nullable = true)
	public Double getTalla2() {
		return talla2;
	}

	public void setTalla2(Double talla2) {
		this.talla2 = talla2;
	}

	@Column(name = "talla3", nullable = true)
	public Double getTalla3() {
		return talla3;
	}

	public void setTalla3(Double talla3) {
		this.talla3 = talla3;
	}

	@Column(name = "imc1", nullable = true)
	public Double getImc1() {
		return imc1;
	}

	public void setImc1(Double imc1) {
		this.imc1 = imc1;
	}

	@Column(name = "imc2", nullable = true)
	public Double getImc2() {
		return imc2;
	}

	public void setImc2(Double imc2) {
		this.imc2 = imc2;
	}

	@Column(name = "imc3", nullable = true)
	public Double getImc3() {
		return imc3;
	}

	public void setImc3(Double imc3) {
		this.imc3 = imc3;
	}

	@Column(name = "difpeso", nullable = true)
	public Double getDifPeso() {
		return difPeso;
	}

	public void setDifPeso(Double difPeso) {
		this.difPeso = difPeso;
	}

	@Column(name = "diftalla", nullable = true)
	public Double getDifTalla() {
		return difTalla;
	}

	public void setDifTalla(Double difTalla) {
		this.difTalla = difTalla;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}

	@EmbeddedId
	public PesoyTallaId getPtId() {
		return ptId;
	}

	public void setPtId(PesoyTallaId ptId) {
		this.ptId = ptId;
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

    @Column(name = "TOMA_MEDIDAS", length = 1)
    public String getTomoMedidaSn() {
        return tomoMedidaSn;
    }

    public void setTomoMedidaSn(String tomoMedidaSn) {
        this.tomoMedidaSn = tomoMedidaSn;
    }

    @Column(name = "RAZON_NO_TOMA_MEDIDAS")
    public String getRazonNoTomoMedidas() {
        return razonNoTomoMedidas;
    }

    public void setRazonNoTomoMedidas(String razonNoTomoMedidas) {
        this.razonNoTomoMedidas = razonNoTomoMedidas;
    }

	@Column(name = "est_actuales")
	public String getEstudiosAct() {
		return estudiosAct;
	}

	public void setEstudiosAct(String estudiosAct) {
		this.estudiosAct = estudiosAct;
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
