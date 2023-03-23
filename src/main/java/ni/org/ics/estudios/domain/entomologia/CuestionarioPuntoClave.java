package ni.org.ics.estudios.domain.entomologia;

import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.muestreoanual.MovilInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * Created by miguel on 1/9/2022.
 */
@Entity
@Table(name = "ento_cuestionario_punto_clave", catalog = "estudios_ics")
public class CuestionarioPuntoClave  implements Auditable {

    private String codigoCuestionario;
    private Date fechaCuestionario;
    private Integer barrio;
    private String nombrePuntoClave;
    private String direccionPuntoClave;
    private String tipoPuntoClave;
    private String tipoPuntoProductividad;
    private String tipoPuntoProductividadOtro;
    private String tipoPuntoAglomeracion;
    private String tipoPuntoAglomeracionOtro;
    private Integer cuantasPersonasReunen;
    private Integer cuantosDiasSemanaReunen;
    private String horaInicioReunion;
    private String horaFinReunion;
    private String puntoGps;
    private Double latitud;
    private Double longitud;
    private String tipoIngresoCodigoSitio;
    private String codigoSitio;

    private String hayAmbientePERI;
    private String horaCapturaPERI;
    private Double humedadRelativaPERI;
    private Double temperaturaPERI;
    private String tipoIngresoCodigoPERI;
    private String codigoPERI;

    private String hayAmbienteINTRA;
    private String horaCapturaINTRA;
    private Double humedadRelativaINTRA;
    private Double temperaturaINTRA;
    private String tipoIngresoCodigoINTRA;
    private String codigoINTRA;

    private String nombrePersonaContesta;
    //odk
    private MovilInfo movilInfo;

    @Id
    @Column(name = "codigo_cuestionario", nullable = false, length = 36)
    public String getCodigoCuestionario() {
        return codigoCuestionario;
    }

    public void setCodigoCuestionario(String codigoCuestionario) {
        this.codigoCuestionario = codigoCuestionario;
    }

    @Column(name = "fecha_cuestionario", nullable = false)
    public Date getFechaCuestionario() {
        return fechaCuestionario;
    }

    public void setFechaCuestionario(Date fechaCuestionario) {
        this.fechaCuestionario = fechaCuestionario;
    }

    @Column(name = "codigo_barrio", nullable = false)
    public Integer getBarrio() {
        return barrio;
    }

    public void setBarrio(Integer barrio) {
        this.barrio = barrio;
    }

    @Column(name = "nombre_punto_clave", nullable = false, length = 250)
    public String getNombrePuntoClave() {
        return nombrePuntoClave;
    }

    public void setNombrePuntoClave(String nombrePuntoClave) {
        this.nombrePuntoClave = nombrePuntoClave;
    }

    @Column(name = "direccion_punto_clave", nullable = false, length = 500)
    public String getDireccionPuntoClave() {
        return direccionPuntoClave;
    }

    public void setDireccionPuntoClave(String direccionPuntoClave) {
        this.direccionPuntoClave = direccionPuntoClave;
    }

    @Column(name = "tipo_punto_clave", nullable = false, length = 2)
    public String getTipoPuntoClave() {
        return tipoPuntoClave;
    }

    public void setTipoPuntoClave(String tipoPuntoClave) {
        this.tipoPuntoClave = tipoPuntoClave;
    }

    @Column(name = "tipo_punto_clave_prod", nullable = true, length = 2)
    public String getTipoPuntoProductividad() {
        return tipoPuntoProductividad;
    }

    public void setTipoPuntoProductividad(String tipoPuntoProductividad) {
        this.tipoPuntoProductividad = tipoPuntoProductividad;
    }

    @Column(name = "tipo_punto_clave_prod_otro", nullable = true, length = 255)
    public String getTipoPuntoProductividadOtro() {
        return tipoPuntoProductividadOtro;
    }

    public void setTipoPuntoProductividadOtro(String tipoPuntoProductividadOtro) {
        this.tipoPuntoProductividadOtro = tipoPuntoProductividadOtro;
    }

    @Column(name = "tipo_punto_clave_aglo", nullable = true, length = 2)
    public String getTipoPuntoAglomeracion() {
        return tipoPuntoAglomeracion;
    }

    public void setTipoPuntoAglomeracion(String tipoPuntoAglomeracion) {
        this.tipoPuntoAglomeracion = tipoPuntoAglomeracion;
    }

    @Column(name = "tipo_punto_clave_aglo_otro", nullable = true, length = 255)
    public String getTipoPuntoAglomeracionOtro() {
        return tipoPuntoAglomeracionOtro;
    }

    public void setTipoPuntoAglomeracionOtro(String tipoPuntoAglomeracionOtro) {
        this.tipoPuntoAglomeracionOtro = tipoPuntoAglomeracionOtro;
    }

    @Column(name = "cuantas_personas_reunen", nullable = true)
    public Integer getCuantasPersonasReunen() {
        return cuantasPersonasReunen;
    }

    public void setCuantasPersonasReunen(Integer cuantasPersonasReunen) {
        this.cuantasPersonasReunen = cuantasPersonasReunen;
    }

    @Column(name = "cuantos_dias_sem_reunen", nullable = true)
    public Integer getCuantosDiasSemanaReunen() {
        return cuantosDiasSemanaReunen;
    }

    public void setCuantosDiasSemanaReunen(Integer cuantosDiasSemanaReunen) {
        this.cuantosDiasSemanaReunen = cuantosDiasSemanaReunen;
    }

    @Column(name = "hora_inicio_reunion", nullable = true, length = 16)
    public String getHoraInicioReunion() {
        return horaInicioReunion;
    }

    public void setHoraInicioReunion(String horaInicioReunion) {
        this.horaInicioReunion = horaInicioReunion;
    }

