/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月23日 上午11:02:05
 */
package com.taiji.pubsec.szpt.bigdata.wififilerecv.fileprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.szpt.bigdata.kafka.utils.SerializeUtils;
import com.taiji.pubsec.szpt.bigdata.wififilerecv.classfier.EquipmentinfoFileClassfier;
import com.taiji.pubsec.szpt.bigdata.wififilerecv.filereceive.FtpScanner;

/**
 * 侦听jsm消息队列，对下载的终端设备数据文件进行解析，将数据内容发送到kafka中，并发送文件删除信息到jms队列中
 * 
 * @author yucy
 *
 */
public class EquipmentFileProcessor extends AbstractMsgListener {
	private static final Logger logger = LoggerFactory.getLogger(EquipmentFileProcessor.class);

	private String equipmentTopic = "terminalgatherequipment";
	
	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.bigdata.wififilerecv.fileprocess.AbstractMsgListener#doProcess(java.lang.String)
	 */
	@Override
	void doProcess(String filename) {
		logger.debug("开始处理文件{}...", filename);
		ZipFile zip = null;
		BufferedReader br = null;

		try {
			zip = new ZipFile(workfolder + File.separator + FtpScanner.DOWNLOAD_DIR + File.separator + filename);

			Enumeration<? extends ZipEntry> entries = zip.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (entry.getName().contains(
						EquipmentinfoFileClassfier.SURPPORTED_FILENAME)) {
					long size = entry.getSize();
					if (size > 0) {
						br = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)));
						String line;
						while ((line = br.readLine()) != null) {
//							byte[] data = SerializeUtils.serialize(line);
							kafkaProducer.sendData(equipmentTopic, line.getBytes());
						}
					}
					break;
				}
			}

		} catch (IOException e) {
			logger.error("IOException", e);
		} finally {
			if (null != br){
				try {
					br.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
			}
			if (null != zip) {
				try {
					zip.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
			}
		}
		logger.debug("文件{}处理完成。", filename);
	}

	public void setEquipmentTopic(String equipmentTopic) {
		this.equipmentTopic = equipmentTopic;
	}

}
