package com.yunzhong.appointment.entity;

public class City {
    private String cityId;

    private String cityName;

    private String provinceId;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", provinceId=" + provinceId + "]";
	}
}