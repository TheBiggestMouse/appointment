package com.yunzhong.appointment.entity;

public class SysRoleMenu {
    private String rmId;

    private String roleId;

    private String menuId;

    public String getRmId() {
        return rmId;
    }

    public void setRmId(String rmId) {
        this.rmId = rmId == null ? null : rmId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

	@Override
	public String toString() {
		return "SysRoleMenu [rmId=" + rmId + ", roleId=" + roleId + ", menuId=" + menuId + "]";
	}
}