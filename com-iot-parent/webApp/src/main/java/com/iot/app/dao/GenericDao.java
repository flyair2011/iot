package com.iot.app.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @date 25 Junl 2017
 * @see It is the base dao interface
 * @author Mickle
 */
public interface GenericDao <T, PK extends Serializable>{

	T load(PK id) throws Exception;
	
	T get(PK id) throws Exception;
	
	List<T> findALL() throws Exception;
	
	void persist(T entity) throws Exception;
	
	PK save(T entity) throws Exception;
	
	void saveOrUpdate(T entity) throws Exception;
	
	void delete(PK id) throws Exception;
	
	void flush() throws Exception;
	
	List<T> findItemsByHQ(String hql);
	
}
