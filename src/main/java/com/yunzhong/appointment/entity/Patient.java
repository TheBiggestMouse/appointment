package com.yunzhong.appointment.entity;

import java.util.Date;

public class Patient {

	private String patientId;

    private String userId;
    
    private String userName;

    private String patientName;

    private String patientTel;

    private String patientSex;

    private String patientUid;

    private Date patientBirth;

    private String provinceId;

    private String provinceName;

    private String cityId;

    private String cityName;

    private String areaId;

    private String areaName;


	private Date patientTime;

    private String standby;
    
    private String patientPic;
	
    


    
    public String getPatientPic() {
    	return patientPic;
    }
    
    public void setPatientPic(String patientPic) {
    	this.patientPic = patientPic;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientTel() {
        return patientTel;
    }

    public void setPatientTel(String patientTel) {
        this.patientTel = patientTel == null ? null : patientTel.trim();
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex == null ? null : patientSex.trim();
    }

    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid == null ? null : patientUid.trim();
    }

    public Date getPatientBirth() {
        return patientBirth;
    }

    public void setPatientBirth(Date patientBirth) {
        this.patientBirth = patientBirth;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Date getPatientTime() {
        return patientTime;
    }

    public void setPatientTime(Date patientTime) {
        this.patientTime = patientTime;
    }

    public String getStandby() {
        return standby;
    }

    public void setStandby(String standby) {
        this.standby = standby == null ? null : standby.trim();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", userId=" + userId
				+ ", userName=" + userName + ", patientName=" + patientName
				+ ", patientTel=" + patientTel + ", patientSex=" + patientSex
				+ ", patientUid=" + patientUid + ", patientBirth="
				+ patientBirth + ", provinceId=" + provinceId
				+ ", provinceName=" + provinceName + ", cityId=" + cityId
				+ ", cityName=" + cityName + ", areaId=" + areaId
				+ ", areaName=" + areaName + ", patientTime=" + patientTime
				+ ", standby=" + standby + ", patientPic=" + patientPic + "]";
	}

	


}