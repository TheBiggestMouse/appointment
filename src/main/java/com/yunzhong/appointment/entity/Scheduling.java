package com.yunzhong.appointment.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Scheduling {
    private String schedulingId;

    private String doctorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date schedulingDate;

    private String time1;

    private String time2;

    private String time3;

    private String time4;

    private String time5;

    private String time6;

    private Integer count1;

    private Integer count2;

    private Integer count3;

    private Integer count4;

    private Integer count5;

    private Integer count6;

    private String standby;
    /**
     * 医生姓名
     */
    private String ppName;
    /**
     * 科室名称
     */
    private String departmentName;
    
    public String getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(String schedulingId) {
        this.schedulingId = schedulingId == null ? null : schedulingId.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public Date getSchedulingDate() {
        return schedulingDate;
    }

    public void setSchedulingDate(Date schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1 == null ? null : time1.trim();
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2 == null ? null : time2.trim();
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3 == null ? null : time3.trim();
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4 == null ? null : time4.trim();
    }

    public String getTime5() {
        return time5;
    }

    public void setTime5(String time5) {
        this.time5 = time5 == null ? null : time5.trim();
    }

    public String getTime6() {
        return time6;
    }

    public void setTime6(String time6) {
        this.time6 = time6 == null ? null : time6.trim();
    }

    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

    public Integer getCount2() {
        return count2;
    }

    public void setCount2(Integer count2) {
        this.count2 = count2;
    }

    public Integer getCount3() {
        return count3;
    }

    public void setCount3(Integer count3) {
        this.count3 = count3;
    }

    public Integer getCount4() {
        return count4;
    }

    public void setCount4(Integer count4) {
        this.count4 = count4;
    }

    public Integer getCount5() {
        return count5;
    }

    public void setCount5(Integer count5) {
        this.count5 = count5;
    }

    public Integer getCount6() {
        return count6;
    }

    public void setCount6(Integer count6) {
        this.count6 = count6;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Override
	public String toString() {
		return "Scheduling [schedulingId=" + schedulingId + ", doctorId="
				+ doctorId + ", schedulingDate=" + schedulingDate + ", time1="
				+ time1 + ", time2=" + time2 + ", time3=" + time3 + ", time4="
				+ time4 + ", time5=" + time5 + ", time6=" + time6 + ", count1="
				+ count1 + ", count2=" + count2 + ", count3=" + count3
				+ ", count4=" + count4 + ", count5=" + count5 + ", count6="
				+ count6 + ", standby=" + standby + ", ppName=" + ppName
				+ ", departmentName=" + departmentName + "]";
	}
}