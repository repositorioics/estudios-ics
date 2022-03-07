package ni.org.ics.estudios.domain.catalogs;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ICS on 12/02/2022.
 */
@Entity
@Table(name = "rango_edad_volumen", catalog = "estudios_ics")
public class Rango_Edad_Volumen implements Serializable {

    private Integer rango_id;
    private int edad_meses_minima;
    private int getEdad_meses_maxima;
    private String tipo_muestra;
    private int volumen;
    private String estudio;
    private int volumen_adicional;

    @Id
    @Column(name = "RANGO_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getRango_id() {
        return rango_id;
    }

    public void setRango_id(Integer rango_id) {
        this.rango_id = rango_id;
    }


    @Column(name = "EDAD_MESES_MINIMA", nullable = false)
    public int getEdad_meses_minima() {
        return edad_meses_minima;
    }

    public void setEdad_meses_minima(int edad_meses_minima) {
        this.edad_meses_minima = edad_meses_minima;
    }

    @Column(name = "EDAD_MESES_MAXIMA", nullable = false)
    public int getGetEdad_meses_maxima() {
        return getEdad_meses_maxima;
    }

    public void setGetEdad_meses_maxima(int getEdad_meses_maxima) {
        this.getEdad_meses_maxima = getEdad_meses_maxima;
    }
    @Column(name = "TIPO_MUESTRA", nullable = false)
    public String getTipo_muestra() {
        return tipo_muestra;
    }

    public void setTipo_muestra(String tipo_muestra) {
        this.tipo_muestra = tipo_muestra;
    }

    @Column(name = "VOLUMEN", nullable = false)
    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    @Column(name = "ESTUDIO", nullable = true, length = 150)
    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    @Column(name = "VOLUMEN_ADICIONAL", nullable = true)
    public int getVolumen_adicional() {
        return volumen_adicional;
    }

    public void setVolumen_adicional(int volumen_adicional) {
        this.volumen_adicional = volumen_adicional;
    }
}
