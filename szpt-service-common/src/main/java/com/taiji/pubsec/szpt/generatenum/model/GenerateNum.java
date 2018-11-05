package com.taiji.pubsec.szpt.generatenum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 生成编号
 * @author genganpeng
 *
 */
@Entity
@Table(name = "t_bh")
public class GenerateNum {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	@Column(name="type", length=36)
	private String type;
	
	@Column(name="name", length=36)
	private String name;
	
	@Column(name="formatStr", length=800)
	private String formatStr;
	
	@Column(name="isYear")
	private int isYear;
	
	@Column(name="currentYear", length=50)
	private String currentYear;
	
	@Column(name="num")
	private int num;
	
	@Column(name="prefixStr", length=800)
	private String prefixStr;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 分类
	 * @return
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 格式化字符串
	 * @return
	 */
	public String getFormatStr() {
		return formatStr;
	}

	public void setFormatStr(String formatStr) {
		this.formatStr = formatStr;
	}

	/**
	 * 是否按照年份：0表示按年，1表示不按年
	 * @return
	 */
	public int getIsYear() {
		return isYear;
	}

	public void setIsYear(int isYear) {
		this.isYear = isYear;
	}

	/**
	 * 当前年份
	 * @return
	 */
	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	/**
	 * 编号
	 * @return
	 */
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 前缀
	 * @return
	 */
	public String getPrefixStr() {
		return prefixStr;
	}

	public void setPrefixStr(String prefixStr) {
		this.prefixStr = prefixStr;
	}
	
}
