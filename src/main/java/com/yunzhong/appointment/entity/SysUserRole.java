package com.yunzhong.appointment.entity;

public class SysUserRole {
    private String urId;

    private String userId;

    private String roleId;

    public String getUrId() {
        return urId;
    }

    public void setUrId(String urId) {
        this.urId = urId == null ? null : urId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

	@Override
	public String toString() {
		return "SysUserRole [urId=" + urId + ", userId=" + userId + ", roleId=" + roleId + "]";
	}
}