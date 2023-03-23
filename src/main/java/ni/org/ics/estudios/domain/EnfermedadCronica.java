package ni.org.ics.estudios.domain;

import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Miguel Salinas on 16/08/2018.
 * V1.0
 */
@Entity
@Table(name = "enfermedades_cronicas", catalog = "estudios_ics")
public class EnfermedadCronica extends BaseMetaData implements Serializable, Auditable {

    private static final long serialVersionUID = 1L;

    private String Id;
    private Tamizaje tamizaje;
    private String enfermedad;
    private String anioEnfermedad;
    private String mesEnfermedad;
    private String otraEnfermedad;

    @Id
    @Column(name = "CODIGO", nullable = false, insertable = true, updatable = false, length = 36)
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_TAMIZAJE", nullable = false)
    @ForeignKey(name = "FK_TAMIZAJE_ENFCRONICA")
    public Tamizaje getTamizaje() {
        return tamizaje;
    }

    public void setTamizaje(Tamizaje tamizaje) {
        this.tamizaje = tamizaje;
    }

    @Column(name="ENFERMEDAD", nullable = true, length = 2)
    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Column(name="ANIO", nullable = true, length = 4)
    public String getAnioEnfermedad() {
        return anioEnfermedad;
    }

    public void setAnioEnfermedad(String anioEnfermedad) {
        this.anioEnfermedad = anioEnfermedad;
    }

    @Column(name="MES", nullable = true, length = 2)
    public String getMesEnfermedad() {
        return mesEnfermedad;
    }

    public void setMesEnfermedad(String mesEnfermedad) {
        this.mesEnfermedad = mesEnfermedad;
    }

    @Column(name="OTRA_ENFERMEDAD", nullable = true)
    public String getOtraEnfermedad() {
        return otraEnfermedad;
    }

    public void setOtraEnfermedad(String otraEnfermedad) {
        this.otraEnfermedad = otraEnfermedad;
    }

    @Override
    public String toString() {
        return "EnfermedadCronica{'" + Id + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnfermedadCronica)) return false;

        EnfermedadCronica that = (EnfermedadCronica) o;

        if (!Id.equals(that.Id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Id.hashCode();
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
