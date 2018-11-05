package com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service.ClueSubjectDeployService;
import com.taiji.pubsec.szpt.dpp.surveillance.util.service.ImgClueUtilService;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgClueDeployMsg;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListImgInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;

@Service("imgClueSubjectDeployService")
public class ImgClueSubjectDeployServiceImpl implements ClueSubjectDeployService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgClueSubjectDeployServiceImpl.class);
	
	@Resource
	protected KafkaProducer kafkaProducer ;
	
	@Resource
	protected ImgClueUtilService imgClueUtilService ;

	@Override
	public boolean deploy(SurveilListInfo surveilListInfo, String operate) {	
		LOGGER.debug("部署图片线索开始");
		deploySurveilInfo(operate,surveilListInfo) ;
		LOGGER.debug("部署图片线索结束");
		return true;
	}
	
	protected void deploySurveilInfo(String operate, SurveilListInfo surveilListInfo){
		
		ImgClueDeployMsg ism = new ImgClueDeployMsg() ;
		ism.setOperate(operate);
		ism.setSurveilListInfo(surveilListInfo);

		if(operate.equals(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_CANCEL)){
			kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_IMG_CLUE_DEPLOY_TO_DATAGATE(), SerializeUtils.serialize(ism));
			return ;
		}
		
		List<SurveilListImgInfo> imgInfos = loadImgs(surveilListInfo);
		
		ism.setSurveilListImgInfos(imgInfos);
		
		if(imgInfos.size()>0){
			LOGGER.debug("图片线索发送到内外网交换端");
			kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_IMG_CLUE_DEPLOY_TO_DATAGATE(), SerializeUtils.serialize(ism));
		}else{
			LOGGER.debug("没有图片线索，不发送");
		}

	}
	
	protected List<SurveilListImgInfo> loadImgs(SurveilListInfo surveilListInfo){
		List<SurveilListImgInfo> imgs = new ArrayList<SurveilListImgInfo>() ;
		for(SurveilListImgInfo info:surveilListInfo.getSurveilListImgInfos()){
			SurveilListImgInfo img = loadImg(info, surveilListInfo) ;
			if(img!=null){
				imgs.add(img) ;
			}
		}
		return imgs ;
	}
	
	protected SurveilListImgInfo loadImg(SurveilListImgInfo info, SurveilListInfo surveilListInfo){
		InputStream imgIn = null ;
		
		SurveilListImgInfo img = null;
		try{
			imgIn = imgClueUtilService.getSurveilListImgAsStream(info.getImgClueId()) ;
			img = new SurveilListImgInfo(info.getImgClueId(), FileFmtUtils.inputStreamToBase64String(imgIn, false)) ;
		}catch (Exception e) {
			new IllegalArgumentException(e);
		}finally{
			try{
				if(imgIn!=null){
					imgIn.close();	
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return img ;
	}
}
