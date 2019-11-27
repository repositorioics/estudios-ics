package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.DatosCoordenadas;
import ni.org.ics.estudios.domain.ContactoParticipante;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.dto.DatosParticipante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/2/2017.
 * V1.0
 */
@Service("participanteService")
@Transactional
public class ParticipanteService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;


    /* Obtiene un lista de todos los Participantes */
    @SuppressWarnings("unchecked")
	public List<Participante> getParticipantes(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Participante");
        return query.list();
    }
    /* Obtiene Un Participante por su codigo */
    public Participante getParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Participante where codigo = :codigo");
        query.setParameter("codigo",codigo);
        return (Participante)query.uniqueResult();
    }

    public void saveOrUpdateParticipante(Participante participante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participante);
    }

    /**
     * Regresa un codigo de casa
     *
     * @return un <code>Integer</code>
     */

    public Integer checkCasa(Integer codigo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Integer codCasa = 0;
        Query query = session.createQuery("FROM Participante par where par.codigo = "+ codigo);
        Participante participante = (Participante) query.uniqueResult();
        if (participante!= null){
            codCasa = participante.getCasa().getCodigo();
        }
        return codCasa;
    }

    /**
     * Verifica un Participante
     *
     * @return true or false
     */

    public Boolean checkParticipante(Integer codigo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Participante par where par.codigo = "+codigo);
        Participante participante = (Participante) query.uniqueResult();
        if(participante!=null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Regresa todos los Participantes Activos
     *
     * @return una lista de <code>Participante</code>(s) Activos
     */

    @SuppressWarnings("unchecked")
    public List<Participante> getParticipantesActivos() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("select p FROM Participante p, ParticipanteProcesos pp where p.codigo = pp.codigo and pp.estPart = 1");
        // Retrieve all
        return  query.list();
    }

    /**
     * Crear o actualizar un ContactoParticipante
     * @param contactoParticipante
     */
    public void saveOrUpdateContactoParticipante(ContactoParticipante contactoParticipante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(contactoParticipante);
    }

    @SuppressWarnings("unchecked")
    public List<ContactoParticipante> getContactosParticipantes(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ContactoParticipante where pasive = '0'");
        return query.list();
    }

    /**
     * Crear o actualizar un DatosCoordenadas
     * @param datosCoordenadas
     */
    public void saveOrUpdateDatosCoordenadas(DatosCoordenadas datosCoordenadas){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(datosCoordenadas);
    }

    @SuppressWarnings("unchecked")
    public List<DatosCoordenadas> getDatosCoordenadas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DatosCoordenadas ");
        return query.list();
    }

    public List<ContactoParticipante> getContactosParticipante(Integer codigoParticipante){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ContactoParticipante where participante.codigo = :codigo and pasive = '0' order by recordDate desc");
        query.setParameter("codigo", codigoParticipante);
        return query.list();
    }

    public List<DatosParticipante> getDatosParticipante(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as codigo, p.nombre1 as nombre1, p.nombre2 as nombre2,  p.apellido1 as apellido1, p.apellido2 as apellido2, p.sexo as sexo, p.fechaNac as fechaNac, " +
                "p.nombre1Padre as nombre1Padre, p.nombre2Padre as nombre2Padre, p.apellido1Padre as apellido1Padre, p.apellido2Padre as apellido2Padre, p.nombre1Madre as nombre1Madre, p.nombre2Madre as nombre2Madre, " +
                "p.apellido1Madre as apellido1Madre, p.apellido2Madre as apellido2Madre, c.codigo as codigoCasa, b.codigo as codigoBarrio, b.nombre as nombreBarrio, c.direccion as direccion, c.manzana as manzana, pp.estudio as estudios, pp.estPart as estPart, " +
                "pp.tutor as tutor, coalesce((select spanish from MessageResource where catKey = cast(pp.relacionFam as string) and catRoot = 'CP_CAT_RFTUTOR'), 'Sin Relac Familiar') as relacionFamTutor " +
                "from Participante p inner join p.casa c inner join c.barrio b, ParticipanteProcesos pp where p.codigo = pp.codigo " +
                " and p.codigo = :codigo ");
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(DatosParticipante.class));
        return query.list();
    }
}
