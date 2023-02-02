package ni.org.ics.estudios.domain.muestreoanual;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ing. Santiago Carballo on 30/01/2023.
 */

@Embeddable
public class EncuestaPerimetroAbdominalId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer codigo;
    private Date fecha;

    public EncuestaPerimetroAbdominalId() {

    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof EncuestaPerimetroAbdominalId))
            return false;
        EncuestaPerimetroAbdominalId castOther = (EncuestaPerimetroAbdominalId) other;
        return ((this.getCodigo() == castOther.getCodigo())
                && (this.getFecha() == castOther.getFecha()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * 3 + this.getCodigo();
        result = 37 * result + Integer.valueOf(this.getFecha().toString());
        return result;
    }

    @Column(name = "codigo", nullable = false, length = 5)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fechaPT) {
        this.fecha = fechaPT;
    }
}
