package poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import poly.model.User;
import poly.utils.JpaUtils;

public class UserDAO {
	public int create(User user) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error: " + e.toString());
			return -1;
		} finally {
			em.close();
		}

		return 1;
	}

	public int update(User user) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error: " + e.toString());
			return -1;
		} finally {
			em.close();
		}

		return 1;
	}

	public int delete(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			User user = em.find(User.class, id);
			em.remove(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			System.out.println("Error: " + e.toString());
			return -1;
		} finally {
			em.close();
		}

		return 1;
	}

	public User findByID(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		User user = em.find(User.class, id);

		return user;
	}

	public List<User> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);

		return query.getResultList();
	}

	// Chưa test
	public List<User> findByRole(String admin) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "SELECT u FROM User u WHERE u.admin =: admin";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("admin", admin);

		return query.getResultList();
	}

	public void findPage(int page, int size) {
		EntityManager em = JpaUtils.getEntityManager();

		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(page * size);
		query.setMaxResults(size);

		List<User> users = query.getResultList();
	}

	public int count() {
		return findAll().size();
	}

	public boolean checkLogin(String username, String password) {
		User user = findByID(username);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return true;
			}
		}

		return false;
	}
}
