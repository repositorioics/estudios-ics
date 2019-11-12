package ni.org.ics.estudios.domain.hemodinamica;

        import ni.org.ics.estudios.domain.BaseMetaData;

        import javax.persistence.*;

        import ni.org.ics.estudios.domain.Participante;
        import org.codehaus.jackson.annotate.JsonIgnore;
        import org.hibernate.annotations.ForeignKey;
        import org.hibernate.annotations.GenericGenerator;

        import java.util.Date;
/**
 * Created by ICS_Inspiron3 on 22/05/2019.
 */
@Entity
@Table(name = "datoshemodinamica", catalog = "estudios_ics")
public class DatosHemodinamica extends BaseMetaData {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String idDatoHemo;
    private Participante participante;
    private String silais;
    private String uSalud;
    private String municipio;
    private String sector;
    private String direccion;
    private Date fecha;
    private String nExpediente;
    private String telefono;
    private String edad;
    private String peso;
    private Double talla;
    private Double asc;
    private Double imc;
    private String IMCdetallado;
    private Date fie;
    private Integer diasenf;
    /* Nuevos Campos Agreados*/
    //Sistolica Diastolica
    private String sdMin;
    private String sdMed;
    private String sdMax;



    private String pamMed;
    private String pamMax;

    //Frecuencias Cardiacas
    private Integer fcMin;
    private Integer fcMed;
    private Integer fcProm;

    //Frecuencias Respiratorias
    private Integer frMin;
    private Integer frMax;

    private String barrioF;
    private char positivo = '0';

    /*  Getter y Setter*/
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "idDatoHemo", nullable = false)
    public String getIdDatoHemo(){
        return idDatoHemo;
    }

    public void setIdDatoHemo(String idDatoHemo) {
        this.idDatoHemo = idDatoHemo;
    }

    @ManyToOne
    @JoinColumn(name="idParticipante", updatable = false)
    @ForeignKey(name = "FK_idParticipante")
    public Participante getParticipante(){
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }


    @Column(name = "silais", nullable = false)
    public String getSilais() {
        return silais;
    }

    public void setSilais(String silais) {
        this.silais = silais;
    }

    @Column(name = "uSalud", nullable = false)
    public String getuSalud() {
        return uSalud;
    }

    public void setuSalud(String uSalud) {
        this.uSalud = uSalud;
    }

    @Column(name = "municipio", nullable = false)
    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Column(name = "sector", nullable = false)
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Column(name = "direccion", nullable = false)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "nExpediente", nullable = false)
    public String getnExpediente() {
        return nExpediente;
    }

    public void setnExpediente(String nExpediente) {
        this.nExpediente = nExpediente;
    }

    @Column(name = "telefono", nullable = false)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Column(name = "edad", nullable = false)
    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }


    @Column(name = "peso", nullable = false)
    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }


    @Column(name = "talla", nullable = false)
    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }


    @Column(name = "areasupcorp", nullable = false)
    public Double getAsc() {
        return asc;
    }

    public void setAsc(Double asc) {
        this.asc = asc;
    }


    @Column(name = "imc", nullable = false)
    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }


    @Column(name = "IMCdetallado", nullable = false)
    public String getIMCdetallado(){return IMCdetallado;}

    public void setIMCdetallado(String IMCdetallado) {
        this.IMCdetallado = IMCdetallado;
    }

    @Column(name = "fie", nullable = false)
    public Date getFie(){return fie;}

    public void setFie(Date fie) {
        this.fie = fie;
    }

    @Column(name = "diasenf", nullable = false)
   public Integer getDiasenf(){return diasenf;}

    public void setDiasenf(Integer diasenf) {
        this.diasenf = diasenf;
    }

    /*  nuevos campos */
    @Column(name = "sdMin", nullable = true)
    public String getSdMin(){return sdMin;}
    public void setSdMin(String sdMin){this.sdMin = sdMin;}

    @Column(name = "sdMed", nullable = true)
    public String getSdMed(){return sdMed;}
    public void setSdMed(String sdMed){
        this.sdMed = sdMed;
    }
    @Column(name = "sdMax", nullable = false)
    public String getSdMax (){
        return sdMax;
    }
    public void setSdMax(String sdMax){
        this.sdMax = sdMax;
    }

    //Pam
    private String pamMin;
    @Column(name = "pamMed", nullable = true)
    public String getPamMed() {
        return pamMed;
    }

    public void setPamMed(String pamMed) {
        this.pamMed = pamMed;
    }




    @Column(name = "pamMin", nullable = true)
    public String getPamMin() {
        return pamMin;
    }

    public void setPamMin(String pamMin) {
        this.pamMin = pamMin;
    }


    @Column(name = "pamMax", nullable = true)
    public String getPamMax() {
        return pamMax;
    }
    public void setPamMax(String pamMax) {
        this.pamMax = pamMax;
    }

    @Column(name = "fcMin", nullable = true)
    public Integer getFcMin() {
        return fcMin;
    }

    public void setFcMin(Integer fcMin) {
        this.fcMin = fcMin;
    }
    @Column(name = "fcMed", nullable = true)
    public Integer getFcMed() {
        return fcMed;
    }

    public void setFcMed(Integer fcMed) {
        this.fcMed = fcMed;
    }

    @Column(name = "fcProm", nullable = true)
    public Integer getFcProm() {
        return fcProm;
    }

    public void setFcProm(Integer fcProm) {
        this.fcProm = fcProm;
    }

    @Column(name = "frMin", nullable = true)
    public Integer getFrMin() {
        return frMin;
    }

    public void setFrMin(Integer frMin) {
        this.frMin = frMin;
    }


    @Column(name = "frMax", nullable = true)
    public Integer getFrMax() {
        return frMax;
    }

    public void setFrMax(Integer frMax) {
        this.frMax = frMax;
    }


    public String getBarrioF() {
        return barrioF;
    }

    public void setBarrioF(String barrioF) {
        this.barrioF = barrioF;
    }

    @Column(name = "positivo", nullable = true)
    public char getPositivo() {
        return positivo;
    }

    public void setPositivo(char positivo) {
        this.positivo = positivo;
    }
}
