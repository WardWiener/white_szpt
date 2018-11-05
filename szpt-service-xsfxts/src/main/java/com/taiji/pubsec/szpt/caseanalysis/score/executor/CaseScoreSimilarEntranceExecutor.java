package com.taiji.pubsec.szpt.caseanalysis.score.executor;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;

@Service
public class CaseScoreSimilarEntranceExecutor extends AbstractCaseScoreSimilarExecutor {

	private static CaseScoreSimilarEntranceExecutor instance;
	@Resource
	private CaseService caseService;

	public static CaseScoreSimilarEntranceExecutor getScoreExecutor(String id) {
		//id = CaseTag.KEY_CASEPLACE
		if (instance == null) {
			instance = SpringContextUtil.getApplicationContext().getBean(CaseScoreSimilarEntranceExecutor.class) ;
		}
		instance.setId(id);
		return instance;
	}

	@Override
	protected Set<String> getComparedCaseTagValues(String caseCode) {
		Set<String> values = new HashSet<String>();
		values.add(caseService.findCaseByCode(caseCode).getCaseTag().getEntrance());
		return values;
	}
}
