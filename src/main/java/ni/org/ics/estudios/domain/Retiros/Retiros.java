package ni.org.ics.estudios.domain.Retiros;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Personal;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 29/10/2020.
 */

@Entity
@Table(name = "documentacion_retiro_data", catalog = "estudios_ics")
public class Retiros extends BaseMetaData implements Auditable {


    private static final long serialVersionUID = 1L;


    private Integer idretiro;

    private Participante participante;

    private Integer codigocasapdcs;

    private String codigocasafamilia;

    private Date fecharetiro;

    private String tipofecha;

    private String quiencomunica;

    private Integer relfam;

    private Integer personadocumenta;

    private Integer medicosupervisor;

    private String motivo;

    private String otrosmotivo;

    private Character devolviocarnet;

    private String observaciones;


    private Date fechafallecido;

    private String estudiosanteriores;

    private String estudioretirado;

    private Boolean actual=true;

    public Retiros() {
    }

    public Retiros(Integer idretiro, Participante participante, Integer codigocasapdcs, String codigocasafamilia, Date fecharetiro, String tipofecha, String quiencomunica, Integer medicosupervisor, Integer relfam,  Integer personadocumenta, String motivo, String otrosmotivo, Character devolviocarnet, String observaciones,  Date fechafallecido, String estudiosanteriores, String estudioretirado,Boolean actual) {
        this.idretiro = idretiro;
        this.participante = participante;
        this.codigocasapdcs = codigocasapdcs;
        this.codigocasafamilia = codigocasafamilia;
        this.fecharetiro = fecharetiro;
        this.tipofecha = tipofecha;
        this.quiencomunica = quiencomunica;
        this.relfam = relfam;
        this.personadocumenta = personadocumenta;
        this.motivo = motivo;
        this.otrosmotivo = otrosmotivo;
        this.devolviocarnet = devolviocarnet;
        this.observaciones = observaciones;
        this.fechafallecido = fechafallecido;
        this.estudiosanteriores = estudiosanteriores;
        this.estudioretirado = estudioretirado;
        this.actual = actual;
        this.medicosupervisor = medicosupervisor;
    }


    @Id
    @Column(name = "IDRETIRO", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdretiro() {
        return idretiro;
    }

    public void setIdretiro(Integer idretiro) {
        this.idretiro = idretiro;
    }


    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_CODIGO_PARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "CODIGO_CASAPDCS", nullable = true)
    public Integer getCodigocasapdcs() {
        return codigocasapdcs;
    }

    public void setCodigocasapdcs(Integer codigocasapdcs) {
        this.codigocasapdcs = codigocasapdcs;
    }

    @Column(name = "CASA_FAMILIA", nullable = true)
    public String getCodigocasafamilia() {
        return codigocasafamilia;
    }

    public void setCodigocasafamilia(String codigocasafamilia) {
        this.codigocasafamilia = codigocasafamilia;
    }

    @Column(name = "FECHA_RETIRO", nullable = true)
    public Date getFecharetiro() {
        return fecharetiro;
    }

    public void setFecharetiro(Date fecharetiro) {
        this.fecharetiro = fecharetiro;
    }
    @Column(name = "TIPO_FECHA", length = 2, nullable = true)
    public String getTipofecha() {
        return tipofecha;
    }

    public void setTipofecha(String tipofecha) {
        this.tipofecha = tipofecha;
    }

    @Column(name = "QUIEN_COMUNICA", nullable = true)
    public String getQuiencomunica() {
        return quiencomunica;
    }
    public void setQuiencomunica(String quiencomunica) {
        this.quiencomunica = quiencomunica;
    }

    @Column(name = "RELFAM", nullable = true)
    public Integer getRelfam() {
        return relfam;
    }

    public void setRelfam(Integer relfam) {
        this.relfam = relfam;
    }

    @Column(name = "PERSONA_DOCUMENTA", nullable = true)
    public Integer getPersonadocumenta() {
        return personadocumenta;
    }

    public void setPersonadocumenta(Integer personadocumenta) {
        this.personadocumenta = personadocumenta;
    }
    @Column(name = "MOTIVO_RETIRO", nullable = true)
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Column(name = "OTRO_MOTIVO_RETIRO", nullable = true)
    public String getOtrosmotivo() {
        return otrosmotivo;
    }

    public void setOtrosmotivo(String otrosmotivo) {
        this.otrosmotivo = otrosmotivo;
    }

    @Column(name = "DEVOLVIO_CARNET", nullable = true)
    public Character getDevolviocarnet() {
        return devolviocarnet;
    }

    public void setDevolviocarnet(Character devolviocarnet) {
        this.devolviocarnet = devolviocarnet;
    }

    @Column(name = "OBSERVACIONES", nullable = true)
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(name = "FECHA_FALLECIDO", nullable = true)
    public Date getFechafallecido() {
        return fechafallecido;
    }

    public void setFechafallecido(Date fechafallecido) {
        this.fechafallecido = fechafallecido;
    }

    @Column(name = "ESTUDIOS_ANTERIORES", nullable = true)
    public String getEstudiosanteriores() {
        return estudiosanteriores;
    }

    public void setEstudiosanteriores(String estudiosanteriores) {
        this.estudiosanteriores = estudiosanteriores;
    }

    @Column(name = "ESTUDIO_RETIRADO", nullable = true)
    public String getEstudioretirado() {
        return estudioretirado;
    }

    public void setEstudioretirado(String estudioretirado) {
        this.estudioretirado = estudioretirado;
    }

    @Column(name = "ACTUAL",  nullable = true)
    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    @Column(name = "MEDICO_SUPERVISOR", nullable = true)
    public Integer getMedicosupervisor() {
        return medicosupervisor;
    }

    public void setMedicosupervisor(Integer medicosupervisor) {
        this.medicosupervisor = medicosupervisor;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        // TODO Auto-generated method stub
        return true;
    }
}
