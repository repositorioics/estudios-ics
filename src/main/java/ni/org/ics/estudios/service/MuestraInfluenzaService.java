package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.MuestraInfluenza;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 7/4/2017.
 * V1.0
 */
@Transactional
@Service("muestraInfluenzaService")
public class MuestraInfluenzaService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    public List<MuestraInfluenza> getMuestrasInfluenza(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from MuestraInfluenza");
        return query.list();
    }

    public List<MuestraInfluenza> getMuestrasInfluenza(Integer codigoParticipante, String codigoMx, Date fechaInicio, Date fechaFin, String codigoCasa){
        Session session = sessionFactory.getCurrentSession();
        StringBuilder strConsulta= new StringBuilder("from MuestraInfluenza where anulada = false");
        if (codigoParticipante!=null)
            strConsulta.append(" and idMx = :codigoParticipante");
        if (codigoMx!=null && !codigoMx.isEmpty())
            strConsulta.append(" and codLab = :codigoMx");
        if (fechaInicio!=null && fechaFin!=null)
            strConsulta.append(" and hoy between :fechaInicio and :fechaFin");
        if (codigoCasa!=null && !codigoCasa.isEmpty())
            strConsulta.append(" and idMx in (select p.participante.codigo from ParticipanteCohorteFamilia p where p.casaCHF.codigoCHF = :codigoCasa)");

        //Query query = session.createQuery("from MuestraInfluenza where anulada = false and hoy between :fechaInicio and :fechaFin and idMx in (select p.participante.codigo from ParticipanteCohorteFamilia p where p.casaCHF.codigoCHF = :casa)");
        Query query = session.createQuery(strConsulta.toString());
        if (codigoParticipante!=null)
            query.setParameter("codigoParticipante",codigoParticipante);
        if (codigoMx!=null && !codigoMx.isEmpty())
            query.setParameter("codigoMx",codigoMx);
        if (fechaInicio!=null && fechaFin!=null){
            query.setParameter("fechaInicio",fechaInicio);
            query.setParameter("fechaFin",fechaFin);
        }
        if (codigoCasa!=null && !codigoCasa.isEmpty())
            query.setParameter("codigoCasa",codigoCasa);

        return query.list();
    }

    public List<MuestraInfluenza> getMuestrasInfluenza(Integer codigoParticipante, String codigoCasa, String tipo){

        List<ParticipanteCohorteFamiliaCaso> positivos = new ArrayList<ParticipanteCohorteFamiliaCaso>();
        if (codigoCasa!=null && !codigoCasa.isEmpty())
            positivos = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCasaPos(codigoCasa);
        if (positivos.size()==0 && codigoParticipante!=null) {
            positivos = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosPosByParticipante(codigoParticipante);
        }
        Session session = sessionFactory.getCurrentSession();
        StringBuilder strConsulta= new StringBuilder("from MuestraInfluenza where anulada = false and fechaTomaMx >= :fif");
        if (codigoParticipante!=null)
            strConsulta.append(" and idMx = :codigoParticipante");
        if (codigoCasa!=null && !codigoCasa.isEmpty())
            strConsulta.append(" and idMx in (select p.participante.codigo from ParticipanteCohorteFamilia p where p.casaCHF.codigoCHF = :codigoCasa)");

        /*if (tipo.equalsIgnoreCase("CHF_CAT_TIP_ETIQ_MI_04")){
            strConsulta.append(" and codLab like '%.TR%'");
        }*/
        strConsulta.append(" order by fechaTomaMx desc ");
        //Query query = session.createQuery("from MuestraInfluenza where anulada = false and hoy between :fechaInicio and :fechaFin and fechaTomaMx >= :fif");
        Query query = session.createQuery(strConsulta.toString());
        query.setParameter("fif",(positivos.get(0)!=null? positivos.get(0).getFechaEnfermedad() : new Date()));
        if (codigoParticipante!=null)
            query.setParameter("codigoParticipante",codigoParticipante);
        if (codigoCasa!=null && !codigoCasa.isEmpty())
            query.setParameter("codigoCasa",codigoCasa);

        return query.list();
    }

    public void saveOrUpdateMuestraInfluenza(MuestraInfluenza muestraInfluenza)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(muestraInfluenza);
    }





}
