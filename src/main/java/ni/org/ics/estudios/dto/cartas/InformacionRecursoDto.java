package ni.org.ics.estudios.dto.cartas;

import java.math.BigInteger;

/**
 * Created by miguel on 7/3/2022.
 */
public class InformacionRecursoDto {
    private String nombre;
    private BigInteger cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }
}
