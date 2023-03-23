package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaCasaMA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Servicio para el objeto EncuestaCasaEB
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("encuestaCasaMAService")
@Transactional
public class EncuestaCasaService {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;


	/**
	 * Regresa todos las encuestas de Casas de los participantes
	 * 
	 * @return una lista de <code>datos de las encuestas de casas</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<EncuestaCasaMA> getEncuestasCasas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM EncuestaCasaMA");
		// Retrieve all
		return  query.list();
	}

	/**
	 * Verifica una Encuesta de Casa
	 * 
	 * @return true or false
	 */

	public Boolean checkEncuestaCasa (Integer codCasa, Date fechaEncCasa){
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		Timestamp timeStamp = new Timestamp(fechaEncCasa.getTime());
		Query query = session.createQuery("FROM EncuestaCasaMA enccas where (enccas.codCasa = "
				+ codCasa + " or enccas.codCasaChf = '"+ codCasa +"') AND enccas.fechaEncCasa = :fechaE");
		query.setTimestamp("fechaE",timeStamp);

        EncuestaCasaMA encuestacasa = (EncuestaCasaMA) query.uniqueResult();
		if (encuestacasa!=null){
			return true;
		}
		else{
			return false;
		}			
	}

	/**
	 * Agrega una Encuesta de Casa
	 * 
	 * 
	 */
	public void addEncuestaCasa(EncuestaCasaMA encuestaCasa){
		Session session = sessionFactory.getCurrentSession();
		session.save(encuestaCasa);
	}

	/**
	 * Actualiza una Encuesta de Casa
	 * 
	 * 
	 */

	public void updateEncuestaCasa(EncuestaCasaMA encuestaCasa){
		Session session = sessionFactory.getCurrentSession();
		session.update(encuestaCasa);
	}


}
