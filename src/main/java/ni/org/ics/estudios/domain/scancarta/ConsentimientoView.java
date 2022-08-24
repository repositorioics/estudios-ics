package ni.org.ics.estudios.domain.scancarta;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 07/04/2022.
 */
@Entity
@Table(name = "vw_consentimientos_ma2022")
@Immutable
public class ConsentimientoView implements Serializable {

    @Column
    private Date fecha_carta;
    @Id
    @Column
    private Integer codigo_participante;
    @Column
    private Integer codigo_estudio;
    @Column
    private String version;
    @Column
    private String origen;

    public ConsentimientoView(Date fecha_carta, Integer codigo_participante, Integer codigo_estudio, String version, String origen) {
        this.fecha_carta = fecha_carta;
        this.codigo_participante = codigo_participante;
        this.codigo_estudio = codigo_estudio;
        this.version = version;
        this.origen = origen;
    }

    public ConsentimientoView() {
    }

    public Date getFecha_carta() {
        return fecha_carta;
    }

    public void setFecha_carta(Date fecha_carta) {
        this.fecha_carta = fecha_carta;
    }

    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    public Integer getCodigo_estudio() {
        return codigo_estudio;
    }

    public void setCodigo_estudio(Integer codigo_estudio) {
        this.codigo_estudio = codigo_estudio;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
}
