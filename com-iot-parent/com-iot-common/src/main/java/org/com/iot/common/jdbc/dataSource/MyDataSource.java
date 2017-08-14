package org.com.iot.common.jdbc.dataSource;

import org.com.iot.common.AESUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 此类继承了DriverManagerDataSource并重写setPassword方法,用来解密密码密文
 * @author Mickle
 *
 */
public class MyDataSource extends DriverManagerDataSource{
 @Override
 public void setPassword(String password){
	 super.setPassword(AESUtils.decrypt(password));
 }
}
