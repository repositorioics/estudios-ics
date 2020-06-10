package ni.org.ics.estudios.service.covid;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
//import ni.org.ics.estudios.domain.covid19.CasaCasoCovid19;
import ni.org.ics.estudios.domain.covid19.CasoCovid19;
import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.domain.covid19.ParticipanteCovid19;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ICS on 01/06/2020.
 */

@Service("CovidService")
@Transactional
public class CovidService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;



    public List<ParticipanteCovid19> getParticipantesCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCovid19 p where p.pasive = '0'");
        return query.list();
    }

    public void saveOrUpdateParticipanteCovid19(ParticipanteCovid19 participanteCovid19){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participanteCovid19);
    }

    public List<CasoCovid19> getCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasoCovid19 c where c.pasive = '0' and c.inactivo = '0'");
        return query.list();
    }

    public void saveOrUpdateCasoCovid19(CasoCovid19 casa){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(casa);
    }

    /***
     * Obtiene una lista de todos los participantes en monitoreo
     */
    public List<ParticipanteCasoCovid19> getParticipantesCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.inactivo = '0'");
        return query.list();
    }

    public void saveOrUpdateParticipanteCasoCovid19(ParticipanteCasoCovid19 participanteCasoCovid19){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participanteCasoCovid19);
    }

    // Metodo para buscar codigo en Covid_Participante
    public ParticipanteCovid19 getParticipanteCovidById(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCovid19 where participante.codigo= :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCovid19)query.uniqueResult();
    }

    /***
     * Obtiene un participante de monitoreo por código de participante reportado como positivo
     * @param codigo Código de participante
     * @return ParticipanteCasoCovid19
     */
    public ParticipanteCasoCovid19 getParticipanteCasoCovid19Pos(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.inactivo = '0' and p.enfermo = 'S' and p.participante.codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCasoCovid19)query.uniqueResult();
    }


    // Metodo para Obtener los participantes de una casa.
    public List<ParticipanteCohorteFamilia> getParticipantesCHFByCodigoCasa(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select pchf from ParticipanteCohorteFamilia pchf, ParticipanteProcesos p where pchf.participante.codigo = p.codigo and pchf.casaCHF.codigoCHF = :codigo and pchf.pasive = '0' and p.estPart = 1");
        query.setParameter("codigo", codigo);
        return query.list();
    }

    //  Metodo para obtener listado casos positivos / no positivos Codvid
    @SuppressWarnings("unchecked")
    public List<ParticipanteCasoCovid19> getParticipanteCasosPositivosCovid(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.enfermo = 'S' and p.codigoCaso.pasive = '0'");
        return query.list();
    }

    //Seleccionar los participantes
    @SuppressWarnings("unchecked")
    public List<ParticipanteCasoCovid19> getParticipantesCohorteFamiliaCasoByCodigoCaso(String codigo){
        Session session = sessionFactory.getCurrentSession();
        //Query query = session.createQuery("from ParticipanteCohorteFamiliaCaso p where p.pasive = '0' and p.codigoCaso.pasive = '0' and p.codigoCaso.codigoCaso = :codigo order by p.participante.participante.codigo");
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.pasive = '0' and p.codigoCaso.codigoCaso = :codigo order by p.participante.participante.codigo");
        query.setParameter("codigo", codigo);
        return query.list();
    }
    // obtiene el caso positivo
    public ParticipanteCasoCovid19 getParticipanteCasoPositivo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.codigoCasoParticipante = :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCasoCovid19)query.uniqueResult();
    }

    public ParticipanteCasoCovid19 getCasoByCodigo2(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 where pasive = '0' and codigoCasoParticipante = :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCasoCovid19)query.uniqueResult();
    }


    /* Obtiene Un Participante por su codigo */
    public Participante getParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Participante where codigo = :codigo");
        query.setParameter("codigo",codigo);
        return (Participante)query.uniqueResult();
    }

    // Metodo para guardar CasoCovid19
    public void saveOrUpdateCasoCovid19(CasoCovid19 casocovid19){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(casocovid19);
    }

    public void saveOrUpdateParticipanteCasoCovid19(ParticipanteCasoCovid19 participante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participante);
    }


}
