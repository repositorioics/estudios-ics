package ni.org.ics.estudios.domain.muestreoanual;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa los datos de la lactancia materna
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "lactancia_materna", catalog = "estudios_ics")
public class LactanciaMaterna {

	/**
	 * 
	 */
	
	private LactanciaMaternaId lmId;
	private Integer edad;
	private Integer dioPecho;
	private Integer tiemPecho;
	private Integer mesDioPecho;
	private Integer pechoExc;
	private Integer pechoExcAntes;
	private Integer tiempPechoExcAntes;
	private Integer mestPechoExc;
	private Integer formAlim;
	private String otraAlim;
	private Integer edadLiqDistPecho;
	private Integer mesDioLiqDisPecho;
	private Integer edadLiqDistLeche;
	private Integer mesDioLiqDisLeche;
	private Integer edAlimSolidos;
	private Integer mesDioAlimSol;
	private MovilInfo movilInfo;
	private Integer otrorecurso1;
	private Integer otrorecurso2;

	@EmbeddedId
	public LactanciaMaternaId getLmId() {
		return lmId;
	}

	public void setLmId(LactanciaMaternaId lmId) {
		this.lmId = lmId;
	}
	@Column(name = "edad", nullable = true, length = 2)
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	
	@Column(name = "diopecho", nullable = true, length = 1)
	public Integer getDioPecho() {
		return dioPecho;
	}

	public void setDioPecho(Integer dioPecho) {
		this.dioPecho = dioPecho;
	}

	@Column(name = "tiempecho", nullable = true, length = 1)
	public Integer getTiemPecho() {
		return tiemPecho;
	}

	public void setTiemPecho(Integer tiemPecho) {
		this.tiemPecho = tiemPecho;
	}

	@Column(name = "mesdiopecho", nullable = true, length = 1)
	public Integer getMesDioPecho() {
		return mesDioPecho;
	}

	public void setMesDioPecho(Integer mesDioPecho) {
		this.mesDioPecho = mesDioPecho;
	}

	@Column(name = "pechoexc", nullable = true, length = 1)
	public Integer getPechoExc() {
		return pechoExc;
	}

	public void setPechoExc(Integer pechoExc) {
		this.pechoExc = pechoExc;
	}

	@Column(name = "pechoexcantes", nullable = true, length = 1)
	public Integer getPechoExcAntes() {
		return pechoExcAntes;
	}

	public void setPechoExcAntes(Integer pechoExcAntes) {
		this.pechoExcAntes = pechoExcAntes;
	}

	@Column(name = "tiempopechoexcantes", nullable = true, length = 1)
	public Integer getTiempPechoExcAntes() {
		return tiempPechoExcAntes;
	}

	public void setTiempPechoExcAntes(Integer tiempPechoExcAntes) {
		this.tiempPechoExcAntes = tiempPechoExcAntes;
	}

	@Column(name = "mespechoexcantes", nullable = true)
	public Integer getMestPechoExc() {
		return mestPechoExc;
	}

	public void setMestPechoExc(Integer mestPechoExc) {
		this.mestPechoExc = mestPechoExc;
	}

	@Column(name = "formalim", nullable = true, length = 1)
	public Integer getFormAlim() {
		return formAlim;
	}

	public void setFormAlim(Integer formAlim) {
		this.formAlim = formAlim;
	}

	@Column(name = "otraalim", nullable = true, length = 50)
	public String getOtraAlim() {
		return otraAlim;
	}

	public void setOtraAlim(String otraAlim) {
		this.otraAlim = otraAlim;
	}

	@Column(name = "edadliqdistpecho", nullable = true)
	public Integer getEdadLiqDistPecho() {
		return edadLiqDistPecho;
	}

	public void setEdadLiqDistPecho(Integer edadLiqDistPecho) {
		this.edadLiqDistPecho = edadLiqDistPecho;
	}

	@Column(name = "mesdioliqdispecho", nullable = true)
	public Integer getMesDioLiqDisPecho() {
		return mesDioLiqDisPecho;
	}

	public void setMesDioLiqDisPecho(Integer mesDioLiqDisPecho) {
		this.mesDioLiqDisPecho = mesDioLiqDisPecho;
	}

	@Column(name = "edadliqdistleche", nullable = true)
	public Integer getEdadLiqDistLeche() {
		return edadLiqDistLeche;
	}

	public void setEdadLiqDistLeche(Integer edadLiqDistLeche) {
		this.edadLiqDistLeche = edadLiqDistLeche;
	}
	
	@Column(name = "mesdioliqdisleche", nullable = true)
	public Integer getMesDioLiqDisLeche() {
		return mesDioLiqDisLeche;
	}

	public void setMesDioLiqDisLeche(Integer mesDioLiqDisLeche) {
		this.mesDioLiqDisLeche = mesDioLiqDisLeche;
	}

	@Column(name = "edalimsolidos", nullable = true)
	public Integer getEdAlimSolidos() {
		return edAlimSolidos;
	}

	public void setEdAlimSolidos(Integer edAlimSolidos) {
		this.edAlimSolidos = edAlimSolidos;
	}

	@Column(name = "mesdioalimsol", nullable = true)
	public Integer getMesDioAlimSol() {
		return mesDioAlimSol;
	}

	public void setMesDioAlimSol(Integer mesDioAlimSol) {
		this.mesDioAlimSol = mesDioAlimSol;
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

}
