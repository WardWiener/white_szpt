package com.taiji.pubsec.szpt.ajgl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
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
import com.taiji.pubsec.szpt.ajgl.service.IArchivedFileService;

@Service("archivedFileService")
public class ArchivedFileServiceImpl implements IArchivedFileService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public ArchivedFile findById(String id) {
		return (ArchivedFile) this.dao.findById(ArchivedFile.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocChuanHuanNew findDocChuanHuanNewById(String docId) {
		return (DocChuanHuanNew) this.dao.findById(DocChuanHuanNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocDaiBuNew findDocDaiBuNewById(String docId) {
		return (DocDaiBuNew) this.dao.findById(DocDaiBuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocGongAnXingZhengChuFaNew findDocGongAnXingZhengChuFaNewById(String docId) {
		return (DocGongAnXingZhengChuFaNew) this.dao.findById(DocGongAnXingZhengChuFaNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocJvChuanNew findDocJvChuanNewById(String docId) {
		return (DocJvChuanNew) this.dao.findById(DocJvChuanNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocJvLiuNew findDocJvLiuNewById(String docId) {
		return (DocJvLiuNew) this.dao.findById(DocJvLiuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocKouYaJueDingNew findDocKouYaJueDingNewById(String docId) {
		return (DocKouYaJueDingNew) this.dao.findById(DocKouYaJueDingNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocKouYaJueDingNew2012 findDocKouYaJueDingNew2012ById(String docId) {
		return (DocKouYaJueDingNew2012) this.dao.findById(DocKouYaJueDingNew2012.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocQiangZhiGeLiJieDuNew findDocQiangZhiGeLiJieDuNewById(String docId) {
		return (DocQiangZhiGeLiJieDuNew) this.dao.findById(DocQiangZhiGeLiJieDuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocQiSuYiJianNew findDocQiSuYiJianNewById(String docId) {
		return (DocQiSuYiJianNew) this.dao.findById(DocQiSuYiJianNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocQuBaoHouShenNew findDocQuBaoHouShenNewById(String docId) {
		return (DocQuBaoHouShenNew) this.dao.findById(DocQuBaoHouShenNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocShiFangTongZhiNew findDocShiFangTongZhiNewById(String docId) {
		return (DocShiFangTongZhiNew) this.dao.findById(DocShiFangTongZhiNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocShiFangTongZhiNew2012 findDocShiFangTongZhiNew2012ById(String docId) {
		return (DocShiFangTongZhiNew2012) this.dao.findById(DocShiFangTongZhiNew2012.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocTiQingPiZhunDaiBuNew findDocTiQingPiZhunDaiBuNewById(String docId) {
		return (DocTiQingPiZhunDaiBuNew) this.dao.findById(DocTiQingPiZhunDaiBuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocYanChangJvLiuQiXianNew findDocYanChangJvLiuQiXianNewById(String docId) {
		return (DocYanChangJvLiuQiXianNew) this.dao.findById(DocYanChangJvLiuQiXianNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocZhengJvBaoQuanJueDingNew findDocZhengJvBaoQuanJueDingNewById(String docId) {
		return (DocZhengJvBaoQuanJueDingNew) this.dao.findById(DocZhengJvBaoQuanJueDingNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArchivedFile> findArchivedFileByCase(String caseId) {
		String xql = "select af from ArchivedFile as af where af.caseId = ?";
		return this.dao.findAllByParams(ArchivedFile.class, xql, new Object[]{caseId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocJianShiJueDingZhiXingTongZhiShuNew findDocJianShiJueDingZhiXingTongZhiShuNewById(String docId) {
		return (DocJianShiJueDingZhiXingTongZhiShuNew) this.dao.findById(DocJianShiJueDingZhiXingTongZhiShuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocJieChuJianShiJvZhuJueDingTongZhiShuNew findDocJieChuJianShiJvZhuJueDingTongZhiShuNewById(String docId) {
		return (DocJieChuJianShiJvZhuJueDingTongZhiShuNew) this.dao.findById(DocJieChuJianShiJvZhuJueDingTongZhiShuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocPoAnDengJiBiao findDocPoAnDengJiBiaoById(String docId) {
		return (DocPoAnDengJiBiao) this.dao.findById(DocPoAnDengJiBiao.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocZeLingSheQuJieDuSheQuKangFuJueDingShuNew findDocZeLingSheQuJieDuSheQuKangFuJueDingShuNewById(
			String docId) {
		return (DocZeLingSheQuJieDuSheQuKangFuJueDingShuNew) this.dao.findById(DocZeLingSheQuJieDuSheQuKangFuJueDingShuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocJieChuSheQuJieDuSheQuKangFuTongZhiShuNew findDocJieChuSheQuJieDuSheQuKangFuTongZhiShuNewById(
			String docId) {
		return (DocJieChuSheQuJieDuSheQuKangFuTongZhiShuNew) this.dao.findById(DocJieChuSheQuJieDuSheQuKangFuTongZhiShuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocChuanHuanNewXingShi2012 findDocChuanHuanNewXingShi2012ById(String docId) {
		return (DocChuanHuanNewXingShi2012) this.dao.findById(DocChuanHuanNewXingShi2012.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocJieShouAnJianDengJiBiaoNew findDocJieShouAnJianDengJiBiaoNewById(String docId) {
		return (DocJieShouAnJianDengJiBiaoNew) this.dao.findById(DocJieShouAnJianDengJiBiaoNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocLiAnJueDingShuNew findDocLiAnJueDingShuNewById(String docId) {
		return (DocLiAnJueDingShuNew) this.dao.findById(DocLiAnJueDingShuNew.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocJieAnShenPiBiao findDocJieAnShenPiBiaoById(String docId) {
		return (DocJieAnShenPiBiao) this.dao.findById(DocJieAnShenPiBiao.class, docId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DocJieChuQuBaoHouShenJueDingShuNew findDocJieChuQuBaoHouShenJueDingShuNewById(String docId) {
		return (DocJieChuQuBaoHouShenJueDingShuNew) this.dao.findById(DocJieChuQuBaoHouShenJueDingShuNew.class, docId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DocBianGengJiYaQiXianTongZhiShu findDocBianGengJiYaQiXianTongZhiShuById(String docId) {
		return (DocBianGengJiYaQiXianTongZhiShu) this.dao.findById(DocBianGengJiYaQiXianTongZhiShu.class, docId);
	}
}
