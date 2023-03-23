package ni.org.ics.estudios.domain.scancarta;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.catalogs.Version;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 18/05/2021.
 */

@Entity
@Table(name = "scan_catalog_extensiones", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "ID_EXTENSION") })
public class Extensiones extends BaseMetaData implements Serializable {


    private Integer id;
    private String fecha_extension;
    private Version version;
    private String extension;
    private boolean isActive;
    private boolean anulada;

    public Extensiones() {}

    public Extensiones(Integer id, Version version, String extension, boolean isActive, String fecha_extension, boolean anulada) {
        this.id = id;
        this.version = version;
        this.extension = extension;
        this.isActive = isActive;
        this.fecha_extension = fecha_extension;
        this.anulada = anulada;
    }

    @Id
    @Column(name = "ID_EXTENSION", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "IDVERSION", nullable = false)
    @ForeignKey(name = "FK_IDVERSION")
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }
    @Column(name = "EXTENSION", nullable = false)
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    @Column(name = "ACTIVE")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Column(name = "FECHA_EXTENSION")
    public String getFecha_extension() {
        return fecha_extension;
    }

    public void setFecha_extension(String fecha_extension) {
        this.fecha_extension = fecha_extension;
    }

    @Column(name = "ANULADA")
    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }
}
