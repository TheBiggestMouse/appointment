package com.yunzhong.appointment.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Appointmentorder {
    private String appointmentId;

    private Date appointmentTime;

    private String orderState;

    private String payType;

    private String paytypeOrderid;

    private String patientId;

    private String patientName;

    private String doctorId;

    private String doctorName;

    private String professionalId;

    private String professionalName;

    private String departmentId;

    private String departmentName;

    private String illnessId;

    private String illnessName;

    private Date appointmentDate;

    private Double registeredfee;


    private String standby;

    
    public String getNian() {
		return nian;
	}

	public void setNian(String nian) {
		this.nian = nian;
	}

	private String nian;
    
    public String getYycs() {
		return yycs;
	}

	public void setYycs(String yycs) {
		this.yycs = yycs;
	}

	private String  yycs;

    /**
     * 用于医生预约次数统计的字段
     */
    private BigDecimal docTimesCount;

 
    private String countmnn;
    
    private String nm;

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getCountmnn() {
		return countmnn;
	}

	public void setCountmnn(String countmnn) {
		this.countmnn = countmnn;
	}


    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId == null ? null : appointmentId.trim();
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPaytypeOrderid() {
        return paytypeOrderid;
    }

    public void setPaytypeOrderid(String paytypeOrderid) {
        this.paytypeOrderid = paytypeOrderid == null ? null : paytypeOrderid.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId == null ? null : professionalId.trim();
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName == null ? null : professionalName.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(String illnessId) {
        this.illnessId = illnessId == null ? null : illnessId.trim();
    }

    public String getIllnessName() {
        return illnessName;
    }

    public void setIllnessName(String illnessName) {
        this.illnessName = illnessName == null ? null : illnessName.trim();
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Double getRegisteredfee() {
        return registeredfee;
    }

    public void setRegisteredfee(Double registeredfee) {
        this.registeredfee = registeredfee;
    }

    public String getStandby() {
        return standby;
    }

    public void setStandby(String standby) {
        this.standby = standby == null ? null : standby.trim();
    }
	public BigDecimal getDocTimesCount() {
		return docTimesCount;
	}

	public void setDocTimesCount(BigDecimal docTimesCount) {
		this.docTimesCount = docTimesCount;
	}

	@Override
	public String toString() {
		return "Appointmentorder [appointmentId=" + appointmentId
				+ ", appointmentTime=" + appointmentTime + ", orderState="
				+ orderState + ", payType=" + payType + ", paytypeOrderid="
				+ paytypeOrderid + ", patientId=" + patientId
				+ ", patientName=" + patientName + ", doctorId=" + doctorId
				+ ", doctorName=" + doctorName + ", professionalId="
				+ professionalId + ", professionalName=" + professionalName
				+ ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", illnessId=" + illnessId
				+ ", illnessName=" + illnessName + ", appointmentDate="
				+ appointmentDate + ", registeredfee=" + registeredfee
				+ ", standby=" + standby + ", nian=" + nian + ", yycs=" + yycs
				+ ", docTimesCount=" + docTimesCount + ", countmnn=" + countmnn
				+ ", nm=" + nm + "]";
	}


	

	
	




}