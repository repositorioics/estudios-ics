package ni.org.ics.estudios.service.SerologiaOct2020;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia_Detalle_Envio;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Personal_Cargo;
import ni.org.ics.estudios.dto.ParticipanteSeroDto;
import ni.org.ics.estudios.dto.SerologiaDto;
import ni.org.ics.estudios.service.UsuarioService;
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
    public List<Personal_Cargo> getListPersonRececiona() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Integer idcargo[]= {4};
            Query query = session.createQuery("from Personal_Cargo p where p.cargo.idcargo in(:idcargo) and p.personal.pasive='0' ");
            query.setParameterList("idcargo", idcargo);
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }

    //region Verificar si existe registro Guardado.
    public boolean ExisteSerologia(Date fecha, Integer codigo) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Serologia s where s.fecha =:fecha and s.participante =:codigo and s.codigoPbmc=0");
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

    //Métodos para Guardar SerologiaEnvio
    @SuppressWarnings("unchecked")
    public void save_Envio_Serologia(SerologiaEnvio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public void save_Detalles_Serologia_Envio(Serologia_Detalle_Envio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }



    //endregion

    public Serologia getSerologiaById(String idSerologia) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = Integer.parseInt(idSerologia);
        Query query = session.createQuery("FROM Serologia s where s.idSerologia =:id");
        query.setParameter("id",id);
        Serologia sero = (Serologia) query.uniqueResult();
        return sero;
    }

    public List<Serologia>SerologiaList()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.pasive='0' and s.codigoPbmc=0 order by s.idSerologia asc");
        return query.list();
    }

    //Llena la DataTables con Serologias no enviadas
    @SuppressWarnings("unchecked")
    public List<SerologiaDto> SerologiaNoEnviadaDto()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s.idSerologia as idSerologia, s.estudio as estudios, s.enviado as enviado, s.participante as idparticipante, DATE_FORMAT(s.fecha, '%d/%m/%Y') as fecha, s.volumen as volumen, coalesce(s.observacion, '') as observacion, coalesce(s.descripcion, '') as descripcion, coalesce(s.codigoPbmc, '') as codigoPbmc, " +
                " s.casaPDCS as casaPDCS from Serologia s where s.enviado ='0' and s.pasive ='0' and s.codigoPbmc=0  order by s.participante asc");
        query.setResultTransformer(Transformers.aliasToBean(SerologiaDto.class));
        return query.list();
    }


    /*public List<Serologia>SerologiaNoEnviada()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.enviado ='0' order by s.recordDate asc ");
        return query.list();
    }*/

    @SuppressWarnings("unchecked")
    public List<SerologiaDto>SeroNoEnviadaDto()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Character noSend = '0';
        Query query = session.createQuery("select s.idSerologia as idSerologia, s.enviado as cerrado, p.codigo as idparticipante, s.fecha as fecha, s.volumen as volumen, s.observacion as observacion, s.precepciona as idpersonal, s.gradilla as gradilla,s.estudio as estudios, " +
                " s.casaCHF as casaChf, s.recordDate as recordDate  from Serologia s inner join Participante p where s.participante = p.codigo and s.enviado =:noSend ");
        query.setParameter("noSend", noSend);
        query.setResultTransformer(Transformers.aliasToBean(Serologia.class));
        return query.list();
    }

    //Actualiza el campo envio
    public  void ModificarEnvio(Integer idSerologia){
        Session session = sessionFactory.getCurrentSession();
        Character enviado = '1';
        Query query = session.createQuery("update Serologia c set c.enviado= :enviado where c.idSerologia= :idSerologia");
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
        Query query = session.createQuery("from Serologia s where s.fecha between :fechaInicio and :fechaFin and s.enviado='0' ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }

//region Catalogos
    /*public List<Envio>getAllEnvios(){
        Session session = sessionFactory.getCurrentSession();
        Character pasivoNo = '0';
        Query query = session.createQuery("from  Envio e where e.pasive= :pasivoNo");
        query.setParameter("pasivoNo",pasivoNo);
        return query.list();
    }*/

//endregion

    @SuppressWarnings("unchecked")
    public Personal getPersonal() throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Personal");
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


    /* Obtiene Un Participante por su codigo  */
    public Participante getParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Participante where codigo =:codigo");
        query.setParameter("codigo",codigo);
        return (Participante)query.uniqueResult();
    }

 //10401 = 8 no 12


    public List<SerologiaEnvio> getSerologiaEnvioByDates(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SerologiaEnvio se where se.fecha between :fechaInicio and :fechaFin and se.idenvio =:nEnvios ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }

    // todo **  Consulta para llenar el reporte Serologia **
    public List<Serologia_Detalle_Envio>getAllSerologia(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia_Detalle_Envio se where se.serologiaEnvio.fecha between :fechaInicio and :fechaFin and se.serologiaEnvio.idenvio =:nEnvios and se.serologia.codigoPbmc=0  order by se.serologia.participante asc ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }

}
