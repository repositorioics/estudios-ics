package ni.org.ics.estudios.domain.muestreoanual;

import ni.org.ics.estudios.domain.BaseMetaData;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by miguel on 13/3/2022.
 */
@Entity
@Table(name = "ma_datos_verificacion", catalog = "estudios_ics")
public class DatosVerificacionMA extends BaseMetaData implements Serializable {

    private Integer idVerifica;
    private Integer codigoParticipante;
    private String conQuienAcude;
    private String relFamAcude;
    private String otraRelFamAcude;
    private String asentimiento;
    private String nuevaDireccion;
    private Integer codigoBarrio;
    private String contacto;
    private String otraFormaContacto;
    private String observacion;

    @Id
    @Column(name = "ID_VERIFICA", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdVerifica() {
        return idVerifica;
    }

    public void setIdVerifica(Integer idVerifica) {
        this.idVerifica = idVerifica;
    }

    @Column(name = "CODIGO_PARTICIPANTE", nullable = false, length = 6)
    public Integer getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(Integer codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    @Column(name = "CON_QUIEN_ACUDE", nullable = false, length = 128)
    public String getConQuienAcude() {
        return conQuienAcude;
    }

    public void setConQuienAcude(String conQuienAcude) {
        this.conQuienAcude = conQuienAcude;
    }

    @Column(name = "RELACION_FAM_ACUDE", nullable = false, length = 3)
    public String getRelFamAcude() {
        return relFamAcude;
    }

    public void setRelFamAcude(String relFamAcude) {
        this.relFamAcude = relFamAcude;
    }

    @Column(name = "OTRA_RELACION_FAM_ACUDE", nullable = false, length = 64)
    public String getOtraRelFamAcude() {
        return otraRelFamAcude;
    }

    public void setOtraRelFamAcude(String otraRelFamAcude) {
        this.otraRelFamAcude = otraRelFamAcude;
    }

    @Column(name = "ASENTIMIENTO", nullable = false, length = 2)
    public String getAsentimiento() {
        return asentimiento;
    }

    public void setAsentimiento(String asentimiento) {
        this.asentimiento = asentimiento;
    }

    @Column(name = "NUEVA_DIRECCION", nullable = true, length = 256)
    public String getNuevaDireccion() {
        return nuevaDireccion;
    }

    public void setNuevaDireccion(String nuevaDireccion) {
        this.nuevaDireccion = nuevaDireccion;
    }

    @Column(name = "CODIGO_BARRIO", nullable = true)
    public Integer getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(Integer codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    @Column(name = "CONTACTO", nullable = false, length = 32)
    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Column(name = "OTRA_FORMA_CONTACTO", nullable = true, length = 128)
    public String getOtraFormaContacto() {
        return otraFormaContacto;
    }

    public void setOtraFormaContacto(String otraFormaContacto) {
        this.otraFormaContacto = otraFormaContacto;
    }

    @Column(name = "OBSERVACION", nullable = false, length = 512)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
