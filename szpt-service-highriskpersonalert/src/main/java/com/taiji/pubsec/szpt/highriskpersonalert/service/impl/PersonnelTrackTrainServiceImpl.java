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
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.TrainTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

@Service("personnelTrackTrainService")
public class PersonnelTrackTrainServiceImpl implements PersonnelTrackService{
	private static final Logger logger = LoggerFactory.getLogger(PersonnelTrackTrainServiceImpl.class);

	final static String tablename = "trainticket";
	
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
		String q = SolrConstant.TrainTicket.idcard.getValue() + ":" + idcode;
		
		StringBuffer sb = new StringBuffer();
		String start = df.format(timeStart);
		String end = df.format(timeEnd);
		sb.delete(0,sb.length());
		sb.append(SolrConstant.TrainTicket.starttime.getValue()).append(":[").append(start).append(" TO ").append(end).append("]");
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
			queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_TRAIN_TICKET, query);
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
			TrainTrackBean tt = new TrainTrackBean();
			tt.setTrainNo( (String) doc.getFieldValue(SolrConstant.TrainTicket.trainnumber.getValue()) );
			tt.setPersonIdcode( (String) doc.getFieldValue(SolrConstant.TrainTicket.idcard.getValue()) );
			tt.setOriginLocation( (String) doc.getFieldValue(SolrConstant.TrainTicket.startstation.getValue()) );
			tt.setDestinationLocation( (String) doc.getFieldValue(SolrConstant.TrainTicket.arriveatstation.getValue()) );
			tt.setSetoutTime( (Date) doc.getFieldValue(SolrConstant.TrainTicket.starttime.getValue()) );
			 
			persontracks.add(tt);

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
				 TrainTrackBean tt = new TrainTrackBean();
				 tt.setTrainNo( Bytes.toString(r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("trainnumber"))) );
				 tt.setPersonIdcode( Bytes.toString(r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("idcard"))) );
				 tt.setOriginLocation( Bytes.toString(r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("startstation"))) );
				 tt.setDestinationLocation( Bytes.toString(r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("arriveatstation"))) );
				 long entertime = Bytes.toLong( r.getValue(Bytes.toBytes(FAMILY), Bytes.toBytes("starttime")) );
				 tt.setSetoutTime( new Date(entertime) );
				 
				 persontracks.add(tt);
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
