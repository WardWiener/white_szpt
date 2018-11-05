package com.taiji.pubsec.szpt.zhzats.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.zhzats.bean.yanpanListBean;
import com.taiji.pubsec.szpt.zhzats.model.JqVideo;


public interface YanpanService {
	
	/**
	 * 刑事警情
	 * @param 警情查询条件    data包含： keyword关键字  addr发生地点  countryCode所属村居编码   
	 *   			  jqlx警情类型编码   occurTimeStart发生开始时间  occurTimeEnd发生结束时间
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return yanpanListBean的list集合和总页数	
	 */
	Pager<yanpanListBean> findXsjqByPage(Map<String, Object> data, int pageNo, int pageSize);
	
	/**
	 * 查询警情视频
	 * @param jqCode 警情code
	 * @return JqVideo警情视频集合
	 */
	List<JqVideo> queryJqVideo(String jqCode);
	
}
