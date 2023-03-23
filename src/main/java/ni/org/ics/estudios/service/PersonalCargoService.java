package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.catalogs.Cargo;
import ni.org.ics.estudios.domain.catalogs.Carta;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Personal_Cargo;
import ni.org.ics.estudios.domain.relationships.UserStudy;
import ni.org.ics.estudios.users.model.Authority;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ICS on 19/11/2021.
 */
@Service("personalCargoService")
@Transactional
public class PersonalCargoService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;


    // Obtener las personas
    public List<Personal> getAllPersonals(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p order by p.nombreApellido asc");
        return query.list();
    }


    //region todo PERSONAL_CARGO


    public List<Personal_Cargo> getEstudiosUsuario(Integer username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Personal_Cargo auth " +
                "where auth.personal.idpersonal =:username");
        query.setParameter("username",username);
        return  query.list();
    }

    public boolean deleteAllCargos(int idpersonal){
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("DELETE FROM Personal_Cargo pc where pc.personal.idpersonal=:idpersonal");
            query.setParameter("idpersonal", idpersonal);
            query.executeUpdate();
            return true;
        }catch (Exception ex){
            return  false;
        }
    }


    public List<Cargo> getAllCargos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cargo c where c.pasive='0' order by c.idcargo asc");
        return query.list();
    }

    //Obtener personal por ID
    public Personal getPersonaById(Integer codigoPersonal){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p where p.idpersonal=:codigoPersonal");
        query.setParameter("codigoPersonal",codigoPersonal);
        return (Personal) query.uniqueResult();
    }

    //Obtener el Cargo por ID
    public Cargo getCargoById(Integer cargoId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cargo c where c.idcargo=:cargoId and c.pasive='0'");
        query.setParameter("cargoId",cargoId);
        return (Cargo) query.uniqueResult();
    }



    public int Desactivarcargo(Integer idPersona){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Personal_Cargo pc set pc.pasive='1' where pc.personal.idpersonal=:idPersona");
        query.setParameter("idPersona",idPersona);
        int result = query.executeUpdate();
        return result;
    }
     public int ActivarCargo(Integer idPersona){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Personal_Cargo pc set pc.pasive='0' where pc.personal.idpersonal=:idPersona");
        query.setParameter("idPersona",idPersona);
        int result = query.executeUpdate();
        return result;
     }

    public void saveOrUpdatePersona(Personal persona){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(persona);
    }

    public void saveOrUpdate(Personal_Cargo personCargo){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(personCargo);
    }


    @SuppressWarnings("unchecked")
    public List<Personal_Cargo> getCargosOfPersonById(Integer idPersona) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Personal_Cargo pc " +
                "where pc.personal.codigo =:idPersona");
        query.setParameter("idPersona",idPersona);
        return  query.list();
    }

    //Listado Personal Cargo
    public List<Personal_Cargo> getPersonalCargoList(){
        Session session = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from Personal_Cargo");
        return  query.list();
    }

    //Listado Personal Cargo
    public List<Personal_Cargo> getPersonalCargoListById(int idperson){
        Session session = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from Personal_Cargo pc where pc.personal.idpersonal=:idperson");
        query.setParameter("idperson",idperson);
        return  query.list();
    }




    public Personal_Cargo getCargoPersonal(Integer idPersona, Integer idcargo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM Personal_Cargo pc " +
                "where pc.personal.codigo =:idPersona and pc.cargo.codigo =:idcargo");
        query.setParameter("idPersona",idPersona);
        query.setParameter("idcargo",idcargo);
        Personal_Cargo rolUsuario = (Personal_Cargo) query.uniqueResult();
        // Retrieve
        return  rolUsuario;
    }

    //endregion
}
