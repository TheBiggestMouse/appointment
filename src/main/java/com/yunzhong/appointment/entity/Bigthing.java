package com.yunzhong.appointment.entity;

import java.util.Date;

public class Bigthing {
    private String bigthingId;

    private Date bigthingTime;

    private String bigthingTitle;

    private String bigthingContent;

    public String getBigthingId() {
        return bigthingId;
    }

    public void setBigthingId(String bigthingId) {
        this.bigthingId = bigthingId == null ? null : bigthingId.trim();
    }

    public Date getBigthingTime() {
        return bigthingTime;
    }

    public void setBigthingTime(Date bigthingTime) {
        this.bigthingTime = bigthingTime;
    }

    public String getBigthingTitle() {
        return bigthingTitle;
    }

    public void setBigthingTitle(String bigthingTitle) {
        this.bigthingTitle = bigthingTitle == null ? null : bigthingTitle.trim();
    }

    public String getBigthingContent() {
        return bigthingContent;
    }

    public void setBigthingContent(String bigthingContent) {
        this.bigthingContent = bigthingContent == null ? null : bigthingContent.trim();
    }

	@Override
	public String toString() {
		return "Bigthing [bigthingId=" + bigthingId + ", bigthingTime=" + bigthingTime + ", bigthingTitle="
				+ bigthingTitle + ", bigthingContent=" + bigthingContent + "]";
	}
}