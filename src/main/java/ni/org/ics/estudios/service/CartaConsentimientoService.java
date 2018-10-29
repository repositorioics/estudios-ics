package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.CartaConsentimiento;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/2/2017.
 * V1.0
 */
@Service("cartaConsentimientoService")
@Transactional
public class CartaConsentimientoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<CartaConsentimiento> getCartasConsentimiento()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CartaConsentimiento ");
        return query.list();
    }

    public CartaConsentimiento getCartaConsentimientoByCodigo(String codigo)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CartaConsentimiento where codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (CartaConsentimiento)query.uniqueResult();
    }

    public List<CartaConsentimiento> getCartasConsentimientoByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CartaConsentimiento cc where cc.pasive = '0' and cc.tamizaje.estudio.codigo in ( select estudio.codigo from UserStudy where usuario.username = :username) ");
        query.setParameter("username", username);
        return query.list();
    }

    public void saveOrUpdateCartaConsentimiento(CartaConsentimiento cartaConsentimiento)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cartaConsentimiento);
    }

    public List<CartaConsentimiento> getCartaConsentimientoByCodParticipante(Integer codParticipante)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CartaConsentimiento where participante.codigo = :codigo order by fechaFirma desc");
        query.setParameter("codigo", codParticipante);
        return query.list();
    }
}
