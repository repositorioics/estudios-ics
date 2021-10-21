package ni.org.ics.estudios.domain.scancarta;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Version;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ICS on 03/08/2021.
 */
@Entity
@Table(name = "scan_participante_carta_tmp", catalog = "estudios_ics")
public class ParticipanteCartaTmp extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idparticipante;
    private Version version;
    private int recurso;
    private Date fechacarta;
    private String asentimiento;
    private Integer relfam;
    private String name1tutor;
    private String name2tutor;
    private String surname1tutor;
    private String surname2tutor;
    private String proyecto;
    private boolean contactoFuturo;
    private String observacion;
    private Integer tipoasentimiento;
    private boolean testigopresent;
    private String nombre1testigo;
    private String nombre2testigo;
    private String apellido1testigo;
    private String apellido2testigo;
    private String estudios;


    public  ParticipanteCartaTmp(){}

    @Id
    @Column(name = "ID_PARTICIPANTECARTA_TMP", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "CODIGO_PARTICIPANTE", nullable = false)
    public int getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(int idparticipante) {
        this.idparticipante = idparticipante;
    }

    @ManyToOne
    @JoinColumn(name = "IDVERSION", nullable = false)
    @ForeignKey(name = "FK_VERSION")
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }


    @Column(name = "RECURSO", nullable = false)
    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }



    @Column(name = "FECHA_CARTA", nullable = false)
    public Date getFechacarta() {
        return fechacarta;
    }

    public void setFechacarta(Date fechacarta) {
        this.fechacarta = fechacarta;
    }


    @Column(name = "ASENTIMIENTO", nullable = false)
    public String getAsentimiento() {
        return asentimiento;
    }

    public void setAsentimiento(String asentimiento) {
        this.asentimiento = asentimiento;
    }

    @Column(name = "RELACION_FAMILIAR", nullable = false)
    public Integer getRelfam() {
        return relfam;
    }

    public void setRelfam(Integer relfam) {
        this.relfam = relfam;
    }
    @Column(name = "NAME1TUTOR", nullable = false)
    public String getName1tutor() {
        return name1tutor;
    }

    public void setName1tutor(String name1tutor) {
        this.name1tutor = name1tutor;
    }
    @Column(name = "NAME2TUTOR", nullable = false)
    public String getName2tutor() {
        return name2tutor;
    }

    public void setName2tutor(String name2tutor) {
        this.name2tutor = name2tutor;
    }

    @Column(name = "SURNAME1TUTOR", nullable = false)
    public String getSurname1tutor() {
        return surname1tutor;
    }

    public void setSurname1tutor(String surname1tutor) {
        this.surname1tutor = surname1tutor;
    }
    @Column(name = "SURNAME2TUTOR", nullable = false)
    public String getSurname2tutor() {
        return surname2tutor;
    }

    public void setSurname2tutor(String surname2tutor) {
        this.surname2tutor = surname2tutor;
    }
    @Column(name = "PROYECTO", nullable = false)
    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    @Column(name = "CONTACTO_FUTURO", nullable = true)
    public boolean isContactoFuturo() {
        return contactoFuturo;
    }

    public void setContactoFuturo(boolean contactoFuturo) {
        this.contactoFuturo = contactoFuturo;
    }



    @Column(name="OBSERVACION", nullable = true, length = 255)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    @Column(name = "TIPO_ASENTIMIENTO", nullable = true)
    public Integer getTipoasentimiento() {
        return tipoasentimiento;
    }

    public void setTipoasentimiento(Integer tipoasentimiento) {
        this.tipoasentimiento = tipoasentimiento;
    }
    @Column(name="TESTIGO_PRESENT")
    public boolean isTestigopresent() {
        return testigopresent;
    }

    public void setTestigopresent(boolean testigopresent) {
        this.testigopresent = testigopresent;
    }
    @Column(name="NAME1TESTIGO", nullable = true, length = 50)
    public String getNombre1testigo() {
        return nombre1testigo;
    }

    public void setNombre1testigo(String nombre1testigo) {
        this.nombre1testigo = nombre1testigo;
    }
    @Column(name="NAME2TESTIGO", nullable = true, length = 50)
    public String getNombre2testigo() {
        return nombre2testigo;
    }

    public void setNombre2testigo(String nombre2testigo) {
        this.nombre2testigo = nombre2testigo;
    }

    @Column(name="SURNAME1TESTIGO", nullable = true, length = 50)
    public String getApellido1testigo() {
        return apellido1testigo;
    }

    public void setApellido1testigo(String apellido1testigo) {
        this.apellido1testigo = apellido1testigo;
    }
    @Column(name="SURNAME2TESTIGO", nullable = true, length = 50)
    public String getApellido2testigo() {
        return apellido2testigo;
    }

    public void setApellido2testigo(String apellido2testigo) {
        this.apellido2testigo = apellido2testigo;
    }

    @Column(name = "ESTUDIO", nullable = false, length = 255)
    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return false;
    }
}
