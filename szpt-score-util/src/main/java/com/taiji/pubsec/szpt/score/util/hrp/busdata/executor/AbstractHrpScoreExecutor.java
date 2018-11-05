package com.taiji.pubsec.szpt.score.util.hrp.busdata.executor;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeExecutor;
import com.taiji.pubsec.szpt.score.util.Constant;

public abstract class AbstractHrpScoreExecutor implements ScoreComputeExecutor{

	protected String id ;
	
	protected String getHrpId(Parameter... params){
		for(Parameter p : params){
			if(Constant.PARAMETER_TAG_HRP_ID.equals(p.getTag()) ){
				return (String)p.getParameter() ;
			}
		}
		return null ;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	protected String generateDictItemName(DictionaryItem item){
		String name = item.getName() ;
		DictionaryItem parent = item.getParentItem() ;
		while(parent!=null){
			name = parent.getName() + "@" + name ;
			parent = parent.getParentItem() ;
		}
		return name; 
	}
}
