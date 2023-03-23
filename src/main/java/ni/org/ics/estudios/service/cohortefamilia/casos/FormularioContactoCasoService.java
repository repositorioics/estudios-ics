package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.FormularioContactoCaso;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/11/2017.
 * V1.0
 */
@Transactional
@Service("formularioContactoCasoService")
public class FormularioContactoCasoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<FormularioContactoCaso> getFormularioContactoCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FormularioContactoCaso f where f.pasive = '0' and f.codigoVisitaCaso.codigoParticipanteCaso.codigoCaso.inactiva = '0'");
        return query.list();
    }
    
	public FormularioContactoCaso getFormularioContactoCaso(String codigoCasoContacto){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FormularioContactoCaso f where f.pasive = '0' and f.codigoCasoContacto =:codigoCasoContacto");
        query.setParameter("codigoCasoContacto", codigoCasoContacto);
        return (FormularioContactoCaso) query.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<FormularioContactoCaso> getFormularioContactoCasos(String codigoCasoVisita){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from FormularioContactoCaso f where f.pasive = '0' and f.codigoVisitaCaso.codigoCasoVisita =:codigoCasoVisita order by f.fechaContacto, f.partContacto.participante.codigo");
        query.setParameter("codigoCasoVisita", codigoCasoVisita);
        return query.list();
    }
    
    
    public boolean checkContactoCasoSintomas(Integer codigoPart,String fechaContacto, Integer partContacto){
        Session session = sessionFactory.getCurrentSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");      
	    Date dateCont = null;
		try {
			dateCont = sdf.parse(fechaContacto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp timeStamp = new Timestamp(dateCont.getTime());
        Query query = session.createQuery("from FormularioContactoCaso f where f.pasive = '0' and " +
        		"f.codigoVisitaCaso.codigoParticipanteCaso.participante.participante.codigo = :codigoPart and f.fechaContacto =:fechaContacto and f.partContacto.participante.codigo =:partContacto");
        query.setParameter("codigoPart", codigoPart);
        query.setTimestamp("fechaContacto", timeStamp);
        query.setParameter("partContacto", partContacto);
        return query.list().size()>0;
    }

    public void saveOrUpdateFormularioContactoCaso(FormularioContactoCaso contacto){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contacto);
    }
}
