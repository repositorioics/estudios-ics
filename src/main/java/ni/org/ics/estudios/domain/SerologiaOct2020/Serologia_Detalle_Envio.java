package ni.org.ics.estudios.domain.SerologiaOct2020;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS on 17/10/2020.
 */


@Entity
@Table(name = "serologia_detalle_envio", catalog = "estudios_ics")
public class Serologia_Detalle_Envio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer detalle_id;
    private SerologiaEnvio serologiaEnvio;
    private Serologia serologia;


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
    @JoinColumn(name="ID_SEROLOGIA", nullable = false,referencedColumnName = "ID_SEROLOGIA" )
    @ForeignKey(name = "FK_ID_SEROLOGIA")
    public Serologia getSerologia() {
        return serologia;
    }

    public void setSerologia(Serologia serologia) {
        this.serologia = serologia;
    }


}
