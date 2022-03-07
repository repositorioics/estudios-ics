package ni.org.ics.estudios.domain.Bhc;

import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS on 19/02/2022.
 */

@Entity
@Table(name = "bhc_detalle_envio", catalog = "estudios_ics")
public class Bhc_Detalle_envio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer detalle_id;
    private SerologiaEnvio serologiaEnvio;// Master Tabla Envios_Muestras
    private Bhc bhc;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DETALLE_ID", nullable = false)
    public Integer getDetalle_id() {
        return detalle_id;
    }

    public void setDetalle_id(Integer detalle_id) {
        this.detalle_id = detalle_id;
    }

    @ManyToOne
    @JoinColumn(name="ENVIO_MUESTRA_ID", nullable = false)
    public SerologiaEnvio getSerologiaEnvio() {
        return serologiaEnvio;
    }

    public void setSerologiaEnvio(SerologiaEnvio serologiaEnvio) {
        this.serologiaEnvio = serologiaEnvio;
    }


    @ManyToOne
    @JoinColumn(name="BHC_ID", updatable = false, nullable = false, referencedColumnName ="BHC_ID")
    @ForeignKey(name = "FK_BHC_ID")
    public Bhc getBhc() {
        return bhc;
    }

    public void setBhc(Bhc bhc) {
        this.bhc = bhc;
    }
}
