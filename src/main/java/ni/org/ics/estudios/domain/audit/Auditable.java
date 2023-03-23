package ni.org.ics.estudios.domain.audit;

public interface Auditable {
	
	public boolean isFieldAuditable(String fieldname);

}
