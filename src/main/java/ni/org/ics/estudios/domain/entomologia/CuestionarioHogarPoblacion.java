package ni.org.ics.estudios.domain.entomologia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by miguel on 15/8/2022.
 */
@Entity
@Table(name = "ento_cuestionario_hogar_pob", catalog = "estudios_ics")
public class CuestionarioHogarPoblacion extends BaseMetaData implements Auditable {

    private String codigoPoblacion;
    private String codigoEncuesta;
    private String codificado;
    private String edad;
    private String sexo;

    @Id
    @Column(name = "codigo_poblacion", nullable = false)
    public String getCodigoPoblacion() {
        return codigoPoblacion;
    }

    public void setCodigoPoblacion(String codigoPoblacion) {
        this.codigoPoblacion = codigoPoblacion;
    }

    @Column(name = "codigo_cuestionario", nullable = false)
    public String getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(String codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    @Column(name = "codificado", nullable = false)
    public String getCodificado() {
        return codificado;
    }

    public void setCodificado(String codificado) {
        this.codificado = codificado;
    }

    @Column(name = "edad", nullable = false)
    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Column(name = "sexo", nullable = false)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "CuestionarioHogarPoblacion{" +
                "codigoPoblacion='" + codigoPoblacion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuestionarioHogarPoblacion)) return false;

        CuestionarioHogarPoblacion that = (CuestionarioHogarPoblacion) o;

        if (!codigoPoblacion.equals(that.codigoPoblacion)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoPoblacion.hashCode();
    }
}
