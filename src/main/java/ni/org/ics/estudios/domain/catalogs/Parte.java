package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by ICS on 14/01/2020.
 */

@Entity
@Table(name = "parte", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "idparte") })
public class Parte extends BaseMetaData implements Auditable {
    private static final long serialVersionUID = 1L;

    private Integer idparte;
    private Version version;
    private String parte;
    private String cod;
    private String acepta;
    private String activo;

    @Id
    @Column(name = "idparte", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdparte() {
        return idparte;
    }

    public void setIdparte(Integer idparte) {
        this.idparte = idparte;
    }

    @ManyToOne
    @JoinColumn(name = "idversion", nullable = false)
    @ForeignKey(name = "FK_VERSION_PARTE")
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }
    @Column(name = "parte", nullable = false)
    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @Column(name = "acepta", nullable = false)
    public String getAcepta() {
        return acepta;
    }

    public void setAcepta(String acepta) {
        this.acepta = acepta;
    }

    @Column(name = "activo", nullable = true)
    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
