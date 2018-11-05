package com.taiji.pubsec.szpt.redisson.util;

public class RedisConstant {

	private RedisConstant(){
		
	}
	
	public enum CURRENT_MAC_WIFITRACK{
		
		RMAP_KEY("current_mac_wifitrack"),
		RMAP_KEY_PREFIX("current_mac_wifitrack");
		
		private String value ;
		
		private CURRENT_MAC_WIFITRACK(String value){
			this.value = value ;
		}
		
		public String getValue() {
			return value;
		}
	}
}
