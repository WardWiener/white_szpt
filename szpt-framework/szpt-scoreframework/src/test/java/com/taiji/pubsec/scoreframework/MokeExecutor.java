package com.taiji.pubsec.scoreframework;

import com.taiji.pubsec.scoreframework.ScoreComputeExecutor;
import com.taiji.pubsec.scoreframework.Parameter;

public class MokeExecutor implements ScoreComputeExecutor {
	
	public static String TAG_MOKEEXECUTOR_X = "com.taiji.pubsec.scoreframework.MokeExecutor_x";
	public static String TAG_MOKEEXECUTOR_Y = "com.taiji.pubsec.scoreframework.MokeExecutor_y";
	
	private String id;

	public static MokeExecutor getScoreExecutor(String id){
		MokeExecutor e = new MokeExecutor();
		e.setId(id);
		return e;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object[] excute(Parameter... params) {
		Integer x = null, y = null;
		for(Parameter param : params){
			if( param.support(this) && null != param.getTag()){
				if(param.getTag().equals(TAG_MOKEEXECUTOR_X))
					x = (Integer)param.getParameter();
				else if(param.getTag().equals(TAG_MOKEEXECUTOR_Y)){
					y = (Integer)param.getParameter();
				}
			}
		}
		if(x == null)
			x = 1;
		if(y == null)
			y = 1;
		Integer bunessData = doBussinessOperation(x, y);
		Object[] r = new Object[]{bunessData};
		return r;
	}

	private Integer doBussinessOperation(int x, int y){
		return new Integer(x*y);
	}
}
