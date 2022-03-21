package ni.org.ics.estudios.service.Bhc;

import ni.org.ics.estudios.domain.Bhc.Bhc;
import ni.org.ics.estudios.domain.Bhc.Bhc_Detalle_envio;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Rango_Edad_Volumen;
import ni.org.ics.estudios.dto.BhcEnvioDto;
import ni.org.ics.estudios.service.UsuarioService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 16/02/2022.
 */
@Service("BhcService")
@Transactional
public class BhcService {


    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name="usuarioService")
    private UsuarioService usuarioService;


    public void saveOrUpdateBhc(Bhc bhc){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(bhc);
    }

    public List<Bhc> getAllBhc(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Bhc b where b.pasive='0' and b.enviado='0' ");
        return query.list();
    }

    public Bhc getBhcById(Integer idBhc){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Bhc  b where  b.bhc_id=:idBhc");
        query.setParameter("idBhc",idBhc);
        return (Bhc) query.uniqueResult();
    }

    /* Obtiene Un Participante por su codigo  */
    public Participante getParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Participante where codigo =:codigo");
        query.setParameter("codigo",codigo);
        return (Participante)query.uniqueResult();
    }


    @SuppressWarnings("unchecked")
    public Rango_Edad_Volumen getRangoEdadByTipoMuestra(int edad, String tipoMuestra, String estudio){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Rango_Edad_Volumen r where :edad between r.edad_meses_minima and r.getEdad_meses_maxima and r.tipo_muestra=:tipoMuestra and r.estudio=:estudio");
        query.setParameter("edad",edad);
        query.setParameter("tipoMuestra",tipoMuestra);
        query.setParameter("estudio",estudio);
        return (Rango_Edad_Volumen) query.uniqueResult();
    }

    public boolean ExisteBhc(Date fecha, Integer codigo) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Bhc b where b.fecha_bhc =:fecha and b.codigo_participante =:codigo and b.pasive='0' ");
            query.setParameter("fecha", fecha);
            query.setParameter("codigo", codigo);
            return  query.list().size()>0;

        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }

    public boolean yaTieneMuestraBhcAnual(int yearActual, Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Bhc b where year(b.fecha_bhc)=:yearActual and b.codigo_participante=:codigo and b.pasive='0' ");
        query.setParameter("yearActual", yearActual);
        query.setParameter("codigo", codigo);
        return query.list().size()>0;
    }

    //Contador Pbmc
    public Integer ContarBhc(Date f1, Date f2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from Bhc b where b.fecha_bhc between :f1 and :f2 and b.pasive='0' and b.enviado='0' ");
        query.setParameter("f1",f1);
        query.setParameter("f2",f2);
        Integer result = ((Long) query.iterate().next()).intValue();
        return result;
    }



    public List<Bhc>ObtenerBhcEnviadas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Bhc b where b.fecha_bhc between :fechaInicio and :fechaFin and b.enviado='0' and b.pasive='0' ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }


    @SuppressWarnings("unchecked")
    public void save_Detalles_bhc_Envio(Bhc_Detalle_envio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }

    // todo **  Consulta para llenar el reporte BHC **
    public List<Bhc_Detalle_envio>getBhcDetailsEnvio(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Bhc_Detalle_envio b where b.serologiaEnvio.fecha between :fechaInicio and :fechaFin and b.serologiaEnvio.idenvio =:nEnvios order by b.bhc.codigo_participante asc ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }

    public List<BhcEnvioDto> getReporteEnvioBhc(Integer nEnvios, Date fechaInicio, Date fechaFin) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select r.CODIGO_PARTICIPANTE as codigo, date(r.FECHA_TOMA) as fecha, r.volumen as volumen, r.observacion as observacion, " +
                "adn.ADN_DEN as adnDengue, adn.ADN_UO1 as adnUO1, adn.ADN_FLU as adnFlu, adn.ADN_CHF as adnChf, " +
                "r.USUARIO_REGISTRO as pRecepciona, r.estudio as estudios, " +
                "floor( r.EDAD_MESES/12) as edadA, r.EDAD_MESES mod 12 as edadM, e.NUMERO_ENVIO as viaje " +
                "from participantes p inner join bhc_recepcion r on p.CODIGO = r.CODIGO_PARTICIPANTE " +
                "inner join bhc_detalle_envio de on r.BHC_ID = de.BHC_ID " +
                "inner join envio_muestras e on de.ENVIO_MUESTRA_ID = e.ENVIO_MUESTRA_ID " +
                "inner join view_adn_consentimientos adn on adn.codigo = r.CODIGO_PARTICIPANTE " +
                "where e.FECHA_ENVIO between :fechaInicio and :fechaFin and e.NUMERO_ENVIO = :nEnvios " +
                "ORDER BY r.CODIGO_PARTICIPANTE asc");
        query.setResultTransformer(Transformers.aliasToBean(BhcEnvioDto.class));
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }

}
