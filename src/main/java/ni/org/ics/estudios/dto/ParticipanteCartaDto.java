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

public class ParticipanteCartaDto implements Serializable {

    private Integer codigo;
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
    private  Integer tipoasentimiento;

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
}
