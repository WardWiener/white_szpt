package com.taiji.pubsec.szpt.dagl.action.bean;

/**
 * 嫌疑人Bean
 */
public class CaseSupectRelationBean {

	/**
	 * 编号
	 */
	private String id;
	/**
	 * 嫌疑人类型
	 */
	private String suspectType;
	/**
	 * 案件角色
	 */
	private String crimeRole;
	/**
	 * 违法事实及依据(len4000)
	 */
	private String criRecord;
	/**
	 * 嫌疑依据
	 */
	private String suspiciongist;
	/**
	 * 处理方式
	 */
	private String approach;
	/**
	 * 人员状态
	 */
	private String personState;
	/**
	 * 抓获日期
	 */
	private String dateofCapture;
	/**
	 * 抓获经过
	 */
	private String captureProcess;
	/**
	 * 刑拘批准时间
	 */
	private String xjApprovalTime;
	/**
	 * 刑拘批准人
	 */
	private String xjApprover;
	/**
	 * 是否批准刑拘
	 */
	private String isxjApproval;
	/**
	 * 刑拘时间
	 */
	private String detentionTime;
	/**
	 * 拘留时限(len22)
	 */
	private String detentionLength;
	/**
	 * 拘留地点
	 */
	private String detentionAddress;
	/**
	 * 延长拘留期限批准时间
	 */
	private String ycjlApprovalTime;
	/**
	 * 延长拘留期限批准人
	 */
	private String ycjlApprover;
	/**
	 * 是否批准延长拘留期限
	 */
	private String isycjlApproval;
	/**
	 * 延长拘留期限时间
	 */
	private String ycDetentiomTime;
	/**
	 * 取保候审批准时间
	 */
	private String qbhsApprovalTime;
	/**
	 * 取保候审批准人
	 */
	private String qbhsApprover;
	/**
	 * 是否批准取保候审
	 */
	private String isqbhsApproval;
	/**
	 * 取保候审执行日期
	 */
	private String bailTime;
	/**
	 * 监视居住批准时间
	 */
	private String jsjzApprovalTime;
	/**
	 * 监视居住批准人
	 */
	private String jsjzApprover;
	/**
	 * 是否批准监视居住
	 */
	private String isjsjzApproval;
	/**
	 * 监视居住执行日期
	 */
	private String surveillanceTime;
	/**
	 * 报捕时间
	 */
	private String requestarrestTime;
	/**
	 * 批准逮捕时间
	 */
	private String arrestApprovalTime;
	/**
	 * 是否批准逮捕
	 */
	private String isArrestApproval;
	/**
	 * 执行逮捕时间
	 */
	private String arrestTime;
	/**
	 * 不批捕原因
	 */
	private String arrestRefuse;
	/**
	 * 批准提请逮捕复议时间
	 */
	private String drewReviewTime;
	/**
	 * 批准提请逮捕复议人
	 */
	private String drewReviewer;
	/**
	 * 是否批准提请逮捕复议
	 */
	private String isfyApproval;
	/**
	 * 提请逮捕复议时间
	 */
	private String arrestviewerTime;
	/**
	 * 提请逮捕复核时间
	 */
	private String arrestreviewTime;
	/**
	 * 批准逮捕提请复核人
	 */
	private String arrestReviewer;
	/**
	 * 是否批准提请逮捕复核
	 */
	private String isfhApproval;
	/**
	 * 是否移送起诉
	 */
	private String isysqsApproval;
	/**
	 * 移送起诉时间
	 */
	private String prosecutedTime;
	/**
	 * 移送起诉罪名
	 */
	private String prosecutedCharge;
	/**
	 * 不起诉原因(len900)
	 */
	private String notprosecuteReason;
	/**
	 * 退侦原因(len900)
	 */
	private String continueInveReason;
	/**
	 * 一次退侦时间
	 */
	private String continueInveTime;
	/**
	 * 补侦完毕时间(第一次)
	 */
	private String completeInveTimea;
	/**
	 * 补侦完毕时间(第二次)
	 */
	private String completeInveTimeb;
	/**
	 * 起诉提请复议批准时间
	 */
	private String prosecutionApptime;
	/**
	 * 起诉提请复议批准人
	 */
	private String prosecutionApper;
	/**
	 * 是否批准起诉复议
	 */
	private String ispzqsApproval;
	/**
	 * 起诉复议时间
	 */
	private String prosecuteReviewtime;
	/**
	 * 起诉提请复核批准时间
	 */
	private String qstqReviewApptime;
	/**
	 * 起诉提请复核批准人
	 */
	private String qstqReviewApproval;
	/**
	 * 是否批准起诉复核
	 */
	private String isqsfhApproval;
	/**
	 * 起诉复核时间
	 */
	private String prosecutionReviewTme;
	/**
	 * 其他处理批准时间
	 */
	private String otherApprovalTime;
	/**
	 * 其他处理批准人
	 */
	private String otherApprover;
	/**
	 * 是否批准其他处理
	 */
	private String isotherApproval;
	/**
	 * 其他处理
	 */
	private String otherDeal;
	/**
	 * 劳教、少教时限
	 */
	private String detentionLimit;
	/**
	 * 劳教、少教场所
	 */
	private String detentionPlace;
	/**
	 * 案件处理种类
	 */
	private String caseTreatmentType;
	/**
	 * 行政处罚批准时间
	 */
	private String adminPenaltyApptime;
	/**
	 * 行政处罚批准人
	 */
	private String adminPenaltyApper;
	/**
	 * 是否批准行政处罚
	 */
	private String isxzcfApproval;
	/**
	 * 是否警告(行政处罚)
	 */
	private String iswarningPunish;
	/**
	 * 罚款金额
	 */
	private String finesNo;
	/**
	 * 行政拘留日期
	 */
	private String adminDetentionTime;
	/**
	 * 是否没收违法所得及违法财物
	 */
	private String isConfiscation;
	/**
	 * 是否责令停产停业
	 */
	private String isorderedcase;
	/**
	 * 是否暂扣吊销证照
	 */
	private String isWithheldLicenses;
	/**
	 * 其他
	 */
	private String other;
	/**
	 * 行政处罚执行日期
	 */
	private String adminPenaltyTime;
	/**
	 * 上网追逃申请时间
	 */
	private String pursuitApptime;
	/**
	 * 上网追逃批准人
	 */
	private String pursuitApprover;
	/**
	 * 上网追逃批准时间
	 */
	private String pursuitApprovalTime;
	/**
	 * 是否上网追逃
	 */
	private String isPursuit;
	/**
	 * 追逃编号
	 */
	private String pursuitNo;
	/**
	 * 全省缉控申请时间
	 */
	private String trackApptime;
	/**
	 * 全省缉控批准人
	 */
	private String trackApper;
	/**
	 * 是否全省缉控
	 */
	private String isjkApproval;
	/**
	 * 从本案排除时间
	 */
	private String excludeTime;
	/**
	 * 从本案排除原因(len3000)
	 */
	private String excludeReason;
	/**
	 * 是否从本案排除
	 */
	private String isexclude;
	/**
	 * 备注(len1500)
	 */
	private String annex;
	/**
	 * 录入人
	 */
	private String inputPerson;
	/**
	 * 录入时间
	 */
	private String inputTime;
	/**
	 * 修改人
	 */
	private String modifiedPerson;
	/**
	 * 修改时间
	 */
	private String modifiedTime;
	/**
	 * 入地市库时间
	 */
	private String districtTime;
	/**
	 * 入省库时间
	 */
	private String provinceTime;
	/**
	 * 逮捕复核时间
	 */
	private String tqArrestReviewTime;
	/**
	 * 复议后是否批准逮捕
	 */
	private String isfypzApproval;
	/**
	 * 是否上报命案系统
	 */
	private String ifUpmurder;
	/**
	 * 刑拘审核人
	 */
	private String xjReviewed;
	/**
	 * 延长拘留审核人
	 */
	private String ycjlReviewed;
	/**
	 * 取保候审审核人
	 */
	private String qbhsreviewed;
	/**
	 * 监视居住审核人
	 */
	private String jsjzReviewed;
	/**
	 * 批准逮捕审核人
	 */
	private String arrestReviewed;
	/**
	 * 不批捕审核人
	 */
	private String arrestRefreviewed;
	/**
	 * 移送起诉审核人
	 */
	private String prosecutReviewed;
	/**
	 * 退侦审核人
	 */
	private String continueReviewed;
	/**
	 * 其他处理审核人
	 */
	private String otherReviewed;
	/**
	 * 行政处罚审核人
	 */
	private String adminPenalReviewed;
	/**
	 * 强制隔离戒毒审核人
	 */
	private String detoxifReviewed;
	/**
	 * 劳动教养审核人
	 */
	private String detentionReviewed;
	/**
	 * 批准逮捕批准人
	 */
	private String arrestApprover;
	/**
	 * 不批捕批准人
	 */
	private String arrestRefApprover;
	/**
	 * 移送起诉批准人
	 */
	private String prosecutApproval;
	/**
	 * 退侦批准人
	 */
	private String continueApprover;
	/**
	 * 强制隔离戒毒批准人
	 */
	private String detoxifApprover;
	/**
	 * 劳动教养批准人
	 */
	private String detentionApprover;
	/**
	 * 刑拘办理人
	 */
	private String xjTransactor;
	/**
	 * 延长拘留办理人
	 */
	private String ycjlTransactor;
	/**
	 * 取保候审办理人
	 */
	private String qbhsTransactor;
	/**
	 * 监视居住办理人
	 */
	private String jsjzTransactor;
	/**
	 * 批准逮捕办理人
	 */
	private String arrestTransactor;
	/**
	 * 不批捕办理人
	 */
	private String arrestRefTransactor;
	/**
	 * 提请复议办理人
	 */
	private String drewTransactor;
	/**
	 * 提请逮捕复议办理人
	 */
	private String arrvTransactor;
	/**
	 * 批捕复核办理人
	 */
	private String arreviewTransactor;
	/**
	 * 移送起诉办理人
	 */
	private String prosecutTransactor;
	/**
	 * 退侦办理人
	 */
	private String contTransactor;
	/**
	 * 起诉复议办理人
	 */
	private String prosTransactor;
	/**
	 * 起诉复核办理人
	 */
	private String prosecTransactor;
	/**
	 * 其他处理办理人
	 */
	private String otherTransactor;
	/**
	 * 强制隔离戒毒办理人
	 */
	private String detoxifTransactor;
	/**
	 * 劳动教养办理人
	 */
	private String detTransactor;
	/**
	 * 行政处罚办理人
	 */
	private String adminTransactor;
	/**
	 * 是否一次退侦
	 */
	private String isConttran;
	/**
	 * 是否二次退侦
	 */
	private String isConttran2;
	/**
	 * 复核后是否批捕
	 */
	private String isfhpzApproval;
	/**
	 * 是否罚款
	 */
	private String isFines;
	/**
	 * 是否行政拘留
	 */
	private String isadminDetention;
	/**
	 * 是否行政拘留并罚款
	 */
	private String isFinesDetention;
	/**
	 * 社区戒毒办理人
	 */
	private String sqjdRansactor;
	/**
	 * 社区戒毒审核人
	 */
	private String sqjdReviewer;
	/**
	 * 社区戒毒批准人
	 */
	private String sqjdApproved;
	/**
	 * 社区戒毒批准时间
	 */
	private String sqjdApprovalTime;
	/**
	 * 是否社区戒毒
	 */
	private String ifsqjd;
	/**
	 * 社区康复办理人
	 */
	private String sqkfActor;
	/**
	 * 社区康复审核人
	 */
	private String sqkfReviewer;
	/**
	 * 社区康复批准人
	 */
	private String sqkfApproval;
	/**
	 * 社区康复批准时间
	 */
	private String sqkfApprovalTime;
	/**
	 * 是否社区康复
	 */
	private String issqkf;
	/**
	 * 收容教育办理人
	 */
	private String srjyActor;
	/**
	 * 收容教育审核人
	 */
	private String srjyReviewer;
	/**
	 * 收容教育批准人
	 */
	private String srjyApproved;
	/**
	 * 收容教育批准时间
	 */
	private String srjyApprovalTime;
	/**
	 * 是否收容教育
	 */
	private String issrjy;
	/**
	 * 少教办理人
	 */
	private String sjepeople;
	/**
	 * 少教审核人
	 */
	private String sjAudit;
	/**
	 * 少教批准人
	 */
	private String sjApproved;
	/**
	 * 少教批准时间
	 */
	private String sjApprovedTime;
	/**
	 * 是否少教
	 */
	private String issj;
	/**
	 * 二次退侦时间
	 */
	private String continueInvetimeb;
	/**
	 * 是否收容教养
	 */
	private String issrjys;
	/**
	 * 是否自愿接受强制隔离戒毒
	 */
	private String iszyDetoxif;
	/**
	 * 收容教养办理人
	 */
	private String srjysActor;
	/**
	 * 收容教养批准时间
	 */
	private String srjysApprovalTime;
	/**
	 * 收容教养批准人
	 */
	private String srjysApproved;
	/**
	 * 收容教养审核人
	 */
	private String srjysReviewer;
	/**
	 * 提请批捕时间
	 */
	private String toArrestTime;
	/**
	 * 是否提请批捕
	 */
	private String istoarrest;
	/**
	 * 是否限制出境
	 */
	private String isLimitExport;
	/**
	 * 不批准出境情形
	 */
	private String limitReason;
	/**
	 * 限制出境日期
	 */
	private String limitDate;
	/**
	 * 特殊人群类型
	 */
	private String specialGroup;
	/**
	 * 入库时间
	 */
	private String hck_rksj;
	/**
	 * 更新时间
	 */
	private String hck_gxsj;
	/**
	 * 人员编号
	 */
	private CriminalPersonBean criminalPerson = new CriminalPersonBean();
	
