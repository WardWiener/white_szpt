package com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service.ClueDeployService;
import com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.SurveilListService;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListMobileInfo;

@Service
public class SurveilListServiceImpl implements SurveilListService, ApplicationContextAware{

    private static final Logger LOGGER = LoggerFactory.getLogger(SurveilListServiceImpl.class);

    private Map<String, SurveilListInfo> surveilListInfoMap = new HashMap<String, SurveilListInfo>();

    @Autowired
    private SqlDao sqlDao ;

    @Resource
    private SurveilListDAService surveilListDAService;

    @Resource
    private ClueDeployService clueDeployService ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.loadAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate();
    }

    @Override
    public void loadAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate() {
        List<SurveilListInfo> list = this.surveilListDAService.findAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate();
        surveilListInfoMap.clear();
        for(SurveilListInfo info:list){
            surveilListInfoMap.put(info.getNum(), info);
        }
    }

    @Override
    public void unTransRenewSurveilListBySurveilListCode(String surveilListCode){

        LOGGER.debug("更新布控单缓存事务外的门面方法，布控单号：{}，为了避免事务问题，等待1分钟查询布控单是否已加入内存，如果发现有则跳出等待", surveilListCode);

        int times = 60;
        while (times > 0) {
            LOGGER.debug("等待第{}次，布控单缓存数量：{}", 60-times+1, surveilListInfoMap.size());
            if(renewSurveilListBySurveilListCode(surveilListCode)){
                LOGGER.debug("内存中已存在，跳出等待");
                break;
            }
            waitForSleep(1000) ;
            times--;
        }
    }

    @Override
    public boolean renewSurveilListBySurveilListCode(String surveilListCode) {

        LOGGER.debug("更新布控单：" + surveilListCode) ;

        LOGGER.debug("更新前布控单数量：" + surveilListInfoMap.size()) ;

        this.loadAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate();

        SurveilListInfo info = this.getSurveilListInfoByNotOutOfDateByNum(surveilListCode);

        if(info==null){
            LOGGER.debug("布控单{}在内存中不存在", surveilListCode) ;
            return false;
        }

        clueDeployService.deploy(info, SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_ADD_OR_UPDATE);

        LOGGER.debug("更新后布控单数量：" + surveilListInfoMap.size()) ;
        return true ;
    }

    private void waitForSleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LOGGER.error("loadAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate中的waitForChane报错：{}", e.getMessage());
        }
    }

    @Override
    public void unTransCancelSurveilListBySurveilListCode(String surveilListCode){

        LOGGER.debug("删除布控单缓存事务外的门面方法，布控单号：{}，为了避免事务问题，等待1分钟查询布控单是否已删除，如果发现有则跳出等待", surveilListCode);

        int times = 60;
        while (times > 0) {
            LOGGER.debug("等待第{}次，布控单缓存数量：{}", 60-times+1, surveilListInfoMap.size());
            if(cancelSurveilListBySurveilListCode(surveilListCode)){
                LOGGER.debug("内存中已删除，跳出等待");
                break;
            }
            waitForSleep(1000) ;
            times--;
        }
    }

    @Override
    public boolean cancelSurveilListBySurveilListCode(String surveilListCode) {

        LOGGER.debug("删除布控单：" + surveilListCode) ;
        LOGGER.debug("删除前布控单数量：" + surveilListInfoMap.size()) ;

        SurveilListInfo info = this.getSurveilListInfoByNotOutOfDateByNum(surveilListCode);

        if(info!=null){
            clueDeployService.deploy(info, SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_CANCEL);
        }

        this.loadAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate();

        if(this.getSurveilListInfoByNotOutOfDateByNum(surveilListCode)!=null){
            LOGGER.debug("内存中仍然存在：{}", this.getSurveilListInfoByNotOutOfDateByNum(surveilListCode)) ;
            return false ;
        }

        LOGGER.debug("删除后布控单数量：" + surveilListInfoMap.size()) ;

        return true ;
    }

    @Override
    public List<SurveilListInfo> getSurveilListInfosByNotOutOfDate() {
        Long now = new Date().getTime() ;
        List<SurveilListInfo> list = new ArrayList<SurveilListInfo>() ;
        for (Map.Entry<String, SurveilListInfo> entry : surveilListInfoMap.entrySet()) {
            SurveilListInfo info = entry.getValue() ;
            if(info.getStartTime()<=now && info.getEndTime()>=now){
                list.add(info);
            }
        }
        return list;
    }

    @Override
    public SurveilListInfo getSurveilListInfoByNotOutOfDateByNum(String num) {
        Long now = new Date().getTime() ;
        SurveilListInfo info = surveilListInfoMap.get(num) ;
        if(info!=null && info.getStartTime()<=now && info.getEndTime()>=now){
            return info ;
        }
        return null;
    }

    @Override
    public List<SurveilListInfo> getSurveilListInfoByNotOutOfDateByIdCardNo(String idCardNo) {
        Long now = new Date().getTime() ;
        List<SurveilListInfo> list = new ArrayList<SurveilListInfo>() ;
        for(Map.Entry<String, SurveilListInfo> entry : surveilListInfoMap.entrySet()){
            SurveilListInfo info = entry.getValue() ;
            if(info.getStartTime()<=now && info.getEndTime()>=now && info.getIdCardNo().equals(idCardNo)){
                list.add(info);
            }
        }

        return list ;
    }

    @Override
    public List<SurveilListInfo> getSurveilListInfoByNotOutOfDateByMac(String mac) {
        Long now = new Date().getTime() ;
        List<SurveilListInfo> list = new ArrayList<SurveilListInfo>() ;
        for(Map.Entry<String, SurveilListInfo> entry : surveilListInfoMap.entrySet()){
            SurveilListInfo info = entry.getValue() ;
            if(!(info.getStartTime()<=now && info.getEndTime()>=now)){
                continue ;
            }

            if(determineMac(info, mac)){
                list.add(info);
            }
        }
        return list;
    }

    private boolean determineMac(SurveilListInfo info, String mac){
        for(SurveilListMobileInfo mInfo:info.getSurveilListMobileInfos()){
            if(mInfo.getMac().equals(mac)){
                return true ;
            }
        }
        return false ;
    }

    @Override
    public List<SurveilListInfo> getSurveilListInfoByNotOutOfDateByPhone(String phone) {
        Long now = new Date().getTime() ;
        List<SurveilListInfo> list = new ArrayList<SurveilListInfo>() ;
        for(Map.Entry<String, SurveilListInfo> entry : surveilListInfoMap.entrySet()){
            SurveilListInfo info = entry.getValue() ;
            if(!(info.getStartTime()<=now && info.getEndTime()>=now)){
                continue ;
            }

            if(determinePhone(info, phone)){
                list.add(info);
            }
        }
        return list;
    }

    private boolean determinePhone(SurveilListInfo info, String phone){
        for(SurveilListMobileInfo mInfo:info.getSurveilListMobileInfos()){
            if(mInfo.getPhone().equals(phone)){
                return true ;
            }
        }
        return false ;
    }
}
