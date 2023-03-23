package ni.org.ics.estudios.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by ICS_Inspiron3 on 19/08/2019.
 */
@Entity
@Table(name = "rangosfrecuenciascardiacas", catalog = "estudios_ics")
public class RangosFrecuenciasCardiacas implements Serializable {
    private Integer codigo;
    private Integer edadmin;
    private Integer edadmax;
    private String fcMinima;
    private String fcMedia;
    private String fcPromedio;
    private String frMinima;
    private String frMaxima;
    private String umedida;

    public RangosFrecuenciasCardiacas(Integer codigo, Integer edadmin, Integer edadmax, String fcMinima, String fcMedia, String fcPromedio, String frMinima, String frMaxima, String umedida) {
        this.codigo = codigo;
        this.edadmin = edadmin;
        this.edadmax = edadmax;
        this.fcMinima = fcMinima;
        this.fcMedia = fcMedia;
        this.fcPromedio = fcPromedio;
        this.frMinima = frMinima;
        this.frMaxima = frMaxima;
        this.umedida = umedida;
    }

    public RangosFrecuenciasCardiacas() {
    }

    @Id
    @Column(name = "CODIGO", nullable = false)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    @Column(name = "EDADMIN", nullable = false)
    public Integer getEdadmin() {
        return edadmin;
    }

    public void setEdadmin(Integer edadmin) {
        this.edadmin = edadmin;
    }
    @Column(name = "EDADMAX", nullable = false)
    public Integer getEdadmax() {
        return edadmax;
    }

    public void setEdadmax(Integer edadmax) {
        this.edadmax = edadmax;
    }

    @Column(name = "FCMINIMA", nullable = false)
    public String getFcMinima() {
        return fcMinima;
    }

    public void setFcMinima(String fcMinima) {
        this.fcMinima = fcMinima;
    }
    @Column(name = "FCMEDIA", nullable = false)
    public String getFcMedia() {
        return fcMedia;
    }

    public void setFcMedia(String fcMedia) {
        this.fcMedia = fcMedia;
    }
    @Column(name = "FCPROMEDIO", nullable = false)
    public String getFcPromedio() {
        return fcPromedio;
    }

    public void setFcPromedio(String fcPromedio) {
        this.fcPromedio = fcPromedio;
    }
    @Column(name = "FRMINIMA", nullable = false)
    public String getFrMinima() {
        return frMinima;
    }

    public void setFrMinima(String frMinima) {
        this.frMinima = frMinima;
    }
    @Column(name = "FRMAXIMA", nullable = false)
    public String getFrMaxima() {
        return frMaxima;
    }

    public void setFrMaxima(String frMaxima) {
        this.frMaxima = frMaxima;
    }
    @Column(name = "UMEDIDA", nullable = false)
    public String getUmedida() {
        return umedida;
    }

    public void setUmedida(String umedida) {
        this.umedida = umedida;
    }
}
