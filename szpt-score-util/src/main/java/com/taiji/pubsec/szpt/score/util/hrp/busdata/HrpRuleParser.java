package com.taiji.pubsec.szpt.score.util.hrp.busdata;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.score.util.hrp.pojo.ScorePointInfo;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.RuleDetail;

public class HrpRuleParser {
	
	private HrpRuleParser(){
		
	}

	/**
	 * 将scorepoint的规则结构化，拆分出scorepoint和scorepoint下面的规则
	 * @param ruleDetails
	 */
	public static List<ScorePointInfo> parser(List<RuleDetail> ruleDetails){
		
		List<ScorePointInfo> scorePoints = getAllPoints(ruleDetails);

		for(RuleDetail rd:ruleDetails){
			ruleDetailToScorePoint(rd, scorePoints);
		}
		
		return scorePoints ;
	}
	
	private static void ruleDetailToScorePoint(RuleDetail rd, List<ScorePointInfo> scorePoints){
		for(ScorePointInfo cp:scorePoints){
			if(rd.getKey().contains(cp.getKey())){
				
				if(rd.getKey().contains(RuleDetail.SCORE_POINT_WEIGHT)){
					cp.setWeight(Double.valueOf(rd.getValue().toString()));
				}
				
				cp.getRuleDetails().add(rd) ;
			}
		}
	}

	private static List<ScorePointInfo> getAllPoints(List<RuleDetail> ruleDetails){
		
		List<ScorePointInfo> list = new ArrayList<ScorePointInfo>();
		
		for(RuleDetail rd:ruleDetails){
			String[] keys = rd.getKey().split("-") ;
			
			ScorePointInfo ck = getPointFromList(keys[0], list) ;
			if(ck==null){
				ck = new ScorePointInfo() ;
				ck.setKey(keys[0]);
				list.add(ck) ;
			}
			
		}
		return list ;
	}
	
	private static ScorePointInfo getPointFromList(String key, List<ScorePointInfo> list){
		for(ScorePointInfo cp:list){
			if(cp.getKey().equals(key)){
				return cp ;
			}
		}
		return null ;
	}
}
