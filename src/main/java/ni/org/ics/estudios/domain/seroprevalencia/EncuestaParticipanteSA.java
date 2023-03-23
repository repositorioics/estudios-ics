package ni.org.ics.estudios.domain.seroprevalencia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 5/25/2017.
 * V1.0
 */
@Entity
@Table(name = "sa_encuestas_participante", catalog = "estudios_ics", uniqueConstraints = @UniqueConstraint(columnNames = {"CODIGO_PARTICIPANTE","FECHA_REGISTRO"}))
public class EncuestaParticipanteSA extends BaseMetaData implements Auditable {

    private String codigo;
    private Participante participante;
    //Aspectos Educacionales
    private String escuchadoZikaSn;
    private String queEsSika;
    private String otroQueEsSika;
    private String transmiteZika;
    private String otroTransmiteZika;
    //Sintomas Previos
    private String sintomas;
    //Diagnostico previo por Arbovirus
    //Diagnostico previo por Zika
    private String tenidoZikaSn;
    private String fechaZika;
    private String sintomasZika;
    private String zikaConfirmadoMedico;
    //Diagnostico previo de Dengue
    private String tenidoDengueSn;
    private String fechaDengue;
    private String dengueConfirmadoMedico;
    //Diagnostico Previo de Chikungunya
    private String tenidoChikSn;
    private String fechaChik;
    private String chikConfirmadoMedico;
    //Vacunacion para fiebre Amarilla (not captured by MINSA vaccine booklet)
    private String vacunaFiebreAmarillaSn;
    private String fechaVacunaFiebreAmar;
    //Transfusion Sanguinea
    private String transfusionSangreSn;
    private String fechaTransfusionSangre;
    //Medidas para el control de vectores
    private String usaRepelentes;
    private String conoceLarvas;
    private String lugaresLarvas;
    private String otrosLugaresLarvas;
    //Aspectos eduacionales
    //Para mujeres y hombres de 16-49 anos
    private String tenidoHijos;
    private String usaPlanificacionFam;
    private String usaCondon;
    private String usaOtroMetodo;
    //MA 2018
    private String sabeZika;
    private String usaRopa;
    private String embarazadaUltAnio;
    private String visitaCemeneterio;
    private String cadaCuantoVisitaCem;
    private String mesesVisitaCementerio;
    private String descOtroMetodo;

