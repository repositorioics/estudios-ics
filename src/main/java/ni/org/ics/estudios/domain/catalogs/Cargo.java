package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by ICS_Inspiron3 on 23/07/2019.
 */

@Entity
@Table(name = "Cargo", catalog = "estudios_ics")
public class Cargo extends BaseMetaData {

    private static final long serialVersionUID = 1L;

    private Integer codigo;
    private String nombre;
    private Character activo;

    private AreaTrabajo areatrabajo;

    @Id
    @Column(name = "CargoId", nullable = false, length = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOMBRECARGO", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Column(name = "ACTIVO", nullable = false, length = 1)
    public Character getActivo() {
        return activo;
    }

    public void setActivo(Character activo) {
        this.activo = activo;
    }

    @ManyToOne
    @JoinColumn(name="idAreaTrabajo", updatable = false)
    @ForeignKey(name = "FK_idAreaTrabajo")
    public AreaTrabajo getAreatrabajo() {
        return areatrabajo;
    }

    public void setAreatrabajo(AreaTrabajo areatrabajo) {
        this.areatrabajo = areatrabajo;
    }
}
