package ni.org.ics.estudios.service.comparacion;

import ni.org.ics.estudios.domain.catalogs.Personal_Cargo;
import ni.org.ics.estudios.domain.muestreoanual.MuestraMA;
import ni.org.ics.estudios.domain.muestreoanual.RecepcionBHC;
import ni.org.ics.estudios.domain.muestreoanual.RecepcionSero;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.users.model.UserSistema;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.Date;

/**
 * Created by ICS on 07/01/2021.
 */
@Service("ComparacionService")
@Transactional
public class ComparasionService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /*Metodo para Guardar Tabla recepcionBHC*/
    //region SERVICIO BHC

    @SuppressWarnings("unchecked")
    public Integer SaveBHC(RecepcionBHC details) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(details);
            return  1;
        }catch (Exception e){
          System.out.println("Error: "+e.getMessage());
            return 0;
        }
    }

    //METODO PARA EDITAR
    public boolean editbhc(RecepcionBHC obj)throws Exception{
        boolean bandera = false;
        try{
            Session session = sessionFactory.getCurrentSession();
            session.update(obj);
            System.out.println(bandera);
            return bandera = true;
        }catch (Exception e){
            System.out.println(e.getMessage()+bandera);
            return bandera = false;
        }
    }

    public int editarManual(String estado, int codigo, String fechaRegistBhc,  String lugar,String observa, boolean paxgene, String user, double vol, Date star, Date fin)throws Exception{

        try
        {
            Session session = sessionFactory.getCurrentSession();
            Date fecreg = DateUtil.StringToDate(fechaRegistBhc,"dd/MM/yyyy HH:mm:ss");
            Query query = session.createQuery("UPDATE RecepcionBHC r set r.estado=:estado, r.fecreg=:fecreg, r.lugar=:lugar, r.observacion=:observa, r.paxgene=:paxgene, r.username=:user ,r.volumen=:vol " +
                    " where r.recBhcId.fechaRecBHC between :star and :fin and  r.recBhcId.codigo=:codigo");
            query.setParameter("estado",estado);
            query.setParameter("codigo",codigo);
            query.setParameter("fecreg",fecreg);
            query.setParameter("lugar",lugar);
            query.setParameter("observa",observa);
            query.setParameter("paxgene",paxgene);
            query.setParameter("user",user);
            query.setParameter("vol",vol);
            query.setParameter("star",star);
            query.setParameter("fin",fin);
            int result = query.executeUpdate();
            return result;
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    //OBTENER BHC POR CODIGO Y FECHA
    public RecepcionBHC getRecepcionBhcById(Integer codigo, Date dfechabhc, Date enDate)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionBHC r where r.recBhcId.fechaRecBHC BETWEEN :dfechabhc and :enDate and r.recBhcId.codigo= :codigo ");
        query.setParameter("codigo", codigo);
        query.setParameter("dfechabhc", dfechabhc);
        query.setParameter("enDate",enDate);
        return (RecepcionBHC) query.uniqueResult();
    }

    public List<RecepcionBHC> getAllBhc()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        Integer a = year;
        Query query = session.createQuery("from RecepcionBHC r where year(r.recBhcId.fechaRecBHC)=:a order by r.recBhcId.fechaRecBHC desc");
        query.setParameter("a",a);
        return (List<RecepcionBHC>) query.list();
    }

    //METODO PARA ELIMINAR
    public int deletebhc(Integer codigo, Date fecha,Date enDate)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE RecepcionBHC r where r.recBhcId.fechaRecBHC BETWEEN :fecha and :enDate and r.recBhcId.codigo= :codigo");
        query.setParameter("fecha",fecha);
        query.setParameter("enDate",enDate);
        query.setParameter("codigo",codigo);
        int result = query.executeUpdate();
        return result;
    }

    public List<RecepcionBHC> getBhcById(Integer codigo)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionBHC r where r.recBhcId.codigo=:codigo");
        query.setParameter("codigo", codigo);
        return (List<RecepcionBHC>) query.list();
    }

    public List<UserSistema> getUsuarios()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        boolean habil = true;
        String sup = "sup";
        Query query = session.createQuery("from UserSistema us where us.enabled= :habil and username like :sup ");
        query.setParameter("habil",habil);
        query.setParameter("sup", '%' + sup + '%');
        return query.list();
    }

    public List<String> getNombreUsuario(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct u.username from UserSistema u where u.username like :username and u.enabled=true ");
        query.setParameter("username", '%' + username + '%');
        return query.list();
    }

    public ParticipanteBusquedaDto getDatosParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as codigo, p.casa.codigo as casaPediatrica, pp.casaCHF as casaFamilia, pp.estudio as estudios, pp.subEstudios as subEstudios, pp.estPart as estado " +
                "from Participante p, ParticipanteProcesos pp where p.codigo = pp.codigo and p.codigo= :codigo");
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(ParticipanteBusquedaDto.class));
        return (ParticipanteBusquedaDto)query.uniqueResult();
    }
