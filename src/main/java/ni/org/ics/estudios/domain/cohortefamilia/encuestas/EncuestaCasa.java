package ni.org.ics.estudios.domain.cohortefamilia.encuestas;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_encuestas_casa", catalog = "estudios_ics")
public class EncuestaCasa extends BaseMetaData implements Auditable {

    private CasaCohorteFamilia casa;
    private int cantidadCuartos;
    private int cantidadCuartosDormir;
    private Date fechaEncuestas;
    private String encuestador;
    private int hrsSinServicioAgua;
    private String ubicacionLlaveAgua;
    private String llaveCompartida;
    private String almacenaAgua;
    private String almacenaEnBarriles;
    private String almacenaEnTanques;
    private String almacenaEnPilas;
    private String almacenaOtrosRecipientes;
    private String otrosRecipientes;
    private Integer numBarriles;
    private Integer numTanques;
    private Integer numPilas;
    private Integer numOtrosRecipientes;
    private String barrilesTapados;
    private String tanquesTapados;
    private String pilasTapadas;
    private String otrosRecipientesTapados;
    private String barrilesConAbate;
    private String tanquesConAbate;
    private String pilasConAbate;
    private String otrosRecipientesConAbate;
    private String ubicacionLavandero;
    private String materialParedes;
    private String materialPiso;
    private String materialTecho;
    private String otroMaterialParedes;
    private String otroMaterialPiso;
    private String otroMaterialTecho;
    private String casaPropia;
    private String tieneAbanico;
    private String tieneTelevisor;
    private String tieneRefrigerador;
    private String tienAireAcondicionado;
    private String aireAcondicionadoFuncionando;
    private Integer numAbanicos;
    private Integer numTelevisores;
    private Integer numRefrigeradores;
    private String tieneMoto;
    private String tieneCarro;
    private String tienMicrobus;
    private String tieneCamioneta;
    private String tieneCamion;
    private String tieneOtroMedioTransAuto;
    private String otroMedioTransAuto;
    private String cocinaConLenia;
    private String ubicacionCocinaLenia;
    private String periodicidadCocinaLenia;
    private Integer numDiarioCocinaLenia;   //# de veces que cocina
    private Integer numSemanalCocinaLenia;  //# de veces que cocina semanalmente
    private Integer numQuincenalCocinaLenia;    //# de veces que cocina quincenalmente
    private Integer numMensualCocinaLenia;  //# de veces que cocina al mes
    private String tieneAnimales;
    private String tieneGallinas;
    private String tienePatos;
    private String tieneCerdos;
    private Integer cantidadGallinas;
    private Integer cantidadPatos;
    private Integer cantidadCerdos;
    private String gallinasDentroCasa;
    private String patosDentroCasa;
    private String cerdosDentroCasa;
    private String personaFumaDentroCasa;  //Alguna persona que no pertenece al estudio fuma dentro de la casa
    private String madreFuma;
    private String padreFuma;
    private String otrosFuman;
    private Integer cantidadOtrosFuman;
    private Integer cantidadCigarrilosMadre; // diarios
    private Integer cantidadCigarrillosPadre; // diarios
    private Integer cantidadCigarrillosOtros; // diarios

    @Id
    @OneToOne
    @JoinColumn(name = "CODIGO_CASACHF", nullable = false)
    @ForeignKey(name = "FK_CASA_ENCUESTACASA")
    public CasaCohorteFamilia getCasa() {
        return casa;
    }

    public void setCasa(CasaCohorteFamilia casa) {
        this.casa = casa;
    }

    @Column(name = "CANTIDAD_CUARTOS")
    public int getCantidadCuartos() {
        return cantidadCuartos;
    }

    public void setCantidadCuartos(int cantidadCuartos) {
        this.cantidadCuartos = cantidadCuartos;
    }

    @Column(name = "CANT_CUARTOS_DORMIR")
    public int getCantidadCuartosDormir() {
        return cantidadCuartosDormir;
    }

    public void setCantidadCuartosDormir(int cantidadCuartosDormir) {
        this.cantidadCuartosDormir = cantidadCuartosDormir;
    }

