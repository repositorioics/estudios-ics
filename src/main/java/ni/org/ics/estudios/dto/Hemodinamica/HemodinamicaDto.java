package ni.org.ics.estudios.dto.Hemodinamica;

import ni.org.ics.estudios.domain.hemodinamica.HemoDetalle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PC on 21/12/2022.
 */
public class HemodinamicaDto implements Serializable {

    private String idDatoHemo;
    private Integer codigo_participante;
    private Date fechaRegistro;
    private String usuario;
    private double asuperficiecorporal;
    private String edad;
    private Date fecha;
    private double imc;
    private String nExpediente;
    private String peso;
    private double talla;
    private Date fechaInicioEnfermedad;
    private Integer dias_enfermo;
    private Character positivo;
    List<HemoDetalle>listaDetalles = new ArrayList<HemoDetalle>();


    public HemodinamicaDto(){}

    public HemodinamicaDto(String idDatoHemo, Integer codigo_participante, Date fechaRegistro, String usuario, double asuperficiecorporal, String edad, Date fecha, double imc, String nExpediente, String peso, double talla, Date fechaInicioEnfermedad, Integer dias_enfermo, Character positivo, List<HemoDetalle> listaDetalles) {
        this.idDatoHemo = idDatoHemo;
        this.codigo_participante = codigo_participante;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
        this.asuperficiecorporal = asuperficiecorporal;
        this.edad = edad;
        this.fecha = fecha;
        this.imc = imc;
        this.nExpediente = nExpediente;
        this.peso = peso;
        this.talla = talla;
        this.fechaInicioEnfermedad = fechaInicioEnfermedad;
        this.dias_enfermo = dias_enfermo;
        this.positivo = positivo;
        this.listaDetalles = listaDetalles;
    }

    public String getIdDatoHemo() {
        return idDatoHemo;
    }

    public void setIdDatoHemo(String idDatoHemo) {
        this.idDatoHemo = idDatoHemo;
    }

    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getAsuperficiecorporal() {
        return asuperficiecorporal;
    }

    public void setAsuperficiecorporal(double asuperficiecorporal) {
        this.asuperficiecorporal = asuperficiecorporal;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getnExpediente() {
        return nExpediente;
    }

    public void setnExpediente(String nExpediente) {
        this.nExpediente = nExpediente;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public Date getFechaInicioEnfermedad() {
        return fechaInicioEnfermedad;
    }

    public void setFechaInicioEnfermedad(Date fechaInicioEnfermedad) {
        this.fechaInicioEnfermedad = fechaInicioEnfermedad;
    }

    public Integer getDias_enfermo() {
        return dias_enfermo;
    }

    public void setDias_enfermo(Integer dias_enfermo) {
        this.dias_enfermo = dias_enfermo;
    }

    public Character getPositivo() {
        return positivo;
    }

    public void setPositivo(Character positivo) {
        this.positivo = positivo;
    }

    public List<HemoDetalle> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<HemoDetalle> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
}
