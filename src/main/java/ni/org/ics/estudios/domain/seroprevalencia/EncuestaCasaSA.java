package ni.org.ics.estudios.domain.seroprevalencia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Casa;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 5/25/2017.
 * V1.0
 */
@Entity
@Table(name = "sa_encuestas_casa", catalog = "estudios_ics", uniqueConstraints = @UniqueConstraint(columnNames = {"CODIGO_CASA","FECHA_REGISTRO"}))
public class EncuestaCasaSA extends BaseMetaData implements Auditable {

    private String codigo;
    private Casa casa;
    private CasaCohorteFamilia casaChf;
    private String sedazoPuertasVentanas;
    private String compraProdEvitarZancudos;
    private String tienePatioJardin;
    private String utilizaAbate;
    private String fumiga;
    private String cadaCuantoFumiga;

    private String miembroFamConZikaSn;
    private Integer cantMiembrosZika;
    private String fechaDxZika;
    private String relacionFamZika;
    private String otraRelacionFamZika;

    private String miembroFamConDengueSn;
    private Integer cantMiembrosDengue;
    private String fechaDxDengue;
    private String relacionFamDengue;
    private String otraRelacionFamDengue;

    private String miembroFamConChikSn;
    private Integer cantMiembrosChik;
    private String fechaDxChik;
    private String relacionFamChik;
    private String otraRelacionFamChik;

    @Id
    @Column(name = "CODIGO", length = 50, nullable = false)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASA", nullable = false)
    @ForeignKey(name = "FK_CASA_ENCUESTACASASA")
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASACHF", nullable = true)
    @ForeignKey(name = "FK_CASACHF_ENCUESTACASASA")
    public CasaCohorteFamilia getCasaChf() {
        return casaChf;
    }

    public void setCasaChf(CasaCohorteFamilia casaChf) {
        this.casaChf = casaChf;
    }

    @Column(name = "SEDAZOS", length = 1)
    public String getSedazoPuertasVentanas() {
        return sedazoPuertasVentanas;
    }

    public void setSedazoPuertasVentanas(String sedazoPuertasVentanas) {
        this.sedazoPuertasVentanas = sedazoPuertasVentanas;
    }

    @Column(name = "COMPRA_PRODUCTOS", length = 1)
    public String getCompraProdEvitarZancudos() {
        return compraProdEvitarZancudos;
    }

    public void setCompraProdEvitarZancudos(String compraProdEvitarZancudos) {
        this.compraProdEvitarZancudos = compraProdEvitarZancudos;
    }

    @Column(name = "TIENE_PATIO", length = 1)
    public String getTienePatioJardin() {
        return tienePatioJardin;
    }

    public void setTienePatioJardin(String tienePatioJardin) {
        this.tienePatioJardin = tienePatioJardin;
    }

    @Column(name = "UTILIZA_ABATE", length = 1)
    public String getUtilizaAbate() {
        return utilizaAbate;
    }

    public void setUtilizaAbate(String utilizaAbate) {
        this.utilizaAbate = utilizaAbate;
    }

    @Column(name = "FUMIGA", length = 1)
    public String getFumiga() {
        return fumiga;
    }

    public void setFumiga(String fumiga) {
        this.fumiga = fumiga;
    }

    @Column(name = "CUANTO_FUMIGA", length = 5)
    public String getCadaCuantoFumiga() {
        return cadaCuantoFumiga;
    }

    public void setCadaCuantoFumiga(String cadaCuantoFumiga) {
        this.cadaCuantoFumiga = cadaCuantoFumiga;
    }

    @Column(name = "MIEMBRO_FAM_ZIKA", length = 1)
    public String getMiembroFamConZikaSn() {
        return miembroFamConZikaSn;
    }

    public void setMiembroFamConZikaSn(String miembroFamConZikaSn) {
        this.miembroFamConZikaSn = miembroFamConZikaSn;
    }

    @Column(name = "CANT_MIEMBROS_ZIKA")
    public Integer getCantMiembrosZika() {
        return cantMiembrosZika;
    }

    public void setCantMiembrosZika(Integer cantMiembrosZika) {
        this.cantMiembrosZika = cantMiembrosZika;
    }

