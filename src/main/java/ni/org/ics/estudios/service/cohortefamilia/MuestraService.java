package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.domain.cohortefamilia.MuestraSuperficie;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/17/2017.
 * V1.0
 */
@Transactional
@Service("muestraService")
public class MuestraService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<Muestra> getMuestras() throws Exception
    {
        Calendar hoy = Calendar.getInstance();
        int anioActual = hoy.get(Calendar.YEAR);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Muestra where pasive = '0' and recordDate >= :primerDia");
        query.setParameter("primerDia", DateUtil.StringToDate("01/01/"+String.valueOf(anioActual), "dd/MM/yyyy"));
        return  query.list();
    }
    
    @SuppressWarnings("unchecked")
	public List<Muestra> getMuestrasTx() throws Exception{
        Calendar hoy = Calendar.getInstance();
        int anioActual = hoy.get(Calendar.YEAR);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Muestra where pasive = '0' and proposito = '3' and recordDate >= :primerDia");
        query.setParameter("primerDia", DateUtil.StringToDate("01/01/"+String.valueOf(anioActual), "dd/MM/yyyy"));
        return  query.list();
    }
    
	public Muestra getMuestra(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Muestra m where m.codigo =:codigo");
        query.setParameter("codigo", codigo);
        return  (Muestra) query.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<Muestra> getMuestrasTx(Integer codigo, Date fecha){
        Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(fecha.getTime());
        Query query = session.createQuery("from Muestra m where pasive = '0' and proposito = '3' and m.participante.codigo =:codigo and m.recordDate =:fechaM");
        query.setParameter("codigo", codigo);
        query.setParameter("fechaM", timeStamp);
        return  query.list();
    }

    @SuppressWarnings("unchecked")
	public List<Muestra> getMuestrasByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Muestra ec where ec.pasive = '0' and ec.participante.casa.codigo in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return  query.list();
    }

    public void saveOrUpdate(Muestra muestra){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(muestra);
    }

    public List<MuestraSuperficie> getMuestrasSuperficie() throws Exception
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from MuestraSuperficie m, CasaCohorteFamiliaCaso c where m.caso = c.codigoCaso and c.pasive = '0' and c.inactiva = '0' and m.pasive = '0'");
        return  query.list();
    }

    public void saveOrUpdateMxSup(MuestraSuperficie muestra){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(muestra);
    }

    public List<Muestra> getMuestrasUO1() throws Exception{
        Calendar hoy = Calendar.getInstance();
        int anioActual = hoy.get(Calendar.YEAR);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Muestra where pasive = '0' and proposito = '4' and recordDate >= :primerDia");
        query.setParameter("primerDia", DateUtil.StringToDate("01/01/"+String.valueOf(anioActual), "dd/MM/yyyy"));
        return  query.list();
    }
}
