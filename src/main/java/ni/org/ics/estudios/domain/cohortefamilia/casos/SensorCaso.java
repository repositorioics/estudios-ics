package ni.org.ics.estudios.domain.cohortefamilia.casos;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.AreaAmbiente;
import ni.org.ics.estudios.domain.cohortefamilia.Cuarto;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 11/10/2018.
 * V1.0
 */
@Entity
@Table(name = "chf_sensores_casos", catalog = "estudios_ics")
public class SensorCaso extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoSensor;
	private CasaCohorteFamiliaCaso codigoCaso;
    private String numeroSensor;
    private AreaAmbiente area;
    private Cuarto cuarto;
	private Date fechaColocacion;
    private Date fechaRetiro;
    private String horaRetiro;
    private String observacionRetiro;
    private String sensorSN;
    private String razonNoColocaSensor;

    @Id
    @Column(name = "CODIGO_SENSOR", length = 50, nullable = false)
    public String getCodigoSensor() {
        return codigoSensor;
    }

    public void setCodigoSensor(String codigoSensor) {
        this.codigoSensor = codigoSensor;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASO", nullable = true)
    @ForeignKey(name = "FK_SENSORES_CASA_CASO")
    public CasaCohorteFamiliaCaso getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(CasaCohorteFamiliaCaso codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    @Column(name = "NUMERO_SENSOR", length = 10, nullable = false)
    public String getNumeroSensor() {
        return numeroSensor;
    }

    public void setNumeroSensor(String numeroSensor) {
        this.numeroSensor = numeroSensor;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_AREA", nullable = true)
    @ForeignKey(name = "FK_SENSORES_AREA_AMB")
    public AreaAmbiente getArea() {
        return area;
    }

    public void setArea(AreaAmbiente area) {
        this.area = area;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CUARTO", nullable = true)
    @ForeignKey(name = "FK_SENSOR_CUARTO")
    public Cuarto getCuarto() {
        return cuarto;
    }

    public void setCuarto(Cuarto cuarto) {
        this.cuarto = cuarto;
    }

    @Column(name = "FECHA_COLOCACION")
    public Date getFechaColocacion() {
        return fechaColocacion;
    }

    public void setFechaColocacion(Date fechaColocacion) {
        this.fechaColocacion = fechaColocacion;
    }

    @Column(name = "FECHA_RETIRO")
    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    @Column(name = "HORA_RETIRO", length = 20)
    public String getHoraRetiro() {
        return horaRetiro;
    }

    public void setHoraRetiro(String horaRetiro) {
        this.horaRetiro = horaRetiro;
    }

    @Column(name = "OBSERVACION_RETIRO")
    public String getObservacionRetiro() {
        return observacionRetiro;
    }

    public void setObservacionRetiro(String observacionRetiro) {
        this.observacionRetiro = observacionRetiro;
    }

    @Column(name = "SENSOR_SN", length = 2)
    public String getSensorSN() {
        return sensorSN;
    }

    public void setSensorSN(String sensorSN) {
        this.sensorSN = sensorSN;
    }

    @Column(name = "RAZON_NO_COLOCA_SENSOR")
    public String getRazonNoColocaSensor() {
        return razonNoColocaSensor;
    }

    public void setRazonNoColocaSensor(String razonNoColocaSensor) {
        this.razonNoColocaSensor = razonNoColocaSensor;
    }

    @Override
	public String toString(){
		return codigoCaso.getCodigoCaso() + "-" + numeroSensor;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorCaso)) return false;

        SensorCaso that = (SensorCaso) o;

        if (!codigoSensor.equals(that.codigoSensor)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoSensor.hashCode();
        result = 31 * result + codigoCaso.hashCode();
        return result;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
