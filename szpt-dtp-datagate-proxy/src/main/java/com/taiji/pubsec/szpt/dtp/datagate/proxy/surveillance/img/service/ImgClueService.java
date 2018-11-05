package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.service;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgClueDeployMsg;

public interface ImgClueService {
	
	public void deploySurveilList(ImgClueDeployMsg imgClueDeployMsg) ;
	
	public void cancelSurveilList(String surveilListCode) ;
}
