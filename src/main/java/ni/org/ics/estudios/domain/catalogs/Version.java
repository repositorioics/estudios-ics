package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 14/01/2020.
 */

@Entity
@Table(name = "scan_catalog_version", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "idversion") })
public class Version extends BaseMetaData implements Auditable{

    private static final long serialVersionUID = 1L;

    private Integer idversion;
    private Estudio estudio;
    private String version;
    private String fecha_version;
    private String fecha_format;
    private boolean activo;

    @Id
    @Column(name = "IDVERSION", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdversion() {
        return idversion;
    }

    public void setIdversion(Integer idversion) {
        this.idversion = idversion;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_ESTUDIO", nullable = false)
    @ForeignKey(name = "FK_CODIGO_ESTUDIO")
    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    @Column(name = "VERSION", length = 100)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "FECHA_VERSION")
    public String getFecha_version() {
        return fecha_version;
    }

    public void setFecha_version(String fecha_version) {
        this.fecha_version = fecha_version;
    }

    @Column(name = "FECHA_FORMAT")
    public String getFecha_format() {
        return fecha_format;
    }

    public void setFecha_format(String fecha_format) {
        this.fecha_format = fecha_format;
    }

    @Column(name = "ACTIVO", nullable = true)
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        return idversion.hashCode();
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
