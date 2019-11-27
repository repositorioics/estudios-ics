package ni.org.ics.estudios.service.hemodinanicaService;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Barrio;
import ni.org.ics.estudios.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.estudios.domain.hemodinamica.HemoDetalle;
import ni.org.ics.estudios.dto.RangosFrecuenciasCardiacas;
import ni.org.ics.estudios.dto.RangosPresion;
import ni.org.ics.estudios.service.UsuarioService;
import ni.org.ics.estudios.users.model.UserSistema;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS_Inspiron3 on 22/05/2019.
 */


@Service("datoshemodinamicaService")
@Transactional
public class DatoshemodinamicaService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name="usuarioService")
    private UsuarioService usuarioService;

    @SuppressWarnings("unchecked")
    public List<DatosHemodinamica> getListadoHemo() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            //String getUserName = usuarioActual.getUsername();
            //Query query = session.createQuery("from DatosHemodinamica where recordUser =:getUserName");
            //query.setParameter("getUserName", getUserName);
            Query query = session.createQuery("from DatosHemodinamica order by fecha asc");
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }
    //Contar el número de Detalle


     /* Metodo busca Participante en  HemoDinamica por ID  */
     @SuppressWarnings("unchecked")
     public List<DatosHemodinamica> getListadoHemoByID(Integer idParticipante) throws Exception {
         try {
             Session session = sessionFactory.getCurrentSession();
              Query query = session.createQuery("from DatosHemodinamica where idParticipante =:idParticipante order by fecha desc");
              query.setParameter("idParticipante", idParticipante);
             return query.list();
         }catch (Exception e){
             throw e;
         }
     }
    /* Fin del Metodo*/

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
        Query query = session.createQuery("FROM DatosHemodinamica d where " +  "d.idDatoHemo =:idDatoHemo order by fecha asc ");
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
    @SuppressWarnings("unchecked")
    public List<HemoDetalle> NumeroHemoDet(String idDatoHemo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM HemoDetalle hd where " + "hd.datoshemodinamica.idDatoHemo =:idDatoHemo");
        query.setParameter("idDatoHemo", idDatoHemo);
        return query.list();
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

    /* Seleccionar registros Rangos de presión   */
    @SuppressWarnings("unchecked")
    public RangosPresion ObtenerRangosPresion(String sexo, Integer edad, String medida){
        Session session = sessionFactory.getCurrentSession();
        String consulta = "FROM RangosPresion WHERE :edad BETWEEN edadmin AND edadmax AND sexo =:sexo AND umedida =:medida";
        Query query = session.createQuery(consulta);
        query.setParameter("edad", edad);
        query.setParameter("sexo", sexo);
        query.setParameter("medida", medida);
        RangosPresion obj = (RangosPresion) query.uniqueResult();
        return obj;
    }

    @SuppressWarnings("unchecked")
    public RangosFrecuenciasCardiacas ObtenerFCardiaca(String medida, Integer edad){
        Session session = sessionFactory.getCurrentSession();
        String consulta ="from RangosFrecuenciasCardiacas where :edad between edadmin and edadmax and umedida =:medida";
        Query query = session.createQuery(consulta);
        query.setParameter("medida", medida);
        query.setParameter("edad", edad);
        RangosFrecuenciasCardiacas obj = (RangosFrecuenciasCardiacas) query.uniqueResult();
        return obj;
    }

    public boolean VerifyId(String idDatoHemo){
        Session session = sessionFactory.getCurrentSession();
        String consulta = " FROM HemoDetalle WHERE idDatoHemo = :idDatoHemo";
        Query query = session.createQuery(consulta);
        query.setParameter("idDatoHemo",idDatoHemo);
        HemoDetalle obj = (HemoDetalle)query.uniqueResult();
        if (obj !=null){
            return true;
        }else {
            return false;
        }

    }


}
