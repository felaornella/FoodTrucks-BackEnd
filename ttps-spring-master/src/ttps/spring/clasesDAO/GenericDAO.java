package ttps.spring.clasesDAO;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {

	public Boolean actualizar(T entity);
	public void borrar(T entity);
	public void borrar(Serializable id);
	public boolean existe(Serializable id);
	public T persistir(T entity);
	public T recuperarPorId(Serializable id);
	public List<T> recuperarTodos();
}
