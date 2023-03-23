package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.AreaAmbiente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@DiscriminatorValue("sala")
public class Sala extends AreaAmbiente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
