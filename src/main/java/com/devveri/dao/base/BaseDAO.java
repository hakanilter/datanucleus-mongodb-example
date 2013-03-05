package com.devveri.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseDAO<T, ID extends Serializable> 
{
	private static final String PERSISTENCE_UNIT_NAME = "example";
	private static final Object lock = new Object();
	
	private final Class<T> persistentClass;
	
	private EntityManagerFactory emf; 
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public BaseDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected EntityManager getEntityManager() 
	{
		if (em == null) {
			synchronized (lock) {
				if (em == null) {
					emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			        em = emf.createEntityManager(); 					
				}
			}
		}
		return em;
	}
	
	public void close() 
	{
		if (em != null) {
			em.close();
			em = null;			
		}		
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}
	
	public void save(final T t) {
		getEntityManager().persist(t);
	}
	
	public void delete(ID id) 
	{
		T t = getEntityManager().getReference(persistentClass, id);
		if (t != null) {
			getEntityManager().remove(t);	
		}
	}

	public T update(T t) {
		return getEntityManager().merge(t);
	}	
	
	public T findById(ID id) {
		return getEntityManager().find(persistentClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() 
	{
		final String query = String.format("select t from %s t", persistentClass.getSimpleName());
		return getEntityManager().createQuery(query).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) 
	{
		final String query = String.format("select t from %s t where %s = :parameter", 
				persistentClass.getSimpleName(), propertyName);
		return getEntityManager().createQuery(query).setParameter("parameter", value).getResultList();
	}
}
