package ni.org.ics.estudios.domain;

import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import java.util.Calendar;
import java.util.Date;

/**
 * Simple objeto de dominio que representa un participante de los estudios
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "participantes", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "codigo") })
public class Participante extends BaseMetaData implements Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer codigo;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String sexo;
	private Date fechaNac;
    private String nombre1Padre;
    private String nombre2Padre;
    private String apellido1Padre;
    private String apellido2Padre;
    private String nombre1Madre;
    private String nombre2Madre;
    private String apellido1Madre;
    private String apellido2Madre;
    private Casa casa;
    //Agregar datos de tutor
    private String nombre1Tutor;
    private String nombre2Tutor;
    private String apellido1Tutor;
    private String apellido2Tutor;
    private String relacionFamiliarTutor;

	@Id
	@Column(name = "CODIGO", nullable = false, length = 6)
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "NOMBRE1", nullable = false)
	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	@Column(name = "NOMBRE2", nullable = true)
	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	@Column(name = "APELLIDO1", nullable = false)
	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	@Column(name = "APELLIDO2", nullable = true)
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	@Column(name = "SEXO", nullable = false)
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Column(name = "FECHANAC", nullable = false)
	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

    @Column(name = "NOMBRE1_PADRE", length = 100)
    public String getNombre1Padre() {
        return nombre1Padre;
    }

    public void setNombre1Padre(String nombre1Padre) {
        this.nombre1Padre = nombre1Padre;
    }

    @Column(name = "NOMBRE2_PADRE", length = 100)
    public String getNombre2Padre() {
        return nombre2Padre;
    }

    public void setNombre2Padre(String nombre2Padre) {
        this.nombre2Padre = nombre2Padre;
    }

    @Column(name = "APELLIDO1_PADRE", length = 100)
    public String getApellido1Padre() {
        return apellido1Padre;
    }

    public void setApellido1Padre(String apellido1Padre) {
        this.apellido1Padre = apellido1Padre;
    }

    @Column(name = "APELLIDO2_PADRE", length = 100)
    public String getApellido2Padre() {
        return apellido2Padre;
    }

    public void setApellido2Padre(String apellido2Padre) {
        this.apellido2Padre = apellido2Padre;
    }

    @Column(name = "NOMBRE1_MADRE", length = 100)
    public String getNombre1Madre() {
        return nombre1Madre;
    }

    public void setNombre1Madre(String nombre1Madre) {
        this.nombre1Madre = nombre1Madre;
    }

    @Column(name = "NOMBRE2_MADRE", length = 100)
    public String getNombre2Madre() {
        return nombre2Madre;
    }

    public void setNombre2Madre(String nombre2Madre) {
        this.nombre2Madre = nombre2Madre;
    }

    @Column(name = "APELLIDO1_MADRE", length = 100)
    public String getApellido1Madre() {
        return apellido1Madre;
    }

    public void setApellido1Madre(String apellido1Madre) {
        this.apellido1Madre = apellido1Madre;
    }

    @Column(name = "APELLIDO2_MADRE", length = 100)
    public String getApellido2Madre() {
        return apellido2Madre;
    }

    public void setApellido2Madre(String apellido2Madre) {
        this.apellido2Madre = apellido2Madre;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASA", nullable = false)
    @ForeignKey(name = "FK_CASA_PARTICIPANTE")
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    @Column(name = "NOMBRE1_TUTOR", length = 100)
    public String getNombre1Tutor() {
        return nombre1Tutor;
    }

    public void setNombre1Tutor(String nombre1Tutor) {
        this.nombre1Tutor = nombre1Tutor;
    }

    @Column(name = "NOMBRE2_TUTOR", length = 100)
    public String getNombre2Tutor() {
        return nombre2Tutor;
    }

    public void setNombre2Tutor(String nombre2Tutor) {
        this.nombre2Tutor = nombre2Tutor;
    }

    @Column(name = "APELLIDO1_TUTOR", length = 100)
    public String getApellido1Tutor() {
        return apellido1Tutor;
    }

    public void setApellido1Tutor(String apellido1Tutor) {
        this.apellido1Tutor = apellido1Tutor;
    }

    @Column(name = "APELLIDO2_TUTOR", length = 100)
    public String getApellido2Tutor() {
        return apellido2Tutor;
    }

    public void setApellido2Tutor(String apellido2Tutor) {
        this.apellido2Tutor = apellido2Tutor;
    }

    @Column(name = "RELACION_FAMILIAR", nullable = true, length = 10)
    public String getRelacionFamiliarTutor() {
        return relacionFamiliarTutor;
    }

    public void setRelacionFamiliarTutor(String relacionFamiliarTutor) {
        this.relacionFamiliarTutor = relacionFamiliarTutor;
    }

    @Transient
    @JsonIgnore
    public String getEdad(){
        if (this.getFechaNac()!=null) {
            Calendar calendarDOB = Calendar.getInstance();
            Calendar calendarToday = Calendar.getInstance();

            calendarToday.setTime(new Date());
            calendarDOB.setTime(this.fechaNac);
            Integer diaInicio = calendarDOB.get(Calendar.DAY_OF_MONTH);
            Integer mesInicio = calendarDOB.get(Calendar.MONTH)+1;
            Integer anioInicio = calendarDOB.get(Calendar.YEAR);

            Integer diaActual = calendarToday.get(Calendar.DAY_OF_MONTH);
            Integer mesActual = calendarToday.get(Calendar.MONTH)+1;
            Integer anioActual = calendarToday.get(Calendar.YEAR);

            int b = 0;
            Integer dias = 0;
            Integer anios = 0;
            Integer meses = 0;
            b = calendarDOB.getActualMaximum(Calendar.DAY_OF_MONTH);
            if ((anioInicio > anioActual) || (anioInicio.equals(anioActual) && mesInicio > mesActual)
                    || (anioInicio.equals(anioActual) && mesInicio.equals(mesActual) && diaInicio > diaActual)) {
                return "ND";
            } else {
                if (mesInicio <= mesActual) {
                    anios = anioActual - anioInicio;
                    if (diaInicio <= diaActual) {
                        meses = mesActual - mesInicio;
                        dias = (diaActual - diaInicio);
                    } else {
                        if (mesActual.equals(mesInicio)) {
                            anios = anios - 1;
                        }
                        meses = (mesActual - mesInicio - 1 + 12) % 12;
                        dias = b - (diaInicio - diaActual);
                    }
                } else {
                    anios = anioActual - anioInicio - 1;
                    if (diaInicio > diaActual) {
                        meses = mesActual - mesInicio - 1 + 12;
                        dias = b - (diaInicio - diaActual);
                    } else {
                        meses = mesActual - mesInicio + 12;
                        dias = diaActual - diaInicio;
                    }
                }
            }
            return anios.toString() + "/" + meses.toString() + "/" + dias.toString();
        }else{
            return "ND";
        }
    }

    @Transient
    @JsonIgnore
    public String getNombreCompleto(){
        String nombreCompleto = this.getNombre1().toUpperCase();
        if (this.getNombre2()!=null) nombreCompleto = nombreCompleto + " "+  this.getNombre2().toUpperCase();
        nombreCompleto = nombreCompleto +" "+ this.getApellido1().toUpperCase();
        if (this.getApellido2()!=null) nombreCompleto = nombreCompleto + " "+  this.getApellido2().toUpperCase();

        return nombreCompleto;
    }

    @Transient
    @JsonIgnore
    public String getTutor(){
        String tutor = " ";
        if (this.getNombre1Tutor()!=null) tutor = this.getNombre1Tutor();
        if (this.getNombre2Tutor()!=null) tutor = tutor + " "+  this.getNombre2Tutor();
        if (this.getApellido1Tutor()!=null) tutor = tutor +" "+ this.getApellido1Tutor();
        if (this.getApellido2Tutor()!=null) tutor = tutor + " "+  this.getApellido2Tutor();

        return tutor;
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
        if (!(o instanceof Participante)) return false;

        Participante participante = (Participante) o;

        return (!codigo.equals(participante.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
