package com.taiji.pubsec.szpt.surveillance.util.message.clue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListImgInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;

public class ImgClueDeployMsg implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8508147449025927164L;
	
	private String operate ;
	
	private SurveilListInfo surveilListInfo ;
	private List<SurveilListImgInfo> surveilListImgInfos = new ArrayList<SurveilListImgInfo>() ;
	
	public SurveilListInfo getSurveilListInfo() {
		return surveilListInfo;
	}
	public void setSurveilListInfo(SurveilListInfo surveilListInfo) {
		this.surveilListInfo = surveilListInfo;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public List<SurveilListImgInfo> getSurveilListImgInfos() {
		return surveilListImgInfos;
	}
	public void setSurveilListImgInfos(List<SurveilListImgInfo> surveilListImgInfos) {
		this.surveilListImgInfos = surveilListImgInfos;
	}

}
