package com.taiji.pubsec.scoreframework;

public interface ScoreComputeResult<T> {
	T getValue();
	String getOtherResults();
}
