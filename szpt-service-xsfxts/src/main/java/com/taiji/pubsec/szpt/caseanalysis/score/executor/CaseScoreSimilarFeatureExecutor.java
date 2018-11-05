package com.taiji.pubsec.szpt.caseanalysis.score.executor;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseFeatureTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;

@Service
public class CaseScoreSimilarFeatureExecutor extends AbstractCaseScoreSimilarExecutor {

	private static CaseScoreSimilarFeatureExecutor instance;
	@Resource
	private CaseService caseService;

	public static CaseScoreSimilarFeatureExecutor getScoreExecutor(String id) {
		//id = CaseTag.KEY_CASEPLACE
		if (instance == null) {
			instance = SpringContextUtil.getApplicationContext().getBean(CaseScoreSimilarFeatureExecutor.class) ;
		}
		instance.setId(id);
		return instance;
	}

	@Override
	protected Set<String> getComparedCaseTagValues(String caseCode) {
		Set<String> values = new HashSet<String>();
		for(CaseFeatureTag feature : caseService.findCaseByCode(caseCode).getCaseTag().getFeatures()){
			values.add(feature.getFeature());
		}
		return values;
	}
}
