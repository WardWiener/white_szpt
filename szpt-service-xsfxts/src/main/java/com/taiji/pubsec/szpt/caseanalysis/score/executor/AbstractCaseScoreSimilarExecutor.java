package com.taiji.pubsec.szpt.caseanalysis.score.executor;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeExecutor;

@Service
public abstract class AbstractCaseScoreSimilarExecutor implements ScoreComputeExecutor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractCaseScoreSimilarExecutor.class);

	private String key;
	
	
	@Override
	public Object[] excute(Parameter... params) {
		// arg0：主案件code
		// arg1: 主案件打标值集合
		// arg2：比对案件code
		// arg3: 模板id
//		String result = null;
		Map<String,Set<String>> tagSet = (Map<String, Set<String>>) params[1].getParameter();
		String comparedCaseCode = (String) params[2].getParameter();
		Set<String> comparedCaseValues = getComparedCaseTagValues(comparedCaseCode);
		Set<String> mainCaseCommunitySet = tagSet.get(getId());
		boolean result = comparedCaseValues.retainAll(mainCaseCommunitySet);  //comparedCaseValues中为交集
		LOGGER.debug("集合交集结果:{}, 交集数量：{}", result, comparedCaseValues.size());
		
		
		Double ratio = (double) 0;
		if(mainCaseCommunitySet.size() != 0) {
			ratio = (double) (comparedCaseValues.size() / mainCaseCommunitySet.size());
		}
		
		return new Object[]{ratio};
	}

	@Override
	public String getId() {
		return key;
	}
	
	public void setId(String id) {
		key = id;
	}

	protected abstract Set<String> getComparedCaseTagValues(String caseCode);
}
