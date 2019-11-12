package ni.org.ics.estudios.service.reportes;

import com.google.common.base.Predicate;
import ni.org.ics.estudios.domain.*;
import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaParticipante;
import ni.org.ics.estudios.domain.muestreoanual.EncuestaParticipanteMA;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.*;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.service.muestreoanual.EncuestaParticipanteService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.users.model.UserSistema;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.FilterLists;
import ni.org.ics.estudios.web.utils.pdf.DatosGeneralesParticipante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 19/10/2018.
 * V1.0
 */

@Service("reportesPdfService")
@Transactional
public class ReportesPdfService {
    @Resource(name = "participanteService")
    ParticipanteService participanteService;
    @Resource(name = "participanteProcesosService")
    ParticipanteProcesosService participanteProcesosService;
    @Resource(name = "messageResourceService")
    MessageResourceService messageResourceService;
    @Resource(name = "cartaConsentimientoService")
    CartaConsentimientoService cartaConsentimientoService;
    @Resource(name = "encuestaParticipanteMAService")
    EncuestaParticipanteService encuestaParticipanteMAService;
    @Resource(name = "tamizajeService")
    TamizajeService tamizajeService;
    @Resource(name = "usuarioService")
    UsuarioService usuarioService;
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    @Resource(name = "encuestaParticipanteService")
    private ni.org.ics.estudios.service.cohortefamilia.EncuestaParticipanteService encuestaParticipanteService;
    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;

    private final static String SI="SI";
    private final static String NO="NO";
    private final static String NINGUNO="NINGUNO";
    private final static String NINGUNA="NINGUNA";
    private final static String DESCONOCIDO="DESCONOCIDO";
    private final static String CSSF = "SOCRATES FLORES VIVAS";
    private List<MessageResource> catalogos = new ArrayList<MessageResource>();
    private List<UserSistema> usuarios = new ArrayList<UserSistema>();

    /***
     * Obtiene todas las cartas de consentimiento que coinciden con los parámetros de búsqueda especificados
     * Se ordena por fecha de firma (los mas antiguos primero) y por estudio (iniciando por familia)
     * Cuándo se filtran todos los estudios, se obtiene sólo la carta mas antigua
     * @param estudio codigo del estudio a filtrar, 0 si se desea filtrar por todos los estudios
     * @param codigoParticipante participante que se desea filtrar, null si no hay participante en específico
     * @param fechaInicio rango inicial de fecha de firma de carta de consentimiento, null si no hay rango
     * @param fechaFin rango final de fecha de firma de carta de consentimiento, null si no hay rango
     * @return List<CartaConsentimiento>
     */
    public List<CartaConsentimiento> getCartasConsentimientoByFiltro(Integer estudio, Integer codigoParticipante, Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        String sqlQuery = "select cc from CartaConsentimiento cc inner join cc.tamizaje t inner join cc.participante p " +
                "where 1=1 ";//(cc.reconsentimiento is null or cc.reconsentimiento = '0')

        //sub consulta para obtener la carta mas antigua
        String sqlSubQuery = "select min(cc2.fechaFirma) from CartaConsentimiento cc2 inner join cc2.tamizaje t2 inner join cc2.participante p2 " +
                "where p2.codigo = p.codigo "; //(cc2.reconsentimiento is null or cc2.reconsentimiento = '0') and

        if (estudio>0) {
            sqlQuery += " and t.estudio.codigo = :estudio";
            sqlSubQuery += " and t2.estudio.codigo = t.estudio.codigo";
        }
        if(codigoParticipante != null) {
            sqlQuery += " and p.codigo = :codigoParticipante";
        }
        if (fechaInicio!=null && fechaFin!=null) {
            sqlQuery += " and cc.fechaFirma between :fechaInicio and :fechaFin";
            sqlSubQuery += " and cc2.fechaFirma between :fechaInicio and :fechaFin";
        }
        sqlQuery += " and cc.fechaFirma = ("+sqlSubQuery+") order by cc.fechaFirma asc, t.estudio.codigo asc, p.codigo";

        Query query = session.createQuery(sqlQuery);

        if(codigoParticipante != null)
            query.setParameter("codigoParticipante", codigoParticipante);
        if (estudio>0)
            query.setParameter("estudio", estudio);
        if (fechaInicio!=null && fechaFin!=null) {
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
        }

        return query.list();
    }

