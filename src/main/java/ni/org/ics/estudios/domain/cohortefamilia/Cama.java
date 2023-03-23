package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.Habitacion;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_camas_habitacion", catalog = "estudios_ics")
public class Cama extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCama;
    private String descCama;
    private Cuarto cuarto;

    @Id
    @Column(name = "CODIGO", length = 36)
    public String getCodigoCama() {
        return codigoCama;
    }

    public void setCodigoCama(String codigoCama) {
        this.codigoCama = codigoCama;
    }
    
    
    @Column(name = "DESC_CAMA", length = 255)
    public String getDescCama() {
		return descCama;
	}

	public void setDescCama(String descCama) {
		this.descCama = descCama;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_CUARTO")
    @ForeignKey(name = "FK_CAMA_CUARTO")
    public Cuarto getCuarto() {
        return cuarto;
    }

    public void setCuarto(Cuarto cuarto) {
        this.cuarto = cuarto;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "Cama{" + codigoCama + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cama)) return false;

        Cama cama = (Cama) o;

        return (!codigoCama.equals(cama.codigoCama));
    }

    @Override
    public int hashCode() {
        return codigoCama.hashCode();
    }
}
