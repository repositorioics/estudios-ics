package ni.org.ics.estudios.domain;

import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

/**
 * Objeto que representa un número telefónico asociado a una casa o participante
 * Created by Miguel Salinas on 4/28/2017.
 * V1.0
 */
@Entity
@Table(name = "telefonos_contacto", catalog = "estudios_ics")
public class TelefonoContacto extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String numero;
    private String operadora;
    private String tipo;
    private Casa casa;
    private Participante participante;

    @Id
    @Column(name = "ID", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NUMERO", nullable = false, length = 150)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_CASA", nullable = true)
    @ForeignKey(name = "FK_CASA_TELEFONO")
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_PARTICIPANTE", nullable = true)
    @ForeignKey(name = "FK_PARTICIPANTE_TELEFONO")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name="CODIGO_OPERADORA", nullable = true, length = 50)
    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }
    
    
    @Column(name="TIPO_NUMERO", nullable = false, length = 50)
    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelefonoContacto)) return false;

        TelefonoContacto telefonoContacto = (TelefonoContacto) o;

        return  (!id.equals(telefonoContacto.id));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
