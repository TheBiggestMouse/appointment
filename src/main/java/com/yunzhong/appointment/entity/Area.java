package com.yunzhong.appointment.entity;

public class Area {
    private String areaId;

    private String areaName;

    private String cityId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaName=" + areaName + ", cityId=" + cityId + "]";
	}
}