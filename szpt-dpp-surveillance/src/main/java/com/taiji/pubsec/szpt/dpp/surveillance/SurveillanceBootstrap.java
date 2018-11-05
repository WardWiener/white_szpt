package com.taiji.pubsec.szpt.dpp.surveillance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.taiji.pubsec.common.tools.system.ProcessPidTools;
/**
 * @author yucy
 *
 */
public class SurveillanceBootstrap {

	private static final Logger LOGGER = LoggerFactory.getLogger(SurveillanceBootstrap.class) ;
    
    private static String[] configpaths = new String[]{"classes/applicationContext*.xml"} ;
    
	private SurveillanceBootstrap() {
	}

	/**
     * 接受一个参数，指定spring文件的位置，如：conf/appContext.xml或者classpath:appContext.xml
     * @param args 
     */
    public static void main(String[] args) {
    	
       	generatePid();
    	
        if(args.length > 0){
        	LOGGER.debug("使用配置：" + args[0] + " 。");
            configpaths = args;
        }else{
        	LOGGER.debug("使用配置：" + configpaths + " 。");
        }
        
        //装载Spring容器
        LOGGER.debug("启动spring容器...");
        AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(configpaths);
        LOGGER.debug("spring容器启动完毕。");
        
        ctx.registerShutdownHook();
        
        InputStreamReader is =new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
       
        String input = "";
        try {
            while((input = br.readLine()) != null){
            	LOGGER.info("输入：" + input);
                if("quit".equals(input)){
                    ctx.close();
                    break;
                }
            }
        }
        catch(IOException e) {
        	LOGGER.error("控制台输入错误", e);
        }
        
        LOGGER.info("程序退出。");
    }
    
    private static void generatePid(){
    	try{
    		String runClassPath = SurveillanceBootstrap.class.getClassLoader().getResource("").getPath() ;
    		
    		if(runClassPath.lastIndexOf("classes") > 0){
    			runClassPath = runClassPath.substring(0, runClassPath.lastIndexOf("classes")-1) ;
    			
    			if(!runClassPath.endsWith("/")){
    				runClassPath += "/" ;
    			}
    		}
    		
    		runClassPath += "run_pid" ;
    		
    		ProcessPidTools.generatePidLock(ProcessPidTools.getPid(), runClassPath) ;
    	}catch(Exception e){
    		LOGGER.error(e.getMessage());
    		System.exit(0);
    	}
    }

}
