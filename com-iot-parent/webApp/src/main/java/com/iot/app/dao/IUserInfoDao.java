package com.iot.app.dao;

import org.springframework.stereotype.Repository;

import com.iot.app.common.dao.IBaseDAO;
import com.iot.app.entity.UserInfoDto;

@Repository("iBaseDAO")
public interface IUserInfoDao extends IBaseDAO<UserInfoDto>{

}
