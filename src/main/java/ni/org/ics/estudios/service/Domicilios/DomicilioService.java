package ni.org.ics.estudios.service.Domicilios;

import ni.org.ics.estudios.domain.Casa;
import ni.org.ics.estudios.domain.CasaTmp;
import ni.org.ics.estudios.domain.DatosCoordenadas;

import ni.org.ics.estudios.domain.catalogs.Barrio;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.dto.DomicilioPdviDto;
import ni.org.ics.estudios.dto.ParticipantesCodigo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS_Inspiron3 on 16/07/2019.
 */
@Service("DomicilioService")
@Transactional
public class DomicilioService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;



    @SuppressWarnings("unchecked")
    public List<DomicilioPdviDto>ListOfPDVI(Integer parametro2)throws Exception{
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from DomicilioPdviDto where  codigo =:parametro2 order by fechacambio desc ");
            //Query query=session.createQuery("select cast(dp.id as integer ) as Id ,(case when dp.codigo_casa_chf is null then 'ninguna' else dp.codigo_casa_chf end ) ,(case when dp.codigo_casa_pdcs is null then 'ninguna' else dp.codigo_casa_pdcs end ) , dp.ndireccion, b.nombre as NameBarrio, dp.codigo, dp.manzana, dp.origen from DomicilioPdviDto dp, Barrio b where dp.barrio = b.codigo and dp.codigo =:parametro");
            //Query query = session.createQuery("select  dp, b.nombre as NombreBarrio from DomicilioPdviDto dp, Barrio b where dp.barrio = b.codigo and dp.codigo =:parametro ");
            //Query query = session.createQuery("from DomicilioPdviDto dp inner  join Barrio b on  dp.barrio = b.codigo and dp.codigo =:parametro");
            query.setParameter("parametro2",parametro2);
            return  query.list();
        }catch (Exception e){
            throw e;
        }
    }




    // Búsca participante por codigo
    @SuppressWarnings("unchecked")
    public ParticipantesCodigo getCodigos(Integer parametro)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("SELECT cast( p.codigo as string ) as codigo, cast(c.codigo as string) as casaP, coalesce( pp.estudio, 'Vacio') as estudio, " +
                    "(SELECT cc.codigoCHF from ParticipanteCohorteFamilia pc, CasaCohorteFamilia cc where pc.casaCHF = cc.codigoCHF and pc.participante = p.codigo) as casaFamilia " +
                    "FROM Participante p, Casa c, ParticipanteProcesos pp where p.casa = c.codigo and p.codigo = pp.codigo  and p.codigo =:parametro");
            query.setParameter("parametro", parametro);
            query.setResultTransformer(Transformers.aliasToBean(ParticipantesCodigo.class));
            return (ParticipantesCodigo) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }
    /*poblar el Select Usuario*/
    public List<Personal>ListPersonal()throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Personal order by Nombre");
            return query.list();
        }catch (Exception e)
        { throw e;}
    }

     @SuppressWarnings("unchecked")
     public List<DatosCoordenadas> CoordenadasParticipante(Integer parametro)throws Exception{
         try
         {
             Session session = sessionFactory.getCurrentSession();
             Query query = session.createQuery("from DatosCoordenadas where participante.codigo =:parametro order by actual desc ");
             query.setParameter("parametro",parametro);
             return query.list();
         }catch (Exception e){
             throw e;
         }
     }

    /* Metodo para Guardar el Datos Coordenadas */
    @SuppressWarnings("unchecked")
    public void SaveDomicilio(DatosCoordenadas obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }

    /*Update campo Actual*/
    public void UpdateActual(String codigo)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            String consulta = "UPDATE DatosCoordenadas SET actual = '0' WHERE codigo =:codigo";
            Query query = session.createQuery(consulta);
            query.setParameter("codigo",codigo);
            query.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    /*Update campo Actual a 0 todos los existentes*/
    public void allActualBaja(Integer codigoPart) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            String consulta = "UPDATE DatosCoordenadas SET actual = '0' WHERE participante.codigo =:codigoPart";
            Query query = session.createQuery(consulta);
            query.setParameter("codigoPart", codigoPart);
            query.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }


    /***** SERVICIOS PARA LAS NUEVAS CASAS *******/

     /* --  Verificar si Existe una hoja con identica  -- */
    @SuppressWarnings("unchecked")
    public boolean SiExisteHouse(Integer cod) throws Exception {
        try {
            boolean bandera = false;
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Casa c where " + "c.codigo =:cod");
            query.setParameter("cod", cod);
            if (query.list().size() > 0) {
                bandera = true;
            }
            return bandera;
        }catch (Exception e){
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
public void SaveCasaTmp(CasaTmp obj) throws Exception {
            try {
                Session session = sessionFactory.getCurrentSession();
                session.saveOrUpdate(obj);
            }catch (Exception e){
                throw e;
            }
        }


    @SuppressWarnings("unchecked")
        public List<CasaTmp> ListCasasTmp(String username)throws Exception{
            try
            {
                Session session = sessionFactory.getCurrentSession();
                String consulta = "from CasaTmp where usuario =:username";
                Query query = session.createQuery(consulta);
                query.setParameter("username", username);
                return query.list();
            }
        catch (Exception ex){
            throw ex;
        }
    }
    /************/
}
