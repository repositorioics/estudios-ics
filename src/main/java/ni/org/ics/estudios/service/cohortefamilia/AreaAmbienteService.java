package ni.org.ics.estudios.service.cohortefamilia;


import ni.org.ics.estudios.domain.cohortefamilia.Banio;
import ni.org.ics.estudios.domain.cohortefamilia.Cocina;
import ni.org.ics.estudios.domain.cohortefamilia.Comedor;
import ni.org.ics.estudios.domain.cohortefamilia.Habitacion;
import ni.org.ics.estudios.domain.cohortefamilia.Sala;
import ni.org.ics.estudios.domain.cohortefamilia.Ventana;

import ni.org.ics.estudios.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Transactional
@Service("areaAmbienteService")
public class AreaAmbienteService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    Date fechaMinima = null;
    public AreaAmbienteService(){
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            fechaMinima = DateUtil.StringToDate("01/01/"+year+"", "dd/MM/yyyy");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Banio> getBaniosByHabitacion(String habitacion){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Banio where areaAmbiente.codigo = :habitacion ");
        query.setParameter("habitacion",habitacion);
        return query.list();
    }

    public List<Ventana> getVentanasByArea(String codigoArea){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ventana where codigo = :codigoArea");
        query.setParameter("codigoArea",codigoArea);
        return query.list();
    }

    public Banio getBanioByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Banio where codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (Banio)query.uniqueResult();
    }

    public Cocina getCocinaByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cocina where codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (Cocina)query.uniqueResult();
    }

    public Comedor getComedorByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Comedor where codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (Comedor)query.uniqueResult();
    }

    public Habitacion getHabitacionByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Habitacion where codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (Habitacion)query.uniqueResult();
    }

    public Sala getSalaByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Sala where codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (Sala)query.uniqueResult();
    }

    public Ventana getVentanaByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ventana where codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (Ventana)query.uniqueResult();
    }

    public void saveOrUpdateBanio(Banio banio)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(banio);
    }

    public void saveOrUpdateCocina(Cocina cocina)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cocina);
    }

    public void saveOrUpdateComedor(Comedor comedor)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(comedor);
    }

    public void saveOrUpdateHabitacion(Habitacion habitacion)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(habitacion);
    }

    public void saveOrUpdateSala(Sala sala)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(sala);
    }

    public void saveOrUpdateVentana(Ventana ventana)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(ventana);
    }

    public List<Banio> getBaniosByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select b from Banio b where b.pasive = false and b.casa.codigoCHF in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public List<Cocina> getCocinasByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from Cocina c where c.pasive = false and c.casa.codigoCHF in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public List<Comedor> getComedoresByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from Comedor c where c.pasive = false and c.casa.codigoCHF in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public List<Habitacion> getHabitacionesByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select h from Habitacion h where h.pasive = false and h.casa.codigoCHF in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public List<Sala> getSalasByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from Sala s where s.pasive = false and s.casa.codigoCHF in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public List<Ventana> getVentanasByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Ventana v where v.pasive = false and v.casa.codigoCHF in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public List<Banio> getBanios()
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "select b from Banio b where b.pasive = '0'";
        if (fechaMinima!=null) sqlQuery += " and b.recordDate >= :fechaMinima";
        Query query = session.createQuery(sqlQuery);
        if (fechaMinima!=null) query.setParameter("fechaMinima", fechaMinima);

        return query.list();
    }

    public List<Cocina> getCocinas()
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "select c from Cocina c where c.pasive = '0'";
        if (fechaMinima!=null) sqlQuery += " and c.recordDate >= :fechaMinima";
        Query query = session.createQuery(sqlQuery);
        if (fechaMinima!=null) query.setParameter("fechaMinima", fechaMinima);

        return query.list();
    }

    public List<Comedor> getComedores()
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "select c from Comedor c where c.pasive = '0'";
        if (fechaMinima!=null) sqlQuery += " and c.recordDate >= :fechaMinima";
        Query query = session.createQuery(sqlQuery);
        if (fechaMinima!=null) query.setParameter("fechaMinima", fechaMinima);

        return query.list();
    }

    public List<Habitacion> getHabitaciones()
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "select h from Habitacion h where h.pasive = '0'";
        if (fechaMinima!=null) sqlQuery += " and h.recordDate >= :fechaMinima";
        Query query = session.createQuery(sqlQuery);
        if (fechaMinima!=null) query.setParameter("fechaMinima", fechaMinima);

        return query.list();
    }

    public List<Sala> getSalas()
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "select s from Sala s where s.pasive = '0'";
        if (fechaMinima!=null) sqlQuery += " and s.recordDate >= :fechaMinima";
        Query query = session.createQuery(sqlQuery);
        if (fechaMinima!=null) query.setParameter("fechaMinima", fechaMinima);

        return query.list();
    }

    public List<Ventana> getVentanas()
    {
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "from Ventana v where v.pasive = '0'";
        if (fechaMinima!=null) sqlQuery += " and v.recordDate >= :fechaMinima";
        Query query = session.createQuery(sqlQuery);
        if (fechaMinima!=null) query.setParameter("fechaMinima", fechaMinima);

        return query.list();
    }
}

