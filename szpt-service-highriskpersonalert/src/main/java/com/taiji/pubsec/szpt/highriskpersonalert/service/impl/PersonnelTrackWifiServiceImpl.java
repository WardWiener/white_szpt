package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.WifiTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

@Service("personnelTrackWifiService")
public class PersonnelTrackWifiServiceImpl implements PersonnelTrackService{
	private static final Logger logger = LoggerFactory.getLogger(PersonnelTrackWifiServiceImpl.class);
	
	private final static String COLLECTION = "wifitrack";

	private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	@Resource
	IHighriskPersonService highriskPersonService;
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;
	
	@Override
	public List<PersonTrackInfo> getPersonTracks(Date timeStart, Date timeEnd, String macOrPhonenumber) {
		List<PersonTrackInfo> persontracks = new ArrayList<>();
		
		HighriskPerson hrp = highriskPersonService.findByIdCode(macOrPhonenumber);
		
		StringBuffer sb = new StringBuffer();
		sb.append(macOrPhonenumber) ;
/*		for(MobilePhoneInfo mp : hrp.getMobilePhoneInfos()){
			String mac = mp.getMac();
			String phone = mp.getNumber();
			if( null != mac && !"".equals(mac) ){
				if( sb.length() > 0){
					sb.append(" ");
				}
				sb.append(mac);
				
			}else if( null != phone && !"".equals(phone) ){
				if( sb.length() > 0){
					sb.append(" ");
				}
				sb.append(phone);
			}
			
		}*/
		
		//获得该身份证下的mac和电话号码作为查询
		String q = sb.toString();
		
		String start = df.format(timeStart);
		String end = df.format(timeEnd);
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		//设置时间过滤
	    String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		//设置查询字段为mac或者phonenumber
		query.set("qf", "mac phonenumber");
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == list )
			return persontracks;
		
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			WifiTrackBean wifi = new WifiTrackBean();
			wifi.setMac((String) doc.getFieldValue(SolrConstant.WifiTrack.mac.getValue()));
//			wifi.setPersonIdcode( idcode );

			String placeCode = (String) doc.getFieldValue(SolrConstant.WifiTrack.placecode.getValue());
			wifi.setPlaceCode(placeCode);

			wifi.setEnterTime((Date) doc.getFieldValue(SolrConstant.WifiTrack.entertime.getValue()));
			wifi.setLeaveTime((Date) doc.getFieldValue(SolrConstant.WifiTrack.leavetime.getValue()));
			wifi.setStayInterval((Long) wifi.getLeaveTime().getTime() - wifi.getEnterTime().getTime());
			wifi.setPhone( (String) doc.getFieldValue(SolrConstant.WifiTrack.phonenumber.getValue()) );

			String placeName = (String) doc.getFieldValue(SolrConstant.WifiTrack.placename.getValue());
			wifi.setPlaceName(placeName);

			// 解析经纬度数据
			String location = (String) doc.getFieldValue(SolrConstant.WifiTrack.placeposition.getValue());
			if(StringUtils.isNotBlank(location)){
				String[] loc = location.split(",");
				wifi.setLatitude( Double.valueOf(loc[0]) );
				wifi.setLongitude( Double.valueOf(loc[1]) );
			}

			if(StringUtils.isBlank(placeName) || StringUtils.isBlank(location)){

				PlaceBasicInfo pbi = placeBasicInfoService.findByCode(placeCode) ;
				if(StringUtils.isBlank(placeName) && pbi!=null){
					placeName = pbi.getInternetServicePlaceName() ;

				}
				if(wifi.getLongitude()==null && pbi!=null){
					wifi.setLongitude(Double.valueOf(pbi.getLongitude()));
				}
				if(wifi.getLatitude()==null && pbi!=null){
					wifi.setLatitude(Double.valueOf(pbi.getLatitude()));
				}
			}


			persontracks.add(wifi);
		}
		
		return persontracks;
	}

}