	/**
	 * 时间戳
	 */
	private String updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuspectType() {
		return suspectType;
	}

	public void setSuspectType(String suspectType) {
		this.suspectType = suspectType;
	}

	public String getCrimeRole() {
		return crimeRole;
	}

	public void setCrimeRole(String crimeRole) {
		this.crimeRole = crimeRole;
	}

	public String getCriRecord() {
		return criRecord;
	}

	public void setCriRecord(String criRecord) {
		this.criRecord = criRecord;
	}

	public String getSuspiciongist() {
		return suspiciongist;
	}

	public void setSuspiciongist(String suspiciongist) {
		this.suspiciongist = suspiciongist;
	}

	public String getApproach() {
		return approach;
	}

	public void setApproach(String approach) {
		this.approach = approach;
	}

	public String getPersonState() {
		return personState;
	}

	public void setPersonState(String personState) {
		this.personState = personState;
	}

	public String getDateofCapture() {
		return dateofCapture;
	}

	public void setDateofCapture(String dateofCapture) {
		this.dateofCapture = dateofCapture;
	}

	public String getCaptureProcess() {
		return captureProcess;
	}

	public void setCaptureProcess(String captureProcess) {
		this.captureProcess = captureProcess;
	}

	public String getXjApprovalTime() {
		return xjApprovalTime;
	}

