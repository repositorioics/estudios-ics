package ni.org.ics.estudios.domain.entomologia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by miguel on 15/8/2022.
 */
@Entity
@Table(name = "ento_cuestionario_hogar", catalog = "estudios_ics")
public class CuestionarioHogar  extends BaseMetaData implements Auditable {

    private String codigoEncuesta;

    //00.	Código de la casa
    private int codigoCasa;

    //01.	¿Quién está contestando éste cuestionario?
    private String quienContesta;
    private String quienContestaOtro;

    //02.	Me podría decir ¿Qué edad tiene usted?
    private String edadContest;

    //03.	¿Cuál es su último año aprobado?
    private String escolaridadContesta;

    //04.	¿Cuánto tiempo tienen de vivir en este barrio?
    private String tiempoVivirBarrio;

    //05.	 ¿Cuántas personas viven en esta casa?
    private int cuantasPersonasViven;

    //06.	¿Qué edad tienen las niñas y mujeres que viven en esta casa, me    puede decir sus edades de menor a mayor?
    private String edadesFemenino;

    //07.	¿Qué edad tienen los niños y hombres que viven en esta casa, me puede decir sus edades de menor a mayor?
    private String edadesMasculino;

    //08.	De todas las personas que me mencionó, ¿Alguna de ellas usó mosquitero para dormir el día de ayer?
    private String usaronMosquitero;
    private String quienesUsaronMosquitero;

    //09.	De todas las personas que me mencionó, ¿Alguna de ellas usó repelente en su piel, para protegerse de los zancudos el día de ayer?
    private String usaronRepelente;
    private String quienesUsaronRepelente;

    //10.	¿Conoce Usted las larvas o clavitos de los zancudos?
    private String conoceLarvas;

    //11.	¿Alguien les ha visitado para enseñarles como buscar y eliminar las larvas o clavitos de los zancudos?
    private String alguienVisEliminarLarvas;

    //12.	¿Quién?
    private String quienVisEliminarLarvas;
    private String quienVisEliminarLarvasOtro;

    //13. ¿Alguien de esta casa dedica tiempo para buscar y eliminar criaderos de zancudos aquí en su casa?
    private String alguienDedicaElimLarvasCasa;

    //14.	¿Quién?
    private String quienDedicaElimLarvasCasa;

    //15. ¿Cada cuánto tiempo buscan y eliminan criaderos de zancudos aquí en su casa?
    private String tiempoElimanCridaros;

    //16. ¿Hay bastante zancudos aquí en su casa?
    private String hayBastanteZancudos;

    //17.	¿Qué hace falta en esta casa para evitar los criaderos de zancudos?
    private String queFaltaCasaEvitarZancudos;
    private String queFaltaCasaEvitarZancudosOtros;

    //18.	El mes pasado, gastaron dinero en compra de productos para evitar los Zancudos?
    private String gastaronDineroProductos;

    //19.	¿Qué productos compraron?
    private String queProductosCompraron;
    private String queProductosCompraronOtros;

    //20.	¿Cuánto gastaron?
    private String cuantoGastaron;

    //21	¿Cuándo fue la última vez que el MINSA visitó su casa para aplicar BTI en sus recipientes con agua?
    private String ultimaVisitaMinsaBTI;

    //22	¿Cuándo fue la última vez que el MINSA fumigó su casa?
    private String ultimaVezMinsaFumigo;

    //23 ¿Qué tanto riesgo hay en su casa de enfermar por el virus del dengue?
    private String riesgoCasaDengue;

    //24.	¿Hay problemas con el abastecimiento de agua en este barrio?
    private String problemasAbastecimiento;

    //25.	¿Cada cuánto se les va el agua?
    private String cadaCuantoVaAgua;
    private String cadaCuantoVaAguaOtro;

    //26.	¿Cuantas horas al día se les va?
    private int horasSinAguaDia;

    //27.	¿Qué hacen con la basura del hogar?
    private String queHacenBasuraHogar;
    private String queHacenBasuraHogarOtro;

    //28.	¿Qué tanto riesgo hay en este barrio de enfermar por el virus del dengue?
    private String riesgoBarrioDengue;

    //29.	¿En los últimos tres meses, en este barrio han realizado acciones para eliminar criaderos de zancudos del barrio?
    private String accionesCriaderosBarrio;

    //30.	¿Qué acciones realizaron?
    private String queAcciones;
    private String queAccionesOtro;

    //31.	Alguien de la casa participó en esa actividad?
    private String alguienParticipo;

    //32 Quién?
    private String quienParticipo;

    //33.	¿Cuál es el mayor criadero de Zancudos de este barrio?
    private String mayorCriaderoBarrio;

