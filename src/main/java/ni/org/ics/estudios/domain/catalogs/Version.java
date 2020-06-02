package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by ICS on 14/01/2020.
 */

@Entity
@Table(name = "version", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "idversion") })
public class Version extends BaseMetaData implements Auditable{

    private static final long serialVersionUID = 1L;

    private Integer idversion;
    private Carta carta;
    private String version;
    private String cod;
    private String activo;

    @Id
    @Column(name = "idversion", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdversion() {
        return idversion;
    }

    public void setIdversion(Integer idversion) {
        this.idversion = idversion;
    }
    @ManyToOne
    @JoinColumn(name = "idcarta", nullable = false)
    @ForeignKey(name = "FK_CARTA_VERSION")
    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }
    @Column(name = "version", length = 100)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    @Column(name = "cod", length = 100)
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @Column(name = "activo", length = 20, nullable = true)
    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
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
