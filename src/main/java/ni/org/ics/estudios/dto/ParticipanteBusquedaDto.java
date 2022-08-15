package ni.org.ics.estudios.dto;

import ni.org.ics.estudios.dto.muestras.MxDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteBusquedaDto implements Serializable {

    private Integer codigo;
    private Integer casaPediatrica;
    private String casaFamilia;
    private String estudios;
    private String subEstudios;
    private Integer estado;
    private String validacion;
    /**/
    private String alertas;
    private String intervalo;
    private List<MxDto> muestras = new ArrayList<MxDto>();
    private List<MxDto>chf_muestras = new ArrayList<MxDto>();
    private String sweetAlerta;

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

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public List<MxDto> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<MxDto> muestras) {
        this.muestras = muestras;
    }

    public List<MxDto> getChf_muestras() {
        return chf_muestras;
    }

    public void setChf_muestras(List<MxDto> chf_muestras) {
        this.chf_muestras = chf_muestras;
    }

    public String getAlertas() {
        return alertas;
    }

    public void setAlertas(String alertas) {
        this.alertas = alertas;
    }

    public String getSweetAlerta() {
        return sweetAlerta;
    }

    public void setSweetAlerta(String sweetAlerta) {
        this.sweetAlerta = sweetAlerta;
    }
}
