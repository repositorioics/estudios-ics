package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;

import ni.org.ics.estudios.dto.influenzauo1.casosPositivosDiffDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/11/2017.
 * V1.0
 */
@Transactional
@Service("participanteCohorteFamiliaCasoService")
public class ParticipanteCohorteFamiliaCasoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    /***
     * Obtiene una lista de todos los participantes en monitoreo
     */
	public List<ParticipanteCohorteFamiliaCaso> getParticipanteCohorteFamiliaCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.inactiva = '0'");
        return query.list();
    }

    /***
     * Obtiene todos los participantes de monitoreo activos reportados como positivos
     * @return List<ParticipanteCohorteFamiliaCaso>
     */
    @SuppressWarnings("unchecked")
	public List<ParticipanteCohorteFamiliaCaso> getParticipanteCohorteFamiliaCasosPositivos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.enfermo = 'S' and p.codigoCaso.pasive = '0' order by p.codigoCaso.recordDate desc , p.recordDate desc ");
        return query.list();
    }

    /***
     * Obtiene un participante de monitoreo por el codigod del participante en el caso
     * @param codigo Código de participante en el caso
     * @return ParticipanteCohorteFamiliaCaso
     */
    public ParticipanteCohorteFamiliaCaso getParticipanteCohorteFamiliaCasosByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.inactiva = '0' and p.codigoCasoParticipante = :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCohorteFamiliaCaso)query.uniqueResult();
    }

    /***
     * Obtiene los participantes de un caso de monitoreo
     * @param codigo Código de caso
     * @return List<ParticipanteCohorteFamiliaCaso>
     */
    @SuppressWarnings("unchecked")
	public List<ParticipanteCohorteFamiliaCaso> getParticipantesCohorteFamiliaCasoByCodigoCaso(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.pasive = '0' and p.codigoCaso.codigoCaso = :codigo order by p.participante.participante.codigo");
        query.setParameter("codigo", codigo);
        return query.list();
    }

    /***
     * Obtiene los participantes de un caso por código de casa chf
     * @param codigo Código de casa
     * @return List<ParticipanteCohorteFamiliaCaso>
     */
    @SuppressWarnings("unchecked")
	public List<ParticipanteCohorteFamiliaCaso> getParticipantesCohorteFamiliaCasoByCodigoCasa(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.inactiva = '0' and p.codigoCaso.casa.codigoCHF = :codigo order by p.participante.participante.codigo asc");
        query.setParameter("codigo", codigo);
        return query.list();
    }

    /***
     * Obtiene un participante de monitoreo por código de participante reportado como positivo
     * @param codigo Código de participante
     * @return ParticipanteCohorteFamiliaCaso
     */
    public ParticipanteCohorteFamiliaCaso getParticipanteCohorteFamiliaCasosByParticipantePos(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.inactiva = '0' and p.enfermo = 'S' and p.participante.participante.codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCohorteFamiliaCaso)query.uniqueResult();
    }

    /***
     * Obtiene el participante de monitoreo reportado como positivo según la casa a la que pertenece un participante
     * @param codigo Código de participante
     * @return ParticipanteCohorteFamiliaCaso
     */
    @SuppressWarnings("unchecked")
	public List<ParticipanteCohorteFamiliaCaso> getParticipanteCohorteFamiliaCasosPosByParticipante(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from ParticipanteCohorteFamiliaCaso p, ParticipanteCohorteFamilia pp where p.participante.participante.codigo = pp.participante.codigo and p.pasive = '0' and p.codigoCaso.inactiva = '0' and p.enfermo = 'S' " +
                " and pp.casaCHF.codigoCHF =(select pc.casaCHF.codigoCHF from ParticipanteCohorteFamilia pc where pc.participante.codigo = :codigo)" +
                " order by p.fechaEnfermedad asc");
        query.setParameter("codigo", codigo);
        return query.list();
    }

    /***
     * Obtiene el participante reportado como positivo en una casa
     * @param codigo Código de casa
     * @return ParticipanteCohorteFamiliaCaso
     */
    @SuppressWarnings("unchecked")
	public List<ParticipanteCohorteFamiliaCaso> getParticipanteCohorteFamiliaCasosByCasaPos(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.inactiva = '0' and p.enfermo = 'S' and p.codigoCaso.casa.codigoCHF = :codigo");
        query.setParameter("codigo", codigo);
        return query.list();
    }

    /***
     * Obtiene un participante de monitoreo por código de participante
     * @param codigo Código de participante
     * @return ParticipanteCohorteFamiliaCaso
     */
    public ParticipanteCohorteFamiliaCaso getParticipanteCohorteFamiliaCasosByParticipante(Integer codigo, String caso){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.inactiva = '0' and p.participante.participante.codigo = :codigo and p.codigoCaso.codigoCaso = :caso");
        query.setParameter("codigo", codigo);
        query.setParameter("caso", caso);
        return (ParticipanteCohorteFamiliaCaso)query.uniqueResult();
    }

    /***
     * Guarda un participante de monitoreo
     * @param participante agregar o actualizar
     */
    public void saveOrUpdateParticipanteCohorteFamiliaCaso(ParticipanteCohorteFamiliaCaso participante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participante);
    }

    /***
     * Obtiene un participante de monitoreo por el codigod del participante
     * @param codigo Código de participante
     * @return ParticipanteCohorteFamiliaCaso
     */
    public ParticipanteCohorteFamiliaCaso getParticipanteCohorteFamiliaCasosByCodigoPart(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.inactiva = '0' and p.participante.participante.codigo  = :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCohorteFamiliaCaso)query.uniqueResult();
    }



    public casosPositivosDiffDto getdiasTranscurridos(String codigo, int intervalo ){
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT c.CODIGO_CASO as codigoCaso, c.CODIGO_CASA_CHF as casaCHF, " +
                "DATE_FORMAT(c.FECHA_INICIO,'%d-%m-%Y') AS fechaIngreso, DATE_FORMAT(CURDATE(),'%d-%m-%Y' ) AS hoy,TIMESTAMPDIFF(DAY, c.FECHA_INICIO, NOW()) AS diasTranscurridos \n" +
                "FROM chf_casas_casos c WHERE c.FECHA_INACTIVA IS NULL AND c.INACTIVA = '0' AND c.PASIVO ='0' AND c.codigo_caso =:codigo HAVING diasTranscurridos >= :intervalo ORDER BY 2 ASC;";
        Query query = session.createSQLQuery(sql);
        query.setParameter("codigo", codigo);
        query.setParameter("intervalo", intervalo);
        query.setResultTransformer(Transformers.aliasToBean(casosPositivosDiffDto.class));
        return (casosPositivosDiffDto) query.uniqueResult();
    }

    public casosPositivosDiffDto getInfoDiasTranscurridos( String codigo ){
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT c.CODIGO_CASO as codigoCaso, c.CODIGO_CASA_CHF as casaCHF, \n" +
                "DATE_FORMAT(c.FECHA_INICIO,'%d-%m-%Y') AS fechaIngreso, DATE_FORMAT(CURDATE(),'%d-%m-%Y' ) AS hoy,TIMESTAMPDIFF(DAY, c.FECHA_INICIO, NOW()) AS diasTranscurridos " +
                "FROM chf_casas_casos c WHERE c.FECHA_INACTIVA IS NULL AND c.INACTIVA = '0' AND c.PASIVO ='0' AND c.codigo_caso =:codigo ORDER BY 2 ASC;";
        Query query = session.createSQLQuery(sql);
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(casosPositivosDiffDto.class));
        return (casosPositivosDiffDto) query.uniqueResult();
    }

}
