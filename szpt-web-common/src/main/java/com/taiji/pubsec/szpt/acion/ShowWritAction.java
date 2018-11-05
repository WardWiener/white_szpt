package com.taiji.pubsec.szpt.acion;

import javax.annotation.Resource;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.ajgl.bean.DocBianGengJiYaQiXianTongZhiShuBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocChuanHuanNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocChuanHuanNewXingShi2012Bean;
import com.taiji.pubsec.szpt.ajgl.bean.DocDaiBuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocGongAnXingZhengChuFaNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocJianShiJueDingZhiXingTongZhiShuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocJieAnShenPiBiaoBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocJieChuJianShiJvZhuJueDingTongZhiShuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocJieChuQuBaoHouShenJueDingShuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocJieShouAnJianDengJiBiaoNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocJvChuanNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocJvLiuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocKouYaJueDingNew2012Bean;
import com.taiji.pubsec.szpt.ajgl.bean.DocLiAnJueDingShuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocPoAnDengJiBiaoBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocQiSuYiJianNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocQiangZhiGeLiJieDuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocQuBaoHouShenNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocShiFangTongZhiNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocTiQingPiZhunDaiBuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocYanChangJvLiuQiXianNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocZeLingSheQuJieDuSheQuKangFuJueDingShuNewBean;
import com.taiji.pubsec.szpt.ajgl.bean.DocZhengJvBaoQuanJueDingNewBean;
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
import com.taiji.pubsec.szpt.ajgl.model.DocJieShouAnJianDengJiBiaoNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJvChuanNew;
import com.taiji.pubsec.szpt.ajgl.model.DocJvLiuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocKouYaJueDingNew2012;
import com.taiji.pubsec.szpt.ajgl.model.DocLiAnJueDingShuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocPoAnDengJiBiao;
import com.taiji.pubsec.szpt.ajgl.model.DocQiSuYiJianNew;
import com.taiji.pubsec.szpt.ajgl.model.DocQiangZhiGeLiJieDuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocQuBaoHouShenNew;
import com.taiji.pubsec.szpt.ajgl.model.DocShiFangTongZhiNew;
import com.taiji.pubsec.szpt.ajgl.model.DocTiQingPiZhunDaiBuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocYanChangJvLiuQiXianNew;
import com.taiji.pubsec.szpt.ajgl.model.DocZeLingSheQuJieDuSheQuKangFuJueDingShuNew;
import com.taiji.pubsec.szpt.ajgl.model.DocZhengJvBaoQuanJueDingNew;
import com.taiji.pubsec.szpt.ajgl.service.IArchivedFileService;
import com.taiji.pubsec.szpt.util.WritBeanCopierConverter;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;

/**
 * 案件查询Action
 * 
 * @author huangda
 *
 */
