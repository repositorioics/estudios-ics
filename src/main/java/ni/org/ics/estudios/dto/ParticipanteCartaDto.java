package ni.org.ics.estudios.dto;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Parte;
import ni.org.ics.estudios.domain.catalogs.Version;
import ni.org.ics.estudios.domain.scancarta.DetalleParte;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 23/01/2020.
 */

public class ParticipanteCartaDto  {

    private Integer codigo;
    private Integer estudio;
    private Integer version;
    private String  asentimiento;
    private Integer relfam;
    private String  nombfirma;
    private String  nombre2Firma;
    private String  apellido1Firma;
    private String  apellido2Firma;
    private String  contactoFuturo;
    private String  retiro;
    private String  proyecto;
    private Integer person;
    private String  fechacarta;
    private String  observacion;
    private List<ParteDto> parte;
    private Integer idparticipante;
    private String recurso;
    private Integer tipoasentimiento;
    private String testigopresente;
    private String nombre1testigo;
    private String nombre2testigo;
    private String apellido1testigo;
    private String apellido2testigo;
    private Integer edadyears;
    private Integer edadmeses;
    private Integer edaddias;
    private String fecha_registro;
    private boolean tineneExtension;
    private String nombreUsuario;
    private String accion;
    private String estudios_actuales;

    public ParticipanteCartaDto() {}

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAsentimiento() {
        return asentimiento;
    }

    public void setAsentimiento(String asentimiento) {
        this.asentimiento = asentimiento;
    }

    public Integer getRelfam() {
        return relfam;
    }

    public void setRelfam(Integer relfam) {
        this.relfam = relfam;
    }

    public String getNombfirma() {
        return nombfirma;
    }

    public void setNombfirma(String nombfirma) {
        this.nombfirma = nombfirma;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public List<ParteDto> getParte() {
        return parte;
    }

    public void setParte(List<ParteDto> parte) {
        this.parte = parte;
    }

    public String getFechacarta() {
        return fechacarta;
    }

    public void setFechacarta(String fechacarta) {
        this.fechacarta = fechacarta;
    }

    public String getNombre2Firma() {
        return nombre2Firma;
    }

    public void setNombre2Firma(String nombre2Firma) {
        this.nombre2Firma = nombre2Firma;
    }

    public String getApellido1Firma() {
        return apellido1Firma;
    }

    public void setApellido1Firma(String apellido1Firma) {
        this.apellido1Firma = apellido1Firma;
    }

    public String getApellido2Firma() {
        return apellido2Firma;
    }

    public void setApellido2Firma(String apellido2Firma) {
        this.apellido2Firma = apellido2Firma;
    }

    public String getContactoFuturo() {
        return contactoFuturo;
    }

    public void setContactoFuturo(String contactoFuturo) {
        this.contactoFuturo = contactoFuturo;
    }

    public String getRetiro() {
        return retiro;
    }

    public void setRetiro(String retiro) {
        this.retiro = retiro;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(Integer idparticipante) {
        this.idparticipante = idparticipante;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public Integer getTipoasentimiento() {
        return tipoasentimiento;
    }

    public void setTipoasentimiento(Integer tipoasentimiento) {
        this.tipoasentimiento = tipoasentimiento;
    }

    public String getTestigopresente() {
        return testigopresente;
    }

    public void setTestigopresente(String testigopresente) {
        this.testigopresente = testigopresente;
    }

    public String getNombre1testigo() {
        return nombre1testigo;
    }

    public void setNombre1testigo(String nombre1testigo) {
        this.nombre1testigo = nombre1testigo;
    }

    public String getNombre2testigo() {
        return nombre2testigo;
    }

    public void setNombre2testigo(String nombre2testigo) {
        this.nombre2testigo = nombre2testigo;
    }

    public String getApellido1testigo() {
        return apellido1testigo;
    }

    public void setApellido1testigo(String apellido1testigo) {
        this.apellido1testigo = apellido1testigo;
    }

    public String getApellido2testigo() {
        return apellido2testigo;
    }

    public void setApellido2testigo(String apellido2testigo) {
        this.apellido2testigo = apellido2testigo;
    }

    public Integer getEdadyears() {
        return edadyears;
    }

    public void setEdadyears(Integer edadyears) {
        this.edadyears = edadyears;
    }

    public Integer getEdadmeses() {
        return edadmeses;
    }

    public void setEdadmeses(Integer edadmeses) {
        this.edadmeses = edadmeses;
    }

    public Integer getEdaddias() {
        return edaddias;
    }

    public void setEdaddias(Integer edaddias) {
        this.edaddias = edaddias;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }
    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Integer getEstudio() {
        return estudio;
    }

    public void setEstudio(Integer estudio) {
        this.estudio = estudio;
    }

    public boolean isTineneExtension() {
        return tineneExtension;
    }

    public void setTineneExtension(boolean tineneExtension) {
        this.tineneExtension = tineneExtension;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getEstudios_actuales() {
        return estudios_actuales;
    }

    public void setEstudios_actuales(String estudios_actuales) {
        this.estudios_actuales = estudios_actuales;
    }
}
