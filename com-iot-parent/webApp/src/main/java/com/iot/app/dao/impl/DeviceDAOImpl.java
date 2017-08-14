package com.iot.app.dao.impl;

import org.springframework.stereotype.Service;

import com.iot.app.common.dao.IBaseDAO;
import com.iot.app.common.dao.impl.BaseHbmImpl;
import com.iot.app.entity.DeviceDto;

@Service("deviceDao")
public class DeviceDAOImpl extends BaseHbmImpl<DeviceDto> implements IBaseDAO<DeviceDto> {

}
