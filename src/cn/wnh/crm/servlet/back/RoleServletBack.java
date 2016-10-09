package cn.mldn.crm.servlet.back;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;
import cn.mldn.crm.vo.Groups;
import cn.mldn.crm.vo.Role;

@SuppressWarnings("serial")
@WebServlet("/pages/back/role/RoleServletBack/*")
public class RoleServletBack extends AbstractCRMServlet {
	private Role role = new Role();

	public String addPre() {
		if (super.isAction(19)) {
			try {
				Map<String, Object> map = ServiceFactory
						.getIRoleServiceBackInstance().addPre(super.getMid());
				super.request.setAttribute("allGroupses",
						map.get("allGroupses"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "role.add.page";
		} else {
			return "error.page";
		}
	}

	public String editPre() {
		if (super.isAction(21)) {
			try {
				int rid = Integer.parseInt(super.getParameter("rid")) ;
				Map<String, Object> map = ServiceFactory
						.getIRoleServiceBackInstance().editPre(super.getMid(),rid);
				super.request.setAttribute("allGroupses",
						map.get("allGroupses"));
				super.request.setAttribute("role",map.get("role") );
				super.request.setAttribute("roleGroups", map.get("roleGroups"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "role.edit.page";
		} else {
			return "error.page";
		}
	}
	
	public String rm() {
		if (super.isAction(22)) {
			try {
				String ids = super.getParameter("ids") ;
				Set<Integer> set = new HashSet<Integer>() ;
				String result [] = ids.split("\\|") ;
				for (int x = 0 ; x < result.length ; x ++) {
					set.add(Integer.parseInt(result[x])) ;
				}
				if (ServiceFactory.getIRoleServiceBackInstance().rm(
						super.getMid(), set)) {
					super.setMsgAndUrl("vo.rm.success", "role.list.servlet");
				} else {
					super.setMsgAndUrl("vo.rm.failure", "role.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.rm.failure", "role.add.servlet");
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}
	
	public String edit() {
		if (super.isAction(21)) {
			String gids[] = super.getParameterValues("gid"); // 取得表单的gid数据
			List<Groups> all = new ArrayList<Groups>();
			for (int x = 0; x < gids.length; x++) {
				Groups g = new Groups();
				g.setGid(Integer.parseInt(gids[x]));
				all.add(g);
			}
			this.role.setGroups(all); // 一个角色对应多个权限组信息
			try {
				if (ServiceFactory.getIRoleServiceBackInstance().edit(
						super.getMid(), this.role)) {
					super.setMsgAndUrl("vo.edit.success", "role.list.servlet");
				} else {
					super.setMsgAndUrl("vo.edit.failure", "role.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "role.add.servlet");
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}
	
	public String show() {
		if (super.isAction(34)) {
			try {
				int rid = Integer.parseInt(super.getParameter("rid")) ;
				request.setAttribute("role", ServiceFactory.getIRoleServiceBackInstance().show(super.getMid(), rid));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "role.show.page";
		} else {
			return "error.page";
		}
	}

	public String add() {
		if (super.isAction(19)) {
			String gids[] = super.getParameterValues("gid"); // 取得表单的gid数据
			List<Groups> all = new ArrayList<Groups>();
			for (int x = 0; x < gids.length; x++) {
				Groups g = new Groups();
				g.setGid(Integer.parseInt(gids[x]));
				all.add(g);
			}
			this.role.setGroups(all); // 一个角色对应多个权限组信息
			try {
				if (ServiceFactory.getIRoleServiceBackInstance().add(
						super.getMid(), this.role)) {
					super.setMsgAndUrl("vo.add.success", "role.add.servlet");
				} else {
					super.setMsgAndUrl("vo.add.failure", "role.add.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "role.add.servlet");
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}

	public String list() {
		if (super.isAction(20)) {
			try {
				request.setAttribute("allRoles", ServiceFactory
						.getIRoleServiceBackInstance().list(super.getMid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "role.list.page";
		} else {
			return "error.page";
		}
	}

	public Role getRole() {
		return role;
	}

	@Override
	public String getMarkTitle() {
		return "角色";
	}

	@Override
	public String getUploadDir() {
		return null;
	}

	@Override
	public String getDefaultColumn() {
		return null;
	}

	@Override
	public String getColumntData() {
		return null;
	}

}
