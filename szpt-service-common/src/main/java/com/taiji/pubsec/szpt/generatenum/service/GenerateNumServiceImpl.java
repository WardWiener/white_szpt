package com.taiji.pubsec.szpt.generatenum.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.service.AbstractBaseService;
import com.taiji.pubsec.szpt.generatenum.model.GenerateNum;

@Service("generateNumService")
public class GenerateNumServiceImpl extends AbstractBaseService<GenerateNum, String> implements IGenerateNumService {

	private static final int NUM = 2;
	private static final int ADDNUM = 1;
	
	@Autowired
	public GenerateNumServiceImpl(Dao<com.taiji.pubsec.szpt.generatenum.model.GenerateNum, String> jpaDao) {
		setDao(jpaDao);
	}

	@Override
	public String acquireNum(String type) {
		synchronized (type) {
			if (StringUtils.isEmpty(type)) {
				throw new IllegalArgumentException("分类不可以为空");
			}
			String hql = " from GenerateNum where type = ?";
			Object[] keys = new Object[] { type };
			GenerateNum generateNum = this.findByKey(hql, keys);
			if (generateNum == null) {
				generateNum = saveGenerateNum(type);
			}
			int isYear = generateNum.getIsYear();
			String prefixStr = generateNum.getPrefixStr();
			if (StringUtils.isEmpty(prefixStr))
				prefixStr = "";
			String formatStr = generateNum.getFormatStr();
			if (StringUtils.isEmpty(formatStr)) {
				formatStr = "";
			}
			String currentYear = generateNum.getCurrentYear();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String nowYear = sdf.format(new Date());
			boolean isSameCurrentYear = false;
			if (currentYear.equals(nowYear)) {
				isSameCurrentYear = true;
			}
			if (isYear == 0 && !isSameCurrentYear) {
				generateNum.setCurrentYear(nowYear);
				generateNum.setNum(NUM);
				this.update(generateNum);
				StringBuilder sb = new StringBuilder("");
				return sb.append(prefixStr).append(String.format(formatStr, ADDNUM)).toString();
			} 
			if (isYear == 1 || isSameCurrentYear) {
				int num = generateNum.getNum();
				generateNum.setNum(num + ADDNUM);
				this.update(generateNum);
				StringBuilder sb = new StringBuilder("");
				return sb.append(prefixStr).append(String.format(formatStr, num)).toString();
			} 
			throw new IllegalArgumentException("按照年份生成编码依据不存在");
		}
	}

	@Override
	public String queryNextNum(String type) {
		if (StringUtils.isEmpty(type)) {
			throw new IllegalArgumentException("分类不可以为空");
		}
		String hql = " from GenerateNum where type = ?";
		Object[] keys = new Object[] { type };
		GenerateNum generateNum = this.findByKey(hql, keys);
		if (generateNum == null) {
			generateNum = saveGenerateNum(type);
		}
		int isYear = generateNum.getIsYear();
		String prefixStr = generateNum.getPrefixStr();
		if (StringUtils.isEmpty(prefixStr))
			prefixStr = "";
		String formatStr = generateNum.getFormatStr();
		if (StringUtils.isEmpty(formatStr)) {
			formatStr = "";
		}
		String currentYear = generateNum.getCurrentYear();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String nowYear = sdf.format(new Date());
		boolean isSameCurrentYear = false;
		if (currentYear.equals(nowYear)) {
			isSameCurrentYear = true;
		}
		if (isYear == 0 && !isSameCurrentYear) {
			StringBuilder sb = new StringBuilder("");
			return sb.append(prefixStr).append(String.format(formatStr, ADDNUM)).toString();
		} 
		if (isYear == 1 || isSameCurrentYear) {
			int num = generateNum.getNum();
			StringBuilder sb = new StringBuilder("");
			return sb.append(prefixStr).append(String.format(formatStr, num)).toString();
		} 
		throw new IllegalArgumentException("按照年份生成编码依据不存在");
	}
	
	private GenerateNum saveGenerateNum(String type) {
		GenerateNum num = new GenerateNum();
		num.setType(type);
		num.setName(type);
		num.setNum(ADDNUM);
		num.setFormatStr("%d");
		//按年
		num.setIsYear(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String nowYear = sdf.format(new Date());
		num.setCurrentYear(nowYear);
		
		this.save(num);
		return num;
	}
}
