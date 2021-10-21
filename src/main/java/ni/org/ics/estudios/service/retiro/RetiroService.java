package ni.org.ics.estudios.service.retiro;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.Retiros.Retiros;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Personal_Cargo;
import ni.org.ics.estudios.domain.catalogs.Razones_Retiro;
import ni.org.ics.estudios.dto.HistorialRetiroDto;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.dto.ParticipanteSeroDto;
import ni.org.ics.estudios.dto.RetiroDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ICS on 29/10/2020.
 */
@Service("RetiroService")
@Transactional
public class RetiroService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<Personal> getPersonalRecibeRetiro()throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Personal order by nombre asc ");
        return query.list();
    }


    @SuppressWarnings("unchecked")
    public ParticipanteSeroDto getDatosParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as codigo, concat(p.nombre1,' ',p.nombre2,' ',p.apellido1,' ',p.apellido2) as nombreCompleto, p.casa.codigo as casaPediatrica, pp.casaCHF as casaFamilia, pp.estudio as estudios, p.fechaNac as fechaNacimiento, pp.estPart as estado " +
                " from Participante p, ParticipanteProcesos pp where p.codigo = pp.codigo and p.codigo= :codigo");
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(ParticipanteSeroDto.class));
        return (ParticipanteSeroDto)query.uniqueResult();
    }



    /* Este Servicio Devuelve un Participante por parametro, falta validar que el participante este activo */
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
    public void ActualizarCampoEstudio(Integer codigo, String estudioFinales) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update ParticipanteProcesos pp set pp.estudio= :estudioFinales where pp.codigo= :codigo");
        query.setParameter("codigo",codigo);
        query.setParameter("estudioFinales",estudioFinales);
        query.executeUpdate();
    }

    //ESTE METODO ES PARA ACTUALIZAR 2 CAMPOS siempre y cuando sea 1 estudio
    @SuppressWarnings("unchecked")
    public void ActualizarCampoEstudioAndEstado(Integer codigo) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Integer inactivo = 0;
        String estudio ="";
        Query query = session.createQuery("update ParticipanteProcesos pp set pp.estudio= :estudio, pp.estPart= :inactivo where pp.codigo= :codigo");
        query.setParameter("estudio",estudio);
        query.setParameter("inactivo",inactivo);
        query.setParameter("codigo",codigo);
        query.executeUpdate();
    }

    //ESTE METODO ES PARA ACTUALIZAR VARIOS CAMPOS siempre y cuando tenga un solo estudio
    @SuppressWarnings("unchecked")
    public void ActualizarVariosCampos(Integer codigo) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Integer inactivo = 0;
        String estudio ="";
        String no = "No";
        Query query = session.createQuery("update ParticipanteProcesos pp set pp.estudio= :estudio, pp.estPart= :inactivo, pp.pesoTalla=:no, " +
                " pp.encLacMat=:no, pp.encPart=:no, pp.pbmc=:no, pp.paxgene=:no where pp.codigo= :codigo");
        query.setParameter("estudio",estudio);
        query.setParameter("inactivo",inactivo);
        query.setParameter("no",no);
        query.setParameter("codigo",codigo);
        query.executeUpdate();
    }

    public void ActualizarCampoActual(Integer codigo)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Boolean falso = false;
        Query query = session.createQuery("update Retiros r set r.actual= :falso where r.participante.codigo= :codigo");
        query.setParameter("codigo",codigo);
        query.setParameter("falso",falso);
        query.executeUpdate();

    }
    /* antiguo metodo para obteneer supervisores
    public List<Personal> getSupervisor()throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Integer cargoId = 14;
        String verdad = "true";
        Query query = session.createQuery("from Personal p where p.cargo.codigo= :cargoId and p.activochf= :verdad");
        query.setParameter("cargoId",cargoId);
        query.setParameter("verdad",verdad);
        return query.list();
    }

 */

      public Personal getSupervisorById(Integer idpersona)throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Integer cargoId = 14;
        String verdad = "true";
        Query query = session.createQuery("from Personal p where p.idPersona= :idpersona and p.cargo.codigo= :cargoId and p.activochf= :verdad");
        query.setParameter("idpersona",idpersona);
        query.setParameter("cargoId",cargoId);
        query.setParameter("verdad",verdad);
        Personal obj = (Personal) query.uniqueResult();
        return obj;
    }



    //Obtener Supervisor y Directora.
    @SuppressWarnings("unchecked")
    public List<Personal_Cargo> getSupervisor()throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Integer  cargoId [] = { 4, 14, 6, 15 };
        boolean verdad = true;
        Query query = session.createQuery("from Personal_Cargo pc where pc.cargo.codigo in (:cargoId) and pc.estado= :verdad");
        query.setParameterList("cargoId",cargoId);
        query.setParameter("verdad",verdad);
        return query.list();
    }


    //OBTENER SUPERVISOR Y PERSONAL DE MUESTREO
    @SuppressWarnings("unchecked")
    public List<Personal_Cargo> getSupervisorAndDigitador()throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Integer  cargoId [] = { 1, 4, 7, 14 };
        boolean verdad = true;
        Query query = session.createQuery("from Personal_Cargo pc where pc.cargo.codigo in (:cargoId) and pc.estado= :verdad");
        query.setParameterList("cargoId",cargoId);
        query.setParameter("verdad",verdad);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Razones_Retiro> getRazonesRetiros(Integer grupo)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Razones_Retiro r where r.grupoid= :grupo");
        query.setParameter("grupo", grupo);
        return (List<Razones_Retiro>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Retiros> getListadoRetiroByID(Integer idParticipante) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Retiros r where r.participante.codigo =:idParticipante order by r.fecharetiro asc");
            query.setParameter("idParticipante", idParticipante);
            return query.list();
        }catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public Retiros getRetiroByID(Integer idretiro) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Retiros r where r.idretiro =:idretiro order by r.fecharetiro desc ");
            query.setParameter("idretiro", idretiro);
            Retiros objRetiro = (Retiros) query.uniqueResult();
            return objRetiro;
        }catch (Exception e){
            throw e;
        }
    }


    public Razones_Retiro getRazonRetiro(String razon)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query  =  session.createQuery("from Razones_Retiro rr where rr.motivo= :razon");
            query.setParameter("razon", razon);
            return (Razones_Retiro) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }


    /*Update campo Actual a 0 todos los existentes*/
    public void allActualBaja(Integer codigoPart) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Boolean result = false;
            String consulta = "UPDATE Retiros r SET r.actual = :result WHERE r.participante.codigo =:codigoPart";
            Query query = session.createQuery(consulta);
            query.setParameter("result", result);
            query.setParameter("codigoPart", codigoPart);
            query.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public void SaveRetiros(Retiros obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }


    //region documentacion_retiro_data_to_mysql (HISTORIAL)
    @SuppressWarnings("unchecked")
    public List<RetiroDto>ListOfRetiro(Integer parametro2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RetiroDto r where codigo_participante= :parametro2");
        query.setParameter("parametro2",parametro2);
        return  query.list();
    }
    //endregion


    //esto es por si es solo estudio de Familia..
    public boolean procesosEnNoFamilia(Integer codigo) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String no = "No";
            Integer stado = 0;
            String casaChf = "";
            String noStudio = "";
            Query query = session.createQuery("update ParticipanteProcesos pp set pp.pesoTalla= :no, pp.encLacMat= :no, pp.encPart= :no, pp.consFlu= :no, pp.consDeng= :no, pp.infoVacuna= :no, pp.enCasa= :no, pp.enCasaChf= :no, pp.enCasaSa= :no, pp.paxgene= :no, pp.pbmc= :no " +
                    " ,pp.estPart= :stado, pp.estudio= :noStudio, pp.casaCHF= :casaChf where pp.codigo= :codigo");
            query.setParameter("codigo", codigo);
            query.setParameter("no", no);
            query.setParameter("stado", stado);
            query.setParameter("noStudio",noStudio);
            query.setParameter("casaChf",casaChf);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean procesosAllEnNo(Integer codigo) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String no = "No";
            Integer stado = 0;
            String casaChf = "";
            String noStudio = "";
            Query query = session.createQuery("update ParticipanteProcesos pp set pp.pesoTalla= :no, pp.encLacMat= :no, pp.encPart= :no, pp.consFlu= :no, pp.consDeng= :no, pp.infoVacuna= :no, pp.enCasa= :no, pp.enCasaChf= :no, pp.enCasaSa= :no, pp.paxgene= :no, pp.pbmc= :no " +
                    " ,pp.estPart= :stado, pp.estudio= :noStudio, pp.casaCHF= :casaChf where pp.codigo= :codigo");
            query.setParameter("codigo", codigo);
            query.setParameter("no", no);
            query.setParameter("stado", stado);
            query.setParameter("noStudio",noStudio);
            query.setParameter("casaChf",casaChf);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }


    @SuppressWarnings("unchecked")
    public void updateEstudiosYcasaFamilia(Integer codigo, String estudioFinales) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        String vacio = "";
        String no = "No";
        Query query = session.createQuery("update ParticipanteProcesos pp set pp.estudio= :estudioFinales, pp.casaCHF=:vacio, pp.enCasaChf=:no, pp.obsequioChf=:no, pp.reConsChf18=:no, pp.consChf=:no where pp.codigo= :codigo");
        query.setParameter("estudioFinales",estudioFinales);
        query.setParameter("vacio",vacio);
        query.setParameter("no",no);
        query.setParameter("codigo",codigo);
        query.executeUpdate();
    }


    //Metodo para actualizar retiro de flu pero hay mas retiros
    public boolean procesosEnNoFlu(Integer codigo){
        try{
            Session session = sessionFactory.getCurrentSession();
            String no = "No";
            Query query = session.createQuery("update ParticipanteProcesos pp set pp.consFlu=:no, pp.infoVacuna=:no, PP.datosParto=:no, encLacMat=:no, pp.pesoTalla=:no where pp.codigo=:codigo");
            query.setParameter("no",no);
            query.setParameter("codigo",codigo);
            query.executeUpdate();
            return true;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    //Metodo para actualizar retiro de DENGUE pero hay mas retiros
    public boolean procesosEnNoDengue(Integer codigo){
        try{
            Session session = sessionFactory.getCurrentSession();
            String no = "No";
            Query query = session.createQuery("update ParticipanteProcesos pp set pp.consDeng=:no, pp.reConsDeng=:no,pp.posDengue=:no where pp.codigo=:codigo");
            query.setParameter("no",no);
            query.setParameter("codigo",codigo);
            query.executeUpdate();
            return true;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    //Metodo para activar procesos de UO1
    public boolean procesosEnNoUO1(Integer codigo){
        try{
            Session session = sessionFactory.getCurrentSession();
            String no = "No";
            Query query = session.createQuery("update ParticipanteProcesos pp set pp.infoVacuna=:no, pp.encLacMat=:no where pp.codigo=:codigo");
            query.setParameter("no",no);
            query.setParameter("codigo",codigo);
            query.executeUpdate();
            return true;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    //Metodo para actualizar procesos de Covid
    public boolean procesosEnNoCovid(Integer codigo){
        try{
            Session session = sessionFactory.getCurrentSession();
            String no = "No";
            String substudy = "0";
            Query query = session.createQuery("update ParticipanteProcesos pp set pp.consCovid19=:no, pp.subEstudios=:substudy, pp.cuestCovid=:no, pp.consCovid19=:no where pp.codigo=:codigo");
            query.setParameter("no",no);
            query.setParameter("substudy",substudy);
            query.setParameter("codigo",codigo);
            query.executeUpdate();
            return true;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


}
