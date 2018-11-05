package com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.complex.tools.attachment.AttachmentMeta;
import com.taiji.pubsec.complex.tools.attachment.DefaultAttachmentMetaImpl;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListImgInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListMobileInfo;

@Service
class SurveilListDAService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveilListDAService.class) ;

	@Autowired
	private SqlDao sqlDao ;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;
	
	@SuppressWarnings("unchecked")
	public List<SurveilListInfo> findAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate(){
		String sql = "SELECT bk.id AS id,"
				+ " bk.bh AS num,"
				+ " bk.sfzh AS idCardNo,"
				+ " bk.xm AS personName,"
				+ " bk.xb AS sex,"
				+ " bk.hjdz AS residenceAddress,"
				+ " bk.bkkssj AS startTime,"
				+ " bk.bkjssj AS endTime,"
				+ " bk.sjzt AS status,"
				+ " bk.bz AS note,"
				+ " bk.zxxgrxm AS lastModifyPerson,"
				+ " bk.zxxgsj AS lastModifyTime,"
				+ " bk.czzt AS operateStatus"
				+ " FROM t_gwry_rybk as bk"
				+ " WHERE "
				+ " bk.sjzt=:status"
				+ " AND bk.czzt=:operateStatus"
				+ " AND bk.bkkssj<=:now"
				+ " AND bk.bkjssj>=:now" ;
		Map<String, Object> sqlMap = new HashMap<String, Object>() ;
		sqlMap.put("status", SurveillanceUtilConstant.DICT_ON);
		sqlMap.put("operateStatus", SurveillanceUtilConstant.DICT_OPERATE_STATUS_PASS);
		sqlMap.put("now", new Date());

		LOGGER.debug("更新布控单sql：{}, 参数：{}", sql, sqlMap);

		List<Object[]> list = this.sqlDao.find(sql, sqlMap) ;
		
		List<SurveilListInfo> results = new ArrayList<SurveilListInfo>() ;
		
		for(Object[] arr:list){
			SurveilListInfo info = new SurveilListInfo() ;
			info.setId(arr[0]!=null?arr[0].toString():null);
			info.setNum(arr[1]!=null?arr[1].toString():null);
			info.setIdCardNo(arr[2]!=null?arr[2].toString():null);
			info.setPersonName(arr[3]!=null?arr[3].toString():null);
			info.setSex(arr[4]!=null?arr[4].toString():null);
			info.setResidenceAddress(arr[5]!=null?arr[5].toString():null);
			info.setStartTime(arr[6]!=null?((Date)(arr[6])).getTime():null);
			info.setEndTime(arr[7]!=null?((Date)(arr[7])).getTime():null);
			info.setStatus(arr[8]!=null?arr[8].toString():null);
			info.setNote(arr[9]!=null?arr[9].toString():null);
			info.setLastModifyPerson(arr[10]!=null?arr[10].toString():null);
			info.setLastModifyTime(arr[11]!=null?((Date)(arr[11])).getTime():null);
			info.setOperateStatus(arr[12]!=null?arr[12].toString():null);
			info.setSurveilListMobileInfos(findAllSurveilListMobileInfos(info.getId()));
			info.setSurveilListImgInfos(findAllSurveilListImgInfos(info.getId()));
			results.add(info);

			LOGGER.debug("查询出布控单：编号:{}, 审批状态:{}，状态:{}", info.getNum(), info.getOperateStatus(), info.getStatus());
		}
		
		return results ;
	}
	
	@SuppressWarnings("unchecked")
	private List<SurveilListMobileInfo> findAllSurveilListMobileInfos(String surveilListId){
		String sql = "SELECT m.id as id, "
				+ " m.mac as mac,"
				+ " m.sjhm as phone"
				+ " FROM t_gwry_rybkyddxx AS m"
				+ " WHERE"
				+ " m.rybk_id=:surveilListId" ;
		Map<String, Object> sqlMap = new HashMap<String, Object>() ;
		sqlMap.put("surveilListId", surveilListId);
		
		List<Object[]> list = this.sqlDao.find(sql, sqlMap) ;
		
		List<SurveilListMobileInfo> results = new ArrayList<SurveilListMobileInfo>() ;
		
		for(Object[] arr:list){
			results.add(new SurveilListMobileInfo(arr[0].toString(), arr[1].toString(), arr[2].toString()));
		}
		
		return results ;
	}
	
	@SuppressWarnings("unchecked")
	private List<SurveilListImgInfo> findAllSurveilListImgInfos(String surveilListId){
		
		String sql = "SELECT p.id as id,"
				+ " p.fjy_id"
				+ " FROM t_gwry_rybk_bkzp AS p"
				+ " WHERE"
				+ " p.rybk_id=:surveilListId" ;
		Map<String, Object> sqlMap = new HashMap<String, Object>() ;
		sqlMap.put("surveilListId", surveilListId);
		
		List<Object[]> list = (List<Object[]>)this.sqlDao.find(sql, sqlMap) ;
		
		List<SurveilListImgInfo> results = new ArrayList<SurveilListImgInfo>() ;
		
		for(Object[] obj:list){
			String clueId = (String)obj[0] ;
/*			String fjy_id = (String)obj[1] ;
			AttachmentMeta meta = findAttachmentMeta(fjy_id) ;
			
			InputStream in = meta.getAttachmentCopys().get(0).getInputStream() ;*/
			/**
			 * 图片不缓存到内存中
			 */
			results.add(new SurveilListImgInfo(clueId, null)) ;
/*			try{
				results.add(new SurveilListImgInfo(clueId, FileFmtUtils.inputStreamToBase64String(in, false))) ;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{	
					if(in!=null){
						in.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}*/
		}
		
		return results ;
	}
	
	@SuppressWarnings("unchecked")
	private AttachmentMeta findAttachmentMeta(String metaId){
		return (AttachmentMeta)this.dao.findById(DefaultAttachmentMetaImpl.class, metaId) ;
	}
}
