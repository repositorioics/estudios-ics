package ni.org.ics.estudios.dto.muestras;

import ni.org.ics.estudios.web.utils.StringUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PC on 2/8/2022.
 */
public class MxDto implements Serializable {

    private Integer id;
    private String casaFam;
    private String fechaToma;
    private String fechaRegistro;
    private String usuario;
    private Integer codigoParticipante;
    private String enfermo;
    private String codLabMuestra;
    private double volumen;
    private Integer numPinchazos;
    private String tipoMuestra;
    private String tipoTubo;
    private String tuboBhc;
    private String tuboPbmc;
    private String tuboRojo;
    private String proposito;
    private String estudiosActuales;
    private String muestraTomada;
    private String razonNoToma;
    private String terreno;
    private String sweetAlerta;
    private Date fechaInicio;
    private Date fechaInactiva;
    private String estado;
    private String diasActiva;
    private String diasInactiva;

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCasaFam() {
        return casaFam;
    }

    public void setCasaFam(String casaFam) {
        this.casaFam = casaFam;
    }

    public String getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(String fechaToma) {
        this.fechaToma = fechaToma;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(Integer codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    public String getEnfermo() {
        return enfermo;
    }

    public void setEnfermo(String enfermo) {
        this.enfermo = enfermo;
    }

    public String getCodLabMuestra() {
        return codLabMuestra;
    }

    public void setCodLabMuestra(String codLabMuestra) {
        this.codLabMuestra = codLabMuestra;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public Integer getNumPinchazos() {
        return numPinchazos;
    }

    public void setNumPinchazos(Integer numPinchazos) {
        this.numPinchazos = numPinchazos;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public String getTipoTubo() {
        return tipoTubo;
    }

    public void setTipoTubo(String tipoTubo) {
        this.tipoTubo = tipoTubo;
    }

    public String getTuboBhc() {
        return tuboBhc;
    }

    public void setTuboBhc(String tuboBhc) {
        this.tuboBhc = tuboBhc;
    }

    public String getTuboPbmc() {
        return tuboPbmc;
    }

    public void setTuboPbmc(String tuboPbmc) {
        this.tuboPbmc = tuboPbmc;
    }

    public String getTuboRojo() {
        return tuboRojo;
    }

    public void setTuboRojo(String tuboRojo) {
        this.tuboRojo = tuboRojo;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getEstudiosActuales() {
        return estudiosActuales;
    }

    public void setEstudiosActuales(String estudiosActuales) {
        this.estudiosActuales = estudiosActuales;
    }

    public String getMuestraTomada() {
        return muestraTomada;
    }

    public void setMuestraTomada(String muestraTomada) {
        this.muestraTomada = muestraTomada;
    }

    public String getRazonNoToma() {
        return razonNoToma;
    }

    public void setRazonNoToma(String razonNoToma) {
        this.razonNoToma = razonNoToma;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public String getSweetAlerta() {
        return sweetAlerta;
    }

    public void setSweetAlerta(String sweetAlerta) {
        this.sweetAlerta = sweetAlerta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInactiva() {
        return fechaInactiva;
    }

    public void setFechaInactiva(Date fechaInactiva) {
        this.fechaInactiva = fechaInactiva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDiasActiva() {
        return diasActiva;
    }

    public void setDiasActiva(String diasActiva) {
        this.diasActiva = diasActiva;
    }

    public String getDiasInactiva() {
        return diasInactiva;
    }

    public void setDiasInactiva(String diasInactiva) {
        this.diasInactiva = diasInactiva;
    }
}
