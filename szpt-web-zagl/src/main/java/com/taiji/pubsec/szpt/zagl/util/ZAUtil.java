package com.taiji.pubsec.szpt.zagl.util;

import java.util.List;



public class ZAUtil {
	
	public static String changeListToArray(List<String> codeArr){
		String s="code in(";
		for(int i=0;i<codeArr.size();i++){
			
			if(i==0){
				s+=codeArr.get(i);
			}else{
				s+=","+codeArr.get(i);
			}
		}
		s+=")";
		return s;
	} 
	public static String changeListToArray(String[] codeArr){
		String s="code in(";
		for(int i=0;i<codeArr.length;i++){
			if(i==0){
				s+=codeArr[i];
			}else{
				s+=","+codeArr[i];
			}
		}
		s+=")";
		return s;
	}

}
