/**
 * StringUtil.java
 */
package org.com.iot.common;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类，扩展了apache的StringUtils
 *
 * @author Mickle
 */
public final class StringUtil{

	/**
	 * 判断String是否为null&&""
	 * return 为空：true,不为空：false
	 */
	public static boolean isEmpty(Object str){
		if(str != null && "".equals(str.toString())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断String是否为null&&""
	 * return 为空：false,不为空：true
	 */
	public static boolean isNotEmpty(Object str){
		if(str != null && !"".equals(str.toString())){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断String是否为null
	 * return 为空：true,不为空：false
	 */
	public static boolean isBlank(Object str){
		if(str == null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断String是否为null
	 * return 为空：false,不为空：true
	 */
	public static boolean isNotBlank(Object str){
		if(str == null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 检查字符串中是否包含中文
	 *
	 * @param string
	 * @return
	 */
	public static final boolean checkContainChinese(String string) {
		int n = string.length();
		for (int i = 0; i < n; i++) {
			if (string.charAt(i) > 127) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查字符串是否全是字母
	 *
	 * @param string
	 * @return
	 */
	public static final boolean checkIsAllEnglish(String string) {

		return string.matches("[a-zA-Z]+");
	}

	/**
	 * 通过URL传递的中文，会因为默认的编码格式，导致request取得的值为乱码。 此方法就是返回UTF8编码的中文。
	 *
	 * @param string 西方编码的乱码字符串
	 * @return UTF8编码的中文
	 * @throws UnsupportedEncodingException
	 */
	public static final String toChinese(String string) throws UnsupportedEncodingException {
		return new String(string.getBytes("ISO8859_1"), "UTF-8");
	}

	/**
	 * 过滤HTML符号，安全输出内容到HTML文档中显示
	 * @param code
	 * @return
	 */
	public static final String escapeHtml(String code) {
		if(isBlank(code)){
			return code;
		}
		code = code.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		code = code.replaceAll("'", "").replaceAll("\"", "");
		return code;
	}
	
	/**
	 * 查找出字符串中数字和字母(去掉V之前数据)
	 * @param str
	 * @return
	 */
	public static final String getNumberOrEn(String str){
		String v_return = "";
		if(isNotEmpty(str)){
			if(str.indexOf("V") > -1){
				str = str.substring(str.indexOf("V")+1, str.length());
			}
			int idx = 1;
			String v_temp = "";
			while (idx <= str.length()){
		        v_temp = str.substring(idx-1,idx);
		        if ((v_temp.charAt(0)>= 48 && v_temp.charAt(0) <= 57) ||
		            (v_temp.charAt(0)>= 65 && v_temp.charAt(0) <= 90) ||
		             v_temp.charAt(0)== 35){
		        	   v_return = v_return+v_temp;
		        }
		       idx++;
			}
			if(isEmpty(v_return)){
				v_return = str;
			}
		}
		return v_return;
	}
	
	public static void main(String[] args){
		System.out.println(getNumberOrEn("张三李四王五"));
	}
}
