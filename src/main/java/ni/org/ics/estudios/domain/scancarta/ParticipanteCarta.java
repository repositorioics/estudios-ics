package ni.org.ics.estudios.domain.scancarta;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Version;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 14/01/2020.
 */

@Entity
@Table(name = "participantecarta", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "idparticipantecarta") })
public class ParticipanteCarta extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer idparticipantecarta;
    private Participante participante;
    private Version version;
    private Personal personal;
    private Date fechacarta;
    private String asentimiento;
    private String recurso;
    private Integer relfam;
    private String quienfirma;
    private String nombre2Firma;
    private String apellido1Firma;
    private String apellido2Firma;
    private String proyecto;
    private char contactoFuturo;
    private char retirado = '0';
    private String observacion;
    private Integer tipoasentimiento;

    @Id
    @Column(name = "idparticipantecarta", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdparticipantecarta() {
        return idparticipantecarta;
    }

    public void setIdparticipantecarta(Integer idparticipantecarta) {
        this.idparticipantecarta = idparticipantecarta;
    }

    @ManyToOne
    @JoinColumn(name = "idparticipante", nullable = false)
    @ForeignKey(name = "FK_IDPARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @ManyToOne
    @JoinColumn(name = "idversion", nullable = false)
    @ForeignKey(name = "FK_VERSION")
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }


    @Column(name = "fechacarta", nullable = false)
    public Date getFechacarta() {
        return fechacarta;
    }

    public void setFechacarta(Date fechacarta) {
        this.fechacarta = fechacarta;
    }


    @Column(name = "asentimiento", nullable = true, length = 6)
    public String getAsentimiento() {
        return asentimiento;
    }

    public void setAsentimiento(String asentimiento) {
        this.asentimiento = asentimiento;
    }

    @Column(name = "recurso", nullable = true)
    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    @ManyToOne
    @JoinColumn(name = "idPersona", nullable = false)
    @ForeignKey(name = "FK_IDPERSONA")
    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    @Column(name = "relfam", nullable = false)
    public Integer getRelfam() {
        return relfam;
    }

    public void setRelfam(Integer relfam) {
        this.relfam = relfam;
    }


    @Column(name = "quienfirma", nullable = false)
    public String getQuienfirma() {
        return quienfirma;
    }

    public void setQuienfirma(String quienfirma) {
        this.quienfirma = quienfirma;
    }

    @Column(name = "nombre2firma", nullable = false)
    public String getNombre2Firma() {
        return nombre2Firma;
    }

    public void setNombre2Firma(String nombre2Firma) {
        this.nombre2Firma = nombre2Firma;
    }
    @Column(name = "apellido1firma", nullable = false)
    public String getApellido1Firma() {
        return apellido1Firma;
    }

    public void setApellido1Firma(String apellido1Firma) {
        this.apellido1Firma = apellido1Firma;
    }
    @Column(name = "apellido2firma", nullable = false)
    public String getApellido2Firma() {
        return apellido2Firma;
    }

    public void setApellido2Firma(String apellido2Firma) {
        this.apellido2Firma = apellido2Firma;
    }

    @Column(name = "proyecto", nullable = false)
    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
    @Column(name = "contactofuturo", nullable = true)
    public char getContactoFuturo() {
        return contactoFuturo;
    }

    public void setContactoFuturo(char contactoFuturo) {
        this.contactoFuturo = contactoFuturo;
    }

    @Column(name = "tipoasentimiento", nullable = true)
    public Integer getTipoasentimiento() {
        return tipoasentimiento;
    }

    public void setTipoasentimiento(Integer tipoasentimiento) {
        this.tipoasentimiento = tipoasentimiento;
    }

    @Column(name="retirado", nullable = false, length = 1)
    public char getRetirado() {
        return retirado;
    }

    public void setRetirado(char retirado) {
        this.retirado = retirado;
    }

    @Column(name="observacion", nullable = true, length = 255)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Transient
     public boolean isEsRetirado() {
        return this.retirado == '1';
    }

    @Transient
    public boolean isEsContacto() {
        return this.contactoFuturo == '1';
    }
}
