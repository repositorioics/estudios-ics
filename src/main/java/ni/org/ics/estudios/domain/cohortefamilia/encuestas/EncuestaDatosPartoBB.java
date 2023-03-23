package ni.org.ics.estudios.domain.cohortefamilia.encuestas;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Simple objeto de dominio que representa los datos de parto de los
 * participantes en el estudio
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "chf_encuestas_partobb", catalog = "estudios_ics")
public class EncuestaDatosPartoBB extends BaseMetaData implements Auditable {

	/**
	 * 
	 */
    private ParticipanteCohorteFamilia participante;
	private String tipoParto;
	private String tiempoEmb_sndr;
	private Integer tiempoEmbSemana;
	private String docMedTiempoEmb_sn;
	private String docMedTiempoEmb;
	private String otroDocMedTiempoEmb;
	private Date fum;
	private Integer sg;
	private String fumFueraRango_sn;
	private String fumFueraRango_razon;
	private Integer edadGest;
	private String docMedEdadGest_sn;
	private String docMedEdadGest;
	private String OtroDocMedEdadGest;
	private String prematuro_sndr;
	private String pesoBB_sndr;
	private Integer pesoBB;
	private String docMedPesoBB_sn;
	private String docMedPesoBB;
	private String otroDocMedPesoBB;
	private String recurso1;
	private String otrorecurso1;

