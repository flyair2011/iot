package com.iot.app.service.impl;

import org.springframework.stereotype.Service;

import com.iot.app.common.service.impl.BaseServiceImpl;
import com.iot.app.entity.UserInfoDto;
import com.iot.app.service.IUserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoDto> implements IUserInfoService {


}
