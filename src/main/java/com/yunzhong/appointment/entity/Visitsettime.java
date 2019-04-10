package com.yunzhong.appointment.entity;

import java.util.Date;

public class Visitsettime {
    private String visitstId;

    private String doctorId;

    private String visitPlan;

    private String visitMoncount;

    private String visitTuescount;

    private String visitWedcount;

    private String visitThurcount;

    private String visitFricount;

    private String visitSatcount;

    private String visitSuncount;

    private Date visitTime;

    private String standby;
    
    /**
     * 作者: 郎国峰
     * 时间: 2017年11月11日07:19:21
     * 描述: 自动排班人员姓名
     */
    private String ppName;
    /**
     * 作者: 郎国峰
     * 时间: 2017年11月11日07:20:47
     * 描述: 自动排班人员所属科室id
     */
    private String departmentId;
    /**
     * 作者: 郎国峰
     * 时间: 2017年11月11日07:21:33
     * 描述: 自动排班人员所属科室名称
     */
	private String departmentName;
    
    public String getVisitstId() {
        return visitstId;
    }

    public void setVisitstId(String visitstId) {
        this.visitstId = visitstId == null ? null : visitstId.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getVisitPlan() {
        return visitPlan;
    }

    public void setVisitPlan(String visitPlan) {
        this.visitPlan = visitPlan == null ? null : visitPlan.trim();
    }

    public String getVisitMoncount() {
        return visitMoncount;
    }

    public void setVisitMoncount(String visitMoncount) {
        this.visitMoncount = visitMoncount == null ? null : visitMoncount.trim();
    }

    public String getVisitTuescount() {
        return visitTuescount;
    }

    public void setVisitTuescount(String visitTuescount) {
        this.visitTuescount = visitTuescount == null ? null : visitTuescount.trim();
    }

    public String getVisitWedcount() {
        return visitWedcount;
    }

    public void setVisitWedcount(String visitWedcount) {
        this.visitWedcount = visitWedcount == null ? null : visitWedcount.trim();
    }

    public String getVisitThurcount() {
        return visitThurcount;
    }

    public void setVisitThurcount(String visitThurcount) {
        this.visitThurcount = visitThurcount == null ? null : visitThurcount.trim();
    }

    public String getVisitFricount() {
        return visitFricount;
    }

    public void setVisitFricount(String visitFricount) {
        this.visitFricount = visitFricount == null ? null : visitFricount.trim();
    }

    public String getVisitSatcount() {
        return visitSatcount;
    }

    public void setVisitSatcount(String visitSatcount) {
        this.visitSatcount = visitSatcount == null ? null : visitSatcount.trim();
    }

    public String getVisitSuncount() {
        return visitSuncount;
    }

    public void setVisitSuncount(String visitSuncount) {
        this.visitSuncount = visitSuncount == null ? null : visitSuncount.trim();
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getStandby() {
        return standby;
    }

    public void setStandby(String standby) {
        this.standby = standby == null ? null : standby.trim();
    }
    
	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
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

	@Override
	public String toString() {
		return "Visitsettime [visitstId=" + visitstId + ", doctorId="
				+ doctorId + ", visitPlan=" + visitPlan + ", visitMoncount="
				+ visitMoncount + ", visitTuescount=" + visitTuescount
				+ ", visitWedcount=" + visitWedcount + ", visitThurcount="
				+ visitThurcount + ", visitFricount=" + visitFricount
				+ ", visitSatcount=" + visitSatcount + ", visitSuncount="
				+ visitSuncount + ", visitTime=" + visitTime + ", standby="
				+ standby + ", ppName=" + ppName + ", departmentId="
				+ departmentId + ", departmentName=" + departmentName + "]";
	}
	
	
}