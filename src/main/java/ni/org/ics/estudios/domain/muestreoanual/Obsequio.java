package ni.org.ics.estudios.domain.muestreoanual;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa los datos de la entrega de obsequios
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "obsequios", catalog = "estudios_ics")
public class Obsequio {

	/**
	 * 
	 */
	private ObsequioId obId;
	private Integer obseqSN;
	private Integer carnetSN;
	
	private String persRecCarnet;
	private Integer relacionFam;
	private String otroRelacionFam;
	private Integer telefono;
	private Integer cmDomicilio;
	private Integer barrio;
	private String dire;
	private String observaciones;
	
	private MovilInfo movilInfo;
	private Integer otrorecurso1;

	@Column(name = "obseqsn", nullable = true, length = 1)
	public Integer getObseqSN() {
		return obseqSN;
	}

	public void setObseqSN(Integer obseqSN) {
		this.obseqSN = obseqSN;
	}

	@Column(name = "carnetsn", nullable = true, length = 1)
	public Integer getCarnetSN() {
		return carnetSN;
	}

	public void setCarnetSN(Integer carnetSN) {
		this.carnetSN = carnetSN;
	}

	@Column(name = "persona_retira", nullable = true)
	public String getPersRecCarnet() {
		return persRecCarnet;
	}

	public void setPersRecCarnet(String persRecCarnet) {
		this.persRecCarnet = persRecCarnet;
	}

	@Column(name = "rel_fam", nullable = true, length = 1)
	public Integer getRelacionFam() {
		return relacionFam;
	}

	public void setRelacionFam(Integer relacionFam) {
		this.relacionFam = relacionFam;
	}

	@Column(name = "o_rel_fam", nullable = true)
	public String getOtroRelacionFam() {
		return otroRelacionFam;
	}

	public void setOtroRelacionFam(String otroRelacionFam) {
		this.otroRelacionFam = otroRelacionFam;
	}

	@Column(name = "telefono", nullable = true)
	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	@Column(name = "cdom", nullable = true, length = 1)
	public Integer getCmDomicilio() {
		return cmDomicilio;
	}

	public void setCmDomicilio(Integer cmDomicilio) {
		this.cmDomicilio = cmDomicilio;
	}

	@Column(name = "barrio", nullable = true, length = 2)
	public Integer getBarrio() {
		return barrio;
	}

	public void setBarrio(Integer barrio) {
		this.barrio = barrio;
	}

	@Column(name = "direccion", nullable = true)
	public String getDire() {
		return dire;
	}

	public void setDire(String dire) {
		this.dire = dire;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}

	@EmbeddedId
	public ObsequioId getObId() {
		return obId;
	}

	public void setObId(ObsequioId obId) {
		this.obId = obId;
	}

	@Column(name = "observaciones", nullable = true)
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	@Column(name = "otrorecurso1", nullable = true, length = 10)
	public Integer getOtrorecurso1() {
		return otrorecurso1;
	}

	public void setOtrorecurso1(Integer otrorecurso1) {
		this.otrorecurso1 = otrorecurso1;
	}
}
