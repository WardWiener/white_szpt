package com.taiji.pubsec.szpt.caseanalysis.score.executor;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;

@Service
public class CaseScoreSimilarPlaceExecutor extends AbstractCaseScoreSimilarExecutor {

	private static CaseScoreSimilarPlaceExecutor instance;
	@Resource
	private CaseService caseService;

	public static CaseScoreSimilarPlaceExecutor getScoreExecutor(String id) {
		//id = CaseTag.KEY_CASEPLACE
		if (instance == null) {
			instance = SpringContextUtil.getApplicationContext().getBean(CaseScoreSimilarPlaceExecutor.class) ;
		}
		instance.setId(id);
		return instance;
	}

	@Override
	protected Set<String> getComparedCaseTagValues(String caseCode) {
		Set<String> values = new HashSet<String>();
		values.add(caseService.findCaseByCode(caseCode).getCaseTag().getOccurPlace());
		return values;
	}
}
