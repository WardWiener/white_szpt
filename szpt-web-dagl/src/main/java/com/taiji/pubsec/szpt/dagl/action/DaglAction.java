package com.taiji.pubsec.szpt.dagl.action;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.persistence.dao.JpaSqlDaoImpl;
import com.taiji.pubsec.szpt.ajgl.service.ICaseExecutionProcessService;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.dagl.action.bean.CriminalBasicCaseDetailBean;
import com.taiji.pubsec.szpt.dagl.action.bean.FeedbackBean;
import com.taiji.pubsec.szpt.dagl.bean.YaydBean;
import com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE;

import net.sf.json.JSONObject;

@Controller("daglAction")
@Scope("prototype")
public class DaglAction extends BaseAction{

	@Resource
	private ICriminalCaseService criminalCaseService ;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource
	private ICaseExecutionProcessService caseExecutionProcessService ;
	@Resource
	private FullTextSearchService fullTextSearchService;
	@Resource
	private JpaSqlDaoImpl sqlDao;
	@Resource
	private InfoSnapshotService infoSnapshotService;// 快照接口

	private List<CriminalBasicCase> criminalBasicCaseList;
	private Integer start ;
	private Integer length ;
	private Integer totalNum ;
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private List<YaydBean> yaydBeans = new ArrayList<YaydBean>() ;

	private String searchText ;
	
