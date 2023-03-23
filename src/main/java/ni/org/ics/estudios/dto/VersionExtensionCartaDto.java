package ni.org.ics.estudios.dto;

import java.io.Serializable;

/**
 * Created by ICS on 19/05/2021.
 */
public class VersionExtensionCartaDto  implements Serializable {

    private Integer idParticipanteCarta;
    private Integer codigoParticipante;
    private String nombreCompleto;
    private Integer idEstudio;
    private String nmobreEstudio;
    private Integer idVersion;
    private String nombreVersion;
    private String fechaCarta;
    private String estado;
    private boolean tieneExtesion;
    private Integer cantidadExtension;
    private boolean anulada;
    private String pqAnulada;


    public VersionExtensionCartaDto() {
    }

    public VersionExtensionCartaDto(Integer idParticipanteCarta,Integer codigoParticipante, String nombreCompleto, Integer idEstudio, String nmobreEstudio, Integer idVersion, String nombreVersion, String fechaCarta, String estado, boolean tieneExtesion, Integer cantidadExtension, boolean anulada, String pqAnulada) {
        this.idParticipanteCarta = idParticipanteCarta;
        this.codigoParticipante = codigoParticipante;
        this.nombreCompleto = nombreCompleto;
        this.idEstudio = idEstudio;
        this.nmobreEstudio = nmobreEstudio;
        this.idVersion = idVersion;
        this.nombreVersion = nombreVersion;
        this.fechaCarta = fechaCarta;
        this.estado = estado;
        this.tieneExtesion = tieneExtesion;
        this.cantidadExtension = cantidadExtension;
        this.anulada = anulada;
        this.pqAnulada = pqAnulada;
    }


    public Integer getIdParticipanteCarta() {
        return idParticipanteCarta;
    }

    public void setIdParticipanteCarta(Integer idParticipanteCarta) {
        this.idParticipanteCarta = idParticipanteCarta;
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

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getNmobreEstudio() {
        return nmobreEstudio;
    }

    public void setNmobreEstudio(String nmobreEstudio) {
        this.nmobreEstudio = nmobreEstudio;
    }

    public Integer getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(Integer idVersion) {
        this.idVersion = idVersion;
    }

    public String getNombreVersion() {
        return nombreVersion;
    }

    public void setNombreVersion(String nombreVersion) {
        this.nombreVersion = nombreVersion;
    }

    public String getFechaCarta() {
        return fechaCarta;
    }

    public void setFechaCarta(String fechaCarta) {
        this.fechaCarta = fechaCarta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isTieneExtesion() {
        return tieneExtesion;
    }

    public void setTieneExtesion(boolean tieneExtesion) {
        this.tieneExtesion = tieneExtesion;
    }

    public Integer getCantidadExtension() {
        return cantidadExtension;
    }

    public void setCantidadExtension(Integer cantidadExtension) {
        this.cantidadExtension = cantidadExtension;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getPqAnulada() {
        return pqAnulada;
    }

    public void setPqAnulada(String pqAnulada) {
        this.pqAnulada = pqAnulada;
    }
}
