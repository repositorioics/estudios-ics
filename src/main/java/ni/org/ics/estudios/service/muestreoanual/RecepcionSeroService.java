package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.RecepcionSero;
import ni.org.ics.estudios.domain.muestreoanual.RecepcionSeroId;
import ni.org.ics.estudios.web.utils.pdf.Constants;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Servicio para el objeto RecepcionSero
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("seroService")
@Transactional
public class RecepcionSeroService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las Sero
	 * 
	 * @return una lista de <code>RecepcionSero</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<RecepcionSero> getRecepcionSeros() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RecepcionSero");
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<RecepcionSero> getRecepcionSerosHoy() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
	    Date dateWithoutTime = null;
		try {
			dateWithoutTime = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp timeStamp = new Timestamp(dateWithoutTime.getTime());
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RecepcionSero sero where sero.fechaRecSero = :fechaRecSero");
		query.setTimestamp("fechaRecSero", timeStamp);
		// Retrieve all
		return  query.list();
	}


    @SuppressWarnings("unchecked")
    public List<RecepcionSero> getCompSeroSupEstHoy() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateWithoutTime = null;
        try {
            dateWithoutTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Timestamp timeStamp = new Timestamp(dateWithoutTime.getTime());
        // Create a Hibernate query (HQL)
        Query query = session.createSQLQuery("select recepcionsero.codigo as codigo, recepcionsero.fecha_registro as fecreg, recepcionsero.lugar as lugar, " +
                "recepcionsero.volbhc as volumen, recepcionsero.observacion as observacion, recepcionsero.username as username " +
                "from estudios_ics.recepcionsero left join estudios_ics.muestras on recepcionsero.codigo = muestras.codigo and recepcionsero.fecha_sero = muestras.fecha_registro " +
                "where ((recepcionsero.fecha_sero = :fechaSero) and " +
                "(muestras.codigo Is Null or recepcionsero.fecha_sero <> muestras.fecha_registro or muestras.tuborojo=0) " +
                "and (YEAR(recepcionsero.fecha_sero) = :anio));");
        query.setTimestamp("fechaSero", timeStamp);
        query.setInteger("anio", Constants.ANIOMUESTREO);
        query.setResultTransformer(Transformers.aliasToBean(RecepcionSero.class));
        // Retrieve all
        return  query.list();
    }


    @SuppressWarnings("unchecked")
    public List<RecepcionSero> getCompSeroSupLabHoy() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateWithoutTime = null;
        try {
            dateWithoutTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Timestamp timeStamp = new Timestamp(dateWithoutTime.getTime());
        // Create a Hibernate query (HQL)
        Query query = session.createSQLQuery("select recepcionsero.codigo as codigo, recepcionsero.fecha_registro as fecreg, recepcionsero.lugar as lugar, " +
                "recepcionsero.volbhc as volumen, recepcionsero.observacion as observacion, recepcionsero.username as  username " +
                "from estudios_ics.recepcionsero left join estudios_ics.labsero on recepcionsero.codigo = labsero.codigo and recepcionsero.fecha_sero = labsero.fecha_sero " +
                "where ((recepcionsero.fecha_sero  = :fechaSero) and " +
                "(labsero.codigo Is Null or recepcionsero.fecha_sero <> labsero.fecha_sero) " +
                "and (YEAR(recepcionsero.fecha_sero) = :anio));");
        query.setTimestamp("fechaSero", timeStamp);
        query.setInteger("anio", Constants.ANIOMUESTREO);
        query.setResultTransformer(Transformers.aliasToBean(RecepcionSero.class));
        // Retrieve all
        return  query.list();
    }
	
	
	/**
	 * Regresa una RecepcionSero
	 * 
	 * @return un <code>RecepcionSero</code>
	 */

	public RecepcionSero getRecepcionSero(String seroId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM RecepcionSero sero where sero.id = :id");
        query.setParameter("id", seroId);
		
		RecepcionSero rSero = (RecepcionSero) query.uniqueResult();
		return rSero;
	}
	
	/**
	 * Verifica una RecepcionSero
	 * 
	 * @return true or false
	 */

	public Boolean checkRecepcionSero(String seroId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM RecepcionSero sero where sero.id = :id");
		query.setParameter("id", seroId);
		
		RecepcionSero rSero = (RecepcionSero) query.uniqueResult();
		if(rSero!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega un RecepcionSero
	 * 
	 * 
	 */
	public void addRecepcionSero(RecepcionSero rSero) {
		Session session = sessionFactory.getCurrentSession();
		session.save(rSero);
	}
	
	/**
	 * Actualiza un RecepcionSero
	 * 
	 * 
	 */
	public void updateRecepcionSero(RecepcionSero rSero) {
		Session session = sessionFactory.getCurrentSession();
		session.update(rSero);
	}
	
}
