package com.taiji.pubsec.scoreframework;

import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;

public class DefaultParameter implements Parameter{
	private Object value;
	private String tag;
	
	public DefaultParameter(Object value, String tag) {
		super();
		this.value = value;
		this.tag = tag;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.scoreframework.Parameter#support(com.taiji.pubsec.scoreframework.ScoreComputeObject)
	 */
	@Override
	public boolean support(ScoreComputeObject scoreObject) {
		if( scoreObject instanceof ScoreComputeTaskImpl)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.scoreframework.Parameter#getTag()
	 */
	@Override
	public String getTag() {
		return tag;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.scoreframework.Parameter#getParameter()
	 */
	@Override
	public Object getParameter() {
		// TODO Auto-generated method stub
		return value;
	}
}
