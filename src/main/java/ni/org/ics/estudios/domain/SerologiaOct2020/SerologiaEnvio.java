package ni.org.ics.estudios.domain.SerologiaOct2020;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 17/10/2020.
 */
@Entity
@Table(name = "envio_muestras", catalog = "estudios_ics")
public class SerologiaEnvio extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer idserologiaenvio;
    private Integer idenvio;
    private Date fecha;
    private String hora;
    private double temperatura;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ENVIO_MUESTRA_ID", nullable = false)
    public Integer getIdserologiaenvio() {
        return idserologiaenvio;
    }

    public void setIdserologiaenvio(Integer idserologiaenvio) {
        this.idserologiaenvio = idserologiaenvio;
    }

    @Column(name = "NUMERO_ENVIO", nullable = false)
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

    @Column(name = "TEMPERATURA", nullable = false)
    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }


}
