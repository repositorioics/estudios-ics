package ni.org.ics.estudios.dto.cartas;

import java.math.BigInteger;

/**
 * Created by miguel on 7/3/2022.
 */
public class InformacionRangoEdadDto {

    private String estudio;
    private String version;
    private String rango;
    private BigInteger total;

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

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }
}