	public void setXjApprovalTime(String xjApprovalTime) {
		this.xjApprovalTime = xjApprovalTime;
	}

	public String getXjApprover() {
		return xjApprover;
	}

	public void setXjApprover(String xjApprover) {
		this.xjApprover = xjApprover;
	}

	public String getIsxjApproval() {
		return isxjApproval;
	}

	public void setIsxjApproval(String isxjApproval) {
		this.isxjApproval = isxjApproval;
	}

	public String getDetentionTime() {
		return detentionTime;
	}

	public void setDetentionTime(String detentionTime) {
		this.detentionTime = detentionTime;
	}

	public String getDetentionLength() {
		return detentionLength;
	}

	public void setDetentionLength(String detentionLength) {
		this.detentionLength = detentionLength;
	}

	public String getDetentionAddress() {
		return detentionAddress;
	}

	public void setDetentionAddress(String detentionAddress) {
		this.detentionAddress = detentionAddress;
	}

	public String getYcjlApprovalTime() {
		return ycjlApprovalTime;
	}

	public void setYcjlApprovalTime(String ycjlApprovalTime) {
		this.ycjlApprovalTime = ycjlApprovalTime;
	}

	public String getYcjlApprover() {
		return ycjlApprover;
	}

	public void setYcjlApprover(String ycjlApprover) {
		this.ycjlApprover = ycjlApprover;
	}