//endregion


    //region Metodos para SEROLOGIA


    //Guardar Serologia
    public void GuardarSerologia(RecepcionSero obj)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }
    public Integer ModificarSerologia(RecepcionSero objUp)throws Exception{
        try {
        Session session = sessionFactory.getCurrentSession();
        Integer cod = objUp.getCodigo();
        String est = objUp.getEstado();
        Date fechaSero = objUp.getFechaRecSero();
        Date fechaReg = objUp.getFecreg();
        String lug = objUp.getLugar();
        String obs = objUp.getObservacion();
        String userN = objUp.getUsername();
        Double vol = objUp.getVolumen();
        String id = objUp.getId();
        Query query = session.createQuery("update RecepcionSero rs set rs.codigo =:cod, rs.estado=:est, rs.fechaRecSero=:fechaSero, rs.fecreg=:fechaReg, rs.lugar=:lug, rs.observacion=:obs, rs.username=:userN, rs.volumen=:vol where rs.id=:id");
        query.setParameter("cod", cod);
        query.setParameter("est", est);
        query.setParameter("fechaSero", fechaSero);
        query.setParameter("fechaReg", fechaReg);
        query.setParameter("lug", lug);
        query.setParameter("obs", obs);
        query.setParameter("userN", userN);
        query.setParameter("vol", vol);
        query.setParameter("id", id);
        Integer result = query.executeUpdate();
        return result;
    }catch (Exception e){
        System.out.println(e.getMessage());
        return 0;
    }
    }

    //Lista todas las Serologia
    public List<RecepcionSero> getAllSerologias()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        Integer a = year;
        Query query = session.createQuery("from RecepcionSero s where year(s.fecreg) =:a order by s.fecreg desc");
        query.setParameter("a",a);
        return query.list();
    }

    public Integer DeleteSerologiaById(String id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE RecepcionSero s where s.id= :id");
        query.setParameter("id",id);
        Integer result = query.executeUpdate();
        System.out.println("resultado: "+ result);
        return result;
    }

    //OBTENER BHC POR CODIGO
    public RecepcionSero getSerologiaById(String id)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionSero s where s.id= :id ");
        query.setParameter("id", id);
        return (RecepcionSero) query.uniqueResult();
    }
    //endregion




    //region RECEPCION DE MUESTRAS

    public void SaveMuestra(MuestraMA objMx)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(objMx);
    }

    public boolean editMuestra(MuestraMA objMx)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            session.update(objMx);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Integer deleteMuestra(Integer codigo, Date finicio, Date ffin)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("DELETE MuestraMA m WHERE m.movilInfo.today BETWEEN :finicio AND :ffin AND m.mId.codigo = :codigo");
            query.setParameter("finicio", finicio);
            query.setParameter("ffin", ffin);
            query.setParameter("codigo", codigo);
            Integer result = query.executeUpdate();
            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public MuestraMA getMuestraByID(Integer id, Date fstar, Date fend)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM MuestraMA m WHERE m.mId.fechaMuestra BETWEEN :fstar AND :fend AND m.mId.codigo = :id");
        query.setParameter("id",id);
        query.setParameter("fstar",fstar);
        query.setParameter("fend",fend);
        return (MuestraMA) query.uniqueResult();
    }

    public List<MuestraMA>getAllMuestras()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        Integer a = year;
        Query query = session.createQuery("from MuestraMA m where year(m.mId.fechaMuestra)= :a");
        query.setParameter("a", a);
        return query.list();
    }


    public List<Personal_Cargo> getRecursos(HashSet<Integer> ids)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal_Cargo pc where pc.cargo.idcargo in (:cargoId) order by pc.personal.nombreApellido asc ");
        query.setParameterList("cargoId", ids);
        return query.list();
    }
    //endregion

}
