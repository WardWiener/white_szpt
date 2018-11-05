/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月22日 上午11:57:56
 */
package com.taiji.pubsec.szpt.dpp.wififilerecv.filereceive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yucy
 * 
 */
public class FtpScanner implements FileScanner {
	private static final Logger logger = LoggerFactory
			.getLogger(FtpScanner.class);

	public static final String DOWNLOAD_DIR = "download";
	public static final String OFFSET_FILE = "offset";
	private static final String ENCODE = "UTF-8";
	public static final String FILENAME_SEPARATOR = "-";

	// 扫描的ftp上的文件夹
	private String ftpfolder;
	// 本地工作目录
	private String workfolder;
	private long timeout = 10000;
	private int retry = 3;

	private FtpClientHolder ftpClientHolder;
	private Integer offsettime;

	private static ExecutorService threadPool = Executors.newSingleThreadExecutor();
	
	private FTPClient ftpclient;
	
	public void init() {
		File workdir = new File(workfolder);
		if (!workdir.exists()){
			createDir(workfolder);
		}
		File offset = new File(workfolder + File.separator + OFFSET_FILE);
		if (!offset.exists()) {
			try {
				offset.createNewFile();
				writeOffset(-1);
			} catch (IOException e) {
				logger.error("创建文件失败。", e);
			}
		}
		
		Integer recordvalue = readOffset();
		if(null!=recordvalue){
			offsettime = recordvalue;
		}else{
			logger.error("读取{}文件失败，程序退出！", OFFSET_FILE);
			System.exit(0);
		}
		
		File downdir = new File(workfolder + File.separator + DOWNLOAD_DIR);
		if (!downdir.exists()){
			createDir(workfolder + File.separator + DOWNLOAD_DIR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taiji.pubsec.szpt.bigdata.wififilerecv.filereceive.FileScanner#scan()
	 */
	@Override
	public void scan() {
		long start = System.currentTimeMillis();
		
		logger.debug("准备扫描FTP目录{}...", ftpfolder);
		ftpclient = ftpClientHolder.get();
		if (null == ftpclient) {
			logger.error("FTPClient对象为空，扫描退出。");
			return;
		}
		
		try {
//			ftp.changeWorkingDirectory(ftpfolder);
//			FTPListParseEngine engine = ftpclient.initiateListParsing("com.taiji.pubsec.wifi", directory);
			FTPFile[] files = ftpclient.listFiles(ftpfolder, new FTPFileFilter() {
				
				@Override
				public boolean accept(FTPFile ftpfile) {
					String filename = ftpfile.getName();
					if(ftpfile.isFile() && filename.endsWith(".zip") && ftpfile.getSize()>0 && checkfilename(filename))
						return true;
					return false;
				}
			});
			
			logger.trace("FTP服务器上共有{}个wifi文件。", files.length);
			List<String> filenames = select(files);
			if (filenames.size() < 1) {
				logger.debug("没有要处理的文件.");
				return;
			}

			// 获得需要处理的文件名称，最新的文件排在前面
			String[] fnames = filenames.toArray(new String[filenames.size()]);
			logger.debug("本次要处理{}个文件。", fnames.length);
			for (int i = fnames.length - 1; i > -1; i--) {
				long tstart = System.currentTimeMillis();
				File file = download(fnames[i]);
				long donwend = System.currentTimeMillis();
				logger.debug("下载{}共耗时：{}。", fnames[i], translatetime(donwend-tstart));
				if(null != file){
					operateFileOnFTP(file.getName(), operate);
					//以下是将处理完的offsettime记录到文件中
					//取出文件名中的时间
					String[] arraynames = fnames[i].split(FILENAME_SEPARATOR);
					Integer time = Integer.valueOf(arraynames[4]);
					writeOffset(time);
				}else{
					logger.error("文件{}下载失败！", fnames[i]);
				}
				long tend = System.currentTimeMillis();
				logger.debug("文件{}处理共耗时：{}。", fnames[i], translatetime(tend-tstart));
			}

		} catch (IOException e) {
			logger.error("IOException", e);
			ftpClientHolder.destroy();
		}

		long end = System.currentTimeMillis();
		logger.debug("本次扫描处理结束，共耗时：{}。", translatetime(end-start));
	}

	/*
	 * 在重试次数之前下载指定文件，直到下载成功。对下载的文件判断是否为空，为空则删除
	 */
	private File download(String fname) {
		File file = null;
		for(int i=0; i<retry; i++){
			logger.debug("下载文件{}...第{}次", fname, i+1);
			ftpclient = ftpClientHolder.get();
			file = doDownload(fname, i + 1);
			if(null!=file && file.exists() && file.length()>0 ){
				return file;
			}else{
				while(file.exists()){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					logger.debug("下载的文件为空，删除{}。", fname);
					file.delete();
				}
			}
		}
		return null;
	}
	
	/**
	 * 开启一个线程下载文件并等待其下载完成，在超时时间到之后退出等待
	 * @param fname
	 * @param retryNum 当前重试的次数
	 * @return
	 */
	private File doDownload(String fname, int retryNum){
		File file = new File(workfolder + File.separator + DOWNLOAD_DIR
				+ File.separator + fname);
		Future<File> future = threadPool.submit( new DownloadCall(file) );
		try {
			return future.get(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (ExecutionException e) {
			logger.error("ExecutionException", e);
		} catch (TimeoutException e) {
			//关闭ftp连接，由于ftp连接仍然占有文件的句柄，文件无法删除
			//因为线程的超时时间比ftp连接的超时时间短
			ftpClientHolder.destroy();
			if (retryNum == retry) {
				//防止打印日志造成系统重启，空文件无法删除
				while(file.exists()){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					logger.debug("下载的文件为空，删除{}。", fname);
					file.delete();
				}
				logger.error(" CODE_RESTART_@_" + getPid() + "_@_ 下载超时。(" + fname + ")", e);
				//等待重启
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			else {
				logger.error("下载超时。(" + fname + ")", e);
			}
		}
		
		
		return file;
	}
	
	private String getPid(){
		String name = ManagementFactory.getRuntimeMXBean().getName();
		String pid = name.split("@")[0];
		return pid;
	}

	/*
	 * 从FTP文件中选取出本次需要处理的文件名，按时间倒序排列
	 */
	private List<String> select(FTPFile[] files) {
		Map<Integer, ArrayList<String>> timename = new HashMap<Integer, ArrayList<String>>();
		for (FTPFile file : files) {
			String fname = file.getName();
			// 取出文件名中的时间
			String[] arraynames = fname.split(FILENAME_SEPARATOR);
			if(arraynames.length!=6){
				continue;
			}

			Integer time = Integer.valueOf(arraynames[4]);
			
			ArrayList<String> fnames = timename.get(time);
			if (null == fnames) {
				fnames = new ArrayList<>();
				timename.put(time, fnames);
			}
			fnames.add(fname);
		}
		// 根据时间进行排序
		Set<Integer> sortset = new TreeSet<Integer>();
		sortset.addAll(timename.keySet());

		List<String> selected = new ArrayList<>();
		Integer[] array = sortset.toArray(new Integer[sortset.size()]);
		// 从最新的时间开始遍历
		for (int i = array.length - 1; i > -1; i--) {
			if (array[i] <= offsettime) {
				// 如果时间小于等于记录的上次处理的最后一个文件的时间，则退出遍历
				break;
			}
			//按照时间降序
			selected.addAll(timename.get(array[i]));
		}

		return selected;
	}
	
	private void writeOffset(Integer offset){
		FileWriter fw = null;
		try {
			fw = new FileWriter(workfolder + File.separator + OFFSET_FILE);
	        String s = String.valueOf(offset);
	        fw.write(s,0,s.length());
	        logger.trace("写入offset值：{}", s);
		} catch (IOException e) {
			logger.error("写入文件错误。", e);
		}finally{
			if(null!=fw){
				try {
					fw.flush();
				} catch (IOException e) {
					logger.error("写入文件错误。", e);
				}
			}
		}
	}

	private Integer readOffset(){
		BufferedReader bufferedReader = null;
		InputStreamReader read = null;
		try {
			read = new InputStreamReader(new FileInputStream(workfolder + File.separator + OFFSET_FILE), ENCODE);// 考虑到编码格式
			bufferedReader = new BufferedReader(read);
			String lineTxt = bufferedReader.readLine();
			Integer r = Integer.valueOf(lineTxt);
			logger.info("读取到{}的值为{}", OFFSET_FILE, r);
			return r;
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException", e);
		} catch (IOException e) {
			logger.error("读取文件错误。", e);
		}finally{
			if(null != bufferedReader){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
			if(null != read){
				try {
					read.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}

		return null;
	}
	
	private boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            logger.error("创建目录{}失败，目录已经存在", destDirName);  
            return false;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
        	logger.trace("创建目录{}", destDirName);  
            return true;  
        } else {  
        	logger.error("创建目录{}失败！", destDirName);  
            return false;  
        }  
    }

	private boolean checkfilename(String filename){
		String[] arraynames = filename.split("-");
		if(arraynames.length == 6){
			return true;
		}
		return false;
	}

	private String translatetime(long interv){
		long second = 1000;
		long minute = 60 * second;
		long hour = 60 * minute;
		long h =  interv/hour;
		long m =  (interv - h * hour) / minute;
		long s = (interv - h * hour - m * minute) / second;
		long ms = interv - h * hour - m * minute - s * second;
		return h + "小时" + m + "分钟" + s + "秒" + ms + "毫秒";
	}
	
	public void setFtpfolder(String ftpfolder) {
		this.ftpfolder = ftpfolder;
	}

	public void setWorkfolder(String workfolder) {
		this.workfolder = workfolder;
	}

	public void setOffsettime(Integer offsettime) {
		this.offsettime = offsettime;
	}

	public void setFtpClientHolder(FtpClientHolder ftpClientHolder) {
		this.ftpClientHolder = ftpClientHolder;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	private class DownloadCall implements Callable<File>{

		File file;
		
		DownloadCall(File file) {
			super();
			this.file = file;
		}

		@Override
		public File call() throws Exception {
			File tempFile = new File(file.getAbsolutePath() + ".temp");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(tempFile);
				ftpclient.changeWorkingDirectory(ftpfolder);
				boolean flag = ftpclient.retrieveFile(file.getName(), fos);
				if(flag){
					fos.close();
					tempFile.renameTo(file) ;
				}
			} catch (IOException e) {
				ftpClientHolder.destroy();
				logger.error(file.getName() + " : IOException", e);
			} finally {
				if (null != fos) {
					try {
						fos.close();
					} catch (IOException e) {
						logger.error("IOException", e);
					}
				}
			}
			return file;
		}
		
	}
	
	private String operate;
	
	private String backupdir = "bak";
	
	private static final String OPERATE_DELETE = "delete";
	private static final String OPERATE_MOVE = "move";
	
	private void operateFileOnFTP(String fname, String operate){
		for(int i=0; i<retry; i++){
			logger.trace("移动文件{}...第{}次", fname, i+1);
			ftpclient = ftpClientHolder.get();
			Boolean success = doOperate(fname, operate);
			if( success ){
				logger.trace("操作({})文件{}成功", operate, fname);
				return;
			}
		}
		logger.error("操作({})文件{}成功", operate, fname);
	}
	
	private Boolean doOperate(String fname, String operate){
		Future<Boolean> future = threadPool.submit( new OperateFileOnFTPCall(fname, operate) );
		
		try {
			return future.get(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (ExecutionException e) {
			logger.error("ExecutionException", e);
		} catch (TimeoutException e) {
			//超时关闭ftp连接 释放文件和socket资源
			ftpClientHolder.destroy();
			logger.error("移动文件超时。", e);
		}
		
		return false;
	}
	
	private class OperateFileOnFTPCall implements Callable<Boolean>{

		String fname;
		String operate;
		
		OperateFileOnFTPCall(String fname, String operate) {
			super();
			this.fname = fname;
			this.operate = operate;
		}

		@Override
		public Boolean call() throws Exception {
			String old = ftpfolder + File.separator + fname;
			String ne = backupdir + File.separator + fname;
			try {
				
				Boolean result = false;
				if(OPERATE_DELETE.equals(operate)){
					logger.trace("删除文件{}。", fname);
					result = ftpclient.deleteFile(fname); 
				}
				if(OPERATE_MOVE.equals(operate)){
					logger.trace("移动文件{}到FTP目录{}", fname, backupdir);
					result = ftpclient.rename(old, ne);
				}
				return result;
			} catch (IOException e) {
				ftpClientHolder.destroy();
				logger.error("在FTP上操作( " + operate + " )文件错误。", e);
			}
			return false;
		}
		
	}
	
	public void setBackupdir(String backupdir) {
		this.backupdir = backupdir;
	}


	public void setOperate(String operate) {
		this.operate = operate;
	}

}
