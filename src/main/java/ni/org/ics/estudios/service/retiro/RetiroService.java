package ni.org.ics.estudios.service.retiro;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.Retiros.Retiros;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Razones_Retiro;
import ni.org.ics.estudios.dto.HistorialRetiroDto;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.dto.ParticipanteSeroDto;
import ni.org.ics.estudios.dto.RetiroDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ICS on 29/10/2020.
 */
@Service("RetiroService")
@Transactional
public class RetiroService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<Personal> getPersonalRecibeRetiro()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal order by nombre asc ");
        return query.list();
    }


    @SuppressWarnings("unchecked")
    public ParticipanteSeroDto getDatosParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as codigo, concat(p.nombre1,' ',p.nombre2,' ',p.apellido1,' ',p.apellido2) as nombreCompleto, p.casa.codigo as casaPediatrica, pp.casaCHF as casaFamilia, pp.estudio as estudios, p.fechaNac as fechaNacimiento, pp.estPart as estado " +
                " from Participante p, ParticipanteProcesos pp where p.codigo = pp.codigo and p.codigo= :codigo");
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(ParticipanteSeroDto.class));
        return (ParticipanteSeroDto)query.uniqueResult();
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

    @SuppressWarnings("unchecked")
    public void ActualizarCampoEstudio(Integer codigo, String estudioFinales) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update ParticipanteProcesos pp set pp.estudio= :estudioFinales where pp.codigo= :codigo");
        query.setParameter("codigo",codigo);
        query.setParameter("estudioFinales",estudioFinales);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public void ActualizarCampoEstudioAndEstado(Integer codigo) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Integer inactivo = 0;
        String estudio ="";
        Query query = session.createQuery("update ParticipanteProcesos pp set pp.estudio= :estudio, pp.estPart= :inactivo where pp.codigo= :codigo");
        query.setParameter("estudio",estudio);
        query.setParameter("inactivo",inactivo);
        query.setParameter("codigo",codigo);
        query.executeUpdate();
    }

    public void ActualizarCampoActual(Integer codigo)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Boolean falso = false;
        Query query = session.createQuery("update Retiros r set r.actual= :falso where r.participante.codigo= :codigo");
        query.setParameter("codigo",codigo);
        query.setParameter("falso",falso);
        query.executeUpdate();

    }

    public List<Personal> getSupervisor()throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Integer cargoId = 14;
        String verdad = "true";
        Query query = session.createQuery("from Personal p where p.cargo.codigo= :cargoId and p.activochf= :verdad");
        query.setParameter("cargoId",cargoId);
        query.setParameter("verdad",verdad);
        return query.list();
    }


    public Personal getSupervisorById(Integer idpersona)throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Integer cargoId = 14;
        String verdad = "true";
        Query query = session.createQuery("from Personal p where p.idPersona= :idpersona and p.cargo.codigo= :cargoId and p.activochf= :verdad");
        query.setParameter("idpersona",idpersona);
        query.setParameter("cargoId",cargoId);
        query.setParameter("verdad",verdad);
        Personal obj = (Personal) query.uniqueResult();
        return obj;
    }


    @SuppressWarnings("unchecked")
    public List<Razones_Retiro> getRazonesRetiros(Integer grupo)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Razones_Retiro r where r.grupoid= :grupo");
        query.setParameter("grupo", grupo);
        return (List<Razones_Retiro>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Retiros> getListadoRetiroByID(Integer idParticipante) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Retiros r where r.participante.codigo =:idParticipante order by r.fecharetiro asc");
            query.setParameter("idParticipante", idParticipante);
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public Retiros getRetiroByID(Integer idretiro) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Retiros r where r.idretiro =:idretiro order by r.fecharetiro desc ");
            query.setParameter("idretiro", idretiro);
            Retiros objRetiro = (Retiros) query.uniqueResult();
            return objRetiro;
        }catch (Exception e){
            throw e;
        }
    }


    public Razones_Retiro getRazonRetiro(String razon)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query  =  session.createQuery("from Razones_Retiro rr where rr.motivo= :razon");
            query.setParameter("razon", razon);
            return (Razones_Retiro) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }


    }


    /*Update campo Actual a 0 todos los existentes*/
    public void allActualBaja(Integer codigoPart) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Boolean result = false;
            String consulta = "UPDATE Retiros r SET r.actual = :result WHERE r.participante.codigo =:codigoPart";
            Query query = session.createQuery(consulta);
            query.setParameter("result", result);
            query.setParameter("codigoPart", codigoPart);
            query.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }




    @SuppressWarnings("unchecked")
    public void SaveRetiros(Retiros obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }



    //region documentacion_retiro_data_to_mysql (HISTORIAL)
    @SuppressWarnings("unchecked")
    public List<RetiroDto>ListOfRetiro(Integer parametro2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RetiroDto r where codigo_participante= :parametro2");
        query.setParameter("parametro2",parametro2);
        return  query.list();
    }



    //endregion




}
