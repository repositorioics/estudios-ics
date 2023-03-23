package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.Cama;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_persona_cama", catalog = "estudios_ics")
public class PersonaCama extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPersona;
    private String estaEnEstudio; //0 No, 1 Si
    private Participante participante; //si es participante
    private String sexo;  //si no participa
    private Integer edad;   //si no participa
    private Cama cama;

    @Id
    @Column(name = "CODIGO", length = 36)
    public String getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(String codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    @Column(name = "ESTA_EN_ESTUDIO")
    public String getEstaEnEstudio() {
        return estaEnEstudio;
    }

    public void setEstaEnEstudio(String estaEnEstudio) {
        this.estaEnEstudio = estaEnEstudio;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "CODIGO_PARTICIPANTE")
    @ForeignKey(name = "FK_PARTICIPANTE_PERSONAENCUESTA")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "SEXO_NOPARTICIPA", length = 50)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "EDAD_NOPARTICIPA")
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CAMA")
    @ForeignKey(name = "FK_CAMA_PERSONAENCUESTA")
    public Cama getCama() {
        return cama;
    }

    public void setCama(Cama cama) {
        this.cama = cama;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "PersonaCama{" + codigoPersona + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonaCama)) return false;

        PersonaCama that = (PersonaCama) o;

        return  (!codigoPersona.equals(that.codigoPersona));
    }

    @Override
    public int hashCode() {
        return codigoPersona.hashCode();
    }
}
