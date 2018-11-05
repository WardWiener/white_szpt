/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.InOutTime;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StayRecord;

/**
 * 在同一个场所的相遇记录。
 * 因为在产生meet数据的时候，是在同一个场所做的计算，
 * 而同一个场所中同一个mac不可能会有相遇的停留记录，
 * 所以meet里的停留记录stays中都是不同mac的记录。
 * 
 * @author yucy
 *
 */
public class MeetRecord implements Serializable {
	private static final long serialVersionUID = -7819362443884772787L;

	private String placeCode;

	private List<StayRecord> stays = new ArrayList<>();
	
	public MeetRecord() {
		super();
	}

	public MeetRecord(String placeCode) {
		super();
		this.placeCode = placeCode;
	}

	/**
	 * 获得相遇时间段。
	 * 
	 * @return
	 */
	public InOutTime gotMeetTime(){
		InOutTime result = InOutTime.EMPTY_VALUE;
		
		if(stays.size() == 1){
			return stays.get(0).getInout();
		}
		
		StayRecord[] arrstay = new StayRecord[stays.size()];
		stays.toArray(arrstay);
		for(int i=0; i<arrstay.length-1; i++){
			for(int j=i+1; j<arrstay.length; j++){
				InOutTime t = arrstay[i].getInout().intersect(arrstay[j].getInout());
				if(null == result){
					result = t;
				}else{
					result = result.intersect(t);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获得相遇的各方的mac地址，用";"连接。
	 * mac地址通过排序后连接，因此只要是相遇的各方相同，不论存放的顺序如何，返回的值都相同。
	 */
	public String gotIdentityForMac(){
		Set<String> set = new TreeSet<>();
		for(StayRecord stay : stays){
			set.add(stay.getMac());
		}
		String[] arr = new String[set.size()];
		set.toArray(arr);
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<arr.length; i++){
			if(i>0 && i<arr.length){
				sb.append(";");
			}
			sb.append(arr[i]);
		}
		
		return sb.toString();
	}
	
	public String gotIdentityForPlacecodeAndMacAndIntime(){
		Set<String> set = new TreeSet<>();
		for(StayRecord stay : stays){
			set.add(stay.getMac() + "_" + stay.getInout().getIn());
		}
		String[] arr = new String[set.size()];
		set.toArray(arr);
		
		StringBuffer sb = new StringBuffer();
		sb.append(placeCode).append("-");
		for(int i=0; i<arr.length; i++){
			if(i>0 && i<arr.length){
				sb.append("-");
			}
			sb.append(arr[i]);
		}
		
		return sb.toString();
	}
	
	public boolean putStay(StayRecord stay){
		if(!placeCode.equals(stay.getPlacecode())){
			return false;
		}
		if(stays.size() == 0){
			stays.add(stay);
			return true;
		}
		InOutTime meettime = stay.getInout().intersect(gotMeetTime());
		if( !InOutTime.EMPTY_VALUE.equals(meettime) ){
			stays.add(stay);
			return true;
		}
		return false;
	}
	

	/**
	 * 判断两个相遇是否是同一次的相遇.
	 * 如果两个相遇有相同的placecode，并且包含的各个stay，有相同的mac、进入时间，则认为是同一次相遇。
	 * 同一次相遇可能采集的时间不同造成stay的离开时间可能有差别，所以判断标准以进入时间相同为准。
	 * 
	 * @param that
	 * @return
	 */
	public boolean theSameMeet(MeetRecord that){
		if( null == placeCode ){
			return false;
		}
		if( !placeCode.equals(that.getPlaceCode()) ){
			return false;
		}
		if(stays.size() != that.getStays().size()){
			return false;
		}
		for(StayRecord stay : stays){
			boolean haved = false;
			for(StayRecord staythat : that.getStays()){
				if(stay.getMac().equals(staythat.getMac()) 
						&& stay.getInout().getIn() == staythat.getInout().getIn()){
					haved = true;
					break;
				}
			}
			if(!haved){
				return false;
			}
		}
		return true;
	}
	
	public String getPlaceCode() {
		return placeCode;
	}

	public List<StayRecord> getStays() {
		return stays;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public void setStays(List<StayRecord> stays) {
		this.stays = stays;
	}

	@Override
	public String toString() {
		return "MeetRecord [placeCode=" + placeCode + ", stays=" + stays + "]";
	}

}
