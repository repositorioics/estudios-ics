package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaSatisfaccionUsuario;
import ni.org.ics.estudios.domain.muestreoanual.EncuestaSatisfaccionUsuarioCC;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.sql.Timestamp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Ing. Santiago Carballo on 01/03/2023.
 */
@Service("encuestaSatisfaccionUsuarioService")
@Transactional
public class EncuestaSatisfaccionUsuarioService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdateEncuestaSatisfaccionUsuario(EncuestaSatisfaccionUsuario encuestaSatisfaccionUsuario){
        Session session = sessionFactory.getCurrentSession();
        session.save(encuestaSatisfaccionUsuario);
    }

    public void saveOrUpdateEncuestaSatisfaccionUsuarioCc(EncuestaSatisfaccionUsuarioCC encuestaSatisfaccionUsuario){
        Session session = sessionFactory.getCurrentSession();
        session.save(encuestaSatisfaccionUsuario);
    }

    public void addEncuestaSatisfaccionUsuario(EncuestaSatisfaccionUsuario encuestaSatisfaccionUsuario){
        Session session = sessionFactory.getCurrentSession();
        session.save(encuestaSatisfaccionUsuario);
    }

    public void updateEncuestaSatisfaccionUsuario(EncuestaSatisfaccionUsuario encuestaSatisfaccionUsuario){
        Session session = sessionFactory.getCurrentSession();
        session.update(encuestaSatisfaccionUsuario);
    }

    public EncuestaSatisfaccionUsuario getEncuestaSatisfaccionUsuario (Long fecha, Integer codigoParticipante){
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        Timestamp timeStamp = new Timestamp(fecha);
        Query query = session.createQuery("FROM EncuestaSatisfaccionUsuario encsat where DATE_FORMAT(encsat.fechaRegistro, '%Y%m%d') = DATE_FORMAT(:fechaRegistro, '%Y%m%d') " +
                " AND encsat.codigoParticipante = :codigoParticipante");
        query.setTimestamp("fechaRegistro",timeStamp);
        query.setInteger("codigoParticipante", codigoParticipante);

        EncuestaSatisfaccionUsuario encuestaSatisfaccionUsuario = (EncuestaSatisfaccionUsuario) query.uniqueResult();
        return encuestaSatisfaccionUsuario;
    }

    public Boolean checkEncuestaSatisfaccionUsuario (Long fecha, Integer codigoParticipante){
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Timestamp timeStamp = new Timestamp(fecha);
        Query query = session.createQuery("FROM EncuestaSatisfaccionUsuario encsat where DATE_FORMAT(encsat.fechaRegistro, '%Y%m%d') = DATE_FORMAT(:fechaRegistro, '%Y%m%d') " +
                " AND encsat.codigoParticipante = :codigoParticipante");
        query.setTimestamp("fechaRegistro",timeStamp);
        query.setInteger("codigoParticipante", codigoParticipante);

        EncuestaSatisfaccionUsuario encuestaSatisfaccionUsuario = (EncuestaSatisfaccionUsuario) query.uniqueResult();
        if (encuestaSatisfaccionUsuario != null){
            return true;
        }
        else{
            return false;
        }
    }
}
