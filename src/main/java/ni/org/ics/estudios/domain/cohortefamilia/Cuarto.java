package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_cuartos", catalog = "estudios_ics")
public class Cuarto extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private CasaCohorteFamilia casa;
	private String codigoHabitacion;
    private int cantidadCamas;
    
    
    @Id
    @Column(name = "CODIGO", length = 50)
    public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASACHF", nullable = false)
    @ForeignKey(name = "FK_CUARTO_CASA")
    public CasaCohorteFamilia getCasa() {
        return casa;
    }

	public void setCasa(CasaCohorteFamilia casa) {
		this.casa = casa;
	}

	@Column(name = "ID_HABITACION", length = 5)
    public String getCodigoHabitacion() {
        return codigoHabitacion;
    }

    public void setCodigoHabitacion(String codigoHabitacion) {
        this.codigoHabitacion = codigoHabitacion;
    }

    @Column(name = "CANTIDAD_CAMAS")
    public int getCantidadCamas() {
        return cantidadCamas;
    }

    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
