package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.businesscomponents.organization.model.Person;

/**
 * 人员布控
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_rybk")
public class PersonExecuteControl {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    @Column(name = "bh")
    private String num; //编号
    
    @ManyToOne
    @JoinColumn(name = "cjr_id")
    private Person createPerson;	//创建人
    
    @Column(name = "sfzh")
    private String idCardNo;    //身份证号

    @Column(name = "xm")
    private String personName;  //姓名

    @Column(name = "xb")
    private String sex; //性别

    @Column(name = "hjdz")
    private String residenceAddress;    //户籍地址

    @Column(name = "bkkssj")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime; //布控开始时间

    @Column(name = "bkjssj")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;   //布控结束时间

    @Column(name = "sjzt")
    private String status;  //数据字典：启用；失效

    @Column(name = "bz")
    private String note;	//备注

    @Column(name = "zxxgrxm")
    private String lastModifyPerson;    //最新修改人姓名

    @Column(name = "zxxgsj")
    private Date lastModifyTime;    //最新修改时间
    
    /**
     * 操作状态：0000000033000新增0000000033001待审批0000000033002审批通过0000000033003审批驳回
     */
    @Column(name = "czzt")
    private String operateStatus;
    
    @OneToMany(mappedBy = "personExecuteControl")
    private Set<PersonControlImg> personControlImgs = new HashSet<>() ;

    @OneToMany(mappedBy = "personExecuteControl")
    private Set<PersonMobileInfo> personMobileInfos = new HashSet<PersonMobileInfo>();   //人员布控移动端信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLastModifyPerson() {
        return lastModifyPerson;
    }

    public void setLastModifyPerson(String lastModifyPerson) {
        this.lastModifyPerson = lastModifyPerson;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

	public Set<PersonMobileInfo> getPersonMobileInfos() {
		return personMobileInfos;
	}

	public void setPersonMobileInfos(Set<PersonMobileInfo> personMobileInfos) {
		this.personMobileInfos = personMobileInfos;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public Set<PersonControlImg> getPersonControlImgs() {
		return personControlImgs;
	}

	public void setPersonControlImgs(Set<PersonControlImg> personControlImgs) {
		this.personControlImgs = personControlImgs;
	}

	public Person getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Person createPerson) {
		this.createPerson = createPerson;
	}
	
}
