package BookStore.dao.impl;

import BookStore.dao.BookStoreDao;
import BookStore.domain.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class BookStoreDaoImpl implements BookStoreDao {

	private SessionFactory sessionFactory = null;
	private Transaction tx = null;

	@Override
	public void makeConnection() {
		try {
			Configuration config = new Configuration().configure();
			sessionFactory = config.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int login(String name, String password) {
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		String HQL = "FROM UserInfo WHERE UserName = \'" + name + "\' AND " + "Password = \'" + password + "\'";
		Query q = session.createQuery(HQL);
		if (q.list().size() == 0) {
			tx.commit();
			return -1;
		} else {
			List<UserInfo> user = q.list();
			tx.commit();
			return user.get(0).getUserID();
		}
	}

	@Override
	public Query getQueryByHQL(String HQL) {
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Query q = session.createQuery(HQL);
		tx.commit();
		return q;
	}

	@Override
	public Query getQueryBySQL(String SQL) {
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Query q = session.createSQLQuery(SQL);
		tx.commit();
		return q;
	}

	@Override
	public int insert(Object o) {
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		int id = (int) session.save(o);
		tx.commit();
		return id;
	}

	@Override
	public void insertWithoutReturn(Object o) {
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(o);
		tx.commit();
	}

	@Override
	public void delete(Object o) {
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.delete(o);
		tx.commit();
	}

	@Override
	public void update(Object o) {
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(o);
		tx.commit();
	}

}
