package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

/**
 * Created by ICS on 11/11/2020.
 */
@Entity
@Table(name = "documentacion_retiro_razones", catalog = "estudios_ics")
public class Razones_Retiro implements Auditable {

    private static final long serialVersionUID = 1L;
    private Integer retiroid;
    private String motivo;
    private String Descripcion;
    private Integer grupoid;

    @Id
    @Column(name = "CODIGO_RAZON", nullable = false, length = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getRetiroid() {
        return retiroid;
    }

    public void setRetiroid(Integer retiroid) {
        this.retiroid = retiroid;
    }

    @Column(name = "MOTIVO", nullable = false, length = 20)
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Column(name = "DESCRIPCION", nullable = true, length = 200)
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    @Column(name = "GRUPO", nullable = true)
    public Integer getGrupoid() {
        return grupoid;
    }

    public void setGrupoid(Integer grupoid) {
        this.grupoid = grupoid;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return false;
    }

}
