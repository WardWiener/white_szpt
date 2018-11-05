package com.taiji.pubsec.szpt.operationrecord.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 警综日志记录
 * 
 * @author dixf
 *
 */
@Entity
@Table(name = "t_jzrzjl")
public class StandardLogRecord {
	
	public static final int OPERATETYPE_LOGIN = 0;
	public static final int OPERATETYPE_QUERY = 1;
	public static final int OPERATETYPE_ADD = 2;
	public static final int OPERATETYPE_UPDATE = 3;
	public static final int OPERATETYPE_DELETE = 4;
	
	public static final String OPERATERESULT_SUCCESS = "1";
	public static final String OPERATERESULT_FAIL = "0";


	@Id
	@Column(name = "Num_ID")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "native")
	private int id;

	@Column(name = "User_ID", length = 18)
	private String userId; // /用户标识

	@Column(name = "Organization", length = 100)
	private String unitName; // 单位名称

	@Column(name = "Organization_ID", length = 12)
	private String unitCode; // 单位编码

	@Column(name = "User_Name", length = 30)
	private String operator; // 操作人姓名

	@Column(name = "Operate_Time", length = 30)
	private String operateTime; // 操作时间

	@Column(name = "Terminal_ID", length = 40)
	private String terminalId; // 终端标识

	@Column(name = "Operate_Type")
	private int operationType; // 操作类型

	@Column(name = "Operate_Result", length = 1)
	private String result; // 操作结果

	@Column(name = "Error_Code", length = 4)
	private String errorCode; // 错误代码

	@Column(name = "Operate_Name", length = 30)
	private String functionModule; // 功能模块名称

	@Column(name = "Operate_Condition", length = 4000)
	private String condition; // 查询条件

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getFunctionModule() {
		return functionModule;
	}

	public void setFunctionModule(String functionModule) {
		this.functionModule = functionModule;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
