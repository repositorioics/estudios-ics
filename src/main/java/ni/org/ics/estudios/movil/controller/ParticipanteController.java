package ni.org.ics.estudios.movil.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.DatosCoordenadas;
import ni.org.ics.estudios.domain.ContactoParticipante;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.DatosParticipante;
import ni.org.ics.estudios.dto.zen.ParticipanteZen;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.service.zen.ParticipanteZenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class ParticipanteController {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteController.class);

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteZenService")
    private ParticipanteZenService participanteZenService;

    @Resource(name="participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;
    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "participantes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Participante> getParticipantes() {
        try {
            logger.info("Descargando toda la informacion de Participante");
            List<Participante> respuestaList = participanteService.getParticipantes();
            if (respuestaList == null) {
                logger.debug("Nulo");
            }
            return respuestaList;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param participantesArray Objeto serializado de Participante
     * @return String con el resultado
     */
    @RequestMapping(value = "participantes", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantes(@RequestBody Participante[] participantesArray){
        logger.debug("Insertando/Actualizando formularios Participante");
        if (participantesArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Participante> participantes = Arrays.asList(participantesArray);
            for (Participante participante : participantes){
                participanteService.saveOrUpdateParticipante(participante);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Retorna participantes activos. Acepta una solicitud GET para JSON
     * @return participantes JSON
     */
    @RequestMapping(value = "participantesactivos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Participante> descargarParticipantesActivos() {
        logger.info("Descargando toda la informacion de participantes activos");
        List<Participante> participantes = participanteService.getParticipantesActivos();
        if (participantes == null){
            logger.debug("Nulo");
        }
        return participantes;
    }

    /**
     * Retorna codigo casa. Acepta una solicitud GET para JSON
     * @return participante JSON
     */
    @RequestMapping(value = "checkcasa/{codigo}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Integer checkCasa(@PathVariable Integer codigo) {
        logger.info("Descargando codigo de casa del participante");
        Integer codCasa = participanteService.checkCasa(codigo);
        if (codCasa == null){
            logger.debug("Nulo");
        }
        return codCasa;
    }

    /**
     * Retorna participante. Acepta una solicitud GET para JSON
     * @return participante JSON
     */
    @RequestMapping(value = "participante/{codigo}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Participante> descargarParticipante(@PathVariable Integer codigo) {
        logger.info("Descargando toda la informacion del participante "+codigo);
        List<Participante> participantes = new ArrayList<Participante>();
        participantes.add(participanteService.getParticipanteByCodigo(codigo));
        return participantes;
    }


    /**
     * Retorna participantes. Acepta una solicitud GET para JSON
     * @return participantes JSON
     */
    @RequestMapping(value = "participantesprocesos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteProcesos> descargarParticipantes() {
        try {
            logger.info("Descargando toda la informacion de participantes");
            List<ParticipanteProcesos> participantes = participanteProcesosService.getParticipantesProcesos();
            if (participantes == null) {
                logger.debug(new Date() + " - Nulo");
            }
            return participantes;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Acepta una solicitud POST con un parámetro JSON
     * @param participantesArray Objeto serializado de Participante
     * @return String con el resultado
     */
    @RequestMapping(value = "participantesprocesos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantesProcesos(@RequestBody ParticipanteProcesos[] participantesArray) {
        logger.debug("Insertando/Actualizando datos procesos de participantes");
        if (participantesArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ParticipanteProcesos> participantes = Arrays.asList(participantesArray);
            for (ParticipanteProcesos participante : participantes) {
                //Obteniendo el participanteProceso por codigo
                ParticipanteProcesos partProc = participanteProcesosService.getParticipante(participante.getCodigo());
                /*Verificar si los procesos del participante estan pendientes*/

                if (partProc != null) {
                    if (!isNullOrEmpty(partProc.getMovilInfo().getUltimoCambio()) && isNullOrEmpty(participante.getMovilInfo().getUltimoCambio())) {
                        participante.getMovilInfo().setUltimoCambio(partProc.getMovilInfo().getUltimoCambio());
                    }
                    if (!isNullOrEmpty(partProc.getMovilInfo().getStart()) && isNullOrEmpty(participante.getMovilInfo().getStart())) {
                        participante.getMovilInfo().setStart(partProc.getMovilInfo().getStart());
                    }
                    if (!isNullOrEmpty(partProc.getMovilInfo().getEnd()) && isNullOrEmpty(participante.getMovilInfo().getEnd())) {
                        participante.getMovilInfo().setEnd(partProc.getMovilInfo().getEnd());
                    }
                    if (!isNullOrEmpty(partProc.getMovilInfo().getInstancePath()) && isNullOrEmpty(participante.getMovilInfo().getInstancePath())) {
                        participante.getMovilInfo().setInstancePath(partProc.getMovilInfo().getInstancePath());
                    }

                    //conmx
                    if (!isNullOrEmpty(partProc.getConmx()) && !isNullOrEmpty(participante.getConmx())) {
                        if (partProc.getConmx().trim().equals("No") && participante.getConmx().trim().equals("Si")) {
                            participante.setConmx("No");
                        }
                    }

                    //conmxbhc
                    if (!isNullOrEmpty(partProc.getConmxbhc()) && !isNullOrEmpty(participante.getConmxbhc())) {
                        if (partProc.getConmxbhc().trim().equals("No") && participante.getConmxbhc().trim().equals("Si")) {
                            participante.setConmxbhc("No");
                        }
                    }

                    //cons_deng
                    if (!isNullOrEmpty(partProc.getConsDeng()) && !isNullOrEmpty(participante.getConsDeng())) {
                        if (partProc.getConsDeng().trim().equals("No") && participante.getConsDeng().trim().equals("Si")) {
                            participante.setConsDeng("No");
                        }
                    }

                    //cons_flu
                    if (!isNullOrEmpty(partProc.getConsFlu()) && !isNullOrEmpty(participante.getConsFlu())) {
                        if (partProc.getConsFlu().trim().equals("No") && participante.getConsFlu().trim().equals("Si")) {
                            participante.setConsFlu("No");
                        }
                    }

                    //datos_parto
                    if (!isNullOrEmpty(partProc.getDatosParto()) && !isNullOrEmpty(participante.getDatosParto())) {
                        if (partProc.getDatosParto().trim().equals("No") && participante.getDatosParto().trim().equals("Si")) {
                            participante.setDatosParto("No");
                        }
                    }

                    //enc_casa
                    if (!isNullOrEmpty(partProc.getEnCasa()) && !isNullOrEmpty(participante.getEnCasa())) {
                        if (partProc.getEnCasa().trim().equals("No") && participante.getEnCasa().trim().equals("Si")) {
                            participante.setEnCasa("No");
                        }
                    }

                    //enc_casa_chf
                    if (!isNullOrEmpty(partProc.getEnCasaChf()) && !isNullOrEmpty(participante.getEnCasaChf())) {
                        if (partProc.getEnCasaChf().trim().equals("No") && participante.getEnCasaChf().trim().equals("Si")) {
                            participante.setEnCasaChf("No");
                        }
                    }

                    //enc_lacmat
                    if (!isNullOrEmpty(partProc.getEncLacMat()) && !isNullOrEmpty(participante.getEncLacMat())) {
                        if (partProc.getEncLacMat().trim().equals("No") && participante.getEncLacMat().trim().equals("Si")) {
                            participante.setEncLacMat("No");
                        }
                    }

                    //enc_part
                    if (!isNullOrEmpty(partProc.getEncPart()) && !isNullOrEmpty(participante.getEncPart())) {
                        if (partProc.getEncPart().trim().equals("No") && participante.getEncPart().trim().equals("Si")) {
                            participante.setEncPart("No");
                        }
                    }

                    //info_vacuna
                    if (!isNullOrEmpty(partProc.getInfoVacuna()) && !isNullOrEmpty(participante.getInfoVacuna())) {
                        if (partProc.getInfoVacuna().trim().equals("No") && participante.getInfoVacuna().trim().equals("Si")) {
                            participante.setInfoVacuna("No");
                        }
                    }

                    //obsequio
                    if (!isNullOrEmpty(partProc.getObsequio()) && !isNullOrEmpty(participante.getObsequio())) {
                        if (partProc.getObsequio().trim().equals("No") && participante.getObsequio().trim().equals("Si")) {
                            participante.setObsequio("No");
                        }
                    }

                    //pbmc
                    if (!isNullOrEmpty(partProc.getPbmc()) && !isNullOrEmpty(participante.getPbmc())) {
                        if (partProc.getPbmc().trim().equals("No") && participante.getPbmc().trim().equals("Si")) {
                            participante.setPbmc("No");
                        }
                    }

                    //peso_talla
                    if (!isNullOrEmpty(partProc.getPesoTalla()) && !isNullOrEmpty(participante.getPesoTalla())) {
                        if (partProc.getPesoTalla().trim().equals("No") && participante.getPesoTalla().trim().equals("Si")) {
                            participante.setPesoTalla("No");
                        }
                    }

                    //pos_zika
                    if (!isNullOrEmpty(partProc.getPosZika()) && !isNullOrEmpty(participante.getPosZika())) {
                        if (partProc.getPosZika().trim().equals("No") && participante.getPosZika().trim().equals("Si")) {
                            participante.setPosZika("No");
                        }
                    }

                    //obsequio_chf
                    if (!isNullOrEmpty(partProc.getObsequioChf()) && !isNullOrEmpty(participante.getObsequioChf())) {
                        if (partProc.getObsequioChf().trim().equals("No") && participante.getObsequioChf().trim().equals("Si")) {
                            participante.setObsequioChf("No");
                        }
                    }

                    //c_datos_parto
                    if (!isNullOrEmpty(partProc.getcDatosParto()) && !isNullOrEmpty(participante.getcDatosParto())) {
                        if (partProc.getcDatosParto().trim().equals("No") && participante.getcDatosParto().trim().equals("Si")) {
                            participante.setcDatosParto("No");
                        }
                    }

                    //cons_chf
                    if (!isNullOrEmpty(partProc.getConsChf()) && !isNullOrEmpty(participante.getConsChf())) {
                        if (partProc.getConsChf().trim().equals("No") && participante.getConsChf().trim().equals("Si")) {
                            participante.setConsChf("No");
                        }
                    }

                    //cuest_covid19
                    if (!isNullOrEmpty(partProc.getCuestCovid()) && !isNullOrEmpty(participante.getCuestCovid())) {
                        if (partProc.getCuestCovid().trim().equals("No") && participante.getCuestCovid().trim().equals("Si")) {
                            participante.setCuestCovid("No");
                        }
                    }

                    //perimetro_abdominal
                    if (!isNullOrEmpty(partProc.getPerimetroAbdominal()) && !isNullOrEmpty(participante.getPerimetroAbdominal())) {
                        if (partProc.getPerimetroAbdominal().trim().equals("No") && participante.getPerimetroAbdominal().trim().equals("Si")) {
                            participante.setPerimetroAbdominal("No");
                        }
                    }

                    //esat_usuario
                    if (!isNullOrEmpty(partProc.getEsatUsuario()) && !isNullOrEmpty(participante.getEsatUsuario())) {
                        if (partProc.getEsatUsuario().trim().equals("No") && participante.getEsatUsuario().trim().equals("Si")) {
                            participante.setEsatUsuario("No");
                        }
                    }

                    //esat_usuario_cc
                    if (!isNullOrEmpty(partProc.getEsatUsuarioCc()) && !isNullOrEmpty(participante.getEsatUsuarioCc())) {
                        if (partProc.getEsatUsuarioCc().trim().equals("No") && participante.getEsatUsuarioCc().trim().equals("Si")) {
                            participante.setEsatUsuarioCc("No");
                        }
                    }
                }
                participanteProcesosService.saveOrUpdateParticipanteProc(participante);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Retorna participante_procesos. Acepta una solicitud GET para JSON
     * @return participante JSON
     */
    @RequestMapping(value = "participanteprocesos/{codigo}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteProcesos> descargarParticipanteProcesos(@PathVariable Integer codigo) {
        logger.info("Descargando toda la informacion del participante "+codigo);
        List<ParticipanteProcesos> participantes = new ArrayList<ParticipanteProcesos>();
        participantes.add(participanteProcesosService.getParticipante(codigo));

        return participantes;
    }

    /**
     * Retorna participantes. Acepta una solicitud GET para JSON
     * @return participantes JSON
     */
    @RequestMapping(value = "contactosparticipantes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ContactoParticipante> descargarContactosParticipantes() {
        try {
            logger.info("Descargando toda la informacion de contactosParticipantes");
            List<ContactoParticipante> contactosParticipantes = participanteService.getContactosParticipantes();
            if (contactosParticipantes == null) {
                logger.debug(new Date() + " - Nulo");
            }
            return contactosParticipantes;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Acepta una solicitud POST con un parámetro JSON
     * @param participantesArray Objeto serializado de Participante
     * @return String con el resultado
     */
    @RequestMapping(value = "contactosparticipantes", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveContactoParticipantes(@RequestBody ContactoParticipante[] participantesArray){
        logger.debug("Insertando/Actualizando datos contactos de participantes");
        if (participantesArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ContactoParticipante> contactoParticipantes = Arrays.asList(participantesArray);
            for (ContactoParticipante contactoParticipante : contactoParticipantes){
                participanteService.saveOrUpdateContactoParticipante(contactoParticipante);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Retorna datoscoordenadas. Acepta una solicitud GET para JSON
     * @return datoscoordenadas JSON
     */
    @RequestMapping(value = "datoscoordenadas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DatosCoordenadas> getDatosCoordenadas() {
        try {
            logger.info("Descargando toda la informacion de datoscoordenadas");
            List<DatosCoordenadas> coordenadas = participanteService.getDatosCoordenadas();
            if (coordenadas == null) {
                logger.debug(new Date() + " - Nulo");
            }
            return coordenadas;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Acepta una solicitud POST con un parámetro JSON
     * @param datosArray Objeto serializado de coordenas gps
     * @return String con el resultado
     */
    @RequestMapping(value = "datoscoordenadas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveDatosCoordenadas(@RequestBody DatosCoordenadas[] datosArray){
        logger.debug("Insertando/Actualizando datos datoscoordenadas");
        if (datosArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<DatosCoordenadas> datosCoordenadas = Arrays.asList(datosArray);
            for (DatosCoordenadas coordenadas : datosCoordenadas){
                participanteService.saveOrUpdateDatosCoordenadas(coordenadas);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Retorna ParticipanteZen. Acepta una solicitud GET para JSON
     * @return List<ParticipanteZen> JSON
     */
    @RequestMapping(value = "participantesZen", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteZen> getParticipantesZen() {
        try {
            logger.info("Descargando toda la informacion de ParticipanteZen");
            List<ParticipanteZen> participanteZens = participanteZenService.getParticipantes();
            if (participanteZens == null) {
                logger.debug(new Date() + " - Nulo");
            }
            return participanteZens;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Retorna participante_procesos. Acepta una solicitud GET para JSON
     * @return participante JSON
     */
    @RequestMapping(value = "datosParticipante/{codigo}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DatosParticipante> descargarDatosParticipante(@PathVariable Integer codigo) {
        logger.info("Descargando los datos del participante "+codigo);
        return participanteService.getDatosParticipante(codigo);
    }

    /**
     * Retorna ParticipanteZen. Acepta una solicitud GET para JSON
     * @return List<ParticipanteZen> JSON
     */
    @RequestMapping(value = "datosParticipantes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DatosParticipante> getDatosParticipantes() {
        try {
            logger.info("Descargando toda la informacion de Participantes");
            List<DatosParticipante> participantes = participanteService.getDatosParticipantes();
            if (participantes == null) {
                logger.debug(new Date() + " - Nulo");
            }
            return participantes;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Retorna participantes. Acepta una solicitud GET para JSON
     * @return participantes JSON
     */
    @RequestMapping(value = "participantesprocesosCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteProcesos> getParticipantesCovidProcesos() {
        try {
            logger.info("Descargando toda la informacion de procesos participantes casos activos covid19");
            List<ParticipanteProcesos> participantes = participanteProcesosService.getParticipantesCovidProcesos();
            if (participantes == null) {
                logger.debug(new Date() + " - Nulo");
            }
            return participantes;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean isNullOrEmpty(String string) {
        return string==null || string.trim().equals("");
    }

}
