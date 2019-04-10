package com.yunzhong.appointment.entity;

public class Hostipal {
    private String hostipalId;

    private String hostipalName;

    private String hostipalInfo;

    private String hostipalPic;

    private String hostipalAddresspic;

    public String getHostipalId() {
        return hostipalId;
    }

    public void setHostipalId(String hostipalId) {
        this.hostipalId = hostipalId == null ? null : hostipalId.trim();
    }

    public String getHostipalName() {
        return hostipalName;
    }

    public void setHostipalName(String hostipalName) {
        this.hostipalName = hostipalName == null ? null : hostipalName.trim();
    }

    public String getHostipalInfo() {
        return hostipalInfo;
    }

    public void setHostipalInfo(String hostipalInfo) {
        this.hostipalInfo = hostipalInfo == null ? null : hostipalInfo.trim();
    }

    public String getHostipalPic() {
        return hostipalPic;
    }

    public void setHostipalPic(String hostipalPic) {
        this.hostipalPic = hostipalPic == null ? null : hostipalPic.trim();
    }

    public String getHostipalAddresspic() {
        return hostipalAddresspic;
    }

    public void setHostipalAddresspic(String hostipalAddresspic) {
        this.hostipalAddresspic = hostipalAddresspic == null ? null : hostipalAddresspic.trim();
    }

	@Override
	public String toString() {
		return "Hostipal [hostipalId=" + hostipalId + ", hostipalName=" + hostipalName + ", hostipalInfo="
				+ hostipalInfo + ", hostipalPic=" + hostipalPic + ", hostipalAddresspic=" + hostipalAddresspic + "]";
	}
}