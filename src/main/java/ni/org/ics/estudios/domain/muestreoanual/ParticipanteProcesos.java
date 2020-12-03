package ni.org.ics.estudios.domain.muestreoanual;

import ni.org.ics.estudios.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Simple objeto de dominio que representa un participante de los estudios
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "participantes_procesos", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "codigo") })
public class ParticipanteProcesos implements Auditable {

	/**
	 * 
	 */
	private Integer codigo;
	private String conPto;
	private Integer estPart;
	private String estudio;
	private String pbmc;
	private String consDeng;
	private String reConsDeng;
	private String zika;
	private String consFlu;
	private String consChik;
	private String conmx;
	private String conmxbhc;
	private String encLacMat;
	private String pesoTalla;
	private String encPart;
	private String enCasa;
	private String datosVisita;
	private String obsequio;
	private String convalesciente;
    private String infoVacuna;
	private String paxgene;
	private String adn;
	private String retoma;
	private Double volRetoma; //rojo
    private Double volRetomaPbmc;//pbmc
	private String datosParto;
	private String mi;
	private String casaCHF;
    private Integer relacionFam;
    private Integer cuantasPers;
    private String posZika;
    private String enCasaChf;
    private String enCasaSa;
    private String encPartSa;
    private String tutor;
    private String consSa;
    private String coordenadas; //cambio de domicilio
    private String obsequioChf;
    private String cDatosParto;//completar datos parto campos que no se pedian antes del 2018
    private String reConsChf18;//reconsentimiento a participantes de familia que cumplen 18 anios
    //22052019
    private String posDengue;
    //17072019. Saber si el participante es seleccionado para el estudio ZEN(Zika en Ninos)
    private String estudioZen;
    private String mxSuperficie; //1:asent mx superficie, 2:consent manos, 3:Ambos, 0 o null:No aplica
	//MA2020
	private String mostrarAlfabeto;
	private String mostrarPadreAlfabeto;
	private String mostrarMadreAlfabeta;
	private String mostrarNumParto;
	private String antecedenteTutorCP;
    private MovilInfo movilInfo;
    //Covid19
    private String consCovid19;
    private String subEstudios;
    //Parte E CHF para toma mx adicional Covid19
    private String consChf;
    private String cuestCovid;
    private String muestraCovid;
    //Texto que indica si el participante ha sido positivo para Covid19(SARS-COV2)
    private String posCovid;
    //Parte E Dengue. MUESTRA DE SANGRE ADICIONAL
    private String consDenParteE;
    private String mxDenParteE;


