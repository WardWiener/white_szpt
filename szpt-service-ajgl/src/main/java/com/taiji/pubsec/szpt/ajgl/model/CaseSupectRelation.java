package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalPersonService;

/**
 * BPIP_CASE涉案嫌疑人人员案件关联表
 * @author wangfx
 * @version 1.0
 * @created 10-8月-2016 15:03:45
 */
@Entity
@Table(name = "t_ajgl_aj_xyrgx")
public class CaseSupectRelation {

	/**
	 * 编号
	 */
	@Id
	@Column(length = 36)
	private String id;
	/**
	 * 嫌疑人类型
	 */
	@Column(length = 10)
	private String suspectType;
	/**
	 * 案件角色
	 */
	@Column(length = 10)
	private String crimeRole;
	/**
	 * 违法事实及依据(len4000)
	 */
	@Column(length = 4000)
	private String criRecord;
	/**
	 * 嫌疑依据
	 */
	@Column(length = 10)
	private String suspiciongist;
	/**
	 * 处理方式
	 */
	@Column(length = 15)
	private String approach;
	/**
	 * 人员状态
	 */
	@Column(length = 10)
	private String personState;
	/**
	 * 抓获日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateofCapture;
	/**
	 * 抓获经过
	 */
	@Column(length = 4000)
	private String captureProcess;
	/**
	 * 刑拘批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date xjApprovalTime;
	/**
	 * 刑拘批准人
	 */
	@Column(length = 16)
	private String xjApprover;
	/**
	 * 是否批准刑拘
	 */
	@Column(length = 10)
	private String isxjApproval;
	/**
	 * 刑拘时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date detentionTime;
	/**
	 * 拘留时限(len22)
	 */
	@Column(length = 22, nullable = true)
	private Integer detentionLength;
	/**
	 * 拘留地点
	 */
	@Column(length = 90)
	private String detentionAddress;
	/**
	 * 延长拘留期限批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date ycjlApprovalTime;
	/**
	 * 延长拘留期限批准人
	 */
	@Column(length = 16)
	private String ycjlApprover;
	/**
	 * 是否批准延长拘留期限
	 */
	@Column(length = 10)
	private String isycjlApproval;
	/**
	 * 延长拘留期限时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date ycDetentiomTime;
	/**
	 * 取保候审批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date qbhsApprovalTime;
	/**
	 * 取保候审批准人
	 */
	@Column(length = 16)
	private String qbhsApprover;
	/**
	 * 是否批准取保候审
	 */
	@Column(length = 10)
	private String isqbhsApproval;
	/**
	 * 取保候审执行日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date bailTime;
	/**
	 * 监视居住批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date jsjzApprovalTime;
	/**
	 * 监视居住批准人
	 */
	@Column(length = 16)
	private String jsjzApprover;
	/**
	 * 是否批准监视居住
	 */
	@Column(length = 10)
	private String isjsjzApproval;
	/**
	 * 监视居住执行日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date surveillanceTime;
	/**
	 * 报捕时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestarrestTime;
	/**
	 * 批准逮捕时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrestApprovalTime;
	/**
	 * 是否批准逮捕
	 */
	@Column(length = 10)
	private String isArrestApproval;
	/**
	 * 执行逮捕时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrestTime;
	/**
	 * 不批捕原因
	 */
	@Column(length = 900)
	private String arrestRefuse;
	/**
	 * 批准提请逮捕复议时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date drewReviewTime;
	/**
	 * 批准提请逮捕复议人
	 */
	@Column(length = 16)
	private String drewReviewer;
	/**
	 * 是否批准提请逮捕复议
	 */
	@Column(length = 10)
	private String isfyApproval;
	/**
	 * 提请逮捕复议时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrestviewerTime;
	/**
	 * 提请逮捕复核时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrestreviewTime;
	/**
	 * 批准逮捕提请复核人
	 */
	@Column(length = 16)
	private String arrestReviewer;
	/**
	 * 是否批准提请逮捕复核
	 */
	@Column(length = 10)
	private String isfhApproval;
	/**
	 * 是否移送起诉
	 */
	@Column(length = 10)
	private String isysqsApproval;
	/**
	 * 移送起诉时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date prosecutedTime;
	/**
	 * 移送起诉罪名
	 */
	@Column(length = 100)
	private String prosecutedCharge;
	/**
	 * 不起诉原因(len900)
	 */
	@Column(length = 900)
	private String notprosecuteReason;
	/**
	 * 退侦原因(len900)
	 */
	@Column(length = 900)
	private String continueInveReason;
	/**
	 * 一次退侦时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date continueInveTime;
	/**
	 * 补侦完毕时间(第一次)
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date completeInveTimea;
	/**
	 * 补侦完毕时间(第二次)
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date completeInveTimeb;
	/**
	 * 起诉提请复议批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date prosecutionApptime;
	/**
	 * 起诉提请复议批准人
	 */
	@Column(length = 16)
	private String prosecutionApper;
	/**
	 * 是否批准起诉复议
	 */
	@Column(length = 11)
	private String ispzqsApproval;
	/**
	 * 起诉复议时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date prosecuteReviewtime;
	/**
	 * 起诉提请复核批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date qstqReviewApptime;
	/**
	 * 起诉提请复核批准人
	 */
	@Column(length = 16)
	private String qstqReviewApproval;
	/**
	 * 是否批准起诉复核
	 */
	@Column(length = 10)
	private String isqsfhApproval;
	/**
	 * 起诉复核时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date prosecutionReviewTme;
	/**
	 * 其他处理批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date otherApprovalTime;
	/**
	 * 其他处理批准人
	 */
	@Column(length = 16)
	private String otherApprover;
	/**
	 * 是否批准其他处理
	 */
	@Column(length = 10)
	private String isotherApproval;
	/**
	 * 其他处理
	 */
	@Column(length = 10)
	private String otherDeal;
	/**
	 * 劳教、少教时限
	 */
	@Column(length = 280)
	private String detentionLimit;
	/**
	 * 劳教、少教场所
	 */
	@Column(length = 90)
	private String detentionPlace;
	/**
	 * 案件处理种类
	 */
	@Column(length = 11)
	private String caseTreatmentType;
	/**
	 * 行政处罚批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date adminPenaltyApptime;
	/**
	 * 行政处罚批准人
	 */
	@Column(length = 16)
	private String adminPenaltyApper;
	/**
	 * 是否批准行政处罚
	 */
	@Column(length = 10)
	private String isxzcfApproval;
	/**
	 * 是否警告(行政处罚)
	 */
	@Column(length = 10)
	private String iswarningPunish;
	/**
	 * 罚款金额
	 */
	@Column(nullable = true)
	private Double finesNo;
	/**
	 * 行政拘留日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date adminDetentionTime;
	/**
	 * 是否没收违法所得及违法财物
	 */
	@Column(length = 10)
	private String isConfiscation;
	/**
	 * 是否责令停产停业
	 */
	@Column(length = 10)
	private String isorderedcase;
	/**
	 * 是否暂扣吊销证照
	 */
	@Column(length = 10)
	private String isWithheldLicenses;
	/**
	 * 其他
	 */
	@Column(length = 450)
	private String other;
	/**
	 * 行政处罚执行日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date adminPenaltyTime;
	/**
	 * 上网追逃申请时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date pursuitApptime;
	/**
	 * 上网追逃批准人
	 */
	@Column(length = 16)
	private String pursuitApprover;
	/**
	 * 上网追逃批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date pursuitApprovalTime;
	/**
	 * 是否上网追逃
	 */
	@Column(length = 10)
	private String isPursuit;
	/**
	 * 追逃编号
	 */
	@Column(length = 23)
	private String pursuitNo;
	/**
	 * 全省缉控申请时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date trackApptime;
	/**
	 * 全省缉控批准人
	 */
	@Column(length = 16)
	private String trackApper;
	/**
	 * 是否全省缉控
	 */
	@Column(length = 10)
	private String isjkApproval;
	/**
	 * 从本案排除时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date excludeTime;
	/**
	 * 从本案排除原因(len3000)
	 */
	@Column(length = 3000)
	private String excludeReason;
	/**
	 * 是否从本案排除
	 */
	@Column(length = 10)
	private String isexclude;
	/**
	 * 备注(len1500)
	 */
	@Column(length = 1500)
	private String annex;
	/**
	 * 录入人
	 */
	@Column(length = 16)
	private String inputPerson;
	/**
	 * 录入时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputTime;
	/**
	 * 修改人
	 */
	@Column(length = 16)
	private String modifiedPerson;
	/**
	 * 修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTime;
	/**
	 * 入地市库时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date districtTime;
	/**
	 * 入省库时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date provinceTime;
	/**
	 * 删除标识
	 */
	@Column(length = 10)
	private String deleTag;
	@Column(length = 6)
	
