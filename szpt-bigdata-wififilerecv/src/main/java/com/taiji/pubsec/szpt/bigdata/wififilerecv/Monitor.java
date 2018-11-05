/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月25日 下午2:38:22
 */
package com.taiji.pubsec.szpt.bigdata.wififilerecv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yucy
 * 
 */
public class Monitor {

	private static String homedir = System.getenv("WIFIFTP_HOME");
	private static String RUNBAT = "run.bat";
	private static String path = homedir + File.separator + "bin" + File.separator + RUNBAT;
	private static String execmd = "cmd /c start " + path;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("home dir : " + homedir);
		System.out.println("executable file path : " + path);
		System.out.println("execute execmd : " + execmd);
		
		startProc();

		final String logpath = homedir + File.separator + "logs" + File.separator + "app-debug.log";
		final File logfile = new File(logpath);
		
		new Thread(){
			public void run(){
				long start = System.currentTimeMillis();
				long end = System.currentTimeMillis();
				while(true){
					try {
						String pid = checkLog( new FileInputStream(logfile) );
						if(null != pid){
							//根据关键词获取pid 因为这里管理java的pid无法关闭运行此pid的cmd
							String cmdPid = existProcByKeyWord(RUNBAT);
							System.out.println("killing the program(pid=" + pid + ")...");
							System.out.println("killing the program(cmdPid=" + cmdPid + ")...");
							while(!killProc(pid)){
								sleep(3000);
							}
							while(!killProc(cmdPid)){
								sleep(3000);
							}
							end = System.currentTimeMillis();
							System.out.println("run time : " + translatetime(end - start));
							sleep(2000);
							
							while(!logfile.renameTo(new File(logpath + String.valueOf(new Date().getTime())))){
								System.out.println("deleteing the log... : false");
								sleep(3000);
							}
							System.out.println("deleteing the log... : true");
							
							System.out.println("starting the program...");
							start = System.currentTimeMillis();
							startProc();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		InputStreamReader is =new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
       
        String input = "";
        try {
            while((input = br.readLine()) != null){
                System.out.println("输入：" + input);
                if("quit".equals(input)){
                    break;
                }
            }
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
        
        System.out.println("程序退出。");
	}

	private static void startProc(){
		try {
			Process process = Runtime.getRuntime().exec(execmd);
			// 读取错误流和正常流的输入，否则会阻塞，不能正确获得结果
//			InputStream stderr = process.getErrorStream();
			int progEnd = process.waitFor();
			System.out.println("end : " + progEnd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static boolean killProc(String pid){
		try {
			Runtime.getRuntime().exec("Taskkill /F /PID " + pid);
			return !existProc(pid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private static String existProcByKeyWord(String keyWord){
		String s = null;
		BufferedReader bufferedReader = null;
		try {
			Process process = Runtime.getRuntime().exec("tasklist /V");
			InputStream std = process.getInputStream();
			bufferedReader = new BufferedReader( new InputStreamReader(std) );
			while ((s = bufferedReader.readLine()) != null) {
				//防止有相同前缀的任务存在
				if(s.contains(keyWord)){
					//将多个连续空格变成一个
					s = s.replaceAll("\\s{1,}", " ");
					String[] lineArray = s.split(" ");
		            String pid  = lineArray[1];
					return pid;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null!=bufferedReader){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	private static boolean existProc(String pid){
		String s = null;
		BufferedReader bufferedReader = null;
		try {
			Process process = Runtime.getRuntime().exec("tasklist");
			InputStream std = process.getInputStream();
			bufferedReader = new BufferedReader( new InputStreamReader(std) );
			while ((s = bufferedReader.readLine()) != null) {
				//防止有相同前缀和后缀的任务存在
				if(s.contains(" " + pid + " ")){
					System.out.println("program(pid=" + pid + ") still exist!");
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null!=bufferedReader){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
  	private static String checkLog(final InputStream inputStream) {
		String s = null;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8") );
			while ((s = bufferedReader.readLine()) != null) {
//				System.out.println(s);
				if(s.contains("CODE_RESTART")){
					String pid = getTargetPid(s);
					System.out.println("program(pid=" + pid + ") need restart!");
					return pid;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=bufferedReader){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
  	
  	private static String getTargetPid(String s){
  		String flag = "CODE_RESTART_@_";

		int start = s.indexOf(flag);
		String substr = s.substring(start + flag.length());
		String[] ts = substr.split("_@_");
		return ts[0];
  	}

	private static String translatetime(long interv){
		long second = 1000;
		long minute = 60 * second;
		long hour = 60 * minute;
		long h =  interv/hour;
		long m =  (interv - h * hour) / minute;
		long s = (interv - h * hour - m * minute) / second;
		long ms = interv - h * hour - m * minute - s * second;
		return h + "小时" + m + "分钟" + s + "秒" + ms + "毫秒";
	}

}
