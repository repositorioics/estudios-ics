package ni.org.ics.estudios.service.hemodinanicaService;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Barrio;
import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.estudios.domain.hemodinamica.HemoDetalle;
import ni.org.ics.estudios.users.model.UserSistema;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS_Inspiron3 on 22/05/2019.
 */


@Service("datoshemodinamicaService")
@Transactional
public class datoshemodinamicaService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<DatosHemodinamica> getListadoHemo() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from DatosHemodinamica");
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }
/*Metodo para Guardar hoja Hemodinamica*/
    @SuppressWarnings("unchecked")
    public void SaveDatosHemo(DatosHemodinamica obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }
    /*Metodo para Guardar el Detalle Hemodinamico*/
    @SuppressWarnings("unchecked")
    public void SaveDetalleHemo(HemoDetalle details) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(details);
        }catch (Exception e){
            throw e;
        }
    }

    /* Este Servicio Devuelve un Participante por parametro, falta validar que el participante este activo */
    @SuppressWarnings("unchecked")
    public Participante getParticipante(Integer parametro) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Participante where codigo =:parametro");
            query.setParameter("parametro", parametro);
            return (Participante) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }

    /* --  Verificar si Existe una hoja con identica  -- */
    public boolean SiExisteHemo(String idDatoHemo, Date fecha, String horas) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from HemoDetalle h where " + "h.datoshemodinamica.idDatoHemo =:idDatoHemo and  h.fecha =:fecha and h.hora =:horas");
            query.setParameter("idDatoHemo", idDatoHemo);
            query.setParameter("fecha", fecha);
            query.setParameter("horas", horas);
            return  query.list().size()>0;
        }catch (Exception e){
            throw e;
        }
    }

    public DatosHemodinamica getbyId(String idDatoHemo) throws Exception {
        try {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM DatosHemodinamica d where " +  "d.idDatoHemo =:idDatoHemo");
        query.setParameter("idDatoHemo",idDatoHemo);
        DatosHemodinamica objHemo = (DatosHemodinamica) query.uniqueResult();
        return objHemo;
        }catch (Exception e){
            throw e;
        }
    }
    /*obtener el listado de los detalles hemodinamica */
    public List<HemoDetalle> getListHemoDetalle(String idDatoHemo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM HemoDetalle det where " + "det.datoshemodinamica.idDatoHemo =:idDatoHemo");
        query.setParameter("idDatoHemo",idDatoHemo);
        return  query.list();
    }

    public int NumeroHemoDet(String idDatoHemo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) FROM HemoDetalle hd where " + "hd.datoshemodinamica.idDatoHemo =:idDatoHemo");
        query.setParameter("idDatoHemo", idDatoHemo);
        int counter = query.hashCode();
        return counter ;
    }

    /* Servicio para obtener hemo detalle por id*/
    public HemoDetalle getByHemoDetalleId(String idHemoDetalle){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM HemoDetalle h where " + "h.idHemoDetalle =:idHemoDetalle");
        query.setParameter("idHemoDetalle", idHemoDetalle);
                HemoDetalle objDet =(HemoDetalle) query.uniqueResult();
        return objDet;
    }

    /*Metodo para Obtener los barrios*/
    @SuppressWarnings("unchecked")
    public List<Barrio> getBarrios(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Barrio order by nombre");
        return query.list();
    }


    /**/



}
