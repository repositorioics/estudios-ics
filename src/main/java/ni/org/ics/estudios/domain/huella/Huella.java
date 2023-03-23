package ni.org.ics.estudios.domain.huella;

import org.hibernate.type.LobType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 13/11/2019.
 * V1.0
 */
@Entity
@Table(name = "huellas_dat", catalog = "huella_ics")
public class Huella {

    private String codigo;
    private byte[] objeto;
    private Date fechaRegistro;
    private char estado;

    @Id
    @Column(name = "codigo")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "objeto", columnDefinition="longblob")
    public byte[] getObjeto() {
        return objeto;
    }

    public void setObjeto(byte[] objeto) {
        this.objeto = objeto;
    }

    @Column(name = "fechaRegistro")
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Column(name = "estado")
    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

}