    @Column(name = "hora_fin_reunion", nullable = true, length = 16)
    public String getHoraFinReunion() {
        return horaFinReunion;
    }

    public void setHoraFinReunion(String horaFinReunion) {
        this.horaFinReunion = horaFinReunion;
    }

    @Column(name = "punto_gps", nullable = true, length = 100)
    public String getPuntoGps() {
        return puntoGps;
    }

    public void setPuntoGps(String puntoGps) {
        this.puntoGps = puntoGps;
    }

    @Column(name = "latitud", nullable = true)
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Column(name = "longitud", nullable = true)
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Column(name = "tipo_ingreso_cod_sitio", nullable = false, length = 2)
    public String getTipoIngresoCodigoSitio() {
        return tipoIngresoCodigoSitio;
    }

    public void setTipoIngresoCodigoSitio(String tipoIngresoCodigoSitio) {
        this.tipoIngresoCodigoSitio = tipoIngresoCodigoSitio;
    }

    @Column(name = "codigo_sitio", nullable = false, length = 16)
    public String getCodigoSitio() {
        return codigoSitio;
    }

    public void setCodigoSitio(String codigoSitio) {
        this.codigoSitio = codigoSitio;
    }

    @Column(name = "hay_ambiente_peri", nullable = false, length = 2)
    public String getHayAmbientePERI() {
        return hayAmbientePERI;
    }

    public void setHayAmbientePERI(String hayAmbientePERI) {
        this.hayAmbientePERI = hayAmbientePERI;
    }

    @Column(name = "hora_captura_peri", nullable = true, length = 16)
    public String getHoraCapturaPERI() {
        return horaCapturaPERI;
    }

    public void setHoraCapturaPERI(String horaCaptura) {
        this.horaCapturaPERI = horaCaptura;
    }

    @Column(name = "porcentaje_humedad_peri", nullable = true)
    public Double getHumedadRelativaPERI() {
        return humedadRelativaPERI;
    }

    public void setHumedadRelativaPERI(Double porcentajeHumedadRelativa) {
        this.humedadRelativaPERI = porcentajeHumedadRelativa;
    }

    @Column(name = "temperatura_peri", nullable = true)
    public Double getTemperaturaPERI() {
        return temperaturaPERI;
    }

    public void setTemperaturaPERI(Double temperatura) {
        this.temperaturaPERI = temperatura;
    }

    @Column(name = "tipo_ingreso_cod_peri", nullable = true, length = 2)
    public String getTipoIngresoCodigoPERI() {
        return tipoIngresoCodigoPERI;
    }

    public void setTipoIngresoCodigoPERI(String tipoIngresoCodigoPERI) {
        this.tipoIngresoCodigoPERI = tipoIngresoCodigoPERI;
    }

    @Column(name = "codigo_peri", nullable = true, length = 16)
    public String getCodigoPERI() {
        return codigoPERI;
    }

    public void setCodigoPERI(String codigoPERI) {
        this.codigoPERI = codigoPERI;
    }

    @Column(name = "hay_ambiente_intra", nullable = false, length = 2)
    public String getHayAmbienteINTRA() {
        return hayAmbienteINTRA;
    }

    public void setHayAmbienteINTRA(String hayAmbienteINTRA) {
        this.hayAmbienteINTRA = hayAmbienteINTRA;
    }

    @Column(name = "hora_captura_intra", nullable = true, length = 16)
    public String getHoraCapturaINTRA() {
        return horaCapturaINTRA;
    }

    public void setHoraCapturaINTRA(String horaCapturaINTRA) {
        this.horaCapturaINTRA = horaCapturaINTRA;
    }

    @Column(name = "porcentaje_humedad_intra", nullable = true)
    public Double getHumedadRelativaINTRA() {
        return humedadRelativaINTRA;
    }

    public void setHumedadRelativaINTRA(Double humedadRelativaINTRA) {
        this.humedadRelativaINTRA = humedadRelativaINTRA;
    }

    @Column(name = "temperatura_intra", nullable = true)
    public Double getTemperaturaINTRA() {
        return temperaturaINTRA;
    }

    public void setTemperaturaINTRA(Double temperaturaINTRA) {
        this.temperaturaINTRA = temperaturaINTRA;
    }

    @Column(name = "tipo_ingreso_cod_intra", nullable = true, length = 2)
    public String getTipoIngresoCodigoINTRA() {
        return tipoIngresoCodigoINTRA;
    }

    public void setTipoIngresoCodigoINTRA(String tipoIngresoCodigoINTRA) {
        this.tipoIngresoCodigoINTRA = tipoIngresoCodigoINTRA;
    }

    @Column(name = "codigo_intra", nullable = true, length = 16)
    public String getCodigoINTRA() {
        return codigoINTRA;
    }

    public void setCodigoINTRA(String codigoINTRA) {
        this.codigoINTRA = codigoINTRA;
    }

    @Column(name = "nombre_contesta_cuestionario", nullable = false, length = 150)
    public String getNombrePersonaContesta() {
        return nombrePersonaContesta;
    }

    public void setNombrePersonaContesta(String nombrePersonaContesta) {
        this.nombrePersonaContesta = nombrePersonaContesta;
    }

    public MovilInfo getMovilInfo() {
        return movilInfo;
    }

    public void setMovilInfo(MovilInfo movilInfo) {
        this.movilInfo = movilInfo;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "CuestionarioPuntoClave{" +codigoCuestionario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuestionarioPuntoClave)) return false;

        CuestionarioPuntoClave that = (CuestionarioPuntoClave) o;

        if (!codigoCuestionario.equals(that.codigoCuestionario)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoCuestionario.hashCode();
    }
}
