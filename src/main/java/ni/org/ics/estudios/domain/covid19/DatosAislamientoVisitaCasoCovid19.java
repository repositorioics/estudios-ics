package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 22/06/2020.
 * V1.0
 */
@Entity
@Table(name = "covid_datos_aislamiento_vc", catalog = "estudios_ics")
public class DatosAislamientoVisitaCasoCovid19 extends BaseMetaData implements Auditable{
    private static final long serialVersionUID = 1L;
    private String codigoAislamiento;
    private VisitaSeguimientoCasoCovid19 codigoCasoVisita;
    private Date fechaDato;
    private String aislado;
    private String dormirSoloComparte;
    private String banioPropioComparte;
    private String alejadoFamilia;
    private String tieneContacto;
    private String conQuienTieneContacto;
    private String lugares;
    private String otrosLugares;
    private Date fechaRecibido; //poner fecha en que se recibe el registro en el server

    @Id
    @Column(name = "CODIGO_AISLAMIENTO", length = 36, nullable = false)
    public String getCodigoAislamiento() {
        return codigoAislamiento;
    }

    public void setCodigoAislamiento(String codigoAislamiento) {
        this.codigoAislamiento = codigoAislamiento;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_VISITA_CASO", nullable = false)
    @ForeignKey(name = "FK_VISITA_DATOSAISLA_COVCAS")
    public VisitaSeguimientoCasoCovid19 getCodigoCasoVisita() {
        return codigoCasoVisita;
    }

    public void setCodigoCasoVisita(VisitaSeguimientoCasoCovid19 codigoCasoVisita) {
        this.codigoCasoVisita = codigoCasoVisita;
    }

    @Column(name = "FECHA_DATO", nullable = false)
    public Date getFechaDato() {
        return fechaDato;
    }

    public void setFechaDato(Date fechaDato) {
        this.fechaDato = fechaDato;
    }

    @Column(name = "AISLADO", length = 3, nullable = false)
    public String getAislado() {
        return aislado;
    }

    public void setAislado(String aislado) {
        this.aislado = aislado;
    }

    @Column(name = "DORMIR_SOLO_COMPARTE", length = 3, nullable = true)
    public String getDormirSoloComparte() {
        return dormirSoloComparte;
    }

    public void setDormirSoloComparte(String dormirSoloComparte) {
        this.dormirSoloComparte = dormirSoloComparte;
    }

    @Column(name = "BANIO_PROPIO_COMPARTE", length = 3, nullable = true)
    public String getBanioPropioComparte() {
        return banioPropioComparte;
    }

    public void setBanioPropioComparte(String banioPropioComparte) {
        this.banioPropioComparte = banioPropioComparte;
    }

    @Column(name = "ALEJADO_FAMILIA", length = 3, nullable = true)
    public String getAlejadoFamilia() {
        return alejadoFamilia;
    }

    public void setAlejadoFamilia(String alejadoFamilia) {
        this.alejadoFamilia = alejadoFamilia;
    }

    @Column(name = "TIENE_CONTACTO", length = 3, nullable = true)
    public String getTieneContacto() {
        return tieneContacto;
    }

    public void setTieneContacto(String tieneContacto) {
        this.tieneContacto = tieneContacto;
    }

    @Column(name = "CONQUIEN_TIENE_CONTACTO", length = 200, nullable = true)
    public String getConQuienTieneContacto() {
        return conQuienTieneContacto;
    }

    public void setConQuienTieneContacto(String conQuienTieneContacto) {
        this.conQuienTieneContacto = conQuienTieneContacto;
    }

    @Column(name = "LUGARES", length = 32, nullable = true)
    public String getLugares() {
        return lugares;
    }

    public void setLugares(String lugares) {
        this.lugares = lugares;
    }

    @Column(name = "OTROS_LUGARES", length = 500, nullable = true)
    public String getOtrosLugares() {
        return otrosLugares;
    }

    public void setOtrosLugares(String otrosLugares) {
        this.otrosLugares = otrosLugares;
    }

    @JsonIgnore
    @Column(name = "FECHA_RECIBIDO")
    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosAislamientoVisitaCasoCovid19 that = (DatosAislamientoVisitaCasoCovid19) o;
        return codigoAislamiento.equals(that.codigoAislamiento);
    }

    @Override
    public int hashCode() {
        int result = codigoAislamiento.hashCode();
        result = 31 * result + codigoAislamiento.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DatosAislamientoVisitaCasoCovid19{" +
                "codigoAislamiento='" + codigoAislamiento + '\'' +
                ", fechaDato=" + fechaDato +
                '}';
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
