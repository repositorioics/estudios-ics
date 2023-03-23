package ni.org.ics.estudios.service.corrections;

import ni.org.ics.estudios.domain.audit.CorrectionsTrail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 20/1/2022.
 */
@Transactional
@Service("correctionsTrailService")
public class CorrectionsTrailService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveCorrectionsTrailList(List<CorrectionsTrail> correctionsTrailList)
    {
        Session session = sessionFactory.openSession();
        try {
            if (correctionsTrailList.size() > 0) {
                session.beginTransaction();
                for (CorrectionsTrail correctionsTrail : correctionsTrailList) {
                    session.save(correctionsTrail);
                }
                session.getTransaction().commit();
            }
        }catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
