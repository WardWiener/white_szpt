package com.taiji.pubsec.szpt.ajgl.service;

import java.util.List;

import com.taiji.pubsec.szpt.ajgl.model.ArchivedFile;
import com.taiji.pubsec.szpt.ajgl.model.DocBianGengJiYaQiXianTongZhiShu;
import com.taiji.pubsec.szpt.ajgl.model.DocChuanHuanNew;
import com.taiji.pubsec.szpt.ajgl.model.DocChuanHuanNewXingShi2012;
import com.taiji.pubsec.szpt.ajgl.model.DocDaiBuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocGongAnXingZhengChuFaNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJianShiJueDingZhiXingTongZhiShuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJieAnShenPiBiao;
import com.taiji.pubsec.szpt.ajgl.model.DocJieChuJianShiJvZhuJueDingTongZhiShuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJieChuQuBaoHouShenJueDingShuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJieChuSheQuJieDuSheQuKangFuTongZhiShuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJieShouAnJianDengJiBiaoNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJvChuanNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJvLiuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocKouYaJueDingNew;
import com.taiji.pubsec.szpt.ajgl.model.DocKouYaJueDingNew2012;
import com.taiji.pubsec.szpt.ajgl.model.DocLiAnJueDingShuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocPoAnDengJiBiao;
import com.taiji.pubsec.szpt.ajgl.model.DocQiSuYiJianNew;
import com.taiji.pubsec.szpt.ajgl.model.DocQiangZhiGeLiJieDuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocQuBaoHouShenNew;
import com.taiji.pubsec.szpt.ajgl.model.DocShiFangTongZhiNew;
import com.taiji.pubsec.szpt.ajgl.model.DocShiFangTongZhiNew2012;
import com.taiji.pubsec.szpt.ajgl.model.DocTiQingPiZhunDaiBuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocYanChangJvLiuQiXianNew;
import com.taiji.pubsec.szpt.ajgl.model.DocZeLingSheQuJieDuSheQuKangFuJueDingShuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocZhengJvBaoQuanJueDingNew;

/**
 * 卷宗文书服务接口
 * @author chengkai
 *
 */
public interface IArchivedFileService {
	
	/**
	 * 通过id查询
	 * @param id id
	 * @return 返回卷宗文书信息
	 */
	public ArchivedFile findById(String id);
	
	/**
	 * 通过id查询(行政)传唤证信息
	 * @param docId 文书id
	 * @return (行政)传唤证实体信息
	 */
	public DocChuanHuanNew findDocChuanHuanNewById(String docId);
	
	/**
	 * 通过id查询（刑事）逮捕证信息
	 * @param docId 文书id
	 * @return （刑事）逮捕证实体信息
	 */
	public DocDaiBuNew findDocDaiBuNewById(String docId);
	
	/**
	 * 通过id查询(行政)公安行政处罚决定书信息
	 * @param docId 文书id
	 * @return (行政)公安行政处罚决定书实体信息
	 */
	public DocGongAnXingZhengChuFaNew findDocGongAnXingZhengChuFaNewById(String docId);
	
	/**
	 * 通过id查询（刑事）拘传证信息
	 * @param docId 文书id
	 * @return （刑事）拘传证实体信息
	 */
	public DocJvChuanNew findDocJvChuanNewById(String docId);
	
	/**
	 * 通过id查询（刑事）拘留证信息
	 * @param docId 文书id
	 * @return （刑事）拘留证实体信息
	 */
	public DocJvLiuNew findDocJvLiuNewById(String docId);
	
	/**
	 * 通过id查询查封/扣押决定书信息
	 * @param docId 文书id
	 * @return 查封/扣押决定书实体信息
	 */
	public DocKouYaJueDingNew findDocKouYaJueDingNewById(String docId);
	
	/**
	 * 通过id查询扣押决定书(new_2012)信息
	 * @param docId 文书id
	 * @return 扣押决定书(new_2012)实体信息
	 */
	public DocKouYaJueDingNew2012 findDocKouYaJueDingNew2012ById(String docId);
	
	/**
	 * 通过id查询(行政)强制隔离戒毒决定书信息
	 * @param docId 文书id
	 * @return (行政)强制隔离戒毒决定书实体信息
	 */
	public DocQiangZhiGeLiJieDuNew findDocQiangZhiGeLiJieDuNewById(String docId);
	
	/**
	 * 通过id查询（刑事）起诉意见书（无呈请提请起诉报告书）信息
	 * @param docId 文书id
	 * @return （刑事）起诉意见书（无呈请提请起诉报告书）实体信息
	 */
	public DocQiSuYiJianNew findDocQiSuYiJianNewById(String docId);
	
	/**
	 * 通过id查询（刑事）取保候审决定书/执行通知书信息
	 * @param docId 文书id
	 * @return （刑事）取保候审决定书/执行通知书实体信息
	 */
	public DocQuBaoHouShenNew findDocQuBaoHouShenNewById(String docId);
	
