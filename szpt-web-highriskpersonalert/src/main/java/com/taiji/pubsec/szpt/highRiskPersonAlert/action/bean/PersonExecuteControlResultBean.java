package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

import java.util.List;


/**
 * 人员布控结果
 */
public class PersonExecuteControlResultBean {

    private String id;

    private String catchObject; //捕捉对象

    private Long catchTime; //捕捉时间

    private String catchContent;    //捕捉内容

    private String targetType;  //目标类型

    private String targetTypeId;    //目标类型id

    private Double catchLongitude;  //捕捉经度

    private Double catchLatitude;   //捕捉维度
    
    private String imgBase64 ;
    
    private String catchDetail ; //捕捉详情，json字符串
    
    private String resultStatus ;
    
    private String surveilListNum ;
    
    private String type ;
    
    private String clueId ;
    
    private String clueType;

    public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getCatchDetail() {
		return catchDetail;
	}

	public void setCatchDetail(String catchDetail) {
		this.catchDetail = catchDetail;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatchObject() {
        return catchObject;
    }

    public void setCatchObject(String catchObject) {
        this.catchObject = catchObject;
    }

    public String getCatchContent() {
        return catchContent;
    }

    public void setCatchContent(String catchContent) {
        this.catchContent = catchContent;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetTypeId() {
        return targetTypeId;
    }

    public void setTargetTypeId(String targetTypeId) {
        this.targetTypeId = targetTypeId;
    }

	public Double getCatchLongitude() {
		return catchLongitude;
	}

	public void setCatchLongitude(Double catchLongitude) {
		this.catchLongitude = catchLongitude;
	}

	public Double getCatchLatitude() {
		return catchLatitude;
	}

	public void setCatchLatitude(Double catchLatitude) {
		this.catchLatitude = catchLatitude;
	}

	public Long getCatchTime() {
		return catchTime;
	}

	public void setCatchTime(Long catchTime) {
		this.catchTime = catchTime;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public String getSurveilListNum() {
		return surveilListNum;
	}

	public void setSurveilListNum(String surveilListNum) {
		this.surveilListNum = surveilListNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClueId() {
		return clueId;
	}

	public void setClueId(String clueId) {
		this.clueId = clueId;
	}

	public String getClueType() {
		return clueType;
	}

	public void setClueType(String clueType) {
		this.clueType = clueType;
	}


}
