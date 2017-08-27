package org.com.iot.common.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
/**
 * JDBC公共类
 * @author Mickle
 *
 */
public class JdbcUtil {

	private static BasicDataSource dataSource = null;
	
	static{
		Properties p2 = new Properties();
		Properties p = new Properties();
		InputStream inStream = null;
		try {
			inStream = JdbcUtil.class.getResourceAsStream("/config.properties");
			p.load(inStream);
			String driverClassName = p.getProperty("jdbc.driver");
			String url = p.getProperty("jdbc.url");
			String username = p.getProperty("jdbc.username");
			String password = p.getProperty("jdbc.password");
			p2.put("driverClassName", driverClassName);
			p2.put("url",  url);
			p2.put("username", username);
//			p2.put("password", AESUtils.decrypt(password));
			p2.put("password", password);
			
			dataSource = BasicDataSourceFactory.createDataSource(p2);	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (null != inStream){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static List getSSObjectsBySql(String sql){

		
		// TODO Auto-generated method stub
//		if(StringUtil.isNotEmpty(sql)){
//			sql=sql.replaceAll("like \\?", "like ? escape'~'");
//		}
		System.out.println(sql);
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet res = null;
		List<Object> list =  new ArrayList<Object>();
		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
			ResultSetMetaData resdata = res.getMetaData();
			int col = resdata.getColumnCount();
			
			if (col > 1){
				Object[] os = null;
				while(res.next()){
					os = new Object[col];
					for (int i=0; i<col; i++){
						os[i] = res.getObject(i+1); 
					}
					list.add(os);
				}
			}else{
				Object o = null;
				while(res.next()){
					o = res.getObject(1);
					list.add(o);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (null != res)
					res.close();
					if (null != stmt)
					stmt.close();
					if (null != conn)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		Connection c = getConnection();
		System.out.println(c);
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
