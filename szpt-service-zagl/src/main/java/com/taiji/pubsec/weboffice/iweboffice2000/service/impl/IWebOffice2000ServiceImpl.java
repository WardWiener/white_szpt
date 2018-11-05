package com.taiji.pubsec.weboffice.iweboffice2000.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javassist.expr.Instanceof;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.weboffice.iweboffice2000.service.IWebOffice2000Service;

//@Service("iWebOffice2000Service")
//public class IWebOffice2000ServiceImpl implements IWebOffice2000Service{
//
//	public String createBlank(File templateDoc){
//		
//		String docType = templateDoc.getName().substring(templateDoc.getName().lastIndexOf("."), templateDoc.getName().length()) ;
//
//		String copyFileName = UUID.randomUUID().toString() + docType ;
//		
//	//	File fold = new File(Constant.getTempFold()) ;
//		
////		if((!fold.exists()) || (fold.exists() && (!fold.isDirectory()))){
//			fold.mkdirs() ;
//		}
//		
//		File out = new File(Constant.getTempFold() + copyFileName) ;
//		
//		FileFmtUtils.copyFile(templateDoc, out);
//		
//		return copyFileName ;
//	}
//	
//	public String copyDoc(InputStream fromDoc, String docType){
//
//		String copyFileName = UUID.randomUUID().toString() + docType ;
//		
//		File fold = new File(Constant.getTempFold()) ;
//		
//		if((!fold.exists()) || (fold.exists() && (!fold.isDirectory()))){
//			fold.mkdirs() ;
//		}
//		
//		File out = new File(Constant.getTempFold() + copyFileName) ;
//		
//		FileFmtUtils.writeFile(fromDoc, out);
//		
//		return copyFileName ;
//	}
//}
