package ni.org.ics.estudios.domain.muestreoanual;

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
@Table(name = "datosvisitasterreno", catalog = "estudios_ics")
public class DatosVisitaTerreno {

	/**
	 * 
	 */
	private DatosVisitaTerrenoId visitaId;
	private Integer codCasa;
	private Integer cDom;
	private Integer barrio;
	private Integer manzana;
	private String direccion;
	private String coordenadas;
	private Double latitud;
	private Double longitud;
	
	private String telefonoClasif1;
	private String telefonoConv1;
	private String telefonoCel1;
	private String telefonoEmpresa1;
	private String telefono2SN;
	private String telefonoClasif2;
	private String telefonoConv2;
	private String telefonoCel2;
	private String telefonoEmpresa2;
	private String telefono3SN;
	private String telefonoClasif3;
	private String telefonoConv3;
	private String telefonoCel3;
	private String telefonoEmpresa3;
	private String telefono4SN;
	private String telefonoClasif4;
	private String telefonoConv4;
	private String telefonoCel4;
	private String telefonoEmpresa4;
	private String candidatoNI;
	private String nombreCandNI1;
	private String nombreCandNI2;
	private String apellidoCandNI1;
	private String apellidoCandNI2;
	private String nombreptTutorCandNI;
	private String nombreptTutorCandNI2;
	private String apellidoptTutorCandNI;
	private String apellidoptTutorCandNI2;
	private String relacionFamCandNI;
	private String otraRelacionFamCandNI;
	
	private MovilInfo movilInfo;
	private Integer otrorecurso1;
	private Integer otrorecurso2;

	@EmbeddedId
	public DatosVisitaTerrenoId getVisitaId() {
		return visitaId;
	}

	public void setVisitaId(DatosVisitaTerrenoId visitaId) {
		this.visitaId = visitaId;
	}
	
	@Column(name = "codcasa", nullable = false)
	public Integer getCodCasa() {
		return codCasa;
	}

	public void setCodCasa(Integer codCasa) {
		this.codCasa = codCasa;
	}

	@Column(name = "cdom", nullable = true)
	public Integer getcDom() {
		return cDom;
	}

	public void setcDom(Integer cDom) {
		this.cDom = cDom;
	}

	@Column(name = "barrio", nullable = true)
	public Integer getBarrio() {
		return barrio;
	}

	public void setBarrio(Integer barrio) {
		this.barrio = barrio;
	}

