package ni.org.ics.estudios.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Representa los datos de la entrega de obsequios
 * 
 * @author Miguel Salinas
 **/
@Entity
@Table(name = "datos_obsequios", catalog = "estudios_ics")
public class ObsequioGeneral extends BaseMetaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String id;
    private Integer participante;
    private String casa;
    private String casaChf;
    private String seguimiento;
    private String numVisitaSeguimiento;
    private String motivo;//1 Pediatrica, 2 Familia, 3 Seguimiento_inicial, 4 Seguimiento_final
	private Integer obsequioSN;
	private String personaRecibe;
	private Integer relacionFam;
	private String otraRelacionFam;
	private String telefono;
	private String observaciones;

    private Boolean encuestaValida; //13-02-2024

    @Id
    @Column(name = "CODIGO", nullable = false, insertable = true, updatable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="CODIGO_PARTICIPANTE", nullable = true)
    public Integer getParticipante() {
        return participante;
    }

    public void setParticipante(Integer participante) {
        this.participante = participante;
    }

    @Column(name = "CODIGO_CASA", nullable = true, length = 5)
    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    @Column(name = "CODIGO_CASACHF", nullable = true, length = 5)
    public String getCasaChf() {
        return casaChf;
    }

    public void setCasaChf(String casaChf) {
        this.casaChf = casaChf;
    }

    @Column(name = "CODIGO_SEGUIMIENTO", nullable = true, length = 36)
    public String getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }

    @Column(name = "VISITA", nullable = true, length = 2)
    public String getNumVisitaSeguimiento() {
        return numVisitaSeguimiento;
    }

    public void setNumVisitaSeguimiento(String numVisitaSeguimiento) {
        this.numVisitaSeguimiento = numVisitaSeguimiento;
    }

    @Column(name = "MOTIVO", nullable = false, length = 3)
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Column(name = "OBSEQUIO_SN", nullable = true)
    public Integer getObsequioSN() {
        return obsequioSN;
    }

    public void setObsequioSN(Integer obsequioSN) {
        this.obsequioSN = obsequioSN;
    }

    @Column(name = "PERSONA_RECIBE", nullable = true, length = 250)
    public String getPersonaRecibe() {
        return personaRecibe;
    }

    public void setPersonaRecibe(String personaRecibe) {
        this.personaRecibe = personaRecibe;
    }

    @Column(name = "RELACION_FAM", nullable = true)
    public Integer getRelacionFam() {
        return relacionFam;
    }

    public void setRelacionFam(Integer relacionFam) {
        this.relacionFam = relacionFam;
    }

    @Column(name = "OTRA_RELACION_FAM", nullable = true, length = 250)
    public String getOtraRelacionFam() {
        return otraRelacionFam;
    }

    public void setOtraRelacionFam(String otraRelacionFam) {
        this.otraRelacionFam = otraRelacionFam;
    }

    @Column(name = "TELEFONO", nullable = true, length = 50)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "OBSERVACION", nullable = true, length = 250)
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @JsonIgnore
    @Column(name = "ENCUESTA_VALIDA")
    public Boolean getEncuestaValida() {
        return encuestaValida;
    }

    public void setEncuestaValida(Boolean encuestaValida) {
        this.encuestaValida = encuestaValida;
    }

    @Override
    public String toString() {
        return id;
    }


}
