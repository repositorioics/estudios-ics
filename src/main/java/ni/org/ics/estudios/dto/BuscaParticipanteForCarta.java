package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 14/07/2021.
 */
public class BuscaParticipanteForCarta implements Serializable {

    private Integer id;
    private Integer codigoParticipante;
    private String nombreCompleto;
    private int  edadDia;
    private int edadMes;
    private int edadAnios;
    private String estudios;
    private List<EstudioDto>listEstudios;
    private boolean menorEdad;
    private String nombreTutor;
    private String nombreMadre;
    private  String nombrePadre;
    private String realFam;
    private Date fechaNac;
    private String direccion;
    private String estado;
    private boolean anulada;

    private String name1Tutor;
    private String name2Tutor;
    private String surname1Tutor;
    private String surname2Tutor;


    public BuscaParticipanteForCarta() {
    }

    public BuscaParticipanteForCarta(Integer id, Integer codigoParticipante, String nombreCompleto, int edadDia, int edadMes, int edadAnios, String estudios, List<EstudioDto> listEstudios, boolean menorEdad, String nombreTutor, String nombreMadre, String nombrePadre, String realFam, Date fechaNac, String direccion, String estado, boolean anulada, String name1Tutor, String name2Tutor, String surname1Tutor, String surname2Tutor) {
        this.id = id;
        this.codigoParticipante = codigoParticipante;
        this.nombreCompleto = nombreCompleto;
        this.edadDia = edadDia;
        this.edadMes = edadMes;
        this.edadAnios = edadAnios;
        this.estudios = estudios;
        this.listEstudios = listEstudios;
        this.menorEdad = menorEdad;
        this.nombreTutor = nombreTutor;
        this.nombreMadre = nombreMadre;
        this.nombrePadre = nombrePadre;
        this.realFam = realFam;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.estado = estado;
        this.anulada = anulada;
        this.name1Tutor = name1Tutor;
        this.name2Tutor = name2Tutor;
        this.surname1Tutor = surname1Tutor;
        this.surname2Tutor = surname2Tutor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(Integer codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdadDia() {
        return edadDia;
    }

    public void setEdadDia(int edadDia) {
        this.edadDia = edadDia;
    }

    public int getEdadMes() {
        return edadMes;
    }

    public void setEdadMes(int edadMes) {
        this.edadMes = edadMes;
    }

    public int getEdadAnios() {
        return edadAnios;
    }

    public void setEdadAnios(int edadAnios) {
        this.edadAnios = edadAnios;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public List<EstudioDto> getListEstudios() {
        return listEstudios;
    }

    public void setListEstudios(List<EstudioDto> listEstudios) {
        this.listEstudios = listEstudios;
    }

    public boolean isMenorEdad() {
        return menorEdad;
    }

    public void setMenorEdad(boolean menorEdad) {
        this.menorEdad = menorEdad;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getRealFam() {
        return realFam;
    }

    public void setRealFam(String realFam) {
        this.realFam = realFam;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getName1Tutor() {
        return name1Tutor;
    }

    public void setName1Tutor(String name1Tutor) {
        this.name1Tutor = name1Tutor;
    }

    public String getName2Tutor() {
        return name2Tutor;
    }

    public void setName2Tutor(String name2Tutor) {
        this.name2Tutor = name2Tutor;
    }

    public String getSurname1Tutor() {
        return surname1Tutor;
    }

    public void setSurname1Tutor(String surname1Tutor) {
        this.surname1Tutor = surname1Tutor;
    }

    public String getSurname2Tutor() {
        return surname2Tutor;
    }

    public void setSurname2Tutor(String surname2Tutor) {
        this.surname2Tutor = surname2Tutor;
    }
}