    @Column(name = "FECHA_ENCUESTA")
    public Date getFechaEncuestas() {
        return fechaEncuestas;
    }

    public void setFechaEncuestas(Date fechaEncuestas) {
        this.fechaEncuestas = fechaEncuestas;
    }

    @Column(name = "ENCUESTADOR")
    public String getEncuestador() {
        return encuestador;
    }

    public void setEncuestador(String encuestador) {
        this.encuestador = encuestador;
    }

    @Column(name = "HRS_SIN_SRV_AGUA")
    public int getHrsSinServicioAgua() {
        return hrsSinServicioAgua;
    }

    public void setHrsSinServicioAgua(int hrsSinServicioAgua) {
        this.hrsSinServicioAgua = hrsSinServicioAgua;
    }

    @Column(name = "UBICACION_LLAVE_AGUA")
    public String getUbicacionLlaveAgua() {
        return ubicacionLlaveAgua;
    }

    public void setUbicacionLlaveAgua(String ubicacionLlaveAgua) {
        this.ubicacionLlaveAgua = ubicacionLlaveAgua;
    }

    @Column(name = "LLAVEAGUA_COMPARTIDA", length = 50)
    public String getLlaveCompartida() {
        return llaveCompartida;
    }

    public void setLlaveCompartida(String llaveCompartida) {
        this.llaveCompartida = llaveCompartida;
    }

    @Column(name = "ALMACENA_AGUA", length = 1)
    public String getAlmacenaAgua() {
        return almacenaAgua;
    }

    public void setAlmacenaAgua(String almacenaAgua) {
        this.almacenaAgua = almacenaAgua;
    }

    @Column(name = "ALMACENA_EN_BARRILES", length = 1)
    public String getAlmacenaEnBarriles() {
        return almacenaEnBarriles;
    }

    public void setAlmacenaEnBarriles(String utilizaBarriles) {
        this.almacenaEnBarriles = utilizaBarriles;
    }

    @Column(name = "ALMACENA_EN_TANQUES", length = 1)
    public String getAlmacenaEnTanques() {
        return almacenaEnTanques;
    }

    public void setAlmacenaEnTanques(String utilizaTanques) {
        this.almacenaEnTanques = utilizaTanques;
    }

    @Column(name = "ALMACENA_EN_PILAS", length = 1)
    public String getAlmacenaEnPilas() {
        return almacenaEnPilas;
    }

    public void setAlmacenaEnPilas(String utilizaPilas) {
        this.almacenaEnPilas = utilizaPilas;
    }

    @Column(name = "ALMACENA_EN_OTROSRECIP", length = 1)
    public String getAlmacenaOtrosRecipientes() {
        return almacenaOtrosRecipientes;
    }

    public void setAlmacenaOtrosRecipientes(String utilizaOtrosRecipientes) {
        this.almacenaOtrosRecipientes = utilizaOtrosRecipientes;
    }

    @Column(name = "DESC_OTROS_RECIPIENTES")
    public String getOtrosRecipientes() {
        return otrosRecipientes;
    }

    public void setOtrosRecipientes(String otrosRecipientes) {
        this.otrosRecipientes = otrosRecipientes;
    }

    @Column(name = "NUMERO_BARRILES", length = 3)
    public Integer getNumBarriles() {
        return numBarriles;
    }

    public void setNumBarriles(Integer numBarriles) {
        this.numBarriles = numBarriles;
    }

    @Column(name = "NUMERO_TANQUES", length = 3)
    public Integer getNumTanques() {
        return numTanques;
    }

    public void setNumTanques(Integer numTanques) {
        this.numTanques = numTanques;
    }

    @Column(name = "NUMERO_PILAS", length = 3)
    public Integer getNumPilas() {
        return numPilas;
    }

    public void setNumPilas(Integer numPilas) {
        this.numPilas = numPilas;
    }

    @Column(name = "NUMERO_OTROS_RECIPIENTES", length = 3)
    public Integer getNumOtrosRecipientes() {
        return numOtrosRecipientes;
    }

    public void setNumOtrosRecipientes(Integer numOtrosRecipientes) {
        this.numOtrosRecipientes = numOtrosRecipientes;
    }

