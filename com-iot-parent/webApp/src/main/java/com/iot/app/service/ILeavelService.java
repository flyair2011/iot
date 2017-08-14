package com.iot.app.service;

import java.util.List;

import com.iot.app.entity.LeavelDto;

public interface ILeavelService {

	LeavelDto load(Integer id) throws Exception;
	
	LeavelDto get(Integer id) throws Exception;

	List<LeavelDto> findAll() throws Exception;

	void persist(LeavelDto entity) throws Exception;

	Integer save(LeavelDto entity) throws Exception;

	void saveOrUpdate(LeavelDto entity) throws Exception;

	void delete(Integer id) throws Exception;
	
}
