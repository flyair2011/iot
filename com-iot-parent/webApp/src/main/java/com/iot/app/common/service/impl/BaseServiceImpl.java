package com.iot.app.common.service.impl;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.app.common.dao.IBaseDAO;
import com.iot.app.common.service.IBaseService;

@Service("baseService")
public class BaseServiceImpl<E> implements IBaseService<E>{
	public static final Logger log = Logger.getLogger(BaseServiceImpl.class);
	private int exceptionLine;
	
	@Autowired
	public IBaseDAO<E> iBaseDAO;
	
	
	public String loginUserName = "admin";
	public String secondAccount = "admin";
	
	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getSecondAccount() {
		return secondAccount;
	}

	public void setSecondAccount(String secondAccount) {
		this.secondAccount = secondAccount;
	}


	public IBaseDAO<E> getiBaseDAO() {
		return iBaseDAO;
	}

	public void setiBaseDAO(IBaseDAO<E> iBaseDAO) {
		this.iBaseDAO = iBaseDAO;
	}
	
	public boolean isExist(String wherejpql, Object[] queryParams){
		return iBaseDAO.isExist(wherejpql, queryParams);
	}

	public void remove(Class clazz, Serializable id) {
		iBaseDAO.remove(clazz, id);
	}

	public void remove(Object o) {
		iBaseDAO.remove(o);
	}
	public boolean removeReturnBoolean(Object o){
		boolean flag=true;
		try{
			iBaseDAO.remove(o);
		}catch (Exception e){
			flag=false;
		}
		return flag;
	}

	
	public void removeObjectsByIds(String[] ids) {
		iBaseDAO.removeObjectsByIds(ids);
	}

	public void deleteAll(Collection collection) {
		iBaseDAO.deleteAll(collection);
	}

	public void save(Object o) {
		iBaseDAO.save(o);
	}
	public boolean saveReturnBoolean(Object o) {
		boolean flag=true;
		try{
			iBaseDAO.save(o);
		}catch (Exception e){
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

	public void saveAll(Collection collection) {
		iBaseDAO.saveAll(collection);
	}

	public void saveOrUpdate(Object o) {
		iBaseDAO.saveOrUpdate(o);
	}

	public void update(Object o) {
		iBaseDAO.update(o);
	}
	public boolean updateReturnBoolean(Object o){
		boolean flag=true;
		try{
			iBaseDAO.update(o);
		}catch (Exception e){
			flag=false;
		}
		return flag;
	}
	public void updateAll(Collection collection) {
		iBaseDAO.updateAll(collection);
	}

	public E load(Serializable id) {
		return (E) iBaseDAO.load(id);
	}

	public Object load(Class clazz, Serializable id) {
		return iBaseDAO.load(clazz, id);
	}

	public List<E> loadAll() {
		return iBaseDAO.loadAll();
	}

	public E get(Class clazz, Serializable id) {
		return (E) iBaseDAO.get(clazz, id);
	}

	public E get(Serializable id) {
		return (E) iBaseDAO.get(id);
	}

	public long getCount() {
		return iBaseDAO.getCount();
	}

	public long getCount(String wherejpql, Object[] params) {
		return iBaseDAO.getCount(wherejpql, params);
	}
	
	public List<E> getObjects(int firstindex, int maxresult, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby) {
		return iBaseDAO.getObjects(firstindex, maxresult, wherejpql, queryParams, orderby);
	}

	public List<E> getObjects(String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		return iBaseDAO.getObjects(wherejpql, queryParams, orderby);
	}

	public List<E> getObjects(int firstindex, int maxresult, String wherejpql,
			Object[] queryParams) {
		return iBaseDAO.getObjects(firstindex, maxresult, wherejpql, queryParams);
	}

	public List<E> getObjects(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby) {
		return iBaseDAO.getObjects(firstindex, maxresult, orderby);
	}

	public List<E> getObjects(String wherejpql, Object[] queryParams) {
		return iBaseDAO.getObjects(wherejpql, queryParams);
	}

	public List<E> getObjects(LinkedHashMap<String, String> orderby) {
		return iBaseDAO.getObjects(orderby);
	}

	public List<E> getObjects(int firstindex, int maxresult) {
		return iBaseDAO.getObjects(firstindex, maxresult);
	}

	public List<E> getObjects() {
		return iBaseDAO.getObjects();
	}

	public void saveOrUpdateAll(Collection collection){
		iBaseDAO.saveOrUpdateAll(collection);
	}

	public List<E> getObjectsByParams(int firstindex,
			int maxresult, Map<String, Object> paramMap) {
		
		return iBaseDAO.getObjectsByParams(firstindex, maxresult, paramMap);
	}

	public long getCountByParams(Map<String, Object> paramMap) {
		
		return iBaseDAO.getCountByParams(paramMap);
	}

	public E getObject(String wherejpql, Object[] queryParams) {
		return iBaseDAO.getObject(wherejpql, queryParams);
	}

	public E getObjectByExample(Map<String, Object> paramMap) {
		return iBaseDAO.getObjectByExample(paramMap);
	}
	
	public static String getIpAddr() {
    	InetAddress addr=null;
    	String ip="";
		try {
			addr = InetAddress.getLocalHost();
			 ip= addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        return ip;
    }
	public void saveExceptionMsg(Exception e, String className, String moduleName, String text, String resId, String resName, String opType){
		StackTraceElement[] stacks = e.getStackTrace();
		for(int i = 0; i < stacks.length; i++){
			if(stacks[i].getClassName().equals(className)){
				exceptionLine = stacks[i].getLineNumber();
			}
		}
		log.error(loginUserName + "|" + secondAccount + "|" + resId + "|" + resName + "|" + getIpAddr() + "|" +opType + "|" + moduleName + "|" + "�쳣�����У�" + exceptionLine + "," + text + ":" + e.getMessage());
	}

	
	public List<E> getObjectsByParams(int firstindex, int maxresult,
			Map<String, Object> paramMap, LinkedHashMap<String, String> orderby) {
		return iBaseDAO.getObjectsByParams(firstindex, maxresult, paramMap, orderby);
	}
	
	public List getObjectList(String sql)
	{
		return iBaseDAO.getObjectList(sql);
	}
	public void removeObjectsByIds(String key,String[] ids) {
		iBaseDAO.removeObjectsByIds(key,ids);
	}
	public List findNoPageBySql(String sql, List<Object> para){
		return iBaseDAO.findNoPageBySql(sql, para);
	}

	@Override
	public List getObjectList(int firstindex, int maxresult, String sql) {
		return iBaseDAO.getObjectList(firstindex,maxresult,sql);
	}

	@Override
	public long getCountByParams(String hql, Map<String, Object> paramMap) {
		return iBaseDAO.getCountByParams(hql, paramMap);
	}

	@Override
	public List<E> getObjectsByParams(int firstindex, int maxresult,
			String hql, Map<String, Object> paramMap,
			LinkedHashMap<String, String> orderby) {
		return iBaseDAO.getObjectsByParams(firstindex, maxresult, hql, paramMap, orderby);
	}
}
