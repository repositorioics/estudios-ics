package ni.org.ics.estudios.service.corrections;

import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.Participante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PC on 16/11/2022.
 */
@Transactional
@Service("cartaConsentimientoCorrectionService")
public class CartaConsentimientoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /* Obtiene Un Participante por su codigo  */
    public List<CartaConsentimiento>getConsentimientoByCodeParticipante(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CartaConsentimiento c where c.participante.codigo =:codigo order by c.fechaFirma desc ");
        query.setParameter("codigo",codigo);
        return (List<CartaConsentimiento>) query.list();
    }

    public void updateConsentimiento(CartaConsentimiento cartaConsentimiento){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cartaConsentimiento);
    }

    public CartaConsentimiento getByCartaConsentimientoId(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM CartaConsentimiento c where " + "c.codigo =:codigo ");
        query.setParameter("codigo", codigo);
        return (CartaConsentimiento) query.uniqueResult();
    }

    //region BUSCAR NOMBRE Y APELLIDOS TUTOR
    public List<String> getNombre1(String nombre1){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.nombre1Tutor from Participante p where p.nombre1Tutor like :nombre1");
        query.setParameter("nombre1", '%' + nombre1 + '%');
        return query.list();
    }
    public List<String> getNombre2(String nombre2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.nombre2Tutor from Participante p where p.nombre2Tutor like :nombre2");
        query.setParameter("nombre2", '%' + nombre2 + '%');
        return query.list();
    }
    public List<String> getApellido1(String apellido1){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.apellido1Tutor from Participante p where p.apellido1Tutor like :apellido1");
        query.setParameter("apellido1", '%' + apellido1 + '%');
        return query.list();
    }
    public List<String> getApellido2(String apellido2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.apellido2Tutor from Participante p where p.apellido2Tutor like :apellido2");
        query.setParameter("apellido2", '%' + apellido2 + '%');
        return query.list();
    }
    //endregion


}
