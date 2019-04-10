package com.yunzhong.appointment.entity;

import java.util.ArrayList;
import java.util.List;

public class SysMenu {
    private String menuId;

    private String sysMenuId;

    private String menuName;

    private String menuUrl;

    private String menuIslink;

    private String menuSequ;

    private String menuState;

    private String menuInfo;
    
    private String parMenuName;
    
    private List childrenMenuList = new ArrayList();

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(String sysMenuId) {
        this.sysMenuId = sysMenuId == null ? null : sysMenuId.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuIslink() {
        return menuIslink;
    }

    public void setMenuIslink(String menuIslink) {
        this.menuIslink = menuIslink == null ? null : menuIslink.trim();
    }

    public String getMenuSequ() {
        return menuSequ;
    }

    public void setMenuSequ(String menuSequ) {
        this.menuSequ = menuSequ == null ? null : menuSequ.trim();
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState == null ? null : menuState.trim();
    }

    public String getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo == null ? null : menuInfo.trim();
    }

	public List getChildrenMenuList() {
		return childrenMenuList;
	}

	public void setChildrenMenuList(List childrenMenuList) {
		this.childrenMenuList = childrenMenuList;
	}

	@Override
	public String toString() {
		return "SysMenu [menuId=" + menuId + ", sysMenuId=" + sysMenuId + ", menuName=" + menuName + ", menuUrl="
				+ menuUrl + ", menuIslink=" + menuIslink + ", menuSequ=" + menuSequ + ", menuState=" + menuState
				+ ", menuInfo=" + menuInfo + ", childrenMenuList=" + childrenMenuList + "]";
	}

	public String getParMenuName() {
		return parMenuName;
	}

	public void setParMenuName(String parMenuName) {
		this.parMenuName = parMenuName;
	}

	
}