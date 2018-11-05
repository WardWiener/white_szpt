package com.taiji.pubsec.szpt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author wangfx
 *
 */
public class ParamMapUtil {
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotBlank(Object o) {
		if (o instanceof String) {
			return StringUtils.isNotBlank((String)o);
		}
		if ((o instanceof Long)||(o instanceof Integer)||(o instanceof Date)) {
			if (!o.equals(null)) {
				return true;
			}
		}
		if (o instanceof Collection) {
			if (!((Collection)o).isEmpty()) {
				return true;
			}
		}
		if(o instanceof Boolean) {
			return !o.equals(null);
		}
		return false;
	}

	/**
	 * 当使用like查询的时候，需要把子code去掉
	 * @return
	 */
	public static String[] removeChildCodeForQueryLike(String[] codes){
		List<String> list = new ArrayList<String>();
		for(String code:codes){
			boolean flag = true ;
			for(String code1:codes){
				if(!code.equals(code1) && code.contains(code1)){
					flag = false ;
				}
			}
			if(flag){
				list.add(code);
			}
		}
		return list.toArray(new String[list.size()]); 
	}
}
