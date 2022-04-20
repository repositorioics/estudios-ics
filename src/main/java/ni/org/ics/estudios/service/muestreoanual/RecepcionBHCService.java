package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.RecepcionBHC;
import ni.org.ics.estudios.domain.muestreoanual.RecepcionBHCId;
import ni.org.ics.estudios.dto.muestras.RecepcionBHCDto;
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
 * Servicio para el objeto RecepcionBHC
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("bhcService")
@Transactional
public class RecepcionBHCService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las BHC
	 * 
	 * @return una lista de <code>BHC</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<RecepcionBHC> getRecepcionBHCs() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RecepcionBHC");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RecepcionBHC> getRecepcionBHCsHoy() {
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
		Query query = session.createQuery("FROM RecepcionBHC bhc where bhc.recBhcId.fechaRecBHC = :fechaBHC");
		query.setTimestamp("fechaBHC", timeStamp);
		// Retrieve all
		return  query.list();
	}

    @SuppressWarnings("unchecked")
    public List<RecepcionBHCDto> getCompBHCSupEstHoy() {
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
        Query query = session.createSQLQuery("select recepcionbhc.codigo as codigo, recepcionbhc.fecha_registro as fecreg, recepcionbhc.lugar as lugar, " +
                "recepcionbhc.volbhc as volumen, recepcionbhc.observacion as observacion, recepcionbhc.username as username " +
                "from estudios_ics.recepcionbhc left join estudios_ics.muestras on recepcionbhc.codigo = muestras.codigo and recepcionbhc.fecha_bhc = muestras.fecha_registro " +
                "where ((recepcionbhc.fecha_bhc = :fechaBHC) and " +
                "(muestras.codigo Is Null or recepcionbhc.fecha_bhc <> muestras.fecha_registro or muestras.tubobhc=0) " +
                "and YEAR(recepcionbhc.fecha_bhc) = :anio);");
        query.setTimestamp("fechaBHC", timeStamp);
        query.setInteger("anio", Constants.ANIOMUESTREO);
        query.setResultTransformer(Transformers.aliasToBean(RecepcionBHCDto.class));
        // Retrieve all
        return  query.list();
    }


    @SuppressWarnings("unchecked")
    public List<RecepcionBHCDto> getCompBHCSupLabHoy() {
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
        Query query = session.createSQLQuery("select recepcionbhc.codigo as codigo, recepcionbhc.fecha_registro as fecreg, recepcionbhc.lugar as lugar, " +
                "recepcionbhc.volbhc as volumen, recepcionbhc.observacion as observacion, recepcionbhc.username as username " +
                "from estudios_ics.recepcionbhc left join estudios_ics.labbhc on recepcionbhc.codigo = labbhc.codigo and recepcionbhc.fecha_bhc = labbhc.fecha_bhc " +
                "where ((recepcionbhc.fecha_bhc = :fechaBHC) and " +
                "(labbhc.codigo Is Null or recepcionbhc.fecha_bhc <> labbhc.fecha_bhc) and YEAR(recepcionbhc.fecha_bhc) = :anio);");
        query.setTimestamp("fechaBHC", timeStamp);
        query.setInteger("anio", Constants.ANIOMUESTREO);
        query.setResultTransformer(Transformers.aliasToBean(RecepcionBHCDto.class));
        // Retrieve all
        return  query.list();
    }
	
	/**
	 * Regresa una RecepcionBHC
	 * 
	 * @return un <code>RecepcionBHC</code>
	 */

	public RecepcionBHC getRecepcionBHC(RecepcionBHCId bhcId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(bhcId.getFechaRecBHC().getTime());
		Query query = session.createQuery("FROM RecepcionBHC bhc where bhc.recBhcId.codigo = "
				+ bhcId.getCodigo() +  " AND bhc.recBhcId.fechaRecBHC = :fechaBHC");
		query.setTimestamp("fechaBHC", timeStamp);
		
		RecepcionBHC rBhc = (RecepcionBHC) query.uniqueResult();
		return rBhc;
	}
	
	/**
	 * Verifica una RecepcionBHC
	 * 
	 * @return true or false
	 */

	public Boolean checkRecepcionBHC(RecepcionBHCId bhcId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Timestamp timeStamp = new Timestamp(bhcId.getFechaRecBHC().getTime());
		Query query = session.createQuery("FROM RecepcionBHC bhc where bhc.recBhcId.codigo = "
				+ bhcId.getCodigo() +  " AND bhc.recBhcId.fechaRecBHC = :fechaBHC");
		query.setTimestamp("fechaBHC", timeStamp);
		
		RecepcionBHC rBhc = (RecepcionBHC) query.uniqueResult();
		if(rBhc!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega un RecepcionBHC
	 * 
	 * 
	 */
	public void addRecepcionBHC(RecepcionBHC rBhc) {
		Session session = sessionFactory.getCurrentSession();
		session.save(rBhc);
	}
	
	/**
	 * Actualiza un RecepcionBHC
	 * 
	 * 
	 */
	public void updateRecepcionBHC(RecepcionBHC rBhc) {
		Session session = sessionFactory.getCurrentSession();
		session.update(rBhc);
	}
	
}
