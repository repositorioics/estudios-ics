package ni.org.ics.estudios.dto;

import ni.org.ics.estudios.domain.catalogs.Cargo;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by ICS on 24/11/2021.
 */
public class PersonalCargoDto implements Serializable {


    private int codigo;
    private String nombre;
    private HashSet<CargoDto>cargos = new HashSet<CargoDto>();
    private char pasive;
    private boolean estado;

    public PersonalCargoDto(){}

    public PersonalCargoDto(int codigo, String nombre, HashSet<CargoDto> cargos, char pasive, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cargos = cargos;
        this.pasive = pasive;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashSet<CargoDto> getCargos() {
        return cargos;
    }

    public void setCargos(HashSet<CargoDto> cargos) {
        this.cargos = cargos;
    }


    public char getPasive() {
        return pasive;
    }

    public void setPasive(char pasive) {
        this.pasive = pasive;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