	@Column(name = "direccion", nullable = true)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "manzana", nullable = true)
	public Integer getManzana() {
		return manzana;
	}

	public void setManzana(Integer manzana) {
		this.manzana = manzana;
	}

	@Column(name = "coordenadas", nullable = true)
	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}
	
	@Column(name = "latitud", nullable = true)
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	@Column(name = "longitud", nullable = true)
	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
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

	@Column(name = "telefonoClasif1", nullable = true, length = 50)
	public String getTelefonoClasif1() {
		return telefonoClasif1;
	}

	public void setTelefonoClasif1(String telefonoClasif1) {
		this.telefonoClasif1 = telefonoClasif1;
	}

	@Column(name = "telefonoConv1", nullable = true, length = 50)
	public String getTelefonoConv1() {
		return telefonoConv1;
	}

	public void setTelefonoConv1(String telefonoConv1) {
		this.telefonoConv1 = telefonoConv1;
	}

	@Column(name = "telefonoCel1", nullable = true, length = 50)
	public String getTelefonoCel1() {
		return telefonoCel1;
	}

	public void setTelefonoCel1(String telefonoCel1) {
		this.telefonoCel1 = telefonoCel1;
	}

	@Column(name = "telefonoEmpresa1", nullable = true, length = 50)
	public String getTelefonoEmpresa1() {
		return telefonoEmpresa1;
	}

	public void setTelefonoEmpresa1(String telefonoEmpresa1) {
		this.telefonoEmpresa1 = telefonoEmpresa1;
	}

	@Column(name = "telefono2SN", nullable = true, length = 2)
	public String getTelefono2SN() {
		return telefono2SN;
	}

	public void setTelefono2SN(String telefono2sn) {
		telefono2SN = telefono2sn;
	}

	@Column(name = "telefonoClasif2", nullable = true, length = 50)
	public String getTelefonoClasif2() {
		return telefonoClasif2;
	}

	public void setTelefonoClasif2(String telefonoClasif2) {
		this.telefonoClasif2 = telefonoClasif2;
	}

	@Column(name = "telefonoConv2", nullable = true, length = 50)
	public String getTelefonoConv2() {
		return telefonoConv2;
	}

	public void setTelefonoConv2(String telefonoConv2) {
		this.telefonoConv2 = telefonoConv2;
	}

	@Column(name = "telefonoCel2", nullable = true, length = 50)
	public String getTelefonoCel2() {
		return telefonoCel2;
	}

	public void setTelefonoCel2(String telefonoCel2) {
		this.telefonoCel2 = telefonoCel2;
	}

	@Column(name = "telefonoEmpresa2", nullable = true, length = 50)
	public String getTelefonoEmpresa2() {
		return telefonoEmpresa2;
	}

	public void setTelefonoEmpresa2(String telefonoEmpresa2) {
		this.telefonoEmpresa2 = telefonoEmpresa2;
	}

	@Column(name = "telefono3SN", nullable = true, length = 2)
	public String getTelefono3SN() {
		return telefono3SN;
	}

	public void setTelefono3SN(String telefono3sn) {
		telefono3SN = telefono3sn;
	}

	@Column(name = "telefonoClasif3", nullable = true, length = 50)
	public String getTelefonoClasif3() {
		return telefonoClasif3;
	}

	public void setTelefonoClasif3(String telefonoClasif3) {
		this.telefonoClasif3 = telefonoClasif3;
	}

	@Column(name = "telefonoConv3", nullable = true, length = 50)
	public String getTelefonoConv3() {
		return telefonoConv3;
	}

	public void setTelefonoConv3(String telefonoConv3) {
		this.telefonoConv3 = telefonoConv3;
	}

	@Column(name = "telefonoCel3", nullable = true, length = 50)
	public String getTelefonoCel3() {
		return telefonoCel3;
	}

	public void setTelefonoCel3(String telefonoCel3) {
		this.telefonoCel3 = telefonoCel3;
	}

	@Column(name = "telefonoEmpresa3", nullable = true, length = 50)
	public String getTelefonoEmpresa3() {
		return telefonoEmpresa3;
	}

	public void setTelefonoEmpresa3(String telefonoEmpresa3) {
		this.telefonoEmpresa3 = telefonoEmpresa3;
	}

	@Column(name = "telefono4SN", nullable = true, length = 2)
	public String getTelefono4SN() {
		return telefono4SN;
	}

	public void setTelefono4SN(String telefono4sn) {
		telefono4SN = telefono4sn;
	}

	@Column(name = "telefonoClasif4", nullable = true, length = 50)
	public String getTelefonoClasif4() {
		return telefonoClasif4;
	}

	public void setTelefonoClasif4(String telefonoClasif4) {
		this.telefonoClasif4 = telefonoClasif4;
	}

	@Column(name = "telefonoConv4", nullable = true, length = 50)
	public String getTelefonoConv4() {
		return telefonoConv4;
	}

	public void setTelefonoConv4(String telefonoConv4) {
		this.telefonoConv4 = telefonoConv4;
	}

	@Column(name = "telefonoCel4", nullable = true, length = 50)
	public String getTelefonoCel4() {
		return telefonoCel4;
	}

	public void setTelefonoCel4(String telefonoCel4) {
		this.telefonoCel4 = telefonoCel4;
	}

	@Column(name = "telefonoEmpresa4", nullable = true, length = 50)
	public String getTelefonoEmpresa4() {
		return telefonoEmpresa4;
	}

	public void setTelefonoEmpresa4(String telefonoEmpresa4) {
		this.telefonoEmpresa4 = telefonoEmpresa4;
	}

	@Column(name = "candidatoNI", nullable = true, length = 2)
	public String getCandidatoNI() {
		return candidatoNI;
	}

	public void setCandidatoNI(String candidatoNI) {
		this.candidatoNI = candidatoNI;
	}

	@Column(name = "nombreCandNI1", nullable = true, length = 100)
	public String getNombreCandNI1() {
		return nombreCandNI1;
	}

	public void setNombreCandNI1(String nombreCandNI1) {
		this.nombreCandNI1 = nombreCandNI1;
	}

	@Column(name = "nombreCandNI2", nullable = true, length = 100)
	public String getNombreCandNI2() {
		return nombreCandNI2;
	}

	public void setNombreCandNI2(String nombreCandNI2) {
		this.nombreCandNI2 = nombreCandNI2;
	}

	@Column(name = "apellidoCandNI1", nullable = true, length = 100)
	public String getApellidoCandNI1() {
		return apellidoCandNI1;
	}

	public void setApellidoCandNI1(String apellidoCandNI1) {
		this.apellidoCandNI1 = apellidoCandNI1;
	}

	@Column(name = "apellidoCandNI2", nullable = true, length = 100)
	public String getApellidoCandNI2() {
		return apellidoCandNI2;
	}

	public void setApellidoCandNI2(String apellidoCandNI2) {
		this.apellidoCandNI2 = apellidoCandNI2;
	}

	@Column(name = "nombreptTutorCandNI", nullable = true, length = 100)
	public String getNombreptTutorCandNI() {
		return nombreptTutorCandNI;
	}

	public void setNombreptTutorCandNI(String nombreptTutorCandNI) {
		this.nombreptTutorCandNI = nombreptTutorCandNI;
	}

	@Column(name = "nombreptTutorCandNI2", nullable = true, length = 100)
	public String getNombreptTutorCandNI2() {
		return nombreptTutorCandNI2;
	}

	public void setNombreptTutorCandNI2(String nombreptTutorCandNI2) {
		this.nombreptTutorCandNI2 = nombreptTutorCandNI2;
	}

	@Column(name = "apellidoptTutorCandNI", nullable = true, length = 100)
	public String getApellidoptTutorCandNI() {
		return apellidoptTutorCandNI;
	}

	public void setApellidoptTutorCandNI(String apellidoptTutorCandNI) {
		this.apellidoptTutorCandNI = apellidoptTutorCandNI;
	}

	@Column(name = "apellidoptTutorCandNI2", nullable = true, length = 100)
	public String getApellidoptTutorCandNI2() {
		return apellidoptTutorCandNI2;
	}

	public void setApellidoptTutorCandNI2(String apellidoptTutorCandNI2) {
		this.apellidoptTutorCandNI2 = apellidoptTutorCandNI2;
	}

	@Column(name = "relacionFamCandNI", nullable = true, length = 2)
	public String getRelacionFamCandNI() {
		return relacionFamCandNI;
	}

	public void setRelacionFamCandNI(String relacionFamCandNI) {
		this.relacionFamCandNI = relacionFamCandNI;
	}

	@Column(name = "otraRelacionFamCandNI", nullable = true, length = 50)
	public String getOtraRelacionFamCandNI() {
		return otraRelacionFamCandNI;
	}

	public void setOtraRelacionFamCandNI(String otraRelacionFamCandNI) {
		this.otraRelacionFamCandNI = otraRelacionFamCandNI;
	}
	
}
