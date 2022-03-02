package ni.org.ics.estudios.domain;

import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import java.util.Date;

/**
 * Created by FIRSTICT on 4/28/2017.
 * V1.0
 */
@Entity
@Table(name = "cartas_consentimientos", catalog = "estudios_ics")
public class CartaConsentimiento extends BaseMetaData implements Auditable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String codigo;
    private Date fechaFirma;
    private Tamizaje tamizaje;
    private Participante participante;
    private String nombre1Tutor;
    private String nombre2Tutor;
    private String apellido1Tutor;
    private String apellido2Tutor;
    private String relacionFamiliarTutor;
    private String participanteOTutorAlfabeto;
    private String testigoPresente;
    private String nombre1Testigo;
    private String nombre2Testigo;
    private String apellido1Testigo;
    private String apellido2Testigo;
    private String aceptaParteA;
    private String motivoRechazoParteA;
    private String aceptaContactoFuturo;
    private String aceptaParteB; //Consentimiento para almacenamiento y uso de muestras en estudios futuros
    private String aceptaParteC; //Consentimiento adicional para estudios genéticos
    private String aceptaParteD; //Consentimiento adicional para ZIKA (Estudio Cohorte Dengue)
    private String aceptaParteE; //Consentimiento para almacenamiento y uso de meustras en estudios futuros EstudioFLu
    private String aceptaParteF;//re-enrolamiento y obtención de muestras de sangre adicionales-CEIRS. MA2022
    private String version; //Indicar la versión actual al momento de registrar la carta
    //reconsentimiento dengue 2018
    private String otroMotivoRechazoParteA;
    private String motivoRechazoParteDExt;
    private String otroMotivoRechazoParteDExt;
    private String mismoTutor;
    private String motivoDifTutor;
    private String otroMotivoDifTutor;
    private String otraRelacionFamTutor;
    private String verifTutor;
    private String reconsentimiento; //indica si es carta por reconsentimiento del estudio
    private Date fechaRecibido;
    //consentimiento muestras superficie casas
    private String nombre1MxSuperficie;
    private String nombre2MxSuperficie;
    private String apellido1MxSuperficie;
    private String apellido2MxSuperficie;
    private String casaChf;
    //muestra adicional chf covid19
    private String motivoRechazoParteE;
    private String otroMotivoRechazoParteE;
    //re-enrolamiento y obtención de muestras de sangre adicionales-CEIRS. MA2022
    private String motivoRechazoParteF;
    private String otroMotivoRechazoParteF;

    @Id
    @Column(name = "CODIGO", nullable = false, insertable = true, updatable = false, length = 36)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="FECHA_FIRMA")
    public Date getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    @Column(name = "NOMBRE1_TUTOR", length = 100)
    public String getNombre1Tutor() {
        return nombre1Tutor;
    }

    public void setNombre1Tutor(String nombre1Tutor) {
        this.nombre1Tutor = nombre1Tutor;
    }

    @Column(name = "NOMBRE2_TUTOR", length = 100)
    public String getNombre2Tutor() {
        return nombre2Tutor;
    }

    public void setNombre2Tutor(String nombre2Tutor) {
        this.nombre2Tutor = nombre2Tutor;
    }

    @Column(name = "APELLIDO1_TUTOR", length = 100)
    public String getApellido1Tutor() {
        return apellido1Tutor;
    }

    public void setApellido1Tutor(String apellido1Tutor) {
        this.apellido1Tutor = apellido1Tutor;
    }

    @Column(name = "APELLIDO2_TUTOR", length = 100)
    public String getApellido2Tutor() {
        return apellido2Tutor;
    }

    public void setApellido2Tutor(String apellido2Tutor) {
        this.apellido2Tutor = apellido2Tutor;
    }

    @Column(name = "PARTICIPANTE_TUTOR_ALFABETO", length = 1)
    public String getParticipanteOTutorAlfabeto() {
        return participanteOTutorAlfabeto;
    }

    public void setParticipanteOTutorAlfabeto(String participanteOTutorAlfabeto) {
        this.participanteOTutorAlfabeto = participanteOTutorAlfabeto;
    }

    @Column(name = "TESTIGO_PRESENTE", length = 1)
    public String getTestigoPresente() {
        return testigoPresente;
    }

    public void setTestigoPresente(String testigoPresente) {
        this.testigoPresente = testigoPresente;
    }

    @Column(name = "NOMBRE1_TESTIGO", length = 100)
    public String getNombre1Testigo() {
        return nombre1Testigo;
    }

    public void setNombre1Testigo(String nombre1Testigo) {
        this.nombre1Testigo = nombre1Testigo;
    }

    @Column(name = "NOMBRE2_TESTIGO", length = 100)
    public String getNombre2Testigo() {
        return nombre2Testigo;
    }

    public void setNombre2Testigo(String nombre2Testigo) {
        this.nombre2Testigo = nombre2Testigo;
    }

    @Column(name = "APELLIDO1_TESTIGO", length = 100)
    public String getApellido1Testigo() {
        return apellido1Testigo;
    }

    public void setApellido1Testigo(String apellido1Testigo) {
        this.apellido1Testigo = apellido1Testigo;
    }

    @Column(name = "APELLIDO2_TESTIGO", length = 100)
    public String getApellido2Testigo() {
        return apellido2Testigo;
    }

    public void setApellido2Testigo(String apellido2Testigo) {
        this.apellido2Testigo = apellido2Testigo;
    }

    @Column(name = "ACEPTA_PARTE_A", length = 1)
    public String getAceptaParteA() {
        return aceptaParteA;
    }

    public void setAceptaParteA(String aceptaParteA) {
        this.aceptaParteA = aceptaParteA;
    }

    @Column(name = "MOTIVO_RECHAZO_PARTE_A")
    public String getMotivoRechazoParteA() {
        return motivoRechazoParteA;
    }

    public void setMotivoRechazoParteA(String motivoRechazoParteA) {
        this.motivoRechazoParteA = motivoRechazoParteA;
    }

    @Column(name = "ACEPTA_CONTACTO_FUTURO", length = 1)
    public String getAceptaContactoFuturo() {
        return aceptaContactoFuturo;
    }

    public void setAceptaContactoFuturo(String aceptaContactoFuturo) {
        this.aceptaContactoFuturo = aceptaContactoFuturo;
    }

    @Column(name = "ACEPTA_PARTE_B", length = 1)
    public String getAceptaParteB() {
        return aceptaParteB;
    }

    public void setAceptaParteB(String aceptaParteB) {
        this.aceptaParteB = aceptaParteB;
    }

    @Column(name = "ACEPTA_PARTE_C", length = 1)
    public String getAceptaParteC() {
        return aceptaParteC;
    }

    public void setAceptaParteC(String aceptaParteC) {
        this.aceptaParteC = aceptaParteC;
    }

    @Column(name = "ACEPTA_PARTE_D", length = 1)
    public String getAceptaParteD() {
        return aceptaParteD;
    }

    public void setAceptaParteD(String aceptaParteD) {
        this.aceptaParteD = aceptaParteD;
    }

    @Column(name = "VERSION", length = 10)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_TAMIZAJE", nullable = false)
    @ForeignKey(name = "FK_TAMIZAJE_CARTACON")
    public Tamizaje getTamizaje() {
        return tamizaje;
    }

    public void setTamizaje(Tamizaje tamizaje) {
        this.tamizaje = tamizaje;
    }

    @ManyToOne
    @JoinColumn(name="CODIGO_PARTICIPANTE", nullable = true)
    @ForeignKey(name = "FK_PARTICIPANTE_CARTACON")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "RELACION_FAMILIAR", nullable = true, length = 50)
    public String getRelacionFamiliarTutor() {
        return relacionFamiliarTutor;
    }

    public void setRelacionFamiliarTutor(String relacionFamiliar) {
        this.relacionFamiliarTutor = relacionFamiliar;
    }

    @Column(name = "OTRO_MOT_RECHAZO_PARTE_A", nullable = true)
    public String getOtroMotivoRechazoParteA() {
        return otroMotivoRechazoParteA;
    }

    public void setOtroMotivoRechazoParteA(String otroMotivoRechazoParteA) {
        this.otroMotivoRechazoParteA = otroMotivoRechazoParteA;
    }

    @Column(name = "MOTIVO_RECHAZO_PARTE_D", nullable = true, length = 2)
    public String getMotivoRechazoParteDExt() {
        return motivoRechazoParteDExt;
    }

    public void setMotivoRechazoParteDExt(String motivoRechazoParteDExt) {
        this.motivoRechazoParteDExt = motivoRechazoParteDExt;
    }

    @Column(name = "OTRO_MOT_RECHAZO_PARTE_D", nullable = true)
    public String getOtroMotivoRechazoParteDExt() {
        return otroMotivoRechazoParteDExt;
    }

    public void setOtroMotivoRechazoParteDExt(String otroMotivoRechazoParteDExt) {
        this.otroMotivoRechazoParteDExt = otroMotivoRechazoParteDExt;
    }

    @Column(name = "MISMO_TUTOR", nullable = true, length = 2)
    public String getMismoTutor() {
        return mismoTutor;
    }

    public void setMismoTutor(String mismoTutor) {
        this.mismoTutor = mismoTutor;
    }

    @Column(name = "MOTIVO_DIF_TUTOR", nullable = true, length = 2)
    public String getMotivoDifTutor() {
        return motivoDifTutor;
    }

    public void setMotivoDifTutor(String motivoDifTutor) {
        this.motivoDifTutor = motivoDifTutor;
    }

    @Column(name = "OTRO_MOTIVO_DIF_TUTOR", nullable = true)
    public String getOtroMotivoDifTutor() {
        return otroMotivoDifTutor;
    }

    public void setOtroMotivoDifTutor(String otroMotivoDifTutor) {
        this.otroMotivoDifTutor = otroMotivoDifTutor;
    }

    @Column(name = "OTRA_RELACION_FAMILIAR", nullable = true, length = 100)
    public String getOtraRelacionFamTutor() {
        return otraRelacionFamTutor;
    }

    public void setOtraRelacionFamTutor(String otraRelacionFamTutor) {
        this.otraRelacionFamTutor = otraRelacionFamTutor;
    }

    @Column(name = "VERIFICA_TUTOR", nullable = true, length = 20)
    public String getVerifTutor() {
        return verifTutor;
    }

    public void setVerifTutor(String verifTutor) {
        this.verifTutor = verifTutor;
    }

    @Column(name = "RECONSENTIMIENTO", length = 2)
    public String getReconsentimiento() {
        return reconsentimiento;
    }

    public void setReconsentimiento(String reconsentimiento) {
        this.reconsentimiento = reconsentimiento;
    }

    @JsonIgnore
    @Column(name = "FECHA_RECIBIDO")
    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaEnvio) {
        this.fechaRecibido = fechaEnvio;
    }

    @Column(name = "NOMBRE1_MXSUPERFICIE", length = 100, nullable = true)
    public String getNombre1MxSuperficie() {
        return nombre1MxSuperficie;
    }

    public void setNombre1MxSuperficie(String nombre1MxSuperficie) {
        this.nombre1MxSuperficie = nombre1MxSuperficie;
    }

    @Column(name = "NOMBRE2_MXSUPERFICIE", length = 100, nullable = true)
    public String getNombre2MxSuperficie() {
        return nombre2MxSuperficie;
    }

    public void setNombre2MxSuperficie(String nombre2MxSuperficie) {
        this.nombre2MxSuperficie = nombre2MxSuperficie;
    }

    @Column(name = "APELLIDO1_MXSUPERFICIE", length = 100, nullable = true)
    public String getApellido1MxSuperficie() {
        return apellido1MxSuperficie;
    }

    public void setApellido1MxSuperficie(String apellido1MxSuperficie) {
        this.apellido1MxSuperficie = apellido1MxSuperficie;
    }

    @Column(name = "APELLIDO2_MXSUPERFICIE", length = 100, nullable = true)
    public String getApellido2MxSuperficie() {
        return apellido2MxSuperficie;
    }

    public void setApellido2MxSuperficie(String apellido2MxSuperficie) {
        this.apellido2MxSuperficie = apellido2MxSuperficie;
    }

    @Column(name = "CODIGO_CASA_CHF", length = 4, nullable = true)
    public String getCasaChf() {
        return casaChf;
    }

    public void setCasaChf(String casaChf) {
        this.casaChf = casaChf;
    }

    @Column(name = "ACEPTA_PARTE_E", length = 1)
    public String getAceptaParteE() {
        return aceptaParteE;
    }

    public void setAceptaParteE(String aceptaParteE) {
        this.aceptaParteE = aceptaParteE;
    }

    @Column(name = "MOTIVO_RECHAZO_PARTE_E", nullable = true, length = 2)
    public String getMotivoRechazoParteE() {
        return motivoRechazoParteE;
    }

    public void setMotivoRechazoParteE(String motivoRechazoParteE) {
        this.motivoRechazoParteE = motivoRechazoParteE;
    }

    @Column(name = "OTRO_MOT_RECHAZO_PARTE_E", nullable = true)
    public String getOtroMotivoRechazoParteE() {
        return otroMotivoRechazoParteE;
    }

    public void setOtroMotivoRechazoParteE(String otroMotivoRechazoParteE) {
        this.otroMotivoRechazoParteE = otroMotivoRechazoParteE;
    }

    @Column(name = "ACEPTA_PARTE_F", length = 1)
    public String getAceptaParteF() {
        return aceptaParteF;
    }

    public void setAceptaParteF(String aceptaParteF) {
        this.aceptaParteF = aceptaParteF;
    }

    @Column(name = "MOTIVO_RECHAZO_PARTE_F", nullable = true, length = 2)
    public String getMotivoRechazoParteF() {
        return motivoRechazoParteF;
    }

    public void setMotivoRechazoParteF(String motivoRechazoParteF) {
        this.motivoRechazoParteF = motivoRechazoParteF;
    }

    @Column(name = "OTRO_MOT_RECHAZO_PARTE_F", nullable = true)
    public String getOtroMotivoRechazoParteF() {
        return otroMotivoRechazoParteF;
    }

    public void setOtroMotivoRechazoParteF(String otroMotivoRechazoParteF) {
        this.otroMotivoRechazoParteF = otroMotivoRechazoParteF;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return this.codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaConsentimiento)) return false;

        CartaConsentimiento that = (CartaConsentimiento) o;

        return  (!codigo.equals(that.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
