package com.taiji.pubsec.szpt.util;

public class ConstantEnumUtil {
	
	public static String toJSONStringByValue(ConstantEnum[] values){
		String str = "{" ;
		for(int i=0; i<values.length; i++){
			str += "\"" + values[i] + "\":" ;
			if(((Object)values[i].getValue()) instanceof String){
				str	+= "\"" + values[i].getValue() + "\"" ;
			}else{
				str	+= values[i].getValue() ;
			}
       		 
    		if(values.length>1 && (i+1)<values.length){
    			str +="," ;
    		}
		}
		
    	str +="}";
    	return str ;
	}
	
	public static String toJSONStringByKeyAndValue(ConstantEnum[] values){
		String str = "{" ;
		for(int i=0; i<values.length; i++){
			str += "\"" + values[i].getKey() + "\":" ;
			if(((Object)values[i].getValue()) instanceof String){
				str	+= "\"" + values[i].getValue() + "\"" ;
			}else{
				str	+= values[i].getValue() ;
			}
       		 
    		if(values.length>1 && (i+1)<values.length){
    			str +="," ;
    		}
		}
		
    	str +="}";
    	return str ;
	}
	
}
