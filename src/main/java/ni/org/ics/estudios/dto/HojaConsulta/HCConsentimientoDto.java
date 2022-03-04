package ni.org.ics.estudios.dto.HojaConsulta;

import java.math.BigInteger;
import java.util.Date;
/**
 * Created by miguel on 15/2/2022.
 */
public class HCConsentimientoDto {

    private Date fecha;
    private Integer codigo;
    private BigInteger cons;
    private BigInteger asentChik;
    private BigInteger parteB;
    private BigInteger parteC;
    private BigInteger parteD;
    private BigInteger asentimientoEsc;
    private BigInteger parteE;
    private BigInteger parteF;
    private String tipoPartTrans;
    private String reactivacion;
    private Date ahora;
    private boolean retirado = false;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public BigInteger getCons() {
        return cons;
    }

    public void setCons(BigInteger cons) {
        this.cons = cons;
    }

    public BigInteger getAsentChik() {
        return asentChik;
    }

    public void setAsentChik(BigInteger asentChik) {
        this.asentChik = asentChik;
    }

    public BigInteger getParteB() {
        return parteB;
    }

    public void setParteB(BigInteger parteB) {
        this.parteB = parteB;
    }

    public BigInteger getParteC() {
        return parteC;
    }

    public void setParteC(BigInteger parteC) {
        this.parteC = parteC;
    }

    public BigInteger getParteD() {
        return parteD;
    }

    public void setParteD(BigInteger parteD) {
        this.parteD = parteD;
    }

    public BigInteger getAsentimientoEsc() {
        return asentimientoEsc;
    }

    public void setAsentimientoEsc(BigInteger asentimientoEsc) {
        this.asentimientoEsc = asentimientoEsc;
    }

    public BigInteger getParteE() {
        return parteE;
    }

    public void setParteE(BigInteger parteE) {
        this.parteE = parteE;
    }

    public BigInteger getParteF() {
        return parteF;
    }

    public void setParteF(BigInteger parteF) {
        this.parteF = parteF;
    }

    public String getTipoPartTrans() {
        return tipoPartTrans;
    }

    public void setTipoPartTrans(String tipoPartTrans) {
        this.tipoPartTrans = tipoPartTrans;
    }

    public String getReactivacion() {
        return reactivacion;
    }

    public void setReactivacion(String reactivacion) {
        this.reactivacion = reactivacion;
    }

    public Date getAhora() {
        return ahora;
    }

    public void setAhora(Date ahora) {
        this.ahora = ahora;
    }

    public boolean getRetirado() {
        return retirado;
    }

    public void setRetirado(boolean retirado) {
        this.retirado = retirado;
    }
}
