package com.yunzhong.appointment.util;
/**
 * 
 * @className SelectOptions
 * @description 用于页面的下拉
 * @author 石洪刚
 * @time 2017年10月6日 上午8:11:36
 */
public class SelectOptions {

	private String label;
	private String value;
	private Boolean selected;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "SelectOptions [label=" + label + ", value=" + value
				+ ", selected=" + selected + "]";
	}
}
