package ni.org.ics.estudios.domain.muestreoanual;

import org.hibernate.type.LobType;

import java.util.Date;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa los datos de vacunas recibidas
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "documentacion", catalog = "estudios_ics")
public class Documentos {

	/**
	 * 
	 */
	private DocumentosId docsId;
	private String tipoDoc;
	private byte[] documento;
	private String usuario;
	private String estado;
	private Date fechaRecepcion;
	private Date today;

	@Id
	public DocumentosId getDocsId() {
		return docsId;
	}

	public void setDocsId(DocumentosId docsId) {
		this.docsId = docsId;
	}

	@Column(name = "tipo_doc", nullable = false, length = 10)
	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	@Column(name = "usuario", nullable = false, length = 10)
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "estado", nullable = false, length = 10)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "documento", nullable = false)
	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	@Column(name = "fecha_recepcion", nullable = false)
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	@Column(name = "fecha_registro", nullable = false)
	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}
	
	

}
