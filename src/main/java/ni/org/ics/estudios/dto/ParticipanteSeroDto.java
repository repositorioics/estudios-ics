package ni.org.ics.estudios.dto;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 19/10/2020.
 */
public class ParticipanteSeroDto {

    private Integer codigo;
    private String nombreCompleto;
    private Integer casaPediatrica;
    private String casaFamilia;
    private String estudios;
    private String subEstudios;
    private Integer estado;
    private Date fechaNacimiento;
    private String edadParticipante;
    private ArrayList<String> SusEstudios;
    private String nombrepadre;
    private String nombremadre;
    private String nombretutor;


    public ParticipanteSeroDto() {
    }

    public ParticipanteSeroDto(Integer codigo, String nombreCompleto, Integer casaPediatrica, String casaFamilia, String estudios, String subEstudios, Integer estado, Date fechaNacimiento, String edadParticipante, ArrayList<String> susEstudios, String nombrepadre, String nombremadre,String nombretutor) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.casaPediatrica = casaPediatrica;
        this.casaFamilia = casaFamilia;
        this.estudios = estudios;
        this.subEstudios = subEstudios;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.edadParticipante = edadParticipante;
        SusEstudios = susEstudios;
        this.nombrepadre = nombrepadre;
        this.nombremadre = nombremadre;
        this.nombretutor = nombretutor;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCasaFamilia() {
        return casaFamilia;
    }

    public void setCasaFamilia(String casaFamilia) {
        this.casaFamilia = casaFamilia;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getSubEstudios() {
        return subEstudios;
    }

    public void setSubEstudios(String subEstudios) {
        this.subEstudios = subEstudios;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdadParticipante() {
        return edadParticipante;
    }

    public void setEdadParticipante(String edadParticipante) {
        this.edadParticipante = edadParticipante;
    }

    public Integer getCasaPediatrica() {
        return casaPediatrica;
    }

    public void setCasaPediatrica(Integer casaPediatrica) {
        this.casaPediatrica = casaPediatrica;
    }

    public ArrayList<String> getSusEstudios() {
        return SusEstudios;
    }

    public void setSusEstudios(ArrayList<String> susEstudios) {
        SusEstudios = susEstudios;
    }

    public String getNombrepadre() {
        return nombrepadre;
    }

    public void setNombrepadre(String nombrepadre) {
        this.nombrepadre = nombrepadre;
    }

    public String getNombremadre() {
        return nombremadre;
    }

    public void setNombremadre(String nombremadre) {
        this.nombremadre = nombremadre;
    }

    public String getNombretutor() {
        return nombretutor;
    }

    public void setNombretutor(String nombretutor) {
        this.nombretutor = nombretutor;
    }
}
