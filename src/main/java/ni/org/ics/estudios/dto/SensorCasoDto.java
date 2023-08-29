package ni.org.ics.estudios.dto;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.AreaAmbiente;
import ni.org.ics.estudios.domain.cohortefamilia.Cuarto;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ing. Santiago Carballo on 25/08/2023.
 */

public class SensorCasoDto implements Serializable {
    private String codigoSensor;
    private String codigoCaso;
    private String numeroSensor;
    private String area;
    private String cuarto;
    private Date fechaColocacion;
    private Date fechaRetiro;
    private String horaRetiro;
    private String observacionRetiro;
    private String sensorSN;
    private String razonNoColocaSensor;

    private Date recordDate;
    private String recordUser;
    private char pasive;
    private char estado;
    private String deviceid;

    public SensorCasoDto() {}

    public SensorCasoDto(String codigoSensor, String codigoCaso, String numeroSensor, String area, String cuarto, Date fechaColocacion, Date fechaRetiro, String horaRetiro, String observacionRetiro, String sensorSN, String razonNoColocaSensor, Date recordDate, String recordUser, char pasive, char estado, String deviceid) {
        this.codigoSensor = codigoSensor;
        this.codigoCaso = codigoCaso;
        this.numeroSensor = numeroSensor;
        this.area = area;
        this.cuarto = cuarto;
        this.fechaColocacion = fechaColocacion;
        this.fechaRetiro = fechaRetiro;
        this.horaRetiro = horaRetiro;
        this.observacionRetiro = observacionRetiro;
        this.sensorSN = sensorSN;
        this.razonNoColocaSensor = razonNoColocaSensor;
        this.recordDate = recordDate;
        this.recordUser = recordUser;
        this.pasive = pasive;
        this.estado = estado;
        this.deviceid = deviceid;
    }

    public String getCodigoSensor() {
        return codigoSensor;
    }

    public void setCodigoSensor(String codigoSensor) {
        this.codigoSensor = codigoSensor;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getNumeroSensor() {
        return numeroSensor;
    }

    public void setNumeroSensor(String numeroSensor) {
        this.numeroSensor = numeroSensor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCuarto() {
        return cuarto;
    }

    public void setCuarto(String cuarto) {
        this.cuarto = cuarto;
    }

    public Date getFechaColocacion() {
        return fechaColocacion;
    }

    public void setFechaColocacion(Date fechaColocacion) {
        this.fechaColocacion = fechaColocacion;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getHoraRetiro() {
        return horaRetiro;
    }

    public void setHoraRetiro(String horaRetiro) {
        this.horaRetiro = horaRetiro;
    }

    public String getObservacionRetiro() {
        return observacionRetiro;
    }

    public void setObservacionRetiro(String observacionRetiro) {
        this.observacionRetiro = observacionRetiro;
    }

    public String getSensorSN() {
        return sensorSN;
    }

    public void setSensorSN(String sensorSN) {
        this.sensorSN = sensorSN;
    }

    public String getRazonNoColocaSensor() {
        return razonNoColocaSensor;
    }

    public void setRazonNoColocaSensor(String razonNoColocaSensor) {
        this.razonNoColocaSensor = razonNoColocaSensor;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }

    public char getPasive() {
        return pasive;
    }

    public void setPasive(char pasive) {
        this.pasive = pasive;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}
