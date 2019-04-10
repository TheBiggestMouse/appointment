package com.yunzhong.appointment.entity;

public class Illness {
    private String illId;

    private String illtpId;

    private String illName;

    private String illInfo;

    public String getIllId() {
        return illId;
    }

    public void setIllId(String illId) {
        this.illId = illId == null ? null : illId.trim();
    }

    public String getIlltpId() {
        return illtpId;
    }

    public void setIlltpId(String illtpId) {
        this.illtpId = illtpId == null ? null : illtpId.trim();
    }

    public String getIllName() {
        return illName;
    }

    public void setIllName(String illName) {
        this.illName = illName == null ? null : illName.trim();
    }

    public String getIllInfo() {
        return illInfo;
    }

    public void setIllInfo(String illInfo) {
        this.illInfo = illInfo == null ? null : illInfo.trim();
    }

	@Override
	public String toString() {
		return "Illness [illId=" + illId + ", illtpId=" + illtpId + ", illName=" + illName + ", illInfo=" + illInfo
				+ "]";
	}
}