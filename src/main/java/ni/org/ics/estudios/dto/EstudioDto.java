package ni.org.ics.estudios.dto;

import java.io.Serializable;

/**
 * Created by ICS on 07/12/2020.
 */
public class EstudioDto implements Serializable {

    private Integer prioridad;
    private String nombreEstudio;

    public EstudioDto() {
    }

    public EstudioDto(Integer prioridad, String nombreEstudio) {
        this.prioridad = prioridad;
        this.nombreEstudio = nombreEstudio;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public String getNombreEstudio() {
        return nombreEstudio;
    }

    public void setNombreEstudio(String nombreEstudio) {
        this.nombreEstudio = nombreEstudio;
    }
}
