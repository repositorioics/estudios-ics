package ni.org.ics.estudios.service.zen;

import ni.org.ics.estudios.domain.ContactoParticipante;
import ni.org.ics.estudios.domain.DatosCoordenadas;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.dto.zen.ParticipanteZen;
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
@Service("participanteZenService")
@Transactional
public class ParticipanteZenService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;


    /* Obtiene un lista de todos los Participantes */
    @SuppressWarnings("unchecked")
	public List<ParticipanteZen> getParticipantes() throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as codigo, p.nombre1 as nombre1, p.nombre2 as nombre2,  p.apellido1 as apellido1, p.apellido2 as apellido2, p.sexo as sexo, p.fechaNac as fechaNac, " +
                "p.nombre1Padre as nombre1Padre, p.nombre2Padre as nombre2Padre, p.apellido1Padre as apellido1Padre, p.apellido2Padre as apellido2Padre, p.nombre1Madre as nombre1Madre, p.nombre2Madre as nombre2Madre, " +
                "p.apellido1Madre as apellido1Madre, p.apellido2Madre as apellido2Madre, c.codigo as codigoCasa, b.codigo as codigoBarrio, b.nombre as nombreBarrio, c.direccion as direccion, c.manzana as manzana, pp.estudio as estudios, pp.estPart as estPart, " +
                "pp.tutor as tutor, coalesce((select spanish from MessageResource where catKey = cast(pp.relacionFam as string) and catRoot = 'CP_CAT_RFTUTOR'), 'Sin Relac Familiar') as relacionFamTutor " +
                "from Participante p inner join p.casa c inner join c.barrio b, ParticipanteProcesos pp where p.codigo = pp.codigo " +
                " and pp.estudioZen != '0' ");
        query.setResultTransformer(Transformers.aliasToBean(ParticipanteZen.class));
        return query.list();
    }
}