    @Id
	@Column(name = "codigo", nullable = false, length = 6)
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "est_part", nullable = false, length = 1)
	public Integer getEstPart() {
		return estPart;
	}

	public void setEstPart(Integer estado) {
		this.estPart = estado;
	}

	@Column(name = "estudio", nullable = false)
	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}

	@Column(name = "pbmc", nullable = false, length = 2)
	public String getPbmc() {
		return pbmc;
	}

	public void setPbmc(String pbmc) {
		this.pbmc = pbmc;
	}

	@Column(name = "cons_deng", nullable = false, length = 2)
	public String getConsDeng() {
		return consDeng;
	}

	public void setConsDeng(String consDeng) {
		this.consDeng = consDeng;
	}

	@Column(name = "cons_flu", nullable = false, length = 2)
	public String getConsFlu() {
		return consFlu;
	}

	public void setConsFlu(String consFlu) {
		this.consFlu = consFlu;
	}
	
	@Column(name = "cons_chik", nullable = false, length = 2)
	public String getConsChik() {
		return consChik;
	}

	public void setConsChik(String consChik) {
		this.consChik = consChik;
	}

	@Column(name = "conmx", nullable = false, length = 2)
	public String getConmx() {
		return conmx;
	}

	public void setConmx(String conmx) {
		this.conmx = conmx;
	}

	@Column(name = "enc_lacmat", nullable = false, length = 2)
	public String getEncLacMat() {
		return encLacMat;
	}

	public void setEncLacMat(String encLacMat) {
		this.encLacMat = encLacMat;
	}

	@Column(name = "peso_talla", nullable = false, length = 2)
	public String getPesoTalla() {
		return pesoTalla;
	}

	public void setPesoTalla(String pesoTalla) {
		this.pesoTalla = pesoTalla;
	}

	@Column(name = "enc_part", nullable = false, length = 2)
	public String getEncPart() {
		return encPart;
	}

	public void setEncPart(String encPart) {
		this.encPart = encPart;
	}
	
	@Column(name = "enc_casa", nullable = false, length = 2)
	public String getEnCasa() {
		return enCasa;
	}

	public void setEnCasa(String enCasa) {
		this.enCasa = enCasa;
	}

	@Column(name = "conmxbhc", nullable = false, length = 2)
	public String getConmxbhc() {
		return conmxbhc;
	}

	public void setConmxbhc(String conmxbhc) {
		this.conmxbhc = conmxbhc;
	}

	@Column(name = "obsequio", nullable = false, length = 2)
	public String getObsequio() {
		return obsequio;
	}

	public void setObsequio(String obsequio) {
		this.obsequio = obsequio;
	}

	@Column(name = "convalesciente", nullable = true, length = 2)
	public String getConvalesciente() {
		return convalesciente;
	}

	public void setConvalesciente(String convalesciente) {
		this.convalesciente = convalesciente;
	}

    @Column(name = "info_vacuna", nullable = true, length = 2)
    public String getInfoVacuna() {
        return infoVacuna;
    }

    public void setInfoVacuna(String infoVacuna) {
        this.infoVacuna = infoVacuna;
    }

    @Column(name = "paxgene", nullable = true, length = 2)
	public String getPaxgene() {
		return paxgene;
	}

	public void setPaxgene(String paxgene) {
		this.paxgene = paxgene;
	}

	@Column(name = "retoma", nullable = true, length = 2)
	public String getRetoma() {
		return retoma;
	}

	public void setRetoma(String retoma) {
		this.retoma = retoma;
	}

	@Column(name = "vol_retoma", nullable = true)
	public Double getVolRetoma() {
		return volRetoma;
	}

	public void setVolRetoma(Double volRetoma) {
		this.volRetoma = volRetoma;
	}

    @Column(name = "vol_retoma_pbmc", nullable = true)
    public Double getVolRetomaPbmc() {
        return volRetomaPbmc;
    }

    public void setVolRetomaPbmc(Double volRetomaPbmc) {
        this.volRetomaPbmc = volRetomaPbmc;
    }

    @Column(name = "recons_den", nullable = false, length = 2)
	public String getReConsDeng() {
		return reConsDeng;
	}

	public void setReConsDeng(String reConsDeng) {
		this.reConsDeng = reConsDeng;
	}
	
	@Column(name = "zika", nullable = false, length = 2)
	public String getZika() {
		return zika;
	}

	public void setZika(String zika) {
		this.zika = zika;
	}
	
	@Column(name = "adn", nullable = false, length = 2)
	public String getAdn() {
		return adn;
	}

	public void setAdn(String adn) {
		this.adn = adn;
	}

	@Column(name = "conpto", nullable = true, length = 2)
	public String getConPto() {
		return conPto;
	}

	public void setConPto(String conPto) {
		this.conPto = conPto;
	}

	@Column(name = "datos_parto", nullable = true, length = 2)
	public String getDatosParto() {
		return datosParto;
	}

	public void setDatosParto(String datosParto) {
		this.datosParto = datosParto;
	}

	@Column(name = "datos_visita", nullable = true, length = 2)
	public String getDatosVisita() {
		return datosVisita;
	}

	public void setDatosVisita(String datosVisita) {
		this.datosVisita = datosVisita;
	}

	@Column(name = "mi", nullable = true, length = 2)
	public String getMi() {
		return mi;
	}

	public void setMi(String mi) {
		this.mi = mi;
	}

	@Column(name = "casa_chf", nullable = true, length = 10)
	public String getCasaCHF() {
		return casaCHF;
	}

	public void setCasaCHF(String casaCHF) {
		this.casaCHF = casaCHF;
	}

    @Column(name = "relacion_fam", nullable = false, length = 1)
    public Integer getRelacionFam() {
        return relacionFam;
    }

    public void setRelacionFam(Integer relacionFam) {
        this.relacionFam = relacionFam;
    }

    @Column(name = "cuantas_personas", nullable = false, length = 1)
    public Integer getCuantasPers() {
        return cuantasPers;
    }

    public void setCuantasPers(Integer asiste) {
        this.cuantasPers = asiste;
    }

    @Column(name = "pos_zika", nullable = true, length = 2)
    public String getPosZika() {
        return posZika;
    }

    public void setPosZika(String posZika) {
        this.posZika = posZika;
    }

    @Column(name = "enc_casa_chf", nullable = true, length = 2)
    public String getEnCasaChf() {
        return enCasaChf;
    }

    public void setEnCasaChf(String enCasaChf) {
        this.enCasaChf = enCasaChf;
    }

    @Column(name = "enc_casa_sa", nullable = true, length = 2)
    public String getEnCasaSa() {
        return enCasaSa;
    }

    public void setEnCasaSa(String enCasaSa) {
        this.enCasaSa = enCasaSa;
    }

    @Column(name = "enc_part_sa", nullable = true, length = 2)
    public String getEncPartSa() {
        return encPartSa;
    }

    public void setEncPartSa(String encPartSa) {
        this.encPartSa = encPartSa;
    }

    @Column(name = "tutor", nullable = true, length = 255)
    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    @Column(name = "cons_sa", nullable = true, length = 2)
    public String getConsSa() {
        return consSa;
    }

    public void setConsSa(String consSa) {
        this.consSa = consSa;
    }

    @Column(name = "coordenada", nullable = true, length = 2)
    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String camDom) {
        this.coordenadas = camDom;
    }

    @Column(name = "obsequio_chf", nullable = true, length = 2)
    public String getObsequioChf() {
        return obsequioChf;
    }

    public void setObsequioChf(String obsequioChf) {
        this.obsequioChf = obsequioChf;
    }

    @Column(name = "c_datos_parto", nullable = true, length = 2)
    public String getcDatosParto() {
        return cDatosParto;
    }

    public void setcDatosParto(String cDatosParto) {
        this.cDatosParto = cDatosParto;
    }

    @Column(name = "recons_chf_18", nullable = true, length = 2)
    public String getReConsChf18() {
        return reConsChf18;
    }

    public void setReConsChf18(String reConsChf18) {
        this.reConsChf18 = reConsChf18;
    }

    @Column(name = "pos_dengue", nullable = true, length = 255)
    public String getPosDengue() {
        return posDengue;
    }

    public void setPosDengue(String posDengue) {
        this.posDengue = posDengue;
    }

    @JsonIgnore
    @Column(name = "estudio_zen", nullable = true, length = 2)
    public String getEstudioZen() {
        return estudioZen;
    }

    public void setEstudioZen(String estudioZen) {
        this.estudioZen = estudioZen;
    }

    @Column(name = "mx_superficie", nullable = true, length = 2)
    public String getMxSuperficie() {
        return mxSuperficie;
    }

    public void setMxSuperficie(String mxSuperficie) {
        this.mxSuperficie = mxSuperficie;
    }

	@Column(name = "preguntar_alfabeto", nullable = true, length = 2)
	public String getMostrarAlfabeto() {
		return mostrarAlfabeto;
	}

	public void setMostrarAlfabeto(String mostrarAlfabeto) {
		this.mostrarAlfabeto = mostrarAlfabeto;
	}

	@Column(name = "preguntar_padre_alfabeto", nullable = true, length = 2)
	public String getMostrarPadreAlfabeto() {
		return mostrarPadreAlfabeto;
	}

	public void setMostrarPadreAlfabeto(String mostrarPadreAlfabeto) {
		this.mostrarPadreAlfabeto = mostrarPadreAlfabeto;
	}

	@Column(name = "preguntar_madre_alfabeto", nullable = true, length = 2)
	public String getMostrarMadreAlfabeta() {
		return mostrarMadreAlfabeta;
	}

	public void setMostrarMadreAlfabeta(String mostrarMadreAlfabeta) {
		this.mostrarMadreAlfabeta = mostrarMadreAlfabeta;
	}

	@Column(name = "preguntar_num_parto", nullable = true, length = 2)
	public String getMostrarNumParto() {
		return mostrarNumParto;
	}

	public void setMostrarNumParto(String mostrarNumParto) {
		this.mostrarNumParto = mostrarNumParto;
	}

	@Column(name = "preguntar_antecedente_tutor", nullable = true, length = 2)
	public String getAntecedenteTutorCP() {
		return antecedenteTutorCP;
	}

	public void setAntecedenteTutorCP(String antecedenteTutorCP) {
		this.antecedenteTutorCP = antecedenteTutorCP;
	}

    @Column(name = "cons_covid19", nullable = true, length = 2)
    public String getConsCovid19() {
        return consCovid19;
    }

    public void setConsCovid19(String consCovid19) {
        this.consCovid19 = consCovid19;
    }

    @Column(name = "subestudios", nullable = true, length = 10)
    public String getSubEstudios() {
        return subEstudios;
    }

    public void setSubEstudios(String subEstudios) {
        this.subEstudios = subEstudios;
    }

    @Column(name = "cons_chf", nullable = true, length = 2)
    public String getConsChf() {
        return consChf;
    }

    public void setConsChf(String consChf) {
        this.consChf = consChf;
    }

    @Column(name = "cuest_covid19", nullable = true, length = 2)
    public String getCuestCovid() {
        return cuestCovid;
    }

    public void setCuestCovid(String cuestCovid) {
        this.cuestCovid = cuestCovid;
    }

    @Column(name = "mx_adic_covid19", nullable = true, length = 2)
    public String getMuestraCovid() {
        return muestraCovid;
    }

    public void setMuestraCovid(String muestraCovid) {
        this.muestraCovid = muestraCovid;
    }

    @Column(name = "pos_covid", nullable = true, length = 100)
    public String getPosCovid() {
        return posCovid;
    }

    public void setPosCovid(String posCovid) {
        this.posCovid = posCovid;
    }

    @Column(name = "cons_den_parte_e", nullable = true, length = 2)
    public String getConsDenParteE() {
        return consDenParteE;
    }

    public void setConsDenParteE(String consDenParteE) {
        this.consDenParteE = consDenParteE;
    }

    @Column(name = "mx_den_parte_e", nullable = true, length = 2)
    public String getMxDenParteE() {
        return mxDenParteE;
    }

    public void setMxDenParteE(String mxDenParteE) {
        this.mxDenParteE = mxDenParteE;
    }

    public MovilInfo getMovilInfo() {
        return movilInfo;
    }

    public void setMovilInfo(MovilInfo movilInfo) {
        this.movilInfo = movilInfo;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        if (fieldname.matches("movilInfo")) return false;
        else return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteProcesos)) return false;

        ParticipanteProcesos procesos = (ParticipanteProcesos) o;

        if (!codigo.equals(procesos.codigo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return "ParticipanteProcesos{" + codigo + '}';
    }
}
