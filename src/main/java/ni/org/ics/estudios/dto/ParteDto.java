package ni.org.ics.estudios.dto;

import java.io.Serializable;

/**
 * Created by ICS on 23/01/2020.
 */
public class ParteDto implements Serializable {
    private Integer idparticipantecarta;
    private Integer idparte;
    private boolean acepta;

    public ParteDto() {
    }

    public ParteDto(Integer idparticipantecarta, Integer idparte, boolean acepta) {
        this.idparticipantecarta = idparticipantecarta;
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
}