	/**
	 * 被抓前在本辖区停留时间
	 */
	private String disputeInterval;
	/**
	 * 在本县落脚点详细地址
	 */
	@Column(length = 450)
	private String footHoldAddress;
	/**
	 * 无效字段
	 */
	@Column(length = 10)
	private String ifUptraffick;
	@Column(length = 10)
	/**
	 * 无效字段
	 */
	private String ifUpgun;
	/**
	 * 无效字段
	 */
	@Column(length = 10)
	private String ifUpblackmail;
	/**
	 * 逮捕复核时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date tqArrestReviewTime;
	/**
	 * 复议后是否批准逮捕
	 */
	@Column(length = 10)
	private String isfypzApproval;
	/**
	 * 无效字段
	 */
	@Column(length = 10)
	private String ifUpmurder;
	/**
	 * 刑拘审核人
	 */
	private String xjReviewed;
	/**
	 * 延长拘留审核人
	 */
	@Column(length = 16)
	private String ycjlReviewed;
	/**
	 * 取保候审审核人
	 */
	@Column(length = 16)
	private String qbhsreviewed;
	/**
	 * 监视居住审核人
	 */
	@Column(length = 16)
	private String jsjzReviewed;
	/**
	 * 批准逮捕审核人
	 */
	@Column(length = 16)
	private String arrestReviewed;
	/**
	 * 不批捕审核人
	 */
	@Column(length = 16)
	private String arrestRefreviewed;
	/**
	 * 移送起诉审核人
	 */
	@Column(length = 16)
	private String prosecutReviewed;
	/**
	 * 退侦审核人
	 */
	@Column(length = 16)
	private String continueReviewed;
	/**
	 * 其他处理审核人
	 */
	@Column(length = 16)
	private String otherReviewed;
	/**
	 * 行政处罚审核人
	 */
	@Column(length = 16)
	private String adminPenalReviewed;
	
	/** 
	 * 强制隔离戒毒审核人
	 */
	@Column(length = 16)
	private String qzgljdshr;
	/** 
	 * 劳动教养审核人
	 */
	@Column(length = 16)
	private String ldjyshr;
	/** 
	 * 强制隔离戒毒批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date detoxifreTime;
	/** 
	 * 劳动教养批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date detentionreTime;
	/**
	 * 强制隔离戒毒审核人
	 */
	@Column(length = 16)
	private String detoxifReviewed;
	/**
	 * 劳动教养审核人
	 */
	@Column(length = 16)
	private String detentionReviewed;
	/**
	 * 批准逮捕批准人
	 */
	@Column(length = 16)
	private String arrestApprover;
	/**
	 * 不批捕批准人
	 */
	@Column(length = 16)
	private String arrestRefApprover;
	/**
	 * 移送起诉批准人
	 */
	@Column(length = 16)
	private String prosecutApproval;
	/**
	 * 退侦批准人
	 */
	@Column(length = 16)
	private String continueApprover;
	/**
	 * 强制隔离戒毒批准人
	 */
	@Column(length = 16)
	private String detoxifApprover;
	/**
	 * 劳动教养批准人
	 */
	@Column(length = 16)
	private String detentionApprover;
	/**
	 * 刑拘办理人
	 */
	@Column(length = 16)
	private String xjTransactor;
	/**
	 * 延长拘留办理人
	 */
	@Column(length = 16)
	private String ycjlTransactor;
	/**
	 * 取保候审办理人
	 */
	@Column(length = 16)
	private String qbhsTransactor;
	/**
	 * 监视居住办理人
	 */
	@Column(length = 16)
	private String jsjzTransactor;
	/**
	 * 批准逮捕办理人
	 */
	@Column(length = 16)
	private String arrestTransactor;
	/**
	 * 不批捕办理人
	 */
	@Column(length = 16)
	private String arrestRefTransactor;
	/**
	 * 提请复议办理人
	 */
	@Column(length = 16)
	private String drewTransactor;
	/**
	 * 提请逮捕复议办理人
	 */
	@Column(length = 16)
	private String arrvTransactor;
	/**
	 * 批捕复核办理人
	 */
	@Column(length = 16)
	private String arreviewTransactor;
	/**
	 * 移送起诉办理人
	 */
	@Column(length = 16)
	private String prosecutTransactor;
	/**
	 * 退侦办理人
	 */
	@Column(length = 16)
	private String contTransactor;
	/**
	 * 起诉复议办理人
	 */
	@Column(length = 16)
	private String prosTransactor;
	/**
	 * 起诉复核办理人
	 */
	@Column(length = 16)
	private String prosecTransactor;
	/**
	 * 其他处理办理人
	 */
	@Column(length = 16)
	private String otherTransactor;
	/**
	 * 强制隔离戒毒办理人
	 */
	@Column(length = 16)
	private String detoxifTransactor;
	/**
	 * 劳动教养办理人
	 */
	@Column(length = 16)
	private String detTransactor;
	/**
	 * 行政处罚办理人
	 */
	@Column(length = 16)
	private String adminTransactor;
	/** 
	 * 报捕办理人
	 */
	@Column(length = 16)
	private String requestTransactor;
	/** 
	 * 报捕批准人
	 */
	@Column(length = 16)
	private String requestApprover;
	/** 
	 * 报捕审核人
	 */
	@Column(length = 16)
	private String requestReviewed;
	/** 
	 * 是否报捕
	 */
	@Column(length = 10)
	private String isRequestApproval;
	/** 
	 * 是否不批捕
	 */
	@Column(length = 10)
	private String isArrestRef;
	/**
	 * 是否一次退侦
	 */
	@Column(length = 10)
	private String isConttran;
	/** 
	 * 起诉提请复议审批人
	 */
	@Column(length = 16)
	private String proseReviewed;
	/** 
	 * 起诉提请复核审批人
	 */
	@Column(length = 16)
	private String qstqReviewed;
	/** 
	 * 是否强制隔离戒毒
	 */
	@Column(length = 10)
	private String isDetoxif;
	/** 
	 * 是否劳动教养
	 */
	@Column(length = 10)
	private String isDetention;
	/** 
	 * 全省缉控办理人
	 */
	@Column(length = 16)
	private String trackactor;
	/** 
	 * 全省缉控审核人
	 */
	@Column(length = 16)
	private String trackReviewed;
	/** 
	 * 上网追讨办理人
	 */
	@Column(length = 16)
	private String pursuitActor;
	/** 
	 * 上网追逃审核人
	 */
	@Column(length = 16)
	private String pursuitReviewed;
	/** 
	 * 提请逮捕审核人
	 */
	@Column(length = 16)
	private String arrestRevieweder;
	/**
	 * 是否二次退侦
	 */
	@Column(length = 10)
	private String isConttran2;
	/**
	 * 复核后是否批捕
	 */
	@Column(length = 10)
	private String isfhpzApproval;
	/**
	 * 是否罚款
	 */
	@Column(length = 10)
	private String isFines;
	/**
	 * 是否行政拘留
	 */
	@Column(length = 10)
	private String isadminDetention;
	/**
	 * 是否行政拘留并罚款
	 */
	@Column(length = 10, nullable = true)
	private String isFinesDetention;
	/**
	 * 社区戒毒办理人
	 */
	@Column(length = 16)
	private String sqjdRansactor;
	/**
	 * 社区戒毒审核人
	 */
	@Column(length = 16)
	private String sqjdReviewer;
	/**
	 * 社区戒毒批准人
	 */
	@Column(length = 16)
	private String sqjdApproved;
	/**
	 * 社区戒毒批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date sqjdApprovalTime;
	/**
	 * 是否社区戒毒
	 */
	@Column(length = 10)
	private String ifsqjd;
	/**
	 * 社区康复办理人
	 */
	@Column(length = 16)
	private String sqkfActor;
	/**
	 * 社区康复审核人
	 */
	@Column(length = 16)
	private String sqkfReviewer;
	/**
	 * 社区康复批准人
	 */
	@Column(length = 16)
	private String sqkfApproval;
	/**
	 * 社区康复批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date sqkfApprovalTime;
	/**
	 * 是否社区康复
	 */
	@Column(length = 16)
	private String issqkf;
	/**
	 * 收容教育办理人
	 */
	@Column(length = 16)
	private String srjyActor;
	/**
	 * 收容教育审核人
	 */
	@Column(length = 16)
	private String srjyReviewer;
	/**
	 * 收容教育批准人
	 */
	@Column(length = 16)
	private String srjyApproved;
	/**
	 * 收容教育批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date srjyApprovalTime;
	/**
	 * 是否收容教育
	 */
	@Column(length = 10)
	private String issrjy;
	/**
	 * 少教办理人
	 */
	@Column(length = 16)
	private String sjepeople;
	/**
	 * 少教审核人
	 */
	@Column(length = 16)
	private String sjAudit;
	/**
	 * 少教批准人
	 */
	@Column(length = 16)
	private String sjApproved;
	/**
	 * 少教批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date sjApprovedTime;
	/**
	 * 是否少教
	 */
	@Column(length = 10)
	private String issj;
	/**
	 * 二次退侦时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date continueInvetimeb;
	/** 
	 * 是否在逃
	 */
	@Column(length = 10)
	private String isonrun;
	/**
	 * 是否收容教养
	 */
	@Column(length = 10)
	private String issrjys;
	/**
	 * 是否自愿接受强制隔离戒毒
	 */
	@Column(length = 10)
	private String iszyDetoxif;
	/**
	 * 收容教养办理人
	 */
	@Column(length = 16)
	private String srjysActor;
	/**
	 * 收容教养批准时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date srjysApprovalTime;
	/**
	 * 收容教养批准人
	 */
	@Column(length = 16)
	private String srjysApproved;
	/**
	 * 收容教养审核人
	 */
	@Column(length = 16)
	private String srjysReviewer;
	/** 
	 * 是否在押
	 */
	@Column(length = 10)
	private String iszaiya;
	/**
	 * 提请批捕时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date toArrestTime;
	/**
	 * 是否提请批捕
	 */
	@Column(length = 10)
	private String istoarrest;
	/** 
	 * 抓获方式
	 */
	@Column(length = 12)
	private String arrestmanner;
	/**
	 * 是否限制出境
	 */
	@Column(length = 10)
	private String isLimitExport;
	/**
	 * 不批准出境情形
	 */
	@Column(length = 100)
	private String limitReason;
	/**
	 * 限制出境日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date limitDate;
	/**
	 * 特殊人群类型
	 */
	@Column(length = 10)
	private String specialGroup;
	
//	/**
//	 * 人员编号
//	 */
//	@ManyToOne
//	@JoinColumn(name = "person_id")
//	private CriminalPerson person;
	private String person_id;	//人员编号
	
