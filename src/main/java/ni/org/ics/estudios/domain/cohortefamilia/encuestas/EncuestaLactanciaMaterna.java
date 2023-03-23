package ni.org.ics.estudios.domain.cohortefamilia.encuestas;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa los datos de la lactancia materna
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "chf_encuestas_lactancia_materna", catalog = "estudios_ics")
public class EncuestaLactanciaMaterna extends BaseMetaData implements Auditable {

	/**
	 * 
	 */

    private ParticipanteCohorteFamilia participante;
    private String dioPecho;
    private String tiemPecho;
    private Integer mesDioPecho;
    private String pechoExc;
    private String formAlim;
    private String pechoExcAntes;
    private String tiempPechoExcAntes;
    private Integer mestPechoExc;
    private String otraAlim;
    private String edadLiqDistPecho;
    private Integer mesDioLiqDisPecho;
    private String edadLiqDistLeche;
    private Integer mesDioLiqDisLeche;
    private String edAlimSolidos;
    private Integer mesDioAlimSol;
    private String recurso1;
    private String otrorecurso1;

    @Id
    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTECHF_LACTANCIAMAT")
    public ParticipanteCohorteFamilia getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteCohorteFamilia participante) {
        this.participante = participante;
    }

	@Column(name = "DIO_PECHO", nullable = true, length = 5)
	public String getDioPecho() {
		return dioPecho;
	}

	public void setDioPecho(String dioPecho) {
		this.dioPecho = dioPecho;
	}

	@Column(name = "TIEMPO_PECHO", nullable = true, length = 50)
	public String getTiemPecho() {
		return tiemPecho;
	}

	public void setTiemPecho(String tiemPecho) {
		this.tiemPecho = tiemPecho;
	}

	@Column(name = "MES_DIO_PECHO", nullable = true)
	public Integer getMesDioPecho() {
		return mesDioPecho;
	}

	public void setMesDioPecho(Integer mesDioPecho) {
		this.mesDioPecho = mesDioPecho;
	}

	@Column(name = "PECHO_EXCLU", nullable = true, length = 50)
	public String getPechoExc() {
		return pechoExc;
	}

	public void setPechoExc(String pechoExc) {
		this.pechoExc = pechoExc;
	}

	@Column(name = "PECHO_EXCLU_ANTES", nullable = true, length = 50)
	public String getPechoExcAntes() {
		return pechoExcAntes;
	}

	public void setPechoExcAntes(String pechoExcAntes) {
		this.pechoExcAntes = pechoExcAntes;
	}

	@Column(name = "TIEMPO_PECHO_EXCLU_ANTES", nullable = true, length = 50)
	public String getTiempPechoExcAntes() {
		return tiempPechoExcAntes;
	}

	public void setTiempPechoExcAntes(String tiempPechoExcAntes) {
		this.tiempPechoExcAntes = tiempPechoExcAntes;
	}

	@Column(name = "MES_PECHO_EXCLU_ANTES", nullable = true)
	public Integer getMestPechoExc() {
		return mestPechoExc;
	}

	public void setMestPechoExc(Integer mestPechoExc) {
		this.mestPechoExc = mestPechoExc;
	}

	@Column(name = "FORMA_ALIMENTA", nullable = true, length = 50)
	public String getFormAlim() {
		return formAlim;
	}

	public void setFormAlim(String formAlim) {
		this.formAlim = formAlim;
	}

	@Column(name = "OTRA_ALIMENTACION", nullable = true, length = 50)
	public String getOtraAlim() {
		return otraAlim;
	}

	public void setOtraAlim(String otraAlim) {
		this.otraAlim = otraAlim;
	}

	@Column(name = "EDAD_LIQUIDO_DIST_PECHO", nullable = true, length = 50)
	public String getEdadLiqDistPecho() {
		return edadLiqDistPecho;
	}

	public void setEdadLiqDistPecho(String edadLiqDistPecho) {
		this.edadLiqDistPecho = edadLiqDistPecho;
	}

	@Column(name = "MES_DIO_LIQ_DIST_PECHO", nullable = true)
	public Integer getMesDioLiqDisPecho() {
		return mesDioLiqDisPecho;
	}

	public void setMesDioLiqDisPecho(Integer mesDioLiqDisPecho) {
		this.mesDioLiqDisPecho = mesDioLiqDisPecho;
	}

	@Column(name = "EDAD_LIQUIDO_DIST_LECHE", nullable = true, length = 50)
	public String getEdadLiqDistLeche() {
		return edadLiqDistLeche;
	}

	public void setEdadLiqDistLeche(String edadLiqDistLeche) {
		this.edadLiqDistLeche = edadLiqDistLeche;
	}
	
	@Column(name = "MES_DIO_LIQ_DIST_LECHE", nullable = true)
	public Integer getMesDioLiqDisLeche() {
		return mesDioLiqDisLeche;
	}

	public void setMesDioLiqDisLeche(Integer mesDioLiqDisLeche) {
		this.mesDioLiqDisLeche = mesDioLiqDisLeche;
	}

	@Column(name = "EDAD_ALIMEN_SOLIDOS", nullable = true, length = 50)
	public String getEdAlimSolidos() {
		return edAlimSolidos;
	}

	public void setEdAlimSolidos(String edAlimSolidos) {
		this.edAlimSolidos = edAlimSolidos;
	}

	@Column(name = "MES_DIO_ALIMEN_SOLIDOS", nullable = true)
	public Integer getMesDioAlimSol() {
		return mesDioAlimSol;
	}

	public void setMesDioAlimSol(Integer mesDioAlimSol) {
		this.mesDioAlimSol = mesDioAlimSol;
	}

	@Column(name = "RECURSO1", nullable = true, length = 50)
	public String getRecurso1() {
		return recurso1;
	}

	public void setRecurso1(String recurso1) {
		this.recurso1 = recurso1;
	}

	@Column(name = "OTRO_RECURSO1", nullable = true, length = 50)
	public String getOtrorecurso1() {
		return otrorecurso1;
	}

	public void setOtrorecurso1(String otrorecurso1) {
		this.otrorecurso1 = otrorecurso1;
	}

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaLactanciaMaterna{" + participante.getParticipante().getCodigo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaLactanciaMaterna)) return false;

        EncuestaLactanciaMaterna that = (EncuestaLactanciaMaterna) o;

        return  (!participante.equals(that.participante));
    }

    @Override
    public int hashCode() {
        return participante.hashCode();
    }

}
