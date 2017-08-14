package com.iot.app.dao.impl;

import org.springframework.stereotype.Service;

import com.iot.app.common.dao.impl.BaseHbmImpl;
import com.iot.app.dao.IUserInfoDao;
import com.iot.app.entity.UserInfoDto;

@Service("userInforDao")
public class UserInfoDaoImpl extends BaseHbmImpl<UserInfoDto> implements IUserInfoDao {


}