	/**
	 * 时间戳
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updateTime;
	
//	/**
//	 * 案件编号
//	 */
//	@ManyToOne
//	@JoinColumn(name = "case_id")
//	private CriminalBasicCase basicCase;
	private String case_id;	//案件编号
	
	public String xzcfjds_paperName;	//行政处罚决定书行政处罚决定书文书号
	
	public String xzcfjds_paperType;	//行政处罚决定书行政处罚决定书文书类型
	
	public String xzcfjds_paperId;	//行政处罚决定书行政处罚决定书文书id
	
	public String xzcfjds_punishPatternAndTimeLimit;	//行政处罚决定书行政处罚决定书处罚方式及期限
	
	public String zlsqjdjds_paperName;	//责令社区戒毒决定书文书号
	
	public String zlsqjdjds_paperType;	//责令社区戒毒决定书文书类型
	
	public String zlsqjdjds_paperId;	//责令社区戒毒决定书文书id
	
	public String qzgljdjds_paperName;	//强制隔离戒毒决定书文书号
	
	public String qzgljdjds_paperType;	//强制隔离戒毒决定书文书类型
	
	public String qzgljdjds_paperId;	//强制隔离戒毒决定书文书id
	
	public String jlz_paperName;	//拘留证文书号
	
	public String jlz_paperType;	//拘留证文书类型
	
	public String jlz_paperId;	//拘留证文书id
	
	public String ycjlqxtzx_paperName;	//延长拘留期限通知书文书号
	
	public String ycjlqxtzx_paperType;	//延长拘留期限通知书文书类型
	
	public String ycjlqxtzx_paperId;	//延长拘留期限通知书文书id
	
	public String dbz_paperName;	//逮捕证文书号
	
	public String dbz_paperType;	//逮捕证文书类型
	
	public String dbz_paperId;	//逮捕证文书id
	
	public String qsyjs_paperName;	//起诉意见书文书号
	
	public String qsyjs_paperType;	//起诉意见书文书类型
	
	public String qsyjs_paperId;	//起诉意见书文书id
	
	public String qbhs_paperName;	//取保候审文书号
	
	public String qbhs_paperType;	//取保候审文书类型
	
	public String qbhs_paperId;	//取保候审文书id
	
	public String jsjz_paperName;	//取保候审文书号
	
	public String jsjz_paperType;	//监视居住文书类型
	
	public String jsjz_paperId;	//监视居住文书id
	