    @Id
    @OneToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTECHF_DATOSPARTOBB")
    public ParticipanteCohorteFamilia getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteCohorteFamilia participante) {
        this.participante = participante;
    }

	@Column(name = "TIPO_PARTO", nullable = true, length = 5)
	public String getTipoParto() {
		return tipoParto;
	}

	public void setTipoParto(String tipoParto) {
		this.tipoParto = tipoParto;
	}

	@Column(name = "TIEMPO_EMB_SNDR", nullable = true, length = 5)
	public String getTiempoEmb_sndr() {
		return tiempoEmb_sndr;
	}

	public void setTiempoEmb_sndr(String tiempoEmb_sndr) {
		this.tiempoEmb_sndr = tiempoEmb_sndr;
	}

	@Column(name = "TIEMPO_EMB_SEMANA", nullable = true, length = 2)
	public Integer getTiempoEmbSemana() {
		return tiempoEmbSemana;
	}

	public void setTiempoEmbSemana(Integer tiempoEmbSemana) {
		this.tiempoEmbSemana = tiempoEmbSemana;
	}

	@Column(name = "DOC_MED_TIEMPO_EMB_SN", nullable = true, length = 1)
	public String getDocMedTiempoEmb_sn() {
		return docMedTiempoEmb_sn;
	}

	public void setDocMedTiempoEmb_sn(String docMedTiempoEmb_sn) {
		this.docMedTiempoEmb_sn = docMedTiempoEmb_sn;
	}

	@Column(name = "DOC_MED_TIEMPO_EMB", nullable = true, length = 5)
	public String getDocMedTiempoEmb() {
		return docMedTiempoEmb;
	}

	public void setDocMedTiempoEmb(String docMedTiempoEmb) {
		this.docMedTiempoEmb = docMedTiempoEmb;
	}

	@Column(name = "OTRO_DOC_MED_TIEMPO_EMB", nullable = true, length = 500)
	public String getOtroDocMedTiempoEmb() {
		return otroDocMedTiempoEmb;
	}

	public void setOtroDocMedTiempoEmb(String otroDocMedTiempoEmb) {
		this.otroDocMedTiempoEmb = otroDocMedTiempoEmb;
	}

	@Column(name = "FUM", nullable = true)
	public Date getFum() {
		return fum;
	}

	public void setFum(Date fum) {
		this.fum = fum;
	}

	@Column(name = "SG", nullable = true, length = 2)
	public Integer getSg() {
		return sg;
	}

	public void setSg(Integer sg) {
		this.sg = sg;
	}

	@Column(name = "EDAD_GEST", nullable = true, length = 2)
	public Integer getEdadGest() {
		return edadGest;
	}

	public void setEdadGest(Integer edadGest) {
		this.edadGest = edadGest;
	}

	@Column(name = "DOC_MED_EDAD_GEST_SN", nullable = true, length = 1)
	public String getDocMedEdadGest_sn() {
		return docMedEdadGest_sn;
	}

	public void setDocMedEdadGest_sn(String docMedEdadGest_sn) {
		this.docMedEdadGest_sn = docMedEdadGest_sn;
	}

	@Column(name = "DOC_MED_EDAD_GEST", nullable = true, length = 5)
	public String getDocMedEdadGest() {
		return docMedEdadGest;
	}

	public void setDocMedEdadGest(String docMedEdadGest) {
		this.docMedEdadGest = docMedEdadGest;
	}

	@Column(name = "OTRO_DOC_MED_EDAD_GEST", nullable = true, length = 500)
	public String getOtroDocMedEdadGest() {
		return OtroDocMedEdadGest;
	}

	public void setOtroDocMedEdadGest(String otroDocMedEdadGest) {
		OtroDocMedEdadGest = otroDocMedEdadGest;
	}

	@Column(name = "PREMATURO_SNDR", nullable = true, length = 5)
	public String getPrematuro_sndr() {
		return prematuro_sndr;
	}

	public void setPrematuro_sndr(String prematuro_sndr) {
		this.prematuro_sndr = prematuro_sndr;
	}

	@Column(name = "PESO_BB_SNDR", nullable = true, length = 5)
	public String getPesoBB_sndr() {
		return pesoBB_sndr;
	}

	public void setPesoBB_sndr(String pesoBB_sndr) {
		this.pesoBB_sndr = pesoBB_sndr;
	}

	@Column(name = "PESO_BB", nullable = true, length = 5)
	public Integer getPesoBB() {
		return pesoBB;
	}

	public void setPesoBB(Integer pesoBB) {
		this.pesoBB = pesoBB;
	}

	@Column(name = "DOC_MED_PESO_BB_SN", nullable = true, length = 1)
	public String getDocMedPesoBB_sn() {
		return docMedPesoBB_sn;
	}

	public void setDocMedPesoBB_sn(String docMedPesoBB_sn) {
		this.docMedPesoBB_sn = docMedPesoBB_sn;
	}

	@Column(name = "DOC_MED_PESO_BB", nullable = true, length = 5)
	public String getDocMedPesoBB() {
		return docMedPesoBB;
	}

	public void setDocMedPesoBB(String docMedPesoBB) {
		this.docMedPesoBB = docMedPesoBB;
	}

	@Column(name = "OTRO_DOC_MED_PESO_BB", nullable = true, length = 500)
	public String getOtroDocMedPesoBB() {
		return otroDocMedPesoBB;
	}

	public void setOtroDocMedPesoBB(String otroDocMedPesoBB) {
		this.otroDocMedPesoBB = otroDocMedPesoBB;
	}

	@Column(name = "RECURSO1", nullable = true, length = 50)
	public String getRecurso1() {
		return recurso1;
	}

	public void setRecurso1(String otrorecurso1) {
		this.recurso1 = otrorecurso1;
	}

	@Column(name = "OTRO_RECURSO1", nullable = true, length = 50)
	public String getOtrorecurso1() {
		return otrorecurso1;
	}

	public void setOtrorecurso1(String otrorecurso2) {
		this.otrorecurso1 = otrorecurso2;
	}

	@Column(name = "FUM_FUERA_RANGO_SN", nullable = true, length = 2)
	public String getFumFueraRango_sn() {
		return fumFueraRango_sn;
	}

	public void setFumFueraRango_sn(String fumFueraRango_sn) {
		this.fumFueraRango_sn = fumFueraRango_sn;
	}

	@Column(name = "FUM_FUERARANGO_RAZON", nullable = true, length = 750)
	public String getFumFueraRango_razon() {
		return fumFueraRango_razon;
	}

	public void setFumFueraRango_razon(String fumFueraRango_razon) {
		this.fumFueraRango_razon = fumFueraRango_razon;
	}

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaDatosPartoBB{" + participante.getParticipante().getCodigo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaDatosPartoBB)) return false;

        EncuestaDatosPartoBB that = (EncuestaDatosPartoBB) o;

        return  (!participante.equals(that.participante));
    }

    @Override
    public int hashCode() {
        return participante.hashCode();
    }
}
