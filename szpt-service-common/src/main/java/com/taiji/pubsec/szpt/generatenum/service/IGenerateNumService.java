package com.taiji.pubsec.szpt.generatenum.service;


import com.taiji.pubsec.persistence.service.BaseService;

/**
 * 生成编号
 * 规则：
 * 1、根据type取到对应的记录
 * 2、如果isYear=0按年，则走步骤3,如果isYear=1不按年,则走4
 * 3、判定当前所在年份是否是currentYear，如果不是则更新currentYear为当前年份，并将num置为2，
 * 	并返回prefixStr+String.format(formatStr, 1)，否则继续步骤4
 * 4、取出num，并返回prefixStr+String.format(formatStr, num)，将num+1更新到数据库结束
 * @author genganpeng
 *
 */
public interface IGenerateNumService extends BaseService<com.taiji.pubsec.szpt.generatenum.model.GenerateNum, String>{
	/**
	 * 根据type按照既定的规则生成编号，同时系统会随之更新，不保证所有号码都是连续的
	 * @param type 分类
	 * @return 前缀+格式化的编号
	 */
	String acquireNum(String type);
	
	/**
	 * 根据type按照既定的规则查询下一个编号，这个步骤只是取下一个编号，真正生成编号是在保存数据的时候，会出现显示和保存后显示的编号不一致的状态
	 * @param type 分类
	 * @return 前缀+格式化的编号
	 */
	String queryNextNum(String type);
	
}
