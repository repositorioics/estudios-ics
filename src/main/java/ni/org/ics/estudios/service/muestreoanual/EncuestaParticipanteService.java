package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaParticipanteId;
import ni.org.ics.estudios.domain.muestreoanual.EncuestaParticipanteMA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Servicio para el objeto EncuestaParticipante
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("encuestaParticipanteMAService")
@Transactional
public class EncuestaParticipanteService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las encuestas de los participantes
	 * 
	 * @return una lista de <code>datos de las encuestas de participantes</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<EncuestaParticipanteMA> getEncuestasParticipantes() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM EncuestaParticipanteMA");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa una Encuesta de caa
	 * 
	 * @return una <code>Encuesta de Participante</code>
	 */

	public EncuestaParticipanteMA getEncuestaParticipante (EncuestaParticipanteId epId){
		// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	
	Timestamp timeStamp = new Timestamp(epId.getFechaEncPar().getTime());
	Query query = session.createQuery("FROM EncuestaParticipanteMA encpar where encpar.epId.codigo = " + epId.getCodigo() + " AND encpar.epId.fechaEncPar = :fechaE");
	query.setTimestamp("fechaE",timeStamp);

        EncuestaParticipanteMA encuestaParticipante = (EncuestaParticipanteMA) query.uniqueResult();
	return encuestaParticipante;
	}

    public List<EncuestaParticipanteMA> getEncuestasParticipante (Integer codParticipante){
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM EncuestaParticipanteMA encpar where encpar.epId.codigo = :codigo order by encpar.epId.fechaEncPar asc ");
        query.setParameter("codigo",codParticipante);

        return query.list();
    }
	
	/**
	 * Verifica una Encuesta de Participante
	 * 
	 * @return true or false
	 */

public Boolean checkEncuestaParticipante (EncuestaParticipanteId epId){
		// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	Timestamp timeStamp = new Timestamp(epId.getFechaEncPar().getTime());
	Query query = session.createQuery("FROM EncuestaParticipanteMA encpar where encpar.epId.codigo = " + epId.getCodigo() + " AND encpar.epId.fechaEncPar = :fechaE");
	query.setTimestamp("fechaE",timeStamp);

    EncuestaParticipanteMA encuestaparticipante = (EncuestaParticipanteMA) query.uniqueResult();
	if (encuestaparticipante!=null){
		return true;
	}
	else{
		return false;
	}			
	}
	
	/**
	 * Agrega una Encuesta de Participante
	 * 
	 * 
	 */
public void addEncuestaParticipante(EncuestaParticipanteMA encuestaParticipante){
	Session session = sessionFactory.getCurrentSession();
	session.save(encuestaParticipante);
	}
	
	/**
	 * Actualiza una Encuesta de Participante
	 * 
	 * 
	 */

public void updateEncuestaParticipante(EncuestaParticipanteMA encuestaParticipante){
	Session session = sessionFactory.getCurrentSession();
	session.update(encuestaParticipante);
	}

	
}
