package ni.org.ics.estudios.service;

import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.domain.relationships.UserStudy;
import ni.org.ics.estudios.users.model.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el objeto UserSistema
 * 
 * @author William Aviles
 * 
 **/

@Service("usuarioService")
@Transactional
public class UsuarioService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los usuarios
	 * 
	 * @return una lista de <code>UserSistema</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<UserSistema> getUsers() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM UserSistema");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los usuarios activos
	 * 
	 * @return una lista de <code>UserSistema</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<UserSistema> getActiveUsers() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM UserSistema us where us.enabled is true");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa un Usuario
	 * 
	 * @return un <code>UserSistema</code>
	 */

	public UserSistema getUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username =:username");
		query.setParameter("username",username);
		UserSistema user = (UserSistema) query.uniqueResult();
		return user;
	}
	
	
	/**
	 * Verifica Credenciales
	 * 
	 * @return boolean
	 */

	public Boolean checkCredential(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username =:username");
		query.setParameter("username",username);
		UserSistema user = (UserSistema) query.uniqueResult();
		return user.getCredentialsNonExpired();
	}

    /**
     * Verifica un User
     *
     * @return boolean
     */

    public Boolean checkUser(String username) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UserSistema u where " +
                "u.username = '" + username + "'");
        UserSistema userPermissions = (UserSistema) query.uniqueResult();
        if (userPermissions !=null){
            return true;
        }
        else{
            return false;
        }
    }
	/**
	 * Guarda un usuario
	 * 
	 * 
	 */
	public void saveUser(UserSistema user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	/**
	 * Regresa los UserAccess
	 * 
	 * @return una lista de <code>UserAccess</code>
	 */

	@SuppressWarnings("unchecked")
	public List<UserAccess> getUserAccess(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserAccess u where " +
				"u.usuario.username = '" + username + "' order by u.loginDate DESC");
		return query.list();
	}
	
	/**
	 * Regresa todos los roles de usuarios
	 * 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Rol> getRoles() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Rol");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Guarda un rol del usuario
	 * 
	 * 
	 */
	public void saveRoleUser(Authority rol) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(rol);
	}
	
	/**
	 * Regresa todos los roles de usuarios
	 * 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Authority> getRolesUsuario(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Authority auth " +
				"where auth.authId.username =:username and auth.pasive ='0'");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un rol del usuario
	 * 
	 * @return un <code>Authority</code>
	 */

	public Authority getRolUsuario(String username, String rol) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Authority auth " +
				"where auth.authId.username =:username and auth.authId.authority =:rol");
		query.setParameter("username",username);
		query.setParameter("rol",rol);
		Authority rolUsuario = (Authority) query.uniqueResult();
		// Retrieve 
		return  rolUsuario;
	}
	
	/**
	 * Regresa todos los roles del usuario
	 * 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Authority> getRolesUsuarioTodos(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Authority auth " +
				"where auth.authId.username =:username");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}

    public List<UserStudy> getEstudiosUsuario(String username) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM UserStudy auth " +
                "where auth.usuario.username =:username and auth.pasive = '0'");
        query.setParameter("username",username);
        // Retrieve all
        return  query.list();
    }

    public List<UserStudy> getAllEstudiosUsuario(String username) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM UserStudy auth " +
                "where auth.usuario.username =:username");
        query.setParameter("username",username);
        // Retrieve all
        return  query.list();
    }

    public List<Estudio> getEstudios(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Estudio where pasive = '0'");
        return query.list();
    }

    public UserStudy getEstudioUsuario(String username, int estudio) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM UserStudy uc " +
                "where uc.usuario.username =:username and uc.estudio.codigo =:estudio");
        query.setParameter("username",username);
        query.setParameter("estudio", estudio);
        return  (UserStudy) query.uniqueResult();
    }

    public void saveUserStudies(UserStudy userStudy) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(userStudy);
    }


    /**
     * Borra rol admin
     *
     *
     */

    public Integer deleteRoleAdmin(String userName) {
        // Retrieve session from Hibernate
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hqlDelete = "delete Authority auth where auth.authId.username = :userName and" +
                " auth.authId.authority = 'ROLE_ADMIN'";
        int deletedEntities = s.createQuery( hqlDelete )
                .setString( "userName", userName )
                .executeUpdate();
        tx.commit();
        s.close();
        return deletedEntities;
    }

    /**
     * Borra rol super
     *
     *
     */

    public Integer deleteRoleSuper(String userName) {
        // Retrieve session from Hibernate
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hqlDelete = "delete Authority auth where auth.authId.username = :userName and" +
                " auth.authId.authority = 'ROLE_SUPER'";
        int deletedEntities = s.createQuery( hqlDelete )
                .setString( "userName", userName )
                .executeUpdate();
        tx.commit();
        s.close();
        return deletedEntities;
    }

    /**
     * Borra rol web
     *
     *
     */

    public Integer deleteRoleWeb(String userName) {
        // Retrieve session from Hibernate
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hqlDelete = "delete Authority auth where auth.authId.username = :userName and" +
                " auth.authId.authority = 'ROLE_WEB'";
        int deletedEntities = s.createQuery( hqlDelete )
                .setString( "userName", userName )
                .executeUpdate();
        tx.commit();
        s.close();
        return deletedEntities;
    }

    /**
     * Borra rol terreno
     *
     *
     */

    public Integer deleteRoleTerreno(String userName) {
        // Retrieve session from Hibernate
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hqlDelete = "delete Authority auth where auth.authId.username = :userName and" +
                " auth.authId.authority = 'ROLE_MOVIL'";
        int deletedEntities = s.createQuery( hqlDelete )
                .setString( "userName", userName )
                .executeUpdate();
        tx.commit();
        s.close();
        return deletedEntities;
    }

    /**
     * Regresa todos los permisos de los usuarios
     *
     * @return una lista de <code>UserPermissions</code>(s)
     */

    @SuppressWarnings("unchecked")
    public List<UserPermissions> getUserPermissions() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM UserPermissions ");
        // Retrieve all
        return  query.list();
    }

    /**
     * Regresa todos los permisos de un usuario
     *
     * @return una lista de <code>UserPermissions</code>(es)
     */

    @SuppressWarnings("unchecked")
    public UserPermissions getUserPermissions(String username) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM UserPermissions perm " +
                "where perm.username =:username");
        query.setParameter("username",username);
        // Retrieve all
        return (UserPermissions)query.uniqueResult();
    }

    /**
     * Registra o actualiza los permisos de un usuario
     * @param permissions
     */
    public void saveUserPermissions(UserPermissions permissions) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(permissions);
    }
}