@Controller("showWritAction")
@Scope("prototype")
public class ShowWritAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final String TYPE_XB = "xb";// 性别
	private static final String TYPE_SF = "sf";// 是否
	private static final String ENABLED = "1";// 启用
	public final static char[] upper = "〇一二三四五六七八九十".toCharArray();
	
	@Resource
	private IArchivedFileService archivedFileService;
	@Resource
	private IDictionaryItemService dictionaryItemService;

	private String archivedFileId;

	private String writId;

	private String paperId;
	private String paperType;

	private DocDaiBuNewBean dbzBean = new DocDaiBuNewBean();
	private DocChuanHuanNewBean chzBean = new DocChuanHuanNewBean();
	private DocChuanHuanNewXingShi2012Bean chzxsBean = new DocChuanHuanNewXingShi2012Bean();
	private DocJvLiuNewBean jlzBean = new DocJvLiuNewBean();
	private DocQuBaoHouShenNewBean qbhsBean = new DocQuBaoHouShenNewBean();
	private DocShiFangTongZhiNewBean sftzsBean = new DocShiFangTongZhiNewBean();
	private DocTiQingPiZhunDaiBuNewBean tqpzdbsBean = new DocTiQingPiZhunDaiBuNewBean();
	private DocYanChangJvLiuQiXianNewBean ycjlqxBean = new DocYanChangJvLiuQiXianNewBean();
	private DocKouYaJueDingNew2012Bean kyjdsBean = new DocKouYaJueDingNew2012Bean();
	private DocGongAnXingZhengChuFaNewBean xzcfBean = new DocGongAnXingZhengChuFaNewBean();
	private DocJvChuanNewBean jczBean = new DocJvChuanNewBean();
	private DocQiangZhiGeLiJieDuNewBean qzgljdBean = new DocQiangZhiGeLiJieDuNewBean();
	private DocQiSuYiJianNewBean qsyjBean = new DocQiSuYiJianNewBean();
	private DocZhengJvBaoQuanJueDingNewBean zjbqjdBean = new DocZhengJvBaoQuanJueDingNewBean();
	private DocJianShiJueDingZhiXingTongZhiShuNewBean jsjzBean = new DocJianShiJueDingZhiXingTongZhiShuNewBean();
	private DocJieChuJianShiJvZhuJueDingTongZhiShuNewBean jcjsjzBean = new DocJieChuJianShiJvZhuJueDingTongZhiShuNewBean();
	private DocJieShouAnJianDengJiBiaoNewBean sadjBean = new DocJieShouAnJianDengJiBiaoNewBean();
	private DocLiAnJueDingShuNewBean lajdBean = new DocLiAnJueDingShuNewBean();
	private DocPoAnDengJiBiaoBean padjBean = new DocPoAnDengJiBiaoBean();
	private DocZeLingSheQuJieDuSheQuKangFuJueDingShuNewBean zlsqjdBean = new DocZeLingSheQuJieDuSheQuKangFuJueDingShuNewBean();
	private DocJieChuQuBaoHouShenJueDingShuNewBean jcqbhsBean = new DocJieChuQuBaoHouShenJueDingShuNewBean();
	private DocJieAnShenPiBiaoBean jaspBean = new DocJieAnShenPiBiaoBean();
	private DocBianGengJiYaQiXianTongZhiShuBean bgjyqxBean = new DocBianGengJiYaQiXianTongZhiShuBean();
	
	/**
	 * 文书跳转action
	 * @return
	 */
	public String checkWrit() {
		if (archivedFileId != null) {
			ArchivedFile af = archivedFileService.findById(archivedFileId);
			if (af != null) {
				writId = af.getSourceId();
				return af.getType();
			} else {
				return "error";
			}
		} else if (paperId != null && paperType != null) {
			writId = paperId;
			return paperType;
		} else {
			return "error";
		}
	}
	
	/**
	 * 逮捕证
	 * 
	 * @return
	 */
	public String findDbzInfo() {
		DocDaiBuNew doc = archivedFileService.findDocDaiBuNewById(writId);
		copyBean(doc, dbzBean, null);
		dbzBean.setC17(findSF(dbzBean.getC17()));
		dbzBean.setA10(findXB(dbzBean.getA10()));
		dbzBean.setB12(findXB(dbzBean.getB12()));
		dbzBean.setC12(findXB(dbzBean.getC12()));
		return SUCCESS;
	}

	/**
	 * 传唤证
	 * 
	 * @return
	 */
	public String findChzInfo() {
		DocChuanHuanNew doc = archivedFileService
				.findDocChuanHuanNewById(writId);
		copyBean(doc, chzBean, null);
		return SUCCESS;
	}

	/**
	 * 传唤证刑事
	 * 
	 * @return
	 */
	public String findChzxsInfo() {
		DocChuanHuanNewXingShi2012 doc = archivedFileService
				.findDocChuanHuanNewXingShi2012ById(writId);
		copyBean(doc, chzxsBean, null);
		chzxsBean.setA10(findXB(chzxsBean.getA10()));
		chzxsBean.setB9(findXB(chzxsBean.getB9()));
		chzxsBean.setC9(findXB(chzxsBean.getC9()));
		return SUCCESS;
	}
	
	/**
	 * 拘留证
	 * 
	 * @return
	 */
	public String findJlzInfo() {
		DocJvLiuNew doc = archivedFileService.findDocJvLiuNewById(writId);
		copyBean(doc, jlzBean, null);
		jlzBean.setA10(findXB(jlzBean.getA10()));
		jlzBean.setB10(findXB(jlzBean.getB10()));
		jlzBean.setC10(findXB(jlzBean.getC10()));
		jlzBean.setC17(findSF(jlzBean.getC17()));
		return SUCCESS;
	}

	/**
	 * 取保候审决定通知书
	 * 
	 * @return
	 */
	public String findQbhsInfo() {
		DocQuBaoHouShenNew doc = archivedFileService
				.findDocQuBaoHouShenNewById(writId);
		copyBean(doc, qbhsBean, null);
		qbhsBean.setA10(findXB(qbhsBean.getA10()));
		qbhsBean.setA15(findXB(qbhsBean.getA15()));
		qbhsBean.setB8(findXB(qbhsBean.getB8()));
		qbhsBean.setC8(findXB(qbhsBean.getC8()));
		qbhsBean.setD10(findXB(qbhsBean.getD10()));
		return SUCCESS;
	}

	/**
	 * 释放通知书
	 * 
	 * @return
	 */
	public String findSftzsInfo() {
		DocShiFangTongZhiNew doc = archivedFileService
				.findDocShiFangTongZhiNewById(writId);
		copyBean(doc, sftzsBean, null);
		sftzsBean.setA9(findXB(sftzsBean.getA9()));
		sftzsBean.setB8(findXB(sftzsBean.getB8()));
		sftzsBean.setC8(findXB(sftzsBean.getC8()));
		sftzsBean.setD10(findXB(sftzsBean.getD10()));
		return SUCCESS;
	}

	/**
	 * 提请批准逮捕书
	 * 
	 * @return
	 */
	public String findTqpzdbsInfo() {
		DocTiQingPiZhunDaiBuNew doc = archivedFileService
				.findDocTiQingPiZhunDaiBuNewById(writId);
		copyBean(doc, tqpzdbsBean, null);
		return SUCCESS;
	}

	/**
	 * 延长拘留期限通知书
	 * 
	 * @return
	 */
	public String findYcjlqxInfo() {
		DocYanChangJvLiuQiXianNew doc = archivedFileService
				.findDocYanChangJvLiuQiXianNewById(writId);
		copyBean(doc, ycjlqxBean, null);
		ycjlqxBean.setA10(findXB(ycjlqxBean.getA10()));
		ycjlqxBean.setB11(findXB(ycjlqxBean.getB11()));
		ycjlqxBean.setC11(findXB(ycjlqxBean.getC11()));
		return SUCCESS;
	}

	/**
	 * 扣押决定书
	 * 
	 * @return
	 */
	public String findKyjdsInfo() {
		DocKouYaJueDingNew2012 doc = archivedFileService
				.findDocKouYaJueDingNew2012ById(writId);
		copyBean(doc, kyjdsBean, null);
		kyjdsBean.setA10(findXB(kyjdsBean.getA10()));
		kyjdsBean.setB8(findXB(kyjdsBean.getB8()));
		kyjdsBean.setC8(findXB(kyjdsBean.getC8()));
		return SUCCESS;
	}

	/**
	 * 公安行政处罚决定书
	 * 
	 * @return
	 */
	public String findXzcfjdsInfo() {
		DocGongAnXingZhengChuFaNew doc = archivedFileService
				.findDocGongAnXingZhengChuFaNewById(writId);
		copyBean(doc, xzcfBean, null);
		return SUCCESS;
	}
	
	/**
	 * 拘传证
	 * 
	 * @return
	 */
	public String findJczInfo() {
		DocJvChuanNew doc = archivedFileService
				.findDocJvChuanNewById(writId);
		copyBean(doc, jczBean, null);
		jczBean.setA10(findXB(jczBean.getA10()));
		jczBean.setB10(findXB(jczBean.getB10()));
		return SUCCESS;
	}
	
	/**
	 * 强制隔离戒毒决定书
	 * 
	 * @return
	 */
	public String findQzgljdInfo() {
		DocQiangZhiGeLiJieDuNew doc = archivedFileService
				.findDocQiangZhiGeLiJieDuNewById(writId);
		copyBean(doc, qzgljdBean, null);
		qzgljdBean.setB7(findXB(qzgljdBean.getB7()));
		return SUCCESS;
	}
	
	/**
	 * 起诉意见书
	 * 
	 * @return
	 */
	public String findQsyjsInfo() {
		DocQiSuYiJianNew doc = archivedFileService
				.findDocQiSuYiJianNewById(writId);
		copyBean(doc, qsyjBean, null);
		return SUCCESS;
	}
	
	/**
	 * 证据保全决定书
	 * 
	 * @return
	 */
	public String findZjbqjdsInfo() {
		DocZhengJvBaoQuanJueDingNew doc = archivedFileService
				.findDocZhengJvBaoQuanJueDingNewById(writId);
		copyBean(doc, zjbqjdBean, null);
		return SUCCESS;
	} 
	
	/**
	 * 监视居住决定书
	 * 
	 * @return
	 */
	public String findJsjzjdsInfo() {
		DocJianShiJueDingZhiXingTongZhiShuNew doc = archivedFileService
				.findDocJianShiJueDingZhiXingTongZhiShuNewById(writId);
		copyBean(doc, jsjzBean, null);
		jsjzBean.setA10(findXB(jsjzBean.getA10()));
		jsjzBean.setA16(findSF(jsjzBean.getA16()));
		jsjzBean.setB8(findXB(jsjzBean.getB8()));
		jsjzBean.setC8(findXB(jsjzBean.getC8()));
		jsjzBean.setD11(findXB(jsjzBean.getD11()));
		jsjzBean.setD17(findSF(jsjzBean.getD17()));
		return SUCCESS;
	}
	
	/**
	 * 解除监视居住决定书
	 * 
	 * @return
	 */
	public String findJcjsjzjdsInfo() {
		DocJieChuJianShiJvZhuJueDingTongZhiShuNew doc = archivedFileService
				.findDocJieChuJianShiJvZhuJueDingTongZhiShuNewById(writId);
		copyBean(doc, jcjsjzBean, null);
		jcjsjzBean.setA10(findXB(jcjsjzBean.getA10()));
		jcjsjzBean.setB8(findXB(jcjsjzBean.getB8()));
		jcjsjzBean.setC8(findXB(jcjsjzBean.getC8()));
		jcjsjzBean.setD10(findXB(jcjsjzBean.getD10()));
		return SUCCESS;
	}
	
	/**
	 * 受案登记表
	 * 
	 * @return
	 */
	public String findSadjbInfo() {
		DocJieShouAnJianDengJiBiaoNew doc = archivedFileService
				.findDocJieShouAnJianDengJiBiaoNewById(writId);
		copyBean(doc, sadjBean, null);
		sadjBean.setA3(findXB(sadjBean.getA3()));
		return SUCCESS;
	}
	
	/**
	 * 立案决定书
	 * 
	 * @return
	 */
	public String findLajdsInfo() {
		DocLiAnJueDingShuNew doc = archivedFileService
				.findDocLiAnJueDingShuNewById(writId);
		copyBean(doc, lajdBean, null);
		lajdBean.setA10(findXB(lajdBean.getA10()));
		return SUCCESS;
	}
	
	/**
	 * 破案登记表
	 * 
	 * @return
	 */
	public String findPadjbInfo() {
		DocPoAnDengJiBiao doc = archivedFileService
				.findDocPoAnDengJiBiaoById(writId);
		copyBean(doc, padjBean, null);
		padjBean.setA20(findXB(padjBean.getA20()));
		padjBean.setA21(findXB(padjBean.getA21()));
		padjBean.setA22(findXB(padjBean.getA22()));
		padjBean.setA23(findXB(padjBean.getA23()));
		padjBean.setA24(findXB(padjBean.getA24()));
		padjBean.setA25(findXB(padjBean.getA25()));
		padjBean.setA26(findXB(padjBean.getA26()));
		padjBean.setA27(findXB(padjBean.getA27()));
		padjBean.setA28(findXB(padjBean.getA28()));
		padjBean.setA29(findXB(padjBean.getA29()));
		return SUCCESS;
	}
	
	/**
	 * 责令社区戒毒决定书
	 * 
	 * @return
	 */
	public String findZlsqjdInfo() {
		DocZeLingSheQuJieDuSheQuKangFuJueDingShuNew doc = archivedFileService
				.findDocZeLingSheQuJieDuSheQuKangFuJueDingShuNewById(writId);
		copyBean(doc, zlsqjdBean, null);
		zlsqjdBean.setB7(findXB(zlsqjdBean.getB7()));
		return SUCCESS;
	}
	
	/**
	 * 解除取保候审决定书
	 * 
	 * @return
	 */
	public String findJcqbhsInfo() {
		DocJieChuQuBaoHouShenJueDingShuNew doc = archivedFileService
				.findDocJieChuQuBaoHouShenJueDingShuNewById(writId);
		copyBean(doc, jcqbhsBean, null);
		jcqbhsBean.setA10(findXB(jcqbhsBean.getA10()));
		jcqbhsBean.setB12(findXB(jcqbhsBean.getB12()));
		jcqbhsBean.setC8(findXB(jcqbhsBean.getC8()));
		jcqbhsBean.setD8(findXB(jcqbhsBean.getD8()));
		jcqbhsBean.setE10(findXB(jcqbhsBean.getE10()));
		return SUCCESS;
	}
	 
	/**
	 * 行政结案审批表
	 * 
	 * @return
	 */
	public String findJaspbInfo() {
		DocJieAnShenPiBiao doc = archivedFileService
				.findDocJieAnShenPiBiaoById(writId);
		copyBean(doc, jcqbhsBean, null);
		jaspBean.setA7(findXB(jaspBean.getA7()));
		return SUCCESS;
	}
	
	/**
	 * 变更羁押期限通知书
	 * 
	 * @return
	 */
	public String findBgjyqxInfo() {
		DocBianGengJiYaQiXianTongZhiShu doc = archivedFileService
				.findDocBianGengJiYaQiXianTongZhiShuById(writId);
		copyBean(doc, bgjyqxBean, null);
		bgjyqxBean.setA8(findXB(bgjyqxBean.getA8()));
		bgjyqxBean.setB10(findXB(bgjyqxBean.getB10()));
		bgjyqxBean.setC10(findXB(bgjyqxBean.getC10()));
		bgjyqxBean.setD10(findXB(bgjyqxBean.getD10()));
		return SUCCESS;
	}
	
	/**
	 * 性别code转name
	 * 
	 * @param 性别code
	 * @return 性别name
	 */
	private String findXB(String code) {
		DictionaryItem data = dictionaryItemService
				.findDictionaryItemByDicTypeCodeAndItemCode(TYPE_XB, code,
						ENABLED);
		return data != null ? data.getName() : "";
	}

	/**
	 * 是否code转name
	 * 
	 * @param code
	 * @return name
	 */
	private String findSF(String code) {
		DictionaryItem data = dictionaryItemService
				.findDictionaryItemByDicTypeCodeAndItemCode(TYPE_SF, code,
						ENABLED);
		return data != null ? data.getName() : "";
	}
	
	/**
	 * 自定义beanCopy
	 * @param source
	 * @param target
	 * @param dateFmt
	 */
	private static void copyBean(Object source, Object target, String dateFmt) {
		BeanCopier copier = BeanCopier.create(source.getClass(),
				target.getClass(), true);
		WritBeanCopierConverter wbcc = new WritBeanCopierConverter(dateFmt);
		copier.copy(source, target, wbcc);
	}

	
	
	public String getWritId() {
		return writId;
	}

	public void setWritId(String writId) {
		this.writId = writId;
	}

	public String getArchivedFileId() {
		return archivedFileId;
	}

	public void setArchivedFileId(String archivedFileId) {
		this.archivedFileId = archivedFileId;
	}

	public DocDaiBuNewBean getDbzBean() {
		return dbzBean;
	}

	public void setDbzBean(DocDaiBuNewBean dbzBean) {
		this.dbzBean = dbzBean;
	}

	public DocChuanHuanNewBean getChzBean() {
		return chzBean;
	}

	public void setChzBean(DocChuanHuanNewBean chzBean) {
		this.chzBean = chzBean;
	}

	public DocJvLiuNewBean getJlzBean() {
		return jlzBean;
	}

	public void setJlzBean(DocJvLiuNewBean jlzBean) {
		this.jlzBean = jlzBean;
	}

	public DocQuBaoHouShenNewBean getQbhsBean() {
		return qbhsBean;
	}

	public void setQbhsBean(DocQuBaoHouShenNewBean qbhsBean) {
		this.qbhsBean = qbhsBean;
	}

	public DocShiFangTongZhiNewBean getSftzsBean() {
		return sftzsBean;
	}

	public void setSftzsBean(DocShiFangTongZhiNewBean sftzsBean) {
		this.sftzsBean = sftzsBean;
	}

	public DocTiQingPiZhunDaiBuNewBean getTqpzdbsBean() {
		return tqpzdbsBean;
	}

	public void setTqpzdbsBean(DocTiQingPiZhunDaiBuNewBean tqpzdbsBean) {
		this.tqpzdbsBean = tqpzdbsBean;
	}

	public DocYanChangJvLiuQiXianNewBean getYcjlqxBean() {
		return ycjlqxBean;
	}

	public void setYcjlqxBean(DocYanChangJvLiuQiXianNewBean ycjlqxBean) {
		this.ycjlqxBean = ycjlqxBean;
	}

	public DocKouYaJueDingNew2012Bean getKyjdsBean() {
		return kyjdsBean;
	}

	public void setKyjdsBean(DocKouYaJueDingNew2012Bean kyjdsBean) {
		this.kyjdsBean = kyjdsBean;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public DocGongAnXingZhengChuFaNewBean getXzcfBean() {
		return xzcfBean;
	}

	public void setXzcfBean(DocGongAnXingZhengChuFaNewBean xzcfBean) {
		this.xzcfBean = xzcfBean;
	}

	public DocJvChuanNewBean getJczBean() {
		return jczBean;
	}

	public void setJczBean(DocJvChuanNewBean jczBean) {
		this.jczBean = jczBean;
	}

	public DocQiangZhiGeLiJieDuNewBean getQzgljdBean() {
		return qzgljdBean;
	}

	public void setQzgljdBean(DocQiangZhiGeLiJieDuNewBean qzgljdBean) {
		this.qzgljdBean = qzgljdBean;
	}

	public DocQiSuYiJianNewBean getQsyjBean() {
		return qsyjBean;
	}

	public void setQsyjBean(DocQiSuYiJianNewBean qsyjBean) {
		this.qsyjBean = qsyjBean;
	}

	public DocZhengJvBaoQuanJueDingNewBean getZjbqjdBean() {
		return zjbqjdBean;
	}

	public void setZjbqjdBean(DocZhengJvBaoQuanJueDingNewBean zjbqjdBean) {
		this.zjbqjdBean = zjbqjdBean;
	}

	public DocJianShiJueDingZhiXingTongZhiShuNewBean getJsjzBean() {
		return jsjzBean;
	}

	public void setJsjzBean(DocJianShiJueDingZhiXingTongZhiShuNewBean jsjzBean) {
		this.jsjzBean = jsjzBean;
	}

	public DocJieChuJianShiJvZhuJueDingTongZhiShuNewBean getJcjsjzBean() {
		return jcjsjzBean;
	}

	public void setJcjsjzBean(
			DocJieChuJianShiJvZhuJueDingTongZhiShuNewBean jcjsjzBean) {
		this.jcjsjzBean = jcjsjzBean;
	}

	public DocJieShouAnJianDengJiBiaoNewBean getSadjBean() {
		return sadjBean;
	}

	public void setSadjBean(DocJieShouAnJianDengJiBiaoNewBean sadjBean) {
		this.sadjBean = sadjBean;
	}

	public DocLiAnJueDingShuNewBean getLajdBean() {
		return lajdBean;
	}

	public void setLajdBean(DocLiAnJueDingShuNewBean lajdBean) {
		this.lajdBean = lajdBean;
	}

	public DocChuanHuanNewXingShi2012Bean getChzxsBean() {
		return chzxsBean;
	}

	public void setChzxsBean(DocChuanHuanNewXingShi2012Bean chzxsBean) {
		this.chzxsBean = chzxsBean;
	}

	public DocZeLingSheQuJieDuSheQuKangFuJueDingShuNewBean getZlsqjdBean() {
		return zlsqjdBean;
	}

	public void setZlsqjdBean(DocZeLingSheQuJieDuSheQuKangFuJueDingShuNewBean zlsqjdBean) {
		this.zlsqjdBean = zlsqjdBean;
	}

	public DocPoAnDengJiBiaoBean getPadjBean() {
		return padjBean;
	}

	public void setPadjBean(DocPoAnDengJiBiaoBean padjBean) {
		this.padjBean = padjBean;
	}

	public DocJieChuQuBaoHouShenJueDingShuNewBean getJcqbhsBean() {
		return jcqbhsBean;
	}

	public void setJcqbhsBean(DocJieChuQuBaoHouShenJueDingShuNewBean jcqbhsBean) {
		this.jcqbhsBean = jcqbhsBean;
	}
	
	public DocJieAnShenPiBiaoBean getJaspBean() {
		return jaspBean;
	}

	public void setJaspBean(DocJieAnShenPiBiaoBean jaspBean) {
		this.jaspBean = jaspBean;
	}
	
	public DocBianGengJiYaQiXianTongZhiShuBean getBgjyqxBean() {
		return bgjyqxBean;
	}

	public void setBgjyqxBean(DocBianGengJiYaQiXianTongZhiShuBean bgjyqxBean) {
		this.bgjyqxBean = bgjyqxBean;
	}
}
