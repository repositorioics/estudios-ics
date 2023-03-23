package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.LabPax;
import ni.org.ics.estudios.domain.muestreoanual.TempLab;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Servicio para el objeto RecepcionSero
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("reporteService")
@Transactional
public class ReporteService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountRojosLab() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_sero), COUNT(codigo) AS Total FROM labsero GROUP BY DATE(fecha_sero);");
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TempLab> getTempLab() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("From TempLab");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getStatTempLab() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_temp) as fecha, COUNT(temperatura) AS Cuenta, 2 as LimInf, 10 as LimSup, Min(temperatura) AS Minimo, Avg(temperatura) AS Promedio, Max(temperatura) AS Maximo FROM templab GROUP BY DATE(fecha_temp);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountRojosEst() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_muestra), COUNT(codigo) AS Total FROM muestras WHERE tuborojo=1 GROUP BY DATE(fecha_muestra);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountRojosSup() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_sero), COUNT(codigo) AS Total FROM recepcionsero GROUP BY DATE(fecha_sero);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountBHCsLab() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_bhc), COUNT(codigo) AS Total FROM labbhc GROUP BY DATE(fecha_bhc);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountBHCsEst() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_muestra), COUNT(codigo) AS Total FROM muestras WHERE tubobhc=1 GROUP BY DATE(fecha_muestra);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountBHCsSup() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_bhc), COUNT(codigo) AS Total FROM recepcionbhc GROUP BY DATE(fecha_bhc);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountPBMCsLab() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_pbmc), COUNT(codigo) AS Total FROM labpbmc GROUP BY DATE(fecha_pbmc);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountPaxsLab() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_pax), COUNT(codigo) AS Total FROM labpax GROUP BY DATE(fecha_pax);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<LabPax> getLabPax() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("From LabPax");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountPBMCsEst() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_muestra), COUNT(codigo) AS Total FROM muestras WHERE tuboleu=1 GROUP BY DATE(fecha_muestra);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMuestrasxBarrio() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		
		Query query = session.createSQLQuery("DELETE FROM muestrasxdia_barrio;");
		query.executeUpdate();
		query = session.createSQLQuery("INSERT IGNORE INTO muestrasxdia_barrio ( Codigo, fecha, Vol, Obs, Tipo, Fec2 ) " +
				"SELECT labpbmc.Codigo, labpbmc.fecha_pbmc, labpbmc.volpbmc, labpbmc.observacion, 'PBMC' AS Tipo, labpbmc.fecha_registro FROM labpbmc WHERE (((labpbmc.volpbmc)>0));");
		query.executeUpdate();
		query = session.createSQLQuery("INSERT IGNORE INTO muestrasxdia_barrio ( Codigo, Tipo, Fecha, Vol, Obs, Fec2 ) " +
				"SELECT labsero.Codigo, 'Rojo' AS Tipo, labsero.fecha_sero, labsero.volsero, labsero.observacion, labsero.fecha_registro FROM labsero WHERE (((labsero.volsero)>0));");
		query.executeUpdate();
		
		query = session.createSQLQuery("SELECT DISTINCT barrios.nombre_barrio, Count(participantes.codigo) AS total, Count(muestrasxdia_barrio.codigo) AS tomadas, " +
				"Count(participantes.codigo) -  Count(muestrasxdia_barrio.codigo) AS faltantes, (Count(muestrasxdia_barrio.codigo))/ Count(participantes.codigo) * 100 as Porcentaje " +
				"FROM (((participantes) INNER JOIN barrios ON participantes.barrio = barrios.barrio) " +
				"LEFT JOIN muestrasxdia_barrio ON participantes.codigo = muestrasxdia_barrio.codigo) " +
				"WHERE (((participantes.edad)>=6) AND ((participantes.est_part)=1) AND ((participantes.codigo)<30000)) GROUP BY barrios.nombre_barrio;");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMuestrasxEdad() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		
		Query query = session.createSQLQuery("DELETE FROM muestrasxdia_barrio;");
		query.executeUpdate();
		query = session.createSQLQuery("INSERT IGNORE INTO muestrasxdia_barrio ( Codigo, fecha, Vol, Obs, Tipo, Fec2 ) " +
				"SELECT labpbmc.Codigo, labpbmc.fecha_pbmc, labpbmc.volpbmc, labpbmc.observacion, 'PBMC' AS Tipo, labpbmc.fecha_registro FROM labpbmc WHERE (((labpbmc.volpbmc)>0));");
		query.executeUpdate();
		query = session.createSQLQuery("INSERT IGNORE INTO muestrasxdia_barrio ( Codigo, Tipo, Fecha, Vol, Obs, Fec2 ) " +
				"SELECT labsero.Codigo, 'Rojo' AS Tipo, labsero.fecha_sero, labsero.volsero, labsero.observacion, labsero.fecha_registro FROM labsero WHERE (((labsero.volsero)>0));");
		query.executeUpdate();
		
		query = session.createSQLQuery("SELECT DISTINCT TRUNCATE(DATEDIFF(DATE(NOW()),participantes.fechanac) / 365.25,0) AS e, Count(participantes.codigo) AS total, " +
				"Count(muestrasxdia_barrio.codigo) AS tomadas, Count(participantes.codigo) -  Count(muestrasxdia_barrio.codigo) AS faltantes, " +
				"(Count(muestrasxdia_barrio.codigo))/ Count(participantes.codigo) * 100 as Porcentaje " +
				"FROM (((participantes)) LEFT JOIN muestrasxdia_barrio ON participantes.codigo = muestrasxdia_barrio.codigo) " +
				"WHERE (((participantes.edad)>=6) AND ((participantes.est_part)=1) AND ((participantes.codigo)<30000)) GROUP BY TRUNCATE(DATEDIFF(DATE(NOW()),participantes.fechanac) / 365.25,0);");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMuestrasxEstudio() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		
		Query query = session.createSQLQuery("DELETE FROM muestrasxdia_barrio;");
		query.executeUpdate();
		query = session.createSQLQuery("INSERT IGNORE INTO muestrasxdia_barrio ( Codigo, fecha, Vol, Obs, Tipo, Fec2 ) " +
				"SELECT labpbmc.Codigo, labpbmc.fecha_pbmc, labpbmc.volpbmc, labpbmc.observacion, 'PBMC' AS Tipo, labpbmc.fecha_registro FROM labpbmc WHERE (((labpbmc.volpbmc)>0));");
		query.executeUpdate();
		query = session.createSQLQuery("INSERT IGNORE INTO muestrasxdia_barrio ( Codigo, Tipo, Fecha, Vol, Obs, Fec2 ) " +
				"SELECT labsero.Codigo, 'Rojo' AS Tipo, labsero.fecha_sero, labsero.volsero, labsero.observacion, labsero.fecha_registro FROM labsero WHERE (((labsero.volsero)>0));");
		query.executeUpdate();
		
		query = session.createSQLQuery("SELECT DISTINCT estudio, Count(participantes.codigo) AS total, Count(muestrasxdia_barrio.codigo) AS tomadas, " +
				"Count(participantes.codigo) -  Count(muestrasxdia_barrio.codigo) AS faltantes, (Count(muestrasxdia_barrio.codigo))/ Count(participantes.codigo) * 100 as Porcentaje " +
				"FROM (((participantes)) LEFT JOIN muestrasxdia_barrio ON participantes.codigo = muestrasxdia_barrio.codigo) " +
				"WHERE (((participantes.edad)>=6) AND ((participantes.est_part)=1) AND ((participantes.codigo)<30000)) GROUP BY estudio;");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMuestrasxTubo() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DISTINCT pbmc, Count(participantes.codigo) AS total, Count(labsero.codigo)+Count(labpbmc.codigo) AS tomadas, " +
		"Count(participantes.codigo)-Count(labsero.codigo)-Count(labpbmc.codigo) AS faltantes, Count(labsero.codigo) AS rojos, " +
		"Count(labpbmc.codigo) AS pbmcs, (Count(labsero.codigo)+Count(labpbmc.codigo))/Count(participantes.codigo)*100 AS Porcentaje " +
		"FROM participantes LEFT JOIN labsero ON participantes.codigo = labsero.codigo LEFT JOIN labpbmc ON participantes.codigo = labpbmc.codigo " +
		"WHERE (((participantes.edad)>=6) AND ((participantes.est_part)=1) AND ((participantes.codigo)<30000)) GROUP BY pbmc;");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMuestrasTotal() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DISTINCT Count(participantes.codigo) AS total, Count(labsero.codigo)+Count(labpbmc.codigo) AS tomadas, " +
				"Count(participantes.codigo)-Count(labsero.codigo)-Count(labpbmc.codigo) AS faltantes, Count(labbhc.codigo) AS bhcs, " +
				"Count(labsero.codigo) AS rojos, Count(labpbmc.codigo) AS pbmcs, (Count(labsero.codigo)+Count(labpbmc.codigo))/Count(participantes.codigo)*100 AS Porc_Realizados, " +
				"((Count(participantes.codigo)-Count(labsero.codigo)-Count(labpbmc.codigo))/Count(participantes.codigo))*100 AS Porc_Faltantes, " +
				"Count(labpax.codigo) AS paxs FROM (((participantes LEFT JOIN labsero ON participantes.codigo = labsero.codigo) LEFT JOIN labpbmc ON participantes.codigo = labpbmc.codigo)" +
				" LEFT JOIN labbhc ON participantes.codigo = labbhc.codigo) " +
				"LEFT JOIN labpax ON participantes.codigo = labpax.codigo WHERE " +
				"(((participantes.edad)>=6) AND ((participantes.est_part)=1) AND ((participantes.codigo)<30000));");
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getMuestrasDia() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("DELETE FROM muestrasxdia;");
		query.executeUpdate();
		query = session.createSQLQuery("INSERT IGNORE INTO muestrasxdia ( Codigo, fecha, Vol, Obs, Tipo, Fec2 ) " +
				"SELECT labpbmc.Codigo, labpbmc.fecha_pbmc, labpbmc.volpbmc, labpbmc.observacion, 'PBMC' AS Tipo, labpbmc.fecha_registro FROM labpbmc WHERE (((labpbmc.volpbmc)>0));");
		query.executeUpdate();
		query = session.createSQLQuery("INSERT IGNORE INTO muestrasxdia ( Codigo, Tipo, Fecha, Vol, Obs, Fec2 ) " +
				"SELECT labsero.Codigo, 'Rojo' AS Tipo, labsero.fecha_sero, labsero.volsero, labsero.observacion, labsero.fecha_registro FROM labsero WHERE (((labsero.volsero)>0));");
		query.executeUpdate();
		query = session.createSQLQuery("SELECT fecha, COUNT( codigo ) AS Total, " +
				"SUM( CASE Tipo WHEN 'PBMC' THEN 1 ELSE 0 END ) AS 'PBMC', " +
				"SUM( CASE Tipo WHEN 'Rojo' THEN 1 ELSE 0 END ) AS 'Rojo' FROM muestrasxdia GROUP BY fecha;");
		// Retrieve all
		return  query.list();
	}


}
