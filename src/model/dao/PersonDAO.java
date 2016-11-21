package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.dto.Person;

public class PersonDAO {
	private static final String JPA_UNIT_NAME = "ALTEA_TP7";
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory(
					JPA_UNIT_NAME).createEntityManager();
		}
		return entityManager;
	}

	public List<Person> selectAll() {
		List<Person> persons = getEntityManager().createQuery(
				"select p from Person p").getResultList();
		return persons;
	}

	public Person getPerson (Long pId) 	{
		return getEntityManager().find(Person.class,pId);
	}
	
	public Person insert(Person u) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(u);
		getEntityManager().getTransaction().commit();
		return u;
	}

	public void delete(Person u) {
		getEntityManager().getTransaction().begin();
		u = getEntityManager().merge(u);//<-Important
		getEntityManager().remove(u);
		getEntityManager().getTransaction().commit();
	}

	public Person update(Person u) {
		getEntityManager().getTransaction().begin();
		u = getEntityManager().merge(u);
		getEntityManager().getTransaction().commit();
		return u;
	}
}