    /**
     * Obtiene la descripción en lenguaje español de un mensaje
     * @param messageKey identificador del mansaje a evaluar
     * @return String si se encuentra el mensaje, vacío en caso contrario
     */
    private String getMensajeSpanishByMessageKey(final String messageKey){
        Predicate<MessageResource> predicate = new Predicate<MessageResource>() {
            @Override
            public boolean apply(MessageResource messageResource) {
                return messageResource.getMessageKey().equals(messageKey);
            }
        };
        Collection<MessageResource> resultado = FilterLists.filter(catalogos, predicate);
        return resultado.size()>0?resultado.iterator().next().getSpanish():"";
    }

    /**
     * Obtiene una mensaje tipo catálogo por medio del catalago principal y una llave de la lista
     * @param catKey key a evaluar
     * @param catalogo catálogo
     * @return MessageResource si es encontrado, null en caso contrario
     */
    private MessageResource getMensajeByCatalogAndCatKey(final String catKey, final String catalogo){
        Predicate<MessageResource> predicate = new Predicate<MessageResource>() {
            @Override
            public boolean apply(MessageResource messageResource) {
                return messageResource.getCatRoot()!=null && messageResource.getCatKey()!=null && messageResource.getCatRoot().equals(catalogo) && messageResource.getCatKey().equals(catKey);
            }
        };
        Collection<MessageResource> resultado = FilterLists.filter(catalogos, predicate);
        return resultado.size()>0?resultado.iterator().next():null;
    }

    /***
     * Obtiene una Colección de mensajes tipo catálogo por medio del catalago principal y varias llaves de la lista
     * @param catKeys key de los elementos dentro del catálogo
     * @param catalogo catálogo
     * @return Collection<MessageResource>
     */
    private Collection<MessageResource> getMensajesByCatalogAndCatKey(final String catKeys, final String catalogo){
        Predicate<MessageResource> predicate = new Predicate<MessageResource>() {
            @Override
            public boolean apply(MessageResource messageResource) {
                return messageResource.getCatRoot()!=null && messageResource.getCatKey()!=null && messageResource.getCatRoot().equals(catalogo) && catKeys.contains(messageResource.getCatKey());
            }
        };
        return FilterLists.filter(catalogos, predicate);
    }

    /***
     * Obtiene el nombre completo de un usuario del sistema mediante su nombre de usuario
     * @param username nombre de usuario a evaluar
     * @return String nombre completo
     */
    private String getUserCompleteNameByUsername(final String username){
        Predicate<UserSistema> predicate = new Predicate<UserSistema>() {
            @Override
            public boolean apply(UserSistema messageResource) {
                return messageResource.getUsername().equals(username);
            }
        };
        Collection<UserSistema> resultado = FilterLists.filter(usuarios, predicate);
        return resultado.size()>0?resultado.iterator().next().getCompleteName():"";
    }

