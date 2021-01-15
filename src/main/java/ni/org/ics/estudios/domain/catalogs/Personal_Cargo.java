package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS on 04/01/2021.
 */
@Entity
@Table(name = "personal_cargo",  catalog = "estudios_ics")
public class Personal_Cargo implements Auditable {

    private Integer id;
    private Cargo cargo;
    private Personal personal;
    private boolean estado;

    public Personal_Cargo(Integer id, Cargo cargo, Personal personal, boolean estado) {
        this.id = id;
        this.cargo = cargo;
        this.personal = personal;
        this.estado = estado;
    }

    public Personal_Cargo(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CARGO", nullable = false)
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PERSONAL", nullable = false)
    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    @Column(name = "ACTIVO")
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }






}
