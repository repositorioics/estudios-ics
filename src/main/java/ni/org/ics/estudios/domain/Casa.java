package ni.org.ics.estudios.domain;

import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Barrio;

import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

/**
 * Simple objeto de dominio que representa los datos demograficos de la casa
 * donde habitan 1 o mas participantes de los estudios
 * 
 * @author Miguel Salinas
 **/

@Entity
@Table(name = "casas", catalog = "estudios_ics")
public class Casa extends BaseMetaData implements Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer codigo;
	private Barrio barrio;
	private String direccion;
    private String manzana;
	private Double latitud;
	private Double longitud;
    private String nombre1JefeFamilia;
    private String nombre2JefeFamilia;
    private String apellido1JefeFamilia;
    private String apellido2JefeFamilia;

    @Id
	@Column(name = "CODIGO", nullable = false, length = 4)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

	@Column(name = "DIRECCION", nullable = false)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "LATITUD", nullable = true)
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	@Column(name = "LONGITUD", nullable = true)
	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

    @Column(name = "NOMBRE1_JEFE", nullable = false)
    public String getNombre1JefeFamilia() {
        return nombre1JefeFamilia;
    }

    public void setNombre1JefeFamilia(String nombre1JefeFamilia) {
        this.nombre1JefeFamilia = nombre1JefeFamilia;
    }

    @Column(name = "NOMBRE2_JEFE", nullable = true)
    public String getNombre2JefeFamilia() {
        return nombre2JefeFamilia;
    }

    public void setNombre2JefeFamilia(String nombre2JefeFamilia) {
        this.nombre2JefeFamilia = nombre2JefeFamilia;
    }

    @Column(name = "APELLIDO1_JEFE", nullable = false)
    public String getApellido1JefeFamilia() {
        return apellido1JefeFamilia;
    }

    public void setApellido1JefeFamilia(String apellido1JefeFamilia) {
        this.apellido1JefeFamilia = apellido1JefeFamilia;
    }

    @Column(name = "APELLIDO2_JEFE", nullable = true)
    public String getApellido2JefeFamilia() {
        return apellido2JefeFamilia;
    }

    public void setApellido2JefeFamilia(String apellido2JefeFamilia) {
        this.apellido2JefeFamilia = apellido2JefeFamilia;
    }

    @Column(name = "MANZANA", nullable = false, length = 4)
    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    @ManyToOne
	@JoinColumn(name="CODIGO_BARRIO", updatable = false)
    @ForeignKey(name = "FK_BARRIO_CASA")
    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

	@Override
	public String toString(){
		return this.codigo + " " + this.latitud+" "+this.longitud;
	}

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Casa)) return false;

        Casa casa = (Casa) o;

        return (codigo.equals(casa.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
