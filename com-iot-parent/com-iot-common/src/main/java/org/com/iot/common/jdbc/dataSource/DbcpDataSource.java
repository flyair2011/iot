package org.com.iot.common.jdbc.dataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.com.iot.common.AESUtils;

public class DbcpDataSource extends BasicDataSource{

	@Override
	 public void setPassword(String password){
		 super.setPassword(AESUtils.decrypt(password));
	 }
}