    @Column(name = "BARRILES_TAPADOS", length = 1)
    public String getBarrilesTapados() {
        return barrilesTapados;
    }

    public void setBarrilesTapados(String barrilesTapados) {
        this.barrilesTapados = barrilesTapados;
    }

    @Column(name = "TANQUES_TAPADOS", length = 1)
    public String getTanquesTapados() {
        return tanquesTapados;
    }

    public void setTanquesTapados(String tanquesTapados) {
        this.tanquesTapados = tanquesTapados;
    }

    @Column(name = "PILAS_TAPADAS", length = 1)
    public String getPilasTapadas() {
        return pilasTapadas;
    }

    public void setPilasTapadas(String pilasTapadas) {
        this.pilasTapadas = pilasTapadas;
    }

    @Column(name = "OTROS_RECIP_TAPADOS", length = 1)
    public String getOtrosRecipientesTapados() {
        return otrosRecipientesTapados;
    }

    public void setOtrosRecipientesTapados(String otrosRecipientesTapados) {
        this.otrosRecipientesTapados = otrosRecipientesTapados;
    }

    @Column(name = "BARRILES_CON_ABATE", length = 1)
    public String getBarrilesConAbate() {
        return barrilesConAbate;
    }

    public void setBarrilesConAbate(String barrilesConAbate) {
        this.barrilesConAbate = barrilesConAbate;
    }

    @Column(name = "TANQUES_CON_ABATE", length = 1)
    public String getTanquesConAbate() {
        return tanquesConAbate;
    }

    public void setTanquesConAbate(String tanquesConAbate) {
        this.tanquesConAbate = tanquesConAbate;
    }

    @Column(name = "PILAS_CON_ABATE", length = 1)
    public String getPilasConAbate() {
        return pilasConAbate;
    }

    public void setPilasConAbate(String pilasConAbate) {
        this.pilasConAbate = pilasConAbate;
    }

    @Column(name = "OTROSRECIP_CON_ABATE", length = 1)
    public String getOtrosRecipientesConAbate() {
        return otrosRecipientesConAbate;
    }

    public void setOtrosRecipientesConAbate(String otrosRecipientesConAbate) {
        this.otrosRecipientesConAbate = otrosRecipientesConAbate;
    }

    @Column(name = "UBICACION_LAVANDERO", length = 50)
    public String getUbicacionLavandero() {
        return ubicacionLavandero;
    }

    public void setUbicacionLavandero(String ubicacionLavandero) {
        this.ubicacionLavandero = ubicacionLavandero;
    }

    @Column(name = "MATERIAL_PAREDES", length = 350)
    public String getMaterialParedes() {
        return materialParedes;
    }

    public void setMaterialParedes(String materialParedes) {
        this.materialParedes = materialParedes;
    }

    @Column(name = "MATERIAL_PISO", length = 250)
    public String getMaterialPiso() {
        return materialPiso;
    }

    public void setMaterialPiso(String materialPiso) {
        this.materialPiso = materialPiso;
    }

    @Column(name = "MATERIAL_TECHO", length = 200)
    public String getMaterialTecho() {
        return materialTecho;
    }

    public void setMaterialTecho(String materialTecho) {
        this.materialTecho = materialTecho;
    }

    @Column(name = "OTRO_MATERIAL_PAREDES")
    public String getOtroMaterialParedes() {
        return otroMaterialParedes;
    }

    public void setOtroMaterialParedes(String otroMaterialParedes) {
        this.otroMaterialParedes = otroMaterialParedes;
    }

    @Column(name = "OTRO_MATERIAL_PISO")
    public String getOtroMaterialPiso() {
        return otroMaterialPiso;
    }

    public void setOtroMaterialPiso(String otroMaterialPiso) {
        this.otroMaterialPiso = otroMaterialPiso;
    }

    @Column(name = "OTRO_MATERIAL_TECHO")
    public String getOtroMaterialTecho() {
        return otroMaterialTecho;
    }

    public void setOtroMaterialTecho(String otroMaterialTecho) {
        this.otroMaterialTecho = otroMaterialTecho;
    }

