package com.iot.app.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;





/**
 * 通用 DAO接口
 * @author Mickle
 */
@SuppressWarnings("all")
public interface IBaseDAO<E> {
	
	/**
	 * 根据查询条件查询记录是否存在
	 * @param wherejpql    查询条件
	 * @param queryParams  查询参数
	 * @date  2015-05-14
	 */
	public boolean isExist(String wherejpql, Object[] queryParams);
	
	/**
	 * 删除实体集合
	 * @param ids     实体ID数组
	 */
	public void removeObjectsByIds(String[] ids);
	
	/**
	 * 根据条件查询实体总数
	 * @param wherejpql   查询条件
	 * @param params      查询参数
	 */
	public long getCount(String wherejpql, Object[] params);
	
	
	/**
	 * 查询实体总数
	 */
	public long getCount();
	
	/**
	 * 加载实体(只用于查询)
	 * @param id   实体ID
	 */
	public E load(Serializable id);

	/**
	 * 加载实体(只用于查询)
	 * @param clazz   实体类
	 * @param id      实体ID
	 */
	public Object load(Class clazz, Serializable id);

	/**
	 * 加载所有实体(只用于查询)
	 */
	public List<E> loadAll();

	/**
	 * 获取实体
	 * @param clazz  实体类
	 * @param id     实体ID
	 */
    public E get(Class clazz, Serializable id);

    /**
	 * 获取实体
	 * @param id   实体ID
	 */
	public E get(Serializable id);

	/**
	 * 删除实体
	 * @param clazz   实体类
	 * @param id      实体ID
	 */
	public void remove(Class clazz, Serializable id);

	/**
	 * 删除实体
	 * @param o     实体对象
	 */
	public void remove(Object o);

	/**
	 * 删除所有实体集合
	 * @param collection   所有实体集合
	 */
	public void deleteAll(Collection collection);

	/**
	 * 保存实体
	 * @param o      实体类
	 */
	public void save(Object o);

	public Long saveObject(Object o);
	
	/**
	 * 保存所有集合实体
	 * @param collection   所有实体集合
	 */
	public void saveAll(Collection collection);

	/**
	 * 保存或更新实体
	 * @param o     实体类
	 */
	public void saveOrUpdate(Object o);
	/**
	 * 保存或更新实体集合
	 * @param o    
	 */
	public void saveOrUpdateAll(Collection collection);
	/**
	 * 更新实体
	 * @param o     实体类
	 */
	public void update(Object o);

	/**
	 * @param <E>
	 * @param collection   所有实体集合
	 */
	public void updateAll(Collection collection);
	
	/**
	 * 获取分页数据
	 * @param <E>
	 * @param firstindex   开始索引
	 * @param maxresult    最大条数
	 * @param wherejpql    查询条件
	 * @param queryParams  查询参数
	 * @param orderby      排序参数
	 * @return             实体结果集
	 */
	public List<E> getObjects(int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);
	
	/**
	 * 获取分页数据
	 * @param <E>
	 * @param wherejpql    查询条件
	 * @param queryParams  查询参数
	 * @param orderby      排序参数
	 * @return             实体结果集
	 */
	public List<E> getObjects(String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);
	
	/**
	 * 获取分页数据
	 * @param <E>
	 * @param firstindex   开始索引
	 * @param maxresult    最大条数
	 * @param wherejpql    查询条件
	 * @param queryParams  查询参数
	 * @return             实体结果集
	 */
	public List<E> getObjects(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
	
	/**
	 * 获取分页数据
	 * @param <E>
	 * @param firstindex   开始索引
	 * @param maxresult    最大条数
	 * @param orderby      排序参数
	 * @return             实体结果集
	 */
	public List<E> getObjects(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);
	
	/**
	 * 获取分页数据
	 * @param <E>
	 * @param wherejpql    查询条件
	 * @param queryParams  查询参数
	 * @return             实体结果集
	 */
	public List<E> getObjects(String wherejpql, Object[] queryParams);
	
	/**
	 * 获取分页数据
	 * @param <E>
	 * @param orderby      排序参数
	 * @return             实体结果集
	 */
	public List<E> getObjects(LinkedHashMap<String, String> orderby);
	
	/**
	 * 获取分页数据
	 * @param <E>
	 * @param firstindex   开始索引
	 * @param maxresult    最大条数
	 * @return             实体结果集
	 */
	public List<E> getObjects(int firstindex, int maxresult);	
	
	/**
	 * 获取分页数据
	 * @param <E>
	 * @return             实体全部结果集
	 */
	public List<E> getObjects();
	
	/**
	 * 根据查询参数获得分页数据
	 * @param <E>
	 * @param firstindex   开始索引
	 * @param maxresult    最大条数
	 * @param paramMap	       参数(key为字段名称 value为值)
	 * @return             实体结果集
	 */
	public List<E> getObjectsByParams(int firstindex, int maxresult,Map<String ,Object> paramMap);
	
	/**
	 * 根据查询参数获得分页数据
	 * @param <E>
	 * @param firstindex   开始索引
	 * @param maxresult    最大条数
	 * @param paramMap	       参数(key为字段名称 value为值)
	 * @param orderby      排序参数
	 * @return             实体结果集
	 */
	public List<E> getObjectsByParams(int firstindex, int maxresult,Map<String ,Object> paramMap,LinkedHashMap<String, String> orderby);
	
	/**
	 * 根据查询参数获得分页数据
	 * @param <E>
	 * @param firstindex   开始索引
	 * @param maxresult    最大条数
	 * @param paramMap	       参数(key为字段名称 value为值)
	 * @param orderby      排序参数
	 * @return             实体结果集
	 */
	public List<E> getObjectsByParams(int firstindex, int maxresult,String hql,Map<String ,Object> paramMap,LinkedHashMap<String, String> orderby);
	
	/**
	 * 根据条件查询实体总数
	 * @param paramMap	       参数(key为字段名称 value为值)
	 */
	public long getCountByParams(Map<String ,Object> paramMap);
	
	/**
	 * 根据条件查询实体总数
	 * @param paramMap	       参数(key为字段名称 value为值)
	 */
	public long getCountByParams(String hql,Map<String ,Object> paramMap);
	
	
	/**查找实体单个实体对象
	 * @param paramMap
	 * @return
	 */
	public E getObjectByExample(Map<String ,Object> paramMap);
	
	
	/**根据查询条件查询单个实体对象
	 * @param wherejpql
	 * @param paramMap
	 * @return
	 */
	public E getObject(String wherejpql, Object[] queryParams);
	
	/**
	 * 根据通用sql语句查询结果集
	 * @param sql  关系库通用sql
	 * @return
	 */
	public List getObjectList(String sql);
	/**
	 * 根据通用sql语句查询结果集
	 * @param firstindex   开始索引
	 * @param maxresult    最大条数
	 * @param sql  关系库通用sql
	 * @return
	 */
	public List getObjectList(int firstindex, int maxresult,String sql);
	/**
	 * 批量删除实体集合
	 * @param key 数据库表字段名
	 * @param ids     实体ID数组
	 */
	public void removeObjectsByIds(String key,String[] ids);
	/**
	 * sql查询
	 */
	public List findNoPageBySql(String sql, List<Object> para);
}
