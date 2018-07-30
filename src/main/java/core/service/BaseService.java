package core.service;

import core.dao.Dao;
import core.support.BaseParameter;
import core.support.QueryResult;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseService<E> implements Service<E> {

	protected Dao<E> dao;

	public void persist(E entity) {
		dao.persist(entity);
	}

	public boolean deleteByPK(Serializable... id) {
		return dao.deleteByPK(id);
	}

	public void delete(E entity) {
		dao.delete(entity);
	}

	public void deleteByProperties(String[] propName, Object[] propValue) {
		dao.deleteByProperties(propName, propValue);
	}

	public void deleteByProperties(String propName, Object propValue) {
		dao.deleteByProperties(propName, propValue);
	}

	public E get(Serializable id) {
		return dao.get(id);
	}

	public E getByProerties(String[] propName, Object[] propValue) {
		return dao.getByProerties(propName, propValue);
	}

	public E getByProerties(String propName, Object propValue) {
		return dao.getByProerties(propName, propValue);
	}

	public E getByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		return dao.getByProerties(propName, propValue, sortedCondition);
	}

	public E getByProerties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return dao.getByProerties(propName, propValue, sortedCondition);
	}

	public E load(Serializable id) {
		return dao.load(id);
	}

	public List<E> queryByProerties(String[] propName, Object[] propValue) {
		return dao.queryByProerties(propName, propValue);
	}

	public List<E> queryByProerties(String propName, Object propValue) {
		return dao.queryByProerties(propName, propValue);
	}

	public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		return dao.queryByProerties(propName, propValue, sortedCondition);
	}

	public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return dao.queryByProerties(propName, propValue, sortedCondition);
	}

	public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top) {
		return dao.queryByProerties(propName, propValue, sortedCondition, top);
	}

	public List<E> queryByProerties(String[] propName, Object[] propValue, Integer top) {
		return dao.queryByProerties(propName, propValue, top);
	}

	public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top) {
		return dao.queryByProerties(propName, propValue, sortedCondition, top);
	}

	public List<E> queryByProerties(String propName, Object propValue, Integer top) {
		return dao.queryByProerties(propName, propValue, top);
	}

	public E merge(E entity) {
		return dao.merge(entity);
	}

	public void update(E entity) {
		dao.update(entity);
	}
	
	public void saveOrUpdate(E entity) {
		dao.saveOrUpdate(entity);
	}

	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue) {
		dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue) {
		dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue) {
		dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue) {
		dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	public void update(E entity, Serializable oldId) {
		dao.update(entity, oldId);
	}

	public List<E> doQueryAll() {
		return dao.doQueryAll();
	}

	public List<E> doQueryAll(Map<String, String> sortedCondition, Integer top) {
		return dao.doQueryAll(sortedCondition, top);
	}

	public List<E> doQueryAll(Integer top) {
		return dao.doQueryAll(top);
	}

	public void evict(E entity) {
		dao.evict(entity);
	}

	public void clear() {
		dao.clear();
	}

	public Long countAll() {
		return dao.countAll();
	}

	public Long doCount(BaseParameter parameter) {
		return dao.doCount(parameter);
	}

	public List<E> doQuery(BaseParameter parameter) {
		return dao.doQuery(parameter);
	}

	public QueryResult<E> doPaginationQuery(BaseParameter parameter) {
		return dao.doPaginationQuery(parameter);
	}

	public QueryResult<E> doPaginationQuery(BaseParameter parameter, boolean bool) {
		return dao.doPaginationQuery(parameter, bool);
	}
	
	@Override
	public void deleteAudit(String conditionName, Object conditionValue) {
		Timestamp now = new Timestamp(new Date().getTime());
		String[] propertyName = {"isDeleted", "updateTime"};
		Object[] propertyValue = {"Y", now};
		dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}
}