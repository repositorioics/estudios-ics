package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.Casa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Servicio para presentar mapas
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("mapaService")
@Transactional
public class MapaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;


	@SuppressWarnings("unchecked")
	public List<Casa> getPuntos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("From Casa cas " +
				"where cas.latitud is not null and cas.latitud >0");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getParticipantesPuntos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("select participantes.codigo, nombre1, nombre2,apellido1,apellido2, participantes.cod_casa, " +
				"participantes.desc_barrio, latitud, longitud, pbmc, tubobhc, tuborojo, tuboleu " +
				"from participantes left join muestras on participantes.codigo = muestras.codigo " +
				"where latitud is not null and latitud > 0 and est_part = 1 order by codigo;");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getParticipantesMuestras() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("select participantes.codigo, nombre1, nombre2,apellido1,apellido2, cod_casa, " +
				"desc_barrio, latitud, longitud, pbmc, tubobhc, tuborojo, tuboleu " +
				"from participantes left join muestras on participantes.codigo = muestras.codigo " +
				"where latitud is not null and latitud > 0 and est_part = 1 and (tuborojo=1 or tuboleu=1) order by codigo;");
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getParticipantesPendientes() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("select participantes.codigo, nombre1, nombre2,apellido1,apellido2, cod_casa, " +
				"desc_barrio, latitud, longitud, pbmc, paxgene, tubobhc, tuborojo, tuboleu " +
				"from participantes left join muestras on participantes.codigo = muestras.codigo " +
				"where latitud is not null and latitud > 0 and est_part = 1 and muestras.codigo is null order by codigo;");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getParticipantesPendientes(Integer barrio) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("select participantes.codigo, nombre1, nombre2,apellido1,apellido2, cod_casa, " +
				"desc_barrio, latitud, longitud, pbmc, tubobhc, tuborojo, tuboleu " +
				"from participantes left join muestras on participantes.codigo = muestras.codigo " +
				"where latitud is not null and latitud > 0 and est_part = 1 and casas.barrio = " + barrio +" and muestras.codigo is null order by codigo;");
		// Retrieve all
		return  query.list();
	}
	
	
	
}
