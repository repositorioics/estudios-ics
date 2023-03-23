package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.TelefonoContacto;
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
@Service("telefonoContactoService")
@Transactional
public class TelefonoContactoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    
	@SuppressWarnings("unchecked")
	public List<TelefonoContacto> getTelefonosContacto(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TelefonoContacto tc where tc.pasive = '0'");
        return query.list();
    }
	
    public void saveOrUdateTelefono(TelefonoContacto telefono){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(telefono);
    }

    
}