    @Id
    @Column(name = "codigo_cuestionario", nullable = false, length = 36)
    public String getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(String codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    @Column(name = "codigo_casa", nullable = false)
    public int getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(int codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    @Column(name = "quien_contesta", nullable = false, length = 4)
    public String getQuienContesta() {
        return quienContesta;
    }

    public void setQuienContesta(String quienContesta) {
        this.quienContesta = quienContesta;
    }

    @Column(name = "quien_contesta_otro", length = 255)
    public String getQuienContestaOtro() {
        return quienContestaOtro;
    }

    public void setQuienContestaOtro(String quienContestaOtro) {
        this.quienContestaOtro = quienContestaOtro;
    }

    @Column(name = "quien_contesta_edad", nullable = false, length = 8)
    public String getEdadContest() {
        return edadContest;
    }

    public void setEdadContest(String edadContest) {
        this.edadContest = edadContest;
    }

    @Column(name = "quien_contesta_escolaridad", nullable = false, length = 4)
    public String getEscolaridadContesta() {
        return escolaridadContesta;
    }

    public void setEscolaridadContesta(String escolaridadContesta) {
        this.escolaridadContesta = escolaridadContesta;
    }

    @Column(name = "tiempo_vivir_barrio", nullable = false, length = 4)
    public String getTiempoVivirBarrio() {
        return tiempoVivirBarrio;
    }

    public void setTiempoVivirBarrio(String tiempoVivirBarrio) {
        this.tiempoVivirBarrio = tiempoVivirBarrio;
    }

    @Column(name = "cuantas_personas_viven", nullable = false)
    public int getCuantasPersonasViven() {
        return cuantasPersonasViven;
    }

    public void setCuantasPersonasViven(int cuantasPersonasViven) {
        this.cuantasPersonasViven = cuantasPersonasViven;
    }

    @Column(name = "edades_femenino", nullable = false, length = 64)
    public String getEdadesFemenino() {
        return edadesFemenino;
    }

    public void setEdadesFemenino(String edadesFemenino) {
        this.edadesFemenino = edadesFemenino;
    }

    @Column(name = "edades_masculino", nullable = false, length = 64)
    public String getEdadesMasculino() {
        return edadesMasculino;
    }

    public void setEdadesMasculino(String edadesMasculio) {
        this.edadesMasculino = edadesMasculio;
    }

    @Column(name = "usaron_mosquitero", nullable = false, length = 4)
    public String getUsaronMosquitero() {
        return usaronMosquitero;
    }

    public void setUsaronMosquitero(String usaronMosqutero) {
        this.usaronMosquitero = usaronMosqutero;
    }

    @Column(name = "quienes_usaron_mosquitero", nullable = true, length = 128)
    public String getQuienesUsaronMosquitero() {
        return quienesUsaronMosquitero;
    }

    public void setQuienesUsaronMosquitero(String quienesUsaronMosquitero) {
        this.quienesUsaronMosquitero = quienesUsaronMosquitero;
    }

    @Column(name = "usaron_repelente", nullable = false, length = 4)
    public String getUsaronRepelente() {
        return usaronRepelente;
    }

    public void setUsaronRepelente(String usaronRepelente) {
        this.usaronRepelente = usaronRepelente;
    }

    @Column(name = "quienes_usaron_repelente", nullable = true, length = 128)
    public String getQuienesUsaronRepelente() {
        return quienesUsaronRepelente;
    }

    public void setQuienesUsaronRepelente(String quienesUsaronRepelente) {
        this.quienesUsaronRepelente = quienesUsaronRepelente;
    }

    @Column(name = "conoce_larvas", nullable = false, length = 4)
    public String getConoceLarvas() {
        return conoceLarvas;
    }

    public void setConoceLarvas(String conoceLarvas) {
        this.conoceLarvas = conoceLarvas;
    }

    @Column(name = "alguien_vis_elim_larvas", nullable = false, length = 4)
    public String getAlguienVisEliminarLarvas() {
        return alguienVisEliminarLarvas;
    }

    public void setAlguienVisEliminarLarvas(String alguienVisEliminarLarvas) {
        this.alguienVisEliminarLarvas = alguienVisEliminarLarvas;
    }

    @Column(name = "quien_vis_elim_larvas", nullable = true, length = 4)
    public String getQuienVisEliminarLarvas() {
        return quienVisEliminarLarvas;
    }

    public void setQuienVisEliminarLarvas(String quienVisEliminarLarvas) {
        this.quienVisEliminarLarvas = quienVisEliminarLarvas;
    }

    @Column(name = "quien_vis_elim_larvas_otro", nullable = true, length = 255)
    public String getQuienVisEliminarLarvasOtro() {
        return quienVisEliminarLarvasOtro;
    }

    public void setQuienVisEliminarLarvasOtro(String quienVisEliminarLarvasOtro) {
        this.quienVisEliminarLarvasOtro = quienVisEliminarLarvasOtro;
    }

    @Column(name = "alguien_dedica_elim_larvas", nullable = false, length = 4)
    public String getAlguienDedicaElimLarvasCasa() {
        return alguienDedicaElimLarvasCasa;
    }

    public void setAlguienDedicaElimLarvasCasa(String alguienDedicaElimLarvasCasa) {
        this.alguienDedicaElimLarvasCasa = alguienDedicaElimLarvasCasa;
    }

    @Column(name = "quien_dedica_elim_larvas", nullable = true, length = 128)
    public String getQuienDedicaElimLarvasCasa() {
        return quienDedicaElimLarvasCasa;
    }

    public void setQuienDedicaElimLarvasCasa(String quienDedicaElimLarvasCasa) {
        this.quienDedicaElimLarvasCasa = quienDedicaElimLarvasCasa;
    }

    @Column(name = "tiempo_eliminan_criaderos", nullable = true, length = 4)
    public String getTiempoElimanCridaros() {
        return tiempoElimanCridaros;
    }

    public void setTiempoElimanCridaros(String tiempoElimanCridaros) {
        this.tiempoElimanCridaros = tiempoElimanCridaros;
    }

    @Column(name = "hay_bastante_zancudos", nullable = false, length = 4)
    public String getHayBastanteZancudos() {
        return hayBastanteZancudos;
    }

    public void setHayBastanteZancudos(String hayBastanteZancudos) {
        this.hayBastanteZancudos = hayBastanteZancudos;
    }

    @Column(name = "falta_evitar_zancudos", nullable = false, length = 64)
    public String getQueFaltaCasaEvitarZancudos() {
        return queFaltaCasaEvitarZancudos;
    }

    public void setQueFaltaCasaEvitarZancudos(String queFaltaCasaEvitarZancudos) {
        this.queFaltaCasaEvitarZancudos = queFaltaCasaEvitarZancudos;
    }

    @Column(name = "falta_evitar_zancudos_otros", nullable = true, length = 255)
    public String getQueFaltaCasaEvitarZancudosOtros() {
        return queFaltaCasaEvitarZancudosOtros;
    }

    public void setQueFaltaCasaEvitarZancudosOtros(String queFaltaCasaEvitarZancudosOtros) {
        this.queFaltaCasaEvitarZancudosOtros = queFaltaCasaEvitarZancudosOtros;
    }

    @Column(name = "gastaron_dinero_productos", nullable = false, length = 4)
    public String getGastaronDineroProductos() {
        return gastaronDineroProductos;
    }

    public void setGastaronDineroProductos(String gastaronDineroProductos) {
        this.gastaronDineroProductos = gastaronDineroProductos;
    }

    @Column(name = "que_productos_compraron", nullable = true, length = 64)
    public String getQueProductosCompraron() {
        return queProductosCompraron;
    }

    public void setQueProductosCompraron(String queProductosCompraron) {
        this.queProductosCompraron = queProductosCompraron;
    }

    @Column(name = "que_productos_compraron_otros", nullable = true, length = 255)
    public String getQueProductosCompraronOtros() {
        return queProductosCompraronOtros;
    }

    public void setQueProductosCompraronOtros(String queProductosCompraronOtros) {
        this.queProductosCompraronOtros = queProductosCompraronOtros;
    }

    @Column(name = "cuanto_gastaron_productos", nullable = true, length = 4)
    public String getCuantoGastaron() {
        return cuantoGastaron;
    }

    public void setCuantoGastaron(String cuantoGastaron) {
        this.cuantoGastaron = cuantoGastaron;
    }

    @Column(name = "ultima_vez_minsa_bti", nullable = false, length = 4)
    public String getUltimaVisitaMinsaBTI() {
        return ultimaVisitaMinsaBTI;
    }

    public void setUltimaVisitaMinsaBTI(String ultimaVisitaMinsaBTI) {
        this.ultimaVisitaMinsaBTI = ultimaVisitaMinsaBTI;
    }

    @Column(name = "ultima_vez_minsa_fumigo", nullable = false, length = 4)
    public String getUltimaVezMinsaFumigo() {
        return ultimaVezMinsaFumigo;
    }

    public void setUltimaVezMinsaFumigo(String ultimaVezMinsaFumigo) {
        this.ultimaVezMinsaFumigo = ultimaVezMinsaFumigo;
    }

    @Column(name = "riesgo_enfermar_dengue_casa", nullable = false, length = 4)
    public String getRiesgoCasaDengue() {
        return riesgoCasaDengue;
    }

    public void setRiesgoCasaDengue(String riesgoCasaDengue) {
        this.riesgoCasaDengue = riesgoCasaDengue;
    }

    @Column(name = "hay_problema_agua", nullable = false, length = 4)
    public String getProblemasAbastecimiento() {
        return problemasAbastecimiento;
    }

    public void setProblemasAbastecimiento(String problemasAbastecimiento) {
        this.problemasAbastecimiento = problemasAbastecimiento;
    }

    @Column(name = "cada_cuanto_va_agua", nullable = true, length = 4)
    public String getCadaCuantoVaAgua() {
        return cadaCuantoVaAgua;
    }

    public void setCadaCuantoVaAgua(String cadaCuantoVaAgua) {
        this.cadaCuantoVaAgua = cadaCuantoVaAgua;
    }

    @Column(name = "cada_cuanto_va_agua_otro", nullable = true, length = 255)
    public String getCadaCuantoVaAguaOtro() {
        return cadaCuantoVaAguaOtro;
    }

    public void setCadaCuantoVaAguaOtro(String cadaCuantoVaAguaOtro) {
        this.cadaCuantoVaAguaOtro = cadaCuantoVaAguaOtro;
    }

    @Column(name = "horas_sin_agua", nullable = true)
    public int getHorasSinAguaDia() {
        return horasSinAguaDia;
    }

    public void setHorasSinAguaDia(int horasSinAguaDia) {
        this.horasSinAguaDia = horasSinAguaDia;
    }

    @Column(name = "que_hacen_basura", nullable = false, length = 4)
    public String getQueHacenBasuraHogar() {
        return queHacenBasuraHogar;
    }

    public void setQueHacenBasuraHogar(String queHacenBasuraHogar) {
        this.queHacenBasuraHogar = queHacenBasuraHogar;
    }

    @Column(name = "que_hacen_basura_otro", nullable = true, length = 255)
    public String getQueHacenBasuraHogarOtro() {
        return queHacenBasuraHogarOtro;
    }

    public void setQueHacenBasuraHogarOtro(String queHacenBasuraHogarOtro) {
        this.queHacenBasuraHogarOtro = queHacenBasuraHogarOtro;
    }

    @Column(name = "riesgo_enfermar_dengue_barrio", nullable = false, length = 4)
    public String getRiesgoBarrioDengue() {
        return riesgoBarrioDengue;
    }

    public void setRiesgoBarrioDengue(String riesgoBarrioDengue) {
        this.riesgoBarrioDengue = riesgoBarrioDengue;
    }

    @Column(name = "acciones_barrio_elim_zancudos", nullable = false, length = 4)
    public String getAccionesCriaderosBarrio() {
        return accionesCriaderosBarrio;
    }

    public void setAccionesCriaderosBarrio(String accionesCriaderosBarrio) {
        this.accionesCriaderosBarrio = accionesCriaderosBarrio;
    }

    @Column(name = "que_acciones", nullable = true, length = 16)
    public String getQueAcciones() {
        return queAcciones;
    }

    public void setQueAcciones(String queAcciones) {
        this.queAcciones = queAcciones;
    }

    @Column(name = "que_acciones_otro", nullable = true, length = 255)
    public String getQueAccionesOtro() {
        return queAccionesOtro;
    }

    public void setQueAccionesOtro(String queAccionesOtro) {
        this.queAccionesOtro = queAccionesOtro;
    }

    @Column(name = "alguien_participo_acciones", nullable = true, length = 4)
    public String getAlguienParticipo() {
        return alguienParticipo;
    }

    public void setAlguienParticipo(String alguienParticipo) {
        this.alguienParticipo = alguienParticipo;
    }

    @Column(name = "quien_participo_acciones", nullable = true, length = 128)
    public String getQuienParticipo() {
        return quienParticipo;
    }

    public void setQuienParticipo(String quienParticipo) {
        this.quienParticipo = quienParticipo;
    }

    @Column(name = "mayor_criadero_barrio", nullable = false, length = 255)
    public String getMayorCriaderoBarrio() {
        return mayorCriaderoBarrio;
    }

    public void setMayorCriaderoBarrio(String mayorCreaderoBarrio) {
        this.mayorCriaderoBarrio = mayorCreaderoBarrio;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "codigoEncuesta='" + codigoEncuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuestionarioHogar)) return false;

        CuestionarioHogar that = (CuestionarioHogar) o;

        if (!codigoEncuesta.equals(that.codigoEncuesta)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigoEncuesta.hashCode();
    }
}
