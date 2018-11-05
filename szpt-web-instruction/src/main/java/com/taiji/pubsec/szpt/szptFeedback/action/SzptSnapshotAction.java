package com.taiji.pubsec.szpt.szptFeedback.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONObject;

/**
 * 快照查看查询Action
 * 
 * @author WangLei
 *
 */
@Controller("szptSnapshotAction")
@Scope("prototype")
public class SzptSnapshotAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private InfoSnapshotService infoSnapshotService;// 快照接口
	
	/**
	 * 根据id查询快照
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void findSnapshotById(){
		Map<String, Object> map = JSONObject.fromObject(read());
		
		JSONObject obj = new JSONObject();
		InfoSnapshot is = infoSnapshotService.findInfoSnapshotById((String)map.get("id"));
		if(is == null){
			this.outJSON(obj.toString()) ;
			return ;
		}
		JSONObject data = JSONObject.fromObject(is.getSnapshot());
		obj.put("result", data);
		obj.put("intro", is.getIntro());
		this.outJSON(obj.toString()) ;
	}
}
