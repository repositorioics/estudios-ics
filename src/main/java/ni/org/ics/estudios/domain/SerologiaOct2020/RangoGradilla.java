package ni.org.ics.estudios.domain.SerologiaOct2020;

import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

/**
 * Created by ICS on 20/10/2020.
 */

@Entity
@Table(name = "chf_rango_gradilla", catalog = "estudios_ics")
public class RangoGradilla implements Auditable {

    private Integer id;
    private Integer inicio;
    private Integer fin;
    private  Integer gradilla;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDGRADILLA", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "INICIO", nullable = true)
    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }
    @Column(name = "FIN", nullable = true)
    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }
    @Column(name = "GRADILLA", nullable = true)
    public Integer getGradilla() {
        return gradilla;
    }

    public void setGradilla(Integer gradilla) {
        this.gradilla = gradilla;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
