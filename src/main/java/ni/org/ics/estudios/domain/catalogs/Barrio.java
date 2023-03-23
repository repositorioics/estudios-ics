package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa un barrio
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "barrios", catalog = "estudios_ics")
public class Barrio extends BaseMetaData implements Auditable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String nombre;

    @Id
    @Column(name = "CODIGO", nullable = false, length = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOMBRE", nullable = false, length = 150)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return false;
    }

    @Override
    public String toString() {
        return "'" + codigo + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barrio)) return false;

        Barrio barrio = (Barrio) o;

        return (!codigo.equals(barrio.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
