package ni.org.ics.estudios.service.Domicilios;

import ni.org.ics.estudios.domain.DatosCoordenadas;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.dto.CoordenadasParticipanteDto;
import ni.org.ics.estudios.dto.ParticipantesCodigo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ICS_Inspiron3 on 16/07/2019.
 */
@Service("DomicilioService")
@Transactional
public class DomicilioService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;


/*
    @SuppressWarnings("unchecked")
    public List<DomicilioPdviDto>ListOfPDVI(Integer parametro2)throws Exception{
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from DomicilioPdviDto where  codigo =:parametro2 order by fechacambio desc ");
            //Query query=session.createQuery("select cast(dp.id as integer ) as Id ,(case when dp.codigo_casa_chf is null then 'ninguna' else dp.codigo_casa_chf end ) ,(case when dp.codigo_casa_pdcs is null then 'ninguna' else dp.codigo_casa_pdcs end ) , dp.ndireccion, b.nombre as NameBarrio, dp.codigo, dp.manzana, dp.origen from DomicilioPdviDto dp, Barrio b where dp.barrio = b.codigo and dp.codigo =:parametro");
            //Query query = session.createQuery("select  dp, b.nombre as NombreBarrio from DomicilioPdviDto dp, Barrio b where dp.barrio = b.codigo and dp.codigo =:parametro ");
            //Query query = session.createQuery("from DomicilioPdviDto dp inner  join Barrio b on  dp.barrio = b.codigo and dp.codigo =:parametro");
            query.setParameter("parametro2",parametro2);
            return  query.list();
        }catch (Exception e){
            throw e;
        }
    }
*/



    // Búsca participante por codigo
    @SuppressWarnings("unchecked")
    public ParticipantesCodigo getCodigos(Integer parametro)throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("SELECT cast( p.codigo as string ) as codigo, cast(c.codigo as string) as casaP, coalesce( pp.estudio, 'Vacio') as estudio, " +
                    "(SELECT cc.codigoCHF from ParticipanteCohorteFamilia pc, CasaCohorteFamilia cc where pc.casaCHF = cc.codigoCHF and pc.participante = p.codigo) as casaFamilia " +
                    "FROM Participante p, Casa c, ParticipanteProcesos pp where p.casa = c.codigo and p.codigo = pp.codigo  and p.codigo =:parametro");
            query.setParameter("parametro", parametro);
            query.setResultTransformer(Transformers.aliasToBean(ParticipantesCodigo.class));
            return (ParticipantesCodigo) query.uniqueResult();
        }catch (Exception e){
            throw e;
        }
    }
    /*poblar el Select Usuario*/
    public List<Personal> ListPersonal(List<Integer> ids)throws Exception{
        try{
            //2022-01-07. Se solicita aparezcan todos los cargos. Jairo Carey
            Session session = sessionFactory.getCurrentSession();
            // Query query = session.createQuery("from Personal order by Nombre");
            Query query  = session.createQuery("select distinct p from Personal_Cargo pc inner join pc.personal p inner join pc.cargo c where c.idcargo in (:ids) order by p.idpersonal" );
        query.setParameterList("ids", ids);
            return query.list();
        }catch (Exception e)
        { throw e;}
    }

     @SuppressWarnings("unchecked")
     public List<CoordenadasParticipanteDto> CoordenadasParticipante(Integer parametro)throws Exception{
         try
         {
             Session session = sessionFactory.getCurrentSession();
             //Query query = session.createQuery("from DatosCoordenadas where participante.codigo =:parametro order by actual desc ");
             //Query query = session.createSQLQuery("from DatosCoordenadas d left join Personal p  on d.recurso1 = p.idPersona where d.participante.codigo = :parametro");
             /*Query query = session.createSQLQuery("SELECT d.CODIGO,d.CODIGO_PARTICIPANTE, d.CODIGO_CASA,d.CODIGO_CHF, fecha_reportado, " +
                     "d.CODIGO_BARRIO, b.NOMBRE,case when d.OTRO_BARRIO ='' then '-' ELSE d.OTRO_BARRIO END AS oBarrio,d.MANZANA,d.DIRECCION,d,per.nombre AS NombrePersona,d.OBSERVACION " +
                     " FROM datos_coordenadas AS d LEFT JOIN Personal AS per ON d.idPersona = per.idpersonal " +
                     "INNER JOIN barrios AS b ON d.CODIGO_BARRIO = b.CODIGO and codigo_participante = :parametro ORDER BY d.fecha_registro asc");*/
             Query query = session.createSQLQuery("SELECT d.CODIGO,d.CODIGO_PARTICIPANTE, d.CODIGO_CASA,d.CODIGO_CHF, fecha_reportado, cast(d.FECHA_REGISTRO AS DATE) AS FECHA_REGISTRO, " +
                     "d.CODIGO_BARRIO, b.NOMBRE,case when d.OTRO_BARRIO ='' then '-' ELSE d.OTRO_BARRIO END AS oBarrio,d.MANZANA,d.DIRECCION,d.idPersona,per.NOMBRE_APELLIDO AS NombrePersona,d.OBSERVACION " +
                     " FROM datos_coordenadas AS d LEFT JOIN cat_personal AS per ON d.idPersona = per.PERSONA_ID " +
                     "INNER JOIN barrios AS b ON d.CODIGO_BARRIO = b.CODIGO and codigo_participante = :parametro ORDER BY d.fecha_registro asc");

             /*Query query = session.createSQLQuery("SELECT d.CODIGO,d.CODIGO_PARTICIPANTE, d.CODIGO_CASA,d.CODIGO_CHF, d.fecha_reportado, d.CODIGO_BARRIO, " +
                     "b.NOMBRE,case when d.OTRO_BARRIO ='' then '-' ELSE d.OTRO_BARRIO END AS oBarrio, " +
                     "d.MANZANA,d.DIRECCION, " +
                     "per.NOMBRE_APELLIDO AS NombrePersona, " +
                     "d.OBSERVACION " +
                     "    FROM datos_coordenadas AS d LEFT JOIN cat_personal AS per ON d.idPersona = per.PERSONA_ID " +
                     "     INNER JOIN barrios AS b ON d.CODIGO_BARRIO = b.CODIGO AND d.codigo_participante =:parametro ORDER BY d.fecha_registro asc");*/
             query.setParameter("parametro",parametro);
             return query.list();
         }catch (Exception e){
             throw e;
         }
     }


    public List<CoordenadasParticipanteDto>convertToDtoCoordenadas(Integer parametro){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d.codigo as CODIGO, " +
                "d.participante.codigo AS CODIGO_PARTICIPANTE, " +
                "d.codigoCasa AS CODIGO_CASA, " +
                "d.casacohortefamilia AS CODIGO_CHF, " +
                "d.direccion AS DIRECCION, " +
                "d.fechaReportado AS FECHA_REPORTADO, " +
                "d.movilInfo.today AS FECHA_REGISTRO, " +
                " d.movilInfo.username as NOMBRE_USUARIO, "+
                "CASE WHEN d.otroBarrio ='' THEN '-' ELSE d.otroBarrio END AS OTRO_BARRIO, " +
                "d.barrio.codigo AS CODIGO_BARRIO, " +
                "(SELECT b.nombre FROM Barrio b WHERE b.codigo=d.barrio.codigo ) AS NOMBRE, " +
                "d.recurso1 AS idPersona," +
                "(SELECT p.nombreApellido FROM Personal p WHERE p.idpersonal = d.recurso1 ) AS NombrePersona,  " +
                "d.manzana AS STRING_MANZANA, " +
                " d.observacion AS OBSERVACION " +
                "FROM DatosCoordenadas AS d WHERE d.participante.codigo = :parametro ");
        query.setParameter("parametro",parametro);
        query.setResultTransformer(Transformers.aliasToBean(CoordenadasParticipanteDto.class));
        return query.list();
    }


    /* Metodo para Guardar el Datos Coordenadas */
    @SuppressWarnings("unchecked")
    public void SaveDomicilio(DatosCoordenadas obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }

    /*Update campo Actual*/
    public void UpdateActual(String codigo)throws Exception{
        try {
            Session session = sessionFactory.getCurrentSession();
            String consulta = "UPDATE DatosCoordenadas SET actual = '0' WHERE codigo =:codigo";
            Query query = session.createQuery(consulta);
            query.setParameter("codigo",codigo);
            query.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }

    /*Update campo Actual a 0 todos los existentes*/
    public void allActualBaja(Integer codigoPart) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            String consulta = "UPDATE DatosCoordenadas SET actual = '0' WHERE participante.codigo =:codigoPart";
            Query query = session.createQuery(consulta);
            query.setParameter("codigoPart", codigoPart);
            query.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }


    /***** SERVICIOS PARA LAS NUEVAS CASAS *******/

     /* --  Verificar si Existe una hoja con identica  -- */
    @SuppressWarnings("unchecked")
    public boolean SiExisteHouse(Integer cod) throws Exception {
        try {
            boolean bandera = false;
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Casa c where " + "c.codigo =:cod");
            query.setParameter("cod", cod);
            if (query.list().size() > 0) {
                bandera = true;
            }
            return bandera;
        }catch (Exception e){
            throw e;
        }
    }

    /************/

    //region Cambiar Casa.


    // Obtener Participantes por Codigo de Casa Pediatrica
    public List<Participante> getParticipantesByCodigoCasa(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Participante p, ParticipanteProcesos pp where p.codigo = pp.codigo and p.casa.codigo =:codigo and p.pasive = '0'" +
                " and pp.estPart = 1");
        query.setParameter("codigo", codigo);
        return query.list();
    }

    //endregion
}
