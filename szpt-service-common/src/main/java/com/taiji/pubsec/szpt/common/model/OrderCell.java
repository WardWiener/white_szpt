package com.taiji.pubsec.szpt.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.taiji.pubsec.szpt.util.ConstantEnum;
import com.taiji.pubsec.szpt.util.ConstantEnumUtil;

/**
 * 		指挥单元
 * @author white
 *
 */
@Entity
@Table(name="t_ty_jwzh_zhdy")
public class OrderCell {
	
	public enum ORDERCELL_TYPE implements ConstantEnum{
		
		TYPE_ZHUGE("主格"),
		TYPE_FUGE("辅格"),
		TYPE_DW("单位");
		
		@Override
		public String getKey() {
			return this.name();
		}
		
		private String value ;
		
		private ORDERCELL_TYPE(String value){
			this.value = value ;
		}
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public static String toJSONString(){
			return ConstantEnumUtil.toJSONStringByValue(ORDERCELL_TYPE.values()) ;
		}
	}

	@Id
	private String id ;
	
	/**
	 * 类型
	 */
	@Column(name="lx")
	private String type ;
	
	/**
	 * 编码
	 * 索引
	 */
	@Column(name="bm")
	@Index(name="index_t_ty_jwzh_zhdy_bm")
	private String code ;
	
	/**
	 * 名称
	 * 索引
	 */
	@Column(name="mc")
	private String name ;
	
	/**
	 * 父id
	 */
	@Column(name="pId")
	@Index(name="index_t_ty_jwzh_zhdy_pId")
	private String parentId ;
	
	/**
	 * 层级，根为0
	 */
	@Column(name="lv")
	private Integer level ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