	public String getXzcfjds_paperName() {
		return xzcfjds_paperName;
	}
	public void setXzcfjds_paperName(String xzcfjds_paperName) {
		this.xzcfjds_paperName = xzcfjds_paperName;
	}
	public String getXzcfjds_paperType() {
		return xzcfjds_paperType;
	}
	public void setXzcfjds_paperType(String xzcfjds_paperType) {
		this.xzcfjds_paperType = xzcfjds_paperType;
	}
	public String getXzcfjds_paperId() {
		return xzcfjds_paperId;
	}
	public void setXzcfjds_paperId(String xzcfjds_paperId) {
		this.xzcfjds_paperId = xzcfjds_paperId;
	}
	public String getXzcfjds_punishPatternAndTimeLimit() {
		return xzcfjds_punishPatternAndTimeLimit;
	}
	public void setXzcfjds_punishPatternAndTimeLimit(String xzcfjds_punishPatternAndTimeLimit) {
		this.xzcfjds_punishPatternAndTimeLimit = xzcfjds_punishPatternAndTimeLimit;
	}
	public String getQzgljdjds_paperName() {
		return qzgljdjds_paperName;
	}
	public void setQzgljdjds_paperName(String qzgljdjds_paperName) {
		this.qzgljdjds_paperName = qzgljdjds_paperName;
	}
	public String getQzgljdjds_paperType() {
		return qzgljdjds_paperType;
	}
	public void setQzgljdjds_paperType(String qzgljdjds_paperType) {
		this.qzgljdjds_paperType = qzgljdjds_paperType;
	}
	public String getQzgljdjds_paperId() {
		return qzgljdjds_paperId;
	}
	public void setQzgljdjds_paperId(String qzgljdjds_paperId) {
		this.qzgljdjds_paperId = qzgljdjds_paperId;
	}
	public String getJlz_paperName() {
		return jlz_paperName;
	}
	public void setJlz_paperName(String jlz_paperName) {
		this.jlz_paperName = jlz_paperName;
	}
	public String getJlz_paperType() {
		return jlz_paperType;
	}
	public void setJlz_paperType(String jlz_paperType) {
		this.jlz_paperType = jlz_paperType;
	}
	public String getJlz_paperId() {
		return jlz_paperId;
	}
	public void setJlz_paperId(String jlz_paperId) {
		this.jlz_paperId = jlz_paperId;
	}
	public String getYcjlqxtzx_paperName() {
		return ycjlqxtzx_paperName;
	}
	public void setYcjlqxtzx_paperName(String ycjlqxtzx_paperName) {
		this.ycjlqxtzx_paperName = ycjlqxtzx_paperName;
	}
	public String getYcjlqxtzx_paperType() {
		return ycjlqxtzx_paperType;
	}
	public void setYcjlqxtzx_paperType(String ycjlqxtzx_paperType) {
		this.ycjlqxtzx_paperType = ycjlqxtzx_paperType;
	}
	public String getYcjlqxtzx_paperId() {
		return ycjlqxtzx_paperId;
	}
	public void setYcjlqxtzx_paperId(String ycjlqxtzx_paperId) {
		this.ycjlqxtzx_paperId = ycjlqxtzx_paperId;
	}
	public String getDbz_paperName() {
		return dbz_paperName;
	}
	public void setDbz_paperName(String dbz_paperName) {
		this.dbz_paperName = dbz_paperName;
	}
	public String getDbz_paperType() {
		return dbz_paperType;
	}
	public void setDbz_paperType(String dbz_paperType) {
		this.dbz_paperType = dbz_paperType;
	}
	public String getDbz_paperId() {
		return dbz_paperId;
	}
	public void setDbz_paperId(String dbz_paperId) {
		this.dbz_paperId = dbz_paperId;
	}
	public String getQsyjs_paperName() {
		return qsyjs_paperName;
	}
	public void setQsyjs_paperName(String qsyjs_paperName) {
		this.qsyjs_paperName = qsyjs_paperName;
	}
	public String getQsyjs_paperType() {
		return qsyjs_paperType;
	}
	public void setQsyjs_paperType(String qsyjs_paperType) {
		this.qsyjs_paperType = qsyjs_paperType;
	}
	public String getQsyjs_paperId() {
		return qsyjs_paperId;
	}
	public void setQsyjs_paperId(String qsyjs_paperId) {
		this.qsyjs_paperId = qsyjs_paperId;
	}
	public String getQbhs_paperName() {
		return qbhs_paperName;
	}
	public void setQbhs_paperName(String qbhs_paperName) {
		this.qbhs_paperName = qbhs_paperName;
	}
	public String getQbhs_paperType() {
		return qbhs_paperType;
	}
	public void setQbhs_paperType(String qbhs_paperType) {
		this.qbhs_paperType = qbhs_paperType;
	}
	public String getQbhs_paperId() {
		return qbhs_paperId;
	}
	public void setQbhs_paperId(String qbhs_paperId) {
		this.qbhs_paperId = qbhs_paperId;
	}
	public String getId() {
		return id;
	}
	public String getSuspectType() {
		return suspectType;
	}
	public String getCrimeRole() {
		return crimeRole;
	}
	public String getCriRecord() {
		return criRecord;
	}
	public String getSuspiciongist() {
		return suspiciongist;
	}
	public String getApproach() {
		return approach;
	}
	public String getPersonState() {
		return personState;
	}
	public Date getDateofCapture() {
		return dateofCapture;
	}
	public String getCaptureProcess() {
		return captureProcess;
	}
	public Date getXjApprovalTime() {
		return xjApprovalTime;
	}
	public String getXjApprover() {
		return xjApprover;
	}
	public Date getDetentionTime() {
		return detentionTime;
	}
	public Integer getDetentionLength() {
		return detentionLength;
	}
	public String getDetentionAddress() {
		return detentionAddress;
	}
	public Date getYcjlApprovalTime() {
		return ycjlApprovalTime;
	}
	public String getYcjlApprover() {
		return ycjlApprover;
	}
	public String getIsycjlApproval() {
		return isycjlApproval;
	}
	public Date getYcDetentiomTime() {
		return ycDetentiomTime;
	}
	public Date getQbhsApprovalTime() {
		return qbhsApprovalTime;
	}
	public String getQbhsApprover() {
		return qbhsApprover;
	}
	public String getIsqbhsApproval() {
		return isqbhsApproval;
	}
	public Date getBailTime() {
		return bailTime;
	}
	public Date getJsjzApprovalTime() {
		return jsjzApprovalTime;
	}
	public String getJsjzApprover() {
		return jsjzApprover;
	}
	public String getIsjsjzApproval() {
		return isjsjzApproval;
	}
	public Date getSurveillanceTime() {
		return surveillanceTime;
	}
	public Date getRequestarrestTime() {
		return requestarrestTime;
	}
	public Date getArrestApprovalTime() {
		return arrestApprovalTime;
	}
	public String getIsArrestApproval() {
		return isArrestApproval;
	}
	public Date getArrestTime() {
		return arrestTime;
	}
	public String getArrestRefuse() {
		return arrestRefuse;
	}
	public Date getDrewReviewTime() {
		return drewReviewTime;
	}
	public String getDrewReviewer() {
		return drewReviewer;
	}
	public String getIsfyApproval() {
		return isfyApproval;
	}
	public Date getArrestviewerTime() {
		return arrestviewerTime;
	}
	public Date getArrestreviewTime() {
		return arrestreviewTime;
	}
	public String getArrestReviewer() {
		return arrestReviewer;
	}
	public String getIsfhApproval() {
		return isfhApproval;
	}
	public String getIsysqsApproval() {
		return isysqsApproval;
	}
	public Date getProsecutedTime() {
		return prosecutedTime;
	}
	public String getProsecutedCharge() {
		return prosecutedCharge;
	}
	public String getNotprosecuteReason() {
		return notprosecuteReason;
	}
	public String getContinueInveReason() {
		return continueInveReason;
	}
	public Date getContinueInveTime() {
		return continueInveTime;
	}
	public Date getCompleteInveTimea() {
		return completeInveTimea;
	}
	public Date getCompleteInveTimeb() {
		return completeInveTimeb;
	}
	public Date getProsecutionApptime() {
		return prosecutionApptime;
	}
	public String getProsecutionApper() {
		return prosecutionApper;
	}
	public String getIspzqsApproval() {
		return ispzqsApproval;
	}
	public Date getProsecuteReviewtime() {
		return prosecuteReviewtime;
	}
	public Date getQstqReviewApptime() {
		return qstqReviewApptime;
	}
	public String getQstqReviewApproval() {
		return qstqReviewApproval;
	}
	public String getIsqsfhApproval() {
		return isqsfhApproval;
	}
	public Date getProsecutionReviewTme() {
		return prosecutionReviewTme;
	}
	public Date getOtherApprovalTime() {
		return otherApprovalTime;
	}
	public String getOtherApprover() {
		return otherApprover;
	}
	public String getIsotherApproval() {
		return isotherApproval;
	}
	public String getOtherDeal() {
		return otherDeal;
	}
	public String getDetentionLimit() {
		return detentionLimit;
	}
	public String getDetentionPlace() {
		return detentionPlace;
	}
	public String getCaseTreatmentType() {
		return caseTreatmentType;
	}
	public Date getAdminPenaltyApptime() {
		return adminPenaltyApptime;
	}
	public String getAdminPenaltyApper() {
		return adminPenaltyApper;
	}
	public String getIsxzcfApproval() {
		return isxzcfApproval;
	}
	public String getIswarningPunish() {
		return iswarningPunish;
	}
	public Double getFinesNo() {
		return finesNo;
	}
	public Date getAdminDetentionTime() {
		return adminDetentionTime;
	}
	public String getIsConfiscation() {
		return isConfiscation;
	}
	public String getIsorderedcase() {
		return isorderedcase;
	}
	public String getIsWithheldLicenses() {
		return isWithheldLicenses;
	}
	public String getOther() {
		return other;
	}
	public Date getAdminPenaltyTime() {
		return adminPenaltyTime;
	}
	public Date getPursuitApptime() {
		return pursuitApptime;
	}
	public String getPursuitApprover() {
		return pursuitApprover;
	}
	public Date getPursuitApprovalTime() {
		return pursuitApprovalTime;
	}
	public String getIsPursuit() {
		return isPursuit;
	}
	public String getPursuitNo() {
		return pursuitNo;
	}
	public Date getTrackApptime() {
		return trackApptime;
	}
	public String getTrackApper() {
		return trackApper;
	}
	public String getIsjkApproval() {
		return isjkApproval;
	}
	public Date getExcludeTime() {
		return excludeTime;
	}
	public String getExcludeReason() {
		return excludeReason;
	}
	public String getIsexclude() {
		return isexclude;
	}
	public String getAnnex() {
		return annex;
	}
	public String getInputPerson() {
		return inputPerson;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public String getModifiedPerson() {
		return modifiedPerson;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public Date getDistrictTime() {
		return districtTime;
	}
	public Date getProvinceTime() {
		return provinceTime;
	}
	public String getDeleTag() {
		return deleTag;
	}
	public String getDisputeInterval() {
		return disputeInterval;
	}
	public String getFootHoldAddress() {
		return footHoldAddress;
	}
	public String getIfUptraffick() {
		return ifUptraffick;
	}
	public String getIfUpgun() {
		return ifUpgun;
	}
	public String getIfUpblackmail() {
		return ifUpblackmail;
	}
	public Date getTqArrestReviewTime() {
		return tqArrestReviewTime;
	}
	public String getIsfypzApproval() {
		return isfypzApproval;
	}
	public String getIfUpmurder() {
		return ifUpmurder;
	}
	public String getXjReviewed() {
		return xjReviewed;
	}
	public String getYcjlReviewed() {
		return ycjlReviewed;
	}
	public String getQbhsreviewed() {
		return qbhsreviewed;
	}
	public String getJsjzReviewed() {
		return jsjzReviewed;
	}
	public String getArrestReviewed() {
		return arrestReviewed;
	}
	public String getArrestRefreviewed() {
		return arrestRefreviewed;
	}
	public String getProsecutReviewed() {
		return prosecutReviewed;
	}
	public String getContinueReviewed() {
		return continueReviewed;
	}
	public String getOtherReviewed() {
		return otherReviewed;
	}
	public String getAdminPenalReviewed() {
		return adminPenalReviewed;
	}
	public String getQzgljdshr() {
		return qzgljdshr;
	}
	public String getLdjyshr() {
		return ldjyshr;
	}
	public Date getDetoxifreTime() {
		return detoxifreTime;
	}
	public Date getDetentionreTime() {
		return detentionreTime;
	}
	public String getDetoxifReviewed() {
		return detoxifReviewed;
	}
	public String getDetentionReviewed() {
		return detentionReviewed;
	}
	public String getArrestApprover() {
		return arrestApprover;
	}
	public String getArrestRefApprover() {
		return arrestRefApprover;
	}
	public String getProsecutApproval() {
		return prosecutApproval;
	}
	public String getContinueApprover() {
		return continueApprover;
	}
	public String getDetoxifApprover() {
		return detoxifApprover;
	}
	public String getDetentionApprover() {
		return detentionApprover;
	}
	public String getXjTransactor() {
		return xjTransactor;
	}
	public String getYcjlTransactor() {
		return ycjlTransactor;
	}
	public String getQbhsTransactor() {
		return qbhsTransactor;
	}
	public String getJsjzTransactor() {
		return jsjzTransactor;
	}
	public String getArrestTransactor() {
		return arrestTransactor;
	}
	public String getArrestRefTransactor() {
		return arrestRefTransactor;
	}
	public String getDrewTransactor() {
		return drewTransactor;
	}
	public String getArrvTransactor() {
		return arrvTransactor;
	}
	public String getArreviewTransactor() {
		return arreviewTransactor;
	}
	public String getProsecutTransactor() {
		return prosecutTransactor;
	}
	public String getContTransactor() {
		return contTransactor;
	}
	public String getProsTransactor() {
		return prosTransactor;
	}
	public String getProsecTransactor() {
		return prosecTransactor;
	}
	public String getOtherTransactor() {
		return otherTransactor;
	}
	public String getDetoxifTransactor() {
		return detoxifTransactor;
	}
	public String getDetTransactor() {
		return detTransactor;
	}
	public String getAdminTransactor() {
		return adminTransactor;
	}
	public String getRequestTransactor() {
		return requestTransactor;
	}
	public String getRequestApprover() {
		return requestApprover;
	}
	public String getRequestReviewed() {
		return requestReviewed;
	}
	public String getIsRequestApproval() {
		return isRequestApproval;
	}
	public String getIsArrestRef() {
		return isArrestRef;
	}
	public String getIsConttran() {
		return isConttran;
	}
	public String getProseReviewed() {
		return proseReviewed;
	}
	public String getQstqReviewed() {
		return qstqReviewed;
	}
	public String getIsDetoxif() {
		return isDetoxif;
	}
	public String getIsDetention() {
		return isDetention;
	}
	public String getTrackactor() {
		return trackactor;
	}
	public String getTrackReviewed() {
		return trackReviewed;
	}
	public String getPursuitActor() {
		return pursuitActor;
	}
	public String getPursuitReviewed() {
		return pursuitReviewed;
	}
	public String getArrestRevieweder() {
		return arrestRevieweder;
	}
	public String getIsConttran2() {
		return isConttran2;
	}
	public String getIsfhpzApproval() {
		return isfhpzApproval;
	}
	public String getIsFines() {
		return isFines;
	}
	public String getIsadminDetention() {
		return isadminDetention;
	}
	public String getSqjdRansactor() {
		return sqjdRansactor;
	}
	public String getSqjdReviewer() {
		return sqjdReviewer;
	}
	public String getSqjdApproved() {
		return sqjdApproved;
	}
	public Date getSqjdApprovalTime() {
		return sqjdApprovalTime;
	}
	public String getIfsqjd() {
		return ifsqjd;
	}
	public String getSqkfActor() {
		return sqkfActor;
	}
	public String getSqkfReviewer() {
		return sqkfReviewer;
	}
	public String getSqkfApproval() {
		return sqkfApproval;
	}
	public Date getSqkfApprovalTime() {
		return sqkfApprovalTime;
	}
	public String getIssqkf() {
		return issqkf;
	}
	public String getSrjyActor() {
		return srjyActor;
	}
	public String getSrjyReviewer() {
		return srjyReviewer;
	}
	public String getSrjyApproved() {
		return srjyApproved;
	}
	public Date getSrjyApprovalTime() {
		return srjyApprovalTime;
	}
	public String getIssrjy() {
		return issrjy;
	}
	public String getSjepeople() {
		return sjepeople;
	}
	public String getSjAudit() {
		return sjAudit;
	}
	public String getSjApproved() {
		return sjApproved;
	}
	public Date getSjApprovedTime() {
		return sjApprovedTime;
	}
	public String getIssj() {
		return issj;
	}
	public Date getContinueInvetimeb() {
		return continueInvetimeb;
	}
	public String getIsonrun() {
		return isonrun;
	}
	public String getIssrjys() {
		return issrjys;
	}
	public String getIszyDetoxif() {
		return iszyDetoxif;
	}
	public String getSrjysActor() {
		return srjysActor;
	}
	public Date getSrjysApprovalTime() {
		return srjysApprovalTime;
	}
	public String getSrjysApproved() {
		return srjysApproved;
	}
	public String getSrjysReviewer() {
		return srjysReviewer;
	}
	public String getIszaiya() {
		return iszaiya;
	}
	public Date getToArrestTime() {
		return toArrestTime;
	}
	public String getIstoarrest() {
		return istoarrest;
	}
	public String getArrestmanner() {
		return arrestmanner;
	}
	public String getIsLimitExport() {
		return isLimitExport;
	}
	public String getLimitReason() {
		return limitReason;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public String getSpecialGroup() {
		return specialGroup;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSuspectType(String suspectType) {
		this.suspectType = suspectType;
	}
	public void setCrimeRole(String crimeRole) {
		this.crimeRole = crimeRole;
	}
	public void setCriRecord(String criRecord) {
		this.criRecord = criRecord;
	}
	public void setSuspiciongist(String suspiciongist) {
		this.suspiciongist = suspiciongist;
	}
	public void setApproach(String approach) {
		this.approach = approach;
	}
	public void setPersonState(String personState) {
		this.personState = personState;
	}
	public void setDateofCapture(Date dateofCapture) {
		this.dateofCapture = dateofCapture;
	}
	public void setCaptureProcess(String captureProcess) {
		this.captureProcess = captureProcess;
	}
	public void setXjApprovalTime(Date xjApprovalTime) {
		this.xjApprovalTime = xjApprovalTime;
	}
	public void setXjApprover(String xjApprover) {
		this.xjApprover = xjApprover;
	}
	public void setDetentionTime(Date detentionTime) {
		this.detentionTime = detentionTime;
	}
	public void setDetentionLength(Integer detentionLength) {
		this.detentionLength = detentionLength;
	}
	public void setDetentionAddress(String detentionAddress) {
		this.detentionAddress = detentionAddress;
	}
	public void setYcjlApprovalTime(Date ycjlApprovalTime) {
		this.ycjlApprovalTime = ycjlApprovalTime;
	}
	public void setYcjlApprover(String ycjlApprover) {
		this.ycjlApprover = ycjlApprover;
	}
	public void setIsycjlApproval(String isycjlApproval) {
		this.isycjlApproval = isycjlApproval;
	}
	public void setYcDetentiomTime(Date ycDetentiomTime) {
		this.ycDetentiomTime = ycDetentiomTime;
	}
	public void setQbhsApprovalTime(Date qbhsApprovalTime) {
		this.qbhsApprovalTime = qbhsApprovalTime;
	}
	public void setQbhsApprover(String qbhsApprover) {
		this.qbhsApprover = qbhsApprover;
	}
	public void setIsqbhsApproval(String isqbhsApproval) {
		this.isqbhsApproval = isqbhsApproval;
	}
	public void setBailTime(Date bailTime) {
		this.bailTime = bailTime;
	}
	public void setJsjzApprovalTime(Date jsjzApprovalTime) {
		this.jsjzApprovalTime = jsjzApprovalTime;
	}
	public void setJsjzApprover(String jsjzApprover) {
		this.jsjzApprover = jsjzApprover;
	}
	public void setIsjsjzApproval(String isjsjzApproval) {
		this.isjsjzApproval = isjsjzApproval;
	}
	public void setSurveillanceTime(Date surveillanceTime) {
		this.surveillanceTime = surveillanceTime;
	}
	public void setRequestarrestTime(Date requestarrestTime) {
		this.requestarrestTime = requestarrestTime;
	}
	public void setArrestApprovalTime(Date arrestApprovalTime) {
		this.arrestApprovalTime = arrestApprovalTime;
	}
	public void setIsArrestApproval(String isArrestApproval) {
		this.isArrestApproval = isArrestApproval;
	}
	public void setArrestTime(Date arrestTime) {
		this.arrestTime = arrestTime;
	}
	public void setArrestRefuse(String arrestRefuse) {
		this.arrestRefuse = arrestRefuse;
	}
	public void setDrewReviewTime(Date drewReviewTime) {
		this.drewReviewTime = drewReviewTime;
	}
	public void setDrewReviewer(String drewReviewer) {
		this.drewReviewer = drewReviewer;
	}
	public void setIsfyApproval(String isfyApproval) {
		this.isfyApproval = isfyApproval;
	}
	public void setArrestviewerTime(Date arrestviewerTime) {
		this.arrestviewerTime = arrestviewerTime;
	}
	public void setArrestreviewTime(Date arrestreviewTime) {
		this.arrestreviewTime = arrestreviewTime;
	}
	public void setArrestReviewer(String arrestReviewer) {
		this.arrestReviewer = arrestReviewer;
	}
	public void setIsfhApproval(String isfhApproval) {
		this.isfhApproval = isfhApproval;
	}
	public void setIsysqsApproval(String isysqsApproval) {
		this.isysqsApproval = isysqsApproval;
	}
	public void setProsecutedTime(Date prosecutedTime) {
		this.prosecutedTime = prosecutedTime;
	}
	public void setProsecutedCharge(String prosecutedCharge) {
		this.prosecutedCharge = prosecutedCharge;
	}
	public void setNotprosecuteReason(String notprosecuteReason) {
		this.notprosecuteReason = notprosecuteReason;
	}
	public void setContinueInveReason(String continueInveReason) {
		this.continueInveReason = continueInveReason;
	}
	public void setContinueInveTime(Date continueInveTime) {
		this.continueInveTime = continueInveTime;
	}
	public void setCompleteInveTimea(Date completeInveTimea) {
		this.completeInveTimea = completeInveTimea;
	}
	public void setCompleteInveTimeb(Date completeInveTimeb) {
		this.completeInveTimeb = completeInveTimeb;
	}
	public void setProsecutionApptime(Date prosecutionApptime) {
		this.prosecutionApptime = prosecutionApptime;
	}
	public void setProsecutionApper(String prosecutionApper) {
		this.prosecutionApper = prosecutionApper;
	}
	public void setIspzqsApproval(String ispzqsApproval) {
		this.ispzqsApproval = ispzqsApproval;
	}
	public void setProsecuteReviewtime(Date prosecuteReviewtime) {
		this.prosecuteReviewtime = prosecuteReviewtime;
	}
	public void setQstqReviewApptime(Date qstqReviewApptime) {
		this.qstqReviewApptime = qstqReviewApptime;
	}
	public void setQstqReviewApproval(String qstqReviewApproval) {
		this.qstqReviewApproval = qstqReviewApproval;
	}
	public void setIsqsfhApproval(String isqsfhApproval) {
		this.isqsfhApproval = isqsfhApproval;
	}
	public void setProsecutionReviewTme(Date prosecutionReviewTme) {
		this.prosecutionReviewTme = prosecutionReviewTme;
	}
	public void setOtherApprovalTime(Date otherApprovalTime) {
		this.otherApprovalTime = otherApprovalTime;
	}
	public void setOtherApprover(String otherApprover) {
		this.otherApprover = otherApprover;
	}
	public void setIsotherApproval(String isotherApproval) {
		this.isotherApproval = isotherApproval;
	}
	public void setOtherDeal(String otherDeal) {
		this.otherDeal = otherDeal;
	}
	public void setDetentionLimit(String detentionLimit) {
		this.detentionLimit = detentionLimit;
	}
	public void setDetentionPlace(String detentionPlace) {
		this.detentionPlace = detentionPlace;
	}
	public void setCaseTreatmentType(String caseTreatmentType) {
		this.caseTreatmentType = caseTreatmentType;
	}
	public void setAdminPenaltyApptime(Date adminPenaltyApptime) {
		this.adminPenaltyApptime = adminPenaltyApptime;
	}
	public void setAdminPenaltyApper(String adminPenaltyApper) {
		this.adminPenaltyApper = adminPenaltyApper;
	}
	public void setIsxzcfApproval(String isxzcfApproval) {
		this.isxzcfApproval = isxzcfApproval;
	}
	public void setIswarningPunish(String iswarningPunish) {
		this.iswarningPunish = iswarningPunish;
	}
	public void setFinesNo(Double finesNo) {
		this.finesNo = finesNo;
	}
	public void setAdminDetentionTime(Date adminDetentionTime) {
		this.adminDetentionTime = adminDetentionTime;
	}
	public void setIsConfiscation(String isConfiscation) {
		this.isConfiscation = isConfiscation;
	}
	public void setIsorderedcase(String isorderedcase) {
		this.isorderedcase = isorderedcase;
	}
	public void setIsWithheldLicenses(String isWithheldLicenses) {
		this.isWithheldLicenses = isWithheldLicenses;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public void setAdminPenaltyTime(Date adminPenaltyTime) {
		this.adminPenaltyTime = adminPenaltyTime;
	}
	public void setPursuitApptime(Date pursuitApptime) {
		this.pursuitApptime = pursuitApptime;
	}
	public void setPursuitApprover(String pursuitApprover) {
		this.pursuitApprover = pursuitApprover;
	}
	public void setPursuitApprovalTime(Date pursuitApprovalTime) {
		this.pursuitApprovalTime = pursuitApprovalTime;
	}
	public void setIsPursuit(String isPursuit) {
		this.isPursuit = isPursuit;
	}
	public void setPursuitNo(String pursuitNo) {
		this.pursuitNo = pursuitNo;
	}
	public void setTrackApptime(Date trackApptime) {
		this.trackApptime = trackApptime;
	}
	public void setTrackApper(String trackApper) {
		this.trackApper = trackApper;
	}
	public void setIsjkApproval(String isjkApproval) {
		this.isjkApproval = isjkApproval;
	}
	public void setExcludeTime(Date excludeTime) {
		this.excludeTime = excludeTime;
	}
	public void setExcludeReason(String excludeReason) {
		this.excludeReason = excludeReason;
	}
	public void setIsexclude(String isexclude) {
		this.isexclude = isexclude;
	}
	public void setAnnex(String annex) {
		this.annex = annex;
	}
	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public void setDistrictTime(Date districtTime) {
		this.districtTime = districtTime;
	}
	public void setProvinceTime(Date provinceTime) {
		this.provinceTime = provinceTime;
	}
	public void setDeleTag(String deleTag) {
		this.deleTag = deleTag;
	}
	public void setDisputeInterval(String disputeInterval) {
		this.disputeInterval = disputeInterval;
	}
	public void setFootHoldAddress(String footHoldAddress) {
		this.footHoldAddress = footHoldAddress;
	}
	public void setIfUptraffick(String ifUptraffick) {
		this.ifUptraffick = ifUptraffick;
	}
	public void setIfUpgun(String ifUpgun) {
		this.ifUpgun = ifUpgun;
	}
	public void setIfUpblackmail(String ifUpblackmail) {
		this.ifUpblackmail = ifUpblackmail;
	}
	public void setTqArrestReviewTime(Date tqArrestReviewTime) {
		this.tqArrestReviewTime = tqArrestReviewTime;
	}
	public void setIsfypzApproval(String isfypzApproval) {
		this.isfypzApproval = isfypzApproval;
	}
	public void setIfUpmurder(String ifUpmurder) {
		this.ifUpmurder = ifUpmurder;
	}
	public void setXjReviewed(String xjReviewed) {
		this.xjReviewed = xjReviewed;
	}
	public void setYcjlReviewed(String ycjlReviewed) {
		this.ycjlReviewed = ycjlReviewed;
	}
	public void setQbhsreviewed(String qbhsreviewed) {
		this.qbhsreviewed = qbhsreviewed;
	}
	public void setJsjzReviewed(String jsjzReviewed) {
		this.jsjzReviewed = jsjzReviewed;
	}
	public void setArrestReviewed(String arrestReviewed) {
		this.arrestReviewed = arrestReviewed;
	}
	public void setArrestRefreviewed(String arrestRefreviewed) {
		this.arrestRefreviewed = arrestRefreviewed;
	}
	public void setProsecutReviewed(String prosecutReviewed) {
		this.prosecutReviewed = prosecutReviewed;
	}
	public void setContinueReviewed(String continueReviewed) {
		this.continueReviewed = continueReviewed;
	}
	public void setOtherReviewed(String otherReviewed) {
		this.otherReviewed = otherReviewed;
	}
	public void setAdminPenalReviewed(String adminPenalReviewed) {
		this.adminPenalReviewed = adminPenalReviewed;
	}
	public void setQzgljdshr(String qzgljdshr) {
		this.qzgljdshr = qzgljdshr;
	}
	public void setLdjyshr(String ldjyshr) {
		this.ldjyshr = ldjyshr;
	}
	public void setDetoxifreTime(Date detoxifreTime) {
		this.detoxifreTime = detoxifreTime;
	}
	public void setDetentionreTime(Date detentionreTime) {
		this.detentionreTime = detentionreTime;
	}
	public void setDetoxifReviewed(String detoxifReviewed) {
		this.detoxifReviewed = detoxifReviewed;
	}
	public void setDetentionReviewed(String detentionReviewed) {
		this.detentionReviewed = detentionReviewed;
	}
	public void setArrestApprover(String arrestApprover) {
		this.arrestApprover = arrestApprover;
	}
	public void setArrestRefApprover(String arrestRefApprover) {
		this.arrestRefApprover = arrestRefApprover;
	}
	public void setProsecutApproval(String prosecutApproval) {
		this.prosecutApproval = prosecutApproval;
	}
	public void setContinueApprover(String continueApprover) {
		this.continueApprover = continueApprover;
	}
	public void setDetoxifApprover(String detoxifApprover) {
		this.detoxifApprover = detoxifApprover;
	}
	public void setDetentionApprover(String detentionApprover) {
		this.detentionApprover = detentionApprover;
	}
	public void setXjTransactor(String xjTransactor) {
		this.xjTransactor = xjTransactor;
	}
	public void setYcjlTransactor(String ycjlTransactor) {
		this.ycjlTransactor = ycjlTransactor;
	}
	public void setQbhsTransactor(String qbhsTransactor) {
		this.qbhsTransactor = qbhsTransactor;
	}
	public void setJsjzTransactor(String jsjzTransactor) {
		this.jsjzTransactor = jsjzTransactor;
	}
	public void setArrestTransactor(String arrestTransactor) {
		this.arrestTransactor = arrestTransactor;
	}
	public void setArrestRefTransactor(String arrestRefTransactor) {
		this.arrestRefTransactor = arrestRefTransactor;
	}
	public void setDrewTransactor(String drewTransactor) {
		this.drewTransactor = drewTransactor;
	}
	public void setArrvTransactor(String arrvTransactor) {
		this.arrvTransactor = arrvTransactor;
	}
	public void setArreviewTransactor(String arreviewTransactor) {
		this.arreviewTransactor = arreviewTransactor;
	}
	public void setProsecutTransactor(String prosecutTransactor) {
		this.prosecutTransactor = prosecutTransactor;
	}
	public void setContTransactor(String contTransactor) {
		this.contTransactor = contTransactor;
	}
	public void setProsTransactor(String prosTransactor) {
		this.prosTransactor = prosTransactor;
	}
	public void setProsecTransactor(String prosecTransactor) {
		this.prosecTransactor = prosecTransactor;
	}
	public void setOtherTransactor(String otherTransactor) {
		this.otherTransactor = otherTransactor;
	}
	public void setDetoxifTransactor(String detoxifTransactor) {
		this.detoxifTransactor = detoxifTransactor;
	}
	public void setDetTransactor(String detTransactor) {
		this.detTransactor = detTransactor;
	}
	public void setAdminTransactor(String adminTransactor) {
		this.adminTransactor = adminTransactor;
	}
	public void setRequestTransactor(String requestTransactor) {
		this.requestTransactor = requestTransactor;
	}
	public void setRequestApprover(String requestApprover) {
		this.requestApprover = requestApprover;
	}
	public void setRequestReviewed(String requestReviewed) {
		this.requestReviewed = requestReviewed;
	}
	public void setIsRequestApproval(String isRequestApproval) {
		this.isRequestApproval = isRequestApproval;
	}
	public void setIsArrestRef(String isArrestRef) {
		this.isArrestRef = isArrestRef;
	}
	public void setIsConttran(String isConttran) {
		this.isConttran = isConttran;
	}
	public void setProseReviewed(String proseReviewed) {
		this.proseReviewed = proseReviewed;
	}
	public void setQstqReviewed(String qstqReviewed) {
		this.qstqReviewed = qstqReviewed;
	}
	public void setIsDetoxif(String isDetoxif) {
		this.isDetoxif = isDetoxif;
	}
	public void setIsDetention(String isDetention) {
		this.isDetention = isDetention;
	}
	public void setTrackactor(String trackactor) {
		this.trackactor = trackactor;
	}
	public void setTrackReviewed(String trackReviewed) {
		this.trackReviewed = trackReviewed;
	}
	public void setPursuitActor(String pursuitActor) {
		this.pursuitActor = pursuitActor;
	}
	public void setPursuitReviewed(String pursuitReviewed) {
		this.pursuitReviewed = pursuitReviewed;
	}
	public void setArrestRevieweder(String arrestRevieweder) {
		this.arrestRevieweder = arrestRevieweder;
	}
	public void setIsConttran2(String isConttran2) {
		this.isConttran2 = isConttran2;
	}
	public void setIsfhpzApproval(String isfhpzApproval) {
		this.isfhpzApproval = isfhpzApproval;
	}
	public void setIsFines(String isFines) {
		this.isFines = isFines;
	}
	public void setIsadminDetention(String isadminDetention) {
		this.isadminDetention = isadminDetention;
	}
	public void setSqjdRansactor(String sqjdRansactor) {
		this.sqjdRansactor = sqjdRansactor;
	}
	public void setSqjdReviewer(String sqjdReviewer) {
		this.sqjdReviewer = sqjdReviewer;
	}
	public void setSqjdApproved(String sqjdApproved) {
		this.sqjdApproved = sqjdApproved;
	}
	public void setSqjdApprovalTime(Date sqjdApprovalTime) {
		this.sqjdApprovalTime = sqjdApprovalTime;
	}
	public void setIfsqjd(String ifsqjd) {
		this.ifsqjd = ifsqjd;
	}
	public void setSqkfActor(String sqkfActor) {
		this.sqkfActor = sqkfActor;
	}
	public void setSqkfReviewer(String sqkfReviewer) {
		this.sqkfReviewer = sqkfReviewer;
	}
	public void setSqkfApproval(String sqkfApproval) {
		this.sqkfApproval = sqkfApproval;
	}
	public void setSqkfApprovalTime(Date sqkfApprovalTime) {
		this.sqkfApprovalTime = sqkfApprovalTime;
	}
	public void setIssqkf(String issqkf) {
		this.issqkf = issqkf;
	}
	public void setSrjyActor(String srjyActor) {
		this.srjyActor = srjyActor;
	}
	public void setSrjyReviewer(String srjyReviewer) {
		this.srjyReviewer = srjyReviewer;
	}
	public void setSrjyApproved(String srjyApproved) {
		this.srjyApproved = srjyApproved;
	}
	public void setSrjyApprovalTime(Date srjyApprovalTime) {
		this.srjyApprovalTime = srjyApprovalTime;
	}
	public void setIssrjy(String issrjy) {
		this.issrjy = issrjy;
	}
	public void setSjepeople(String sjepeople) {
		this.sjepeople = sjepeople;
	}
	public void setSjAudit(String sjAudit) {
		this.sjAudit = sjAudit;
	}
	public void setSjApproved(String sjApproved) {
		this.sjApproved = sjApproved;
	}
	public void setSjApprovedTime(Date sjApprovedTime) {
		this.sjApprovedTime = sjApprovedTime;
	}
	public void setIssj(String issj) {
		this.issj = issj;
	}
	public void setContinueInvetimeb(Date continueInvetimeb) {
		this.continueInvetimeb = continueInvetimeb;
	}
	public void setIsonrun(String isonrun) {
		this.isonrun = isonrun;
	}
	public void setIssrjys(String issrjys) {
		this.issrjys = issrjys;
	}
	public void setIszyDetoxif(String iszyDetoxif) {
		this.iszyDetoxif = iszyDetoxif;
	}
	public void setSrjysActor(String srjysActor) {
		this.srjysActor = srjysActor;
	}
	public void setSrjysApprovalTime(Date srjysApprovalTime) {
		this.srjysApprovalTime = srjysApprovalTime;
	}
	public void setSrjysApproved(String srjysApproved) {
		this.srjysApproved = srjysApproved;
	}
	public void setSrjysReviewer(String srjysReviewer) {
		this.srjysReviewer = srjysReviewer;
	}
	public void setIszaiya(String iszaiya) {
		this.iszaiya = iszaiya;
	}
	public void setToArrestTime(Date toArrestTime) {
		this.toArrestTime = toArrestTime;
	}
	public void setIstoarrest(String istoarrest) {
		this.istoarrest = istoarrest;
	}
	public void setArrestmanner(String arrestmanner) {
		this.arrestmanner = arrestmanner;
	}
	public void setIsLimitExport(String isLimitExport) {
		this.isLimitExport = isLimitExport;
	}
	public void setLimitReason(String limitReason) {
		this.limitReason = limitReason;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public void setSpecialGroup(String specialGroup) {
		this.specialGroup = specialGroup;
	}
	public String getIsxjApproval() {
		return isxjApproval;
	}
	public void setIsxjApproval(String isxjApproval) {
		this.isxjApproval = isxjApproval;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getIsFinesDetention() {
		return isFinesDetention;
	}
	public void setIsFinesDetention(String isFinesDetention) {
		this.isFinesDetention = isFinesDetention;
	}
	public String getCase_id() {
		return case_id;
	}
	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}
	
	public String getZlsqjdjds_paperName() {
		return zlsqjdjds_paperName;
	}
	public void setZlsqjdjds_paperName(String zlsqjdjds_paperName) {
		this.zlsqjdjds_paperName = zlsqjdjds_paperName;
	}
	public String getZlsqjdjds_paperType() {
		return zlsqjdjds_paperType;
	}
	public void setZlsqjdjds_paperType(String zlsqjdjds_paperType) {
		this.zlsqjdjds_paperType = zlsqjdjds_paperType;
	}
	public String getZlsqjdjds_paperId() {
		return zlsqjdjds_paperId;
	}
	public void setZlsqjdjds_paperId(String zlsqjdjds_paperId) {
		this.zlsqjdjds_paperId = zlsqjdjds_paperId;
	}
	public String getJsjz_paperName() {
		return jsjz_paperName;
	}
	public void setJsjz_paperName(String jsjz_paperName) {
		this.jsjz_paperName = jsjz_paperName;
	}
	public String getJsjz_paperType() {
		return jsjz_paperType;
	}
	public void setJsjz_paperType(String jsjz_paperType) {
		this.jsjz_paperType = jsjz_paperType;
	}
	public String getJsjz_paperId() {
		return jsjz_paperId;
	}
	public void setJsjz_paperId(String jsjz_paperId) {
		this.jsjz_paperId = jsjz_paperId;
	}
	public CriminalBasicCase getBasicCase(){
		ICriminalCaseService criminalCaseService = (ICriminalCaseService) SpringContextUtil.getBean("criminalCaseService");
		CriminalBasicCase basicCase = criminalCaseService.findCriminalCaseByCaseId(case_id);
		return basicCase;
	}
	
	public CriminalPerson getPerson(){
		ICriminalPersonService criminalPersonService = (ICriminalPersonService) SpringContextUtil.getBean("criminalPersonService");
		CriminalPerson criminalPerson = criminalPersonService.findById(person_id);
		return criminalPerson;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	
}