	public String getIsycjlApproval() {
		return isycjlApproval;
	}

	public void setIsycjlApproval(String isycjlApproval) {
		this.isycjlApproval = isycjlApproval;
	}

	public String getYcDetentiomTime() {
		return ycDetentiomTime;
	}

	public void setYcDetentiomTime(String ycDetentiomTime) {
		this.ycDetentiomTime = ycDetentiomTime;
	}

	public String getQbhsApprovalTime() {
		return qbhsApprovalTime;
	}

	public void setQbhsApprovalTime(String qbhsApprovalTime) {
		this.qbhsApprovalTime = qbhsApprovalTime;
	}

	public String getQbhsApprover() {
		return qbhsApprover;
	}

	public void setQbhsApprover(String qbhsApprover) {
		this.qbhsApprover = qbhsApprover;
	}

	public String getIsqbhsApproval() {
		return isqbhsApproval;
	}

	public void setIsqbhsApproval(String isqbhsApproval) {
		this.isqbhsApproval = isqbhsApproval;
	}

	public String getBailTime() {
		return bailTime;
	}

	public void setBailTime(String bailTime) {
		this.bailTime = bailTime;
	}

	public String getJsjzApprovalTime() {
		return jsjzApprovalTime;
	}

	public void setJsjzApprovalTime(String jsjzApprovalTime) {
		this.jsjzApprovalTime = jsjzApprovalTime;
	}

	public String getJsjzApprover() {
		return jsjzApprover;
	}

	public void setJsjzApprover(String jsjzApprover) {
		this.jsjzApprover = jsjzApprover;
	}

	public String getIsjsjzApproval() {
		return isjsjzApproval;
	}

	public void setIsjsjzApproval(String isjsjzApproval) {
		this.isjsjzApproval = isjsjzApproval;
	}

	public String getSurveillanceTime() {
		return surveillanceTime;
	}

	public void setSurveillanceTime(String surveillanceTime) {
		this.surveillanceTime = surveillanceTime;
	}

	public String getRequestarrestTime() {
		return requestarrestTime;
	}

	public void setRequestarrestTime(String requestarrestTime) {
		this.requestarrestTime = requestarrestTime;
	}

	public String getArrestApprovalTime() {
		return arrestApprovalTime;
	}

	public void setArrestApprovalTime(String arrestApprovalTime) {
		this.arrestApprovalTime = arrestApprovalTime;
	}

	public String getIsArrestApproval() {
		return isArrestApproval;
	}

	public void setIsArrestApproval(String isArrestApproval) {
		this.isArrestApproval = isArrestApproval;
	}

	public String getArrestTime() {
		return arrestTime;
	}

	public void setArrestTime(String arrestTime) {
		this.arrestTime = arrestTime;
	}

	public String getArrestRefuse() {
		return arrestRefuse;
	}

	public void setArrestRefuse(String arrestRefuse) {
		this.arrestRefuse = arrestRefuse;
	}

	public String getDrewReviewTime() {
		return drewReviewTime;
	}

	public void setDrewReviewTime(String drewReviewTime) {
		this.drewReviewTime = drewReviewTime;
	}

	public String getDrewReviewer() {
		return drewReviewer;
	}

	public void setDrewReviewer(String drewReviewer) {
		this.drewReviewer = drewReviewer;
	}

	public String getIsfyApproval() {
		return isfyApproval;
	}

	public void setIsfyApproval(String isfyApproval) {
		this.isfyApproval = isfyApproval;
	}

	public String getArrestviewerTime() {
		return arrestviewerTime;
	}

	public void setArrestviewerTime(String arrestviewerTime) {
		this.arrestviewerTime = arrestviewerTime;
	}

	public String getArrestreviewTime() {
		return arrestreviewTime;
	}

	public void setArrestreviewTime(String arrestreviewTime) {
		this.arrestreviewTime = arrestreviewTime;
	}

	public String getArrestReviewer() {
		return arrestReviewer;
	}

	public void setArrestReviewer(String arrestReviewer) {
		this.arrestReviewer = arrestReviewer;
	}

	public String getIsfhApproval() {
		return isfhApproval;
	}

	public void setIsfhApproval(String isfhApproval) {
		this.isfhApproval = isfhApproval;
	}

	public String getIsysqsApproval() {
		return isysqsApproval;
	}

	public void setIsysqsApproval(String isysqsApproval) {
		this.isysqsApproval = isysqsApproval;
	}

	public String getProsecutedTime() {
		return prosecutedTime;
	}

	public void setProsecutedTime(String prosecutedTime) {
		this.prosecutedTime = prosecutedTime;
	}

	public String getProsecutedCharge() {
		return prosecutedCharge;
	}

	public void setProsecutedCharge(String prosecutedCharge) {
		this.prosecutedCharge = prosecutedCharge;
	}

	public String getNotprosecuteReason() {
		return notprosecuteReason;
	}

	public void setNotprosecuteReason(String notprosecuteReason) {
		this.notprosecuteReason = notprosecuteReason;
	}

	public String getContinueInveReason() {
		return continueInveReason;
	}

	public void setContinueInveReason(String continueInveReason) {
		this.continueInveReason = continueInveReason;
	}

	public String getContinueInveTime() {
		return continueInveTime;
	}

	public void setContinueInveTime(String continueInveTime) {
		this.continueInveTime = continueInveTime;
	}

