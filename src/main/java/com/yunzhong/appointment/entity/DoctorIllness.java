package com.yunzhong.appointment.entity;

public class DoctorIllness {
    private String docillId;

    private String illId;

    private String doctorId;

    public String getDocillId() {
        return docillId;
    }

    public void setDocillId(String docillId) {
        this.docillId = docillId == null ? null : docillId.trim();
    }

    public String getIllId() {
        return illId;
    }

    public void setIllId(String illId) {
        this.illId = illId == null ? null : illId.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

	@Override
	public String toString() {
		return "DoctorIllness [docillId=" + docillId + ", illId=" + illId + ", doctorId=" + doctorId + "]";
	}
}