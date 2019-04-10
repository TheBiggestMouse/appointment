package com.yunzhong.appointment.entity;

public class Const {
    private String constId;
    
    private String constVal;

	public String getConstId() {
		return constId;
	}

	public void setConstId(String constId) {
		this.constId = constId;
	}

	public String getConstVal() {
		return constVal;
	}

	public void setConstVal(String constVal) {
		this.constVal = constVal;
	}

	@Override
	public String toString() {
		return "Const [constId=" + constId + ", constVal=" + constVal + "]";
	}

    
}