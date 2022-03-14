package ni.org.ics.estudios.dto.cartas;

import java.math.BigInteger;

/**
 * Created by miguel on 7/3/2022.
 */
public class InformacionPorBarrioDto {

    private String barrio;
    private String estudio;
    private String version;
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

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }
}
