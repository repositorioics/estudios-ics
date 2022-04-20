package ni.org.ics.estudios.dto.muestras;

import java.util.Date;

/**
 * Created by miguel on 5/4/2022.
 */
public class MuestraDto {
    private Integer codigo;
    private Date fechaMuestra;
    private Integer pinchazos;
    private Integer recurso1;
    private Integer recurso2;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaMuestra() {
        return fechaMuestra;
    }

    public void setFechaMuestra(Date fechaMuestra) {
        this.fechaMuestra = fechaMuestra;
    }

    public Integer getPinchazos() {
        return pinchazos;
    }

    public void setPinchazos(Integer pinchazos) {
        this.pinchazos = pinchazos;
    }

    public Integer getRecurso1() {
        return recurso1;
    }

    public void setRecurso1(Integer recurso1) {
        this.recurso1 = recurso1;
    }

    public Integer getRecurso2() {
        return recurso2;
    }

    public void setRecurso2(Integer recurso2) {
        this.recurso2 = recurso2;
    }
}
