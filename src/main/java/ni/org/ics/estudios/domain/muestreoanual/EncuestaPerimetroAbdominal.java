package ni.org.ics.estudios.domain.muestreoanual;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Ing. Santiago Carballo on 30/01/2023.
 *
 */

@Entity
@Table(name = "perimetroabdominal", catalog = "estudios_ics")
public class EncuestaPerimetroAbdominal {
    private EncuestaPerimetroAbdominalId paId;
    private String tomoMedidaSn;
    private String razonNoTomoMedidas;
    private Double pabdominal1;
    private Double pabdominal2;
    private Double pabdominal3;
    private Double difpabdominal;
    private Integer otrorecurso1;
    private Integer otrorecurso2;
    private String estudiosAct;
    private MovilInfo movilInfo;
    private Boolean encuestaValida; //18-01-2024

    @Column(name = "toma_medidas", length = 1)
    public String getTomoMedidaSn() {
        return tomoMedidaSn;
    }


    public void setTomoMedidaSn(String tomoMedidaSn) {
        this.tomoMedidaSn = tomoMedidaSn;
    }

    @Column(name = "razon_no_toma_medidas")
    public String getRazonNoTomoMedidas() {
        return razonNoTomoMedidas;
    }

    public void setRazonNoTomoMedidas(String razonNoTomoMedidas) {
        this.razonNoTomoMedidas = razonNoTomoMedidas;
    }

    @Column(name = "pabdominal1")
    public Double getPabdominal1() {
        return pabdominal1;
    }

    public void setPabdominal1(Double pabdominal1) {
        this.pabdominal1 = pabdominal1;
    }

    @Column(name = "pabdominal2")
    public Double getPabdominal2() {
        return pabdominal2;
    }

    public void setPabdominal2(Double pabdominal2) {
        this.pabdominal2 = pabdominal2;
    }

    @Column(name = "pabdominal3")
    public Double getPabdominal3() {
        return pabdominal3;
    }

    public void setPabdominal3(Double pabdominal3) {
        this.pabdominal3 = pabdominal3;
    }

    @Column(name = "difpabdominal")
    public Double getDifpabdominal() {
        return difpabdominal;
    }

    public void setDifpabdominal(Double difpabdominal) {
        this.difpabdominal = difpabdominal;
    }

    @Column(name = "est_actuales")
    public String getEstudiosAct() {
        return estudiosAct;
    }

    public void setEstudiosAct(String estudiosAct) {
        this.estudiosAct = estudiosAct;
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

    public MovilInfo getMovilInfo() {
        return movilInfo;
    }

    public void setMovilInfo(MovilInfo movilInfo) {
        this.movilInfo = movilInfo;
    }

    @EmbeddedId
    public EncuestaPerimetroAbdominalId getPaId() {
        return paId;
    }

    public void setPaId(EncuestaPerimetroAbdominalId paId) {
        this.paId = paId;
    }

    @JsonIgnore
    @Column(name = "ENCUESTA_VALIDA")
    public Boolean getEncuestaValida() {
        return encuestaValida;
    }

    public void setEncuestaValida(Boolean encuestaValida) {
        this.encuestaValida = encuestaValida;
    }
}
