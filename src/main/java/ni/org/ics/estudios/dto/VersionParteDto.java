package ni.org.ics.estudios.dto;

import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.domain.catalogs.Parte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 01/07/2021.
 */
public class VersionParteDto implements Serializable {

    private Integer idversion;
    private String estudio;
    private String version;
    private ArrayList<String> SusPartes;
    private String partes;
    private Date fecha_version;
    private boolean activo;

    public VersionParteDto(){}

    public VersionParteDto(Integer idversion, String estudio, String version, ArrayList<String> susPartes, Date fecha_version, boolean activo) {
        this.idversion = idversion;
        this.estudio = estudio;
        this.version = version;
        SusPartes = susPartes;
        this.fecha_version = fecha_version;
        this.activo = activo;
    }

    public Integer getIdversion() {
        return idversion;
    }

    public void setIdversion(Integer idversion) {
        this.idversion = idversion;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<String> getSusPartes() {
        return SusPartes;
    }

    public void setSusPartes(ArrayList<String> susPartes) {
        SusPartes = susPartes;
    }

    public Date getFecha_version() {
        return fecha_version;
    }

    public void setFecha_version(Date fecha_version) {
        this.fecha_version = fecha_version;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getPartes() {
        return partes;
    }

    public void setPartes(String partes) {
        this.partes = partes;
    }
}