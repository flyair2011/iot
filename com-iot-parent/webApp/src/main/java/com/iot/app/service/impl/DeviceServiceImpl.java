package com.iot.app.service.impl;

import org.springframework.stereotype.Service;

import com.iot.app.common.service.impl.BaseServiceImpl;
import com.iot.app.entity.DeviceDto;
import com.iot.app.service.IDeviceService;

@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl<DeviceDto> implements IDeviceService {
	
}
