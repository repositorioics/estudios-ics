package ni.org.ics.estudios.domain.muestreoanual;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa los datos de la lactancia materna
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "encuesta_satisfaccion", catalog = "estudios_ics")
public class EncuestaSatisfaccion {

	/**
	 * 
	 */
	
	private Date fechaEncuesta;
	private String estudio;
	private Integer atenPerEst;
	private Integer tiemAten;
	private Integer atenPerAdm;
	private Integer atenPerEnferm;
	private Integer atenPerMed;
	private Integer ambAten;
	private Integer atenPerLab;
	private Integer explDxEnf;
	private Integer fludenSN;
	private Integer fluConImp;
	private Integer denConImp;
	private Integer explPeligEnf;
	private Integer expMedCuid;
	private MovilInfo movilInfo;
	private Integer otrorecurso1;
	
	@Id
	@Column(name = "fecha_encuesta", nullable = false)
	public Date getFechaEncuesta() {
		return fechaEncuesta;
	}

	public void setFechaEncuesta(Date fechaEncuesta) {
		this.fechaEncuesta = fechaEncuesta;
	}

	@Column(name = "estudio", nullable = true)
	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}

	@Column(name = "atenperest", nullable = true, length = 1)
	public Integer getAtenPerEst() {
		return atenPerEst;
	}

	public void setAtenPerEst(Integer atenPerEst) {
		this.atenPerEst = atenPerEst;
	}

	@Column(name = "tiematen", nullable = true, length = 1)
	public Integer getTiemAten() {
		return tiemAten;
	}

	public void setTiemAten(Integer tiemAten) {
		this.tiemAten = tiemAten;
	}

	@Column(name = "atenperadm", nullable = true, length = 1)
	public Integer getAtenPerAdm() {
		return atenPerAdm;
	}

	public void setAtenPerAdm(Integer atenPerAdm) {
		this.atenPerAdm = atenPerAdm;
	}

	@Column(name = "atenperenferm", nullable = true, length = 1)
	public Integer getAtenPerEnferm() {
		return atenPerEnferm;
	}

	public void setAtenPerEnferm(Integer atenPerEnferm) {
		this.atenPerEnferm = atenPerEnferm;
	}

	@Column(name = "atenpermed", nullable = true, length = 1)
	public Integer getAtenPerMed() {
		return atenPerMed;
	}

	public void setAtenPerMed(Integer atenPerMed) {
		this.atenPerMed = atenPerMed;
	}

	@Column(name = "ambaten", nullable = true, length = 1)
	public Integer getAmbAten() {
		return ambAten;
	}

	public void setAmbAten(Integer ambAten) {
		this.ambAten = ambAten;
	}

	@Column(name = "atenperlab", nullable = true, length = 1)
	public Integer getAtenPerLab() {
		return atenPerLab;
	}

	public void setAtenPerLab(Integer atenPerLab) {
		this.atenPerLab = atenPerLab;
	}

	@Column(name = "expldxenf", nullable = true, length = 1)
	public Integer getExplDxEnf() {
		return explDxEnf;
	}

	public void setExplDxEnf(Integer explDxEnf) {
		this.explDxEnf = explDxEnf;
	}

	@Column(name = "fludensn", nullable = true, length = 1)
	public Integer getFludenSN() {
		return fludenSN;
	}

	public void setFludenSN(Integer fludenSN) {
		this.fludenSN = fludenSN;
	}

	@Column(name = "flunonimp", nullable = true, length = 1)
	public Integer getFluConImp() {
		return fluConImp;
	}

	public void setFluConImp(Integer fluConImp) {
		this.fluConImp = fluConImp;
	}

	@Column(name = "denconimp", nullable = true, length = 1)
	public Integer getDenConImp() {
		return denConImp;
	}

	public void setDenConImp(Integer denConImp) {
		this.denConImp = denConImp;
	}

	@Column(name = "explpeligenf", nullable = true, length = 1)
	public Integer getExplPeligEnf() {
		return explPeligEnf;
	}

	public void setExplPeligEnf(Integer explPeligEnf) {
		this.explPeligEnf = explPeligEnf;
	}

	@Column(name = "expmedcuid", nullable = true, length = 1)
	public Integer getExpMedCuid() {
		return expMedCuid;
	}

	public void setExpMedCuid(Integer expMedCuid) {
		this.expMedCuid = expMedCuid;
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

}