	/**
	 * 通过id查询（刑事）释放通知书信息
	 * @param docId 文书id
	 * @return （刑事）释放通知书实体信息
	 */
	public DocShiFangTongZhiNew findDocShiFangTongZhiNewById(String docId);
	
	/**
	 * 通过id查询（刑事）释放通知书信息
	 * @param docId 文书id
	 * @return （刑事）释放通知书实体信息
	 */
	public DocShiFangTongZhiNew2012 findDocShiFangTongZhiNew2012ById(String docId);
	
	/**
	 * 通过id查询（刑事）提请批准逮捕书信息
	 * @param docId 文书id
	 * @return （刑事）提请批准逮捕书实体信息
	 */
	public DocTiQingPiZhunDaiBuNew findDocTiQingPiZhunDaiBuNewById(String docId);
	
	/**
	 * 通过id查询（刑事）延长拘留期限通知书信息
	 * @param docId 文书id
	 * @return （刑事）延长拘留期限通知书实体信息
	 */
	public DocYanChangJvLiuQiXianNew findDocYanChangJvLiuQiXianNewById(String docId);
	
	/**
	 * 通过id查询证据保全决定书（无证据保全清单）信息
	 * @param docId 文书id
	 * @return 证据保全决定书（无证据保全清单）实体信息
	 */
	public DocZhengJvBaoQuanJueDingNew findDocZhengJvBaoQuanJueDingNewById(String docId);
	
	/**
	 * 通过id查询监视居住决定执行通知书信息
	 * @param docId 文书id
	 * @return 监视居住决定执行通知书实体信息
	 */
	public DocJianShiJueDingZhiXingTongZhiShuNew findDocJianShiJueDingZhiXingTongZhiShuNewById(String docId);
	
	/**
	 * 通过id查询解除监视居住决定通知书信息
	 * @param docId 文书id
	 * @return 解除监视居住决定通知书实体信息
	 */
	public DocJieChuJianShiJvZhuJueDingTongZhiShuNew findDocJieChuJianShiJvZhuJueDingTongZhiShuNewById(String docId);
	
	/**
	 * 通过id查询破案登记表信息
	 * @param docId 文书id
	 * @return 破案登记表实体信息
	 */
	public DocPoAnDengJiBiao findDocPoAnDengJiBiaoById(String docId);
	
	/**
	 * 通过id查询责令社区戒毒 社区康复决定书信息
	 * @param docId 文书id
	 * @return 责令社区戒毒 社区康复决定书实体信息
	 */
	public DocZeLingSheQuJieDuSheQuKangFuJueDingShuNew findDocZeLingSheQuJieDuSheQuKangFuJueDingShuNewById(String docId);
	
	/**
	 * 通过id查询解除社区戒毒 社区康复通知书信息
	 * @param docId 文书id
	 * @return 解除社区戒毒 社区康复通知书实体信息
	 */
	public DocJieChuSheQuJieDuSheQuKangFuTongZhiShuNew findDocJieChuSheQuJieDuSheQuKangFuTongZhiShuNewById(String docId);
	
	/**
	 * 通过id查询(刑事)传唤证信息
	 * @param docId 文书id
	 * @return (刑事)传唤证实体信息
	 */
	public DocChuanHuanNewXingShi2012 findDocChuanHuanNewXingShi2012ById(String docId);
	
	/**
	 * 通过id查询接受案件登记表信息
	 * @param docId 文书id
	 * @return 接受案件登记表实体信息
	 */
	public DocJieShouAnJianDengJiBiaoNew findDocJieShouAnJianDengJiBiaoNewById(String docId);
	
	/**
	 * 通过id查询立案决定书信息
	 * @param docId 文书id
	 * @return 立案决定书实体信息
	 */
	public DocLiAnJueDingShuNew findDocLiAnJueDingShuNewById(String docId);
	
	/**
	 * 通过id查询(行政)结案审批表信息
	 * @param docId 文书id
	 * @return (行政)结案审批表实体信息
	 */
	public DocJieAnShenPiBiao findDocJieAnShenPiBiaoById(String docId);
	
	/**
	 * 通过案件id查询案件文书关系信息列表
	 * @param caseId 案件id
	 * @return 返回案件文书关心信息列表
	 */
	List<ArchivedFile> findArchivedFileByCase(String caseId);
	
	/**
	/**
	 * 通过id查询解除取保候审决定书/通知书信息
	 * @param docId 文书id
	 * @return 解除取保候审决定书/通知书实体信息
	 */
	public DocJieChuQuBaoHouShenJueDingShuNew findDocJieChuQuBaoHouShenJueDingShuNewById(String docId);
	
	/**
	 * 通过id查询变更羁押期限通知书信息
	 * @param docId 文书id
	 * @return 变更羁押期限通知书实体信息
	 */
	public DocBianGengJiYaQiXianTongZhiShu findDocBianGengJiYaQiXianTongZhiShuById(String docId);
}