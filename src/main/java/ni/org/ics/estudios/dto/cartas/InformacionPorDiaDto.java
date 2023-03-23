package ni.org.ics.estudios.dto.cartas;

import java.math.BigInteger;
import java.util.Date;
/**
 * Created by miguel on 7/3/2022.
 */
public class InformacionPorDiaDto {

    private Date dia;
    private BigInteger total;

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }
}
