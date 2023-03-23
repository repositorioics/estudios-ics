package ni.org.ics.estudios.domain.muestreoanual;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MovilInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Manejo ODK
	private Integer idInstancia;
	private String instancePath;
	private String estado;
	private Boolean eliminado;
	private String ultimoCambio;
	//Metadata del formulario movil
	private String start;
	private String end;
	private String deviceid;
	private String simserial;
	private String phonenumber;
	private Date today;	
	private String username; 
	private Integer recurso1;
	private Integer recurso2;	
	

	public MovilInfo(){
		
	}
	
	
	
	public MovilInfo(Integer idInstancia, String instancePath, String estado, Boolean eliminado, String ultimoCambio,
			String start, String end, String deviceid, String simserial, String phonenumber, Date today,
			String username, Integer recurso1, Integer recurso2) {
		super();
		this.idInstancia = idInstancia;
		this.instancePath = instancePath;
		this.estado = estado;
		this.eliminado = eliminado;
		this.ultimoCambio = ultimoCambio;
		this.start = start;
		this.end = end;
		this.deviceid = deviceid;
		this.simserial = simserial;
		this.phonenumber = phonenumber;
		this.today = today;
		this.username = username;
		this.recurso1 = recurso1;
		this.recurso2 = recurso2;
	}



	@Column(name = "id_instancia", nullable = false)
	public Integer getIdInstancia() {
		return idInstancia;
	}

	public void setIdInstancia(Integer idInstancia) {
		this.idInstancia = idInstancia;
	}

	@Column(name = "instance_path", nullable = false, length = 255)
	public String getInstancePath() {
		return instancePath;
	}

	public void setInstancePath(String instancePath) {
		this.instancePath = instancePath;
	}

	@Column(name = "estado", nullable = false, length = 15)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "ultimo_cambio", nullable = false, length = 100)
	public String getUltimoCambio() {
		return ultimoCambio;
	}

	public void setUltimoCambio(String ultimoCambio) {
		this.ultimoCambio = ultimoCambio;
	}

	@Column(name = "creado", nullable = false, length = 50)
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	@Column(name = "terminado", nullable = false, length = 50)
	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Column(name = "identificador_equipo", nullable = false, length = 50)
	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	@Column(name = "serie_sim", nullable = true, length = 50)
	public String getSimserial() {
		return simserial;
	}

	public void setSimserial(String simserial) {
		this.simserial = simserial;
	}

	@Column(name = "numero_telefono", nullable = true, length = 50)
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Column(name = "fecha_registro", nullable = false)
	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	@Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "eliminado", nullable = false)
	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	
	@Column(name = "recurso1", nullable = false)
	public Integer getRecurso1() {
		return recurso1;
	}

	public void setRecurso1(Integer recurso1) {
		this.recurso1 = recurso1;
	}
		
	
	@Column(name = "recurso2", nullable = true)
	public Integer getRecurso2() {
		return recurso2;
	}

	public void setRecurso2(Integer recurso2) {
		this.recurso2 = recurso2;
	}
}
