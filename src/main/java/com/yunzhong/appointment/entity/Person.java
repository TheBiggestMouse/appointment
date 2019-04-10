package com.yunzhong.appointment.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Person {
    private String ppId;

    private String userId;

    private String ppName;

    private String ppInfo;

    private String doctorDomain;

    private String ppState;

    private Date ppTime;

    private String departmentId;

    private String departmentName;

    private String professionalId;

    private String professionalName;

    private Double doctorPay;

    private String ppPic;

    private String standby;

    /**
     * 医生出诊总次数
     */
    private BigDecimal outCallCount;
    
	public String getPpId() {
		return ppId;
	}

	public void setPpId(String ppId) {
		this.ppId = ppId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public String getPpInfo() {
		return ppInfo;
	}

	public void setPpInfo(String ppInfo) {
		this.ppInfo = ppInfo;
	}

	public String getDoctorDomain() {
		return doctorDomain;
	}

	public void setDoctorDomain(String doctorDomain) {
		this.doctorDomain = doctorDomain;
	}

	public String getPpState() {
		return ppState;
	}

	public void setPpState(String ppState) {
		this.ppState = ppState;
	}

	public Date getPpTime() {
		return ppTime;
	}

	public void setPpTime(Date ppTime) {
		this.ppTime = ppTime;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(String professionalId) {
		this.professionalId = professionalId;
	}

	public String getProfessionalName() {
		return professionalName;
	}

	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}

	public Double getDoctorPay() {
		return doctorPay;
	}

	public void setDoctorPay(Double doctorPay) {
		this.doctorPay = doctorPay;
	}

	public String getPpPic() {
		return ppPic;
	}

	public void setPpPic(String ppPic) {
		this.ppPic = ppPic;
	}

	public String getStandby() {
		return standby;
	}

	public void setStandby(String standby) {
		this.standby = standby;
	}
	public BigDecimal getOutCallCount() {
		return outCallCount;
	}

	public void setOutCallCount(BigDecimal outCallCount) {
		this.outCallCount = outCallCount;
	}

	@Override
	public String toString() {
		return "Person [ppId=" + ppId + ", userId=" + userId + ", ppName="
				+ ppName + ", ppInfo=" + ppInfo + ", doctorDomain="
				+ doctorDomain + ", ppState=" + ppState + ", ppTime=" + ppTime
				+ ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", professionalId=" + professionalId
				+ ", professionalName=" + professionalName + ", doctorPay="
				+ doctorPay + ", ppPic=" + ppPic + ", standby=" + standby
				+ ", outCallCount=" + outCallCount + "]";
	}
	

	

	

   
}