package ni.org.ics.estudios.dto;

import java.math.BigInteger;
import java.util.Date;
/**
 * Created by miguel on 20/3/2022.
 */
public class BhcEnvioDto {

    private Integer codigo;
    private Date fecha;
    private Double volumen;
    private String observacion;
    private String adnDengue;
    private String adnUO1;
    private String adnFlu;
    private String adnChf;
    private String pRecepciona;
    private String estudios;
    private Double edadA;
    private Double edadM;
    private Integer viaje;
    private Integer lugar_envio;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getAdnDengue() {
        return adnDengue;
    }

    public void setAdnDengue(String adnDengue) {
        this.adnDengue = adnDengue;
    }

    public String getAdnUO1() {
        return adnUO1;
    }

    public void setAdnUO1(String adnUO1) {
        this.adnUO1 = adnUO1;
    }

    public String getAdnFlu() {
        return adnFlu;
    }

    public void setAdnFlu(String adnFlu) {
        this.adnFlu = adnFlu;
    }

    public String getAdnChf() {
        return adnChf;
    }

    public void setAdnChf(String adnChf) {
        this.adnChf = adnChf;
    }

    public String getpRecepciona() {
        return pRecepciona;
    }

    public void setpRecepciona(String pRecepciona) {
        this.pRecepciona = pRecepciona;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public Double getEdadA() {
        return edadA;
    }

    public void setEdadA(Double edadA) {
        this.edadA = edadA;
    }

    public Double getEdadM() {
        return edadM;
    }

    public void setEdadM(Double edadM) {
        this.edadM = edadM;
    }

    public Integer getViaje() {
        return viaje;
    }

    public void setViaje(Integer viaje) {
        this.viaje = viaje;
    }

    public Integer getLugar_envio() {
        return lugar_envio;
    }

    public void setLugar_envio(Integer lugar_envio) {
        this.lugar_envio = lugar_envio;
    }
}
