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

    //region Seccion de Catalogo Carta

    public void saveCarta(Carta carta){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(carta);
    }

    public void DeleteCarta(Integer id) throws  Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Carta where idcarta = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }


    public List<Carta> getScanCartas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Carta order by carta");
        return query.list();
    }

    public List<Carta> getCartaActiva(){
        Session session = sessionFactory.getCurrentSession();
        String verdadera = "true";
        Query query = session.createQuery("from Carta c where c.activo= :verdadera order by carta");
        query.setParameter("verdadera",verdadera);
        return query.list();
    }

    public boolean CheckequalsCarta(String nombreCarta) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Carta c where c.carta= :nombreCarta" );
        query.setParameter("nombreCarta",nombreCarta);
       return query.list().size()>0;
    }

    public Carta getCartaById(Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Carta where idcarta= :idcarta");
        query.setParameter("idcarta",idcarta);
        return (Carta) query.uniqueResult();
    }
    //endregion

//region    Version
    public void saveScanVersion(Version version) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(version);
    }

    public void DesHabilitarCarta(Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        String f = "false";
        Query query = session.createQuery("update Carta c set c.activo= :f where c.idcarta= :idcarta");
        query.setParameter("f",f);
        query.setParameter("idcarta", idcarta);
        query.executeUpdate();
    }

    public  void HabilitaCarta(Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        String t = "true";
        Query query = session.createQuery("update Carta c set c.activo= :t where c.idcarta= :idcarta");
        query.setParameter("t",t);
        query.setParameter("idcarta", idcarta);
        query.executeUpdate();
    }

    public List<Version> getScanVersion(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Version order by version");
        return query.list();
    }

    public List<Version>getVersionActiva(){
        Session session = sessionFactory.getCurrentSession();
        String verdadero = "true";
        Query query = session.createQuery("from Version v where v.activo= :verdadero order by version");
        query.setParameter("verdadero",verdadero);
        return query.list();
    }

    public Version getVersionById(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        Query query= session.createQuery("from Version where idversion = :idversion");
        query.setParameter("idversion", idversion);
        return (Version) query.uniqueResult();
    }

    public List<Version> getByIdCarta(Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        String verdadero = "true";
        Query query = session.createQuery("from Version v where v.carta.idcarta =:idcarta and v.activo=:verdadero");
        query.setParameter("idcarta",idcarta);
        query.setParameter("verdadero", verdadero);
        return query.list();
    }

    public void DeshabilitarVersion(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        String f = "false";
        Query query = session.createQuery("update Version v set v.activo= :f where v.idversion= :idversion");
        query.setParameter("f",f);
        query.setParameter("idversion", idversion);
        query.executeUpdate();
    }

    public void HabilitarVersion(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        String v = "true";
        Query query = session.createQuery("update Version v set v.activo= :v where v.idversion= :idversion");
        query.setParameter("v",v);
        query.setParameter("idversion", idversion);
        query.executeUpdate();
    }

    public boolean checkExistVersion(String version, Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Version v where v.version= :version and v.carta.idcarta=:idcarta");
        query.setParameter("version",version);
        query.setParameter("idcarta",idcarta);
        return  query.list().size()>0;
    }

//endregion

    //region Catalogo Partes - ScanCarta
    public void SaveParte(Parte parte){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(parte);
    }

    public boolean CheckequalsParte(String nombreParte, Integer idversion) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte p where p.parte= :nombreParte and p.version.idversion= :idversion" );
        query.setParameter("nombreParte",nombreParte);
        query.setParameter("idversion",idversion);
        return query.list().size() > 0;
    }

    public List<Parte>getListParte(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte order by parte desc");
        return query.list();
    }

    public Parte getParteById(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte where idparte= :idparte");
        query.setParameter("idparte",idparte);
        return (Parte) query.uniqueResult();
    }

    public void DesHabilitarParte(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        String f = "false";
        Query query = session.createQuery("update Parte p set p.activo= :f where p.idparte= :idparte");
        query.setParameter("f",f);
        query.setParameter("idparte", idparte);
        query.executeUpdate();
    }

    public  void HabilitarParte(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        String t = "true";
        Query query = session.createQuery("update Parte p set p.activo= :t where p.idparte= :idparte");
        query.setParameter("t",t);
        query.setParameter("idparte", idparte);
        query.executeUpdate();
    }

    //endregion


    public List<Version> getVersioCarta(Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        String t = "true";
        Query query = session.createQuery("from Version v where v.idcarta= :idcarta and v.activo= :t");
        query.setParameter("idcarta",idcarta);
        query.setParameter("t",t);
        return query.list();
    }
    public List<Parte>getParte(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        String t = "true";
        Query query = session.createQuery("from Parte p where p.idversion= :idversion and p.activo= :t");
        query.setParameter("idversion",idversion);
        query.setParameter("t",t);
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