	public String findYayd(){
		Map<String,Object> data = JSONObject.fromObject(read());
		Map<String,Object> conditions = new HashMap<String, Object>() ;
		String caseCodeOrName = String.valueOf(data.get("searchText"));
		List<CriminalBasicCaseDetailBean> cbdbLst = this.queryCriminalBasicCasesByCodeOrName(caseCodeOrName);
		if(null != cbdbLst && cbdbLst.size() >0 ){
			for(CriminalBasicCaseDetailBean cbdb : cbdbLst){
				String sql = "select count(*) from t_xsajfx_xyrgx t where  t.ajbh = ? ";
				Integer  xyrNum = this.sqlDao.getJdbcTemplate().queryForObject(sql, new String[]{cbdb.getCaseCode()},Integer.class);
				List<InfoSnapshot> isList = infoSnapshotService.findInfoSnapshotByTargetIdAndTypeAndCode(cbdb.getCaseCode(),
						CriminalBasicCase.class.getName(), INFO_SNAPSHOT_MODULE.XSAJFX_XYRMACFX.getValue());
				cbdb.setXyrNum(xyrNum);
				if(isList != null){
					cbdb.setAnalSnopNum(isList.size());
				}else{
					cbdb.setAnalSnopNum(0);
				}
				String sawpSql = "select count(*) from t_xsajfx_sawp_ajgl t where  t.ajbh = ? ";
				Integer  sawpNum = this.sqlDao.getJdbcTemplate().queryForObject(sawpSql, new String[]{cbdb.getCaseCode()},Integer.class);
				cbdb.setInvolvedObjectNum(sawpNum);
			}
			resultMap.put("result",cbdbLst);
		}	
		
		return SUCCESS ;
	}
	public String findMycase(){
		MySecureUser user =  SessionUserDetailsUtil.getMySecureUser();
		Map<String, Object> userMap = user.getUserMap();
		Map<String, String> mPerson = new HashMap<String, String>(0) ;
		if (userMap.get("person") != null){
			mPerson = (Map<String, String>) userMap.get("person");
			List<CriminalBasicCaseDetailBean> cbdbLst = queryCriminalBasicCasesByPolice(mPerson.get("code"));
			if(null != cbdbLst && cbdbLst.size() >0 ){
				for(CriminalBasicCaseDetailBean cbdb : cbdbLst){
					String sql = "select count(*) from t_xsajfx_xyrgx t where  t.ajbh = ? ";
					Integer  xyrNum = this.sqlDao.getJdbcTemplate().queryForObject(sql, new String[]{cbdb.getCaseCode()},Integer.class);
					List<InfoSnapshot> isList = infoSnapshotService.findInfoSnapshotByTargetIdAndTypeAndCode(cbdb.getCaseCode(),
							CriminalBasicCase.class.getName(), INFO_SNAPSHOT_MODULE.XSAJFX_XYRMACFX.getValue());
					cbdb.setXyrNum(xyrNum);
					if(isList != null){
						cbdb.setAnalSnopNum(isList.size());
					}else{
						cbdb.setAnalSnopNum(0);
					}
					String sawpSql = "select count(*) from t_xsajfx_sawp_ajgl t where  t.ajbh = ? ";
					Integer  sawpNum = this.sqlDao.getJdbcTemplate().queryForObject(sawpSql, new String[]{cbdb.getCaseCode()},Integer.class);
					cbdb.setInvolvedObjectNum(sawpNum);
				}
				resultMap.put("result",cbdbLst);
			}
		}
		return SUCCESS;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<YaydBean> getYaydBeans() {
		return yaydBeans;
	}

	public void setYaydBeans(List<YaydBean> yaydBeans) {
		this.yaydBeans = yaydBeans;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public List<CriminalBasicCase> getCriminalBasicCaseList() {
		return criminalBasicCaseList;
	}

	public void setCriminalBasicCaseList(List<CriminalBasicCase> criminalBasicCaseList) {
		this.criminalBasicCaseList = criminalBasicCaseList;
	}
	@SuppressWarnings("unchecked")
	private List<CriminalBasicCaseDetailBean>  queryCriminalBasicCasesByCodeOrName(String nameOrCode){
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String  sql = "select t.fxsjqs,t.ajmc,t.ajbh,t.fadd, t.ajlbmc from t_xsajfx_ajjbxx t where  (t.ajbh like ?  escape '/' or t.ajmc like ? escape '/') ";
		List<CriminalBasicCaseDetailBean> isList = (List<CriminalBasicCaseDetailBean>) this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, "%"+nameOrCode+"%");
						ps.setString(2, "%"+nameOrCode+"%");
					}

				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CriminalBasicCaseDetailBean> list = new ArrayList<CriminalBasicCaseDetailBean>();
						while (rs.next()) {
							CriminalBasicCaseDetailBean cbdb = new CriminalBasicCaseDetailBean();
							cbdb.setCaseName(rs.getString("ajmc"));
							cbdb.setCaseCode(rs.getString("ajbh"));
							Date  temp = rs.getDate("fxsjqs");
							if(temp != null){
								cbdb.setCaseTimeStart(sdf.format(temp));
							}
							cbdb.setCaseAddress(rs.getString("fadd"));
							cbdb.setCaseSort(rs.getString("ajlbmc"));
							list.add(cbdb);
						}
						return list;
					}
				});
		
		return isList;
	}
	@SuppressWarnings("unchecked")
	private List<CriminalBasicCaseDetailBean>  queryCriminalBasicCasesByPolice(String code){
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String  sql = "select t.fxsjqs,t.ajmc,t.ajbh,t.fadd, t.ajlbmc from t_xsajfx_ajjbxx t where  t.bar1jh = ? or t.bar2jh = ?";
		List<CriminalBasicCaseDetailBean> isList = (List<CriminalBasicCaseDetailBean>) this.sqlDao.getJdbcTemplate().query(sql,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, code);
						ps.setString(2, code);
					}

				}, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<CriminalBasicCaseDetailBean> list = new ArrayList<CriminalBasicCaseDetailBean>();
						while (rs.next()) {
							CriminalBasicCaseDetailBean cbdb = new CriminalBasicCaseDetailBean();
							cbdb.setCaseName(rs.getString("ajmc"));
							cbdb.setCaseCode(rs.getString("ajbh"));
							Date  temp = rs.getDate("fxsjqs");
							if(temp != null){
								cbdb.setCaseTimeStart(sdf.format(temp));
							}
							cbdb.setCaseAddress(rs.getString("fadd"));
							cbdb.setCaseSort(rs.getString("ajlbmc"));
							list.add(cbdb);
						}
						return list;
					}
				});
		
		return isList;
	}
}
