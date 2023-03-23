package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaSatisfaccionUsuario;
import ni.org.ics.estudios.domain.muestreoanual.EncuestaSatisfaccionUsuarioCC;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
