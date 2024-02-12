package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.RecepcionPBMC;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ing. Santiago Carballo on 09/02/2024.
 */

@Service("pbmcService")
@Transactional
public class RecepcionPBMCService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<RecepcionPBMC> getAllRecepcionPBMC() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM RecepcionPBMC");
        // Retrieve all
        return  query.list();
    }

    @SuppressWarnings("unchecked")
    public List<RecepcionPBMC> getAllRecepcionPBMCCurrentDay() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String date = null;
        date = targetFormat.format(new Date());
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM RecepcionPBMC pbmc where pbmc.fechaPbmc = :fechaPbmc");
        query.setString("fechaPbmc", date);
        // Retrieve all
        return  query.list();
    }

    @SuppressWarnings("unchecked")
    public RecepcionPBMC getRecepcionPBMC(Integer codigo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String date = null;
        date = targetFormat.format(new Date());
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM RecepcionPBMC pbmc where pbmc.codigo = :codigo and pbmc.fechaPbmc = :fechaPbmc");
        query.setInteger("codigo", codigo);
        query.setString("fechaPbmc", date);

        RecepcionPBMC rPbmc = (RecepcionPBMC) query.uniqueResult();
        return  rPbmc;
    }

    /**
     * Agrega un RecepcionPBMC
     */
    public void addRecepcionPBMC(RecepcionPBMC pbmc) {
        Session session = sessionFactory.getCurrentSession();
        session.save(pbmc);
    }

    /**
     * Actualiza un RecepcionPBMC
     */
    public void updateRecepcionPBMC(RecepcionPBMC pbmc) {
        Session session = sessionFactory.getCurrentSession();
        session.update(pbmc);
    }
}
