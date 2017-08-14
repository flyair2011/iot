package com.iot.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.app.dao.ILeavelDao;
import com.iot.app.entity.LeavelDto;
import com.iot.app.service.ILeavelService;

@Service("leavleService")
public class LeavleServiceImpl implements ILeavelService {
	
	@Autowired
	private ILeavelDao leavelDao;

	public LeavelDto load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public LeavelDto get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return leavelDao.get(id);
	}

	public List<LeavelDto> findAll() throws Exception {
		// TODO Auto-generated method stub
		return leavelDao.findALL();
	}

	public void persist(LeavelDto entity) throws Exception {
		// TODO Auto-generated method stub
		leavelDao.persist(entity);
	}

	public Integer save(LeavelDto entity) throws Exception {
		// TODO Auto-generated method stub
		return leavelDao.save(entity);
	}

	public void saveOrUpdate(LeavelDto entity) throws Exception {
		// TODO Auto-generated method stub
		leavelDao.saveOrUpdate(entity);
	}

	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		leavelDao.delete(id);
	}

}
