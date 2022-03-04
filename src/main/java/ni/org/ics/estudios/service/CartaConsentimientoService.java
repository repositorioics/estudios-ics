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

    public List<CartaConsentimiento> getCartaConsentimientoByCodParticipanteEstudio(Integer codParticipante, Integer estudio)
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "from CartaConsentimiento where participante.codigo = :codigo ";
        if (estudio>0)  sqlQuery += " and tamizaje.estudio.codigo = :estudio";
        sqlQuery += " order by fechaFirma asc";
        Query query = session.createQuery(sqlQuery);

        query.setParameter("codigo", codParticipante);
        if (estudio>0)  query.setParameter("estudio", estudio);
        return query.list();
    }

    /**
     * Obtiene las cartas de consentimiento para cohorte pediatrica de un participante en el subestudio covid
     * //4=Influenza, 5=U01
     * version de las cartas es 1, y tienen que tener dato en ParteD que es la parte que se anexo para consentir subestudio Covid
     * CP indica que es Cohorte Pediatrica
      * @param codParticipante a obtener cartas
     * @return
     */
    public List<CartaConsentimiento> getCartaConsCovidCPByCodParticipante(Integer codParticipante)
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "from CartaConsentimiento where participante.codigo = :codigo " + " and tamizaje.cohorte = 'CP' and tamizaje.estudio.codigo in (4,5) and version = '1' and aceptaParteD is not null";
        sqlQuery += " order by fechaFirma asc";
        Query query = session.createQuery(sqlQuery);

        query.setParameter("codigo", codParticipante);
        return query.list();
    }

    public List<CartaConsentimiento> getCartaConsentimientoByCodCasaEstudio(String casaChf, Integer estudio)
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "from CartaConsentimiento where casaChf = :casaChf ";
        if (estudio>0)  sqlQuery += " and tamizaje.estudio.codigo = :estudio";
        sqlQuery += " order by fechaFirma asc";
        Query query = session.createQuery(sqlQuery);

        query.setParameter("casaChf", casaChf);
        if (estudio>0)  query.setParameter("estudio", estudio);
        return query.list();
    }


    public CartaConsentimiento getCartaConsentimientoByParticipante(Integer codParticipante)
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "from CartaConsentimiento where participante.codigo = :codigo ";
        Query query = session.createQuery(sqlQuery);

        query.setParameter("codigo", codParticipante);
        return (CartaConsentimiento) query.uniqueResult();
    }
}
