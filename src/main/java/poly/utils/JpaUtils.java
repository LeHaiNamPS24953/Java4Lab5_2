package poly.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaUtils {
	public static EntityManager getEntityManager() {
		EntityManager em = Persistence.createEntityManagerFactory("Poly").createEntityManager();
		return em;
	}
}
