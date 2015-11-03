package com.javacents.ormpitfalls.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

/**
 * Base generic dao which provides CRUD operations for entities
 *
 * @param <T>
 * @param <ID>
 */
public abstract class BaseDao<T, ID> {
	@PersistenceContext
	protected EntityManager entityManager;
	private Class<T> entityClass;

	public BaseDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Transactional
	public void add(T entity) {
		entityManager.persist(entity);
	}

	@Transactional(readOnly = false)
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Transactional(readOnly = false)
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Transactional
	public T find(ID id) {
		return entityManager.find(entityClass, id);
	}
}
