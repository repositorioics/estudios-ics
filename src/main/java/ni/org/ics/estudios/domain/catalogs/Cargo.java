package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS_Inspiron3 on 23/07/2019.
 */

@Entity
@Table(name = "cat_cargo", catalog = "estudios_ics")
public class Cargo extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idcargo;
    private String nombreCargo;
    private AreaTrabajo areatrabajo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARGO_ID")
    public Integer getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    @Column(name = "NOMBRE_CARGO")
    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }


    @ManyToOne
    @JoinColumn(name="CODIGO_AREATRABAJO", updatable = false)
    @ForeignKey(name = "FK_CODIGO_AREATRABAJO")
    public AreaTrabajo getAreatrabajo() {
        return areatrabajo;
    }

    public void setAreatrabajo(AreaTrabajo areatrabajo) {
        this.areatrabajo = areatrabajo;
    }

}
