package ni.org.ics.estudios.dto.cartas;

import java.math.BigInteger;

/**
 * Created by miguel on 7/3/2022.
 */
public class InformacionPorEstudioDto {

    private String estudio;
    private BigInteger total;

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }
}
