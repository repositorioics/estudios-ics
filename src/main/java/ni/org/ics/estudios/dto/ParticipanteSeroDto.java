package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ICS on 19/10/2020.
 */
public class ParticipanteSeroDto implements Serializable {

    private String codigo;
    private Integer idSerologia;
    private Integer idparticipante;
    private double volumen;
    private double edadMeses;
    private String nombreCompleto;
    private Integer codigo_casa_PDCS;
    private String codigo_casa_Familia;
    private String edad_year;
    private String edad_meses;
    private String edad_dias;
    private String estado;
    private Integer est_part;//Utilizado en RetiroController
    private Date fechaNacimiento;
    private String edadParticipante;
    private String estudios;
    private ArrayList<String> SusEstudios;
    private String nombrepadre;
    private String nombremadre;
    private String nombretutor;
    private String observacion;
    private String descripcion;
    private Date fecha;
    private double edadEnMeses;
    private String volumen_serologia_desde_bd;
    private String volumen_adicional_desde_bd;
    private String es_pbmc;


    public ParticipanteSeroDto() {
    }

    public ParticipanteSeroDto(String codigo, Integer idSerologia, Integer idparticipante, double volumen, double edadMeses, String nombreCompleto, Integer codigo_casa_PDCS, String codigo_casa_Familia, String edad_year, String edad_meses, String edad_dias, String estado, Integer est_part,Date fechaNacimiento, String edadParticipante, String estudios, ArrayList<String> susEstudios, String nombrepadre, String nombremadre, String nombretutor, String observacion, String descripcion, Date fecha, double edadEnMeses, String volumen_serologia_desde_bd, String volumen_adicional_desde_bd, String es_pbmc) {
        this.codigo = codigo;
        this.idSerologia = idSerologia;
        this.idparticipante = idparticipante;
        this.volumen = volumen;
        this.edadMeses = edadMeses;
        this.nombreCompleto = nombreCompleto;
        this.codigo_casa_PDCS = codigo_casa_PDCS;
        this.codigo_casa_Familia = codigo_casa_Familia;
        this.edad_year = edad_year;
        this.edad_meses = edad_meses;
        this.edad_dias = edad_dias;
        this.estado = estado;
        this.est_part=est_part;
        this.fechaNacimiento = fechaNacimiento;
        this.edadParticipante = edadParticipante;
        this.estudios = estudios;
        this.SusEstudios = susEstudios;
        this.nombrepadre = nombrepadre;
        this.nombremadre = nombremadre;
        this.nombretutor = nombretutor;
        this.observacion = observacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.edadEnMeses = edadEnMeses;
        this.volumen_serologia_desde_bd = volumen_serologia_desde_bd;
        this.volumen_adicional_desde_bd = volumen_adicional_desde_bd;
        this.es_pbmc= es_pbmc;
    }

    /*Getter & Setter*/

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdSerologia() {
        return idSerologia;
    }

    public void setIdSerologia(Integer idSerologia) {
        this.idSerologia = idSerologia;
    }

    public Integer getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(Integer idparticipante) {
        this.idparticipante = idparticipante;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(double edadMeses) {
        this.edadMeses = edadMeses;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getCodigo_casa_PDCS() {
        return codigo_casa_PDCS;
    }

    public void setCodigo_casa_PDCS(Integer codigo_casa_PDCS) {
        this.codigo_casa_PDCS = codigo_casa_PDCS;
    }

    public String getCodigo_casa_Familia() {
        return codigo_casa_Familia;
    }

    public void setCodigo_casa_Familia(String codigo_casa_Familia) {
        this.codigo_casa_Familia = codigo_casa_Familia;
    }

    public String getEdad_year() {
        return edad_year;
    }

    public void setEdad_year(String edad_year) {
        this.edad_year = edad_year;
    }

    public String getEdad_meses() {
        return edad_meses;
    }

    public void setEdad_meses(String edad_meses) {
        this.edad_meses = edad_meses;
    }

    public String getEdad_dias() {
        return edad_dias;
    }

    public void setEdad_dias(String edad_dias) {
        this.edad_dias = edad_dias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getEst_part() {
        return est_part;
    }

    public void setEst_part(Integer est_part) {
        this.est_part = est_part;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdadParticipante() {
        return edadParticipante;
    }

    public void setEdadParticipante(String edadParticipante) {
        this.edadParticipante = edadParticipante;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public ArrayList<String> getSusEstudios() {
        return SusEstudios;
    }

    public void setSusEstudios(ArrayList<String> susEstudios) {
        SusEstudios = susEstudios;
    }

    public String getNombrepadre() {
        return nombrepadre;
    }

    public void setNombrepadre(String nombrepadre) {
        this.nombrepadre = nombrepadre;
    }

    public String getNombremadre() {
        return nombremadre;
    }

    public void setNombremadre(String nombremadre) {
        this.nombremadre = nombremadre;
    }

    public String getNombretutor() {
        return nombretutor;
    }

    public void setNombretutor(String nombretutor) {
        this.nombretutor = nombretutor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getEdadEnMeses() {
        return edadEnMeses;
    }

    public void setEdadEnMeses(double edadEnMeses) {
        this.edadEnMeses = edadEnMeses;
    }

    public String getVolumen_serologia_desde_bd() {
        return volumen_serologia_desde_bd;
    }

    public void setVolumen_serologia_desde_bd(String volumen_serologia_desde_bd) {
        this.volumen_serologia_desde_bd = volumen_serologia_desde_bd;
    }

    public String getVolumen_adicional_desde_bd() {
        return volumen_adicional_desde_bd;
    }

    public void setVolumen_adicional_desde_bd(String volumen_adicional_desde_bd) {
        this.volumen_adicional_desde_bd = volumen_adicional_desde_bd;
    }

    public String getEs_pbmc() {
        return es_pbmc;
    }

    public void setEs_pbmc(String es_pbmc) {
        this.es_pbmc = es_pbmc;
    }
}
