package com.yunzhong.appointment.entity;

public class Professional {
    private String professionalId;

    private String dpId;

    private String professionalName;

    private Double professionalFee;

    public String getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId == null ? null : professionalId.trim();
    }

    public String getDpId() {
        return dpId;
    }

    public void setDpId(String dpId) {
        this.dpId = dpId == null ? null : dpId.trim();
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName == null ? null : professionalName.trim();
    }

    public Double getProfessionalFee() {
        return professionalFee;
    }

    public void setProfessionalFee(Double professionalFee) {
        this.professionalFee = professionalFee;
    }

	@Override
	public String toString() {
		return "Professional [professionalId=" + professionalId + ", dpId=" + dpId + ", professionalName="
				+ professionalName + ", professionalFee=" + professionalFee + "]";
	}
}