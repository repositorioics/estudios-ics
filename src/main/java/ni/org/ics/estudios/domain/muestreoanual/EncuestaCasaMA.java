package ni.org.ics.estudios.domain.muestreoanual;

import javax.persistence.*;
import java.util.Date;

/**
 * Simple objeto de dominio que representa una encuesta de casa que se le
 * realiza a cada participante de los
 * 
 * estudios
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "encuestas_casas", catalog = "estudios_ics",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cod_casa","fecha_encuesta"}),
                @UniqueConstraint(columnNames = {"cod_casachf","fecha_encuesta"})
        })
public class EncuestaCasaMA {

	/**
	 * 
	 */
    private String codigo;
    private Integer codCasa;
    private Date fechaEncCasa;
    private String codCasaChf;
	//private EncuestaCasaId encCasaId;
	private Integer cvivencasa1;
	private Integer cvivencasa2;
	private Integer cvivencasa3;
	private Integer cvivencasa4;
	private Integer cvivencasa5;
	private Integer cvivencasa6;
	private Integer cvivencasa7;
	private Integer ccuartos;
	private Integer grifo;
	private Integer grifoComSN;
	private Integer horasagua;
	private String mcasa;
	private String ocasa;
	private String piso;
	private String opiso;
	private Integer techo;
	private String otecho;
	private Integer cpropia;
	private Integer cabanicos;
	private Integer ctelevisores;
	private Integer crefrigeradores;
	private Integer moto;
	private Integer carro;
	private Integer cocinalena;
	private Integer animalesSN;
	private Integer pollos;
	private Integer polloscasa;
	private Integer patos;
	private Integer patoscasa;
	private Integer perros;
	private Integer perroscasa;
	private Integer gatos;
	private Integer gatoscasa;
	private Integer cerdos;
	private Integer cerdoscasa;
	private MovilInfo movilInfo;
	private Integer otrorecurso1;
	private Integer otrorecurso2;
    //CHF + NUEVAS PREGUNTAS MA2018
    private String viveEmbEnCasa;
    private Integer cantidadCuartos;
    private String almacenaAgua;
    private String almacenaEnBarriles;
    private Integer numeroBarriles;
    private String barrilesTapados;
    private String barrilesConAbate;
    private String almacenaEnTanques;
    private Integer numeroTanques;
    private String tanquesTapados;
    private String tanquesConAbate;
    private String almacenaEnPilas;
    private Integer numeroPilas;
    private String pilasTapadas;
    private String pilasConAbate;
    private String almacenaEnOtrosrecip;
    private String descOtrosRecipientes;
    private Integer numeroOtrosRecipientes;
    private String otrosRecipTapados;
    private String otrosrecipConAbate;
    private String ubicacionLavandero;
    private String tieneAbanico;
    private String tieneTelevisor;
    private String tieneRefrigeradorFreezer;
    private String tieneAireAcondicionado;
    private String funcionamientoAire;
    private String opcFabCarro;
    private Integer yearNow;
    private Integer yearFabCarro;
    private String tieneMicrobus;
    private String tieneCamioneta;
    private String tieneCamion;
    private String tieneOtroMedioTrans;
    private String descOtroMedioTrans;
    private String cocinaConLenia;
    private String ubicacionCocinaLenia;
    private String periodicidadCocinaLenia;
    private Integer numDiarioCocinaLenia;
    private Integer numSemanalCocinaLenia;
    private Integer numQuincenalCocinaLenia;
    private Integer numMensualCocinaLenia;
    private String tieneGallinas;
    private String tienePatos;
    private String tienePerros;
    private String tieneGatos;
    private String tieneCerdos;
    private String persFumaDentroCasa;
    private String madreFuma;
    private Integer cantCigarrillosMadre;
    private String padreFuma;
    private Integer cantCigarrillosPadre;
    private String otrosFuman;
    private Integer cantOtrosFuman;
    private Integer cantCigarrillosOtros;
    private String servRecolBasura;
    private Integer frecServRecolBasura;
    private String llantasOtrosContConAgua;
    private String opcFabMicrobus;
    private Integer yearFabMicrobus;
    private String opcFabCamioneta;
    private Integer yearFabCamioneta;
    private String opcFabCamion;
    private Integer yearFabCamion;
    private String opcFabOtroMedioTrans;
    private Integer yearFabOtroMedioTrans;
	//MA2020
	private String cambiadoCasa;
	private String remodelacionCasa;
	private String tieneVehiculo;
	private Integer participante;
    @Id
    @Column(name = "codigo", length = 50, nullable = false)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "cod_casa", nullable = true, length = 4)
    public Integer getCodCasa() {
        return codCasa;
    }

    public void setCodCasa(Integer codCasa) {
        this.codCasa = codCasa;
    }

    @Column(name = "fecha_encuesta", nullable = false)
    public Date getFechaEncCasa() {
        return fechaEncCasa;
    }

    public void setFechaEncCasa(Date fechaEncCasa) {
        this.fechaEncCasa = fechaEncCasa;
    }

    @Column(name = "cod_casachf", nullable = true, length = 4)
    public String getCodCasaChf() {
        return codCasaChf;
    }

    public void setCodCasaChf(String codCasaChf) {
        this.codCasaChf = codCasaChf;
    }

    /*@EmbeddedId
	public EncuestaCasaId getEncCasaId() {
		return encCasaId;
	}

	public void setEncCasaId(EncuestaCasaId encCasaId) {
		this.encCasaId = encCasaId;
	}*/

	@Column(name = "cvivencasa1", nullable = false, length = 2)
	public Integer getCvivencasa1() {
		return cvivencasa1;
	}

	public void setCvivencasa1(Integer cvivencasa1) {
		this.cvivencasa1 = cvivencasa1;
	}

	@Column(name = "cvivencasa2", nullable = false, length = 2)
	public Integer getCvivencasa2() {
		return cvivencasa2;
	}

	public void setCvivencasa2(Integer cvivencasa2) {
		this.cvivencasa2 = cvivencasa2;
	}

	@Column(name = "cvivencasa3", nullable = false, length = 2)
	public Integer getCvivencasa3() {
		return cvivencasa3;
	}

	public void setCvivencasa3(Integer cvivencasa3) {
		this.cvivencasa3 = cvivencasa3;
	}

	@Column(name = "cvivencasa4", nullable = false, length = 2)
	public Integer getCvivencasa4() {
		return cvivencasa4;
	}

	public void setCvivencasa4(Integer cvivencasa4) {
		this.cvivencasa4 = cvivencasa4;
	}

	@Column(name = "cvivencasa5", nullable = false, length = 2)
	public Integer getCvivencasa5() {
		return cvivencasa5;
	}

	public void setCvivencasa5(Integer cvivencasa5) {
		this.cvivencasa5 = cvivencasa5;
	}

	@Column(name = "cvivencasa6", nullable = false, length = 2)
	public Integer getCvivencasa6() {
		return cvivencasa6;
	}

	public void setCvivencasa6(Integer cvivencasa6) {
		this.cvivencasa6 = cvivencasa6;
	}

	@Column(name = "ccuartos", nullable = false, length = 2)
	public Integer getCcuartos() {
		return ccuartos;
	}

	public void setCcuartos(Integer ccuartos) {
		this.ccuartos = ccuartos;
	}

	@Column(name = "grifo", nullable = true, length = 1)
	public Integer getGrifo() {
		return grifo;
	}

	public void setGrifo(Integer grifo) {
		this.grifo = grifo;
	}

	@Column(name = "grifocomsn", nullable = true, length = 1)
	public Integer getGrifoComSN() {
		return grifoComSN;
	}

	public void setGrifoComSN(Integer grifoComSN) {
		this.grifoComSN = grifoComSN;

	}

	@Column(name = "horasagua", nullable = true, length = 2)
	public Integer gethorasagua() {
		return horasagua;
	}

	public void sethorasagua(Integer horasagua) {
		this.horasagua = horasagua;

	}

	@Column(name = "mcasa", nullable = true, length = 20)
	public String getMcasa() {
		return mcasa;
	}

	public void setMcasa(String mcasa) {
		this.mcasa = mcasa;
	}

	@Column(name = "ocasa", nullable = true, length = 20)
	public String getOcasa() {
		return ocasa;
	}

	public void setOcasa(String ocasa) {
		this.ocasa = ocasa;
	}

	@Column(name = "piso", nullable = true, length = 20)
	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	@Column(name = "opiso", nullable = true, length = 20)
	public String getOpiso() {
		return opiso;
	}

	public void setOpiso(String opiso) {
		this.opiso = opiso;
	}

	@Column(name = "techo", nullable = true, length = 1)
	public Integer getTecho() {
		return techo;
	}

	public void setTecho(Integer techo) {
		this.techo = techo;
	}

	@Column(name = "otecho", nullable = true, length = 20)
	public String getOtecho() {
		return otecho;
	}

	public void setOtecho(String otecho) {
		this.otecho = otecho;
	}

	@Column(name = "cpropia", nullable = true, length = 1)
	public Integer getCpropia() {
		return cpropia;
	}

	public void setCpropia(Integer cpropia) {
		this.cpropia = cpropia;
	}

	@Column(name = "cabanicos", nullable = true, length = 1)
	public Integer getCabanicos() {
		return cabanicos;
	}

	public void setCabanicos(Integer cabanicos) {
		this.cabanicos = cabanicos;
	}

	@Column(name = "ctelevisores", nullable = true, length = 1)
	public Integer getCtelevisores() {
		return ctelevisores;
	}

	public void setCtelevisores(Integer ctelevisores) {
		this.ctelevisores = ctelevisores;
	}

	@Column(name = "crefrigeradores", nullable = true, length = 1)
	public Integer getCrefrigeradores() {
		return crefrigeradores;
	}

	public void setCrefrigeradores(Integer crefrigeradores) {
		this.crefrigeradores = crefrigeradores;
	}

	@Column(name = "moto", nullable = true, length = 1)
	public Integer getMoto() {
		return moto;
	}

	public void setMoto(Integer moto) {
		this.moto = moto;
	}

	@Column(name = "carro", nullable = true, length = 1)
	public Integer getCarro() {
		return carro;
	}

	public void setCarro(Integer carro) {
		this.carro = carro;
	}

	@Column(name = "cocinalena", nullable = true, length = 1)
	public Integer getCocinalena() {
		return cocinalena;
	}

	public void setCocinalena(Integer cocinalena) {
		this.cocinalena = cocinalena;
	}

	@Column(name = "animalessn", nullable = true, length = 1)
	public Integer getAnimalesSN() {
		return animalesSN;
	}

	public void setAnimalesSN(Integer animalesSN) {
		this.animalesSN = animalesSN;
	}

	@Column(name = "pollos", nullable = true, length = 2)
	public Integer getPollos() {
		return pollos;
	}

	public void setPollos(Integer pollos) {
		this.pollos = pollos;
	}

	@Column(name = "polloscasa", nullable = true, length = 1)
	public Integer getPolloscasa() {
		return polloscasa;
	}

	public void setPolloscasa(Integer polloscasa) {
		this.polloscasa = polloscasa;
	}

	@Column(name = "patos", nullable = true, length = 2)
	public Integer getPatos() {
		return patos;
	}

	public void setPatos(Integer patos) {
		this.patos = patos;
	}

	@Column(name = "patoscasa", nullable = true, length = 1)
	public Integer getPatoscasa() {
		return patoscasa;
	}

	public void setPatoscasa(Integer patoscasa) {
		this.patoscasa = patoscasa;
	}

	@Column(name = "perros", nullable = true, length = 2)
	public Integer getPerros() {
		return perros;
	}

	public void setPerros(Integer perros) {
		this.perros = perros;
	}

	@Column(name = "perroscasa", nullable = true, length = 1)
	public Integer getPerroscasa() {
		return perroscasa;
	}

	public void setPerroscasa(Integer perroscasa) {
		this.perroscasa = perroscasa;
	}

	@Column(name = "gatos", nullable = true, length = 2)
	public Integer getGatos() {
		return gatos;
	}

	public void setGatos(Integer gatos) {
		this.gatos = gatos;
	}

	@Column(name = "gatoscasa", nullable = true, length = 1)
	public Integer getGatoscasa() {
		return gatoscasa;
	}

	public void setGatoscasa(Integer gatoscasa) {
		this.gatoscasa = gatoscasa;
	}

	@Column(name = "cerdos", nullable = true, length = 2)
	public Integer getCerdos() {
		return cerdos;
	}

	public void setCerdos(Integer cerdos) {
		this.cerdos = cerdos;
	}

	@Column(name = "cerdoscasa", nullable = true, length = 1)
	public Integer getCerdoscasa() {
		return cerdoscasa;
	}

	public void setCerdoscasa(Integer cerdoscasa) {
		this.cerdoscasa = cerdoscasa;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}
	
	@Column(name = "otrorecurso1", nullable = true, length = 10)
	public Integer getOtrorecurso1() {
		return otrorecurso1;
	}

	public void setOtrorecurso1(Integer otrorecurso1) {
		this.otrorecurso1 = otrorecurso1;
	}

	@Column(name = "otrorecurso2", nullable = true, length = 10)
	public Integer getOtrorecurso2() {
		return otrorecurso2;
	}

	public void setOtrorecurso2(Integer otrorecurso2) {
		this.otrorecurso2 = otrorecurso2;
	}

	@Column(name = "cvivencasa7", nullable = true, length = 2)
	public Integer getCvivencasa7() {
		return cvivencasa7;
	}

	public void setCvivencasa7(Integer cvivencasa7) {
		this.cvivencasa7 = cvivencasa7;
	}

    @Column(name = "viveEmbEnCasa", nullable = true, length = 2)
    public String getViveEmbEnCasa() {
        return viveEmbEnCasa;
    }

    public void setViveEmbEnCasa(String viveEmbEnCasa) {
        this.viveEmbEnCasa = viveEmbEnCasa;
    }

    @Column(name = "CANTIDAD_CUARTOS", nullable = true, length = 2)
    public Integer getCantidadCuartos() {
        return cantidadCuartos;
    }

    public void setCantidadCuartos(Integer cantidadCuartos) {
        this.cantidadCuartos = cantidadCuartos;
    }

    @Column(name = "ALMACENA_AGUA", nullable = true, length = 2)
    public String getAlmacenaAgua() {
        return almacenaAgua;
    }

    public void setAlmacenaAgua(String almacenaAgua) {
        this.almacenaAgua = almacenaAgua;
    }

    @Column(name = "ALMACENA_EN_BARRILES", nullable = true, length = 2)
    public String getAlmacenaEnBarriles() {
        return almacenaEnBarriles;
    }

    public void setAlmacenaEnBarriles(String almacenaEnBarriles) {
        this.almacenaEnBarriles = almacenaEnBarriles;
    }

    @Column(name = "NUMERO_BARRILES", nullable = true, length = 2)
    public Integer getNumeroBarriles() {
        return numeroBarriles;
    }

    public void setNumeroBarriles(Integer numeroBarriles) {
        this.numeroBarriles = numeroBarriles;
    }

    @Column(name = "BARRILES_TAPADOS", nullable = true, length = 2)
    public String getBarrilesTapados() {
        return barrilesTapados;
    }

    public void setBarrilesTapados(String barrilesTapados) {
        this.barrilesTapados = barrilesTapados;
    }

    @Column(name = "BARRILES_CON_ABATE", nullable = true, length = 2)
    public String getBarrilesConAbate() {
        return barrilesConAbate;
    }

    public void setBarrilesConAbate(String barrilesConAbate) {
        this.barrilesConAbate = barrilesConAbate;
    }

    @Column(name = "ALMACENA_EN_TANQUES", nullable = true, length = 2)
    public String getAlmacenaEnTanques() {
        return almacenaEnTanques;
    }

    public void setAlmacenaEnTanques(String almacenaEnTanques) {
        this.almacenaEnTanques = almacenaEnTanques;
    }

    @Column(name = "NUMERO_TANQUES", nullable = true, length = 2)
    public Integer getNumeroTanques() {
        return numeroTanques;
    }

    public void setNumeroTanques(Integer numeroTanques) {
        this.numeroTanques = numeroTanques;
    }

    @Column(name = "TANQUES_TAPADOS", nullable = true, length = 2)
    public String getTanquesTapados() {
        return tanquesTapados;
    }

    public void setTanquesTapados(String tanquesTapados) {
        this.tanquesTapados = tanquesTapados;
    }

    @Column(name = "TANQUES_CON_ABATE", nullable = true, length = 2)
    public String getTanquesConAbate() {
        return tanquesConAbate;
    }

    public void setTanquesConAbate(String tanquesConAbate) {
        this.tanquesConAbate = tanquesConAbate;
    }

    @Column(name = "ALMACENA_EN_PILAS", nullable = true, length = 2)
    public String getAlmacenaEnPilas() {
        return almacenaEnPilas;
    }

    public void setAlmacenaEnPilas(String almacenaEnPilas) {
        this.almacenaEnPilas = almacenaEnPilas;
    }

    @Column(name = "NUMERO_PILAS", nullable = true, length = 2)
    public Integer getNumeroPilas() {
        return numeroPilas;
    }

    public void setNumeroPilas(Integer numeroPilas) {
        this.numeroPilas = numeroPilas;
    }

    @Column(name = "PILAS_TAPADAS", nullable = true, length = 2)
    public String getPilasTapadas() {
        return pilasTapadas;
    }

    public void setPilasTapadas(String pilasTapadas) {
        this.pilasTapadas = pilasTapadas;
    }

    @Column(name = "PILAS_CON_ABATE", nullable = true, length = 2)
    public String getPilasConAbate() {
        return pilasConAbate;
    }

    public void setPilasConAbate(String pilasConAbate) {
        this.pilasConAbate = pilasConAbate;
    }

    @Column(name = "ALMACENA_EN_OTROSRECIP", nullable = true, length = 2)
    public String getAlmacenaEnOtrosrecip() {
        return almacenaEnOtrosrecip;
    }

    public void setAlmacenaEnOtrosrecip(String almacenaEnOtrosrecip) {
        this.almacenaEnOtrosrecip = almacenaEnOtrosrecip;
    }

    @Column(name = "DESC_OTROS_RECIPIENTES", nullable = true, length = 255)
    public String getDescOtrosRecipientes() {
        return descOtrosRecipientes;
    }

    public void setDescOtrosRecipientes(String descOtrosRecipientes) {
        this.descOtrosRecipientes = descOtrosRecipientes;
    }

    @Column(name = "NUMERO_OTROS_RECIPIENTES", nullable = true, length = 2)
    public Integer getNumeroOtrosRecipientes() {
        return numeroOtrosRecipientes;
    }

    public void setNumeroOtrosRecipientes(Integer numeroOtrosRecipientes) {
        this.numeroOtrosRecipientes = numeroOtrosRecipientes;
    }

    @Column(name = "OTROS_RECIP_TAPADOS", nullable = true, length = 2)
    public String getOtrosRecipTapados() {
        return otrosRecipTapados;
    }

    public void setOtrosRecipTapados(String otrosRecipTapados) {
        this.otrosRecipTapados = otrosRecipTapados;
    }

    @Column(name = "OTROSRECIP_CON_ABATE", nullable = true, length = 2)
    public String getOtrosrecipConAbate() {
        return otrosrecipConAbate;
    }

    public void setOtrosrecipConAbate(String otrosrecipConAbate) {
        this.otrosrecipConAbate = otrosrecipConAbate;
    }

    @Column(name = "UBICACION_LAVANDERO", nullable = true, length = 2)
    public String getUbicacionLavandero() {
        return ubicacionLavandero;
    }

    public void setUbicacionLavandero(String ubicacionLavandero) {
        this.ubicacionLavandero = ubicacionLavandero;
    }

    @Column(name = "TIENE_ABANICO", nullable = true, length = 2)
    public String getTieneAbanico() {
        return tieneAbanico;
    }

    public void setTieneAbanico(String tieneAbanico) {
        this.tieneAbanico = tieneAbanico;
    }

    @Column(name = "TIENE_TELEVISOR", nullable = true, length = 2)
    public String getTieneTelevisor() {
        return tieneTelevisor;
    }

    public void setTieneTelevisor(String tieneTelevisor) {
        this.tieneTelevisor = tieneTelevisor;
    }

    @Column(name = "TIENE_REFRIGERADOR_FREEZER", nullable = true, length = 2)
    public String getTieneRefrigeradorFreezer() {
        return tieneRefrigeradorFreezer;
    }

    public void setTieneRefrigeradorFreezer(String tieneRefrigeradorFreezer) {
        this.tieneRefrigeradorFreezer = tieneRefrigeradorFreezer;
    }

    @Column(name = "TIENE_AIRE_ACONDICIONADO", nullable = true, length = 2)
    public String getTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(String tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    @Column(name = "FUNCIONAMIENTO_AIRE", nullable = true, length = 2)
    public String getFuncionamientoAire() {
        return funcionamientoAire;
    }

    public void setFuncionamientoAire(String funcionamientoAire) {
        this.funcionamientoAire = funcionamientoAire;
    }

    @Column(name = "opcFabCarro", nullable = true, length = 2)
    public String getOpcFabCarro() {
        return opcFabCarro;
    }

    public void setOpcFabCarro(String opcFabVehiv) {
        this.opcFabCarro = opcFabVehiv;
    }

    @Column(name = "yearNow", nullable = true, length = 2)
    public Integer getYearNow() {
        return yearNow;
    }

    public void setYearNow(Integer yearNow) {
        this.yearNow = yearNow;
    }

    @Column(name = "yearFabCarro", nullable = true, length = 2)
    public Integer getYearFabCarro() {
        return yearFabCarro;
    }

    public void setYearFabCarro(Integer yearFabVehic) {
        this.yearFabCarro = yearFabVehic;
    }

    @Column(name = "TIENE_MICROBUS", nullable = true, length = 2)
    public String getTieneMicrobus() {
        return tieneMicrobus;
    }

    public void setTieneMicrobus(String tieneMicrobus) {
        this.tieneMicrobus = tieneMicrobus;
    }

    @Column(name = "TIENE_CAMIONETA", nullable = true, length = 2)
    public String getTieneCamioneta() {
        return tieneCamioneta;
    }

    public void setTieneCamioneta(String tieneCamioneta) {
        this.tieneCamioneta = tieneCamioneta;
    }

    @Column(name = "TIENE_CAMION", nullable = true, length = 2)
    public String getTieneCamion() {
        return tieneCamion;
    }

    public void setTieneCamion(String tieneCamion) {
        this.tieneCamion = tieneCamion;
    }

    @Column(name = "TIENE_OTRO_MEDIO_TRANS", nullable = true, length = 2)
    public String getTieneOtroMedioTrans() {
        return tieneOtroMedioTrans;
    }

    public void setTieneOtroMedioTrans(String tieneOtroMedioTrans) {
        this.tieneOtroMedioTrans = tieneOtroMedioTrans;
    }

    @Column(name = "DESC_OTRO_MEDIO_TRANS", nullable = true, length = 255)
    public String getDescOtroMedioTrans() {
        return descOtroMedioTrans;
    }

    public void setDescOtroMedioTrans(String descOtroMedioTrans) {
        this.descOtroMedioTrans = descOtroMedioTrans;
    }

    @Column(name = "COCINA_CON_LENIA", nullable = true, length = 2)
    public String getCocinaConLenia() {
        return cocinaConLenia;
    }

    public void setCocinaConLenia(String cocinaConLenia) {
        this.cocinaConLenia = cocinaConLenia;
    }

    @Column(name = "UBICACION_COCINA_LENIA", nullable = true, length = 2)
    public String getUbicacionCocinaLenia() {
        return ubicacionCocinaLenia;
    }

    public void setUbicacionCocinaLenia(String ubicacionCocinaLenia) {
        this.ubicacionCocinaLenia = ubicacionCocinaLenia;
    }

    @Column(name = "PERIODICIDAD_COCINA_LENIA", nullable = true, length = 2)
    public String getPeriodicidadCocinaLenia() {
        return periodicidadCocinaLenia;
    }

    public void setPeriodicidadCocinaLenia(String periodicidadCocinaLenia) {
        this.periodicidadCocinaLenia = periodicidadCocinaLenia;
    }

    @Column(name = "NUM_DIARIO_COCINA_LENIA", nullable = true, length = 2)
    public Integer getNumDiarioCocinaLenia() {
        return numDiarioCocinaLenia;
    }

    public void setNumDiarioCocinaLenia(Integer numDiarioCocinaLenia) {
        this.numDiarioCocinaLenia = numDiarioCocinaLenia;
    }

    @Column(name = "NUM_SEMANAL_COCINA_LENIA", nullable = true, length = 2)
    public Integer getNumSemanalCocinaLenia() {
        return numSemanalCocinaLenia;
    }

    public void setNumSemanalCocinaLenia(Integer numSemanalCocinaLenia) {
        this.numSemanalCocinaLenia = numSemanalCocinaLenia;
    }

    @Column(name = "NUM_QUINCENAL_COCINA_LENIA", nullable = true, length = 2)
    public Integer getNumQuincenalCocinaLenia() {
        return numQuincenalCocinaLenia;
    }

    public void setNumQuincenalCocinaLenia(Integer numQuincenalCocinaLenia) {
        this.numQuincenalCocinaLenia = numQuincenalCocinaLenia;
    }

    @Column(name = "NUM_MENSUAL_COCINA_LENIA", nullable = true, length = 2)
    public Integer getNumMensualCocinaLenia() {
        return numMensualCocinaLenia;
    }

    public void setNumMensualCocinaLenia(Integer numMensualCocinaLenia) {
        this.numMensualCocinaLenia = numMensualCocinaLenia;
    }

    @Column(name = "TIENE_GALLINAS", nullable = true, length = 2)
    public String getTieneGallinas() {
        return tieneGallinas;
    }

    public void setTieneGallinas(String tieneGallinas) {
        this.tieneGallinas = tieneGallinas;
    }

    @Column(name = "TIENE_PATOS", nullable = true, length = 2)
    public String getTienePatos() {
        return tienePatos;
    }

    public void setTienePatos(String tienePatos) {
        this.tienePatos = tienePatos;
    }

    @Column(name = "TIENE_PERROS", nullable = true, length = 2)
    public String getTienePerros() {
        return tienePerros;
    }

    public void setTienePerros(String tienePerros) {
        this.tienePerros = tienePerros;
    }

    @Column(name = "TIENE_GATOS", nullable = true, length = 2)
    public String getTieneGatos() {
        return tieneGatos;
    }

    public void setTieneGatos(String tieneGatos) {
        this.tieneGatos = tieneGatos;
    }

    @Column(name = "TIENE_CERDOS", nullable = true, length = 2)
    public String getTieneCerdos() {
        return tieneCerdos;
    }

    public void setTieneCerdos(String tieneCerdos) {
        this.tieneCerdos = tieneCerdos;
    }

    @Column(name = "PERS_FUMA_DENTRO_CASA", nullable = true, length = 2)
    public String getPersFumaDentroCasa() {
        return persFumaDentroCasa;
    }

    public void setPersFumaDentroCasa(String persFumaDentroCasa) {
        this.persFumaDentroCasa = persFumaDentroCasa;
    }

    @Column(name = "MADRE_FUMA", nullable = true, length = 2)
    public String getMadreFuma() {
        return madreFuma;
    }

    public void setMadreFuma(String madreFuma) {
        this.madreFuma = madreFuma;
    }

    @Column(name = "CANT_CIGARRILLOS_MADRE", nullable = true, length = 2)
    public Integer getCantCigarrillosMadre() {
        return cantCigarrillosMadre;
    }

    public void setCantCigarrillosMadre(Integer cantCigarrillosMadre) {
        this.cantCigarrillosMadre = cantCigarrillosMadre;
    }

    @Column(name = "PADRE_FUMA", nullable = true, length = 2)
    public String getPadreFuma() {
        return padreFuma;
    }

    public void setPadreFuma(String padreFuma) {
        this.padreFuma = padreFuma;
    }

    @Column(name = "CANT_CIGARRILLOS_PADRE", nullable = true, length = 2)
    public Integer getCantCigarrillosPadre() {
        return cantCigarrillosPadre;
    }

    public void setCantCigarrillosPadre(Integer cantCigarrillosPadre) {
        this.cantCigarrillosPadre = cantCigarrillosPadre;
    }

    @Column(name = "OTROS_FUMAN", nullable = true, length = 2)
    public String getOtrosFuman() {
        return otrosFuman;
    }

    public void setOtrosFuman(String otrosFuman) {
        this.otrosFuman = otrosFuman;
    }

    @Column(name = "CANT_OTROS_FUMAN", nullable = true, length = 2)
    public Integer getCantOtrosFuman() {
        return cantOtrosFuman;
    }

    public void setCantOtrosFuman(Integer cantOtrosFuman) {
        this.cantOtrosFuman = cantOtrosFuman;
    }

    @Column(name = "CANT_CIGARRILLOS_OTROS", nullable = true, length = 2)
    public Integer getCantCigarrillosOtros() {
        return cantCigarrillosOtros;
    }

    public void setCantCigarrillosOtros(Integer cantCigarrillosOtros) {
        this.cantCigarrillosOtros = cantCigarrillosOtros;
    }

    @Column(name = "servRecolBasura", nullable = true, length = 2)
    public String getServRecolBasura() {
        return servRecolBasura;
    }

    public void setServRecolBasura(String servRecolBasura) {
        this.servRecolBasura = servRecolBasura;
    }

    @Column(name = "frecServRecolBasura", nullable = true, length = 2)
    public Integer getFrecServRecolBasura() {
        return frecServRecolBasura;
    }

    public void setFrecServRecolBasura(Integer frecServRecolBasura) {
        this.frecServRecolBasura = frecServRecolBasura;
    }

    @Column(name = "llantasOtrosContConAgua", nullable = true, length = 2)
    public String getLlantasOtrosContConAgua() {
        return llantasOtrosContConAgua;
    }

    public void setLlantasOtrosContConAgua(String llantasOtrosContConAgua) {
        this.llantasOtrosContConAgua = llantasOtrosContConAgua;
    }

    @Column(name = "opcFabMicrobus", nullable = true, length = 2)
    public String getOpcFabMicrobus() {
        return opcFabMicrobus;
    }

    public void setOpcFabMicrobus(String opcFabMicrobus) {
        this.opcFabMicrobus = opcFabMicrobus;
    }

    @Column(name = "yearFabMicrobus", nullable = true, length = 2)
    public Integer getYearFabMicrobus() {
        return yearFabMicrobus;
    }

    public void setYearFabMicrobus(Integer yearFabMicrobus) {
        this.yearFabMicrobus = yearFabMicrobus;
    }

    @Column(name = "opcFabCamioneta", nullable = true, length = 2)
    public String getOpcFabCamioneta() {
        return opcFabCamioneta;
    }

    public void setOpcFabCamioneta(String opcFabCamioneta) {
        this.opcFabCamioneta = opcFabCamioneta;
    }

    @Column(name = "yearFabCamioneta", nullable = true, length = 2)
    public Integer getYearFabCamioneta() {
        return yearFabCamioneta;
    }

    public void setYearFabCamioneta(Integer yearFabCamioneta) {
        this.yearFabCamioneta = yearFabCamioneta;
    }

    @Column(name = "opcFabCamion", nullable = true, length = 2)
    public String getOpcFabCamion() {
        return opcFabCamion;
    }

    public void setOpcFabCamion(String opcFabCamion) {
        this.opcFabCamion = opcFabCamion;
    }

    @Column(name = "yearFabCamion", nullable = true, length = 2)
    public Integer getYearFabCamion() {
        return yearFabCamion;
    }

    public void setYearFabCamion(Integer yearFabCamion) {
        this.yearFabCamion = yearFabCamion;
    }

    @Column(name = "opcFabOtroMedioTrans", nullable = true, length = 2)
    public String getOpcFabOtroMedioTrans() {
        return opcFabOtroMedioTrans;
    }

    public void setOpcFabOtroMedioTrans(String opcFabOtroMedioTrans) {
        this.opcFabOtroMedioTrans = opcFabOtroMedioTrans;
    }

    @Column(name = "yearFabOtroMedioTrans", nullable = true, length = 2)
    public Integer getYearFabOtroMedioTrans() {
        return yearFabOtroMedioTrans;
    }

    public void setYearFabOtroMedioTrans(Integer yearFabOtroMedioTrans) {
        this.yearFabOtroMedioTrans = yearFabOtroMedioTrans;
    }

	@Column(name = "cambiado_casa", nullable = true, length = 2)
	public String getCambiadoCasa() {
		return cambiadoCasa;
	}

	public void setCambiadoCasa(String cambiadoCasa) {
		this.cambiadoCasa = cambiadoCasa;
	}

	@Column(name = "remodelacion_casa", nullable = true, length = 2)
	public String getRemodelacionCasa() {
		return remodelacionCasa;
	}

	public void setRemodelacionCasa(String remodelacionCasa) {
		this.remodelacionCasa = remodelacionCasa;
	}

	@Column(name = "tiene_vehiculo", nullable = true, length = 2)
	public String getTieneVehiculo() {
		return tieneVehiculo;
	}

	public void setTieneVehiculo(String tieneVehiculo) {
		this.tieneVehiculo = tieneVehiculo;
	}

	@Column(name = "part_seleccionado")
	public Integer getParticipante() {
		return participante;
	}

	public void setParticipante(Integer participante) {
		this.participante = participante;
	}
}
