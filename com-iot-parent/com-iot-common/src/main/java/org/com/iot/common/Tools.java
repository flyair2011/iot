package org.com.iot.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Tools {
	public static String ONETAB = "&nbsp;&nbsp;";
	public static String TWOTAB = "&nbsp;&nbsp;&nbsp;&nbsp;";
	public static String BR = "<br>";
	
	/*************************** Java通用工具类方法添加		huanhongliang	2015/5/7	***********************/
	
	/**@author Mickle
	 * @param roleId	角色ID
	 * @param roles		角色数组
	 * @return	是否为指定的角色
	 */
	public static boolean judgeRoleInArr(String roleId, String roles[]) {
		int length = roles.length;
		for (int i = 0; i < length; i++) {
			if (roleId.equals(roles[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**@author 环宏亮
	 * @param str	字符串
	 * @return	是否为空串
	 */
	public static boolean isEmptyString(String str) {
		boolean flag = false;
		if (str == null || str.length() <= 0) {
			flag = true;
		}
		return flag;
	}

	/**@author 环宏亮
	 * @param source	要加密的字符串
	 * @return	MD5加密后的字符串
	 */
	public static String md5(String source) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char str[] = new char[16 * 2];
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source.getBytes());
			byte tmp[] = md.digest();
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(str);
	}

	/**@author 环宏亮
	 * @param n		随机数的位数
	 * @return	产生n位不重复的随机数
	 */
	public static String generateRandomPassword(int n) {
		char[] pw = new char[n];
		int c = 'A';
		int r1 = 0;
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			r1 = random.nextInt(4);
			switch (r1) {
			case 0:
				c = '0' + random.nextInt(2);
				break;
			case 1:
				c = 'a' + random.nextInt(26);
				break;
			case 2:
				c = 'b' + random.nextInt(25);
				break;
			}
			pw[i] = (char) c;
		}
		return new String(pw);
	}


	/**@author 环宏亮
	 * @param month		月份数	
	 * @return		格式化的月份表示
	 */
	public static String formatMonth(int month) {
		String m_temp = null;
		if (month < 10) {
			m_temp = "0" + month;
		} else {
			m_temp = month + "";
		}
		return m_temp;
	}

	/**@author 环宏亮
	 * @param number	要传入的数值
	 * @return		格式化的数值表示
	 */
	public static String formatNumber(int number) {
		String n_temp = null;
		if (number < 10) {
			n_temp = "00" + number;
		} else if (number < 100) {
			n_temp = "0" + number;
		} else {
			n_temp = "" + number;
		}
		return n_temp;
	}

	/**@author 环宏亮
	 * @param str	要切割的字符串
	 * @param prefix	切割的符号表示
	 * @return	切割的字符串的INT数组
	 */
	public static int[] getSplitData(String str, String prefix) {
		String[] arr = str.split(prefix);
		int length = arr.length;
		int members[] = new int[length];
		for (int i = 0; i < length; i++) {
			members[i] = Integer.parseInt(arr[i]);
		}
		return members;
	}

	
	/**@author 环宏亮
	 * @param currentDate	当前日期
	 * @param startDate	起始日期
	 * @return		起始日期与当前日期的差值小于3天边返回true
	 */
	public static boolean isEmergency(Date currentDate, Date startDate) {
		boolean flag = false;
		long current = currentDate.getTime();
		long start = startDate.getTime();
		long distance = start - current;
		if (distance <= 3 * 24 * 60 * 60 * 1000L) {
			flag = true;
		}
		return flag;
	}

	/**@author 环宏亮
	 * @param startDate		起始日期
	 * @param endDate	结束日期
	 * @return		起始日期和结束日期相差的天数
	 */
	public static double getDistanceDays(Date startDate, Date endDate) {
		double days = 0d;
		long start = startDate.getTime();
		long end = endDate.getTime();
		long distance = end - start;
		if (distance < 1L) {//same day means that leave half day
			days = 0.5;
		} else {
			days = distance / (24 * 60 * 60 * 1000d);
			days++;
		}
		return days;
	}
	
	/**@author 环宏亮
	 * @param date		要格式化的日期
	 * @param includeSHS	是否包括时分秒【指定的日期格式】
	 * @return		格式化的日期
	 */
	public static String formatDate(Date date, boolean includeSHS) {
		String time = null;
		SimpleDateFormat format = null;
		if (includeSHS) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		} else {
			format = new SimpleDateFormat("yyyy-MM-dd");
		}
		time = format.format(date);
		return time;
	}
	
	/**@author 环宏亮
	 * @param date		要格式化的日期
	 * @param includeSHS	是否包括时分秒【指定的日期格式】
	 * @param timeFormat	格式化表示
	 * @return	格式化的日期
	 */
	public static String formatRecDate(Date date, boolean includeSHS, String timeFormat) {
		String time = null;
		SimpleDateFormat format = null;
		if (includeSHS) {
			format = new SimpleDateFormat(timeFormat);
		} else {
			format = new SimpleDateFormat(timeFormat);
		}
		time = format.format(date);
		return time;
	}
	
	
	/**@author 环宏亮
	 * @return	获取起始时间
	 */
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);
        return todayStart.getTime();
	}
	
	/**@author 环宏亮
	 * @return	获取结束时间	
	 */
	public static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();  
		todayEnd.set(Calendar.HOUR, 23);  
		todayEnd.set(Calendar.MINUTE, 59);  
		todayEnd.set(Calendar.SECOND, 59);
        return todayEnd.getTime();
	}

	/**@author 环宏亮
	 * @param parameters	Object参数数组
	 * @param target	目标数组
	 * @param offset	间隔数
	 * @param length	字符串长度
	 * @return		带查询参数的字符串
	 */
	public static String appendUrlString(Object[] parameters, String target[],
			int offset, int length) {
		StringBuffer url = new StringBuffer();
		Object temp = null;
		for (int i = offset; i < length; i++) {
			if (parameters[i] == null) {
				target[i] += "=";
			} else {
				temp = parameters[i];
				if (parameters[i] instanceof Date) {
					temp = formatDate((Date) parameters[i], false);
				}
				target[i] += ("=" + temp);
			}
			if (i != length - 1) {
				target[i] += "&";
			}
			url.append(target[i]);
		}
		return url.toString();
	}

	/**@author 环宏亮
	 * @param emails	邮箱列表
	 * @return	邮箱列表数组
	 */
	@SuppressWarnings({"unchecked"})
	public static String[] getEmailList(List emails) {
		List emailAdd = new ArrayList();
		for (int i = 0; i < emails.size(); i++) {
			if (!emailAdd.contains(emails.get(i))) {
				emailAdd.add(emails.get(i));
			}
		}
		String temp[] = new String[emailAdd.size()];
		Iterator itr = emailAdd.iterator();
		int i = 0;
		while (itr.hasNext()) {
			temp[i] = (String) itr.next();
			i++;
		}
		return temp;
	}
	
	/**@author	环宏亮
	 * @param str	要转换的字符串数组
	 * @return		将字符串数组转换为List
	 */
	public static List<String> convertStringToList(String[] str) {
		List<String> list = new ArrayList<String>();
		for (int i=0;i<str.length;i++) {
			list.add(str[i]);
		}
		return list;
	}

	/**@author 环宏亮
	 * @param superiors	List数组
	 * @return		返回不重复的数组
	 */
	@SuppressWarnings({ "unchecked"})
	public static List<Integer> getSuperiorList(List superiors) {
		List superiorList = new ArrayList();
		for (int i = 0; i < superiors.size(); i++) {
			if (!superiorList.contains(superiors.get(i))) {
				superiorList.add(superiors.get(i));
			}
		}
		return superiorList;
	}
	

	/**#@author 环宏亮
	 * @param string	字符串
	 * @return		检查是否有空串、NULL值
	 */
	public static boolean checkNullorEmpty(String string) {
		boolean isNullorEmpty = false;
		if (null == string || "".equals(string)) {
			isNullorEmpty = true;
		}
		return isNullorEmpty;
	}

	/**@author 环宏亮
	 * @param object	Object对象
	 * @return		是否有空串、NULL值
	 */
	@SuppressWarnings({"unchecked" })
	public static boolean isEmpty(Object object) {
		if (object == null || "".equals(object.toString())) {
			return true;
		}
		if (object instanceof List && ((List) object).size() < 1) {
			return true;
		}
		return false;
	}

	
	/**  
     * @author 环宏亮
     * @param n 月份偏移量  
     * @return  获取下n个月后的日期  
     */  
    public static Date getDateMonthInterval(int n) {   
        Calendar now = Calendar.getInstance();   
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + n);// 设置时间向前进n个月   
        now.set(Calendar.HOUR_OF_DAY, 0);   
        now.set(Calendar.MINUTE, 0);   
        now.set(Calendar.SECOND, 0);   
        return now.getTime();   
    }
    
    /**  
     * @author 环宏亮
     * @param date  日期
     * @return  获取某个时间所在的月份  
     */  
    public static int getMonth(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        return c.get(Calendar.MONTH) + 1;   
    }
    
    /**  
     * @author 环宏亮
     * @param nextStep 月份偏移量  
     * @return  系统当前时间过N个月后的时间  
     */  
    public static Date getNextMonth(int nextStep){   
        Calendar c = Calendar.getInstance();   
        int m = c.get(Calendar.MONTH);   
        c.set(Calendar.MONTH, m + nextStep);   
        return c.getTime();   
    }
    

	/**@author 环宏亮
	 * @param obj	Object对象
	 * @param defaultStr	默认的字符串
	 * @return		是否有空串、NULL值
	 */
	public static String isEmpty(Object obj, String defaultStr) {
		if (obj == null || obj.toString().equals(""))
			return defaultStr;
		return obj.toString();
	}

	/**@author 环宏亮
	 * @param fileName		文件名称
	 * @return		是否为excel格式
	 */
	public static boolean isExcelFile(String fileName) {
		return (fileName.lastIndexOf(".xlsx") != -1)
				|| (fileName.lastIndexOf(".xls") != -1);
	}

	/**@author 环宏亮
	 * @param eventId	事件ID
	 * @return	是否为正确的事件ID
	 */
	public static boolean isCorrectEventId(String eventId) {
		if (isEmpty(eventId)) {
			return false;
		}
		return isCorrectNumber(eventId);
	}

	/**@author 环宏亮
	 * @param dateStr	日期字符串
	 * @param partten	日期格式
	 * @param acceptNull	是否接受NULL值
	 * @return	是否为正确的日期格式
	 */
	public static boolean isCorrectDate(String dateStr, String partten,
			boolean acceptNull) {
		if (isEmpty(dateStr) && acceptNull) {
			return true;
		}
		return Pattern.compile(partten).matcher(dateStr).matches();
	}

	/**@author 环宏亮
	 * @param dateStr	日期字符串
	 * @param acceptNull	是否为正确的日期格式
	 * @return 	是否为正确的日期格式
	 */
	public static boolean isCorrectDate(String dateStr, boolean acceptNull) {
		return isCorrectDate(dateStr, "\\d{1,2}-\\w{3}-\\d{4}", acceptNull);
	}

	/**@author 环宏亮
	 * @param numberStr		数值格式的字符串
	 * @return		是否为正确的数值表示格式
	 */
	public static boolean isCorrectNumber(String numberStr) {
		return Pattern.compile("\\d+").matcher(numberStr).matches();
	}

	/**@author 环宏亮
	 * @param floatStr		浮点型格式的字符串
	 * @return		是否为正确的数值表示格式
	 */
	public static boolean isCorrectFloat(String floatStr) {
		return Pattern.compile("^(-?\\d+)(\\.\\d+)?$").matcher(floatStr)
				.matches();
	}


    /**@author 环宏亮
     * @param str	要进行编码的字符串
     * @return		编码后的字符串
     */
    public static String strEncoder(String str){
    	try {
			str = URLEncoder.encode(str, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return str;
    }
    

	
	
	/**
	 * 对字符串进行编码(utf-8)和去除空格
	 * @param dateFormat
	 * @return
	 */
	public static String strEncodeAndTrim(String str){
		try {
			if(null!=str){
				 str = URLDecoder.decode(str.trim(), "UTF-8").trim();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
    	
	}
	//计算出百分比
	public static String computePercent(long numberA, long numberB, String pattern) {
		String percent = "";
		double y1 = numberA*1.0;
		double z1 = numberB*1.0;
		double result = y1/z1;
		DecimalFormat format = new DecimalFormat(pattern);
		percent = format.format(result);
		return percent;
	}
	
	//保留小数点后面n位
	public static double computePercent(long writeSize, long fileSize, int n) {
		double vals = 0.0;
		if (fileSize!=0) {
			if (writeSize<fileSize) {
				BigDecimal bg = new BigDecimal(1.0*writeSize/fileSize);
				
				//保留小数点后2位
				vals = bg.setScale(n, BigDecimal.ROUND_HALF_UP).doubleValue();
			} else {
				vals = 1.0;
			}
		} else {		//文件大小未读取到
			vals = 2.0;
		}
		return vals;
	}
	
	/**
     * 将字符串数据组合成字符串
     * @return String
     */
	public static String getStringForSZ(String[] ss){
		String s = "";
		for(int i = 0; i < ss.length; i++){
			s += ss[i] + ", ";
		}
		if(s.length() > 2){
			s = s.substring(0, s.length() - 2);
		}
		return s;
	}
	
	/**
     * 将字符串数据组合成字符串
     * @return String
     */
	public static String getStringForSZ(String[] ss,String fomart){
		String s = "";
		for(int i = 0; i < ss.length; i++){
			s += fomart+ss[i].trim()+fomart+ ",";
		}
		if(s.length() > 2){
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
	
	/**
	 * 判断socket通讯是否正常
	 * @param ip
	 * @param port
	 * @return
	 */
	public static boolean checkSocket(String ip,int port,int timeout){
		Socket socket = new Socket();
		DataInputStream dis = null;
		DataOutputStream dos = null;
		String cmd = "isnormal";//给服务器发送是否正常的握手信息
		try{
			socket.connect(new InetSocketAddress(ip, port),timeout);//设置连接超时时间为1秒
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(cmd);
		}catch (Exception e) {
			return false;
		}finally{
			try {
				if(null!=dis){
					dis.close();
				}
				if(null!=dos){
					dos.close();
				}
				if(null!=socket){
					socket.close();
				}
			} catch (IOException e) {
			}
		}
		return true;
	}
	public static String year2ChineseSmall(String year){
		String[] pattern={"零","一","二","三","四","五","六","七","八","九"};
		String returnStr="";
		char c = 0;
		for(int i=0;i<year.length();i++){
			try{
				c=year.charAt(i);
				returnStr+=pattern[Integer.parseInt(c+"")]+"";
			}catch (NumberFormatException e) {
				returnStr+=c+"";
			}
		}
		return returnStr;
	}
	public static String month2ChineseSmall(int month){
		String[] pattern={"零","一","二","三","四","五","六","七","八","九","十","十一","十二"};
		if(month<1||month>12){
			System.out.println("error");
			return "";
		}else{
			return pattern[month];
		}
		
	}
	public static void main(String[] args) {
		System.out.println(Tools.month2ChineseSmall(12));
	}
}
