package ni.org.ics.estudios.users.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.validator.constraints.NotBlank;



/**
 * Simple objeto de dominio que representa los permisos de los usuario en los procesos
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "usuarios_permisos", catalog = "estudios_ics")
public class UserPermissions {
	private String username;
	private Boolean muestra=false;
	private Boolean encuestaCasa=false;
	private Boolean encuestaParticipante=false;
	private Boolean encuestaLactancia=false;
	private Boolean encuestaSatisfaccion=false;
	private Boolean obsequio=false;
	private Boolean pesoTalla=false;
	private Boolean vacunas=false;
	private Boolean visitas=false;
	private Boolean recepcion=false;
	private Boolean consentimiento=false;
	private Boolean casazika=false;
	private Boolean tamizajezika=false;
	private Boolean datosparto=false;
    private Boolean pabdominal=false; //Perimetro Abdominal
    private Boolean encSatUsu=false; //Encuesta satisfaccion usuario
    private Boolean encSatUsuCc=false; //Encuesta satisfaccion usuario control de calidad

	@Id
	@Column(name = "username", nullable = false, length =50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "muestra", nullable = false)
	public Boolean getMuestra() {
		return muestra;
	}
	public void setMuestra(Boolean muestra) {
		this.muestra = muestra;
	}

	@Column(name = "ecasa", nullable = false)
	public Boolean getEncuestaCasa() {
		return encuestaCasa;
	}
	public void setEncuestaCasa(Boolean encuestaCasa) {
		this.encuestaCasa = encuestaCasa;
	}

	@Column(name = "eparticipante", nullable = false)
	public Boolean getEncuestaParticipante() {
		return encuestaParticipante;
	}
	public void setEncuestaParticipante(Boolean encuestaParticipante) {
		this.encuestaParticipante = encuestaParticipante;
	}

	@Column(name = "elactancia", nullable = false)
	public Boolean getEncuestaLactancia() {
		return encuestaLactancia;
	}
	public void setEncuestaLactancia(Boolean encuestaLactancia) {
		this.encuestaLactancia = encuestaLactancia;
	}

	@Column(name = "esatisfaccion", nullable = false)
	public Boolean getEncuestaSatisfaccion() {
		return encuestaSatisfaccion;
	}
	public void setEncuestaSatisfaccion(Boolean encuestaSatisfaccion) {
		this.encuestaSatisfaccion = encuestaSatisfaccion;
	}

	@Column(name = "obsequio", nullable = false)
	public Boolean getObsequio() {
		return obsequio;
	}
	public void setObsequio(Boolean obsequio) {
		this.obsequio = obsequio;
	}

	@Column(name = "pesotalla", nullable = false)
	public Boolean getPesoTalla() {
		return pesoTalla;
	}
	public void setPesoTalla(Boolean pesoTalla) {
		this.pesoTalla = pesoTalla;
	}

	@Column(name = "vacunas", nullable = false)
	public Boolean getVacunas() {
		return vacunas;
	}
	public void setVacunas(Boolean vacunas) {
		this.vacunas = vacunas;
	}

	@Column(name = "visitas", nullable = false)
	public Boolean getVisitas() {
		return visitas;
	}
	public void setVisitas(Boolean visitas) {
		this.visitas = visitas;
	}

	@Column(name = "recepcion", nullable = false)
	public Boolean getRecepcion() {
		return recepcion;
	}
	public void setRecepcion(Boolean recepcion) {
		this.recepcion = recepcion;
	}

	@Column(name = "consentimiento", nullable = false)
	public Boolean getConsentimiento() {
		return consentimiento;
	}
	public void setConsentimiento(Boolean consentimiento) {
		this.consentimiento = consentimiento;
	}

	@Column(name = "casazika", nullable = false)
	public Boolean getCasazika() {
		return casazika;
	}
	public void setCasazika(Boolean casazika) {
		this.casazika = casazika;
	}

	@Column(name = "tamizajezika", nullable = false)
	public Boolean getTamizajezika() {
		return tamizajezika;
	}
	public void setTamizajezika(Boolean tamizajezika) {
		this.tamizajezika = tamizajezika;
	}
	
	@Column(name = "datosparto", nullable = false)
	public Boolean getDatosparto() {
		return datosparto;
	}
	public void setDatosparto(Boolean datosparto) {
		this.datosparto = datosparto;
	}

    @Column(name = "pabdominal", nullable = false)
    public Boolean getpAbdominal() {
        return pabdominal;
    }

    public void setpAbdominal(Boolean pAbdominal) {
        this.pabdominal = pAbdominal;
    }

    @Column(name = "encsatusu", nullable = false)
    public Boolean getEncSatUsu() {
        return encSatUsu;
    }

    public void setEncSatUsu(Boolean encSatUsu) {
        this.encSatUsu = encSatUsu;
    }

    @Column(name = "encsatusucc", nullable = false)
    public Boolean getEncSatUsuCc() {
        return encSatUsuCc;
    }

    public void setEncSatUsuCc(Boolean encSatUsuCc) {
        this.encSatUsuCc = encSatUsuCc;
    }


}
