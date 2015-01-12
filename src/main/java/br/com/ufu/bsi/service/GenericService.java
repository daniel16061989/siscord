package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;

/**
 * Interface da camada de serviço comum a todas as entidades
 */
public interface GenericService<T> {

	T save(T entity) throws SiscordGenericException;

	List<T> save(Iterable<T> entities) throws SiscordGenericException;

	T findOne(Integer id) throws SiscordGenericException;

	boolean exists(Integer id) throws SiscordGenericException;

	List<T> findAll() throws SiscordGenericException;

	List<T> findAll(Iterable<Integer> ids) throws SiscordGenericException;

	long count() throws SiscordGenericException;

	void delete(Integer id) throws SiscordGenericException;

	void delete(T entity) throws SiscordGenericException;

	void delete(Iterable<? extends T> entities) throws SiscordGenericException;

	void deleteAll() throws SiscordGenericException;
}
