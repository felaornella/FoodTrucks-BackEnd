package ttps.spring.clasesDAOImpJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ttps.spring.clasesDAO.GenericDAO;

@Transactional
public class GenericDAOImpJPA<T> implements GenericDAO<T> {

	
	private EntityManager entityManager;
	
	protected Class<T> persistentClass;
	
	public GenericDAOImpJPA(Class<T> clase) {
		persistentClass = clase;
	}
	
	public Class<T> getPersistentClass(){
		return this.persistentClass;
	}
	

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Boolean actualizar(T entity) {
		this.getEntityManager().merge(entity);
		return entity != null;
	}

	@Override
	public void borrar(T entity) {
		try {
			//this.getEntityManager().remove(entity);
			this.getEntityManager().remove(this.getEntityManager().contains(entity) ? entity : this.getEntityManager().merge(entity));
		} catch (RuntimeException e) {
			System.out.println("Problema al Borrar");
			throw e;
		}
	}

	@Override
	public void borrar(Serializable id) {

		Object q = this.getEntityManager().
				createQuery("SELECT o FROM "+  this.getPersistentClass().getSimpleName() + " o where id="+ id).getSingleResult();
		if (q != null) {
			this.borrar((T)q);
		}
	}

	@Override
	public boolean existe(Serializable id) {
		
		if (this.recuperarPorId(id).equals(null) ) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public T persistir(T entity) {
		try {
			this.getEntityManager().persist(entity);
			return entity;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T recuperarPorId(Serializable id) {
		try {
			Object obj = this.getEntityManager().
					createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName() + " o where id=" + id).getSingleResult();
			return (T)obj;
		} catch (RuntimeException e) {
			System.out.println("No se encuentra "+ this.getPersistentClass().getSimpleName() +" con id ingresado");
			return null;
		}
	}

	public List<T> recuperarTodos() {
	
		Query consulta = this.getEntityManager().
				createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName() + " o ");
		List<T> resultado = (List<T>) consulta.getResultList();
		return resultado;
	}

}
