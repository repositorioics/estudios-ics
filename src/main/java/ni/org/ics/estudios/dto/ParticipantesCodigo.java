package ni.org.ics.estudios.dto;

import java.io.Serializable;

/**
 * Created by ICS_Inspiron3 on 16/07/2019.
 */

public class ParticipantesCodigo implements Serializable {

    private String codigo;
    private String casaP;
    private String casaFamilia;
    private String estudio;

    public ParticipantesCodigo(){
        this.codigo="";
        this.casaP="";
        this.casaFamilia="";
        this.estudio="";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCasaP() {
        return casaP;
    }

    public void setCasaP(String casaP) {
        this.casaP = casaP;
    }

    public String getCasaFamilia() {
        return casaFamilia;
    }

    public void setCasaFamilia(String casaFamilia) {
        this.casaFamilia = casaFamilia;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

}
