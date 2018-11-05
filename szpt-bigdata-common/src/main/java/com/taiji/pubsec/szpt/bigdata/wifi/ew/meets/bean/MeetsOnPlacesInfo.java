/**
 * 
 */
package com.taiji.pubsec.szpt.bigdata.wifi.ew.meets.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 在各场所相遇的mac，他们的所有的相遇记录信息。
 * 这个对象里，meets中各个meet的gotIdentityForMac()方法返回的值是一样的。
 * 
 * @author yucy
 *
 */
public class MeetsOnPlacesInfo implements Serializable{
	private static final long serialVersionUID = 3928921339108857262L;
	
	private List<MeetRecord> meets = new ArrayList<>();
//	private Integer meetCount = 0;
	private Integer meetCountOnDiffrentPlace = 0;
	
	/**
	 * 判断另一组相遇是否是同一组，判断依据是：两组对应的meet相同；
	 * meet相同的判断依据是：两个meet有相同的placecode，并且包含的各个stay，有相同的mac、进入时间，则认为是同一次相遇。
	 * 
	 * @param that
	 * @return
	 */
	public boolean theSameMeets(MeetsOnPlacesInfo that){
		if(meets.size() != that.getMeets().size()){
			return false;
		}
		for(MeetRecord meet : meets){
			boolean haved = false;
			for(MeetRecord meetthat : that.getMeets()){
				if(meet.theSameMeet(meetthat)){
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
	
	/**
	 * 使用meets中每个meet的placecode与每个stay的mac地址+进入时间联合，形成的字符串返回。
	 * 
	 * @return 
	 */
	public String gotKey(){
		StringBuffer sb = new StringBuffer();
		
		Set<String> set = new TreeSet<>();
		for(MeetRecord meet : meets){
			String meetkey = meet.gotIdentityForPlacecodeAndMacAndIntime();
			set.add(meetkey);
		}
		
		String[] arr = new String[set.size()];
		set.toArray(arr);
		
		for(int i=0; i<arr.length; i++){
			if(i>0 && i<arr.length){
				sb.append("-");
			}
			sb.append(arr[i]);
		}
		
		return sb.toString();
	}
	
	public Integer getMeetCountOnDiffrentPlace() {
		return meetCountOnDiffrentPlace;
	}

	public void setMeetCountOnDiffrentPlace(Integer meetCountOnDiffrentPlace) {
		this.meetCountOnDiffrentPlace = meetCountOnDiffrentPlace;
	}

	public List<MeetRecord> getMeets() {
		return meets;
	}

	public void setMeets(List<MeetRecord> meets) {
		this.meets = meets;
	}

	@Override
	public String toString() {
		return "MeetsOverTwoPlaceInfo [meets=" + meets + ", meetCountOnDiffrentPlace="
				+ meetCountOnDiffrentPlace + "]";
	}
		
}
