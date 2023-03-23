package ni.org.ics.estudios.dto;

import java.io.Serializable;

/**
 * Created by ICS on 26/11/2021.
 */
public class CargoDto implements Serializable {

    private int codigoCargo;
    private String nombreCargo;
    private boolean estado;

    public CargoDto(){}

    public CargoDto(int codigoCargo, String nombreCargo, boolean estado) {
        this.codigoCargo = codigoCargo;
        this.nombreCargo = nombreCargo;
        this.estado = estado;
    }

    public int getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(int codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