    @Column(name = "CASA_PROPIA", length = 1)
    public String getCasaPropia() {
        return casaPropia;
    }

    public void setCasaPropia(String casaPropia) {
        this.casaPropia = casaPropia;
    }

    @Column(name = "TIENE_ABANICO", length = 1)
    public String getTieneAbanico() {
        return tieneAbanico;
    }

    public void setTieneAbanico(String tieneAbanico) {
        this.tieneAbanico = tieneAbanico;
    }

    @Column(name = "TIENE_TELEVISOR", length = 1)
    public String getTieneTelevisor() {
        return tieneTelevisor;
    }

    public void setTieneTelevisor(String tieneTelevisor) {
        this.tieneTelevisor = tieneTelevisor;
    }

    @Column(name = "TIENE_REFRIGERADOR_FREEZER", length = 1)
    public String getTieneRefrigerador() {
        return tieneRefrigerador;
    }

    public void setTieneRefrigerador(String tieneRefrigerador) {
        this.tieneRefrigerador = tieneRefrigerador;
    }

    @Column(name = "TIENE_AIRE_ACONDICIONADO", length = 1)
    public String getTienAireAcondicionado() {
        return tienAireAcondicionado;
    }

    public void setTienAireAcondicionado(String tienAireAcondicionado) {
        this.tienAireAcondicionado = tienAireAcondicionado;
    }

    @Column(name = "FUNCIONAMIENTO_AIRE", length = 2)
    public String getAireAcondicionadoFuncionando() {
        return aireAcondicionadoFuncionando;
    }

    public void setAireAcondicionadoFuncionando(String aireAcondicionadoFuncionando) {
        this.aireAcondicionadoFuncionando = aireAcondicionadoFuncionando;
    }

    @Column(name = "CANTIDAD_ABANICOS", length = 2)
    public Integer getNumAbanicos() {
        return numAbanicos;
    }

    public void setNumAbanicos(Integer numAbanicos) {
        this.numAbanicos = numAbanicos;
    }

    @Column(name = "CANTIDAD_TELEVISORES", length = 2)
    public Integer getNumTelevisores() {
        return numTelevisores;
    }

    public void setNumTelevisores(Integer numTelevisores) {
        this.numTelevisores = numTelevisores;
    }

    @Column(name = "CANTIDAD_REFRIGERADORES", length = 2)
    public Integer getNumRefrigeradores() {
        return numRefrigeradores;
    }

    public void setNumRefrigeradores(Integer numRefrigeradores) {
        this.numRefrigeradores = numRefrigeradores;
    }

    @Column(name = "TIENE_MOTO", length = 1)
    public String getTieneMoto() {
        return tieneMoto;
    }

    public void setTieneMoto(String tieneMoto) {
        this.tieneMoto = tieneMoto;
    }

    @Column(name = "TIENE_CARRO", length = 1)
    public String getTieneCarro() {
        return tieneCarro;
    }

    public void setTieneCarro(String tieneCarro) {
        this.tieneCarro = tieneCarro;
    }

    @Column(name = "TIENE_MICROBUS", length = 1)
    public String getTienMicrobus() {
        return tienMicrobus;
    }

    public void setTienMicrobus(String tienMicrobus) {
        this.tienMicrobus = tienMicrobus;
    }

    @Column(name = "TIENE_CAMIONETA", length = 1)
    public String getTieneCamioneta() {
        return tieneCamioneta;
    }

    public void setTieneCamioneta(String tieneCamioneta) {
        this.tieneCamioneta = tieneCamioneta;
    }

    @Column(name = "TIENE_CAMION", length = 1)
    public String getTieneCamion() {
        return tieneCamion;
    }

    public void setTieneCamion(String tieneCamion) {
        this.tieneCamion = tieneCamion;
    }

    @Column(name = "TIENE_OTRO_MEDIO_TRANS", length = 1)
    public String getTieneOtroMedioTransAuto() {
        return tieneOtroMedioTransAuto;
    }

    public void setTieneOtroMedioTransAuto(String tieneOtroMedioTransAuto) {
        this.tieneOtroMedioTransAuto = tieneOtroMedioTransAuto;
    }