	public String getCompleteInveTimea() {
		return completeInveTimea;
	}

	public void setCompleteInveTimea(String completeInveTimea) {
		this.completeInveTimea = completeInveTimea;
	}

	public String getCompleteInveTimeb() {
		return completeInveTimeb;
	}

	public void setCompleteInveTimeb(String completeInveTimeb) {
		this.completeInveTimeb = completeInveTimeb;
	}

	public String getProsecutionApptime() {
		return prosecutionApptime;
	}

	public void setProsecutionApptime(String prosecutionApptime) {
		this.prosecutionApptime = prosecutionApptime;
	}

	public String getProsecutionApper() {
		return prosecutionApper;
	}

	public void setProsecutionApper(String prosecutionApper) {
		this.prosecutionApper = prosecutionApper;
	}

	public String getIspzqsApproval() {
		return ispzqsApproval;
	}

	public void setIspzqsApproval(String ispzqsApproval) {
		this.ispzqsApproval = ispzqsApproval;
	}

	public String getProsecuteReviewtime() {
		return prosecuteReviewtime;
	}

	public void setProsecuteReviewtime(String prosecuteReviewtime) {
		this.prosecuteReviewtime = prosecuteReviewtime;
	}

	public String getQstqReviewApptime() {
		return qstqReviewApptime;
	}

	public void setQstqReviewApptime(String qstqReviewApptime) {
		this.qstqReviewApptime = qstqReviewApptime;
	}

	public String getQstqReviewApproval() {
		return qstqReviewApproval;
	}

	public void setQstqReviewApproval(String qstqReviewApproval) {
		this.qstqReviewApproval = qstqReviewApproval;
	}

	public String getIsqsfhApproval() {
		return isqsfhApproval;
	}

	public void setIsqsfhApproval(String isqsfhApproval) {
		this.isqsfhApproval = isqsfhApproval;
	}

	public String getProsecutionReviewTme() {
		return prosecutionReviewTme;
	}

	public void setProsecutionReviewTme(String prosecutionReviewTme) {
		this.prosecutionReviewTme = prosecutionReviewTme;
	}

	public String getOtherApprovalTime() {
		return otherApprovalTime;
	}

	public void setOtherApprovalTime(String otherApprovalTime) {
		this.otherApprovalTime = otherApprovalTime;
	}

	public String getOtherApprover() {
		return otherApprover;
	}

	public void setOtherApprover(String otherApprover) {
		this.otherApprover = otherApprover;
	}

	public String getIsotherApproval() {
		return isotherApproval;
	}

	public void setIsotherApproval(String isotherApproval) {
		this.isotherApproval = isotherApproval;
	}

	public String getOtherDeal() {
		return otherDeal;
	}

	public void setOtherDeal(String otherDeal) {
		this.otherDeal = otherDeal;
	}

	public String getDetentionLimit() {
		return detentionLimit;
	}

	public void setDetentionLimit(String detentionLimit) {
		this.detentionLimit = detentionLimit;
	}

	public String getDetentionPlace() {
		return detentionPlace;
	}

	public void setDetentionPlace(String detentionPlace) {
		this.detentionPlace = detentionPlace;
	}

	public String getCaseTreatmentType() {
		return caseTreatmentType;
	}

	public void setCaseTreatmentType(String caseTreatmentType) {
		this.caseTreatmentType = caseTreatmentType;
	}

	public String getAdminPenaltyApptime() {
		return adminPenaltyApptime;
	}

	public void setAdminPenaltyApptime(String adminPenaltyApptime) {
		this.adminPenaltyApptime = adminPenaltyApptime;
	}

	public String getAdminPenaltyApper() {
		return adminPenaltyApper;
	}

	public void setAdminPenaltyApper(String adminPenaltyApper) {
		this.adminPenaltyApper = adminPenaltyApper;
	}

	public String getIsxzcfApproval() {
		return isxzcfApproval;
	}

	public void setIsxzcfApproval(String isxzcfApproval) {
		this.isxzcfApproval = isxzcfApproval;
	}

	public String getIswarningPunish() {
		return iswarningPunish;
	}

	public void setIswarningPunish(String iswarningPunish) {
		this.iswarningPunish = iswarningPunish;
	}

	public String getFinesNo() {
		return finesNo;
	}

	public void setFinesNo(String finesNo) {
		this.finesNo = finesNo;
	}

	public String getAdminDetentionTime() {
		return adminDetentionTime;
	}

	public void setAdminDetentionTime(String adminDetentionTime) {
		this.adminDetentionTime = adminDetentionTime;
	}

	public String getIsConfiscation() {
		return isConfiscation;
	}

	public void setIsConfiscation(String isConfiscation) {
		this.isConfiscation = isConfiscation;
	}

	public String getIsorderedcase() {
		return isorderedcase;
	}

	public void setIsorderedcase(String isorderedcase) {
		this.isorderedcase = isorderedcase;
	}

	public String getIsWithheldLicenses() {
		return isWithheldLicenses;
	}

