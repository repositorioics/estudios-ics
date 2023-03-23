package ni.org.ics.estudios.domain.muestreoanual;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa los datos de la toma de muestra
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "muestras", catalog = "estudios_ics")
public class MuestraMA {

	/**
	 * 
	 */
	
	private MuestraId mId;
	private Integer fiebreM;
	private Integer consulta;
	private Integer tuboBHC;
	private Integer bhc_razonNo;
	private String bhc_otraRazonNo;
	private Integer tuboRojo;
	private Integer rojo_razonNo;
	private String rojo_otraRazonNo;
	private Integer tuboLeu;
	private Integer pbmc_razonNo;
	private String pbmc_otraRazonNo;
	private String horaBHC;
	private String horaPBMC;
	private String horaInicioPax;
	private String horaFinPax;
	private String codPax;
	private String terreno;
	private Integer pinchazos;
	private MovilInfo movilInfo;
	private Integer otrorecurso1;
	private Integer otrorecurso2;
    private String estudiosAct; // estudios actuales al momento de llenar la encuesta
    //MA2019
    private String hd_sn;
    private String hdPorqueNo;
    //MA2020
	private Integer tuboPax;

    //MA2023
    private String observacion;

	@Column(name = "fiebre", nullable = true, length = 1)
	public Integer getFiebreM() {
		return fiebreM;
	}

	public void setFiebreM(Integer fiebreM) {
		this.fiebreM = fiebreM;
	}

	@Column(name = "consulta", nullable = true, length = 1)
	public Integer getConsulta() {
		return consulta;
	}

	public void setConsulta(Integer consulta) {
		this.consulta = consulta;
	}

	@Column(name = "tubobhc", nullable = true, length = 1)
	public Integer getTuboBHC() {
		return tuboBHC;
	}

	public void setTuboBHC(Integer tuboBHC) {
		this.tuboBHC = tuboBHC;
	}

	@Column(name = "tuborojo", nullable = true, length = 1)
	public Integer getTuboRojo() {
		return tuboRojo;
	}

	public void setTuboRojo(Integer tuboRojo) {
		this.tuboRojo = tuboRojo;
	}

	@Column(name = "tuboleu", nullable = true, length = 1)
	public Integer getTuboLeu() {
		return tuboLeu;
	}

	public void setTuboLeu(Integer tuboLeu) {
		this.tuboLeu = tuboLeu;
	}

	@Column(name = "pinchazos", nullable = true, length = 1)
	public Integer getPinchazos() {
		return pinchazos;
	}

	public void setPinchazos(Integer pinchazos) {
		this.pinchazos = pinchazos;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}
	
	
	@Column(name = "tubobhcno", nullable = true, length = 1)
	public Integer getBhc_razonNo() {
		return bhc_razonNo;
	}

	public void setBhc_razonNo(Integer bhc_razonNo) {
		this.bhc_razonNo = bhc_razonNo;
	}

	@Column(name = "tubobhcnootra", nullable = true)
	public String getBhc_otraRazonNo() {
		return bhc_otraRazonNo;
	}

	public void setBhc_otraRazonNo(String bhc_otraRazonNo) {
		this.bhc_otraRazonNo = bhc_otraRazonNo;
	}

	@Column(name = "tuborojono", nullable = true, length = 1)
	public Integer getRojo_razonNo() {
		return rojo_razonNo;
	}

	public void setRojo_razonNo(Integer rojo_razonNo) {
		this.rojo_razonNo = rojo_razonNo;
	}

	@Column(name = "tuborojonootra", nullable = true)
	public String getRojo_otraRazonNo() {
		return rojo_otraRazonNo;
	}

	public void setRojo_otraRazonNo(String rojo_otraRazonNo) {
		this.rojo_otraRazonNo = rojo_otraRazonNo;
	}

	@Column(name = "tuboleuno", nullable = true, length = 1)
	public Integer getPbmc_razonNo() {
		return pbmc_razonNo;
	}

	public void setPbmc_razonNo(Integer pbmc_razonNo) {
		this.pbmc_razonNo = pbmc_razonNo;
	}

	@Column(name = "tuboleunootra", nullable = true)
	public String getPbmc_otraRazonNo() {
		return pbmc_otraRazonNo;
	}

	public void setPbmc_otraRazonNo(String pbmc_otraRazonNo) {
		this.pbmc_otraRazonNo = pbmc_otraRazonNo;
	}
	
	
	@Column(name = "hora_inicio_pax", nullable = true, length = 50)
	public String getHoraInicioPax() {
		return horaInicioPax;
	}

	public void setHoraInicioPax(String horaInicioPax) {
		this.horaInicioPax = horaInicioPax;
	}

	@Column(name = "hora_fin_pax", nullable = true, length = 50)
	public String getHoraFinPax() {
		return horaFinPax;
	}

	public void setHoraFinPax(String horaFinPax) {
		this.horaFinPax = horaFinPax;
	}

	@Column(name = "cod_pax", nullable = true, length = 50)
	public String getCodPax() {
		return codPax;
	}

	public void setCodPax(String codPax) {
		this.codPax = codPax;
	}
	
	
	@Column(name = "terreno", nullable = true, length = 2)
	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	@Column(name = "hora_bhc", nullable = true, length = 50)
	public String getHoraBHC() {
		return horaBHC;
	}

	public void setHoraBHC(String horaBHC) {
		this.horaBHC = horaBHC;
	}

	@Column(name = "hora_pbmc", nullable = true, length = 50)
	public String getHoraPBMC() {
		return horaPBMC;
	}

	public void setHoraPBMC(String horaPBMC) {
		this.horaPBMC = horaPBMC;
	}

	@EmbeddedId
	public MuestraId getmId() {
		return mId;
	}

	public void setmId(MuestraId mId) {
		this.mId = mId;
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

    @Column(name = "est_actuales", nullable = true, length = 255)
    public String getEstudiosAct() {
        return estudiosAct;
    }

    public void setEstudiosAct(String estudiosAct) {
        this.estudiosAct = estudiosAct;
    }

    @Column(name = "hd_sn", nullable = true, length = 2)
    public String getHd_sn() {
        return hd_sn;
    }

    public void setHd_sn(String hd_sn) {
        this.hd_sn = hd_sn;
    }

    @Column(name = "hd_porque_no", nullable = true, length = 512)
    public String getHdPorqueNo() {
        return hdPorqueNo;
    }

    public void setHdPorqueNo(String hdPorqueNo) {
        this.hdPorqueNo = hdPorqueNo;
    }

	@Column(name = "tubopax")
	public Integer getTuboPax() {
		return tuboPax;
	}

	public void setTuboPax(Integer tuboPax) {
		this.tuboPax = tuboPax;
	}

    @Column(name = "observacion", nullable = true, length = 250)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
