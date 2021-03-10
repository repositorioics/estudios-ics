package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 08/03/2021.
 */
public class CambioDomParticipanteDto implements Serializable {

    private Integer[] list;
    private String barrio;
    private String casaFam;
    private String casaP;
    private String dir;
    private String fecha_reportado;
    private String manzana;
    private String observacion;
    private String razonnogeoref;
    private String recurso1;
    private String telefono;
    private String estudio;
    private String otroBarrio;


    public CambioDomParticipanteDto() {
    }

    public Integer[] getList() {
        return list;
    }

    public void setList(Integer[] list) {
        this.list = list;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCasaFam() {
        return casaFam;
    }

    public void setCasaFam(String casaFam) {
        this.casaFam = casaFam;
    }

    public String getCasaP() {
        return casaP;
    }

    public void setCasaP(String casaP) {
        this.casaP = casaP;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFecha_reportado() {
        return fecha_reportado;
    }

    public void setFecha_reportado(String fecha_reportado) {
        this.fecha_reportado = fecha_reportado;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getRazonnogeoref() {
        return razonnogeoref;
    }

    public void setRazonnogeoref(String razonnogeoref) {
        this.razonnogeoref = razonnogeoref;
    }

    public String getRecurso1() {
        return recurso1;
    }

    public void setRecurso1(String recurso1) {
        this.recurso1 = recurso1;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getOtroBarrio() {
        return otroBarrio;
    }

    public void setOtroBarrio(String otroBarrio) {
        this.otroBarrio = otroBarrio;
    }
}
