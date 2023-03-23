package ni.org.ics.estudios.service;

import ni.org.ics.estudios.users.model.Authority;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Servicio para el objeto Authority
 * 
 * @author William Aviles
 * 
 **/

@Service("roleService")
@Transactional
public class RoleService {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;


	/**
	 * Regresa todos los Datos de los roles
	 * 
	 * @return una lista de <code>Authority</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Authority> getRoles() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Authority");
		// Retrieve all
		return  query.list();
	}	
	
	
	/**
	 * Regresa todos los Datos de los roles de un usuario
	 * 
	 * @return una lista de <code>Authority</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Authority> getRoles(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Authority auth where auth.authId.username = '"+ username + "'");
		// Retrieve all
		return  query.list();
	}	


	/**
	 * Verifica si el usuario tiene rol de admin
	 * 
	 * @return true or false
	 */

	@SuppressWarnings("unchecked")
	public Boolean checRoleAdmin(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Authority auth where auth.authId.username = '"+ username
				+ "'and (auth.authId.authority = 'ROLE_ADMIN' or auth.authId.authority = 'ROLE_SUPER')" );
		List<Authority> auth = query.list();
		if(auth!=null && !auth.isEmpty()){
			return true;
		}
		else{
			return false;
		}

	}
}