    @Column(name = "DESC_OTRO_MEDIO_TRANS")
    public String getOtroMedioTransAuto() {
        return otroMedioTransAuto;
    }

    public void setOtroMedioTransAuto(String otroMedioTransAuto) {
        this.otroMedioTransAuto = otroMedioTransAuto;
    }

    @Column(name = "COCINA_CON_LENIA", length = 1)
    public String getCocinaConLenia() {
        return cocinaConLenia;
    }

    public void setCocinaConLenia(String cocinaConLenia) {
        this.cocinaConLenia = cocinaConLenia;
    }

    @Column(name = "UBICACION_COCINA_LENIA", length = 50)
    public String getUbicacionCocinaLenia() {
        return ubicacionCocinaLenia;
    }

    public void setUbicacionCocinaLenia(String ubicacionCocinaLenia) {
        this.ubicacionCocinaLenia = ubicacionCocinaLenia;
    }

    @Column(name = "PERIODICIDAD_COCINA_LENIA", length = 50)
    public String getPeriodicidadCocinaLenia() {
        return periodicidadCocinaLenia;
    }

    public void setPeriodicidadCocinaLenia(String periodicidadCocinaLenia) {
        this.periodicidadCocinaLenia = periodicidadCocinaLenia;
    }

    @Column(name = "NUM_DIARIO_COCINA_LENIA", length = 2)
    public Integer getNumDiarioCocinaLenia() {
        return numDiarioCocinaLenia;
    }

    public void setNumDiarioCocinaLenia(Integer numDiarioCocinaLenia) {
        this.numDiarioCocinaLenia = numDiarioCocinaLenia;
    }

    @Column(name = "NUM_SEMANAL_COCINA_LENIA", length = 2)
    public Integer getNumSemanalCocinaLenia() {
        return numSemanalCocinaLenia;
    }

    public void setNumSemanalCocinaLenia(Integer numSemanalCocinaLenia) {
        this.numSemanalCocinaLenia = numSemanalCocinaLenia;
    }

    @Column(name = "NUM_QUINCENAL_COCINA_LENIA", length = 2)
    public Integer getNumQuincenalCocinaLenia() {
        return numQuincenalCocinaLenia;
    }

    public void setNumQuincenalCocinaLenia(Integer numQuincenalCocinaLenia) {
        this.numQuincenalCocinaLenia = numQuincenalCocinaLenia;
    }

    @Column(name = "NUM_MENSUAL_COCINA_LENIA", length = 2)
    public Integer getNumMensualCocinaLenia() {
        return numMensualCocinaLenia;
    }

    public void setNumMensualCocinaLenia(Integer numMensualCocinaLenia) {
        this.numMensualCocinaLenia = numMensualCocinaLenia;
    }

    @Column(name = "TIENE_ANIMALES", length = 1)
    public String getTieneAnimales() {
        return tieneAnimales;
    }

    public void setTieneAnimales(String tieneAnimales) {
        this.tieneAnimales = tieneAnimales;
    }

    @Column(name = "TIENE_GALLINAS", length = 1)
    public String getTieneGallinas() {
        return tieneGallinas;
    }

    public void setTieneGallinas(String tieneGallinas) {
        this.tieneGallinas = tieneGallinas;
    }

    @Column(name = "TIENE_PATOS", length = 1)
    public String getTienePatos() {
        return tienePatos;
    }

    public void setTienePatos(String tienePatos) {
        this.tienePatos = tienePatos;
    }

    @Column(name = "TIENE_CERDOS", length = 1)
    public String getTieneCerdos() {
        return tieneCerdos;
    }

    public void setTieneCerdos(String tieneCerdos) {
        this.tieneCerdos = tieneCerdos;
    }

    @Column(name = "CANT_GALLINAS", length = 3)
    public Integer getCantidadGallinas() {
        return cantidadGallinas;
    }

    public void setCantidadGallinas(Integer cantidadGallinas) {
        this.cantidadGallinas = cantidadGallinas;
    }

