package com.iot.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iot.app.dao.ILeavelDao;
import com.iot.app.entity.LeavelDto;

@Repository("leavelDao")
public class LeavleDaoImpl implements ILeavelDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	
	public LeavelDto load(Integer id) {
		// TODO Auto-generated method stub
		return (LeavelDto) this.getCurrentSession().load(LeavelDto.class, id);
	}

	public LeavelDto get(Integer id) {
		// TODO Auto-generated method stub
		return (LeavelDto) this.getCurrentSession().get(LeavelDto.class, id);
	}

	public List<LeavelDto> findALL() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<LeavelDto> userInfos = this.getCurrentSession()
				.createQuery("from LeavelDto").list();
		return userInfos;
	}

	public void persist(LeavelDto entity) {
		// TODO Auto-generated method stub

	}

	public Integer save(LeavelDto entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveOrUpdate(LeavelDto entity) {
		// TODO Auto-generated method stub

	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	public void flush() {
		// TODO Auto-generated method stub

	}


	@Override
	public List<LeavelDto> findItemsByHQ(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

}