	public void setIsWithheldLicenses(String isWithheldLicenses) {
		this.isWithheldLicenses = isWithheldLicenses;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getAdminPenaltyTime() {
		return adminPenaltyTime;
	}

	public void setAdminPenaltyTime(String adminPenaltyTime) {
		this.adminPenaltyTime = adminPenaltyTime;
	}

	public String getPursuitApptime() {
		return pursuitApptime;
	}

	public void setPursuitApptime(String pursuitApptime) {
		this.pursuitApptime = pursuitApptime;
	}

	public String getPursuitApprover() {
		return pursuitApprover;
	}

	public void setPursuitApprover(String pursuitApprover) {
		this.pursuitApprover = pursuitApprover;
	}

	public String getPursuitApprovalTime() {
		return pursuitApprovalTime;
	}

	public void setPursuitApprovalTime(String pursuitApprovalTime) {
		this.pursuitApprovalTime = pursuitApprovalTime;
	}

	public String getIsPursuit() {
		return isPursuit;
	}

	public void setIsPursuit(String isPursuit) {
		this.isPursuit = isPursuit;
	}

	public String getPursuitNo() {
		return pursuitNo;
	}

	public void setPursuitNo(String pursuitNo) {
		this.pursuitNo = pursuitNo;
	}

	public String getTrackApptime() {
		return trackApptime;
	}

	public void setTrackApptime(String trackApptime) {
		this.trackApptime = trackApptime;
	}

	public String getTrackApper() {
		return trackApper;
	}

	public void setTrackApper(String trackApper) {
		this.trackApper = trackApper;
	}

	public String getIsjkApproval() {
		return isjkApproval;
	}

	public void setIsjkApproval(String isjkApproval) {
		this.isjkApproval = isjkApproval;
	}

	public String getExcludeTime() {
		return excludeTime;
	}

	public void setExcludeTime(String excludeTime) {
		this.excludeTime = excludeTime;
	}

	public String getExcludeReason() {
		return excludeReason;
	}

	public void setExcludeReason(String excludeReason) {
		this.excludeReason = excludeReason;
	}

	public String getIsexclude() {
		return isexclude;
	}

	public void setIsexclude(String isexclude) {
		this.isexclude = isexclude;
	}

	public String getAnnex() {
		return annex;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
	}

	public String getInputPerson() {
		return inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

	public String getModifiedPerson() {
		return modifiedPerson;
	}

	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getDistrictTime() {
		return districtTime;
	}

	public void setDistrictTime(String districtTime) {
		this.districtTime = districtTime;
	}

	public String getProvinceTime() {
		return provinceTime;
	}

	public void setProvinceTime(String provinceTime) {
		this.provinceTime = provinceTime;
	}

	public String getTqArrestReviewTime() {
		return tqArrestReviewTime;
	}

	public void setTqArrestReviewTime(String tqArrestReviewTime) {
		this.tqArrestReviewTime = tqArrestReviewTime;
	}

	public String getIsfypzApproval() {
		return isfypzApproval;
	}

	public void setIsfypzApproval(String isfypzApproval) {
		this.isfypzApproval = isfypzApproval;
	}

	public String getIfUpmurder() {
		return ifUpmurder;
	}

	public void setIfUpmurder(String ifUpmurder) {
		this.ifUpmurder = ifUpmurder;
	}

	public String getXjReviewed() {
		return xjReviewed;
	}

	public void setXjReviewed(String xjReviewed) {
		this.xjReviewed = xjReviewed;
	}

	public String getYcjlReviewed() {
		return ycjlReviewed;
	}

	public void setYcjlReviewed(String ycjlReviewed) {
		this.ycjlReviewed = ycjlReviewed;
	}

	public String getQbhsreviewed() {
		return qbhsreviewed;
	}

	public void setQbhsreviewed(String qbhsreviewed) {
		this.qbhsreviewed = qbhsreviewed;
	}

	public String getJsjzReviewed() {
		return jsjzReviewed;
	}

	public void setJsjzReviewed(String jsjzReviewed) {
		this.jsjzReviewed = jsjzReviewed;
	}

	public String getArrestReviewed() {
		return arrestReviewed;
	}

	public void setArrestReviewed(String arrestReviewed) {
		this.arrestReviewed = arrestReviewed;
	}

	public String getArrestRefreviewed() {
		return arrestRefreviewed;
	}

	public void setArrestRefreviewed(String arrestRefreviewed) {
		this.arrestRefreviewed = arrestRefreviewed;
	}

	public String getProsecutReviewed() {
		return prosecutReviewed;
	}

	public void setProsecutReviewed(String prosecutReviewed) {
		this.prosecutReviewed = prosecutReviewed;
	}

	public String getContinueReviewed() {
		return continueReviewed;
	}

	public void setContinueReviewed(String continueReviewed) {
		this.continueReviewed = continueReviewed;
	}

	public String getOtherReviewed() {
		return otherReviewed;
	}

	public void setOtherReviewed(String otherReviewed) {
		this.otherReviewed = otherReviewed;
	}

	public String getAdminPenalReviewed() {
		return adminPenalReviewed;
	}

	public void setAdminPenalReviewed(String adminPenalReviewed) {
		this.adminPenalReviewed = adminPenalReviewed;
	}

	public String getDetoxifReviewed() {
		return detoxifReviewed;
	}

	public void setDetoxifReviewed(String detoxifReviewed) {
		this.detoxifReviewed = detoxifReviewed;
	}

	public String getDetentionReviewed() {
		return detentionReviewed;
	}

	public void setDetentionReviewed(String detentionReviewed) {
		this.detentionReviewed = detentionReviewed;
	}

	public String getArrestApprover() {
		return arrestApprover;
	}

	public void setArrestApprover(String arrestApprover) {
		this.arrestApprover = arrestApprover;
	}

	public String getArrestRefApprover() {
		return arrestRefApprover;
	}

	public void setArrestRefApprover(String arrestRefApprover) {
		this.arrestRefApprover = arrestRefApprover;
	}

	public String getProsecutApproval() {
		return prosecutApproval;
	}

	public void setProsecutApproval(String prosecutApproval) {
		this.prosecutApproval = prosecutApproval;
	}

	public String getContinueApprover() {
		return continueApprover;
	}

	public void setContinueApprover(String continueApprover) {
		this.continueApprover = continueApprover;
	}

	public String getDetoxifApprover() {
		return detoxifApprover;
	}

	public void setDetoxifApprover(String detoxifApprover) {
		this.detoxifApprover = detoxifApprover;
	}

	public String getDetentionApprover() {
		return detentionApprover;
	}

	public void setDetentionApprover(String detentionApprover) {
		this.detentionApprover = detentionApprover;
	}

	public String getXjTransactor() {
		return xjTransactor;
	}

	public void setXjTransactor(String xjTransactor) {
		this.xjTransactor = xjTransactor;
	}

	public String getYcjlTransactor() {
		return ycjlTransactor;
	}

	public void setYcjlTransactor(String ycjlTransactor) {
		this.ycjlTransactor = ycjlTransactor;
	}

	public String getQbhsTransactor() {
		return qbhsTransactor;
	}

	public void setQbhsTransactor(String qbhsTransactor) {
		this.qbhsTransactor = qbhsTransactor;
	}

	public String getJsjzTransactor() {
		return jsjzTransactor;
	}

	public void setJsjzTransactor(String jsjzTransactor) {
		this.jsjzTransactor = jsjzTransactor;
	}

	public String getArrestTransactor() {
		return arrestTransactor;
	}

	public void setArrestTransactor(String arrestTransactor) {
		this.arrestTransactor = arrestTransactor;
	}

	public String getArrestRefTransactor() {
		return arrestRefTransactor;
	}

	public void setArrestRefTransactor(String arrestRefTransactor) {
		this.arrestRefTransactor = arrestRefTransactor;
	}

	public String getDrewTransactor() {
		return drewTransactor;
	}

	public void setDrewTransactor(String drewTransactor) {
		this.drewTransactor = drewTransactor;
	}

	public String getArrvTransactor() {
		return arrvTransactor;
	}

	public void setArrvTransactor(String arrvTransactor) {
		this.arrvTransactor = arrvTransactor;
	}

	public String getArreviewTransactor() {
		return arreviewTransactor;
	}

	public void setArreviewTransactor(String arreviewTransactor) {
		this.arreviewTransactor = arreviewTransactor;
	}

	public String getProsecutTransactor() {
		return prosecutTransactor;
	}

	public void setProsecutTransactor(String prosecutTransactor) {
		this.prosecutTransactor = prosecutTransactor;
	}

	public String getContTransactor() {
		return contTransactor;
	}

	public void setContTransactor(String contTransactor) {
		this.contTransactor = contTransactor;
	}

	public String getProsTransactor() {
		return prosTransactor;
	}

	public void setProsTransactor(String prosTransactor) {
		this.prosTransactor = prosTransactor;
	}

	public String getProsecTransactor() {
		return prosecTransactor;
	}

	public void setProsecTransactor(String prosecTransactor) {
		this.prosecTransactor = prosecTransactor;
	}

	public String getOtherTransactor() {
		return otherTransactor;
	}

	public void setOtherTransactor(String otherTransactor) {
		this.otherTransactor = otherTransactor;
	}

	public String getDetoxifTransactor() {
		return detoxifTransactor;
	}

	public void setDetoxifTransactor(String detoxifTransactor) {
		this.detoxifTransactor = detoxifTransactor;
	}

	public String getDetTransactor() {
		return detTransactor;
	}

	public void setDetTransactor(String detTransactor) {
		this.detTransactor = detTransactor;
	}

	public String getAdminTransactor() {
		return adminTransactor;
	}

	public void setAdminTransactor(String adminTransactor) {
		this.adminTransactor = adminTransactor;
	}

	public String getIsConttran() {
		return isConttran;
	}

	public void setIsConttran(String isConttran) {
		this.isConttran = isConttran;
	}

	public String getIsConttran2() {
		return isConttran2;
	}

	public void setIsConttran2(String isConttran2) {
		this.isConttran2 = isConttran2;
	}

	public String getIsfhpzApproval() {
		return isfhpzApproval;
	}

	public void setIsfhpzApproval(String isfhpzApproval) {
		this.isfhpzApproval = isfhpzApproval;
	}

	public String getIsFines() {
		return isFines;
	}

	public void setIsFines(String isFines) {
		this.isFines = isFines;
	}

	public String getIsadminDetention() {
		return isadminDetention;
	}

	public void setIsadminDetention(String isadminDetention) {
		this.isadminDetention = isadminDetention;
	}

	public String getIsFinesDetention() {
		return isFinesDetention;
	}

	public void setIsFinesDetention(String isFinesDetention) {
		this.isFinesDetention = isFinesDetention;
	}

	public String getSqjdRansactor() {
		return sqjdRansactor;
	}

	public void setSqjdRansactor(String sqjdRansactor) {
		this.sqjdRansactor = sqjdRansactor;
	}

	public String getSqjdReviewer() {
		return sqjdReviewer;
	}

	public void setSqjdReviewer(String sqjdReviewer) {
		this.sqjdReviewer = sqjdReviewer;
	}

	public String getSqjdApproved() {
		return sqjdApproved;
	}

	public void setSqjdApproved(String sqjdApproved) {
		this.sqjdApproved = sqjdApproved;
	}

	public String getSqjdApprovalTime() {
		return sqjdApprovalTime;
	}

	public void setSqjdApprovalTime(String sqjdApprovalTime) {
		this.sqjdApprovalTime = sqjdApprovalTime;
	}

	public String getIfsqjd() {
		return ifsqjd;
	}

	public void setIfsqjd(String ifsqjd) {
		this.ifsqjd = ifsqjd;
	}

	public String getSqkfActor() {
		return sqkfActor;
	}

	public void setSqkfActor(String sqkfActor) {
		this.sqkfActor = sqkfActor;
	}

	public String getSqkfReviewer() {
		return sqkfReviewer;
	}

	public void setSqkfReviewer(String sqkfReviewer) {
		this.sqkfReviewer = sqkfReviewer;
	}

	public String getSqkfApproval() {
		return sqkfApproval;
	}

	public void setSqkfApproval(String sqkfApproval) {
		this.sqkfApproval = sqkfApproval;
	}

	public String getSqkfApprovalTime() {
		return sqkfApprovalTime;
	}

	public void setSqkfApprovalTime(String sqkfApprovalTime) {
		this.sqkfApprovalTime = sqkfApprovalTime;
	}

	public String getIssqkf() {
		return issqkf;
	}

	public void setIssqkf(String issqkf) {
		this.issqkf = issqkf;
	}

	public String getSrjyActor() {
		return srjyActor;
	}

	public void setSrjyActor(String srjyActor) {
		this.srjyActor = srjyActor;
	}

	public String getSrjyReviewer() {
		return srjyReviewer;
	}

	public void setSrjyReviewer(String srjyReviewer) {
		this.srjyReviewer = srjyReviewer;
	}

	public String getSrjyApproved() {
		return srjyApproved;
	}

	public void setSrjyApproved(String srjyApproved) {
		this.srjyApproved = srjyApproved;
	}

	public String getSrjyApprovalTime() {
		return srjyApprovalTime;
	}

	public void setSrjyApprovalTime(String srjyApprovalTime) {
		this.srjyApprovalTime = srjyApprovalTime;
	}

	public String getIssrjy() {
		return issrjy;
	}

	public void setIssrjy(String issrjy) {
		this.issrjy = issrjy;
	}

	public String getSjepeople() {
		return sjepeople;
	}

	public void setSjepeople(String sjepeople) {
		this.sjepeople = sjepeople;
	}

	public String getSjAudit() {
		return sjAudit;
	}

	public void setSjAudit(String sjAudit) {
		this.sjAudit = sjAudit;
	}

	public String getSjApproved() {
		return sjApproved;
	}

	public void setSjApproved(String sjApproved) {
		this.sjApproved = sjApproved;
	}

	public String getSjApprovedTime() {
		return sjApprovedTime;
	}

	public void setSjApprovedTime(String sjApprovedTime) {
		this.sjApprovedTime = sjApprovedTime;
	}

	public String getIssj() {
		return issj;
	}

	public void setIssj(String issj) {
		this.issj = issj;
	}

	public String getContinueInvetimeb() {
		return continueInvetimeb;
	}

	public void setContinueInvetimeb(String continueInvetimeb) {
		this.continueInvetimeb = continueInvetimeb;
	}

	public String getIssrjys() {
		return issrjys;
	}

	public void setIssrjys(String issrjys) {
		this.issrjys = issrjys;
	}

	public String getIszyDetoxif() {
		return iszyDetoxif;
	}

	public void setIszyDetoxif(String iszyDetoxif) {
		this.iszyDetoxif = iszyDetoxif;
	}

	public String getSrjysActor() {
		return srjysActor;
	}

	public void setSrjysActor(String srjysActor) {
		this.srjysActor = srjysActor;
	}

	public String getSrjysApprovalTime() {
		return srjysApprovalTime;
	}

	public void setSrjysApprovalTime(String srjysApprovalTime) {
		this.srjysApprovalTime = srjysApprovalTime;
	}

	public String getSrjysApproved() {
		return srjysApproved;
	}

	public void setSrjysApproved(String srjysApproved) {
		this.srjysApproved = srjysApproved;
	}

	public String getSrjysReviewer() {
		return srjysReviewer;
	}

	public void setSrjysReviewer(String srjysReviewer) {
		this.srjysReviewer = srjysReviewer;
	}

	public String getToArrestTime() {
		return toArrestTime;
	}

	public void setToArrestTime(String toArrestTime) {
		this.toArrestTime = toArrestTime;
	}

	public String getIstoarrest() {
		return istoarrest;
	}

	public void setIstoarrest(String istoarrest) {
		this.istoarrest = istoarrest;
	}

	public String getIsLimitExport() {
		return isLimitExport;
	}

	public void setIsLimitExport(String isLimitExport) {
		this.isLimitExport = isLimitExport;
	}

	public String getLimitReason() {
		return limitReason;
	}

	public void setLimitReason(String limitReason) {
		this.limitReason = limitReason;
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public String getSpecialGroup() {
		return specialGroup;
	}

	public void setSpecialGroup(String specialGroup) {
		this.specialGroup = specialGroup;
	}

	public String getHck_rksj() {
		return hck_rksj;
	}

	public void setHck_rksj(String hck_rksj) {
		this.hck_rksj = hck_rksj;
	}

	public String getHck_gxsj() {
		return hck_gxsj;
	}

	public void setHck_gxsj(String hck_gxsj) {
		this.hck_gxsj = hck_gxsj;
	}

	public CriminalPersonBean getCriminalPerson() {
		return criminalPerson;
	}

	public void setCriminalPerson(CriminalPersonBean criminalPerson) {
		this.criminalPerson = criminalPerson;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}