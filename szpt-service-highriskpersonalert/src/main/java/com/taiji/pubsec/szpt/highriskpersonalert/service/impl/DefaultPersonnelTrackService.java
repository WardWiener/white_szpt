package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;

/**
 * 人员轨迹接口默认实现
 * 
 * @author wangfx
 *
 */
@Service("defaultPersonnelTrackService")
public class DefaultPersonnelTrackService implements PersonnelTrackService {
	
	private Logger LOGGER = LoggerFactory.getLogger(DefaultPersonnelTrackService.class) ;

	private static List<String> serviceNames = Arrays.asList("personnelTrackAirPlaneService",
			"personnelTrackHotelService", "personnelTrackInternetBarService", "personnelTrackTrainService");

	@Override
	public List<PersonTrackInfo> getPersonTracks(Date timeStart, Date timeEnd, String idcode) {
		List<PersonTrackInfo> personTrackInfos = new ArrayList<PersonTrackInfo>();
		Map<String, PersonnelTrackService> beanMap = SpringContextUtil.getApplicationContext()
				.getBeansOfType(PersonnelTrackService.class);
		for (Map.Entry<String, PersonnelTrackService> entry : beanMap.entrySet()) {
			if (serviceNames.contains(entry.getKey())) {
				PersonnelTrackService personTrackService = entry.getValue();
				personTrackInfos.addAll(personTrackService.getPersonTracks(timeStart, timeEnd, idcode));
			}
		}
		// for(int i = 0; i < personTrackInfos.size(); i++) {
		// PersonTrackInfo personTrackInfoa = personTrackInfos.get(i);
		// for(int j = i + 1; j < personTrackInfos.size(); j ++) {
		// PersonTrackInfo personTrackInfob = personTrackInfos.get(j);
		// if(personTrackInfob.getAppearTime().before(personTrackInfoa.getAppearTime()))
		// {
		// personTrackInfos.set(i, personTrackInfob);
		// personTrackInfos.set(j, personTrackInfoa);
		// }
		// }
		// }

		if (!personTrackInfos.isEmpty()) {
			Collections.sort(personTrackInfos, new Comparator<PersonTrackInfo>() {
				public int compare(PersonTrackInfo obj1, PersonTrackInfo obj2) {
					LOGGER.debug("Compare obj1 class: {}", obj1.getClass().getName());
					LOGGER.debug("Compare obj2 class: {}", obj2.getClass().getName());
					
					if(obj2.getAppearTime()==null || obj1.getAppearTime()==null){
						return 0 ;
					}
					return obj2.getAppearTime().compareTo(obj1.getAppearTime());
				}
			});
		}

		return personTrackInfos;
	}

}
