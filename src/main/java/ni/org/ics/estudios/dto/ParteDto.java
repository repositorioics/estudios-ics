package ni.org.ics.estudios.dto;

import java.io.Serializable;

/**
 * Created by ICS on 23/01/2020.
 */
public class ParteDto {
    private Integer idparticipantecarta;
    private Integer iddetalle;
    private Integer idparte;
    private String nombreparte;
    private boolean acepta;
    private boolean locked;

    public ParteDto() {
    }

    public ParteDto(Integer idparte, boolean acepta) {
        this.idparte = idparte;
        this.acepta = acepta;
    }

    public Integer getIdparte() {
        return idparte;
    }

    public void setIdparte(Integer idparte) {
        this.idparte = idparte;
    }

    public boolean isAcepta() {
        return acepta;
    }

    public void setAcepta(boolean acepta) {
        this.acepta = acepta;
    }

    public Integer getIdparticipantecarta() {
        return idparticipantecarta;
    }

    public void setIdparticipantecarta(Integer idparticipantecarta) {
        this.idparticipantecarta = idparticipantecarta;
    }

    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public String getNombreparte() {
        return nombreparte;
    }

    public void setNombreparte(String nombreparte) {
        this.nombreparte = nombreparte;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}