package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

/**
 * Created by ICS_Inspiron3 on 23/07/2019.
 */
@Entity
@Table(name = "areaTrabajo", catalog = "estudios_ics")
public class AreaTrabajo extends BaseMetaData {
    private static final long serialVersionUID = 1L;

    private Integer areaId;
    private String nombreArea;
    private Character activo;

    @Id
    @Column(name = "CODIGOAREA", nullable = false, length = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }


    @Column(name = "NOMBREAREA", nullable = false, length = 50)
    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    @Column(name = "ACTIVO", nullable = false, length = 1)
    public Character getActivo() {
        return activo;
    }

    public void setActivo(Character activo) {
        this.activo = activo;
    }
}
