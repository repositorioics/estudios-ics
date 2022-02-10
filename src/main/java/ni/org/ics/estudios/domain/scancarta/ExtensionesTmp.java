package ni.org.ics.estudios.domain.scancarta;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Version;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 23/08/2021.
 */

@Entity
@Table(name = "scan_detalle_extension_tmp", catalog = "estudios_ics")
public class ExtensionesTmp extends BaseMetaData implements Serializable {

    private Integer idParticipantExtensiontmp;
    private Date fechaExtension;
    private String nombre1Tutor;
    private String nombre2Tutor;
    private String apellido1Tutor;
    private String apellido2Tutor;
    private boolean testigoPresente;
    private String nombre1Testigo;
    private String nombre2Testigo;
    private String apellido1Testigo;
    private String apellido2Testigo;
    private String observacion;
    private ParticipanteCartaTmp participantecartatmp;
    private Extensiones extensiones;
    private boolean anulada;
    private Personal personal;

    public ExtensionesTmp(){}

    @Id
    @Column(name = "ID_PARTICIPANT_EXTENSION_TMP", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdParticipantExtensiontmp() {
        return idParticipantExtensiontmp;
    }

    public void setIdParticipantExtensiontmp(Integer idParticipantExtensiontmp) {
        this.idParticipantExtensiontmp = idParticipantExtensiontmp;
    }
    @Column(name = "FECHA_EXTENSION")
    public Date getFechaExtension() {
        return fechaExtension;
    }

    public void setFechaExtension(Date fechaExtension) {
        this.fechaExtension = fechaExtension;
    }
    @Column(name = "NOMBRE1TUTOR",nullable = false, length = 60)
    public String getNombre1Tutor() {
        return nombre1Tutor;
    }

    public void setNombre1Tutor(String nombre1Tutor) {
        this.nombre1Tutor = nombre1Tutor;
    }
    @Column(name = "NOMBRE2TUTOR", length = 60)
    public String getNombre2Tutor() {
        return nombre2Tutor;
    }

    public void setNombre2Tutor(String nombre2Tutor) {
        this.nombre2Tutor = nombre2Tutor;
    }
    @Column(name = "APELLIDO1TUTOR",nullable = false, length = 60)
    public String getApellido1Tutor() {
        return apellido1Tutor;
    }

    public void setApellido1Tutor(String apellido1Tutor) {
        this.apellido1Tutor = apellido1Tutor;
    }
    @Column(name = "APELLIDO2TUTOR", length = 60)
    public String getApellido2Tutor() {
        return apellido2Tutor;
    }

    public void setApellido2Tutor(String apellido2Tutor) {
        this.apellido2Tutor = apellido2Tutor;
    }
    @Column(name = "TESTIG_PRESENTE")
    public boolean isTestigoPresente() {
        return testigoPresente;
    }

    public void setTestigoPresente(boolean testigoPresente) {
        this.testigoPresente = testigoPresente;
    }
    @Column(name = "NOMBRE1TESTIGO", nullable = false, length = 60)
    public String getNombre1Testigo() {
        return nombre1Testigo;
    }

    public void setNombre1Testigo(String nombre1Testigo) {
        this.nombre1Testigo = nombre1Testigo;
    }
    @Column(name = "NOMBRE2TESTIGO", length = 60)
    public String getNombre2Testigo() {
        return nombre2Testigo;
    }

    public void setNombre2Testigo(String nombre2Testigo) {
        this.nombre2Testigo = nombre2Testigo;
    }
    @Column(name = "APELLIDO1TESTIGO", nullable = false, length = 60)
    public String getApellido1Testigo() {
        return apellido1Testigo;
    }

    public void setApellido1Testigo(String apellido1Testigo) {
        this.apellido1Testigo = apellido1Testigo;
    }
    @Column(name = "APELLIDO2TESTIGO", length = 60)
    public String getApellido2Testigo() {
        return apellido2Testigo;
    }

    public void setApellido2Testigo(String apellido2Testigo) {
        this.apellido2Testigo = apellido2Testigo;
    }
    @Column(name = "OBSERVACION")
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    @ManyToOne
    @JoinColumn(name = "PARTICIPANTE_CARTA_TMP", nullable = false)
    public ParticipanteCartaTmp getParticipantecartatmp() {
        return participantecartatmp;
    }

    public void setParticipantecartatmp(ParticipanteCartaTmp participantecartatmp) {
        this.participantecartatmp = participantecartatmp;
    }

    @ManyToOne
    @JoinColumn(name = "COD_EXTENSION", nullable = false)
    public Extensiones getExtensiones() {
        return extensiones;
    }

    public void setExtensiones(Extensiones extensiones) {
        this.extensiones = extensiones;
    }

    @ManyToOne
    @JoinColumn(name = "PERSONA_ID", nullable = false)
    @ForeignKey(name = "FK_PERSONA_ID")
    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }


}
