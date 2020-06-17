package ni.org.ics.estudios.dto;

import java.io.Serializable;

public class ParticipanteBusquedaDto implements Serializable {

    private Integer codigo;
    private Integer casaPediatrica;
    private String casaFamilia;
    private String estudios;
    private String subEstudios;
    private Integer estado;
    private String validacion;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCasaPediatrica() {
        return casaPediatrica;
    }

    public void setCasaPediatrica(Integer casaPediatrica) {
        this.casaPediatrica = casaPediatrica;
    }

    public String getCasaFamilia() {
        return casaFamilia;
    }

    public void setCasaFamilia(String casaFamilia) {
        this.casaFamilia = casaFamilia;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getSubEstudios() {
        return subEstudios;
    }

    public void setSubEstudios(String subEstudios) {
        this.subEstudios = subEstudios;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }
}
