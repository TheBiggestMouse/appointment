package com.yunzhong.appointment.entity;

import java.util.List;


public class Illnessposition {
    private String illtpId;

    private String illtpName;
    //  康磊    方便查询 增加字段   2017年11月1日 15:12:01
    private List<Illness> illList;
    

    public List<Illness> getIllList() {
		return illList;
	}

	public void setIllList(List<Illness> illList) {
		this.illList = illList;
	}

	public String getIlltpId() {
        return illtpId;
    }

    public void setIlltpId(String illtpId) {
        this.illtpId = illtpId == null ? null : illtpId.trim();
    }

    public String getIlltpName() {
        return illtpName;
    }

    public void setIlltpName(String illtpName) {
        this.illtpName = illtpName == null ? null : illtpName.trim();
    }

	@Override
	public String toString() {
		return "Illnessposition [illtpId=" + illtpId + ", illtpName="
				+ illtpName + ", illList=" + illList + "]";
	}


}