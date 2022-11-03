package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by ICS on 04/01/2021.
 */
@Entity
@Table(name = "personal_cargo",  catalog = "estudios_ics")
public class Personal_Cargo extends BaseMetaData implements Auditable  {

    private static final long serialVersionUID = 1L;

    private Cargo cargo;
    private Personal personal;

    @Id
    @ManyToOne
    @JoinColumn(name="CARGO_ID", insertable = false, updatable = false)
    @ForeignKey(name = "FK_CARGO_ID")
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="PERSONA_ID", insertable = false, updatable = false)
    @ForeignKey(name = "FK_PERSONA_ID")
    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "PersonalCargo{" + personal.getNombreApellido() +
                ", " + cargo.getIdcargo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal_Cargo)) return false;

        Personal_Cargo that = (Personal_Cargo) o;

        if (!cargo.equals(that.cargo)) return false;
        if (!personal.equals(that.personal)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        return Objects.hash(cargo,personal);
    }
}