    @Column(name = "CANT_PATOS", length = 3)
    public Integer getCantidadPatos() {
        return cantidadPatos;
    }

    public void setCantidadPatos(Integer cantidadPatos) {
        this.cantidadPatos = cantidadPatos;
    }

    @Column(name = "CANT_CERDOS", length = 3)
    public Integer getCantidadCerdos() {
        return cantidadCerdos;
    }

    public void setCantidadCerdos(Integer cantidadCerdos) {
        this.cantidadCerdos = cantidadCerdos;
    }

    @Column(name = "GALLINAS_DENTRO_CASA", length = 1)
    public String getGallinasDentroCasa() {
        return gallinasDentroCasa;
    }

    public void setGallinasDentroCasa(String gallinasDentroCasa) {
        this.gallinasDentroCasa = gallinasDentroCasa;
    }

    @Column(name = "PATOS_DENTRO_CASA", length = 1)
    public String getPatosDentroCasa() {
        return patosDentroCasa;
    }

    public void setPatosDentroCasa(String patosDentroCasa) {
        this.patosDentroCasa = patosDentroCasa;
    }

    @Column(name = "CERDOS_DENTRO_CASA", length = 1)
    public String getCerdosDentroCasa() {
        return cerdosDentroCasa;
    }

    public void setCerdosDentroCasa(String cerdosDentroCasa) {
        this.cerdosDentroCasa = cerdosDentroCasa;
    }

    @Column(name = "PERS_FUMA_DENTRO_CASA", length = 1)
    public String getPersonaFumaDentroCasa() {
        return personaFumaDentroCasa;
    }

    public void setPersonaFumaDentroCasa(String personaFumaDentroCasa) {
        this.personaFumaDentroCasa = personaFumaDentroCasa;
    }

    @Column(name = "MADRE_FUMA", length = 1)
    public String getMadreFuma() {
        return madreFuma;
    }

    public void setMadreFuma(String madreFuma) {
        this.madreFuma = madreFuma;
    }

    @Column(name = "PADRE_FUMA", length = 1)
    public String getPadreFuma() {
        return padreFuma;
    }

    public void setPadreFuma(String padreFuma) {
        this.padreFuma = padreFuma;
    }

    @Column(name = "OTROS_FUMAN", length = 1)
    public String getOtrosFuman() {
        return otrosFuman;
    }

    public void setOtrosFuman(String otrosFuman) {
        this.otrosFuman = otrosFuman;
    }

    @Column(name = "CANT_OTROS_FUMAN", length = 3)
    public Integer getCantidadOtrosFuman() {
        return cantidadOtrosFuman;
    }

    public void setCantidadOtrosFuman(Integer cantidadOtrosFuman) {
        this.cantidadOtrosFuman = cantidadOtrosFuman;
    }

    @Column(name = "CANT_CIGARRILLOS_MADRE", length = 3)
    public Integer getCantidadCigarrilosMadre() {
        return cantidadCigarrilosMadre;
    }

    public void setCantidadCigarrilosMadre(Integer cantidadCigarrilosMadre) {
        this.cantidadCigarrilosMadre = cantidadCigarrilosMadre;
    }

    @Column(name = "CANT_CIGARRILLOS_PADRE", length = 3)
    public Integer getCantidadCigarrillosPadre() {
        return cantidadCigarrillosPadre;
    }

    public void setCantidadCigarrillosPadre(Integer cantidadCigarrillosPadre) {
        this.cantidadCigarrillosPadre = cantidadCigarrillosPadre;
    }

    @Column(name = "CANT_CIGARRILLOS_OTROS", length = 3)
    public Integer getCantidadCigarrillosOtros() {
        return cantidadCigarrillosOtros;
    }

    public void setCantidadCigarrillosOtros(Integer cantidadCigarrillosOtros) {
        this.cantidadCigarrillosOtros = cantidadCigarrillosOtros;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaCasa{" + casa.getCodigoCHF() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaCasa)) return false;

        EncuestaCasa that = (EncuestaCasa) o;

        return  (!casa.equals(that.casa));
    }

    @Override
    public int hashCode() {
        return casa.hashCode();
    }
}
