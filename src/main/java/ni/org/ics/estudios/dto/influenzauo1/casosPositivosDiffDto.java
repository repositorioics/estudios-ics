package ni.org.ics.estudios.dto.influenzauo1;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Lserrano on 26/07/2023.
 */
public class casosPositivosDiffDto implements Serializable {

    private String codigoCaso;
    private Integer codigoParticipante;
    private String fechaIngreso;
    private String hoy;
    private BigInteger diasTranscurridos;
    private Integer diasDeBusqueda;
    private String casaCHF;

    public casosPositivosDiffDto(){}

    public casosPositivosDiffDto(String codigoCaso, Integer codigoParticipante, String fechaIngreso, String hoy, BigInteger diasTranscurridos, Integer diasDeBusqueda, String casaCHF) {
        this.codigoCaso = codigoCaso;
        this.codigoParticipante = codigoParticipante;
        this.fechaIngreso = fechaIngreso;
        this.hoy = hoy;
        this.diasTranscurridos = diasTranscurridos;
        this.diasDeBusqueda = diasDeBusqueda;
        this.casaCHF = casaCHF;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public Integer getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(Integer codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getHoy() {
        return hoy;
    }

    public void setHoy(String hoy) {
        this.hoy = hoy;
    }

    public BigInteger getDiasTranscurridos() {
        return diasTranscurridos;
    }

    public void setDiasTranscurridos(BigInteger diasTranscurridos) {
        this.diasTranscurridos = diasTranscurridos;
    }

    public Integer getDiasDeBusqueda() {
        return diasDeBusqueda;
    }

    public void setDiasDeBusqueda(Integer diasDeBusqueda) {
        this.diasDeBusqueda = diasDeBusqueda;
    }

    public String getCasaCHF() {
        return casaCHF;
    }

    public void setCasaCHF(String casaCHF) {
        this.casaCHF = casaCHF;
    }
}
