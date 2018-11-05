package com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.taiji.pubsec.szpt.surveillance.result.SurveilListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.common.tools.base64.Base64CryptFmtUtil;
import com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.SurveilListService;
import com.taiji.pubsec.szpt.dpp.surveillance.util.service.ImgClueUtilService;
import com.taiji.pubsec.szpt.surveillance.util.AttachmentUtil;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ClueInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;
import com.taiji.pubsec.szpt.surveillance.result.model.DefaultSurveilListResult;
import com.taiji.pubsec.szpt.surveillance.result.service.SurveilListResultService;

@Service("imgClueSubjectProcessService")
public class ImgClueSubjectProcessServiceImpl extends AbstractClueSubjectProcessServiceImpl{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgClueSubjectProcessServiceImpl.class);
	
	@Autowired
	private SqlDao sqlDao ;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;
	
	@Resource
	private ImgClueUtilService imgClueUtilService;

	@Override
	public boolean support(ClueInfo clueInfo) {
		if(clueInfo instanceof ImgInfo){
			return true ;
		}
		return false;
	}

	@Override
	public List<SurveilListResult> process(ClueInfo clueInfo) {

		if(!support(clueInfo)){
			return null ;
		}
		
		LOGGER.debug("处理图片线索开始");
		List<SurveilListResult> list = saveResults(clueInfo);
		LOGGER.debug("处理图片线索结束");
		
		return list ;
	}
	
	private List<SurveilListResult> saveResults(ClueInfo clueInfo){
		List<SurveilListResult> results = new ArrayList<>() ;
		List<SurveilListInfo> list = getSubjectRequiredSurveilListInfos(clueInfo) ;
		for(SurveilListInfo info:list){
			results.add(saveResult(info, clueInfo)) ;
		}
		return results ;
	}
	
	private SurveilListResult saveResult(SurveilListInfo surveilListInfo, ClueInfo clueInfo){
		ImgInfo imgInfo = (ImgInfo)clueInfo ;
		if(surveilListInfo==null){
			return null ;
		}
		DefaultSurveilListResult result = new DefaultSurveilListResult() ;

		result.setClueType(DefaultSurveilListResult.CLUE_TYPE.IMG.getValue());
		result.setCatchContent(imgInfo.sketch());
		result.setResultStatus(SurveillanceUtilConstant.DICT_XTZT_INIT);
		result.setCatchDetail(imgInfo.detailDescription());
		result.setCatchLongitude(imgInfo.getLongitude());
		result.setCatchLatitude(imgInfo.getLatitude());
		
		result.setSurveilListNum(imgInfo.getSurveilListCode());

		Calendar cal = Calendar.getInstance() ;
		cal.setTimeInMillis(imgInfo.getCatchDate());
		
		result.setCatchTime(cal.getTime());

		InputStream catchImgResultIn = null ;

		try{
			/**
			 * surveilListImgInfo的图片没有缓存到内存中需要重新查
			 */
			catchImgResultIn = new ByteArrayInputStream(Base64CryptFmtUtil.decodeToByte(imgInfo.getImgBase64().getBytes())) ;
			result.setCatchImgResult(AttachmentUtil.getInstanceOfAttachmentMeta(catchImgResultIn));
			
		}catch (Exception e) {
			new IllegalArgumentException(e);
		}finally{
			try{
				if(catchImgResultIn!=null){
					catchImgResultIn.close();	
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return saveSurveilListResultInfo(result);
	}

	@Override
	public List<SurveilListInfo> getSubjectRequiredSurveilListInfos(ClueInfo clueInfo) {
		ImgInfo clue = (ImgInfo)clueInfo;
		List<SurveilListInfo> list = new ArrayList<SurveilListInfo>();
		SurveilListInfo info = surveilListService.getSurveilListInfoByNotOutOfDateByNum(clue.getSurveilListCode()) ;
		if(info!=null){
			list.add(info);
		}
		return list;
	}
	
	@Resource
	@Override
	public void setSurveilListService(SurveilListService surveilListService) {
		this.surveilListService = surveilListService;
	}
	
	@Resource
	@Override
	public void setSurveilListResultService(SurveilListResultService surveilListResultService){
		this.surveilListResultService = surveilListResultService ;
	}
}
