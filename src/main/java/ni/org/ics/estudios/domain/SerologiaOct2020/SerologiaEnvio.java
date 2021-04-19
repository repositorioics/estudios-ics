package ni.org.ics.estudios.domain.SerologiaOct2020;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 17/10/2020.
 */
@Entity
@Table(name = "chf_serologia_envios", catalog = "estudios_ics")
public class SerologiaEnvio extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;


    private Integer idserologiaenvio;

    private Serologia serologia;

    private Integer idenvio;

    private Date fecha;

    private String hora;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDSEROLOGIAENVIO", nullable = false)
    public Integer getIdserologiaenvio() {
        return idserologiaenvio;
    }

    public void setIdserologiaenvio(Integer idserologiaenvio) {
        this.idserologiaenvio = idserologiaenvio;
    }

    @ManyToOne
    @JoinColumn(name="IDSEROLOGIA", updatable = false)
    @ForeignKey(name = "FK_IDSEROLOGIA")
    public Serologia getSerologia() {
        return serologia;
    }

    public void setSerologia(Serologia serologia) {
        this.serologia = serologia;
    }



    @Column(name = "IDENVIO", nullable = false)
    public Integer getIdenvio() {
        return idenvio;
    }

    public void setIdenvio(Integer idenvio) {
        this.idenvio = idenvio;
    }

    @Column(name = "FECHA_ENVIO", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @Column(name = "HORA_ENVIO", nullable = false)
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }


}
