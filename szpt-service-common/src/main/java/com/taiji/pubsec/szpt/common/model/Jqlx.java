package com.taiji.pubsec.szpt.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.taiji.pubsec.szpt.util.ConstantEnum;
import com.taiji.pubsec.szpt.util.ConstantEnumUtil;

/**
 * 警情类型
 * @author XIEHF
 *
 */
@Entity
@Table(name="t_ty_jwzh_jqlx")
public class Jqlx {	
	
	public enum LEVEL implements ConstantEnum{
		ONE(0),
		TWO(1),
		THREE(2),
		FOUR(3),
		FIVE(4);
		
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
	@Index(name="index_t_ty_jwzh_jqlx_bm")
	private String code ;
	
	/**
	 * 名称
	 */
	@Column(name="mc")
	private String name ;
	
	/**
	 * 层级
	 */
	@Column(name="lv")
	private Integer level ;
	
	/**
	 * 父id
	 */
	@Column(name="fid")
	@Index(name="index_t_ty_jwzh_jqlx_fid")
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
