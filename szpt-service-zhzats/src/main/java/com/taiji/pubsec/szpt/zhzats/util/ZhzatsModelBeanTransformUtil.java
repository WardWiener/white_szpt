package com.taiji.pubsec.szpt.zhzats.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import com.taiji.pubsec.szpt.zhzats.bean.CjFeedbackBean;
import com.taiji.pubsec.szpt.zhzats.model.CjFeedback;

/**
 * 综合治安台式公用model和Bean互转类
 * 
 * @author WangLei
 *
 */
@Component
public class ZhzatsModelBeanTransformUtil {

	/**
	 * 处警反馈Model转Bean
	 * 
	 * @param cjf
	 * @return
	 */
	public CjFeedbackBean CjFeedbackToCjFeedbackBean(CjFeedback cjf) {
		if(cjf == null){
			return null;
		}
		CjFeedbackBean cfb = new CjFeedbackBean();
		BeanCopier copier = BeanCopier.create(cjf.getClass(), cfb.getClass(), false);
		copier.copy(cjf, cfb, null);
		cfb.setFeedbackTime(cjf.getFeedbackTime() == null ? null : cjf.getFeedbackTime().getTime());
		return cfb;
	}
}
