package ni.org.ics.estudios.web.utils.pdf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel Salinas on 19/10/2018.
 * V1.0
 */
public class DatosGeneralesParticipante implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private String fechaIngreso;
    private String jefeFamilia;
    private String tutor;
    private String padre;
    private String madre;
    private String relFamTutor;
    private String tipoVivienda;
    private String barrio;
    private String manzana;
    private String direccion;
    private String telefonos;
    private String nombreContacto;
    private String barrioContacto;
    private String telefonosContacto;
    private String direccionContacto;
    private String nombreCompleto;
    private String edad;
    private String fechaNacimiento;
    private String sexo;
    private String estudiante;
    private String turno;
    private String escuela;
    private String tiempoResidencia;
    private String permanecerTresAnios;
    private String dondeAsiste;
    private String unidadAsiste;
    private String asistirCSSF;
    private String enfermedadCronica;
    private String cualEnfermedadCronica;
    private String tomaTratamiento;
    private String cualTratamiento;
    private String tieneDxDengue;
    private String fechaDxDengue;
    private String hospitalizadoDengue;
    private String fechaHospitalizadoDengue;
    private String digitador;

    public DatosGeneralesParticipante(){
        this.codigo = "";
        this.fechaIngreso = "";
        this.jefeFamilia = "";
        this.tutor = "";
        this.padre = "";
        this.madre = "";
        this.relFamTutor = "";
        this.tipoVivienda = "";
        this.barrio = "";
        this.manzana = "";
        this.direccion = "";
        this.telefonos = "";
        this.nombreContacto = "";
        this.barrioContacto = "";
        this.telefonosContacto = "";
        this.direccionContacto = "";
        this.nombreCompleto = "";
        this.edad = "";
        this.fechaNacimiento = "";
        this.sexo = "";
        this.estudiante = "";
        this.turno = "";
        this.escuela = "";
        this.tiempoResidencia = "";
        this.permanecerTresAnios = "";
        this.dondeAsiste = "";
        this.unidadAsiste = "";
        this.asistirCSSF = "";
        this.enfermedadCronica = "";
        this.cualEnfermedadCronica = "";
        this.tomaTratamiento = "";
        this.cualTratamiento = "";
        this.tieneDxDengue = "";
        this.fechaDxDengue = "";
        this.hospitalizadoDengue = "";
        this.fechaHospitalizadoDengue = "";
        this.digitador = "";
    }
    private List<String> mensajes = new ArrayList<String>();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getJefeFamilia() {
        return jefeFamilia;
    }

    public void setJefeFamilia(String jefeFamilia) {
        this.jefeFamilia = jefeFamilia;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getMadre() {
        return madre;
    }

    public void setMadre(String madre) {
        this.madre = madre;
    }

    public String getRelFamTutor() {
        return relFamTutor;
    }

    public void setRelFamTutor(String relFamTutor) {
        this.relFamTutor = relFamTutor;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getBarrioContacto() {
        return barrioContacto;
    }

    public void setBarrioContacto(String barrioContacto) {
        this.barrioContacto = barrioContacto;
    }

    public String getTelefonosContacto() {
        return telefonosContacto;
    }

    public void setTelefonosContacto(String telefonosContacto) {
        this.telefonosContacto = telefonosContacto;
    }

    public String getDireccionContacto() {
        return direccionContacto;
    }

    public void setDireccionContacto(String direccionContacto) {
        this.direccionContacto = direccionContacto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getTiempoResidencia() {
        return tiempoResidencia;
    }

    public void setTiempoResidencia(String tiempoResidencia) {
        this.tiempoResidencia = tiempoResidencia;
    }

    public String getPermanecerTresAnios() {
        return permanecerTresAnios;
    }

    public void setPermanecerTresAnios(String permanecerTresAnios) {
        this.permanecerTresAnios = permanecerTresAnios;
    }

    public String getDondeAsiste() {
        return dondeAsiste;
    }

    public void setDondeAsiste(String dondeAsiste) {
        this.dondeAsiste = dondeAsiste;
    }

    public String getUnidadAsiste() {
        return unidadAsiste;
    }

    public void setUnidadAsiste(String unidadAsiste) {
        this.unidadAsiste = unidadAsiste;
    }

    public String getAsistirCSSF() {
        return asistirCSSF;
    }

    public void setAsistirCSSF(String asistirCSSF) {
        this.asistirCSSF = asistirCSSF;
    }

    public String getEnfermedadCronica() {
        return enfermedadCronica;
    }

    public void setEnfermedadCronica(String enfermedadCronica) {
        this.enfermedadCronica = enfermedadCronica;
    }

    public String getCualEnfermedadCronica() {
        return cualEnfermedadCronica;
    }

    public void setCualEnfermedadCronica(String cualEnfermedadCronica) {
        this.cualEnfermedadCronica = cualEnfermedadCronica;
    }

    public String getTomaTratamiento() {
        return tomaTratamiento;
    }

    public void setTomaTratamiento(String tomaTratamiento) {
        this.tomaTratamiento = tomaTratamiento;
    }

    public String getCualTratamiento() {
        return cualTratamiento;
    }

    public void setCualTratamiento(String cualTratamiento) {
        this.cualTratamiento = cualTratamiento;
    }

    public String getTieneDxDengue() {
        return tieneDxDengue;
    }

    public void setTieneDxDengue(String tieneDxDengue) {
        this.tieneDxDengue = tieneDxDengue;
    }

    public String getFechaDxDengue() {
        return fechaDxDengue;
    }

    public void setFechaDxDengue(String fechaDxDengue) {
        this.fechaDxDengue = fechaDxDengue;
    }

    public String getHospitalizadoDengue() {
        return hospitalizadoDengue;
    }

    public void setHospitalizadoDengue(String hospitalizadoDengue) {
        this.hospitalizadoDengue = hospitalizadoDengue;
    }

    public String getFechaHospitalizadoDengue() {
        return fechaHospitalizadoDengue;
    }

    public void setFechaHospitalizadoDengue(String fechaHospitalizadoDengue) {
        this.fechaHospitalizadoDengue = fechaHospitalizadoDengue;
    }

    public String getDigitador() {
        return digitador;
    }

    public void setDigitador(String digitador) {
        this.digitador = digitador;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }
}
