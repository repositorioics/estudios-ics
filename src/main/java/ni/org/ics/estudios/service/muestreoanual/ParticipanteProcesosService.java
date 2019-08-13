package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Servicio para el objeto Participante
 * 
 * @author William Aviles
 * 
 **/

@Service("participanteProcesosService")
@Transactional
public class ParticipanteProcesosService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

    /**
     * Regresa todos los Participantes Activos
     *
     * @return una lista de <code>Participante</code>(s) Activos
     */

    @SuppressWarnings("unchecked")
    public List<ParticipanteProcesos> getParticipantesProcesos() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM ParticipanteProcesos p");
        // Retrieve all
        return  query.list();
    }

    public void saveOrUpdateParticipanteProc(ParticipanteProcesos participante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participante);
    }

	/**
	 * Regresa un participante
	 * 
	 * @return un <code>Participante</code>
	 */

	public ParticipanteProcesos getParticipante(Integer codigo) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM ParticipanteProcesos par where par.codigo = "+codigo);
		ParticipanteProcesos participante = (ParticipanteProcesos) query.uniqueResult();
		return participante;
	}





	@SuppressWarnings("unchecked")
	public List<ParticipanteProcesos> getParticipantesMensajes(Integer barrio,
			String tubo, String estudio, String edad) {
		// Retrieve session from Hibernate
		/*Session session = sessionFactory.getCurrentSession();
		Integer edad1 = 0;
		Integer edad2 = 0;
		// Create a Hibernate query (HQL)
		if (edad.equals("%")){
			edad2=360;
		}
		else if(edad.equals("<2")){
			edad1=6;
			edad2=23;
		}
		else if(edad.equals(">2")){
			edad1=24;
			edad2=360;
		}
		Query query = session.createQuery("FROM ParticipanteProcesos par where par.barrioFicha1 =:barrio and par.pbmc=:tubo " +
				"and par.estudio like:estudio and edad between :edad1 and :edad2 and par.telefono is not null and par.estPart=1");
		query.setParameter("barrio", barrio);
		query.setParameter("estudio", estudio);
		query.setParameter("tubo", tubo);
		query.setParameter("edad1", edad1);
		query.setParameter("edad2", edad2);*/
		// Retrieve all
		return null; //query.list();
	}
	
}
