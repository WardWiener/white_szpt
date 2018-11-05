package com.taiji.pubsec.scoreframework;

/**
 * {@link ScoreComputeResult}的实现。
 * 对Double类型的考核结果进行了包装。
 * @author lenovo
 *
 */
public class ScoreComputeResultImpl implements ScoreComputeResult<Double> {
	private Double score;
	private String otherResults ;
	
	public ScoreComputeResultImpl(Double score) {
		super();
		this.score = score;
	}
	
	public ScoreComputeResultImpl(Double score, String otherResults) {
		super();
		this.score = score;
		this.otherResults = otherResults;
	}

	public Double getValue() {
		return score;
	}

	public void setValue(Double score){
		this.score = score;
	}

	@Override
	public String toString() {
		StringBuffer r = new StringBuffer();
		r.append("[").append(String.valueOf(score));
		r.append("]");
		return r.toString();
	}

	@Override
	public String getOtherResults() {
		return otherResults;
	}

	public void setOtherResults(String otherResults) {
		this.otherResults = otherResults;
	}


	
	
	
}
