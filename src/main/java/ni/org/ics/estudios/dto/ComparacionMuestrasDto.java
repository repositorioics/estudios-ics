package ni.org.ics.estudios.dto;

import ni.org.ics.estudios.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.domain.muestreoanual.*;
import ni.org.ics.estudios.dto.muestras.MuestraDto;
import ni.org.ics.estudios.dto.muestras.RecepcionBHCDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguel on 4/4/2022.
 */
public class ComparacionMuestrasDto {
    //comparacion rojo
    private List<RecepcionSero> rojoSupervisorNoEst = new ArrayList<RecepcionSero>();
    List<RecepcionSero> rojoSupervisorNoLab = new ArrayList<RecepcionSero>();
    List<MuestraDto> rojoEstacionesNoSup = new ArrayList<MuestraDto>();
    List<MuestraDto> rojoEstacionesNoLab = new ArrayList<MuestraDto>();
    List<LabSero> rojoLaboratorioNoSup = new ArrayList<LabSero>();
    List<LabSero> rojoLaboratorioNoEst = new ArrayList<LabSero>();
    //comparacion bhc
    List<RecepcionBHCDto> bhcSupNoEst = new ArrayList<RecepcionBHCDto>();
    List<RecepcionBHCDto> bhcSupNoLab = new ArrayList<RecepcionBHCDto>();
    List<MuestraDto> bhcEstnoSup = new ArrayList<MuestraDto>();
    List<MuestraDto> bhcEstnoLab = new ArrayList<MuestraDto>();
    List<LabBHC> bhcLabNoSup = new ArrayList<LabBHC>();
    List<LabBHC> bhcLabNoEst = new ArrayList<LabBHC>();
    //comparacion pbmc
    List<MuestraDto> pbmcEstnoLab = new ArrayList<MuestraDto>();
    List<LabPbmc> pbmcLabNoEst = new ArrayList<LabPbmc>();


    public List<RecepcionSero> getRojoSupervisorNoEst() {
        return rojoSupervisorNoEst;
    }

    public void setRojoSupervisorNoEst(List<RecepcionSero> rojoSupervisorNoEst) {
        this.rojoSupervisorNoEst = rojoSupervisorNoEst;
    }

    public List<RecepcionSero> getRojoSupervisorNoLab() {
        return rojoSupervisorNoLab;
    }

    public void setRojoSupervisorNoLab(List<RecepcionSero> rojoSupervisorNoLab) {
        this.rojoSupervisorNoLab = rojoSupervisorNoLab;
    }

    public List<MuestraDto> getRojoEstacionesNoSup() {
        return rojoEstacionesNoSup;
    }

    public void setRojoEstacionesNoSup(List<MuestraDto> rojoEstacionesNoSup) {
        this.rojoEstacionesNoSup = rojoEstacionesNoSup;
    }

    public List<MuestraDto> getRojoEstacionesNoLab() {
        return rojoEstacionesNoLab;
    }

    public void setRojoEstacionesNoLab(List<MuestraDto> rojoEstacionesNoLab) {
        this.rojoEstacionesNoLab = rojoEstacionesNoLab;
    }

    public List<LabSero> getRojoLaboratorioNoSup() {
        return rojoLaboratorioNoSup;
    }

    public void setRojoLaboratorioNoSup(List<LabSero> rojoLaboratorioNoSup) {
        this.rojoLaboratorioNoSup = rojoLaboratorioNoSup;
    }

    public List<LabSero> getRojoLaboratorioNoEst() {
        return rojoLaboratorioNoEst;
    }

    public void setRojoLaboratorioNoEst(List<LabSero> rojoLaboratorioNoEst) {
        this.rojoLaboratorioNoEst = rojoLaboratorioNoEst;
    }

    public List<RecepcionBHCDto> getBhcSupNoEst() {
        return bhcSupNoEst;
    }

    public void setBhcSupNoEst(List<RecepcionBHCDto> bhcSupNoEst) {
        this.bhcSupNoEst = bhcSupNoEst;
    }

    public List<RecepcionBHCDto> getBhcSupNoLab() {
        return bhcSupNoLab;
    }

    public void setBhcSupNoLab(List<RecepcionBHCDto> bhcSupNoLab) {
        this.bhcSupNoLab = bhcSupNoLab;
    }

    public List<MuestraDto> getBhcEstnoSup() {
        return bhcEstnoSup;
    }

    public void setBhcEstnoSup(List<MuestraDto> bhcEstnoSup) {
        this.bhcEstnoSup = bhcEstnoSup;
    }

    public List<MuestraDto> getBhcEstnoLab() {
        return bhcEstnoLab;
    }

    public void setBhcEstnoLab(List<MuestraDto> bhcEstnoLab) {
        this.bhcEstnoLab = bhcEstnoLab;
    }

    public List<LabBHC> getBhcLabNoSup() {
        return bhcLabNoSup;
    }

    public void setBhcLabNoSup(List<LabBHC> bhcLabNoSup) {
        this.bhcLabNoSup = bhcLabNoSup;
    }

    public List<LabBHC> getBhcLabNoEst() {
        return bhcLabNoEst;
    }

    public void setBhcLabNoEst(List<LabBHC> bhcLabNoEst) {
        this.bhcLabNoEst = bhcLabNoEst;
    }

    public List<MuestraDto> getPbmcEstnoLab() {
        return pbmcEstnoLab;
    }

    public void setPbmcEstnoLab(List<MuestraDto> pbmcEstnoLab) {
        this.pbmcEstnoLab = pbmcEstnoLab;
    }

    public List<LabPbmc> getPbmcLabNoEst() {
        return pbmcLabNoEst;
    }

    public void setPbmcLabNoEst(List<LabPbmc> pbmcLabNoEst) {
        this.pbmcLabNoEst = pbmcLabNoEst;
    }
}
