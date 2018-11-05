/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年12月7日 上午10:42:25
 */
package com.taiji.pubsec.szpt.solr;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yucy
 *
 */
public class SolrHelper implements Serializable{
	private static final long serialVersionUID = 8865871645434835282L;

	private static final Logger logger = LoggerFactory.getLogger(SolrHelper.class);
	
	public static String SOLR_ZKHOST = "solr.zkhost";
	public static String SOLR_AUTO_COMMIT_INTERVAL_MS = "solr.auto_commit_interval_ms";
	public static String SOLR_CLIENT_TIMEOUT_MS = "solr.client.timeout";
	public static String SOLR_CONNECT_TIMEOUT_MS = "solr.connect.timeout";
	
	private static String zkHostStr;
	private Integer commitWithinMs = 1000;
	
	private static SolrHelper instance = null;
	private static CloudSolrClient solr = null;
	private int zkClientTimeout = 20000;
	private int zkConnectTimeout = 2000;
	
	private static long second = 1000;
	private static long minute = 60 * second;
	private static long hour = 60 * minute;
	
	public static SolrHelper getInstance() {
		if (instance == null) {
			synchronized (SolrHelper.class) {
				if (instance == null) {
					instance = new SolrHelper();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 带zookeeper的构造方法
	 * @param zkHostStr
	 * @return
	 */
	public static SolrHelper getInstance(String zkHost) {
		zkHostStr = zkHost;
		if (instance == null) {
			synchronized (SolrHelper.class) {
				if (instance == null) {
					instance = new SolrHelper();
				}
			}
		}
		return instance;
	}

	private SolrHelper() {
		if (zkHostStr == null || "".equals(zkHostStr)) {
			zkHostStr = System.getProperty(SOLR_ZKHOST);
		}
		if (System.getProperty(SOLR_CLIENT_TIMEOUT_MS) != null) {
			zkClientTimeout = Integer.valueOf( System.getProperty(SOLR_CLIENT_TIMEOUT_MS) );
		}
		if (System.getProperty(SOLR_CONNECT_TIMEOUT_MS) != null) {
			zkConnectTimeout = Integer.valueOf( System.getProperty(SOLR_CONNECT_TIMEOUT_MS) );
		}
		
		String commitwhithinms = System.getProperty(SOLR_AUTO_COMMIT_INTERVAL_MS);
		if( null != commitwhithinms && !"".equals(commitwhithinms) ){
			this.commitWithinMs = Integer.valueOf( commitwhithinms );
		}
		
		if ( null == zkHostStr || "".equals(zkHostStr.trim())) {
			logger.error("solr.zkhost 必须被设置在JVM的环境属性中，以便连接到Solr服务器!");
		}
		logger.info("solr的zkhost设置在{}", zkHostStr);
		
		solr = new CloudSolrClient.Builder().withZkHost(zkHostStr).build();
		solr.setZkClientTimeout(zkClientTimeout);
		solr.setZkConnectTimeout(zkConnectTimeout);
		solr.connect();
	}
	
	
	public SolrClient getSolrClient(){
		return solr;
	}

	/**
	 * 添加一个索引
	 * 
	 * @param collection Solr的collection的名称
	 * @param uniqueKey 文档的uniqueKey字段名称
	 * @param map 被索引文档的字段名称及值
	 */
	public void addIndex(String collection, String uniqueKey, Map<String, Object> map) {
		if (map == null || map.keySet().size() == 0) {
			logger.error("没有要添加的索引。");
			return;
		}
		if( null == uniqueKey || "".equals(uniqueKey)){
			logger.error("必须指定uniqueKey!");
		}
		solr.setIdField(uniqueKey);
		
		Collection<SolrInputDocument> c = new ArrayList<SolrInputDocument>();
		SolrInputDocument doc = new SolrInputDocument();
		for(Map.Entry<String, Object> entry : map.entrySet()){
			String key = entry.getKey();
			Object value = entry.getValue();
			if( null != key && !"".equals(key) ){
				doc.addField(key, value);
			}
		}
		c.add(doc);
		try {
			solr.add(collection, c);
			solr.commit(collection);
		} catch (SolrServerException e) {
			logger.error("SolrServerException : {}", e);
		} catch (IOException e) {
			logger.error("IOException : {}", e);
		}
			
	}

	/**
	 * 添加多个索引
	 * 
	 * @param collection Solr的collection的名称
	 * @param uniqueKey 文档的uniqueKey字段名称
	 * @param lists 被索引的文档列表
	 */
	public void addIndexes(String collection, String uniqueKey, List<Map<String, Object>> lists) {
		if (lists == null || lists.size() == 0) {
			logger.error("没有要添加的索引。");
			return;
		}
		if( null == uniqueKey || "".equals(uniqueKey)){
			logger.error("必须指定uniqueKey!");
		}
		solr.setIdField(uniqueKey);
		
		Collection<SolrInputDocument> c = new ArrayList<SolrInputDocument>();
		long start = System.currentTimeMillis();

		for (Map<String, Object> map : lists) {
			if (map == null || map.keySet().size() == 0) {
				continue;
			}
			SolrInputDocument doc = new SolrInputDocument();
			for(Map.Entry<String, Object> entry : map.entrySet()){
				String key = entry.getKey();
				Object value = entry.getValue();
				if( null != key && !"".equals(key) ){
					doc.addField(key, value);
				}
			}
			c.add(doc);
		}
		
		long start1 = System.currentTimeMillis();
		logger.debug("生成SolrInputDocuments完成，共耗时:{}", translateTime(start1 - start));
		try {
			solr.add(collection, c, commitWithinMs);
			long start3 = System.currentTimeMillis();
			logger.debug("添加索引，共耗时:{}", translateTime(start3-start1));
			solr.commit(collection);
		} catch (SolrServerException | IOException e) {
			try {
				solr.rollback(collection);
			} catch (SolrServerException | IOException e1) {
				logger.error("回滚失败，共耗时:{}", e);
			}
			long start4 = System.currentTimeMillis();
			logger.error("添加索引失败{}", e);
			logger.debug("添加索引失败，共耗时:{}", translateTime(start4-start));
		}
	}

	/**
	 * 通过id列表删除索引
	 * 
	 * @param collection solr的collection名称
	 * @param list id列表
	 */
	public void delete(String collection, List<String> list) {
		if (list == null || list.size() == 0) {
			return;
		}

		try{
			for(String id : list){
				solr.deleteById(collection, id);
			}
			solr.commit(collection);
		}catch (SolrServerException | IOException e) {
			try {
				solr.rollback(collection);
			} catch (SolrServerException | IOException e1) {
				logger.error("删除索引{}回滚失败", list);
			}
			logger.error("删除索引{}失败", list);
		}
	}

	/**
	 * 删除collection的全部索引
	 * 
	 * @param collection solr的collection的名称
	 */
	public void deleteAll(String collection) {
		if( null == collection ){
			return;
		}
		
		try {
			solr.deleteByQuery(collection, "*:*");
			solr.commit(collection);
		} catch (SolrServerException | IOException e) {
			try {
				solr.rollback(collection);
			} catch (SolrServerException | IOException e1) {
				logger.error("删除{}的全部索引回滚失败", collection);
			}
			logger.error("删除{}的全部索引失败", collection);
		}
	}

	/**
	 * 通过查询参数搜索
	 * 
	 * @param collection
	 * @param params
	 * @param start
	 *            if is null or < 0, then not set
	 * @param size
	 *            if is null or < 1, then not set
	 * @return
	 * @throws SolrServerException
	 */
	public SolrDocumentList search(String collection, Map<String, String> params, Integer start, Integer size) {
		if ( null == collection || params == null || params.keySet().size() == 0) {
			return null;
		}
		
		SolrQuery query = new SolrQuery();
		for(Entry<String, String> param : params.entrySet()){
			String key = param.getKey();
			String value = param.getValue();
			if (key != null && !"".equals(key.trim()) && value != null && !"".equals(value)) {
				query.set(key, value);
			}
		}
		
		if (start != null && start >= 0)
			query.setStart(start);
		if (size != null && size >= 1)
			query.setRows(size);

		SolrDocumentList list = null;
		try {
			QueryResponse queryResponse = solr.query(collection, query);
			list = queryResponse.getResults();
		} catch (SolrServerException | IOException e) {
			logger.error("执行查询（{}）过程错误：{}", params, e);
		}

		return list;
	}

	/**
	 * 通过查询字符串搜索
	 * 
	 * collection 
	 * @param queryStr
	 * @param start
	 *            if is null or < 0, then not set
	 * @param size
	 *            if is null or < 1, then not set
	 * @return
	 * @throws SolrServerException
	 */
	public SolrDocumentList search(String collection, String queryStr, Integer start, Integer size) {
		if (queryStr == null || "".equals(queryStr.trim())) {
			return null;
		}
		
		SolrQuery query = new SolrQuery();
		query.setQuery(queryStr);
		if (start != null && start >= 0)
			query.setStart(start);
		if (size != null && size >= 1)
			query.setRows(size);

		SolrDocumentList list = null;
		try {
			QueryResponse queryResponse = solr.query(collection, query);
			list = queryResponse.getResults();
		} catch (SolrServerException | IOException e) {
			logger.error("执行查询（{}）过程错误：{}", queryStr, e);
		}

		return list;
	}
	
	public void setZkClientTimeout(int zkClientTimeout) {
		this.zkClientTimeout = zkClientTimeout;
	}

	public void setZkConnectTimeout(int zkConnectTimeout) {
		this.zkConnectTimeout = zkConnectTimeout;
	}

	private String translateTime(long interval){
		long hours = interval / hour;
		long minutes = ( interval % hour ) / minute;
		long seconds = ( interval % second ) / second;
		long ms = ( interval % 1000 );
		
		return hours + "小时" + minutes + "分钟" + seconds + "秒" + ms + "毫秒";
	}
}
