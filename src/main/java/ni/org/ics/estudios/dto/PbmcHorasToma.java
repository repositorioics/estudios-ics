package ni.org.ics.estudios.dto;

import java.util.Date;

/**
 * Created by miguel on 22/3/2022.
 */
public class PbmcHorasToma {
    private Integer codigo;
    private Date fechaToma;
    private String horaToma;
    private String horaPbmc;
    private Integer tuboRojo;
    private Integer tuboPbmc;


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(Date fechaToma) {
        this.fechaToma = fechaToma;
    }

    public String getHoraPbmc() {
        return horaPbmc;
    }

    public void setHoraPbmc(String horaPbmc) {
        this.horaPbmc = horaPbmc;
    }

    public Integer getTuboRojo() {
        return tuboRojo;
    }

    public void setTuboRojo(Integer tuboRojo) {
        this.tuboRojo = tuboRojo;
    }

    public Integer getTuboPbmc() {
        return tuboPbmc;
    }

    public void setTuboPbmc(Integer tuboPbmc) {
        this.tuboPbmc = tuboPbmc;
    }
}
