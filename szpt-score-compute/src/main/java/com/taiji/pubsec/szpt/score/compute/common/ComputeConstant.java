package com.taiji.pubsec.szpt.score.compute.common;

public class ComputeConstant {
	
	private static String KAFKA_TOPIC_SCORE_RESULT_HP ;
	private static String KAFKA_TOPIC_SCORE_RESULT_CASE ;
	
	private ComputeConstant(String KAFKA_TOPIC_SCORE_RESULT_HP, String KAFKA_TOPIC_SCORE_RESULT_CASE){
		ComputeConstant.KAFKA_TOPIC_SCORE_RESULT_HP = KAFKA_TOPIC_SCORE_RESULT_HP ;
		ComputeConstant.KAFKA_TOPIC_SCORE_RESULT_CASE = KAFKA_TOPIC_SCORE_RESULT_CASE;
	}
	
	public static String KAFKA_TOPIC_SCORE_RESULT_CASE(){
		return KAFKA_TOPIC_SCORE_RESULT_CASE ;
	}
	
	public static String KAFKA_TOPIC_SCORE_RESULT_HP(){
		return KAFKA_TOPIC_SCORE_RESULT_HP ;
	}
}
