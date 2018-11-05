package com.taiji.pubsec.szpt.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.taiji.pubsec.szpt.util.ConstantEnum;
import com.taiji.pubsec.szpt.util.ConstantEnumUtil;

/**
 * 单位
 * @author XIEHF
 *
 */
@Entity
@Table(name="t_ty_dw")
@Deprecated
public class SzptUnit {
	
	public enum UNIT_TYPE implements ConstantEnum{
		TYPE_FJ("分局"),
		TYPE_PCS("派出所"),
		TYPE_JZ("警种"),
		TYPE_ZHUGE("主格"),
		TYPE_FUGE("辅格");
		
		private String value ;
		
		private UNIT_TYPE(String value){
			this.value = value ;
		}
		@Override
		public String getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(UNIT_TYPE.values()) ;
		}
	}
	
	public enum LEVEL implements ConstantEnum{
		ONE(0),
		TWO(1),
		THREE(2);
		
		private Integer value ;
		
		private LEVEL(Integer value){
			this.value = value ;
		}
		@Override
		public Integer getValue() {
			return value;
		}
		@Override
		public String getKey() {	
			return this.name();
		}
		public void setValue(Integer value) {
			this.value = value;
		}
		
		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(LEVEL.values()) ;
		}

	}
	
	@Id
	private String id ;
	
	/**
	 * 编码
	 */
	@Column(name="bm")
	@Index(name="index_t_ty_dw_bm")
	private String code ;
	
	@Column(name="lv")
	private Integer level ;

	/**
	 * 名称
	 */
	@Column(name="mc")
	private String name ;
	
	/**
	 * 类型
	 */
	@Column(name="lx")
	private String type ;

	/**
	 * 父ID
	 */
	@Column(name="ty_dw_id")
	@Index(name="index_t_ty_dw_ty_dw_id")
	private String parentId ;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
