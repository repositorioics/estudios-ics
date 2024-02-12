package ni.org.ics.estudios.domain.muestreoanual;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ing. Santiago Carballo on 09/02/2024.
 */

@Entity
@Table(name = "recepcionpbmc", catalog = "estudios_ics")
public class RecepcionPBMC {
    private Integer id;
    private Integer codigo;
    private String fechaPbmc;
    private Double volPbmc;
    private Boolean rojoAdicional;
    private Double volRojoAdicional;
    private String lugar;
    private String observacion;
    private String username;
    private String estudio;
    private String estado;
    private String fechaCreacion;
    private Date fechaRegistro;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="codigo", nullable = false)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "fecha_pbmc", length = 15)
    public String getFechaPbmc() {
        return fechaPbmc;
    }

    public void setFechaPbmc(String fechaPbmc) {
        this.fechaPbmc = fechaPbmc;
    }

    @Column(name="vol_pbmc", nullable = false)
    public Double getVolPbmc() {
        return volPbmc;
    }

    public void setVolPbmc(Double volPbmc) {
        this.volPbmc = volPbmc;
    }

    @Column(name="rojo_adicional")
    public Boolean getRojoAdicional() {
        return rojoAdicional;
    }

    public void setRojoAdicional(Boolean rojoAdicional) {
        this.rojoAdicional = rojoAdicional;
    }

    @Column(name="vol_rojo_adicional")
    public Double getVolRojoAdicional() {
        return volRojoAdicional;
    }

    public void setVolRojoAdicional(Double volRojoAdiciona) {
        this.volRojoAdicional = volRojoAdiciona;
    }

    @Column(name = "lugar", length = 255)
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Column(name = "observacion", length = 255)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Column(name = "username", length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "estudio", length = 255)
    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    @Column(name = "estado", length = 255)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "fecha_creacion", length = 255)
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name="fecha_registro")
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
