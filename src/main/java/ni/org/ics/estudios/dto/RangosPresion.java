package ni.org.ics.estudios.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by ICS_Inspiron3 on 15/08/2019.
 */
@Entity
@Table(name = "rangospresion", catalog = "estudios_ics")
public class RangosPresion implements Serializable {

    private Integer codigo;
    private Integer edadmin;
    private Integer edadmax;
    private String umedida;
    private String sexo;
    private String psdmin;
    private String psdmed;
    private String psdmax;
    private String pammin;
    private String pammed;
    private String pammax;

    public RangosPresion(Integer codigo, Integer edadmin, Integer edadmax, String umedida, String sexo, String psdmin, String psdmed, String psdmax, String pammin, String pammed, String pammax) {
        this.codigo = codigo;
        this.edadmin = edadmin;
        this.edadmax = edadmax;
        this.umedida = umedida;
        this.sexo = sexo;
        this.psdmin = psdmin;
        this.psdmed = psdmed;
        this.psdmax = psdmax;
        this.pammin = pammin;
        this.pammed = pammed;
        this.pammax = pammax;
    }

    public RangosPresion() {
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
    @Column(name = "UMEDIDA", nullable = false)
    public String getUmedida() {
        return umedida;
    }

    public void setUmedida(String umedida) {
        this.umedida = umedida;
    }
    @Column(name = "SEXO", nullable = false)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    @Column(name = "PSDMIN", nullable = false)
    public String getPsdmin() {
        return psdmin;
    }

    public void setPsdmin(String psdmin) {
        this.psdmin = psdmin;
    }
    @Column(name = "PSDMED", nullable = false)
    public String getPsdmed() {
        return psdmed;
    }

    public void setPsdmed(String psdmed) {
        this.psdmed = psdmed;
    }
    @Column(name = "PSDMAX", nullable = false)
    public String getPsdmax() {
        return psdmax;
    }

    public void setPsdmax(String psdmax) {
        this.psdmax = psdmax;
    }
    @Column(name = "PAMMIN", nullable = false)
    public String getPammin() {
        return pammin;
    }

    public void setPammin(String pammin) {
        this.pammin = pammin;
    }
    @Column(name = "PAMMED", nullable = false)
    public String getPammed() {
        return pammed;
    }

    public void setPammed(String pammed) {
        this.pammed = pammed;
    }
    @Column(name = "PAMMAX", nullable = false)
    public String getPammax() {
        return pammax;
    }

    public void setPammax(String pammax) {
        this.pammax = pammax;
    }

}
