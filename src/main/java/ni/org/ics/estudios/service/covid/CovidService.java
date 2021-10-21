package ni.org.ics.estudios.service.covid;

import ni.org.ics.estudios.domain.covid19.*;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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

    public List<CasoCovid19> getCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasoCovid19 c where c.pasive = '0' and c.inactivo = '0'");
        return query.list();
    }

    public CasoCovid19 getCasoCovid19ByCodigo(String codigoCaso){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasoCovid19 c where c.codigoCaso = :codigoCaso");
        query.setParameter("codigoCaso", codigoCaso);
        return (CasoCovid19)query.uniqueResult();
    }

    public CasoCovid19 getCasoCovid19ByCasaChf(String casaChf){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasoCovid19 c where c.casa.codigoCHF = :casaChf and c.pasive = '0' and c.inactivo = '0'");
        query.setParameter("casaChf", casaChf);
        return (CasoCovid19)query.uniqueResult();
    }

    /**
     * Obtener datos de participante según su código para validar si aplica o no en la activación del caso
     * @param codigo Código de participante
     * @return ParticipanteBusquedaDto
     */
    public ParticipanteBusquedaDto getDatosParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p.codigo as codigo, p.casa.codigo as casaPediatrica, pp.casaCHF as casaFamilia, pp.estudio as estudios, pp.subEstudios as subEstudios, pp.estPart as estado " +
                "from Participante p, ParticipanteProcesos pp where p.codigo = pp.codigo and p.codigo= :codigo");
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(ParticipanteBusquedaDto.class));
        return (ParticipanteBusquedaDto)query.uniqueResult();
    }

    /***
     * Obtiene una lista de todos los participantes en monitoreo
     */
    public List<ParticipanteCasoCovid19> getParticipantesCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.inactivo = '0'");
        return query.list();
    }

    public ParticipanteCasoCovid19 getParticipanteCasoCovid19ByCodCasoPart(String codigoCasoParticipante){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.codigoCasoParticipante = :codigoCasoParticipante");
        query.setParameter("codigoCasoParticipante", codigoCasoParticipante);
        return (ParticipanteCasoCovid19)query.uniqueResult();
    }

    /***
     * Obtiene un participante de monitoreo por código de participante reportado como positivo
     * @param codigo Código de participante
     * @return ParticipanteCasoCovid19
     */
    public ParticipanteCasoCovid19 getParticipanteCasoCovid19Pos(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.inactivo = '0' and p.enfermo <> 'N' and p.participante.codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCasoCovid19)query.uniqueResult();
    }

    /***
     * Obtiene un participante de monitoreo por código de participante reportado como positivo y codigo caso
     * @param codigo Código de participante
     * @return ParticipanteCasoCovid19
     */
    public ParticipanteCasoCovid19 getParticipanteCasoCovid19ByCodigoAndCodCaso(Integer codigo, String codigoCaso){
        Session session = sessionFactory.getCurrentSession();
        //Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.inactivo = '0' and p.enfermo <> 'N' and p.participante.codigo = :codigo and p.codigoCaso.codigoCaso = :codigoCaso");
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.inactivo = '0' and p.participante.codigo = :codigo and p.codigoCaso.codigoCaso = :codigoCaso");
        query.setParameter("codigo", codigo);
        query.setParameter("codigoCaso", codigoCaso);
        return (ParticipanteCasoCovid19)query.uniqueResult();
    }

    //  Metodo para obtener listado casos positivos / no positivos Codvid
    @SuppressWarnings("unchecked")
    public List<ParticipanteCasoCovid19> getParticipanteCasosPositivosCovid(){

        List<ParticipanteCasoCovid19> participanteCasoCovid19s;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.enfermo = 'I' and p.codigoCaso.casa is not null and p.codigoCaso.pasive = '0'");
        participanteCasoCovid19s = query.list();
        query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.enfermo = 'S' and p.codigoCaso.casa is null and p.codigoCaso.pasive = '0'");
        participanteCasoCovid19s.addAll(query.list());
        return participanteCasoCovid19s;
    }

    @SuppressWarnings("unchecked")
    public boolean esParticipanteIndiceCasoCovidByCodigo(int codParticipante){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select count(p.codigoCasoParticipante) from ParticipanteCasoCovid19 p where p.pasive = '0' and p.enfermo = 'I' and p.codigoCaso.pasive = '0' and p.participante.codigo = :codParticipante");
        query.setParameter("codParticipante", codParticipante);
        return (Long) query.uniqueResult()>0;
    }

    public boolean esParticipanteMiembroCasoCovidByCodigo(int codParticipante){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(" select count(p.codigoCasoParticipante) from ParticipanteCasoCovid19 p where p.pasive = '0' and p.enfermo = 'N' and p.codigoCaso.pasive = '0' and p.participante.codigo = :codParticipante");
        query.setParameter("codParticipante", codParticipante);
        return query.uniqueResult()!=null;
    }

    //Seleccionar los participantes
    @SuppressWarnings("unchecked")
    public List<ParticipanteCasoCovid19> getParticipantesCasoCovid19ByCodigoCaso(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.pasive = '0' and p.codigoCaso.codigoCaso = :codigo order by p.participante.codigo");
        query.setParameter("codigo", codigo);
        return query.list();
    }

    public ParticipanteCasoCovid19 getParticipanteIndiceCasoCovid19ByCodigoCaso(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.enfermo = 'I' and p.pasive = '0' and p.codigoCaso.pasive = '0' and p.codigoCaso.codigoCaso = :codigo ");
        query.setParameter("codigo", codigo);
        return (ParticipanteCasoCovid19)query.uniqueResult();
    }

    public List<VisitaSeguimientoCasoCovid19> getVisitaSeguimientoCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaSeguimientoCasoCovid19 v where v.pasive = '0' and v.codigoParticipanteCaso.codigoCaso.inactivo = '0'");
        return query.list();
    }

    public List<VisitaFallidaCasoCovid19> getVisitasFallidasCasosActivosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaFallidaCasoCovid19 v where v.pasive = '0' and v.codigoParticipanteCaso.codigoCaso.inactivo = '0'");
        return query.list();
    }

    public CandidatoTransmisionCovid19 getCandidatoTransmisionCovid19(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CandidatoTransmisionCovid19 v where v.pasive = '0' and v.codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (CandidatoTransmisionCovid19)query.uniqueResult();
    }

    public List<CandidatoTransmisionCovid19> getCandidatosTransmisionCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CandidatoTransmisionCovid19 v where v.pasive = '0' order by fechaIngreso asc ");
        return query.list();
    }

    /**
     * Obtiene todos los candidatos PENDIENTE para realizar tamizaje caso indice
    * */
    public List<CandidatoTransmisionCovid19> getCandidatosPendientesTransmisionCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CandidatoTransmisionCovid19 v where v.pasive = '0' and v.consentimiento = 'PENDIENTE'");
        return query.list();
    }

    /***
     * Obtiene una lista con los SintomasVisitaCasoCovid19 de los casos activos en seguimiento
     * @return List<SintomasVisitaCasoCovid19>
     */
    public List<SintomasVisitaCasoCovid19> getSintomasVisitasCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SintomasVisitaCasoCovid19 s where s.pasive = '0' and s.codigoCasoVisita.pasive = '0' and s.codigoCasoVisita.codigoParticipanteCaso.codigoCaso.inactivo = '0'");
        return query.list();
    }

    public List<DatosAislamientoVisitaCasoCovid19> getDatosAislamientoVisitasCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DatosAislamientoVisitaCasoCovid19 d where d.pasive = '0' and d.codigoCasoVisita.pasive = '0' and d.codigoCasoVisita.codigoParticipanteCaso.codigoCaso.inactivo = '0'");
        return query.list();
    }

    /***
     * Obtiene una lista con las visitas finales de casos activos covid19
     * @return List<VisitaFinalCasoCovid19>
     */
    public List<VisitaFinalCasoCovid19> getVisitasFinalesCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaFinalCasoCovid19 d where d.pasive = '0' and d.pasive = '0' and d.codigoParticipanteCaso.codigoCaso.inactivo = '0'");
        return query.list();
    }

    /***
     * Obtiene una lista con los sintomas de visitas finales de casos activos covid19
     * @return List<SintomasVisitaFinalCovid19>
     */
    public List<SintomasVisitaFinalCovid19> getSintomasVisitasFinalesCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SintomasVisitaFinalCovid19 s where s.pasive = '0' and s.codigoVisitaFinal.pasive = '0' and s.codigoVisitaFinal.codigoParticipanteCaso.codigoCaso.inactivo = '0'");
        return query.list();
    }


    /***
     * Obtiene una lista con los sintomas de visitas finales de casos activos covid19
     * @return List<CuestionarioCovid19>
     */
    public List<CuestionarioCovid19> getCuestionariosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CuestionarioCovid19 s where s.pasive = '0'");
        return query.list();
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

    public void saveOrUpdateParticipanteCovid19(ParticipanteCovid19 participanteCovid19){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participanteCovid19);
    }

    public void saveOrUpdateVisitaSeguimientoCasoCovid19(VisitaSeguimientoCasoCovid19 visitaSeguimientoCasoCovid19){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visitaSeguimientoCasoCovid19);
    }

    public void saveOrUpdateVisitaFallidaCasoCovid19(VisitaFallidaCasoCovid19 visitaFallidaCasoCovid19){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visitaFallidaCasoCovid19);
    }

    public void saveOrUpdateCandidatoTransmisionCovid19(CandidatoTransmisionCovid19 candidato){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(candidato);
    }

    public void saveOrUpdateSintomasVisitaCasoCovid19(SintomasVisitaCasoCovid19 sintoma){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(sintoma);
    }

    public void saveOrUpdateDatosAislamientoVisitaCasoCovid19(DatosAislamientoVisitaCasoCovid19 dato){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(dato);
    }

    public void saveOrUpdateVisitaFinalCasoCovid19(VisitaFinalCasoCovid19 visita){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visita);
    }

    public void saveOrUpdateSintomasVisitaFinalCovid19(SintomasVisitaFinalCovid19 sintomas){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(sintomas);
    }

    public void saveOrUpdateCuestionarioCovid19(CuestionarioCovid19 cuestionario){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cuestionario);
    }

    //Verifico q no exista un indice para la casa familia con misma fecha
    public Integer existeIndice(String cod_casaF, Date fi, Date ff){
        Session session = sessionFactory.getCurrentSession();
        Character indice='1';
        Query query = session.createQuery("select count(c.indice) from CandidatoTransmisionCovid19 c where c.fechaIngreso between :fi and :ff and  c.casaCHF=:cod_casaF and c.indice=:indice");
        query.setParameter("fi", fi);
        query.setParameter("ff", ff);
        query.setParameter("cod_casaF", cod_casaF);
        query.setParameter("indice", indice);
        Integer result = ((Long)query.iterate().next()).intValue();
        return result;
    }

    // Obtengo los participantes por codigo casa CHF
    public List<ParticipanteProcesos>obtenerParticipanteByCasaCHF(String casaChf){
        Session session = sessionFactory.getCurrentSession();
        String vacio="";
        Query query = session.createQuery("from ParticipanteProcesos pp where pp.casaCHF=:casaChf and pp.estudio<>:vacio");
        query.setParameter("casaChf",casaChf);
        query.setParameter("vacio",vacio);
        return query.list();
    }

    //Guardar o Actualizar covid_otros_positivos
    public void saveOrUpdateOtrosPositivos(OtrosPositivosCovid otros){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(otros);
    }

    // Verifica si existe en la tabla covid_otros_positivos
    public boolean verificaSiExiste(Integer idparticipante, Date inicio, Date finale){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OtrosPositivosCovid o where o.candidatoTransmisionCovid19.fechaIngreso between :inicio and :finale and o.candidatoTransmisionCovid19.participante.codigo=:idparticipante");
        query.setParameter("idparticipante",idparticipante);
        query.setParameter("inicio", inicio);
        query.setParameter("finale", finale);
        return query.list().size() > 0;
    }

    @SuppressWarnings("unchecked")
    public boolean verificaCasoYaExiste(String codigo, Integer idparticipante){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OtrosPositivosCovid  o where o.candidatoTransmisionCovid19.codigo=:codigo and o.codigo_participante=:idparticipante");
        query.setParameter("codigo",codigo);
        query.setParameter("idparticipante", idparticipante);
        return query.list().size()>0;
    }

    // Obtener de la tabla covid_otros_positivos
    public List<OtrosPositivosCovid> ObtenerOtrosPositivos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OtrosPositivosCovid o where o.pasive = '0' order by codigo desc ");
        return query.list();
    }
    // Obtener caso por id
    public OtrosPositivosCovid getOtrosPositivoTransmisionCovid19(Long codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OtrosPositivosCovid v where v.pasive = '0' and v.codigo =:codigo");
        query.setParameter("codigo", codigo);
        return (OtrosPositivosCovid)query.uniqueResult();
    }

    // Verifica si no se ha cerrado la casa de familia.
    @SuppressWarnings("unchecked")
    public boolean getCasoEsActivo(String casaChf){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasoCovid19 cc where cc.casa.codigoCHF =:casaChf and cc.inactivo ='0' and cc.fechaInactivo =null ");
        query.setParameter("casaChf", casaChf);
        return query.list().size()>0;
    }

    public boolean VerificarSiCasoActivoPorFecha(String casaChf, Date finicio, Date ffin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasoCovid19 cc where cc.fechaIngreso between :finicio and :ffin and cc.casa.codigoCHF=:casaChf and cc.inactivo='0' and fechaInactivo=null ");
        query.setParameter("casaChf",casaChf);
        query.setParameter("finicio",finicio);
        query.setParameter("ffin",ffin);
        return query.list().size()>0;
    }

    // Capturar el Caso indice en la tabla covid_candidato_transmision
    public CandidatoTransmisionCovid19 getIdCandidatoTransmisionCovid19(  Date fi, Date ff,Integer idparticipante,String casaChf){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CandidatoTransmisionCovid19 c where c.fechaIngreso between :fi and :ff and c.participante.codigo=:idparticipante and c.casaCHF=:casaChf");
        query.setParameter("fi",fi);
        query.setParameter("ff",ff);
        query.setParameter("idparticipante",idparticipante);
        query.setParameter("casaChf",casaChf);
        return (CandidatoTransmisionCovid19) query.uniqueResult();
    }


    public CandidatoTransmisionCovid19 getByIdCasoIndice(String codigoCaso){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CandidatoTransmisionCovid19 c where c.codigo=:codigoCaso");
        query.setParameter("codigoCaso", codigoCaso);
        return (CandidatoTransmisionCovid19) query.uniqueResult();
    }


    //Metodo para obtener el Miembro del Hogar para luego modificarlo
    public ParticipanteCasoCovid19 getByIdAndParticipanteId(String codigo_caso, int participanteId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.codigoCaso.codigoCaso=:codigo_caso and p.participante.codigo=:participanteId");
        query.setParameter("codigo_caso",codigo_caso);
        query.setParameter("participanteId",participanteId);
        return (ParticipanteCasoCovid19) query.uniqueResult();
    }

    /**
     * Obtiene todos los otros positivos asociados a los candidatos PENDIENTE para realizar tamizaje caso indice
     * */
    public List<OtrosPositivosCovid> getOtrosPositivosPendientesTransmisionCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select op from OtrosPositivosCovid op inner join op.candidatoTransmisionCovid19 v where op.pasive = '0' and v.pasive = '0' and v.consentimiento = 'PENDIENTE'");
        return query.list();
    }
}
