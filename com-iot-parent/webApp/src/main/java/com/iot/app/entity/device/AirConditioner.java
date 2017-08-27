package com.iot.app.entity.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.iot.app.entity.DeviceDto;

/**
 * 
 * @author Mickle
 *
 */
@Entity
@Table(name="AIR_CONDITIONER")
@Inheritance(strategy = InheritanceType.JOINED)
public class AirConditioner extends DeviceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="KEY_1")
    private String key_1;
	
	@Column(name="KEY_2")
    private String key_2;
	
	@Column(name="KEY_3")
	private String key_3;
	
	@Column(name="KEY_4")
	private String key_4;
	
	@Column(name="KEY_5")
	private String key_5;
	
	@Column(name="KEY_6")
	private String key_6;
	
	@Column(name="KEY_7")
	private String key_7;
	
	@Column(name="KEY_8")
	private String key_8;
	
	@Column(name="KEY_9")
	private String key_9;
	
	@Column(name="KEY_10")
	private String key_10;
    
    public AirConditioner() {
		// TODO Auto-generated constructor stub
	}

	public String getKey_3() {
		return key_3;
	}



	public void setKey_3(String key_3) {
		this.key_3 = key_3;
	}



	public String getKey_4() {
		return key_4;
	}



	public void setKey_4(String key_4) {
		this.key_4 = key_4;
	}



	public String getKey_5() {
		return key_5;
	}



	public void setKey_5(String key_5) {
		this.key_5 = key_5;
	}



	public String getKey_6() {
		return key_6;
	}



	public void setKey_6(String key_6) {
		this.key_6 = key_6;
	}



	public String getKey_7() {
		return key_7;
	}



	public void setKey_7(String key_7) {
		this.key_7 = key_7;
	}



	public String getKey_8() {
		return key_8;
	}



	public void setKey_8(String key_8) {
		this.key_8 = key_8;
	}



	public String getKey_9() {
		return key_9;
	}



	public void setKey_9(String key_9) {
		this.key_9 = key_9;
	}



	public String getKey_10() {
		return key_10;
	}



	public void setKey_10(String key_10) {
		this.key_10 = key_10;
	}



	public String getKey_1() {
		return key_1;
	}

	public void setKey_1(String key_1) {
		this.key_1 = key_1;
	}

	public String getKey_2() {
		return key_2;
	}

	public void setKey_2(String key_2) {
		this.key_2 = key_2;
	}
    
}
