package br.com.ufu.bsi.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.service.GenericService;

@Transactional(readOnly = true)
public abstract class GenericServiceImpl<T> implements GenericService<T> {

	protected JdbcTemplate jdbcTemplate;

	protected abstract JpaRepository<T, Integer> getRepository();

	@Override
	@Transactional(rollbackFor = { Throwable.class })
	public T save(T entity) throws SiscordGenericException {
		try {
			return getRepository().save(entity);
		} catch (Exception e) {
			
		}
		return entity;
	}

	@Override
	@Transactional(rollbackFor = { Throwable.class })
	public List<T> save(Iterable<T> entities) throws SiscordGenericException  {
		try {
			return getRepository().save(entities);
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public T findOne(Integer id) throws SiscordGenericException {
		try {
			return getRepository().findOne(id);
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public boolean exists(Integer id) throws SiscordGenericException {
		try {
			return getRepository().exists(id);
		} catch (Exception e) {
			
		}
		return false;
	}

	@Override
	public List<T> findAll() throws SiscordGenericException {
		try {
			return getRepository().findAll();
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public List<T> findAll(Iterable<Integer> ids) throws SiscordGenericException {
		try {
			Iterable<T> entities = getRepository().findAll(ids);
			ArrayList<T> entitiesList = new ArrayList<T>();
			for (T entity : entities) {
				entitiesList.add(entity);
			}
			return entitiesList;
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public long count() throws SiscordGenericException {
		try {
			return getRepository().count();
		} catch (Exception e) {
			
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = { Throwable.class })
	public void delete(Integer id) throws SiscordGenericException {
		try {
			getRepository().delete(id);
		} catch (Exception e) {
			
		}
	}

	@Override
	@Transactional(rollbackFor = { Throwable.class })
	public void delete(T entity) throws SiscordGenericException {
		try {
			getRepository().delete(entity);
		} catch (Exception e) {
			
		}
	}

	@Override
	@Transactional(rollbackFor = { Throwable.class })
	public void delete(Iterable<? extends T> entities) throws SiscordGenericException {
		try {
			getRepository().delete(entities);
		} catch (Exception e) {
			
		}
	}

	@Override
	@Transactional(rollbackFor = { Throwable.class })
	public void deleteAll() throws SiscordGenericException {
		try {
			getRepository().deleteAll();
		} catch (Exception e) {
			
		}
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
