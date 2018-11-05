package com.taiji.pubsec.szpt.zagl.service;

import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMaterial;


/**
 * 专案资料服务接口，增删改查功能
 * @author dixiaofeng
 * @version 1.0
 * @created 21-11月-2016 19:24:09
 */
public interface SpecialCaseMaterialService {

	/**
	 * 添加资料信息
	 * @param material 专案资料实体信息
	 * @return 成功返回true；失败返回false
	 */
	public boolean addMaterial(SpecialCaseMaterial material);

	/**
	 * 删除资料信息
	 * @param materialId 资料id
	 * @return 成功返回true；失败返回false
	 */
	public boolean deleteMaterial(String materialId);

	/**
	 * 分页查询专案所有资料，按创建时间倒序
	 * @param caseId    专案id
	 * @param paramsMap 其他查询条件，包括:
	 * <br>uploadPenson 上传人员
	 * <br>fileName 资料名称
	 * <br>startDate 上传日期开始
	 * <br>endDate 上传日期结束
	 * <br>zazllxCode 专案资料类型    
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回专案信息分页列表
	 */
	public Pager<SpecialCaseMaterial> findMaterialByCase(String caseId, Map<String,Object> paramsMap, int pageNo, int pageSize);

	/**
	 * 按id查询资料
	 * @param materialId 资料id
	 * @return 返回资料实体信息,查不到返回null
	 */
	public SpecialCaseMaterial findMaterialById(String materialId);

	/**
	 * 更新资料信息
	 * @param material 资料实体信息
	 * @return 成功返回true；失败返回false
	 */
	public boolean updateMaterial(SpecialCaseMaterial material);
	
	/**
	 * 通过专案资料名称、资料类型、专案id查询专案资料检查该专案资料是否已上传
	 * @param fileName 资料名称
	 * @param fileType 资料类型
	 * @param caseId 专案id
	 * @return 返回专案资料信息，若没找到，返回null
	 */
	public SpecialCaseMaterial findMaterialByConditions(String fileName, String fileType, String caseId);

}