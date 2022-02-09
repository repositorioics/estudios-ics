package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ICS_Inspiron3 on 23/07/2019.
 */
@Entity
@Table(name = "cat_personal", catalog = "estudios_ics")
public class Personal extends BaseMetaData implements Auditable, Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idpersonal;
    private String nombreApellido;


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERSONA_ID")
    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    @Column(name = "NOMBRE_APELLIDO", length = 50, nullable = false)
    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
