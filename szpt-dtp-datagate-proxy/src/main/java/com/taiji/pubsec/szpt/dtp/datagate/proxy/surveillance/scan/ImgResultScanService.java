package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.scan;

import java.util.List;

import com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model.ImgClueResult;

public interface ImgResultScanService {

	public void scan() ;
	
	public void sendResults(List<ImgClueResult> list) ;
	
	public void saveUnreadToRead(List<ImgClueResult> list) ;
}
