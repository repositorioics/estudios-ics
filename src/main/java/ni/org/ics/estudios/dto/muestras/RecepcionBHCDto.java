package ni.org.ics.estudios.dto.muestras;

import ni.org.ics.estudios.domain.muestreoanual.RecepcionBHCId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

public class RecepcionBHCDto {

	/**
	 * 
	 */
    private Integer codigo;
    private Date fechaRecBHC;
	private Double volumen;
	private String lugar;
	private String observacion;
	private String username;
	private Date fecreg;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRecBHC() {
        return fechaRecBHC;
    }

    public void setFechaRecBHC(Date fechaRecBHC) {
        this.fechaRecBHC = fechaRecBHC;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFecreg() {
        return fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }
}