    /**
     * Obtiene los datos generales de los participantes al momento de ingresar al primer estudio, según parámetros recibidos
     * @param estudio codigo del estudio a filtrar, 0 si se desea filtrar por todos los estudios
     * @param codigoParticipante participante que se desea filtrar, null si no hay participante en específico
     * @param fechaInicio rango inicial de fecha de firma de carta de consentimiento, null si no hay rango
     * @param fechaFin rango final de fecha de firma de carta de consentimiento, null si no hay rango
     * @return List<DatosGeneralesParticipante>
     */
    public List<DatosGeneralesParticipante> getDatosGeneralesParticipante(Integer estudio, Integer codigoParticipante, Date fechaInicio, Date fechaFin) {
        catalogos = messageResourceService.loadAllMessages();
        usuarios = usuarioService.getUsers();
        List<DatosGeneralesParticipante> resultado = new ArrayList<DatosGeneralesParticipante>();
        List<CartaConsentimiento> participantes = getCartasConsentimientoByFiltro(estudio, codigoParticipante, fechaInicio, fechaFin);
        MessageResource messageResource = null;
        Integer ultimoParticipante = 0;
        //Participante participante = participanteService.getParticipanteByCodigo(codigo);
        for(CartaConsentimiento cc: participantes) {
            Participante participante = cc.getParticipante();
            if (participante != null && !participante.getCodigo().equals(ultimoParticipante)) {
                DatosGeneralesParticipante dgp = new DatosGeneralesParticipante();
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(participante.getCodigo());
                List<ContactoParticipante> contactoParticipantes = participanteService.getContactosParticipante(participante.getCodigo());
                List<EncuestaParticipanteMA> encuestasParticipanteMA = new ArrayList<EncuestaParticipanteMA>();
                List<EncuestaParticipante> encuestasParticipante = new ArrayList<EncuestaParticipante>();

                dgp.setNombreCompleto(participante.getNombreCompleto());
                String edad[] = participante.getEdad().split("/");
                if (edad.length > 0) {
                    dgp.setEdad(edad[0]);
                }
                dgp.setCodigo(participante.getCodigo().toString());
                dgp.setBarrio(participante.getCasa().getBarrio().getNombre());
                dgp.setDireccion(participante.getCasa().getDireccion());
                dgp.setManzana(participante.getCasa().getManzana());
                dgp.setSexo(participante.getSexo());
                dgp.setFechaNacimiento(DateUtil.DateToString(participante.getFechaNac(), "dd/MM/yyyy"));

                String jefeFamilia = participante.getCasa().getNombre1JefeFamilia().toUpperCase();
                if (participante.getCasa().getNombre2JefeFamilia() != null)
                    jefeFamilia = jefeFamilia + " " + participante.getCasa().getNombre2JefeFamilia().toUpperCase();
                jefeFamilia = jefeFamilia + " " + participante.getCasa().getApellido1JefeFamilia().toUpperCase();
                if (participante.getCasa().getApellido2JefeFamilia() != null)
                    jefeFamilia = jefeFamilia + " " + participante.getCasa().getApellido2JefeFamilia().toUpperCase();
                dgp.setJefeFamilia(jefeFamilia);

                dgp.setTutor(procesos.getTutor());

                if (procesos.getRelacionFam() != null)
                    messageResource = this.getMensajeByCatalogAndCatKey(procesos.getRelacionFam().toString(), "CP_CAT_RFTUTOR");
                if (messageResource != null) dgp.setRelFamTutor(messageResource.getSpanish());

                String madre = participante.getNombre1Madre().toUpperCase();
                if (participante.getNombre2Madre() != null)
                    madre = madre + " " + participante.getNombre2Madre().toUpperCase();
                madre = madre + " " + participante.getApellido1Madre().toUpperCase();
                if (participante.getApellido2Madre() != null)
                    madre = madre + " " + participante.getApellido2Madre().toUpperCase();
                dgp.setMadre(madre);

                String padre = participante.getNombre1Padre().toUpperCase();
                if (participante.getNombre2Padre() != null)
                    padre = padre + " " + participante.getNombre2Padre().toUpperCase();
                padre = padre + " " + participante.getApellido1Padre().toUpperCase();
                if (participante.getApellido2Padre() != null)
                    padre = padre + " " + participante.getApellido2Padre().toUpperCase();
                dgp.setPadre(padre);

                for (ContactoParticipante contacto : contactoParticipantes) {
                    if (contacto.getEsPropio() != null && contacto.getEsPropio().equalsIgnoreCase("0")) {
                        dgp.setNombreContacto(contacto.getNombre());
                        dgp.setBarrioContacto(contacto.getBarrio()!=null?contacto.getBarrio().getNombre():"");
                        dgp.setTelefonosContacto((contacto.getNumero1() != null ? contacto.getNumero1() : "") + "   " + (contacto.getNumero2() != null ? contacto.getNumero2() : ""));
                        if (contacto.getDireccion()!=null && contacto.getDireccion().toUpperCase().contains("MISMA")) {
                            dgp.setDireccionContacto(participante.getCasa().getDireccion());
                        } else {
                            dgp.setDireccionContacto(contactoParticipantes.get(0).getDireccion());
                        }
                        break;
                    }
                }

                for (ContactoParticipante contacto : contactoParticipantes) {
                    if (contacto.getEsPropio() != null && contacto.getEsPropio().equalsIgnoreCase("1")) {
                        dgp.setTelefonos((contacto.getNumero1() != null ? contacto.getNumero1() : "") + "   " + (contacto.getNumero2() != null ? contacto.getNumero2() : ""));
                        break;
                    }
                }

                if (contactoParticipantes.size() <= 0) {
                    dgp.getMensajes().add(this.getMensajeSpanishByMessageKey("lbl.contact.not.found"));
                }

                //se toma la primera porque estan ordenadas de la mas reciente a la mas antigua
                //if (cartaConsentimientos.size() > 0) {
                    dgp.setFechaIngreso(DateUtil.DateToString(cc.getFechaFirma(), "dd/MM/yyyy"));
                    dgp.setDigitador(this.getUserCompleteNameByUsername(cc.getRecordUser()));

                    Tamizaje tamizaje = cc.getTamizaje();
                    if (tamizaje != null) {
                        messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getTipoVivienda(), "CP_CAT_TV");
                        if (messageResource != null) dgp.setTipoVivienda(messageResource.getSpanish());

                        messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getTiempoResidencia(), "CP_CAT_TR");
                        if (messageResource != null) dgp.setTiempoResidencia(messageResource.getSpanish());

                        //pediatrica
                        if (tamizaje.getCriteriosInclusion() != null && tamizaje.getCriteriosInclusion().length() == 3) {
                            if (tamizaje.getCriteriosInclusion().contains("2"))
                                dgp.setPermanecerTresAnios(SI);
                            else
                                dgp.setPermanecerTresAnios(NO);
                            //familia
                        } else if (tamizaje.getCriteriosInclusion() != null && tamizaje.getCriteriosInclusion().length() == 7) {
                            if (tamizaje.getCriteriosInclusion().contains("3"))
                                dgp.setPermanecerTresAnios(SI);
                            else
                                dgp.setPermanecerTresAnios(NO);
                        } else //cuando es reconsentimiento y estaba activo, no se guardan los criterios, se supone que ya los cumple
                            dgp.setPermanecerTresAnios(SI);
                        if (tamizaje.getDondeAsisteProblemasSalud() != null) {
                            messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getDondeAsisteProblemasSalud(), "CHF_CAT_DONDEASISTE");
                            if (messageResource != null) dgp.setDondeAsiste(messageResource.getSpanish());

                            switch (Integer.valueOf(tamizaje.getDondeAsisteProblemasSalud())) {
                                case 1: //Centro de Salud Sócrates Flores
                                    dgp.setUnidadAsiste(CSSF);
                                    break;
                                case 2: //Otro centro de salud
                                    dgp.setUnidadAsiste(tamizaje.getOtroCentroSalud());
                                    break;
                                case 3: //Puesto de Salud
                                    messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getPuestoSalud(), "CHF_CAT_PUESTO");
                                    if (messageResource != null) dgp.setUnidadAsiste(messageResource.getSpanish());
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (tamizaje.getAceptaAtenderCentro() != null) {
                            messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getAceptaAtenderCentro(), "CHF_CAT_SINO");
                            if (messageResource != null) dgp.setAsistirCSSF(messageResource.getSpanish().toUpperCase());
                        } else //cuando es reconsentimiento y estaba activo, no se guardan los criterios, se supone que ya los cumple
                            dgp.setAsistirCSSF(SI);

                        if (tamizaje.getEnfermedadCronica() != null) {
                            messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getEnfermedadCronica(), "CHF_CAT_SINO");
                            if (messageResource != null)
                                dgp.setEnfermedadCronica(messageResource.getSpanish().toUpperCase());
                            if (tamizaje.getEnfermedadCronica().equalsIgnoreCase("1")) {
                                List<MessageResource> enfermedades = messageResourceService.getMensajeEnfCronByTamizaje(tamizaje.getCodigo(), "ENFERMEDAD_CRN");
                                for (MessageResource enfermedad : enfermedades) {
                                    dgp.setCualEnfermedadCronica((dgp.getCualEnfermedadCronica().length() > 0 ? ", " : "") + dgp.getCualEnfermedadCronica() + enfermedad.getSpanish());
                                }
                            }

                        } else
                            dgp.setEnfermedadCronica(NO);

