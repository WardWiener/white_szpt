package com.taiji.pubsec.szpt.dagl.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.persistence.dao.JpaSqlDaoImpl;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.attachment.service.impl.AttachmentCustomizedServiceImpl;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalFord;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseTagService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CriminalFordService;
import com.taiji.pubsec.szpt.caseanalysis.util.CommonBeanModelConverterUtil;
import com.taiji.pubsec.szpt.dagl.action.bean.AcceptPoliceInfoBean;
import com.taiji.pubsec.szpt.dagl.action.bean.AlarmInfoBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CaseAndPoliceInfoBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CaseJzwsBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CaseVideoBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CriminalObjectBean;
import com.taiji.pubsec.szpt.dagl.action.bean.CriminalPersonBean;
import com.taiji.pubsec.szpt.dagl.action.bean.FeedbackBean;
import com.taiji.pubsec.szpt.dagl.action.bean.InstructionAndFeedbackBean;
import com.taiji.pubsec.szpt.dagl.action.util.ConstanBean;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONObject;

/**
 * 一案一档Action
 * 
 * @author WangLei
 *
 */
@Controller("yaydAction")
@Scope("prototype")
public class YaydAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;

	@Resource
	private CaseService caseService;// 案件接口

	@Resource
	private CaseTagService caseTagService;// 案件打标接口

	@Resource
	private CommonBeanModelConverterUtil commonBeanModelConverterUtil;

	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;
	
	@Resource
	private IInstructionService instructionService;// 指令接口

	@Resource
	private IUnitService unitService;// 单位接口

	@Resource
	private IPersonService personService;// 人员接口

	@Resource
	private InfoSnapshotService infoSnapshotService;// 快照接口

	@Resource
	private CriminalFordService criminalFordService;// 涉案团伙接口

	@Resource
	private JpaSqlDaoImpl sqlDao;
	

	private String acceptPoliceId;// 接警id
	private String location;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	private Map<String,List<FeedbackBean>> feedbackMap = new HashMap<String, List<FeedbackBean>>();
	
	private Map<String,List<InstructionAndFeedbackBean>> instructionfeedbackMap = new HashMap<String, List<InstructionAndFeedbackBean>>();
   
	private List<CriminalPersonBean> shrBeanList = new ArrayList<CriminalPersonBean>();
	private List<CriminalPersonBean> dsrBeanList = new ArrayList<CriminalPersonBean>();
	private List<CriminalPersonBean> barBeanList = new ArrayList<CriminalPersonBean>();
	private List<CriminalPersonBean> xyrBeanList = new ArrayList<CriminalPersonBean>();
	
	@SuppressWarnings("unchecked")
	public String showYaydDetailPage(){
		
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String saveVideoForCaseCode(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String caseCode = String.valueOf(data.get("caseCode"));
		List<String> fileBeanLst = (List<String>)data.get("fileBeanLst");
		for(String str : fileBeanLst){
			Attachment att = attachmentCustomizedService.findById(str);
			att.setTargetId(caseCode);
			att.setType(com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase.class.getName());
			attachmentCustomizedService.save(att);
		}
		return SUCCESS;
	}
	
	/**
	 * 获取视频音频
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryVideoByCaseCode() {
		Map<String, Object> data = JSONObject.fromObject(read());
		String caseCode = String.valueOf(data.get("caseCode"));
		List<CaseVideoBean> jcjsp = new ArrayList<CaseVideoBean>();
		List<CaseVideoBean> jcjyp = new ArrayList<CaseVideoBean>();
		List<CaseVideoBean> qtsp = new ArrayList<CaseVideoBean>();
		List<CaseVideoBean> qtyp = new ArrayList<CaseVideoBean>();
		List<CaseVideoBean> jcjLst = queryVideo(caseCode);
		for(CaseVideoBean temp : jcjLst){
			if(temp.getFileType().equals("2")){
				jcjsp.add(temp);
			}else if(temp.getFileType().equals("3")){
				jcjyp.add(temp);
			}
		}
		List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType(caseCode, com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase.class.getName());
		for(Attachment temp : attLst){
			String hz = temp.getName().substring(temp.getName().lastIndexOf('.')+1, temp.getName().length());
			if(hz.equals("mp4") || hz.equals("mpeg4")){
				CaseVideoBean bean = new CaseVideoBean();
				bean.setFileName(temp.getName());
				bean.setPlayAddress(this.getRequest().getContextPath() + "/yayd/downloadFile.action?attachmentId=" + temp.getId());
				qtsp.add(bean);
			}else if(hz.equals("mp3") || hz.equals("mpeg3")){
				CaseVideoBean bean = new CaseVideoBean();
				bean.setFileName(temp.getName());
				bean.setPlayAddress(this.getRequest().getContextPath() + "/yayd/downloadFile.action?attachmentId=" + temp.getId());
				qtyp.add(bean);
			}
		}
		resultMap.put("jcjsp", jcjsp);
		resultMap.put("jcjyp", jcjyp);
		resultMap.put("qtsp", qtsp);
		resultMap.put("qtyp", qtyp);
		return SUCCESS;
	}
	/**
	 * 查询涉案团伙
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryCriminalFordByCaseCode() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String caseCode = (String) rqst.get("caseCode");
		List<CriminalFord> cfs = criminalFordService.findCriminalFordsByCaseCode(caseCode);
		List<Map<String, String>> cfsMap = new ArrayList<Map<String, String>>();
		if (cfs == null || cfs.isEmpty()) {
			resultMap.put("cfsMap", cfsMap);
			return SUCCESS;
		}
		for (CriminalFord cf : cfs) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", cf.getName());
			map.put("count", String.valueOf(cf.getCount()));
			map.put("mainPeople", cf.getMainPeople());
			map.put("form", cf.getForm());
			map.put("resort", cf.getResort());
			map.put("peculiarity", cf.getPeculiarity());
			cfsMap.add(map);
		}
		resultMap.put("cfsMap", cfsMap);
		return SUCCESS;
	}

	/**
	 * 查询警情信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryCriminalPoliceCaseByCode() {
		String jsonStr = read();
		if (jsonStr == null || jsonStr.isEmpty())
			return SUCCESS;
		Map<String, Object> rqst = JSONObject.fromObject(jsonStr);
		String caseCode = (String) rqst.get("caseCode");
		String sql = "select j.jjsj,j.ty_jqlx_mc,j.bjr,j.jqdz,j.jqgy from t_xsajfx_aj_jq t , t_zhzats_jwzh_jq j where t.jqid = j.id and t.ajbh = ?  ";
		List<AlarmInfoBean> isList = (List<AlarmInfoBean>) this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, caseCode);
					}

				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List list = new ArrayList();
						while (rs.next()) {
							AlarmInfoBean info = new AlarmInfoBean();
							info.setReportTime(rs.getString("jjsj"));
							info.setReportMode(rs.getString("ty_jqlx_mc"));
							info.setRvcallPerson(rs.getString("bjr"));
							info.setHappenPlace(rs.getString("jqdz"));
							info.setReportDetails(rs.getString("jqgy"));
							list.add(info);
						}
						return list;
					}
				});
		resultMap.put("result", isList);
		return SUCCESS;
	}

	/**
	 * 根据案件编号查询所有接警信息 根据接警时间倒序排列
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryAcceptPoliceInfoByCaseCode() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm yyyy年MM月dd日");
		String jsonStr = read();
		if (jsonStr == null || jsonStr.isEmpty())
			return SUCCESS;
		Map<String, Object> rqst = JSONObject.fromObject(jsonStr);
		String caseCode = (String) rqst.get("caseCode");
		String sql = "select t.id,t.ty_zhdy_mc,t.cjsj from t_zhzats_jwzh_cj t ,t_xsajfx_aj_jq jq  where jq.jqid = t.fact_jq_id  and jq.ajbh= ? order by cjsj desc";
		List<AcceptPoliceInfoBean> isList = (List<AcceptPoliceInfoBean>) this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, caseCode);
					}
				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List list = new ArrayList();
						while (rs.next()) {
							AcceptPoliceInfoBean info = new AcceptPoliceInfoBean();
							info.setAccOrderCell(rs.getString("ty_zhdy_mc"));
							String strDate = sdf.format(rs.getDate("cjsj"));
							info.setAccTime(strDate);
							info.setId(rs.getString("id"));
							list.add(info);
						}
						return list;
					}
				});
		resultMap.put("accPoliceResult", isList);
		for(int i = 0 ; i < isList.size() ; i++){
        feedbackMap.put(isList.get(i).getId(), this.queryFeedBackContentByAcceptPoliceCode(isList.get(i).getId())); 
		}
		return SUCCESS;
	}

	/**
	 * 根据案件编号 查询反馈信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryCriminaFeedbackByCode() {
		String jsonStr = read();
		if (jsonStr == null || jsonStr.isEmpty())
			return SUCCESS;
		Map<String, Object> rqst = JSONObject.fromObject(jsonStr);
		String caseCode = (String) rqst.get("caseCode");
		String sql = "select f.fkrxm, f.fksj, f.fknr from t_xsajfx_aj_jq t , "
				+ "t_zhzats_jwzh_cj c, t_zhzats_jwzh_fk f where  t.ajbh = ?  and t.jqid = c.fact_jq_id  and  f.fact_cj_id=c.id ";
		List<FeedbackBean> isList = (List<FeedbackBean>) this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, caseCode);
					}

				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List list = new ArrayList();
						while (rs.next()) {
							FeedbackBean info = new FeedbackBean();
							info.setFeedbackContent(rs.getString("fknr"));
							info.setFeedbackPerson(rs.getString("fkrxm"));
							info.setFeedbackTime(rs.getString("fksj"));
							list.add(info);
						}
						return list;
					}
				});
		resultMap.put("result", isList);
		return SUCCESS;
	}

	/**
	 * 查询嫌疑人MAC快照
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySuspectMacSnapshot() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String caseCode = (String) rqst.get("caseCode");
		List<InfoSnapshot> isList = infoSnapshotService.findInfoSnapshotByTargetIdAndTypeAndCode(caseCode,
				CriminalBasicCase.class.getName(), INFO_SNAPSHOT_MODULE.XSAJFX_XYRMACFX.getValue());
		resultMap.put("result", isList);
		return SUCCESS;
	}

	/**
	 * 根据案件code查询打标数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryCaseTagByCaseCode() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String caseCode = (String) rqst.get("caseCode");
		CaseTag ct = caseTagService.findCaseTag(caseCode);
		resultMap.put("ctb", commonBeanModelConverterUtil.caseTagToCaseTagBean(ct));
		return SUCCESS;
	}

	/**
	 * 查询指令及反馈
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryInstructionAndFeedbackByCaseCode() {
		Map<String, Object> data = JSONObject.fromObject(read());
		String caseCode = String.valueOf(data.get("caseCode"));
		CaseAndPoliceInfoBean capi = this.queryPoliceInfoIdByCaseCode(caseCode);
		if(capi == null) return SUCCESS;
		List<Instruction> instructionLst = instructionService.findInstructionByRelatedObjectId(capi.getPoliceInfoId());
		List<InstructionAndFeedbackBean> instructionBeanLst = instructionChangeInstructionAndFeedbackBean(
				instructionLst);
		List<InstructionAndFeedbackBean> instructionAndFeedbackBeanLst = new ArrayList<InstructionAndFeedbackBean>();
		if (null != instructionBeanLst) {
			instructionAndFeedbackBeanLst = instructionBeanLst;
			for(int i = 0 ; i < instructionBeanLst.size() ; i++){
				instructionfeedbackMap.put(instructionBeanLst.get(i).getAcceptObjectId(),
						queryInstructFeedBackInfobyInstructId(instructionBeanLst.get(i).getAcceptObjectId()));
			}
		}
		resultMap.put("result", instructionAndFeedbackBeanLst);
		return SUCCESS;
	}
	/**
	 * 查询涉案人员
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public String queryInvolvedCasePerson(){
    	Map<String, Object> data = JSONObject.fromObject(read());
		String caseCode = String.valueOf(data.get("caseCode"));
		shrBeanList = this.queryCriminalPerson(caseCode, ConstanBean.INVOLVE_SHR);
		dsrBeanList = this.queryCriminalPerson(caseCode, ConstanBean.INVOLVE_DSR);
		barBeanList = this.queryCriminalPerson(caseCode, ConstanBean.INVOLVE_BAR);
		xyrBeanList = this.querySuspectPerson(caseCode);
    	return SUCCESS;
    }
	/**
	 * 查询涉案物品
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public String queryInvolvedThing(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String caseCode = String.valueOf(data.get("caseCode"));
		String sql = "select t.bgq,t.wpbh,t.wptz,t.ajbh,t.bgg,t.wswh,t.xyrxm,t.wpmc "
				+ "from t_xsajfx_sawp_ajgl t "
				+ "where  t.ajbh = ? ";
		com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase  casebasic = caseService.findCaseByCode(caseCode);
		List<CriminalObjectBean> criminalObjectBeans = new ArrayList<CriminalObjectBean>();
		criminalObjectBeans = (List<CriminalObjectBean>)this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, caseCode);
					}
				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CriminalObjectBean> list = new ArrayList<CriminalObjectBean>();
						while (rs.next()) {
							CriminalObjectBean info = new CriminalObjectBean();
							info.setObjid(rs.getString("wpbh"));
							info.setObjectTz(rs.getString("wptz"));
							info.setSavefield(rs.getString("bgq"));
							info.setSaveCupboard(rs.getString("bgg"));
							info.setWswh(rs.getString("wswh"));
							info.setSuspectName(rs.getString("xyrxm"));
							info.setObjectName(rs.getString("wpmc"));
							info.setCaseCode(rs.getString("ajbh"));
							info.setCaseName(casebasic.getCaseName());
							list.add(info);
						}
						return list;
					}
				});
		resultMap.put("result", criminalObjectBeans);
		return SUCCESS;
	}
	/**
	 * 查询音视频
	 * @return
	 */
	@SuppressWarnings("unchecked")
	  private List<CaseVideoBean> queryVideo(String caseCode){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sql = "select t.wjmc,t.wjlx,t.kssj,t.jssj,t.gxsj,t.zt,t.bfdz,t.yldz,t.jqbm,t.lzrdw,t.lzrxm,t.lzrjh,t.lzsc,t.wjdx"
		  		+ " from  t_zhzats_jwzh_jqsp t , t_xsajfx_aj_jq j , t_zhzats_jwzh_jq  jq"
		  		+ " where j.ajbh = ? and j.jqid = jq.id  and jq.bm = t.jqbm";
		  List<CaseVideoBean> caseVideoBeanList = (List<CaseVideoBean>)this.sqlDao.getJdbcTemplate().query(sql,
					new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setString(1, caseCode);
						}
					}, new ResultSetExtractor() {
						public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<CaseVideoBean> list = new ArrayList<CaseVideoBean>();
							while (rs.next()) {
								CaseVideoBean info = new CaseVideoBean();
								info.setFileName(rs.getString("wjmc"));
								info.setPoliceCode(rs.getString("jqbm"));
								info.setFileType(rs.getString("wjlx"));
								info.setPlayAddress(rs.getString("bfdz"));
								info.setStartTime(sdf.format(rs.getDate("kssj")));
								info.setEndTime(sdf.format(rs.getDate("jssj")));
								info.setStartTime(sdf.format(rs.getDate("jssj")));
								info.setUpdateTime(sdf.format(rs.getDate("gxsj")));
								info.setStatus("zt");
								info.setYlAddress("yldz");
								info.setLzrPoliceNum("lzrjh");
								info.setLzrName("lzrxm");
								info.setLzrUnit("lzrUnit");
								info.setLzsc("lzsc");
								info.setWjdx("wjdx");
								list.add(info);
							}
							return list;
						}
					});
		  return caseVideoBeanList;
	  }
	/**
	 * 查询嫌疑人
	 * @return
	 */
	@SuppressWarnings("unchecked")
    private  List<CriminalPersonBean> querySuspectPerson(String caseCode){
		String sql = "select r.bm , r.sfzh , r.ryxm, r.xb  "
				+ "from t_xsajfx_xyrgx t , t_xsajfx_sary r  "
				+ "where t.rybh = r.rybh and t.ajbh = ? ";
		List<CriminalPersonBean> isxyrBeanList = (List<CriminalPersonBean>)this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, caseCode);
					}
				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CriminalPersonBean> list = new ArrayList<CriminalPersonBean>();
						while (rs.next()) {
							CriminalPersonBean info = new CriminalPersonBean();
							info.setAliasName(rs.getString("bm"));
							info.setName(rs.getString("ryxm"));
							info.setIdcardNo(rs.getString("sfzh"));
							info.setSex(rs.getString("xb"));
							list.add(info);
						}
						return list;
					}
				});
		return isxyrBeanList;
    }
	/**
	 * 查询卷宗文书
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryJZWS(){
		  Map<String, Object> data = JSONObject.fromObject(read());
		  String caseCode = String.valueOf(data.get("caseCode"));
		String sql = "select t.wslx, t.wsmc ,t.fwdz from t_xsajfx_jzws t  where  t.ajbh = ?";
		List<CaseJzwsBean> isList = (List<CaseJzwsBean>)this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, caseCode);
					}
				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CaseJzwsBean> list = new ArrayList<CaseJzwsBean>();
						while (rs.next()) {
							CaseJzwsBean info = new CaseJzwsBean();
							info.setWsName(rs.getString("wsmc"));
							String url = ConstanBean.AJGL_SERVER_URL+rs.getString("fwdz");
							info.setAskAddress(url);
							info.setCaseCode(caseCode);
							info.setWsType(rs.getString("wslx"));
							list.add(info);
						}
						return list;
					}
				});
		resultMap.put("result", isList);
		return SUCCESS;
	}
	/**
	 * 查询证据笔录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String queryZjbl(){
		 Map<String, Object> data = JSONObject.fromObject(read());
		 String caseCode = String.valueOf(data.get("caseCode"));
		 String sql =" select t.blnr ,t.lrsj,t.blmc from t_xsajfx_zjbl t where t.ajbh = ? ";
		 List<CaseJzwsBean> isList = (List<CaseJzwsBean>)this.sqlDao.getJdbcTemplate().query(sql,
					new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setString(1, caseCode);
						}
					}, new ResultSetExtractor() {
						public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<CaseJzwsBean> list = new ArrayList<CaseJzwsBean>();
							while (rs.next()) {
								CaseJzwsBean info = new CaseJzwsBean();
								info.setWsName(rs.getString("blmc"));
								String url = ConstanBean.AJGL_SERVER_URL+rs.getString("blnr");
								info.setAskAddress(url);
								info.setCaseCode(caseCode);
								info.setLrsj(rs.getString("lrsj"));
								list.add(info);
							}
							return list;
						}
					});
			resultMap.put("result", isList);
		 return SUCCESS;
	}
	/**
	 * 指令集合转指令反馈集合
	 * 
	 * @param instructionLst
	 *            指令集合
	 * @return
	 */
	private List<InstructionAndFeedbackBean> instructionChangeInstructionAndFeedbackBean(
			List<Instruction> instructionLst) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm yyyy年MM月dd日");
		List<InstructionAndFeedbackBean> instructionAndFeedbackBeanLst = new ArrayList<InstructionAndFeedbackBean>();
		if (null != instructionLst && instructionLst.size() > 0) {
			for (Instruction instruction : instructionLst) {
				if(instruction.getInstructionReceiveSubjects().size() > 0){
					for (InstructionReceiveSubject irs : instruction.getInstructionReceiveSubjects()) {
						InstructionAndFeedbackBean instructionAndFeedbackBean = new InstructionAndFeedbackBean();
						instructionAndFeedbackBean.setId(instruction.getId());
						instructionAndFeedbackBean.setCreateTimeLong(
								instruction.getCreateTime() == null ? 0 : instruction.getCreateTime().getTime());
						Unit createUnit = unitService.findUnitById(instruction.getCreatePeopleDepartmentId());
						instructionAndFeedbackBean.setCreateDepartment(null != createUnit ? createUnit.getShortName() : "");
						String str = "";
						if (Unit.class.getName().equals(irs.getReceiveSubjectType())) {
							str = str + unitService.findUnitById(irs.getReceiveSubjectId()).getShortName();
							instructionAndFeedbackBean.setAcceptObjectId(irs.getId());
						}
						instructionAndFeedbackBean.setAcceptDepartments(str);
						instructionAndFeedbackBean.setContent(instruction.getContent());
						instructionAndFeedbackBean.setCreateDate(sdf.format(instruction.getCreateTime()));
						instructionAndFeedbackBean.setIsFeedBack(false);
						instructionAndFeedbackBeanLst.add(instructionAndFeedbackBean);
					 }
					}else{
						InstructionAndFeedbackBean instructionAndFeedbackBean = new InstructionAndFeedbackBean();
						instructionAndFeedbackBean.setId(instruction.getId());
						instructionAndFeedbackBean.setCreateTimeLong(
								instruction.getCreateTime() == null ? 0 : instruction.getCreateTime().getTime());
						Unit createUnit = unitService.findUnitById(instruction.getCreatePeopleDepartmentId());
						instructionAndFeedbackBean.setCreateDepartment(null != createUnit ? createUnit.getShortName() : "");
						instructionAndFeedbackBean.setContent(instruction.getContent());
						instructionAndFeedbackBean.setCreateDate(sdf.format(instruction.getCreateTime()));
						instructionAndFeedbackBean.setIsFeedBack(false);
						
						instructionAndFeedbackBeanLst.add(instructionAndFeedbackBean);
					}
				}
				
			return instructionAndFeedbackBeanLst;
		}
		return null;
	}

	/**
	 * 指令接收主体集合转指令反馈集合
	 * 
	 * @param instructionReceiveSubjectFeedbackLst
	 * @return
	 */
	private List<InstructionAndFeedbackBean> instructionReceiveSubjectFeedbackChangeInstructionAndFeedbackBean(
			List<InstructionReceiveSubjectFeedback> instructionReceiveSubjectFeedbackLst) {
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat sj = new SimpleDateFormat("HH:mm:ss");
		if (null != instructionReceiveSubjectFeedbackLst && instructionReceiveSubjectFeedbackLst.size() > 0) {
			List<InstructionAndFeedbackBean> instructionAndFeedbackBeanLst = new ArrayList<InstructionAndFeedbackBean>();
			for (InstructionReceiveSubjectFeedback instructionFeedback : instructionReceiveSubjectFeedbackLst) {
				InstructionAndFeedbackBean instructionAndFeedbackBean = new InstructionAndFeedbackBean();
				instructionAndFeedbackBean.setId(instructionFeedback.getId());
				instructionAndFeedbackBean.setCreateDate(dt.format(instructionFeedback.getFeedbackTime()));
				instructionAndFeedbackBean.setCreateTime(sj.format(instructionFeedback.getFeedbackTime()));
				instructionAndFeedbackBean.setCreateTimeLong(instructionFeedback.getFeedbackTime() == null ? 0
						: instructionFeedback.getFeedbackTime().getTime());
				Unit createUnit = unitService.findUnitById(instructionFeedback.getInstructionReceiveSubject()
						.getInstruction().getCreatePeopleDepartmentId());
				instructionAndFeedbackBean.setCreateDepartment(null != createUnit ? createUnit.getShortName() : "");
				instructionAndFeedbackBean.setFeedbackContent(instructionFeedback.getFeedbackContent());
				instructionAndFeedbackBean.setIsFeedBack(true);
				Person person = personService.findById(instructionFeedback.getFeedbackPeopleId());
				instructionAndFeedbackBean.setFeedbackDepartment(person.getOrganization().getShortName());
				instructionAndFeedbackBeanLst.add(instructionAndFeedbackBean);
			}
			return instructionAndFeedbackBeanLst;
		}
		return null;
	}


	/**
	 * 根据接警id返回所有的反馈内容 根据反馈时间倒序排列
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List queryFeedBackContentByAcceptPoliceCode(String acceptPoliceId) {
        String  feedbackSql = "select t.fksj,t.ty_zhdy_mc,t.fknr from t_zhzats_jwzh_fk t where t.fact_cj_id = ? order by fksj desc";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm yyyy年MM月dd日");
        List<FeedbackBean> isList = (List<FeedbackBean>) this.sqlDao.getJdbcTemplate().query(feedbackSql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, acceptPoliceId);
					}

				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List list = new ArrayList();
						while (rs.next()) {
							FeedbackBean info = new FeedbackBean();
							info.setFeedbackContent(rs.getString("fknr"));
							String strDate = sdf.format(rs.getDate("fksj"));
							info.setFeedbackTime(strDate);
							info.setFeedbackOrderCell(rs.getString("ty_zhdy_mc"));
							list.add(info);
						}
						return list;
					}
				});
		
		return isList;
	}
	
	/**
	 * 根据案件编码获取其警情ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private CaseAndPoliceInfoBean queryPoliceInfoIdByCaseCode(String caseCode){
		String  sql = "select t.jqid from t_xsajfx_aj_jq t where t.ajbh = ?"; 
		CaseAndPoliceInfoBean caseAndPoliceInfoBean = (CaseAndPoliceInfoBean)this.sqlDao.getJdbcTemplate().query(sql,
					new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setString(1, caseCode);
						}

					}, new ResultSetExtractor() {
						public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
							CaseAndPoliceInfoBean casePolicBean = new CaseAndPoliceInfoBean();
							while(rs.next()){
								casePolicBean.setPoliceInfoId(rs.getString("jqid"));
								casePolicBean.setCaseCode(caseCode);
							}
							return casePolicBean;
						}
					});
		return caseAndPoliceInfoBean;
	}
	/**
	 * 根据指令接收主体ID 查询反馈内容
	 * @param instructAccObjId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<InstructionAndFeedbackBean> queryInstructFeedBackInfobyInstructId(String instructAccObjId){
		String sql = "select t.fkrxm,t.fksj,t.fknr from t_zlgl_zljsztfk t where t.zljszt_id =  ?";
		  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm yyyy年MM月dd日");
		  List<InstructionAndFeedbackBean>  isList = (List<InstructionAndFeedbackBean>)this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, instructAccObjId);
					}

				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<InstructionAndFeedbackBean> list = new ArrayList<InstructionAndFeedbackBean>();
						while (rs.next()) {
							InstructionAndFeedbackBean info = new InstructionAndFeedbackBean();
							info.setFeedbackContent(rs.getString("fknr"));
							String strDate = sdf.format(rs.getDate("fksj"));
							info.setFeedbackTime(strDate);
							info.setFeedbackDepartment(rs.getString("fkrxm"));
							list.add(info);
						}
						return list;
					}
				});
		return isList;
	}
	/**
	 * 根据人员类型以及案件编号查询 涉案人员
	 * @param caseCode
	 * @param personType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<CriminalPersonBean> queryCriminalPerson(String caseCode ,String personType){
		String sql = "select r.bm , r.sfzh , r.ryxm, r.xb"
				+ " from t_xsajfx_shrgx t , t_xsajfx_sary r "
				+ " where r.rybh = t.rybh and t.salx = ?  and t.ajbh = ?";
		List<CriminalPersonBean> isList = (List<CriminalPersonBean>)this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, personType);
						ps.setString(2, caseCode);
					}

				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CriminalPersonBean> list = new ArrayList<CriminalPersonBean>();
						while (rs.next()) {
							CriminalPersonBean info = new CriminalPersonBean();
							info.setAliasName(rs.getString("bm"));
							info.setName(rs.getString("ryxm"));
							info.setIdcardNo(rs.getString("sfzh"));
							info.setSex(rs.getString("xb"));
							list.add(info);
						}
						return list;
					}
				});
		return isList;
	}
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getAcceptPoliceId() {
		return acceptPoliceId;
	}

	public void setAcceptPoliceId(String acceptPoliceId) {
		this.acceptPoliceId = acceptPoliceId;
	}

	public Map<String, List<FeedbackBean>> getFeedbackMap() {
		return feedbackMap;
	}

	public void setFeedbackMap(Map<String, List<FeedbackBean>> feedbackMap) {
		this.feedbackMap = feedbackMap;
	}

	public Map<String, List<InstructionAndFeedbackBean>> getInstructionfeedbackMap() {
		return instructionfeedbackMap;
	}

	public void setInstructionfeedbackMap(Map<String, List<InstructionAndFeedbackBean>> instructionfeedbackMap) {
		this.instructionfeedbackMap = instructionfeedbackMap;
	}

	public List<CriminalPersonBean> getShrBeanList() {
		return shrBeanList;
	}

	public void setShrBeanList(List<CriminalPersonBean> shrBeanList) {
		this.shrBeanList = shrBeanList;
	}

	public List<CriminalPersonBean> getDsrBeanList() {
		return dsrBeanList;
	}

	public void setDsrBeanList(List<CriminalPersonBean> dsrBeanList) {
		this.dsrBeanList = dsrBeanList;
	}

	public List<CriminalPersonBean> getBarBeanList() {
		return barBeanList;
	}

	public void setBarBeanList(List<CriminalPersonBean> barBeanList) {
		this.barBeanList = barBeanList;
	}

	public List<CriminalPersonBean> getXyrBeanList() {
		return xyrBeanList;
	}

	public void setXyrBeanList(List<CriminalPersonBean> xyrBeanList) {
		this.xyrBeanList = xyrBeanList;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
