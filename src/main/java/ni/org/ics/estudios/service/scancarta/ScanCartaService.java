package ni.org.ics.estudios.service.scancarta;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Carta;
import ni.org.ics.estudios.domain.catalogs.Parte;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Version;
import ni.org.ics.estudios.domain.scancarta.DetalleParte;
import ni.org.ics.estudios.domain.scancarta.ParticipanteCarta;
//import ni.org.ics.estudios.domain.scancarta.ScanCarta;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 6/27/2017.
 * V1.0
 */
@Transactional
@Service("scanCartaService")
public class ScanCartaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<Carta> getScanCartas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Carta");
        return query.list();
    }
    public List<Version> getVersioCarta(Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Version where idcarta = :idcarta");
        query.setParameter("idcarta",idcarta);
        return query.list();
    }
    public List<Parte>getParte(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte where idversion = :idversion");
        query.setParameter("idversion",idversion);
        return query.list();
    }
    public List<Parte>getParteList(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte order by parte asc");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public ParticipanteCarta getCartasParticipante(Integer parametro) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from ParticipanteCarta p where p.idparticipantecarta =:parametro");
            query.setParameter("parametro", parametro);
            return (ParticipanteCarta) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public boolean SiExisteParticipanteCarta(Integer idversion, Integer idparticipante)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query =session.createQuery("from ParticipanteCarta pc where version.idversion =:idversion and participante.codigo =:idparticipante");
            query.setParameter("idversion",idversion);
            query.setParameter("idparticipante",idparticipante);
            return  query.list().size()>0;
        }
        catch (Exception e){
            throw e;
        }
    }


    public List<Personal>getPersonal(){
        Session session = sessionFactory.getCurrentSession();
        Integer cod = 4;
        Query query = session.createQuery("from Personal where idcargo = :cod order by nombre asc");
        query.setParameter("cod",cod);
        return query.list();
    }

    public List<ParticipanteCarta> getScanCartasByParticipante(Integer parametro){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCarta where participante.codigo = :parametro");
        query.setParameter("parametro",parametro);
        return query.list();
    }

    public List<Parte> getParteParticipante(Integer idparticipantecarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte  where participantecarta.idparticipantecarta = :idparticipantecarta");
        query.setParameter("idparticipantecarta",idparticipantecarta);
        return  query.list();
    }

    public List<DetalleParte>getDetalleParteList(Integer idParticipanteCarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte where participantecarta.idparticipantecarta = :idParticipanteCarta");
        query.setParameter("idParticipanteCarta",idParticipanteCarta);
        return  query.list();
    }

    public void updateRetiro(Integer idParticipanteCarta)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update ParticipanteCarta set retirado='1' where idparticipantecarta = :idParticipanteCarta");
        query.setParameter("idParticipanteCarta", idParticipanteCarta);
        query.executeUpdate();
    }
    //Metodo para eliminar partes
    public void DeleteParteParticipante(Integer idparticipantecarta)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE from DetalleParte where participantecarta.idparticipantecarta= :idparticipantecarta");
        query.setParameter("idparticipantecarta", idparticipantecarta);
        query.executeUpdate();
    }



    public void saveOrUpdateScanCarta(ParticipanteCarta scanCarta)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(scanCarta);
    }

    public void saveParteCarta(DetalleParte detalis){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(detalis);
    }
}
