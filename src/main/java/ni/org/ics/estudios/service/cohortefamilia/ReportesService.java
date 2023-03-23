package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaFallidaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaFinalCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaSeguimientoCaso;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Miguel Salinas on 9/8/2017.
 * V1.0
 */
@Transactional
@Service("reportesService")
public class ReportesService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private String getDesCatalogo(List<MessageResource> catalogo, String catKey){
        for(MessageResource cat : catalogo){
            if (cat.getCatKey().equals(catKey)){
                return cat.getSpanish();
            }
        }
        return null;
    }

    public List<Object[]> getHistorialVisitas(String codigoCasa, Integer codigoParticipante, Date fechaInicio, Date fechaFin) throws ParseException {
        List<Object[]> resultado = new ArrayList<Object[]>();
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from MessageResource where catRoot in ('CHF_CAT_VISITA_NO_C','CHF_CAT_VISITA_NO_P') and messageKey != 'CHF_CAT_VISITA_NO_C_04' and pasive = '0'");
        List<MessageResource> catalogo = query.list();
        List<ParticipanteCohorteFamiliaCaso> participantes = new ArrayList<ParticipanteCohorteFamiliaCaso>();
        List<VisitaSeguimientoCaso> visitas = new ArrayList<VisitaSeguimientoCaso>();
        List<VisitaFallidaCaso> visitasFall = new ArrayList<VisitaFallidaCaso>();
        //participante.participante.codigo = 9920 and 11055
        //participante.participante.codigo = 6271 and
        //participante.participante.codigo = 9942 and
        //participante.participante.codigo = 7519 and
        StringBuilder sbQuery = new StringBuilder("from ParticipanteCohorteFamiliaCaso where pasive = '0' ");

        if (codigoCasa!=null && !codigoCasa.isEmpty()){
            sbQuery.append("and participante.casaCHF.codigoCHF = :casa ");
        }
        if (codigoParticipante!=null){
            sbQuery.append("and participante.participante.codigo = :participante ");
        }
        sbQuery.append("order by codigoCaso.codigoCaso, participante.participante.codigo");
        query = session.createQuery(sbQuery.toString());
        if (codigoCasa!=null && !codigoCasa.isEmpty()){
            query.setParameter("casa", codigoCasa);
        }
        if (codigoParticipante!=null){
            query.setParameter("participante", codigoParticipante);
        }
        participantes = query.list();
        for(ParticipanteCohorteFamiliaCaso parti : participantes){
            sbQuery = new StringBuilder("from VisitaSeguimientoCaso where codigoParticipanteCaso.codigoCasoParticipante = :codigo ");
            if (fechaInicio!=null && fechaFin!=null) {
                sbQuery.append("and fechaVisita  between :fechaInicio and :fechaFin ");
            }
            sbQuery.append("order by visita asc, fechaVisita asc");
            query = session.createQuery(sbQuery.toString());

            query.setParameter("codigo", parti.getCodigoCasoParticipante());
            if (fechaInicio!=null && fechaFin!=null){
                query.setParameter("fechaInicio",fechaInicio);
                query.setParameter("fechaFin",fechaFin);
            }
            visitas = query.list();
            int numVisita = 1;
            int indiceV = 1;

            //buscar si tiene visita final
            query = session.createQuery("from VisitaFinalCaso where codigoParticipanteCaso.codigoCasoParticipante = :codigo");
            query.setParameter("codigo", parti.getCodigoCasoParticipante());
            VisitaFinalCaso visitaFinal = (VisitaFinalCaso)query.uniqueResult();

            for(VisitaSeguimientoCaso visita : visitas){
                Object[] dato = new Object[10];
                dato[0] = parti.getCodigoCaso().getCodigoCaso();
                dato[1] = parti.getCodigoCaso().getCasa().getCodigoCHF();
                dato[2] = parti.getParticipante().getParticipante().getCodigo();
                dato[3] = visita.getVisita();
                dato[4] = visita.getFechaVisita();
                dato[5] = "";
                dato[6] = "";
                dato[7] = parti.getCodigoCasoParticipante();
                dato[8] = "N";
                dato[9] = parti.getCodigoCaso().getFechaInicio();
                int numVisitaActiva = Integer.valueOf(visita.getVisita());
                //significa que no hay visita 1 exitosa, bucar visitas fallidas anteriores a la visita actual en el ciclo
                if (numVisita==1 && numVisitaActiva>1){
                    query = session.createQuery("from VisitaFallidaCaso where codigoParticipanteCaso.codigoCasoParticipante = :codigo" +
                            " and fechaVisita < :inicio order by fechaVisita asc");
                    query.setParameter("codigo", parti.getCodigoCasoParticipante());
                    query.setParameter("inicio", visita.getFechaVisita());

                    visitasFall = query.list();

                    int numVisitaFall = 0;
                    Date fvisitaFallAnterior = new Date();
                    for(VisitaFallidaCaso visitaFallida : visitasFall){
                        if (!DateUtil.DateToString(visita.getFechaVisita(),"dd/MM/yyyy").equalsIgnoreCase(DateUtil.DateToString(visitaFallida.getFechaVisita(),"dd/MM/yyyy"))) {
                            if (!DateUtil.DateToString(fvisitaFallAnterior, "dd/MM/yyyy").equalsIgnoreCase(DateUtil.DateToString(visitaFallida.getFechaVisita(), "dd/MM/yyyy"))) {
                                numVisitaFall = numVisita++;
                            }
                            Object[] datoF = new Object[10];
                            datoF[0] = parti.getCodigoCaso().getCodigoCaso();
                            datoF[1] = parti.getCodigoCaso().getCasa().getCodigoCHF();
                            datoF[2] = parti.getParticipante().getParticipante().getCodigo();
                            datoF[3] = (visitaFallida.getVisita() != null ? visitaFallida.getVisita() : numVisitaFall);
                            datoF[4] = visitaFallida.getFechaVisita();
                            datoF[5] = getDesCatalogo(catalogo, visitaFallida.getRazonVisitaFallida());
                            datoF[6] = visitaFallida.getOtraRazon();
                            datoF[7] = parti.getCodigoCasoParticipante();
                            datoF[8] = "S";
                            datoF[9] = parti.getCodigoCaso().getFechaInicio();
                            resultado.add(datoF);
                            fvisitaFallAnterior = visitaFallida.getFechaVisita();
                        }
                    }
                }
                Object[] ultimoRegistro = null;
                if (resultado.size()>0) ultimoRegistro = resultado.get(resultado.size()-1);
                resultado.add(dato);
                if (numVisita!=numVisitaActiva){
                    //hay un salto entre las visitas exitosas, entonces buscar las fallidas entre ambas fechas
                    if (numVisita<numVisitaActiva && ultimoRegistro!=null){

                        query = session.createQuery("from VisitaFallidaCaso where codigoParticipanteCaso.codigoCasoParticipante = :codigo" +
                                " and fechaVisita between :inicio and :fin order by fechaVisita asc");
                        query.setParameter("codigo", parti.getCodigoCasoParticipante());
                        query.setParameter("inicio", (Date)ultimoRegistro[4]);
                        query.setParameter("fin", DateUtil.StringToDate(DateUtil.DateToString(visita.getFechaVisita(), "dd/MM/yyyy"), "dd/MM/yyyy")); //como viene con hora, se extrae solo la fecha para que no traiga visitas del mismo dia de la visita exitosa
                    }else{
                        Calendar fin = GregorianCalendar.getInstance();
                        fin.setTime(visita.getFechaVisita());
                        fin.add(Calendar.DAY_OF_MONTH, 1);
                        query = session.createQuery("from VisitaFallidaCaso where codigoParticipanteCaso.codigoCasoParticipante = :codigo" +
                                " and fechaVisita between :inicio and :fin order by fechaVisita asc");
                        query.setParameter("codigo", parti.getCodigoCasoParticipante());
                        query.setParameter("inicio", visita.getFechaVisita());
                        query.setParameter("fin", DateUtil.StringToDate(DateUtil.DateToString(fin.getTime(), "dd/MM/yyyy"), "dd/MM/yyyy")); //como viene con hora, se extrae solo la fecha  para que no traiga visitas del mismo dia de la visita exitosa
                    }

                    visitasFall = query.list();

                    Date fvisitaFallAnterior = new Date();
                    String numVisitaFall = "";
                    for(VisitaFallidaCaso visitaFallida : visitasFall){
                            Object[] datoF = new Object[10];
                            datoF[0] = parti.getCodigoCaso().getCodigoCaso();
                            datoF[1] = parti.getCodigoCaso().getCasa().getCodigoCHF();
                            datoF[2] = parti.getParticipante().getParticipante().getCodigo();
                            datoF[4] = visitaFallida.getFechaVisita();
                            datoF[5] = getDesCatalogo(catalogo, visitaFallida.getRazonVisitaFallida());
                            datoF[6] = visitaFallida.getOtraRazon();
                            datoF[7] = parti.getCodigoCasoParticipante();
                            datoF[8] = "S";
                            datoF[9] = parti.getCodigoCaso().getFechaInicio();
                            /*Se ordena lista, según fecha de visita (que debería coincidir con el num de visita)*/
                            Date fecha1 = visitaFallida.getFechaVisita();
                            ultimoRegistro = resultado.get(resultado.size() - 1);
                            Date fecha2 = (Date) ultimoRegistro[4];
                            if (fecha1.compareTo(fecha2) < 0) {
                                datoF[3] = (Integer.valueOf(ultimoRegistro[3].toString()) - 1);
                                resultado.set(resultado.size() - 1, datoF);
                                resultado.add(ultimoRegistro);
                            } else {
                                if (!DateUtil.DateToString(fvisitaFallAnterior,"dd/MM/yyyy").equalsIgnoreCase(DateUtil.DateToString(visitaFallida.getFechaVisita(),"dd/MM/yyyy"))){
                                    numVisitaFall = String.valueOf(++numVisitaActiva);
                                }
                                datoF[3] = (visitaFallida.getVisita()!=null? visitaFallida.getVisita(): numVisitaFall);
                                resultado.add(datoF);
                            }
                        fvisitaFallAnterior = visitaFallida.getFechaVisita();
                    }
                }
                //es la ultima visita exitosa, se buscan fallidas posteriores a la ultima exitosa
                if (indiceV==visitas.size()){

                    if (visitaFinal==null) {
                        query = session.createQuery("from VisitaFallidaCaso where codigoParticipanteCaso.codigoCasoParticipante = :codigo" +
                                " and fechaVisita > :inicio order by fechaVisita asc");
                    }else{
                        query = session.createQuery("from VisitaFallidaCaso where codigoParticipanteCaso.codigoCasoParticipante = :codigo" +
                                " and fechaVisita > :inicio and fechaVisita <:fin order by fechaVisita asc");
                        query.setParameter("fin", DateUtil.StringToDate(DateUtil.DateToString(visitaFinal.getFechaVisita(),"dd/MM/yyyy"),"dd/MM/yyyy")); //como viene con hora, se extrae solo la fecha para que no traiga visitas del mismo dia de la visita exitosa
                    }
                    query.setParameter("codigo", parti.getCodigoCasoParticipante());
                    query.setParameter("inicio", visita.getFechaVisita());

                    visitasFall = query.list();
                    Date fvisitaFallAnterior = new Date();
                    String numVisitaFall = "";

                    for(VisitaFallidaCaso visitaFallida : visitasFall){
                        Calendar fin = GregorianCalendar.getInstance();
                        fin.setTime(visita.getFechaVisita());
                        fin.add(Calendar.DAY_OF_MONTH, 15);//visita final
                        if (!DateUtil.DateToString(visita.getFechaVisita(),"dd/MM/yyyy").equalsIgnoreCase(DateUtil.DateToString(visitaFallida.getFechaVisita(),"dd/MM/yyyy"))) {
                            if (visitaFallida.getFechaVisita().compareTo(fin.getTime())>0){
                                if (visitaFinal==null)
                                    numVisitaFall = "F";
                                else
                                    numVisitaFall = "N"; //no agregar porque ya tiene final exitosa

                            }else if (!DateUtil.DateToString(fvisitaFallAnterior,"dd/MM/yyyy").equalsIgnoreCase(DateUtil.DateToString(visitaFallida.getFechaVisita(),"dd/MM/yyyy"))){
                                numVisitaFall = String.valueOf(++numVisitaActiva);
                            }
                            if (!numVisitaFall.equalsIgnoreCase("N")) {//solo agregar si no es final o no tiene final exitosa y es final fallida
                                Object[] datoF = new Object[10];
                                datoF[0] = parti.getCodigoCaso().getCodigoCaso();
                                datoF[1] = parti.getCodigoCaso().getCasa().getCodigoCHF();
                                datoF[2] = parti.getParticipante().getParticipante().getCodigo();
                                datoF[3] = (visitaFallida.getVisita() != null ? visitaFallida.getVisita() : numVisitaFall);
                                datoF[4] = visitaFallida.getFechaVisita();
                                datoF[5] = getDesCatalogo(catalogo, visitaFallida.getRazonVisitaFallida());
                                datoF[6] = visitaFallida.getOtraRazon();
                                datoF[7] = parti.getCodigoCasoParticipante();
                                datoF[8] = "S";
                                datoF[9] = parti.getCodigoCaso().getFechaInicio();
                                resultado.add(datoF);
                            }
                        }
                        fvisitaFallAnterior = visitaFallida.getFechaVisita();
                    }
                    if (visitaFinal!=null) {
                        Object[] datoF = new Object[10];
                        datoF[0] = parti.getCodigoCaso().getCodigoCaso();
                        datoF[1] = parti.getCodigoCaso().getCasa().getCodigoCHF();
                        datoF[2] = parti.getParticipante().getParticipante().getCodigo();
                        datoF[3] = "F";
                        datoF[4] = visitaFinal.getFechaVisita();
                        datoF[5] = "";
                        datoF[6] = "";
                        datoF[7] = parti.getCodigoCasoParticipante();
                        datoF[8] = "N";
                        datoF[9] = parti.getCodigoCaso().getFechaInicio();
                        resultado.add(datoF);
                    }
                }
                indiceV++;
                numVisita++;
            }
        }

        return resultado;
    }

    public List<Object[]> getHistorialVisitas2(){
        List<Object[]> resultado = new ArrayList<Object[]>();
        Session session = sessionFactory.getCurrentSession();

        Query query = null;

        Query query2 = session.createQuery("select c.codigoCaso, c.casa.codigoCHF, p.participante.participante.codigo, f.visita, f.fechaVisita, '' as razonVisitaFallida, '' as otraRazon, p.codigoCasoParticipante, 'N' as fallida, c.fechaInicio " +
                "from VisitaSeguimientoCaso f inner join f.codigoParticipanteCaso p inner join p.codigoCaso c " +
                "where p.participante.participante.codigo in (6271) and c.codigoCaso = 'F9Uh-MVA7-AwxK-ebH5-9UQb-KxbU-dt9f-s'" +//''8sZU-r6ol-Np20-YwuZ-9Epy-4SwM-8Lg5-8' "+ ///p.participante.participante.codigo in (5827) and
                "order by c.codigoCaso, p.participante.participante.codigo, f.visita");

        List<Object[]> datos = query2.list();
        //datos.addAll(query2.list());

        String caso="";
        Integer participante=null;
        Integer casa;
        int visita = 1;
        Date ultimaVisita = null;
        Object[] ultimoRegistro = new Object[9];
        for(Object[] dato: datos){
            if (caso.isEmpty())
                caso = dato[0].toString();
            if (participante==null)
                participante = (Integer)dato[2];
            if (caso.equalsIgnoreCase(dato[0].toString()) && participante.equals((Integer)dato[2]) && visita!=Integer.valueOf(dato[3].toString())){
                //if (visita<Integer.valueOf(dato[3].toString())) {
                query = session.createQuery("select c.codigoCaso, c.casa.codigoCHF, p.participante.participante.codigo, (coalesce(f.visita, 0)) as visita, f.fechaVisita, " +
                        "(select spanish from MessageResource where catRoot in ('CHF_CAT_VISITA_NO_C','CHF_CAT_VISITA_NO_P') and messageKey != 'CHF_CAT_VISITA_NO_C_04' and catKey = f.razonVisitaFallida), f.otraRazon, f.codigoParticipanteCaso.codigoCasoParticipante, 'S' as fallida, c.fechaInicio  " +
                        "from VisitaFallidaCaso f inner join f.codigoParticipanteCaso p inner join p.codigoCaso c " +
                        "where c.codigoCaso = :caso and p.codigoCasoParticipante = :pcaso " +
                        "and f.fechaVisita > :fvisita" +
                        " order by f.fechaVisita asc");
                /*}else{

                }*/

                query.setParameter("caso", dato[0].toString());
                query.setParameter("pcaso", dato[7].toString());
                query.setParameter("fvisita", ultimaVisita);
                List<Object[]> fallidas = query.list();
                if (fallidas.size()>0){

                    Object[] tmp = fallidas.get(0);
                    if ((Integer)tmp[3]==0) {
                        tmp[3] = visita;
                    }else{
                        visita = (Integer)tmp[3];
                    }

                    /*Se ordena lista, según fecha de visita (que debería coincidir con el num de visita)*/
                    Date fecha1 = (Date)tmp[4];
                    ultimoRegistro = resultado.get(resultado.size()-1);
                    Date fecha2 = (Date)ultimoRegistro[4];
                    if (fecha1.compareTo(fecha2)<0){
                        resultado.set(resultado.size()-1, tmp);
                        resultado.add(ultimoRegistro);
                    }else {
                        resultado.add(tmp);
                    }
                    //visita++;
                    ultimaVisita = (Date)tmp[4];
                }

            }else{
                ultimaVisita = (Date)dato[4];
            }
            if (!caso.equalsIgnoreCase(dato[0].toString()))
                caso = dato[0].toString();

            if (!participante.equals((Integer)dato[2])){
                participante = (Integer)dato[2];
                visita =1;
            }
            resultado.add(dato);

            visita++;

        }
        return resultado;
    }
}
