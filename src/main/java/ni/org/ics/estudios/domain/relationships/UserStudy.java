package ni.org.ics.estudios.domain.relationships;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.users.model.UserSistema;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Entity
@Table(name = "usuarios_estudio", catalog = "estudios_ics")
public class UserStudy extends BaseMetaData implements Auditable {

    private UserSistema usuario;
    private Estudio estudio;

    @Id
    @ManyToOne
    @JoinColumn(name="CODIGO_ESTUDIO", insertable = false, updatable = false)
    @ForeignKey(name = "FK_USUEST_ESTUDIOS")
    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="NOMBRE_USUARIO", insertable = false, updatable = false)
    @ForeignKey(name = "FK_USUEST_USUARIOS")
    public UserSistema getUsuario() {
        return usuario;
    }

    public void setUsuario(UserSistema usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "UserStudy{" + usuario.getUsername() +
                ", " + estudio.getCodigo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStudy)) return false;

        UserStudy userStudy = (UserStudy) o;

        if (!estudio.equals(userStudy.estudio)) return false;
        if (!usuario.equals(userStudy.usuario)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = usuario.hashCode();
        result = 31 * result + estudio.hashCode();
        return result;
    }
}
