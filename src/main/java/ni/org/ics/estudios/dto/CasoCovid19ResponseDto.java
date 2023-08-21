package ni.org.ics.estudios.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS_Inspiron3 on 25/07/2019.
 */


public class CasoCovid19ResponseDto implements Serializable {

    private String codigoCaso;
    private Integer codigoParticipante;
    private Integer cantidadRetirados;

    public CasoCovid19ResponseDto(){}

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

    public Integer getCantidadRetirados() {
        return cantidadRetirados;
    }

    public void setCantidadRetirados(Integer cantidadRetirados) {
        this.cantidadRetirados = cantidadRetirados;
    }
}