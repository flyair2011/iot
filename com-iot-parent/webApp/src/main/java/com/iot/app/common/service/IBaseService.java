package com.iot.app.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("iBaseService")
public interface IBaseService<E> {
	
	public boolean isExist(String wherejpql, Object[] queryParams);
	
	public void remove(Class clazz, Serializable id);
	public void remove(Object o);
	public boolean removeReturnBoolean(Object o);
	
	public void removeObjectsByIds(String[] ids);

	public void deleteAll(Collection collection);

	public void save(Object o);
	public boolean saveReturnBoolean(Object o);

	public void saveAll(Collection collection);

	public void saveOrUpdate(Object o);

	public void update(Object o);
	public boolean updateReturnBoolean(Object o);
	public void updateAll(Collection collection);
	
	public E load(Serializable id);

	public Object load(Class clazz, Serializable id);
	public List<E> loadAll();

	public E get(Class clazz, Serializable id);

	public E get(Serializable id);
	public long getCount();
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public long getCount(String wherejpql, Object[] queryParams);

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<E> getObjects(int firstindex, int maxresult, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby);

	public List<E> getObjects(String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby);

	public List<E> getObjects(int firstindex, int maxresult, String wherejpql,
			Object[] queryParams);

	public List<E> getObjects(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby);
	public List<E> getObjects(String wherejpql, Object[] queryParams);

	public List<E> getObjects(LinkedHashMap<String, String> orderby);

	public List<E> getObjects(int firstindex, int maxresult);

	public List<E> getObjects();

	
	public void saveOrUpdateAll(Collection collection);

	
	public List<E> getObjectsByParams(int firstindex, int maxresult,
			Map<String,Object> paramMap) ;
	public List<E> getObjectsByParams(int firstindex, int maxresult,
			Map<String,Object> paramMap,LinkedHashMap<String, String> orderby) ;
	
	public List<E> getObjectsByParams(int firstindex, int maxresult,String hql,
			Map<String,Object> paramMap,LinkedHashMap<String, String> orderby) ;
	
	public long getCountByParams(Map<String,Object> paramMap);
	
	public long getCountByParams(String hql,Map<String,Object> paramMap);
	
	
	public E getObjectByExample(Map<String ,Object> paramMap);
	
	public E getObject(String wherejpql, Object[] queryParams);
	
	public List getObjectList(String sql);
	
	public List getObjectList(int firstindex, int maxresult,String sql);
	public void removeObjectsByIds(String key,String[] ids);
	public List findNoPageBySql(String sql, List<Object> para);
}
