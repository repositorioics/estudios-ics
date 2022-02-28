package ni.org.ics.estudios.domain.Pbmc;

import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS on 07/02/2022.
 */

@Entity
@Table(name = "pbmc_detalle_envio", catalog = "estudios_ics")
public class Pbmc_Detalle_Envio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer detalle_id;
    private SerologiaEnvio serologiaEnvio;// Master Tabla Envios_Muestras
    private Pbmc pbmc;


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
    @JoinColumn(name="ENVIO_MUESTRA_ID", updatable = false, nullable = false, referencedColumnName = "ENVIO_MUESTRA_ID")
    @ForeignKey(name = "FK_ENVIO_MUESTRA_ID")
    public SerologiaEnvio getSerologiaEnvio() {
        return serologiaEnvio;
    }

    public void setSerologiaEnvio(SerologiaEnvio serologiaEnvio) {
        this.serologiaEnvio = serologiaEnvio;
    }

    @ManyToOne
    @JoinColumn(name="ID_PBMC", updatable = false, nullable = false, referencedColumnName ="ID_PBMC")
    @ForeignKey(name = "FK_ID_PBMC")
    public Pbmc getPbmc() {
        return pbmc;
    }

    public void setPbmc(Pbmc pbmc) {
        this.pbmc = pbmc;
    }
}
