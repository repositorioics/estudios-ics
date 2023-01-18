package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 12/02/2020.
 */
public class CoordenadasParticipanteDto implements Serializable {

    private String CODIGO;
    private Integer CODIGO_PARTICIPANTE;
    private Integer CODIGO_CASA;
    private Integer CODIGO_CHF;
    private String FECHA_REPORTADO;
    private Date FECHA_REGISTRO;
    private Integer CODIGO_BARRIO;
    private String NOMBRE;
    private String OTRO_BARRIO;
    private Integer MANZANA;
    private String STRING_MANZANA;
    private String DIRECCION;
    private Integer idPersona;
    private String NombrePersona;
    private String NOMBRE_USUARIO;
    private String OBSERVACION;

    public CoordenadasParticipanteDto() {
    }

    public CoordenadasParticipanteDto(String NOMBRE_USUARIO,String CODIGO, String STRING_MANZANA, Date FECHA_REGISTRO, String OBSERVACION, Integer CODIGO_PARTICIPANTE, Integer CODIGO_CASA, Integer CODIGO_CHF, String FECHA_REPORTADO, Integer CODIGO_BARRIO, String NOMBRE, String OTRO_BARRIO, Integer MANZANA, String DIRECCION, Integer idPersona, String nombrePersona) {
        this.CODIGO=CODIGO;
        this.OBSERVACION = OBSERVACION;
        this.CODIGO_PARTICIPANTE = CODIGO_PARTICIPANTE;
        this.CODIGO_CASA = CODIGO_CASA;
        this.CODIGO_CHF = CODIGO_CHF;
        this.FECHA_REPORTADO = FECHA_REPORTADO;
        this.CODIGO_BARRIO = CODIGO_BARRIO;
        this.NOMBRE = NOMBRE;
        this.OTRO_BARRIO = OTRO_BARRIO;
        this.MANZANA = MANZANA;
        this.DIRECCION = DIRECCION;
        this.idPersona = idPersona;
        NombrePersona = nombrePersona;
        this.FECHA_REGISTRO=FECHA_REGISTRO;
        this.STRING_MANZANA=STRING_MANZANA;
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;
    }

    public Integer getCODIGO_PARTICIPANTE() {
        return CODIGO_PARTICIPANTE;
    }

    public void setCODIGO_PARTICIPANTE(Integer CODIGO_PARTICIPANTE) {
        this.CODIGO_PARTICIPANTE = CODIGO_PARTICIPANTE;
    }

    public Integer getCODIGO_CASA() {
        return CODIGO_CASA;
    }

    public void setCODIGO_CASA(Integer CODIGO_CASA) {
        this.CODIGO_CASA = CODIGO_CASA;
    }

    public Integer getCODIGO_CHF() {
        return CODIGO_CHF;
    }

    public void setCODIGO_CHF(Integer CODIGO_CHF) {
        this.CODIGO_CHF = CODIGO_CHF;
    }

    public String getFECHA_REPORTADO() {
        return FECHA_REPORTADO;
    }

    public void setFECHA_REPORTADO(String FECHA_REPORTADO) {
        this.FECHA_REPORTADO = FECHA_REPORTADO;
    }

    public Integer getCODIGO_BARRIO() {
        return CODIGO_BARRIO;
    }

    public void setCODIGO_BARRIO(Integer CODIGO_BARRIO) {
        this.CODIGO_BARRIO = CODIGO_BARRIO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getOTRO_BARRIO() {
        return OTRO_BARRIO;
    }

    public void setOTRO_BARRIO(String OTRO_BARRIO) {
        this.OTRO_BARRIO = OTRO_BARRIO;
    }

    public Integer getMANZANA() {
        return MANZANA;
    }

    public void setMANZANA(Integer MANZANA) {
        this.MANZANA = MANZANA;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getOBSERVACION() {
        return OBSERVACION;
    }

    public void setOBSERVACION(String OBSERVACION) {
        this.OBSERVACION = OBSERVACION;
    }

    public String getNombrePersona() {
        return NombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        NombrePersona = nombrePersona;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getSTRING_MANZANA() {
        return STRING_MANZANA;
    }

    public void setSTRING_MANZANA(String STRING_MANZANA) {
        this.STRING_MANZANA = STRING_MANZANA;
    }

    public Date getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(Date FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public String getNOMBRE_USUARIO() {
        return NOMBRE_USUARIO;
    }

    public void setNOMBRE_USUARIO(String NOMBRE_USUARIO) {
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;
    }
}
