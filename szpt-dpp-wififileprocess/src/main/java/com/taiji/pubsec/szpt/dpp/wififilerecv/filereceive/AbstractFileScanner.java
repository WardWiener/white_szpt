/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年10月30日 下午2:03:36
 */
package com.taiji.pubsec.szpt.dpp.wififilerecv.filereceive;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有文件扫描器的抽象类，封装了文件记录服务
 * 
 * @author yucy
 *
 */
public abstract class AbstractFileScanner implements FileScanner {

	private List<Classfier> classfiers = new ArrayList<Classfier>();

    //TODO 文件记录服务

	protected boolean doClassfy(File file){
        for(Classfier classfier : getClassfiers()){
            if(classfier.classfy(file)){
            	//只要一个分类器分类成功就不再继续分类
            	return true;
            }
        }
        return false;
    }
	
    public List<Classfier> getClassfiers() {
        return classfiers;
    }

    public void setClassfiers(List<Classfier> classfiers) {
        this.classfiers = classfiers;
    }

}
