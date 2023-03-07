package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ICS on 23/11/2020.
 */

public class HistorialRetiroDto implements Serializable {

    private Integer codigo_participante;
    private String nombreCompletoParticipante;
    private String nombreCompletoPadre;
    private String nombreCompletoMadre;
    private String sexo;
    private String fechaNac;
    private String edad;
    private Integer codigoCasa;
    private String CodigoCasaFamilia;
    private Integer codigoBarrio;
    private String nombreBarrio;
    private String direccion;
    private String manzana;
    private String estudios;
    private Integer estPart;
    private String tutor;
    private String relacionFamTutor;
    private String jefe;
    private String isPbmc;
    private String isPaxgene;
    private String pesotalla;
    private String vacunas;
    private String lactMat;
    private String encPart;
    private String encCHF;
    private String encCasaCoh;
    private String encCasaSa;
    private String consCovid;
    private String consDeng;
    private String consFlu;
    private String consChf;
    private String cuestCovid;
    private String tieneBhc;
    private String tieneSerologia;
    private String alertas;
    //agregado por Santiago MA2023
    private String perimetroAbdominal;
    private String encSatUsu;

    private List<RetiroDto> retiroList;

    public HistorialRetiroDto() {
    }

    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    public String getNombreCompletoParticipante() {
        return nombreCompletoParticipante;
    }

    public void setNombreCompletoParticipante(String nombreCompletoParticipante) {
        this.nombreCompletoParticipante = nombreCompletoParticipante;
    }

    public String getNombreCompletoPadre() {
        return nombreCompletoPadre;
    }

    public void setNombreCompletoPadre(String nombreCompletoPadre) {
        this.nombreCompletoPadre = nombreCompletoPadre;
    }

    public String getNombreCompletoMadre() {
        return nombreCompletoMadre;
    }

    public void setNombreCompletoMadre(String nombreCompletoMadre) {
        this.nombreCompletoMadre = nombreCompletoMadre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Integer getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(Integer codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    public String getCodigoCasaFamilia() {
        return CodigoCasaFamilia;
    }

    public void setCodigoCasaFamilia(String codigoCasaFamilia) {
        CodigoCasaFamilia = codigoCasaFamilia;
    }

    public Integer getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(Integer codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public void setNombreBarrio(String nombreBarrio) {
        this.nombreBarrio = nombreBarrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public Integer getEstPart() {
        return estPart;
    }

    public void setEstPart(Integer estPart) {
        this.estPart = estPart;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getRelacionFamTutor() {
        return relacionFamTutor;
    }

    public void setRelacionFamTutor(String relacionFamTutor) {
        this.relacionFamTutor = relacionFamTutor;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public String getIsPbmc() {
        return isPbmc;
    }

    public void setIsPbmc(String isPbmc) {
        this.isPbmc = isPbmc;
    }

    public String getIsPaxgene() {
        return isPaxgene;
    }

    public void setIsPaxgene(String isPaxgene) {
        this.isPaxgene = isPaxgene;
    }

    public String getPesotalla() {
        return pesotalla;
    }

    public void setPesotalla(String pesotalla) {
        this.pesotalla = pesotalla;
    }

    public String getVacunas() {
        return vacunas;
    }

    public void setVacunas(String vacunas) {
        this.vacunas = vacunas;
    }

    public String getLactMat() {
        return lactMat;
    }

    public void setLactMat(String lactMat) {
        this.lactMat = lactMat;
    }

    public String getEncPart() {
        return encPart;
    }

    public void setEncPart(String encPart) {
        this.encPart = encPart;
    }

    public String getEncCHF() {
        return encCHF;
    }

    public void setEncCHF(String encCHF) {
        this.encCHF = encCHF;
    }

    public String getEncCasaCoh() {
        return encCasaCoh;
    }

    public void setEncCasaCoh(String encCasaCoh) {
        this.encCasaCoh = encCasaCoh;
    }

    public String getEncCasaSa() {
        return encCasaSa;
    }

    public void setEncCasaSa(String encCasaSa) {
        this.encCasaSa = encCasaSa;
    }

    public String getConsCovid() {
        return consCovid;
    }

    public void setConsCovid(String consCovid) {
        this.consCovid = consCovid;
    }

    public String getConsDeng() {
        return consDeng;
    }

    public void setConsDeng(String consDeng) {
        this.consDeng = consDeng;
    }

    public String getConsFlu() {
        return consFlu;
    }

    public void setConsFlu(String consFlu) {
        this.consFlu = consFlu;
    }

    public List<RetiroDto> getRetiroList() {
        return retiroList;
    }

    public void setRetiroList(List<RetiroDto> retiroList) {
        this.retiroList = retiroList;
    }

    public String getConsChf() {
        return consChf;
    }

    public void setConsChf(String consChf) {
        this.consChf = consChf;
    }

    public String getCuestCovid() {
        return cuestCovid;
    }

    public String getTieneBhc() {
        return tieneBhc;
    }

    public void setTieneBhc(String tieneBhc) {
        this.tieneBhc = tieneBhc;
    }

    public String getTieneSerologia() {
        return tieneSerologia;
    }

    public void setTieneSerologia(String tieneSerologia) {
        this.tieneSerologia = tieneSerologia;
    }

    public void setCuestCovid(String cuestCovid) {
        this.cuestCovid = cuestCovid;
    }

    public String getAlertas() {
        return alertas;
    }

    public void setAlertas(String alertas) {
        this.alertas = alertas;
    }

    public String getPerimetroAbdominal() {
        return perimetroAbdominal;
    }

    public void setPerimetroAbdominal(String perimetroAbdominal) {
        this.perimetroAbdominal = perimetroAbdominal;
    }

    public String getEncSatUsu() {
        return encSatUsu;
    }

    public void setEncSatUsu(String encSatUsu) {
        this.encSatUsu = encSatUsu;
    }
}