    @Id
    @Column(name = "codigo", length = 50, nullable = false)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_ENCUESTAPARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "ESCUCHADO_ZIKA", length = 1)
    public String getEscuchadoZikaSn() {
        return escuchadoZikaSn;
    }

    public void setEscuchadoZikaSn(String escuchadoZikaSn) {
        this.escuchadoZikaSn = escuchadoZikaSn;
    }

    @Column(name = "SABE_ZIKA", length = 50)
    public String getQueEsSika() {
        return queEsSika;
    }

    public void setQueEsSika(String queEsSika) {
        this.queEsSika = queEsSika;
    }

    @Column(name = "OTRO_SABE_ZIKA", length = 255)
    public String getOtroQueEsSika() {
        return otroQueEsSika;
    }

    public void setOtroQueEsSika(String otroQueEsSika) {
        this.otroQueEsSika = otroQueEsSika;
    }

    @Column(name = "COMO_TRANSMITE_ZIKA", length = 50)
    public String getTransmiteZika() {
        return transmiteZika;
    }

    public void setTransmiteZika(String transmiteZika) {
        this.transmiteZika = transmiteZika;
    }

    @Column(name = "OTRO_TRANSMITE_ZIKA", length = 255)
    public String getOtroTransmiteZika() {
        return otroTransmiteZika;
    }

    public void setOtroTransmiteZika(String otroTransmiteZika) {
        this.otroTransmiteZika = otroTransmiteZika;
    }

    @Column(name = "SINTOMAS", length = 50)
    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    @Column(name = "TENIDO_ZIKA", length = 1)
    public String getTenidoZikaSn() {
        return tenidoZikaSn;
    }

    public void setTenidoZikaSn(String tenidoZikaSn) {
        this.tenidoZikaSn = tenidoZikaSn;
    }

    @Column(name = "FECHA_ZIKA", length = 10)
    public String getFechaZika() {
        return fechaZika;
    }

    public void setFechaZika(String fechaZika) {
        this.fechaZika = fechaZika;
    }

    @Column(name = "SINTOMAS_ZIKA", length = 50)
    public String getSintomasZika() {
        return sintomasZika;
    }

    public void setSintomasZika(String sintomasZika) {
        this.sintomasZika = sintomasZika;
    }

    @Column(name = "ZIKA_CONFIRMADO", length = 1)
    public String getZikaConfirmadoMedico() {
        return zikaConfirmadoMedico;
    }

    public void setZikaConfirmadoMedico(String zikaConfirmadoMedico) {
        this.zikaConfirmadoMedico = zikaConfirmadoMedico;
    }

    @Column(name = "TENIDO_DENGUE", length = 1)
    public String getTenidoDengueSn() {
        return tenidoDengueSn;
    }

    public void setTenidoDengueSn(String tenidoDengueSn) {
        this.tenidoDengueSn = tenidoDengueSn;
    }

    @Column(name = "FECHA_DENGUE", length = 10)
    public String getFechaDengue() {
        return fechaDengue;
    }

    public void setFechaDengue(String fechaDengue) {
        this.fechaDengue = fechaDengue;
    }

    @Column(name = "DENGUE_CONFIRMADO", length = 1)
    public String getDengueConfirmadoMedico() {
        return dengueConfirmadoMedico;
    }

    public void setDengueConfirmadoMedico(String dengueConfirmadoMedico) {
        this.dengueConfirmadoMedico = dengueConfirmadoMedico;
    }

    @Column(name = "TENIDO_CHIK", length = 1)
    public String getTenidoChikSn() {
        return tenidoChikSn;
    }

    public void setTenidoChikSn(String tenidoChikSn) {
        this.tenidoChikSn = tenidoChikSn;
    }

    @Column(name = "FECHA_CHIK", length = 10)
    public String getFechaChik() {
        return fechaChik;
    }

    public void setFechaChik(String fechaChik) {
        this.fechaChik = fechaChik;
    }

    @Column(name = "CHIK_CONFIRMADO", length = 1)
    public String getChikConfirmadoMedico() {
        return chikConfirmadoMedico;
    }

    public void setChikConfirmadoMedico(String chikConfirmadoMedico) {
        this.chikConfirmadoMedico = chikConfirmadoMedico;
    }

    @Column(name = "VACUNA_FIEBREA", length = 1)
    public String getVacunaFiebreAmarillaSn() {
        return vacunaFiebreAmarillaSn;
    }

    public void setVacunaFiebreAmarillaSn(String vacunaFiebreAmarillaSn) {
        this.vacunaFiebreAmarillaSn = vacunaFiebreAmarillaSn;
    }

    @Column(name = "FECHA_VACUNA_FIEBREA", length = 10)
    public String getFechaVacunaFiebreAmar() {
        return fechaVacunaFiebreAmar;
    }

    public void setFechaVacunaFiebreAmar(String fechaVacunaFiebreAmar) {
        this.fechaVacunaFiebreAmar = fechaVacunaFiebreAmar;
    }

    @Column(name = "TRANSFUSION_SANGRE", length = 1)
    public String getTransfusionSangreSn() {
        return transfusionSangreSn;
    }

    public void setTransfusionSangreSn(String transfusionSangreSn) {
        this.transfusionSangreSn = transfusionSangreSn;
    }

    @Column(name = "FECHA_TRANSFUSION_SANGRE", length = 10)
    public String getFechaTransfusionSangre() {
        return fechaTransfusionSangre;
    }

    public void setFechaTransfusionSangre(String fechaTransfusionSangre) {
        this.fechaTransfusionSangre = fechaTransfusionSangre;
    }

    @Column(name = "USA_REPELENTES", length = 1)
    public String getUsaRepelentes() {
        return usaRepelentes;
    }

    public void setUsaRepelentes(String usaRepelentes) {
        this.usaRepelentes = usaRepelentes;
    }

    @Column(name = "CONOCE_LARVAS", length = 1)
    public String getConoceLarvas() {
        return conoceLarvas;
    }

    public void setConoceLarvas(String conoceLarvas) {
        this.conoceLarvas = conoceLarvas;
    }

    @Column(name = "LUGARES_LARVAS", length = 50)
    public String getLugaresLarvas() {
        return lugaresLarvas;
    }

    public void setLugaresLarvas(String lugaresLarvas) {
        this.lugaresLarvas = lugaresLarvas;
    }

    @Column(name = "OTROS_LUGARES_LARVAS", length = 255)
    public String getOtrosLugaresLarvas() {
        return otrosLugaresLarvas;
    }

    public void setOtrosLugaresLarvas(String otrosLugaresLarvas) {
        this.otrosLugaresLarvas = otrosLugaresLarvas;
    }

    @Column(name = "TIENE_HIJOS", length = 1)
    public String getTenidoHijos() {
        return tenidoHijos;
    }

    public void setTenidoHijos(String tieneHijos) {
        this.tenidoHijos = tieneHijos;
    }

    @Column(name = "PLANIFICACION_FAM", length = 1)
    public String getUsaPlanificacionFam() {
        return usaPlanificacionFam;
    }

    public void setUsaPlanificacionFam(String usaPlanificacionFam) {
        this.usaPlanificacionFam = usaPlanificacionFam;
    }

    @Column(name = "USA_CONDON", length = 1)
    public String getUsaCondon() {
        return usaCondon;
    }

    public void setUsaCondon(String usaCondon) {
        this.usaCondon = usaCondon;
    }

    @Column(name = "OTRO_METODO", length = 1)
    public String getUsaOtroMetodo() {
        return usaOtroMetodo;
    }

    public void setUsaOtroMetodo(String usaOtroMetodo) {
        this.usaOtroMetodo = usaOtroMetodo;
    }

    @Column(name = "SABE_EFECTOS_ZIKA", length = 1)
    public String getSabeZika() {
        return sabeZika;
    }

    public void setSabeZika(String sabeZika) {
        this.sabeZika = sabeZika;
    }

    @Column(name = "USA_ROPA", length = 1)
    public String getUsaRopa() {
        return usaRopa;
    }

    public void setUsaRopa(String usaRopa) {
        this.usaRopa = usaRopa;
    }

    @Column(name = "EMB_ULTIMO_ANIO", length = 1)
    public String getEmbarazadaUltAnio() {
        return embarazadaUltAnio;
    }

    public void setEmbarazadaUltAnio(String embarazadaUltAnio) {
        this.embarazadaUltAnio = embarazadaUltAnio;
    }

    @Column(name = "VISITA_CEMENTERIO", length = 1)
    public String getVisitaCemeneterio() {
        return visitaCemeneterio;
    }

    public void setVisitaCemeneterio(String visitaCemeneterio) {
        this.visitaCemeneterio = visitaCemeneterio;
    }

    @Column(name = "CADA_CUANTO_VC", length = 5)
    public String getCadaCuantoVisitaCem() {
        return cadaCuantoVisitaCem;
    }

    public void setCadaCuantoVisitaCem(String cadaCuantoVisitaCem) {
        this.cadaCuantoVisitaCem = cadaCuantoVisitaCem;
    }

    @Column(name = "MESES_VC", length = 40)
    public String getMesesVisitaCementerio() {
        return mesesVisitaCementerio;
    }

    public void setMesesVisitaCementerio(String mesesVisitaCementerio) {
        this.mesesVisitaCementerio = mesesVisitaCementerio;
    }

    @Column(name = "DESC_OTRO_METODO", length = 255)
    public String getDescOtroMetodo() {
        return descOtroMetodo;
    }

    public void setDescOtroMetodo(String descOtroMetodo) {
        this.descOtroMetodo = descOtroMetodo;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaParticipanteSA{" + participante.getCodigo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaParticipanteSA)) return false;

        EncuestaParticipanteSA that = (EncuestaParticipanteSA) o;

        return  (!participante.equals(that.participante));
    }

    @Override
    public int hashCode() {
        return participante.hashCode();
    }
}
