package ni.org.ics.estudios.service;

import ni.org.ics.estudios.language.MessageResource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Servicio para el objeto UserSistema
 * 
 * @author William Aviles
 * 
 **/

@Service("messageResourceService")
@Transactional
public class MessageResourceService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los mensajes
	 * 
	 * @return una lista de <code>MessageResource</code>(s)
	 */

	public void addCatalogo(){
		Session session = sessionFactory.getCurrentSession();
		MessageResource addT = new MessageResource();
		addT.setMessageKey("civil3");
		addT.setCatKey("3");
		addT.setEnglish("Test english");
		addT.setSpanish("Prueba español");
		session.saveOrUpdate(addT);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageResource> loadAllMessages() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageResource> loadMessages(String parametro) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource mr where mr.messageKey like :parametro or mr.spanish like :parametro or mr.english like :parametro");
		query.setParameter("parametro", '%'+parametro+'%');
		// Retrieve all
		return  query.list();
	}
	/* OBTIENE LA LISTA PARA LLENAR UN SELECT DE LA TABLA MENSAJE */
	@SuppressWarnings("unchecked")
	public List<MessageResource> getCatalogo(String catalogo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource mens where mens.isCat ='0'" +
				" and mens.catRoot =:catalogo and mens.catKey is not null and mens.pasive = '0' order by mens.order");
		query.setParameter("catalogo", catalogo);
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageResource> getCatalogos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource mens where (mens.isCat ='1'" +
				" or mens.catKey is not null) and mens.pasive = '0' order by mens.order");
		// Retrieve all
		return  query.list();
	}
	
	
	public MessageResource getMensaje(String idMensaje) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM MessageResource mens where mens.messageKey =:idMensaje");
		query.setParameter("idMensaje", idMensaje);
		// Retrieve all
		return  (MessageResource) query.uniqueResult();
	}

    public MessageResource getMensaje(String idMensaje, String catalogo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM MessageResource mens where mens.catRoot =:catalogo and mens.messageKey =:idMensaje");
        query.setParameter("catalogo",catalogo);
        query.setParameter("idMensaje", idMensaje);
        // Retrieve all
        return  (MessageResource) query.uniqueResult();
    }

	/**
	 * Guarda un mensaje
	 * 
	 * 
	 */
	public void saveMensaje(MessageResource message) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(message);
	}

    @SuppressWarnings("unchecked")
    public List<MessageResource> loadAllMessagesNoCatalogs() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM MessageResource where isCat = '0' and catKey is null");
        // Retrieve all
        return  query.list();
    }


    /* ocupar este para Mostrar los catalogos */
    public MessageResource getMensajeByCatalogAndCatKey(String catalogo, String catKey) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM MessageResource mens where mens.catRoot =:catalogo and mens.catKey = :catKey");
        query.setParameter("catalogo",catalogo);
        query.setParameter("catKey",catKey);
        return  (MessageResource) query.uniqueResult();
    }

    public List<MessageResource> getMensajeByCatalogAndCatKeys(String catKeys, String catalogo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM MessageResource mens where mens.catRoot =:catalogo and mens.catKey in ('"+catKeys+"')");
        query.setParameter("catalogo",catalogo);
        // Retrieve all
        return  query.list();
    }

    public List<MessageResource> getMensajeEnfCronByTamizaje(String tamizaje, String catalogo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("select mens FROM MessageResource mens, EnfermedadCronica ec inner join ec.tamizaje t " +
                "where mens.catRoot =:catalogo and t.codigo = :tamizaje and ec.enfermedad = mens.catKey and ec.pasive ='0' ");
        query.setParameter("catalogo", catalogo);
        query.setParameter("tamizaje", tamizaje);
        // Retrieve all
        return  query.list();
    }


    /**/

}
