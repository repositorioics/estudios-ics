package ni.org.ics.estudios.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 26/11/2020. ESTE DTO ES PARA MAPEAR LA TABLA DOCUMENTACION_RETIRO_DATA PERO NO ES NECESARIA
 */
public class RetiroDto implements Serializable {

    private Integer idretiro;
    private String codigocasafamilia;
    private Integer codigocasapdcs;
    private Character devolviocarnet;
    private String estudioretirado;
    private String estudios_anteriores;
    private Date fechafallecido;
    private Date fecharetiro;
    private Integer medicosupervisor;
    private String motivo;
    private String descripcionretiro;
    private String observaciones;
    private String otrosmotivo;
    private Integer personadocumenta;
    private String quiencomunica;
    private Integer relfam;
    private Date fecha_registro;
    private String usuario_registro;
    private Boolean actual=true;
    private String tipofecha;
    private Integer codigo_participante;

    public RetiroDto() {
    }

    public RetiroDto(Integer idretiro, String codigocasafamilia, Integer codigocasapdcs, Character devolviocarnet, String estudioretirado, String estudios_anteriores, Date fechafallecido, Date fecharetiro, Integer medicosupervisor, String motivo, String observaciones, String otrosmotivo, Integer personadocumenta, String quiencomunica, Integer relfam, Date fecha_registro, String usuario_registro, Boolean actual, String tipofecha, Integer codigo_participante, String descripcionretiro) {
        this.idretiro = idretiro;
        this.codigocasafamilia = codigocasafamilia;
        this.codigocasapdcs = codigocasapdcs;
        this.devolviocarnet = devolviocarnet;
        this.estudioretirado = estudioretirado;
        this.estudios_anteriores = estudios_anteriores;
        this.fechafallecido = fechafallecido;
        this.fecharetiro = fecharetiro;
        this.medicosupervisor = medicosupervisor;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.otrosmotivo = otrosmotivo;
        this.personadocumenta = personadocumenta;
        this.quiencomunica = quiencomunica;
        this.relfam = relfam;
        this.fecha_registro = fecha_registro;
        this.usuario_registro = usuario_registro;
        this.actual = actual;
        this.tipofecha = tipofecha;
        this.codigo_participante = codigo_participante;
        this.descripcionretiro = descripcionretiro;
    }



    public Integer getIdretiro() {
        return idretiro;
    }

    public void setIdretiro(Integer idretiro) {
        this.idretiro = idretiro;
    }


    public String getCodigocasafamilia() {
        return codigocasafamilia;
    }

    public void setCodigocasafamilia(String codigocasafamilia) {
        this.codigocasafamilia = codigocasafamilia;
    }

    public Integer getCodigocasapdcs() {
        return codigocasapdcs;
    }

    public void setCodigocasapdcs(Integer codigocasapdcs) {
        this.codigocasapdcs = codigocasapdcs;
    }

    public Character getDevolviocarnet() {
        return devolviocarnet;
    }

    public void setDevolviocarnet(Character devolviocarnet) {
        this.devolviocarnet = devolviocarnet;
    }

    public String getEstudioretirado() {
        return estudioretirado;
    }

    public void setEstudioretirado(String estudioretirado) {
        this.estudioretirado = estudioretirado;
    }

    public String getEstudios_anteriores() {
        return estudios_anteriores;
    }

    public void setEstudios_anteriores(String estudios_anteriores) {
        this.estudios_anteriores = estudios_anteriores;
    }

    public Date getFechafallecido() {
        return fechafallecido;
    }

    public void setFechafallecido(Date fechafallecido) {
        this.fechafallecido = fechafallecido;
    }

    public Date getFecharetiro() {
        return fecharetiro;
    }

    public void setFecharetiro(Date fecharetiro) {
        this.fecharetiro = fecharetiro;
    }

    public Integer getMedicosupervisor() {
        return medicosupervisor;
    }

    public void setMedicosupervisor(Integer medicosupervisor) {
        this.medicosupervisor = medicosupervisor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getOtrosmotivo() {
        return otrosmotivo;
    }

    public void setOtrosmotivo(String otrosmotivo) {
        this.otrosmotivo = otrosmotivo;
    }

    public Integer getPersonadocumenta() {
        return personadocumenta;
    }

    public void setPersonadocumenta(Integer personadocumenta) {
        this.personadocumenta = personadocumenta;
    }

    public String getQuiencomunica() {
        return quiencomunica;
    }

    public void setQuiencomunica(String quiencomunica) {
        this.quiencomunica = quiencomunica;
    }

    public Integer getRelfam() {
        return relfam;
    }

    public void setRelfam(Integer relfam) {
        this.relfam = relfam;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getUsuario_registro() {
        return usuario_registro;
    }

    public void setUsuario_registro(String usuario_registro) {
        this.usuario_registro = usuario_registro;
    }

    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    public String getTipofecha() {
        return tipofecha;
    }

    public void setTipofecha(String tipofecha) {
        this.tipofecha = tipofecha;
    }

    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }


    public String getDescripcionretiro() {
        return descripcionretiro;
    }

    public void setDescripcionretiro(String descripcionretiro) {
        this.descripcionretiro = descripcionretiro;
    }
}
