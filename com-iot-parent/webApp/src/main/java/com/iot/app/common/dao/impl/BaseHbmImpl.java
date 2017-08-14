package com.iot.app.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.com.iot.common.GenericsUtils;
import org.com.iot.common.SQLUtil;
import org.com.iot.common.StringUtil;
import org.com.iot.common.Tools;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iot.app.common.dao.IBaseDAO;



@Transactional
@SuppressWarnings("all")
@Component("iBaseDAO")
public class BaseHbmImpl<E> extends HibernateDaoSupport implements IBaseDAO<E> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	 @Resource(name="sessionFactory")  
     public void setSuperSessionFactory(SessionFactory sessionFactory){  
        super.setSessionFactory(sessionFactory);  
     }  
	 
	
	List<E> list = new ArrayList<E>();  
	
	protected Class<E> entityClass;
	
	protected Class getEntityClass(){
	    return this.entityClass;
	}
	
	public BaseHbmImpl(){
		this.entityClass = GenericsUtils.getGenericClass(getClass());
	}
	
	public void remove(Class clazz, Serializable id) {
		getHibernateTemplate().delete(get(clazz, id));
	}

	public void remove(Object o) {	
		getHibernateTemplate().delete(o);
	}
	
	public void removeObjectsByIds(String[] ids){		
			list = new ArrayList<E>();
			for(int i = 0; i < ids.length; i++){				
				list.add((E) get(Integer.parseInt(ids[i])));
			}
			deleteAll(list);	
	}

	public void deleteAll(Collection collection){
		getHibernateTemplate().deleteAll(collection);
	}

	public void save(Object o) {
		
		this.sessionFactory.openSession().save(o);
	}
	
	public Long saveObject(Object o) {
		
		return (Long) this.sessionFactory.openSession().save(o);
	}

	public void saveAll(Collection collection) {
		Iterator iterator = collection.iterator();
	    while (iterator.hasNext())
	    	this.sessionFactory.openSession().save(iterator.next());
	}

	public void saveOrUpdateAll(Collection collection){
		Iterator iterator = collection.iterator();
		 while (iterator.hasNext())
			 this.sessionFactory.openSession().saveOrUpdate(iterator.next());
	}
	public void saveOrUpdate(Object o) {	
		this.sessionFactory.openSession().saveOrUpdate(o);
	}

	public void update(Object o) {	
		this.sessionFactory.openSession().update(o);
	}

	public void updateAll(Collection collection) {
		Iterator iterator = collection.iterator();
	    while (iterator.hasNext())
	    	this.sessionFactory.openSession().update(iterator.next());
	}
	
	public E load(Serializable id) {
		return (E) this.sessionFactory.openSession().load(getEntityClass(), id);
	}

	public Object load(Class clazz, Serializable id) {
		return getHibernateTemplate().load(clazz, id);
	}

	public List<E> loadAll() {
		return null;
	}

	public E get(Class clazz, Serializable id) {
		return (E) this.sessionFactory.openSession().get(clazz, id);
	}

	public E get(Serializable id) {
		return (E) this.sessionFactory.openSession().get(getEntityClass(), id);
	}
	
	public long getCount() {
		return getCount(null, null);	
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public long getCount(String wherejpql, Object[] queryParams) {
		String entityname = getEntityName(this.entityClass);
		
		Query query = this.sessionFactory.openSession().createQuery("select count("+ getCountField(this.entityClass) +") from "+ entityname+ " o "+ (wherejpql == null || "".equals(wherejpql.trim()) ? "" : " where " + wherejpql));
		setQueryParams(query, queryParams);
		return (Long) query.uniqueResult();
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public boolean isExist(String wherejpql, Object[] queryParams) {
		String entityname = getEntityName(this.entityClass);
		Query query = this.sessionFactory.openSession().createQuery("select o from "+ entityname+ " o "+ (wherejpql == null || "".equals(wherejpql.trim()) ? "" : " where " + wherejpql));
		setQueryParams(query, queryParams);
		if(query.list().size() > 0){
			return false;
		}
		return true;
	}
	
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<E> getObjects(int firstindex, int maxresult, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby) {
		if(null!=wherejpql&&!"".equals(wherejpql)){
		wherejpql=wherejpql.replaceAll("like \\?", "like ? escape'~'");
		}
		String entityname = getEntityName(this.entityClass);
		Query query = this.sessionFactory.openSession().createQuery("select o from "+ entityname+ " o "+ (wherejpql == null || "".equals(wherejpql.trim()) ? "" : " where " + wherejpql) + buildOrderby(orderby));
		setQueryParams(query, queryParams);
		if(firstindex!=-1 && maxresult!=-1) query.setFirstResult(firstindex).setMaxResults(maxresult);
		return query.list();
	}

	public List<E> getObjects(String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		return getObjects(-1, -1, wherejpql, queryParams, orderby);
	}

	public List<E> getObjects(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
		return getObjects(firstindex, maxresult, wherejpql, queryParams, null);	
	}

	public List<E> getObjects(int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getObjects(firstindex, maxresult, null, null, orderby);	
	}

	public List<E> getObjects(String wherejpql, Object[] queryParams) {
		return getObjects(-1, -1, wherejpql, queryParams, null);	
	}

	public List<E> getObjects(LinkedHashMap<String, String> orderby) {
		return getObjects(-1, -1, null, null, orderby);	
	}

	public List<E> getObjects(int firstindex, int maxresult) {
		return getObjects(firstindex, maxresult, null, null, null);	
	}

	public List<E> getObjects() {
		return getObjects(-1, -1, null, null, null);
	}
	
	protected static void setQueryParams(Query query, Object[] params){
		if(params != null && params.length > 0){
			for(int i = 0; i < params.length; i++){
				if(params[i].toString().contains("_")){
					query.setParameter(i, params[i].toString().replaceAll("_", "~_"));
				}else{
					query.setParameter(i, params[i]);
				}
				
			}
		}
	}
	
	protected static <E> String getEntityName(Class<E> clazz){
		
		
		
		 String entityname= clazz.getSimpleName();
		
		
//		Entity entity = clazz.getAnnotation(Entity.class);
//		if(entity != null && entity.name() != null && !"".equals(entity.name())){
//			entityname = entity.name();
//		}
		return entityname;
	}
	
	protected static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	
	protected static <E> String getCountField(Class<E> clazz){
		String out = "o";
//		try {
//			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
//			for(PropertyDescriptor propertydesc : propertyDescriptors){
//				Method method = propertydesc.getReadMethod();
//				if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){					
//					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
//					out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        return out;
	}
	
	public List<E> getObjectsByParams(int firstindex, int maxresult,Map<String ,Object> paramMap) {
		String hql ;
		Object[] queryParams;
		if(paramMap==null||paramMap.size()==0){
			
			return  this.getObjects(firstindex, maxresult);
		}
		hql=SQLUtil.createWhereSQL(paramMap);		
		queryParams=SQLUtil.createParams(paramMap);		
		return this.getObjects(firstindex, maxresult, hql, queryParams,null);	
	}
	
	public List<E> getObjectsByParams(int firstindex, int maxresult,Map<String ,Object> paramMap,LinkedHashMap<String, String> orderby) {
		String hql ;
		Object[] queryParams;
		if(paramMap==null||paramMap.size()==0){
			
			return  this.getObjects(firstindex, maxresult,orderby);
		}
		hql=SQLUtil.createWhereSQL(paramMap);		
		queryParams=SQLUtil.createParams(paramMap);		
		return this.getObjects(firstindex, maxresult, hql, queryParams,orderby);	
	}
	
	public List<E> getObjectsByParams(int firstindex, int maxresult,String hql,Map<String ,Object> paramMap,LinkedHashMap<String, String> orderby) {
		Object[] queryParams;
		if(paramMap==null||paramMap.size()==0){
			return  this.getObjects(firstindex, maxresult, hql, null, orderby);
		}
		hql=SQLUtil.createWhereSQL(paramMap)+hql;		
		queryParams=SQLUtil.createParams(paramMap);		
		return this.getObjects(firstindex, maxresult, hql, queryParams,orderby);	
	}
	
	
	
	public long getCountByParams(Map<String, Object> paramMap) {
		
		String hql ;
		Object[] queryParams;
		if(paramMap==null||paramMap.size()==0){
			return  this.getCount();
		}
		hql=SQLUtil.createWhereSQL(paramMap);
		queryParams=SQLUtil.createParams(paramMap);
		return this.getCount( hql, queryParams);
	}
	
	
	public E getObjectByExample(Map<String ,Object> paramMap) {
		String hql = null;
		Object[] queryParams = null;
		if (null != paramMap) {
			hql = SQLUtil.createWhereSQL(paramMap);		
			queryParams = SQLUtil.createParams(paramMap);
		}
		return this.getObject(hql, queryParams);	
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public E getObject(String wherejpql, Object[] queryParams) {
		String entityname = getEntityName(this.entityClass);
		Query query = this.sessionFactory.openSession().createQuery("select o from "+ entityname+ " o "+ (wherejpql == null || "".equals(wherejpql.trim()) ? "" : " where " + wherejpql));
		setQueryParams(query, queryParams);
		
		List<E> list = query.list();
		if (null != list&&list.size()!=0) {		//即使list不为空，也要判断list里面有没有返回的数据
			return (E) list.get(0);
		}
		
		return null;
	}
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List getObjectList(String sql){
		Query query = this.sessionFactory.openSession().createSQLQuery(sql);
		return query.list();
	}
	
	public List findNoPageBySql(String sql, List<Object> para) {
		// TODO Auto-generated method stub
		if(StringUtil.isNotEmpty(sql)){
			sql=sql.replaceAll("like \\?", "like ? escape'~'");
		}
		List list = null;
		Session session = this.sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(sql);
		setQueryParams(query,para);
		list = query.list();
		return list;
	}
	
	private void setQueryParams(Query query, List<Object> params){
		if(params != null){
			for(int i = 0; i < params.size(); i++){
				query.setParameter(i, params.get(i));
			}
		}
	}
	
	
	public void removeObjectsByIds(String key,String[] ids){
		String entityname = getEntityName(this.entityClass);
		String sql="delete from "+entityname+" as o where o."+key+" in ("+Tools.getStringForSZ(ids)+")";
		Query query = this.sessionFactory.openSession().createQuery(sql);
		query.executeUpdate();
	} 
	public static void main(String[] args) {
		String str="1=1 and o.filterName like ? ";
		System.out.println("aa"+str.replaceAll("like \\?", "like ?escape'~'"));
	}

	public List getObjectList(int firstindex, int maxresult, String sql) {
		Query query = this.sessionFactory.openSession().createSQLQuery(sql);
		if(firstindex!=-1 && maxresult!=-1) query.setFirstResult(firstindex).setMaxResults(maxresult);
		return query.list();
	}

	public long getCountByParams(String hql, Map<String, Object> paramMap) {
		Object[] queryParams;
		if(paramMap==null||paramMap.size()==0){
			return  this.getCount();
		}
		hql=SQLUtil.createWhereSQL(paramMap)+hql;;
		queryParams=SQLUtil.createParams(paramMap);
		return this.getCount( hql, queryParams);
	}

	
}
