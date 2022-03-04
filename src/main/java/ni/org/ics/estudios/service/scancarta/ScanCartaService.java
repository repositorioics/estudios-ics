package ni.org.ics.estudios.service.scancarta;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.*;
import ni.org.ics.estudios.domain.scancarta.*;
import ni.org.ics.estudios.dto.scan;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.cert.Extension;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

//import ni.org.ics.estudios.domain.scancarta.ScanCarta;

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

    public Participante getParticipante(Integer codigo) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Participante par where par.codigo = "+codigo);
        Participante participante = (Participante) query.uniqueResult();
        return participante;
    }

    public List<Carta> getScanCartas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Carta order by carta");
        return query.list();
    }

    public List<Extension> getExtensionVersion(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Extensiones e where e.version.id=:idversion");
        query.setParameter("idversion",idversion);
        return query.list();
    }

    public List<Extensiones> getAllExtension(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Extensiones e order by e.extension asc");
        return query.list();
    }

    //Obtengo la lista de la tabla extensionesTmp pasive='0'
    public List<ExtensionesTmp> getListExtensionTmp(){
        Session session = sessionFactory.getCurrentSession();
        String nameUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Query query = session.createQuery("from ExtensionesTmp e where e.pasive='0' and e.recordUser=:nameUser order by e.fechaExtension desc ");
        query.setParameter("nameUser",nameUser);
        return query.list();
    }
    public List<Carta> getCartaActiva(){
        Session session = sessionFactory.getCurrentSession();
        String verdadera = "true";
        Query query = session.createQuery("from Carta c where c.activo= :verdadera order by carta");
        query.setParameter("verdadera",verdadera);
        return query.list();
    }

    public List<Estudio>getEstudios(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Estudio e where e.pasive='0' order by e.nombre asc ");
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
        Query query = session.createQuery("from Version v where v.activo=true order by v.version");
        return query.list();
    }

    public List<Version>getVersionActiva(){
        Session session = sessionFactory.getCurrentSession();
        boolean verdadero = true;
        Query query = session.createQuery("from Version v where v.activo= :verdadero order by version");
        query.setParameter("verdadero",verdadero);
        return query.list();
    }

    public Version getVersionById(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        Query query= session.createQuery("from Version v where v.idversion = :idversion");
        query.setParameter("idversion", idversion);
        return (Version) query.uniqueResult();
    }


    public List<Version> getVersionByIdestudio(Integer idestudio){
        Session session = sessionFactory.getCurrentSession();
        boolean verdadero = true;
        Query query = session.createQuery("from Version v where v.estudio.codigo =:idestudio and v.activo=:verdadero");
        query.setParameter("idestudio",idestudio);
        query.setParameter("verdadero", verdadero);
        return query.list();
    }

    public void DeshabilitarVersion(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        boolean f = false;
        Query query = session.createQuery("update Version v set v.activo= :f where v.idversion= :idversion");
        query.setParameter("f",f);
        query.setParameter("idversion", idversion);
        query.executeUpdate();
    }

    public void HabilitarVersion(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        boolean v = true;
        Query query = session.createQuery("update Version v set v.activo= :v where v.idversion= :idversion");
        query.setParameter("v",v);
        query.setParameter("idversion", idversion);
        query.executeUpdate();
    }

    public boolean checkExistVersion(String version, Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Version v where v.version= :version and v.estudio.codigo=:idcarta");
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

    public boolean CheckequalsExtension(String nombreExtension, Integer idversion) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Extensiones e where e.extension= :nombreExtension and e.version.idversion= :idversion" );
        query.setParameter("nombreExtension",nombreExtension);
        query.setParameter("idversion",idversion);
        return query.list().size() > 0;
    }
    //Llenar Select Partes en Catalogo Parte
    public List<Parte>getListParte(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte p where p.activo=true order by p.parte asc");
        return query.list();
    }

    public Parte getParteById(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte where idparte= :idparte");
        query.setParameter("idparte",idparte);
        return (Parte) query.uniqueResult();
    }
    public List<Parte> getParteByVersionId(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Parte where version.idversion= :idversion");
        query.setParameter("idversion",idversion);
        return  query.list();
    }

    public void DesHabilitarParte(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        boolean f = false;
        Query query = session.createQuery("update Parte p set p.activo= :f where p.idparte= :idparte");
        query.setParameter("f",f);
        query.setParameter("idparte", idparte);
        query.executeUpdate();
    }

    public  void HabilitarParte(Integer idparte){
        Session session = sessionFactory.getCurrentSession();
        boolean t = true;
        Query query = session.createQuery("update Parte p set p.activo= :t where p.idparte= :idparte");
        query.setParameter("t",t);
        query.setParameter("idparte", idparte);
        query.executeUpdate();
    }
    //endregion

    /* OBTENER VERSION POR CARTA  */
    public List<Version> getVersioCarta(Integer idcarta){
        Session session = sessionFactory.getCurrentSession();
        boolean t = true;
        Query query = session.createQuery("from Version v where v.estudio.codigo= :idcarta and v.activo= :t");
        query.setParameter("idcarta",idcarta);
        query.setParameter("t",t);
        return query.list();
    }

    public List<Parte>getParte(Integer idversion){
        Session session = sessionFactory.getCurrentSession();
        String t = "true";
        Query query = session.createQuery("from Parte p where p.version.id = :idversion and p.activo=true");
        query.setParameter("idversion",idversion);
        //query.setParameter("t",t);
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
    public boolean SiExisteParticipanteCarta(Integer idversion, Integer idparticipante, String fechaCarta)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Date fecha = DateUtil.StringToDate(fechaCarta,"dd/MM/yyyy");
            boolean anulada = false;
            Query query =session.createQuery("from ParticipanteCarta pc where pc.fechacarta =:fecha and version.idversion =:idversion and participante.codigo =:idparticipante and anulada=:anulada ");
            query.setParameter("idversion",idversion);
            query.setParameter("idparticipante",idparticipante);
            query.setParameter("fecha",fecha);
            query.setParameter("anulada",anulada);
            return  query.list().size()>0;
        }
        catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Personal_Cargo>getPersonal(HashSet<Integer> ids){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal_Cargo p where p.cargo.idcargo in (:ids) and p.pasive ='0' ");
        query.setParameterList("ids",ids);
        return query.list();
    }


    public Personal getPersonalById(Integer idPersonal){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal p where p.idpersonal=:idPersonal and p.pasive ='0' ");
        query.setParameter("idPersonal", idPersonal);
        return (Personal) query.uniqueResult();
    }


    public List<ParticipanteCarta> getScanCartasByParticipante(Integer parametro){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCarta where participante.codigo = :parametro");
        query.setParameter("parametro",parametro);
        return query.list();
    }

    //Metodo para obtener cartaparticipante by idparticipanteCarta
    public ParticipanteCarta getScanCartasByIdParticipanteCarta(Integer parametro){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCarta pc where pc.idparticipantecarta = :parametro");
        query.setParameter("parametro",parametro);
        return (ParticipanteCarta) query.uniqueResult();
    }

    public boolean tieneExtensionByVersion(Integer idVersion)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            boolean verdad = true;
            Query query = session.createQuery("from Extensiones e where e.version.id=:idVersion and e.active=:verdad");
            query.setParameter("idVersion", idVersion);
            query.setParameter("verdad", verdad);
            return query.list().size() > 0;
        }catch (Exception e){
            return false;
        }
    }

    public List<Parte> getParteParticipante(Integer idparticipantecarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte  where participantecarta.idparticipantecarta = :idparticipantecarta");
        query.setParameter("idparticipantecarta",idparticipantecarta);
        return  query.list();
    }

    public List<DetalleParte>getDetalleParteList(Integer idParticipanteCarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte where participantecarta.idparticipantecarta =:idParticipanteCarta");
        query.setParameter("idParticipanteCarta",idParticipanteCarta);
        return  query.list();
    }


    // Obtener todas las extensiones ListadoExtensionParticipante.
    public List<ParticipanteExtension>getAllPartipantExtension(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteExtension pe where pe.anulada=false  order by pe.fechaExtension desc ");
        return query.list();
    }


    // Metodo participante extension by id_participante_carta
    public ParticipanteExtension getParticipanteExtensionById(int idparticipanteExtension){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteExtension pe where pe.idParticipantExtension=:idparticipanteExtension  ");
        query.setParameter("idparticipanteExtension",idparticipanteExtension);
        return (ParticipanteExtension) query.uniqueResult();
    }



    //OBTENER Extension por idParticipantExtension para editar
    public ParticipanteExtension getByIDDetalleParte(Integer idParticipantExtension){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteExtension d where d.idParticipantExtension= :idParticipantExtension");
        query.setParameter("idParticipantExtension",idParticipantExtension);
        return (ParticipanteExtension) query.uniqueResult();
    }

    //region Metodo para anular carta, parte y extension
    public boolean updateAnular(Integer idParticipanteCarta, String pqAnulada)throws Exception{
        try{
        Session session = sessionFactory.getCurrentSession();
        boolean Si = true;
        Query query = session.createQuery("update ParticipanteCarta pc set pc.anulada= :Si , pc.pq_anulada = :pqAnulada where idparticipantecarta = :idParticipanteCarta");
        query.setParameter("Si",Si);
        query.setParameter("pqAnulada", pqAnulada);
        query.setParameter("idParticipanteCarta", idParticipanteCarta);
            int result = query.executeUpdate();
            return result > 0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean UpdateParteAnulada(Integer idparticipantecarta){
        try{
            Session session = sessionFactory.getCurrentSession();
            boolean si = true;
            Query query = session.createQuery("update DetalleParte dp set dp.anulada=:si where dp.participantecarta.idparticipantecarta=:idparticipantecarta");
            query.setParameter("si",si);
            query.setParameter("idparticipantecarta",idparticipantecarta);
            int result = query.executeUpdate();
            return result > 0;
        }catch (Exception e){
            return false;
        }
    }

    // Actualizar el Campo Anular de Extension
    public boolean UpdateExtensionAnulada(Integer idPartcipanteCarta){
        try {
            Session session = sessionFactory.getCurrentSession();
            boolean si = true;
            Query query = session.createQuery("update ParticipanteExtension pe set pe.anulada=:si  where pe.participantecarta.idparticipantecarta=:idPartcipanteCarta");
            query.setParameter("si",si);
            query.setParameter("idPartcipanteCarta",idPartcipanteCarta);
            int result = query.executeUpdate();
            return result > 0;
        }catch (Exception e){
            return false;
        }

    }
    //endregion
    //Metodo para eliminar partes
    public void DeleteParteParticipante(Integer idparticipantecarta)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE from DetalleParte where participantecarta.idparticipantecarta= :idparticipantecarta");
        query.setParameter("idparticipantecarta", idparticipantecarta);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Estudio> getAllEstudios()throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Estudio order by id");
            return  (List<Estudio>) query.list();
        }catch (Exception e){
            return null;
        }
    }

    //METODO PARA OBTENER LA LISTA DE LOS DETALLES
    @SuppressWarnings("unchecked")
    public List<DetalleParte>getDetalleByIdParticipanteCarta(Integer idparticipantecarta)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParte dp  where dp.participantecarta.id=:idparticipantecarta order by dp.iddetalle asc");
        query.setParameter("idparticipantecarta", idparticipantecarta);
        return query.list();
    }

    public List<Extensiones>getExtension(Integer idversion)throws Exception {
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Extensiones e where e.version.id=:idversion");
            query.setParameter("idversion", idversion);
            return query.list();
        }catch (Exception e){
            return null;
        }
    }
    // Metodo para la poblar la tabla en Extension.jsp
     public List<Extensiones>getExtensionsByVersion(Integer idversion)throws Exception {
      try{
          Session session = sessionFactory.getCurrentSession();
          Query query = session.createQuery("from Extensiones e where e.version.id=:idversion");
          query.setParameter("idversion", idversion);
          return query.list();
          }catch (Exception e){
               return null;
           }
     }

    @SuppressWarnings("unchecked")
    @Transactional
    public void saveOrUpdateScanCarta(ParticipanteCarta scanCarta) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(scanCarta);
    }

    public void saveParteCarta(DetalleParte detalle){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(detalle);
    }

    public void saveORupdateExtension(Extensiones extensiones){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(extensiones);
    }

    public Extensiones getExtensionById(Integer idextension){
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Extensiones e where e.id=:idextension ");
        query.setParameter("idextension",idextension);
        return (Extensiones)query.uniqueResult();
    }

    // Obtengo la lista de Extension Temporal by idParticipanteCartatmp para pasarla a las tabla Oficial
    public List<ExtensionesTmp>getListExtensionTmpByParticipanteCartaId(int idParticipanteCartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ExtensionesTmp et where et.participantecartatmp.id=:idParticipanteCartatmp and et.pasive='0'");
        query.setParameter("idParticipanteCartatmp",idParticipanteCartatmp);
        return query.list();
    }

    public boolean ActualizarAcepta(Integer idDetalle, boolean newacepta)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("update DetalleParte dp set dp.acepta= :newacepta where dp.iddetalle= :idDetalle");
            query.setParameter("idDetalle",idDetalle);
            query.setParameter("newacepta", newacepta);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
}

    public void saveParticpanteExtension(ParticipanteExtension obj)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }
    //Integer idVersion, Integer idParticipante
    public boolean VerificaExtension(String dinicial, String dfinal, Integer idExtension, Integer idparticipantecarta)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Date finicial = DateUtil.StringToDate(dinicial, "dd/MM/yyyy");
            Date ffinal =  DateUtil.StringToDate(dfinal+ " 23:59:59", "dd/MM/yyyy HH:mm:ss"); //DateUtil.StringToDate(dfinal, "dd/MM/yyyy");
            Query query = session.createQuery("from ParticipanteExtension pe where pe.fechaExtension between :finicial and :ffinal and pe.extensiones.id=:idExtension and pe.participantecarta.idparticipantecarta =:idparticipantecarta and  pe.anulada=false ");
            query.setParameter("finicial", finicial);
            query.setParameter("ffinal", ffinal);
            query.setParameter("idExtension", idExtension);
            query.setParameter("idparticipantecarta", idparticipantecarta);
            return query.list().size() > 0;
        }catch (Exception e){
            return false;
        }
    }

    public List<ParticipanteExtension>getAllPartExt(Integer idparticipantecarta){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteExtension pe where pe.participantecarta.id= :idparticipantecarta and pe.anulada=false ");
        query.setParameter("idparticipantecarta", idparticipantecarta);
        return query.list();
    }


    public Integer cantExtensionByCarta(Integer idParticipanteCartaExtension)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (*) from ParticipanteExtension pe where pe.participantecarta.id=:idParticipanteCartaExtension");
        query.setParameter("idParticipanteCartaExtension",idParticipanteCartaExtension);
        Integer result = Integer.valueOf(String.valueOf(query.uniqueResult()));
        return result;
    }

    //region    CARTAS TEMPORAL
    // Guardar Carta en Tabla Temporal
    public void guardarCartaTMP(ParticipanteCartaTmp temporal)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(temporal);
    }

    public void saveParteCartaTMP(DetalleParteTmp detalle){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(detalle);
    }

    //Metodo para obtener el listado de la tabla Temporal
    public List<ParticipanteCartaTmp>getAllParticipanteCartaTmp()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        String nameUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Query query = session.createQuery("from ParticipanteCartaTmp tm where tm.pasive='0' and tm.recordUser=:nameUser order by tm.id asc ");
        query.setParameter("nameUser",nameUser);
        return query.list();
    }


    public Integer countCartaTmpByUser()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        String nameUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Query query = session.createQuery("select count (*) from ParticipanteCartaTmp p where p.pasive='0' and p.recordUser=:nameUser order by p.id asc");
        query.setParameter("nameUser",nameUser);
        Integer result = Integer.valueOf(String.valueOf(query.uniqueResult()));
        return result;
    }


    public ParticipanteCartaTmp getAllParticipanteCartaTmpById(int id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCartaTmp tm where tm.id=:id");
        query.setParameter("id",id);
        return (ParticipanteCartaTmp) query.uniqueResult();
    }

    public List<DetalleParteTmp>getDetalleParteTmp()throws Exception{
       Session session = sessionFactory.getCurrentSession();
       Query query = session.createQuery("from DetalleParteTmp tm order by tm.id asc ");
       return query.list();
    }

    public List<DetalleParteTmp>getDetalleParteTmpById(int id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParteTmp tm where participantecartatmp.id=:id ");
        query.setParameter("id",id);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public boolean SiExisteParticipanteCartaTMP(Integer idversion, Integer idparticipante, String fechaCarta)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Date fecha = DateUtil.StringToDate(fechaCarta,"dd/MM/yyyy");
            Query query =session.createQuery("from ParticipanteCartaTmp pc where pc.fechacarta =:fecha and version.idversion =:idversion and idparticipante =:idparticipante");
            query.setParameter("idversion",idversion);
            query.setParameter("idparticipante",idparticipante);
            query.setParameter("fecha",fecha);
            return  query.list().size()>0;
        }
        catch (Exception e){
            throw e;
        }
    }

    public Participante getCodigoParticipante(int codigo)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Participante p where p.id=:codigo");
        query.setParameter("codigo",codigo);
        return (Participante) query.uniqueResult();
    }

    // GUARDAR/ACTUALIZAR EXTENSIONES TEMPORALES
    public void guardarExtensionTmp(ExtensionesTmp tmp)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tmp);
    }


    public boolean verificaSiyaTieneExtension(int codigo, int idExtension, String fechaext)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Date finicial = DateUtil.StringToDate(fechaext, "dd/MM/yyyy");
            Date ffinal = DateUtil.StringToDate(fechaext + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            Query query = session.createQuery("from ExtensionesTmp et where et.fechaExtension between :finicial and :ffinal and et.extensiones.id = :idExtension and et.idParticipantExtensiontmp = :codigo");
            query.setParameter("finicial", finicial);
            query.setParameter("ffinal", ffinal);
            query.setParameter("idExtension", idExtension);
            query.setParameter("codigo", codigo);
            return query.list().size() > 0;
        }catch (Exception e){
            return false;
        }
    }
    // Obtener todas las extensiones que no estan anuladas **
    public List<ParticipanteExtension>getAllExtensiones(){
        Session session = sessionFactory.getCurrentSession();
        boolean no = false;
        Query query = session.createQuery("from ParticipanteExtension pe where pe.anulada=:no order by pe.idParticipantExtension desc ");
        query.setParameter("no",no);
        return query.list();
    }

    public List<DetalleParteTmp>getList_Detalle_Parte_Tmp(Integer idParticipanteCartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParteTmp dt where dt.participantecartatmp.id = :idParticipanteCartatmp");
        query.setParameter("idParticipanteCartatmp",idParticipanteCartatmp);
        return  query.list();
    }


    //endregion



    //Metodo para verificar si la version tiene parte principal
    public List<Parte> listadoPartesPrincipales(int idversion)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        boolean si = true;
        Query query = session.createQuery("from Parte p where p.version.id=:idversion ");
        query.setParameter("idversion", idversion);
        return query.list();
    }


    // METODO PARA OBTENER LA PARTE PRINCIPAL POR VERSION
    public List<Parte> getPartePrincipal(int idversion){
        Session session = sessionFactory.getCurrentSession();
        boolean si = true;
        Query query = session.createQuery("from Parte p where p.activo=:si and p.principal=:si and p.version.idversion=:idversion");
        query.setParameter("idversion", idversion);
        query.setParameter("si", si);
        return (List<Parte>) query.list();
    }

    //region BUSCAR NOMBRE Y APELLIDOS TUTOR
    public List<String> getNombre1(String nombre1){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.nombre1Tutor from Participante p where p.nombre1Tutor like :nombre1");
        query.setParameter("nombre1", '%' + nombre1 + '%');
        return query.list();
    }
    public List<String> getNombre2(String nombre2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.nombre2Tutor from Participante p where p.nombre2Tutor like :nombre2");
        query.setParameter("nombre2", '%' + nombre2 + '%');
        return query.list();
    }
    public List<String> getApellido1(String apellido1){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.apellido1Tutor from Participante p where p.apellido1Tutor like :apellido1");
        query.setParameter("apellido1", '%' + apellido1 + '%');
        return query.list();
    }
    public List<String> getApellido2(String apellido2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct p.apellido2Tutor from Participante p where p.apellido2Tutor like :apellido2");
        query.setParameter("apellido2", '%' + apellido2 + '%');
        return query.list();
    }
    //endregion

    //region Eliminar todos Temporales por participantecartaTmp


    public int deleteDetalleParteTmp(Integer idParticipanteCartaTmp){
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("delete from DetalleParteTmp dp where dp.participantecartatmp.id =:idParticipanteCartaTmp");
            query.setParameter("idParticipanteCartaTmp", idParticipanteCartaTmp);
            int response =  query.executeUpdate();
            return response;
        }catch (Exception e){
            return 0;
        }
    }


    //Elimina todas las Extensiones

    public boolean isExistExtensionesById(int id)throws  Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ExtensionesTmp etmp where etmp.participantecartatmp.id=:id");
        query.setParameter("id", id);
        return query.list().size() > 0;
    }

    //Extensiones Temporal
    public ExtensionesTmp getExtensionTmpById(Integer idParticipantExtension){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ExtensionesTmp et where et.idParticipantExtensiontmp=:idParticipantExtension");
        query.setParameter("idParticipantExtension",idParticipantExtension);
        return (ExtensionesTmp) query.uniqueResult();
    }

    //Obtengo partes aceptadas
    public List<DetalleParteTmp>getDetalleParteTmpByAccept(int id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DetalleParteTmp tm where participantecartatmp.id=:id and tm.acepta=true");
        query.setParameter("id",id);
        return query.list();
    }


    public boolean Borrar_Participante_Carta_Extension(Integer idParticipanteCartatmp){
        try{
            Session session= sessionFactory.getCurrentSession();
            Query query = session.createQuery("delete from ExtensionesTmp et where et.participantecartatmp.id=:idParticipanteCartatmp");
            query.setParameter("idParticipanteCartatmp",idParticipanteCartatmp);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    // Elimina los registros de la tabla DetalleParteTmp por idParticipanteCartatmp
    public boolean Borrar_Detalle_Partes_tmp(Integer idParticipanteCartatmp)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("delete from DetalleParteTmp dpt where dpt.participantecartatmp.id=:idParticipanteCartatmp");
            query.setParameter("idParticipanteCartatmp", idParticipanteCartatmp);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean Borrar_Participante_Carta_Tmp(Integer idParticipanteCartatmp)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("delete from ParticipanteCartaTmp pct where pct.id=:idParticipanteCartatmp");
            query.setParameter("idParticipanteCartatmp", idParticipanteCartatmp);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    // Desact ExtensionesTmp by IdCartaTmp
    public int disableExtnsionTmpByIdCartaTmp(int participantecartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update ExtensionesTmp e set e.pasive='1' where e.participantecartatmp.id=:participantecartatmp ");
        query.setParameter("participantecartatmp",participantecartatmp);
        int response =  query.executeUpdate();
        return response;
    }
    // Dessact Detalles_Partes_Temp participantecartatmp.id
    public int disableDetailPartesTmpByIdCartaTmp(int participantecartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update DetalleParteTmp p set p.pasive='1' where p.participantecartatmp.id=:participantecartatmp ");
        query.setParameter("participantecartatmp",participantecartatmp);
        int response =  query.executeUpdate();
        return response;
    }
    // Dessact ParticipanteCartaTmp participantecartatmp.id
    public int disableParticipanteCartaTmpById(int participantecartatmp){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update ParticipanteCartaTmp p set p.pasive='1' where p.id=:participantecartatmp ");
        query.setParameter("participantecartatmp",participantecartatmp);
        int response =  query.executeUpdate();
        return response;
    }
    //endregion


    //TODO: --> * METODO PARA CONSULTAR TABLA SCAN * <--
    public List<scan> getDataScan(int idparticipante){
        Session session = sessionFactory.getCurrentSession();
        //int cons=10;
        String Sql = "from scan s where s.codigo=:idparticipante ";
        Query query = session.createQuery(Sql);
        query.setParameter("idparticipante", idparticipante);
        //query.setParameter("cons", cons);
        List<scan> list = query.list();
        for (scan a : list) {
            System.out.println("Codigo: "
                    + a.getCodigo()+" Fecha:"
                    + DateUtil.DateToString(a.getFecha(),"dd/MM/yyyy")+" Consentimiento:"
                    + a.getCons());
        }
        return list;
    }
}
