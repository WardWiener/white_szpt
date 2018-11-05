package com.taiji.pubsec.szpt.util;

public class ArcgisConstant {
	
	public static final String BASE_MAP_LAYER_TYPE_DYNAMIC = "dynamic";
	public static final String BASE_MAP_LAYER_TYPE_TILED = "tiled";
	
	public static String MAP_JS_SERVER  ;
	public static String BASE_MAP_SERVER  ;
	public static String BASE_MAP_LAYER_TYPE ;
	
	public static String MAP_CAMERA ;
	public static String MAP_CAR_CAMERA ;
	
	public ArcgisConstant(String arg0, String arg1, String arg2){
		ArcgisConstant.MAP_JS_SERVER = arg0 ;
		ArcgisConstant.BASE_MAP_SERVER = arg1 ;
		ArcgisConstant.BASE_MAP_LAYER_TYPE = arg2 ;
	}

}
