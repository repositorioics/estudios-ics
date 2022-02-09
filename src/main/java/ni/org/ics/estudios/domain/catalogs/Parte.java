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
@Table(name = "scan_catalog_parte", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "IDPARTE") })
public class Parte extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer idparte;
    private Version version;
    private String parte;
    private String acepta;
    private boolean activo;
    private boolean principal;


    @Id
    @Column(name = "IDPARTE", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdparte() {
        return idparte;
    }

    public void setIdparte(Integer idparte) {
        this.idparte = idparte;
    }

    @ManyToOne
    @JoinColumn(name = "IDVERSION", nullable = false)
    @ForeignKey(name = "FK_VERSION_PARTE")
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }
    @Column(name = "PARTE", nullable = false)
    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    @Column(name = "ACEPTA", nullable = false)
    public String getAcepta() {
        return acepta;
    }

    public void setAcepta(String acepta) {
        this.acepta = acepta;
    }

    @Column(name = "ACTIVO", nullable = true)
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Column(name = "PRINCIPAL", nullable = true)
    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
