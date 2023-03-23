package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.LabSero;
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

@Service("labSeroService")
@Transactional
public class LabSeroService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las Sero
	 * 
	 * @return una lista de <code>RecepcionSero</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<LabSero> getLabSeros() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM LabSero");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<LabSero> getLabSerosHoy() {
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
		Query query = session.createQuery("FROM LabSero sero where sero.labSeroId.fechaRecSero = :fechaRecSero");
		query.setTimestamp("fechaRecSero", timeStamp);
		// Retrieve all
		return  query.list();
	}


    @SuppressWarnings("unchecked")
    public List<LabSero> getCompSeroLabSupHoy() {
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
        Query query = session.createSQLQuery("select labsero.codigo as codigo, labsero.fecha_registro as fecreg, labsero.volsero as volumen, labsero.observacion as observacion, labsero.username as username " +
                "from estudios_ics.labsero left join estudios_ics.recepcionsero on labsero.codigo = recepcionsero.codigo and labsero.fecha_sero = recepcionsero.fecha_sero " +
                "where ((labsero.fecha_sero = :fechaRecSero) and (recepcionsero.codigo Is Null or labsero.fecha_sero <> recepcionsero.fecha_sero) " +
                "and (YEAR(labsero.fecha_sero) = :anio)) order by labsero.codigo;");
        query.setTimestamp("fechaRecSero", timeStamp);
        query.setInteger("anio", Constants.ANIOMUESTREO);
        query.setResultTransformer(Transformers.aliasToBean(LabSero.class));
        // Retrieve all
        return  query.list();
    }


    @SuppressWarnings("unchecked")
    public List<LabSero> getCompSeroLabEstHoy() {
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
        Query query = session.createSQLQuery("select labsero.codigo as codigo, labsero.fecha_registro as fecreg, labsero.volsero as volumen, labsero.observacion as observacion, labsero.username as username " +
                "from estudios_ics.labsero left join estudios_ics.muestras on labsero.codigo = muestras.codigo and labsero.fecha_sero = muestras.fecha_registro " +
                "where ((labsero.fecha_sero = :fechaRecSero) and (muestras.codigo Is Null or labsero.fecha_sero <> muestras.fecha_registro or muestras.tuborojo=0) " +
                "and (YEAR(labsero.fecha_sero) = :anio)) order by labsero.codigo;");
        query.setTimestamp("fechaRecSero", timeStamp);
        query.setInteger("anio", Constants.ANIOMUESTREO);
        query.setResultTransformer(Transformers.aliasToBean(LabSero.class));
        // Retrieve all
        return  query.list();
    }
	

}
