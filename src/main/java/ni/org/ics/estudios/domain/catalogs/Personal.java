package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by ICS_Inspiron3 on 23/07/2019.
 */
@Entity
@Table(name = "Personal", catalog = "estudios_ics")
public class Personal extends BaseMetaData implements Auditable {
    private static final long serialVersionUID = 1L;

    private Integer codigo;

    private Integer idPersona;

    private String nombre;

    private Cargo cargo;

    @Id
    @Column(name = "CODIGO", nullable = false, length = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


    @Override
    public String toString(){
        return "'" + codigo + "'";
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal)) return false;

        Personal personal = (Personal) o;

        return (codigo.equals(personal.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
    @ManyToOne
    @JoinColumn(name="idCargo", updatable = false)
    @ForeignKey(name = "FK_idCargo")
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    @Column(name = "idPersona", nullable = false, length = 4)
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
