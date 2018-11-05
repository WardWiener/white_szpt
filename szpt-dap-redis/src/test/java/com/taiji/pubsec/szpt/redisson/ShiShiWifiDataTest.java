package com.taiji.pubsec.szpt.redisson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMapCache;
import org.redisson.api.RSetCache;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.taiji.pubsec.szpt.redisson.util.RedisConstant;

import net.sf.json.JSONObject;


/**
 * 实时wifi假数据测试类
 * 
 * @author WangLei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class ShiShiWifiDataTest {
	
	// 用于记录当前批次的所有wifi轨迹数据的缓存
	private final static String REDIS_KEY_CURRENT_MAC_WIFITRACK = "current_mac_wifitrack";
	//放入Redis中数据过期时间（秒）
	private final static long REDIS_EVICTION_TIME = 3600;
	
	private final static String REDIS_KEY_CURRENT_POINT_MACS_PREFIX = "current_point_";

	@Test
	public void addWifiData(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] longitudes = {"106.67274835561507", "106.6773236903723", "106.68029468696793", "106.68243380451678", "106.68332510349545", "106.68510770145281", "106.68831637777609", "106.69164389396316", "106.69348591185243", "106.69455547062687", "106.69621922872042", "106.69794240674588", "106.69871486586072", "106.70269600129886", "106.70661771680506", "106.70946987353688", "106.71101479176657", "106.7135104289069", "106.71606548597914", "106.71671910523015", "106.71826402345988", "106.71850170318753", "106.71856112311944", "106.71862054305136", "106.71909590250665"};
		String[] latitudes = {"26.50601012194", "26.506488688360005", "26.508509280155153", "26.511008384001084", "26.512763041444906", "26.514092309541127", "26.514251820678798", "26.514198650324186", "26.512709870401032", "26.51132741462101", "26.509732252660275", "26.507977548917907", "26.506860905308436", "26.50638234043894", "26.506435514411773", "26.5072331210509", "26.50925369975239", "26.510476664332053", "26.509466390179792", "26.506169644301497", "26.503617259938142", "26.501437053394426", "26.498990918763326", "26.497289229180513", "26.494630288766803"};
		
		List<WifiData> wifiDatas = new ArrayList<WifiData>();
		
		int aIndex = 1;
		for(int i=0;i<3;i++){
			WifiData wifidata = new WifiData();
			wifidata.setId("wifiDataId" + i);
			wifidata.setMac("A"+ aIndex +"-BB-CC-DD-EE-FF");
			wifidata.setEnterTime(sdf.format(new Date()));
			wifidata.setLeaveTime(sdf.format(new Date()));
			wifidata.setGatherTime(sdf.format(new Date()));
			wifidata.setCreateTime(new Date());
			wifidata.setPlaceId("52010421004388");
			wifidata.setPlaceName("凯旋门网吧");
			wifidata.setLongitude(longitudes[i]);
			wifidata.setLatitude(latitudes[i]);
			wifidata.setIdcode("120624199303487745" + i);
			wifidata.setFiveColorPersonName("");
			wifidata.setPeopleType(new ArrayList<String>());
			wifidata.setWarnType("");
			wifidata.setNoupdateCount(0);
			
			wifiDatas.add(wifidata);
			aIndex ++ ;
		}
		
		int bIndex = 1;
		for(int i=3;i<7;i++){
			WifiData wifidata = new WifiData();
			wifidata.setId("wifiDataId" + i);
			wifidata.setMac("AA-B"+ bIndex +"-CC-DD-EE-FF");
			wifidata.setEnterTime(sdf.format(new Date()));
			wifidata.setLeaveTime(sdf.format(new Date()));
			wifidata.setGatherTime(sdf.format(new Date()));
			wifidata.setCreateTime(new Date());
			wifidata.setPlaceId("5201142B000012");
			wifidata.setPlaceName("松花江路王武菜场大门");
			wifidata.setLongitude(longitudes[i]);
			wifidata.setLatitude(latitudes[i]);
			wifidata.setIdcode("220624199303487745" + i);
			wifidata.setFiveColorPersonName("");
			wifidata.setPeopleType(new ArrayList<String>());
			wifidata.setWarnType("");
			wifidata.setNoupdateCount(0);
			
			wifiDatas.add(wifidata);
			bIndex ++ ;
		}
		
		int cIndex = 1;
		for(int i=7;i<13;i++){
			WifiData wifidata = new WifiData();
			wifidata.setId("wifiDataId" + i);
			wifidata.setMac("AA-BB-C"+ cIndex +"-DD-EE-FF");
			wifidata.setEnterTime(sdf.format(new Date()));
			wifidata.setLeaveTime(sdf.format(new Date()));
			wifidata.setGatherTime(sdf.format(new Date()));
			wifidata.setCreateTime(new Date());
			wifidata.setPlaceId("52011521000538");
			wifidata.setPlaceName("金阳吉隆主题酒店");
			wifidata.setLongitude(longitudes[i]);
			wifidata.setLatitude(latitudes[i]);
			wifidata.setIdcode("320624199303487745" + i);
			wifidata.setFiveColorPersonName("");
			wifidata.setPeopleType(new ArrayList<String>());
			wifidata.setWarnType("");
			wifidata.setNoupdateCount(0);
			
			wifiDatas.add(wifidata);
			cIndex ++ ;
		}
		
		int dIndex = 1;
		for(int i=13;i<19;i++){
			WifiData wifidata = new WifiData();
			wifidata.setId("wifiDataId" + i);
			wifidata.setMac("AA-BB-CC-D"+ dIndex +"-EE-FF");
			wifidata.setEnterTime(sdf.format(new Date()));
			wifidata.setLeaveTime(sdf.format(new Date()));
			wifidata.setGatherTime(sdf.format(new Date()));
			wifidata.setCreateTime(new Date());
			wifidata.setPlaceId("52011325000343");
			wifidata.setPlaceName("长宁街66-1鑫垚小菜馆门头上");
			wifidata.setLongitude(longitudes[i]);
			wifidata.setLatitude(latitudes[i]);
			wifidata.setIdcode("420624199303487745" + i);
			wifidata.setFiveColorPersonName("");
			wifidata.setPeopleType(new ArrayList<String>());
			wifidata.setWarnType("");
			wifidata.setNoupdateCount(0);
			
			wifiDatas.add(wifidata);
			dIndex ++;
		}
		
		int eIndex = 1;
		for(int i=19;i<24;i++){
			WifiData wifidata = new WifiData();
			wifidata.setId("wifiDataId" + i);
			wifidata.setMac("AA-BB-CC-DD-E"+ eIndex +"-FF");
			wifidata.setEnterTime(sdf.format(new Date()));
			wifidata.setLeaveTime(sdf.format(new Date()));
			wifidata.setGatherTime(sdf.format(new Date()));
			wifidata.setCreateTime(new Date());
			wifidata.setPlaceId("52011325000957");
			wifidata.setPlaceName("建设路68号居民楼");
			wifidata.setLongitude(longitudes[i]);
			wifidata.setLatitude(latitudes[i]);
			wifidata.setIdcode("520624199303487745" + i);
			wifidata.setFiveColorPersonName("");
			wifidata.setPeopleType(new ArrayList<String>());
			wifidata.setWarnType("");
			wifidata.setNoupdateCount(0);
			
			wifiDatas.add(wifidata);
			eIndex ++ ;
		}
		
		int fIndex = 1;
		for(int i=24;i<25;i++){
			WifiData wifidata = new WifiData();
			wifidata.setId("wifiDataId" + i);
			wifidata.setMac("AA-BB-CC-DD-EE-F" + fIndex);
			wifidata.setEnterTime(sdf.format(new Date()));
			wifidata.setLeaveTime(sdf.format(new Date()));
			wifidata.setGatherTime(sdf.format(new Date()));
			wifidata.setCreateTime(new Date());
			wifidata.setPlaceId("52010427001120");
			wifidata.setPlaceName("赖师傅大碗菜海纳店");
			wifidata.setLongitude(longitudes[i]);
			wifidata.setLatitude(latitudes[i]);
			wifidata.setIdcode("620624199303487745" + i);
			wifidata.setFiveColorPersonName("");
			wifidata.setPeopleType(new ArrayList<String>());
			wifidata.setWarnType("");
			wifidata.setNoupdateCount(0);
			
			wifiDatas.add(wifidata);
			fIndex ++ ;
		}
		
		for(WifiData wd : wifiDatas){
			//获得redis中存放的map
			RMapCache<String, String> maptrack = RedissonHelper.getClient().getMapCache(REDIS_KEY_CURRENT_MAC_WIFITRACK);
			
			String mac = wd.getMac();
			String pointcode = wd.getPlaceId();
			
			//将wifi轨迹放入缓存
			maptrack.fastPut(mac, JSONObject.fromObject(wd).toString(), REDIS_EVICTION_TIME, TimeUnit.SECONDS);
			
			//获得该场所所有mac地址的缓存
			RSetCache<String> macs = RedissonHelper.getClient().getSetCache(REDIS_KEY_CURRENT_POINT_MACS_PREFIX + pointcode);
			//将mac放入缓存
			macs.add(mac, REDIS_EVICTION_TIME, TimeUnit.SECONDS);
		}
	}
	
	@Test
	public void testHget(){
		List<String> macs = new ArrayList<>();
		macs.add("AA-BB-CC-DD-EE-F1");
		Set<String> set= new HashSet<String>();
		set.addAll(macs);
		
		RMapCache<String, String> maptrack = RedissonHelper.getClient().getMapCache(RedisConstant.CURRENT_MAC_WIFITRACK.RMAP_KEY.getValue());
		
		System.out.println("============" + maptrack.values().size() + "==============");
		Map<String, String> map = maptrack.getAll(set);
		for(Entry<String, String> entry : maptrack.getAll(set).entrySet()){
			System.out.println("uuuuuuuuuuuuuuuuuuuuuu");
			System.out.println("+++++++++++++   "  + entry.getKey() + "::::::::::"+ entry.getValue());
		}
		System.out.println("==================");
	}
	
	@Test
	public void testHgetFindByPlaceCode(){
		RSetCache<String> macs = RedissonHelper.getClient().getSetCache(REDIS_KEY_CURRENT_POINT_MACS_PREFIX + "52010421004388");
		int macsSize = macs.size();
		System.out.println(macsSize);
	}
}
