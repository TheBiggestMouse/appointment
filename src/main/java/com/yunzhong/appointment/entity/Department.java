package com.yunzhong.appointment.entity;

public class Department {
    private String dpId;

    private String dplId;

    private String dpName;
   

	public String getDpId() {
        return dpId;
    }

    public void setDpId(String dpId) {
        this.dpId = dpId == null ? null : dpId.trim();
    }

    public String getDplId() {
        return dplId;
    }

    public void setDplId(String dplId) {
        this.dplId = dplId == null ? null : dplId.trim();
    }

    public String getDpName() {
        return dpName;
    }

    public void setDpName(String dpName) {
        this.dpName = dpName == null ? null : dpName.trim();
    }

	@Override
	public String toString() {
		return "Department [dpId=" + dpId + ", dplId=" + dplId + ", dpName="
				+ dpName + "]";
	}

	

	
}