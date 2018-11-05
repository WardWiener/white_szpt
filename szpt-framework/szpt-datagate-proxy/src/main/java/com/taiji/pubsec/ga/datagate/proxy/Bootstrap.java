/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月29日 下午4:50:53
 */
package com.taiji.pubsec.ga.datagate.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author yucy
 *
 */
public class Bootstrap {
private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    
    private static String configpath = "classes/appContext-*.xml"; //classpath:appContext-amq-server.xml
    
	private Bootstrap() {
	}

	/**
     * 接受一个参数，指定spring文件的位置，如：conf/appContext.xml或者classpath:appContext.xml
     * @param args 
     */
    public static void main(String[] args) {
        if(args.length > 0 && null != args[0] && !"".equals(args[0])){
            logger.debug("使用配置：" + args[0] + " 。");
            configpath = args[0];
        }else{
            logger.debug("使用配置：" + configpath + " 。");
        }
        
        //装载Spring容器
        logger.debug("启动spring容器...");
        AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(configpath);
        logger.debug("spring容器启动完毕。");
        
        ctx.registerShutdownHook();
        
        InputStreamReader is =new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
       
        String input = "";
        try {
            while((input = br.readLine()) != null){
                logger.info("输入：" + input);
                if("quit".equals(input)){
                    ctx.close();
                    break;
                }
            }
        }
        catch(IOException e) {
        	logger.error("控制台输入错误", e);
        }
        
        logger.info("程序退出。");
    }
}
