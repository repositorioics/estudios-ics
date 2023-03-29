package ni.org.ics.estudios.dto;

import java.io.Serializable;

/**
 * Created by ICS on 28/04/2021.
 */
public class ParticipantesEnCasa implements Serializable {

    private Integer codCasaPediatrica;
    private String codCasaFamilia;
    private Integer idParticipante;
    private String nombreParticipante;
    private String anios;
    private String meses;
    private String dias;
    private String estado;
    private String estudio;

    public ParticipantesEnCasa(){}

    public ParticipantesEnCasa(Integer codCasaPediatrica, String codCasaFamilia, Integer idParticipante, String nombreParticipante, String anios, String meses, String dias, String estado, String estudio) {
        this.codCasaPediatrica = codCasaPediatrica;
        this.codCasaFamilia = codCasaFamilia;
        this.idParticipante = idParticipante;
        this.nombreParticipante = nombreParticipante;
        this.anios = anios;
        this.meses = meses;
        this.dias = dias;
        this.estado = estado;
        this.estudio = estudio;
    }

    public Integer getCodCasaPediatrica() {
        return codCasaPediatrica;
    }

    public void setCodCasaPediatrica(Integer codCasaPediatrica) {
        this.codCasaPediatrica = codCasaPediatrica;
    }

    public String getCodCasaFamilia() {
        return codCasaFamilia;
    }

    public void setCodCasaFamilia(String codCasaFamilia) {
        this.codCasaFamilia = codCasaFamilia;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }

    public String getAnios() {
        return anios;
    }

    public void setAnios(String anios) {
        this.anios = anios;
    }

    public String getMeses() {
        return meses;
    }

    public void setMeses(String meses) {
        this.meses = meses;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstudio() {
        return estudio;
    }
    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
}



