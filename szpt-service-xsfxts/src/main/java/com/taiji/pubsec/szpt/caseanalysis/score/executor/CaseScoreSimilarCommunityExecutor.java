package com.taiji.pubsec.szpt.caseanalysis.score.executor;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeExecutor;
import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.service.ICommunityService;

@Service
public class CaseScoreSimilarCommunityExecutor implements ScoreComputeExecutor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CaseScoreSimilarCommunityExecutor.class);
	
//	private static CaseScoreSimilarCommunityExecutor instance;
	@Resource
	private CaseService caseService;
//	@Resource
//	private CaseTagService caseTagService;
	@Resource
	private ICommunityService communityService;
	
	
	@Override
	public Object[] excute(Parameter... params) {
		// arg0：主案件code
		// arg1: 主案件打标值集合
		// arg2：比对案件code
		String result = null;
		Map<String,Set<String>> tagSet = (Map<String, Set<String>>) params[1].getParameter();
		String comparedCaseCode = (String) params[2].getParameter();
		LOGGER.debug("compared case code:{}", comparedCaseCode);
		String comparedCaseCommunity = caseService.findCaseByCode(comparedCaseCode).getCaseTag().getCommunity();
		Set<String> mainCaseCommunitySet = tagSet.get(CaseTag.KEY_CASECOMMUNITY);
		if(mainCaseCommunitySet.contains(comparedCaseCommunity)) {
			result = RobberyTheftCaseScoreTemplate.COMMUNITYRULE_SAME;
		} else {
			boolean isNeighbouring = false;
			for(String mainCommunity : mainCaseCommunitySet) {
				if(communityService.isNeighbourhood(comparedCaseCommunity, mainCommunity)){
					isNeighbouring = true;
					break;
				};
			}
			if(isNeighbouring) {
				result = RobberyTheftCaseScoreTemplate.COMMUNITYRULE_SAME;
			} else {
				result = RobberyTheftCaseScoreTemplate.COMMUNITYRULE_NONNEIGHBOR;
			}
		}
		
		return new Object[]{result};
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	// public void setId(String id) {
	//
	// }
	public static CaseScoreSimilarCommunityExecutor getScoreExecutor(String id) {
		return SpringContextUtil.getApplicationContext().getBean(CaseScoreSimilarCommunityExecutor.class) ;
	}
}
