package com.taiji.pubsec.weboffice.iweboffice2000.service;

import java.io.File;
import java.io.InputStream;

public interface IWebOffice2000Service {
	
	public String createBlank(File templateDoc) ;
	
	public String copyDoc(InputStream fromDoc, String docType) ;

}
