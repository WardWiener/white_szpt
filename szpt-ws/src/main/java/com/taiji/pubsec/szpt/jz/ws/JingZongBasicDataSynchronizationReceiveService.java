package com.taiji.pubsec.szpt.jz.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * 接收警综总线推送的组织机构、人员变更数据
 * @author Administrator
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface JingZongBasicDataSynchronizationReceiveService {
	
	/**
	 * 
	 * @param batchNo 批次号，ESB数据发送批次号
	 * @param identify 标识号，标识号用于区分不同的数据类型或数据体
	 * @param dataJson 数据包，数据更新业务数据包（Json格式字符串）
	 * @return Json字符串
	 *                [
	 *                "isSucceed":	必须  Boolean  是否成功标志。如果接收失败或者数据处理失败，则标记为false，否则为true。,
	 *                "batchNo":	非必须	String	批次号（直接传回总线传入的批次号）。,
	 *                "remark":		非必须	String	失败说明，如果isSucceed=false，则该字段用于说明失败说明。
	 *                ]
	 */
	@WebMethod
	public String receiveDataFromBusService(@WebParam(name = "batchNo")String batchNo, 
			@WebParam(name = "identify")String identify, @WebParam(name = "dataJson")String dataJson);

}