                        if (tamizaje.getTratamiento() != null) {
                            messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getTratamiento(), "CHF_CAT_SND");
                            if (messageResource != null)
                                dgp.setTomaTratamiento(messageResource.getSpanish().toUpperCase());
                            if (tamizaje.getCualTratamiento() != null) {
                                messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getCualTratamiento(), "CPD_CAT_TRATAMIENTO");
                                if (messageResource != null) dgp.setCualTratamiento(messageResource.getSpanish());
                            } else dgp.setTomaTratamiento(NO);
                        } else
                            dgp.setTomaTratamiento(NO);

                        if (tamizaje.getDiagDengue() != null) {
                            messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getDiagDengue(), "CHF_CAT_SND");
                            if (messageResource != null)
                                dgp.setTieneDxDengue(messageResource.getSpanish().toUpperCase());
                            if (tamizaje.getFechaDiagDengue() != null)
                                dgp.setFechaDxDengue(DateUtil.DateToString(tamizaje.getFechaDiagDengue(), "dd/MM/yyyy"));
                        } else
                            dgp.setTieneDxDengue(NO);

                        if (tamizaje.getHospDengue() != null) {
                            messageResource = this.getMensajeByCatalogAndCatKey(tamizaje.getHospDengue(), "CHF_CAT_SND");
                            if (messageResource != null)
                                dgp.setHospitalizadoDengue(messageResource.getSpanish().toUpperCase());
                            if (tamizaje.getFechaHospDengue() != null)
                                dgp.setFechaHospitalizadoDengue(DateUtil.DateToString(tamizaje.getFechaHospDengue(), "dd/MM/yyyy"));
                        } else
                            dgp.setHospitalizadoDengue(NO);

                        if (tamizaje.getEstudio().getCodigo().equals(1)){//familia
                            //al inicio se guardaba en la tabla chf_encuestas_participante
                            encuestasParticipante = encuestaParticipanteService.getEncuestaParticipanteByCodigo(participante.getCodigo());
                            if (!(encuestasParticipante.size()>0))//ya en MA2018 se guardaba en la tabla encuestas_participantes, pues se unificaron las encuestas
                                encuestasParticipanteMA = encuestaParticipanteMAService.getEncuestasParticipante(participante.getCodigo());
                        }else{
                            encuestasParticipanteMA = encuestaParticipanteMAService.getEncuestasParticipante(participante.getCodigo());
                        }
                    }
                /*} else {
                    dgp.getMensajes().add(this.getMensajeSpanishByMessageKey("lbl.consent.not.found"));
                }*/


                if (encuestasParticipante.size() > 0) {
                    if (encuestasParticipante.get(0).getVaNinoEscuela() != null) {
                        if (encuestasParticipante.get(0).getVaNinoEscuela().equalsIgnoreCase("S"))
                            dgp.setEstudiante(SI);
                        else if (encuestasParticipante.get(0).getVaNinoEscuela().equalsIgnoreCase("N"))
                            dgp.setEstudiante(NO);
                        else
                            dgp.setEstudiante(DESCONOCIDO);
                    } else
                    dgp.setEstudiante(NO);

                    if (encuestasParticipante.get(0).getTurno() != null) {
                        messageResource = this.getMensajeByCatalogAndCatKey(encuestasParticipante.get(0).getTurno(), "CHF_CAT_TURNO");
                        if (messageResource != null) dgp.setTurno(messageResource.getSpanish());
                    } else {
                        dgp.setTurno(NINGUNO);
                    }
                    if (encuestasParticipante.get(0).getCentroEstudio() != null) {
                        if (!encuestasParticipante.get(0).getCentroEstudio().equals("235")){//otra escuela
                            messageResource = this.getMensajeByCatalogAndCatKey(encuestasParticipante.get(0).getCentroEstudio(), "CENTRO_EST");
                            if (messageResource != null) dgp.setEscuela(messageResource.getSpanish());
                        }else {
                            dgp.setEscuela(encuestasParticipante.get(0).getNombreCentroEstudio());
                        }
                    } else {
                        dgp.setEscuela(NINGUNA);
                    }

                }else if (encuestasParticipanteMA.size() > 0) {
                    if (encuestasParticipanteMA.get(0).getNinoAsisteEscuela() != null) {
                        if (encuestasParticipanteMA.get(0).getNinoAsisteEscuela().equalsIgnoreCase("1"))
                            dgp.setEstudiante(SI);
                        else if (encuestasParticipanteMA.get(0).getNinoAsisteEscuela().equalsIgnoreCase("0"))
                            dgp.setEstudiante(NO);
                        else
                            dgp.setEstudiante(DESCONOCIDO);
                    } else
                        dgp.setEstudiante(NO);


                    if (encuestasParticipanteMA.get(0).getTurno() != null) {
                        messageResource = this.getMensajeByCatalogAndCatKey(encuestasParticipanteMA.get(0).getTurno().toString(), "CHF_CAT_TURNO");
                        if (messageResource != null) dgp.setTurno(messageResource.getSpanish());
                    } else {
                        dgp.setTurno(NINGUNO);
                    }
                    if (encuestasParticipanteMA.get(0).getnEscuela() != null) {
                        if (!encuestasParticipanteMA.get(0).getnEscuela().equals(235)){//otra escuela
                            messageResource = this.getMensajeByCatalogAndCatKey(encuestasParticipanteMA.get(0).getnEscuela().toString(), "CENTRO_EST");
                            if (messageResource != null) dgp.setEscuela(messageResource.getSpanish());
                        }else {
                            dgp.setEscuela(encuestasParticipanteMA.get(0).getOtraEscuela());
                        }
                    } else {
                        dgp.setEscuela(NINGUNA);
                    }
                } else {
                    dgp.getMensajes().add(this.getMensajeSpanishByMessageKey("lbl.participant survey.not.found"));
                }
                resultado.add(dgp);
            } /*else {
                dgp.getMensajes().add(this.getMensajeSpanishByMessageKey("lbl.participant.not.found"));
            }*/
            ultimoParticipante = participante.getCodigo();
        }

        return resultado;

    }
}
