package ni.org.ics.estudios.service.SerologiaOct2020;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.SerologiaOct2020.Envio;
import ni.org.ics.estudios.domain.SerologiaOct2020.RangoGradilla;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.dto.GradillaDto;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.dto.ParticipanteSeroDto;
import ni.org.ics.estudios.dto.SerologiaDto;
import ni.org.ics.estudios.service.UsuarioService;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.transform.Transformer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ICS on 15/10/2020.
 */

@Service("SerologiaService")
@Transactional
public class SerologiaOct2020Service {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name="usuarioService")
    private UsuarioService usuarioService;


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
    public List<Personal> getListPersonRececiona() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            int idcargo=6;
            Query query = session.createQuery("from Personal p where cargo.id =:idcargo");
            query.setParameter("idcargo", idcargo);
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }

    //region Verificar si existe registro Guardado.
    public boolean ExisteSerologia(Date fecha, Integer codigo) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Serologia s where s.fecha =:fecha and participante.codigo =:codigo");
            query.setParameter("fecha", fecha);
            query.setParameter("codigo", codigo);
            return  query.list().size()>0;

        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    //endregion

    //region Métodos para Guardar Serologia
    @SuppressWarnings("unchecked")
    public void saveSerologia(Serologia obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }


    @SuppressWarnings("unchecked")
    public void saveSerologiaEnviadas(SerologiaEnvio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }


    //endregion

    public Serologia getSerologiaById(String idSerologia) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Integer id = Integer.parseInt(idSerologia);
        Query query = session.createQuery("FROM Serologia s where s.idSerologia =:id");
        query.setParameter("id",id);
        Serologia sero = (Serologia) query.uniqueResult();
        return sero;
    }

    public List<Serologia>SerologiaList()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.pasive='0' order by s.idSerologia asc");
        return query.list();
    }

    //Llena la DataTables con Serologias no enviadas
    public List<Serologia>SerologiaNoEnviada()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Character noSend = '0';
        Query query = session.createQuery("from Serologia s where s.cerrado =:noSend order by s.recordDate");
        query.setParameter("noSend", noSend);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<SerologiaDto>SeroNoEnviadaDto()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Character noSend = '0';
        Query query = session.createQuery("select s.idSerologia as idSerologia, s.cerrado as cerrado, p.codigo as idparticipante, s.fecha as fecha, s.volumen as volumen, s.observacion as observacion, s.precepciona as idpersonal, s.gradilla as gradilla,s.estudio as estudios, " +
                " s.casaCHF as casaChf, s.recordDate as recordDate  from Serologia s inner join Participante p where s.participante.codigo = p.codigo and s.cerrado =:noSend ");
        query.setParameter("noSend", noSend);
        query.setResultTransformer(Transformers.aliasToBean(Serologia.class));
        return query.list();
    }

    //Actualiza el campo envio
    public  void ModificarEnvio(Integer idSerologia){
        Session session = sessionFactory.getCurrentSession();
        Character enviado = '1';
        Query query = session.createQuery("update Serologia c set c.cerrado= :enviado where c.idSerologia= :idSerologia");
        query.setParameter("enviado",enviado);
        query.setParameter("idSerologia", idSerologia);
        query.executeUpdate();
    }

    public  void ModificarAllEnvios(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Character enviado = '1';
        Query query = session.createQuery("update Serologia c set c.cerrado= :enviado where c.fecha between :fechaInicio and :fechaFin");
        query.setParameter("enviado",enviado);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        //and c.idSerologia= :idSerologia query.setParameter("idSerologia", idSerologia);
        query.executeUpdate();
    }

    public List<Serologia>ObtenerSerologiasEnviadas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Character enviado = '0';
        Query query = session.createQuery("from Serologia s where s.fecha between :fechaInicio and :fechaFin and s.cerrado= :enviado");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("enviado",enviado);
        return query.list();
    }

//region Catalogos
    public List<Envio>getAllEnvios(){
        Session session = sessionFactory.getCurrentSession();
        Character pasivoNo = '0';
        Query query = session.createQuery("from  Envio e where e.pasive= :pasivoNo");
        query.setParameter("pasivoNo",pasivoNo);
        return query.list();
    }


    public List<RangoGradilla> getNumGradilla(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RangoGradilla");
        return query.list();

    }
//endregion

    @SuppressWarnings("unchecked")
    public Personal getPersonal() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Personal");
            //query.setParameter("idPersona", idPersona);
            return (Personal) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }


    public List<SerologiaEnvio> getAllSeroEnviadas()throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from SerologiaEnvio");
            return query.list();
        }catch (Exception ex){
            throw ex;
        }
    }

    public ParticipanteSeroDto getDatosParticipanteById(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as codigo, concat(p.nombre1,' ', p.nombre2,' ', p.apellido1,' ', p.apellido2) as nombreCompleto, pp.casaCHF as casaFamilia, pp.estPart as estado, pp.estudio as estudios, p.fechaNac as fechaNacimiento from Participante p, ParticipanteProcesos pp where p.codigo = pp.codigo and p.codigo= :codigo");
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(ParticipanteSeroDto.class));
        return (ParticipanteSeroDto)query.uniqueResult();
    }

    public GradillaDto getUtlGradillaByDates(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select max(s.idSerologia) as maxidserologia, s.gradilla as ngradilla from Serologia s where fecha between :fechaInicio and :fechaFin");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setResultTransformer(Transformers.aliasToBean(GradillaDto.class));
        return (GradillaDto)query.uniqueResult();
    }
 //10401 = 8 no 12


    public List<SerologiaEnvio> getSerologiaEnvioByDates(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SerologiaEnvio se where se.fecha between :fechaInicio and :fechaFin and se.idenvio =:nEnvios");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }

}
