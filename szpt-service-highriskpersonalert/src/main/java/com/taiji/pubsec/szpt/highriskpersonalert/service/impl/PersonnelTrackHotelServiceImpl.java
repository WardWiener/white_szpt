package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.szpt.hbase.HBaseHelper;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HotelTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

@Service("personnelTrackHotelService")
public class PersonnelTrackHotelServiceImpl implements PersonnelTrackService{
	private static final Logger logger = LoggerFactory.getLogger(PersonnelTrackHotelServiceImpl.class);

	final static String tablename = "accommodation";
	
	private final static String FAMILY = "info";
	
	private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@Override
	public List<PersonTrackInfo> getPersonTracks(Date timeStart, Date timeEnd, String idcode) {
		return getPersonTracksBySolr(timeStart, timeEnd, idcode);
	}
	
	/*
	 * 通过solr的方式来搜索
	 */
	private List<PersonTrackInfo> getPersonTracksBySolr(Date timeStart, Date timeEnd, String idcode) {
		String q = SolrConstant.Accommodation.idcard.getValue() + ":" + idcode;
		
		StringBuffer sb = new StringBuffer();
		String start = df.format(timeStart);
		String end = df.format(timeEnd);
		sb.delete(0,sb.length());
		sb.append(SolrConstant.Accommodation.entertime.getValue()).append(":[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR ").append(SolrConstant.Accommodation.leavetime.getValue()).append(":[").append(start).append(" TO ").append(end).append("]");
		//设置时间过滤
	    String fq = sb.toString();
	    
	    SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		
		List<PersonTrackInfo> persontracks = new ArrayList<>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_ACCOMMODATION, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == list){
			return persontracks;
		}
		
		
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			HotelTrackBean ht = new HotelTrackBean(); 
			ht.setHotelCode( (String) doc.getFieldValue(SolrConstant.Accommodation.hotelcode.getValue()) );
			ht.setHotelName( (String) doc.getFieldValue(SolrConstant.Accommodation.hotelname.getValue()) );
			ht.setHotelAddr( (String) doc.getFieldValue(SolrConstant.Accommodation.hoteladdress.getValue()) );
			ht.setPersonIdcode( (String) doc.getFieldValue(SolrConstant.Accommodation.idcard.getValue()) );
			ht.setEnterTime( (Date) doc.getFieldValue(SolrConstant.Accommodation.entertime.getValue()) );
			ht.setLeaveTime( (Date) doc.getFieldValue(SolrConstant.Accommodation.leavetime.getValue()) );
			ht.setRoomnum((String) doc.getFieldValue(SolrConstant.Accommodation.roomnum.getValue()));
			persontracks.add(ht);
		}
		return persontracks;
	}
	
	/*
	 * 通过HBase获取数据，暂时不使用，使用通过solr的方式来查询
	 */
	private List<PersonTrackInfo> getPersonTracksByHbase(Date timeStart, Date timeEnd, String idcode) {
		//设置搜索起始位置为身份证号+起始范围时间点
		String start_rowkey = idcode + timeStart.getTime();
		//设置停止位置为身份证号+结束范围时间点
		String stop_rowkey = idcode +timeEnd.getTime();

		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(start_rowkey));
		scan.setStopRow(Bytes.toBytes(stop_rowkey));

		ResultScanner rs = null;
		List<PersonTrackInfo> persontracks = new ArrayList<>();
		try {
			Table table = HBaseHelper.getConn().getTable(TableName.valueOf(tablename));
			rs = table.getScanner(scan);
			 for  (Result r : rs) {
				 HotelTrackBean ht = new HotelTrackBean();
				 ht.setHotelCode(  Bytes.toString(r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("hotelcode"))) );
				 ht.setHotelName( Bytes.toString(r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("hotelname"))) );
				 ht.setHotelAddr( Bytes.toString(r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("hoteladdress"))) );
				 ht.setPersonIdcode( Bytes.toString(r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("idcard"))) );
				 long entertime = Bytes.toLong( r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("entertime")) );
				 ht.setEnterTime( new Date(entertime) );
				 long leavetime = Bytes.toLong( r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("leavetime")) );
				 ht.setLeaveTime( new Date(leavetime) );
				 
				 persontracks.add(ht);
		     }
		} catch (IOException e) {
			logger.error("IOException", e);
		} finally {
			if( null!=rs ){
				rs.close();
			}
		}
		
		return persontracks;
	}
}
