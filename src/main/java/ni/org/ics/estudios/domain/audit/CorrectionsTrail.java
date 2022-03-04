package ni.org.ics.estudios.domain.audit;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bitacora_correcciones", catalog = "estudios_ics")
public class CorrectionsTrail {

	private int id;
	private String entityId;
	private String entityName;
	private String entityProperty;
	private String entityPropertyOldValue;
	private String entityPropertyNewValue;
	private String username;
	private Date operationDate;
	private String observacion;


	public CorrectionsTrail() {
		super();
	}

	public CorrectionsTrail(String entityId, String entityName,
                            String entityProperty, String entityPropertyOldValue,
                            String entityPropertyNewValue,
                            String username, Date operationDate) {
		super();
		this.entityId = entityId;
		this.entityName = entityName;
		this.entityProperty = entityProperty;
		this.entityPropertyOldValue = entityPropertyOldValue;
		this.entityPropertyNewValue = entityPropertyNewValue;
		this.username = username;
		this.operationDate = operationDate;
	}
	
	@Id
	@GenericGenerator(name="idautoinc3" , strategy="increment")
	@GeneratedValue(generator="idautoinc3")
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "ID_ENTIDAD", nullable = true, length =50)
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	@Column(name = "NOMBRE_ENTIDAD", nullable = true, length =200)
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	@Column(name = "PROPIEDAD", nullable = true, length =150)
	public String getEntityProperty() {
		return entityProperty;
	}
	public void setEntityProperty(String entityProperty) {
		this.entityProperty = entityProperty;
	}
	@Column(name = "VALOR_ANTERIOR", nullable = true, length =1000)
	public String getEntityPropertyOldValue() {
		return entityPropertyOldValue;
	}
	public void setEntityPropertyOldValue(String entityPropertyOldValue) {
		this.entityPropertyOldValue = entityPropertyOldValue;
	}
	@Column(name = "NUEVO_VALOR", nullable = true, length =1000)
	public String getEntityPropertyNewValue() {
		return entityPropertyNewValue;
	}
	public void setEntityPropertyNewValue(String entityPropertyNewValue) {
		this.entityPropertyNewValue = entityPropertyNewValue;
	}
	@Column(name = "NOMBRE_USUARIO", nullable = true, length =50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "FECHA_OPERACION", nullable = true)
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
    public String getObservacion() {
        return observacion;
    }
    @Column(name = "OBSERVACION", nullable = true, length =512)
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
