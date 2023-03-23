package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ICS on 01/07/2021.
 */
public class VersionExtensionDto implements Serializable {

    private Integer idversion;
    private Date fechacreada;
    private String version;
    private String estudio;
    private ArrayList<String> SusExtens;
    private String nombreextension;
    private boolean isActive;

    public VersionExtensionDto() {}

    public VersionExtensionDto(Integer idversion, Date fechacreada, String version, String estudio, ArrayList<String> susExtens, String nombreextension, boolean isActive) {
        this.idversion = idversion;
        this.fechacreada = fechacreada;
        this.version = version;
        this.estudio = estudio;
        SusExtens = susExtens;
        this.nombreextension = nombreextension;
        this.isActive = isActive;
    }

    public Integer getIdversion() {
        return idversion;
    }

    public void setIdversion(Integer idversion) {
        this.idversion = idversion;
    }

    public Date getFechacreada() {
        return fechacreada;
    }

    public void setFechacreada(Date fechacreada) {
        this.fechacreada = fechacreada;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<String> getSusExtens() {
        return SusExtens;
    }

    public void setSusExtens(ArrayList<String> susExtens) {
        SusExtens = susExtens;
    }

    public String getNombreextension() {
        return nombreextension;
    }

    public void setNombreextension(String nombreextension) {
        this.nombreextension = nombreextension;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
}
