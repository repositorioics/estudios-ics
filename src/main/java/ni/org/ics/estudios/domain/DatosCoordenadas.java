package ni.org.ics.estudios.domain;

//import jdk.nashorn.internal.ir.annotations.Ignore;
import ni.org.ics.estudios.domain.catalogs.Barrio;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import ni.org.ics.estudios.domain.muestreoanual.MovilInfo;
import org.hibernate.annotations.ForeignKey;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *  Objeto que representa DatosCoordenadas
 * @author Miguel Salinas
 **/
@Entity
@Table(name = "datos_coordenadas", catalog = "estudios_ics")
public class DatosCoordenadas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String codigo;
    private Integer codigoCasa; //1
    private String estudios; //2
    private Participante participante; //3
    private String motivo; //no
	private Barrio barrio; //4
    private String otroBarrio; //5
	private String direccion; //6
    private String manzana; //7
    private String conpunto; //13
    private String puntoGps; //no
	private Double latitud; //no
	private Double longitud; //no
    private String razonNoGeoref; //8
    private String otraRazonNoGeoref; //no
    private Integer casacohortefamilia; //9
    private Character actual; //10
    private String telefono; //11
    private Integer recurso1; //12
    private String Observacion;
    private String fechaReportado;



    private MovilInfo movilInfo;
    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "CODIGO_CASA", nullable = true, length = 4)
    public Integer getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(Integer codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    @Column(name = "ESTUDIOS_ACTUALES", nullable = true, length = 255)
    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CAMBIODOM")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "MOTIVO", nullable = true, length = 2)
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_BARRIO", nullable = true)
    @ForeignKey(name = "FK_BARRIO_CAMBIODOM")
    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    @Column(name = "OTRO_BARRIO", nullable = true, length = 250)
    public String getOtroBarrio() {
        return otroBarrio;
    }

    public void setOtroBarrio(String otroBarrio) {
        this.otroBarrio = otroBarrio;
    }

    @Column(name = "DIRECCION", nullable = true, length = 250)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "MANZANA", nullable = true, length = 4)
    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    @Column(name = "CON_PUNTO_GPS", nullable = true, length = 2)
    public String getConpunto() {
        return conpunto;
    }

    public void setConpunto(String conpunto) {
        this.conpunto = conpunto;
    }

    @Column(name = "PUNTO_GPS", nullable = true, length = 100)
    public String getPuntoGps() {
        return puntoGps;
    }

    public void setPuntoGps(String puntoGps) {
        this.puntoGps = puntoGps;
    }

    @Column(name = "LATITUD", nullable = true)
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Column(name = "LONGITUD", nullable = true)
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Column(name = "RAZON_SIN_PUNTO", nullable = true, length = 2)
    public String getRazonNoGeoref() {
        return razonNoGeoref;
    }

    public void setRazonNoGeoref(String razonNoGeoref) {
        this.razonNoGeoref = razonNoGeoref;
    }

    @Column(name = "OTRA_RAZON_SIN_PUNTO", nullable = true, length = 250)
    public String getOtraRazonNoGeoref() {
        return otraRazonNoGeoref;
    }

    public void setOtraRazonNoGeoref(String otraRazonNoGeoref) {
        this.otraRazonNoGeoref = otraRazonNoGeoref;
    }

    public MovilInfo getMovilInfo() {
        return movilInfo;
    }

    public void setMovilInfo(MovilInfo movilInfo) {
        this.movilInfo = movilInfo;
    }

    @Column(name = "OBSERVACION", nullable = true, length = 250)
    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    @Override
	public String toString(){
		return this.codigo + " " + this.latitud+" "+this.longitud;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatosCoordenadas)) return false;

        DatosCoordenadas casa = (DatosCoordenadas) o;

        return (!codigo.equals(casa.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }


    @Column(name = "CODIGO_CHF", nullable = true)
    public Integer getCasacohortefamilia() {
        return casacohortefamilia;
    }

    public void setCasacohortefamilia(Integer casacohortefamilia) {
        this.casacohortefamilia = casacohortefamilia;
    }

    @Column(name = "actual", nullable = true)
    public Character getActual() {
        return actual;
    }

    public void setActual(Character actual) {
        this.actual = actual;
    }
    @Column(name = "telefono", nullable = true)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "idPersona", nullable = false)
    public Integer getRecurso1() {
        return recurso1;
    }

    public void setRecurso1(Integer recurso1) {
        this.recurso1 = recurso1;
    }

    @Column(name = "FECHA_REPORTADO", nullable = true)
    public String getFechaReportado() {
        return fechaReportado;
    }

    public void setFechaReportado(String fechaReportado) {
        this.fechaReportado = fechaReportado;
    }
}
