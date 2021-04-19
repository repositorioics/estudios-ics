package ni.org.ics.estudios.domain.SerologiaOct2020;

/**
 * Created by ICS on 17/10/2020.
 */

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chf_envios", catalog = "estudios_ics")
public class Envio extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer idenvio;

    private Integer numeroEnvio;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDENVIO", nullable = false)
    public Integer getIdenvio() {
        return idenvio;
    }

    public void setIdenvio(Integer idenvio) {
        this.idenvio = idenvio;
    }

    @Column(name = "NUMEROENVIO", nullable = true)
    public Integer getNumeroEnvio() {
        return numeroEnvio;
    }

    public void setNumeroEnvio(Integer numeroEnvio) {
        this.numeroEnvio = numeroEnvio;
    }


    @Override
    public int hashCode() {
        return idenvio.hashCode();
    }


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
