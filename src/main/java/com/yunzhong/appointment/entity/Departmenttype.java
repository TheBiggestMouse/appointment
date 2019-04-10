package com.yunzhong.appointment.entity;

import java.util.List;


public class Departmenttype {
    private String dplId;

    private String dplName;
    
    private List<Department> dpList;
    

    public List<Department> getDpList() {
		return dpList;
	}

	public void setDpList(List<Department> dpList) {
		this.dpList = dpList;
	}

	public String getDplId() {
        return dplId;
    }

    public void setDplId(String dplId) {
        this.dplId = dplId == null ? null : dplId.trim();
    }

    public String getDplName() {
        return dplName;
    }

    public void setDplName(String dplName) {
        this.dplName = dplName == null ? null : dplName.trim();
    }

	@Override
	public String toString() {
		return "Departmenttype [dplId=" + dplId + ", dplName=" + dplName
				+ ", dpList=" + dpList + "]";
	}

	


	
}