    @Column(name = "FECHA_DIAG_ZIKA", length = 10)
    public String getFechaDxZika() {
        return fechaDxZika;
    }

    public void setFechaDxZika(String fechaDxZika) {
        this.fechaDxZika = fechaDxZika;
    }

    @Column(name = "RELACION_FAM_ZIKA", length = 50)
    public String getRelacionFamZika() {
        return relacionFamZika;
    }

    public void setRelacionFamZika(String relacionFamZika) {
        this.relacionFamZika = relacionFamZika;
    }

    @Column(name = "OTRA_RELACION_FAM_ZIKA", length = 255)
    public String getOtraRelacionFamZika() {
        return otraRelacionFamZika;
    }

    public void setOtraRelacionFamZika(String otraRelacionFamZika) {
        this.otraRelacionFamZika = otraRelacionFamZika;
    }

    @Column(name = "MIEMBRO_FAM_DENGUE", length = 1)
    public String getMiembroFamConDengueSn() {
        return miembroFamConDengueSn;
    }

    public void setMiembroFamConDengueSn(String miembroFamConDengueSn) {
        this.miembroFamConDengueSn = miembroFamConDengueSn;
    }

    @Column(name = "CANT_MIEMBROS_DENGUE")
    public Integer getCantMiembrosDengue() {
        return cantMiembrosDengue;
    }

    public void setCantMiembrosDengue(Integer cantMiembrosDengue) {
        this.cantMiembrosDengue = cantMiembrosDengue;
    }

    @Column(name = "FECHA_DIAG_DENGUE", length = 10)
    public String getFechaDxDengue() {
        return fechaDxDengue;
    }

    public void setFechaDxDengue(String fechaDxDengue) {
        this.fechaDxDengue = fechaDxDengue;
    }

    @Column(name = "RELACION_FAM_DENGUE", length = 50)
    public String getRelacionFamDengue() {
        return relacionFamDengue;
    }

    @Column(name = "OTRA_RELACION_FAM_DENGUE", length = 255)
    public String getOtraRelacionFamDengue() {
        return otraRelacionFamDengue;
    }

    public void setOtraRelacionFamDengue(String otraRelacionFamDengue) {
        this.otraRelacionFamDengue = otraRelacionFamDengue;
    }

    public void setRelacionFamDengue(String relacionFamDengue) {
        this.relacionFamDengue = relacionFamDengue;
    }

    @Column(name = "MIEMBRO_FAM_CHIK", length = 1)
    public String getMiembroFamConChikSn() {
        return miembroFamConChikSn;
    }

    public void setMiembroFamConChikSn(String miembroFamConChikSn) {
        this.miembroFamConChikSn = miembroFamConChikSn;
    }

    @Column(name = "CANT_MIEMBROS_CHIK")
    public Integer getCantMiembrosChik() {
        return cantMiembrosChik;
    }

    public void setCantMiembrosChik(Integer cantMiembrosChik) {
        this.cantMiembrosChik = cantMiembrosChik;
    }

    @Column(name = "FECHA_DIAG_CHIK", length = 10)
    public String getFechaDxChik() {
        return fechaDxChik;
    }

    public void setFechaDxChik(String fechaDxChik) {
        this.fechaDxChik = fechaDxChik;
    }

    @Column(name = "RELACION_FAM_CHIK", length = 50)
    public String getRelacionFamChik() {
        return relacionFamChik;
    }

    public void setRelacionFamChik(String relacionFamChik) {
        this.relacionFamChik = relacionFamChik;
    }

    @Column(name = "OTRA_RELACION_FAM_CHIK", length = 255)
    public String getOtraRelacionFamChik() {
        return otraRelacionFamChik;
    }

    public void setOtraRelacionFamChik(String otraRelacionFamChik) {
        this.otraRelacionFamChik = otraRelacionFamChik;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaCasaSA{" + casa.getCodigo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaCasaSA)) return false;

        EncuestaCasaSA that = (EncuestaCasaSA) o;

        if (!casa.equals(that.getCasa())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return casa.hashCode();
    }
}
