package com.taiji.pubsec.szpt.zhzats.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.zhzats.bean.yanpanListBean;
import com.taiji.pubsec.szpt.zhzats.model.CjFeedback;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;
import com.taiji.pubsec.szpt.zhzats.model.JqVideo;

/**
 * 综合治安查询数据结果集转换model/bean工具类.
 * 
 * 
 *
 */
@Component
public class ZhzatsDbResultSetUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZhzatsDbResultSetUtil.class);
	
	static public FactJq transferFactJqFromResultSet(Object[] rs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		FactJq alarm = new FactJq();
		alarm.setId(rs[0].toString());

		if(rs[1] != null) {
			alarm.setAddr(rs[1].toString());
		}
		try {
			if(rs[2] != null) {
				alarm.setAnswerAlarmDate(sdf.parse(rs[2].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("警情信息t_zhzats_jwzh_jq接警时间字段jjsj转换错误", e);
		}
		try {
			if(rs[3] != null) {
				alarm.setCallingDate(sdf.parse(rs[3].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("警情信息t_zhzats_jwzh_jq报警时间字段bjsj转换错误", e);
		}
		if(rs[4] != null) {
			alarm.setCallingPerson(rs[4].toString());
		}
		if(rs[5] != null) {
			alarm.setCallingPersonPhone(rs[5].toString());
		}
		if(rs[6] != null) {
			alarm.setCode(rs[6].toString());
		}
		if(rs[7] != null) {
			alarm.setCountryCode(rs[7].toString());
		}
		if(rs[8] != null) {
			alarm.setCountryName(rs[8].toString());
		}
		if(rs[9] != null) {
			alarm.setJqSource(rs[9].toString());
		}
		if(rs[10] != null) {
			alarm.setJqSummary(rs[10].toString());
		}
		if(rs[11] != null) {
			alarm.setJqlxCode(rs[11].toString());
		}
		if(rs[12] != null) {
			alarm.setJqlxName(rs[12].toString());
		}
		if(rs[13] != null) {
			alarm.setLatitude(Double.valueOf(rs[13].toString()));
		}
		if(rs[14] != null) {
			alarm.setLongitude(Double.valueOf(rs[14].toString()));
		}
		if(rs[15] != null) {
		    alarm.setName(rs[15].toString());
		}
		try {
			if(rs[16] != null) {
				alarm.setOccurrenceTime(sdf.parse(rs[16].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("警情信息t_zhzats_jwzh_jq发生时间字段fssj转换错误", e);
		}
		if(rs[17] != null) {
	        alarm.setPcsCode(rs[17].toString());
		}
		if(rs[18] != null) {
			alarm.setPcsName(rs[18].toString());
		}
		if(rs[19] != null) {
			alarm.setUrgencyLevel(rs[19].toString());
		}
		
		if(rs[20] != null) {
			alarm.setJjr(rs[20].toString());
		}
		return alarm;
	}
	
	static public List<FactJq> transferFactJqListFromResultSet(List<Object[]> objs) {
		List<FactJq> alarms = new ArrayList<FactJq>();
		
		for(Object[] alarmObj : objs) {
			alarms.add(transferFactJqFromResultSet(alarmObj));
		}
		return alarms;
	}
	
	static public Pager<yanpanListBean> transferFactJqPagerFromResultSet(Pager<Object[]> objs) {
		Pager<yanpanListBean> pager = new Pager<yanpanListBean>();
		List<FactJq> alarms = new ArrayList<FactJq>();
		for(Object[] alarmObj : objs.getPageList()) {
			alarms.add(transferFactJqFromResultSet(alarmObj));
		}
		List<yanpanListBean> pagerBean = new ArrayList<yanpanListBean>();
		
		for (FactJq factJq : alarms) {
			yanpanListBean bean = new yanpanListBean() ;
			BeanUtils.copyProperties(factJq, bean);
			pagerBean.add(bean);
		}
		pager.setPageList(pagerBean);
		pager.setTotalNum(objs.getTotalNum());
		return pager;
	}
	
	static public CjFeedback transferCjFeedbackFromResultSet(Object[] rs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CjFeedback fk = new CjFeedback();
		fk.setId(rs[0].toString());

		if(rs[1] != null) {
			fk.setContent(rs[1].toString());
		}
		if(rs[2] != null) {
			fk.setFactCjId(rs[2].toString());
		}
		try {
			if(rs[3] != null) {
				fk.setFeedbackTime(sdf.parse(rs[3].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("警情反馈信息t_zhzats_jwzh_fk反馈时间字段fksj转换错误", e);
		}
		if(rs[4] != null) {
			fk.setFkOrderCellCode(rs[4].toString());
		}
		
		if(rs[5] != null) {
			fk.setFkOrderCellId(rs[5].toString());
		}
		if(rs[6] != null) {
			fk.setFkOrderCellName(rs[6].toString());
		}
		if(rs[7] != null) {
			fk.setFkPerson(rs[7].toString());
		}
		
		if(rs[8] != null) {
			fk.setFeedbackType(rs[8].toString());
		}
		return fk;
	}
	
	static public List<CjFeedback> transferCjFeedbackListFromResultSet(List<Object[]> objs) {
		List<CjFeedback> feedbacks = new ArrayList<CjFeedback>();
		
		for(Object[] fkObj : objs) {
			feedbacks.add(transferCjFeedbackFromResultSet(fkObj));
		}
		return feedbacks;
	}
	
	static public JqVideo transferJqVideoFromResultSet(Object[] rs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JqVideo video = new JqVideo();
		video.setId(rs[0].toString());

		if(rs[1] != null) {
			video.setJqCode(rs[1].toString());
		}
		if(rs[2] != null) {
			video.setFileId(rs[2].toString());
		}
		if(rs[3] != null) {
			video.setFileName(rs[3].toString());
		}
		if(rs[4] != null) {
			video.setFileType(rs[4].toString());
		}
		if(rs[5] != null) {
			video.setPlayAdd(rs[5].toString());
		}
		if(rs[6] != null) {
			video.setShowAdd(rs[6].toString());
		}
		if(rs[7] != null) {
			video.setProducerNum(rs[7].toString());
		}
		if(rs[8] != null) {
			video.setProducerName(rs[8].toString());
		}
		if(rs[9] != null) {
			video.setProducerDwCode(rs[9].toString());
		}
		try {
			if(rs[10] != null) {
				video.setStartDate(sdf.parse(rs[10].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("警情视频信息t_zhzats_jwzh_jqsp开始时间字段kssj转换错误", e);
		}
		try {
			if(rs[11] != null) {
				video.setEndDate(sdf.parse(rs[11].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("警情视频信息t_zhzats_jwzh_jqsp结束时间字段jssj转换错误", e);
		}
		
		
		if(rs[12] != null) {
			video.setVideoLength(rs[12].toString());
		}
		if(rs[13] != null) {
			video.setSaveVideoLength(rs[13].toString());
		}
		if(rs[14] != null) {
			video.setFileSize(Integer.valueOf(rs[14].toString()));
		}
		if(rs[15] != null) {
			video.setStatus(rs[15].toString());
		}
		if(rs[16] != null) {
			video.setSource(rs[16].toString());
		}
		try {
			if(rs[17] != null) {
				video.setUpdateDate(sdf.parse(rs[17].toString()));
			}
		}catch(ParseException e) {
			LOGGER.error("警情视频信息t_zhzats_jwzh_jqsp更新时间字段gxsj转换错误", e);
		}

		
		return video;
	}
	
	static public List<JqVideo> transferJqVideoListFromResultSet(List<Object[]> objs) {
		List<JqVideo> videos = new ArrayList<JqVideo>();
		
		for(Object[] alarmObj : objs) {
			videos.add(transferJqVideoFromResultSet(alarmObj));
		}
		return videos;
	}
}
