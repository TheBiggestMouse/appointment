package com.yunzhong.appointment.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.SysMenu;
import com.yunzhong.appointment.entity.SysRole;
import com.yunzhong.appointment.entity.SysRoleMenu;
import com.yunzhong.appointment.entity.SysUserRole;
import com.yunzhong.appointment.mapper.SysMenuMapper;
import com.yunzhong.appointment.mapper.SysRoleMapper;
import com.yunzhong.appointment.mapper.SysRoleMenuMapper;
import com.yunzhong.appointment.mapper.SysUserRoleMapper;
import com.yunzhong.appointment.system.service.SysRoleService;
import com.yunzhong.appointment.util.PageData;
import com.yunzhong.appointment.util.SelectOptions;
/**
 * 
* @ClassName: SysRoleServiceImpl 
* @Description: 角色 模型层实现类
* @author 康磊 
* @date 2017年10月27日 上午11:08:17 
*
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleMapper mapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public List<SysRole> listRole(PageData pd) {
		pd.setPageData(pd);
		return mapper.listrole(pd);
	}

	@Override
	public SysRole getroleById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdateRole(SysRole role) {
		if("".equals(role.getRoleId())){
			role.setRoleId(UUID.randomUUID().toString());
			mapper.insertSelective(role);
		}else{
			mapper.updateByPrimaryKeySelective(role);
		}	
	}

	@Override
	public void removeOneRole(String[] ids) {
		for (String id : ids) {
			sysRoleMenuMapper.removeOneRoleMenu(id);
			sysUserRoleMapper.removeOneUserRole(id);
			mapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<SelectOptions> listMenuByRoleId(String id) {
		//得到角色关联的菜单主键
		List<String> menuRoleList = sysMenuMapper.listMenuByUserId(id);
		//得到所有菜单
		List<SysMenu> menuList = sysMenuMapper.listMenu();
		List<SelectOptions> opts = new ArrayList<SelectOptions>();
		for (SysMenu menu : menuList) {
			SelectOptions opt = new SelectOptions();
			opt.setLabel(menu.getMenuId());
			opt.setValue(menu.getMenuName());
			if(menuRoleList.contains(menu.getMenuId())){
				opt.setSelected(true);
			}else{
				opt.setSelected(false);
			}
			opts.add(opt);
		}
		return opts;
	}

	@Override
	public void updateAuthRole(String menuIds, String roleId) {
		//删除原有用户角色信息
		sysRoleMenuMapper.removeOneRoleMenu(roleId);
				//增加新的用户角色信息
				String[] menuids = menuIds.split("@");
				for (String menuId : menuids) {
					SysRoleMenu rm = new SysRoleMenu();
					rm.setRmId(UUID.randomUUID().toString());
					rm.setRoleId(roleId);
					rm.setMenuId(menuId);
					sysRoleMenuMapper.insertSelective(rm);
				}
	}
	
	@Override
	public String verifyName(String roleName) {
		String str = mapper.selectByRoleName(roleName);
		return str ;
	}

	@Override
	public void roleMenu(String roleId, String menuIds) {
		// TODO Auto-generated method stub
		sysRoleMenuMapper.removeOneRoleMenu(roleId);
		if(!"".equals(menuIds)){
			String[] ids = menuIds.split("@");
			for (int i = 0; i < ids.length; i++) {
				SysRoleMenu srm = new SysRoleMenu();
				srm.setRmId(UUID.randomUUID().toString());
				srm.setMenuId(ids[i]);
				srm.setRoleId(roleId);
				sysRoleMenuMapper.insertSelective(srm);
			}
		}
		
	}

	
}
