package com.iot.app.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.iot.app.common.action.BaseAction;
import com.iot.app.common.action.Result;
import com.iot.app.entity.LeavelDto;
import com.iot.app.service.ILeavelService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @date 25 Junk 2017
 * @see Equement Leavel action
 * @author Mickle
 *
 */

@Namespace("/leavel")
@ParentPackage("struts-default")
public class LeavelAction extends BaseAction implements ModelDriven<LeavelDto>, Preparable{

	
	private Integer id;
	
	private LeavelDto leavleDto;
	
	@Autowired
	private ILeavelService leavelService;
	
	private List<LeavelDto> leavelDtos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Action(value="leav")
	public void getAllLeavle() throws Exception{
		
		leavelDtos = leavelService.findAll();
		Result result = new Result();
		result.setCode(0);
		result.setSuccess(true);
		result.setObj(leavelDtos);
		this.writeJson(result);
		
	}

	public LeavelDto getLeavleDto() {
		return leavleDto;
	}

	public void setLeavleDto(LeavelDto leavleDto) {
		this.leavleDto = leavleDto;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public LeavelDto getModel() {
		// TODO Auto-generated method stub
		return leavleDto;
	}
	
	
}
