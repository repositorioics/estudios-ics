package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

/**
 * Created by ICS on 14/01/2020.
 */
@Entity
@Table(name = "cartas", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "idcarta") })
public class Carta extends BaseMetaData implements Auditable{

    private static final long serialVersionUID = 1L;

    private Integer idcarta;
    private String carta;
    private String cod;
    private String activo;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDCARTA", nullable = false, length = 6)
    public Integer getIdcarta() {
        return idcarta;
    }

    public void setIdcarta(Integer idcarta) {
        this.idcarta = idcarta;
    }

    @Column(name = "CARTA", nullable = false)
    public String getCarta() {
        return carta;
    }

    public void setCarta(String carta) {
        this.carta = carta;
    }
    @Column(name = "COD", nullable = true)
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @Column(name = "activo", length = 20, nullable = true)
    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        return idcarta.hashCode();
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
