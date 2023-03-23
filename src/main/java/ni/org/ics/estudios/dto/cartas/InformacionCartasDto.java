package ni.org.ics.estudios.dto.cartas;

import java.util.List;

/**
 * Created by miguel on 7/3/2022.
 */
public class InformacionCartasDto {

    private List<InformacionPorBarrioDto> porBarrio;
    private List<InformacionPorDiaDto> porDia;
    private List<InformacionPorEstudioDto> porEstudio;
    private List<InformacionRangoEdadDto> porRangoEdad;
    private List<InformacionRecursoDto> porRecurso;
    private List<InformacionUsuarioDto> porUsuario;

    public List<InformacionPorBarrioDto> getPorBarrio() {
        return porBarrio;
    }

    public void setPorBarrio(List<InformacionPorBarrioDto> porBarrio) {
        this.porBarrio = porBarrio;
    }

    public List<InformacionPorDiaDto> getPorDia() {
        return porDia;
    }

    public void setPorDia(List<InformacionPorDiaDto> porDia) {
        this.porDia = porDia;
    }

    public List<InformacionPorEstudioDto> getPorEstudio() {
        return porEstudio;
    }

    public void setPorEstudio(List<InformacionPorEstudioDto> porEstudio) {
        this.porEstudio = porEstudio;
    }

    public List<InformacionRangoEdadDto> getPorRangoEdad() {
        return porRangoEdad;
    }

    public void setPorRangoEdad(List<InformacionRangoEdadDto> porRangoEdad) {
        this.porRangoEdad = porRangoEdad;
    }

    public List<InformacionRecursoDto> getPorRecurso() {
        return porRecurso;
    }

    public void setPorRecurso(List<InformacionRecursoDto> porRecurso) {
        this.porRecurso = porRecurso;
    }

    public List<InformacionUsuarioDto> getPorUsuario() {
        return porUsuario;
    }

    public void setPorUsuario(List<InformacionUsuarioDto> porUsuario) {
        this.porUsuario = porUsuario;
    }
}
