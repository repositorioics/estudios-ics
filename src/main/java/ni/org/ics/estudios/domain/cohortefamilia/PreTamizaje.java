package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Casa;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Estudio;

import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

/**
 * Created by FIRSTICT on 4/28/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_pre_tamizajes", catalog = "estudios_ics")
public class PreTamizaje extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
    private char aceptaTamizajeCasa;
    private String razonNoAceptaTamizajeCasa;
    private String otraRazonNoAceptaTamizajeCasa;
    private Casa casa;
    private Estudio estudio;

    @Id
    @Column(name = "CODIGO", nullable = false, insertable = true, updatable = false, length = 36)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name="ACEPTA_TAMIZAJE", nullable = false, length = 1)
    public char getAceptaTamizajeCasa() {
        return aceptaTamizajeCasa;
    }

    public void setAceptaTamizajeCasa(char aceptaTamizaje) {
        this.aceptaTamizajeCasa = aceptaTamizaje;
    }

    @Column(name="RAZON_NO_ACEPTA", nullable = true, length = 50)
    public String getRazonNoAceptaTamizajeCasa() {
        return razonNoAceptaTamizajeCasa;
    }

    public void setRazonNoAceptaTamizajeCasa(String razonNoParticipa) {
        this.razonNoAceptaTamizajeCasa = razonNoParticipa;
    }

    @Column(name="OTRA_RAZON_NO_ACEPTA", nullable = true)
    public String getOtraRazonNoAceptaTamizajeCasa() {
        return otraRazonNoAceptaTamizajeCasa;
    }

    public void setOtraRazonNoAceptaTamizajeCasa(String otraRazonNoAceptaTamizajeCasa) {
        this.otraRazonNoAceptaTamizajeCasa = otraRazonNoAceptaTamizajeCasa;
    }

    @ManyToOne
    @JoinColumn(name="CODIGO_CASA", nullable = false)
    @ForeignKey(name = "FK_CASA_PRETAMIZAJE")
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    @ManyToOne
    @JoinColumn(name="CODIGO_ESTUDIO", nullable = false)
    @ForeignKey(name = "FK_ESTUDIO_PRETAMIZAJE")
    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "'" + codigo + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreTamizaje)) return false;

        PreTamizaje preTamizaje = (PreTamizaje) o;

        return (!codigo.equals(preTamizaje.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
