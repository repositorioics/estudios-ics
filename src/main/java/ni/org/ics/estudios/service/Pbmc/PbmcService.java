package ni.org.ics.estudios.service.Pbmc;

import ni.org.ics.estudios.domain.Pbmc.Pbmc;
import ni.org.ics.estudios.domain.Pbmc.Pbmc_Detalle_Envio;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.catalogs.Rango_Edad_Volumen;
import ni.org.ics.estudios.dto.PbmcHorasToma;
import ni.org.ics.estudios.service.UsuarioService;
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
 * Created by ICS on 02/02/2022.
 */
@Service("PbmcService")
@Transactional
public class PbmcService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name="usuarioService")
    private UsuarioService usuarioService;


    public boolean saveOrUpdatePbmc(Pbmc pbmc){
        try{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(pbmc);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Pbmc> getAllPbmc(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Pbmc p where p.pasive ='0' and enviado='0' order by p.codigo_participante asc ");
        return query.list();
    }

    public Pbmc getPbmcByID(Integer id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Pbmc p where p.pbcm_id=:id");
        query.setParameter("id", id);
        return (Pbmc) query.uniqueResult();
    }

    public List<Pbmc>ListaPbmcNoEnviadas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Pbmc p where p.pasive='0' and p.enviado='0' order by p.pbcm_id asc ");
        return query.list();
    }



    // todo **  Consulta para llenar el reporte PBMC **
    public List<Pbmc_Detalle_Envio>getAllPbmc(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Pbmc_Detalle_Envio pe where pe.serologiaEnvio.fecha between :fechaInicio and :fechaFin and pe.serologiaEnvio.idenvio =:nEnvios  order by pe.pbmc.codigo_participante asc ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }

    public List<PbmcHorasToma> getHorasPbmc(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select m.codigo as codigo, date(m.fecha_muestra) as fechaToma, TIME_FORMAT(m.fecha_muestra, '%H:%i:%S') as horaToma, m.hora_pbmc as horaPbmc, m.tuborojo as tuboRojo, m.tuboleu as tuboPbmc " +
                "from muestras m, pbmc_recepcion p " +
                "inner join pbmc_detalle_envio de on p.ID_PBMC = de.ID_PBMC " +
                "inner join envio_muestras e on de.ENVIO_MUESTRA_ID = e.ENVIO_MUESTRA_ID " +
                "where date(m.fecha_muestra) = date(p.FECHA) and m.codigo = p.CODIGO_PARTICIPANTE " +
                "and e.NUMERO_ENVIO  = :nEnvios and date(e.FECHA_ENVIO) between :fechaInicio and :fechaFin " +
                "order by m.codigo asc");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);

        query.setResultTransformer(Transformers.aliasToBean(PbmcHorasToma.class));
        return query.list();
    }


    //region Verificar si existe registro Guardado.
    public boolean ExistePbmc(Date fecha, Integer codigo) throws Exception{
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Pbmc p where p.fecha_pbmc =:fecha and p.codigo_participante =:codigo");
            query.setParameter("fecha", fecha);
            query.setParameter("codigo", codigo);
            return  query.list().size()>0;
        }catch (Exception e){
            System.err.println(e.toString());
            throw e;
        }
    }
    public boolean yaTieneMuestraPbmcAnual(int yearActual, Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Pbmc p where year(p.fecha_pbmc)=:yearActual and p.codigo_participante=:codigo and p.pasive='0' ");
        query.setParameter("yearActual", yearActual);
        query.setParameter("codigo", codigo);
        return query.list().size()>0;
    }

    //endregion

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

    //Contador Pbmc
    public Integer ContarPbmc(Date f1, Date f2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from Pbmc p where p.fecha_pbmc between :f1 and :f2 and pasive='0' and p.enviado='0' ");
        query.setParameter("f1",f1);
        query.setParameter("f2",f2);
        Integer result = ((Long) query.iterate().next()).intValue();
        return result;
    }

    //todo: Método para enviar muestras de PBMC
    public List<Pbmc>ObtenerPbmcEnviadas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Pbmc p where p.fecha_pbmc between :fechaInicio and :fechaFin and p.enviado='0' and p.pasive='0' ");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public void save_Detalles_Pbmc_Envio(Pbmc_Detalle_Envio obj) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(obj);
        }catch (Exception e){
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public Serologia getSerologiaByPbmc_Id(Integer pbmc_id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Serologia s where s.codigoPbmc =:pbmc_id and s.pasive='0' and s.descripcion='PBMC'");
        query.setParameter("pbmc_id",pbmc_id);
        Serologia sero = (Serologia) query.uniqueResult();
        return sero;
    }

    @SuppressWarnings("unchecked")
    public Serologia getSerologiaByPbmc_Id_And_Serologia_Id(Integer pbmc_id, Integer sero_id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Serologia s where s.codigoPbmc =:pbmc_id and s.idSerologia=:sero_id and s.pasive='0'");
        query.setParameter("pbmc_id",pbmc_id);
        query.setParameter("sero_id",sero_id);
        Serologia sero = (Serologia) query.uniqueResult();
        return sero;
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


    //todo: llenar la tabla para el envio de serologia con Pbmc
    public List<Serologia>getPbmcConSerologia(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.pasive='0' and s.codigoPbmc>0 and s.descripcion='PBMC' and s.enviado='0' and s.volumen>0 order by s.participante asc ");
        return query.list();
    }

    //todo: Obtengo las serologia de PBMC para realizar el envío
    public List<Serologia> getSerologiaConPbmc( Date f1, Date f2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.fecha between :f1 and :f2 and pasive='0' and s.codigoPbmc>0 and s.descripcion='PBMC'");
        query.setParameter("f1", f1);
        query.setParameter("f2", f2);
        return query.list();
    }


    //todo: Obtengo las serologia de PBMC con envio en 0 para realizar el envío --> actual
    public List<Serologia> getSerologiaDePbmc( Date f1, Date f2){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Serologia s where s.fecha between :f1 and :f2 and pasive='0' and s.codigoPbmc>0 and s.enviado='0' and s.descripcion='Pbmc' and s.volumen>0");
        query.setParameter("f1", f1);
        query.setParameter("f2", f2);
        return query.list();
    }


    //aun no se utiliza..!
    public List<SerologiaEnvio> getSerologiaEnvioByDates(Integer nEnvios, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SerologiaEnvio se where se.fecha between :fechaInicio and :fechaFin and se.idenvio =:nEnvios");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        query.setParameter("nEnvios", nEnvios);
        return query.list();
    }

}
